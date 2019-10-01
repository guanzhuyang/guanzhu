package com.design.yang.dto;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-05-27 17:20
 */
public class TradeVolumn {
    double vol;
    String months;

    public double getVol() {
        return vol;
    }

    public void setVol(double vol) {
        this.vol = vol;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public TradeVolumn() {
    }

    public TradeVolumn(double vol, String months) {
        this.vol = vol;
        this.months = months;
    }
}
