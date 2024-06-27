package com.spring_boot.webshop_app.controller;

import com.spring_boot.webshop_app.service.AuthLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/auth-level")
public class AuthLevelController {

    private final AuthLevelService authLevelService;

    @Autowired
    public AuthLevelController(AuthLevelService authLevelService) {
        this.authLevelService = authLevelService;
    }

    @GetMapping(value = "/id/{title}")
    ResponseEntity<Integer> findIdByTitle(@PathVariable String title) {

        return ResponseEntity
                .ok()
                .body(authLevelService.findIdByTitle(title));
    }
}
