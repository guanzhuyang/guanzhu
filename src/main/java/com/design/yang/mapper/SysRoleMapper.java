package com.design.yang.mapper;

import com.design.yang.dto.SysRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SysRoleMapper {

    @Select("Select " +
            "role_id roleId," +
            "role_name roleName," +
            "role_desc roleDesc," +
            "create_time createTime," +
            "status " +
            "from sys_role " +
            "where status = 'Y'")
    @ResultType(SysRole.class)
    /**
    *@Description: select all effective system role
    *@Param: []
    *@return: java.util.List<com.design.yang.dto.SysRole>
    *@date: 2019-4-3
    */
    List<SysRole> selectEffectiveRole();

    @Select("Select " +
            "role_id roleId," +
            "role_name roleName," +
            "role_desc roleDesc," +
            "create_time createTime," +
            "status " +
            "from sys_role " +
            "where status = 'Y' " +
            "and role_name = #{roleName}")
    @ResultType(SysRole.class)
    SysRole selectEffectiveRoleByRoleName(@Param("roleName") String roleName);


    @Insert("insert into sys_role " +
            "( role_name ," +
            " role_desc ," +
            " create_time ," +
            " status " +
            ") values (" +
            "#{roleName}," +
            "#{roleDesc)," +
            "now()," +
            "'Y')")
    void insertNewRole(SysRole role);

    @Insert("insert into user_role " +
            "(user_id , role_id , create_time )" +
            "values (#{userid} , #{roleid} , now())")
    void insertRoleUser(@Param("userid") Long userid ,@Param("roleid") Long roleid);
}
