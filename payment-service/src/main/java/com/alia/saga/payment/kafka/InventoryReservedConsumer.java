package com.alia.saga.payment.kafka;

import com.alia.saga.payment.service.PaymentService;
import com.alia.saga.shared.events.InventoryReservedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryReservedConsumer {

    private final PaymentService paymentService;

    public InventoryReservedConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(
            topics = "inventory-reserved",
            groupId = "payment-service"
    )
    public void handleInventoryReserved(InventoryReservedEvent event) {
        paymentService.processPayment(event);

        System.out.println(
                "Payment completed for order: "
                        + event.getOrderId()
        );
    }
}