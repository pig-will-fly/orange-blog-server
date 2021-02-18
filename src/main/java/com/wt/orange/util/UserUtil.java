package com.wt.orange.util;

import com.wt.orange.entity.AuthUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p> 用户信息工具类 </p>
 *
 * @author Wang Tao
 * @date 2021-02-17 21:22:41
 */
public class UserUtil {
    /**
     * <p>获取认证用户信息</p>
     *
     * @return AuthUser 已认证用户
     * @author Wang Tao
     * @date 2021-02-18 16:46:03
     */
    public static AuthUser getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (AuthUser) authentication.getPrincipal();
    }
}
