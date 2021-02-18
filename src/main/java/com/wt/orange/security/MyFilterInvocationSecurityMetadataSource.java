package com.wt.orange.security;

import com.wt.orange.entity.Menu;
import com.wt.orange.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>元数据加载器</p>
 *
 * @author Wang Tao
 * @date 2021-02-06 14:46:02
 */
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<Url, Collection<ConfigAttribute>> requiredAuthoritys = null;

    @Autowired
    private MenuService menuService;

    private void loadRequiredAuthoritys() {
        requiredAuthoritys = new ConcurrentHashMap<>();
        // 获取需要权限的的菜单
        List<Menu> menuList = this.menuService.getRequireAuthorityMenuList();
        menuList.forEach(item -> {
            Url url = new Url(item.getUrl(), item.getMethod());
            List<ConfigAttribute> attributes = (List<ConfigAttribute>) requiredAuthoritys.get(url);
            if (attributes == null)
                attributes = new ArrayList<>();
            attributes.add(new SecurityConfig(item.getPermission()));
            requiredAuthoritys.put(url, attributes);
        });
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (requiredAuthoritys == null) {
            loadRequiredAuthoritys();
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        // 获取当前请求所需要权限
        for (Url url : requiredAuthoritys.keySet()) {
            if (new AntPathRequestMatcher(url.getUrl(), url.getMethod().toUpperCase()).matches(request)) {
                return requiredAuthoritys.get(url);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * <p>构建url对象</p>
     *
     * @author Wang Tao
     * @date 2021-02-06 18:30:49
     */
    static class Url {
        private String url;
        private String method;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public Url(String url, String method) {
            this.url = url;
            this.method = method;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Url that = (Url) o;
            return Objects.equals(url, that.url) && Objects.equals(method, that.method);
        }

        @Override
        public int hashCode() {
            return Objects.hash(url, method);
        }
    }
}