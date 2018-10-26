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

 Date: 26/10/2018 23:03:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_private_category
-- ----------------------------
DROP TABLE IF EXISTS `t_private_category`;
CREATE TABLE `t_private_category`  (
  `userid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `category` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`userid`, `category`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '博客个人分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_private_category
-- ----------------------------
INSERT INTO `t_private_category` VALUES ('xiaojian', 'true');
INSERT INTO `t_private_category` VALUES ('xiaojian', '程序');

SET FOREIGN_KEY_CHECKS = 1;
