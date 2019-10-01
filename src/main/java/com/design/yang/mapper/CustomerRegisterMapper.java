package com.design.yang.mapper;

import com.design.yang.dto.RegisterInfo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerRegisterMapper {
    List<RegisterInfo> selectByAccount(@Param("account") String account);
    List<RegisterInfo> selectByMobile(@Param("mobile") String mobile);
    List<RegisterInfo> selectByEmail(@Param("email") String email);
    void insertNewCustomer(RegisterInfo register);
    void updateCustomer(RegisterInfo register);
}
