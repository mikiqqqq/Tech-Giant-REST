package com.spring_boot.webshop_app.controller;

import com.azure.security.keyvault.secrets.SecretClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/secrets")
public class SecretsController {

    private final String sasTokenName;

    @Autowired
    public SecretsController(SecretClient secretClient) {
        this.sasTokenName = secretClient.getSecret("sas-token").getValue();
    }

    @GetMapping("/sas-token")
    public ResponseEntity<String> getSasToken() {
        return ResponseEntity.ok(sasTokenName);
    }
}