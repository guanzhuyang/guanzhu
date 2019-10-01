package com.design.yang.mapper;

import com.design.yang.dto.UserIdentity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserIdentityMapper {
    List<UserIdentity> selectUserIdentity(UserIdentity identity);
    void insertUserIdentity(UserIdentity identity);
    void updateUserIdentity(UserIdentity identity);
}
