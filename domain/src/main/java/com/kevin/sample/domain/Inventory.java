package com.kevin.sample.domain;

import com.kevin.sample.domain.enums.InventoryStatus;
import lombok.Data;

import java.util.Date;

@Data
public class Inventory {

    private long inventoryId;
    private String name;
    private int pcs;
    private int sale;
    private InventoryStatus status;
    private Date createdTime;

}
