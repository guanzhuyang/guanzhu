package com.design.yang.service;

import com.design.yang.dto.SampleUserInfo;
import com.design.yang.dto.UserBaseInfo;
import com.design.yang.mapper.SampleUserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-08 22:19
 */

@Service
public class SampleUserInfoService {
    @Autowired
    SampleUserInfoMapper sampleUserInfoMapper;

    public SampleUserInfo querySamUserInfoByid(String id){
        Long idlong = Long.valueOf(id);
        return sampleUserInfoMapper.getSamUserInfoByid(idlong);
    }

    public UserBaseInfo queryBaseInfo(String id){
        Long idlong = Long.valueOf(id);
        return sampleUserInfoMapper.getBaseInfo(idlong);
    }
}
