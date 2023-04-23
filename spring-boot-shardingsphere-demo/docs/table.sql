SET NAMES utf8;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_blog_0
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_0`;
CREATE TABLE `t_blog_0` (
  `id` bigint(30) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `userId` bigint(30) NOT NULL,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_blog_1
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_1`;
CREATE TABLE `t_blog_1` (
  `id` bigint(30) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `userId` bigint(30) NOT NULL,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_blog_2
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_2`;
CREATE TABLE `t_blog_2` (
  `id` bigint(30) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `userId` bigint(30) NOT NULL,
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_config
-- ----------------------------
DROP TABLE IF EXISTS `t_config`;
CREATE TABLE `t_config` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `remark` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for t_user_0
-- ----------------------------
DROP TABLE IF EXISTS `t_user_0`;
CREATE TABLE `t_user_0` (
  `userId` bigint(30) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_1
-- ----------------------------
DROP TABLE IF EXISTS `t_user_1`;
CREATE TABLE `t_user_1` (
  `userId` bigint(30) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_2
-- ----------------------------
DROP TABLE IF EXISTS `t_user_2`;
CREATE TABLE `t_user_2` (
  `userId` bigint(30) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;