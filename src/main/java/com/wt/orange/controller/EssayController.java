package com.wt.orange.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wt.orange.service.BlogCommentService;
import com.wt.orange.service.BlogEssayService;
import com.wt.orange.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 文章内容相关 </p>
 *
 * @author Wang Tao
 * @date 2021-01-12 14:44:35
 */
@RestController
@RequestMapping("/api/essay")
public class EssayController {

    @Autowired
    private BlogEssayService blogEssayService;
    @Autowired
    private BlogCommentService blogCommentService;

    /**
     * <p>分页查询文章列表</p>
     *
     * @param currentPage 当前页码
     * @param limit       分页大小
     * @param type        文章类型
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-01-14 01:05:50
     */
    @GetMapping("/list")
    public ResponseResult<Map<String, Object>> getEssayListPage(
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
            @RequestParam(value = "type", required = false) Integer type) {

        Page<Map<String, Object>> page = new Page<>(currentPage, limit);
        page = blogEssayService.getEssayListPage(page, type);

        Map<String, Object> data = new HashMap<>();
        data.put("records", page.getRecords());
        data.put("total", page.getTotal());

        return ResponseResult.success(data);
    }

    /**
     * <p>查询文章详情</p>
     *
     * @param id 文章id
     * @return ResponseResult
     * @date 2021-01-14 20:13:09
     * @author Wang Tao
     */
    @GetMapping("/detail")
    public ResponseResult<Map<String, Object>> getEssayDetail(@RequestParam(value = "id") Long id) {

        Map<String, Object> data = blogEssayService.getEssayDetail(id);
        return ResponseResult.success(data);
    }

    /**
     * <p>查询博客概览（发布文章数量、文章阅读量、文章评论量）</p>
     *
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-01-16 00:58:16
     */
    @GetMapping("/overview")
    public ResponseResult<Map<String, Integer>> getBlogOverview() {

        // 发布文章数
        Integer essayNum = blogEssayService.getBlogEssayNum();
        // 文章阅读量
        Integer viewsNum = blogEssayService.getBlogEssayViewsNum();
        // 文章评论量
        Integer commentNum = blogCommentService.getCommentNum();

        Map<String, Integer> data = new HashMap<>();
        data.put("essayNum", essayNum);
        data.put("viewsNum", viewsNum);
        data.put("commentNum", commentNum);

        return ResponseResult.success(data);
    }

    /**
     * <p>查询最新文章信息</p>
     *
     * @param limit 查询条数
     * @param type  文章类型
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-01-16 10:21:26
     */
    @GetMapping("/newest")
    public ResponseResult<List<Map<String, Object>>> getNewestBlogEssay(
            @RequestParam(value = "limit", required = false, defaultValue = "6") Integer limit,
            @RequestParam(value = "type", required = false) Integer type) {

        List<Map<String, Object>> data = blogEssayService.getNewestBlogEssay(limit, type);
        return ResponseResult.success(data);
    }

    /**
     * <p>查询最热文章信息</p>
     *
     * @param limit 查询条数
     * @param type  文章类型
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-01-16 15:40:42
     */
    @GetMapping("/hottest")
    public ResponseResult<List<Map<String, Object>>> getHottestBlogEssay(
            @RequestParam(value = "limit", required = false, defaultValue = "6") Integer limit,
            @RequestParam(value = "type", required = false) Integer type) {

        List<Map<String, Object>> data = blogEssayService.getHottestBlogEssay(limit, type);
        return ResponseResult.success(data);
    }

    /**
     * <p>查询博客文章标题云</p>
     *
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-01-16 16:05:06
     */
    @GetMapping("/cloud")
    public ResponseResult<List<Map<String, Object>>> getEssayTitleCloud() {

        List<Map<String, Object>> data = blogEssayService.getEssayTitleCloud();
        return ResponseResult.success(data);
    }

    /**
     * <p>更新文章浏览次数</p>
     *
     * @param param 更新条件
     * @return void
     * @throws
     * @author Wang Tao
     * @date 2021-01-18 00:30:17
     */
    @PostMapping("/view")
    public void updateEssayView(@RequestBody Map<String, Long> param) {

        this.blogEssayService.updateEssayView(param.get("id"));
    }
}
