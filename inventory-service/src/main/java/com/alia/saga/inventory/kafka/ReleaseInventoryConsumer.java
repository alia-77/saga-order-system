package com.alia.saga.inventory.kafka;

import com.alia.saga.inventory.service.InventoryService;
import com.alia.saga.shared.commands.ReleaseInventoryCommand;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReleaseInventoryConsumer {

    private final InventoryService inventoryService;

    public ReleaseInventoryConsumer(
            InventoryService inventoryService
    ) {
        this.inventoryService = inventoryService;
    }

    @KafkaListener(
            topics = "release-inventory",
            groupId = "inventory-service-compensation"
    )
    public void handleReleaseInventory(
            ReleaseInventoryCommand command
    ) {

        inventoryService.releaseInventory(
                command.getProductName(),
                command.getQuantity()
        );

        System.out.println(
                "Inventory released for order: "
                        + command.getOrderId()
        );
    }
}