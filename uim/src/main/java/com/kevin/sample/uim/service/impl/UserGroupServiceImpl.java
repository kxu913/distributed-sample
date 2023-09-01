package com.kevin.sample.uim.service.impl;

import com.kevin.sample.domain.UserGroup;
import com.kevin.sample.uim.mapper.UserGroupMapper;
import com.kevin.sample.uim.service.UserGroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserGroupServiceImpl implements UserGroupService {
    private final UserGroupMapper mapper;

    @Override
    public int bind(long userId, int groupId) {
        return mapper.bind(userId, groupId);
    }

    @Override
    public List<UserGroup> getUserGroup(long userId) {
        return mapper.getUserGroup(userId);
    }
}
