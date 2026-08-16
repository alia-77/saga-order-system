package com.alia.saga.inventory.kafka;

import com.alia.saga.inventory.service.InventoryService;
import com.alia.saga.shared.events.InventoryReservationFailedEvent;
import com.alia.saga.shared.events.InventoryReservedEvent;
import com.alia.saga.shared.events.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedConsumer {

    private final InventoryService inventoryService;
    private final InventoryEventProducer inventoryEventProducer;

    public OrderCreatedConsumer(
            InventoryService inventoryService,
            InventoryEventProducer inventoryEventProducer
    ) {
        this.inventoryService = inventoryService;
        this.inventoryEventProducer = inventoryEventProducer;
    }

    @KafkaListener(topics = "order-created", groupId = "inventory-service")
    public void handleOrderCreated(OrderCreatedEvent event) {
        boolean reserved = inventoryService.reserveInventory(
                event.getProductName(),
                event.getQuantity()
        );

        if (reserved) {
            inventoryEventProducer.publishReserved(
                    new InventoryReservedEvent(
                            event.getOrderId(),
                            event.getProductName(),
                            event.getQuantity()
                    )
            );
        } else {
            inventoryEventProducer.publishReservationFailed(
                    new InventoryReservationFailedEvent(
                            event.getOrderId(),
                            event.getProductName(),
                            event.getQuantity()
                    )
            );
        }

        System.out.println(
                "Order " + event.getOrderId()
                        + " inventory reservation: "
                        + (reserved ? "SUCCESS" : "FAILED")
        );
    }
}