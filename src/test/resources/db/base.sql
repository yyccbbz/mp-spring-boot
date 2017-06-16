

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `login_name` varchar(30) NOT NULL COMMENT '登录名称',
  `user_name` varchar(30) NOT NULL COMMENT '用户姓名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(60) NULL DEFAULT NULL COMMENT '邮箱',
  `role_type` int(6) NOT NULL DEFAULT '0' COMMENT '0、普通用户 1、管理员 2、超级管理员',
  `status` int(6) NOT NULL DEFAULT '0' COMMENT '0、正常 1、禁用',
  `create_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `last_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后登录时间' ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='用户表';



DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `name` varchar(30) NOT NULL COMMENT '角色',
  `sort` int(6) NOT NULL DEFAULT '0' COMMENT '排序',
  `description` varchar(60) NOT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';


DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id`  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键' ,
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户角色表';

