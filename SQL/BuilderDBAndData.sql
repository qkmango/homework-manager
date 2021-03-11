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
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '科目id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '科目名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20005 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (20000, 'java');
INSERT INTO `course` VALUES (20001, 'php');
INSERT INTO `course` VALUES (20002, 'HTML');
INSERT INTO `course` VALUES (20003, '微机原理');
INSERT INTO `course` VALUES (20004, 'sqlserver');

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
-- Records of format
-- ----------------------------
INSERT INTO `format` VALUES (0, '学号后两位', 1, '63');
INSERT INTO `format` VALUES (1, '学号', 0, '1932100063');
INSERT INTO `format` VALUES (2, '姓名', 1, '小明');
INSERT INTO `format` VALUES (3, '_', 1, '_');
INSERT INTO `format` VALUES (4, '作业名', 1, '实验报告一');

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
) ENGINE = InnoDB AUTO_INCREMENT = 30019 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1932101152 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (100000, '11111', '11111', '芒果小洛', '1');
INSERT INTO `user` VALUES (100001, '22222', '22222', 'mmmmm', '0');
INSERT INTO `user` VALUES (1932101101, '1932101101', '1932101101', '曹志强', '0');
INSERT INTO `user` VALUES (1932101102, '1932101102', '1932101102', '常建华', '0');
INSERT INTO `user` VALUES (1932101103, '1932101103', '1932101103', '陈晓伟', '0');
INSERT INTO `user` VALUES (1932101104, '1932101104', '1932101104', '陈旭', '0');
INSERT INTO `user` VALUES (1932101105, '1932101105', '1932101105', '陈智杰', '0');
INSERT INTO `user` VALUES (1932101106, '1932101106', '1932101106', '程静', '0');
INSERT INTO `user` VALUES (1932101107, '1932101107', '1932101107', '方明', '0');
INSERT INTO `user` VALUES (1932101108, '1932101108', '1932101108', '丰上拾', '0');
INSERT INTO `user` VALUES (1932101109, '1932101109', '1932101109', '高雨', '0');
INSERT INTO `user` VALUES (1932101110, '1932101110', '1932101110', '郭林林', '0');
INSERT INTO `user` VALUES (1932101111, '1932101111', '1932101111', '何俊杰', '0');
INSERT INTO `user` VALUES (1932101112, '1932101112', '1932101112', '胡观颐', '0');
INSERT INTO `user` VALUES (1932101113, '1932101113', '1932101113', '胡徽桥', '0');
INSERT INTO `user` VALUES (1932101114, '1932101114', '1932101114', '黄磊', '0');
INSERT INTO `user` VALUES (1932101115, '1932101115', '1932101115', '纪国伟', '0');
INSERT INTO `user` VALUES (1932101116, '1932101116', '1932101116', '江悦', '0');
INSERT INTO `user` VALUES (1932101117, '1932101117', '1932101117', '金鹏', '0');
INSERT INTO `user` VALUES (1932101118, '1932101118', '1932101118', '李乐乐', '0');
INSERT INTO `user` VALUES (1932101119, '1932101119', '1932101119', '栗龙', '0');
INSERT INTO `user` VALUES (1932101120, '1932101120', '1932101120', '梁少俊', '0');
INSERT INTO `user` VALUES (1932101121, '1932101121', '1932101121', '廖汉豪', '0');
INSERT INTO `user` VALUES (1932101122, '1932101122', '1932101122', '林蝶', '0');
INSERT INTO `user` VALUES (1932101123, '1932101123', '1932101123', '鲁立鹏', '0');
INSERT INTO `user` VALUES (1932101124, '1932101124', '1932101124', '马涛', '0');
INSERT INTO `user` VALUES (1932101125, '1932101125', '1932101125', '彭义安', '0');
INSERT INTO `user` VALUES (1932101126, '1932101126', '1932101126', '邵雨', '0');
INSERT INTO `user` VALUES (1932101127, '1932101127', '1932101127', '汪桂冰', '0');
INSERT INTO `user` VALUES (1932101128, '1932101128', '1932101128', '汪金', '0');
INSERT INTO `user` VALUES (1932101129, '1932101129', '1932101129', '王浩洋', '0');
INSERT INTO `user` VALUES (1932101130, '1932101130', '1932101130', '王慧晴', '0');
INSERT INTO `user` VALUES (1932101131, '1932101131', '1932101131', '王良政', '0');
INSERT INTO `user` VALUES (1932101132, '1932101132', '1932101132', '王蒙恩', '0');
INSERT INTO `user` VALUES (1932101133, '1932101133', '1932101133', '王思贤', '0');
INSERT INTO `user` VALUES (1932101134, '1932101134', '1932101134', '王新宇', '0');
INSERT INTO `user` VALUES (1932101135, '1932101135', '1932101135', '王宇', '0');
INSERT INTO `user` VALUES (1932101136, '1932101136', '1932101136', '徐恩念', '0');
INSERT INTO `user` VALUES (1932101137, '1932101137', '1932101137', '许贺贺', '0');
INSERT INTO `user` VALUES (1932101138, '1932101138', '1932101138', '许玉楠', '0');
INSERT INTO `user` VALUES (1932101139, '1932101139', '1932101139', '姚佳乐', '0');
INSERT INTO `user` VALUES (1932101140, '1932101140', '1932101140', '姚静涛', '0');
INSERT INTO `user` VALUES (1932101141, '1932101141', '1932101141', '叶龙', '0');
INSERT INTO `user` VALUES (1932101142, '1932101142', '1932101142', '易思尚', '0');
INSERT INTO `user` VALUES (1932101143, '1932101143', '1932101143', '余霆', '0');
INSERT INTO `user` VALUES (1932101144, '1932101144', '1932101144', '余兆林', '0');
INSERT INTO `user` VALUES (1932101145, '1932101145', '1932101145', '余郑龙', '0');
INSERT INTO `user` VALUES (1932101146, '1932101146', '1932101146', '袁陈龙', '0');
INSERT INTO `user` VALUES (1932101147, '1932101147', '1932101147', '臧同同', '0');
INSERT INTO `user` VALUES (1932101148, '1932101148', '1932101148', '张凌风', '0');
INSERT INTO `user` VALUES (1932101149, '1932101149', '1932101149', '张嗣龙', '1');
INSERT INTO `user` VALUES (1932101150, '1932101150', '1932101150', '周晗', '0');
INSERT INTO `user` VALUES (1932101151, '1932101151', '1932101151', '郑澳', '0');

-- ----------------------------
-- View structure for v_commit_count
-- ----------------------------
DROP VIEW IF EXISTS `v_commit_count`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `v_commit_count` AS select count(0) AS `count`,`commit_homework`.`hid` AS `hid` from `commit_homework` group by `commit_homework`.`hid`;

SET FOREIGN_KEY_CHECKS = 1;
