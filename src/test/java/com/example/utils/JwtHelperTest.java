package com.example.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;

class JwtHelperTest {

    @Test
    void shouldCreateToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1L);
        claims.put("username", "admin");

        String token = JwtHelper.createToken(claims);

        assertFalse(token == null || token.isBlank());
    }
}
