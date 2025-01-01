-- MySQL dump 10.13  Distrib 8.0.31, for Linux (x86_64)
--
-- Host: localhost    Database: power_mall
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cms_content`
--

DROP TABLE IF EXISTS `cms_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cms_content` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '分组名称',
  `group_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '分组编码',
  `title` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标题',
  `code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '内容编码',
  `sort` int NOT NULL COMMENT '内容排序',
  `img` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '图片',
  `redirect_url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '点击跳转地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `type_code` (`group_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1681196361456914445 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='网站内容表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cms_content`
--

LOCK TABLES `cms_content` WRITE;
/*!40000 ALTER TABLE `cms_content` DISABLE KEYS */;
INSERT INTO `cms_content` VALUES (1,'首页轮播图','INDEX_SWIPER','首页轮播图-1',NULL,4,'http://128.1.132.86:9000/lunbotu/f2a2dd719d344e37a01b0b038aaeb1d4',NULL,'2023-06-12 08:55:14','2024-12-02 17:36:29'),(2,'首页轮播图','INDEX_SWIPER','首页轮播图-2',NULL,3,'http://128.1.132.86:9000/lunbotu/dea5d567a95b411dbb77d76af4303aea',NULL,'2023-06-12 08:55:43','2024-12-02 17:36:34'),(3,'首页轮播图','INDEX_SWIPER','首页轮播图-3',NULL,2,'http://128.1.132.86:9000/lunbotu/3701d4a7d39042e5aa5d75879d4f7b9a',NULL,'2023-06-15 16:32:31','2024-12-02 17:36:56'),(4,'首页轮播图','INDEX_SWIPER','首页轮播图-4',NULL,1,'http://128.1.132.86:9000/lunbotu/8ead79551ecb44849812829a9917911f',NULL,'2023-06-15 16:34:16','2024-12-02 17:37:38'),(5,'首页广告','INDEX_ADVERT','首页广告-1',NULL,1,'http://128.1.132.86:9000/lunbotu/9a1d1783186549eb9dc239d2590e1286',NULL,'2023-06-15 16:40:52','2024-12-02 17:40:47'),(6,'首页广告','INDEX_ADVERT','首页广告-2',NULL,2,'http://128.1.132.86:9000/lunbotu/dc1a5d18a0544c5284a9ce6a259fdd7e',NULL,'2023-06-15 16:55:02','2024-12-02 17:41:06'),(7,'首页导航','INDEX_NAVIGATION','限时秒杀','GOODS_SECKILL',1,NULL,NULL,'2023-06-12 08:55:43','2023-07-18 07:45:16'),(8,'首页导航','INDEX_NAVIGATION','精选推荐','GOODS_RECOMMEND ',2,NULL,NULL,'2023-06-12 08:55:43','2023-06-15 16:55:35'),(1681196361456914436,'首页轮播图','INDEX_SWIPER','测试','',1,'http://128.1.132.86:9000/mall/e20c8e870ab14e0eacd1c411b9c5b2bc','','2024-12-19 15:17:13','2024-12-19 15:17:13'),(1681196361456914437,'首页轮播图','INDEX_SWIPER','测试','',1,'http://128.1.132.86:9000/mall/e20c8e870ab14e0eacd1c411b9c5b2bc','','2024-12-19 15:18:54','2024-12-19 15:18:54'),(1681196361456914438,'首页轮播图','INDEX_SWIPER','测试','',1,'http://128.1.132.86:9000/mall/fdc2eea625e2403dacac59fc38b00992','','2024-12-19 15:20:17','2024-12-19 15:20:17'),(1681196361456914439,'首页轮播图','INDEX_SWIPER','测试','',1,'http://128.1.132.86:9000/mall/fdc2eea625e2403dacac59fc38b00992','','2024-12-19 15:20:27','2024-12-19 15:20:27'),(1681196361456914440,'首页轮播图','INDEX_SWIPER','测试','',1,'http://128.1.132.86:9000/mall/fdc2eea625e2403dacac59fc38b00992','','2024-12-19 15:22:10','2024-12-19 15:22:10'),(1681196361456914441,'首页轮播图','INDEX_SWIPER','测试','',1,'http://128.1.132.86:9000/mall/86ee83b7b33d47638003908894c7f963','','2024-12-19 15:26:29','2024-12-19 15:26:29'),(1681196361456914442,'首页导航','INDEX_NAVIGATION','测试','',1,'http://128.1.132.86:9000/mall/91ecf8de2ee7426ba91e411cf71f7944','','2024-12-19 15:27:18','2024-12-19 15:27:18'),(1681196361456914443,'首页导航','INDEX_NAVIGATION','测试','',1,'http://128.1.132.86:9000/mall/91ecf8de2ee7426ba91e411cf71f7944','','2024-12-19 15:27:51','2024-12-19 15:27:51'),(1681196361456914444,'测试内容-广告','INDEX_ADVERT','测试',NULL,1,'http://128.1.132.86:9000/mall/78d3a7380ba144db87d3a10c406ed680',NULL,'2024-12-29 15:25:22','2024-12-29 15:25:22');
/*!40000 ALTER TABLE `cms_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oms_order`
--

DROP TABLE IF EXISTS `oms_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oms_order` (
  `id` bigint NOT NULL COMMENT 'id',
  `order_sn` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `total_amount` int NOT NULL COMMENT '订单总金额',
  `status` tinyint NOT NULL COMMENT '1-待付款;2-待发货;3-已发货;4-已完成;;5-已取消;',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `pay_type` int NOT NULL COMMENT '付款方式：1-微信，2-支付宝',
  `pay_amount` int DEFAULT NULL COMMENT '应付金额',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime DEFAULT NULL COMMENT '确认收货时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '订单取消时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `create_time` (`create_time`) USING BTREE,
  KEY `status` (`user_id`,`status`) USING BTREE,
  KEY `pay_type` (`pay_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oms_order`
--

LOCK TABLES `oms_order` WRITE;
/*!40000 ALTER TABLE `oms_order` DISABLE KEYS */;
INSERT INTO `oms_order` VALUES (1866483904149766144,'1866483672670322685',1,40000,5,'aliqua','2024-12-10 22:03:36',0,40000,'2024-12-17 19:44:27',NULL,NULL,NULL);
/*!40000 ALTER TABLE `oms_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oms_order_delivery`
--

DROP TABLE IF EXISTS `oms_order_delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oms_order_delivery` (
  `id` bigint NOT NULL COMMENT '主键',
  `order_id` bigint DEFAULT NULL COMMENT '订单ID',
  `receiver_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收货人',
  `receiver_mobile` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `receiver_province` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '省份',
  `receiver_city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '城市',
  `receiver_area` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '区/县',
  `receiver_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '详细收货地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='订单配送信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oms_order_delivery`
--

LOCK TABLES `oms_order_delivery` WRITE;
/*!40000 ALTER TABLE `oms_order_delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `oms_order_delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oms_order_item`
--

DROP TABLE IF EXISTS `oms_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oms_order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint NOT NULL COMMENT '订单id',
  `spu_id` bigint NOT NULL COMMENT '商品ID',
  `sku_id` bigint NOT NULL COMMENT '商品库存id',
  `spu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `sku_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '规格名称',
  `img` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品图片',
  `price` int NOT NULL COMMENT '商品单价',
  `count` int NOT NULL COMMENT '商品购买数量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `product_id` (`sku_id`) USING BTREE,
  KEY `order_id` (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1866483904418201601 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='订单商品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oms_order_item`
--

LOCK TABLES `oms_order_item` WRITE;
/*!40000 ALTER TABLE `oms_order_item` DISABLE KEYS */;
INSERT INTO `oms_order_item` VALUES (1866483904418201600,1866483904149766144,30,118,'测试商品','军绿色 S','http://dummyimage.com/400x400',20000,2,'2024-12-10 22:03:36','2024-12-10 22:03:36');
/*!40000 ALTER TABLE `oms_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_category`
--

DROP TABLE IF EXISTS `pms_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pms_category` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '父ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '分类名称',
  `level` int NOT NULL COMMENT '分类层级 1.一级分类 2.二级分类 3.三级分类',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图标',
  `weight` double NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_pid` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='商品分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_category`
--

LOCK TABLES `pms_category` WRITE;
/*!40000 ALTER TABLE `pms_category` DISABLE KEYS */;
INSERT INTO `pms_category` VALUES (1,0,'服装',1,'https://cdn-we-retail.ym.tencent.com/tsr/classify/img-1.png',1,'2023-06-09 10:52:14','2023-06-14 04:49:00'),(2,1,'女装',2,'https://cdn-we-retail.ym.tencent.com/miniapp/category/category-default.png',1.1,'2023-06-09 05:52:23','2023-06-09 06:48:35'),(3,2,'卫衣',3,'https://cdn-we-retail.ym.tencent.com/tsr/classify/img-1.png',1.11,'2023-06-09 10:52:14','2023-06-09 06:47:25'),(4,2,'毛呢外套',3,'https://cdn-we-retail.ym.tencent.com/tsr/classify/img-2.png',1.12,'2023-06-09 10:52:14','2023-06-09 06:47:40'),(5,2,'雪纺衫',3,'https://cdn-we-retail.ym.tencent.com/tsr/classify/img-3.png',1.13,'2023-06-09 10:52:14','2023-06-09 06:47:42'),(6,2,'羽绒服',3,'https://cdn-we-retail.ym.tencent.com/tsr/classify/img-4.png',1.14,'2023-06-09 10:52:14','2023-06-09 06:47:45'),(7,2,'毛衣',3,'https://cdn-we-retail.ym.tencent.com/tsr/classify/img-5.png',1.15,'2023-06-09 10:52:14','2023-06-09 06:47:49'),(8,2,'棉衣',3,'https://cdn-we-retail.ym.tencent.com/tsr/classify/img-6.png',1.16,'2023-06-09 10:52:14','2023-06-09 06:47:52'),(9,2,'西装',3,'https://cdn-we-retail.ym.tencent.com/tsr/classify/img-7.png',1.17,'2023-06-09 10:52:14','2023-06-09 06:47:54'),(10,2,'马甲',3,'https://cdn-we-retail.ym.tencent.com/tsr/classify/img-8.png',1.18,'2023-06-09 10:52:14','2023-06-09 06:47:57'),(11,2,'连衣裙',3,'https://cdn-we-retail.ym.tencent.com/tsr/classify/img-9.png',1.19,'2023-06-09 10:52:14','2023-06-09 06:48:01'),(12,1,'男装',2,'https://cdn-we-retail.ym.tencent.com/miniapp/category/category-default.png',1.2,'2023-06-09 10:52:14','2023-06-09 06:52:45'),(13,12,'卫衣',3,'https://cdn-we-retail.ym.tencent.com/tsr/classify/img-1.png',1.21,'2023-06-09 10:52:14','2023-06-09 06:52:18'),(14,12,'裤子',3,'https://cdn-we-retail.ym.tencent.com/tsr/classify/img-11.png',1.22,'2023-06-09 10:52:14','2023-06-09 06:52:25'),(15,0,'小家电',1,'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202304071846_12e5bcbffc6e4ed969dbaf2b7cd45d36.png',2,'2023-06-09 06:07:30','2023-06-14 04:49:09'),(16,15,'厨房电器',2,'https://cdn-we-retail.ym.tencent.com/miniapp/category/category-default.png',2.1,'2023-06-09 06:08:42','2023-07-12 03:34:38'),(17,16,'电饭煲',3,'https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1553764670.0369286.jpg',2.11,'2023-06-09 06:09:47','2023-06-09 06:56:13'),(18,16,'空气炸锅',3,'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202304071846_12e5bcbffc6e4ed969dbaf2b7cd45d36.png',2.12,'2023-06-09 06:10:16','2023-06-09 06:57:54'),(19,15,'环境电器',2,'https://cdn-we-retail.ym.tencent.com/miniapp/category/category-default.png',2.2,'2023-06-09 06:12:01','2023-07-12 03:34:41'),(20,19,'加湿器',3,'https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202207251549_b08f2a116971c49853718131d214993e.jpg',2.21,'2023-06-09 06:13:22','2023-06-09 06:59:11'),(21,19,'空气净化器',3,'https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/5f2d5c086ec68c203d22b09af10233cf.jpg',2.22,'2023-06-09 06:13:47','2023-06-09 06:59:13'),(22,0,'水果',1,'http://128.1.132.86:9000/lunbotu/a50cf9b0a9db44238418fbcc6f87a058',3,'2023-06-14 14:52:11','2024-12-02 17:45:12'),(23,0,'蔬菜',1,'http://128.1.132.86:9000/lunbotu/4cd35a0f405d4a95b1a5154f9ccfec00',4,'2023-06-14 15:06:53','2024-12-02 17:45:52'),(24,0,'海鲜',1,'http://128.1.132.86:9000/lunbotu/1a4e75fddecb4226ada612ed779f6e61',5,'2023-06-14 14:53:12','2024-12-02 17:46:34'),(32,0,'一级分类',1,'http://dummyimage.com/100x100',8,'2024-11-29 20:51:06','2024-11-29 20:51:06'),(34,2,'童装',3,'http://localhost',0,'2024-12-19 14:02:39','2024-12-19 14:02:39'),(35,2,'童装',3,'http://localhost',0,'2024-12-19 14:02:47','2024-12-19 14:02:47'),(38,16,'冰箱',3,'http',2.3,'2024-12-19 14:10:20','2024-12-19 14:10:20'),(39,16,'冰箱',3,'http',2.3,'2024-12-19 14:10:21','2024-12-19 14:10:21'),(40,16,'冰箱',3,'http',2.3,'2024-12-19 14:10:22','2024-12-19 14:10:22'),(44,32,'二级分类',2,'http://128.1.132.86:9000/mall/ef549f5366fc47e3820527928f231a30',1,'2024-12-29 15:07:32','2024-12-29 15:07:32'),(45,0,'测试一级分类',1,'http://128.1.132.86:9000/mall/f3757783b1bd4362895f0efa5533609f',0,'2024-12-29 15:08:00','2024-12-29 15:08:00');
/*!40000 ALTER TABLE `pms_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_sku`
--

DROP TABLE IF EXISTS `pms_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pms_sku` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `spu_id` bigint NOT NULL COMMENT 'SPU ID',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `price` int NOT NULL COMMENT '商品价格',
  `stock` int NOT NULL COMMENT '库存数量',
  `spec_value_list` json NOT NULL COMMENT '商品规格值',
  `status` int DEFAULT NULL COMMENT '状态：0.下架 1.上架',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_pms_sku_pms_spu` (`spu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='商品库存表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_sku`
--

LOCK TABLES `pms_sku` WRITE;
/*!40000 ALTER TABLE `pms_sku` DISABLE KEYS */;
INSERT INTO `pms_sku` VALUES (2,1,'白色 M',100,21,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"白色\", \"specValueId\": 1}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"M\", \"specValueId\": 4}]',1,'2023-05-28 11:32:27','2023-07-18 07:57:49'),(3,1,'军绿色 S',20000,20,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"军绿色\", \"specValueId\": 2}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"S\", \"specValueId\": 3}]',1,'2023-05-28 11:32:27','2023-06-19 02:09:45'),(4,1,'军绿色 M',20000,20,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"军绿色\", \"specValueId\": 2}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"M\", \"specValueId\": 4}]',1,'2023-05-28 11:32:27','2023-07-14 02:02:51'),(7,2,'白色 L',9900,40,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"白色\", \"specValueId\": 6}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"L\", \"specValueId\": 7}]',1,'2023-06-01 10:00:00','2023-06-01 10:00:00'),(8,2,'白色 XL',9900,25,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"白色\", \"specValueId\": 6}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"XL\", \"specValueId\": 8}]',1,'2023-06-01 10:00:00','2023-06-01 10:00:00'),(12,3,'深蓝 30',15900,40,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"深蓝\", \"specValueId\": 10}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"30\", \"specValueId\": 12}]',1,'2023-06-02 10:00:00','2023-06-02 10:00:00'),(13,4,'灰色 40',25900,20,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"灰色\", \"specValueId\": 13}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"40\", \"specValueId\": 15}]',1,'2023-06-03 10:00:00','2023-06-03 10:00:00'),(14,4,'灰色 41',25900,15,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"灰色\", \"specValueId\": 13}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"41\", \"specValueId\": 16}]',1,'2023-06-03 10:00:00','2023-06-03 10:00:00'),(15,4,'黑色 40',25900,25,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"黑色\", \"specValueId\": 14}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"40\", \"specValueId\": 15}]',1,'2023-06-03 10:00:00','2023-06-03 10:00:00'),(16,4,'黑色 41',25900,30,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"黑色\", \"specValueId\": 14}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"41\", \"specValueId\": 16}]',1,'2023-06-03 10:00:00','2023-06-03 10:00:00'),(126,4,'灰色 40',25900,20,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"灰色\", \"specValueId\": 13}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"40\", \"specValueId\": 15}]',1,'2023-12-12 10:00:00','2023-12-12 10:00:00'),(127,4,'灰色 41',25900,15,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"灰色\", \"specValueId\": 13}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"41\", \"specValueId\": 16}]',1,'2023-12-12 10:00:00','2023-12-12 10:00:00'),(128,4,'灰色 42',25900,25,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"灰色\", \"specValueId\": 13}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"42\", \"specValueId\": 17}]',1,'2023-12-12 10:00:00','2023-12-12 10:00:00'),(129,4,'灰色 43',25900,20,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"灰色\", \"specValueId\": 13}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"43\", \"specValueId\": 18}]',1,'2023-12-12 10:00:00','2023-12-12 10:00:00'),(130,4,'黑色 40',25900,30,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"黑色\", \"specValueId\": 14}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"40\", \"specValueId\": 15}]',1,'2023-12-12 10:00:00','2023-12-12 10:00:00'),(131,4,'黑色 41',25900,25,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"黑色\", \"specValueId\": 14}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"41\", \"specValueId\": 16}]',1,'2023-12-12 10:00:00','2023-12-12 10:00:00'),(132,4,'黑色 42',25900,20,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"黑色\", \"specValueId\": 14}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"42\", \"specValueId\": 17}]',1,'2023-12-12 10:00:00','2023-12-12 10:00:00'),(133,4,'黑色 43',25900,15,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValue\": \"黑色\", \"specValueId\": 14}, {\"specId\": 2, \"specName\": \"尺码\", \"specValue\": \"43\", \"specValueId\": 18}]',1,'2023-12-12 10:00:00','2023-12-12 10:00:00'),(134,41,'白测',100,1,'[{\"specName\": \"白色\", \"specValue\": \"xl\"}]',1,'2024-12-19 14:43:56','2024-12-19 14:43:56'),(135,42,'白测',100,1,'[{\"specName\": \"白色\", \"specValue\": \"xl\"}]',1,'2024-12-19 14:44:45','2024-12-19 14:44:45'),(136,1,'运动连帽拉链卫衣休闲开衫长袖多色运动细绒面料运动上衣',2000,5,'[{\"specName\": \"面\", \"specValue\": \"大份而不\"}]',1,'2024-12-29 14:33:04','2024-12-29 14:33:04'),(137,1,'面-小份',2000,0,'[{\"specName\": \"面\", \"specValue\": \"小份\"}]',1,'2024-12-29 14:39:43','2024-12-29 14:39:43');
/*!40000 ALTER TABLE `pms_sku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pms_spu`
--

DROP TABLE IF EXISTS `pms_spu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pms_spu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `category_id` bigint NOT NULL COMMENT '商品分类ID',
  `price` int NOT NULL COMMENT '价格',
  `sales` int DEFAULT '0' COMMENT '销量',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品主图',
  `img_list` json NOT NULL COMMENT '商品图集',
  `detail` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '商品详情',
  `status` tinyint DEFAULT '1' COMMENT '商品状态  0:下架 1:上架',
  `spec_list` json DEFAULT NULL COMMENT '商品规格',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_pms_spu_pms_category` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pms_spu`
--

LOCK TABLES `pms_spu` WRITE;
/*!40000 ALTER TABLE `pms_spu` DISABLE KEYS */;
INSERT INTO `pms_spu` VALUES (1,'运动连帽拉链卫衣休闲开衫长袖多色运动细绒面料运动上衣',3,2000,120,'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-17a.png','[\"https://cdn-we-retail.ym.tencent.com/tsr/goods/dz-3a.png\"]','111111111',1,'[{\"name\": \"颜色灰\", \"values\": [], \"inputValue\": \"\", \"inputVisible\": false}]','2023-05-12 14:51:08','2023-07-18 07:57:05'),(2,'简约百搭纯棉T恤',3,9900,200,'https://example.com/tshirt.png','[\"https://example.com/tshirt-1.png\", \"https://example.com/tshirt-2.png\"]','优质棉料，舒适透气',1,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValueList\": [{\"specValue\": \"黑色\", \"specValueId\": 5}, {\"specValue\": \"白色\", \"specValueId\": 6}]}, {\"specId\": 2, \"specName\": \"尺码\", \"specValueList\": [{\"specValue\": \"L\", \"specValueId\": 7}, {\"specValue\": \"XL\", \"specValueId\": 8}]}]','2023-06-01 10:00:00','2023-06-01 10:00:00'),(3,'时尚牛仔裤',4,15900,150,'https://example.com/jeans.png','[\"https://example.com/jeans-1.png\", \"https://example.com/jeans-2.png\"]','修身显瘦，弹力舒适',1,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValueList\": [{\"specValue\": \"浅蓝\", \"specValueId\": 9}, {\"specValue\": \"深蓝\", \"specValueId\": 10}]}, {\"specId\": 2, \"specName\": \"尺码\", \"specValueList\": [{\"specValue\": \"29\", \"specValueId\": 11}, {\"specValue\": \"30\", \"specValueId\": 12}]}]','2023-06-02 10:00:00','2023-06-02 10:00:00'),(4,'休闲运动鞋',5,25900,80,'https://example.com/shoes.png','[\"https://example.com/shoes-1.png\", \"https://example.com/shoes-2.png\"]','轻便透气，减震防滑',1,'[{\"specId\": 1, \"specName\": \"颜色\", \"specValueList\": [{\"specValue\": \"灰色\", \"specValueId\": 13}, {\"specValue\": \"黑色\", \"specValueId\": 14}]}, {\"specId\": 2, \"specName\": \"尺码\", \"specValueList\": [{\"specValue\": \"40\", \"specValueId\": 15}, {\"specValue\": \"41\", \"specValueId\": 16}]}]','2023-06-03 10:00:00','2023-06-03 10:00:00'),(43,'测试商品',32,100,0,'http://128.1.132.86:9000/mall/95069c5cfe544a6f876f7860a75aa24c','[\"http://128.1.132.86:9000/mall/15446c0f555c4246ad3ed5d3ed14f599\"]','测试',1,'[]','2024-12-19 14:50:13','2024-12-19 14:50:13'),(45,'测试添加山沟',45,123400,0,'http://128.1.132.86:9000/mall/c61df8d6873d4514b18caa8a185411c8','[\"http://128.1.132.86:9000/mall/6cad6803d4b847d897a26a206ce5d68c\", \"http://128.1.132.86:9000/mall/53139af6119f4976a8c986787dd4d925\"]','1222222',1,'[]','2024-12-29 15:36:23','2024-12-29 15:36:23');
/*!40000 ALTER TABLE `pms_spu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_admin_user`
--

DROP TABLE IF EXISTS `ums_admin_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ums_admin_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_admin_user`
--

LOCK TABLES `ums_admin_user` WRITE;
/*!40000 ALTER TABLE `ums_admin_user` DISABLE KEYS */;
INSERT INTO `ums_admin_user` VALUES (1,'admin','123456');
/*!40000 ALTER TABLE `ums_admin_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ums_app_user`
--

DROP TABLE IF EXISTS `ums_app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ums_app_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `mobile` varchar(11) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号码',
  `nickname` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '昵称：默认使用手机号',
  `password` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `create_time` timestamp NOT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL COMMENT '更新时间',
  `mail` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ums_app_user`
--

LOCK TABLES `ums_app_user` WRITE;
/*!40000 ALTER TABLE `ums_app_user` DISABLE KEYS */;
INSERT INTO `ums_app_user` VALUES (2,'bob.smith','15517631376','Bob','$2a$10$OTEp63iiwD4VaJWG4WrWQuGjuemPCcsq31WOZfRS.bQNHMR67.aKy','avatar_bob.jpg','2023-09-02 03:30:00','2023-09-02 03:30:00','3995248675@qq.com'),(3,'carol.daniels','13712345680','Carol','098f6bcd4621d373cade4e832627b4f6','avatar_carol.jpg','2023-09-03 01:15:00','2023-09-03 01:15:00',NULL),(4,'david.wilson','13612345681','David','25f9e794323b453885f5181f1b624d0b','avatar_david.jpg','2023-09-04 06:45:00','2023-09-04 06:45:00',NULL),(5,'emily.thomas','13512345682','Emily','d8578edf8458ce06fbc5bb76a58c5ca4','avatar_emily.jpg','2023-09-05 08:20:00','2023-09-05 08:20:00',NULL);
/*!40000 ALTER TABLE `ums_app_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-01 14:55:40
