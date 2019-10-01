package com.design.yang.controller;

import com.design.yang.BaseResponse.BaseResponse;
import com.design.yang.constantClass.Constant;
import com.design.yang.service.CurrencyBalanceService;
import com.design.yang.service.CurrencyInfoService;
import com.design.yang.service.CurrencyService;
import com.design.yang.service.EntrustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-18 16:39
 */
@RestController
@RequestMapping("/api/public/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;
    @Autowired
    EntrustService entrustService;
    @Autowired
    CurrencyInfoService currencyInfoService;
    @Autowired
    CurrencyBalanceService currencyBalanceService;

    @RequestMapping("/details")
    public BaseResponse getCurrencyDetails(){
        return new BaseResponse(currencyService.getNewDetail(""));
    }

    @GetMapping("/tra")
    public BaseResponse getAllEntByCur(HttpServletRequest request){
        String cur = request.getParameter("cur");
        Map<String, List> map =  entrustService.getSamEnt(cur);
        return new BaseResponse(map);
    }

    @GetMapping("/info")
    public BaseResponse getCurInfo(HttpServletRequest request){
        String cur = request.getParameter("cur");
        return new BaseResponse(currencyInfoService.selectInfoByName(cur));
    }

    @GetMapping("/support")
    public BaseResponse getRequestCur(){
        return new BaseResponse(currencyService.getRequestCur());
    }

    @GetMapping("/rato")
    public BaseResponse getCNYRate(){
        return new BaseResponse(currencyBalanceService.getRato("CNY"));
    }

    @GetMapping("/price")
    public BaseResponse getCurPrice(HttpServletRequest request){
        String cur = request.getParameter("cur");
        return new BaseResponse(currencyBalanceService.getPriceCur(cur));
    }

    @GetMapping("/cnyprice")
    public BaseResponse getCNYPrice(HttpServletRequest request){
        String cur = request.getParameter("cur");
        Double price;
        if(Constant.BASE_CURRENCY.equals(cur)){
            price = currencyBalanceService.getRato("CNY");
        }else {
            price = currencyBalanceService.getRato("CNY")*currencyBalanceService.getPriceCur(cur);
        }
        return new BaseResponse(price);
    }
}
