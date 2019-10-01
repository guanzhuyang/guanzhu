package com.design.yang.dto;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-18 11:07
 */
public class SystemConfig {
    String key;
    String value;
    String desc;
    String status;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
