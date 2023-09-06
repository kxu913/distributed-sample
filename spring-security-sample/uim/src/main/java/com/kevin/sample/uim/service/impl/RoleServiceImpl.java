/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service.impl;

import com.kevin.sample.uim.domain.Role;
import com.kevin.sample.uim.mapper.RoleMapper;
import com.kevin.sample.uim.service.RoleService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Override
    public List<Role> getRole() {
        return roleMapper.getRole();
    }
    @Override
    public long saveRole(Role domain) {
        return  roleMapper.saveRole(domain);
    }
}
