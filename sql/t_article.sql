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

 Date: 30/10/2018 19:39:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '文章分类(0-原创,1-转载,2-翻译)',
  `userid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `article_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `article_label` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章标签（json格式存储）',
  `article_category` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '博客分类',
  `article_private_category` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个人分类',
  `last_modified` datetime(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `article_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '0已发布，1私密，2草稿，3回收站',
  `comment_count` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES (3, '0', 'xiaojian', '我的文章1', '[\"test\"]', '程序人生', '[\"程序\"]', '2018-10-19 07:20:12', '2018-10-19 07:20:12', '我的文章是发送到发送到', 0, NULL);
INSERT INTO `t_article` VALUES (4, '0', 'xiaojian', '我的文章2', '[\"test\"]', '程序人生', '[\"程序\"]', '2018-10-19 07:48:40', '2018-10-19 07:48:40', 'property 对应的是实体类中的属性字段 2、实际条件查询案例 /** * 根据名称...1. 继承通用的Mapper,必须指定泛型 例如下面的例子: public interface UserInfoMap', 0, NULL);
INSERT INTO `t_article` VALUES (5, '0', 'xiaojian', '我的文章3', '[\"test\"]', '前端', '[\"程序\"]', '2018-10-25 14:00:27', '2018-10-25 14:00:27', '测试文字水电费水电费', 0, 0);
INSERT INTO `t_article` VALUES (6, '0', 'xiaojian', '我的文章5', '[\"test\"]', '运维', '[\"true\"]', '2018-10-25 14:09:57', '2018-10-25 14:09:57', '测试文字是否水电费水电费', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
