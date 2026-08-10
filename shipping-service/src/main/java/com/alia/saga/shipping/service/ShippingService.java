package com.alia.saga.shipping.service;

import com.alia.saga.shipping.model.Shipping;
import com.alia.saga.shipping.repository.ShippingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingService {

    private final ShippingRepository shippingRepository;

    public ShippingService(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    public Shipping createShipping(Shipping shipping) {
        shipping.setStatus("READY");
        return shippingRepository.save(shipping);
    }

    public List<Shipping> getAllShipping() {
        return shippingRepository.findAll();
    }

    public Shipping getShippingById(Long id) {
        return shippingRepository.findById(id).orElse(null);
    }
}