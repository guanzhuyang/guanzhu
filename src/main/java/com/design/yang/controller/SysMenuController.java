package com.design.yang.controller;

import com.design.yang.BaseResponse.BaseResponse;
import com.design.yang.redisService.SysRoleMenuRedisServive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: yang
 * @description: menu controller
 * @author: 阳
 * @create: 2019-04-07 13:21
 */

@RestController
@Slf4j
//@CrossOrigin(origins = "*",allowedHeaders = "*")
public class SysMenuController {
    @Autowired
    SysRoleMenuRedisServive sysRoleMenuRedisServive;

    @RequestMapping("/api/menu")
    public BaseResponse getMenuByRole(){
        return new BaseResponse(sysRoleMenuRedisServive.get("SYS_ADMIN_MENU"));
    }
}
