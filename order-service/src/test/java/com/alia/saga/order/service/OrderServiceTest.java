package com.alia.saga.order.service;

import com.alia.saga.order.kafka.OrderEventProducer;
import com.alia.saga.order.model.Order;
import com.alia.saga.order.repository.OrderRepository;
import com.alia.saga.shared.events.OrderCreatedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderEventProducer orderEventProducer;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrderSavesOrderAndPublishesEvent() {
        Order order = new Order("Alice", "Laptop", 2);
        Order savedOrder = mock(Order.class);

        when(savedOrder.getId()).thenReturn(42L);
        when(savedOrder.getProductName()).thenReturn("Laptop");
        when(savedOrder.getQuantity()).thenReturn(2);
        when(orderRepository.save(order)).thenReturn(savedOrder);

        Order result = orderService.createOrder(order);

        assertSame(savedOrder, result);
        verify(orderRepository).save(order);

        ArgumentCaptor<OrderCreatedEvent> captor =
                ArgumentCaptor.forClass(OrderCreatedEvent.class);
        verify(orderEventProducer).publishOrderCreated(captor.capture());

        OrderCreatedEvent event = captor.getValue();
        assertEquals(42L, event.getOrderId());
        assertEquals("Laptop", event.getProductName());
        assertEquals(2, event.getQuantity());
    }

    @Test
    void getAllOrdersReturnsOrders() {
        List<Order> orders = List.of(
                new Order("Alice", "Laptop", 2),
                new Order("Bob", "Phone", 1)
        );
        when(orderRepository.findAll()).thenReturn(orders);

        assertSame(orders, orderService.getAllOrders());
    }

    @Test
    void getOrderByIdReturnsOrderWhenFound() {
        Order order = new Order("Alice", "Laptop", 2);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        assertSame(order, orderService.getOrderById(1L));
    }

    @Test
    void getOrderByIdReturnsNullWhenNotFound() {
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        assertNull(orderService.getOrderById(1L));
    }
}
