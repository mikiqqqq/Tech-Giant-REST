package com.spring_boot.webshop_app.util;

import com.spring_boot.webshop_app.model.User;
import com.spring_boot.webshop_app.service.AuthLevelService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.azure.security.keyvault.secrets.SecretClient;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final AuthLevelService authLevelService;
    private final SecretClient secretClient;
    private String secret;

    @Autowired
    public JwtUtil(AuthLevelService authLevelService, SecretClient secretClient) {
        this.authLevelService = authLevelService;
        this.secretClient = secretClient;
    }

    @PostConstruct
    public void init() {
        // Fetch the secret value from Azure Key Vault once during initialization
        this.secret = secretClient.getSecret("jwt-secret").getValue();
        System.out.println("JWT Secret fetched from Key Vault: " + this.secret);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        String username = user.getEmail();

        claims.put("name", user.getName());
        claims.put("email", user.getEmail());
        claims.put("role", authLevelService.findById(user.getAuthLevelId()).getTitle());

        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiration
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}