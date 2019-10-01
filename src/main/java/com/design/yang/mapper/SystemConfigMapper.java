package com.design.yang.mapper;

import com.design.yang.dto.SystemConfig;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SystemConfigMapper {
    @Select("SELECT\n" +
            "\tconfig_key `key`,\n" +
            "\tconfig_value `value`,\n" +
            "\tconfig_desc `desc`,\n" +
            "\t`status` \n" +
            "FROM\n" +
            "\tsystem_config \n" +
            "WHERE\n" +
            "\tconfig_key = #{key}")
    @ResultType(SystemConfig.class)
    SystemConfig getConfigBykey(@Param("key") String key);
    @Select("SELECT\n" +
            "\tconfig_key `key`,\n" +
            "\tconfig_value `value`,\n" +
            "\tconfig_desc `desc`,\n" +
            "\t`status` \n" +
            "FROM\n" +
            "\tsystem_config")
    @ResultType(SystemConfig.class)
    List<SystemConfig> getAllConfig();

    @Select("SELECT\n" +
            "\tconfig_key `key`,\n" +
            "\tconfig_value `value`,\n" +
            "\tconfig_desc `desc`,\n" +
            "\t`status` \n" +
            "FROM\n" +
            "\tsystem_config \n" +
            "WHERE\n" +
            "\t`status` = 'Y'")
    @ResultType(SystemConfig.class)
    List<SystemConfig> getAllEnableConfig();

    @Insert("INSERT INTO system_config ( config_key, config_value, config_desc, `status` )\n" +
            "VALUES\n" +
            "\t(#{key},#{value},#{desc},#{status})")
    void insertConfig(SystemConfig config);

    @Update("UPDATE system_config \n" +
            "SET config_value = #{value},config_desc = #{desc},`status` = #{`status`} \n" +
            "WHERE config_key = #{key}")
    void updateConfig(SystemConfig config);
}
