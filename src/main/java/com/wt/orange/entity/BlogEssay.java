package com.wt.orange.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;


/**
 * <p> 博客文章实体类 </p>
 *
 * @author Wang Tao
 * @date 2021-01-11 22:41:29
 */
public class BlogEssay {
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
     * 文章概览
     */
    private String summary;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 发布人
     */
    private String publisher;
    /**
     * 是否发布
     * 0：草稿 1：发布
     */
    @TableField(fill= FieldFill.INSERT)
    private Integer published;
    /**
     * 文章类型
     */
    private Integer type;
    /**
     * 阅读数量
     */
    @TableField(fill= FieldFill.INSERT)
    private Integer views;
    /**
     * 评论数量
     */
    @TableField(fill= FieldFill.INSERT)
    private Integer review;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getReview() {
        return review;
    }

    public void setReview(Integer review) {
        this.review = review;
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
