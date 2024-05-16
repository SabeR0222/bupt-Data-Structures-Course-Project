/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : localhost:3306
 Source Schema         : study_tour_system

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 12/05/2024 15:45:30
*/
CREATE DATABASE IF NOT EXISTS study_tour_system;
USE study_tour_system;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for journals
-- ----------------------------
DROP TABLE IF EXISTS `journals`;
CREATE TABLE `journals` (
  `journal_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `location_id` int DEFAULT NULL,
  `popularity` int DEFAULT NULL,
  `evaluation` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`journal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of journals
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for locations
-- ----------------------------
DROP TABLE IF EXISTS `locations`;
CREATE TABLE `locations` IF Not EXISTS `locations`(
  `location_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `popularity` int DEFAULT NULL,
  `evaluation` int DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of locations
-- ----------------------------
BEGIN;
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (1, '北京邮电大学', 0, '邮电', 99, 10);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (2, '北京航空航天大学', 0, '航空航天', 90, 9);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (3, '北京理工大学', 0, '理工', 80, 8);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (4, '北京大学', 0, '综合', 85, 9);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (5, '厦门大学', 0, '综合', 99, 10);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (6, '南京理工大学', 0, '理工', 95, 9);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (7, '东方明珠', 1, '建筑', 97, 10);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (8, '外滩', 1, '街道', 83, 7);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (9, '佘山国家森林公园', 1, '公园', 98, 8);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (10, '南京路步行街', 1, '街道', 87, 9);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (11, '世纪公园', 1, '公园', 90, 6);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (12, '宋庆龄故居', 1, '建筑', 96, 10);
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES (1, 'admin', '123456');
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES (2, 'saber', '1234567');
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES (3, 'PKing', '12345678');
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES (4, 'LC', '123456');
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES (5, 'admin5', '123456');
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES (6, 'admin6', '123456');
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES (7, 'admin7', '123456');
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES (8, 'admin8', '123456');
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES (9, 'admin9', '123456');
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES (10, 'admin10', '123456');
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES (11, 'admin11', '123456');
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES (12, 'admin12', '123456');
INSERT INTO `users` (`user_id`, `username`, `password`) VALUES (13, 'admin13', '123456');
COMMIT;



SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `location_browse_counts`;
CREATE TABLE location_browse_counts (
    -- 主键由用户ID和地点ID组成，确保每对用户-地点的唯一性
    user_id INTEGER NOT NULL,
    location_id INTEGER NOT NULL,
    count INTEGER NOT NULL DEFAULT 0, -- 浏览次数，默认为0

    -- 定义联合主键
    PRIMARY KEY (user_id, location_id),

    -- 外键约束，确保用户ID存在于users表中
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (location_id) REFERENCES locationss(location_id),

    -- 可选：为location_id创建索引，提高按地点查询的速度
    INDEX idx_location_id (location_id)
);
BEGIN;
-- 用户1在不同地点的浏览计数
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (1, 1, 25);
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (1, 2, 10);
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (1, 3, 5);
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (1, 4, 25);
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (1, 5, 10);
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (1, 6, 5);

-- 用户2在不同地点的浏览计数
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (2, 1, 15);
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (2, 4, 30);
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (2, 5, 7);

-- 用户3只在一处浏览
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (3, 3, 40);

-- 用户4在多个地点的浏览计数
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (4, 2, 20);
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (4, 4, 12);
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (4, 5, 8);

-- 用户11在不同地点的浏览计数
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (11, 1, 8);
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (11, 3, 15);
INSERT INTO location_browse_counts (user_id, location_id, count) VALUES (11, 5, 10);

COMMIT;

DROP TABLE IF EXISTS `article_scores`;
CREATE TABLE article_scores (
    -- 主键由用户ID和地点ID组成，确保每对用户-地点的唯一性
    user_id INTEGER NOT NULL,
    article_id INTEGER NOT NULL,
    score INTEGER NOT NULL DEFAULT 0, -- 浏览次数，默认为0

    -- 定义联合主键
    PRIMARY KEY (user_id, article_id),

    -- 外键约束，确保用户ID存在于users表中
    FOREIGN KEY (user_id) REFERENCES users(user_id)
    FOREIGN KEY (article_id) REFERENCES articles(article_id)
);
