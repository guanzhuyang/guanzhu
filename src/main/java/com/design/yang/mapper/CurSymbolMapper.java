package com.design.yang.mapper;

import com.design.yang.dto.CurSymbol;
import com.design.yang.dto.CurrencySymbol;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CurSymbolMapper {
    @Select("SELECT\n" +
            "\t`name`,\n" +
            "\tsymbol,\n" +
            "\talias,\n" +
            "\tvolume_usd volume \n" +
            "FROM\n" +
            "\tcurrency_symbol \n" +
            "WHERE\n" +
            "\t`status` = 'Y'")
    @ResultType(CurSymbol.class)
    List<CurSymbol> getRequestCur();

    @Select("<script>"+
            "SELECT\n" +
            "\tcs.`name` name,\n" +
            "\tcs.symbol symbol,\n" +
            "\tcs.volume_usd volume,\n" +
            "\tcs.`status` status,\n" +
            "\tcs.alias alias,\n" +
            "\tci.name_zn nameZn,\n" +
            "\tci.issue_time issueTime,\n" +
            "\tci.circulation,\n" +
            "\tci.turnover,\n" +
            "\tci.official_website officialWebsite,\n" +
            "\tci.introduction \n" +
            "FROM\n" +
            "\tcurrency_symbol cs\n" +
            "\tLEFT JOIN currency_info ci ON cs.symbol = ci.`name`\n"+
            "WHERE 1=1  \n" +
            "<if test='symbol != null'>" +
            "AND cs.symbol LIKE concat('%',#{symbol},'%')" +
            "</if>"+
            "<if test='status != null'>" +
            "AND cs.status = #{status}" +
            "</if>"+
            "</script>"
            )
    @ResultType(CurrencySymbol.class)
    List<CurrencySymbol> getAllCur(CurrencySymbol sym);

    @Update("<script> " +
            "update currency_symbol " +
            "<set> " +
            "<if test='alias != null'> " +
            "alias = #{alias} " +
            "</if> " +
            "<if test='status != null'> " +
            "status = #{status} " +
            "</if> " +
            "</set> " +
            "where symbol = #{symbol}" +
            "</script>")
    void updateCurrencySym(CurrencySymbol sym);



}
