package com.madhu.jobintelligenceplatformspringboot.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    private final SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

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
}
