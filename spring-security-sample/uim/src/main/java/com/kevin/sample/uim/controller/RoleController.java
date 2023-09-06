/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.controller;


import com.kevin.sample.uim.domain.Role;
import com.kevin.sample.uim.service.RoleService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/role")
    public List<Role> getRole() {
        return roleService.getRole();
    }

    @PostMapping("/role")
    public long saveRole(@RequestBody Role domain) {
        return  roleService.saveRole(domain);
    }
    
}
