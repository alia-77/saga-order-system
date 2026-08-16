package com.alia.saga.shared.events;

public class InventoryReservationFailedEvent {

    private Long orderId;
    private String productName;
    private Integer quantity;

    public InventoryReservationFailedEvent() {
    }

    public InventoryReservationFailedEvent(Long orderId, String productName, Integer quantity) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getQuantity() {
        return quantity;
    }
}