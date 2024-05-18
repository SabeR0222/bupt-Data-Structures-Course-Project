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

 Date: 17/05/2024 22:03:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article_scores
-- ----------------------------
DROP TABLE IF EXISTS `article_scores`;
CREATE TABLE `article_scores` (
  `user_id` int NOT NULL,
  `article_id` int NOT NULL,
  `score` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`article_id`),
  KEY `article_id` (`article_id`),
  CONSTRAINT `article_scores_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `article_scores_ibfk_2` FOREIGN KEY (`article_id`) REFERENCES `articles` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of article_scores
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for articles
-- ----------------------------
DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles` (
  `article_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `location_id` int DEFAULT NULL,
  `content` blob NOT NULL,
  `huffman_codes` text,
  `popularity` int DEFAULT '0',
  `evaluation` int DEFAULT '0',
  `user_id` int NOT NULL,
  PRIMARY KEY (`article_id`),
  KEY `user_id` (`user_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `articles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `articles_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `locations` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of articles
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for edges
-- ----------------------------
DROP TABLE IF EXISTS `edges`;
CREATE TABLE `edges` (
  `edge_id` int NOT NULL AUTO_INCREMENT,
  `src_id` int DEFAULT NULL,
  `dest_id` int DEFAULT NULL,
  `weight` int DEFAULT NULL,
  `crowding` double DEFAULT NULL,
  `is_rideable` bit(1) DEFAULT NULL,
  PRIMARY KEY (`edge_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3060 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of edges
-- ----------------------------
BEGIN;
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2732, 1, 91, 20, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2733, 2, 7, 44, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2734, 2, 11, 126, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2735, 2, 46, 10, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2736, 2, 48, 54, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2737, 2, 49, 138, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2738, 2, 78, 183, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2739, 2, 89, 84, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2740, 2, 91, 84, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2741, 3, 36, 59, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2742, 3, 85, 17, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2743, 4, 5, 16, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2744, 4, 9, 79, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2745, 4, 74, 133, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2746, 4, 86, 34, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2747, 5, 9, 95, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2748, 5, 74, 149, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2749, 5, 86, 50, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2750, 6, 7, 44, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2751, 6, 10, 82, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2752, 6, 76, 139, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2753, 6, 88, 40, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2754, 7, 8, 110, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2755, 7, 11, 82, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2756, 7, 48, 10, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2757, 7, 49, 94, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2758, 7, 78, 139, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2759, 7, 89, 40, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2760, 8, 12, 82, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2761, 8, 16, 474, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2762, 8, 34, 483, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2763, 8, 50, 72, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2764, 8, 58, 239, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2765, 8, 59, 75, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2766, 8, 80, 139, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2767, 8, 90, 40, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2768, 8, 97, 328, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2769, 8, 107, 443, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2770, 8, 108, 44, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2771, 9, 74, 54, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2772, 9, 86, 45, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2773, 10, 11, 44, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2774, 10, 76, 57, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2775, 10, 88, 42, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2776, 11, 12, 110, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2777, 11, 48, 72, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2778, 11, 49, 12, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2779, 11, 78, 57, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2780, 11, 89, 42, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2781, 12, 16, 392, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2782, 12, 34, 401, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2783, 12, 50, 10, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2784, 12, 58, 157, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2785, 12, 59, 157, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2786, 12, 80, 57, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2787, 12, 90, 42, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2788, 12, 97, 246, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2789, 12, 107, 361, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2790, 12, 108, 126, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2791, 13, 62, 83, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2792, 13, 73, 42, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2793, 13, 94, 147, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2794, 14, 43, 30, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2795, 14, 47, 46, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2796, 14, 93, 32, 0.8, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2797, 15, 65, 87, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2798, 15, 93, 127, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2799, 16, 34, 9, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2800, 16, 50, 402, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2801, 16, 58, 235, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2802, 16, 59, 549, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2803, 16, 80, 335, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2804, 16, 90, 434, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2805, 16, 97, 146, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2806, 16, 107, 31, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2807, 16, 108, 518, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2808, 17, 18, 198, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2809, 17, 60, 334, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2810, 17, 81, 98, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2811, 17, 92, 85, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2812, 17, 99, 287, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2813, 17, 102, 380, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2814, 18, 19, 60, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2815, 18, 58, 116, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2816, 18, 60, 136, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2817, 18, 81, 100, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2818, 18, 92, 283, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2819, 18, 99, 89, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2820, 18, 102, 182, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2821, 19, 21, 54, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2822, 19, 70, 149, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2823, 19, 109, 100, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2824, 19, 0, 283, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2825, 20, 21, 193, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2826, 20, 26, 107, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2827, 20, 28, 300, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2828, 20, 64, 90, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2829, 20, 71, 338, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2830, 20, 82, 93, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2831, 20, 100, 282, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2832, 20, 103, 375, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2833, 21, 28, 107, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2834, 21, 64, 283, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2835, 21, 71, 145, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2836, 21, 82, 100, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2837, 21, 100, 89, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2838, 21, 103, 182, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2839, 22, 74, 24, 0.8, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2840, 22, 75, 28, 1, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2841, 23, 24, 6, 0.8, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2842, 23, 95, 35, 0.8, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2843, 24, 96, 37, 0.8, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2844, 25, 56, 22, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2845, 25, 57, 35, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2846, 26, 45, 160, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2847, 26, 54, 90, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2848, 26, 63, 330, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2849, 26, 83, 93, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2850, 26, 101, 282, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2851, 26, 104, 375, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2852, 27, 79, 89, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2853, 28, 64, 390, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2854, 28, 71, 38, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2855, 28, 82, 207, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2856, 28, 100, 18, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2857, 28, 103, 75, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2861, 29, 56, 45, 0.5, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2862, 29, 67, 17, 0.5, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2867, 30, 86, 22, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2868, 30, 87, 30, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2869, 31, 85, 22, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2870, 31, 86, 20, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2871, 32, 96, 60, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2872, 32, 97, 56, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2873, 33, 98, 81, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2874, 34, 50, 411, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2875, 34, 58, 244, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2876, 34, 59, 558, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2877, 34, 80, 344, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2878, 34, 90, 443, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2879, 34, 97, 155, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2880, 34, 107, 40, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2881, 34, 108, 527, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2882, 35, 102, 59, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2883, 35, 103, 55, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2884, 36, 85, 76, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2885, 37, 41, 61, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2886, 37, 72, 15, 1, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2887, 37, 73, 63, 0.8, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2888, 37, 84, 99, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2889, 38, 83, 25, 1, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2890, 39, 55, 33, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2891, 39, 108, 65, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2892, 40, 44, 32, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2893, 40, 61, 191, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2894, 40, 77, 329, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2895, 40, 96, 140, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2896, 40, 105, 276, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2897, 40, 106, 25, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2898, 41, 84, 38, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2899, 42, 46, 60, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2900, 42, 108, 40, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2901, 44, 61, 223, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2902, 44, 77, 361, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2903, 44, 96, 172, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2904, 44, 105, 308, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2905, 44, 106, 57, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2906, 45, 54, 250, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2907, 45, 63, 170, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2908, 45, 83, 67, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2909, 45, 101, 122, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2910, 45, 104, 215, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2911, 48, 49, 84, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2912, 48, 78, 129, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2913, 48, 89, 30, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2914, 49, 78, 45, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2915, 49, 89, 54, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2916, 50, 58, 167, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2917, 50, 59, 147, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2918, 50, 80, 67, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2919, 50, 90, 32, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2920, 50, 97, 256, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2921, 50, 107, 371, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2922, 50, 108, 116, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2923, 51, 52, 32, 0.5, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2926, 51, 67, 5, 0.5, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2931, 52, 53, 18, 0.5, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2943, 53, 95, 19, 0.5, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2944, 54, 63, 420, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2945, 54, 64, 107, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2946, 54, 83, 183, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2947, 54, 101, 372, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2948, 54, 104, 465, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2949, 55, 92, 18, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2951, 56, 75, 53, 0.5, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2955, 57, 105, 21, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2956, 58, 59, 314, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2957, 58, 80, 100, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2958, 58, 90, 199, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2959, 58, 97, 89, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2960, 58, 107, 204, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2961, 58, 108, 283, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2962, 59, 80, 214, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2963, 59, 90, 115, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2964, 59, 97, 403, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2965, 59, 107, 518, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2966, 59, 108, 31, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2967, 60, 81, 236, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2968, 60, 92, 419, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2969, 60, 99, 47, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2970, 60, 102, 46, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2971, 61, 77, 138, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2972, 61, 96, 51, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2973, 61, 105, 85, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2974, 61, 106, 166, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2975, 62, 73, 125, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2976, 62, 94, 64, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2977, 63, 83, 237, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2978, 63, 101, 48, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2979, 63, 104, 45, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2980, 64, 71, 428, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2981, 64, 82, 183, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2982, 64, 100, 372, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2983, 64, 103, 465, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2984, 64, 0, 54, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2985, 65, 93, 40, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2986, 66, 76, 22, 0.8, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2987, 66, 77, 16, 0.8, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2992, 68, 78, 18, 0.8, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2993, 68, 79, 35, 0.8, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2994, 69, 79, 32, 0.8, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2995, 69, 80, 25, 0.8, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2996, 70, 109, 49, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2997, 70, 0, 134, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2998, 71, 82, 245, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (2999, 71, 100, 56, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3000, 71, 103, 37, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3001, 73, 74, 18, 0.8, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3002, 73, 94, 189, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3003, 74, 86, 99, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3004, 75, 76, 40, 0.8, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3005, 75, 87, 99, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3008, 76, 88, 99, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3009, 77, 78, 6, 0.8, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3010, 77, 96, 189, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3011, 77, 105, 53, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3012, 77, 106, 304, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3013, 78, 89, 99, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3014, 80, 81, 116, 1, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3015, 80, 90, 99, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3016, 80, 97, 189, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3017, 80, 107, 304, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3018, 80, 108, 183, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3019, 81, 92, 183, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3020, 81, 99, 189, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3021, 81, 102, 282, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3022, 81, 109, 60, 1, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3023, 82, 83, 107, 1, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3024, 82, 100, 189, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3025, 82, 103, 282, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3026, 82, 109, 54, 1, b'1');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3027, 83, 101, 189, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3028, 83, 104, 282, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3029, 84, 85, 39, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3030, 87, 88, 40, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3031, 87, 91, 84, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3033, 88, 89, 44, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3034, 89, 90, 110, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3035, 90, 97, 288, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3036, 90, 107, 403, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3037, 90, 108, 84, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3039, 92, 99, 372, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3040, 92, 102, 465, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3041, 92, 0, 60, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3042, 93, 94, 4, 0.8, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3043, 94, 95, 70, 0.8, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3044, 96, 105, 136, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3045, 96, 106, 115, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3046, 97, 98, 61, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3047, 97, 107, 115, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3048, 97, 108, 372, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3049, 98, 99, 55, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3050, 99, 100, 114, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3051, 99, 102, 93, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3052, 100, 101, 107, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3053, 100, 103, 93, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3054, 101, 104, 93, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3055, 103, 104, 107, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3056, 105, 106, 251, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3057, 106, 107, 116, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3058, 107, 108, 487, 1, b'0');
INSERT INTO `edges` (`edge_id`, `src_id`, `dest_id`, `weight`, `crowding`, `is_rideable`) VALUES (3059, 109, 0, 183, 1, b'0');
COMMIT;

-- ----------------------------
-- Table structure for location_browse_counts
-- ----------------------------
DROP TABLE IF EXISTS `location_browse_counts`;
CREATE TABLE `location_browse_counts` (
  `user_id` int NOT NULL,
  `location_id` int NOT NULL,
  `count` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`location_id`),
  KEY `idx_location_id` (`location_id`),
  CONSTRAINT `location_browse_counts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `location_browse_counts_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `locations` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of location_browse_counts
-- ----------------------------
BEGIN;
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (1, 1, 25);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (1, 2, 10);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (1, 3, 5);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (1, 4, 25);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (1, 5, 10);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (1, 6, 5);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (2, 1, 15);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (2, 4, 30);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (2, 5, 7);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (3, 3, 40);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (4, 2, 20);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (4, 4, 12);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (4, 5, 8);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (11, 1, 8);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (11, 3, 15);
INSERT INTO `location_browse_counts` (`user_id`, `location_id`, `count`) VALUES (11, 5, 10);
COMMIT;

-- ----------------------------
-- Table structure for locations
-- ----------------------------
DROP TABLE IF EXISTS `locations`;
CREATE TABLE `locations` (
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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

-- ----------------------------
-- Table structure for vertexes
-- ----------------------------
DROP TABLE IF EXISTS `vertexes`;
CREATE TABLE `vertexes` (
  `vertex_id` int NOT NULL,
  `x` int DEFAULT NULL,
  `y` int DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`vertex_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of vertexes
-- ----------------------------
BEGIN;
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (0, 150, 648, '西门', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (1, 150, 258, '青年公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (2, 150, 362, '学十三公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (3, 217, 184, '学十一公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (4, 200, 226, '学九公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (5, 184, 226, '留学生公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (6, 194, 318, '学五公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (7, 194, 362, '学三公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (8, 194, 472, '学一公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (9, 279, 226, '学十公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (10, 276, 318, '学八公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (11, 276, 362, '学四公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (12, 276, 472, '学二公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (13, 375, 208, '经管楼', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (14, 522, 172, '学六公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (15, 649, 204, '青年教师公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (16, 668, 472, '学29公寓', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (17, 235, 588, '教四楼', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (18, 433, 588, '教一楼', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (19, 433, 648, '主楼', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (20, 240, 702, '教三楼', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (21, 433, 702, '教二楼', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (22, 333, 250, '综合食堂东门', 2);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (23, 522, 313, '学苑风味餐厅', 2);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (24, 522, 319, '学生食堂', 2);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (25, 386, 300, '麦当劳', 2);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (26, 240, 809, '校医院', 2);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (27, 422, 415, '图书馆', 3);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (28, 540, 702, '科学会堂南门', 3);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (29, 431, 278, '综合服务楼', 3);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (30, 234, 248, '综合食堂西门', 3);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (31, 234, 206, '教九', 3);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (32, 522, 416, '篮球场', 4);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (33, 603, 533, '体育馆', 4);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (34, 677, 472, '游泳馆', 4);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (35, 615, 647, '体育场', 4);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (36, 158, 184, '北邮锦江酒店', 4);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (37, 333, 145, '北门快递站', 5);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (38, 333, 834, '中门邮局', 5);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (39, 150, 537, '邮局', 5);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (40, 662, 356, '东配楼', 5);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (41, 272, 145, '北门外卖柜', 6);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (42, 150, 432, '西门外卖柜', 6);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (43, 522, 142, '学六外卖柜', 6);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (44, 694, 356, '东门外卖柜', 6);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (45, 400, 809, '中门外卖柜', 6);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (46, 150, 372, '学十三洗衣房', 7);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (47, 476, 172, '学六洗衣房', 7);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (48, 204, 362, '学三洗衣房', 7);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (49, 288, 362, '学四洗衣房', 7);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (50, 266, 472, '学二洗衣房', 7);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (51, 453, 278, '物美超市', 8);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (52, 485, 278, '瑞幸咖啡', 8);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (53, 503, 278, '打印店', 8);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (54, 150, 809, '校车车库', 8);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (55, 150, 570, 'ATM', 8);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (56, 386, 278, '学生活动中心', 9);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (57, 386, 335, '学生发展中心', 9);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (58, 433, 472, '行政办公楼', 9);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (59, 119, 472, '鸿通楼', 9);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (60, 569, 588, '科学会堂北门', 9);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (61, 471, 356, '档案馆', 10);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (62, 458, 208, '大创中心', 10);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (63, 570, 809, '创新楼', 10);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (64, 150, 702, '停车坪', 10);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (65, 562, 204, '科研楼', 10);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (66, 333, 340, '大电视', 11);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (67, 448, 278, '浴室', 11);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (68, 333, 380, '小松林', 11);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (69, 333, 447, '时光广场', 11);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (70, 284, 648, '主席像', 11);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (71, 578, 702, '李白烈士雕像', 11);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (72, 333, 130, '北门', 1);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (73, 333, 208, '经管院左下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (74, 333, 226, '学十公寓右下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (75, 333, 278, '综合食堂右下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (76, 333, 318, '学八公寓右下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (77, 333, 356, '小松林左上角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (78, 333, 362, '学四公寓右上角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (79, 333, 415, '图书馆左边', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (80, 333, 472, '教四楼右上角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (81, 333, 588, '教四楼右下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (82, 333, 702, '教三楼右上角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (83, 333, 809, '教三楼右下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (84, 234, 145, '学十公寓左上角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (85, 234, 184, '学十一公寓右下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (86, 234, 226, '教九右下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (87, 234, 278, '学八公寓左上角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (88, 234, 318, '学八公寓左下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (89, 234, 362, '学四公寓左上角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (90, 234, 472, '学二公寓左下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (91, 150, 278, '学五公寓左上角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (92, 150, 588, '教四楼左下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (93, 522, 204, '科研楼左上角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (94, 522, 208, '学六公寓右下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (95, 522, 278, '锅炉房右下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (96, 522, 356, '学生食堂左下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (97, 522, 472, '篮球场左下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (98, 522, 533, '体育馆左边', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (99, 522, 588, '教一楼右下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (100, 522, 702, '主楼右下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (101, 522, 809, '教二楼右下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (102, 615, 588, '科学会堂右上角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (103, 615, 702, '创新楼右上角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (104, 615, 809, '创新楼右下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (105, 386, 356, '学生发展中心左下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (106, 637, 356, '学29公寓左上角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (107, 637, 472, '学29公寓左下角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (108, 150, 472, '教四楼左上角', 0);
INSERT INTO `vertexes` (`vertex_id`, `x`, `y`, `label`, `type`) VALUES (109, 333, 648, '主席像右边', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
