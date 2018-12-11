-- MySQL dump 10.13  Distrib 5.7.14, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel
-- ------------------------------------------------------
-- Server version	5.7.14-log

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
-- Table structure for table `features`
--

DROP TABLE IF EXISTS `features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `features` (
  `featureID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`featureID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `features`
--

LOCK TABLES `features` WRITE;
/*!40000 ALTER TABLE `features` DISABLE KEYS */;
INSERT INTO `features` VALUES (1,'Parking'),(2,'Television'),(3,'Flowers'),(4,'Table Tennis'),(5,'Carnival Rides'),(6,'Swiming Pool'),(7,'Attendent'),(8,'Coffee'),(9,'Cocktails'),(10,'Lockers'),(11,'Gym'),(12,'Wifi'),(13,'Washing Room'),(14,'Waiters'),(15,'Hair Dryer'),(16,'Smoker Free');
/*!40000 ALTER TABLE `features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RoomID` int(11) DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  `BookedDates` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `toRoomC` (`RoomID`),
  KEY `toUserC` (`UserID`),
  CONSTRAINT `toRoomC` FOREIGN KEY (`RoomID`) REFERENCES `roomtype` (`roomID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `toUserC` FOREIGN KEY (`UserID`) REFERENCES `userprofile` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (93,1,112,'2016-11-29'),(94,1,112,'2016-11-30'),(95,1,112,'2016-12-16');
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomfeatures`
--

DROP TABLE IF EXISTS `roomfeatures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomfeatures` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `roomID` int(11) DEFAULT NULL,
  `featureID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `toRoom` (`roomID`),
  KEY `toFeature` (`featureID`),
  CONSTRAINT `toFeature` FOREIGN KEY (`featureID`) REFERENCES `features` (`featureID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `toRoom` FOREIGN KEY (`roomID`) REFERENCES `roomtype` (`roomID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomfeatures`
--

LOCK TABLES `roomfeatures` WRITE;
/*!40000 ALTER TABLE `roomfeatures` DISABLE KEYS */;
INSERT INTO `roomfeatures` VALUES (20,2,1),(21,2,5),(26,3,1),(27,3,3),(28,3,5),(29,3,7),(30,3,9),(31,3,11),(32,3,13),(33,3,16),(40,1,1),(41,1,2),(42,1,3),(43,1,4),(44,1,5),(45,1,8),(46,1,16);
/*!40000 ALTER TABLE `roomfeatures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `status` binary(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `type` (`type`),
  CONSTRAINT `type` FOREIGN KEY (`type`) REFERENCES `roomtype` (`roomID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (14,1,'1'),(16,2,'1'),(17,2,'1'),(20,3,'1'),(22,1,'1'),(23,1,'1'),(24,1,'1'),(25,1,'1'),(26,3,'0'),(27,2,'0'),(28,3,'0'),(29,1,'1'),(30,1,'0'),(31,1,'0');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomtype`
--

DROP TABLE IF EXISTS `roomtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roomtype` (
  `roomID` int(11) NOT NULL AUTO_INCREMENT,
  `price` float DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `aprovedPrice` float DEFAULT NULL,
  `aprovedQuantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`roomID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomtype`
--

LOCK TABLES `roomtype` WRITE;
/*!40000 ALTER TABLE `roomtype` DISABLE KEYS */;
INSERT INTO `roomtype` VALUES (1,1500,8,1500,6),(2,444.4,3,400,2),(3,1000,3,999,1);
/*!40000 ALTER TABLE `roomtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userprofile`
--

DROP TABLE IF EXISTS `userprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userprofile` (
  `userName` char(15) CHARACTER SET latin1 DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userPassword` varchar(15) DEFAULT NULL,
  `activationStatus` binary(1) DEFAULT '0',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userprofile`
--

LOCK TABLES `userprofile` WRITE;
/*!40000 ALTER TABLE `userprofile` DISABLE KEYS */;
INSERT INTO `userprofile` VALUES ('Mudassir','hunbledhillon@gmail.com',112,'lahore','0'),('Admin','admin@email.com',113,'admin','0'),('Manager','manager@email.com',114,'manager','0'),('786765878','*&*(&(*',115,'klsdjfds','0'),('l134118','l134118@lhr.nu.edu.pk',116,'lahore','0'),('l134118','l134118@lhr.nu.edu.pk',117,'lahore','0'),('l134118','l134118@lhr.nu.edu.pk',118,'lahore','0'),('l134118','l134118@lhr.nu.edu.pk',119,'lahore','0'),('Mohsin','mohsinsarwar409@gmail.com',120,'123','0');
/*!40000 ALTER TABLE `userprofile` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-09 21:11:54
