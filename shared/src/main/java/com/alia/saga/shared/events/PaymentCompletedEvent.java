package com.alia.saga.shared.events;

public class PaymentCompletedEvent {

    private Long orderId;

    public PaymentCompletedEvent() {
    }

    public PaymentCompletedEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}