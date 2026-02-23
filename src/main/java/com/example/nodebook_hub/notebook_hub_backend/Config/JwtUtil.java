package com.example.nodebook_hub.notebook_hub_backend.Config;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import io.jsonwebtoken.*;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("prasannaUnique03prasannaUnique03".getBytes()); // At least 32 bytes key
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public String generateToken(String email,Boolean isAdmin) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", isAdmin ? "ADMIN" : "USER");  // Store role in JWT
        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        ZonedDateTime expirationTime = currentTime.plusHours(1);

        System.out.println("System Time (IST): " + currentTime);
        System.out.println("Token Expiry (IST): " + expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(currentTime.toInstant()))
                .setExpiration(Date.from(expirationTime.toInstant()))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);

            return !isTokenExpired(token);

        } catch (ExpiredJwtException e) {
            System.out.println("🚫 Token has expired!");
        } catch (SecurityException | MalformedJwtException e) {
            System.out.println("🚫 Invalid token signature or structure.");
        } catch (Exception e) {
            System.out.println("🚫 Token validation error.");
        }
        return false;
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String extractRole(String token) {
        return extractAllClaims(token).get("role", String.class);  // Extract role from token
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
