package com.kevin.sample.domain;

import com.kevin.sample.domain.enums.OrderStatus;
import lombok.Data;

import java.util.Date;

@Data
public class Order {


    private long orderId;
    private int userId;
    private long inventoryId;
    private String description;
    private Date createdTime;
    private OrderStatus status;

    public Order withUserId(int userId){
        this.userId = userId;
        return this;
    }
    public Order withInventoryId(long inventoryId){
        this.inventoryId = inventoryId;
        return this;
    }
    public Order buildSeckillOrder(){
        this.description = "second kill";
        this.createdTime = new Date();
        this.status = OrderStatus.CREATED;
        return this;
    }
}
