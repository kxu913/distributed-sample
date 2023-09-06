/**
 * auto generate by java-web-cli, created by @Kevin Xu
 */
package com.kevin.sample.uim.controller;

import com.kevin.sample.uim.domain.Resource;
import com.kevin.sample.uim.service.ResourceService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class ResourceController {

    private final ResourceService resourceService;

    @GetMapping("/resource")
    public List<Resource> getResource() {
        return resourceService.getResource();
    }

    @PostMapping("/resource")
    public long saveResource(@RequestBody Resource domain) {
        return  resourceService.saveResource(domain);
    }
    
}
