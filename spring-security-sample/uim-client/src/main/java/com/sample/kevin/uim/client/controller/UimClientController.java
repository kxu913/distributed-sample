package com.sample.kevin.uim.client.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@EnableMethodSecurity
public class UimClientController {
    @GetMapping("/greet")
    public Mono<String> auth(Authentication auth) {

        if(auth!=null){
            return Mono.just(auth.getName());
        }
        return Mono.just("guest");
    }

    @GetMapping("/")
    public Mono<String> index() {
        return Mono.just("you have got authorization, you can redirect to another pages.");
    }

    @GetMapping("/greet2")
    @PreAuthorize("hasAuthority('SCOPE_user:write')")
    public Mono<String> noAuth(Authentication auth) {

        if(auth!=null){
            return Mono.just("from 2 ->"+ auth.getName());
        }
        return Mono.just("from 2 -> guest");
    }

}
