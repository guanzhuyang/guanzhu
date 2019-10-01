package com.design.yang.mapper;

import com.design.yang.dto.MenuInfo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RoleMenuMapper {
    List<MenuInfo> getMenuByRole(@Param("role_id")Long roleId);
}
