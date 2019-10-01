package com.design.yang.controller;

import com.design.yang.BaseResponse.BaseResponse;
import com.design.yang.dto.SystemConfig;
import com.design.yang.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-05-31 14:01
 */
@RestController
@RequestMapping("/api/system")
public class SystemConfigController {
    @Autowired
    SystemConfigService systemConfigService;

    @RequestMapping("/all")
    public BaseResponse getAll(){
        return new BaseResponse(systemConfigService.getAllConfig());
    }

    @RequestMapping("/new")
    public BaseResponse insertNew(@RequestBody(required = false) SystemConfig config){
        systemConfigService.insertConfig(config);
        return new BaseResponse();
    }
}
