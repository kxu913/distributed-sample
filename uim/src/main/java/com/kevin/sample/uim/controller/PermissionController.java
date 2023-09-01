/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.controller;

import com.google.common.base.Strings;

import com.kevin.sample.domain.Permission;
import com.kevin.sample.uim.service.PermissionService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping("/permission")
    public List<Permission> getPermission() {
        return permissionService.getPermission();
    }

    @PostMapping("/permission")
    public long savePermission(@RequestBody Permission domain) {
        return permissionService.savePermission(domain);
    }

}
