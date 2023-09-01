package com.kevin.sample.uim.service.impl;

import com.kevin.sample.domain.GroupRole;
import com.kevin.sample.uim.mapper.GroupRoleMapper;
import com.kevin.sample.uim.service.GroupRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupRoleServiceImpl implements GroupRoleService {
    private final GroupRoleMapper mapper;

    @Override
    public List<GroupRole> getGroupRole(int groupId) {
        return mapper.getGroupRole(groupId);
    }

    @Override
    public int bind(int groupId, int roleId) {
        return mapper.bind(groupId,roleId);
    }
}
