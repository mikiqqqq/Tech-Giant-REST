package com.spring_boot.webshop_app.service;

import com.spring_boot.webshop_app.model.DiscountCode;

public interface DiscountCodeService {
    DiscountCode findFirstActive();
    DiscountCode findByDiscountCode(String discountCode);
}
