/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service.impl;

import com.kevin.sample.uim.generator.IdGenerator;
import com.kevin.sample.uim.domain.*;
import com.kevin.sample.uim.mapper.UserMapper;
import com.kevin.sample.uim.service.GroupRoleService;
import com.kevin.sample.uim.service.RolePermissionService;
import com.kevin.sample.uim.service.UserGroupService;
import com.kevin.sample.uim.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    private final UserGroupService userGroupService;

    private final GroupRoleService groupRoleService;

    private final RolePermissionService rolePermissionService;


    @Override
    public List<User> getUsers() {
        return userMapper.getUser();
    }

    @Override
    public long saveUser(User domain) {
        long id = new IdGenerator(1, 1, 1).nextId();
        domain.setUserId(id);
        domain.setEnabled(true);
        userMapper.saveUser(domain);
        return id;
    }

    @Override
    public UserDetail getUser(long userId) {
        User user = userMapper.getUserById(userId);
        Assert.notNull(user,"user can't be null");
        Assert.isTrue(user.getUserId()!=0,"user can't be empty.");
        List<UserGroup> groups = userGroupService.getUserGroup(userId).stream().distinct().collect(Collectors.toList());
        List<GroupRole> tmpGroupRoles = new ArrayList<>();
        for (UserGroup ug : groups) {
            tmpGroupRoles.addAll(groupRoleService.getGroupRole(ug.getGroupId()));

        }
        List<GroupRole> groupRoles = tmpGroupRoles.stream().distinct().collect(Collectors.toList());

        List<RolePermission> tmpRolePermissions = new ArrayList<>();
        for (GroupRole ug : groupRoles) {
            tmpRolePermissions.addAll(rolePermissionService.getRolePermission(ug.getRoleId()));

        }
        List<RolePermission> rolePermissions = tmpRolePermissions.stream().distinct().collect(Collectors.toList());
        UserDetail userDetail = new UserDetail();
        userDetail.setUserId(userId);
        userDetail.setPassword(user.getPassword());
        userDetail.setGroups(groups);
        userDetail.setRoles(groupRoles.stream().map(GroupRole::mixinGroupRole).collect(Collectors.toList()));
        userDetail.setPermissions(rolePermissions.stream().map(RolePermission::mixinRolePermission).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList()));
        return userDetail;
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public void updatePassword(long userId, String password) {
        userMapper.updatePassword(userId,password);

    }

    public User validate(String userName, String password){
        return userMapper.getUserByNameAndPwd(userName,password);
    }
}
