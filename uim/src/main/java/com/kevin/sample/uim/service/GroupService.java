/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service;
import java.util.List;
import com.kevin.sample.domain.Group;
public interface GroupService {
    public List<Group> getGroup();

    public long saveGroup(Group domain);

}
