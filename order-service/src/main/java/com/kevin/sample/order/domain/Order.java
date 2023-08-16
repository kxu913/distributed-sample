package com.kevin.sample.order.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Order {


    private long orderId;
    private int userId;
    private long sId;
    private String description;
    private Date createdTime;
}
