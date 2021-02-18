package com.wt.orange.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wt.orange.entity.BlogEssay;
import com.wt.orange.entity.Menu;
import com.wt.orange.service.BlogEssayService;
import com.wt.orange.service.MenuService;
import com.wt.orange.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 博客管理 </p>
 *
 * @author Wang Tao
 * @date 2021-02-07 22:12:17
 */
@RequestMapping("/api/manage")
@RestController
public class BlogManagementController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private BlogEssayService blogEssayService;

    /**
     * <p>获取用户权限菜单</p>
     *
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-02-08 22:50:54
     */
    @GetMapping("/menu/list")
    public ResponseResult getMenuList() {
        List<Menu> data = this.menuService.getHasAuthMenuList();
        return ResponseResult.success("博客管理菜单");
    }

    /**
     * <p>获取文章管理列表</p>
     *
     * @param currentPage 当前页
     * @param limit       每页显示数据量
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-02-18 17:19:06
     */
    @GetMapping("/essay/list")
    public ResponseResult<Map<String, Object>> getEssayManageListPage(
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
            @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {

        Page<Map<String, Object>> page = new Page<>(currentPage, limit);
        page = blogEssayService.getEssayManageListPage(page);

        Map<String, Object> data = new HashMap<>();
        data.put("records", page.getRecords());
        data.put("total", page.getTotal());

        return ResponseResult.success(data);
    }

    /**
     * <p>根据id获取待编辑文章详情</p>
     *
     * @param id 文章id
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-02-18 17:20:54
     */
    @PostMapping("/essay/edit")
    public ResponseResult<Map<String, Object>> getEssayDetailForEdit(
            @RequestParam(value = "id") Long id) {

        return ResponseResult.success(blogEssayService.getEssayDetailForEdit(id));
    }

    /**
     * <p>保存文章信息</p>
     *
     * @param blogEssay 文章实体类
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-02-18 17:22:24
     */
    @PostMapping("/essay/save")
    public ResponseResult<Map<String, Object>> saveBlogEssay(@RequestBody BlogEssay blogEssay) {

        blogEssayService.saveBlogEssay(blogEssay);

        return ResponseResult.success();
    }

    /**
     * <p>文章发布</p>
     *
     * @param essayList 待发布文章实体
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-02-18 17:23:23
     */
    @PutMapping("/essay/publish")
    public ResponseResult<Map<String, Object>> publishBlogEssay(@RequestBody List<BlogEssay> essayList) {

        blogEssayService.publishBlogEssay(essayList);

        return ResponseResult.success();
    }

    /**
     * <p>删除文章</p>
     *
     * @param ids 待删除文章id
     * @return ResponseResult
     * @author Wang Tao
     * @date 2021-02-18 17:24:44
     */
    @DeleteMapping("/essay/delete")
    public ResponseResult<Map<String, Object>> deleteBlogEssay(@RequestBody List<Long> ids) {

        blogEssayService.deleteBlogEssay(ids);

        return ResponseResult.success();
    }
}
