package com.design.yang.service;

import com.design.yang.AppException.AppChildrenException.EntrustIsNotExistException;
import com.design.yang.AppException.AppChildrenException.EntrustNoAuthorityException;
import com.design.yang.AppException.AppChildrenException.UserIsNotAuthenticationException;
import com.design.yang.AppException.AppException;
import com.design.yang.constantClass.Constant;
import com.design.yang.dto.EntrustInfo;
import com.design.yang.dto.EntrustTransaction;
import com.design.yang.entrustCore.Entrust;
import com.design.yang.entrustCore.EntrustDetail;
import com.design.yang.entrustCore.SamEntrust;
import com.design.yang.exceptionConfig.ExceptionConfig;
import com.design.yang.mapper.EntrustInfoMapper;
import com.design.yang.mapper.EntrustTransactionMapper;
import com.design.yang.redisService.EntrustRedisService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-19 21:52
 */
@Service
public class EntrustService {
    @Autowired
    EntrustRedisService entrustRedisService;
    @Autowired
    EntrustInfoMapper entrustInfoMapper;
    @Autowired
    EntrustTransactionMapper entrustTransactionMapper;
    @Autowired
    CurrencyBalanceService currencyBalanceService;
    @Autowired
    UserIdentityService userIdentityService;

    public Entrust getEntrust(String cur){
        Entrust entrust = entrustRedisService.get("entrust_"+cur);
        if(entrust == null) return new Entrust();
        else return entrust;
    }

    public void setEntrust(String cur,Entrust entrust){
        entrustRedisService.add("entrust_"+cur,entrust);
    }

    @Transactional
    public void doTra(EntrustInfo info,List<EntrustDetail> transactions){
        Double com = Double.valueOf(0);
        Double act = Double.valueOf(0);
        List<EntrustTransaction> transactions1 = new ArrayList<>();
        int op = Constant.BUY_DIRECTION.equals(info.getDirection()) ? 0 : 1;
        for(EntrustDetail transaction : transactions){
            EntrustTransaction tr = transaction.getTransaction();
            EntrustInfo in = transaction.getInfo();
            Double num = tr.getNumber();//交易货币数量
            Double epri = in.getPrice();//设想交易价格
            Double pri = tr.getPrice();//实际交易USDT价格
            act = act + (num*pri);//USDT总价
            com += num;
            entrustInfoMapper.updateEntrustInfoCompleted(tr.getTraderId(),num);
            //主交易为买,副交易卖,卖方增加USDT余额,减少交易货币余额,并解除交易份额的锁仓
            if(op == 0){
                currencyBalanceService.completeSellTransaction(in.getUserId(),Constant.BASE_CURRENCY,pri*num);//增加USDT余额
                currencyBalanceService.completeBuyTransaction(in.getUserId(),in.getPairs(),num,num);//减少交易货币余额,解除交易数锁仓
            }else {//主交易为卖,副交易买,买方减少USDT余额并解除交易USDT的锁仓
                currencyBalanceService.completeSellTransaction(in.getUserId(),in.getPairs(),num);//增加交易货币余额
                currencyBalanceService.completeBuyTransaction(in.getUserId(),Constant.BASE_CURRENCY,epri*num,pri*num);//减少USDT余额,解除以预订价*数量的锁仓
            }
            transactions1.add(tr);
        }
        if(transactions1.size() > 0 ) entrustTransactionMapper.insertTransaction(transactions1);

        //主交易为买,副交易卖,买方减少USDT余额并解除交易USDT的锁仓
        if(op == 0){
            currencyBalanceService.completeSellTransaction(info.getUserId(),info.getPairs(),com);//增加交易货币余额
            currencyBalanceService.completeBuyTransaction(info.getUserId(),Constant.BASE_CURRENCY,info.getPrice()*com,act);//减少USDT余额,解除以预订价*数量的锁仓
        }else {//主交易为卖,副交易买,卖方增加USDT余额,减少交易货币余额,并解除交易份额的锁仓
            currencyBalanceService.completeSellTransaction(info.getUserId(),Constant.BASE_CURRENCY,act);//增加USDT余额
            currencyBalanceService.completeBuyTransaction(info.getUserId(),info.getPairs(),com,com);//减少交易货币余额,解除交易量锁仓
        }
        entrustInfoMapper.updateEntrustInfoCompleted(info.getEntrustId(),com);
    }

    private boolean checkEnt(EntrustInfo info){
        if(info.getNumber() == null)
            throw new AppException(ExceptionConfig.Entrust_Number_Is_Null,ExceptionConfig.Entrust_Number_Is_Null_Msg);
        if(info.getPrice() == null)
            throw new AppException(ExceptionConfig.Entrust_Price_Is_Null,ExceptionConfig.Entrust_Price_Is_Null_Msg);
        if(info.getPairs() == null || "".equals(info.getPairs()))
            throw new AppException(ExceptionConfig.Entrust_Pairs_Is_Null,ExceptionConfig.Entrust_Pairs_Is_Null_Msg);
        return true;
    }

    @Transactional
    public void fixedBuy(EntrustInfo info){
        if(!userIdentityService.isAuthentication(info.getUserId())){
            throw new UserIsNotAuthenticationException();
        }
        checkEnt(info);
        currencyBalanceService.releaseEntrust(info.getUserId(),Constant.BASE_CURRENCY,info.getNumber()*info.getPrice());
        entrustInfoMapper.insertEntrustInfo(info);
        Entrust entrust = getEntrust(info.getPairs());
        EntrustInfo entrustInfo = new EntrustInfo();
        BeanUtils.copyProperties(info,entrustInfo);
        List<EntrustDetail> transactions = entrust.addFixedBuy(entrustInfo);
        new Thread(() -> doTra(info,transactions)).start();
        setEntrust(info.getPairs(),entrust);
    }

    @Transactional
    public void fixedSell(EntrustInfo info){
        if(!userIdentityService.isAuthentication(info.getUserId())){
            throw new UserIsNotAuthenticationException();
        }
        checkEnt(info);
        currencyBalanceService.releaseEntrust(info.getUserId(),info.getPairs(),info.getNumber());
        entrustInfoMapper.insertEntrustInfo(info);
        Entrust entrust = getEntrust(info.getPairs());
        EntrustInfo entrustInfo = new EntrustInfo();
        BeanUtils.copyProperties(info,entrustInfo);
        List<EntrustDetail> transactions = entrust.addFixedSell(entrustInfo);
        new Thread(() -> doTra(info,transactions)).start();
        setEntrust(info.getPairs(),entrust);
    }
//    public void marketBuy(EntrustInfo info){
//
//    }

//    public void marketSell(EntrustInfo info){
//
//    }
//
    public void revoke(EntrustInfo info){
        Entrust entrust = getEntrust(info.getPairs());
        EntrustInfo entrustInfo = entrust.revokeEntrust(info);
        setEntrust(info.getPairs(),entrust);
        if(entrustInfo == null){
            throw new EntrustIsNotExistException();
        }
        if(entrustInfo.getUserId() != info.getUserId()){
            throw new EntrustNoAuthorityException();
        }
        if(info.getDirection().equals(Constant.BUY_DIRECTION)){
            currencyBalanceService.revokeEntrust(entrustInfo.getUserId(),Constant.BASE_CURRENCY,
                    entrustInfo.getPrice()*entrustInfo.getNumber());
        }else {
            currencyBalanceService.revokeEntrust(entrustInfo.getUserId(),entrustInfo.getPairs(),entrustInfo.getNumber());
        }

        entrustInfoMapper.updateEntrustInfoRevoke(info.getEntrustId());
    }

    public Map<String,List> getSamEnt(String cur){
        Entrust entrust = getEntrust(cur);
        List<SamEntrust> buy = entrust.toBuySamEnt();
        List<SamEntrust> sell = entrust.toSellSamEnt();
        Map<String,List> ent = new HashMap<>();
        ent.put("buy",buy);
        ent.put("sell",sell);
        return ent;
    }

    public List<EntrustInfo> getCurrentEntrust(Long userid){
        return entrustInfoMapper.selectCurrentEntrustInfo(userid,Constant.Accuracy);
    }

    public List<EntrustInfo> getHisEntrust(EntrustInfo info){
        return entrustInfoMapper.selectEntrustInfo(info);
    }

}
