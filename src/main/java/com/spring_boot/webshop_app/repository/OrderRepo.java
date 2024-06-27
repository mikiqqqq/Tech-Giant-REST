package com.spring_boot.webshop_app.repository;

import com.spring_boot.webshop_app.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    List<Order> findOrderByStatusAndEmail(String status, String email);
}
