-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: music-online-classes
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `account_status` varchar(255) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `expertise` varchar(255) DEFAULT NULL,
  `first_log_in` bit(1) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `reset_token` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK4bgmpi98dylab6qdvf9xyaxu4` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin','AKTIVAN','2022-04-09',NULL,'admin@gmail.com','Nije selektovan',_binary '\0','Admin','Admin12345!','+381631144224',NULL,'Admin','Admin',NULL),('ana123','AKTIVAN','02-03-2025','','anaaaa@gmail.com','Nije selektovan',_binary '\0','Ana','Ana12345!','+381639911444',NULL,'Anic','Ucenik','/uploads/profile_pictures/ana123_profile_picture.jpg'),('kata123','ODBIJENA_AKTIVACIJA','01-07-2025','','kata@gmail.com','Nije selektovan',_binary '','Katarina','Kata12345!','+381639988551',NULL,'Katic','Ucenik',NULL),('mina123','AKTIVAN','09-03-2025','','minaa@gmail.com','Nije selektovan',_binary '\0','Mina','Mina12345!','+381694455888',NULL,'mina','Ucenik','/uploads/profile_pictures/mina123_profile_picture.jpg'),('nenad123','AKTIVAN','09-06-2025','Muzicka akademija','nenad@gmail.com','Klavir',_binary '','Nenad','Nenad12345!','+381639911222',NULL,'Nenadovic','Profesor',NULL),('pera123','AKTIVAN','09-03-2025','Muzicka akademija','peraa@gmail.com','Gitara',_binary '\0','Pera','Pera12345!','+381631144225',NULL,'Peric','Profesor','/uploads/profile_pictures/pera123_profile_picture.jpg');
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

-- Dump completed on 2025-07-06 14:45:41
