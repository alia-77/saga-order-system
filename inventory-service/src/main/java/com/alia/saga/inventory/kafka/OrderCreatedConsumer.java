package com.alia.saga.inventory.kafka;

import com.alia.saga.inventory.service.InventoryService;
import com.alia.saga.shared.events.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedConsumer {

    private final InventoryService inventoryService;

    public OrderCreatedConsumer(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "order-created", groupId = "inventory-service")
    public void handleOrderCreated(OrderCreatedEvent event) {
        boolean reserved = inventoryService.reserveInventory(
                event.getProductName(),
                event.getQuantity()
        );

        System.out.println(
                "Order " + event.getOrderId()
                        + " inventory reservation: "
                        + (reserved ? "SUCCESS" : "FAILED")
        );
    }
}