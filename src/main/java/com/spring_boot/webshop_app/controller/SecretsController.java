package com.spring_boot.webshop_app.controller;

import com.azure.security.keyvault.secrets.SecretClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secrets")
public class SecretsController {

    private final SecretClient secretClient;
    private final String sasTokenName;

    @Autowired
    public SecretsController(SecretClient secretClient, @Value("${azure.keyvault.sas-token-name}") String sasTokenName) {
        this.secretClient = secretClient;
        this.sasTokenName = sasTokenName;
    }

    @GetMapping("/sas-token")
    public ResponseEntity<String> getSasToken() {
        String sasToken = secretClient.getSecret(sasTokenName).getValue();
        return ResponseEntity.ok(sasToken);
    }
}