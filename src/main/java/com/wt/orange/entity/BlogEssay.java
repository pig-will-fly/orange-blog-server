package com.wt.orange.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (BlogEssay)实体类
 *
 * @author Wang Tao
 * @date 2021-02-01 22:57:44
 */
public class BlogEssay implements Serializable {
    private static final long serialVersionUID = 595490189649334262L;
    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章类型
     */
    private Integer type;
    /**
     * 文章标签
     */
    private String tags;
    /**
     * 文章概览
     */
    private String summary;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 是否发布（0：未发布，1：已发布）
     */
    private Integer published;
    /**
     * 发布者
     */
    private String publisher;
    /**
     * 评论数量
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer views;
    /**
     * 评论数量
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer review;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 数据版本
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
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