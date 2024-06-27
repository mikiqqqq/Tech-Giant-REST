package com.spring_boot.webshop_app.controller;

import com.spring_boot.webshop_app.model.DiscountCode;
import com.spring_boot.webshop_app.service.DiscountCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/discount-code")
public class DiscountCodeController {

    private final DiscountCodeService discountCodeService;

    @Autowired
    public DiscountCodeController(DiscountCodeService discountCodeService) {
        this.discountCodeService = discountCodeService;
    }

    @GetMapping(value = "/get-active")
    ResponseEntity<DiscountCode> getActive() {

        return ResponseEntity
                .ok()
                .body(discountCodeService.findFirstActive());
    }

    @GetMapping(value = "/find-by-code/{discountCode}")
    ResponseEntity<DiscountCode> getDiscountCodeByCode(@PathVariable String discountCode) {

        return ResponseEntity
                .ok()
                .body(discountCodeService.findByDiscountCode(discountCode));
    }
}
