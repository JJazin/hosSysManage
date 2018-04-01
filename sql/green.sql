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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `_case` */

insert  into `_case`(`id`,`uid`,`did`,`hos_id`,`content`,`create_time`,`sickness_id`,`recipel`,`advice`,`reason`) values (1,5,1,1,'ffff','2018-04-03 14:58:30',1,NULL,'asdf','1231321'),(2,5,1,1,'a','2018-04-03 15:08:04',3,NULL,'sdfsdaf','1231321'),(3,5,4,1,'gggg','2018-04-03 15:14:42',2,NULL,'asdf','1231321'),(4,6,4,3,'qqq','2018-04-03 15:31:11',3,NULL,NULL,'1231321'),(5,5,1,1,'嘎发生的','2018-04-04 20:21:45',2,'感冒灵',NULL,'1231321'),(6,5,4,1,'123','2018-04-24 08:58:03',1,'123',NULL,'1231321'),(7,7,4,1,'111','2018-04-24 09:31:47',3,'11',NULL,'1231321'),(8,6,4,3,'123','2018-04-24 12:08:53',2,'123','','1231321'),(9,6,4,3,'111','2018-04-24 15:55:38',1,'111','','1231321'),(10,6,4,3,'','2018-04-28 21:48:16',1,'','',''),(11,5,1,1,'234','2018-04-01 11:38:59',1,'123','','');

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

insert  into `_hospital`(`id`,`name`,`address`,`tel`,`pic`,`desc`) values (1,'上海人民医院','上海市朝阳区人民大街一号','17777777777','/upload/images/f53466b80d9d400d851a7ad9d2855480.png','绯闻绯闻发生的非瓦房店市分 v 出现在绯闻绯闻发生的非瓦房店市分 v 出现在绯闻绯闻发生的非瓦房店市分 v 出现在绯闻绯闻发生的非瓦房店市分 v 出现在绯闻绯闻发生的非瓦房店市分 v 出现在绯闻绯闻发生的非瓦房店市分 v 出现在绯闻绯闻发生的非瓦房店市分 v 出现在'),(3,'哈哈哈医院','海淀区101号','133323232','/upload/images/4392a1a5992947fca7a97313c8a1ab17.png','什么都治不了，千万别来。'),(4,'无敌医院','西山区2204','17899998887','/upload/images/a3d73fe06fbe4bb6be40f3fd88149386.png','什么都能治，没有治不了的病，就是这么调。'),(5,'123','fdsafsd','12312321','/upload/images/46d391e04a284229ac9acd9545b3bdc4.jpg','ttt'),(6,'人民医院','兴和大陆一号','177665544','/upload/images/e06edc0dfa4348cfbf341e3511b52ce1.jpg','人民医院哈哈哈');

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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `_menu` */

insert  into `_menu`(`id`,`pid`,`title`,`href`,`icon`,`status`,`weight`,`create_time`,`update_time`) values (1,0,'系统管理','#','glyphicon glyphicon-th',0,1,'2017-11-04 11:00:59','2018-03-27 19:32:09'),(2,1,'菜单管理','system/menu.html','glyphicon glyphicon-list',0,2,'2017-11-04 11:01:11','2017-11-04 11:18:55'),(3,1,'用户管理','system/user.html','glyphicon glyphicon-user',0,3,'2017-11-04 11:04:02','2017-11-04 11:19:04'),(4,1,'权限管理','system/role.html','glyphicon glyphicon-lock',0,4,'2017-11-04 11:04:26','2017-12-30 10:01:04'),(5,0,'内容管理','#','glyphicon glyphicon-th',0,5,'2017-11-04 11:43:21','2017-11-04 11:43:21'),(12,5,'医院管理','content/hospital/index.html','glyphicon glyphicon-record',0,6,'2017-12-29 14:35:23','2017-12-29 19:54:43'),(14,5,'病例管理','content/case/index.html','glyphicon glyphicon-asterisk',0,7,'2018-03-03 14:31:14','2018-03-03 20:50:06'),(15,5,'病人管理','content/illness_d/index.html','glyphicon glyphicon-glass',0,8,'2018-03-03 15:23:59','2018-03-03 20:50:37'),(16,5,'公告管理','content/notice/index.html','glyphicon glyphicon-star',0,9,'2018-03-03 20:39:29','2018-03-03 20:50:49'),(17,5,'公告查看','content/notice_read/index.html','glyphicon glyphicon-star-empty',0,10,'2018-03-03 20:52:04','2018-03-03 20:52:04'),(18,5,'本季度患病统计','content/season_sickness/index.html','glyphicon glyphicon-pencil',0,11,'2018-03-04 20:47:39','2018-03-04 20:48:04'),(19,5,'诊断结果管理','content/sickness/index.html','glyphicon glyphicon-lock',0,6,'2018-03-27 19:35:01','2018-04-01 11:44:25');

/*Table structure for table `_notice` */

DROP TABLE IF EXISTS `_notice`;

CREATE TABLE `_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `_notice` */

insert  into `_notice`(`id`,`title`,`content`,`create_time`) values (1,'这是一个公告1','哈哈哈2212','2018-03-03 20:39:55'),(2,'asdf','asdf','2018-04-01 11:39:39');

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

insert  into `_role`(`id`,`name`,`create_time`,`update_time`) values (1,'超级管理员','2017-11-04 10:56:58','2018-04-01 11:43:30'),(2,'医生','2018-02-23 12:18:04','2018-03-27 19:35:27'),(3,'病人','2018-02-23 12:18:10','2018-02-23 12:18:10');

/*Table structure for table `_role_menu` */

DROP TABLE IF EXISTS `_role_menu`;

CREATE TABLE `_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=156 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `_role_menu` */

insert  into `_role_menu`(`id`,`role_id`,`menu_id`) values (132,1,1),(133,1,2),(134,1,3),(135,1,4),(136,1,5),(137,1,12),(138,1,14),(139,1,16),(140,1,18),(146,3,5),(147,3,14),(148,3,17),(149,3,18),(150,2,5),(151,2,14),(152,2,15),(153,2,17),(154,2,18),(155,2,19);

/*Table structure for table `_sick` */

DROP TABLE IF EXISTS `_sick`;

CREATE TABLE `_sick` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '诊断结果',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `_sick` */

insert  into `_sick`(`id`,`name`) values (1,'感冒'),(2,'痛风'),(3,'肺炎'),(4,'麻疹123'),(5,'头痛'),(6,'扁桃体炎'),(7,'啦啦啦');

/*Table structure for table `_user` */

DROP TABLE IF EXISTS `_user`;

CREATE TABLE `_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `_user` */

insert  into `_user`(`id`,`role_id`,`name`,`password`,`real_name`,`idcard`,`phone`,`email`,`qq_number`,`wx`,`create_time`,`update_time`,`status`,`hos_id`,`history`,`age`,`sex`,`hos_ids`) values (1,1,'admin','21232f297a57a5a743894a0e4a801fc3','ushier.com','','1777','','','','2017-11-04 10:58:18','2018-04-01 11:38:59',0,NULL,'22',NULL,'男',NULL),(4,2,'doctor','f9f16d97c90d8c6f2cab37bb6d1f1992','华佗','22202202020210','18888888888','18888888888@qq.com','18888888','1818888','2018-02-23 12:19:17','2018-04-01 11:39:06',0,1,'33',NULL,'男',NULL),(5,3,'sick','8d7d5ffd0031f2449cbeaef424c22d75','王小马','222022032132','188877777','154333888888@qq.com','188432432','11231244','2018-02-23 12:20:18','2018-03-03 14:54:24',0,1,'44',NULL,'男',NULL),(6,3,'sick2','8d7d5ffd0031f2449cbeaef424c22d75','马大帅','123312312321','1313133','12312@qq.com','','','2018-03-03 15:25:20','2018-03-03 15:25:36',0,3,'55',NULL,'女',NULL),(7,3,'s1','202cb962ac59075b964b07152d234b70','s1','1','1','1','','','2018-03-23 19:22:25','2018-03-23 19:22:25',0,NULL,'1',1,'1','1'),(10,1,'green','9f27410725ab8cc8854a2769c7a516b8','123','123','123','123','','','2018-04-01 11:13:32','2018-04-01 11:13:32',0,NULL,'',NULL,'',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
