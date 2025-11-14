package com.zzzlew.zzzimserver.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: zzzlew
 * @Date: 2025/11/7 - 11 - 07 - 0:10
 * @Description: com.zzzlew.zzzimserver.properties
 * @version: 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class Jwtproperties {
    private String secretKey;
    private Long expiration;
    private String tokenName;
}
