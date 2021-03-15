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

 Date: 11/03/2021 20:30:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Records of commit_homework
-- ----------------------------
INSERT INTO `commit_homework` VALUES (100000, 30000, 'path', '2021-03-07 01:22:22');
INSERT INTO `commit_homework` VALUES (100000, 30001, 'path', '2021-03-07 02:22:22');
INSERT INTO `commit_homework` VALUES (100000, 30006, 'path', '2021-03-07 03:22:22');
INSERT INTO `commit_homework` VALUES (100000, 30007, 'path', '2021-03-07 04:22:22');
INSERT INTO `commit_homework` VALUES (100000, 30008, 'path', '2021-03-07 05:22:22');
INSERT INTO `commit_homework` VALUES (100000, 30009, 'path', '2021-03-07 06:22:22');
INSERT INTO `commit_homework` VALUES (100000, 30013, 'path', '2021-03-07 06:22:22');
INSERT INTO `commit_homework` VALUES (100001, 30000, 'path', '2021-03-07 14:22:21');
INSERT INTO `commit_homework` VALUES (100001, 30001, 'path', '2021-03-07 13:22:21');
INSERT INTO `commit_homework` VALUES (100001, 30006, 'path', '2021-03-07 12:22:21');
INSERT INTO `commit_homework` VALUES (100001, 30007, 'path', '2021-03-07 11:22:21');
INSERT INTO `commit_homework` VALUES (100001, 30008, 'path', '2021-03-07 14:22:21');
INSERT INTO `commit_homework` VALUES (100001, 30009, 'path', '2021-03-07 13:22:21');
INSERT INTO `commit_homework` VALUES (100002, 30000, 'path', '2021-03-07 12:22:21');
INSERT INTO `commit_homework` VALUES (100003, 30001, 'path', '2021-03-06 11:22:21');
INSERT INTO `commit_homework` VALUES (100004, 30006, 'path', '2021-03-07 14:22:21');
INSERT INTO `commit_homework` VALUES (100005, 30007, 'path', '2021-03-07 13:22:21');
INSERT INTO `commit_homework` VALUES (100006, 30008, 'path', '2021-03-07 12:22:21');
INSERT INTO `commit_homework` VALUES (100007, 30009, 'path', '2021-03-06 11:22:21');
INSERT INTO `commit_homework` VALUES (100008, 30010, 'path', '2021-03-07 14:22:21');
INSERT INTO `commit_homework` VALUES (100009, 30000, 'path', '2021-03-07 13:22:21');
INSERT INTO `commit_homework` VALUES (100010, 30001, 'path', '2021-03-07 12:22:21');
INSERT INTO `commit_homework` VALUES (100011, 30006, 'path', '2021-03-06 11:22:21');
INSERT INTO `commit_homework` VALUES (100012, 30007, 'path', '2021-03-07 14:22:21');
INSERT INTO `commit_homework` VALUES (100013, 30008, 'path', '2021-03-07 13:22:21');
INSERT INTO `commit_homework` VALUES (100014, 30009, 'path', '2021-03-07 12:22:21');
INSERT INTO `commit_homework` VALUES (100015, 30000, 'path', '2021-03-05 11:22:21');
INSERT INTO `commit_homework` VALUES (100016, 30001, 'path', '2021-03-07 14:22:21');
INSERT INTO `commit_homework` VALUES (100017, 30006, 'path', '2021-03-07 13:22:21');
INSERT INTO `commit_homework` VALUES (100018, 30007, 'path', '2021-03-07 12:22:21');
INSERT INTO `commit_homework` VALUES (100019, 30008, 'path', '2021-03-07 11:22:21');
INSERT INTO `commit_homework` VALUES (100020, 30009, 'path', '2021-03-07 14:22:21');
INSERT INTO `commit_homework` VALUES (100021, 30010, 'path', '2021-03-07 13:22:21');
INSERT INTO `commit_homework` VALUES (100022, 30000, 'path', '2021-03-07 12:22:21');
INSERT INTO `commit_homework` VALUES (100050, 30014, '微机原理/30014/100050.txt', '2021-03-10 13:40:52');
INSERT INTO `commit_homework` VALUES (1932101149, 30014, '微机原理/30014/1932101149.txt', '2021-03-10 13:42:17');
INSERT INTO `commit_homework` VALUES (1932101149, 30017, 'sqlserver/30017/1932101149.txt', '2021-03-10 13:45:23');
INSERT INTO `commit_homework` VALUES (1932101149, 30018, 'java/30018/49_实验报告呀.txt', '2021-03-10 13:52:02');

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (20000, 'java');
INSERT INTO `course` VALUES (20001, 'php');
INSERT INTO `course` VALUES (20002, 'HTML');
INSERT INTO `course` VALUES (20003, '微机原理');
INSERT INTO `course` VALUES (20004, 'sqlserver');

-- ----------------------------
-- Records of format
-- ----------------------------
INSERT INTO `format` VALUES (0, '学号后两位', 1, '63');
INSERT INTO `format` VALUES (1, '学号', 0, '1932100063');
INSERT INTO `format` VALUES (2, '姓名', 1, '小明');
INSERT INTO `format` VALUES (3, '_', 1, '_');
INSERT INTO `format` VALUES (4, '作业名', 1, '实验报告一');

-- ----------------------------
-- Records of homework
-- ----------------------------
INSERT INTO `homework` VALUES (30000, 'TEST222', 20004, '2020-20-20', '2021-02-23', 'TEST', 'TEST', '0234');
INSERT INTO `homework` VALUES (30006, 'PHP2', 20003, '2021-02-17', '2021-02-23', '3', 'php', '0234');
INSERT INTO `homework` VALUES (30007, 'TEST', 20000, '2020-20-20', '2021-02-23', 'TEST', 'TEST', '0234');
INSERT INTO `homework` VALUES (30008, 'Java实验报告一', 20001, '2021-02-23', '2021-02-23', '2', '# 详细', '0234');
INSERT INTO `homework` VALUES (30009, 'PHP', 20004, '2021-02-17', '2021-02-23', '3', 'php', '0234');
INSERT INTO `homework` VALUES (30012, 'PHP2', 20004, '2021-02-17', '2021-02-23', '3', 'php', '0234');
INSERT INTO `homework` VALUES (30018, '实验报告呀', 20000, '2021-03-08', '2021-03-10', 'SSSS', 'SSSS', '034');

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (100000, '11111', '11111', '芒果小洛', '1');
INSERT INTO `user` VALUES (100001, '22222', '22222', 'mmmmm', '0');
INSERT INTO `user` VALUES (1932101149, '1132101149', '1132101149', '张嗣龙', '1');

SET FOREIGN_KEY_CHECKS = 1;
