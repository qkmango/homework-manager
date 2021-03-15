# homework-manager

[![star](https://gitee.com/qkmango/homework-manager/badge/star.svg?theme=dark)](https://gitee.com/qkmango/homework-manager/stargazers)
[![fork](https://gitee.com/qkmango/homework-manager/badge/fork.svg?theme=dark)](https://gitee.com/qkmango/homework-manager/members)


## 介绍
> homework-manager，即 作业管理（系统）
> 她可以在班级内部使用，不同的用户权限可以有不同的操作权限

普通用户（学生）：提交作业、撤销提交作业
管理员用户：发布作业、修改作业、撤销发布的作业

## 软件架构
> 前后端分离，通过json进行通信
> 服务器使用的是Tomcat7版本

### 前端

基于layui前端框架构建的页面，所有js工具与框架，都在`lib/`目录下

| 引入               | 类别                              |
| ------------------ | --------------------------------- |
| jquery-3.5.1.js    | 工具类                            |
| jquery.confetti.js | 基于js的纸片飘落的动效            |
| layui/             | layui框架                         |
| laymd/             | 基于layui的markdown编辑与渲染工具 |
| coco-message/      | 消息弹窗工具类                    |
| alioss/            | 阿里云OSS的工具类                 |
| utils/             | 自己开发的项目工具集              |
| cal-heatmap/       | 热力图                          |

### 后端

基于JavaEE的后端，使用Servlet，分为三层架构（控制层、业务逻辑层、数据访问层）

| 技术    | 介绍                          |
| ------- | ----------------------------- |
| JavaEE  | 后端的开发基于JavaEE的Servlet |
| Mybatis | 数据访问层框架                |

### 数据库

使用MySQL 5.5版本



## 构建

使用Maven进行项目的构建



## 安装教程

### 安装服务器

服务器使用的是Tomcat7版本



### 搭建数据库

本项目基于MySQL 5.5版本，如需更换其他版本，请更改pom.xml配置文件的MySQL数据库驱动

创建一个新的数据库，项目中数据库名为homework_manager，运行以下SQL，可以创建相应的表

````sql
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
````



如需插入测试



### 修改配置文件

配置文件在`resources/`目录下

1. alioss.properties（需修改）：sts开头的为阿里OSS临时授权访问的一些配置，其他的为公共或则一般的配置，如endpoint是你的OSS空间的地址，sts.endpoint则为你的OSS空间的临时授权访问的地址
2. jdbc.properties（需修改）：为你的数据库的连接相关的配置
3. log4j.properties：为log4j日志配置文件
4. mybatis-config.xml：为Mybatis的配置文件



### 部署项目

将项目编译为war包后，放在Tomcat的webapps目录中，**并将war包重命名为 hm.war**

**项目的上下路径是hm**，所有的HTML页面都是基于路径 `<base href="/hm/">`



## 功能开发

- [x] 普通用户基本功能：作业的查询、提交、撤销，用户信息的修改 等基本功能
- [x] 管理员基本功能：作业的发布、修改、删除 功能
- [x] 主页数据可视化开发

## 色彩
<div style="background-color:#e9e9e9;color:black">#e9e9e9 rgb(233,233,233) 网页背景灰</div>
<div style="background-color:#30363d;color:white">#30363d rgb(48,54,61) 左侧导航选中背景</div>
<div style="background-color:#0d1117;color:white">#0d1117 rgb(13,17,23) 左侧导航背景</div>

<div style="background-color:#5FB878;color:white">#5FB878 rgb(95,184,120) 文字颜色（左侧上 退出）</div>
<div style="background-color:#2ebc4f;color:white">#2ebc4f rgb(46,188,79) 登陆页按钮鼠标悬浮色</div>

<div style="background-color:#58a6fd;color:black">#58a6fd rgb(88,166,253) 标题文字</div>
<div style="background-color:#01AAED;color:black">#01AAED rgb(1,170,237) 小字</div>

