/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.domain;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class Role {

    private int roleId;

    private String roleName;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleId == role.roleId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }
}
