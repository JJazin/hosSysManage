/*
SQLyog Ultimate v8.32 
MySQL - 5.5.36 : Database - hospital_illness
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hospital_illness` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hospital_illness`;

/*Table structure for table `_app_user` */

DROP TABLE IF EXISTS `_app_user`;

CREATE TABLE `_app_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `cart_vital_code` varchar(100) DEFAULT NULL COMMENT '医保卡号',
  `create_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `_app_user` */

insert  into `_app_user`(`id`,`phone`,`password`,`name`,`cart_vital_code`,`create_time`,`last_login_time`,`status`) values (10,'15764345947','698d51a19d8a121ce581499d7b701668','哈哈','223366','2017-12-17 20:36:08',NULL,0),(11,'15766666666','e10adc3949ba59abbe56e057f20f883e','',NULL,'2018-01-01 14:04:56',NULL,0);

/*Table structure for table `_case` */

DROP TABLE IF EXISTS `_case`;

CREATE TABLE `_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '病人ID',
  `did` int(11) NOT NULL COMMENT '医生ID',
  `hos_id` int(11) NOT NULL COMMENT '医院ID',
  `content` text NOT NULL,
  `create_time` datetime NOT NULL,
  `sickness_id` int(11) DEFAULT NULL COMMENT '疾病',
  `recipel` text COMMENT '处方',
  `advice` text COMMENT '医嘱',
  `reason` text COMMENT '症状',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `_case` */

insert  into `_case`(`id`,`uid`,`did`,`hos_id`,`content`,`create_time`,`sickness_id`,`recipel`,`advice`,`reason`) values (1,5,1,1,'ffff','2018-03-03 14:58:30',1,NULL,'asdf','1231321'),(2,5,1,1,'a','2018-03-03 15:08:04',3,NULL,'sdfsdaf','1231321'),(3,5,4,1,'gggg','2018-03-03 15:14:42',2,NULL,'asdf','1231321'),(4,6,4,3,'qqq','2018-03-03 15:31:11',3,NULL,NULL,'1231321'),(5,5,1,1,'嘎发生的','2018-03-04 20:21:45',2,'感冒灵',NULL,'1231321'),(6,5,4,1,'123','2018-03-24 08:58:03',1,'123',NULL,'1231321'),(7,7,4,1,'111','2018-03-24 09:31:47',3,'11',NULL,'1231321'),(8,6,4,3,'123','2018-03-24 12:08:53',2,'123','','1231321'),(9,6,4,3,'111','2018-03-24 15:55:38',1,'111','','1231321');

/*Table structure for table `_collect` */

DROP TABLE IF EXISTS `_collect`;

CREATE TABLE `_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `hos_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `_collect` */

insert  into `_collect`(`id`,`uid`,`hos_id`) values (8,10,1),(11,10,4);

/*Table structure for table `_dep_doctor` */

DROP TABLE IF EXISTS `_dep_doctor`;

CREATE TABLE `_dep_doctor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,
  `dep_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='科室 - 医生映射表';

/*Data for the table `_dep_doctor` */

insert  into `_dep_doctor`(`id`,`doctor_id`,`dep_id`) values (12,1,1),(13,2,1),(16,3,2),(17,4,2),(18,5,4),(19,6,4);

/*Table structure for table `_department` */

DROP TABLE IF EXISTS `_department`;

CREATE TABLE `_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hos_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '科室名',
  `desc` varchar(500) NOT NULL COMMENT '描述、主治什么',
  `normal_num` int(11) NOT NULL COMMENT '普通号数量',
  `expert_num` int(11) NOT NULL COMMENT '专家号数量',
  `normal_money` int(11) NOT NULL DEFAULT '0' COMMENT '普通挂号费',
  `expert_money` int(11) NOT NULL DEFAULT '0' COMMENT '专家挂号费',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `_department` */

insert  into `_department`(`id`,`hos_id`,`name`,`desc`,`normal_num`,`expert_num`,`normal_money`,`expert_money`) values (1,1,'外科','外科治疗2',8,4,6,12),(2,3,'嘿嘿嘿科室','啥都不会别来找我',10,5,7,14),(3,1,'fwe发低烧f','而且我',2,3,2,3),(4,4,'内科','专职治疗内伤',4,2,4,10);

/*Table structure for table `_doctor` */

DROP TABLE IF EXISTS `_doctor`;

CREATE TABLE `_doctor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hos_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '医生名',
  `sex` tinyint(1) NOT NULL COMMENT '1:男 2:女',
  `tel` varchar(20) NOT NULL,
  `major` varchar(500) NOT NULL COMMENT '擅长',
  `pic` varchar(500) NOT NULL,
  `level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '级别 0:普通医师 1:专家医师',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `_doctor` */

insert  into `_doctor`(`id`,`hos_id`,`name`,`sex`,`tel`,`major`,`pic`,`level`) values (1,1,'张三',2,'18888888882','外伤','/upload/images/30d023cd3b26440b8a856d0278f160f2.png',0),(2,1,'李四小',1,'18888888888','皮外伤','/upload/images/159ce56e610f42c9800e3c2af21296c5.png',1),(3,3,'枉法',1,'12312321','啥也不会','/upload/images/c4cae5982b5e421eaaef9fac00a783f4.png',0),(4,3,'分为前',2,'1231321f','苏打粉','/upload/images/99a4773a829847e29f67980dcf6d1bb4.png',1),(5,4,'小芳',2,'17777777777','内伤','/upload/images/4dd541b2074f4c3ab9b19fce8022da10.png',1),(6,4,'雷雷',1,'12222222222','内伤皮毛','/upload/images/d0352fe9a8a944ba83dec95e8a391940.png',0);

/*Table structure for table `_hospital` */

DROP TABLE IF EXISTS `_hospital`;

CREATE TABLE `_hospital` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '医院名称',
  `address` varchar(500) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `pic` varchar(500) NOT NULL,
  `desc` varchar(500) NOT NULL COMMENT '医院描述 简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `_hospital` */

insert  into `_hospital`(`id`,`name`,`address`,`tel`,`pic`,`desc`) values (1,'上海人民医院','上海市朝阳区人民大街一号','17777777777','/upload/images/f53466b80d9d400d851a7ad9d2855480.png','绯闻绯闻发生的非瓦房店市分 v 出现在绯闻绯闻发生的非瓦房店市分 v 出现在绯闻绯闻发生的非瓦房店市分 v 出现在绯闻绯闻发生的非瓦房店市分 v 出现在绯闻绯闻发生的非瓦房店市分 v 出现在绯闻绯闻发生的非瓦房店市分 v 出现在绯闻绯闻发生的非瓦房店市分 v 出现在'),(3,'哈哈哈医院','海淀区101号','133323232','/upload/images/4392a1a5992947fca7a97313c8a1ab17.png','什么都治不了，千万别来。'),(4,'无敌医院','西山区2204','17899998887','/upload/images/a3d73fe06fbe4bb6be40f3fd88149386.png','什么都能治，没有治不了的病，就是这么调。'),(5,'ttt','fdsafsd','12312321','/upload/images/46d391e04a284229ac9acd9545b3bdc4.jpg','ttt'),(6,'人民医院','兴和大陆一号','177665544','/upload/images/e06edc0dfa4348cfbf341e3511b52ce1.jpg','人民医院哈哈哈'),(7,'666','666','666','/upload/images/59c4e2b3a86147b4884ee9f4ab3be8b5.jpg','666');

/*Table structure for table `_menu` */

DROP TABLE IF EXISTS `_menu`;

CREATE TABLE `_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `_menu` */

insert  into `_menu`(`id`,`pid`,`title`,`href`,`icon`,`status`,`weight`,`create_time`,`update_time`) values (1,0,'系统管理','#','glyphicon glyphicon-th',0,1,'2017-11-04 11:00:59','2017-11-04 11:18:42'),(2,1,'菜单管理','system/menu.html','glyphicon glyphicon-list',0,2,'2017-11-04 11:01:11','2017-11-04 11:18:55'),(3,1,'用户管理','system/user.html','glyphicon glyphicon-user',0,3,'2017-11-04 11:04:02','2017-11-04 11:19:04'),(4,1,'权限管理','system/role.html','glyphicon glyphicon-lock',0,4,'2017-11-04 11:04:26','2017-12-30 10:01:04'),(5,0,'内容管理','#','glyphicon glyphicon-th',0,5,'2017-11-04 11:43:21','2017-11-04 11:43:21'),(12,5,'医院管理','content/hospital/index.html','glyphicon glyphicon-record',0,6,'2017-12-29 14:35:23','2017-12-29 19:54:43'),(14,5,'病例管理','content/case/index.html','glyphicon glyphicon-asterisk',0,7,'2018-03-03 14:31:14','2018-03-03 20:50:06'),(15,5,'病人管理','content/illness_d/index.html','glyphicon glyphicon-glass',0,8,'2018-03-03 15:23:59','2018-03-03 20:50:37'),(16,5,'公告管理','content/notice/index.html','glyphicon glyphicon-star',0,9,'2018-03-03 20:39:29','2018-03-03 20:50:49'),(17,5,'公告查看','content/notice_read/index.html','glyphicon glyphicon-star-empty',0,10,'2018-03-03 20:52:04','2018-03-03 20:52:04'),(18,5,'本季度患病统计','content/season_sickness/index.html','glyphicon glyphicon-pencil',0,11,'2018-03-04 20:47:39','2018-03-04 20:48:04');

/*Table structure for table `_notice` */

DROP TABLE IF EXISTS `_notice`;

CREATE TABLE `_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `_notice` */

insert  into `_notice`(`id`,`title`,`content`,`create_time`) values (1,'这是一个公告1','哈哈哈22','2018-03-03 20:39:55');

/*Table structure for table `_registration` */

DROP TABLE IF EXISTS `_registration`;

CREATE TABLE `_registration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL COMMENT '挂号单编号',
  `money` int(11) NOT NULL COMMENT '金额',
  `hos_id` int(11) NOT NULL COMMENT '医院id',
  `dep_id` int(11) NOT NULL COMMENT '挂号科室id',
  `doctor_id` int(11) NOT NULL COMMENT '医师id',
  `uid` int(11) NOT NULL COMMENT '挂号用户id',
  `order_date` date NOT NULL COMMENT '预约日期',
  `medical_time` varchar(100) DEFAULT NULL COMMENT '预计就医时间 此处存null占位',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0:普通挂号 1:专家挂号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='挂号表';

/*Data for the table `_registration` */

insert  into `_registration`(`id`,`code`,`money`,`hos_id`,`dep_id`,`doctor_id`,`uid`,`order_date`,`medical_time`,`create_time`,`type`) values (12,'53de6e7f9b35475ea22c2af542ef64ee',6,1,1,1,10,'2018-01-01','2018-01-01 09:00:00','2018-01-01 10:55:17',0),(13,'e6da4c4725d145ddb971ee9adbd07c5c',6,1,1,1,10,'2018-01-01','2018-01-01 10:00:00','2018-01-01 10:57:38',0),(14,'9b17e985b36042d1bf755e9d93e24924',12,1,1,2,10,'2018-01-01','2018-01-01 09:00:00','2018-01-01 10:57:53',1),(15,'672c08c2ba154cfab917763bb82c3b1c',12,1,1,2,10,'2018-01-01','2018-01-01 10:00:00','2018-01-01 10:58:08',1),(16,'77fb2e3bc9fa428591a5496996d0f683',12,1,1,2,10,'2018-01-01','2018-01-01 11:00:00','2018-01-01 10:58:11',1),(18,'9f677e09d5854f338fd2c0328c05e14c',12,1,1,2,10,'2018-01-01','2018-01-01 13:00:00','2018-01-01 11:00:37',1),(19,'cbc57a916d3841c69a573c407d8aaa65',6,1,1,1,10,'2018-01-02','2018-01-02 09:00:00','2018-01-01 11:02:37',0),(20,'e6bdfa6fcb324ba8b7b3817a0b2f8cf9',6,1,1,1,10,'2018-01-03','2018-01-03 09:00:00','2018-01-01 12:28:49',0),(21,'f5e87749ab5244e980fedc72d49dae71',10,4,4,5,10,'2018-01-01','2018-01-01 09:00:00','2018-01-01 14:11:21',1),(22,'2aaab71af5dc48d8a2986b16fa71f28e',10,4,4,5,10,'2018-01-01','2018-01-01 10:00:00','2018-01-01 14:11:38',1);

/*Table structure for table `_result` */

DROP TABLE IF EXISTS `_result`;

CREATE TABLE `_result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reg_id` int(11) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='检查结果表';

/*Data for the table `_result` */

insert  into `_result`(`id`,`reg_id`,`content`,`create_time`) values (1,13,'okokok','2018-01-01 13:19:46');

/*Table structure for table `_role` */

DROP TABLE IF EXISTS `_role`;

CREATE TABLE `_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `_role` */

insert  into `_role`(`id`,`name`,`create_time`,`update_time`) values (1,'超级管理员','2017-11-04 10:56:58','2017-11-04 10:57:00'),(2,'医生','2018-02-23 12:18:04','2018-02-23 12:18:04'),(3,'病人','2018-02-23 12:18:10','2018-02-23 12:18:10');

/*Table structure for table `_role_menu` */

DROP TABLE IF EXISTS `_role_menu`;

CREATE TABLE `_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=150 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `_role_menu` */

insert  into `_role_menu`(`id`,`role_id`,`menu_id`) values (132,1,1),(133,1,2),(134,1,3),(135,1,4),(136,1,5),(137,1,12),(138,1,14),(139,1,16),(140,1,18),(141,2,5),(142,2,14),(143,2,15),(144,2,17),(145,2,18),(146,3,5),(147,3,14),(148,3,17),(149,3,18);

/*Table structure for table `_user` */

DROP TABLE IF EXISTS `_user`;

CREATE TABLE `_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `idcard` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `qq_number` varchar(255) DEFAULT NULL,
  `wx` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0',
  `hos_id` int(11) DEFAULT NULL COMMENT '医生，病人所属医院',
  `history` varchar(255) DEFAULT NULL COMMENT '病史',
  `age` int(4) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `hos_ids` varchar(255) DEFAULT NULL COMMENT '历史医院id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `_user` */

insert  into `_user`(`id`,`role_id`,`name`,`password`,`real_name`,`idcard`,`phone`,`email`,`qq_number`,`wx`,`create_time`,`update_time`,`status`,`hos_id`,`history`,`age`,`sex`,`hos_ids`) values (1,1,'admin','21232f297a57a5a743894a0e4a801fc3','ushier.com','','1777','','','','2017-11-04 10:58:18','2018-03-22 18:46:54',0,NULL,'22',NULL,'男',NULL),(4,2,'doctor','f9f16d97c90d8c6f2cab37bb6d1f1992','华佗','22202202020210','18888888888','18888888888@qq.com','18888888','1818888','2018-02-23 12:19:17','2018-03-24 09:33:25',0,3,'33',NULL,'男',NULL),(5,3,'sick','8d7d5ffd0031f2449cbeaef424c22d75','王小马','222022032132','188877777','154333888888@qq.com','188432432','11231244','2018-02-23 12:20:18','2018-03-03 14:54:24',0,1,'44',NULL,'男',NULL),(6,3,'sick2','8d7d5ffd0031f2449cbeaef424c22d75','马大帅','123312312321','1313133','12312@qq.com','','','2018-03-03 15:25:20','2018-03-03 15:25:36',0,3,'55',NULL,'女',NULL),(7,3,'s1','202cb962ac59075b964b07152d234b70','s1','1','1','1','','','2018-03-23 19:22:25','2018-03-23 19:22:25',0,NULL,'1',1,'1','1'),(8,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,'66',NULL,'女',NULL),(9,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,'77',NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
