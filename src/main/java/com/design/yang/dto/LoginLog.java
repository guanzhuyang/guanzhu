package com.design.yang.dto;

import java.util.Date;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-05-31 15:33
 */
public class LoginLog {
    Date time;
    String ip;
    String status;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
