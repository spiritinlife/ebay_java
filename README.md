# ebay_java
This is an implementation of an ebay-like site written for a uni class


## Database creation
CREATE DATABASE ebay_ted;
use ebay_ted.
\. script_path

## SCRIPT SQL

-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: ebay_ted
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `bid`
--

DROP TABLE IF EXISTS `bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bid` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ITEM_ID` int(11) NOT NULL,
  `SELLER_ID` int(11) NOT NULL,
  `BIDDER_ID` int(11) NOT NULL,
  `TIME` datetime DEFAULT NULL,
  `AMOUNT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_BID_ITEM1_idx` (`ITEM_ID`),
  KEY `fk_BID_SELLER1_idx` (`SELLER_ID`),
  KEY `fk_BID_BIDDER1_idx` (`BIDDER_ID`),
  CONSTRAINT `fk_BID_BIDDER1` FOREIGN KEY (`BIDDER_ID`) REFERENCES `bidder` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_BID_ITEM1` FOREIGN KEY (`ITEM_ID`) REFERENCES `item` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_BID_SELLER1` FOREIGN KEY (`SELLER_ID`) REFERENCES `seller` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid`
--

LOCK TABLES `bid` WRITE;
/*!40000 ALTER TABLE `bid` DISABLE KEYS */;
/*!40000 ALTER TABLE `bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bidder`
--

DROP TABLE IF EXISTS `bidder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bidder` (
  `USER_ID` int(11) NOT NULL,
  `RATING` int(11) DEFAULT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  CONSTRAINT `fk_BIDDER_USER1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bidder`
--

LOCK TABLES `bidder` WRITE;
/*!40000 ALTER TABLE `bidder` DISABLE KEYS */;
/*!40000 ALTER TABLE `bidder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('bed bath and beyond'),('comfy'),('drinks');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(45) DEFAULT NULL,
  `CURRENTLY` int(11) DEFAULT NULL,
  `BUY_PRICE` int(11) DEFAULT NULL,
  `FIRST_BID` int(11) DEFAULT NULL,
  `NUMBER_OF_BIDS` int(11) DEFAULT NULL,
  `COUNTRY` varchar(45) DEFAULT NULL,
  `STARTED` datetime DEFAULT NULL,
  `ENDS` datetime DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'pillow',15,25,10,4,'France','2015-08-31 10:13:59','2015-08-31 10:14:01','Super comfy pillow'),(2,'bug spray',10,20,4,2,'Greece','2015-08-31 10:14:34','2015-08-31 10:14:36','Extreme bug spray');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_has_category`
--

DROP TABLE IF EXISTS `item_has_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_has_category` (
  `ITEM_ID` int(11) NOT NULL,
  `CATEGORY_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`ITEM_ID`,`CATEGORY_NAME`),
  KEY `fk_ITEM_has_CATEGORY_CATEGORY1_idx` (`CATEGORY_NAME`),
  KEY `fk_ITEM_has_CATEGORY_ITEM_idx` (`ITEM_ID`),
  CONSTRAINT `fk_ITEM_has_CATEGORY_CATEGORY1` FOREIGN KEY (`CATEGORY_NAME`) REFERENCES `category` (`NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ITEM_has_CATEGORY_ITEM` FOREIGN KEY (`ITEM_ID`) REFERENCES `item` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_has_category`
--

LOCK TABLES `item_has_category` WRITE;
/*!40000 ALTER TABLE `item_has_category` DISABLE KEYS */;
INSERT INTO `item_has_category` VALUES (1,'bed bath and beyond'),(1,'comfy'),(2,'drinks');
/*!40000 ALTER TABLE `item_has_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `ITEM_ID` int(11) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `LONGITUDE` float DEFAULT NULL,
  `LATITUDE` float DEFAULT NULL,
  PRIMARY KEY (`ITEM_ID`),
  CONSTRAINT `fk_LOCATION_ITEM1` FOREIGN KEY (`ITEM_ID`) REFERENCES `item` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller`
--

DROP TABLE IF EXISTS `seller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seller` (
  `USER_ID` int(11) NOT NULL,
  `RATING` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  CONSTRAINT `fk_SELLER_USER1` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller`
--

LOCK TABLES `seller` WRITE;
/*!40000 ALTER TABLE `seller` DISABLE KEYS */;
/*!40000 ALTER TABLE `seller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(45) DEFAULT NULL,
  `LAST_NAME` varchar(45) DEFAULT NULL,
  `USERNAME` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `SOCIAL_SECURITY_NUMBER` varchar(45) DEFAULT NULL,
  `ADDRESS` varchar(45) DEFAULT NULL,
  `PHONE_NUMBER` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-31 11:10:15
