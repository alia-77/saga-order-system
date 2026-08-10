package com.alia.saga.shipping.repository;

import com.alia.saga.shipping.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}