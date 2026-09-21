package com.alia.saga.payment.service;

import com.alia.saga.payment.model.Payment;
import com.alia.saga.payment.repository.PaymentRepository;
import com.alia.saga.shared.events.InventoryReservedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void createPaymentSetsCompletedStatusAndSaves() {
        Payment payment = new Payment(1L, 100.0, "PENDING");
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment result = paymentService.createPayment(payment);

        assertSame(payment, result);
        assertEquals("COMPLETED", payment.getStatus());
        verify(paymentRepository).save(payment);
    }

    @Test
    void processPaymentCreatesCompletedPayment() {
        InventoryReservedEvent event =
                new InventoryReservedEvent(1L, "Laptop", 2);

        Payment savedPayment = mock(Payment.class);
        when(paymentRepository.save(any(Payment.class))).thenReturn(savedPayment);

        Payment result = paymentService.processPayment(event);

        assertSame(savedPayment, result);

        ArgumentCaptor<Payment> captor =
                ArgumentCaptor.forClass(Payment.class);
        verify(paymentRepository).save(captor.capture());

        Payment payment = captor.getValue();
        assertEquals(1L, payment.getOrderId());
        assertEquals(100.0, payment.getAmount());
        assertEquals("COMPLETED", payment.getStatus());
    }

    @Test
    void getAllPaymentsReturnsPayments() {
        List<Payment> payments =
                List.of(new Payment(1L, 100.0, "COMPLETED"));
        when(paymentRepository.findAll()).thenReturn(payments);

        assertSame(payments, paymentService.getAllPayments());
    }

    @Test
    void getPaymentByIdReturnsPaymentWhenFound() {
        Payment payment = new Payment(1L, 100.0, "COMPLETED");
        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));

        assertSame(payment, paymentService.getPaymentById(1L));
    }

    @Test
    void getPaymentByIdReturnsNullWhenNotFound() {
        when(paymentRepository.findById(1L)).thenReturn(Optional.empty());

        assertNull(paymentService.getPaymentById(1L));
    }
}
