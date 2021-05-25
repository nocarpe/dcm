

-- ----------------------------
-- Table structure for tbl_config
-- ----------------------------
DROP TABLE IF EXISTS `tbl_config`;
CREATE TABLE `tbl_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_dept_0
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dept_0`;
CREATE TABLE `tbl_dept_0` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_dept_1
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dept_1`;
CREATE TABLE `tbl_dept_1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_dept_2
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dept_2`;
CREATE TABLE `tbl_dept_2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_dept_3
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dept_3`;
CREATE TABLE `tbl_dept_3` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_dept_4
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dept_4`;
CREATE TABLE `tbl_dept_4` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_dept_5
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dept_5`;
CREATE TABLE `tbl_dept_5` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_dept_6
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dept_6`;
CREATE TABLE `tbl_dept_6` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_dept_7
-- ----------------------------
DROP TABLE IF EXISTS `tbl_dept_7`;
CREATE TABLE `tbl_dept_7` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_tel_0
-- ----------------------------
DROP TABLE IF EXISTS `tbl_tel_0`;
CREATE TABLE `tbl_tel_0` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_tel_1
-- ----------------------------
DROP TABLE IF EXISTS `tbl_tel_1`;
CREATE TABLE `tbl_tel_1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_tel_2
-- ----------------------------
DROP TABLE IF EXISTS `tbl_tel_2`;
CREATE TABLE `tbl_tel_2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tbl_tel_3
-- ----------------------------
DROP TABLE IF EXISTS `tbl_tel_3`;
CREATE TABLE `tbl_tel_3` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` bigint(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
