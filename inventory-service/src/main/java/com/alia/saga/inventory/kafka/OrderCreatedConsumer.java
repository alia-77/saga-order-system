package com.alia.saga.inventory.kafka;

import com.alia.saga.shared.events.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedConsumer {

    @KafkaListener(topics = "order-created", groupId = "inventory-service")
    public void handleOrderCreated(OrderCreatedEvent event) {
        System.out.println(
                "Received order: " + event.getOrderId()
                        + ", product: " + event.getProductName()
                        + ", quantity: " + event.getQuantity()
        );
    }
}