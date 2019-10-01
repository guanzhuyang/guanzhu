package com.design.yang.dto;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-05-27 16:21
 */
public class PeopleNumber {
    int number;
    String months;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public PeopleNumber() {
    }

    public PeopleNumber(int number, String months) {
        this.number = number;
        this.months = months;
    }
}
