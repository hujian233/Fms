SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- database for form
-- ----------------------------
DROP DATABASE IF EXISTS `fmsdb`;
CREATE DATABASE `fmsdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `fmsdb`;

-- -------------------------------
-- Table structure for users
-- -------------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                         `id` INT(10) PRIMARY KEY AUTO_INCREMENT COMMENT '主键自增',
                         `jobnumber` BIGINT(255) NOT NULL UNIQUE COMMENT '工号',
                         `username` VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',
                         `password` VARCHAR(256) NOT NULL COMMENT '密码',
                         `mailaddress` VARCHAR(50) NOT NULL UNIQUE COMMENT '邮箱',
                         `authority` INT(10) NOT NULL COMMENT '权限',
                         `department` VARCHAR(50) NOT NULL  COMMENT '部门',
                         `logintime` DATE COMMENT '上次登录时间'
)ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin;
