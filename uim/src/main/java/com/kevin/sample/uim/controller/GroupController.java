/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.controller;

import com.google.common.base.Strings;

import com.kevin.sample.domain.Group;
import com.kevin.sample.uim.service.GroupService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/group")
    public List<Group> getGroup() {
        return groupService.getGroup();
    }

    @PostMapping("/group")
    public long saveGroup(@RequestBody Group domain) {
        return  groupService.saveGroup(domain);
    }
    
}
