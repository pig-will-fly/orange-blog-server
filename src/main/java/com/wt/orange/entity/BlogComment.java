package com.wt.orange.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (BlogComment)实体类
 *
 * @author Wang Tao
 * @date 2021-02-01 22:57:44
 */
public class BlogComment implements Serializable {
    private static final long serialVersionUID = -61054254309483932L;
    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 博客互动内容
     */
    private String content;
    /**
     * 博客互动类型（1：访客留言，2：文章评论）
     */
    private Integer type;
    /**
     * 文章id
     */
    private Long essayId;
    /**
     * 父节点id
     */
    private Long parentId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 数据版本号
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
    /**
     * 删除标识（0：正常，1：删除）
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer flag;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getEssayId() {
        return essayId;
    }

    public void setEssayId(Long essayId) {
        this.essayId = essayId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

}