package com.alia.saga.payment.kafka;

import com.alia.saga.shared.events.InventoryReservedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryReservedConsumer {

    @KafkaListener(
            topics = "inventory-reserved",
            groupId = "payment-service"
    )
    public void handleInventoryReserved(InventoryReservedEvent event) {
        System.out.println(
                "Payment service received order: "
                        + event.getOrderId()
        );
    }
}