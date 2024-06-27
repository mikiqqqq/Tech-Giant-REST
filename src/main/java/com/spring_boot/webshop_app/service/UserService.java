package com.spring_boot.webshop_app.service;

import com.spring_boot.webshop_app.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
    void save(User user);
    UserDetails loadUserByUsername(String email);
}
