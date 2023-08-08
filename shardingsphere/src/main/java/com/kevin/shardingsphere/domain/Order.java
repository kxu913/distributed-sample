package com.kevin.shardingsphere.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private int orderId;
    private int userId;
    private String description;
    private Date createdTime;
}
