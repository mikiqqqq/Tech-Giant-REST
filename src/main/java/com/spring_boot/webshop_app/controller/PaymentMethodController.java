package com.spring_boot.webshop_app.controller;

import com.spring_boot.webshop_app.model.PaymentMethod;
import com.spring_boot.webshop_app.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/payment-method")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @Autowired
    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping(value = "/get-all")
    ResponseEntity<List<PaymentMethod>> getAll() {
        return ResponseEntity
                .ok()
                .body(paymentMethodService.getAll());
    }
}
