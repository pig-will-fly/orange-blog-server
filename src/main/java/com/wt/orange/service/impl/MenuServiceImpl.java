package com.wt.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wt.orange.entity.Menu;
import com.wt.orange.mapper.MenuMapper;
import com.wt.orange.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p> 用户菜单相关service实现 </p>
 *
 * @author Wang Tao
 * @date 2021-02-06 17:45:36
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * <p>获取所有菜单列表</p>
     *
     * @return Menu>
     * @author Wang Tao
     * @date 2021-02-06 17:53:32
     */
    @Override
    public List<Menu> getRequireAuthorityMenuList() {
        QueryWrapper<Menu> query = new QueryWrapper();
        query.select("url,permission,method").isNotNull("permission");
        return this.menuMapper.selectList(query);
    }

    /**
     * <p>获取用户权限菜单</p>
     *
     * @return Menu
     * @author Wang Tao
     * @date 2021-02-08 22:54:12
     */
    @Override
    public List<Menu> getHasAuthMenuList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return null;
    }

}
