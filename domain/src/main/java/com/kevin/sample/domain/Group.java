/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.domain;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class Group {

    private int groupId;

    private String groupName;

    private String groupDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupId == group.groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId);
    }
}
