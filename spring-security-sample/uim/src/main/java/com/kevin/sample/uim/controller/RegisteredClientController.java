/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.controller;


import com.kevin.sample.uim.domain.SampleRegisteredClient;
import com.kevin.sample.uim.service.RegisteredClientService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class RegisteredClientController {

    private final RegisteredClientService registeredClientService;

    @GetMapping("/clients")
    public List<SampleRegisteredClient> getRegisteredClient() {
        return registeredClientService.getRegisteredClients();
    }

    @PostMapping("/register")
    public long register(@RequestBody SampleRegisteredClient domain) {
        return  registeredClientService.saveRegisteredClient(domain);
    }
    
}
