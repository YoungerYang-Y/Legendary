/*
 Navicat Premium Data Transfer

 Source Server         : LocalMySQL
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : legendary

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 25/02/2021 10:05:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` int(20) NULL DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `type` int(1) NULL DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端资源路径',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  `status` int(1) NULL DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `version` int(10) NULL DEFAULT 1 COMMENT '版本号',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, 'change测试', NULL, NULL, 0, NULL, 1, 1, 11, '2021-02-20 08:59:45', '2021-02-24 20:47:28');
INSERT INTO `sys_permission` VALUES (2, 0, 'XX功能', NULL, NULL, 0, NULL, 2, 1, 1, '2021-02-20 08:59:48', NULL);
INSERT INTO `sys_permission` VALUES (3, 1, '菜单管理', 'sys:menu:read', NULL, 1, '/sys/menu/index', 1, 1, 1, '2021-02-20 09:01:25', NULL);
INSERT INTO `sys_permission` VALUES (4, 1, '权限管理', 'sys:permission:read', NULL, 1, '/sys/permission/index', 2, 1, 1, '2021-02-20 09:02:08', NULL);
INSERT INTO `sys_permission` VALUES (6, 1, '用户管理', 'sys:user:read', NULL, 1, '/sys/user/index', 3, 1, 1, '2021-02-20 09:03:49', NULL);
INSERT INTO `sys_permission` VALUES (7, 1, '角色管理', 'sys:role:read', NULL, 1, '/sys/role/index', 4, 1, 1, '2021-02-20 09:04:16', NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0->禁用；1->启用',
  `version` int(10) NULL DEFAULT 1 COMMENT '版本号',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 1, 1, '2021-02-20 09:10:03', NULL);
INSERT INTO `sys_role` VALUES (2, '系统管理员', 1, 1, '2021-02-20 09:10:35', NULL);
INSERT INTO `sys_role` VALUES (3, '普通用户', 1, 1, '2021-02-20 09:17:37', NULL);

-- ----------------------------
-- Table structure for sys_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_relation`;
CREATE TABLE `sys_role_permission_relation`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(20) NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` int(20) NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission_relation
-- ----------------------------
INSERT INTO `sys_role_permission_relation` VALUES (1, 1, 1);
INSERT INTO `sys_role_permission_relation` VALUES (2, 1, 3);
INSERT INTO `sys_role_permission_relation` VALUES (3, 1, 4);
INSERT INTO `sys_role_permission_relation` VALUES (4, 1, 6);
INSERT INTO `sys_role_permission_relation` VALUES (5, 1, 7);
INSERT INTO `sys_role_permission_relation` VALUES (6, 1, 2);
INSERT INTO `sys_role_permission_relation` VALUES (7, 2, 1);
INSERT INTO `sys_role_permission_relation` VALUES (8, 2, 3);
INSERT INTO `sys_role_permission_relation` VALUES (9, 2, 4);
INSERT INTO `sys_role_permission_relation` VALUES (10, 2, 6);
INSERT INTO `sys_role_permission_relation` VALUES (11, 2, 7);
INSERT INTO `sys_role_permission_relation` VALUES (12, 3, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `phone` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `gender` int(1) NULL DEFAULT NULL COMMENT '性别：0->未知；1->男；2->女',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `city` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在城市',
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `version` int(10) NULL DEFAULT 1 COMMENT '版本号',
  `status` int(1) NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
  `gmt_create` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'yy', 'yy', '超级管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 0, '2021-02-20 09:13:32', NULL);
INSERT INTO `sys_user` VALUES (2, 'admin', 'admin', '系统管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2021-02-20 09:15:07', NULL);
INSERT INTO `sys_user` VALUES (3, 'user1', 'user1', '普通用户', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, '2021-02-20 09:18:34', NULL);

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(20) NULL DEFAULT NULL COMMENT '用户id',
  `login_frequency` int(20) NULL DEFAULT 0 COMMENT '登录次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表（user扩展表）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_relation`;
CREATE TABLE `sys_user_role_relation`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(20) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int(20) NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role_relation
-- ----------------------------
INSERT INTO `sys_user_role_relation` VALUES (1, 1, 1);
INSERT INTO `sys_user_role_relation` VALUES (2, 2, 2);
INSERT INTO `sys_user_role_relation` VALUES (3, 3, 3);

SET FOREIGN_KEY_CHECKS = 1;
