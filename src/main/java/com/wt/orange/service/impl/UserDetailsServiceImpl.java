package com.wt.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wt.orange.entity.AuthUser;
import com.wt.orange.entity.User;
import com.wt.orange.entity.UserPermission;
import com.wt.orange.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p> 用户相关操作 </p>
 *
 * @author Wang Tao
 * @date 2021-01-31 19:44:40
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    /**
     * <p>用户认证自定义逻辑</p>
     *
     * @param name 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException
     * @author Wang Tao
     * @date 2021-02-02 19:31:51
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        // 获取用户信息
        User user = this.getUserInfoByUserName(name);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        // 获取用户权限
        List<UserPermission> userPermissionList = this.getUserPermissionByUserId(user.getId());
        // 组装用户及其权限信息
        AuthUser authUser = new AuthUser();
        BeanUtils.copyProperties(user, authUser);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (UserPermission userPermission : userPermissionList) {
            if (userPermission.getRolePermission() != null)
                grantedAuthorities.add(new SimpleGrantedAuthority(userPermission.getRolePermission()));
            if (userPermission.getMenuPermission() != null)
                grantedAuthorities.add(new SimpleGrantedAuthority(userPermission.getMenuPermission()));
        }
        authUser.setAuthorities(grantedAuthorities);

        return authUser;
    }


    /**
     * <p>根据用户名查询用户信息</p>
     *
     * @param username 用户名
     * @return User
     * @author Wang Tao
     * @date 2021-02-02 19:52:33
     */
    public User getUserInfoByUserName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return this.userMapper.selectOne(wrapper);
    }

    /**
     * <p>根据用户id获取用户权限列表</p>
     *
     * @param userId 用户id
     * @return List 用户权限列表
     * @author Wang Tao
     * @date 2021-02-06 16:53:36
     */
    public List<UserPermission> getUserPermissionByUserId(Long userId) {
        return this.userMapper.getUserPermissionByUserId(userId);
    }
}
