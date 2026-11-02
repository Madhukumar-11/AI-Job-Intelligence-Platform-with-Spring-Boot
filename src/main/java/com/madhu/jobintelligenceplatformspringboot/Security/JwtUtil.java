package com.madhu.jobintelligenceplatformspringboot.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
    secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String username, String role){
        String token = Jwts.builder()
                    .subject(username)
                    .claim("role", role)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + 3600000))
                    .signWith(secretKey)
                    .compact();

        return token;
    }

    // Authentication for checking the JWT token
    private Claims extractAllClaims(String token){
        Claims claims = Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload();
        return claims;
    }
    public boolean validateToken(String token){
        try{
            extractAllClaims(token);
            return true;
        }catch(JwtException | IllegalArgumentException e){
            return false;
        }
    }
    public String extractUsername(String token){
        String username = extractAllClaims(token).getSubject();
        return username;
    }
    public String extractRole(String token){
        String role = extractAllClaims(token).get("role",String.class);
        return role;
    }
}
