package com.design.yang.controller;

import com.design.yang.BaseResponse.BaseResponse;
import com.design.yang.Util.AppSecurityContext;
import com.design.yang.dto.SampleUserInfo;
import com.design.yang.dto.UserBaseInfo;
import com.design.yang.mapper.LoginLogMapper;
import com.design.yang.service.SampleUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: yang
 * @description: user info
 * @author: 阳
 * @create: 2019-04-08 20:39
 */
@RestController
@RequestMapping("/api/userinfo")
@Slf4j
public class UserInfoController {
    @Autowired
    SampleUserInfoService sampleUserInfoService;
    @Autowired
    LoginLogMapper loginLogMapper;
    @RequestMapping("/get")
    public BaseResponse getUserInfo(){
        String id = AppSecurityContext.getUserId();
        log.info("current id is " +id);
        SampleUserInfo userInfo = sampleUserInfoService.querySamUserInfoByid(id);
        userInfo.setRoles(AppSecurityContext.getUserRoles());
        return new BaseResponse(userInfo);
    }
    @RequestMapping("/base")
    public BaseResponse baseInfo(){
        String id = AppSecurityContext.getUserId();
        log.info("current id is " +id);
        UserBaseInfo baseInfo = sampleUserInfoService.queryBaseInfo(id);
        StringBuilder mobile = new StringBuilder(baseInfo.getMobile());
        mobile.replace(3,7,"****");
        baseInfo.setMobile(mobile.toString());
        return new BaseResponse(baseInfo);
    }

    @RequestMapping("/loginLog")
    public BaseResponse getLoginLog(){
        long id = Long.parseLong(AppSecurityContext.getUserId());
        return new BaseResponse(loginLogMapper.selectLogById(id));
    }
}
