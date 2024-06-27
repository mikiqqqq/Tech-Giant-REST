package com.spring_boot.webshop_app.service;

import com.spring_boot.webshop_app.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {
    List<PaymentMethod> getAll();
}
