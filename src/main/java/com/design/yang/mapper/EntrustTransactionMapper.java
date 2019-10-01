package com.design.yang.mapper;

import com.design.yang.dto.EntrustTransaction;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EntrustTransactionMapper {

    void insertTransaction(@Param("transactions")List<EntrustTransaction> transactions);

//    List<>
}
