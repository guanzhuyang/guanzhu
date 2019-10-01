package com.design.yang.controller;

import com.design.yang.BaseResponse.BaseResponse;
import com.design.yang.dto.CurrencySymbol;
import com.design.yang.dto.CurrentInfo;
import com.design.yang.service.CurrencyInfoService;
import com.design.yang.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-05-23 22:49
 */
@RestController
@RequestMapping("/api/cur")
public class CurController {
    @Autowired
    CurrencyService currencyService;

    @Autowired
    CurrencyInfoService currencyInfoService;

    @RequestMapping("/all")
    public BaseResponse getAllCur(HttpServletRequest request, @RequestBody(required = false)CurrencySymbol sym){
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        if(pageNum == 0){
            pageNum = 1;
        }
        if(pageSize == 0){
            pageSize = 10;
        }
        return new BaseResponse(currencyService.getAllCur(pageNum,pageSize,sym));
    }

    @RequestMapping("/enable")
    public BaseResponse enableCur(HttpServletRequest request){
        String cur = request.getParameter("cur");
        currencyService.enableCur(cur);
        return new BaseResponse();
    }

    @RequestMapping("/forbidden")
    public BaseResponse forbiddenCur(HttpServletRequest request){
        String cur = request.getParameter("cur");
        currencyService.forbiddenCur(cur);
        return new BaseResponse();
    }

    @RequestMapping("/addInfo")
    public BaseResponse addNewInfo(@RequestBody(required = false)CurrentInfo info){
        currencyInfoService.insertCurrencyInfo(info);
        return new BaseResponse();
    }

    @RequestMapping("/updateInfo")
    public BaseResponse updateInfo(@RequestBody(required = false)CurrentInfo info){
        currencyInfoService.updateCurrencyInfo(info);
        return new BaseResponse();
    }

    @RequestMapping("/support")
    public BaseResponse supportCur(){
        return new BaseResponse(currencyService.getSupportCur());
    }



}
