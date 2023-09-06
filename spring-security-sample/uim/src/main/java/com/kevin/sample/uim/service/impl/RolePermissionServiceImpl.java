package com.kevin.sample.uim.service.impl;

import com.kevin.sample.uim.domain.RolePermission;
import com.kevin.sample.uim.mapper.RolePermissionMapper;
import com.kevin.sample.uim.service.RolePermissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@AllArgsConstructor
public class RolePermissionServiceImpl implements RolePermissionService {
    private final RolePermissionMapper mapper;

    @Override
    public List<RolePermission> getRolePermission(int roleId) {
        return mapper.getRolePermission(roleId);
    }

    @Override
    public int bind(int roleId, int permissionId) {
        return mapper.bind(roleId, permissionId);
    }
}
