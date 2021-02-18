package com.wt.orange.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p> jwt配置类 </p>
 *
 * @author Wang Tao
 * @date 2021-02-06 23:37:11
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    /**
     * header
     */
    private String tokenHeader;
    /**
     * token前缀
     */
    private String tokenPrefix;
    /**
     * 密钥
     */
    private String secret;
    /**
     * 过期时间
     */
    private Long expiration;

    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
}
