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

 Date: 28/10/2018 21:46:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_article_stat
-- ----------------------------
DROP TABLE IF EXISTS `t_article_stat`;
CREATE TABLE `t_article_stat`  (
  `article_id` bigint(20) NOT NULL,
  `read_count` bigint(20) NOT NULL,
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章阅读量统计' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_article_stat
-- ----------------------------
INSERT INTO `t_article_stat` VALUES (3, 6);
INSERT INTO `t_article_stat` VALUES (4, 1);
INSERT INTO `t_article_stat` VALUES (5, 12);
INSERT INTO `t_article_stat` VALUES (6, 10);

SET FOREIGN_KEY_CHECKS = 1;
