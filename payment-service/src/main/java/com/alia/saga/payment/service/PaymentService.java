package com.alia.saga.payment.service;

import com.alia.saga.payment.model.Payment;
import com.alia.saga.payment.repository.PaymentRepository;
import com.alia.saga.shared.events.InventoryReservedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(Payment payment) {
        payment.setStatus("COMPLETED");
        return paymentRepository.save(payment);
    }

    public Payment processPayment(InventoryReservedEvent event) {
        Payment payment = new Payment();
        payment.setOrderId(event.getOrderId());
        payment.setAmount(100.0);
        payment.setStatus("COMPLETED");

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }
}