package com.design.yang.dto;

import java.util.Date;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-19 14:04
 */
public class EntrustTransaction {
    Long entrustId;
    Long traderId;
    Double price;
    Double number;
    Date transactionTime;


    public EntrustTransaction() {
    }

    public EntrustTransaction(Long entrustId, Long traderId, Double price, Double number, Date transactionTime) {
        this.entrustId = entrustId;
        this.traderId = traderId;
        this.price = price;
        this.number = number;
        this.transactionTime = transactionTime;
    }

    public Long getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Long entrustId) {
        this.entrustId = entrustId;
    }

    public Long getTraderId() {
        return traderId;
    }

    public void setTraderId(Long traderId) {
        this.traderId = traderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }
}
