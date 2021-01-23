package com.wt.orange.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p> mybatis-plus MetaObjectHandler配置 </p>
 *
 * @author Wang Tao
 * @date 2021-01-13 23:19:07
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class)
                // 更新时间
                .strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class)
                // 数据版本号
                .strictInsertFill(metaObject, "version", () -> 0, Integer.class)
                // 阅读数量
                .strictInsertFill(metaObject, "views", () -> 0, Integer.class)
                // 评论数量
                .strictInsertFill(metaObject, "review", () -> 0, Integer.class)
                // 是否发布
                .strictInsertFill(metaObject, "published", () -> 0, Integer.class)
                // 删除标志
                .strictInsertFill(metaObject, "flag", () -> 0, Integer.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
    }
}