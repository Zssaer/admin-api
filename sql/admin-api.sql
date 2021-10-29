/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : admin-api

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 29/10/2021 17:49:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `login_name` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码盐值',
  `role_id` int(0) NOT NULL DEFAULT 1 COMMENT '角色ID',
  `pic` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像',
  `register_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  `admin_status` int(0) NOT NULL DEFAULT 1 COMMENT '管理员状态',
  `create_by` int(0) NOT NULL COMMENT '创建者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1007 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1001, 'admin', '25eb5178b483d1ff3955dc34e7c149377474583e70d56d2f', '5588135374778752', 1, NULL, '2021-10-25 11:44:13', 1, 0);

-- ----------------------------
-- Table structure for admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `admin_permission`;
CREATE TABLE `admin_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pid` int(0) NULL DEFAULT NULL COMMENT '父ID',
  `name` varchar(70) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限名称',
  `type` int(0) NOT NULL COMMENT '权限类型(1:菜单权限,2功能权限)',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限路径/权限值',
  `method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '功能权限-请求类型',
  `icon` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '菜单权限-菜单图标',
  `sort` int(0) NULL DEFAULT NULL COMMENT '菜单权限-排名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1024 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_permission
-- ----------------------------
INSERT INTO `admin_permission` VALUES (101, NULL, '用户管理', 1, 'userMag', NULL, 'el-icon-user-solid', 50);
INSERT INTO `admin_permission` VALUES (102, 101, '管理员管理', 1, 'admin', NULL, NULL, 103);
INSERT INTO `admin_permission` VALUES (103, 101, '角色管理', 1, 'role', NULL, NULL, 102);
INSERT INTO `admin_permission` VALUES (104, 101, '日志管理', 1, 'sysLog', NULL, NULL, 101);
INSERT INTO `admin_permission` VALUES (105, NULL, '配置管理', 1, 'configMag', NULL, 'el-icon-s-tools', 49);
INSERT INTO `admin_permission` VALUES (106, 105, '系统配置', 1, 'sys', NULL, NULL, 202);
INSERT INTO `admin_permission` VALUES (107, 105, '常用配置', 1, 'common', NULL, NULL, 203);
INSERT INTO `admin_permission` VALUES (1000, 102, '管理员管理-查询', 2, 'admin', 'get', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1001, 102, '管理员管理-新增', 2, 'admin', 'post', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1002, 102, '管理员管理-修改', 2, 'admin', 'put', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1003, 102, '管理员管理-删除', 2, 'admin', 'delete', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1004, 103, '角色管理-查询', 2, 'role', 'get', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1005, 103, '角色管理-新增', 2, 'role', 'post', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1006, 103, '角色管理-修改', 2, 'role', 'put', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1007, 103, '角色管理-删除', 2, 'role', 'delete', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1008, 104, '日志管理-查询', 2, 'sysLog', 'get', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1009, 104, '日志管理-新增', 2, 'sysLog', 'post', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1010, 104, '日志管理-修改', 2, 'sysLog', 'put', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1011, 104, '日志管理-删除', 2, 'sysLog', 'delete', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1012, 106, '系统配置-查询', 2, 'sys', 'get', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1013, 106, '系统配置-新增', 2, 'sys', 'post', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1014, 106, '系统配置-修改', 2, 'sys', 'put', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1015, 106, '系统配置-删除', 2, 'sys', 'delete', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1020, 107, '常用配置-查询', 2, 'common', 'get', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1021, 107, '常用配置-新增', 2, 'common', 'post', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1022, 107, '常用配置-修改', 2, 'common', 'put', NULL, NULL);
INSERT INTO `admin_permission` VALUES (1023, 107, '常用配置-删除', 2, 'common', 'delete', NULL, NULL);

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  `is_sys` int(0) NOT NULL DEFAULT 0 COMMENT '是否系统内置',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES (1, '超级管理员', 1, '2021-08-27 00:00:00');

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `group_id` int(0) NOT NULL COMMENT '配置组id',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '配置Key',
  `config_value` varchar(400) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '配置Value',
  `is_sys` int(0) NOT NULL DEFAULT 0 COMMENT '是否系统内置配置',
  `status` int(0) NULL DEFAULT NULL COMMENT '配置状态(0:关闭,1:开启)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config
-- ----------------------------
INSERT INTO `config` VALUES (1, 1, 'Img_Save_Path', 'images/', 1, 1);
INSERT INTO `config` VALUES (2, 1, 'File_Save_Path', 'files/', 1, 1);
INSERT INTO `config` VALUES (3, 1, 'Local_Dir', 'E:/storage/', 1, 1);
INSERT INTO `config` VALUES (4, 1, 'Web_Resoureces_Path', 'http://192.168.0.190:8085/resources/', 1, 1);
INSERT INTO `config` VALUES (5, 3, 'Is_Redis_Cache', '0', 1, 1);
INSERT INTO `config` VALUES (6, 2, '新闻', 'new', 1, 1);
INSERT INTO `config` VALUES (7, 2, '通告', 'notice', 0, 1);
INSERT INTO `config` VALUES (11, 1, 'Server_Port', '99958', 1, 1);

-- ----------------------------
-- Table structure for config_group
-- ----------------------------
DROP TABLE IF EXISTS `config_group`;
CREATE TABLE `config_group`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '配置组名',
  `group_code` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '配置组代号',
  `status` int(0) NOT NULL COMMENT '状态(0:关闭,1:开启)',
  `group_type` int(0) NOT NULL COMMENT '配置类型(1:系统配置,2:常用配置)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '配置组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_group
-- ----------------------------
INSERT INTO `config_group` VALUES (1, '存储路径', 'savePath', 1, 1);
INSERT INTO `config_group` VALUES (2, '文章类型', 'articleType', 1, 2);
INSERT INTO `config_group` VALUES (3, '缓存服务器', 'cacheServer', 1, 1);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_id` int(0) NOT NULL COMMENT '角色ID',
  `permission_id` int(0) NOT NULL COMMENT '权限ID',
  `is_use` int(0) NOT NULL DEFAULT 1 COMMENT '可否使用权限 0:不能 1可以',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 108 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (101, 1, 101, 1);
INSERT INTO `role_permission` VALUES (102, 1, 102, 1);
INSERT INTO `role_permission` VALUES (103, 1, 103, 1);
INSERT INTO `role_permission` VALUES (104, 1, 104, 1);
INSERT INTO `role_permission` VALUES (105, 1, 105, 1);
INSERT INTO `role_permission` VALUES (106, 1, 106, 1);
INSERT INTO `role_permission` VALUES (107, 1, 107, 1);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NULL DEFAULT NULL COMMENT '操作管理员ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '操作管理员名称',
  `operation_name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '操作名称',
  `operation_param` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '操作参数',
  `user_ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '操作管理员IP地址',
  `log_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '日志时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 123 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '任务名称',
  `task_job_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '任务jobkey',
  `task_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '任务描述',
  `task_cron` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '任务cron表达式',
  `task_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '任务传递数据',
  `task_class` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '任务执行类',
  `status` int(0) NULL DEFAULT NULL COMMENT '任务运行状态(0:关闭,1:执行)',
  `create_time` timestamp(0) NOT NULL COMMENT '创建时间',
  `createdBy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建任务的管理员名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '任务管理类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of task
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
