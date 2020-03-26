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
CREATE TABLE `user`
(
    `id`          INT(10) PRIMARY KEY AUTO_INCREMENT COMMENT '主键自增',
    `jobnumber`   BIGINT(255)  NOT NULL UNIQUE COMMENT '工号',
    `username`    VARCHAR(20)  NOT NULL UNIQUE COMMENT '用户名',
    `password`    VARCHAR(256) NOT NULL COMMENT '密码',
    `mailaddress` VARCHAR(50)  NOT NULL UNIQUE COMMENT '邮箱',
    `authority`   INT(10)      NOT NULL COMMENT '权限',
    `department`  VARCHAR(50)  NOT NULL COMMENT '部门',
    `logintime`   DATE COMMENT '上次登录时间'
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_bin;

CREATE TABLE `fixture_define`
(
    `id`       int(10)     NOT NULL AUTO_INCREMENT COMMENT '主键自增',
    `workcell` varchar(50) NOT NULL COMMENT '夹具所属工作部',
    `code`     varchar(50) DEFAULT NULL COMMENT '夹具代码',
    `name`     varchar(30) NOT NULL COMMENT '夹具名称',
    `family`   varchar(20) COMMENT '所属大类',
    `model`    varchar(30) COMMENT '夹具模组',
    `partno`   varchar(20) COMMENT '所属料号',
    `upl`      INT(10)     NOT NULL COMMENT '需要的数量',
    `usedfor`  varchar(20) COMMENT '用途',
    `pmperiod` INT(10) COMMENT '保养点检周期',
    `owner`    varchar(20) COMMENT '责任人',
    `rec_on`   DATE        DEFAULT NULL COMMENT '录入日期',
    `rec_by`   varchar(20) NOT NULL COMMENT '录入人',
    `edit_on`  DATE        DEFAULT NULL COMMENT '修改日期',
    `edit_by`  varchar(20) COMMENT '修改人',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='夹具定义';

CREATE TABLE `fixture_entity`
(
    `code`       varchar(20) COMMENT '夹具代码',
    `seqid`      varchar(20) COMMENT '夹具序列号',
    `billno`     varchar(20) COMMENT '采购单据号',
    `reg_date`   DATE    DEFAULT NULL COMMENT '入库日期',
    `used_count` INT(10) COMMENT '已使用次数',
    `location`   varchar(20) COMMENT '存放库位',
    `status`     INT(10) DEFAULT NULL COMMENT '状态'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='夹具实体';