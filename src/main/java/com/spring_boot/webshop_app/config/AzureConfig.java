package com.spring_boot.webshop_app.config;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureConfig {

    @Value("${azure.keyvault.uri}")
    private String keyVaultUri;

    @Bean
    public SecretClient secretClient() {
        return new SecretClientBuilder()
                .vaultUrl(keyVaultUri)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }

    @Bean
    public String jwtSecret(SecretClient secretClient, @Value("${azure.keyvault.jwt-secret-name}") String jwtSecretName) {
        return secretClient.getSecret(jwtSecretName).getValue();
    }

    @Bean
    public String sasToken(SecretClient secretClient, @Value("${azure.keyvault.sas-token-name}") String sasTokenName) {
        return secretClient.getSecret(sasTokenName).getValue();
    }
}