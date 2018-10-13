/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 13/10/2018 17:30:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog_category
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_category`;
CREATE TABLE `t_blog_category`  (
  `id` tinyint(4) NOT NULL,
  `category` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog_category
-- ----------------------------
INSERT INTO `t_blog_category` VALUES (1, '人工智能');
INSERT INTO `t_blog_category` VALUES (2, '移动开发');
INSERT INTO `t_blog_category` VALUES (3, '物联网');
INSERT INTO `t_blog_category` VALUES (4, '架构');
INSERT INTO `t_blog_category` VALUES (5, '云计算/大数据');
INSERT INTO `t_blog_category` VALUES (6, '游戏开发');
INSERT INTO `t_blog_category` VALUES (7, '运维');
INSERT INTO `t_blog_category` VALUES (8, '数据库');
INSERT INTO `t_blog_category` VALUES (9, '前端');
INSERT INTO `t_blog_category` VALUES (10, '后端');
INSERT INTO `t_blog_category` VALUES (11, '编程语言');
INSERT INTO `t_blog_category` VALUES (12, '研发管理');
INSERT INTO `t_blog_category` VALUES (13, '安全');
INSERT INTO `t_blog_category` VALUES (14, '程序人生');
INSERT INTO `t_blog_category` VALUES (15, '区块链');
INSERT INTO `t_blog_category` VALUES (16, '音视频开发');
INSERT INTO `t_blog_category` VALUES (17, '咨询');
INSERT INTO `t_blog_category` VALUES (18, '计算机理论与基础');

SET FOREIGN_KEY_CHECKS = 1;
