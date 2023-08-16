package com.kevin.sample.order.service.impl;



import com.kevin.sample.order.domain.Order;
import com.kevin.sample.order.mapper.OrderMapper;
import com.kevin.sample.order.service.OrderService;
import org.apache.shardingsphere.transaction.annotation.ShardingSphereTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Order> getOrder() {
        return orderMapper.getOrder();
    }

    @Override
//     @GlobalTransactional(name = "order-group",timeoutMills = 300000 )
    @Transactional
    @ShardingSphereTransactionType(TransactionType.BASE)
    public int saveOrder(Order order) {
        return orderMapper.saveOrder(order);
    }
}
