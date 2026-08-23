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

        List<Inventory> inventories =
                inventoryRepository.findAllByProductName(productName);

        for (Inventory inventory : inventories) {

            if (inventory.getQuantity() >= quantity) {

                inventory.setQuantity(
                        inventory.getQuantity() - quantity
                );

                inventoryRepository.save(inventory);

                return true;
            }
        }

        return false;
    }

    public void releaseInventory(
            String productName,
            Integer quantity
    ) {

        List<Inventory> inventories =
                inventoryRepository.findAllByProductName(productName);

        for (Inventory inventory : inventories) {

            inventory.setQuantity(
                    inventory.getQuantity() + quantity
            );

            inventoryRepository.save(inventory);

            return;
        }
    }
}