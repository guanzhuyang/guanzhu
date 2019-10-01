package com.design.yang.dto;

import java.util.List;

/**
 * @program: yang
 * @description: Sample User Info
 * @author: 阳
 * @create: 2019-04-08 21:13
 */
public class SampleUserInfo {
    String name;
    String avater;
    List<String> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
