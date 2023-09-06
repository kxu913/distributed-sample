package com.kevin.sample.uim.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDetail {
    private long userId;
    private String userName;

    private String password;

    private List<UserGroup> groups;

    private List<Role> roles;

    private List<ResourcePermission> permissions;

    public List<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<>(permissions.size());
        permissions.forEach(p->{
            authorities.add(new SimpleGrantedAuthority(p.getPath().toLowerCase()+":"+p.getOperator().toLowerCase()));

        });
        return authorities;
    }

}
