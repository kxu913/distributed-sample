package com.kevin.sample.domain;

import lombok.Data;

import java.util.List;

@Data
public class UserDetail {
    private long userId;
    private String userName;

    private List<UserGroup> groups;

    private List<Role> roles;

    private List<ResourcePermission> permissions;


}
