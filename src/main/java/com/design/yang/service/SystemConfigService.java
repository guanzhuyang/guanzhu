package com.design.yang.service;

import com.design.yang.dto.SystemConfig;
import com.design.yang.mapper.SystemConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-18 15:46
 */
@Service
public class SystemConfigService {
    @Autowired
    SystemConfigMapper systemConfigMapper;

    public SystemConfig getConfigByKey(String key){
        return systemConfigMapper.getConfigBykey(key);
    }

    public List<SystemConfig> getAllConfig(){
        return systemConfigMapper.getAllConfig();
    }

    public List<SystemConfig> getAllEnableConfig(){
        return systemConfigMapper.getAllEnableConfig();
    }

    public void insertConfig(SystemConfig config){
        systemConfigMapper.insertConfig(config);
    }

    public void updateConfigBykey(SystemConfig config){
        systemConfigMapper.updateConfig(config);
    }
}
