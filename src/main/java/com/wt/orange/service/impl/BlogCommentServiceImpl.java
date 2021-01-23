package com.wt.orange.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wt.orange.constant.Constant;
import com.wt.orange.entity.BlogComment;
import com.wt.orange.entity.BlogEssay;
import com.wt.orange.exception.BusinessException;
import com.wt.orange.mapper.BlogCommentMapper;
import com.wt.orange.mapper.BlogEssayMapper;
import com.wt.orange.service.BlogCommentService;
import com.wt.orange.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p> 博客评论留言相关 </p>
 *
 * @author Wang Tao
 * @date 2021-01-15 15:39:55
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {

    @Autowired
    private BlogCommentMapper blogCommentMapper;
    @Autowired
    private BlogEssayMapper blogEssayMapper;

    /**
     * <p>分页查询评论（留言）列表</p>
     *
     * @param page    分页参数
     * @param essayId 文章id
     * @param type    评论留言类型
     * @return Page<Map < String, Object>>
     * @author Wang Tao
     * @date 2021-01-15 16:35:09
     */
    @Override
    public Page<Map<String, Object>> getCommentListPage(Page<Map<String, Object>> page, Integer type, Long essayId) {
        QueryWrapper<BlogComment> query = new QueryWrapper<>();
        query.select("id", "name", "type", "content", "date_format(update_time,'%Y-%m-%d %H:%i:%s') updateTime", "essay_id essayId", "parent_id parentId")
                .eq("type", type)
                .isNull("parent_id")
                .eq(essayId != null, "essay_id", essayId)
                .orderByDesc("update_time");
        Page<Map<String, Object>> data = this.blogCommentMapper.selectMapsPage(page, query);

        // 获取子节点数据
        List<Map<String, Object>> records = data.getRecords();
        for (Map<String, Object> r : records) {
            r.put("children", getChildCommentList((Long) r.get("id"), (String) r.get("name")));
        }

        return data;
    }

    /**
     * <p>查询文章评论量</p>
     *
     * @return Integer
     * @author Wang Tao
     * @date 2021-01-16 00:35:24
     */
    @Override
    public Integer getCommentNum() {
        QueryWrapper<BlogComment> query = new QueryWrapper<>();
        query.eq("type", Constant.BlogCommentEnum.BLOG_COMMENT_REMARK.getCode());
        return this.blogCommentMapper.selectCount(query);
    }

    /**
     * <p>保存评论留言</p>
     *
     * @param commentVo 评论留言参数
     * @return void
     * @author Wang Tao
     * @date 2021-01-16 23:39:04
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveComment(CommentVo commentVo) {
        // 保存评论留言信息
        BlogComment comment = new BlogComment();
        BeanUtils.copyProperties(commentVo, comment);
        UpdateWrapper<BlogComment> blogCommentUpdateWrapper = new UpdateWrapper<>();
        blogCommentUpdateWrapper.setEntity(comment);
        this.blogCommentMapper.insert(comment);

        // 文章评论同时保存文章评论量
        Long essayId;
        if (Constant.BlogCommentEnum.BLOG_COMMENT_REMARK.getCode().equals(commentVo.getType())
                && (essayId = commentVo.getEssayId()) != null) {
            int i = 0;
            while (true) {
                // 查询记录
                QueryWrapper<BlogEssay> query = new QueryWrapper<>();
                query.select("id,version,review").eq("id", essayId);
                BlogEssay blogEssay = this.blogEssayMapper.selectOne(query);
                // 更新
                blogEssay.setReview(blogEssay.getReview() + 1);
                int rows = this.blogEssayMapper.updateById(blogEssay);
                if (rows > 0)
                    break;
                if (i > 100)
                    throw new BusinessException(Constant.ResultEnum.BUSINESS_ERROR);
                i++;
            }
        }
    }

    /**
     * <p>获取评论留言子节点列表</p>
     *
     * @param parentId 父节点id
     * @return List<Map < String, Object>>
     * @author Wang Tao
     * @date 2021-01-15 17:14:13
     */
    private List<Map<String, Object>> getChildCommentList(Long parentId, String parentName) {
        QueryWrapper<BlogComment> query = new QueryWrapper<>();
        query.select("id", "name", "type", "content", "date_format(update_time,'%Y-%m-%d %H:%i:%s') updateTime", "essay_id essayId", "parent_id parentId")
                .isNotNull("parent_id")
                .eq("parent_id", parentId)
                .orderByDesc("update_time");
        List<Map<String, Object>> list = this.blogCommentMapper.selectMaps(query);
        for (Map<String, Object> r : list) {
            // 递归获取子节点数据
            List<Map<String, Object>> children = getChildCommentList((Long) r.get("id"), (String) r.get("name"));
            r.put("children", children);
            r.put("parentName", "@" + parentName + "：");
            if (children.size() == 0) {
                r.put("children", null);
            }
        }
        return list;
    }
}
