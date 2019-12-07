/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.27-log : Database - api-ctl
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`api-ctl` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `api-ctl`;

/*Table structure for table `api_cus_good` */

DROP TABLE IF EXISTS `api_cus_good`;

CREATE TABLE `api_cus_good` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `cus_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `good_id` varchar(255) DEFAULT NULL COMMENT '接口编号',
  `good_name` varchar(255) DEFAULT NULL COMMENT '接口名称',
  `good_type` int(1) DEFAULT NULL COMMENT '获取方式(1.免费，2.试用，3.包年，4.包月)',
  `app_key` varchar(255) DEFAULT NULL COMMENT 'app_key',
  `app_uri` varchar(255) DEFAULT NULL COMMENT 'uri',
  `create_time` bigint(13) unsigned DEFAULT NULL COMMENT '申请时间',
  `api_numbers` int(10) DEFAULT NULL COMMENT '接口剩余次数',
  `api_starttime` bigint(13) DEFAULT NULL COMMENT '接口开始计费时间',
  `api_endtime` bigint(13) DEFAULT NULL COMMENT '接口结束计费时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='用户接口信息表';

/*Data for the table `api_cus_good` */

insert  into `api_cus_good`(`id`,`cus_id`,`good_id`,`good_name`,`good_type`,`app_key`,`app_uri`,`create_time`,`api_numbers`,`api_starttime`,`api_endtime`) values (1,1,'1','身份证查询',5,'625dee78995b6ca02c53f0d4ca839850','/api/idcard',1575684129243,0,1575129600000,1575907200000);

/*Table structure for table `api_customer_account` */

DROP TABLE IF EXISTS `api_customer_account`;

CREATE TABLE `api_customer_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `cus_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `monery` int(255) DEFAULT NULL COMMENT '余额,单位分',
  `freeze_monery` int(255) DEFAULT NULL COMMENT '冻结资金，单位分',
  `status` varchar(255) DEFAULT NULL COMMENT '余额预警',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='账户余额';

/*Data for the table `api_customer_account` */

insert  into `api_customer_account`(`id`,`cus_id`,`monery`,`freeze_monery`,`status`) values (1,1,100,0,'1');

/*Table structure for table `api_dic_chargemode` */

DROP TABLE IF EXISTS `api_dic_chargemode`;

CREATE TABLE `api_dic_chargemode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='收费方式';

/*Data for the table `api_dic_chargemode` */

insert  into `api_dic_chargemode`(`id`,`name`,`desc`) values (0,'按次收费','按次收费'),(1,'试用','试用'),(2,'免费','免费'),(3,'包年','包年'),(4,'包月','包月'),(5,'自定义时间','自定义时间');

/*Table structure for table `api_good_info` */

DROP TABLE IF EXISTS `api_good_info`;

CREATE TABLE `api_good_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `good_id` int(11) DEFAULT NULL COMMENT '商品编号',
  `chargemode_id` int(11) DEFAULT NULL COMMENT '使用方式',
  `moery` varchar(255) DEFAULT NULL COMMENT '价格',
  `desc` varchar(255) DEFAULT NULL COMMENT '付费说明',
  `numbers` int(11) DEFAULT NULL COMMENT '使用次数限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='接口价格规格信息';

/*Data for the table `api_good_info` */

insert  into `api_good_info`(`id`,`good_id`,`chargemode_id`,`moery`,`desc`,`numbers`) values (1,1,1,'0','试用',10),(2,1,2,'0','免费',-1),(3,1,3,'100000','包年',-1),(4,1,4,'20000','包月',-1),(5,1,5,'','自定义时间',-1);

/*Table structure for table `api_sys_customer` */

DROP TABLE IF EXISTS `api_sys_customer`;

CREATE TABLE `api_sys_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `type` varchar(255) DEFAULT NULL COMMENT '用户类型(个人,企业)',
  `status` varchar(255) DEFAULT NULL COMMENT '认证状态(未认证,已认证)',
  `email` varchar(255) DEFAULT NULL COMMENT '绑定邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `api_sys_customer` */

insert  into `api_sys_customer`(`id`,`name`,`phone`,`type`,`status`,`email`) values (1,'张三','15555555555','2','2','155@qq.com');

/*Table structure for table `api_sys_echarge_log` */

DROP TABLE IF EXISTS `api_sys_echarge_log`;

CREATE TABLE `api_sys_echarge_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `cus_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `order_id` int(11) DEFAULT NULL COMMENT '订单',
  `monery` varchar(255) DEFAULT NULL COMMENT '金额',
  `good_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `create_time` bigint(13) DEFAULT NULL COMMENT '购买时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='充值明细';

/*Data for the table `api_sys_echarge_log` */

insert  into `api_sys_echarge_log`(`id`,`cus_id`,`order_id`,`monery`,`good_name`,`create_time`) values (1,1,19174301,'10000','身份证实名认证',20191206174349);

/*Table structure for table `api_sys_goods` */

DROP TABLE IF EXISTS `api_sys_goods`;

CREATE TABLE `api_sys_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `api_uri` varchar(255) DEFAULT NULL,
  `return_value_type` varchar(255) DEFAULT NULL,
  `request_way` varchar(255) DEFAULT NULL,
  `request_case` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `request_parms_explain` text,
  `return_value_explain` text,
  `return_value_example` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `api_sys_goods` */

insert  into `api_sys_goods`(`id`,`name`,`desc`,`address`,`api_uri`,`return_value_type`,`request_way`,`request_case`,`remark`,`request_parms_explain`,`return_value_explain`,`return_value_example`) values (1,'身份证查询','身份证归属地信息查询','http://apis.juhe.cn/idcard/index','/api/idcard','json','get','http://apis.juhe.cn/idcard/index?key=您申请的KEY&cardno=330326198903081211','身份证归属地信息查询','[{\r\n	\"name\": \"\",\r\n	\"required\": \"Y\",\r\n	\"type\": \"String\",\r\n	\"desc\": \"经度 (如：119.9772857)\"\r\n}, {\r\n	\"name\": \"\",\r\n	\"required\": \"Y\",\r\n	\"type\": \"String\",\r\n	\"desc\": \"经度 (如：119.9772857)\"\r\n}, {\r\n	\"name\": \"\",\r\n	\"required\": \"Y\",\r\n	\"type\": \"String\",\r\n	\"desc\": \"经度 (如：119.9772857)\"\r\n}, {\r\n	\"name\": \"\",\r\n	\"required\": \"Y\",\r\n	\"type\": \"String\",\r\n	\"desc\": \"经度 (如：119.9772857)\"\r\n}]',NULL,'{\r\n	\"resultcode\":\"200\",\r\n	\"reason\":\"Successed!\",\r\n	\"result\":{\r\n		\"lat\":\"39.915065193348\",\r\n		\"lng\":\"116.40389843345\",\r\n		\"type\":\"1\",\r\n		\"address\":\"北京市东城区中华路甲10号\",\r\n		\"business\":\"天安门\",\r\n		\"citycode\":131\r\n	}\r\n}');

/*Table structure for table `api_sys_logs` */

DROP TABLE IF EXISTS `api_sys_logs`;

CREATE TABLE `api_sys_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_key` varchar(255) DEFAULT NULL,
  `params` varchar(255) DEFAULT NULL,
  `returnValue` varchar(255) DEFAULT NULL,
  `callTime` bigint(13) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

/*Data for the table `api_sys_logs` */

insert  into `api_sys_logs`(`id`,`app_key`,`params`,`returnValue`,`callTime`) values (1,'625dee78995b6ca02c53f0d4ca839850','{\"cardno\":\"330326198903081211\",\"key\":\"625dee78995b6ca02c53f0d4ca839850\"}','{\"result\":{\"age\":30,\"birth\":\"19890308\",\"province\":\"浙江\",\"sex\":\"男\"},\"reason\":\"成功的返回\",\"resultcode\":200}',1575696593074),(2,'625dee78995b6ca02c53f0d4ca839850','{\"cardno\":\"330326198903081211\",\"key\":\"625dee78995b6ca02c53f0d4ca839850\"}','{\"result\":{\"age\":30,\"birth\":\"19890308\",\"province\":\"浙江\",\"sex\":\"男\"},\"reason\":\"成功的返回\",\"resultcode\":200}',1575696593785),(3,'625dee78995b6ca02c53f0d4ca839850','{\"cardno\":\"330326198903081211\",\"key\":\"625dee78995b6ca02c53f0d4ca839850\"}','{\"result\":{\"age\":30,\"birth\":\"19890308\",\"province\":\"浙江\",\"sex\":\"男\"},\"reason\":\"成功的返回\",\"resultcode\":200}',1575696594805),(4,'625dee78995b6ca02c53f0d4ca839850','{\"cardno\":\"330326198903081211\",\"key\":\"625dee78995b6ca02c53f0d4ca839850\"}','{\"result\":{\"age\":30,\"birth\":\"19890308\",\"province\":\"浙江\",\"sex\":\"男\"},\"reason\":\"成功的返回\",\"resultcode\":200}',1575696596183),(5,'625dee78995b6ca02c53f0d4ca839850','{\"cardno\":\"330326198903081211\",\"key\":\"625dee78995b6ca02c53f0d4ca839850\"}','{\"result\":{\"age\":30,\"birth\":\"19890308\",\"province\":\"浙江\",\"sex\":\"男\"},\"reason\":\"成功的返回\",\"resultcode\":200}',1575696598823),(6,'625dee78995b6ca02c53f0d4ca839850','{\"cardno\":\"330326198903081211\",\"key\":\"625dee78995b6ca02c53f0d4ca839850\"}','{\"result\":{\"age\":30,\"birth\":\"19890308\",\"province\":\"浙江\",\"sex\":\"男\"},\"reason\":\"成功的返回\",\"resultcode\":200}',1575696663271),(7,'625dee78995b6ca02c53f0d4ca839850','{\"cardno\":\"330326198903081211\",\"key\":\"625dee78995b6ca02c53f0d4ca839850\"}','{\"result\":{\"age\":30,\"birth\":\"19890308\",\"province\":\"浙江\",\"sex\":\"男\"},\"reason\":\"成功的返回\",\"resultcode\":200}',1575696664072),(8,'625dee78995b6ca02c53f0d4ca839850','{\"cardno\":\"330326198903081211\",\"key\":\"625dee78995b6ca02c53f0d4ca839850\"}','{\"result\":{\"age\":30,\"birth\":\"19890308\",\"province\":\"浙江\",\"sex\":\"男\"},\"reason\":\"成功的返回\",\"resultcode\":200}',1575696664937),(9,'625dee78995b6ca02c53f0d4ca839850','{\"cardno\":\"330326198903081211\",\"key\":\"625dee78995b6ca02c53f0d4ca839850\"}','{\"result\":{\"age\":30,\"birth\":\"19890308\",\"province\":\"浙江\",\"sex\":\"男\"},\"reason\":\"成功的返回\",\"resultcode\":200}',1575696665812),(10,'625dee78995b6ca02c53f0d4ca839850','{\"cardno\":\"330326198903081211\",\"key\":\"625dee78995b6ca02c53f0d4ca839850\"}','{\"result\":{\"age\":30,\"birth\":\"19890308\",\"province\":\"浙江\",\"sex\":\"男\"},\"reason\":\"成功的返回\",\"resultcode\":200}',1575696666718);

/*Table structure for table `api_sys_order` */

DROP TABLE IF EXISTS `api_sys_order`;

CREATE TABLE `api_sys_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL COMMENT '订单编号',
  `good_id` varchar(255) DEFAULT NULL COMMENT '接口编号',
  `good_name` varchar(255) DEFAULT NULL COMMENT '接口名称',
  `money` varchar(255) DEFAULT NULL COMMENT '金额',
  `status` varchar(255) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单信息';

/*Data for the table `api_sys_order` */

/*Table structure for table `api_sys_qps` */

DROP TABLE IF EXISTS `api_sys_qps`;

CREATE TABLE `api_sys_qps` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `app_key` varchar(255) DEFAULT NULL COMMENT 'app_key',
  `sec` varchar(255) DEFAULT NULL COMMENT '请求次数的指定时间范围',
  `request_time` varchar(255) DEFAULT NULL COMMENT '指定second 时间内 API请求次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='接口白名单信息记录表';

/*Data for the table `api_sys_qps` */

insert  into `api_sys_qps`(`id`,`app_key`,`sec`,`request_time`) values (1,'625dee78995b6ca02c53f0d4ca839850','60','5');

/*Table structure for table `api_sys_whiteip` */

DROP TABLE IF EXISTS `api_sys_whiteip`;

CREATE TABLE `api_sys_whiteip` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `app_key` varchar(255) DEFAULT NULL COMMENT 'app_key',
  `w_ip` varchar(255) DEFAULT NULL COMMENT '白名单ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='接口白名单信息记录表';

/*Data for the table `api_sys_whiteip` */

insert  into `api_sys_whiteip`(`id`,`app_key`,`w_ip`) values (1,'625dee78995b6ca02c53f0d4ca839850','127.0.0.2,127.0.0.4');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
