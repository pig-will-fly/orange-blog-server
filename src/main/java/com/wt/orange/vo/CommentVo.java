package com.wt.orange.vo;

/**
 * <p> 留言评论参数 </p>
 *
 * @author Wang Tao
 * @date 2021-01-16 23:19:11
 */
public class CommentVo {
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
}
