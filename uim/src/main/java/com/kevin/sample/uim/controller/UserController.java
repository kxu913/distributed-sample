/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.controller;

import com.google.common.base.Strings;

import com.kevin.sample.domain.User;
import com.kevin.sample.domain.UserDetail;
import com.kevin.sample.uim.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/user")
    public long saveUser(@RequestBody User domain) {
        return  userService.saveUser(domain);
    }

    @GetMapping("/user/{userId}")
    public UserDetail getUser(@PathVariable("userId") long userId) {
        return userService.getUser(userId);
    }
    
}
