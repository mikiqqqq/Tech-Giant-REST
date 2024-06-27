package com.spring_boot.webshop_app.repository;

import com.spring_boot.webshop_app.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepo extends JpaRepository<PaymentMethod, Integer> {
}
