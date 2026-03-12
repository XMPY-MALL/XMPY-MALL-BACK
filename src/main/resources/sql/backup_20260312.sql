-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: xmpy
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `color_tb`
--

DROP TABLE IF EXISTS `color_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `color_tb` (
  `color_id` bigint NOT NULL AUTO_INCREMENT,
  `color_name` varchar(30) NOT NULL,
  PRIMARY KEY (`color_id`),
  UNIQUE KEY `color_name` (`color_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color_tb`
--

LOCK TABLES `color_tb` WRITE;
/*!40000 ALTER TABLE `color_tb` DISABLE KEYS */;
INSERT INTO `color_tb` VALUES (3,'네이비'),(2,'베이지'),(4,'블랙'),(1,'화이트');
/*!40000 ALTER TABLE `color_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inquiry_tb`
--

DROP TABLE IF EXISTS `inquiry_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inquiry_tb` (
  `inquiry_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `status` varchar(20) NOT NULL,
  `answer_content` text,
  `created_at` datetime NOT NULL,
  `answered_at` datetime DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`inquiry_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inquiry_tb`
--

LOCK TABLES `inquiry_tb` WRITE;
/*!40000 ALTER TABLE `inquiry_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `inquiry_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice_tb`
--

DROP TABLE IF EXISTS `notice_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice_tb` (
  `notice_id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice_tb`
--

LOCK TABLES `notice_tb` WRITE;
/*!40000 ALTER TABLE `notice_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item_tb`
--

DROP TABLE IF EXISTS `order_item_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item_tb` (
  `order_item_id` bigint NOT NULL AUTO_INCREMENT,
  `orders_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `size_id` bigint NOT NULL,
  `color_id` bigint NOT NULL,
  `price` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item_tb`
--

LOCK TABLES `order_item_tb` WRITE;
/*!40000 ALTER TABLE `order_item_tb` DISABLE KEYS */;
INSERT INTO `order_item_tb` VALUES (1,1,1,1,1,10000,1),(2,2,1,1,1,10000,1),(3,3,1,1,1,10000,1),(4,4,1,1,1,10000,1),(5,5,1,1,1,10000,1),(6,6,1,1,1,10000,2),(7,7,1,1,1,10000,3);
/*!40000 ALTER TABLE `order_item_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_tb`
--

DROP TABLE IF EXISTS `orders_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_tb` (
  `orders_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `status` varchar(20) NOT NULL,
  `total_price` int NOT NULL,
  `address` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`orders_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_tb`
--

LOCK TABLES `orders_tb` WRITE;
/*!40000 ALTER TABLE `orders_tb` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category_detail_tb`
--

DROP TABLE IF EXISTS `product_category_detail_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category_detail_tb` (
  `category_detail_id` bigint NOT NULL AUTO_INCREMENT,
  `category_id` bigint NOT NULL,
  `category_detail_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_detail_id`),
  KEY `fk_category_detail_category` (`category_id`),
  CONSTRAINT `fk_category_detail_category` FOREIGN KEY (`category_id`) REFERENCES `product_category_tb` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category_detail_tb`
--

LOCK TABLES `product_category_detail_tb` WRITE;
/*!40000 ALTER TABLE `product_category_detail_tb` DISABLE KEYS */;
INSERT INTO `product_category_detail_tb` VALUES (1,1,'패딩'),(2,1,'코트'),(3,1,'자켓'),(4,2,'맨투맨'),(5,2,'후드'),(6,2,'티셔츠'),(7,3,'반바지'),(8,3,'청바지'),(9,3,'슬랙스');
/*!40000 ALTER TABLE `product_category_detail_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category_tb`
--

DROP TABLE IF EXISTS `product_category_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category_tb` (
  `category_id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category_tb`
--

LOCK TABLES `product_category_tb` WRITE;
/*!40000 ALTER TABLE `product_category_tb` DISABLE KEYS */;
INSERT INTO `product_category_tb` VALUES (1,'아우터'),(2,'상의'),(3,'하의');
/*!40000 ALTER TABLE `product_category_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_detail_tb`
--

DROP TABLE IF EXISTS `product_detail_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_detail_tb` (
  `product_detail_id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `stock_id` bigint DEFAULT NULL,
  `product_detail_content` text,
  PRIMARY KEY (`product_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_detail_tb`
--

LOCK TABLES `product_detail_tb` WRITE;
/*!40000 ALTER TABLE `product_detail_tb` DISABLE KEYS */;
INSERT INTO `product_detail_tb` VALUES (1,2,0,'<p><img src=\"https://ronzuujrjhptjwjfduho.supabase.co/storage/v1/object/public/elikese/77acb1e0-3c0b-4588-a393-b13088489397.webp\"></p><p></p><p>쌰갈&nbsp;테스트</p>');
/*!40000 ALTER TABLE `product_detail_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `product_sub_menu_view`
--

DROP TABLE IF EXISTS `product_sub_menu_view`;
/*!50001 DROP VIEW IF EXISTS `product_sub_menu_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `product_sub_menu_view` AS SELECT 
 1 AS `product_id`,
 1 AS `product_name`,
 1 AS `price`,
 1 AS `is_best`,
 1 AS `category_detail_id`,
 1 AS `category_detail_name`,
 1 AS `thumbnail_url`,
 1 AS `is_sold_out`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `product_tb`
--

DROP TABLE IF EXISTS `product_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_tb` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) NOT NULL,
  `description` text,
  `is_best` tinyint(1) NOT NULL,
  `price` int NOT NULL,
  `quick_rundown` varchar(255) DEFAULT NULL,
  `category_id` bigint NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `category_detail_id` bigint NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_tb`
--

LOCK TABLES `product_tb` WRITE;
/*!40000 ALTER TABLE `product_tb` DISABLE KEYS */;
INSERT INTO `product_tb` VALUES (2,'쌰걀자켓','쌰갈합니다',1,59000,NULL,1,'2026-03-11 19:38:29','2026-03-11 19:38:29',2),(3,'프리미엄 구스다운 패딩','따뜻하고 가벼운 구스다운 충전재 사용',1,189000,'겨울 필수 아이템',1,'2026-03-12 20:08:47','2026-03-12 20:08:47',1),(4,'숏 패딩 점퍼','활동성 좋은 숏 기장 패딩',1,129000,'캐주얼 데일리 패딩',1,'2026-03-12 20:08:47','2026-03-12 20:08:47',1),(5,'롱 패딩 코트','무릎까지 오는 따뜻한 롱 패딩',1,159000,'보온성 극대화 롱패딩',1,'2026-03-12 20:08:47','2026-03-12 20:08:47',1),(6,'울 혼방 오버핏 코트','클래식한 울 혼방 소재 코트',1,219000,'시즌리스 베이직 코트',1,'2026-03-12 20:08:47','2026-03-12 20:08:47',2),(7,'더블 브레스티드 코트','정장 느낌의 더블버튼 코트',0,249000,'포멀 무드 코트',1,'2026-03-12 20:08:47','2026-03-12 20:08:47',2),(8,'캐시미어 체크 코트','고급스러운 캐시미어 소재',0,289000,'럭셔리 체크 패턴 코트',1,'2026-03-12 20:08:47','2026-03-12 20:08:47',2),(9,'라이너 탈부착 코트','라이너 분리 가능 사계절 코트',0,199000,'실용적인 멀티 코트',1,'2026-03-12 20:08:47','2026-03-12 20:08:47',2),(10,'오버핏 테일러드 자켓','트렌디한 오버핏 실루엣 자켓',0,139000,'캐주얼 자켓',1,'2026-03-12 20:08:47','2026-03-12 20:08:47',3),(11,'린넨 블레이저','시원한 린넨 소재 봄가을 자켓',0,119000,'봄가을 필수 자켓',1,'2026-03-12 20:08:47','2026-03-12 20:08:47',3),(12,'데님 자켓','클래식한 데님 소재 자켓',0,89000,'데일리 데님 자켓',1,'2026-03-12 20:08:47','2026-03-12 20:08:47',3),(13,'베이직 크루넥 맨투맨','부드러운 기모 안감 맨투맨',1,59000,'데일리 베이직 맨투맨',2,'2026-03-12 20:08:47','2026-03-12 20:08:47',4),(14,'오버핏 프린팅 맨투맨','앞면 빅 프린팅 포인트 맨투맨',1,69000,'스트릿 무드 맨투맨',2,'2026-03-12 20:08:47','2026-03-12 20:08:47',4),(15,'반폴라 맨투맨','목을 감싸주는 반폴라 디자인',1,65000,'따뜻한 반폴라 맨투맨',2,'2026-03-12 20:08:47','2026-03-12 20:08:47',4),(16,'컬러블록 맨투맨','두 가지 색상 조합 컬러블록 디자인',1,72000,'감각적인 컬러블록',2,'2026-03-12 20:08:47','2026-03-12 20:08:47',4),(17,'집업 후드 집업','지퍼 개폐 편리한 집업 후드',0,79000,'활동성 좋은 집업 후드',2,'2026-03-12 20:08:47','2026-03-12 20:08:47',5),(18,'오버핏 기모 후드티','겨울용 두꺼운 기모 후드',0,85000,'따뜻한 기모 후드',2,'2026-03-12 20:08:47','2026-03-12 20:08:47',5),(19,'크롭 후드티','짧은 기장의 트렌디한 크롭 후드',0,62000,'크롭 실루엣 후드',2,'2026-03-12 20:08:47','2026-03-12 20:08:47',5),(20,'코튼 슬림핏 반팔티','고급 면 소재 슬림핏 반팔티',0,29000,'베이직 반팔티',2,'2026-03-12 20:08:47','2026-03-12 20:08:47',6),(21,'스트라이프 반팔티','세로 줄무늬 캐주얼 반팔티',0,35000,'스트라이프 패턴 티',2,'2026-03-12 20:08:47','2026-03-12 20:08:47',6),(22,'포켓 포인트 롱슬리브','가슴 포켓 포인트 긴팔 티셔츠',0,39000,'포켓 디테일 롱슬리브',2,'2026-03-12 20:08:47','2026-03-12 20:08:47',6),(23,'코튼 와이드 반바지','편안한 와이드 핏 코튼 반바지',1,45000,'여름 데일리 반바지',3,'2026-03-12 20:08:47','2026-03-12 20:08:47',7),(24,'린넨 하프팬츠','통기성 좋은 린넨 소재 하프팬츠',1,49000,'시원한 린넨 반바지',3,'2026-03-12 20:08:47','2026-03-12 20:08:47',7),(25,'카고 반바지','포켓 많은 실용적인 카고 반바지',1,55000,'유틸리티 카고 반바지',3,'2026-03-12 20:08:47','2026-03-12 20:08:47',7),(26,'슬림 스트레이트 청바지','적당한 핏의 스트레이트 데님',1,79000,'베이직 스트레이트 진',3,'2026-03-12 20:08:47','2026-03-12 20:08:47',8),(27,'와이드 데님 팬츠','트렌디한 와이드 핏 청바지',0,89000,'루즈핏 와이드 진',3,'2026-03-12 20:08:47','2026-03-12 20:08:47',8),(28,'스키니 스트레치 진','신축성 좋은 스키니 청바지',0,75000,'스키니 스트레치 진',3,'2026-03-12 20:08:47','2026-03-12 20:08:47',8),(29,'배기 데님 팬츠','힙합 무드의 루즈한 배기 진',0,85000,'스트릿 배기 진',3,'2026-03-12 20:08:47','2026-03-12 20:08:47',8),(30,'울 혼방 슬랙스','고급스러운 울 혼방 드레스 팬츠',0,99000,'포멀 드레스 슬랙스',3,'2026-03-12 20:08:47','2026-03-12 20:08:47',9),(31,'린넨 와이드 슬랙스','봄여름 린넨 소재 와이드 슬랙스',0,69000,'캐주얼 린넨 슬랙스',3,'2026-03-12 20:08:47','2026-03-12 20:08:47',9),(32,'테이퍼드 슬랙스','아래로 갈수록 좁아지는 테이퍼드 핏',0,89000,'세미 포멀 테이퍼드 슬랙스',3,'2026-03-12 20:08:47','2026-03-12 20:08:47',9);
/*!40000 ALTER TABLE `product_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_thumbnail_tb`
--

DROP TABLE IF EXISTS `product_thumbnail_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_thumbnail_tb` (
  `product_thumnail_id` int NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `product_url` varchar(500) NOT NULL,
  PRIMARY KEY (`product_thumnail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_thumbnail_tb`
--

LOCK TABLES `product_thumbnail_tb` WRITE;
/*!40000 ALTER TABLE `product_thumbnail_tb` DISABLE KEYS */;
INSERT INTO `product_thumbnail_tb` VALUES (1,2,'https://ronzuujrjhptjwjfduho.supabase.co/storage/v1/object/public/elikese/products/59dbf3bd-d06c-43b1-8dca-2d716b978410.jpg'),(2,2,'https://ronzuujrjhptjwjfduho.supabase.co/storage/v1/object/public/elikese/products/82f69148-ea64-47d4-9ffe-4d5103e7efa9.jpg'),(3,2,'https://ronzuujrjhptjwjfduho.supabase.co/storage/v1/object/public/elikese/products/8eb72c6f-0fd0-4df9-92a0-d6a74be023c1.webp'),(4,3,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(5,4,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(6,5,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(7,6,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(8,7,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(9,8,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(10,9,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(11,10,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(12,11,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(13,12,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(14,13,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(15,14,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(16,15,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(17,16,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(18,17,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(19,18,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(20,19,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(21,20,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(22,21,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(23,22,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(24,23,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(25,24,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(26,25,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(27,26,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(28,27,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(29,28,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(30,29,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(31,30,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(32,31,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp'),(33,32,'https://lookple.com/web/product/big/202509/0cdaa90f6858fd328701ed99c8f03baa.webp');
/*!40000 ALTER TABLE `product_thumbnail_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review_tb`
--

DROP TABLE IF EXISTS `review_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review_tb` (
  `review_id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `order_item_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `content` text NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE KEY `order_item_id` (`order_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review_tb`
--

LOCK TABLES `review_tb` WRITE;
/*!40000 ALTER TABLE `review_tb` DISABLE KEYS */;
INSERT INTO `review_tb` VALUES (1,1,1,1,'너무 좋아요!','2026-03-10 20:09:54'),(2,1,2,2,'배송도 빠르고 품질도 좋습니다.','2026-03-10 20:09:54'),(3,1,3,3,'색상이 사진이랑 똑같아요.','2026-03-10 20:09:54'),(4,1,4,4,'재구매 의사 있습니다.','2026-03-10 20:09:54'),(5,1,5,5,'사이즈가 생각보다 크네요.','2026-03-10 20:09:54'),(6,1,6,1,'가성비 최고입니다.','2026-03-10 20:09:54'),(7,1,7,2,'선물용으로 샀는데 만족해요.','2026-03-10 20:09:54');
/*!40000 ALTER TABLE `review_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_tb`
--

DROP TABLE IF EXISTS `role_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_tb` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_tb`
--

LOCK TABLES `role_tb` WRITE;
/*!40000 ALTER TABLE `role_tb` DISABLE KEYS */;
INSERT INTO `role_tb` VALUES (1,'USER'),(2,'ADMIN');
/*!40000 ALTER TABLE `role_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size_tb`
--

DROP TABLE IF EXISTS `size_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size_tb` (
  `size_id` bigint NOT NULL AUTO_INCREMENT,
  `size_name` varchar(30) NOT NULL,
  PRIMARY KEY (`size_id`),
  UNIQUE KEY `size_name` (`size_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size_tb`
--

LOCK TABLES `size_tb` WRITE;
/*!40000 ALTER TABLE `size_tb` DISABLE KEYS */;
INSERT INTO `size_tb` VALUES (3,'L'),(2,'M'),(1,'S'),(4,'XL');
/*!40000 ALTER TABLE `size_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_tb`
--

DROP TABLE IF EXISTS `stock_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_tb` (
  `stock_id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `size_id` bigint NOT NULL,
  `color_id` bigint NOT NULL,
  `count` int NOT NULL,
  `updated_at` datetime NOT NULL,
  `is_sold_out` tinyint NOT NULL,
  PRIMARY KEY (`stock_id`),
  UNIQUE KEY `uk_product_option` (`product_id`,`size_id`,`color_id`),
  KEY `fk_stock_size` (`size_id`),
  KEY `fk_stock_color` (`color_id`),
  CONSTRAINT `fk_stock_color` FOREIGN KEY (`color_id`) REFERENCES `color_tb` (`color_id`),
  CONSTRAINT `fk_stock_product` FOREIGN KEY (`product_id`) REFERENCES `product_tb` (`product_id`),
  CONSTRAINT `fk_stock_size` FOREIGN KEY (`size_id`) REFERENCES `size_tb` (`size_id`)
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_tb`
--

LOCK TABLES `stock_tb` WRITE;
/*!40000 ALTER TABLE `stock_tb` DISABLE KEYS */;
INSERT INTO `stock_tb` VALUES (1,1,1,1,10,'2026-03-10 21:21:22',0),(2,1,2,1,10,'2026-03-10 21:21:22',0),(3,1,3,1,10,'2026-03-10 21:21:22',0),(4,1,4,1,0,'2026-03-10 21:21:22',1),(5,1,1,2,10,'2026-03-10 21:21:22',0),(6,1,2,2,10,'2026-03-10 21:21:22',0),(7,1,3,2,10,'2026-03-10 21:21:22',0),(8,1,4,2,0,'2026-03-10 21:21:22',1),(9,1,1,3,0,'2026-03-10 21:21:22',1),(10,1,2,3,0,'2026-03-10 21:21:22',1),(11,1,3,3,0,'2026-03-10 21:21:22',1),(12,1,4,3,0,'2026-03-10 21:21:22',1),(13,2,1,1,2,'2026-03-11 19:38:29',0),(14,2,2,4,10,'2026-03-12 20:07:18',0),(16,3,1,1,30,'2026-03-12 20:08:47',0),(17,3,2,1,30,'2026-03-12 20:08:47',0),(18,3,3,1,30,'2026-03-12 20:08:47',0),(19,3,4,1,30,'2026-03-12 20:08:47',0),(20,3,1,4,30,'2026-03-12 20:08:47',0),(21,3,2,4,30,'2026-03-12 20:08:47',0),(22,3,3,4,30,'2026-03-12 20:08:47',0),(23,3,4,4,30,'2026-03-12 20:08:47',0),(24,4,1,1,30,'2026-03-12 20:08:47',0),(25,4,2,1,30,'2026-03-12 20:08:47',0),(26,4,3,1,30,'2026-03-12 20:08:47',0),(27,4,4,1,30,'2026-03-12 20:08:47',0),(28,4,1,4,30,'2026-03-12 20:08:47',0),(29,4,2,4,30,'2026-03-12 20:08:47',0),(30,4,3,4,30,'2026-03-12 20:08:47',0),(31,4,4,4,30,'2026-03-12 20:08:47',0),(32,5,1,1,30,'2026-03-12 20:08:47',0),(33,5,2,1,30,'2026-03-12 20:08:47',0),(34,5,3,1,30,'2026-03-12 20:08:47',0),(35,5,4,1,30,'2026-03-12 20:08:47',0),(36,5,1,4,30,'2026-03-12 20:08:47',0),(37,5,2,4,30,'2026-03-12 20:08:47',0),(38,5,3,4,30,'2026-03-12 20:08:47',0),(39,5,4,4,30,'2026-03-12 20:08:47',0),(40,6,1,1,30,'2026-03-12 20:08:47',0),(41,6,2,1,30,'2026-03-12 20:08:47',0),(42,6,3,1,30,'2026-03-12 20:08:47',0),(43,6,4,1,30,'2026-03-12 20:08:47',0),(44,6,1,3,30,'2026-03-12 20:08:47',0),(45,6,2,3,30,'2026-03-12 20:08:47',0),(46,6,3,3,30,'2026-03-12 20:08:47',0),(47,6,4,3,30,'2026-03-12 20:08:47',0),(48,7,1,4,30,'2026-03-12 20:08:47',0),(49,7,2,4,30,'2026-03-12 20:08:47',0),(50,7,3,4,30,'2026-03-12 20:08:47',0),(51,7,4,4,30,'2026-03-12 20:08:47',0),(52,7,1,3,30,'2026-03-12 20:08:47',0),(53,7,2,3,30,'2026-03-12 20:08:47',0),(54,7,3,3,30,'2026-03-12 20:08:47',0),(55,7,4,3,30,'2026-03-12 20:08:47',0),(56,8,1,2,30,'2026-03-12 20:08:47',0),(57,8,2,2,30,'2026-03-12 20:08:47',0),(58,8,3,2,30,'2026-03-12 20:08:47',0),(59,8,4,2,30,'2026-03-12 20:08:47',0),(60,8,1,4,30,'2026-03-12 20:08:47',0),(61,8,2,4,30,'2026-03-12 20:08:47',0),(62,8,3,4,30,'2026-03-12 20:08:47',0),(63,8,4,4,30,'2026-03-12 20:08:47',0),(64,9,1,1,30,'2026-03-12 20:08:47',0),(65,9,2,1,30,'2026-03-12 20:08:47',0),(66,9,3,1,30,'2026-03-12 20:08:47',0),(67,9,4,1,30,'2026-03-12 20:08:47',0),(68,9,1,3,30,'2026-03-12 20:08:47',0),(69,9,2,3,30,'2026-03-12 20:08:47',0),(70,9,3,3,30,'2026-03-12 20:08:47',0),(71,9,4,3,30,'2026-03-12 20:08:47',0),(72,10,1,4,30,'2026-03-12 20:08:47',0),(73,10,2,4,30,'2026-03-12 20:08:47',0),(74,10,3,4,30,'2026-03-12 20:08:47',0),(75,10,4,4,30,'2026-03-12 20:08:47',0),(76,10,1,3,30,'2026-03-12 20:08:47',0),(77,10,2,3,30,'2026-03-12 20:08:47',0),(78,10,3,3,30,'2026-03-12 20:08:47',0),(79,10,4,3,30,'2026-03-12 20:08:47',0),(80,11,1,2,30,'2026-03-12 20:08:47',0),(81,11,2,2,30,'2026-03-12 20:08:47',0),(82,11,3,2,30,'2026-03-12 20:08:47',0),(83,11,4,2,30,'2026-03-12 20:08:47',0),(84,11,1,1,30,'2026-03-12 20:08:47',0),(85,11,2,1,30,'2026-03-12 20:08:47',0),(86,11,3,1,30,'2026-03-12 20:08:47',0),(87,11,4,1,30,'2026-03-12 20:08:47',0),(88,12,1,3,30,'2026-03-12 20:08:47',0),(89,12,2,3,30,'2026-03-12 20:08:47',0),(90,12,3,3,30,'2026-03-12 20:08:47',0),(91,12,4,3,30,'2026-03-12 20:08:47',0),(92,12,1,4,30,'2026-03-12 20:08:47',0),(93,12,2,4,30,'2026-03-12 20:08:47',0),(94,12,3,4,30,'2026-03-12 20:08:47',0),(95,12,4,4,30,'2026-03-12 20:08:47',0),(96,13,1,1,30,'2026-03-12 20:08:47',0),(97,13,2,1,30,'2026-03-12 20:08:47',0),(98,13,3,1,30,'2026-03-12 20:08:47',0),(99,13,4,1,30,'2026-03-12 20:08:47',0),(100,13,1,4,30,'2026-03-12 20:08:47',0),(101,13,2,4,30,'2026-03-12 20:08:47',0),(102,13,3,4,30,'2026-03-12 20:08:47',0),(103,13,4,4,30,'2026-03-12 20:08:47',0),(104,14,1,4,30,'2026-03-12 20:08:47',0),(105,14,2,4,30,'2026-03-12 20:08:47',0),(106,14,3,4,30,'2026-03-12 20:08:47',0),(107,14,4,4,30,'2026-03-12 20:08:47',0),(108,14,1,3,30,'2026-03-12 20:08:47',0),(109,14,2,3,30,'2026-03-12 20:08:47',0),(110,14,3,3,30,'2026-03-12 20:08:47',0),(111,14,4,3,30,'2026-03-12 20:08:47',0),(112,15,1,2,30,'2026-03-12 20:08:47',0),(113,15,2,2,30,'2026-03-12 20:08:47',0),(114,15,3,2,30,'2026-03-12 20:08:47',0),(115,15,4,2,30,'2026-03-12 20:08:47',0),(116,15,1,1,30,'2026-03-12 20:08:47',0),(117,15,2,1,30,'2026-03-12 20:08:47',0),(118,15,3,1,30,'2026-03-12 20:08:47',0),(119,15,4,1,30,'2026-03-12 20:08:47',0),(120,16,1,1,30,'2026-03-12 20:08:47',0),(121,16,2,1,30,'2026-03-12 20:08:47',0),(122,16,3,1,30,'2026-03-12 20:08:47',0),(123,16,4,1,30,'2026-03-12 20:08:47',0),(124,16,1,2,30,'2026-03-12 20:08:47',0),(125,16,2,2,30,'2026-03-12 20:08:47',0),(126,16,3,2,30,'2026-03-12 20:08:47',0),(127,16,4,2,30,'2026-03-12 20:08:47',0),(128,17,1,4,30,'2026-03-12 20:08:47',0),(129,17,2,4,30,'2026-03-12 20:08:47',0),(130,17,3,4,30,'2026-03-12 20:08:47',0),(131,17,4,4,30,'2026-03-12 20:08:47',0),(132,17,1,3,30,'2026-03-12 20:08:47',0),(133,17,2,3,30,'2026-03-12 20:08:47',0),(134,17,3,3,30,'2026-03-12 20:08:47',0),(135,17,4,3,30,'2026-03-12 20:08:47',0),(136,18,1,4,30,'2026-03-12 20:08:47',0),(137,18,2,4,30,'2026-03-12 20:08:47',0),(138,18,3,4,30,'2026-03-12 20:08:47',0),(139,18,4,4,30,'2026-03-12 20:08:47',0),(140,18,1,1,30,'2026-03-12 20:08:47',0),(141,18,2,1,30,'2026-03-12 20:08:47',0),(142,18,3,1,30,'2026-03-12 20:08:47',0),(143,18,4,1,30,'2026-03-12 20:08:47',0),(144,19,1,1,30,'2026-03-12 20:08:47',0),(145,19,2,1,30,'2026-03-12 20:08:47',0),(146,19,3,1,30,'2026-03-12 20:08:47',0),(147,19,4,1,30,'2026-03-12 20:08:47',0),(148,19,1,2,30,'2026-03-12 20:08:47',0),(149,19,2,2,30,'2026-03-12 20:08:47',0),(150,19,3,2,30,'2026-03-12 20:08:47',0),(151,19,4,2,30,'2026-03-12 20:08:47',0),(152,20,1,1,30,'2026-03-12 20:08:47',0),(153,20,2,1,30,'2026-03-12 20:08:47',0),(154,20,3,1,30,'2026-03-12 20:08:47',0),(155,20,4,1,30,'2026-03-12 20:08:47',0),(156,20,1,4,30,'2026-03-12 20:08:47',0),(157,20,2,4,30,'2026-03-12 20:08:47',0),(158,20,3,4,30,'2026-03-12 20:08:47',0),(159,20,4,4,30,'2026-03-12 20:08:47',0),(160,21,1,3,30,'2026-03-12 20:08:47',0),(161,21,2,3,30,'2026-03-12 20:08:47',0),(162,21,3,3,30,'2026-03-12 20:08:47',0),(163,21,4,3,30,'2026-03-12 20:08:47',0),(164,21,1,1,30,'2026-03-12 20:08:47',0),(165,21,2,1,30,'2026-03-12 20:08:47',0),(166,21,3,1,30,'2026-03-12 20:08:47',0),(167,21,4,1,30,'2026-03-12 20:08:47',0),(168,22,1,2,30,'2026-03-12 20:08:47',0),(169,22,2,2,30,'2026-03-12 20:08:47',0),(170,22,3,2,30,'2026-03-12 20:08:47',0),(171,22,4,2,30,'2026-03-12 20:08:47',0),(172,22,1,4,30,'2026-03-12 20:08:47',0),(173,22,2,4,30,'2026-03-12 20:08:47',0),(174,22,3,4,30,'2026-03-12 20:08:47',0),(175,22,4,4,30,'2026-03-12 20:08:47',0),(176,23,1,2,30,'2026-03-12 20:08:47',0),(177,23,2,2,30,'2026-03-12 20:08:47',0),(178,23,3,2,30,'2026-03-12 20:08:47',0),(179,23,4,2,30,'2026-03-12 20:08:47',0),(180,23,1,4,30,'2026-03-12 20:08:47',0),(181,23,2,4,30,'2026-03-12 20:08:47',0),(182,23,3,4,30,'2026-03-12 20:08:47',0),(183,23,4,4,30,'2026-03-12 20:08:47',0),(184,24,1,2,30,'2026-03-12 20:08:47',0),(185,24,2,2,30,'2026-03-12 20:08:47',0),(186,24,3,2,30,'2026-03-12 20:08:47',0),(187,24,4,2,30,'2026-03-12 20:08:47',0),(188,24,1,1,30,'2026-03-12 20:08:47',0),(189,24,2,1,30,'2026-03-12 20:08:47',0),(190,24,3,1,30,'2026-03-12 20:08:47',0),(191,24,4,1,30,'2026-03-12 20:08:47',0),(192,25,1,4,30,'2026-03-12 20:08:47',0),(193,25,2,4,30,'2026-03-12 20:08:47',0),(194,25,3,4,30,'2026-03-12 20:08:47',0),(195,25,4,4,30,'2026-03-12 20:08:47',0),(196,25,1,3,30,'2026-03-12 20:08:47',0),(197,25,2,3,30,'2026-03-12 20:08:47',0),(198,25,3,3,30,'2026-03-12 20:08:47',0),(199,25,4,3,30,'2026-03-12 20:08:47',0),(200,26,1,3,30,'2026-03-12 20:08:47',0),(201,26,2,3,30,'2026-03-12 20:08:47',0),(202,26,3,3,30,'2026-03-12 20:08:47',0),(203,26,4,3,30,'2026-03-12 20:08:47',0),(204,26,1,4,30,'2026-03-12 20:08:47',0),(205,26,2,4,30,'2026-03-12 20:08:47',0),(206,26,3,4,30,'2026-03-12 20:08:47',0),(207,26,4,4,30,'2026-03-12 20:08:47',0),(208,27,1,3,30,'2026-03-12 20:08:47',0),(209,27,2,3,30,'2026-03-12 20:08:47',0),(210,27,3,3,30,'2026-03-12 20:08:47',0),(211,27,4,3,30,'2026-03-12 20:08:47',0),(212,27,1,4,30,'2026-03-12 20:08:47',0),(213,27,2,4,30,'2026-03-12 20:08:47',0),(214,27,3,4,30,'2026-03-12 20:08:47',0),(215,27,4,4,30,'2026-03-12 20:08:47',0),(216,28,1,4,30,'2026-03-12 20:08:47',0),(217,28,2,4,30,'2026-03-12 20:08:47',0),(218,28,3,4,30,'2026-03-12 20:08:47',0),(219,28,4,4,30,'2026-03-12 20:08:47',0),(220,28,1,1,30,'2026-03-12 20:08:47',0),(221,28,2,1,30,'2026-03-12 20:08:47',0),(222,28,3,1,30,'2026-03-12 20:08:47',0),(223,28,4,1,30,'2026-03-12 20:08:47',0),(224,29,1,4,30,'2026-03-12 20:08:47',0),(225,29,2,4,30,'2026-03-12 20:08:47',0),(226,29,3,4,30,'2026-03-12 20:08:47',0),(227,29,4,4,30,'2026-03-12 20:08:47',0),(228,29,1,3,30,'2026-03-12 20:08:47',0),(229,29,2,3,30,'2026-03-12 20:08:47',0),(230,29,3,3,30,'2026-03-12 20:08:47',0),(231,29,4,3,30,'2026-03-12 20:08:47',0),(232,30,1,4,30,'2026-03-12 20:08:47',0),(233,30,2,4,30,'2026-03-12 20:08:47',0),(234,30,3,4,30,'2026-03-12 20:08:47',0),(235,30,4,4,30,'2026-03-12 20:08:47',0),(236,30,1,3,30,'2026-03-12 20:08:47',0),(237,30,2,3,30,'2026-03-12 20:08:47',0),(238,30,3,3,30,'2026-03-12 20:08:47',0),(239,30,4,3,30,'2026-03-12 20:08:47',0),(240,31,1,2,30,'2026-03-12 20:08:47',0),(241,31,2,2,30,'2026-03-12 20:08:47',0),(242,31,3,2,30,'2026-03-12 20:08:47',0),(243,31,4,2,30,'2026-03-12 20:08:47',0),(244,31,1,1,30,'2026-03-12 20:08:47',0),(245,31,2,1,30,'2026-03-12 20:08:47',0),(246,31,3,1,30,'2026-03-12 20:08:47',0),(247,31,4,1,30,'2026-03-12 20:08:47',0),(248,32,1,4,30,'2026-03-12 20:08:47',0),(249,32,2,4,30,'2026-03-12 20:08:47',0),(250,32,3,4,30,'2026-03-12 20:08:47',0),(251,32,4,4,30,'2026-03-12 20:08:47',0),(252,32,1,3,30,'2026-03-12 20:08:47',0),(253,32,2,3,30,'2026-03-12 20:08:47',0),(254,32,3,3,30,'2026-03-12 20:08:47',0),(255,32,4,3,30,'2026-03-12 20:08:47',0);
/*!40000 ALTER TABLE `stock_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tb`
--

DROP TABLE IF EXISTS `user_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_tb` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `email` varchar(100) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_phone` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `user_phone` (`user_phone`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tb`
--

LOCK TABLES `user_tb` WRITE;
/*!40000 ALTER TABLE `user_tb` DISABLE KEYS */;
INSERT INTO `user_tb` VALUES (1,1,'kim@test.com','pw','김철수','1011111111','2026-03-10 20:09:50','서울시 강남구'),(2,1,'lee@test.com','pw','이영희','1022222222','2026-03-10 20:09:50','서울시 마포구'),(3,1,'park@test.com','pw','박민준','1033333333','2026-03-10 20:09:50','부산시 해운대구'),(4,1,'choi@test.com','pw','최수진','1044444444','2026-03-10 20:09:50','대구시 중구'),(5,1,'jung@test.com','pw','정다은','1055555555','2026-03-10 20:09:50','인천시 남동구'),(6,1,'elikese@gmail.com','$2a$10$7eLx5UOhUK4GrtUiOlaqA.nZlSncecy7Fbq5hjZ5QdIeeCf9HHNiK','박화목','010-9120-1874','2026-03-11 19:47:34','부산시 부산진구'),(7,1,'test1234@test.com','$2a$10$NFBEPIfSzMwfSkIxCp22V.kw..cPgzR4VdfJ2cLPR0x5ekrCV1SDu','박화목','010-4544-1234','2026-03-11 19:48:15','부산시 해운대구');
/*!40000 ALTER TABLE `user_tb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `product_sub_menu_view`
--

/*!50001 DROP VIEW IF EXISTS `product_sub_menu_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `product_sub_menu_view` AS select `p`.`product_id` AS `product_id`,`p`.`product_name` AS `product_name`,`p`.`price` AS `price`,`p`.`is_best` AS `is_best`,`p`.`category_detail_id` AS `category_detail_id`,`cd`.`category_detail_name` AS `category_detail_name`,(select `product_thumbnail_tb`.`product_url` from `product_thumbnail_tb` where (`product_thumbnail_tb`.`product_id` = `p`.`product_id`) limit 1) AS `thumbnail_url`,(select min(`stock_tb`.`is_sold_out`) from `stock_tb` where (`stock_tb`.`product_id` = `p`.`product_id`)) AS `is_sold_out` from (`product_tb` `p` join `product_category_detail_tb` `cd` on((`p`.`category_detail_id` = `cd`.`category_detail_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-12 20:11:18
