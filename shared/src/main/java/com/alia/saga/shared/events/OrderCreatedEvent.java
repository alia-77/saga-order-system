package com.alia.saga.shared.events;

public class OrderCreatedEvent {

    private Long orderId;
    private String productName;
    private Integer quantity;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(Long orderId, String productName, Integer quantity) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}