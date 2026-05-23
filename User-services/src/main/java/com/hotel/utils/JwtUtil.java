package com.hotel.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

public class JwtUtil {
    private static final String SECRET = "my_super_secret_key_for_jwt_256_bits_long";
    private static final SecretKey KEY;

    public JwtUtil() {
    }

    public static String generateToken(String userId) {
        return Jwts.builder().subject(userId).signWith(KEY, SignatureAlgorithm.HS256).compact();
    }

    public static Claims decodeToken(String token) {
        return (Claims)Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token).getPayload();
    }

    public static String getUserId(String token) {
        Claims claims = decodeToken(token);
        return claims.getSubject();
    }

    static {
        KEY = Keys.hmacShaKeyFor("my_super_secret_key_for_jwt_256_bits_long".getBytes(StandardCharsets.UTF_8));
    }
}
