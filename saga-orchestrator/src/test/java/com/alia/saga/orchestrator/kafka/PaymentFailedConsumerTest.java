package com.alia.saga.orchestrator.kafka;

import com.alia.saga.shared.commands.ReleaseInventoryCommand;
import com.alia.saga.shared.events.PaymentFailedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentFailedConsumerTest {

    @Mock
    private CompensationEventProducer compensationEventProducer;

    @InjectMocks
    private PaymentFailedConsumer consumer;

    @Test
    void paymentFailurePublishesReleaseInventoryCommand() {
        PaymentFailedEvent event =
                new PaymentFailedEvent(2L, "Laptop", 3);

        consumer.handlePaymentFailed(event);

        ArgumentCaptor<ReleaseInventoryCommand> captor =
                ArgumentCaptor.forClass(ReleaseInventoryCommand.class);
        verify(compensationEventProducer)
                .publishReleaseInventory(captor.capture());

        ReleaseInventoryCommand command = captor.getValue();
        assertEquals(2L, command.getOrderId());
        assertEquals("Laptop", command.getProductName());
        assertEquals(3, command.getQuantity());
    }
}
