package com.alia.saga.order.service;

import com.alia.saga.order.kafka.OrderEventProducer;
import com.alia.saga.order.model.Order;
import com.alia.saga.order.repository.OrderRepository;
import com.alia.saga.shared.events.OrderCreatedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventProducer orderEventProducer;

    public OrderService(
            OrderRepository orderRepository,
            OrderEventProducer orderEventProducer) {
        this.orderRepository = orderRepository;
        this.orderEventProducer = orderEventProducer;
    }

    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getProductName(),
                savedOrder.getQuantity()
        );

        orderEventProducer.publishOrderCreated(event);

        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
}