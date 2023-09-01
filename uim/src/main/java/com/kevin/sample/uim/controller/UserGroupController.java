package com.kevin.sample.uim.controller;

import com.kevin.sample.domain.Group;
import com.kevin.sample.domain.UserGroup;
import com.kevin.sample.uim.service.UserGroupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class UserGroupController {

    private final UserGroupService userGroupService;

    @GetMapping("/ug/{userId}")
    public List<UserGroup> getUserGroup(@PathVariable("userId") long userId) {
        return userGroupService.getUserGroup(userId);
    }

    @PostMapping("/ug/bind")
    public int bind(@RequestBody UserGroup domain) {
        return userGroupService.bind(domain.getUserId(), domain.getGroupId());
    }

}
