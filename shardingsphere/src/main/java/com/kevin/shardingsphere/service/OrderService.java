package com.kevin.shardingsphere.service;

import com.kevin.shardingsphere.domain.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getOrder();

    public int saveOrder(Order order);
}
