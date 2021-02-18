package com.wt.orange.security.filter;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * <p>权限认证过滤器</p>
 *
 * @author Wang Tao
 * @date 2021-02-06 14:33:20
 */
@Component
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public MyFilterSecurityInterceptor(FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource, AccessDecisionManager accessDecisionManager) {
        this.filterInvocationSecurityMetadataSource = filterInvocationSecurityMetadataSource;
        super.setAccessDecisionManager(accessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.filterInvocationSecurityMetadataSource;
    }
}