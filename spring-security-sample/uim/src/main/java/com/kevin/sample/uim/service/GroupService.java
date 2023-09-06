/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service;

import com.kevin.sample.uim.domain.Group;

import java.util.List;

public interface GroupService {
    public List<Group> getGroup();

    public long saveGroup(Group domain);

}
