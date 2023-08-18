package com.kevin.sample.inventory.controller;

import com.kevin.sample.domain.Inventory;
import com.kevin.sample.inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/healthcheck")
    public String healthcheck(){
        return "ok";
    }

    @PostMapping("/inventory")
    public long inventory(@RequestBody Inventory inventory){
        return inventoryService.saveInventory(inventory);

    }

    @GetMapping("/inventory")
    public List<Inventory> getInventories(){
        return inventoryService.getInventories();

    }

    @PutMapping("/inventory")
    public long getInventories(@RequestBody Inventory inventory){
        return inventoryService.updateInventory(inventory.getInventoryId(),inventory.getPcs());

    }
}
