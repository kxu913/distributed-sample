package com.kevin.sample.order.service.impl;



import com.kevin.sample.domain.Order;
import com.kevin.sample.order.mapper.OrderMapper;
import com.kevin.sample.order.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingSphereTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
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
    @GlobalTransactional(name = "order-group",timeoutMills = 300000 )
    @ShardingSphereTransactionType(TransactionType.BASE)
    public long saveOrder(Order order) {
        order.setCreatedTime(new Date());
        orderMapper.saveOrder(order);
        return order.getOrderId();
    }
}
