/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : yves

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-11-02 21:02:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `count` bigint(11) NOT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `powerid` int(11) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('01432632-57e1-47ee-bfe4-94c4704626b6', '1234', '0', null, '1234', null, null, '3');
INSERT INTO `user` VALUES ('1e9a3a96-a496-4033-a510-f2402bf29d69', 'yves', '0', null, 'yves', null, null, '1');
INSERT INTO `user` VALUES ('233bdc45-8a43-4357-8b44-e89a9bed4706', 'qqq', '0', null, '444', null, null, '3');
INSERT INTO `user` VALUES ('59ca5379-e932-473d-97df-a84cfffcd5d8', '123', '0', null, '123', null, null, '3');
INSERT INTO `user` VALUES ('8109ceb5-2140-4e4c-9bac-5a5121516205', '买买买', '0', null, 'mmm', null, null, '3');
INSERT INTO `user` VALUES ('989e55e6-6b3e-43ca-839b-0a315afc1961', 'woai2', '0', '222', 'woai2', '222', '222', '1');
INSERT INTO `user` VALUES ('b474e14d-749f-4ef9-9dc6-bdc9056f1513', '12345', '0', null, '123', null, null, '3');
INSERT INTO `user` VALUES ('ba33481d-c6b7-4b27-8796-ac95cc1b5443', 'nihao', '0', '12425748544', 'nihao', 'heh', 'hehe', '2');
INSERT INTO `user` VALUES ('ea75a08b-f896-4ff6-a546-c975c26219d5', 'bbbb', '0', null, 'bbb', null, null, '3');
