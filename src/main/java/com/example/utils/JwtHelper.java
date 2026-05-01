package com.example.utils;

import com.example.pojo.dto.UserInfo;
import com.example.properties.JwtProperties;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 * 改造重点：去掉 static 关键字，改用 Spring Bean 模式，配合配置类使用
 */
@Slf4j
@Component // 注入到 Spring 容器
public class JwtHelper {

    private final JwtProperties jwtProperties;

    // 通过构造器注入配置
    public JwtHelper(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 生成 Token
     */
    public String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject("AUTH-USER")
                // 从配置类获取过期时间
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .setClaims(claims)
                // 从配置类获取密钥
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getTokenSignKey())
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    /**
     * 解析 Token 并获取用户信息对象
     */
    public UserInfo parseToken(String token) {
        if (!StringUtils.hasText(token)) {
            return null;
        }

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getTokenSignKey())
                    .parseClaimsJws(token)
                    .getBody();

            UserInfo userInfo = new UserInfo();
            // 使用类型转换工具或手动处理数字转换
            Object idObj = claims.get("userId"); // 建议 Claim 的 key 与 Pojo 字段名一致
            if (idObj != null) {
                userInfo.setId(Long.valueOf(idObj.toString()));
            }
            userInfo.setUsername((String) claims.get("username"));

            return userInfo;

        } catch (ExpiredJwtException e) {
            log.error("Token 已过期: {}", e.getMessage());
        } catch (SignatureException e) {
            log.error("Token 签名非法: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Token 解析异常: {}", e.getMessage());
        }
        return null;
    }
}