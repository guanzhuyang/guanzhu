package com.design.yang.controller;

import com.design.yang.BaseResponse.BaseResponse;
import com.design.yang.Util.AppSecurityContext;
import com.design.yang.constantClass.Constant;
import com.design.yang.dto.RechargeCurrency;
import com.design.yang.dto.UserCurrencyBalance;
import com.design.yang.mapper.CurrencyBalanceMapper;
import com.design.yang.service.CurrencyBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-23 17:08
 */
@RestController
@RequestMapping("/api/balance")
public class BalanceController {
    @Autowired
    CurrencyBalanceService currencyBalanceService;
    @Autowired
    CurrencyBalanceMapper currencyBalanceMapper;

    @RequestMapping("/get")
    public BaseResponse getBalance(){
        Long id = Long.valueOf(AppSecurityContext.getUserId());
        return new BaseResponse(currencyBalanceService.getAllBalance(id));
    }

    @RequestMapping("/recharge")
    public BaseResponse addCur(@RequestBody RechargeCurrency currency){
        Long id = Long.valueOf(AppSecurityContext.getUserId());
        currency.setUserId(id);
        Double price;
        if(Constant.BASE_CURRENCY.equals(currency.getCurrency())){
            price = 1.0;
        }else {
            price = currencyBalanceService.getPriceCur(currency.getCurrency());
        }
        Double cnyRato = currencyBalanceService.getRato(Constant.BASE_LEGAL_CURRENCY)*price;
        currency.setRatio(cnyRato);
        currency.setRechargeNumber(currency.getCnyCost()/cnyRato);
//        currency.setCnyCost(cnyRato*currency.getRechargeNumber());
        currencyBalanceService.recharge(currency);
        return new BaseResponse();
    }

    @RequestMapping("/details")
    public BaseResponse getRechargeDetails(){
        Long id = Long.valueOf(AppSecurityContext.getUserId());
        return new BaseResponse(currencyBalanceService.getRechargeDetails(id));
    }

    @RequestMapping("/select")
    public BaseResponse getBalanceByCur(HttpServletRequest request){
        Long id = Long.valueOf(AppSecurityContext.getUserId());
        String cur = request.getParameter("cur");
        if(cur == null || cur.equals("")) {
            return new BaseResponse(currencyBalanceService.getAllBalance(id));
        }
        UserCurrencyBalance currencyBalance = currencyBalanceService.getBalanceByCur(id,cur);
        if(currencyBalance == null){
            currencyBalance = new UserCurrencyBalance();
            currencyBalance.setCurName(cur);
            currencyBalance.setUserId(id);
            currencyBalance.setAvailableBalance(Double.valueOf(0));
            currencyBalance.setBalance(Double.valueOf(0));
            currencyBalanceMapper.insertNewCurrencyBalance(currencyBalance);
        }

        return new BaseResponse(currencyBalance);
    }


}
