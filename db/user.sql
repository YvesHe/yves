/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : yves

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-11-01 19:45:58
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
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('', 'yves', '0', null, 'yves', null, null);
INSERT INTO `user` VALUES ('5af038d2-cfa7-4453-aa1b-b36008eef37e', '123', '0', null, '123', null, null);
INSERT INTO `user` VALUES ('6681a317-e0d7-408f-93b1-efad31cfab0d', 'sadf', '0', null, 'vvv', null, null);
INSERT INTO `user` VALUES ('ca58e014-1d55-459a-9bc0-4a350cf88945', 'test', '0', null, 'test', null, null);
