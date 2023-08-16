package com.kevin.sample.order.controller;

import com.kevin.sample.order.domain.Order;
import com.kevin.sample.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RefreshScope
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;


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
