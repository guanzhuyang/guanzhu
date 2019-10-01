package com.design.yang.controller;

import com.design.yang.BaseResponse.BaseResponse;
import com.design.yang.Util.AppSecurityContext;
import com.design.yang.dto.UserIdentity;
import com.design.yang.service.UserIdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-05-31 13:26
 */
@RestController
@RequestMapping("/api/id")
public class UserIdentityController {
    @Autowired
    UserIdentityService userIdentityService;

    @RequestMapping("/unaudited")
    public BaseResponse getAllUnaudited(){
        return new BaseResponse(userIdentityService.getUnauditedId());
    }

    public BaseResponse passPudited(){
        return new BaseResponse();
    }

    @RequestMapping("/insert")
    public BaseResponse insertNewId(@RequestBody(required = false) UserIdentity identity){
        identity.setUserId(Long.parseLong(AppSecurityContext.getUserId()));
        userIdentityService.insertId(identity);
        return new BaseResponse();
    }

    @RequestMapping("/check")
    public BaseResponse isA(){
        return new BaseResponse(userIdentityService.isAuthentication(Long.parseLong(AppSecurityContext.getUserId())));
    }
}
