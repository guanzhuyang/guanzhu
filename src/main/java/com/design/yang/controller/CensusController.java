package com.design.yang.controller;

import com.design.yang.AppException.AppChildrenException.CurrencyIsNotExistedException;
import com.design.yang.AppException.AppException;
import com.design.yang.BaseResponse.BaseResponse;
import com.design.yang.dto.RevCensus;
import com.design.yang.service.CensusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-05-28 18:36
 */
@RestController
@RequestMapping("/api/census")
public class CensusController {
    @Autowired
    CensusService censusService;

    @RequestMapping("/newUser")
    public BaseResponse newUserData(){
        return new BaseResponse(censusService.newUserData());
    }

    @RequestMapping("/tradeVol")
    public BaseResponse TradeVolData(HttpServletRequest request){
        String cur = request.getParameter("cur");
        if(cur == null){
            throw new CurrencyIsNotExistedException();
        }
        return new BaseResponse(censusService.TradeVolData(cur));
    }

    @RequestMapping("/tradeNum")
    public BaseResponse censusTradeNumber(@RequestBody(required = false) RevCensus census){
        if(census == null){
            throw new CurrencyIsNotExistedException();
        }
        if(census.getCur() == null || "".equals(census.getCur())){
            throw new CurrencyIsNotExistedException();
        }
        if(census.getTime() == null){
            throw new AppException();
        }
        return new BaseResponse(censusService.censusTradeNumber(census.getCur(),census.getTime()+"-01"));
    }

    @RequestMapping("/cur")
    public BaseResponse censusCur(@RequestBody(required = false) RevCensus census){
        if(census == null){
            throw new AppException();
        }
        if(census.getTime() == null){
            throw new AppException();
        }
        return new BaseResponse(censusService.censusCur(census.getTime()+"-01"));
    }
}
