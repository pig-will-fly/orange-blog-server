package com.wt.orange.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wt.orange.entity.BlogEssay;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p> 博客文章service接口 </p>
 *
 * @author Wang Tao
 * @date 2021-01-13 20:27:18
 */
@Service
public interface BlogEssayService {
    /**
     * <p>分页查询文章列表</p>
     *
     * @param page 分页参数
     * @param type 文章类型
     * @return Page
     * @date 2021-01-14 01:08:22
     * @author Wang Tao
     */
    Page<Map<String, Object>> getEssayListPage(Page<Map<String, Object>> page, Integer type);

    /**
     * <p>查询文章详情</p>
     *
     * @param id 文章id
     * @return Map<String, Object>
     * @date 2021-01-14 20:14:20
     * @author Wang Tao
     */
    Map<String, Object> getEssayDetail(Long id);

    /**
     * <p>查询发布文章总数</p>
     *
     * @return Integer
     * @author Wang Tao
     * @date 2021-01-16 00:18:30
     */
    Integer getBlogEssayNum();

    /**
     * <p>查询文章阅读量</p>
     *
     * @return Integer
     * @author Wang Tao
     * @date 2021-01-16 00:18:30
     */
    Integer getBlogEssayViewsNum();

    /**
     * <p>查询最新博客文章</p>
     *
     * @param limit 查询条数
     * @param type  文章类型
     * @return List
     * @author Wang Tao
     * @date 2021-01-16 09:34:36
     */
    List<Map<String, Object>> getNewestBlogEssay(Integer limit, Integer type);

    /**
     * <p>查询最热博客文章</p>
     *
     * @param limit 查询条数
     * @param type  文章类型
     * @return List
     * @author Wang Tao
     * @date 2021-01-16 15:41:52
     */
    List<Map<String, Object>> getHottestBlogEssay(Integer limit, Integer type);

    /**
     * <p>查询博客文章标题云</p>
     *
     * @return List
     * @author Wang Tao
     * @date 2021-01-16 15:58:54
     */
    List<Map<String, Object>> getEssayTitleCloud();

    /**
     * <p>更新文章浏览次数</p>
     * @param id 文章id
     * @return void
     * @throws
     * @author Wang Tao
     * @date 2021-01-18 00:31:49
     */
    void updateEssayView(Long id);

    /**
     * <p>获取文章管理列表</p>
     *
     * @param page 分页信息
     * @return Page
     * @author Wang Tao
     * @date 2021-02-18 17:19:06
     */
    Page<Map<String, Object>> getEssayManageListPage(Page<Map<String, Object>> page);

    /**
     * <p>保存文章信息</p>
     *
     * @param blogEssay 文章实体类
     * @return void
     * @author Wang Tao
     * @date 2021-02-18 17:22:24
     */
    void saveBlogEssay(BlogEssay blogEssay);

    /**
     * <p>根据id获取待编辑文章详情</p>
     *
     * @param id 文章id
     * @return Map
     * @author Wang Tao
     * @date 2021-02-18 17:20:54
     */
    Map<String, Object> getEssayDetailForEdit(Long id);

    /**
     * <p>文章发布</p>
     *
     * @param essayList 待发布文章实体
     * @return void
     * @author Wang Tao
     * @date 2021-02-18 17:23:23
     */
    void publishBlogEssay(List<BlogEssay> essayList);

    /**
     * <p>删除文章</p>
     *
     * @param ids 待删除文章id
     * @return void
     * @author Wang Tao
     * @date 2021-02-18 17:24:44
     */
    void deleteBlogEssay(List<Long> ids);
}
