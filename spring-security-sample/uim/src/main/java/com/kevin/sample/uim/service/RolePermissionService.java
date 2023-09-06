package com.kevin.sample.uim.service;

import com.kevin.sample.uim.domain.RolePermission;

import java.util.List;

public interface RolePermissionService {
    public List<RolePermission> getRolePermission(int roleId);

    public int bind(int roleId, int permissionId);
}
