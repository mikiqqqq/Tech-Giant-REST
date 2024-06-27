package com.spring_boot.webshop_app.repository;

import com.spring_boot.webshop_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
