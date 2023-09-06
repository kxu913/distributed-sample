/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service.impl;

import com.kevin.sample.uim.domain.Permission;
import com.kevin.sample.uim.mapper.PermissionMapper;
import com.kevin.sample.uim.service.PermissionService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    private final PermissionMapper permissionMapper;

    public PermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<Permission> getPermission() {
        return permissionMapper.getPermission();
    }
    @Override
    public long savePermission(Permission domain) {
        return  permissionMapper.savePermission(domain);
    }
}
