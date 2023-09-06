/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.service;
import java.util.List;
import com.kevin.sample.uim.domain.Role;
public interface RoleService {
    public List<Role> getRole();

    public long saveRole(Role domain);

}
