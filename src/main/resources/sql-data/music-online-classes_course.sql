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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `content` text,
  `course_image` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `instrument` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rating` float NOT NULL,
  `professor_username` varchar(255) NOT NULL,
  `price` float NOT NULL,
  `number_of_ratings` float NOT NULL,
  `total_sum_ratings` float NOT NULL,
  `number_of_classes` int NOT NULL,
  `status` enum('ODBIJEN','PRIHVACEN','ZAHTEV_POSLAT') DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `FKedieqpuhmce4iy0gmvrc92a2l` (`professor_username`),
  CONSTRAINT `FKedieqpuhmce4iy0gmvrc92a2l` FOREIGN KEY (`professor_username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Kurs1,Less2,Less3,Less4','/uploads/courses_pictures/guitar2.jpg','Kurs1','Gitara','Pocetni','Gitara kurs1',4.46296,'pera123',100,27,120.5,10,'PRIHVACEN'),(2,'Kurs2, Less2, Less3','/uploads/courses_pictures/violin1.jpg','Kurs2','Violina','Pocetni','Violina za pocetnike',4.20833,'pera123',200,12,50.5,10,'PRIHVACEN'),(3,'Kurs3','/uploads/courses_pictures/Piano1.jpeg','Kurs3','Klavir','Pocetni','Klavir za pocetnike',5,'pera123',300,1,5,13,'PRIHVACEN'),(4,'Kurs4','/uploads/courses_pictures/noImage.png','Kurs4','Elektricna gitara','Pocetni','Elektricna gitara',3.5,'pera123',200,2,7,12,'PRIHVACEN'),(5,'KursPevanje','/uploads/courses_pictures/sing2.jpg','KursPevanje','Pevanje','Pocetni','Pevanje - pocetni nivo',3,'pera123',80,5,15,13,'PRIHVACEN'),(6,'Kurs6','/uploads/courses_pictures/guitar1.jpg','Kurs6','Gitara','Napredni','Gitara napredni nivo',5,'pera123',500,1,5,7,'PRIHVACEN'),(7,'Kurs7','/uploads/courses_pictures/piano5.jpg','Kurs7','Klavir','Napredni','Klavir napredni',5,'pera123',500,1,5,10,'PRIHVACEN'),(8,'Kurs8','/uploads/courses_pictures/noImage.png','Kurs8','Bubanj','Pocetni','Bubanj za pocetnike',4,'pera123',400,5,20,15,'PRIHVACEN'),(9,'Kurs9','/uploads/courses_pictures/drum1.jpg','Kurs9','Bubanj','Napredni','Bubanj napredni nivo',5,'pera123',700,1,5,10,'PRIHVACEN'),(10,'KursHarfe,Modul1,Modul2','/uploads/courses_pictures/harfa1.jpg','KursHarfe','Harfa','Napredni','Harfa - napredni nivo',3.55556,'pera123',400,9,32,30,'PRIHVACEN'),(11,'Kurs11','/uploads/courses_pictures/truba1.jpg','Kurs11','Truba','Pocetni','Truba za pocetnike',5,'pera123',100,1,5,14,'PRIHVACEN'),(13,'Kurs sadrzi:,LekcijaA,LekcijaB','','Ovo je novi kurs.','Saksofon','Pocetni','Neki kurs',0,'pera123',200,0,0,20,'PRIHVACEN'),(16,'Kurs','/uploads/course_pictures/course_1748367174047_image.jpg','Kurs','Harmonika','Napredni','Kurs2',0,'pera123',60,0,0,10,'ODBIJEN'),(17,'Kurs3','/uploads/course_pictures/course_1748367259953_image.jpg','Kurs3','Violina','Pocetni','Kurs3',0,'pera123',30,0,0,5,'ZAHTEV_POSLAT'),(18,'lwlwlw','/uploads/course_pictures/course_1751805189493_image.jpg','sjjaiwi','Gitara','Pocetni','Jajswk',0,'pera123',50,0,0,20,'PRIHVACEN');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-06 14:45:42
