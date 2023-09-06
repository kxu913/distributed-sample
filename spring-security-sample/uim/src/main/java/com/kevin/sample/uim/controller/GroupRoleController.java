package com.kevin.sample.uim.controller;


import com.kevin.sample.uim.domain.GroupRole;
import com.kevin.sample.uim.service.GroupRoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class GroupRoleController {

    private final GroupRoleService groupRoleService;

    @GetMapping("/gr/{groupId}")
    public List<GroupRole> getUserGroup(@PathVariable("groupId") int groupId) {
        return groupRoleService.getGroupRole(groupId);
    }

    @PostMapping("/gr/bind")
    public int bind(@RequestBody GroupRole domain) {
        return groupRoleService.bind( domain.getGroupId(),domain.getRoleId());
    }

}
