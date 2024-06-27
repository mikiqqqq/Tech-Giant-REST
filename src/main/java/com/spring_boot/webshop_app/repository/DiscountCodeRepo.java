package com.spring_boot.webshop_app.repository;

import com.spring_boot.webshop_app.model.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCodeRepo extends JpaRepository<DiscountCode, Integer> {
    DiscountCode findTopByActiveIsTrue();
    DiscountCode findByCode(String discountCode);
}
