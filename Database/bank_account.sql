-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bank
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
  `cname` varchar(20) DEFAULT NULL,
  `pasword` varchar(20) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `ssn` varchar(14) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `dat` date DEFAULT NULL,
  `amount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'Mohi Ahmed','mohi123','Banha','male','29712171400936','01111792647','2018-04-28',1035000),(11,'Mostafa Ali Mohamed','0171430578','Banha','male','29812465348912','01153284612','2018-04-30',1124000),(12,'Mustafa Ashraf','2425028@Memo','Benha','male','29805011404453','01145165762','2018-04-30',940950),(13,'Mostafa Magdy','mostafa123','Shoubra','male','29801051400265','01144152842','2018-04-30',200000),(14,'Mostafa Reda','gedo123','Shoubra','male','29811151400587','01121472264','2018-05-01',650000),(15,'Mostafa Hassan','hassan123','Shoubra','male','29811151400524','01115795246','2018-05-01',600000),(16,'Mostafa Torky','torky123','Tanta','male','29811151400481','01115798154','2018-05-01',500000),(17,'Moaz Abdo','mezo22','Banha','male','29811151400214','01015498456','2018-05-02',250000),(18,'Mahmoud Kareme','hoda123','Banha','male','29811151400488','01152626768','2018-05-03',300000),(19,'Mohamed Ahmed','memo111','Toukh','male','28902011400252','01254982648','2018-05-03',150000),(20,'Mahmoud Rocky','rocky454','Shebin-Qanater','male','29812151400694','01025316452','2018-05-03',230000),(21,'Kareem Ayman','kemo501','Tahla','male','29810041400623','01026495423','2018-05-03',320000),(22,'Mostafa Mohamed','123456','Tanta','male','29811061400165','01124516832','2018-05-03',400000),(23,'Rodina Magdy','rody144','Cairo','female','29008111400521','01015423462','2018-05-03',450000),(24,'Samy Roshdy','1233','Giza','male','28204171400622','01125364812','2018-05-03',320550),(25,'Dody Ali','2468','Mansoura','female','28511221400621','01215264821','2018-05-03',670500),(26,'Mohamed Ragab','1357','Obour','male','29711251400578','01545832667','2018-05-03',700400),(27,'Gegy Sayed','ge456','Alex','male','27506151400651','01015487954','2018-05-03',130750),(28,'Soso Ali','9876','Matrouh','female','29312191400658','01152446975','2018-05-03',97000),(29,'Mohamed Salah','liv30','Gharbiya','male','28912141400622','01012656365','2018-05-03',980000),(30,'Youssef Mohamed','123','Banha','male','29010151400326','01022365487','2018-05-03',150600),(31,'Sayed ahmed ','1234','Cairo','male','28704161400587','01024568975','2018-05-03',165000);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-03 21:25:21
