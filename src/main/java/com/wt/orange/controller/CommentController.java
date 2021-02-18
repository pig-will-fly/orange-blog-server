package com.wt.orange.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wt.orange.constant.Constant;
import com.wt.orange.service.BlogCommentService;
import com.wt.orange.vo.CommentVo;
import com.wt.orange.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> 博客评论留言相关 </p>
 *
 * @author Wang Tao
 * @date 2021-01-15 15:35:59
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private BlogCommentService blogCommentService;

    /**
     * <p></p>
     *
     * @param currentPage 当前页码
     * @param limit       分页大小
     * @param essayId     文章id
     * @param type        评论留言类型
     * @return ResponseResult
     * @date 2021-01-15 18:23:06
     * @author Wang Tao
     */
    @GetMapping("/list")
    public ResponseResult<Map<String, Object>> getCommentListPage(
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "essayId", required = false) Long essayId,
            @RequestParam(value = "type") Integer type) {

        if (Constant.BlogCommentEnum.BLOG_COMMENT_REMARK.getCode().equals(type)
                && essayId == null) {
            return ResponseResult.error(Constant.ResultEnum.PARAM_ERROR);
        }

        Page<Map<String, Object>> page = new Page<>(currentPage, limit);
        page = this.blogCommentService.getCommentListPage(page, type, essayId);

        Map<String, Object> data = new HashMap<>();
        data.put("records", page.getRecords());
        data.put("total", page.getTotal());

        return ResponseResult.success(data);
    }

    /**
     * <p>保存评论留言信息</p>
     *
     * @param commentVo 评论留言参数
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-01-17 16:37:57
     */
    @PostMapping("/save")
    public ResponseResult<Map<String, Object>> saveComment(@RequestBody CommentVo commentVo) {
        this.blogCommentService.saveComment(commentVo);
        return ResponseResult.success();
    }
}
