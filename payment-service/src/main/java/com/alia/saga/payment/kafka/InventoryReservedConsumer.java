package com.alia.saga.payment.kafka;

import com.alia.saga.payment.service.PaymentService;
import com.alia.saga.shared.events.InventoryReservedEvent;
import com.alia.saga.shared.events.PaymentCompletedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryReservedConsumer {

    private final PaymentService paymentService;
    private final PaymentEventProducer paymentEventProducer;

    public InventoryReservedConsumer(
            PaymentService paymentService,
            PaymentEventProducer paymentEventProducer
    ) {
        this.paymentService = paymentService;
        this.paymentEventProducer = paymentEventProducer;
    }

    @KafkaListener(
            topics = "inventory-reserved",
            groupId = "payment-service"
    )
    public void handleInventoryReserved(InventoryReservedEvent event) {
        paymentService.processPayment(event);

        paymentEventProducer.publishPaymentCompleted(
                new PaymentCompletedEvent(event.getOrderId())
        );

        System.out.println(
                "Payment completed for order: "
                        + event.getOrderId()
        );
    }
}