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

 Date: 15/03/2021 21:59:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Records of commit_homework
-- ----------------------------
INSERT INTO `commit_homework` VALUES (100000, 100011, 'sqlserver/100011/00芒果小洛_大作业2.bash_history', '2021-03-15 21:59:19');

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (100000, 'java');
INSERT INTO `course` VALUES (100001, 'php');
INSERT INTO `course` VALUES (100002, 'HTML');
INSERT INTO `course` VALUES (100003, '微机原理');
INSERT INTO `course` VALUES (100004, 'sqlserver');

-- ----------------------------
-- Records of format
-- ----------------------------
INSERT INTO `format` VALUES (0, '学号后两位', 1, '63');
INSERT INTO `format` VALUES (1, '学号', 0, '1142100063');
INSERT INTO `format` VALUES (2, '姓名', 1, '小明');
INSERT INTO `format` VALUES (3, '_', 1, '_');
INSERT INTO `format` VALUES (4, '作业名', 1, '实验报告一');

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES (100000, '实验报告一', 100000, '2021-03-17', '2021-03-15', '实验报告一', '实验报告一', '0234');
INSERT INTO `homework` VALUES (100001, '实验报告2', 100000, '2021-03-25', '2021-03-15', '实验报告2', '实验报告2', '0234');
INSERT INTO `homework` VALUES (100002, '登陆页面', 100001, '2021-03-18', '2021-03-15', '登陆页面', '登陆页面', '0234');
INSERT INTO `homework` VALUES (100003, '大作业', 100004, '2021-03-19', '2021-03-15', '大作业', '大作业', '0234');
INSERT INTO `homework` VALUES (100004, '导航栏', 100002, '2021-03-20', '2021-03-15', '导航栏', '导航栏', '0234');
INSERT INTO `homework` VALUES (100005, '课后作业1', 100003, '2021-03-23', '2021-03-15', '课后作业1', '课后作业1', '0234');
INSERT INTO `homework` VALUES (100006, '实验报告3', 100000, '2021-03-17', '2021-03-15', '实验报告一', '实验报告一', '0234');
INSERT INTO `homework` VALUES (100007, '实验报告4', 100000, '2021-03-25', '2021-03-15', '实验报告2', '实验报告2', '0234');
INSERT INTO `homework` VALUES (100008, '登陆页面2', 100001, '2021-03-18', '2021-03-15', '登陆页面', '登陆页面', '0234');
INSERT INTO `homework` VALUES (100009, '导航栏2', 100002, '2021-03-20', '2021-03-15', '导航栏', '导航栏', '0234');
INSERT INTO `homework` VALUES (100010, '课后作业2', 100003, '2021-03-23', '2021-03-15', '课后作业1', '课后作业1', '0234');
INSERT INTO `homework` VALUES (100011, '大作业2', 100004, '2021-03-19', '2021-03-15', '大作业', '大作业', '0234');

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (100000, '11111', '11111', '芒果小洛', '1');
INSERT INTO `user` VALUES (100001, '22222', '22222', 'mmmmm', '0');
INSERT INTO `user` VALUES (100002, '33333', '33333', 'zsl', '1');

SET FOREIGN_KEY_CHECKS = 1;
