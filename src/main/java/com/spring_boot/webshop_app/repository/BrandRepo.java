package com.spring_boot.webshop_app.repository;

import com.spring_boot.webshop_app.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BrandRepo extends JpaRepository<Brand, Integer> {
}
