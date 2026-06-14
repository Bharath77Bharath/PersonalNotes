package com.bharath.PersonalNotes.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET = "mySuperSecretKeyForJwtAuthentication123456789";
    private final Key SecretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));


    public String generateToken(String username) {

        long EXPIRATION = 1000 * 60 * 15;
        return Jwts
                .builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SecretKey)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) SecretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {

        return extractAllClaims(token).getSubject();

    }

    public boolean validateToken(String token) {
        try {
            extractUsername(token);
            return true;
        } catch (JwtException e) {
            e.printStackTrace();
            return false;
        }
    }
}
