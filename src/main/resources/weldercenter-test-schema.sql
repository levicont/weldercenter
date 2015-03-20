-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.36-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema test
--

CREATE DATABASE IF NOT EXISTS test;
USE test;

--
-- Definition of table `city`
--

DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id_city` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `country` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id_city`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=cp1251 COLLATE=cp1251_ukrainian_ci;

--
-- Dumping data for table `city`
--

/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` (`id_city`,`name`,`country`) VALUES
 (1,'Харьков','Украина'),
 (2,'Солихард','Россия');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;


--
-- Definition of table `commission_certification`
--

DROP TABLE IF EXISTS `commission_certification`;
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
  CONSTRAINT `FK21B62C66452E0663` FOREIGN KEY (`id_teacher_ndt_spec`) REFERENCES `teacher` (`id_teacher`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK21B62C6685DDE6E7` FOREIGN KEY (`id_teacher_head`) REFERENCES `teacher` (`id_teacher`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK21B62C668757B92D` FOREIGN KEY (`id_teacher_safety_spec`) REFERENCES `teacher` (`id_teacher`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK21B62C66F91AC379` FOREIGN KEY (`id_teacher_weld_spec`) REFERENCES `teacher` (`id_teacher`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `commission_certification`
--

/*!40000 ALTER TABLE `commission_certification` DISABLE KEYS */;
INSERT INTO `commission_certification` (`id_commission_certification`,`id_teacher_head`,`id_teacher_ndt_spec`,`id_teacher_safety_spec`,`id_teacher_weld_spec`) VALUES
 (1,2,3,4,1);
/*!40000 ALTER TABLE `commission_certification` ENABLE KEYS */;


--
-- Definition of table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `id_company` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `adress` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `city` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id_company`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=cp1251 COLLATE=cp1251_ukrainian_ci;

--
-- Dumping data for table `company`
--

/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`id_company`,`name`,`adress`,`city`) VALUES
 (1,'Нефтьстрой ОАО','ул. Пушкина, 13',NULL),
 (2,'Газконтакт','ул. Вавилова, 16',NULL);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;


--
-- Definition of table `curriculum`
--

DROP TABLE IF EXISTS `curriculum`;
CREATE TABLE `curriculum` (
  `id_curriculum` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_curriculum`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `curriculum`
--

/*!40000 ALTER TABLE `curriculum` DISABLE KEYS */;
INSERT INTO `curriculum` (`id_curriculum`,`description`,`title`) VALUES
 (1,'Программа предаттестационной подготовки сварщиков 24 часа','Подготовка 24 часа');
/*!40000 ALTER TABLE `curriculum` ENABLE KEYS */;


--
-- Definition of table `curriculum_section`
--

DROP TABLE IF EXISTS `curriculum_section`;
CREATE TABLE `curriculum_section` (
  `id_section` bigint(20) NOT NULL,
  `id_curriculum` bigint(20) NOT NULL,
  KEY `FK56B956E17CD5F956` (`id_curriculum`),
  KEY `FK56B956E1DC6CE8EA` (`id_section`),
  CONSTRAINT `FK56B956E17CD5F956` FOREIGN KEY (`id_curriculum`) REFERENCES `curriculum` (`id_curriculum`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK56B956E1DC6CE8EA` FOREIGN KEY (`id_section`) REFERENCES `section` (`id_section`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `curriculum_section`
--

/*!40000 ALTER TABLE `curriculum_section` DISABLE KEYS */;
INSERT INTO `curriculum_section` (`id_section`,`id_curriculum`) VALUES
 (1,1),
 (2,1);
/*!40000 ALTER TABLE `curriculum_section` ENABLE KEYS */;


--
-- Definition of table `date_test`
--

DROP TABLE IF EXISTS `date_test`;
CREATE TABLE `date_test` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `date_test`
--

/*!40000 ALTER TABLE `date_test` DISABLE KEYS */;
INSERT INTO `date_test` (`id`,`date`) VALUES
 (1,'2001-01-01'),
 (2,'2001-01-30'),
 (3,'2001-01-30'),
 (4,'2012-05-10'),
 (5,'2012-05-24'),
 (6,'1984-10-19');
/*!40000 ALTER TABLE `date_test` ENABLE KEYS */;


--
-- Definition of table `education`
--

DROP TABLE IF EXISTS `education`;
CREATE TABLE `education` (
  `id_education` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_education`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `education`
--

/*!40000 ALTER TABLE `education` DISABLE KEYS */;
INSERT INTO `education` (`id_education`,`type`) VALUES
 (1,'Высшее');
/*!40000 ALTER TABLE `education` ENABLE KEYS */;


--
-- Definition of table `electrode`
--

DROP TABLE IF EXISTS `electrode`;
CREATE TABLE `electrode` (
  `id_electrode` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_electrode`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `electrode`
--

/*!40000 ALTER TABLE `electrode` DISABLE KEYS */;
INSERT INTO `electrode` (`id_electrode`,`type`) VALUES
 (1,'АНО-21'),
 (2,'УОНИ'),
 (3,'ЦУ-5');
/*!40000 ALTER TABLE `electrode` ENABLE KEYS */;


--
-- Definition of table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE `evaluation` (
  `id_evaluation` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_evaluation`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `evaluation`
--

/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
INSERT INTO `evaluation` (`id_evaluation`,`type`) VALUES
 (1,'Годен'),
 (2,'Не годен');
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;


--
-- Definition of table `job`
--

DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id_job` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_job`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `job`
--

/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` (`id_job`,`name`) VALUES
 (1,'Электросварщик'),
 (2,'Газосварщик'),
 (3,'Электрогазосварщик'),
 (4,'Аргонщик');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;


--
-- Definition of table `journal`
--

DROP TABLE IF EXISTS `journal`;
CREATE TABLE `journal` (
  `id_journal` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_begin` date DEFAULT NULL,
  `date_end` date DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `id_curriculum` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_journal`),
  KEY `FKAB64AF377CD5F956` (`id_curriculum`),
  CONSTRAINT `FKAB64AF377CD5F956` FOREIGN KEY (`id_curriculum`) REFERENCES `curriculum` (`id_curriculum`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `journal`
--

/*!40000 ALTER TABLE `journal` DISABLE KEYS */;
INSERT INTO `journal` (`id_journal`,`date_begin`,`date_end`,`number`,`id_curriculum`) VALUES
 (1,'2014-11-04','2014-11-04','14-001',1),
 (7,'2015-01-05','2015-01-08','15-001',1),
 (8,'2015-01-12','2015-01-14','15-002',1),
 (9,'2015-01-19','2015-01-21','15-003',1),
 (10,'2015-01-26','2015-01-28','15-004',1);
/*!40000 ALTER TABLE `journal` ENABLE KEYS */;


--
-- Definition of table `journal_teacher`
--

DROP TABLE IF EXISTS `journal_teacher`;
CREATE TABLE `journal_teacher` (
  `id_teacher` bigint(20) NOT NULL,
  `id_journal` bigint(20) NOT NULL,
  KEY `FKE0BAFADA4857498E` (`id_journal`),
  KEY `FKE0BAFADA45F182E4` (`id_teacher`),
  CONSTRAINT `FKE0BAFADA45F182E4` FOREIGN KEY (`id_teacher`) REFERENCES `teacher` (`id_teacher`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKE0BAFADA4857498E` FOREIGN KEY (`id_journal`) REFERENCES `journal` (`id_journal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `journal_teacher`
--

/*!40000 ALTER TABLE `journal_teacher` DISABLE KEYS */;
INSERT INTO `journal_teacher` (`id_teacher`,`id_journal`) VALUES
 (1,1),
 (2,1),
 (1,8),
 (2,8),
 (2,9),
 (3,9),
 (1,10),
 (2,7);
/*!40000 ALTER TABLE `journal_teacher` ENABLE KEYS */;


--
-- Definition of table `journal_welder`
--

DROP TABLE IF EXISTS `journal_welder`;
CREATE TABLE `journal_welder` (
  `id_journal` bigint(20) NOT NULL,
  `id_welder` bigint(20) NOT NULL,
  KEY `FK2D6BA5FB5F1D58C6` (`id_welder`),
  KEY `FK2D6BA5FB4857498E` (`id_journal`),
  CONSTRAINT `FK2D6BA5FB4857498E` FOREIGN KEY (`id_journal`) REFERENCES `journal` (`id_journal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK2D6BA5FB5F1D58C6` FOREIGN KEY (`id_welder`) REFERENCES `welder` (`id_welder`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `journal_welder`
--

/*!40000 ALTER TABLE `journal_welder` DISABLE KEYS */;
INSERT INTO `journal_welder` (`id_journal`,`id_welder`) VALUES
 (1,1),
 (1,2),
 (8,1),
 (9,1),
 (9,2),
 (10,1),
 (10,2),
 (7,2),
 (7,4);
/*!40000 ALTER TABLE `journal_welder` ENABLE KEYS */;


--
-- Definition of table `mechanical_test`
--

DROP TABLE IF EXISTS `mechanical_test`;
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

--
-- Dumping data for table `mechanical_test`
--

/*!40000 ALTER TABLE `mechanical_test` DISABLE KEYS */;
INSERT INTO `mechanical_test` (`id_mechanical_test`,`angle`,`number`,`prot_date`,`id_evaluation`,`id_weld_pattern`) VALUES
 (1,120,'14-002Mech','2014-11-04',1,1);
/*!40000 ALTER TABLE `mechanical_test` ENABLE KEYS */;


--
-- Definition of table `method`
--

DROP TABLE IF EXISTS `method`;
CREATE TABLE `method` (
  `id_method` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id_method`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=cp1251 COLLATE=cp1251_ukrainian_ci;

--
-- Dumping data for table `method`
--

/*!40000 ALTER TABLE `method` DISABLE KEYS */;
INSERT INTO `method` (`id_method`,`name`) VALUES
 (1,'VT'),
 (2,'PT'),
 (3,'RT'),
 (4,'UT'),
 (5,'MT');
/*!40000 ALTER TABLE `method` ENABLE KEYS */;


--
-- Definition of table `ndt_document`
--

DROP TABLE IF EXISTS `ndt_document`;
CREATE TABLE `ndt_document` (
  `id_ndt_document` bigint(20) NOT NULL AUTO_INCREMENT,
  `full_name` text,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_ndt_document`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ndt_document`
--

/*!40000 ALTER TABLE `ndt_document` DISABLE KEYS */;
INSERT INTO `ndt_document` (`id_ndt_document`,`full_name`,`name`) VALUES
 (1,'Газоснабжение','ДБН В.2.5-20-2001'),
 (2,'Сосуды работающие под давлением','НПАОП 0.00-1.07-96');
/*!40000 ALTER TABLE `ndt_document` ENABLE KEYS */;


--
-- Definition of table `organization`
--

DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id_organization` bigint(20) NOT NULL AUTO_INCREMENT,
  `adress` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_organization`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `organization`
--

/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` (`id_organization`,`adress`,`name`,`phone`) VALUES
 (1,'г. Харьков, пр. Косиора, 100','ООО \"Новые технологии\"','+380 57 715 13 28'),
 (2,'г. Харьков, ул. Светлая, 16','ООО \"Газавтоматика\"','+380 57 714 58 96');
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;


--
-- Definition of table `pattern_diameter`
--

DROP TABLE IF EXISTS `pattern_diameter`;
CREATE TABLE `pattern_diameter` (
  `id_pattern_diameter` bigint(20) NOT NULL AUTO_INCREMENT,
  `diameter` double DEFAULT NULL,
  PRIMARY KEY (`id_pattern_diameter`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pattern_diameter`
--

/*!40000 ALTER TABLE `pattern_diameter` DISABLE KEYS */;
INSERT INTO `pattern_diameter` (`id_pattern_diameter`,`diameter`) VALUES
 (1,57),
 (2,76);
/*!40000 ALTER TABLE `pattern_diameter` ENABLE KEYS */;


--
-- Definition of table `pattern_thickness`
--

DROP TABLE IF EXISTS `pattern_thickness`;
CREATE TABLE `pattern_thickness` (
  `id_pattern_thickness` bigint(20) NOT NULL AUTO_INCREMENT,
  `thickness` double DEFAULT NULL,
  PRIMARY KEY (`id_pattern_thickness`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pattern_thickness`
--

/*!40000 ALTER TABLE `pattern_thickness` DISABLE KEYS */;
INSERT INTO `pattern_thickness` (`id_pattern_thickness`,`thickness`) VALUES
 (1,2),
 (2,5);
/*!40000 ALTER TABLE `pattern_thickness` ENABLE KEYS */;


--
-- Definition of table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id_person` int(10) unsigned NOT NULL,
  `surname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `person`
--

/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`id_person`,`surname`) VALUES
 (1,'Иванов'),
 (2,'Петров'),
 (3,'Сидоров');
/*!40000 ALTER TABLE `person` ENABLE KEYS */;


--
-- Definition of table `person_method`
--

DROP TABLE IF EXISTS `person_method`;
CREATE TABLE `person_method` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_person` int(10) unsigned NOT NULL,
  `id_method` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `person_method`
--

/*!40000 ALTER TABLE `person_method` DISABLE KEYS */;
INSERT INTO `person_method` (`id`,`id_person`,`id_method`) VALUES
 (1,1,1),
 (2,1,2),
 (3,1,3),
 (4,3,2),
 (5,3,5);
/*!40000 ALTER TABLE `person_method` ENABLE KEYS */;


--
-- Definition of table `personal_protocol`
--

DROP TABLE IF EXISTS `personal_protocol`;
CREATE TABLE `personal_protocol` (
  `id_personal_protocol` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `date_periodical_cert` date DEFAULT NULL,
  `id_journal` bigint(20) DEFAULT NULL,
  `id_resolution_certification` bigint(20) DEFAULT NULL,
  `id_theory_test` bigint(20) DEFAULT NULL,
  `id_welder` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_personal_protocol`),
  KEY `FKC06CA1B75F1D58C6` (`id_welder`),
  KEY `FKC06CA1B74857498E` (`id_journal`),
  KEY `FKC06CA1B7DFC6EABD` (`id_resolution_certification`),
  KEY `FKC06CA1B7C9E9C4DB` (`id_theory_test`),
  CONSTRAINT `FKC06CA1B74857498E` FOREIGN KEY (`id_journal`) REFERENCES `journal` (`id_journal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKC06CA1B75F1D58C6` FOREIGN KEY (`id_welder`) REFERENCES `welder` (`id_welder`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKC06CA1B7C9E9C4DB` FOREIGN KEY (`id_theory_test`) REFERENCES `theory_test` (`id_theory_test`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKC06CA1B7DFC6EABD` FOREIGN KEY (`id_resolution_certification`) REFERENCES `resolution_certification` (`id_resolution_certification`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `personal_protocol`
--

/*!40000 ALTER TABLE `personal_protocol` DISABLE KEYS */;
INSERT INTO `personal_protocol` (`id_personal_protocol`,`number`,`date_periodical_cert`,`id_journal`,`id_resolution_certification`,`id_theory_test`,`id_welder`) VALUES
 (1,'14-001','2014-11-04',1,1,1,1);
/*!40000 ALTER TABLE `personal_protocol` ENABLE KEYS */;


--
-- Definition of table `personal_protocol_ndt_document`
--

DROP TABLE IF EXISTS `personal_protocol_ndt_document`;
CREATE TABLE `personal_protocol_ndt_document` (
  `id_personal_protocol` bigint(20) NOT NULL,
  `id_ndt_document` bigint(20) NOT NULL,
  KEY `FK24BFB0447B944AC7` (`id_personal_protocol`),
  KEY `FK24BFB0445FB8245D` (`id_ndt_document`),
  CONSTRAINT `FK24BFB0445FB8245D` FOREIGN KEY (`id_ndt_document`) REFERENCES `ndt_document` (`id_ndt_document`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK24BFB0447B944AC7` FOREIGN KEY (`id_personal_protocol`) REFERENCES `personal_protocol` (`id_personal_protocol`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `personal_protocol_ndt_document`
--

/*!40000 ALTER TABLE `personal_protocol_ndt_document` DISABLE KEYS */;
INSERT INTO `personal_protocol_ndt_document` (`id_personal_protocol`,`id_ndt_document`) VALUES
 (1,1),
 (1,2);
/*!40000 ALTER TABLE `personal_protocol_ndt_document` ENABLE KEYS */;


--
-- Definition of table `qualification`
--

DROP TABLE IF EXISTS `qualification`;
CREATE TABLE `qualification` (
  `id_qualification` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_qualification`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `qualification`
--

/*!40000 ALTER TABLE `qualification` DISABLE KEYS */;
INSERT INTO `qualification` (`id_qualification`,`type`) VALUES
 (1,'Электросварщик'),
 (2,'Электрогазосварщик'),
 (3,'Газосварщик');
/*!40000 ALTER TABLE `qualification` ENABLE KEYS */;


--
-- Definition of table `radiation_test`
--

DROP TABLE IF EXISTS `radiation_test`;
CREATE TABLE `radiation_test` (
  `id_radiation_test` bigint(20) NOT NULL AUTO_INCREMENT,
  `defects` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `prot_date` date DEFAULT NULL,
  `sensitivity` varchar(255) DEFAULT NULL,
  `id_evaluation` bigint(20) DEFAULT NULL,
  `id_weld_pattern` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_radiation_test`),
  KEY `FK12C67D90D7AE25E9` (`id_weld_pattern`),
  KEY `FK12C67D90588CCF58` (`id_evaluation`),
  CONSTRAINT `FK12C67D90588CCF58` FOREIGN KEY (`id_evaluation`) REFERENCES `evaluation` (`id_evaluation`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK12C67D90D7AE25E9` FOREIGN KEY (`id_weld_pattern`) REFERENCES `weld_pattern` (`id_weld_pattern`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `radiation_test`
--

/*!40000 ALTER TABLE `radiation_test` DISABLE KEYS */;
INSERT INTO `radiation_test` (`id_radiation_test`,`defects`,`number`,`prot_date`,`sensitivity`,`id_evaluation`,`id_weld_pattern`) VALUES
 (1,'2П0,8; 2Ш3х4; Нк25','14-001','2014-11-04','0.2',2,1);
/*!40000 ALTER TABLE `radiation_test` ENABLE KEYS */;


--
-- Definition of table `resolution_certification`
--

DROP TABLE IF EXISTS `resolution_certification`;
CREATE TABLE `resolution_certification` (
  `id_resolution_certification` bigint(20) NOT NULL AUTO_INCREMENT,
  `text_resolution` text,
  PRIMARY KEY (`id_resolution_certification`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `resolution_certification`
--

/*!40000 ALTER TABLE `resolution_certification` DISABLE KEYS */;
INSERT INTO `resolution_certification` (`id_resolution_certification`,`text_resolution`) VALUES
 (1,'Сварщик сертифицирован на работы повышенной опасности');
/*!40000 ALTER TABLE `resolution_certification` ENABLE KEYS */;


--
-- Definition of table `section`
--

DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `id_section` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text,
  `order_index` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_section`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `section`
--

/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` (`id_section`,`description`,`order_index`,`title`) VALUES
 (1,'Теория сварочного дела',1,'Теория'),
 (2,'Практика сварочного дела',2,'Практика');
/*!40000 ALTER TABLE `section` ENABLE KEYS */;


--
-- Definition of table `section_topic`
--

DROP TABLE IF EXISTS `section_topic`;
CREATE TABLE `section_topic` (
  `id_topic` bigint(20) NOT NULL,
  `id_section` bigint(20) NOT NULL,
  KEY `FKB1FF2F55D97BCD7E` (`id_topic`),
  KEY `FKB1FF2F55DC6CE8EA` (`id_section`),
  CONSTRAINT `FKB1FF2F55D97BCD7E` FOREIGN KEY (`id_topic`) REFERENCES `topic` (`id_topic`),
  CONSTRAINT `FKB1FF2F55DC6CE8EA` FOREIGN KEY (`id_section`) REFERENCES `section` (`id_section`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `section_topic`
--

/*!40000 ALTER TABLE `section_topic` DISABLE KEYS */;
INSERT INTO `section_topic` (`id_topic`,`id_section`) VALUES
 (1,1),
 (2,1),
 (3,2);
/*!40000 ALTER TABLE `section_topic` ENABLE KEYS */;


--
-- Definition of table `steel_group`
--

DROP TABLE IF EXISTS `steel_group`;
CREATE TABLE `steel_group` (
  `id_steel_group` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text,
  `st_group` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_steel_group`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `steel_group`
--

/*!40000 ALTER TABLE `steel_group` DISABLE KEYS */;
INSERT INTO `steel_group` (`id_steel_group`,`description`,`st_group`) VALUES
 (1,'Углеродистые стали','W1');
/*!40000 ALTER TABLE `steel_group` ENABLE KEYS */;


--
-- Definition of table `steel_type`
--

DROP TABLE IF EXISTS `steel_type`;
CREATE TABLE `steel_type` (
  `id_steel_type` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `id_steel_group` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_steel_type`),
  KEY `FK8468DD2E1154C497` (`id_steel_group`),
  CONSTRAINT `FK8468DD2E1154C497` FOREIGN KEY (`id_steel_group`) REFERENCES `steel_group` (`id_steel_group`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `steel_type`
--

/*!40000 ALTER TABLE `steel_type` DISABLE KEYS */;
INSERT INTO `steel_type` (`id_steel_type`,`type`,`id_steel_group`) VALUES
 (1,'Сталь 20',1),
 (2,'Ст. 2пс',NULL);
/*!40000 ALTER TABLE `steel_type` ENABLE KEYS */;


--
-- Definition of table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id_teacher` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `secname` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_teacher`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teacher`
--

/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` (`id_teacher`,`name`,`secname`,`surname`) VALUES
 (1,'Александр','Владимирович','Ашукин'),
 (2,'Андрей','Анатольевич','Чуйко'),
 (3,'Дмитрий','Анатольевич','Крапива'),
 (4,'Светлана','Александровна','Касьяненко');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;


--
-- Definition of table `theory_test`
--

DROP TABLE IF EXISTS `theory_test`;
CREATE TABLE `theory_test` (
  `id_theory_test` bigint(20) NOT NULL AUTO_INCREMENT,
  `rating` varchar(255) DEFAULT NULL,
  `ticket_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_theory_test`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `theory_test`
--

/*!40000 ALTER TABLE `theory_test` DISABLE KEYS */;
INSERT INTO `theory_test` (`id_theory_test`,`rating`,`ticket_number`) VALUES
 (1,'Сдано','25');
/*!40000 ALTER TABLE `theory_test` ENABLE KEYS */;


--
-- Definition of table `topic`
--

DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id_topic` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text,
  `order_index` int(11) DEFAULT NULL,
  `timelong` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_topic`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `topic`
--

/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` (`id_topic`,`description`,`order_index`,`timelong`,`title`) VALUES
 (1,'Сущность процессов, напряжения и деформации при сварке, понятия и показатели свариваемости',1,6,'Основы сварки плавлением'),
 (2,'Классификация, положения при сварке, зачистка кромок',2,4,'Сварные соединения и швы'),
 (3,'Практические занятия по сварке различных образцов',3,12,'Практические занятия');
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;


--
-- Definition of table `total_protocol`
--

DROP TABLE IF EXISTS `total_protocol`;
CREATE TABLE `total_protocol` (
  `id_total_protocol` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` varchar(255) DEFAULT NULL,
  `date_cert` date DEFAULT NULL,
  `id_journal` bigint(20) DEFAULT NULL,
  `id_commission_certification` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_total_protocol`),
  KEY `FK_TOTAL_PROTOCOL_JOURNAL` (`id_journal`),
  KEY `FK_TOTAL_PROTOCOL_COMMISSION_idx` (`id_commission_certification`),
  CONSTRAINT `FK_TOTAL_PROTOCOL_COMMISSION` FOREIGN KEY (`id_commission_certification`) REFERENCES `commission_certification` (`id_commission_certification`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_TOTAL_PROTOCOL_JOURNAL` FOREIGN KEY (`id_journal`) REFERENCES `journal` (`id_journal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `total_protocol`
--

/*!40000 ALTER TABLE `total_protocol` DISABLE KEYS */;
INSERT INTO `total_protocol` (`id_total_protocol`,`number`,`date_cert`,`id_journal`,`id_commission_certification`) VALUES
 (1,'14-001','2014-11-04',1,1),
 (11,'15-001','2015-01-08',7,NULL),
 (12,'15-002','2015-01-14',8,NULL),
 (13,'15-003','2015-01-21',9,NULL),
 (14,'15-004','2015-01-28',10,NULL);
/*!40000 ALTER TABLE `total_protocol` ENABLE KEYS */;


--
-- Definition of table `visual_test`
--

DROP TABLE IF EXISTS `visual_test`;
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

--
-- Dumping data for table `visual_test`
--

/*!40000 ALTER TABLE `visual_test` DISABLE KEYS */;
INSERT INTO `visual_test` (`id_visual_test`,`defects`,`number`,`prot_date`,`id_evaluation`,`id_weld_pattern`) VALUES
 (1,'ДНО','14-001vt','2014-11-04',1,1);
/*!40000 ALTER TABLE `visual_test` ENABLE KEYS */;


--
-- Definition of table `weld_detail`
--

DROP TABLE IF EXISTS `weld_detail`;
CREATE TABLE `weld_detail` (
  `id_weld_detail` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_weld_detail`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `weld_detail`
--

/*!40000 ALTER TABLE `weld_detail` DISABLE KEYS */;
INSERT INTO `weld_detail` (`id_weld_detail`,`code`,`type`) VALUES
 (1,'P','Пластина'),
 (2,'T','Труба');
/*!40000 ALTER TABLE `weld_detail` ENABLE KEYS */;


--
-- Definition of table `weld_gas`
--

DROP TABLE IF EXISTS `weld_gas`;
CREATE TABLE `weld_gas` (
  `id_weld_gas` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_weld_gas`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `weld_gas`
--

/*!40000 ALTER TABLE `weld_gas` DISABLE KEYS */;
INSERT INTO `weld_gas` (`id_weld_gas`,`type`) VALUES
 (1,'CO2'),
 (2,'C2Н2'),
 (3,'Ar');
/*!40000 ALTER TABLE `weld_gas` ENABLE KEYS */;


--
-- Definition of table `weld_method`
--

DROP TABLE IF EXISTS `weld_method`;
CREATE TABLE `weld_method` (
  `id_weld_method` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_weld_method`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `weld_method`
--

/*!40000 ALTER TABLE `weld_method` DISABLE KEYS */;
INSERT INTO `weld_method` (`id_weld_method`,`code`,`name`) VALUES
 (1,'111','РДЭ'),
 (2,'135','ГС'),
 (3,'141','ВИГ');
/*!40000 ALTER TABLE `weld_method` ENABLE KEYS */;


--
-- Definition of table `weld_pattern`
--

DROP TABLE IF EXISTS `weld_pattern`;
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
  `id_weld_wire` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_weld_pattern`),
  KEY `FKA8EE0D17291D9D2B` (`id_radiation_test`),
  KEY `FKA8EE0D177B944AC7` (`id_personal_protocol`),
  KEY `FKA8EE0D17FC0038FB` (`id_weld_gas`),
  KEY `FKA8EE0D17941E64B9` (`id_weld_detail`),
  KEY `FKA8EE0D17B2D5D5D9` (`id_weld_method`),
  KEY `FKA8EE0D17245C603B` (`id_visual_test`),
  KEY `FKA8EE0D17A5C46FBB` (`id_steel_type`),
  KEY `FKA8EE0D17427C121B` (`id_mechanical_test`),
  KEY `FKA8EE0D178677AE3A` (`id_electrode`),
  KEY `FKA8EE0D178415AEE1` (`id_weld_wire`),
  CONSTRAINT `FKA8EE0D17245C603B` FOREIGN KEY (`id_visual_test`) REFERENCES `visual_test` (`id_visual_test`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D17291D9D2B` FOREIGN KEY (`id_radiation_test`) REFERENCES `radiation_test` (`id_radiation_test`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D17427C121B` FOREIGN KEY (`id_mechanical_test`) REFERENCES `mechanical_test` (`id_mechanical_test`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D177B944AC7` FOREIGN KEY (`id_personal_protocol`) REFERENCES `personal_protocol` (`id_personal_protocol`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D178415AEE1` FOREIGN KEY (`id_weld_wire`) REFERENCES `weld_wire` (`id_weld_wire`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D178677AE3A` FOREIGN KEY (`id_electrode`) REFERENCES `electrode` (`id_electrode`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D17941E64B9` FOREIGN KEY (`id_weld_detail`) REFERENCES `weld_detail` (`id_weld_detail`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D17A5C46FBB` FOREIGN KEY (`id_steel_type`) REFERENCES `steel_type` (`id_steel_type`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D17B2D5D5D9` FOREIGN KEY (`id_weld_method`) REFERENCES `weld_method` (`id_weld_method`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKA8EE0D17FC0038FB` FOREIGN KEY (`id_weld_gas`) REFERENCES `weld_gas` (`id_weld_gas`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `weld_pattern`
--

/*!40000 ALTER TABLE `weld_pattern` DISABLE KEYS */;
INSERT INTO `weld_pattern` (`id_weld_pattern`,`diameter`,`is_heat_treatment`,`is_heating`,`mark`,`thickness`,`id_electrode`,`id_mechanical_test`,`id_personal_protocol`,`id_radiation_test`,`id_steel_type`,`id_visual_test`,`id_weld_detail`,`id_weld_gas`,`id_weld_method`,`id_weld_wire`) VALUES
 (1,57,0,0,'14-001',2,3,1,1,1,1,1,1,1,2,1);
/*!40000 ALTER TABLE `weld_pattern` ENABLE KEYS */;


--
-- Definition of table `weld_pattern_weld_position`
--

DROP TABLE IF EXISTS `weld_pattern_weld_position`;
CREATE TABLE `weld_pattern_weld_position` (
  `id_weld_pattern` bigint(20) NOT NULL,
  `id_weld_position` bigint(20) NOT NULL,
  KEY `FK_weld_pattern_weld_position` (`id_weld_pattern`),
  KEY `FK_weld_pattern_weld_position_2` (`id_weld_position`),
  CONSTRAINT `FK_weld_pattern_weld_position` FOREIGN KEY (`id_weld_pattern`) REFERENCES `weld_pattern` (`id_weld_pattern`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_weld_pattern_weld_position_2` FOREIGN KEY (`id_weld_position`) REFERENCES `weld_position` (`id_weld_position`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `weld_pattern_weld_position`
--

/*!40000 ALTER TABLE `weld_pattern_weld_position` DISABLE KEYS */;
INSERT INTO `weld_pattern_weld_position` (`id_weld_pattern`,`id_weld_position`) VALUES
 (1,1),
 (1,2);
/*!40000 ALTER TABLE `weld_pattern_weld_position` ENABLE KEYS */;


--
-- Definition of table `weld_position`
--

DROP TABLE IF EXISTS `weld_position`;
CREATE TABLE `weld_position` (
  `id_weld_position` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_weld_position`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `weld_position`
--

/*!40000 ALTER TABLE `weld_position` DISABLE KEYS */;
INSERT INTO `weld_position` (`id_weld_position`,`code`,`type`) VALUES
 (1,'PA','Нижнее'),
 (2,'PF','Вертикальное'),
 (3,'PC','Горизонтальное');
/*!40000 ALTER TABLE `weld_position` ENABLE KEYS */;


--
-- Definition of table `weld_wire`
--

DROP TABLE IF EXISTS `weld_wire`;
CREATE TABLE `weld_wire` (
  `id_weld_wire` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_weld_wire`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `weld_wire`
--

/*!40000 ALTER TABLE `weld_wire` DISABLE KEYS */;
INSERT INTO `weld_wire` (`id_weld_wire`,`type`) VALUES
 (1,'09ГС'),
 (2,'09Г2С');
/*!40000 ALTER TABLE `weld_wire` ENABLE KEYS */;


--
-- Definition of table `welder`
--

DROP TABLE IF EXISTS `welder`;
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
  CONSTRAINT `FKD0D293F36A83B29A` FOREIGN KEY (`id_job`) REFERENCES `job` (`id_job`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKD0D293F37DB64EFE` FOREIGN KEY (`id_qualification`) REFERENCES `qualification` (`id_qualification`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKD0D293F3A3167270` FOREIGN KEY (`id_education`) REFERENCES `education` (`id_education`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKD0D293F3C491E346` FOREIGN KEY (`id_organization`) REFERENCES `organization` (`id_organization`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `welder`
--

/*!40000 ALTER TABLE `welder` DISABLE KEYS */;
INSERT INTO `welder` (`id_welder`,`birthday`,`date_begin`,`doc_number`,`name`,`secname`,`surname`,`id_education`,`id_job`,`id_organization`,`id_qualification`,`address`) VALUES
 (1,'2014-11-04','2014-11-04','XA-1620','Иван','Дорофеевич','Добрынин',1,3,1,1,'г. Киев, ул. Новая, 45'),
 (2,'2014-11-04','2014-11-04','XA-1626','Петр','Ильич','Петковский',1,4,2,3,'г. Харьков, ул. Зубарева, 50'),
 (3,'1987-05-18','2000-01-20','ХА-1898','Сергей','Петрович','Иванов',1,1,1,1,'г. Харьков'),
 (4,'1982-06-24','1999-01-04','11-ХА141','Захар','Иванович','Сидоров',1,1,1,1,'г. Мелитополь');
/*!40000 ALTER TABLE `welder` ENABLE KEYS */;


--
-- Definition of table `welder_weld_method`
--

DROP TABLE IF EXISTS `welder_weld_method`;
CREATE TABLE `welder_weld_method` (
  `id_welder` bigint(20) NOT NULL,
  `id_weld_method` bigint(20) NOT NULL,
  KEY `FKBD2C802E5F1D58C6` (`id_welder`),
  KEY `FKBD2C802EB2D5D5D9` (`id_weld_method`),
  CONSTRAINT `FKBD2C802E5F1D58C6` FOREIGN KEY (`id_welder`) REFERENCES `welder` (`id_welder`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKBD2C802EB2D5D5D9` FOREIGN KEY (`id_weld_method`) REFERENCES `weld_method` (`id_weld_method`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `welder_weld_method`
--

/*!40000 ALTER TABLE `welder_weld_method` DISABLE KEYS */;
INSERT INTO `welder_weld_method` (`id_welder`,`id_weld_method`) VALUES
 (1,1),
 (2,1),
 (1,2),
 (2,3),
 (3,1),
 (4,2);
/*!40000 ALTER TABLE `welder_weld_method` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
