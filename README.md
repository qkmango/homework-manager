# homework-manager

[![star](https://gitee.com/qkmango/homework-manager/badge/star.svg?theme=dark)](https://gitee.com/qkmango/homework-manager/stargazers)[![fork](https://gitee.com/qkmango/homework-manager/badge/fork.svg?theme=dark)](https://gitee.com/qkmango/homework-manager/members)


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
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `commit_homework`;
CREATE TABLE `commit_homework`  (
  `uid` int(11) NOT NULL COMMENT '完成者id（user.id）',
  `hid` int(11) NOT NULL COMMENT '完成的作业id（homework.id）',
  `filepath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作业文件的链接',
  PRIMARY KEY (`uid`, `hid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '科目id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '科目名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20005 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

DROP TABLE IF EXISTS `homework`;
CREATE TABLE `homework`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course` int(11) NOT NULL,
  `lastCommitDate` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createDate` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `briefInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `detailInfo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30040 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名（唯一）',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `realname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实名',
  `power` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100002 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
````





### 修改配置文件

配置文件在`resources/`目录下

1. alioss.properties（需修改）：sts开头的为阿里OSS临时授权访问的一些配置，其他的为公共或则一般的配置，如endpoint是你的OSS空间的地址，sts.endpoint则为你的OSS空间的临时授权访问的地址
2. jdbc.properties（需修改）：为你的数据库的连接相关的配置
3. log4j.properties：为log4j日志配置文件
4. mybatis-config.xml：为Mybatis的配置文件



### 部署项目

将项目编译为war包后，放在Tomcat的webapps目录中，**并将war包重命名为 hm.war**

**项目的上下路径是hm**



## 功能开发

- [x] 基本的功能（作业的发布、删除，提交、撤销等基本供）
- [x] 普通用户、管理员 权限的管理（不同权限有着不同的功能，只有管理员才能发布修改删除作业，管理员权限包含不同用户权限）