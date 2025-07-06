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
-- Table structure for table `term`
--

DROP TABLE IF EXISTS `term`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `term` (
  `term_id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `status` enum('ODBIJEN','PRIHVACEN','SLOBODAN','ZAHTEV_POSLAT','NIJE_ODRZAN','ODRZAN') DEFAULT NULL,
  `time` time(6) DEFAULT NULL,
  `professor_username` varchar(255) DEFAULT NULL,
  `student_username` varchar(255) DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `channel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`term_id`),
  KEY `FKemhtrvwjtj1fuqyderrp5reap` (`professor_username`),
  KEY `FK8m7rf9idpfuem2gb9v603bhqn` (`student_username`),
  KEY `FKc0eqwvhyx32k4wpboi9wjid19` (`course_id`),
  CONSTRAINT `FK8m7rf9idpfuem2gb9v603bhqn` FOREIGN KEY (`student_username`) REFERENCES `user` (`username`),
  CONSTRAINT `FKc0eqwvhyx32k4wpboi9wjid19` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `FKemhtrvwjtj1fuqyderrp5reap` FOREIGN KEY (`professor_username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `term`
--

LOCK TABLES `term` WRITE;
/*!40000 ALTER TABLE `term` DISABLE KEYS */;
INSERT INTO `term` VALUES (1,'2025-04-20','SLOBODAN','14:30:00.000000','pera123',NULL,NULL,NULL),(2,'2025-04-21','ODBIJEN','12:00:00.000000','pera123','ana123',2,NULL),(3,'2025-04-22','ODRZAN','16:00:00.000000','pera123','ana123',2,'A'),(4,'2025-04-23','ODBIJEN','14:10:00.000000','pera123','ana123',1,'B'),(5,'2025-04-24','ODRZAN','09:00:00.000000','pera123','ana123',4,'D'),(6,'2025-05-04','ODRZAN','10:00:00.000000','pera123','ana123',4,'F'),(7,'2025-05-07','NIJE_ODRZAN','11:00:00.000000','pera123','ana123',4,'E'),(8,'2025-05-07','PRIHVACEN','12:00:00.000000','pera123','ana123',2,'C'),(9,'2025-05-11','SLOBODAN','18:40:00.000000','pera123',NULL,NULL,'channel-81e76a9d-be88-43bb-8f81-9f5f4b35e3f9'),(10,'2025-05-11','SLOBODAN','18:36:00.000000','pera123',NULL,NULL,'channel-2863666d-ce77-4c8b-be52-d0bb37ada5da'),(11,'2025-05-14','ODBIJEN','16:40:00.000000','pera123','ana123',1,'channel-c4fcb841-41ca-4e00-8dca-9741f010d284'),(12,'2025-08-07','PRIHVACEN','18:25:00.000000','pera123','ana123',2,'channel-20b75d78-44e4-44a8-805b-1586e1d069d7'),(13,'2025-07-25','SLOBODAN','15:30:00.000000','pera123',NULL,NULL,'channel-b2a6ba69-df76-4933-9f2f-f0aa29d4cef0'),(14,'2025-10-09','SLOBODAN','15:15:00.000000','pera123',NULL,NULL,'channel-74997398-be1f-4ee4-9e71-486b2830626f');
/*!40000 ALTER TABLE `term` ENABLE KEYS */;
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
