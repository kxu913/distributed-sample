package com.kevin.shardingsphere.controller;


import java.util.Date;
import java.util.List;

import com.kevin.shardingsphere.domain.Order;

import com.kevin.shardingsphere.service.OrderService;

import org.springframework.web.bind.annotation.*;


@RestController

public class DemoController {

    private final OrderService orderService;

    public DemoController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping("/order")
    @ResponseBody
    public List<Order> hello() {
        return orderService.getOrder();
    }

    @PostMapping("/order")
    public int saveOrder(@RequestBody Order order){
        order.setCreatedTime(new Date());

        return orderService.saveOrder(order);
    }
}
