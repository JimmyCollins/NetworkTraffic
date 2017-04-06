CREATE DATABASE  IF NOT EXISTS `networktraffic` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `networktraffic`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: networktraffic
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `savedanalyses`
--

DROP TABLE IF EXISTS `savedanalyses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `savedanalyses` (
  `AnalysisId` int(11) NOT NULL AUTO_INCREMENT,
  `Date` datetime NOT NULL,
  PRIMARY KEY (`AnalysisId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topdestinationips`
--

DROP TABLE IF EXISTS `topdestinationips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topdestinationips` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `AnalysisId` int(11) NOT NULL,
  `IP` varchar(255) NOT NULL,
  `Count` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `destinationips-AnalysisId_idx` (`AnalysisId`),
  CONSTRAINT `destinationips-AnalysisId` FOREIGN KEY (`AnalysisId`) REFERENCES `savedanalyses` (`AnalysisId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topdestinationports`
--

DROP TABLE IF EXISTS `topdestinationports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topdestinationports` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `AnalysisId` int(11) NOT NULL,
  `Port` int(11) NOT NULL,
  `Count` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `AnalysisId_idx` (`AnalysisId`),
  CONSTRAINT `AnalysisId-destinationports` FOREIGN KEY (`AnalysisId`) REFERENCES `savedanalyses` (`AnalysisId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topsourceips`
--

DROP TABLE IF EXISTS `topsourceips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topsourceips` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `AnalysisId` int(11) NOT NULL,
  `IP` varchar(255) NOT NULL,
  `Count` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `sourceips-AnalysisId_idx` (`AnalysisId`),
  CONSTRAINT `sourceips-AnalysisId` FOREIGN KEY (`AnalysisId`) REFERENCES `savedanalyses` (`AnalysisId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `topsourceports`
--

DROP TABLE IF EXISTS `topsourceports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topsourceports` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `AnalysisId` int(11) NOT NULL,
  `Port` int(11) NOT NULL,
  `Count` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `AnalysisId_idx` (`AnalysisId`),
  CONSTRAINT `AnalysisId-sourceports` FOREIGN KEY (`AnalysisId`) REFERENCES `savedanalyses` (`AnalysisId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-06 20:13:27
