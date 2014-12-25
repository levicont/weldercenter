CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;
-- MySQL dump 10.13  Distrib 5.5.40, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version	5.5.40-0ubuntu0.12.04.1

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
-- Table structure for table `commission_certification`
--

DROP TABLE IF EXISTS `commission_certification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commission_certification` (
  `id_commission_certification` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_teacher_head` bigint(20) DEFAULT NULL,
  `id_teacher_ndt_spec` bigint(20) DEFAULT NULL,
  `id_teacher_safety_spec` bigint(20) DEFAULT NULL,
  `id_teacher_weld_spec` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_commission_certification`),
  KEY `FK21B62C668757B92D` (`id_teacher_safety_spec`),
  KEY `FK21B62C66452E0663` (`id_teacher_ndt_spec`),
  KEY `FK21B62C6685DDE6E7` (`id_teacher_head`),
  KEY `FK21B62C66F91AC379` (`id_teacher_weld_spec`),
  CONSTRAINT `FK21B62C66F91AC379` FOREIGN KEY (`id_teacher_weld_spec`) REFERENCES `teacher` (`id_teacher`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK21B62C66452E0663` FOREIGN KEY (`id_teacher_ndt_spec`) REFERENCES `teacher` (`id_teacher`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK21B62C6685DDE6E7` FOREIGN KEY (`id_teacher_head`) REFERENCES `teacher` (`id_teacher`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK21B62C668757B92D` FOREIGN KEY (`id_teacher_safety_spec`) REFERENCES `teacher` (`id_teacher`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commission_certification`
--

LOCK TABLES `commission_certification` WRITE;
/*!40000 ALTER TABLE `commission_certification` DISABLE KEYS */;
INSERT INTO `commission_certification` VALUES (1,2,3,4,1);
/*!40000 ALTER TABLE `commission_certification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum`
--

DROP TABLE IF EXISTS `curriculum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum` (
  `id_curriculum` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_curriculum`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum`
--

LOCK TABLES `curriculum` WRITE;
/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
INSERT INTO `curriculum` VALUES (1,'Программа предаттестационной подготовки сварщиков 24 часа','Подготовка 24 часа');
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curriculum_section`
--

DROP TABLE IF EXISTS `curriculum_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curriculum_section` (
  `id_section` bigint(20) NOT NULL,
  `id_curriculum` bigint(20) NOT NULL,
  KEY `FK56B956E17CD5F956` (`id_curriculum`),
  KEY `FK56B956E1DC6CE8EA` (`id_section`),
  CONSTRAINT `FK56B956E1DC6CE8EA` FOREIGN KEY (`id_section`) REFERENCES `section` (`id_section`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK56B956E17CD5F956` FOREIGN KEY (`id_curriculum`) REFERENCES `curriculum` (`id_curriculum`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curriculum_section`
--

LOCK TABLES `curriculum_section` WRITE;
/*!40000 ALTER TABLE `curriculum_section` DISABLE KEYS */;
INSERT INTO `curriculum_section` VALUES (1,1),(2,1);
/*!40000 ALTER TABLE `curriculum_section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education`
--

DROP TABLE IF EXISTS `education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education` (
  `id_education` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_education`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education`
--

LOCK TABLES `education` WRITE;
/*!40000 ALTER TABLE `education` DISABLE KEYS */;
INSERT INTO `education` VALUES (1,'Высшее');
/*!40000 ALTER TABLE `education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electrode`
--

DROP TABLE IF EXISTS `electrode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `electrode` (
  `id_electrode` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_electrode`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electrode`
--

LOCK TABLES `electrode` WRITE;
/*!40000 ALTER TABLE `electrode` DISABLE KEYS */;
INSERT INTO `electrode` VALUES (1,'АНО-21'),(2,'УОНИ'),(3,'none');
/*!40000 ALTER TABLE `electrode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluation` (
  `id_evaluation` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_evaluation`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation`
--

LOCK TABLES `evaluation` WRITE;
/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
INSERT INTO `evaluation` VALUES (1,'Годен'),(2,'Не годен');
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job` (
  `id_job` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_job`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,'Электросварщик'),(2,'Газосварщик'),(3,'Электрогазосварщик'),(4,'Аргонщик');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journal`
--

DROP TABLE IF EXISTS `journal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journal` (
  `id_journal` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_begin` date DEFAULT NULL,
  `date_end` date DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `id_curriculum` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_journal`),
  KEY `FKAB64AF377CD5F956` (`id_curriculum`),
  CONSTRAINT `FKAB64AF377CD5F956` FOREIGN KEY (`id_curriculum`) REFERENCES `curriculum` (`id_curriculum`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journal`
--

LOCK TABLES `journal` WRITE;
/*!40000 ALTER TABLE `journal` DISABLE KEYS */;
INSERT INTO `journal` VALUES (1,'2014-11-04','2014-11-04','14-001',1);
/*!40000 ALTER TABLE `journal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journal_teacher`
--

DROP TABLE IF EXISTS `journal_teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journal_teacher` (
  `id_teacher` bigint(20) NOT NULL,
  `id_journal` bigint(20) NOT NULL,
  KEY `FKE0BAFADA4857498E` (`id_journal`),
  KEY `FKE0BAFADA45F182E4` (`id_teacher`),
  CONSTRAINT `FKE0BAFADA45F182E4` FOREIGN KEY (`id_teacher`) REFERENCES `teacher` (`id_teacher`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKE0BAFADA4857498E` FOREIGN KEY (`id_journal`) REFERENCES `journal` (`id_journal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journal_teacher`
--

LOCK TABLES `journal_teacher` WRITE;
/*!40000 ALTER TABLE `journal_teacher` DISABLE KEYS */;
INSERT INTO `journal_teacher` VALUES (1,1),(2,1);
/*!40000 ALTER TABLE `journal_teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journal_welder`
--

DROP TABLE IF EXISTS `journal_welder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journal_welder` (
  `id_journal` bigint(20) NOT NULL,
  `id_welder` bigint(20) NOT NULL,
  KEY `FK2D6BA5FB5F1D58C6` (`id_welder`),
  KEY `FK2D6BA5FB4857498E` (`id_journal`),
  CONSTRAINT `FK2D6BA5FB4857498E` FOREIGN KEY (`id_journal`) REFERENCES `journal` (`id_journal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK2D6BA5FB5F1D58C6` FOREIGN KEY (`id_welder`) REFERENCES `welder` (`id_welder`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journal_welder`
--

LOCK TABLES `journal_welder` WRITE;
/*!40000 ALTER TABLE `journal_welder` DISABLE KEYS */;
INSERT INTO `journal_welder` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `journal_welder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mechanical_test`
--

DROP TABLE IF EXISTS `mechanical_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mechanical_test` (
  `id_mechanical_test` bigint(20) NOT NULL AUTO_INCREMENT,
  `angle` double DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `prot_date` date DEFAULT NULL,
  `id_evaluation` bigint(20) DEFAULT NULL,
  `id_weld_pattern` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_mechanical_test`),
  KEY `FKE3188822D7AE25E9` (`id_weld_pattern`),
  KEY `FKE3188822588CCF58` (`id_evaluation`),
  CONSTRAINT `FKE3188822588CCF58` FOREIGN KEY (`id_evaluation`) REFERENCES `evaluation` (`id_evaluation`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKE3188822D7AE25E9` FOREIGN KEY (`id_weld_pattern`) REFERENCES `weld_pattern` (`id_weld_pattern`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mechanical_test`
--

LOCK TABLES `mechanical_test` WRITE;
/*!40000 ALTER TABLE `mechanical_test` DISABLE KEYS */;
INSERT INTO `mechanical_test` VALUES (1,120,'14-002Mech','2014-11-04',1,1);
/*!40000 ALTER TABLE `mechanical_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ndt_document`
--

DROP TABLE IF EXISTS `ndt_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ndt_document` (
  `id_ndt_document` bigint(20) NOT NULL AUTO_INCREMENT,
  `full_name` text,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_ndt_document`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ndt_document`
--

LOCK TABLES `ndt_document` WRITE;
/*!40000 ALTER TABLE `ndt_document` DISABLE KEYS */;
INSERT INTO `ndt_document` VALUES (1,'Газоснабжение','ДБН В.2.5-20-2001'),(2,'Сосуды работающие под давлением','НПАОП 0.00-1.07-96');
/*!40000 ALTER TABLE `ndt_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization`
--

DROP TABLE IF EXISTS `organization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization` (
  `id_organization` bigint(20) NOT NULL AUTO_INCREMENT,
  `adress` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_organization`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization`
--

LOCK TABLES `organization` WRITE;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` VALUES (1,'г. Харьков, пр. Косиора, 100','ООО \"Новые технологии\"','+380 57 715 13 28'),(2,'г. Харьков, ул. Светлая, 16','ООО \"Газавтоматика\"','+380 57 714 58 96');
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pattern_diameter`
--

DROP TABLE IF EXISTS `pattern_diameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pattern_diameter` (
  `id_pattern_diameter` bigint(20) NOT NULL AUTO_INCREMENT,
  `diameter` double DEFAULT NULL,
  PRIMARY KEY (`id_pattern_diameter`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pattern_diameter`
--

LOCK TABLES `pattern_diameter` WRITE;
/*!40000 ALTER TABLE `pattern_diameter` DISABLE KEYS */;
INSERT INTO `pattern_diameter` VALUES (1,57),(2,76);
/*!40000 ALTER TABLE `pattern_diameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pattern_thickness`
--

DROP TABLE IF EXISTS `pattern_thickness`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pattern_thickness` (
  `id_pattern_thickness` bigint(20) NOT NULL AUTO_INCREMENT,
  `thickness` double DEFAULT NULL,
  PRIMARY KEY (`id_pattern_thickness`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pattern_thickness`
--

LOCK TABLES `pattern_thickness` WRITE;
/*!40000 ALTER TABLE `pattern_thickness` DISABLE KEYS */;
INSERT INTO `pattern_thickness` VALUES (1,2),(2,5);
/*!40000 ALTER TABLE `pattern_thickness` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_protocol`
--

DROP TABLE IF EXISTS `personal_protocol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_protocol` (
  `id_personal_protocol` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_periodical_cert` date DEFAULT NULL,
  `id_commission_certification` bigint(20) DEFAULT NULL,
  `id_journal` bigint(20) DEFAULT NULL,
  `id_resolution_certification` bigint(20) DEFAULT NULL,
  `id_theory_test` bigint(20) DEFAULT NULL,
  `id_welder` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_personal_protocol`),
  KEY `FKC06CA1B75F1D58C6` (`id_welder`),
  KEY `FKC06CA1B7352A2EDD` (`id_commission_certification`),
  KEY `FKC06CA1B74857498E` (`id_journal`),
  KEY `FKC06CA1B7DFC6EABD` (`id_resolution_certification`),
  KEY `FKC06CA1B7C9E9C4DB` (`id_theory_test`),
  CONSTRAINT `FKC06CA1B7C9E9C4DB` FOREIGN KEY (`id_theory_test`) REFERENCES `theory_test` (`id_theory_test`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKC06CA1B7352A2EDD` FOREIGN KEY (`id_commission_certification`) REFERENCES `commission_certification` (`id_commission_certification`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKC06CA1B74857498E` FOREIGN KEY (`id_journal`) REFERENCES `journal` (`id_journal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKC06CA1B75F1D58C6` FOREIGN KEY (`id_welder`) REFERENCES `welder` (`id_welder`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKC06CA1B7DFC6EABD` FOREIGN KEY (`id_resolution_certification`) REFERENCES `resolution_certification` (`id_resolution_certification`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_protocol`
--

LOCK TABLES `personal_protocol` WRITE;
/*!40000 ALTER TABLE `personal_protocol` DISABLE KEYS */;
INSERT INTO `personal_protocol` VALUES (1,'2014-11-04',1,1,1,1,1);
/*!40000 ALTER TABLE `personal_protocol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_protocol_ndt_document`
--

DROP TABLE IF EXISTS `personal_protocol_ndt_document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_protocol_ndt_document` (
  `id_personal_protocol` bigint(20) NOT NULL,
  `id_ndt_document` bigint(20) NOT NULL,
  KEY `FK24BFB0447B944AC7` (`id_personal_protocol`),
  KEY `FK24BFB0445FB8245D` (`id_ndt_document`),
  CONSTRAINT `FK24BFB0445FB8245D` FOREIGN KEY (`id_ndt_document`) REFERENCES `ndt_document` (`id_ndt_document`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK24BFB0447B944AC7` FOREIGN KEY (`id_personal_protocol`) REFERENCES `personal_protocol` (`id_personal_protocol`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_protocol_ndt_document`
--

LOCK TABLES `personal_protocol_ndt_document` WRITE;
/*!40000 ALTER TABLE `personal_protocol_ndt_document` DISABLE KEYS */;
INSERT INTO `personal_protocol_ndt_document` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `personal_protocol_ndt_document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qualification`
--

DROP TABLE IF EXISTS `qualification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qualification` (
  `id_qualification` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_qualification`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qualification`
--

LOCK TABLES `qualification` WRITE;
/*!40000 ALTER TABLE `qualification` DISABLE KEYS */;
INSERT INTO `qualification` VALUES (1,'Электросварщик'),(2,'Электрогазосварщик'),(3,'Газосварщик');
/*!40000 ALTER TABLE `qualification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `radiation_test`
--

DROP TABLE IF EXISTS `radiation_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `radiation_test` (
  `id_radiation_test` bigint(20) NOT NULL AUTO_INCREMENT,
  `defects` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `prot_date` date DEFAULT NULL,
  `sensitivty` varchar(255) DEFAULT NULL,
  `id_evaluation` bigint(20) DEFAULT NULL,
  `id_weld_pattern` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_radiation_test`),
  KEY `FK12C67D90D7AE25E9` (`id_weld_pattern`),
  KEY `FK12C67D90588CCF58` (`id_evaluation`),
  CONSTRAINT `FK12C67D90588CCF58` FOREIGN KEY (`id_evaluation`) REFERENCES `evaluation` (`id_evaluation`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK12C67D90D7AE25E9` FOREIGN KEY (`id_weld_pattern`) REFERENCES `weld_pattern` (`id_weld_pattern`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `radiation_test`
--

LOCK TABLES `radiation_test` WRITE;
/*!40000 ALTER TABLE `radiation_test` DISABLE KEYS */;
INSERT INTO `radiation_test` VALUES (1,'2П0,8; 2Ш3х4; Нк25','14-001','2014-11-04','0.2',2,1);
/*!40000 ALTER TABLE `radiation_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resolution_certification`
--

DROP TABLE IF EXISTS `resolution_certification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resolution_certification` (
  `id_resolution_certification` bigint(20) NOT NULL AUTO_INCREMENT,
  `text_resolution` text,
  PRIMARY KEY (`id_resolution_certification`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resolution_certification`
--

LOCK TABLES `resolution_certification` WRITE;
/*!40000 ALTER TABLE `resolution_certification` DISABLE KEYS */;
INSERT INTO `resolution_certification` VALUES (1,'Сварщик сертифицирован на работы повышенной опасности');
/*!40000 ALTER TABLE `resolution_certification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `id_section` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text,
  `order_index` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_section`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (1,'Теория сварочного дела',1,'Теория'),(2,'Практика сварочного дела',2,'Практика');
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section_topic`
--

DROP TABLE IF EXISTS `section_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section_topic` (
  `id_topic` bigint(20) NOT NULL,
  `id_section` bigint(20) NOT NULL,
  KEY `FKB1FF2F55D97BCD7E` (`id_topic`),
  KEY `FKB1FF2F55DC6CE8EA` (`id_section`),
  CONSTRAINT `FKB1FF2F55DC6CE8EA` FOREIGN KEY (`id_section`) REFERENCES `section` (`id_section`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKB1FF2F55D97BCD7E` FOREIGN KEY (`id_topic`) REFERENCES `topic` (`id_topic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section_topic`
--

LOCK TABLES `section_topic` WRITE;
/*!40000 ALTER TABLE `section_topic` DISABLE KEYS */;
INSERT INTO `section_topic` VALUES (1,1),(2,1),(3,2);
/*!40000 ALTER TABLE `section_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `steel_group`
--

DROP TABLE IF EXISTS `steel_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `steel_group` (
  `id_steel_group` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text,
  `st_group` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_steel_group`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `steel_group`
--

LOCK TABLES `steel_group` WRITE;
/*!40000 ALTER TABLE `steel_group` DISABLE KEYS */;
INSERT INTO `steel_group` VALUES (1,'Углеродистые стали','W1');
/*!40000 ALTER TABLE `steel_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `steel_type`
--

DROP TABLE IF EXISTS `steel_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `steel_type` (
  `id_steel_type` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `id_steel_group` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_steel_type`),
  KEY `FK8468DD2E1154C497` (`id_steel_group`),
  CONSTRAINT `FK8468DD2E1154C497` FOREIGN KEY (`id_steel_group`) REFERENCES `steel_group` (`id_steel_group`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `steel_type`
--

LOCK TABLES `steel_type` WRITE;
/*!40000 ALTER TABLE `steel_type` DISABLE KEYS */;
INSERT INTO `steel_type` VALUES (1,'Сталь 20',1),(2,'Ст. 2пс',NULL);
/*!40000 ALTER TABLE `steel_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `id_teacher` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `secname` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_teacher`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'Александр','Владимирович','Ашукин'),(2,'Андрей','Анатольевич','Чуйко'),(3,'Дмитрий','Анатольевич','Крапива'),(4,'Светлана','Александровна','Касьяненко');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theory_test`
--

DROP TABLE IF EXISTS `theory_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `theory_test` (
  `id_theory_test` bigint(20) NOT NULL AUTO_INCREMENT,
  `rating` varchar(255) DEFAULT NULL,
  `ticket_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_theory_test`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theory_test`
--

LOCK TABLES `theory_test` WRITE;
/*!40000 ALTER TABLE `theory_test` DISABLE KEYS */;
INSERT INTO `theory_test` VALUES (1,'Сдано','25');
/*!40000 ALTER TABLE `theory_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `topic` (
  `id_topic` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text,
  `order_index` int(11) DEFAULT NULL,
  `timelong` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_topic`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'Сущность процессов, напряжения и деформации при сварке, понятия и показатели свариваемости',1,6,'Основы сварки плавлением'),(2,'Классификация, положения при сварке, зачистка кромок',2,4,'Сварные соединения и швы'),(3,'Практические занятия по сварке различных образцов',3,12,'Практические занятия');
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visual_test`
--

DROP TABLE IF EXISTS `visual_test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visual_test` (
  `id_visual_test` bigint(20) NOT NULL AUTO_INCREMENT,
  `defects` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `prot_date` date DEFAULT NULL,
  `id_evaluation` bigint(20) DEFAULT NULL,
  `id_weld_pattern` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_visual_test`),
  KEY `FKB129FE31D7AE25E9` (`id_weld_pattern`),
  KEY `FKB129FE31588CCF58` (`id_evaluation`),
  CONSTRAINT `FKB129FE31588CCF58` FOREIGN KEY (`id_evaluation`) REFERENCES `evaluation` (`id_evaluation`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKB129FE31D7AE25E9` FOREIGN KEY (`id_weld_pattern`) REFERENCES `weld_pattern` (`id_weld_pattern`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visual_test`
--

LOCK TABLES `visual_test` WRITE;
/*!40000 ALTER TABLE `visual_test` DISABLE KEYS */;
INSERT INTO `visual_test` VALUES (1,'ДНО','14-001vt','2014-11-04',1,1);
/*!40000 ALTER TABLE `visual_test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weld_detail`
--

DROP TABLE IF EXISTS `weld_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weld_detail` (
  `id_weld_detail` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_weld_detail`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weld_detail`
--

LOCK TABLES `weld_detail` WRITE;
/*!40000 ALTER TABLE `weld_detail` DISABLE KEYS */;
INSERT INTO `weld_detail` VALUES (1,'P','Пластина'),(2,'T','Труба');
/*!40000 ALTER TABLE `weld_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weld_gas`
--

DROP TABLE IF EXISTS `weld_gas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weld_gas` (
  `id_weld_gas` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_weld_gas`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weld_gas`
--

LOCK TABLES `weld_gas` WRITE;
/*!40000 ALTER TABLE `weld_gas` DISABLE KEYS */;
INSERT INTO `weld_gas` VALUES (1,'CO2'),(2,'C2Н2'),(3,'Ar');
/*!40000 ALTER TABLE `weld_gas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weld_method`
--

DROP TABLE IF EXISTS `weld_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weld_method` (
  `id_weld_method` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_weld_method`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weld_method`
--

LOCK TABLES `weld_method` WRITE;
/*!40000 ALTER TABLE `weld_method` DISABLE KEYS */;
INSERT INTO `weld_method` VALUES (1,'111','РДЭ'),(2,'135','ГС'),(3,'141','ВИГ');
/*!40000 ALTER TABLE `weld_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weld_pattern`
--

DROP TABLE IF EXISTS `weld_pattern`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weld_pattern` (
  `id_weld_pattern` bigint(20) NOT NULL AUTO_INCREMENT,
  `diameter` double DEFAULT NULL,
  `is_heat_treatment` tinyint(1) DEFAULT NULL,
  `is_heating` tinyint(1) DEFAULT NULL,
  `mark` varchar(255) DEFAULT NULL,
  `thickness` double DEFAULT NULL,
  `id_electrode` bigint(20) DEFAULT NULL,
  `id_mechanical_test` bigint(20) DEFAULT NULL,
  `id_personal_protocol` bigint(20) DEFAULT NULL,
  `id_radiation_test` bigint(20) DEFAULT NULL,
  `id_steel_type` bigint(20) DEFAULT NULL,
  `id_visual_test` bigint(20) DEFAULT NULL,
  `id_weld_detail` bigint(20) DEFAULT NULL,
  `id_weld_gas` bigint(20) DEFAULT NULL,
  `id_weld_method` bigint(20) DEFAULT NULL,
  `id_weld_position` bigint(20) DEFAULT NULL,
  `id_weld_wire` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_weld_pattern`),
  KEY `FKA8EE0D17291D9D2B` (`id_radiation_test`),
  KEY `FKA8EE0D177B944AC7` (`id_personal_protocol`),
  KEY `FKA8EE0D17FC0038FB` (`id_weld_gas`),
  KEY `FKA8EE0D17941E64B9` (`id_weld_detail`),
  KEY `FKA8EE0D17E2B2BCA9` (`id_weld_position`),
  KEY `FKA8EE0D17B2D5D5D9` (`id_weld_method`),
  KEY `FKA8EE0D17245C603B` (`id_visual_test`),
  KEY `FKA8EE0D17A5C46FBB` (`id_steel_type`),
  KEY `FKA8EE0D17427C121B` (`id_mechanical_test`),
  KEY `FKA8EE0D178677AE3A` (`id_electrode`),
  KEY `FKA8EE0D178415AEE1` (`id_weld_wire`),
  CONSTRAINT `FKA8EE0D178415AEE1` FOREIGN KEY (`id_weld_wire`) REFERENCES `weld_wire` (`id_weld_wire`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D17245C603B` FOREIGN KEY (`id_visual_test`) REFERENCES `visual_test` (`id_visual_test`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D17291D9D2B` FOREIGN KEY (`id_radiation_test`) REFERENCES `radiation_test` (`id_radiation_test`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D17427C121B` FOREIGN KEY (`id_mechanical_test`) REFERENCES `mechanical_test` (`id_mechanical_test`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D177B944AC7` FOREIGN KEY (`id_personal_protocol`) REFERENCES `personal_protocol` (`id_personal_protocol`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D178677AE3A` FOREIGN KEY (`id_electrode`) REFERENCES `electrode` (`id_electrode`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D17941E64B9` FOREIGN KEY (`id_weld_detail`) REFERENCES `weld_detail` (`id_weld_detail`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D17A5C46FBB` FOREIGN KEY (`id_steel_type`) REFERENCES `steel_type` (`id_steel_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D17B2D5D5D9` FOREIGN KEY (`id_weld_method`) REFERENCES `weld_method` (`id_weld_method`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D17E2B2BCA9` FOREIGN KEY (`id_weld_position`) REFERENCES `weld_position` (`id_weld_position`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D17FC0038FB` FOREIGN KEY (`id_weld_gas`) REFERENCES `weld_gas` (`id_weld_gas`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weld_pattern`
--

LOCK TABLES `weld_pattern` WRITE;
/*!40000 ALTER TABLE `weld_pattern` DISABLE KEYS */;
INSERT INTO `weld_pattern` VALUES (1,57,0,0,'14-001',2,3,1,1,1,1,1,1,1,2,1,1);
/*!40000 ALTER TABLE `weld_pattern` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weld_position`
--

DROP TABLE IF EXISTS `weld_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weld_position` (
  `id_weld_position` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_weld_position`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weld_position`
--

LOCK TABLES `weld_position` WRITE;
/*!40000 ALTER TABLE `weld_position` DISABLE KEYS */;
INSERT INTO `weld_position` VALUES (1,'PA','Нижнее'),(2,'PF','Вертикальное');
/*!40000 ALTER TABLE `weld_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weld_wire`
--

DROP TABLE IF EXISTS `weld_wire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weld_wire` (
  `id_weld_wire` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_weld_wire`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weld_wire`
--

LOCK TABLES `weld_wire` WRITE;
/*!40000 ALTER TABLE `weld_wire` DISABLE KEYS */;
INSERT INTO `weld_wire` VALUES (1,'09ГС'),(2,'09Г2С');
/*!40000 ALTER TABLE `weld_wire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `welder`
--

DROP TABLE IF EXISTS `welder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `welder` (
  `id_welder` bigint(20) NOT NULL AUTO_INCREMENT,
  `birthday` date DEFAULT NULL,
  `date_begin` date DEFAULT NULL,
  `doc_number` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `secname` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `id_education` bigint(20) DEFAULT NULL,
  `id_job` bigint(20) DEFAULT NULL,
  `id_organization` bigint(20) DEFAULT NULL,
  `id_qualification` bigint(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_welder`),
  KEY `FKD0D293F3C491E346` (`id_organization`),
  KEY `FKD0D293F36A83B29A` (`id_job`),
  KEY `FKD0D293F37DB64EFE` (`id_qualification`),
  KEY `FKD0D293F3A3167270` (`id_education`),
  CONSTRAINT `FKD0D293F3A3167270` FOREIGN KEY (`id_education`) REFERENCES `education` (`id_education`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKD0D293F36A83B29A` FOREIGN KEY (`id_job`) REFERENCES `job` (`id_job`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKD0D293F37DB64EFE` FOREIGN KEY (`id_qualification`) REFERENCES `qualification` (`id_qualification`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKD0D293F3C491E346` FOREIGN KEY (`id_organization`) REFERENCES `organization` (`id_organization`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `welder`
--

LOCK TABLES `welder` WRITE;
/*!40000 ALTER TABLE `welder` DISABLE KEYS */;
INSERT INTO `welder` VALUES (1,'2014-11-04','2014-11-04','XA-1620','Иван','Дорофеевич','Добрынин',1,3,1,1,'г. Киев, ул. Новая, 45'),(2,'2014-11-04','2014-11-04','XA-1626','Петр','Ильич','Петковский',1,4,2,3,'г. Харьков, ул. Зубарева, 50');
/*!40000 ALTER TABLE `welder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `welder_weld_method`
--

DROP TABLE IF EXISTS `welder_weld_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `welder_weld_method` (
  `id_welder` bigint(20) NOT NULL,
  `id_weld_method` bigint(20) NOT NULL,
  KEY `FKBD2C802E5F1D58C6` (`id_welder`),
  KEY `FKBD2C802EB2D5D5D9` (`id_weld_method`),
  CONSTRAINT `FKBD2C802EB2D5D5D9` FOREIGN KEY (`id_weld_method`) REFERENCES `weld_method` (`id_weld_method`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKBD2C802E5F1D58C6` FOREIGN KEY (`id_welder`) REFERENCES `welder` (`id_welder`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `welder_weld_method`
--

LOCK TABLES `welder_weld_method` WRITE;
/*!40000 ALTER TABLE `welder_weld_method` DISABLE KEYS */;
INSERT INTO `welder_weld_method` VALUES (1,1),(2,1),(1,2),(2,3);
/*!40000 ALTER TABLE `welder_weld_method` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-04  8:37:15
