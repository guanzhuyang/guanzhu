package com.design.yang.service;

import com.design.yang.AppException.AppChildrenException.BalanceIsNotEnoughException;
import com.design.yang.AppException.AppException;
import com.design.yang.constantClass.Constant;
import com.design.yang.dto.CurDetails;
import com.design.yang.dto.RechargeCurrency;
import com.design.yang.dto.UserBalance;
import com.design.yang.dto.UserCurrencyBalance;
import com.design.yang.exceptionConfig.ExceptionConfig;
import com.design.yang.mapper.CurrencyBalanceMapper;
import com.design.yang.mapper.RechargeCurrencyMapper;
import com.design.yang.redisService.MapRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-20 15:50
 */
@Service
public class CurrencyBalanceService {
    @Autowired
    CurrencyBalanceMapper currencyBalanceMapper;
    @Autowired
    RechargeCurrencyMapper rechargeCurrencyMapper;
    @Autowired
    MapRedisService mapRedisService;

    public void recharge(RechargeCurrency currency){
        rechargeCurrencyMapper.insertNewCecharge(currency);
        UserCurrencyBalance balance = new UserCurrencyBalance();
        balance.setCurName(currency.getCurrency());
        balance.setUserId(currency.getUserId());
        List list = currencyBalanceMapper.selectBanlance(balance);
        if(list.size() > 0){
            currencyBalanceMapper.addBalance(currency.getUserId(),currency.getCurrency(),currency.getRechargeNumber());
        }else {
            balance.setAvailableBalance(0.0);
            balance.setBalance(currency.getRechargeNumber());
            currencyBalanceMapper.insertNewCurrencyBalance(balance);
        }

    }

    public List<RechargeCurrency> getRechargeDetails(Long userid){
        return rechargeCurrencyMapper.selectCechargeCurrencyById(userid);
    }


    public Double getRato(String c){
        Map rateMap = mapRedisService.get("USDT_rate");
        Double rate = Double.valueOf(rateMap.get(c).toString());
        return rate;
    }

    public Double getPriceCur(String c){
        Map<String, CurDetails> map = mapRedisService.get("curreny_details_map");
        return map.get(c).getPrice();
    }

    public Map getAllBalance(Long userid){
        UserCurrencyBalance balance = new UserCurrencyBalance();
        balance.setUserId(userid);
        List<UserCurrencyBalance> balances = currencyBalanceMapper.selectBanlance(balance);
        Map<String, CurDetails> map = mapRedisService.get("curreny_details_map");
        List<UserBalance> userBalances = new ArrayList<>();
        Map rateMap = mapRedisService.get("USDT_rate");
        Double rate = Double.valueOf(rateMap.get(Constant.BASE_LEGAL_CURRENCY).toString());
        Double voUSDT = Double.valueOf(0);
        for(UserCurrencyBalance balance1 : balances){
            UserBalance balance2 = new UserBalance();
            if(Constant.BASE_CURRENCY.equals(balance1.getCurName())){
                balance2.setBalance(balance1,1.0);
            }else {
                balance2.setBalance(balance1,map.get(balance1.getCurName()).getPrice());
            }
            voUSDT = voUSDT + balance2.getValuation();
            userBalances.add(balance2);
        }
        Map remap= new HashMap();
        remap.put("USDTVo",voUSDT);
        remap.put("CNYVo",voUSDT*rate);
        remap.put("balanceList",userBalances);
        return remap;
    }

    public UserCurrencyBalance getBalanceByCur(Long userid,String cur){
        UserCurrencyBalance balance = new UserCurrencyBalance();
        balance.setUserId(userid);
        balance.setCurName(cur);
        List<UserCurrencyBalance> balances = currencyBalanceMapper.selectBanlance(balance);
        if(balances == null || balances.size() == 0){
            return null;
        }
        return balances.get(0);
    }

    public void releaseEntrust(Long userId,String cur,Double total){
        UserCurrencyBalance balance = new UserCurrencyBalance();
        balance.setUserId(userId);
        balance.setCurName(cur);
        List<UserCurrencyBalance> balances = currencyBalanceMapper.selectBanlance(balance);
        UserCurrencyBalance currencyBalance;
        if(balances == null || balances.size() == 0){
            currencyBalance = new UserCurrencyBalance();
            currencyBalance.setCurName(cur);
            currencyBalance.setUserId(userId);
            currencyBalance.setAvailableBalance(Double.valueOf(0));
            currencyBalance.setBalance(Double.valueOf(0));
            currencyBalanceMapper.insertNewCurrencyBalance(currencyBalance);
        }else {
            currencyBalance = balances.get(0);
        }
        if(currencyBalance.getBalance() - currencyBalance.getAvailableBalance() < total){
            throw new BalanceIsNotEnoughException(cur);
        }
        //锁仓
        currencyBalanceMapper.addAvailableBalance(userId,cur,total);
    }

    //减少交易货币量
    public void completeBuyTransaction(Long userId,String cur,Double lock,Double actual){
        //补回锁仓差额,总余额扣除实际交易价
        currencyBalanceMapper.operationBalance(userId,cur,-lock,-actual);
    }

    //增加被交易货币量
    public void completeSellTransaction(Long userId,String cur,Double actual){
        currencyBalanceMapper.operationBalance(userId,cur, Double.valueOf(0),actual);
    }

    //撤销购买委托,补锁仓差额,总余额不变
    public void revokeEntrust(Long userId,String cur,Double lock){
        currencyBalanceMapper.operationBalance(userId,cur,lock,Double.valueOf(0));
    }
}
