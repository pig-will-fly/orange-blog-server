/*Table structure for table `blog_comment` */

DROP TABLE IF EXISTS `blog_comment`;

CREATE TABLE `blog_comment`
(
    `id`          bigint(20) NOT NULL COMMENT '主键id',
    `avatar`      varchar(200) DEFAULT NULL COMMENT '用户头像',
    `name`        varchar(100) DEFAULT NULL COMMENT '用户昵称',
    `email`       varchar(100) DEFAULT NULL COMMENT '用户邮箱',
    `content`     varchar(500) DEFAULT NULL COMMENT '博客互动内容',
    `type`        tinyint(4) DEFAULT NULL COMMENT '博客互动类型（1：访客留言，2：文章评论）',
    `essay_id`    bigint(20) DEFAULT NULL COMMENT '文章id',
    `parent_id`   bigint(20) DEFAULT NULL COMMENT '父节点id',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `version`     int(11) DEFAULT NULL COMMENT '数据版本号',
    `flag`        tinyint(4) DEFAULT NULL COMMENT '删除标识（0：正常，1：删除）',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `blog_essay` */

DROP TABLE IF EXISTS `blog_essay`;

CREATE TABLE `blog_essay`
(
    `id`          bigint(20) NOT NULL COMMENT '主键id',
    `title`       varchar(200) DEFAULT NULL COMMENT '文章标题',
    `type`        tinyint(4) DEFAULT NULL COMMENT '文章类型',
    `summary`     varchar(300) DEFAULT NULL COMMENT '文章概览',
    `content`     text COMMENT '文章内容',
    `published`   tinyint(4) DEFAULT NULL COMMENT '是否发布（0：未发布，1：已发布）',
    `publisher`   varchar(100) DEFAULT NULL COMMENT '发布者',
    `review`      int(11) DEFAULT NULL COMMENT '评论数量',
    `views`       int(11) DEFAULT NULL COMMENT '阅读数量',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `version`     int(11) DEFAULT NULL COMMENT '数据版本',
    `flag`        tinyint(4) DEFAULT NULL COMMENT '删除标识（0：正常，1：删除）',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `blog_types` */

DROP TABLE IF EXISTS `blog_types`;

CREATE TABLE `blog_types`
(
    `id`          bigint(20) NOT NULL COMMENT '主键id',
    `type`        tinyint(4) DEFAULT NULL COMMENT '类型',
    `type_name`   varchar(100) DEFAULT NULL COMMENT '类型名称',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `flag`        tinyint(4) DEFAULT NULL COMMENT '删除标识（0：正常，1：删除）',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu`
(
    `id`          bigint(20) NOT NULL COMMENT '菜单ID',
    `parent_id`   bigint(20) DEFAULT NULL COMMENT '父菜单ID',
    `menu_name`   varchar(100) DEFAULT NULL COMMENT '菜单名称',
    `url`         varchar(100) DEFAULT NULL COMMENT '请求地址',
    `method`      varchar(20)  DEFAULT NULL COMMENT '请求方式',
    `permission`  varchar(50)  DEFAULT NULL COMMENT '权限字符串',
    `menu_type`   tinyint(4) DEFAULT NULL COMMENT '菜单类型（0：目录，1：菜单，2：按钮）',
    `order_num`   tinyint(4) DEFAULT NULL COMMENT '显示顺序',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `version`     int(11) DEFAULT NULL COMMENT '版本号',
    `flag`        tinyint(4) DEFAULT NULL COMMENT '删除标志（0：正常，1：删除）',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role`
(
    `id`          bigint(20) NOT NULL COMMENT '角色id',
    `role_name`   varchar(100) DEFAULT NULL COMMENT '角色名称',
    `permission`  varchar(20)  DEFAULT NULL COMMENT '角色权限',
    `effective`   tinyint(4) DEFAULT NULL COMMENT '是否生效（0：有效，1：失效）',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `version`     int(11) DEFAULT NULL COMMENT '版本号',
    `flag`        tinyint(4) DEFAULT NULL COMMENT '删除标识（0：正常，1：删除）',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu`
(
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关联表';

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    `id`          bigint(20) NOT NULL COMMENT '主键id',
    `username`    varchar(18)  DEFAULT NULL COMMENT '用户名',
    `password`    varchar(100) DEFAULT NULL COMMENT '密码',
    `nickname`    varchar(200) DEFAULT NULL COMMENT '用户昵称',
    `avatar`      varchar(100) DEFAULT NULL COMMENT '头像路径',
    `sex`         tinyint(4) DEFAULT NULL COMMENT '性别（0：女，1：男）',
    `tel`         int(11) DEFAULT NULL COMMENT '电话号码',
    `email`       varchar(100) DEFAULT NULL COMMENT '电子邮箱',
    `user_type`   tinyint(4) DEFAULT NULL COMMENT '用户类型（0：系统用户，1：注册用户）',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `version`     int(11) DEFAULT NULL COMMENT '版本号',
    `flag`        tinyint(4) DEFAULT NULL COMMENT '删除标识（0：正常，1：删除）',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role`
(
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `role_id` bigint(20) NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户和角色关联表';