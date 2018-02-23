-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: Orders
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `OrderId` int(11) NOT NULL AUTO_INCREMENT,
  `BuyerName` varchar(256) DEFAULT NULL,
  `BuyerEmail` varchar(256) DEFAULT NULL,
  `OrderDate` date DEFAULT NULL,
  `OrderTotalValue` float DEFAULT NULL,
  `Address` varchar(256) DEFAULT NULL,
  `Postcode` int(11) DEFAULT NULL,
  PRIMARY KEY (`OrderId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,'Tamás','balogh00@gmail.com','1977-05-16',1355.56,'Hungary',7633),(2,'Kamu','a@b.hu','2018-02-22',124,'Hontalan',9999),(3,'Zsolt','zsolt@worldofbooks.com','2018-02-23',1234,'Nem Tudjuk',123456789),(4,'Tamás','balogh00@gmail.com','1977-05-16',1355.56,'Hungary',7633),(5,'Kamu','a@b.hu','2018-02-22',124,'Hontalan',9999),(6,'Zsolt','zsolt@worldofbooks.com','2018-02-23',1234,'Nem Tudjuk',123456789),(7,'Tamás','balogh00@gmail.com','1977-05-16',1355.56,'Hungary',7633),(8,'Kamu','a@b.hu','2018-02-22',124,'Hontalan',9999),(9,'Zsolt','zsolt@worldofbooks.com','2018-02-23',1234,'Nem Tudjuk',123456789),(10,'Tamás','balogh00@gmail.com','1977-05-16',1355.56,'Hungary',7633),(11,'Kamu','a@b.hu','2018-02-22',124,'Hontalan',9999),(12,'Zsolt','zsolt@worldofbooks.com','2018-02-23',1234,'Nem Tudjuk',123456789);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_item` (
  `OrderItemId` int(11) NOT NULL AUTO_INCREMENT,
  `OrderId` int(11) DEFAULT NULL,
  `SalePrice` float DEFAULT NULL,
  `ShippingPrice` float DEFAULT NULL,
  `TotalItemPrice` float DEFAULT NULL,
  `SKU` varchar(256) DEFAULT NULL,
  `Status` enum('IN_STOCK','OUT_OF_STOCK') DEFAULT NULL,
  PRIMARY KEY (`OrderItemId`),
  KEY `OrderId` (`OrderId`),
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`OrderId`) REFERENCES `order` (`OrderId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,1,1345,10.56,1355.56,'SKU','OUT_OF_STOCK'),(2,2,123,1,124,'SKU','IN_STOCK'),(3,3,1234,0,1234,'SKU','OUT_OF_STOCK'),(4,4,1345,10.56,1355.56,'SKU','OUT_OF_STOCK'),(5,5,123,1,124,'SKU','IN_STOCK'),(6,6,1234,0,1234,'SKU','OUT_OF_STOCK'),(7,7,1345,10.56,1355.56,'SKU','OUT_OF_STOCK'),(8,8,123,1,124,'SKU','IN_STOCK'),(9,9,1234,0,1234,'SKU','OUT_OF_STOCK'),(10,10,1345,10.56,1355.56,'SKU','OUT_OF_STOCK'),(11,11,123,1,124,'SKU','IN_STOCK'),(12,12,1234,0,1234,'SKU','OUT_OF_STOCK');
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-23  4:16:08
