package com.kevin.sample.uim.controller;

import com.kevin.sample.domain.Permission;
import com.kevin.sample.domain.RolePermission;
import com.kevin.sample.uim.service.RolePermissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    @GetMapping("/rp/{roleId}")
    public List<RolePermission> getRolePermission(@PathVariable("roleId") int roleId) {
        return rolePermissionService.getRolePermission(roleId);
    }

    @PostMapping("/rp")
    public long savePermission(@RequestBody RolePermission domain) {
        return rolePermissionService.bind(domain.getRoleId(),domain.getPermissionId());
    }

}
