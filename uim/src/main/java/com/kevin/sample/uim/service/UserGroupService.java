package com.kevin.sample.uim.service;

import com.kevin.sample.domain.UserGroup;

import java.util.List;

public interface UserGroupService {

    public int bind(long userId, int groupId);

    public List<UserGroup> getUserGroup(long userId);
}
