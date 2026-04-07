package com.example.utils;

import com.example.pojo.dto.UserInfo;
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
    private static final String tokenSignKey = "my_super_secret_key_for_jwt_hs256_that_is_32_chars!!";

    /**
     * 生成 Token
     * @param claims 用户名
     * @return 返回加密后的 Token 字符串
     */
    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                // 设置分类
                .setSubject("AUTH-USER")
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                // --- 核心部分：设置自定义载荷 (Payload) ---
                .setClaims(claims) // 存储用户名
                // ---------------------------------------
                // 设置签名算法和密钥
                .signWith(SignatureAlgorithm.HS256, tokenSignKey)
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

    /**
     * 【核心改造】解析 Token 并获取用户信息对象
     * 如果 Token 无效或过期，直接返回 null
     */
    public static UserInfo parseToken(String token) {
        try {
            if (!StringUtils.hasText(token)) {
                return null;
            }

            // 1. 解析 Token
            Claims claims = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody();

            // 2. 将 Claims 中的数据提取到 UserInfo 对象中
            UserInfo userInfo = new UserInfo();

            // 处理 ID (兼容 Integer 和 Long)
            Object idObj = claims.get("id");
            if (idObj instanceof Integer) {
                userInfo.setId(((Integer) idObj).longValue());
            } else if (idObj instanceof Long) {
                userInfo.setId((Long) idObj);
            }

            // 处理 Username
            userInfo.setUsername((String) claims.get("username"));

            return userInfo;

        } catch (Exception e) {
            // 解析失败（签名错误、过期等）
            e.printStackTrace();
            return null;
        }
    }

}