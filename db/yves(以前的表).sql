/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : yves

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2016-10-08 00:44:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `userPwd` varchar(255) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'yves', 'mima123');
INSERT INTO `t_user` VALUES ('2', 'jack', 'mima123');
