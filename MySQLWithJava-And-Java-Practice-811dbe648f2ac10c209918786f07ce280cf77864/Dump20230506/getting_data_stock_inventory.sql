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
-- Table structure for table `stock_inventory`
--

DROP TABLE IF EXISTS `stock_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_inventory` (
  `SKU` varchar(20) NOT NULL,
  `product_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SKU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_inventory`
--

LOCK TABLES `stock_inventory` WRITE;
/*!40000 ALTER TABLE `stock_inventory` DISABLE KEYS */;
INSERT INTO `stock_inventory` VALUES ('1111','lays original chips'),('1144','Ribeye Steak'),('12135','emote'),('12551','sandal'),('16122','ipod'),('17378','key chain'),('21314','bottle'),('23493','air freshener'),('23564','cell phone'),('24692','grid paper'),('31078','candy wrapper'),('31988','leg warmers'),('36161','piano'),('36678','teddies'),('40402','plate'),('42263','cat'),('42388','bowl'),('45815','drill press'),('55074','shoe lace'),('56886','car'),('62094','video games'),('63125','candle'),('63752','sand paper'),('63977','sun glasses'),('63986','pillow'),('67237','tissue box'),('72178','magnet'),('72621','playing card'),('73486','slipper'),('74871','sofa'),('75123','pants'),('77422','glasses'),('78844','socks'),('80260','picture frame'),('80282','USB drive'),('86126','street lights'),('88245','keyboard'),('89010','glass'),('92713','door'),('96441','bag'),('98397','table'),('98895','doll'),('adadsd','12342323212'),('dfsdgsgs','21231'),('game 1','1122game1'),('my new item','524534');
/*!40000 ALTER TABLE `stock_inventory` ENABLE KEYS */;
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
