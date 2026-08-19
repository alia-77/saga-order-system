package com.alia.saga.payment.kafka;

import com.alia.saga.shared.events.PaymentCompletedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishPaymentCompleted(PaymentCompletedEvent event) {
        kafkaTemplate.send(
                "payment-completed",
                event.getOrderId().toString(),
                event
        );
    }
}