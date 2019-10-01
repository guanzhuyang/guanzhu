package com.design.yang.mapper;

import com.design.yang.dto.CurrencyTrade;
import com.design.yang.dto.PeopleNumber;
import com.design.yang.dto.TradeNumber;
import com.design.yang.dto.TradeVolumn;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface UserCensusMapper {

    /**
    *@Description: 用户新增数量与时间关系
    *@Param:
    *@return:
    *@date: 2019-5-28
    */
    @Select("SELECT\n" +
            "\tCOUNT( 0 ) number,\n" +
            "\tdate_format( create_time, '%Y-%m' ) months \n" +
            "FROM\n" +
            "\tuser_login \n" +
            "GROUP BY\n" +
            "\tmonths")
    @ResultType(PeopleNumber.class)
    List<PeopleNumber> censusNumber();

    @Select("SELECT\n" +
            "\tSUM(et.number * et.price) vol,\n" +
            "\tdate_format( et.transaction_time, '%Y-%m' ) months\n" +
            "FROM\n" +
            "\tentrust_info ei\n" +
            "\tRIGHT JOIN entrust_transaction et ON ei.entrust_id = et.entrust_id \n" +
            "\tOR ei.entrust_id = et.trader_id\n" +
            "WHERE ei.direction = 'buy' AND ei.pairs = #{cur}\n" +
            "GROUP BY months")
    @ResultType(TradeVolumn.class)
    /**
    *@Description: 某一币种交易USDT与时间关系
    *@Param: [cur]
    *@return: java.util.List<com.design.yang.dto.TradeVolumn>
    *@date: 2019-5-28
    */
    List<TradeVolumn> censusVolumn(@Param("cur") String cur);

    @Select("SELECT\n" +
            "\tSUM( et.number ) num,\n" +
            "\tet.price pri \n" +
            "FROM\n" +
            "\tentrust_info ei\n" +
            "\tRIGHT JOIN entrust_transaction et ON ei.entrust_id = et.entrust_id \n" +
            "\tOR ei.entrust_id = et.trader_id \n" +
            "WHERE\n" +
            "\tei.direction = 'buy' \n" +
            "\tAND ei.pairs = #{cur} \n" +
            "\tAND date_format( et.transaction_time, '%Y-%m' ) = date_format( #{time}, '%Y-%m' ) \n" +
            "GROUP BY\n" +
            "\tpri")
    @ResultType(TradeNumber.class)
    /**
    *@Description: 在一个月里某一币种价格与交易量关系
    *@Param: [cur, time]
    *@return: java.util.List<com.design.yang.dto.TradeNumber>
    *@date: 2019-5-28
    */
    List<TradeNumber> censusTradeNumber(@Param("cur") String cur ,@Param("time") String time);

    @Select("SELECT\n" +
            "\tSUM( et.number ) num,\n" +
            "\tSUM(et.price * et.number) pri,\n" +
            "\tei.pairs cur\n" +
            "FROM\n" +
            "\tentrust_info ei\n" +
            "\tRIGHT JOIN entrust_transaction et ON ei.entrust_id = et.entrust_id \n" +
            "\tOR ei.entrust_id = et.trader_id \n" +
            "WHERE\n" +
            "\tei.direction = 'buy' \n" +
            "\tAND date_format( et.transaction_time, '%Y-%m' ) = date_format( #{time}, '%Y-%m' ) \n" +
            "GROUP BY\n" +
            "\tpairs\n" +
            "ORDER BY num DESC")
    @ResultType(CurrencyTrade.class)
    /**
    *@Description: 某一月份中全部货币交易量及平均价格
    *@Param: [time]
    *@return: java.util.List<com.design.yang.dto.CurrencyTrade>
    *@date: 2019-5-28
    */
    List<CurrencyTrade> censusCur(@Param("time") String time);
}
