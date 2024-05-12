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
CREATE TABLE `locations` (
  `location_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` enum('学校','景区') DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `popularity` int DEFAULT NULL,
  `evaluation` int DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of locations
-- ----------------------------
BEGIN;
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (1, '北京邮电大学', '学校', '邮电', 99, 10);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (2, '北京航空航天大学', '学校', '航空航天', 90, 9);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (3, '北京理工大学', '学校', '理工', 80, 8);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (4, '北京大学', '学校', '综合', 85, 9);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (5, '厦门大学', '学校', '综合', 99, 10);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (6, '南京理工大学', '学校', '理工', 95, 9);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (7, '东方明珠', '景区', '建筑', 97, 10);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (8, '外滩', '景区', '街道', 83, 7);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (9, '佘山国家森林公园', '景区', '公园', 98, 8);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (10, '南京路步行街', '景区', '街道', 87, 9);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (11, '世纪公园', '景区', '公园', 90, 6);
INSERT INTO `locations` (`location_id`, `name`, `type`, `keyword`, `popularity`, `evaluation`) VALUES (12, '宋庆龄故居', '景区', '建筑', 96, 10);
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
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
