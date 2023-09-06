package com.kevin.sample.inventory.service;

import com.kevin.sample.domain.Inventory;

import java.util.List;

public interface InventoryService {

    public List<Inventory> getInventories();

    public long saveInventory(Inventory inventory);

    public long updateInventory(long id);

    public Inventory getInventory(long id);
}
