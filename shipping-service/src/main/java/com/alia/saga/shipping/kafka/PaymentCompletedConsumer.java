package com.alia.saga.shipping.kafka;

import com.alia.saga.shared.events.PaymentCompletedEvent;
import com.alia.saga.shared.events.ShipmentCreatedEvent;
import com.alia.saga.shipping.model.Shipping;
import com.alia.saga.shipping.repository.ShippingRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentCompletedConsumer {

    private final ShippingRepository shippingRepository;
    private final ShippingEventProducer shippingEventProducer;

    public PaymentCompletedConsumer(
            ShippingRepository shippingRepository,
            ShippingEventProducer shippingEventProducer
    ) {
        this.shippingRepository = shippingRepository;
        this.shippingEventProducer = shippingEventProducer;
    }

    @KafkaListener(
            topics = "payment-completed",
            groupId = "shipping-service"
    )
    public void handlePaymentCompleted(
            PaymentCompletedEvent event
    ) {

        Shipping shipping = new Shipping(
                event.getOrderId(),
                "Default Address",
                "READY"
        );

        shippingRepository.save(shipping);

        shippingEventProducer.publishShipmentCreated(
                new ShipmentCreatedEvent(event.getOrderId())
        );

        System.out.println(
                "Shipment created for order: "
                        + event.getOrderId()
        );
    }
}