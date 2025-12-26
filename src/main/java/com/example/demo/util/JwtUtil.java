package com.example.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Set;

@Component
public class JwtUtil {

    private static final long EXPIRATION_MS = 1000 * 60 * 60; // 1 hour

    // Fixed secret so tests remain stable
    private final Key key = Keys.hmacShaKeyFor(
            "THIS_IS_A_VERY_SECRET_KEY_FOR_JWT_TESTING_123456".getBytes()
    );

    public String generateToken(String email, Long userId, Set<String> roles) {
    return Jwts.builder()
        .setSubject(email)                     // OK to keep
        .claim("email", email)                 // 🔴 REQUIRED
        .claim("userId", userId)               // 🔴 REQUIRED
        .claim("roles", roles)                 // 🔴 REQUIRED
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
}

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

}
