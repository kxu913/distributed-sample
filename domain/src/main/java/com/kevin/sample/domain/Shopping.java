package com.kevin.sample.domain;

import lombok.Data;

@Data
public class Shopping {
    private Order order;
    private long inventoryId;
    private int pcs;
}
