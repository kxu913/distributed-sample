package com.kevin.sample.inventory.service.impl;

import com.google.common.base.Strings;
import com.kevin.sample.domain.Inventory;
import com.kevin.sample.inventory.mapper.InventoryMapper;
import com.kevin.sample.inventory.service.InventoryService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingSphereTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private final InventoryMapper mapper;

    @Override
    public List<Inventory> getInventories() {
        return mapper.getInventories();
    }

    @Override
    public long saveInventory(Inventory inventory) {
        inventory.setCreatedTime(new Date());
        return mapper.saveInventory(inventory);
    }

    @Override
    @GlobalTransactional(name="inventory-group")
    @ShardingSphereTransactionType(TransactionType.BASE)
    public long updateInventory( long id) {
        return mapper.updateInventory(id);
    }

    @Override
    public Inventory getInventory(long id) {
        return mapper.getInventory(id);
    }
}
