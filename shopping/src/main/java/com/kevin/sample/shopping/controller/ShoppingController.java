package com.kevin.sample.shopping.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@AllArgsConstructor
public class ShoppingController {

    @Autowired
    private final RestTemplate restTemplate;

    @GetMapping("/shopping/order")
    public String getOrder(){
        String orderMsg=  restTemplate.getForObject("http://order/order",String.class);
        return "I am from "+ orderMsg;
    }
}
