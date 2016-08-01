/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : zhanda

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2016-01-03 21:44:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mainCompany`
-- ----------------------------
DROP TABLE IF EXISTS `mainCompany`;
CREATE TABLE `mainCompany` (
  `com_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_name` varchar(20) DEFAULT NULL,
  `com_addr` varchar(50) DEFAULT NULL,
  `com_person` varchar(20) DEFAULT NULL,
  `com_phone` varchar(30) DEFAULT NULL,
  `com_fax` varchar(30) DEFAULT NULL,
  `com_type` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of mainCompany
-- ----------------------------
INSERT INTO `mainCompany` VALUES ('1', '展达', '广东', '李小姐', '1300000000', '8861', '-1');

-- ----------------------------
-- Table structure for `deliver_form`
-- ----------------------------
DROP TABLE IF EXISTS `deliver_form`;
CREATE TABLE `deliver_form` (
  `form_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_id` int(11) DEFAULT NULL,
  `deliver_time` timestamp NULL DEFAULT NULL,
  `print_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `print_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of deliver_form
-- ----------------------------

-- ----------------------------
-- Table structure for `deliver_record`
-- ----------------------------
DROP TABLE IF EXISTS `deliver_record`;
CREATE TABLE `deliver_record` (
  `deliver_id` int(11) NOT NULL AUTO_INCREMENT,
  `form_id` int(11) DEFAULT NULL,
  `prod_id` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`deliver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of deliver_record
-- ----------------------------

-- ----------------------------
-- Table structure for `mat_record`
-- ----------------------------
DROP TABLE IF EXISTS `mat_record`;
CREATE TABLE `mat_record` (
  `mat_id` int(11) NOT NULL AUTO_INCREMENT,
  `paper_id` int(11) NOT NULL,
  `zhidu` float NOT NULL,
  `zhichang` float NOT NULL,
  `per` float NOT NULL,
  PRIMARY KEY (`mat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of mat_record
-- ----------------------------

-- ----------------------------
-- Table structure for `paper`
-- ----------------------------
DROP TABLE IF EXISTS `paper`;
CREATE TABLE `paper` (
  `paper_id` int(11) NOT NULL AUTO_INCREMENT,
  `paper_name` varchar(20) NOT NULL,
  `com_id` int(11) NOT NULL,
  `per` float NOT NULL,
  PRIMARY KEY (`paper_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of paper
-- ----------------------------

-- ----------------------------
-- Table structure for `product_data`
-- ----------------------------
DROP TABLE IF EXISTS `product_data`;
CREATE TABLE `product_data` (
  `data_id` int(11) NOT NULL AUTO_INCREMENT,
  `data_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of product_data
-- ----------------------------

-- ----------------------------
-- Table structure for `product_form`
-- ----------------------------
DROP TABLE IF EXISTS `product_form`;
CREATE TABLE `product_form` (
  `form_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_id` int(11) NOT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `print_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `print_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of product_form
-- ----------------------------

-- ----------------------------
-- Table structure for `product_record`
-- ----------------------------
DROP TABLE IF EXISTS `product_record`;
CREATE TABLE `product_record` (
  `prod_id` int(11) NOT NULL AUTO_INCREMENT,
  `form_id` int(11) NOT NULL,
  `data_name` varchar(30) NOT NULL,
  `paper_id` int(11) DEFAULT NULL,
  `length` float DEFAULT NULL,
  `height` float DEFAULT NULL,
  `width` float DEFAULT NULL,
  `mat_id` int(11) DEFAULT NULL,
  `mat_count` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `per` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `memo` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `already_buy` int(11) DEFAULT NULL,
  `already_out` int(11) DEFAULT NULL,
  PRIMARY KEY (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of product_record
-- ----------------------------

-- ----------------------------
-- Table structure for `prod_purc_relation`
-- ----------------------------
DROP TABLE IF EXISTS `prod_purc_relation`;
CREATE TABLE `prod_purc_relation` (
  `rela_id` int(11) NOT NULL AUTO_INCREMENT,
  `prod_id` int(11) DEFAULT NULL,
  `purc_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`rela_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of prod_purc_relation
-- ----------------------------

-- ----------------------------
-- Table structure for `purchase_form`
-- ----------------------------
DROP TABLE IF EXISTS `purchase_form`;
CREATE TABLE `purchase_form` (
  `form_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_id` int(11) DEFAULT NULL,
  `com_no` varchar(20) DEFAULT NULL,
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  `print_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `print_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`form_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of purchase_form
-- ----------------------------

-- ----------------------------
-- Table structure for `purchase_record`
-- ----------------------------
DROP TABLE IF EXISTS `purchase_record`;
CREATE TABLE `purchase_record` (
  `purc_id` int(11) NOT NULL AUTO_INCREMENT,
  `form_id` int(11) DEFAULT NULL,
  `mat_id` int(11) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `total` float DEFAULT NULL,
  `memo` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`purc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of purchase_record
-- ----------------------------

-- ----------------------------
-- Table structure for `size`
-- ----------------------------
DROP TABLE IF EXISTS `size`;
CREATE TABLE `size` (
  `size_id` int(11) NOT NULL AUTO_INCREMENT,
  `com_id` int(11) NOT NULL,
  `size` float NOT NULL,
  PRIMARY KEY (`size_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of size
-- ----------------------------
