package com.kevin.sample.uim.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class GroupRole {

    private int groupId;
    private String groupName;
    private int roleId;
    private String roleName;
    private boolean deleted;

    public Role mixinGroupRole(){
        Role role = new Role();
        role.setRoleId(this.roleId);
        role.setRoleName(this.roleName);
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupRole groupRole = (GroupRole) o;
        return groupId == groupRole.groupId && roleId == groupRole.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, roleId);
    }
}
