/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service;
import com.kevin.sample.uim.domain.Permission;

import java.util.List;

public interface PermissionService {
    public List<Permission> getPermission();

    public long savePermission(Permission domain);

}
