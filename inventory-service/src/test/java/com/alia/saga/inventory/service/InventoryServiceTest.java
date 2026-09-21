package com.alia.saga.inventory.service;

import com.alia.saga.inventory.model.Inventory;
import com.alia.saga.inventory.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryService inventoryService;

    @Test
    void reserveInventoryDecreasesQuantityWhenStockIsAvailable() {
        Inventory inventory = new Inventory("Laptop", 10);
        when(inventoryRepository.findAllByProductName("Laptop"))
                .thenReturn(List.of(inventory));

        boolean result = inventoryService.reserveInventory("Laptop", 3);

        assertTrue(result);
        assertEquals(7, inventory.getQuantity());
        verify(inventoryRepository).save(inventory);
    }

    @Test
    void reserveInventoryReturnsFalseWhenStockIsInsufficient() {
        Inventory inventory = new Inventory("Laptop", 2);
        when(inventoryRepository.findAllByProductName("Laptop"))
                .thenReturn(List.of(inventory));

        boolean result = inventoryService.reserveInventory("Laptop", 3);

        assertFalse(result);
        assertEquals(2, inventory.getQuantity());
        verify(inventoryRepository, never()).save(any());
    }

    @Test
    void releaseInventoryRestoresQuantity() {
        Inventory inventory = new Inventory("Laptop", 7);
        when(inventoryRepository.findAllByProductName("Laptop"))
                .thenReturn(List.of(inventory));

        inventoryService.releaseInventory("Laptop", 3);

        assertEquals(10, inventory.getQuantity());
        verify(inventoryRepository).save(inventory);
    }
}
