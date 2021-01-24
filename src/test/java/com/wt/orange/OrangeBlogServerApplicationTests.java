package com.wt.orange;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wt.orange.entity.BlogComment;
import com.wt.orange.service.BlogCommentService;
import com.wt.orange.service.BlogEssayService;
import com.wt.orange.vo.CommentVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class OrangeBlogServerApplicationTests {
    @Autowired
    private BlogEssayService blogEssayService;
    @Autowired
    private BlogCommentService blogCommentService;

    @Test
    void getEssayListPage() {
        //参数一是当前页，参数二是每页条数
        Page<Map<String, Object>> page = new Page<>(1L, 2L);
        page = blogEssayService.getEssayListPage(page, 1);
    }

    @Test
    void getCommentListPage() {
        //参数一是当前页，参数二是每页条数
        Page<Map<String, Object>> page = new Page<>(1L, 2L);
        page = blogCommentService.getCommentListPage(page, 1, null);
    }

    @Test
    void getBlogOverview() {
        System.out.println(this.blogEssayService.getBlogEssayNum());
        System.out.println(this.blogEssayService.getBlogEssayViewsNum());
    }

    @Test
    void getNewestBlogEssay() {
        System.out.println(this.blogEssayService.getNewestBlogEssay(6, null));
    }

    @Test
    void test() {
        CommentVo vo = new CommentVo();
        vo.setName("test");
        vo.setContent("111");
        this.blogCommentService.saveComment(vo);
    }

}
