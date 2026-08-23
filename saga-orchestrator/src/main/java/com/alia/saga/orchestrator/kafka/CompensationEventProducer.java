package com.alia.saga.orchestrator.kafka;

import com.alia.saga.shared.commands.ReleaseInventoryCommand;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CompensationEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CompensationEventProducer(
            KafkaTemplate<String, Object> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishReleaseInventory(
            ReleaseInventoryCommand command
    ) {
        kafkaTemplate.send(
                "release-inventory",
                command.getOrderId().toString(),
                command
        );
    }
}