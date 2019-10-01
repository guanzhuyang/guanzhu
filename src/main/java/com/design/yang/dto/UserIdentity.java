package com.design.yang.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-05-30 20:46
 */
public class UserIdentity {
    long userId;
    String identitycardPositive;
    String identitycardOpposite;
    String idNumber;
    String idName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date endTime;
    String authority;
    String isExamine;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getIdentitycardPositive() {
        return identitycardPositive;
    }

    public void setIdentitycardPositive(String identitycardPositive) {
        this.identitycardPositive = identitycardPositive;
    }

    public String getIdentitycardOpposite() {
        return identitycardOpposite;
    }

    public void setIdentitycardOpposite(String identitycardOpposite) {
        this.identitycardOpposite = identitycardOpposite;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getIsExamine() {
        return isExamine;
    }

    public void setIsExamine(String isExamine) {
        this.isExamine = isExamine;
    }
}
