package com.kevin.sample.seckill.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Inventory {

    private long inventoryId;
    private String name;
    private int pcs;
    private int sale;
    private String status;
    private Date createdTime;

}
