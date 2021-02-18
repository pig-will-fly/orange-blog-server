package com.wt.orange.entity;

/**
 * <p> 用户权限信息 </p>
 *
 * @author Wang Tao
 * @date 2021-02-04 23:06:14
 */
public class UserPermission {
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色权限
     */
    private String rolePermission;
    /**
     * 菜单名
     */
    private String menuName;
    /**
     * 菜单路径
     */
    private String url;
    /**
     * 菜单权限
     */
    private String menuPermission;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRolePermission() {
        return rolePermission;
    }

    public void setRolePermission(String rolePermission) {
        this.rolePermission = rolePermission;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMenuPermission() {
        return menuPermission;
    }

    public void setMenuPermission(String menuPermission) {
        this.menuPermission = menuPermission;
    }
}
