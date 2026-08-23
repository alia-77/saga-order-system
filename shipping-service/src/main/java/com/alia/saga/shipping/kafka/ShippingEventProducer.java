package com.alia.saga.shipping.kafka;

import com.alia.saga.shared.events.ShipmentCreatedEvent;
import com.alia.saga.shared.events.ShipmentFailedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShippingEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ShippingEventProducer(
            KafkaTemplate<String, Object> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishShipmentCreated(ShipmentCreatedEvent event) {
        kafkaTemplate.send(
                "shipment-created",
                event.getOrderId().toString(),
                event
        );
    }

    public void publishShipmentFailed(ShipmentFailedEvent event) {
        kafkaTemplate.send(
                "shipment-failed",
                event.getOrderId().toString(),
                event
        );
    }
}