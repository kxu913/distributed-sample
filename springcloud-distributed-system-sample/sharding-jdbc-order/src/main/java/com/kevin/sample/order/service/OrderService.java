package com.kevin.sample.order.service;



import java.util.List;
import com.kevin.sample.domain.Order;
public interface OrderService {
    public List<Order> getOrder();

    public long saveOrder(Order order);

    public int updateOrder(Order order);
}
