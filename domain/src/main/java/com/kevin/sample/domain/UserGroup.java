package com.kevin.sample.domain;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class UserGroup {
    private long userId;
    private int groupId;
    private String groupName;
    private boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroup userGroup = (UserGroup) o;
        return userId == userGroup.userId && groupId == userGroup.groupId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupId);
    }
}
