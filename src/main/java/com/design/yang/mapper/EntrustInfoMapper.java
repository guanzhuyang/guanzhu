package com.design.yang.mapper;

import com.design.yang.dto.EntrustInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EntrustInfoMapper {
    void insertEntrustInfo(EntrustInfo info);
    void updateEntrustInfoCompleted(@Param("entrustId")Long entrustId,@Param("completed") Double completed);
    void updateEntrustInfoRevoke(@Param("entrustId")Long entrustId);
    List<EntrustInfo> selectEntrustInfo(EntrustInfo info);
    List<EntrustInfo> selectCurrentEntrustInfo(@Param("userId")Long userid, @Param("accuracy") Double accuracy);
}
