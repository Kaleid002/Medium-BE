package com.example.test_backend.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private static final int exp = 60 * 60 * 1000;//1 hour
    private final SecretKey secretKey;

    public JwtUtil() {
        this.secretKey = Jwts.SIG.HS256.key().build();
    }

    private SecretKey getKey() {
        return secretKey;
    }

    public String generateAccessToken(String userid) {
        return Jwts.builder()
                .subject(userid)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + exp*24))
                .signWith(getKey())
                .compact();
    }

    public String generateRefreshToken(String userid) {
        return Jwts.builder()
                .subject(userid)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (long) exp * 24 * 30))
                .signWith(getKey())
                .compact();
    }

    public Claims getGoogleIdFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
