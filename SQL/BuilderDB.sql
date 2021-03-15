/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : homework_manager

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 11/03/2021 20:29:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for commit_homework
-- ----------------------------
DROP TABLE IF EXISTS `commit_homework`;
CREATE TABLE `commit_homework`  (
  `uid` int(11) NOT NULL COMMENT '完成者id（user.id）',
  `hid` int(11) NOT NULL COMMENT '完成的作业id（homework.id）',
  `filepath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作业文件的链接',
  `datetime` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提交时间',
  PRIMARY KEY (`uid`, `hid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '科目id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '科目名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for format
-- ----------------------------
DROP TABLE IF EXISTS `format`;
CREATE TABLE `format`  (
  `value` int(10) UNSIGNED NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `checked` int(10) UNSIGNED NOT NULL COMMENT '是否默认选中',
  `data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '要展示的数据（格式）',
  PRIMARY KEY (`value`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for homework
-- ----------------------------
DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course` int(11) NOT NULL,
  `lastCommitDate` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createDate` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `briefInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `detailInfo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `format` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '提交的文件名格式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id，唯一标识符，（不用来登陆使用）',
  `username` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名，用于登陆系统的账户（唯一）',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实名',
  `power` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '权限，0为普通用户权限，1为管理员权限',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100000 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- View structure for v_commit_count
-- ----------------------------
DROP VIEW IF EXISTS `v_commit_count`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_commit_count` AS select count(0) AS `count`,`commit_homework`.`hid` AS `hid` from `commit_homework` group by `commit_homework`.`hid`;

SET FOREIGN_KEY_CHECKS = 1;
