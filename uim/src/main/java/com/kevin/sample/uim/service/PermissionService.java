/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service;
import java.util.List;
import com.kevin.sample.domain.Permission;
public interface PermissionService {
    public List<Permission> getPermission();

    public long savePermission(Permission domain);

}
