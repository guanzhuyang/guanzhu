package com.design.yang.dto;

import java.util.Date;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-19 13:33
 */
public class EntrustInfo {
    Long entrustId;
    Long userId;
    String pairs;
    String direction;
    Double price;
    Double number;
    String condition;
    Double completed;
    Date releaseTime;
    Double volume;
    Date startTime;
    Date endTime;
    String isRevoke;

    public EntrustInfo(boolean b) {
        this.entrustId = 1L;
        this.userId = 1L;
        this.pairs = "1";
        this.direction = "1";
        this.price = 1.1;
        this.number = 1.1;
        this.condition = "1";
        this.completed = 1.1;
        this.releaseTime = new Date();
    }

    public EntrustInfo() {
    }

    public Long getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Long entrustId) {
        this.entrustId = entrustId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPairs() {
        return pairs;
    }

    public void setPairs(String pairs) {
        this.pairs = pairs;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Double getCompleted() {
        return completed;
    }

    public void setCompleted(Double completed) {
        this.completed = completed;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getIsRevoke() {
        return isRevoke;
    }

    public void setIsRevoke(String isRevoke) {
        this.isRevoke = isRevoke;
    }
}
