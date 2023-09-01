package com.kevin.sample.uim.service;

import com.kevin.sample.domain.GroupRole;

import java.util.List;

public interface GroupRoleService {
    public List<GroupRole> getGroupRole(int groupId);

    public int bind(int groupId, int roleId);
}
