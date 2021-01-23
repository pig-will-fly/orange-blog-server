package com.wt.orange.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wt.orange.vo.CommentVo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p> 博客评论留言相关 </p>
 *
 * @author Wang Tao
 * @date 2021-01-15 15:38:50
 */
@Service
public interface BlogCommentService {
    /**
     * <p>分页查询评论（留言）列表</p>
     *
     * @param page 分页参数
     * @param essayId 文章id
     * @param type 评论留言类型
     * @return Page<Map < String, Object>>
     * @author Wang Tao
     * @date 2021-01-15 16:35:09
     */
    Page<Map<String, Object>> getCommentListPage(Page<Map<String, Object>> page, Integer type, Long essayId);

    /**
     * <p>查询文章评论量</p>
     *
     * @return Integer
     * @author Wang Tao
     * @date 2021-01-16 00:35:24
     */
    Integer getCommentNum();

    /**
     * <p>保存评论留言</p>
     * @param commentVo 评论留言参数
     * @return void
     * @author Wang Tao
     * @date 2021-01-16 23:39:04
     */
    void saveComment(CommentVo commentVo);
}
