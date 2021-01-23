package com.wt.orange.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p> 博客文章类型实体类 </p>
 *
 * @author Wang Tao
 * @date 2021-01-11 22:41:29
 */
public class BlogTypes {
    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 文章类型
     */
    private Integer type;
    /**
     * 类型名称
     */
    private String typeName;
    /**
     * 数据版本
     */
    @Version
    @TableField(fill= FieldFill.INSERT)
    private Integer version;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
