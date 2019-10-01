package com.design.yang.mapper;

import com.design.yang.dto.TestArea;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AreaTestMapper {


    List<TestArea> getAllArea();
}
