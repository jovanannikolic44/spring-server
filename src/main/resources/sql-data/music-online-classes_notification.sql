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
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `notification_id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `is_read` bit(1) DEFAULT NULL,
  `student_username` varchar(255) DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `professor_username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`notification_id`),
  KEY `FKrdtms78scfvqp7b290ps7xcfu` (`student_username`),
  KEY `FK2qvynpew0iu557b4qk9go0u0c` (`course_id`),
  KEY `FK9gktw2hiepvd077ddkuislyma` (`professor_username`),
  CONSTRAINT `FK2qvynpew0iu557b4qk9go0u0c` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `FK9gktw2hiepvd077ddkuislyma` FOREIGN KEY (`professor_username`) REFERENCES `user` (`username`),
  CONSTRAINT `FKrdtms78scfvqp7b290ps7xcfu` FOREIGN KEY (`student_username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'2025-04-21 12:07:00.000000','Profesor Pera Peric je potvrdio rezervaciju za cas \'Violina za pocetnike\' 2025-04-22',_binary '\0','ana123',2,'pera123'),(2,'2025-04-21 12:13:33.000000','Profesor Pera Peric je odbio rezervaciju za cas \'Gitara kurs1\' 2025-04-23',_binary '\0','ana123',1,'pera123'),(3,'2025-07-05 15:12:24.506841','Rezervacija za kurs Violina za pocetnike je potvrdjenaod strane profesora Pera za datum 2025-08-07 i vreme 18:25.',NULL,'ana123',2,'pera123');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
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
