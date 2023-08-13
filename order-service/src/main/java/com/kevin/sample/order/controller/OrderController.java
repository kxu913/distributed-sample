package com.kevin.sample.order.controller;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class OrderController {

    @GetMapping("/order")
    public String getOrder(){
        return "order service";
    }

    @GetMapping("/orders")
    public String list(){
        return "list service";
    }
}
