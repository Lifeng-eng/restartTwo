package com.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 * 功能：生成 Token（包含 id 和 username）、解析 Token
 */
public class JwtHelper {

    // 1. 设置 Token 的过期时间 (单位：毫秒)
    // 这里设置为 24 小时
    private static final long tokenExpiration = 24 * 60 * 60 * 1000;

    // 2. 设置签名密钥 (用于加密和解密，生产环境建议使用更复杂的字符串)
    private static final String tokenSignKey = "my_secret_key_123456";

    /**
     * 生成 Token
     * @param claims 用户名
     * @return 返回加密后的 Token 字符串
     */
    public static String createToken(Map<String, String> claims) {
        return Jwts.builder()
                // 设置分类
                .setSubject("AUTH-USER")
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                // --- 核心部分：设置自定义载荷 (Payload) ---
                .claim("id", claims.get("id"))           // 存储 ID
                .claim("username", claims.get("username")) // 存储用户名
                // ---------------------------------------
                // 设置签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                // 使用 GZIP 压缩，减少 Token 长度
                .compressWith(CompressionCodecs.GZIP)
                // 生成 Token
                .compact();
    }

    /**
     * 解析 Token 获取用户 ID
     * @param token 客户端传来的 Token
     * @return 用户 ID，如果解析失败返回 null
     */
    public static Long getId(String token) {
        try {
            if (!StringUtils.hasText(token)) {
                return null;
            }
            
            // 解析 Token
            Claims claims = Jwts.parser()
                    .setSigningKey(tokenSignKey) // 必须使用相同的密钥
                    .parseClaimsJws(token)
                    .getBody();
            
            // 从 claims 中取出 id
            // 注意：根据存入时的类型，这里可能需要转换，jjwt 默认数字可能是 Integer
            Object idObj = claims.get("id");
            if (idObj instanceof Integer) {
                return ((Integer) idObj).longValue();
            }
            return (Long) idObj;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析 Token 获取用户名
     * @param token 客户端传来的 Token
     * @return 用户名，如果解析失败返回 null
     */
    public static String getUsername(String token) {
        try {
            if (!StringUtils.hasText(token)) {
                return null;
            }
            
            Claims claims = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody();
            
            return (String) claims.get("username");
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}