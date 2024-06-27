package com.spring_boot.webshop_app.repository;

import com.spring_boot.webshop_app.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepo extends JpaRepository<ProductType, Integer> {
}
