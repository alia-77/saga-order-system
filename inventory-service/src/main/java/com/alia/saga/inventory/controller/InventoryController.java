package com.alia.saga.inventory.controller;

import com.alia.saga.inventory.model.Inventory;
import com.alia.saga.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryService.createInventory(inventory);
    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable("id") Long id) {
        return inventoryService.getInventoryById(id);
    }

    @PostMapping("/reserve")
    public boolean reserveInventory(
            @RequestParam("productName") String productName,
            @RequestParam("quantity") Integer quantity) {

        return inventoryService.reserveInventory(productName, quantity);
    }
}