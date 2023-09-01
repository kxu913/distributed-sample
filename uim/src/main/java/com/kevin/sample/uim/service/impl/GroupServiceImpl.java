/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service.impl;

import com.kevin.sample.domain.Group;
import com.kevin.sample.uim.mapper.GroupMapper;
import com.kevin.sample.uim.service.GroupService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class GroupServiceImpl implements GroupService {
    private final GroupMapper groupMapper;

    public GroupServiceImpl(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    @Override
    public List<Group> getGroup() {
        return groupMapper.getGroup();
    }
    @Override
    public long saveGroup(Group domain) {
        return  groupMapper.saveGroup(domain);
    }
}
