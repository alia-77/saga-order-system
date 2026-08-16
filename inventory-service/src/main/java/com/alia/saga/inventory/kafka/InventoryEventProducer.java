package com.alia.saga.inventory.kafka;

import com.alia.saga.shared.events.InventoryReservedEvent;
import com.alia.saga.shared.events.InventoryReservationFailedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public InventoryEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishReserved(InventoryReservedEvent event) {
        kafkaTemplate.send(
                "inventory-reserved",
                event.getOrderId().toString(),
                event
        );
    }

    public void publishReservationFailed(InventoryReservationFailedEvent event) {
        kafkaTemplate.send(
                "inventory-reservation-failed",
                event.getOrderId().toString(),
                event
        );
    }
}