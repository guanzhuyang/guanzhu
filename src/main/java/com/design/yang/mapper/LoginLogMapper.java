package com.design.yang.mapper;

import com.design.yang.dto.LoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public interface LoginLogMapper {
    @Insert("insert into login_log " +
            "(user_id,ip,login_status,login_time)" +
            "values " +
            "(#{userid},#{ip},#{status},now())")
    public void insertNewLog(@Param("userid")Long userid, @Param("ip")String ip, @Param("status") String status);

    @Select("select login_time time , login_status status , ip from login_log where user_id = #{userid}")
    @ResultType(LoginLog.class)
    public List<LoginLog> selectLogById(@Param("userid") long id);
}
