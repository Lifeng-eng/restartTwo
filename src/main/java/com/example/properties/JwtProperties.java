package com.example.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt") // 关键：将 yaml 中 jwt 前缀的属性映射到此类
public class JwtProperties {
    private String tokenSignKey;
    private Long expiration;
}