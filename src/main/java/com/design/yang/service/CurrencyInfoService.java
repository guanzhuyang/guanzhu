package com.design.yang.service;

import com.design.yang.AppException.AppException;
import com.design.yang.dto.CurrentInfo;
import com.design.yang.exceptionConfig.ExceptionConfig;
import com.design.yang.mapper.CurrentInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-04-23 19:30
 */
@Service
public class CurrencyInfoService {
    @Autowired
    CurrentInfoMapper currentInfoMapper;

    public void insertCurrencyInfo(CurrentInfo info){
        if(info == null || info.getName() == null){
            throw new AppException();
        }
        currentInfoMapper.insertCurrentInfo(info);
    }

    public CurrentInfo selectInfoByName(String name){
        CurrentInfo info = new CurrentInfo();
        info.setName(name);
        List<CurrentInfo> infos = currentInfoMapper.selectCurrentInfo(info);
        if(infos == null || infos.size() == 0){
            throw new AppException(ExceptionConfig.Currency_IS_Not_Existed,ExceptionConfig.Currency_IS_Not_Existed_Msg);
        }
        return infos.get(0);
    }

    public void updateCurrencyInfo(CurrentInfo info){
        if(info == null || info.getName() == null){
            throw new AppException();
        }
        CurrentInfo info2 = new CurrentInfo();
        info2.setName(info.getName());
        List<CurrentInfo> info1 = currentInfoMapper.selectCurrentInfo(info2);
        if(info1.size() == 0){
            currentInfoMapper.insertCurrentInfo(info);
        }else {
            currentInfoMapper.updateCurrentInfo(info);
        }

    }
}
