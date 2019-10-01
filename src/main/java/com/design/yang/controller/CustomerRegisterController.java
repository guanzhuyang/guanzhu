package com.design.yang.controller;

import com.design.yang.BaseResponse.BaseResponse;
import com.design.yang.dto.RegisterInfo;
import com.design.yang.service.CustomerRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-11 09:43
 */
@RestController
@RequestMapping("/api/public/register")
public class CustomerRegisterController {
    @Autowired
    CustomerRegisterService customerRegisterService;

    @PostMapping("/new")
    public BaseResponse newCustomer(@RequestBody(required = false) RegisterInfo info){
        customerRegisterService.RegisterNewCustomer(info);
        return new BaseResponse();
    }
}
