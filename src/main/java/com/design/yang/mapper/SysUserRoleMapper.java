package com.design.yang.mapper;

import com.design.yang.dto.AppUser;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SysUserRoleMapper {
    @Select("SELECT\n" +
            "        ul.user_id userId,\n" +
            "        ul.user_account userAccount,\n" +
            "        ul.user_mobile mobile,\n" +
            "        ul.user_email email,\n" +
            "        ul.`password` password,\n" +
            "        ul.`status` status,\n" +
            "        sr.role_name roles\n" +
            "        FROM\n" +
            "        user_login ul\n" +
            "        LEFT JOIN user_role ur ON ul.user_id = ur.user_id\n" +
            "        LEFT JOIN sys_role sr on ur.role_id = sr.role_id\n" +
            "        WHERE ul.user_account = #{account}")
    @ResultType(AppUser.class)
    public List<AppUser> queryUserByAccount(@Param("account") String account);
}
