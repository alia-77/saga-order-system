package com.alia.saga.order.controller;

import com.alia.saga.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private final OrderService orderService;

    public HealthController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String health() {
        return "Order Service is running!";
    }
}