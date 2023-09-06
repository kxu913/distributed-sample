package com.sample.kevin.uim.resoucesever.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@EnableMethodSecurity
public class ResourceDemo {
    @GetMapping("/greet")
    @PreAuthorize("hasAuthority('SCOPE_/pr:read')")

    public Mono<String> getGreeting(Authentication auth) {

        if(auth!=null){
            return Mono.just(auth.getName());
        }
        return Mono.just("guest");
    }
    @GetMapping("/greet2")
    public Mono<String> noAuth(Authentication auth) {

        if(auth!=null){
            return Mono.just(auth.getName());
        }
        return Mono.just("guest");
    }

}
