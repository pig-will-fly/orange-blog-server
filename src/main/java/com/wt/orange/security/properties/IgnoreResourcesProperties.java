package com.wt.orange.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p> 免登录资源 </p>
 *
 * @author Wang Tao
 * @date 2021-02-03 18:26:53
 */
@Component
@ConfigurationProperties(prefix = "blog.ignored")
public class IgnoreResourcesProperties {
    /**
     * 免登录资源
     */
    private String[] resources;

    public String[] getResources() {
        return resources;
    }

    public void setResources(String[] resources) {
        this.resources = resources;
    }
}
