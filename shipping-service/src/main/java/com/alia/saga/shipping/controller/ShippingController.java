package com.alia.saga.shipping.controller;

import com.alia.saga.shipping.model.Shipping;
import com.alia.saga.shipping.service.ShippingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping
    public Shipping createShipping(@RequestBody Shipping shipping) {
        return shippingService.createShipping(shipping);
    }

    @GetMapping
    public List<Shipping> getAllShipping() {
        return shippingService.getAllShipping();
    }

    @GetMapping("/{id}")
    public Shipping getShippingById(@PathVariable("id") Long id) {
        return shippingService.getShippingById(id);
    }
}