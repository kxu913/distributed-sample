package com.kevin.sample.order.service;



import com.kevin.sample.order.domain.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getOrder();

    public int saveOrder(Order order);
}
