package com.design.yang.mapper;

import com.design.yang.dto.RechargeCurrency;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RechargeCurrencyMapper {
    @Insert("INSERT INTO currency_recharge \n" +
            "\t( user_id, currency, ratio, recharge_number, cny_cost, recharge_time )\n" +
            "VALUES\n" +
            "\t( #{userId}, #{currency}, #{ratio}, #{rechargeNumber}, #{cnyCost}, NOW() )")
    void insertNewCecharge(RechargeCurrency currency);

    @Select("SELECT\n" +
            "\tuser_id userId,\n" +
            "\tcurrency,\n" +
            "\tratio,\n" +
            "\trecharge_number rechargeNumber,\n" +
            "\tcny_cost cnyCost,\n" +
            "\trecharge_time rechargeTime \n" +
            "FROM\n" +
            "\tcurrency_recharge \n" +
            "WHERE\n" +
            "\tuser_id = #{userId}")
    @ResultType(RechargeCurrency.class)
    List<RechargeCurrency> selectCechargeCurrencyById(@Param("userId") Long userId);
}
