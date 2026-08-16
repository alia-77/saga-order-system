package com.alia.saga.shared.events;

public class ShipmentCreatedEvent {

    private Long orderId;

    public ShipmentCreatedEvent() {
    }

    public ShipmentCreatedEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}