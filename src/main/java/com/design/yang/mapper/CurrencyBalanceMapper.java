package com.design.yang.mapper;

import com.design.yang.dto.UserCurrencyBalance;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-20 15:11
 */
@Component
public interface CurrencyBalanceMapper {
    void insertNewCurrencyBalance(UserCurrencyBalance balance);
    void insertNewCurrencyBalances(@Param("bals") List<UserCurrencyBalance> balance);
    void addBalance(@Param("userId") Long userId,@Param("cur") String cur,@Param("ban") Double ban);
    void reduceBalance(@Param("userId")Long userId,@Param("cur")String cur,@Param("ban")Double ban);
    void addAvailableBalance(@Param("userId") Long userId,@Param("cur") String cur,@Param("ban") Double ban);
    void reduceAvailableBalance(@Param("userId")Long userId,@Param("cur")String cur,@Param("ban")Double ban);
    void addTotalBalance(@Param("userId") Long userId,@Param("cur") String cur,@Param("ban") Double ban);
    void reduceTotalBalance(@Param("userId")Long userId,@Param("cur")String cur,@Param("ban")Double ban);
    void operationBalance(@Param("userId")Long userId,@Param("cur")String cur,@Param("lock")Double lock,@Param("total")Double total);
    void updateCurrencyBalance(UserCurrencyBalance balance);
    List<UserCurrencyBalance> selectBanlance(UserCurrencyBalance balance);
}
