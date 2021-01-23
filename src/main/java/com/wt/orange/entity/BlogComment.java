package com.wt.orange.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;


/**
 * <p> 博客互动内容实体类 </p>
 *
 * @author Wang Tao
 * @date 2021-01-11 22:41:29
 */
public class BlogComment {
    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 互动内容
     */
    private String content;
    /**
     * 类型
     * 0：访客留言 1：文章评论
     */
    private Integer type;
    /**
     * 文章id
     */
    private Long essayId;
    /**
     * 上级id
     */
    private Long parentId;
    /**
     * 数据版本
     */
    @Version
    @TableField(fill= FieldFill.INSERT)
    private Integer version;
    /**
     * 创建时间
     */
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 删除标识
     * 0：正常 1：删除
     */
    @TableLogic
    @TableField(fill= FieldFill.INSERT)
    private Integer flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
