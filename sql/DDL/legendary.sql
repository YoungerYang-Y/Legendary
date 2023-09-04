SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
	`id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
	`pid` int NULL DEFAULT NULL COMMENT '父级权限id',
	`name` varchar(100) CHARACTER SET UTF8MB4 NULL DEFAULT NULL COMMENT '名称',
	`value` varchar(200) CHARACTER SET UTF8MB4 NULL DEFAULT NULL COMMENT '权限值',
	`icon` varchar(500) CHARACTER SET UTF8MB4 NULL DEFAULT NULL COMMENT '图标',
	`type` int NULL DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
	`uri` varchar(200) CHARACTER SET UTF8MB4 NULL DEFAULT NULL COMMENT '前端资源路径',
	`sort` int NULL DEFAULT NULL COMMENT '排序',
	`status` int NULL DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
	`version` int NULL DEFAULT 1 COMMENT '版本号',
	`create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
	`update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = UTF8MB4 ROW_FORMAT = DYNAMIC COMMENT '系统权限表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
	`id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
	`name` varchar(64) CHARACTER SET UTF8MB4 NULL DEFAULT NULL COMMENT '角色名称',
	`status` int NULL DEFAULT NULL COMMENT '状态：0->禁用；1->启用',
	`version` int NULL DEFAULT 1 COMMENT '版本号',
	`create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
	`update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
	`mean` varchar(100) CHARACTER SET UTF8MB4 NULL DEFAULT NULL COMMENT '角色含义',
	PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = UTF8MB4 ROW_FORMAT = DYNAMIC COMMENT '角色表';

-- ----------------------------
-- Table structure for sys_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_relation`;

CREATE TABLE `sys_role_permission_relation` (
	`id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
	`role_id` int NULL DEFAULT NULL COMMENT '角色id',
	`permission_id` int NULL DEFAULT NULL COMMENT '权限id',
	PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = UTF8MB4 ROW_FORMAT = DYNAMIC COMMENT '角色权限关系表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
	`id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
	`username` varchar(64) CHARACTER SET UTF8MB4 NULL DEFAULT NULL COMMENT '用户名',
	`password` varchar(64) CHARACTER SET UTF8MB4 NULL DEFAULT NULL COMMENT '密码',
	`nickname` varchar(64) CHARACTER SET UTF8MB4 NULL DEFAULT NULL COMMENT '昵称',
	`phone` varchar(64) CHARACTER SET UTF8MB4 NULL DEFAULT NULL COMMENT '手机号码',
	`email` varchar(100) CHARACTER SET UTF8MB4 NULL DEFAULT NULL COMMENT '邮箱',
	`gender` int NULL DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
	`birthday` date NULL DEFAULT NULL COMMENT '生日',
	`city` varchar(64) CHARACTER SET UTF8MB4 NULL DEFAULT NULL COMMENT '所在城市',
	`icon` varchar(500) CHARACTER SET UTF8MB4 NULL DEFAULT NULL COMMENT '头像',
	`login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
	`version` int NULL DEFAULT 1 COMMENT '版本号',
	`status` int NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
	`create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
	`update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
	PRIMARY KEY USING BTREE (`id`)
	UNIQUE KEY `sys_user_UN` (`username`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = UTF8MB4 ROW_FORMAT = DYNAMIC COMMENT '用户表';

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;

CREATE TABLE `sys_user_info` (
	`id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_id` int NULL DEFAULT NULL COMMENT '用户id',
	`login_frequency` int NULL DEFAULT 0 COMMENT '登录次数',
	PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = UTF8MB4 ROW_FORMAT = DYNAMIC COMMENT '用户信息表（user扩展表）';

-- ----------------------------
-- Table structure for sys_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_relation`;

CREATE TABLE `sys_user_role_relation` (
	`id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_id` int NULL DEFAULT NULL COMMENT '用户id',
	`role_id` int NULL DEFAULT NULL COMMENT '角色id',
	PRIMARY KEY USING BTREE (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = UTF8MB4 ROW_FORMAT = DYNAMIC COMMENT '用户角色关系表';
SET FOREIGN_KEY_CHECKS = 1;