-- MySQL dump 10.13  Distrib 5.5.21, for Win64 (x86)
--
-- Host: localhost    Database: carecenter
-- ------------------------------------------------------
-- Server version	5.5.21

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `birthDate` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'1990-10-10 00:00:00','d1','doktor 1','x','doktor 1'),(2,NULL,'d2','doktor 2','x','doktor 2'),(3,NULL,'d3','doktor 3','x','doktor 3'),(4,'1980-10-20 00:00:00','d4','doktor 4','x','doktor 4'),(5,NULL,'d5','doktor 5','x','doktor 5'),(6,'1960-08-08 00:00:00','p1','pacjent 1','x','pacjent 1'),(7,NULL,'p2','pacjent 2','x','pacjent 2'),(8,NULL,'p3','pacjent 3','x','pacjent 3');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accountgroup`
--

DROP TABLE IF EXISTS `accountgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) DEFAULT NULL,
  `careGroup_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK49862472F9832069` (`account_id`),
  KEY `FK498624728D1E60E9` (`careGroup_id`),
  CONSTRAINT `FK498624728D1E60E9` FOREIGN KEY (`careGroup_id`) REFERENCES `caregroup` (`id`),
  CONSTRAINT `FK49862472F9832069` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountgroup`
--

LOCK TABLES `accountgroup` WRITE;
/*!40000 ALTER TABLE `accountgroup` DISABLE KEYS */;
INSERT INTO `accountgroup` VALUES (1,1,1),(2,2,2),(3,3,3),(4,4,1),(5,4,2),(6,5,1),(7,5,3),(8,6,1),(9,7,2),(10,8,3);
/*!40000 ALTER TABLE `accountgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accountrole`
--

DROP TABLE IF EXISTS `accountrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountrole` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  KEY `FK97094823F9832069` (`account_id`),
  CONSTRAINT `FK97094823F9832069` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountrole`
--

LOCK TABLES `accountrole` WRITE;
/*!40000 ALTER TABLE `accountrole` DISABLE KEYS */;
INSERT INTO `accountrole` VALUES (1,'ROLE_DOCTOR',1),(2,'ROLE_DOCTOR',2),(3,'ROLE_DOCTOR',3),(4,'ROLE_DOCTOR',4),(5,'ROLE_DOCTOR',5),(6,'ROLE_PATIENT',6),(7,'ROLE_PATIENT',7),(8,'ROLE_PATIENT',8);
/*!40000 ALTER TABLE `accountrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activityName` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `activityCategory_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA126572FB4D13DCB` (`activityCategory_id`),
  CONSTRAINT `FKA126572FB4D13DCB` FOREIGN KEY (`activityCategory_id`) REFERENCES `activitycategory` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'Zjedz tabletke','zjedz czerwona',1),(2,'zrob 10 przysiadow','szybko',2),(3,'wypij szklanke wody','zimnej',1),(4,'pobiegaj','nie mniej jak kilometr',3);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activitycareplan`
--

DROP TABLE IF EXISTS `activitycareplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activitycareplan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dayOfWeek` int(11) DEFAULT NULL,
  `hourOfDay` int(11) DEFAULT NULL,
  `activity_id` int(11) DEFAULT NULL,
  `carePlan_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK98D8A629E93D236B` (`activity_id`),
  KEY `FK98D8A6298B5113AB` (`carePlan_id`),
  CONSTRAINT `FK98D8A6298B5113AB` FOREIGN KEY (`carePlan_id`) REFERENCES `careplan` (`id`),
  CONSTRAINT `FK98D8A629E93D236B` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activitycareplan`
--

LOCK TABLES `activitycareplan` WRITE;
/*!40000 ALTER TABLE `activitycareplan` DISABLE KEYS */;
INSERT INTO `activitycareplan` VALUES (1,1,7,2,1),(2,2,13,1,1),(3,3,20,4,1),(4,5,9,4,2),(5,6,17,4,2),(6,6,9,2,3),(7,7,16,3,3);
/*!40000 ALTER TABLE `activitycareplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activitycategory`
--

DROP TABLE IF EXISTS `activitycategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activitycategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryDescription` varchar(255) DEFAULT NULL,
  `categoryName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activitycategory`
--

LOCK TABLES `activitycategory` WRITE;
/*!40000 ALTER TABLE `activitycategory` DISABLE KEYS */;
INSERT INTO `activitycategory` VALUES (1,'opis kategorii 1','kategoria 1'),(2,'opis kategorii 2','kategoria 2'),(3,'opis kategorii 3','kategoria 3');
/*!40000 ALTER TABLE `activitycategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alarm`
--

DROP TABLE IF EXISTS `alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alarm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3C68A31C5895169` (`patient_id`),
  CONSTRAINT `FK3C68A31C5895169` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm`
--

LOCK TABLES `alarm` WRITE;
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
INSERT INTO `alarm` VALUES (1,'description 1',6,'2013-05-16 23:03:27','location 1'),(2,'description 2',7,'2013-05-16 23:03:28','location 2'),(3,'description 1',6,'2013-05-16 23:03:32','location 1'),(4,'description 2',7,'2013-05-16 23:03:32','location 2');
/*!40000 ALTER TABLE `alarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caregroup`
--

DROP TABLE IF EXISTS `caregroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caregroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caregroup`
--

LOCK TABLES `caregroup` WRITE;
/*!40000 ALTER TABLE `caregroup` DISABLE KEYS */;
INSERT INTO `caregroup` VALUES (1,'grupa 1'),(2,'grupa 2'),(3,'grupa 3');
/*!40000 ALTER TABLE `caregroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `careplan`
--

DROP TABLE IF EXISTS `careplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `careplan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `carePlanName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `careplan`
--

LOCK TABLES `careplan` WRITE;
/*!40000 ALTER TABLE `careplan` DISABLE KEYS */;
INSERT INTO `careplan` VALUES (1,'plan opieki nr 1'),(2,'plan opieki nr 2'),(3,'plan opieki nr 3');
/*!40000 ALTER TABLE `careplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `degree` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7A547D3F29651117` (`id`),
  CONSTRAINT `FK7A547D3F29651117` FOREIGN KEY (`id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES ('doc.',1),('bachelor',2),('bachelor',3),('bachelor',4),('bachelor',5);
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `city` varchar(255) DEFAULT NULL,
  `postalCode` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK340C82E529651117` (`id`),
  CONSTRAINT `FK340C82E529651117` FOREIGN KEY (`id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('jastrzebie','44-122','podhalanska',6),('krakow','','',7),('','','',8);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patientcareplan`
--

DROP TABLE IF EXISTS `patientcareplan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patientcareplan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `endDate` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `carePlan_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCD0ABBDF8B5113AB` (`carePlan_id`),
  KEY `FKCD0ABBDFC5895169` (`patient_id`),
  CONSTRAINT `FKCD0ABBDFC5895169` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FKCD0ABBDF8B5113AB` FOREIGN KEY (`carePlan_id`) REFERENCES `careplan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patientcareplan`
--

LOCK TABLES `patientcareplan` WRITE;
/*!40000 ALTER TABLE `patientcareplan` DISABLE KEYS */;
INSERT INTO `patientcareplan` VALUES (1,'2013-07-02 00:00:00','Prosze nie zapomniej wysylac raportow ','2013-05-03 00:00:00',1,8),(2,'2013-08-03 00:00:00','bez uwag','2013-05-03 00:00:00',2,6),(3,'2013-10-07 00:00:00','prosze sie nie zmeczyc za bardzo','2013-05-07 00:00:00',3,7);
/*!40000 ALTER TABLE `patientcareplan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `done` int(11) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `activity_id` int(11) DEFAULT NULL,
  `patientCarePlan_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK91B14154E93D236B` (`activity_id`),
  KEY `FK91B14154528E7869` (`patientCarePlan_id`),
  CONSTRAINT `FK91B14154528E7869` FOREIGN KEY (`patientCarePlan_id`) REFERENCES `patientcareplan` (`id`),
  CONSTRAINT `FK91B14154E93D236B` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `test` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-05-16 23:06:13
