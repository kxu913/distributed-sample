package com.kevin.sample.order.controller;

import com.google.common.base.Strings;

import com.kevin.sample.domain.Order;
import com.kevin.sample.order.service.OrderService;
import io.seata.core.context.RootContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/order")
    @ResponseBody
    public List<Order> hello() {
        return orderService.getOrder();
    }

    @PostMapping("/order")
    public long saveOrder(@RequestBody Order order){

        return orderService.saveOrder(order);
    }

    @PutMapping("/order")
    public long updateOrder(@RequestBody Order order){

        return orderService.updateOrder(order);
    }
}
