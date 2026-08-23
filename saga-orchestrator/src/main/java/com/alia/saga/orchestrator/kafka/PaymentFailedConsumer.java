package com.alia.saga.orchestrator.kafka;

import com.alia.saga.shared.commands.ReleaseInventoryCommand;
import com.alia.saga.shared.events.PaymentFailedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentFailedConsumer {

    private final CompensationEventProducer compensationEventProducer;

    public PaymentFailedConsumer(
            CompensationEventProducer compensationEventProducer
    ) {
        this.compensationEventProducer = compensationEventProducer;
    }

    @KafkaListener(
            topics = "payment-failed",
            groupId = "saga-orchestrator"
    )
    public void handlePaymentFailed(PaymentFailedEvent event) {

        System.out.println(
                "Saga compensation triggered for order: "
                        + event.getOrderId()
        );

        ReleaseInventoryCommand command =
                new ReleaseInventoryCommand(
                        event.getOrderId(),
                        event.getProductName(),
                        event.getQuantity()
                );

        compensationEventProducer.publishReleaseInventory(command);
    }
}