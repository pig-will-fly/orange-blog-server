package com.wt.orange.constant;

/**
 * <p> 常量类 </p>
 *
 * @author Wang Tao
 * @date 2021-01-15 17:47:54
 */
public class Constant {
    /**
     * <p> 结果代码枚举 </p>
     *
     * @author Wang Tao
     * @date 2021-01-16 15:01:52
     */
    public enum ResultEnum {
        /**
         * 成功
         */
        SUCCESS(0, "成功！"),
        /**
         * 失败
         */
        ERROR(-1, "失败！"),
        /**
         * 业务异常
         */
        BUSINESS_ERROR(-2, "业务异常"),
        /**
         * 未知异常
         */
        UNKNOWN_ERROR(-3, "未知异常"),
        /**
         * 参数异常
         */
        PARAM_ERROR(-4, "参数不正确！"),
        /**
         * 权限不足
         */
        ACCESS_DENIED(-5, "权限不足！"),
        /**
         * token过期
         */
        TOKEN_ERROR(-6, "token已过期，请重新登录！");

        private Integer code;

        private String value;

        ResultEnum(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * <p>博客文章相关枚举</p>
     *
     * @author Wang Tao
     * @date 2021-01-15 18:02:56
     */
    public enum BlogEssayEnum {
        /**
         * 所有类型
         */
        BLOG_TYPE_ALL(0, "所有类型"),
        /**
         * 诗语
         */
        BLOG_TYPE_POETRY(1, "诗语"),
        /**
         * 词话
         */
        BLOG_TYPE_POEM(2, "词话"),
        /**
         * 随笔
         */
        BLOG_TYPE_ESSAY(3, "随笔"),
        /**
         * 是否发布-已发布
         */
        BLOG_PUBLISH_Y(1, "已发布"),
        /**
         * 是否发布-未发布
         */
        BLOG_PUBLISH_N(0, "未发布");

        private Integer code;

        private String value;

        BlogEssayEnum(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * <p>博客留言评论相关枚举</p>
     *
     * @author Wang Tao
     * @date 2021-01-15 18:03:52
     */
    public enum BlogCommentEnum {
        /**
         * 访客留言
         */
        BLOG_COMMENT_LEAVE_MSG(1, "访客留言"),
        /**
         * 文章评论
         */
        BLOG_COMMENT_REMARK(2, "文章评论");

        private Integer code;

        private String value;

        BlogCommentEnum(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * <p>公共状态枚举</p>
     *
     * @author Wang Tao
     * @date 2021-02-02 19:41:28
     */
    public enum BlogCommonEnum {
        /**
         * 删除标识-正常
         */
        BLOG_COMMON_FLAG_Y(0, "正常"),
        /**
         * 删除标识-已删除
         */
        BLOG_COMMON_FLAG_N(1, "已删除");

        private Integer code;

        private String value;

        BlogCommonEnum(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }
    }
}
