package com.design.yang.entrustCore;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-22 10:02
 */
public class SamEntrust {
    Double price;
    Double number;

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

    public SamEntrust() {
    }

    public SamEntrust(Double price, Double number) {
        this.price = price;
        this.number = number;
    }
}
