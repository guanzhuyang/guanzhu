package com.design.yang.dto;



/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-17 23:01
 */
public class CurDetails {
    String name;
    String symbol;
    Double price;
    Double priceUsd;
    Double priceBtc;
    Double volume;
    Double high;
    Double low;
    Double changeHourly;
    Double changeDaily;
    Double changeWeekly;
    Double changeMonthly;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(Double priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Double getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(Double priceBtc) {
        this.priceBtc = priceBtc;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getChangeHourly() {
        return changeHourly;
    }

    public void setChangeHourly(Double changeHourly) {
        this.changeHourly = changeHourly;
    }

    public Double getChangeDaily() {
        return changeDaily;
    }

    public void setChangeDaily(Double changeDaily) {
        this.changeDaily = changeDaily;
    }

    public Double getChangeWeekly() {
        return changeWeekly;
    }

    public void setChangeWeekly(Double changeWeekly) {
        this.changeWeekly = changeWeekly;
    }

    public Double getChangeMonthly() {
        return changeMonthly;
    }

    public void setChangeMonthly(Double changeMonthly) {
        this.changeMonthly = changeMonthly;
    }
}
