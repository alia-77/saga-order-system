package com.alia.saga.shipping.service;

import com.alia.saga.shipping.model.Shipping;
import com.alia.saga.shipping.repository.ShippingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShippingServiceTest {

    @Mock
    private ShippingRepository shippingRepository;

    @InjectMocks
    private ShippingService shippingService;

    @Test
    void createShippingSetsReadyStatusAndSaves() {
        Shipping shipping = new Shipping(1L, "Alexandria", "PENDING");
        when(shippingRepository.save(shipping)).thenReturn(shipping);

        Shipping result = shippingService.createShipping(shipping);

        assertSame(shipping, result);
        assertEquals("READY", shipping.getStatus());
        verify(shippingRepository).save(shipping);
    }

    @Test
    void getAllShippingReturnsShipments() {
        List<Shipping> shipments =
                List.of(new Shipping(1L, "Alexandria", "READY"));
        when(shippingRepository.findAll()).thenReturn(shipments);

        assertSame(shipments, shippingService.getAllShipping());
    }

    @Test
    void getShippingByIdReturnsShippingWhenFound() {
        Shipping shipping =
                new Shipping(1L, "Alexandria", "READY");
        when(shippingRepository.findById(1L))
                .thenReturn(Optional.of(shipping));

        assertSame(shipping, shippingService.getShippingById(1L));
    }

    @Test
    void getShippingByIdReturnsNullWhenNotFound() {
        when(shippingRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertNull(shippingService.getShippingById(1L));
    }
}
