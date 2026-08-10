package com.alia.saga.shipping.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shipments")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private String address;

    private String status;

    public Shipping() {
    }

    public Shipping(Long orderId, String address, String status) {
        this.orderId = orderId;
        this.address = address;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}