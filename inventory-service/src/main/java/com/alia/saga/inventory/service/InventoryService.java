package com.alia.saga.inventory.service;

import com.alia.saga.inventory.model.Inventory;
import com.alia.saga.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public boolean reserveInventory(String productName, Integer quantity) {
        Inventory inventory = inventoryRepository.findByProductName(productName)
                .orElse(null);

        if (inventory == null || inventory.getQuantity() < quantity) {
            return false;
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);

        return true;
    }
}