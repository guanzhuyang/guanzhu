package com.design.yang.dto;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-20 15:08
 */
public class UserCurrencyBalance {
    Long userId;
    String curName;
    Double availableBalance;
    Double balance;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public Double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
