package com.wt.orange.entity;

import com.wt.orange.constant.Constant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * <p> 登录用户信息 </p>
 *
 * @author Wang Tao
 * @date 2021-02-04 20:26:43
 */
public class AuthUser extends User implements UserDetails {
    /**
     * 用户权限
     */
    private transient Set<GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Constant.BlogCommonEnum.BLOG_COMMON_FLAG_Y.getCode().equals(super.getFlag());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

}
