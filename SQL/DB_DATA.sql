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
-- Dumping data for table `aircrafts`
--

LOCK TABLES `aircrafts` WRITE;
/*!40000 ALTER TABLE `aircrafts` DISABLE KEYS */;
INSERT INTO `aircrafts` VALUES (63,'AIRBUS','A320','EW-2126',1),(68,'BOING','747-800','EW-21001',1),(77,'BOING','737-500','EW-78554',1),(81,'AIRBUS','A330','EW-25433',1),(86,'BOING','B747-500','EW-22221',1),(93,'EMBRAER','E175','EW-12322',1),(94,'EMBRAER','E195','EW-21032',1);
/*!40000 ALTER TABLE `aircrafts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `airports`
--

LOCK TABLES `airports` WRITE;
/*!40000 ALTER TABLE `airports` DISABLE KEYS */;
INSERT INTO `airports` VALUES (1,'National Airport Minsk','Belarus','Minsk','MSQ'),(2,'Sheremetyevo International Airport','Russia','Moscow','SVO'),(3,'Grodno airport','Belarus','Grodno','GNA'),(7,'Mogilev Airport','Belarus','Mogilev','MVQ'),(8,'Brest Airport','Belarus','Brest','BQT'),(9,'Pulkovo Airport','Russia','St. Petersburg','LED'),(10,'Warsaw Chopin Airport','Poland','Warsaw','WAW'),(11,'Kraków John Paul II International Airport','Poland','Krakow','KRK'),(12,'Vilnius International Airport','Lithuania','Vilnius','VNO');
/*!40000 ALTER TABLE `airports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `brigades`
--

LOCK TABLES `brigades` WRITE;
/*!40000 ALTER TABLE `brigades` DISABLE KEYS */;
INSERT INTO `brigades` VALUES (1,'B737 to LED',1),(13,'B737-800 Pilotov ',1),(21,'EMBRAER JWD544 18.12.21',1),(27,'A320 17.12.21',1),(54,'E195 to krakow',1),(68,'test1123',1),(69,'A320 18.12.21 JWD202',0),(70,'JWD123 16 dec',1),(71,'JWD2022 18.12.21',0),(72,'JWD228 19.12.21',0),(73,'LED 20.12.21',0),(74,'JWD332 SVO 19.12.21',0),(75,'JWD442 21 dec',0),(78,'JWD558 22dec',0),(79,'JWD558 22dec',0),(80,'JWD630 21 dec',0),(81,'JWD860 21.12-21',0),(82,'E175 20dec',0),(83,'E175 WAW 20 dec',0);
/*!40000 ALTER TABLE `brigades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `brigades_has_users`
--

LOCK TABLES `brigades_has_users` WRITE;
/*!40000 ALTER TABLE `brigades_has_users` DISABLE KEYS */;
INSERT INTO `brigades_has_users` VALUES (13,42),(27,42),(68,42),(70,42),(71,42),(72,42),(78,42),(79,42),(81,42),(82,42),(72,44),(73,44),(74,44),(75,44),(80,44),(82,44),(72,45),(79,45),(71,48),(79,48),(80,48),(79,49),(83,49),(75,50),(80,50),(69,51),(73,51),(74,51),(82,51),(69,53),(74,53),(71,54),(72,54),(81,54),(71,55),(80,57),(79,144),(70,145),(71,145),(72,145),(79,145),(80,145),(81,145),(69,147),(75,147),(81,147),(82,147),(83,147),(78,148),(83,149),(71,150),(73,150),(74,150),(81,150),(82,150),(74,151),(75,151),(80,151),(83,151),(69,153),(74,154),(69,156),(73,156),(78,156),(82,156),(70,157),(73,157),(75,157),(80,157),(70,161),(73,161),(74,161),(78,161),(81,161),(82,161),(83,161),(83,162),(70,164),(69,167),(78,167),(81,167),(70,169),(78,175),(70,176),(72,176),(73,176),(69,181),(75,181),(13,185),(71,199),(72,199),(75,199),(80,199),(82,199),(83,199),(13,200);
/*!40000 ALTER TABLE `brigades_has_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `flights`
--

LOCK TABLES `flights` WRITE;
/*!40000 ALTER TABLE `flights` DISABLE KEYS */;
INSERT INTO `flights` VALUES (4,77,13,1,11,'JWD320','2021-11-23 13:52:00',1),(9,63,69,9,10,'JWD202','2021-12-18 14:30:00',0),(10,86,78,1,3,'JWD558','2021-12-22 14:30:00',0),(11,93,83,7,10,'JWD544','2021-12-20 08:50:00',0),(12,63,27,1,8,'JWD322','2021-11-27 14:50:00',1),(13,93,82,1,8,'JWD920','2021-12-20 14:50:00',0),(21,63,1,2,9,'JWD988','2021-12-22 14:50:00',0),(22,63,80,8,2,'JWD630','2021-12-20 22:30:00',0),(30,68,81,9,2,'JWD860','2021-12-21 14:50:00',0),(34,77,72,8,11,'JWD228','2021-12-19 14:50:00',0),(36,77,13,2,9,'JWD420','2021-11-26 19:45:00',1),(38,77,73,1,9,'JWD325','2021-12-20 10:45:00',0),(40,63,74,1,2,'JWD332','2021-12-18 21:30:00',0),(41,77,75,8,9,'JWD442','2021-12-21 10:30:00',0),(42,86,71,8,7,'JWD2022','2021-12-18 19:30:00',0),(49,68,54,1,9,'JWD-942','2021-12-12 14:30:00',1),(50,68,68,9,1,'JWD-943','2021-12-12 18:50:00',1),(51,81,1,1,12,'JWD-233','2021-12-12 20:40:00',1),(52,81,21,12,1,'JWD-234','2021-12-13 03:55:00',1),(59,77,70,10,7,'JWD123','2021-12-16 18:26:00',1);
/*!40000 ALTER TABLE `flights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'admin'),(2,'manager'),(3,'pilot'),(4,'radio engineer'),(5,'flight attendent'),(6,'navigator'),(7,'guest');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'Dmitry','Mager','admin@jwd.com','Admin','$2a$10$2DoI5slqfi4p6fN/RA9QMO5cIrcj4TXvKDXibJHyrpKJjgAhcK3ga','$2a$10$2DoI5slqfi4p6fN/RA9QMO'),(41,2,'Nike','Popov','popov@list.ru','manager','$2a$10$j/KVN9uF3xVFf0e.sgFbFuDVXi5LL3GNemQFhT2zqDMbadsY9i61e','$2a$10$j/KVN9uF3xVFf0e.sgFbFu'),(42,3,'Nike','Pilotov','pilotov@gmail.com','pilot1','$2a$10$NmoE8go6Z7Z6RSCQ/AZzS.YBa.cYZETDnDcFIwUbCJzyaNeTKINbC','$2a$10$NmoE8go6Z7Z6RSCQ/AZzS.'),(43,1,'ilya','gab','justlikeagod1@gmail.com','justlikeagod','$2a$10$0d2bNwYbzTyxLB5O8KpD0OH0g1Cvqmrv/q2AdDFXmFTpAZ/W2h8Da','$2a$10$0d2bNwYbzTyxLB5O8KpD0O'),(44,6,'Chrysa','MacCarter','cmaccarter0@buzzfeed.com','navigator1','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(45,4,'Carma','Krout','ckrout1@youtu.be','engineer2','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(46,5,'Herschel','Bartholomieu','hbartholomieu2@sohu.com','flightattendant3','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(47,2,'Amble','Algate','aalgate3@webmd.com','navigator4','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(48,5,'Armin','Peverell','apeverell4@ucoz.ru','flightattendant5','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(49,6,'Sophi','Sealand','ssealand5@furl.net','navigator6','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(50,4,'Christi','Hadcock','chadcock6@delicious.com','engineer7','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(51,5,'Rex','Cleen','rcleen7@linkedin.com','flightattendant8','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(52,6,'Guntar','Battman','gbattman8@blogger.com','navigator9','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(53,4,'Tiffi','Willshear','twillshear9@yandex.ru','engineer1','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(54,3,'Dian','Anscott','danscotta@cbslocal.com','pilot6','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(55,6,'Dallis','Sannes','dsannesb@google.fr','navigator5','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(56,6,'Ferris','Simonazzi','fsimonazzic@istockphoto.com','navigator10','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(57,5,'Dar','Crone','dcroned@vinaora.com','flightattendant9','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(143,6,'Klemens','Winkell','kwinkell2r@nba.com','navigator2','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(144,3,'Norry','Tolomio','ntolomio2s@blogs.com','pilot2','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(145,5,'Arvy','Benza','abenza2t@addtoany.com','flightattendant1','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(147,5,'Westley','Ruffler','wruffler2v@pcworld.com','flightattendant2','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(148,6,'Keenan','Meeking','kmeeking2w@themeforest.net','navigator3','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(149,3,'Vaclav','Witton','vwitton2x@dedecms.com','pilot3','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(150,4,'Conan','Indge','cindge2y@apache.org','engineer3','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(151,3,'Sara','Poxon','spoxon2z@fc2.com','pilot4','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(152,3,'Tim','Lidgley','tlidgley30@msn.com','pilot5','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(153,3,'Nina','Huckin','nhuckin31@vkontakte.ru','pilot7','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(154,3,'Silas','Rumming','srumming32@rediff.com','pilot8','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(156,3,'Mike','Drew-Clifton','mdrewclifton34@mac.com','pilot9','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(157,3,'Sinclair','Alloisi','salloisi35@home.pl','pilot10','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(160,4,'Lenore','Heckner','lheckner38@discovery.com','engineer4','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(161,5,'Lonnie','Gason','lgason39@nih.gov','flightattendant4','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(162,4,'Rowney','Hudson','rhudson3a@fda.gov','engineer5','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(164,4,'Guillermo','Vanyarkin','gvanyarkin3c@psu.edu','engineer6','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(167,6,'Coreen','McCaghan','cmccaghan3f@acquirethisname.com','navigator7','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(169,6,'Giana','Thornewell','gthornewell3h@hibu.com','navigator8','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(175,4,'Adorne','McNae','amcnae3n@discovery.com','engineer8','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(176,5,'Dora','Fresson','dfresson3o@businessinsider.com','flightattendant6','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(181,5,'Yorker','Fremantle','yfremantle3t@cbslocal.com','flightattendant7','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(185,4,'Darren','Gerrie','dgerrie3x@google.ca','engineer9','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(189,1,'Abigale','Janew','ajanew41@upenn.edu','flightattendant10','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(191,4,'Madel','Glashby','mglashby43@myspace.com','engineer10','$2a$10$LuBKW7kR613tdCDbyJSMLunOeoGBruPxtm6XFP3oU.3X6iYhhgYCa','$2a$10$LuBKW7kR613tdCDbyJSMLu'),(194,2,'Nina','Alrate','alrate@vk.ru','manager1','$2a$10$K9WpM7paVM/z4Cj34Bvxb.6jaSUe2giVLqDK9NuOc.ukG1T9jHEhq','$2a$10$K9WpM7paVM/z4Cj34Bvxb.'),(195,2,'ivan','testov','test@mail.ru','testuser1','$2a$10$0ZkVXmXcU6v7LLWBDDjJ.ulLE305Moe6bL1rwnYFgNRxr2TPCyIoO','$2a$10$0ZkVXmXcU6v7LLWBDDjJ.u'),(196,3,'Софья','Tкаченя','Sonya137@mail.ru','Sonya137','$2a$10$c2JYohM.qeF7dgr4aeg7IeAUqEDJd6rSURVzMN6iRlyowohDd22fG','$2a$10$c2JYohM.qeF7dgr4aeg7Ie'),(197,3,'Pavel','Sirotsin','pavel.sirotsin@gmail.com','Boom','$2a$10$7pcE/BRk3ktBuEJgPc2HrON6PcdviEnR8n6akR9wO2JeCT.B9.AJi','$2a$10$7pcE/BRk3ktBuEJgPc2HrO'),(199,5,'asdasd','asdasd','asdad@weq','asdasd','$2a$10$lzuNQectsgbf56VGCp0YFuwZuhPTQwPuaguV/P0TdJtDnUVcIA7Di','$2a$10$lzuNQectsgbf56VGCp0YFu'),(200,3,'adminA','adminA','admin@asd','adminA','$2a$10$i8SzAYrwge4J1wIaHZ4XOurSbX0D/HNxPnnCuVBtsPc7u2kQgtjt6','$2a$10$i8SzAYrwge4J1wIaHZ4XOu'),(201,3,'TEST','TEST','TEST@1','TESTMAIN','$2a$10$mQDs3dk8BrB4VWCHoZ43QOmGb4ISLFF3Pzj9P3xWCB.bRXcva2csO','$2a$10$mQDs3dk8BrB4VWCHoZ43QO'),(202,7,'qwer','qwer','qewr@qwe','test11111','$2a$10$j1ynRS1ANV5FOPDPh37Pjeu1Hk3eaz8KX5nsJiWfn/45xzFgnski2','$2a$10$j1ynRS1ANV5FOPDPh37Pje');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-15  0:01:42
