package com.alia.saga.shared.events;

public class PaymentFailedEvent {

    private Long orderId;

    public PaymentFailedEvent() {
    }

    public PaymentFailedEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}