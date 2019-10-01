package com.design.yang.mapper;

import com.design.yang.dto.CurDetails;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-18 09:35
 */
@Component
public interface CurDetailsMapper {
    @Select("SELECT\n" +
            "\tcd.`name`,\n" +
            "\tcd.symbol,\n" +
            "\tcd.price,\n" +
            "\tcd.price_usd priceUsd,\n" +
            "\tcd.price_btc priceBtc,\n" +
            "\tcd.volume,\n" +
            "\tcd.high,\n" +
            "\tcd.low,\n" +
            "\tcd.change_hourly changeHourly,\n" +
            "\tcd.change_daily changeDaily,\n" +
            "\tcd.change_weekly changeWeekly,\n" +
            "\tcd.change_monthly changeMonthly \n" +
            "FROM\n" +
            "\tcurrency_details cd\n" +
            "\tJOIN ( SELECT max( timestamps ) mt, NAME FROM currency_details GROUP BY `name` ) AS cm ON cd.NAME = cm.NAME \n" +
            "\tAND cd.timestamps = cm.mt")
    List<CurDetails> getLastCurDetails();

    @Insert({"<script>\n" +
            "INSERT INTO currency_details    \n" +
            "VALUES   \n" +
            "<foreach collection='details' item='item' index='index' separator=','>\n" +
            "(    \n" +
            "NOW( ),    \n" +
            "#{item.name},    \n" +
            "#{item.symbol} ,    \n" +
            "#{item.price},    \n" +
            "#{item.priceUsd},    \n" +
            "#{item.priceBtc},    \n" +
            "#{item.volume},    \n" +
            "#{item.high},    \n" +
            "#{item.low},    \n" +
            "#{item.changeHourly},    \n" +
            "#{item.changeDaily},    \n" +
            "#{item.changeWeekly},    \n" +
            "#{item.changeMonthly}     \n" +
            ")\n" +
            "</foreach>\n" +
            "</script>"})
    void insertCurDetails(@Param("details") List<CurDetails> details);
}
