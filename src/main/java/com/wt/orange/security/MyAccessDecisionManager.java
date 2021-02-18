package com.wt.orange.security;

import com.wt.orange.constant.Constant;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * <p>决策管理</p>
 *
 * @author Wang Tao
 * @date 2021-02-06 14:35:59
 */
@Component("myAccessDecisionManager")
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication auth, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 无权限要求资源无需鉴权
        if (configAttributes == null || configAttributes.size() <= 0) {
            return;
        }
        // 鉴权处理
        for (ConfigAttribute configAttribute : configAttributes) {
            String requiredAuthority = configAttribute.getAttribute();
            for (GrantedAuthority grantedAuthority : auth.getAuthorities()) {
                // 所需权限与用户拥有权限比较
                if (requiredAuthority.trim().equals(grantedAuthority.getAuthority().trim())) {
                    return;
                }
            }
        }
        // 资源访问权限不足
        throw new AccessDeniedException(Constant.ResultEnum.ACCESS_DENIED.getValue());
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}