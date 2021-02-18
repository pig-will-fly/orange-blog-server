package com.wt.orange.entity;

import java.io.Serializable;

/**
 * 角色和菜单关联表(RoleMenu)实体类
 *
 * @author Wang Tao
 * @date 2021-02-04 22:03:34
 */
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = 225218225214114201L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

}