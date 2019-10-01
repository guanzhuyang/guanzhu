package com.design.yang.dto;

import java.util.Date;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-20 16:13
 */
public class RechargeCurrency {
    Long userId;
    String currency;
    Double ratio;
    Double rechargeNumber;
    Double cnyCost;
    Date rechargeTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getRechargeNumber() {
        return rechargeNumber;
    }

    public void setRechargeNumber(Double rechargeNumber) {
        this.rechargeNumber = rechargeNumber;
    }

    public Double getCnyCost() {
        return cnyCost;
    }

    public void setCnyCost(Double cnyCost) {
        this.cnyCost = cnyCost;
    }

    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }
}
