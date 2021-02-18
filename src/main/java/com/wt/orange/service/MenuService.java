package com.wt.orange.service;

import com.wt.orange.entity.Menu;

import java.util.List;

/**
 * <p> 用户菜单相关service接口 </p>
 *
 * @author Wang Tao
 * @date 2021-02-06 17:44:19
 */
public interface MenuService {
    /**
     * <p>获取所有菜单列表</p>
     *
     * @return Menu
     * @author Wang Tao
     * @date 2021-02-06 17:53:32
     */
    List<Menu> getRequireAuthorityMenuList();

    /**
     * <p>获取用户权限菜单</p>
     *
     * @return Menu 用户权限菜单
     * @author Wang Tao
     * @date 2021-02-08 22:51:55
     */
    List<Menu> getHasAuthMenuList();
}
