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

 Date: 13/10/2018 17:30:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `usernm` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `signature` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `image_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像路径',
  `level` tinyint(4) NULL DEFAULT NULL COMMENT '用户等级',
  `page_view` bigint(20) NULL DEFAULT NULL COMMENT '访问量',
  `score` int(11) NULL DEFAULT NULL COMMENT '积分',
  `rank` int(11) NULL DEFAULT NULL COMMENT '排名',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户基本信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('Jann', '70A85FC539772539B87E02197AC6535B5127C07AB6F47147D5647C65170B1425', 'Jann', 'I am me!', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_user` VALUES ('xiaojian', 'DC26FE875BA2C58D87998933E53D4D520487E198F1217E8B610C55E01DE8DFE6', 'xiaojian', '我就是我，不一样的烟火。。。', NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
