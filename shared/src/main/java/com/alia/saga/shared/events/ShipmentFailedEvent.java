package com.alia.saga.shared.events;

public class ShipmentFailedEvent {

    private Long orderId;

    public ShipmentFailedEvent() {
    }

    public ShipmentFailedEvent(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
}
