/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : db_dorm

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 20/03/2022 00:21:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `adminId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`adminId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', '123', 'Lero', '男', '123');

-- ----------------------------
-- Table structure for t_dorm
-- ----------------------------
DROP TABLE IF EXISTS `t_dorm`;
CREATE TABLE `t_dorm`  (
  `dormId` int(11) NOT NULL AUTO_INCREMENT,
  `dormBuildId` int(11) NULL DEFAULT NULL,
  `dormName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dormType` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dormNumber` int(11) NULL DEFAULT NULL,
  `dormTel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dormId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dorm
-- ----------------------------
INSERT INTO `t_dorm` VALUES (1, 1, '220', '男', 6, '110');

-- ----------------------------
-- Table structure for t_dormbuild
-- ----------------------------
DROP TABLE IF EXISTS `t_dormbuild`;
CREATE TABLE `t_dormbuild`  (
  `dormBuildId` int(11) NOT NULL AUTO_INCREMENT,
  `dormBuildName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dormBuildDetail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dormBuildId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dormbuild
-- ----------------------------
INSERT INTO `t_dormbuild` VALUES (1, '1栋', '这是一栋。。。');
INSERT INTO `t_dormbuild` VALUES (4, '2栋', '这是3栋');
INSERT INTO `t_dormbuild` VALUES (5, '3栋', '飞洒地方');
INSERT INTO `t_dormbuild` VALUES (6, '4栋', '这是4栋');
INSERT INTO `t_dormbuild` VALUES (9, '10栋', '阿迪斯发');

-- ----------------------------
-- Table structure for t_dormmanager
-- ----------------------------
DROP TABLE IF EXISTS `t_dormmanager`;
CREATE TABLE `t_dormmanager`  (
  `dormManId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dormBuildId` int(11) NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dormManId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dormmanager
-- ----------------------------
INSERT INTO `t_dormmanager` VALUES (2, 'manager2', '123', 4, '小张', '男', '123');
INSERT INTO `t_dormmanager` VALUES (3, 'manager3', '123', 1, '小李', '女', '123');
INSERT INTO `t_dormmanager` VALUES (4, 'manager4', '123', 5, '小陈', '男', '123');
INSERT INTO `t_dormmanager` VALUES (5, 'manager5', '123', 1, '小宋', '男', '123');
INSERT INTO `t_dormmanager` VALUES (7, 'manager6', '123', 4, '呵呵 ', '女', '123');
INSERT INTO `t_dormmanager` VALUES (8, 'manager1', '123', 6, '小白', '男', '123');
INSERT INTO `t_dormmanager` VALUES (9, 'manager7', '123', 1, '哈哈', '女', '123');
INSERT INTO `t_dormmanager` VALUES (10, 'manager10', '123', 9, '喝了咯', '男', '17683994234');

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record`  (
  `recordId` int(11) NOT NULL AUTO_INCREMENT,
  `studentNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `studentName` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dormBuildId` int(11) NULL DEFAULT NULL,
  `dormName` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `detail` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`recordId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_record
-- ----------------------------
INSERT INTO `t_record` VALUES (3, '007', '测试1', 1, '221', '2014-08-11', '123');
INSERT INTO `t_record` VALUES (4, '005', '赵起', 4, '220', '2014-08-12', '...');
INSERT INTO `t_record` VALUES (5, '006', '王珂珂', 4, '111', '2014-08-12', '00');
INSERT INTO `t_record` VALUES (6, '004', '李进', 6, '220', '2014-08-12', '....');
INSERT INTO `t_record` VALUES (7, '004', '李进', 6, '220', '2014-08-12', '22');
INSERT INTO `t_record` VALUES (16, '003', '王五', 5, '201', '2022-03-18', '111');
INSERT INTO `t_record` VALUES (17, '002', '李四', 4, '120', '2022-03-19', 'abc');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `studentId` int(11) NOT NULL AUTO_INCREMENT,
  `stuNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dormBuildId` int(11) NULL DEFAULT NULL,
  `dormName` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`studentId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES (3, '003', '111', '王五', 5, '201', '男', '2');
INSERT INTO `t_student` VALUES (4, '004', '123', '李进', 6, '220', '女', '1');
INSERT INTO `t_student` VALUES (5, '005', '123', '赵起', 4, '220', '女', '123');
INSERT INTO `t_student` VALUES (6, '006', '123', '王珂珂', 4, '111', '女', '111');
INSERT INTO `t_student` VALUES (9, '007', '123', '测试1', 1, '221', '男', '123');
INSERT INTO `t_student` VALUES (28, '001', '123', '测试1', 1, '111', '男', '123');
INSERT INTO `t_student` VALUES (29, '008', '123', '测试3', 6, '123', '男', '123');
INSERT INTO `t_student` VALUES (32, '019', '123', '喝了咯', 6, '220', '男', '17683994234');

SET FOREIGN_KEY_CHECKS = 1;
