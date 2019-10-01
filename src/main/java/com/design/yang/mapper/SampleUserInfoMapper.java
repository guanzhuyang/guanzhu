package com.design.yang.mapper;

import com.design.yang.dto.SampleUserInfo;
import com.design.yang.dto.UserBaseInfo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-08 22:13
 */
@Component
public interface SampleUserInfoMapper {
    SampleUserInfo getSamUserInfoByid(@Param("id") Long id);

    UserBaseInfo getBaseInfo(@Param("id") Long id);

    void insertInfo(UserBaseInfo info);
}
