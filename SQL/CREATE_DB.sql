-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: mager.keenetic.pro    Database: aircompany_manager_db
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `aircrafts`
--

DROP TABLE IF EXISTS `aircrafts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aircrafts` (
  `aircraft_id` int NOT NULL AUTO_INCREMENT,
  `aircraft_producer` varchar(50) NOT NULL,
  `aircraft_model` varchar(50) NOT NULL,
  `aircraft_registration_code` varchar(10) NOT NULL,
  `in_operation` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`aircraft_id`),
  UNIQUE KEY `id_UNIQUE` (`aircraft_id`),
  UNIQUE KEY `aircraft_registration_code_UNIQUE` (`aircraft_registration_code`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `airports`
--

DROP TABLE IF EXISTS `airports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `airports` (
  `airport_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `country` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `iata_code` varchar(3) NOT NULL,
  PRIMARY KEY (`airport_id`),
  UNIQUE KEY `id_UNIQUE` (`airport_id`),
  UNIQUE KEY `iata_code_UNIQUE` (`iata_code`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `brigades`
--

DROP TABLE IF EXISTS `brigades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brigades` (
  `brigade_id` bigint NOT NULL AUTO_INCREMENT,
  `brigade_name` varchar(50) NOT NULL,
  `is_archived` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`brigade_id`),
  UNIQUE KEY `brigade_id_UNIQUE` (`brigade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `brigades_has_users`
--

DROP TABLE IF EXISTS `brigades_has_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brigades_has_users` (
  `brigades_brigade_id` bigint NOT NULL,
  `users_user_id` bigint NOT NULL,
  PRIMARY KEY (`brigades_brigade_id`,`users_user_id`),
  KEY `fk_brigades_has_users_users1_idx` (`users_user_id`),
  KEY `fk_brigades_has_users_brigades1_idx` (`brigades_brigade_id`),
  CONSTRAINT `fk_brigades_has_users_brigades1` FOREIGN KEY (`brigades_brigade_id`) REFERENCES `brigades` (`brigade_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_brigades_has_users_users1` FOREIGN KEY (`users_user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `flights`
--

DROP TABLE IF EXISTS `flights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flights` (
  `flight_id` bigint NOT NULL AUTO_INCREMENT,
  `aircraft_id` int DEFAULT NULL,
  `brigade_id` bigint DEFAULT NULL,
  `departure_airport_id` int DEFAULT NULL,
  `destination_airport_id` int DEFAULT NULL,
  `flight_callsign` varchar(10) NOT NULL,
  `departure_datetime` timestamp NOT NULL,
  `is_archived` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`flight_id`),
  UNIQUE KEY `id_UNIQUE` (`flight_id`),
  KEY `fk_flights_airports1_idx` (`departure_airport_id`),
  KEY `fk_flights_airports2_idx` (`destination_airport_id`),
  KEY `fk_flights_brigades1_idx` (`brigade_id`),
  KEY `fk_flights_aircrafts1_idx` (`aircraft_id`),
  CONSTRAINT `fk_flights_aircrafts` FOREIGN KEY (`aircraft_id`) REFERENCES `aircrafts` (`aircraft_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_flights_airports_departure` FOREIGN KEY (`departure_airport_id`) REFERENCES `airports` (`airport_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_flights_airports_destination` FOREIGN KEY (`destination_airport_id`) REFERENCES `airports` (`airport_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_flights_brigades` FOREIGN KEY (`brigade_id`) REFERENCES `brigades` (`brigade_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL,
  `role_type` varchar(50) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `id_UNIQUE` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(320) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` char(60) NOT NULL,
  `salt` char(30) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `fk_users_roles_idx` (`role_id`),
  CONSTRAINT `fk_users_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-15  0:00:56
