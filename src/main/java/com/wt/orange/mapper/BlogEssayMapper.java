package com.wt.orange.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wt.orange.entity.BlogEssay;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


/**
 * <p> 博客文章Mapper </p>
 *
 * @author Wang Tao
 * @date 2021-01-13 00:44:00
 */
@Component
public interface BlogEssayMapper extends BaseMapper<BlogEssay> {

    /**
     * <p>查询文章阅读量</p>
     *
     * @return Integer
     * @author Wang Tao
     * @date 2021-01-16 00:18:30
     */
    @Select("select sum(views) views from blog_essay where flag = 0 and published = 1")
    Integer queryViewsNum();
}
