package com.design.yang.mapper;

import com.design.yang.dto.CurrentInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CurrentInfoMapper {
    List<CurrentInfo> selectCurrentInfo(CurrentInfo info);
    void insertCurrentInfo(CurrentInfo info);
    void updateCurrentInfo(CurrentInfo info);
}
