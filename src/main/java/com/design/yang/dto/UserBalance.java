package com.design.yang.dto;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-23 16:40
 */
public class UserBalance {
    String curName;
    Double availableBalance;
    Double balance;
    Double valuation;
    Double lock;

    public Double getLock() {
        return lock;
    }

    public void setLock(Double lock) {
        this.lock = lock;
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

    public Double getValuation() {
        return valuation;
    }

    public void setValuation(Double valuation) {
        this.valuation = valuation;
    }

    public void setBalance(UserCurrencyBalance balance,Double rate){
        this.lock = balance.availableBalance;
        this.balance = balance.getBalance();
        this.curName = balance.getCurName();
        this.availableBalance = this.balance - this.lock;
        this.valuation = this.balance * rate;
    }
}
