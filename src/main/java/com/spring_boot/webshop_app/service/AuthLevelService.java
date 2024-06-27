package com.spring_boot.webshop_app.service;

import com.spring_boot.webshop_app.model.AuthorizationLevel;

public interface AuthLevelService {
    AuthorizationLevel findById(int id);
    int findIdByTitle(String title);
}
