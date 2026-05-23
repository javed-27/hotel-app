package com.hotel.app.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class JwtUtil {
    private static final String SECRET = "my_super_secret_key_for_jwt_256_bits_long";

    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(String userId) {
        return Jwts.builder().subject(userId).signWith(KEY, SignatureAlgorithm.HS256).compact();
    }

    public static Claims decodeToken(String token) {
        return Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token).getPayload();
    }

    public static String getUserId(String token) {
        Claims claims = decodeToken(token);
        return (claims.getSubject());
    }
}