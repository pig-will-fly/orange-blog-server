package com.wt.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wt.orange.constant.Constant;
import com.wt.orange.entity.BlogEssay;
import com.wt.orange.exception.BusinessException;
import com.wt.orange.mapper.BlogEssayMapper;
import com.wt.orange.service.BlogEssayService;
import com.wt.orange.util.BeanUtil;
import com.wt.orange.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 博客文章service实现 </p>
 *
 * @author Wang Tao
 * @date 2021-01-13 20:28:51
 */
@Service
public class BlogEssayServiceImpl extends ServiceImpl<BlogEssayMapper, BlogEssay> implements BlogEssayService {

    @Autowired
    private BlogEssayMapper blogEssayMapper;


    /**
     * <p>分页查询文章列表</p>
     *
     * @param page 分页参数
     * @param type 文章类型
     * @return Page
     * @author Wang Tao
     * @date 2021-01-14 01:08:22
     */
    @Override
    public Page<Map<String, Object>> getEssayListPage(Page<Map<String, Object>> page, Integer type) {
        QueryWrapper<BlogEssay> query = new QueryWrapper<>();
        query.orderByDesc("create_time")
                .select("id", "title", "type", "summary", "review", "views", "date_format(update_time,'%Y-%m-%d %H:%i:%s') updateTime")
                .eq("published", Constant.BlogEssayEnum.BLOG_PUBLISH_Y.getCode())
                .eq(type != null, "type", type);
        return this.blogEssayMapper.selectMapsPage(page, query);
    }

    /**
     * <p>查询文章详情</p>
     *
     * @param id 文章id
     * @return Map<String, Object>
     * @author Wang Tao
     * @date 2021-01-14 20:15:22
     */
    @Override
    public Map<String, Object> getEssayDetail(Long id) {
        // 当前文章
        QueryWrapper<BlogEssay> currentQuery = new QueryWrapper<>();
        currentQuery.select("id", "title", "type", "publisher", "content", "review", "views", "update_time updateTime")
                .eq("published", Constant.BlogEssayEnum.BLOG_PUBLISH_Y.getCode())
                .eq("id", id);
        BlogEssay currentEssay = this.blogEssayMapper.selectOne(currentQuery);

        // 上一篇
        QueryWrapper<BlogEssay> preQuery = new QueryWrapper<>();
        preQuery.select("id", "title", "type")
                .eq("published", Constant.BlogEssayEnum.BLOG_PUBLISH_Y.getCode())
                .eq("type", currentEssay.getType())
                .lt("id", id)
                .orderByDesc("id")
                .last("limit 1");
        BlogEssay preEssay = this.blogEssayMapper.selectOne(preQuery);

        // 下一篇
        QueryWrapper<BlogEssay> nextQuery = new QueryWrapper<>();
        nextQuery.select("id", "title", "type")
                .eq("published", Constant.BlogEssayEnum.BLOG_PUBLISH_Y.getCode())
                .eq("type", currentEssay.getType())
                .gt("id", id)
                .orderByAsc("id")
                .last("limit 1");
        BlogEssay nextEssay = this.blogEssayMapper.selectOne(nextQuery);

        Map<String, Object> result = new HashMap<>();
        result.put("current", BeanUtil.beanToMap(currentEssay, true));
        result.put("pre", BeanUtil.beanToMap(preEssay, true));
        result.put("next", BeanUtil.beanToMap(nextEssay, true));
        return result;
    }

    /**
     * <p>查询发布文章总数</p>
     *
     * @return Integer
     * @author Wang Tao
     * @date 2021-01-16 00:18:30
     */
    @Override
    public Integer getBlogEssayNum() {
        QueryWrapper<BlogEssay> query = new QueryWrapper<>();
        query.eq("published", Constant.BlogEssayEnum.BLOG_PUBLISH_Y.getCode());
        return this.blogEssayMapper.selectCount(query);
    }

    /**
     * <p>查询文章阅读量</p>
     *
     * @return Integer
     * @author Wang Tao
     * @date 2021-01-16 00:18:30
     */
    @Override
    public Integer getBlogEssayViewsNum() {
        return this.blogEssayMapper.queryViewsNum();
    }

    /**
     * <p>查询最新博客文章</p>
     *
     * @param limit 查询条数
     * @param type  文章类型
     * @return List
     * @author Wang Tao
     * @date 2021-01-16 09:34:36
     */
    @Override
    public List<Map<String, Object>> getNewestBlogEssay(Integer limit, Integer type) {
        QueryWrapper<BlogEssay> query = new QueryWrapper<>();
        query.select("id", "title", "summary", "date_format(update_time,'%Y-%m-%d') updateTime")
                .eq("published", Constant.BlogEssayEnum.BLOG_PUBLISH_Y.getCode())
                .eq(!Constant.BlogEssayEnum.BLOG_TYPE_ALL.getCode().equals(type), "type", type)
                .orderByDesc("update_time")
                .last(limit != null, "limit " + limit);
        return this.blogEssayMapper.selectMaps(query);
    }

    /**
     * <p>查询最热博客文章</p>
     *
     * @param limit 查询条数
     * @param type  文章类型
     * @return List
     * @author Wang Tao
     * @date 2021-01-16 15:41:52
     */
    @Override
    public List<Map<String, Object>> getHottestBlogEssay(Integer limit, Integer type) {
        QueryWrapper<BlogEssay> query = new QueryWrapper<>();
        query.select("id", "title", "summary", "date_format(update_time,'%Y-%m-%d') updateTime")
                .eq("published", Constant.BlogEssayEnum.BLOG_PUBLISH_Y.getCode())
                .eq(!Constant.BlogEssayEnum.BLOG_TYPE_ALL.getCode().equals(type), "type", type)
                .orderByDesc("views")
                .last(limit != null, "limit " + limit);
        return this.blogEssayMapper.selectMaps(query);
    }

    /**
     * <p>查询博客文章标题云</p>
     *
     * @return List
     * @author Wang Tao
     * @date 2021-01-16 15:58:54
     */
    @Override
    public List<Map<String, Object>> getEssayTitleCloud() {
        QueryWrapper<BlogEssay> query = new QueryWrapper<>();
        query.select("id", "title")
                .eq("published", Constant.BlogEssayEnum.BLOG_PUBLISH_Y.getCode())
                .orderByDesc("views")
                .last("limit 50");
        return this.blogEssayMapper.selectMaps(query);
    }

    /**
     * <p>更新文章浏览次数</p>
     *
     * @param id 文章id
     * @return void
     * @author Wang Tao
     * @date 2021-01-18 00:31:49
     */
    @Override
    public void updateEssayView(Long id) {
        // 查询记录
        QueryWrapper<BlogEssay> query = new QueryWrapper<>();
        query.select("id,version,views").eq("id", id);
        BlogEssay blogEssay = this.blogEssayMapper.selectOne(query);
        blogEssay.setViews(blogEssay.getViews() + 1);
        // 更新
        this.blogEssayMapper.updateById(blogEssay);
    }

    /**
     * <p>获取文章管理列表</p>
     *
     * @param page 分页信息
     * @return Page
     * @author Wang Tao
     * @date 2021-02-18 17:19:06
     */
    @Override
    public Page<Map<String, Object>> getEssayManageListPage(Page<Map<String, Object>> page) {
        QueryWrapper<BlogEssay> query = new QueryWrapper<>();
        query.orderByDesc("create_time")
                .select("id", "title", "type", "summary", "tags",
                        "published", "date_format(create_time,'%Y-%m-%d %H:%i:%s') createTime",
                        "date_format(update_time,'%Y-%m-%d %H:%i:%s') updateTime");
        return this.blogEssayMapper.selectMapsPage(page, query);
    }

    /**
     * <p>保存文章信息</p>
     *
     * @param blogEssay 文章实体类
     * @return void
     * @author Wang Tao
     * @date 2021-02-18 17:22:24
     */
    @Override
    public void saveBlogEssay(BlogEssay blogEssay) {
        if (!Constant.BlogEssayEnum.BLOG_PUBLISH_Y.getCode().equals(blogEssay.getPublished()))
            blogEssay.setPublished(Constant.BlogEssayEnum.BLOG_PUBLISH_N.getCode());
        else
            blogEssay.setPublisher(UserUtil.getUserInfo().getNickname());

        this.saveOrUpdate(blogEssay);
    }

    /**
     * <p>根据id获取待编辑文章详情</p>
     *
     * @param id 文章id
     * @return Map
     * @author Wang Tao
     * @date 2021-02-18 17:20:54
     */
    @Override
    public Map<String, Object> getEssayDetailForEdit(Long id) {
        QueryWrapper<BlogEssay> currentQuery = new QueryWrapper<>();
        currentQuery.select("id", "title", "type", "tags", "content", "summary", "published")
                .eq("id", id);
        return BeanUtil.beanToMap(this.blogEssayMapper.selectOne(currentQuery), true);
    }

    /**
     * <p>文章发布</p>
     *
     * @param essayList 待发布文章实体
     * @return void
     * @author Wang Tao
     * @date 2021-02-18 17:23:23
     */
    @Override
    public void publishBlogEssay(List<BlogEssay> essayList) {
        String nickName = UserUtil.getUserInfo().getNickname();
        essayList.forEach(essay -> essay.setPublisher(nickName));

        this.updateBatchById(essayList);
    }

    /**
     * <p>删除文章</p>
     *
     * @param ids 待删除文章id
     * @return void
     * @author Wang Tao
     * @date 2021-02-18 17:24:44
     */
    @Override
    public void deleteBlogEssay(List<Long> ids) {
        if (ids.size() < 1)
            throw new BusinessException(Constant.ResultEnum.PARAM_ERROR);

        this.blogEssayMapper.deleteBatchIds(ids);
    }
}
