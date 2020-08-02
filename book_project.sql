/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : book_project

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 02/08/2020 17:20:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_collect_info
-- ----------------------------
DROP TABLE IF EXISTS `book_collect_info`;
CREATE TABLE `book_collect_info` (
  `book_collect_id` varchar(32) NOT NULL,
  `book_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `collect_status` int(16) NOT NULL DEFAULT '0',
  PRIMARY KEY (`book_collect_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for book_command_info
-- ----------------------------
DROP TABLE IF EXISTS `book_command_info`;
CREATE TABLE `book_command_info` (
  `command_id` varchar(32) NOT NULL COMMENT '书籍评论ID',
  `book_id` varchar(32) NOT NULL COMMENT '书籍ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `command_context` varchar(255) NOT NULL COMMENT '评论内容',
  `command_status` int(11) NOT NULL DEFAULT '0' COMMENT '评论默认‘0’表示评论存在,‘’1表示已删除',
  `command_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '评论时间',
  `command_like` int(11) NOT NULL DEFAULT '0' COMMENT '评论赞的数量',
  PRIMARY KEY (`command_id`) USING BTREE,
  KEY `command` (`book_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info` (
  `book_id` varchar(32) NOT NULL COMMENT '书籍ID',
  `book_name` varchar(255) NOT NULL COMMENT '书籍名字',
  `book_intro` varchar(255) NOT NULL COMMENT '书籍简介',
  `book_cover` varchar(255) NOT NULL COMMENT '书籍封面',
  `book_tag` int(11) NOT NULL COMMENT '书籍标签',
  `book_num_collect` int(11) NOT NULL COMMENT '书籍收藏数',
  `book_num_command` int(11) NOT NULL COMMENT '书籍评论数',
  `book_url` varchar(255) NOT NULL COMMENT '书籍URL链接',
  `book_author` varchar(255) NOT NULL COMMENT '书籍作者',
  `chapter_sum` int(11) NOT NULL COMMENT '书籍章节总数',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for bookshelf_info
-- ----------------------------
DROP TABLE IF EXISTS `bookshelf_info`;
CREATE TABLE `bookshelf_info` (
  `bookshelf_id` varchar(32) NOT NULL COMMENT '书架ID',
  `book_id` varchar(32) NOT NULL COMMENT '书籍ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `book_status` int(11) NOT NULL COMMENT '‘0’代表在书架上,‘1’代表已经删除',
  `memory` int(11) NOT NULL COMMENT '存储阅读到的章节',
  PRIMARY KEY (`bookshelf_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for post_command_info
-- ----------------------------
DROP TABLE IF EXISTS `post_command_info`;
CREATE TABLE `post_command_info` (
  `command_id` varchar(32) NOT NULL COMMENT '帖子的评论ID',
  `post_id` varchar(32) NOT NULL COMMENT '帖子ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `command_context` varchar(255) NOT NULL COMMENT '评论内容',
  `command_status` int(11) NOT NULL DEFAULT '0' COMMENT '评论默认‘0’表示评论存在,‘’1表示已删除',
  `command_like` int(11) NOT NULL DEFAULT '0' COMMENT '评论时间',
  `command_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论赞的数量',
  PRIMARY KEY (`command_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for post_info
-- ----------------------------
DROP TABLE IF EXISTS `post_info`;
CREATE TABLE `post_info` (
  `post_id` varchar(32) NOT NULL COMMENT '帖子ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `book_name` varchar(255) NOT NULL COMMENT '书籍ID',
  `post_title` varchar(255) NOT NULL COMMENT '帖子标题',
  `post_content` varchar(255) NOT NULL COMMENT '帖子内容',
  `post_like` int(11) NOT NULL COMMENT '帖子赞的数量',
  `post_tag` varchar(255) NOT NULL COMMENT '帖子标签',
  `post_command_sum` int(11) NOT NULL COMMENT '帖子评论数量',
  `post_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发帖时间',
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for post_like_info
-- ----------------------------
DROP TABLE IF EXISTS `post_like_info`;
CREATE TABLE `post_like_info` (
  `post_like_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `post_id` varchar(32) NOT NULL,
  PRIMARY KEY (`post_like_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for tag_info
-- ----------------------------
DROP TABLE IF EXISTS `tag_info`;
CREATE TABLE `tag_info` (
  `tag_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `tag_name` varchar(255) NOT NULL COMMENT '类目名字',
  `tag_photo` varchar(255) NOT NULL COMMENT '类目图片URL',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `user_id` varchar(32) NOT NULL COMMENT '用户的账户ID',
  `user_password` varchar(32) NOT NULL COMMENT '用户的密码',
  `user_name` varchar(64) NOT NULL COMMENT '用户的昵称',
  `user_personal_word` varchar(255) NOT NULL COMMENT '用户的个性签名',
  `user_photo` varchar(255) DEFAULT NULL COMMENT '用户的头像',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
