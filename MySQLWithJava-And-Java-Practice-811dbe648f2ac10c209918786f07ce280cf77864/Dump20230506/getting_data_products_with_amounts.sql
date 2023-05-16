-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: getting_data
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `products_with_amounts`
--

DROP TABLE IF EXISTS `products_with_amounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_with_amounts` (
  `SKU` varchar(20) NOT NULL,
  `product_name` text,
  `amount` int NOT NULL,
  PRIMARY KEY (`SKU`),
  UNIQUE KEY `SKU_UNIQUE` (`SKU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_with_amounts`
--

LOCK TABLES `products_with_amounts` WRITE;
/*!40000 ALTER TABLE `products_with_amounts` DISABLE KEYS */;
INSERT INTO `products_with_amounts` VALUES ('12135','emote',88),('12551','sandal',89),('16122','ipod',83),('17378','key chain',61),('21314','bottle',25),('23493','air freshener',37),('23564','cell phone',98),('24692','grid paper',60),('31078','candy wrapper',7),('31988','leg warmers',43),('36161','piano',90),('36678','teddies',52),('40402','plate',86),('42263','cat',51),('42388','bowl',32),('45815','drill press',99),('55074','shoe lace',100),('56886','car',50),('62094','video games',20),('63125','candle',59),('63752','sand paper',24),('63977','sun glasses',96),('63986','pillow',44),('67237','tissue box',95),('72178','magnet',33),('72621','playing card',11),('73486','slipper',64),('74871','sofa',5),('75123','pants',39),('77422','glasses',81),('78844','socks',65),('80260','picture frame',87),('80282','USB drive',68),('86126','street lights',55),('88245','keyboard',85),('89010','glass',42),('92713','door',84),('96441','bag',66),('98397','table',49),('98895','doll',56),('adadsd','12342323212',509),('dfsdgsgs','21231',34),('game 1','1122game1',1500),('my new item','524534',534534);
/*!40000 ALTER TABLE `products_with_amounts` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-06 12:42:12
