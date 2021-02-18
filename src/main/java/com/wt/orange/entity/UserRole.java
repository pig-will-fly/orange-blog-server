package com.wt.orange.entity;

import java.io.Serializable;

/**
 * 用户和角色关联表(UserRole)实体类
 *
 * @author Wang Tao
 * @date 2021-02-04 22:03:34
 */
public class UserRole implements Serializable {
    private static final long serialVersionUID = -76359072561565085L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}