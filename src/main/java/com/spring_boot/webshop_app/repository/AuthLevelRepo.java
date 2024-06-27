package com.spring_boot.webshop_app.repository;

import com.spring_boot.webshop_app.model.AuthorizationLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthLevelRepo extends JpaRepository<AuthorizationLevel, Integer> {
    AuthorizationLevel findById(int id);
    AuthorizationLevel findByTitle(String title);
}
