package com.alia.saga.payment.kafka;

import com.alia.saga.payment.service.PaymentService;
import com.alia.saga.shared.events.InventoryReservedEvent;
import com.alia.saga.shared.events.PaymentCompletedEvent;
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
class InventoryReservedConsumerTest {

    @Mock
    private PaymentService paymentService;

    @Mock
    private PaymentEventProducer paymentEventProducer;

    @InjectMocks
    private InventoryReservedConsumer consumer;

    @Test
    void oddOrderPublishesPaymentCompleted() {
        InventoryReservedEvent event =
                new InventoryReservedEvent(1L, "Laptop", 2);

        consumer.handleInventoryReserved(event);

        verify(paymentService).processPayment(event);

        ArgumentCaptor<PaymentCompletedEvent> captor =
                ArgumentCaptor.forClass(PaymentCompletedEvent.class);
        verify(paymentEventProducer).publishPaymentCompleted(captor.capture());

        assertEquals(1L, captor.getValue().getOrderId());
        verify(paymentEventProducer, never())
                .publishPaymentFailed(any(PaymentFailedEvent.class));
    }

    @Test
    void evenOrderPublishesPaymentFailed() {
        InventoryReservedEvent event =
                new InventoryReservedEvent(2L, "Laptop", 2);

        consumer.handleInventoryReserved(event);

        verify(paymentService, never()).processPayment(any());

        ArgumentCaptor<PaymentFailedEvent> captor =
                ArgumentCaptor.forClass(PaymentFailedEvent.class);
        verify(paymentEventProducer).publishPaymentFailed(captor.capture());

        PaymentFailedEvent failedEvent = captor.getValue();
        assertEquals(2L, failedEvent.getOrderId());
        assertEquals("Laptop", failedEvent.getProductName());
        assertEquals(2, failedEvent.getQuantity());

        verify(paymentEventProducer, never())
                .publishPaymentCompleted(any(PaymentCompletedEvent.class));
    }
}
