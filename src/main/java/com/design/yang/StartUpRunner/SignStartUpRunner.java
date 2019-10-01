package com.design.yang.StartUpRunner;

import com.design.yang.dto.MenuInfo;
import com.design.yang.dto.SysRole;
import com.design.yang.mapper.RoleMenuMapper;
import com.design.yang.mapper.SysRoleMapper;
import com.design.yang.redisService.SysRoleMenuRedisServive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class SignStartUpRunner implements CommandLineRunner {
    @Autowired
    SysRoleMenuRedisServive sysRoleMenuRedisServive;
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    RoleMenuMapper roleMenuMapper;
    @Override
    public void run(String... args) throws Exception {
        initRoleMenu();
    }

    /**
    *@Description: init system role menus
    *@Param:
    *@return:
    *@date: 2019-4-3
    */
    private void initRoleMenu(){
//        List<SysRole> sysRoles = sysRoleMapper.selectEffectiveRole();
//        for(SysRole role : sysRoles){
//            List<MenuInfo> menuInfos = roleMenuMapper.getMenuByRole(role.getRoleId());
//            sysRoleMenuRedisServive.add(role.getRoleName()+"_MENU",menuInfos);
//        }
    }
}
