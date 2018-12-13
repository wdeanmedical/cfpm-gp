-- MySQL dump 10.16  Distrib 10.2.6-MariaDB, for osx10.12 (x86_64)
--
-- Host: localhost    Database: cfpm_gp_10000
-- ------------------------------------------------------
-- Server version	10.2.6-MariaDB

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
-- Table structure for table `activity_log`
--

DROP TABLE IF EXISTS `activity_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `activity` varchar(255) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `client_type` varchar(255) DEFAULT NULL,
  `encounter_id` int(11) DEFAULT NULL,
  `field_name` varchar(255) DEFAULT NULL,
  `module` varchar(255) DEFAULT NULL,
  `notes` text DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `service_method` varchar(255) DEFAULT NULL,
  `time_performed` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_log`
--

LOCK TABLES `activity_log` WRITE;
/*!40000 ALTER TABLE `activity_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address_type`
--

DROP TABLE IF EXISTS `address_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address_type`
--

LOCK TABLES `address_type` WRITE;
/*!40000 ALTER TABLE `address_type` DISABLE KEYS */;
INSERT INTO `address_type` VALUES (1,NULL,'2015-11-09 07:56:50',NULL,'Home'),(2,NULL,'2015-11-04 06:35:07',NULL,'Office'),(3,NULL,NULL,NULL,'Billing');
/*!40000 ALTER TABLE `address_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appt_type`
--

DROP TABLE IF EXISTS `appt_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appt_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appt_type`
--

LOCK TABLES `appt_type` WRITE;
/*!40000 ALTER TABLE `appt_type` DISABLE KEYS */;
INSERT INTO `appt_type` VALUES (1,'2018-11-26 06:59:23',NULL,NULL,'checkup','Check Up',1),(2,'2018-11-26 06:59:52',NULL,NULL,'followup','Follow Up',2),(3,'2018-11-26 07:00:23',NULL,NULL,'office_visit','Office Visit',3),(4,'2018-11-26 07:01:04',NULL,NULL,'eval','Evaluation',4),(5,'2018-11-26 07:01:22',NULL,NULL,'cons','Consultation',5);
/*!40000 ALTER TABLE `appt_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bai_form`
--

DROP TABLE IF EXISTS `bai_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bai_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `report` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK88073FD94661880D` (`clinician_id`),
  CONSTRAINT `FK88073FD94661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bai_form`
--

LOCK TABLES `bai_form` WRITE;
/*!40000 ALTER TABLE `bai_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `bai_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `behavior_function`
--

DROP TABLE IF EXISTS `behavior_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `behavior_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `abc` text DEFAULT NULL,
  `antecedents` text DEFAULT NULL,
  `behavior` text DEFAULT NULL,
  `communicate` text DEFAULT NULL,
  `consequences` text DEFAULT NULL,
  `fbabip_id` int(11) DEFAULT NULL,
  `hypothesized` text DEFAULT NULL,
  `modeling` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `behavior_function`
--

LOCK TABLES `behavior_function` WRITE;
/*!40000 ALTER TABLE `behavior_function` DISABLE KEYS */;
/*!40000 ALTER TABLE `behavior_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billing_ticket`
--

DROP TABLE IF EXISTS `billing_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `billing_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billing_ticket`
--

LOCK TABLES `billing_ticket` WRITE;
/*!40000 ALTER TABLE `billing_ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `billing_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billing_ticket_entry`
--

DROP TABLE IF EXISTS `billing_ticket_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `billing_ticket_entry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `billing_ticket_id` int(11) DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `copay_method` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `dx` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `service` varchar(255) DEFAULT NULL,
  `total_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billing_ticket_entry`
--

LOCK TABLES `billing_ticket_entry` WRITE;
/*!40000 ALTER TABLE `billing_ticket_entry` DISABLE KEYS */;
/*!40000 ALTER TABLE `billing_ticket_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendar_event`
--

DROP TABLE IF EXISTS `calendar_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendar_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `appt_type` varchar(255) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `event_type` int(11) DEFAULT NULL,
  `override` tinyint(1) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `task_id` int(11) DEFAULT NULL,
  `time_specified` tinyint(1) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `clinician` int(11) DEFAULT NULL,
  `patient` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2A9BEA59692005EF` (`clinician`),
  KEY `FK2A9BEA59B5315094` (`patient`),
  KEY `FK2A9BEA59CBC7794C` (`user`),
  CONSTRAINT `FK2A9BEA59692005EF` FOREIGN KEY (`clinician`) REFERENCES `user` (`id`),
  CONSTRAINT `FK2A9BEA59B5315094` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
  CONSTRAINT `FK2A9BEA59CBC7794C` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar_event`
--

LOCK TABLES `calendar_event` WRITE;
/*!40000 ALTER TABLE `calendar_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `calendar_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cc_auth_form`
--

DROP TABLE IF EXISTS `cc_auth_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cc_auth_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `cardholder_name` varchar(255) DEFAULT NULL,
  `cc_exp` varchar(255) DEFAULT NULL,
  `cc_number` varchar(255) DEFAULT NULL,
  `cc_type` varchar(255) DEFAULT NULL,
  `cvc` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `initials` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `sig` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8C723FDC4661880D` (`clinician_id`),
  CONSTRAINT `FK8C723FDC4661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cc_auth_form`
--

LOCK TABLES `cc_auth_form` WRITE;
/*!40000 ALTER TABLE `cc_auth_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `cc_auth_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chief_complaint`
--

DROP TABLE IF EXISTS `chief_complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chief_complaint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `days_since` int(11) DEFAULT NULL,
  `denies` varchar(255) DEFAULT NULL,
  `denies_other` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `hours_since` int(11) DEFAULT NULL,
  `how_long_other` varchar(255) DEFAULT NULL,
  `months_since` int(11) DEFAULT NULL,
  `occurs_other` varchar(255) DEFAULT NULL,
  `occurs_when` varchar(255) DEFAULT NULL,
  `pain_duration` varchar(255) DEFAULT NULL,
  `pain_scale` int(11) DEFAULT NULL,
  `pain_type` varchar(255) DEFAULT NULL,
  `pain_x_day` int(11) DEFAULT NULL,
  `pain_x_hour` int(11) DEFAULT NULL,
  `pain_x_month` int(11) DEFAULT NULL,
  `pain_x_week` int(11) DEFAULT NULL,
  `specific_location` varchar(255) DEFAULT NULL,
  `weeks_since` int(11) DEFAULT NULL,
  `years_since` int(11) DEFAULT NULL,
  `encounter` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK58719315D457337` (`encounter`),
  KEY `FK58719314661880D` (`clinician_id`),
  CONSTRAINT `FK58719314661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK58719315D457337` FOREIGN KEY (`encounter`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chief_complaint`
--

LOCK TABLES `chief_complaint` WRITE;
/*!40000 ALTER TABLE `chief_complaint` DISABLE KEYS */;
/*!40000 ALTER TABLE `chief_complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `child_history`
--

DROP TABLE IF EXISTS `child_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `child_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `accidents` varchar(255) DEFAULT NULL,
  `accidents_duration` varchar(80) DEFAULT NULL,
  `adopted` tinyint(1) DEFAULT NULL,
  `allergy_1` varchar(20) DEFAULT NULL,
  `allergy_2` varchar(20) DEFAULT NULL,
  `allergy_3` varchar(20) DEFAULT NULL,
  `allergy_info` text DEFAULT NULL,
  `allergy_level_1` varchar(20) DEFAULT NULL,
  `allergy_level_2` varchar(20) DEFAULT NULL,
  `allergy_level_3` varchar(20) DEFAULT NULL,
  `author_name` varchar(80) DEFAULT NULL,
  `bed_time` varchar(80) DEFAULT NULL,
  `bed_wetting` varchar(20) DEFAULT NULL,
  `birth_weight` varchar(8) DEFAULT NULL,
  `bowel_control` varchar(20) DEFAULT NULL,
  `caregiver_address` varchar(255) DEFAULT NULL,
  `caregiver_city` varchar(20) DEFAULT NULL,
  `caregiver_first_name` varchar(20) DEFAULT NULL,
  `caregiver_last_name` varchar(20) DEFAULT NULL,
  `caregiver_middle_name` varchar(20) DEFAULT NULL,
  `caregiver_occupation` varchar(20) DEFAULT NULL,
  `caregiver_postal_code` varchar(20) DEFAULT NULL,
  `caregiver_primary_phone` varchar(20) DEFAULT NULL,
  `caregiver_secondary_phone` varchar(20) DEFAULT NULL,
  `colic` tinyint(1) DEFAULT NULL,
  `complications` tinyint(1) DEFAULT NULL,
  `complications_desc` varchar(255) DEFAULT NULL,
  `concerns` text DEFAULT NULL,
  `crawled` varchar(20) DEFAULT NULL,
  `daytime_wetting` varchar(20) DEFAULT NULL,
  `dis` tinyint(1) DEFAULT NULL,
  `dis_desc` text DEFAULT NULL,
  `disease_1` varchar(20) DEFAULT NULL,
  `disease_1_desc` varchar(20) DEFAULT NULL,
  `disease_2` varchar(20) DEFAULT NULL,
  `disease_2_desc` varchar(20) DEFAULT NULL,
  `disease_3` varchar(20) DEFAULT NULL,
  `disease_3_desc` varchar(20) DEFAULT NULL,
  `disease_4` varchar(20) DEFAULT NULL,
  `disease_4_desc` varchar(20) DEFAULT NULL,
  `disease_5` varchar(20) DEFAULT NULL,
  `disease_5_desc` varchar(20) DEFAULT NULL,
  `disease_6` varchar(20) DEFAULT NULL,
  `disease_6_desc` varchar(20) DEFAULT NULL,
  `dose_1` varchar(20) DEFAULT NULL,
  `dose_2` varchar(20) DEFAULT NULL,
  `dose_3` varchar(20) DEFAULT NULL,
  `easy_baby` tinyint(1) DEFAULT NULL,
  `emerg_address` varchar(255) DEFAULT NULL,
  `emerg_city` varchar(20) DEFAULT NULL,
  `emerg_first_name` varchar(20) DEFAULT NULL,
  `emerg_last_name` varchar(20) DEFAULT NULL,
  `emerg_middle_name` varchar(20) DEFAULT NULL,
  `emerg_occupation` varchar(20) DEFAULT NULL,
  `emerg_postal_code` varchar(20) DEFAULT NULL,
  `emerg_primary_phone` varchar(20) DEFAULT NULL,
  `emerg_secondary_phone` varchar(20) DEFAULT NULL,
  `enjoys_people` tinyint(1) DEFAULT NULL,
  `ethnicity` varchar(80) DEFAULT NULL,
  `ethnicity_notes` varchar(255) DEFAULT NULL,
  `family_stressors` text DEFAULT NULL,
  `father_address` varchar(255) DEFAULT NULL,
  `father_city` varchar(80) DEFAULT NULL,
  `father_first_name` varchar(20) DEFAULT NULL,
  `father_last_name` varchar(20) DEFAULT NULL,
  `father_middle_name` varchar(20) DEFAULT NULL,
  `father_occupation` varchar(20) DEFAULT NULL,
  `father_postal_code` varchar(20) DEFAULT NULL,
  `father_primary_phone` varchar(20) DEFAULT NULL,
  `father_secondary_phone` varchar(20) DEFAULT NULL,
  `feeding` tinyint(1) DEFAULT NULL,
  `freq_1` varchar(20) DEFAULT NULL,
  `freq_2` varchar(20) DEFAULT NULL,
  `freq_3` varchar(20) DEFAULT NULL,
  `grade` varchar(20) DEFAULT NULL,
  `hospital_duration` varchar(80) DEFAULT NULL,
  `illness` text DEFAULT NULL,
  `information` text DEFAULT NULL,
  `irrirable` tinyint(1) DEFAULT NULL,
  `languages` varchar(255) DEFAULT NULL,
  `last_exam` varchar(255) DEFAULT NULL,
  `med_1` varchar(20) DEFAULT NULL,
  `med_2` varchar(20) DEFAULT NULL,
  `med_3` varchar(20) DEFAULT NULL,
  `med_info` text DEFAULT NULL,
  `medical_problems` tinyint(1) DEFAULT NULL,
  `medical_problems_duration` varchar(80) DEFAULT NULL,
  `medications` tinyint(1) DEFAULT NULL,
  `medications_duration` varchar(80) DEFAULT NULL,
  `mother_address` varchar(255) DEFAULT NULL,
  `mother_age` varchar(8) DEFAULT NULL,
  `mother_city` varchar(80) DEFAULT NULL,
  `mother_first_name` varchar(20) DEFAULT NULL,
  `mother_hospital_duration` varchar(80) DEFAULT NULL,
  `mother_last_name` varchar(20) DEFAULT NULL,
  `mother_middle_name` varchar(20) DEFAULT NULL,
  `mother_occupation` varchar(20) DEFAULT NULL,
  `mother_postal_code` varchar(20) DEFAULT NULL,
  `mother_primary_phone` varchar(20) DEFAULT NULL,
  `mother_secondary_phone` varchar(20) DEFAULT NULL,
  `other_persons` text DEFAULT NULL,
  `oxygen` tinyint(1) DEFAULT NULL,
  `oxygen_reason` varchar(255) DEFAULT NULL,
  `pcp_address` varchar(255) DEFAULT NULL,
  `pcp_name` varchar(80) DEFAULT NULL,
  `pcp_phone` varchar(20) DEFAULT NULL,
  `preg` varchar(255) DEFAULT NULL,
  `prescriber_1` varchar(20) DEFAULT NULL,
  `prescriber_2` varchar(20) DEFAULT NULL,
  `prescriber_3` varchar(20) DEFAULT NULL,
  `problem_1` varchar(20) DEFAULT NULL,
  `problem_10` varchar(20) DEFAULT NULL,
  `problem_10_desc` varchar(80) DEFAULT NULL,
  `problem_11` varchar(20) DEFAULT NULL,
  `problem_11_desc` varchar(80) DEFAULT NULL,
  `problem_12` varchar(20) DEFAULT NULL,
  `problem_12_desc` varchar(80) DEFAULT NULL,
  `problem_13` varchar(20) DEFAULT NULL,
  `problem_13_desc` varchar(80) DEFAULT NULL,
  `problem_14` varchar(20) DEFAULT NULL,
  `problem_14_desc` varchar(80) DEFAULT NULL,
  `problem_15` varchar(20) DEFAULT NULL,
  `problem_15_desc` varchar(80) DEFAULT NULL,
  `problem_16` varchar(20) DEFAULT NULL,
  `problem_16_desc` varchar(80) DEFAULT NULL,
  `problem_17` varchar(20) DEFAULT NULL,
  `problem_17_desc` varchar(80) DEFAULT NULL,
  `problem_18` varchar(20) DEFAULT NULL,
  `problem_18_desc` varchar(80) DEFAULT NULL,
  `problem_19` varchar(20) DEFAULT NULL,
  `problem_19_desc` varchar(80) DEFAULT NULL,
  `problem_1_desc` varchar(80) DEFAULT NULL,
  `problem_2` varchar(20) DEFAULT NULL,
  `problem_20` varchar(20) DEFAULT NULL,
  `problem_20_desc` varchar(80) DEFAULT NULL,
  `problem_21` varchar(20) DEFAULT NULL,
  `problem_21_desc` varchar(80) DEFAULT NULL,
  `problem_22` varchar(20) DEFAULT NULL,
  `problem_22_desc` varchar(80) DEFAULT NULL,
  `problem_23` varchar(20) DEFAULT NULL,
  `problem_23_desc` varchar(80) DEFAULT NULL,
  `problem_24` varchar(20) DEFAULT NULL,
  `problem_24_desc` varchar(80) DEFAULT NULL,
  `problem_25` varchar(20) DEFAULT NULL,
  `problem_25_desc` varchar(80) DEFAULT NULL,
  `problem_26` varchar(20) DEFAULT NULL,
  `problem_26_desc` varchar(80) DEFAULT NULL,
  `problem_27` varchar(20) DEFAULT NULL,
  `problem_27_desc` varchar(80) DEFAULT NULL,
  `problem_2_desc` varchar(80) DEFAULT NULL,
  `problem_3` varchar(20) DEFAULT NULL,
  `problem_3_desc` varchar(80) DEFAULT NULL,
  `problem_4` varchar(20) DEFAULT NULL,
  `problem_4_desc` varchar(80) DEFAULT NULL,
  `problem_5` varchar(20) DEFAULT NULL,
  `problem_5_desc` varchar(80) DEFAULT NULL,
  `problem_6` varchar(20) DEFAULT NULL,
  `problem_6_desc` varchar(80) DEFAULT NULL,
  `problem_7` varchar(20) DEFAULT NULL,
  `problem_7_desc` varchar(80) DEFAULT NULL,
  `problem_8` varchar(20) DEFAULT NULL,
  `problem_8_desc` varchar(80) DEFAULT NULL,
  `problem_9` varchar(20) DEFAULT NULL,
  `problem_9_desc` varchar(80) DEFAULT NULL,
  `prof_1` varchar(80) DEFAULT NULL,
  `prof_2` varchar(80) DEFAULT NULL,
  `prof_3` varchar(80) DEFAULT NULL,
  `prof_4` varchar(80) DEFAULT NULL,
  `prof_age_1` varchar(4) DEFAULT NULL,
  `prof_age_2` varchar(4) DEFAULT NULL,
  `prof_age_3` varchar(4) DEFAULT NULL,
  `prof_age_4` varchar(4) DEFAULT NULL,
  `quiet` tinyint(1) DEFAULT NULL,
  `relationship` varchar(20) DEFAULT NULL,
  `religion` varchar(20) DEFAULT NULL,
  `religion_notes` varchar(255) DEFAULT NULL,
  `repeated` text DEFAULT NULL,
  `repeated_desc` varchar(80) DEFAULT NULL,
  `sat` varchar(20) DEFAULT NULL,
  `school` tinyint(1) DEFAULT NULL,
  `school_grade` varchar(20) DEFAULT NULL,
  `school_name` varchar(80) DEFAULT NULL,
  `sensory` tinyint(1) DEFAULT NULL,
  `sentences` varchar(20) DEFAULT NULL,
  `sibling_dob_1` datetime DEFAULT NULL,
  `sibling_dob_2` datetime DEFAULT NULL,
  `sibling_dob_3` datetime DEFAULT NULL,
  `sibling_dob_4` datetime DEFAULT NULL,
  `sibling_dob_5` datetime DEFAULT NULL,
  `sibling_dob_6` datetime DEFAULT NULL,
  `sibling_name_1` varchar(20) DEFAULT NULL,
  `sibling_name_2` varchar(20) DEFAULT NULL,
  `sibling_name_3` varchar(20) DEFAULT NULL,
  `sibling_name_4` varchar(20) DEFAULT NULL,
  `sibling_name_5` varchar(20) DEFAULT NULL,
  `sibling_name_6` varchar(20) DEFAULT NULL,
  `sibling_rel_1` varchar(20) DEFAULT NULL,
  `sibling_rel_2` varchar(20) DEFAULT NULL,
  `sibling_rel_3` varchar(20) DEFAULT NULL,
  `sibling_rel_4` varchar(20) DEFAULT NULL,
  `sibling_rel_5` varchar(20) DEFAULT NULL,
  `sibling_rel_6` varchar(20) DEFAULT NULL,
  `sick` tinyint(1) DEFAULT NULL,
  `sleep` tinyint(1) DEFAULT NULL,
  `sleep_hours` varchar(80) DEFAULT NULL,
  `sleep_time` varchar(80) DEFAULT NULL,
  `slept` varchar(20) DEFAULT NULL,
  `snore` tinyint(1) DEFAULT NULL,
  `soc_1` varchar(20) DEFAULT NULL,
  `soc_2` varchar(20) DEFAULT NULL,
  `soc_3` varchar(20) DEFAULT NULL,
  `soc_4` varchar(20) DEFAULT NULL,
  `soothe` tinyint(1) DEFAULT NULL,
  `special_care` tinyint(1) DEFAULT NULL,
  `special_care_reason` varchar(255) DEFAULT NULL,
  `special_diet` tinyint(1) DEFAULT NULL,
  `special_diet_duration` varchar(80) DEFAULT NULL,
  `sped` tinyint(1) DEFAULT NULL,
  `sped_name` varchar(20) DEFAULT NULL,
  `stressors` tinyint(1) DEFAULT NULL,
  `stressors_desc` text DEFAULT NULL,
  `walked` varchar(20) DEFAULT NULL,
  `weeks_birth` varchar(8) DEFAULT NULL,
  `words` varchar(20) DEFAULT NULL,
  `caregiver_state` int(11) DEFAULT NULL,
  `emerg_state` int(11) DEFAULT NULL,
  `father_state` int(11) DEFAULT NULL,
  `mother_state` int(11) DEFAULT NULL,
  `sibling_gender_1` int(11) DEFAULT NULL,
  `sibling_gender_2` int(11) DEFAULT NULL,
  `sibling_gender_3` int(11) DEFAULT NULL,
  `sibling_gender_4` int(11) DEFAULT NULL,
  `sibling_gender_5` int(11) DEFAULT NULL,
  `sibling_gender_6` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2C2AA471C9122352` (`mother_state`),
  KEY `FK2C2AA4716EB4D72B` (`father_state`),
  KEY `FK2C2AA471C31F9DF` (`caregiver_state`),
  KEY `FK2C2AA4713398D609` (`sibling_gender_3`),
  KEY `FK2C2AA4713398D60A` (`sibling_gender_4`),
  KEY `FK2C2AA471473B1501` (`emerg_state`),
  KEY `FK2C2AA4713398D607` (`sibling_gender_1`),
  KEY `FK2C2AA4713398D608` (`sibling_gender_2`),
  KEY `FK2C2AA4714661880D` (`clinician_id`),
  KEY `FK2C2AA4713398D60B` (`sibling_gender_5`),
  KEY `FK2C2AA4713398D60C` (`sibling_gender_6`),
  CONSTRAINT `FK2C2AA4713398D607` FOREIGN KEY (`sibling_gender_1`) REFERENCES `gender` (`id`),
  CONSTRAINT `FK2C2AA4713398D608` FOREIGN KEY (`sibling_gender_2`) REFERENCES `gender` (`id`),
  CONSTRAINT `FK2C2AA4713398D609` FOREIGN KEY (`sibling_gender_3`) REFERENCES `gender` (`id`),
  CONSTRAINT `FK2C2AA4713398D60A` FOREIGN KEY (`sibling_gender_4`) REFERENCES `gender` (`id`),
  CONSTRAINT `FK2C2AA4713398D60B` FOREIGN KEY (`sibling_gender_5`) REFERENCES `gender` (`id`),
  CONSTRAINT `FK2C2AA4713398D60C` FOREIGN KEY (`sibling_gender_6`) REFERENCES `gender` (`id`),
  CONSTRAINT `FK2C2AA4714661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK2C2AA471473B1501` FOREIGN KEY (`emerg_state`) REFERENCES `us_state` (`id`),
  CONSTRAINT `FK2C2AA4716EB4D72B` FOREIGN KEY (`father_state`) REFERENCES `us_state` (`id`),
  CONSTRAINT `FK2C2AA471C31F9DF` FOREIGN KEY (`caregiver_state`) REFERENCES `us_state` (`id`),
  CONSTRAINT `FK2C2AA471C9122352` FOREIGN KEY (`mother_state`) REFERENCES `us_state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `child_history`
--

LOCK TABLES `child_history` WRITE;
/*!40000 ALTER TABLE `child_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `child_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_contact_form`
--

DROP TABLE IF EXISTS `client_contact_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_contact_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `call_work_ok` tinyint(1) DEFAULT NULL,
  `cell_msg_ok` tinyint(1) DEFAULT NULL,
  `cell_phone` varchar(255) DEFAULT NULL,
  `completed_by` varchar(255) DEFAULT NULL,
  `completed_by_date` datetime DEFAULT NULL,
  `contact_other` tinyint(1) DEFAULT NULL,
  `contact_other_detail` varchar(255) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `email_ok` tinyint(1) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `home_phone` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `msg_names` varchar(255) DEFAULT NULL,
  `msg_ok` tinyint(1) DEFAULT NULL,
  `msg_work_ok` tinyint(1) DEFAULT NULL,
  `no_info` tinyint(1) DEFAULT NULL,
  `signer` varchar(255) DEFAULT NULL,
  `signer_rel` varchar(255) DEFAULT NULL,
  `text_ok` tinyint(1) DEFAULT NULL,
  `text_waiver` tinyint(1) DEFAULT NULL,
  `vm_ok` tinyint(1) DEFAULT NULL,
  `work_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4E336D774661880D` (`clinician_id`),
  CONSTRAINT `FK4E336D774661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_contact_form`
--

LOCK TABLES `client_contact_form` WRITE;
/*!40000 ALTER TABLE `client_contact_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_contact_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_rights_form`
--

DROP TABLE IF EXISTS `client_rights_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_rights_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `child` tinyint(1) DEFAULT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `clinician_sig` varchar(255) DEFAULT NULL,
  `clinician_sig_date` datetime DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `guardian` varchar(255) DEFAULT NULL,
  `guardian_rel` varchar(255) DEFAULT NULL,
  `signed_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1D05CBB84661880D` (`clinician_id`),
  CONSTRAINT `FK1D05CBB84661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_rights_form`
--

LOCK TABLES `client_rights_form` WRITE;
/*!40000 ALTER TABLE `client_rights_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_rights_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_session`
--

DROP TABLE IF EXISTS `client_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `client_type` varchar(255) DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `last_access_time` datetime NOT NULL,
  `parked` tinyint(1) DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `guardian` int(11) DEFAULT NULL,
  `patient` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7942222B5315094` (`patient`),
  KEY `FK7942222A74149D8` (`guardian`),
  KEY `FK7942222CBC7794C` (`user`),
  CONSTRAINT `FK7942222A74149D8` FOREIGN KEY (`guardian`) REFERENCES `guardian` (`id`),
  CONSTRAINT `FK7942222B5315094` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`),
  CONSTRAINT `FK7942222CBC7794C` FOREIGN KEY (`user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_session`
--

LOCK TABLES `client_session` WRITE;
/*!40000 ALTER TABLE `client_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinic_discharge`
--

DROP TABLE IF EXISTS `clinic_discharge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clinic_discharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `axis_1` varchar(255) DEFAULT NULL,
  `axis_2` varchar(255) DEFAULT NULL,
  `axis_3` varchar(255) DEFAULT NULL,
  `axis_4` varchar(255) DEFAULT NULL,
  `axis_5` varchar(255) DEFAULT NULL,
  `client_school` varchar(255) DEFAULT NULL,
  `client_sig` varchar(255) DEFAULT NULL,
  `client_sig_date` datetime DEFAULT NULL,
  `client_sig_rel` varchar(255) DEFAULT NULL,
  `discharge_date` datetime DEFAULT NULL,
  `discharged` varchar(255) DEFAULT NULL,
  `discharged_by` varchar(255) DEFAULT NULL,
  `discharged_reason` varchar(255) DEFAULT NULL,
  `dose_1` varchar(255) DEFAULT NULL,
  `dose_2` varchar(255) DEFAULT NULL,
  `dose_3` varchar(255) DEFAULT NULL,
  `freq_1` varchar(255) DEFAULT NULL,
  `freq_2` varchar(255) DEFAULT NULL,
  `freq_3` varchar(255) DEFAULT NULL,
  `goals` text DEFAULT NULL,
  `goals_met` text DEFAULT NULL,
  `goals_not_met_reason` text DEFAULT NULL,
  `guardian` varchar(255) DEFAULT NULL,
  `guardian_phone` varchar(255) DEFAULT NULL,
  `guardian_rel` varchar(255) DEFAULT NULL,
  `intake_date` datetime DEFAULT NULL,
  `med_1` varchar(255) DEFAULT NULL,
  `med_2` varchar(255) DEFAULT NULL,
  `med_3` varchar(255) DEFAULT NULL,
  `med_info` text DEFAULT NULL,
  `prescriber_1` varchar(255) DEFAULT NULL,
  `prescriber_2` varchar(255) DEFAULT NULL,
  `prescriber_3` varchar(255) DEFAULT NULL,
  `read_doc` tinyint(1) DEFAULT NULL,
  `service_1` tinyint(1) DEFAULT NULL,
  `service_10` tinyint(1) DEFAULT NULL,
  `service_2` tinyint(1) DEFAULT NULL,
  `service_3` tinyint(1) DEFAULT NULL,
  `service_4` tinyint(1) DEFAULT NULL,
  `service_5` tinyint(1) DEFAULT NULL,
  `service_6` tinyint(1) DEFAULT NULL,
  `service_7` tinyint(1) DEFAULT NULL,
  `service_8` tinyint(1) DEFAULT NULL,
  `service_9` tinyint(1) DEFAULT NULL,
  `service_ref_1` varchar(255) DEFAULT NULL,
  `service_ref_10` varchar(255) DEFAULT NULL,
  `service_ref_2` varchar(255) DEFAULT NULL,
  `service_ref_3` varchar(255) DEFAULT NULL,
  `service_ref_4` varchar(255) DEFAULT NULL,
  `service_ref_5` varchar(255) DEFAULT NULL,
  `service_ref_6` varchar(255) DEFAULT NULL,
  `service_ref_7` varchar(255) DEFAULT NULL,
  `service_ref_8` varchar(255) DEFAULT NULL,
  `service_ref_9` varchar(255) DEFAULT NULL,
  `service_resp_1` varchar(255) DEFAULT NULL,
  `service_resp_10` varchar(255) DEFAULT NULL,
  `service_resp_2` varchar(255) DEFAULT NULL,
  `service_resp_3` varchar(255) DEFAULT NULL,
  `service_resp_4` varchar(255) DEFAULT NULL,
  `service_resp_5` varchar(255) DEFAULT NULL,
  `service_resp_6` varchar(255) DEFAULT NULL,
  `service_resp_7` varchar(255) DEFAULT NULL,
  `service_resp_8` varchar(255) DEFAULT NULL,
  `service_resp_9` varchar(255) DEFAULT NULL,
  `services` varchar(255) DEFAULT NULL,
  `services2` text DEFAULT NULL,
  `witness_sig` varchar(255) DEFAULT NULL,
  `witness_sig_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA027922B4661880D` (`clinician_id`),
  CONSTRAINT `FKA027922B4661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinic_discharge`
--

LOCK TABLES `clinic_discharge` WRITE;
/*!40000 ALTER TABLE `clinic_discharge` DISABLE KEYS */;
/*!40000 ALTER TABLE `clinic_discharge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clinical_progress`
--

DROP TABLE IF EXISTS `clinical_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clinical_progress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `client_sig` varchar(255) DEFAULT NULL,
  `contact_type` varchar(255) DEFAULT NULL,
  `functioning` text DEFAULT NULL,
  `goal` text DEFAULT NULL,
  `goal_1` varchar(255) DEFAULT NULL,
  `goal_2` varchar(255) DEFAULT NULL,
  `goal_desc_1` text DEFAULT NULL,
  `goal_desc_2` text DEFAULT NULL,
  `goal_progress_1` varchar(255) DEFAULT NULL,
  `goal_progress_2` varchar(255) DEFAULT NULL,
  `intervention_desc` text DEFAULT NULL,
  `interventions` varchar(255) DEFAULT NULL,
  `interventions_other` varchar(255) DEFAULT NULL,
  `issue` text DEFAULT NULL,
  `next_appt_date` datetime DEFAULT NULL,
  `persons_present` varchar(255) DEFAULT NULL,
  `persons_present_details` text DEFAULT NULL,
  `plan` text DEFAULT NULL,
  `provider_sig` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5453B1D94661880D` (`clinician_id`),
  CONSTRAINT `FK5453B1D94661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clinical_progress`
--

LOCK TABLES `clinical_progress` WRITE;
/*!40000 ALTER TABLE `clinical_progress` DISABLE KEYS */;
/*!40000 ALTER TABLE `clinical_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complaint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint_slider`
--

DROP TABLE IF EXISTS `complaint_slider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `complaint_slider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `complaint_id` int(11) DEFAULT NULL,
  `max_val` varchar(80) DEFAULT NULL,
  `min_val` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint_slider`
--

LOCK TABLES `complaint_slider` WRITE;
/*!40000 ALTER TABLE `complaint_slider` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaint_slider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consent_form`
--

DROP TABLE IF EXISTS `consent_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `consent_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `client_rel` varchar(255) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `initials0` varchar(255) DEFAULT NULL,
  `initials1` varchar(255) DEFAULT NULL,
  `initials10` varchar(255) DEFAULT NULL,
  `initials11` varchar(255) DEFAULT NULL,
  `initials12` varchar(255) DEFAULT NULL,
  `initials2` varchar(255) DEFAULT NULL,
  `initials3` varchar(255) DEFAULT NULL,
  `initials4` varchar(255) DEFAULT NULL,
  `initials5` varchar(255) DEFAULT NULL,
  `initials6` varchar(255) DEFAULT NULL,
  `initials7` varchar(255) DEFAULT NULL,
  `initials8` varchar(255) DEFAULT NULL,
  `initials9` varchar(255) DEFAULT NULL,
  `resp_date` datetime DEFAULT NULL,
  `resp_name` varchar(255) DEFAULT NULL,
  `signed` tinyint(1) DEFAULT NULL,
  `witness` varchar(255) DEFAULT NULL,
  `witness_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9D4A05C94661880D` (`clinician_id`),
  CONSTRAINT `FK9D4A05C94661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consent_form`
--

LOCK TABLES `consent_form` WRITE;
/*!40000 ALTER TABLE `consent_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `consent_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_message`
--

DROP TABLE IF EXISTS `contact_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL,
  `content` text DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `sent_from` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_message`
--

LOCK TABLES `contact_message` WRITE;
/*!40000 ALTER TABLE `contact_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cpt`
--

DROP TABLE IF EXISTS `cpt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cpt` (
  `id` int(11) NOT NULL DEFAULT 0,
  `code` varchar(32) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cpt`
--

LOCK TABLES `cpt` WRITE;
/*!40000 ALTER TABLE `cpt` DISABLE KEYS */;
INSERT INTO `cpt` VALUES (7703,'87329','IAAD EIA GIARDIA                                                                                    \r',NULL,NULL,NULL),(7704,'87332','IAAD EIA CYTOMEGALOVIRUS                                                                            \r',NULL,NULL,NULL),(7705,'87335','IAAD EIA ESCHERICHIA COLI 0157                                                                      \r',NULL,NULL,NULL),(7706,'87336','IAAD EIA ENTAMOEBA HISTOLYTICA DISPAR GRP                                                           \r',NULL,NULL,NULL),(7707,'87337','IAAD EIA ENTAMOEBA HISTOLYTICA GRP                                                                  \r',NULL,NULL,NULL),(7708,'87338','IAAD EIA HPYLORI STOOL                                                                              \r',NULL,NULL,NULL),(7709,'87339','IAAD EIA HPYLORI                                                                                    \r',NULL,NULL,NULL),(7710,'87340','IAAD EIA HEPATITIS B SURFACE ANTIGEN                                                                \r',NULL,NULL,NULL),(7711,'87341','IAAD EIA HEPATITIS B SURFACE AG NEUTRALIZATION                                                      \r',NULL,NULL,NULL),(7712,'87350','IAAD EIA HEPATITIS BE ANTIGEN                                                                       \r',NULL,NULL,NULL),(7713,'87380','IAAD EIA HEPATITIS DELTA ANTIGEN                                                                    \r',NULL,NULL,NULL),(7714,'87385','IAAD EIA HISTOPLASM CAPSULATUM                                                                      \r',NULL,NULL,NULL),(7715,'87389','IAAD EIA HIV-1 AG W/HIV-1 & HIV-2 ANTBDY SINGLE                                                     \r',NULL,NULL,NULL),(7716,'87390','IAAD EIA HIV-1                                                                                      \r',NULL,NULL,NULL),(7717,'87391','IAAD EIA HIV-2                                                                                      \r',NULL,NULL,NULL),(7718,'87400','IAAD EIA INFLUENZA A/B EACH                                                                         \r',NULL,NULL,NULL),(7719,'87420','IAAD EIA RESPIRATORY SYNCTIAL VIRUS                                                                 \r',NULL,NULL,NULL),(7720,'87425','IAAD EIA ROTAVIRUS                                                                                  \r',NULL,NULL,NULL),(7721,'87427','IAAD EIA SHIGA-LIKE TOXIN                                                                           \r',NULL,NULL,NULL),(7722,'87430','IAAD EIA STREPTOCOCCUS GROUP A                                                                      \r',NULL,NULL,NULL),(7723,'87449','IAAD EIA MULT STEP METHOD NOS EACH ORGANISM                                                         \r',NULL,NULL,NULL),(7724,'87450','IAAD EIA SINGLE STEP METHOD NOS EA ORGANISM                                                         \r',NULL,NULL,NULL),(7725,'87451','IAAD EIA POLYV MLT ORGANISMS EA POLYV ANTISERUM                                                     \r',NULL,NULL,NULL),(7726,'87470','IADNA BARTONELLA DIRECT PROBE TECHNIQUE                                                             \r',NULL,NULL,NULL),(7727,'87471','IADNA BARTONELLA AMPLIFIED PROBE TECHNIQUE                                                          \r',NULL,NULL,NULL),(7728,'87472','IADNA BARTONELLA HENSELAE&QUINTANA QUANTJ                                                           \r',NULL,NULL,NULL),(7729,'87475','IADNA BORRELIA BURGDORFERI DIRECT PROBE TQ                                                          \r',NULL,NULL,NULL),(7730,'87476','IADNA BORRELIA BURGDORFERI AMPLIFIED PROBE TQ                                                       \r',NULL,NULL,NULL),(7731,'87477','IADNA BORRELIA BURGDORFERI QUANTIFICATION                                                           \r',NULL,NULL,NULL),(7732,'87480','IADNA CANDIDA SPECIES DIRECT PROBE TQ                                                               \r',NULL,NULL,NULL),(7733,'87481','IADNA CANDIDA SPECIES AMPLIFIED PROBE TQ                                                            \r',NULL,NULL,NULL),(7734,'87482','IADNA CANDIDA SPECIES QUANTIFICATION                                                                \r',NULL,NULL,NULL),(7735,'87485','IADNA CHLAMYDIA PNEUMONIAE DIRECT PROBE TQ                                                          \r',NULL,NULL,NULL),(7736,'87486','IADNA CHLAMYDIA PNEUMONIAE AMPLIFIED PROBE TQ                                                       \r',NULL,NULL,NULL),(7737,'87487','IADNA CHLAMYDIA PNEUMONIAE QUANTIFICATION                                                           \r',NULL,NULL,NULL),(7738,'87490','IADNA CHLAMYDIA TRACHOMATIS DIRECT PROBE TQ                                                         \r',NULL,NULL,NULL),(7739,'87491','IADNA CHLAMYDIA TRACHOMATIS AMPLIFIED PROBE TQ                                                      \r',NULL,NULL,NULL),(7740,'87492','IADNA CHLAMYDIA TRACHOMATIS QUANTIFICATION                                                          \r',NULL,NULL,NULL),(7741,'87493','INF AGENT DET NUCLEIC ACID CLOSTRIDIUM AMP PROBE                                                    \r',NULL,NULL,NULL),(7742,'87495','IADNA CYTOMEGALOVIRUS DIRECT PROBE TQ                                                               \r',NULL,NULL,NULL),(7743,'87496','IADNA CYTOMEGALOVIRUS AMPLIFIED PROBE TQ                                                            \r',NULL,NULL,NULL),(7744,'87497','IADNA CYTOMEGALOVIRUS QUANTIFICATION                                                                \r',NULL,NULL,NULL),(7745,'87498','IADNA ENTEROVIRUS AMPLIF PROBE & REVRSE TRNSCRIP                                                    \r',NULL,NULL,NULL),(7746,'87500','INFECTIOUS AGENT DNA/RNA VANCOMYCIN RESISTANCE                                                      \r',NULL,NULL,NULL),(7747,'87501','INFECTIOUS AGENT DNA/RNA INFLUENZA EA TYPE                                                          \r',NULL,NULL,NULL),(7748,'87502','INFECTIOUS AGENT DNA/RNA INFLUENZA 1ST 2 TYPES                                                      \r',NULL,NULL,NULL),(7749,'87503','NFCT AGENT DNA/RNA INFLUENZA 1/> TYPES EA ADDL                                                      \r',NULL,NULL,NULL),(7750,'87510','IADNA GARDNERELLA VAGINALIS DIRECT PROBE TQ                                                         \r',NULL,NULL,NULL),(7751,'87511','IADNA GARDNERELLA VAGINALIS AMPLIFIED PROBE TQ                                                      \r',NULL,NULL,NULL),(7752,'87512','IADNA GARDNERELLA VAGINALIS QUANTIFICATION                                                          \r',NULL,NULL,NULL),(7753,'87515','IADNA HEPATITIS B VIRUS DIRECT PROBE TECHNIQUE                                                      \r',NULL,NULL,NULL),(7754,'87516','IADNA HEPATITIS B VIRUS AMPLIFIED PROBE TQ                                                          \r',NULL,NULL,NULL),(7755,'87517','IADNA HEPATITIS B VIRUS QUANTIFICATION                                                              \r',NULL,NULL,NULL),(7756,'87520','IADNA HEPATITIS C DIRECT PROBE TECHNIQUE                                                            \r',NULL,NULL,NULL),(7757,'87521','IADNA HEPATITIS C AMPLIFIED PROBE&REVRSE TRANSCR                                                    \r',NULL,NULL,NULL),(7758,'87522','IADNA HEPATITIS C QUANT & REVERSE TRANSCRIPTION                                                     \r',NULL,NULL,NULL),(7759,'87525','IADNA HEPATITIS G DIRECT PROBE TECHNIQUE                                                            \r',NULL,NULL,NULL),(7760,'87526','IADNA HEPATITIS G AMPLIFIED PROBE TECHNIQUE                                                         \r',NULL,NULL,NULL),(7761,'87527','IADNA HEPATITIS G QUANTIFICATION                                                                    \r',NULL,NULL,NULL),(7762,'87528','IADNA HERPES SIMPLX VIRUS DIRECT PROBE TQ                                                           \r',NULL,NULL,NULL),(7763,'87529','IADNA HERPES SOMPLX VIRUS AMPLIFIED PROBE TQ                                                        \r',NULL,NULL,NULL),(7764,'87530','IADNA HERPES SOMPLX VIRUS QUANTIFICATION                                                            \r',NULL,NULL,NULL),(7765,'87531','IADNA HERPES VIRUS-6 DIRECT PROBE TQ                                                                \r',NULL,NULL,NULL),(7766,'87532','IADNA HERPES VIRUS-6 AMPLIFIED PROBE TQ                                                             \r',NULL,NULL,NULL),(7767,'87533','IADNA HERPES VIRUS-6 QUANTIFICATION                                                                 \r',NULL,NULL,NULL),(7768,'87534','IADNA HIV-1 DIRECT PROBE TECHNIQUE                                                                  \r',NULL,NULL,NULL),(7769,'87535','IADNA HIV-1 AMPLIFIED PROBE & REVERSE TRANSCRPJ                                                     \r',NULL,NULL,NULL),(7770,'87536','IADNA HIV-1 QUANT & REVERSE TRANSCRIPTION                                                           \r',NULL,NULL,NULL),(7771,'87537','IADNA HIV-2 DIRECT PROBE TECHNIQUE                                                                  \r',NULL,NULL,NULL),(7772,'87538','IADNA HIV-2 AMPLIFIED PROBE & REVERSE TRANSCRIPJ                                                    \r',NULL,NULL,NULL),(7773,'87539','IADNA HIV-2 QUANT & REVERSE TRANSCRIPTION                                                           \r',NULL,NULL,NULL),(7774,'87540','IADNA LEGIONELLA PNEUMOPHILA DIRECT PROBE TQ                                                        \r',NULL,NULL,NULL),(7775,'87541','IADNA LEGIONELLA PNEUMOPHILA AMPLIFIED PROBE TQ                                                     \r',NULL,NULL,NULL),(7776,'87542','IADNA LEGIONELLA PNEUMOPHILA QUANTIFICATION                                                         \r',NULL,NULL,NULL),(7777,'87550','IADNA MYCOBACTERIA SPECIES DIRECT PROBE TQ                                                          \r',NULL,NULL,NULL),(7778,'87551','IADNA MYCOBACTERIA SPECIES AMPLIFIED PROBE TQ                                                       \r',NULL,NULL,NULL),(7779,'87552','IADNA MYCOBACTERIA SPECIES QUANTIFICATION                                                           \r',NULL,NULL,NULL),(7780,'87555','IADNA MYCOBACTERIA TUBERCULOSIS DIR PRB                                                             \r',NULL,NULL,NULL),(7781,'87556','IADNA MYCOBACTERIA TUBERCULOSIS AMP PRB                                                             \r',NULL,NULL,NULL),(7782,'87557','IADNA MYCOBACTERIA TUBERCULOSIS QUANTIFICATION                                                      \r',NULL,NULL,NULL),(7783,'87560','IADNA MYCOBACTERIA AVIUM-INTRACLRE DIR PRB                                                          \r',NULL,NULL,NULL),(7784,'87561','IADNA MYCOBACTERIA AVIUM-INTRACLRE AMP PRB                                                          \r',NULL,NULL,NULL),(7785,'87562','IADNA MYCOBACTERIA AVIUM-INTRACELLULARE QUANT                                                       \r',NULL,NULL,NULL),(7786,'87580','IADNA MYCOPLSM PNEUMONIAE DIRECT PROBE TQ                                                           \r',NULL,NULL,NULL),(7787,'87581','IADNA MYCOPLSM PNEUMONIAE AMPLIFIED PROBE TQ                                                        \r',NULL,NULL,NULL),(7788,'87582','IADNA MYCOPLSM PNEUMONIAE QUANTIFICATION                                                            \r',NULL,NULL,NULL),(7789,'87590','IADNA NEISSERIA GONORRHOEAE DIRECT PROBE TQ                                                         \r',NULL,NULL,NULL),(7790,'87591','IADNA NEISSERIA GONORRHOEAE AMPLIFIED PROBE TQ                                                      \r',NULL,NULL,NULL),(7791,'87592','IADNA NEISSERIA GONORRHOEAE QUANTIFICATION                                                          \r',NULL,NULL,NULL),(7792,'87620','IADNA PAPILLOMAVIRUS HUMAN DIRECT PROBE TQ                                                          \r',NULL,NULL,NULL),(7793,'87621','IADNA PAPILLOMAVIRUS HUMAN AMPLIFIED PROBE TQ                                                       \r',NULL,NULL,NULL),(7794,'87622','IADNA PAPILLOMAVIRUS HUMAN QUANTIFICATION                                                           \r',NULL,NULL,NULL),(7795,'87631','IADNA RESPIRATRY PROBE & REV TRNSCR 3-5 TARGETS                                                     \r',NULL,NULL,NULL),(7796,'87632','IADNA RESPIRATRY PROBE & REV TRNSCR 6-11 TARGETS                                                    \r',NULL,NULL,NULL),(7797,'87633','IADNA RESPIRATRY PROBE & REV TRNSCR 12-25 TARGET                                                    \r',NULL,NULL,NULL),(7798,'87640','IADNA S AUREUS AMPLIFIED PROBE TQ                                                                   \r',NULL,NULL,NULL),(7799,'87641','IADNA S AUREUS METHICILLIN RESIST AMP PROBE TQ                                                      \r',NULL,NULL,NULL),(7800,'87650','IADNA STREPTOCOCCUS GROUP A DIRECT PROBE TQ                                                         \r',NULL,NULL,NULL),(7801,'87651','IADNA STREPTOCOCCUS GROUP A AMPLIFIED PROBE TQ                                                      \r',NULL,NULL,NULL),(7802,'87652','IADNA STREPTOCOCCUS GROUP A QUANTIFICATION                                                          \r',NULL,NULL,NULL),(7803,'87653','IADNA STREPTOCOCCUS GROUP B AMPLIFIED PROBE TQ                                                      \r',NULL,NULL,NULL),(7804,'87660','IADNA TRICHOMONAS VAGINALIS DIRECT PROBE TQ                                                         \r',NULL,NULL,NULL),(7805,'87661','IADNA TRICHOMONAS VAGINALIS AMPLIFIED PROBE TECH                                                    \r',NULL,NULL,NULL),(7806,'87797','IADNA NOS DIRECT PROBE TQ EACH ORGANISM                                                             \r',NULL,NULL,NULL),(7807,'87798','IADNA NOS AMPLIFIED PROBE TQ EACH ORGANISM                                                          \r',NULL,NULL,NULL),(7808,'87799','IADNA NOS QUANTIFICATION EACH ORGANISM                                                              \r',NULL,NULL,NULL),(7809,'87800','IADNA MULTIPLE ORGANISMS DIRECT PROBE TQ                                                            \r',NULL,NULL,NULL),(7810,'87801','IADNA MULTIPLE ORGANISMS AMPLIFIED PROBE TQ                                                         \r',NULL,NULL,NULL),(7811,'87802','IAADIADOO STREPTOCOCCUS GROUP B                                                                     \r',NULL,NULL,NULL),(7812,'87803','IAADIADOO CLOSTRIDIUM DIFFICILE TOXIN                                                               \r',NULL,NULL,NULL),(7813,'87804','IAADIADOO INFLUENZA                                                                                 \r',NULL,NULL,NULL),(7814,'87807','IAADIADOO RESPIRATORY SYNCTIAL VIRUS                                                                \r',NULL,NULL,NULL),(7815,'87808','IAADIADOO TRICHOMONAS VAGINALIS                                                                     \r',NULL,NULL,NULL),(7816,'87809','INFECTIOUS AGENT IMMUNOASSAY OPTICAL ADENOVIRUS                                                     \r',NULL,NULL,NULL),(7817,'87810','CHLAMYDIA TRACHOMATIS                                                                               \r',NULL,NULL,NULL),(7818,'87850','IAADIADOO NEISSERIA GONORRHOEAE                                                                     \r',NULL,NULL,NULL),(7819,'87880','IAADIADOO STREPTOCOCCUS GROUP A                                                                     \r',NULL,NULL,NULL),(7820,'87899','IAADIADOO NOT OTHERWISE SPECIFIED                                                                   \r',NULL,NULL,NULL),(7821,'87900','NFCT AGT DRUG SUSCEPT PHENOTYPE PREDICTION                                                          \r',NULL,NULL,NULL),(7822,'87901','NFCT GEXYP NUCLEIC ACID HIV REV TRNSCR&PROTEAS                                                      \r',NULL,NULL,NULL),(7823,'87902','NFCT AGNT GENOTYP NUCLEIC ACID HEPATITIS C VIRUS                                                    \r',NULL,NULL,NULL),(7824,'87903','NFCT PHEXYP RESIST TISS CUL HIV FIRST 1-10 DRUGS                                                    \r',NULL,NULL,NULL),(7825,'87904','NFCT PHEXYP RESIST TISS CUL HIV EA ADDL DRUG                                                        \r',NULL,NULL,NULL),(7826,'87905','INFECTIOUS AGENT ENZYMATIC ACTV OTH/THN VIRUS                                                       \r',NULL,NULL,NULL),(7827,'87906','NFCT GEXYP DNA/RNA HIV 1 OTHER REGION                                                               \r',NULL,NULL,NULL),(7828,'87910','NFCT AGT GENOTYPE NUCLEIC ACID CYTOMEGALOVIRUS                                                      \r',NULL,NULL,NULL),(7829,'87912','NFCT AGENT GENOTYPE HEPATITIS B VIRUS                                                               \r',NULL,NULL,NULL),(7830,'87999','UNLISTED MICROBIOLOGY                                                                               \r',NULL,NULL,NULL),(7831,'88000','NECROPSY GROSS EXAMINATION ONLY W/O CNS                                                             \r',NULL,NULL,NULL),(7832,'88005','NECROPSY GROSS EXAMINATION W/BRAIN                                                                  \r',NULL,NULL,NULL),(7833,'88007','NECROPSY GROSS EXAMINATION W/BRAIN&SPINAL CORD                                                      \r',NULL,NULL,NULL),(7834,'88012','NECROPSY GROSS EXAMINATION INFANT W/BRAIN                                                           \r',NULL,NULL,NULL),(7835,'88014','NECROPSY GROSS EXAM STILLBORN/NEWBORN W/BRAIN                                                       \r',NULL,NULL,NULL),(7836,'88016','NECROPSY GROSS EXAM MACERATED STILLBORN                                                             \r',NULL,NULL,NULL),(7837,'88020','NECROPSY GROSS & MICROSCOPIC W/O CNS                                                                \r',NULL,NULL,NULL),(7838,'88025','NECROPSY GROSS & MICROSCOPIC W/BRAIN                                                                \r',NULL,NULL,NULL),(7839,'88027','NECROPSY GROSS&MCRSCP BRAIN & SPINAL CORD                                                           \r',NULL,NULL,NULL),(7840,'88028','NECROPSY GROSS & MICROSCOPIC INFANT W/BRAIN                                                         \r',NULL,NULL,NULL),(7841,'88029','NECROPSY GROSS&MCRSCP STILLBORN/NEWBORN BRAIN                                                       \r',NULL,NULL,NULL),(7842,'88036','NECROPSY LIMITED GROSS&/MCRSCP REGIONAL                                                             \r',NULL,NULL,NULL),(7843,'88037','NECROPSY LIMITD GROSS&/MCRSCP SINGLE ORGAN                                                          \r',NULL,NULL,NULL),(7844,'88040','NECROPSY FORENSIC EXAMINATION                                                                       \r',NULL,NULL,NULL),(7845,'88045','NECROPSY CORONER CALL                                                                               \r',NULL,NULL,NULL),(7846,'88099','UNLISTED NECROPSY PROCEDURE                                                                         \r',NULL,NULL,NULL),(7847,'88104','CYTP FLU WASHGS/BRUSHINGS XCPT C/V SMRS INTERPJ                                                     \r',NULL,NULL,NULL),(7848,'88106','CYTP FLU BR/WA XCPT C/V FILTER METH ONLY INTERPJ                                                    \r',NULL,NULL,NULL),(7849,'88108','CYTP CONCENTRATION SMEARS & INTERPRETATION                                                          \r',NULL,NULL,NULL),(7850,'88112','CYTP SLCTV CELL ENHANCEMENT INTERPJ XCPT C/V                                                        \r',NULL,NULL,NULL),(7851,'88120','CYTP INSITU HYBRID URINE SPEC 3-5 PROBES EA MNL                                                     \r',NULL,NULL,NULL),(7852,'88121','CYTP INSITU HYBRID URNE SPEC 3-5 PROBES CPTR EA                                                     \r',NULL,NULL,NULL),(7853,'88125','CYTOPATHOLOGY FORENSIC                                                                              \r',NULL,NULL,NULL),(7854,'88130','SEX CHROMATIN IDENTIFICATION BARR BODIES                                                            \r',NULL,NULL,NULL),(7855,'88140','SEX CHROMATIN IDENTJ PERIPHERAL BLOOD SMEAR                                                         \r',NULL,NULL,NULL),(7856,'88141','CYTP CERVICAL/VAGINAL REQ INTERP PHYSICIAN                                                          \r',NULL,NULL,NULL),(7857,'88142','CYTP CERV/VAG AUTO THIN LAYER PREP MNL SCREEN                                                       \r',NULL,NULL,NULL),(7858,'88143','CYTP C/V FLU AUTO THIN MNL SCR&RESCR PHYS                                                           \r',NULL,NULL,NULL),(7859,'88147','CYTP SMRS C/V SCR AUTOMATED SYSTEM PHYS SUPV                                                        \r',NULL,NULL,NULL),(7860,'88148','CYTP SMRS C/V SCR AUTO SYS MNL RESCR PHYS                                                           \r',NULL,NULL,NULL),(7861,'88150','CYTP SLIDES C/V MNL SCR UNDER PHYS                                                                  \r',NULL,NULL,NULL),(7862,'88152','CYTP SLIDES C/V MNL SCR&CPTR RESCR PHYS                                                             \r',NULL,NULL,NULL),(7863,'88153','CYTP SLIDES C/V MNL SCR&RESCR PHYS                                                                  \r',NULL,NULL,NULL),(7864,'88154','CYTP SLIDES C/V MNL SCR&CPTR-RESCR CELL S&I                                                         \r',NULL,NULL,NULL),(7865,'88155','CYTP SLIDES C/V DEFINITIVE HORMONAL EVAL                                                            \r',NULL,NULL,NULL),(7866,'88160','CYTP SMRS ANY OTH SRC SCR&INTERPJ                                                                   \r',NULL,NULL,NULL),(7867,'88161','CYTP SMRS ANY OTH SRC PREPJ SCR&INTERPJ                                                             \r',NULL,NULL,NULL),(7868,'88162','CYTP SMRS ANY OTH SRC EXTND STD > 5 SLIDES                                                          \r',NULL,NULL,NULL),(7869,'88164','CYTP SLIDES CERV/VAG MNL SCRN PHYSICIAN SUPV                                                        \r',NULL,NULL,NULL),(7870,'88165','CYTP SLIDES C/V MNL SCR&RESCR PHYS SUPV                                                             \r',NULL,NULL,NULL),(7871,'88166','CYTP SLIDES C/V MNL SCR&CPTR RESCR PHYS SUPV                                                        \r',NULL,NULL,NULL),(7872,'88167','CYTP SLIDES C/V MNL SCR&CPTR RESCR CELL S&I                                                         \r',NULL,NULL,NULL),(7873,'88172','CYTP FINE NDL ASPIRATE IMMT CYTOHIST STD DX 1ST                                                     \r',NULL,NULL,NULL),(7874,'88173','CYTP EVAL FINE NEEDLE ASPIRATE INTERP & REPORT                                                      \r',NULL,NULL,NULL),(7875,'88174','CYTP C/V AUTO THIN LYR PREPJ SCR SYS PHYS                                                           \r',NULL,NULL,NULL),(7876,'88175','CYTP C/V AUTO THIN LYR PREPJ SCR MNL RESCR PHYS                                                     \r',NULL,NULL,NULL),(7877,'88177','CYTP FINE NDL ASPIRATE IMMT CYTOHIST STD EA EVAL                                                    \r',NULL,NULL,NULL),(7878,'88182','FLOW CYTOMETRY CELL CYCLE/DNA ANALYSIS                                                              \r',NULL,NULL,NULL),(7879,'88184','FLOW CYTOMETRY CELL SURF MARKER TECHL ONLY 1ST                                                      \r',NULL,NULL,NULL),(7880,'88185','FLOW CYTOMETRY CELL SURF MARKER TECHL ONLY EA                                                       \r',NULL,NULL,NULL),(7881,'88187','FLOW CYTOMETRY INTERPJ 2-8 MARKERS                                                                  \r',NULL,NULL,NULL),(7882,'88188','FLOW CYTOMETRY INTERPJ 9-15 MARKERS                                                                 \r',NULL,NULL,NULL),(7883,'88189','FLOW CYTOMETRY INTERPRETATION 16/> MARKERS                                                          \r',NULL,NULL,NULL),(7884,'88199','UNLISTED CYTOPATHOLOGY PROCEDURE                                                                    \r',NULL,NULL,NULL),(7885,'88230','TISS CUL NON-NEO DISORDERS LYMPHOCYTE                                                               \r',NULL,NULL,NULL),(7886,'88233','TISS CUL NON-NEO DISORDERS SKN/OTH SOLID TISS BX                                                    \r',NULL,NULL,NULL),(7887,'88235','TISS CUL NON-NEO DISORDERS AMNIOTIC/CHORNC CELLS                                                    \r',NULL,NULL,NULL),(7888,'88237','TISS CUL NEO DISORDERS BONE MARROW BLOOD CELLS                                                      \r',NULL,NULL,NULL),(7889,'88239','TISS CUL NEO DISORDERS SOLID TUMOR                                                                  \r',NULL,NULL,NULL),(7890,'88240','CRYOPRSRV FRZING&STORAGE CELLS EA CELL LINE                                                         \r',NULL,NULL,NULL),(7891,'88241','THAWING&EXPANSION FROZEN CELLS EACH ALIQUOT                                                         \r',NULL,NULL,NULL),(7892,'88245','CHRMSM BREAKAGE BASELINE SISTER 20-25 CLL                                                           \r',NULL,NULL,NULL),(7893,'88248','CHRMSM BREAKAGE BASELINE BREAKAGE 50-100 CLL                                                        \r',NULL,NULL,NULL),(7894,'88249','CHRMSM BREAKAGE SYNDS SCORE 100 CLL                                                                 \r',NULL,NULL,NULL),(7895,'88261','CHRMSM COUNT 5 CELL 1KARYOTYPE BANDING                                                              \r',NULL,NULL,NULL),(7896,'88262','CHRMSM COUNT 15-20 CLL 2KARYOTYP BANDING                                                            \r',NULL,NULL,NULL),(7897,'88263','CHRMSM COUNT 45 CELL MOSAICISM 2KARYOTYPE                                                           \r',NULL,NULL,NULL),(7898,'88264','CHRMSM ANALYZE 20-25 CELLS                                                                          \r',NULL,NULL,NULL),(7899,'88267','CHRMSM ALYS AMNIOTIC/VILLUS 15 CELL 1KARYOTYPE                                                      \r',NULL,NULL,NULL),(7900,'88269','CHRMSM SITU AMNIOTIC CLL 6-12 COLONIES 1KARYOTYP                                                    \r',NULL,NULL,NULL),(7901,'88271','MOLECULAR CYTOGENETICS DNA PROBE EACH                                                               \r',NULL,NULL,NULL),(7902,'88272','MOLECULAR CYTOGENETICS CHRMOML ISH 3-5 CELLS                                                        \r',NULL,NULL,NULL),(7903,'88273','MOLECULAR CYTOGENETICS CHRMOML ISH 10-30 CLL                                                        \r',NULL,NULL,NULL),(7904,'88274','MOLECULAR CYTOGENETICS INTERPHASE ISH 25-99 CLL                                                     \r',NULL,NULL,NULL),(7905,'88275','MOLEC CYTG INTERPHASE ISH ANALYZE 100-300 CLL                                                       \r',NULL,NULL,NULL),(7906,'88280','CHRMSM ANALYSIS ADDL KARYOTYP EACH STUDY                                                            \r',NULL,NULL,NULL),(7907,'88283','CHRMSM ANALYSIS ADDL SPECIALIZED BANDING                                                            \r',NULL,NULL,NULL),(7908,'88285','CHRMSM ANALYSIS ADDL CELLS COUNTED EACH STUDY                                                       \r',NULL,NULL,NULL),(7909,'88289','CHRMSM ANALYSIS ADDL HIGH RESOLUTION STUDY                                                          \r',NULL,NULL,NULL),(7910,'88291','CYTOGENETICS&MOLEC CYTOGENETICS INTERP&REP                                                          \r',NULL,NULL,NULL),(7911,'88299','UNLISTED CYTOGENETIC STUDY                                                                          \r',NULL,NULL,NULL),(7912,'88300','LEVEL I SURG PATHOLOGY GROSS EXAMINATION ONLY                                                       \r',NULL,NULL,NULL),(7913,'88302','LEVEL II SURG PATHOLOGY GROSS&MICROSCOPIC EXAM                                                      \r',NULL,NULL,NULL),(7914,'88304','LEVEL III SURG PATHOLOGY GROSS&MICROSCOPIC EXA                                                      \r',NULL,NULL,NULL),(7915,'88305','LEVEL IV SURG PATHOLOGY GROSS&MICROSCOPIC EXAM                                                      \r',NULL,NULL,NULL),(7916,'88307','LEVEL V SURG PATHOLOGY GROSS&MICROSCOPIC EXAM                                                       \r',NULL,NULL,NULL),(7917,'88309','LEVEL VI SURG PATHOLOGY GROSS&MICROSCOPIC EXAM                                                      \r',NULL,NULL,NULL),(7918,'88311','DECALCIFICATION PROCEDURE                                                                           \r',NULL,NULL,NULL),(7919,'88312','SPECIAL STAIN GROUP 1 MICROORGANISMS I&R                                                            \r',NULL,NULL,NULL),(7920,'88313','SPCL STN 2 I&R EXCPT MICROORG/ENZYME/IMCYT                                                          \r',NULL,NULL,NULL),(7921,'88314','SPECIAL STAIN I&R HISTOCHEMICAL W/FROZEN TISSU                                                      \r',NULL,NULL,NULL),(7922,'88319','SPECIAL STAIN I&R GROUP III ENZYME CONSITUENTS                                                      \r',NULL,NULL,NULL),(7923,'88321','CONSLTJ&REPRT SLIDES PREPARED ELSEWHERE                                                             \r',NULL,NULL,NULL),(7924,'88323','CONSLTJ&REPRT MATERIAL REQUIRING PREPJ SLIDES                                                       \r',NULL,NULL,NULL),(7925,'88325','CONSLTJ COMPRE REVIEW REPRT REFERRED MATRL                                                          \r',NULL,NULL,NULL),(7926,'88329','PATHOLOGY CONSULTATION DURING SURGERY                                                               \r',NULL,NULL,NULL),(7927,'88331','PATH CONSLTJ SURG 1ST BLK FROZEN SCTJ 1 SPEC                                                        \r',NULL,NULL,NULL),(7928,'88332','PATH CONSLTJ SURG EA ADDL BLK FROZEN SECTION                                                        \r',NULL,NULL,NULL),(7929,'88333','PATH CONSLTJ SURG CYTOLOGIC EXAM INITIAL SITE                                                       \r',NULL,NULL,NULL),(7930,'88334','PATH CONSLTJ SURG CYTOLOGIC EXAM ADDL SITE                                                          \r',NULL,NULL,NULL),(7931,'88342','IMHISTOCHEM/CYTCHM 1ST SEP IDENT ANTIBODY SLIDE                                                     \r',NULL,NULL,NULL),(7932,'88343','IMHISTOCHEM/CYTCHM EA ADDL ANTIBODY SLIDE                                                           \r',NULL,NULL,NULL),(7933,'88346','IMMUNOFLUORESCENT STUDY EA ANTIBODY DIR METHOD                                                      \r',NULL,NULL,NULL),(7934,'88347','IMMUNOFLUORESCENT STUDY EA ANTIBODY INDIR METHOD                                                    \r',NULL,NULL,NULL),(7935,'88348','ELECTRON MICROSCOPY DIAGNOSTIC                                                                      \r',NULL,NULL,NULL),(7936,'88349','ELECTRON MICROSCOPY SCANNING                                                                        \r',NULL,NULL,NULL),(7937,'88355','MORPHOMETRIC ANALYSIS SKELETAL MUSCLE                                                               \r',NULL,NULL,NULL),(7938,'88356','MORPHOMETRIC ANALYSIS NERVE                                                                         \r',NULL,NULL,NULL),(7939,'88358','MORPHOMETRIC ANALYSIS TUMOR                                                                         \r',NULL,NULL,NULL),(7940,'88360','M/PHMTRC ALYS TUMOR IMHCHEM EA ANTIBODY MANUAL                                                      \r',NULL,NULL,NULL),(7941,'88361','M/PHMTRC ALYS TUMOR IMHCHEM EA ANTBDY CMPTR ASST                                                    \r',NULL,NULL,NULL),(7942,'88362','NERVE TEASING PREPARATIONS                                                                          \r',NULL,NULL,NULL),(7943,'88363','EXAM & SELECT ARCHIVE TISSUE MOLECULAR ANALYSI                                                      \r',NULL,NULL,NULL),(7944,'88365','IN SITU HYBRIDIZATION EACH PROBE                                                                    \r',NULL,NULL,NULL),(7945,'88367','M/PHMTRC ALYS ISH EA PRB CPTR-ASST TECHNOLOGY                                                       \r',NULL,NULL,NULL),(7946,'88368','M/PHMTRC ALYS IN SITU HYBRIDIZATION EA PROBE MNL                                                    \r',NULL,NULL,NULL),(7947,'88371','PROTEIN ANAL TISSUE WESTERN BLOT W/INTERP&REPO                                                      \r',NULL,NULL,NULL),(7948,'88372','PROTEIN ALYS WSTRN BLOT I&R IMMUNOLOGICAL EA                                                        \r',NULL,NULL,NULL),(7949,'88375','OPTICAL ENDOMICROSCOPIC IMAGE INTERP & REPORT                                                       \r',NULL,NULL,NULL),(7950,'88380','MICRODISSECTION PREP IDENTIFIED TARGET LASER                                                        \r',NULL,NULL,NULL),(7951,'88381','MICRODISSECTION PREP IDENTIFIED TARGET MANUAL                                                       \r',NULL,NULL,NULL),(7952,'88387','MACRO EXAM DISSECT&PREP TISS NONMICRO STD EA                                                        \r',NULL,NULL,NULL),(7953,'88388','MACR EXM DISS&PRP NONMICR IMPRNT/CONSLT/FRZ SE                                                      \r',NULL,NULL,NULL),(7954,'88399','UNLISTED SURGICAL PATHOLOGY PROCEDURE                                                               \r',NULL,NULL,NULL),(7955,'88720','BILIRUBIN TOTAL TRANSCUTANEOUS                                                                      \r',NULL,NULL,NULL),(7956,'88738','HGB QUANTITATIVE TRANSCUTANEOUS                                                                     \r',NULL,NULL,NULL),(7957,'88740','HEMOGLOBIN QUAN TC PER DAY CARBOXYHEMOGLOBIN                                                        \r',NULL,NULL,NULL),(7958,'88741','HEMOGLOBIN QUANTITATIVE TC PER DAY METHEMOGLOBIN                                                    \r',NULL,NULL,NULL),(7959,'88749','UNLISTED IN VIVO LABORTORY SERVICE                                                                  \r',NULL,NULL,NULL),(7960,'89049','CAFFEINE HALOTHANE CONTRACTURE TEST                                                                 \r',NULL,NULL,NULL),(7961,'89050','CELL COUNT MISCELLANEOUS BODY FLUIDS                                                                \r',NULL,NULL,NULL),(7962,'89051','CELL COUNT MISC BODY FLUIDS W/DIFFERENTIAL COUNT                                                    \r',NULL,NULL,NULL),(7963,'89055','LEUKOCYTE ASSMT FECAL QUAL/SEMIQUANTITATIVE                                                         \r',NULL,NULL,NULL),(7964,'89060','CRYSTAL ID LIGHT MICROSCOPY ALYS TISS/ANY FLUID                                                     \r',NULL,NULL,NULL),(7965,'89125','FAT STAIN FECES URINE/RESPIR SECRETIONS                                                             \r',NULL,NULL,NULL),(7966,'89160','MEAT FIBERS FECES                                                                                   \r',NULL,NULL,NULL),(7967,'89190','NASAL SMEAR EOSINOPHILS                                                                             \r',NULL,NULL,NULL),(7968,'89220','SPUTUM OBTAINING SPEC AEROSOL INDUCED TX SPX                                                        \r',NULL,NULL,NULL),(7969,'89230','SWEAT COLLECTION IONTOPHORESIS                                                                      \r',NULL,NULL,NULL),(7970,'89240','UNLIS MISC PATH                                                                                     \r',NULL,NULL,NULL),(7971,'89250','CUL OOCYTE/EMBRYO <4 DAYS                                                                           \r',NULL,NULL,NULL),(7972,'89251','CUL OOCYTE/EMBRYO < 4 D CO-CULT OCYTE/EMBRY                                                         \r',NULL,NULL,NULL),(7973,'89253','ASSTD EMBRYO HATCHING MICROTQS ANY METH                                                             \r',NULL,NULL,NULL),(7974,'89254','OOCYTE ID FROM FOLLICULAR FLU                                                                       \r',NULL,NULL,NULL),(7975,'89255','PREPJ EMBRYO TR                                                                                     \r',NULL,NULL,NULL),(7976,'89257','SPRM ID FROM ASPIR OTH/THN SEMINAL                                                                  \r',NULL,NULL,NULL),(7977,'89258','CRYOPRSRV EMBRYO                                                                                    \r',NULL,NULL,NULL),(7978,'89259','CRYOPRSRV SPRM                                                                                      \r',NULL,NULL,NULL),(7979,'89260','SPRM ISOL SMPL PREP INSEMINATION/DX SEMEN ALYS                                                      \r',NULL,NULL,NULL),(7980,'89261','SPRM ISOL CPLX PREP INSEMINATION/DX SEMEN ALYS                                                      \r',NULL,NULL,NULL),(7981,'89264','SPRM ID FROM TSTIS TISS FRSH/CRYOPRSRVD                                                             \r',NULL,NULL,NULL),(7982,'89268','INSEMINATION OOCYTES                                                                                \r',NULL,NULL,NULL),(7983,'89272','EXTND CUL OOCYTE/EMBRYO 4-7 DAYS                                                                    \r',NULL,NULL,NULL),(7984,'89280','ASSTD FERTILIZATION MICROTQ </EQUAL 10 OOCYTES                                                      \r',NULL,NULL,NULL),(7985,'89281','ASSTD FERTILIZATION MICROTQ > 10 OOCYTES                                                            \r',NULL,NULL,NULL),(7986,'89290','BX OOCYTE MICROTQ >/EQUAL 5 EMBRY                                                                   \r',NULL,NULL,NULL),(7987,'89291','BX OOCYTE MICROTQ >5 EMBRY                                                                          \r',NULL,NULL,NULL),(7988,'89300','SEMEN ALYS PRESENCE&/MOTILITY SPRM HUHNER                                                           \r',NULL,NULL,NULL),(7989,'89310','SEMEN ALYS MOTILITY&CNT X W/HUHNER TST                                                              \r',NULL,NULL,NULL),(7990,'89320','SEMEN ANALYSIS VOLUME COUNT MOTILITY DIFFERENT                                                      \r',NULL,NULL,NULL),(7991,'89321','SEMEN ANALYSIS SPERM PRESENCE&/MOTILITY SPRM                                                        \r',NULL,NULL,NULL),(7992,'89322','SEMEN ANALYSIS STRICT MORPHOLOGIC CRITERIA                                                          \r',NULL,NULL,NULL),(7993,'89325','SPERM ANTIBODIES                                                                                    \r',NULL,NULL,NULL),(7994,'89329','SPERM EVALUATION HAMSTER PENETRATION TEST                                                           \r',NULL,NULL,NULL),(7995,'89330','SPERM EVALUATION CERVICAL MUCOUS PENETRATION                                                        \r',NULL,NULL,NULL),(7996,'89331','SPERM EVALUATION RETROGRADE EJACULATION URINE                                                       \r',NULL,NULL,NULL),(7997,'89335','CRYOPRSRV REPRODUCTIVE TISSUE TESTICULAR                                                            \r',NULL,NULL,NULL),(7998,'89342','STORAGE PER YEAR EMBRYO                                                                             \r',NULL,NULL,NULL),(7999,'89343','STORAGE PER YEAR SPERM/SEMEN                                                                        \r',NULL,NULL,NULL),(8000,'89344','STORAGE PER YR REPRDTVE TISS TSTICULAR/OVARIAN                                                      \r',NULL,NULL,NULL),(8001,'89346','STORAGE PER YEAR OOCYTE                                                                             \r',NULL,NULL,NULL),(8002,'89352','THAWING CRYOPRESERVED EMBRYO                                                                        \r',NULL,NULL,NULL),(8003,'89353','THAWING CRYOPRESERVED SPERM/SEMEN EACH ALIQUOT                                                      \r',NULL,NULL,NULL),(8004,'89354','THAWING CRYOPRESERVED TESTICULAR/OVARIAN                                                            \r',NULL,NULL,NULL),(8005,'89356','THAWING CRYOPRESERVED OOCYTES EACH ALIQUOT                                                          \r',NULL,NULL,NULL),(8006,'89398','UNLISTED REPRODUCTIVE MEDICINE LAB PROCEDURE                                                        \r',NULL,NULL,NULL),(8007,'90281','IMMUNE GLOBULIN IG HUMAN IM USE                                                                     \r',NULL,NULL,NULL),(8008,'90283','IMMUNE GLOBULIN IGIV HUMAN IV USE                                                                   \r',NULL,NULL,NULL),(8009,'90284','IMMUNE GLOBULIN HUMAN SUBQ INFUSION 100 MG EA                                                       \r',NULL,NULL,NULL),(8010,'90287','BOTULINUM ANTITOXIN EQUINE ANY ROUTE                                                                \r',NULL,NULL,NULL),(8011,'90288','BOTULISM IMMUNE GLOBULIN HUMAN INTRAVENOUS USE                                                      \r',NULL,NULL,NULL),(8012,'90291','CYTOMEGALOVIRUS IMMUNE GLOBULIN HUMAN IV                                                            \r',NULL,NULL,NULL),(8013,'90296','DIPHTHERIA ANTITOXIN EQUINE ANY ROUTE                                                               \r',NULL,NULL,NULL),(8014,'90371','HEPATITIS B IMMUNE GLOBULIN HBIG HUMAN IM                                                           \r',NULL,NULL,NULL),(8015,'90375','RABIES IMMUNE GLOBULIN RIG HUMAN IM/SUBQ                                                            \r',NULL,NULL,NULL),(8016,'90376','RABIES IG HEAT-TREATED HUMAN IM/SUBQ                                                                \r',NULL,NULL,NULL),(8017,'90378','RESPIRATORY SYNCYTIAL VIRUS IG IM 50 MG E                                                           \r',NULL,NULL,NULL),(8018,'90384','RHO(D) IMMUNE GLOBULIN HUMAN FULL-DOSE IM                                                           \r',NULL,NULL,NULL),(8019,'90385','RHO(D) IMMUNE GLOBULIN HUMAN MINI-DOSE IM                                                           \r',NULL,NULL,NULL),(8020,'90386','RHO(D) IMMUNE GLOBULIN HUMAN IV                                                                     \r',NULL,NULL,NULL),(8021,'90389','TETANUS IMMUNE GLOBULIN TIG HUMAN IM                                                                \r',NULL,NULL,NULL),(8022,'90393','VACCINIA IMMUNE GLOBULIN HUMAN IM                                                                   \r',NULL,NULL,NULL),(8023,'90396','VARICELLA-ZOSTER IMMUNE GLOBULIN HUMAN IM                                                           \r',NULL,NULL,NULL),(8024,'90399','UNLISTED IMMUNE GLOBULIN                                                                            \r',NULL,NULL,NULL),(8025,'90460','IM ADM THRU 18YR ANY RTE 1ST/ONLY COMPT VAC/TOX                                                     \r',NULL,NULL,NULL),(8026,'90461','IM ADM THRU 18YR ANY RTE ADDL VAC/TOX COMPT                                                         \r',NULL,NULL,NULL),(8027,'90471','IMADM PRQ ID SUBQ/IM NJXS 1 VACCINE                                                                 \r',NULL,NULL,NULL),(8028,'90472','IMADM PRQ ID SUBQ/IM NJXS EA VACCINE                                                                \r',NULL,NULL,NULL),(8029,'90473','IMADM INTRANSL/ORAL 1 VACCINE                                                                       \r',NULL,NULL,NULL),(8030,'90474','IMADM INTRANSL/ORAL EA VACCINE                                                                      \r',NULL,NULL,NULL),(8031,'90476','ADENOVIRUS VACCINE TYPE 4 LIVE ORAL                                                                 \r',NULL,NULL,NULL),(8032,'90477','ADENOVIRUS VACCINE TYPE 7 LIVE FOR ORAL                                                             \r',NULL,NULL,NULL),(8033,'90581','ANTHRAX VACCINE SUBCUTANEOUS/IM USE                                                                 \r',NULL,NULL,NULL),(8034,'90585','BACILLUS CALMETTE-GUERIN VACC FOR TB LIVE PERQ                                                      \r',NULL,NULL,NULL),(8035,'90586','BACILLUS CALMETTE-GUERIN VACCINE INTRAVESICAL                                                       \r',NULL,NULL,NULL),(8036,'90632','HEPATITIS A VACCINE ADULT FOR INTRAMUSCULAR USE                                                     \r',NULL,NULL,NULL),(8037,'90633','HEPATITIS A VACCINE PEDIATRIC 2 DOSE SCHEDULE IM                                                    \r',NULL,NULL,NULL),(8038,'90634','HEPATITIS A VACCINE PEDIATRIC 3 DOSE SCHEDULE IM                                                    \r',NULL,NULL,NULL),(8039,'90636','HEPATITIS A & B VACCINE HEPA-HEPB ADULT IM                                                          \r',NULL,NULL,NULL),(8040,'90644','MENINGOCOCCAL & HIB-MENCY VACCINE 4 DOSE IM                                                         \r',NULL,NULL,NULL),(8041,'90645','HEMOPHILUS INFLUENZA B VACC HBOC CONJ 4 DOSE IM                                                     \r',NULL,NULL,NULL),(8042,'90646','HEMOPHILUS INFLUENZA B VACCINE PRP-D BOOSTER IM                                                     \r',NULL,NULL,NULL),(8043,'90647','HEMOPHILUS INFLUENZA B VACCINE PRP-OMP 3 DOSE IM                                                    \r',NULL,NULL,NULL),(8044,'90648','HEMOPHILUS INFLUENZA B VACCINE PRP-T 4 DOSE IM                                                      \r',NULL,NULL,NULL),(8045,'90649','HUMAN PAPILLOMA VIRUS VACCINE QUADRIV 3 DOSE IM                                                     \r',NULL,NULL,NULL),(8046,'90650','HUMAN PAPILLOMA VIRUS BIVALENT VACCINE 3 DOSE IM                                                    \r',NULL,NULL,NULL),(8047,'90653','INFLUENZA VACCINE INACT SUBUNIT ADJUVANT IM                                                         \r',NULL,NULL,NULL),(8048,'90654','INFLUENZA VACCINE SPLIT VIRUS PRSRV FREE ID                                                         \r',NULL,NULL,NULL),(8049,'90655','INFLUENZA VACC TRIVALENT PRSRV FREE 6-35 MO IM                                                      \r',NULL,NULL,NULL),(8050,'90656','INFLUENZA VIRUS VACC SPLIT PRSRV FREE 3 YRS/> IM                                                    \r',NULL,NULL,NULL),(8051,'90657','INFLUENZA VIRUS VACCINE SPLIT VIRUS 6-35 MO IM                                                      \r',NULL,NULL,NULL),(8052,'90658','INFLUENZA VIRUS VACCINE SPLIT VIRUS 3/> YRS IM                                                      \r',NULL,NULL,NULL),(8053,'90660','INFLUENZA VIRUS VACCINE LIVE INTRANASAL                                                             \r',NULL,NULL,NULL),(8054,'90661','INFLUENZA VACCINE CELL CULT PRSRV FREE IM                                                           \r',NULL,NULL,NULL),(8055,'90662','INFLUENZA VACCINE SPLT PRSRV FREE INC ANTIGEN IM                                                    \r',NULL,NULL,NULL),(8056,'90664','INFLUENZA VAC PANDEMIC FORMULA LIVE INTRANASAL                                                      \r',NULL,NULL,NULL),(8057,'90666','INFLUENZA VACCINE PANDEMIC SPLT PRSRV FREE IM                                                       \r',NULL,NULL,NULL),(8058,'90667','INFLUENZA VACCINE PANDEMIC SPLT ADJUVANT IM                                                         \r',NULL,NULL,NULL),(8059,'90668','INFLUENZA VACCINE PANDEMIC SPLT IM                                                                  \r',NULL,NULL,NULL),(8060,'90669','PNEUMOCOCCAL CONJ VACCINE 7 VALENT IM                                                               \r',NULL,NULL,NULL),(8061,'90670','PNEUMOCOCCAL CONJ VACCINE 13 VALENT IM                                                              \r',NULL,NULL,NULL),(8062,'90672','INFLUENZA VIRUS VAC QUADRIVALENT LIVE INTRANASAL                                                    \r',NULL,NULL,NULL),(8063,'90673','INFLUENZA VIRUS VACCINE TRIVALEN RIV3 PRSR FR IM                                                    \r',NULL,NULL,NULL),(8064,'90675','RABIES VACCINE INTRAMUSCULAR                                                                        \r',NULL,NULL,NULL),(8065,'90676','RABIES VACCINE INTRADERMAL                                                                          \r',NULL,NULL,NULL),(8066,'90680','ROTAVIRUS VACCINE PENTAVALENT 3 DOSE LIVE ORAL                                                      \r',NULL,NULL,NULL),(8067,'90681','ROTAVIRUS VACC HUMAN ATTENUATED 2 DOSE LIVE ORAL                                                    \r',NULL,NULL,NULL),(8068,'90685','INFLUENZA VAC QUADRIVALENT PRSRV FREE 6-35 MO IM                                                    \r',NULL,NULL,NULL),(8069,'90686','INFLUENZA VAC 4 VALENT PRSRV FREE 3 YRS PLUS IM                                                     \r',NULL,NULL,NULL),(8070,'90687','INFLUENZA VACCINE QUADRIVALENT 6-35 MO IM                                                           \r',NULL,NULL,NULL),(8071,'90688','INFLUENZA VACCINE QUADRIVALENT 3 YRS PLUS IM                                                        \r',NULL,NULL,NULL),(8072,'90690','TYPHOID VACCINE LIVE ORAL                                                                           \r',NULL,NULL,NULL),(8073,'90691','TYPHOID VACCINE VI CAPSULAR POLYSACCHARIDE IM                                                       \r',NULL,NULL,NULL),(8074,'90692','TYPHOID VACC H-P INACTIVATED SUBQ/INTRADERMAL                                                       \r',NULL,NULL,NULL),(8075,'90693','TYPHOID VACCINE AKD SUBQ U.S. MILITARY                                                              \r',NULL,NULL,NULL),(8076,'90696','DTAP-IPV INACTIVATED ADMIN PTS AGE 4-6 YRS IM                                                       \r',NULL,NULL,NULL),(8077,'90698','DTAP-HIB-IPV INACTIVATED VACCINE IM                                                                 \r',NULL,NULL,NULL),(8078,'90700','DIPHTH TETANUS TOX ACELL PERTUSSIS VACC<7 YR IM                                                     \r',NULL,NULL,NULL),(8079,'90702','DIPHTHERIA TETANUS TOXOID ADSORBED <7 YR IM                                                         \r',NULL,NULL,NULL),(8080,'90703','TETANUS TOXOID ADSORBED INTRAMUSCULAR                                                               \r',NULL,NULL,NULL),(8081,'90704','MUMPS VIRUS VACCINE LIVE SUBCUTANEOUS                                                               \r',NULL,NULL,NULL),(8082,'90705','MEASLES VIRUS VACCINE LIVE SUBCUTANEOUS                                                             \r',NULL,NULL,NULL),(8083,'90706','RUBELLA VIRUS VACCINE LIVE SUBCUTANEOUS                                                             \r',NULL,NULL,NULL),(8084,'90707','MEASLES MUMPS RUBELLA VIRUS VACCINE LIVE SUBQ                                                       \r',NULL,NULL,NULL),(8085,'90708','MEASLES & RUBELLA VIRUS VACCINE LIVE SUBQ                                                           \r',NULL,NULL,NULL),(8086,'90710','MEASLES MUMPS RUBELLA VARICELLA VACC LIVE SUBQ                                                      \r',NULL,NULL,NULL),(8087,'90712','POLIOVIRUS VACCINE ANY LIVE ORAL                                                                    \r',NULL,NULL,NULL),(8088,'90713','POLIOVIRUS VACCINE INACTIVATED SUBQ/IM                                                              \r',NULL,NULL,NULL),(8089,'90714','TD TOXOIDS ADSORBED PRSRV FR 7 YR/> IM                                                              \r',NULL,NULL,NULL),(8090,'90715','TDAP VACCINE 7 YRS/> IM                                                                             \r',NULL,NULL,NULL),(8091,'90716','VARICELLA VIRUS VACCINE LIVE SUBQ                                                                   \r',NULL,NULL,NULL),(8092,'90717','YELLOW FEVER VACCINE LIVE SUBQ                                                                      \r',NULL,NULL,NULL),(8093,'90719','DIPHTHERIA TOXOID INTRAMUSCULAR                                                                     \r',NULL,NULL,NULL),(8094,'90720','DTP-HIB VACCINE INTRAMUSCULAR                                                                       \r',NULL,NULL,NULL),(8095,'90721','DTAP-HIB VACCINE INTRAMUSCULAR                                                                      \r',NULL,NULL,NULL),(8096,'90723','DTAP-HEPB-IPV VACCINE INTRAMUSCULAR                                                                 \r',NULL,NULL,NULL),(8097,'90725','CHOLERA VACCINE INJECTABLE                                                                          \r',NULL,NULL,NULL),(8098,'90727','PLAGUE VACCINE INTRAMUSCULAR                                                                        \r',NULL,NULL,NULL),(8099,'90732','PNEUMOCOCCAL POLYSAC VACCINE 23-V 2 YRS/>SUBQ/IM                                                    \r',NULL,NULL,NULL),(8100,'90733','MENINGOCOCCAL POLYSAC VACCINE SUBCUTANEOUS                                                          \r',NULL,NULL,NULL),(8101,'90734','MENINGOCOCCAL CONJ VACCINE TETRAVALENT IM                                                           \r',NULL,NULL,NULL),(8102,'90735','JAPANESE ENCEPHALITIS VIRUS VACCINE SUBCUTANEOUS                                                    \r',NULL,NULL,NULL),(8103,'90736','ZOSTER SHINGLES VACCINE LIVE SUBCUTANEOUS                                                           \r',NULL,NULL,NULL),(8104,'90738','JAPANESE ENCEPHALITIS VACCINE INACTIVATED IM                                                        \r',NULL,NULL,NULL),(8105,'90739','HEPATITIS B VACCINE ADULT 2 DOSE IM                                                                 \r',NULL,NULL,NULL),(8106,'90740','HEPATITIS B VACCINE DIALYSIS DOSAGE 3 DOSE IM                                                       \r',NULL,NULL,NULL),(8107,'90743','HEPATITIS B VACCINE ADOLESCENT 2 DOSE IM                                                            \r',NULL,NULL,NULL),(8108,'90744','HEPATITIS B VACCINE PEDIATRIC3 DOSE IM                                                              \r',NULL,NULL,NULL),(8109,'90746','HEPATITIS B VACCINE ADULT 3 DOSE IM                                                                 \r',NULL,NULL,NULL),(8110,'90747','HEPATITIS B VACCINE DIALYSIS DOSAGE 4 DOSE IM                                                       \r',NULL,NULL,NULL),(8111,'90748','HEPB-HIB VACCINE INTRAMUSCULAR                                                                      \r',NULL,NULL,NULL),(8112,'90749','UNLISTED VACCINE/TOXOID                                                                             \r',NULL,NULL,NULL),(8113,'90785','PSYCHOTHERAPY COMPLEX INTERACTIVE                                                                   \r',NULL,NULL,NULL),(8114,'90791','PSYCHIATRIC DIAGNOSTIC EVALUATION                                                                   \r',NULL,NULL,NULL),(8115,'90792','PSYCHIATRIC DIAGNOSTIC EVAL W/MEDICAL SERVICES                                                      \r',NULL,NULL,NULL),(8116,'90832','PSYCHOTHERAPY PATIENT &/ FAMILY 30 MINUTES                                                          \r',NULL,NULL,NULL),(8117,'90833','PSYCHOTHERAPY PT&/FAMILY W/E&M SRVCS 30 MIN                                                         \r',NULL,NULL,NULL),(8118,'90834','PSYCHOTHERAPY PATIENT &/ FAMILY 45 MINUTES                                                          \r',NULL,NULL,NULL),(8119,'90836','PSYCHOTHERAPY PT&/FAMILY W/E&M SRVCS 45 MIN                                                         \r',NULL,NULL,NULL),(8120,'90837','PSYCHOTHERAPY PATIENT &/ FAMILY 60 MINUTES                                                          \r',NULL,NULL,NULL),(8121,'90838','PSYCHOTHERAPY PT&/FAMILY W/E&M SRVCS 60 MIN                                                         \r',NULL,NULL,NULL),(8122,'90839','PSYCHOTHERAPY FOR CRISIS INITIAL 60 MINUTES                                                         \r',NULL,NULL,NULL),(8123,'90840','PSYCHOTHERAPY FOR CRISIS EACH ADDL 30 MINUTES                                                       \r',NULL,NULL,NULL),(8124,'90845','PSYCHOANALYSIS                                                                                      \r',NULL,NULL,NULL),(8125,'90846','FAMILY PSYCHOTHERAPY W/O PATIENT PRESENT                                                            \r',NULL,NULL,NULL),(8126,'90847','FAMILY PSYCHOTHERAPY W/PATIENT PRESENT                                                              \r',NULL,NULL,NULL),(8127,'90849','MULTIPLE FAMILY GROUP PSYCHOTHERAPY                                                                 \r',NULL,NULL,NULL),(8128,'90853','GROUP PSYCHOTHERAPY                                                                                 \r',NULL,NULL,NULL),(8129,'90863','PHARMACOLOGIC MANAGEMENT W/PSYCHOTHERAPY                                                            \r',NULL,NULL,NULL),(8130,'90865','NARCOSYNTHESIS PSYC DX&THER PURPOSES                                                                \r',NULL,NULL,NULL),(8131,'90867','REPET TMS TX INITIAL W/MAP/MOTR THRESHLD/DEL&M                                                      \r',NULL,NULL,NULL),(8132,'90868','THERAP REPETITIVE TMS TX SUBSEQ DELIVERY & MNG                                                      \r',NULL,NULL,NULL),(8133,'90869','REPET TMS TX SUBSEQ MOTR THRESHLD W/DELIV & MN                                                      \r',NULL,NULL,NULL),(8134,'90870','ELECTROCONVULSIVE THERAPY                                                                           \r',NULL,NULL,NULL),(8135,'90875','INDIV PSYCHOPHYS BIOFEED TRAIN W/PSYTX 30 MIN                                                       \r',NULL,NULL,NULL),(8136,'90876','INDIV PSYCHOPHYS BIOFEED TRAIN W/PSYTX 45 MIN                                                       \r',NULL,NULL,NULL),(8137,'90880','HYPNOTHERAPY                                                                                        \r',NULL,NULL,NULL),(8138,'90882','ENVIRONMENTAL IVNTJ MGMT PURPOSES PSYC PT                                                           \r',NULL,NULL,NULL),(8139,'90885','PSYCHIATRIC EVAL HOSPITAL RECORDS DX PURPOSES                                                       \r',NULL,NULL,NULL),(8140,'90887','INTERPJ/EXPLNAJ RESULTS PSYCHIATRIC EXAM FAMILY                                                     \r',NULL,NULL,NULL),(8141,'90889','PREP REPORT PT PSYCH STATUS AGENCY/PAYER                                                            \r',NULL,NULL,NULL),(8142,'90899','UNLISTED PSYCHIATRIC SERVICE/PROCEDURE                                                              \r',NULL,NULL,NULL),(8143,'90901','BIOFEEDBACK TRAINING ANY MODALITY                                                                   \r',NULL,NULL,NULL),(8144,'90911','BIOFDBK TRNG PERINL MUSC ANORECT/URO SPHX W/EMG                                                     \r',NULL,NULL,NULL),(8145,'90935','HEMODIALYSIS PROCEDURE W/ PHYS/QHP EVALUATION                                                       \r',NULL,NULL,NULL),(8146,'90937','HEMODIALYSIS PX REPEAT EVAL W/WO REVJ DIALYS RX                                                     \r',NULL,NULL,NULL),(8147,'90940','HEMODIALYSIS ACCESS FLOW STUDY                                                                      \r',NULL,NULL,NULL),(8148,'90945','DIALYSIS OTHER/THAN HEMODIALYSIS 1 PHYS/QHP EVAL                                                    \r',NULL,NULL,NULL),(8149,'90947','DIALYSIS OTH/THN HEMODIALY REPEAT PHYS/QHP EVALS                                                    \r',NULL,NULL,NULL),(8150,'90951','ESRD RELATED SVC MONTHLY & <2 YR OLD 4/> VISITS                                                     \r',NULL,NULL,NULL),(8151,'90952','ESRD RELATED SVC MONTHLY <2 YR OLD 2/3 VISITS                                                       \r',NULL,NULL,NULL),(8152,'90953','ESRD RELATED SVC MONTHLY <2 YR OLD 1 VISIT                                                          \r',NULL,NULL,NULL),(8153,'90954','ESRD RELATED SVC MONTHLY 2-11 YR OLD 4/> VISITS                                                     \r',NULL,NULL,NULL),(8154,'90955','ESRD RELATED SVC MONTHLY 2-11 YR OLD 2/3 VISITS                                                     \r',NULL,NULL,NULL),(8155,'90956','ESRD RELATED SVC MONTHLY 2-11 YR OLD 1 VISIT                                                        \r',NULL,NULL,NULL),(8156,'90957','ESRD RELATED SVC MONTHLY 12-19 YR OLD 4/> VISITS                                                    \r',NULL,NULL,NULL),(8157,'90958','ESRD RELATED SVC MONTHLY 12-19 YR OLD 2/3 VISITS                                                    \r',NULL,NULL,NULL),(8158,'90959','ESRD RELATED SVC MONTHLY 12-19 YR OLD 1 VISIT                                                       \r',NULL,NULL,NULL),(8159,'90960','ESRD RELATED SVC MONTHLY 20&/> YR OLD 4/> VISITS                                                    \r',NULL,NULL,NULL),(8160,'90961','ESRD RELATED SVC MONTHLY 20/>YR OLD 2/3 VISITS                                                      \r',NULL,NULL,NULL),(8161,'90962','ESRD RELATED SVC MONTHLY 20&/>YR OLD 1 VISIT                                                        \r',NULL,NULL,NULL),(8162,'90963','ESRD SVC HOME DIALYSIS FULL MONTH <2YR OLD                                                          \r',NULL,NULL,NULL),(8163,'90964','ESRD SVC HOME DIALYSIS FULL MONTH 2-11 YR OLD                                                       \r',NULL,NULL,NULL),(8164,'90965','ESRD SVC HOME DIALYSIS FULL MONTH 12-19 YR OLD                                                      \r',NULL,NULL,NULL),(8165,'90966','ESRD SVC HOME DIALYSIS FULL MONTH 20 YR OLD                                                         \r',NULL,NULL,NULL),(8166,'90967','ESRD RELATED SVC <FULL MONTH <2 YR OLD                                                              \r',NULL,NULL,NULL),(8167,'90968','ESRD RELATED SVC <FULL MONTH 2-11 YR OLD                                                            \r',NULL,NULL,NULL),(8168,'90969','ESRD RELATED SVC <FULL MONTH 12-19 YR OLD                                                           \r',NULL,NULL,NULL),(8169,'90970','ESRD RELATED SVC <FULL MONTH 20/>YR OLD                                                             \r',NULL,NULL,NULL),(8170,'90989','DIALYSIS TRAINING PATIENT COMPLETED COURSE                                                          \r',NULL,NULL,NULL),(8171,'90993','DIALYSIS TRAINING PATIENT PER TRAINING SESSION                                                      \r',NULL,NULL,NULL),(8172,'90997','HEMOPERFUSION                                                                                       \r',NULL,NULL,NULL),(8173,'90999','UNLISTED DIALYSIS PROCEDURE INPATIENT/OUTPATIENT                                                    \r',NULL,NULL,NULL),(8174,'91010','ESOPHAGEAL MOTILITY STUDY W/INTERP&RPT                                                              \r',NULL,NULL,NULL),(8175,'91013','ESOPHAGEAL MOTILITY STD W/I&R STIM/PERFUSION                                                        \r',NULL,NULL,NULL),(8176,'91020','GASTRIC MOTILITY MANOMETRIC STUDIES                                                                 \r',NULL,NULL,NULL),(8177,'91022','DUODENAL MOTILITY MANOMETRIC STUDY                                                                  \r',NULL,NULL,NULL),(8178,'91030','ESOPHAGUS ACID PERFUSION TEST ESOPHAGITIS                                                           \r',NULL,NULL,NULL),(8179,'91034','GASTROESOPHAG REFLX TEST W/CATH PH ELTRD PLCMT                                                      \r',NULL,NULL,NULL),(8180,'91035','GASTROESOPHAG REFLX TEST W/TELEMTRY PH ELTRD                                                        \r',NULL,NULL,NULL),(8181,'91037','GASTROESOPHAG REFLX TEST W/INTRLUML IMPED ELTRD                                                     \r',NULL,NULL,NULL),(8182,'91038','ESOPHGL FUNCJ G-ESOP RFLX IMPD ELTRD PROLNG                                                         \r',NULL,NULL,NULL),(8183,'91040','ESOPHGL BALO DISTENSION PROVOCATION STD                                                             \r',NULL,NULL,NULL),(8184,'91065','BREATH HYDROGEN/METHANE TEST                                                                        \r',NULL,NULL,NULL),(8185,'91110','GI IMAG INTRALUMINAL ESOPHAGUS-ILEUM W/I&R                                                          \r',NULL,NULL,NULL),(8186,'91111','GASTROINTESTINAL TRACT IMAGING ESOPHAGUS W/I&R                                                      \r',NULL,NULL,NULL),(8187,'91112','GI TRANSIT & PRES MEAS WIRELESS CAPSULE W/INTERP                                                    \r',NULL,NULL,NULL),(8188,'91117','COLON MOTILITY STDY MIN 6 HR CONT RECORD W/I&R                                                      \r',NULL,NULL,NULL),(8189,'91120','RECTAL SESATION TONE & COMPLIANCE TEST                                                              \r',NULL,NULL,NULL),(8190,'91122','ANORECTAL MANOMETRY                                                                                 \r',NULL,NULL,NULL),(8191,'91132','ELECTROGASTROGRAPHY DX TRANSCUTANEOUS                                                               \r',NULL,NULL,NULL),(8192,'91133','ELECTROGASTROGRAPHY DX TRANSCUT W/PROVOCTVE TSTG                                                    \r',NULL,NULL,NULL),(8193,'91299','UNLISTED DIAGNOSTIC GASTROENTEROLOGY PROCEDURE                                                      \r',NULL,NULL,NULL),(8194,'92002','OPHTH MEDICAL XM&EVAL INTERMEDIATE NEW PT                                                           \r',NULL,NULL,NULL),(8195,'92004','OPHTH MEDICAL XM&EVAL COMPRE NEW PT 1/> VST                                                         \r',NULL,NULL,NULL),(8196,'92012','OPHTH MEDICAL XM&EVAL INTERMEDIATE ESTAB PT                                                         \r',NULL,NULL,NULL),(8197,'92014','OPHTH MEDICAL XM&EVAL COMPRHNSV ESTAB PT 1/>                                                        \r',NULL,NULL,NULL),(8198,'92015','DETERMINATION REFRACTIVE STATE                                                                      \r',NULL,NULL,NULL),(8199,'92018','OPHTH XM&EVAL ANES W/WO MANJ GLOBE COMPL                                                            \r',NULL,NULL,NULL),(8200,'92019','OPHTH XM&EVAL ANES W/WO MANJ GLOBE LMTD                                                             \r',NULL,NULL,NULL),(8201,'92020','GONIOSCOPY SEPARATE PROCEDURE                                                                       \r',NULL,NULL,NULL),(8202,'92025','COMPUTERIZED CORNEAL TOPOGRAPHY UNI/BI                                                              \r',NULL,NULL,NULL),(8203,'92060','SENSORMOTOR XM W/MLT MEAS OCULAR DEVIJ W/I&R SPX                                                    \r',NULL,NULL,NULL),(8204,'92065','ORTHOPTIC &/PLEOPTIC TRAINING W/MEDICAL DIRECTJ                                                     \r',NULL,NULL,NULL),(8205,'92071','FIT CONTACT LENS TX OCULAR SURFACE DISEASE                                                          \r',NULL,NULL,NULL),(8206,'92072','FITTING CONTACT LENS FOR MNGT OF KERATOCONUS                                                        \r',NULL,NULL,NULL),(8207,'92081','VISUAL FIELD XM UNI/BI W/INTERPRETJ LIMITED EXAM                                                    \r',NULL,NULL,NULL),(8208,'92082','VISUAL FIELD XM UNI/BI W/INTERP INTERMED EXAM                                                       \r',NULL,NULL,NULL),(8209,'92083','VISUAL FIELD XM UNI/BI W/INTERP EXTENDED EXAM                                                       \r',NULL,NULL,NULL),(8210,'92100','SERIAL TONOMETRY SPX W/MLT MEAS INTRAOCULAR PRES                                                    \r',NULL,NULL,NULL),(8211,'92132','CMPTR OPHTHALMIC DX IMG ANT SEGMT W/I&R UNI/BI                                                      \r',NULL,NULL,NULL),(8212,'92133','COMPUTERIZED OPHTHALMIC IMAGING OPTIC NERVE                                                         \r',NULL,NULL,NULL),(8213,'92134','COMPUTERIZED OPHTHALMIC IMAGING RETINA                                                              \r',NULL,NULL,NULL),(8214,'92136','OPH BMTRY PRTL COHER INTRFRMTRY IO LENS PWR CAL                                                     \r',NULL,NULL,NULL),(8215,'92140','PROVOCATIVE TESTS GLAUCOMA I&R W/O TONOGRAPHY                                                       \r',NULL,NULL,NULL),(8216,'92225','OPHTHALMOSCPY EXTENDED RETINAL DRAWING I&R 1ST                                                      \r',NULL,NULL,NULL),(8217,'92226','OPHTHALMOSCPY EXTENDED RETINAL DRAWING I&R SBS                                                      \r',NULL,NULL,NULL),(8218,'92227','REMOTE IMG DX RETINL DIS W/ALYS & REPORT UNI/B                                                      \r',NULL,NULL,NULL),(8219,'92228','REMOTE IMAGING MGT RETINAL DISEASE W/I&R UNI/B                                                      \r',NULL,NULL,NULL),(8220,'92230','FLUORESCEIN ANGIOSCOPY INTERPRETATION & REPORT                                                      \r',NULL,NULL,NULL),(8221,'92235','FLUORESCEIN ANGIOSCOPY INTERPRETATION & REPORT                                                      \r',NULL,NULL,NULL),(8222,'92240','INDOCYANINE GREEN ANGIOGRAPHY W/INTERP & REPOR                                                      \r',NULL,NULL,NULL),(8223,'92250','FUNDUS PHOTOGRAPHY W/INTERPRETATION & REPORT                                                        \r',NULL,NULL,NULL),(8224,'92260','OPHTHALMODYNAMOMETRY                                                                                \r',NULL,NULL,NULL),(8225,'92265','NEEDLE OCULOGRAPHY 1/ XOC MUSC 1/BOTH EYE W/I&R                                                     \r',NULL,NULL,NULL),(8226,'92270','ELECTRO-OCULOGRAPY W/INTERPRETATION & REPORT                                                        \r',NULL,NULL,NULL),(8227,'92275','ELECTRORETINOGRAPY W/INTERPRETATION & REPORT                                                        \r',NULL,NULL,NULL),(8228,'92283','COLOR VISION XM EXTENDED ANOMALOSCOPE/EQUIV                                                         \r',NULL,NULL,NULL),(8229,'92284','DARK ADAPTATION XM W/INTERPRETATION & REPORT                                                        \r',NULL,NULL,NULL),(8230,'92285','XTRNL OCULAR PHOTOG W/I&R DOCMT MEDICAL PROGRE                                                      \r',NULL,NULL,NULL),(8231,'92286','ANT SGM IMAGING W/MICROSCOPY ENDOTHELIAL ANALY                                                      \r',NULL,NULL,NULL),(8232,'92287','ANT SGM IMAGING W/FLUOROSCEIN ANGIO & I&R                                                           \r',NULL,NULL,NULL),(8233,'92310','RX&FITG C-LENS SUPVJ CRNL LENS OU XCPT APHK                                                         \r',NULL,NULL,NULL),(8234,'92311','RX&FITG CONTACT CORNEAL LENS APHAKIA 1 EYE                                                          \r',NULL,NULL,NULL),(8235,'92312','RX&FITG CONTACT CORNEAL LENS APHAKIA BOTH EYES                                                      \r',NULL,NULL,NULL),(8236,'92313','RX&FITG CORNEOSCLERAL LENS                                                                          \r',NULL,NULL,NULL),(8237,'92314','RX&FTG CONTACT CORNEAL LENS EYES XCPT APHAKIA                                                       \r',NULL,NULL,NULL),(8238,'92315','RX CONTACT CORNEAL LENS APHAKIA 1 EYE                                                               \r',NULL,NULL,NULL),(8239,'92316','RX CONTACT CORNEAL LENS APHAKIA BOTH EYES                                                           \r',NULL,NULL,NULL),(8240,'92317','RX CONTACT CORNEOSCLERAL LENS                                                                       \r',NULL,NULL,NULL),(8241,'92325','MODIFICAJ CONTACT LENX SPX SUPVJ ADAPTATION                                                         \r',NULL,NULL,NULL),(8242,'92326','REPLACEMENT CONTACT LENS                                                                            \r',NULL,NULL,NULL),(8243,'92340','FITTING SPECTACLES XCPT APHAKIA MONOFOCAL                                                           \r',NULL,NULL,NULL),(8244,'92341','FITTING SPECTACLES XCPT APHAKIA BIFOCAL                                                             \r',NULL,NULL,NULL),(8245,'92342','FITTING SPECTACLES XCPT APHAKIA MULTIFOCAL                                                          \r',NULL,NULL,NULL),(8246,'92352','FITTING SPECTACLE PROSTH APHAKIA MONOFOCAL                                                          \r',NULL,NULL,NULL),(8247,'92353','FITTING SPECTACLE PROSTH APHAKIA MULTIFOCAL                                                         \r',NULL,NULL,NULL),(8248,'92354','FITTING SPECTACLE MOUNTED LW VIS AID 1 ELMNT                                                        \r',NULL,NULL,NULL),(8249,'92355','FITTING SPECTACLE MOUNTED LW VIS AID TLSCP                                                          \r',NULL,NULL,NULL),(8250,'92358','PROSTHESIS SERVICE APHAKIA TEMPORARY                                                                \r',NULL,NULL,NULL),(8251,'92370','RPR&REFITG SPECTACLES EXCEPT APHAKIA                                                                \r',NULL,NULL,NULL),(8252,'92371','RPR&REFITG SPECTACLE PROSTHESIS APHAKIA                                                             \r',NULL,NULL,NULL),(8253,'92499','UNLIVSTED OPHTHALMOLOGICAL SERVICE/PROCEDURE                                                        \r',NULL,NULL,NULL),(8254,'92502','OTOLARYNGOLOGIC EXAM UNDER GENERAL ANESTHESIA                                                       \r',NULL,NULL,NULL),(8255,'92504','BINOCULAR MICROSCOPY SEPARATE DX PROCEDURE                                                          \r',NULL,NULL,NULL),(8256,'92507','TX SPEECH LANG VOICE COMMJ &/AUDITORY PROC IND                                                      \r',NULL,NULL,NULL),(8257,'92508','TX SPEECH LANGUAGE VOICE COMMJ AUDITRY 2/>INDIV                                                     \r',NULL,NULL,NULL),(8258,'92511','NASOPHARYNGOSCOPY W/ENDOSCOPE SPX                                                                   \r',NULL,NULL,NULL),(8259,'92512','NASAL FUNCTION STUDIES                                                                              \r',NULL,NULL,NULL),(8260,'92516','FACIAL NERVE FUNCTION STUDIES                                                                       \r',NULL,NULL,NULL),(8261,'92520','LARYNGEAL FUNCTION STUDIES                                                                          \r',NULL,NULL,NULL),(8262,'92521','EVALUATION OF SPEECH FLUENCY (STUTTER CLUTTER)                                                      \r',NULL,NULL,NULL),(8263,'92522','EVALUATION OF SPEECH SOUND PRODUCTION ARTICULATE                                                    \r',NULL,NULL,NULL),(8264,'92523','EVAL SPEECH SOUND PRODUCT LANGUAGE COMPREHENSION                                                    \r',NULL,NULL,NULL),(8265,'92524','BEHAVIORAL & QUALIT ANALYSIS VOICE AND RESONANCE                                                    \r',NULL,NULL,NULL),(8266,'92526','TX SWALLOWING DYSFUNCTION&/ORAL FUNCJ FEEDING                                                       \r',NULL,NULL,NULL),(8267,'92531','SPONTANEOUS NYSTAGMUS W/GAZE                                                                        \r',NULL,NULL,NULL),(8268,'92532','POSITIONAL NYSTAGMUS TEST                                                                           \r',NULL,NULL,NULL),(8269,'92533','CALORIC VESTIBULAR TEST EACH IRRIGATION                                                             \r',NULL,NULL,NULL),(8270,'92534','OPTOKINETIC NYSTAGMUS TEST                                                                          \r',NULL,NULL,NULL),(8271,'92540','VSTBLR FUNCJ NYSTAG FOVL&PERPH STIMJ OSCIL TRK                                                      \r',NULL,NULL,NULL),(8272,'92541','SPONTANEOUS NYSTAGMUS TEST                                                                          \r',NULL,NULL,NULL),(8273,'92542','POSITIONAL NYSTAGMUS TEST                                                                           \r',NULL,NULL,NULL),(8274,'92543','CALORIC VESTIBULAR TEST EA IRRIGATION W/RECORD                                                      \r',NULL,NULL,NULL),(8275,'92544','OPTKINETIC NYSTAG BIDIR/FOVEAL/PERIPH STIM W/REC                                                    \r',NULL,NULL,NULL),(8276,'92545','OSCILLATING TRACKING TEST W/RECORDING                                                               \r',NULL,NULL,NULL),(8277,'92546','SINUSOIDAL VERTICAL AXIS ROTATIONAL TESTING                                                         \r',NULL,NULL,NULL),(8278,'92547','USE VERTICAL ELECTRODES                                                                             \r',NULL,NULL,NULL),(8279,'92548','COMPUTERIZED DYNAMIC POSTUROGRAPY                                                                   \r',NULL,NULL,NULL),(8280,'92550','TYMPANOMETRY AND REFLEX THRESHOLD MEASUREMENTS                                                      \r',NULL,NULL,NULL),(8281,'92551','SCREENING TEST PURE TONE AIR ONLY                                                                   \r',NULL,NULL,NULL),(8282,'92552','PURE TONE AUDIOMETRY AIR ONLY                                                                       \r',NULL,NULL,NULL),(8283,'92553','PURE TONE AUDIOMETRY AIR & BONE                                                                     \r',NULL,NULL,NULL),(8284,'92555','SPEECH AUDIOMETRY THRESHOLD                                                                         \r',NULL,NULL,NULL),(8285,'92556','SPEECH AUDIOMETRY THRESHOLD SPEECH RECOGNIJ                                                         \r',NULL,NULL,NULL),(8286,'92557','COMPRE AUDIOMETRY THRESHOLD EVAL SP RECOGNIJ                                                        \r',NULL,NULL,NULL),(8287,'92558','EVOKED OTOACOUSTIC EMISSIONS SCREEN AUTO ANALYS                                                     \r',NULL,NULL,NULL),(8288,'92559','AUDIOMETRIC TESTING GROUPS                                                                          \r',NULL,NULL,NULL),(8289,'92560','BEKESY AUDIOMETRY SCREENING                                                                         \r',NULL,NULL,NULL),(8290,'92561','BEKESY AUDIOMETRY DIAGNOSTIC                                                                        \r',NULL,NULL,NULL),(8291,'92562','LOUDNESS BALANCE BINAURAL/MONAURAL                                                                  \r',NULL,NULL,NULL),(8292,'92563','TONE DECAY TEST                                                                                     \r',NULL,NULL,NULL),(8293,'92564','SHORT INCREMENT SENSITIVITY INDEX                                                                   \r',NULL,NULL,NULL),(8294,'92565','STENGER TEST PURE TONE                                                                              \r',NULL,NULL,NULL),(8295,'92567','TYMPANOMETRY                                                                                        \r',NULL,NULL,NULL),(8296,'92568','ACOUSTIC REFLEX THRESHOLD                                                                           \r',NULL,NULL,NULL),(8297,'92570','ACOUSTIC IMMIT TEST TYMPANOM/ACOUST REFLX/DECAY                                                     \r',NULL,NULL,NULL),(8298,'92571','FILTERED SPEECH TEST                                                                                \r',NULL,NULL,NULL),(8299,'92572','STAGGERED SPONDAIC WORD                                                                             \r',NULL,NULL,NULL),(8300,'92575','SENSORINEURAL ACUITY LEVEL                                                                          \r',NULL,NULL,NULL),(8301,'92576','SYNTHETIC SENTENCE IDENTIFICATION TEST                                                              \r',NULL,NULL,NULL),(8302,'92577','STENGER TEST SPEECH                                                                                 \r',NULL,NULL,NULL),(8303,'92579','VISUAL REINFORCEMENT AUDIOMETRY                                                                     \r',NULL,NULL,NULL),(8304,'92582','CONDITIONING PLAY AUDIOMETRY                                                                        \r',NULL,NULL,NULL),(8305,'92583','SELECT PICTURE AUDIOMETRY                                                                           \r',NULL,NULL,NULL),(8306,'92584','ELECTROCOCHLEOGRAPHY                                                                                \r',NULL,NULL,NULL),(8307,'92585','AUDITORY EVOKED POTENTIALS COMPREHENSIVE                                                            \r',NULL,NULL,NULL),(8308,'92586','AUDITORY EVOKED POTENTIALS LIMITED                                                                  \r',NULL,NULL,NULL),(8309,'92587','DISTORT PRODUCT EVOKED OTOACOUSTIC EMISNS LIMITD                                                    \r',NULL,NULL,NULL),(8310,'92588','DISTRT PROD EVOKD OTOACOUSTIC EMSNS COMP/DX EVAL                                                    \r',NULL,NULL,NULL),(8311,'92590','HEARING AID EXAMINATION & SELECTION MONAURAL                                                        \r',NULL,NULL,NULL),(8312,'92591','HEARING AID EXAMINATION & SELECTION BINAURAL                                                        \r',NULL,NULL,NULL),(8313,'92592','HEARING AID CHECK MONAURAL                                                                          \r',NULL,NULL,NULL),(8314,'92593','HEARING AID CHECK BINAURAL                                                                          \r',NULL,NULL,NULL),(8315,'92594','ELECTROACOUS EVAL HEARING AID MONAURAL                                                              \r',NULL,NULL,NULL),(8316,'92595','ELECTROACOUS EVAL HEARING AID BINAURAL                                                              \r',NULL,NULL,NULL),(8317,'92596','EAR PROTECTOR ATTENUATION MEASUREMENTS                                                              \r',NULL,NULL,NULL),(8318,'92597','EVAL&/FITG VOICE PROSTC DEV SUPLMNT ORAL SPEEC                                                      \r',NULL,NULL,NULL),(8319,'92601','ANALYSIS COCHLEAR IMPLT PT <7 YR PRGRMG                                                             \r',NULL,NULL,NULL),(8320,'92602','ANALYSIS COCHLEAR IMPLT PT <7 YR SBSQ REPRGRMG                                                      \r',NULL,NULL,NULL),(8321,'92603','ANALYSIS COCHLEAR IMPLT 7 YR/> PRGRMG                                                               \r',NULL,NULL,NULL),(8322,'92604','ANALYSIS COCHLEAR IMPLT 7 YR/> SBSQ REPRGRMG                                                        \r',NULL,NULL,NULL),(8323,'92605','EVAL RX N-SP-GEN AUGMT ALT COMMUN DEV F2F 1ST HR                                                    \r',NULL,NULL,NULL),(8324,'92606','THER SVC N-SP-GENRATJ DEV PRGRMG&MODIFICAJ                                                          \r',NULL,NULL,NULL),(8325,'92607','RX SP-GENRATJ AUGMNT&COMUNICAJ DEV 1ST HR                                                           \r',NULL,NULL,NULL),(8326,'92608','RX SP-GENRATJ AUGMNT&COMUNICAJ DEV EA 30 MIN                                                        \r',NULL,NULL,NULL),(8327,'92609','THER SP-GENRATJ DEV PRGRMG&MODIFICAJ                                                                \r',NULL,NULL,NULL),(8328,'92610','EVAL ORAL&PHARYNGEAL SWLNG FUNCJ                                                                    \r',NULL,NULL,NULL),(8329,'92611','MOTION FLUOR EVAL SWLNG FUNCJ C/V REC                                                               \r',NULL,NULL,NULL),(8330,'92612','FLX FIBOPT NDSC EVAL SWLNG C/V REC                                                                  \r',NULL,NULL,NULL),(8331,'92613','FLX FIBOPT NDSC EVAL SWLNG C/V REC PHYS I&R                                                         \r',NULL,NULL,NULL),(8332,'92614','FLX FIBOPT NDSC EVAL LARYN SENS C/V REC                                                             \r',NULL,NULL,NULL),(8333,'92615','FLX FIBOPT NDSC EVAL LARYN SENS PHYS I&R                                                            \r',NULL,NULL,NULL),(8334,'92616','FLX FIBOPT NDSC EVAL SWLNG&LARYN SENS C/V REC                                                       \r',NULL,NULL,NULL),(8335,'92617','FLX FIBOPT NDSC EVAL SWLNG&LARYN SENS PHYS I&R                                                      \r',NULL,NULL,NULL),(8336,'92618','EVAL RX N-SP-GEN AUGMT ALT COMMUN DEV ADD 30 MIN                                                    \r',NULL,NULL,NULL),(8337,'92620','EVAL CENTRAL AUDITORY FUNCJ W/REPRT 1ST 60 MIN                                                      \r',NULL,NULL,NULL),(8338,'92621','EVAL CENTRAL AUDITORY FUNCJ W/REPRT EA 15 MIN                                                       \r',NULL,NULL,NULL),(8339,'92625','ASSESSMENT TINNITUS                                                                                 \r',NULL,NULL,NULL),(8340,'92626','EVALUATION AUDITORY REHAB STATUS 1ST HR                                                             \r',NULL,NULL,NULL),(8341,'92627','EVALUATION AUDITORY REHAB STATUS EA 15 MIN                                                          \r',NULL,NULL,NULL),(8342,'92630','AUDITORY REHABILITATION PRELINGUAL HEARING LOSS                                                     \r',NULL,NULL,NULL),(8343,'92633','AUDITORY REHABILITATION POSTLINGUAL HEARING LOSS                                                    \r',NULL,NULL,NULL),(8344,'92640','ANALYSIS W/PRGRMG AUD BRAINSTEM IMPLANT PR HR                                                       \r',NULL,NULL,NULL),(8345,'92700','UNLISTED OTORHINOLARYNGOLOGICAL SERVICE                                                             \r',NULL,NULL,NULL),(8346,'92920','PRQ TRLUML CORONARY ANGIOPLASTY ONE ART/BRANCH                                                      \r',NULL,NULL,NULL),(8347,'92921','PRQ TRLUML CORONARY ANGIOPLASTY ADDL BRANCH                                                         \r',NULL,NULL,NULL),(8348,'92924','PRQ TRLUML CORONARY ANGIO/ATHERECT ONE ART/BRNCH                                                    \r',NULL,NULL,NULL),(8349,'92925','PRQ TRLUML CORONARY ANGIO/ATHEREC ADDL ART/BRNCH                                                    \r',NULL,NULL,NULL),(8350,'92928','PRQ TRLUML CORONARY STENT W/ANGIO ONE ART/BRNCH                                                     \r',NULL,NULL,NULL),(8351,'92929','PRQ TRLUML CORONARY STENT W/ANGIO ADDL ART/BRNCH                                                    \r',NULL,NULL,NULL),(8352,'92933','PRQ TRLUML CORONRY STENT/ATH/ANGIO ONE ART/BRNCH                                                    \r',NULL,NULL,NULL),(8353,'92934','PRQ TRLUML CORONARY STENT/ATH/ANGIO ADDL BRANCH                                                     \r',NULL,NULL,NULL),(8354,'92937','PRQ TRLUML CORONARY BYP GRFT REVASC ONE VESSEL                                                      \r',NULL,NULL,NULL),(8355,'92938','PRQ TRLUML CORONARY BYP GRFT REVASC ADDL VESSEL                                                     \r',NULL,NULL,NULL),(8356,'92941','PRQ TRLUML CORONRY TOT OCCLUS REVASC MI ONE VSL                                                     \r',NULL,NULL,NULL),(8357,'92943','PRQ TRLUML CORONRY CHRONIC OCCLUS REVASC ONE VSL                                                    \r',NULL,NULL,NULL),(8358,'92944','PRQ TRLUML CORONRY CHRNIC OCCLUS REVASC ADDL VSL                                                    \r',NULL,NULL,NULL),(8359,'92950','CARDIOPULMONARY RESUSCITATION                                                                       \r',NULL,NULL,NULL),(8360,'92953','TEMPORARY TRANSCUTANEOUS PACING                                                                     \r',NULL,NULL,NULL),(8361,'92960','CARDIOVERSION ELECTIVE ARRHYTHMIA EXTERNAL                                                          \r',NULL,NULL,NULL),(8362,'92961','CARDIOVERSION ELECTIVE ARRHYTHMIA INTERNAL SPX                                                      \r',NULL,NULL,NULL),(8363,'92970','CARDIOASSIST-METH CIRCULATORY ASSIST INTERNAL                                                       \r',NULL,NULL,NULL),(8364,'92971','CARDIOASSIST-METH CIRCULATORY ASSIST EXTERNAL                                                       \r',NULL,NULL,NULL),(8365,'92973','PRQ TRANSLUMINAL CORONARY MECHANICL THROMBECTOMY                                                    \r',NULL,NULL,NULL),(8366,'92974','TCAT PLMT RADJ DLVR DEV SBSQ C IV BRACHYTX                                                          \r',NULL,NULL,NULL),(8367,'92975','THROMBOLYSIS INTRACORONARY NFS SLCTV ANGRPH                                                         \r',NULL,NULL,NULL),(8368,'92977','THROMBOLYSIS CORONARY INTRAVENOUS INFUSION                                                          \r',NULL,NULL,NULL),(8369,'92978','INTRAVASC US CORONARY INTERP&RPT INITIAL VESSE                                                      \r',NULL,NULL,NULL),(8370,'92979','INTRAVASC US CORONARY INTERP&RPT ADDL VESSEL                                                        \r',NULL,NULL,NULL),(8371,'92986','PRQ BALLOON VALVULOPLASTY AORTIC VALVE                                                              \r',NULL,NULL,NULL),(8372,'92987','PRQ BALLOON VALVULOPLASTY MITRAL VALVE                                                              \r',NULL,NULL,NULL),(8373,'92990','PRQ BALLOON VALVULOPLASTY PULMONARY VALVE                                                           \r',NULL,NULL,NULL),(8374,'92992','ATRIAL SEPTECT/SEPTOST TRANSVENOUS BALLOON                                                          \r',NULL,NULL,NULL),(8375,'92993','ATRIAL SEPTECT/SEPTOSTOMY BLADE METHOD                                                              \r',NULL,NULL,NULL),(8376,'92997','PRQ TRLUML PULMONARY ART BALLOON ANGIOP 1 VSL                                                       \r',NULL,NULL,NULL),(8377,'92998','PRQ TRLUML PULMONARY ART BALLOON ANGIOP EA VSL                                                      \r',NULL,NULL,NULL),(8378,'93000','ECG ROUTINE ECG W/LEAST 12 LDS W/I&R                                                                \r',NULL,NULL,NULL),(8379,'93005','ECG ROUTINE ECG W/LEAST 12 LDS TRCG ONLY W/O I&R                                                    \r',NULL,NULL,NULL),(8380,'93010','ECG ROUTINE ECG W/LEAST 12 LDS I&R ONLY                                                             \r',NULL,NULL,NULL),(8381,'93015','CV STRS TST XERS&/OR RX CONT ECG W/SI&R                                                             \r',NULL,NULL,NULL),(8382,'93016','CV STRS TST XERS&/OR RX CONT ECG W/O I&R                                                            \r',NULL,NULL,NULL),(8383,'93017','CV STRS TST XERS&/OR RX CONT ECG TRCG ONLY                                                          \r',NULL,NULL,NULL),(8384,'93018','CV STRS TST XERS&/OR RX CONT ECG I&R ONLY                                                           \r',NULL,NULL,NULL),(8385,'93024','ERGONOVINE PROVOCATION TST                                                                          \r',NULL,NULL,NULL),(8386,'93025','MICROVOLT T-WAVE ASSESS VENTRICULAR ARRHYTHMIAS                                                     \r',NULL,NULL,NULL),(8387,'93040','RHYTHM ECG 1-3 LEADS W/INTERPRETATION & REPORT                                                      \r',NULL,NULL,NULL),(8388,'93041','RHYTHM ECG 1-3 LEADS TRACING ONLY W/O I&R                                                           \r',NULL,NULL,NULL),(8389,'93042','RHYTHM ECG 1-3 LEADS INTERPRETATION & REPRT ON                                                      \r',NULL,NULL,NULL),(8390,'93224','XTRNL ECG & 48 HR RECORD SCAN STOR W/R&I                                                            \r',NULL,NULL,NULL),(8391,'93225','XTRNL ECG & 48 HR RECORDING                                                                         \r',NULL,NULL,NULL),(8392,'93226','EXTERNAL ECG SCANNING ANALYSIS REPORT                                                               \r',NULL,NULL,NULL),(8393,'93227','XTRNL ECG CONTINUOUS RHYTHM W/I&R UP TO 48 HRS                                                      \r',NULL,NULL,NULL),(8394,'93228','XTRNL MOBILE CV TELEMETRY W/I&REPORT 30 DAYS                                                        \r',NULL,NULL,NULL),(8395,'93229','XTRNL MOBILE CV TELEMETRY W/TECHNICAL SUPPORT                                                       \r',NULL,NULL,NULL),(8396,'93268','XTRNL PT ACTIV ECG TRANSMIS W/R&I </30 DAYS                                                         \r',NULL,NULL,NULL),(8397,'93270','XTRNL PT ACTIVATED ECG RECORD MONITOR 30 DAYS                                                       \r',NULL,NULL,NULL),(8398,'93271','XTRNL PT ACTIVATED ECG REC DWNLD 30 DAYS                                                            \r',NULL,NULL,NULL),(8399,'93272','XTRNL PT ACTIVTD ECG DWNLD W/R&I </30 DAYS                                                          \r',NULL,NULL,NULL),(8400,'93278','SIGNAL AVERAGED ELECTROCARDIOGRAPHY W/WO ECG                                                        \r',NULL,NULL,NULL),(8401,'93279','PROGRAM EVAL IMPLANTABLE IN PRSN 1 LD PACEMAKER                                                     \r',NULL,NULL,NULL),(8402,'93280','PROGRAM EVAL IMPLANTABLE IN PERSN DUAL LD PACER                                                     \r',NULL,NULL,NULL),(8403,'93281','PROGRAM EVAL IMPLANTABLE IN PRSN MULTI LD PACER                                                     \r',NULL,NULL,NULL),(8404,'93282','PROGRAM EVAL IMPLANTABLE IN PERSN 1 LD CARD/DFB                                                     \r',NULL,NULL,NULL),(8405,'93283','PROGRM EVAL IMPLANTABLE IN PRSN DUAL L CARD/DFB                                                     \r',NULL,NULL,NULL),(8406,'93284','PROGRM EVAL IMPLANTABLE IN PRSN MLT LD CARD/DFB                                                     \r',NULL,NULL,NULL),(8407,'93285','PROGRAM EVAL IMPLANTABLE DEV IN PRSN ILR SYSTEM                                                     \r',NULL,NULL,NULL),(8408,'93286','PERI-PX EVAL&PROGRAM IN PRSN PACEMAKER SYSTEM                                                       \r',NULL,NULL,NULL),(8409,'93287','PERI-PX EVAL&PROGRAM CARDIOVERTER/DEFIBRILLATOR                                                     \r',NULL,NULL,NULL),(8410,'93288','INTERROGATION EVAL IN PERSON 1/DUAL/MLT LEAD PM                                                     \r',NULL,NULL,NULL),(8411,'93289','INTERROGATION EVAL F2F 1/DUAL/MLT LEADS CVDFB                                                       \r',NULL,NULL,NULL),(8412,'93290','INTERROGATION EVAL F2F IMPLANTABLE CV MNTR SYS                                                      \r',NULL,NULL,NULL),(8413,'93291','INTERROGATION EVALUATION IN PERSON ILR SYSTEM                                                       \r',NULL,NULL,NULL),(8414,'93292','INTERROGATION EVAL IN PERSON WR DEFIBRILLATOR                                                       \r',NULL,NULL,NULL),(8415,'93293','TRANSTELEPHONIC RHYTHM STRIP PACEMAKER EVAL                                                         \r',NULL,NULL,NULL),(8416,'93294','INTERROGATION EVAL REMOTE </90 D 1/2/MLT LEAD PM                                                    \r',NULL,NULL,NULL),(8417,'93295','INTERROGATION EVAL REMOTE </90 D 1/2/MLT LD ICD                                                     \r',NULL,NULL,NULL),(8418,'93296','INTERROGATION REMOTE </90 D TECHNICIAN REVIEW                                                       \r',NULL,NULL,NULL),(8419,'93297','INTERROGATION EVAL REMOTE </30 D CV MNTR SYS                                                        \r',NULL,NULL,NULL),(8420,'93298','INTERROGATION EVALUATION REMOTE </30 D ILR SYS                                                      \r',NULL,NULL,NULL),(8421,'93299','INTERROGATION EVAL REMOTE </30 D TECH REVIEW                                                        \r',NULL,NULL,NULL),(8422,'93303','COMPLETE TTHRC ECHO CONGENITAL CARDIAC ANOMALY                                                      \r',NULL,NULL,NULL),(8423,'93304','F-UP/LIMITED TTHRC ECHO CONGENITAL CAR ANOMALY                                                      \r',NULL,NULL,NULL),(8424,'93306','ECHO TTHRC R-T 2D W/WOM-MODE COMPL SPEC&COLR D                                                      \r',NULL,NULL,NULL),(8425,'93307','ECHO TRANSTHORAC R-T 2D W/WO M-MODE REC COMP                                                        \r',NULL,NULL,NULL),(8426,'93308','ECHO TRANSTHORC R-T 2D W/WO M-MODE REC F-UP/LMTD                                                    \r',NULL,NULL,NULL),(8427,'93312','ECHO TRANSESOPHAG R-T 2D W/PRB IMG ACQUISJ I&R                                                      \r',NULL,NULL,NULL),(8428,'93313','ECHO R-T 2D W/PROBE PLACEMENT ONLY                                                                  \r',NULL,NULL,NULL),(8429,'93314','ECHO TRANSESOPHAG R-T 2D IMG ACQUISJ I&R ONLY                                                       \r',NULL,NULL,NULL),(8430,'93315','ECHO TRANSESOPHAG CONGEN PROBE PLCMT IMGNG I&R                                                      \r',NULL,NULL,NULL),(8431,'93316','ECHO TRANSESOPHAG CONGEN PROBE PLCMT ONLY                                                           \r',NULL,NULL,NULL),(8432,'93317','ECHO TRANSESOPHAG IMAGE ACQUISJ INTERP&REPORT                                                       \r',NULL,NULL,NULL),(8433,'93318','ECHO TRANSESOPHAG MONTR CARDIAC PUMP FUNCTJ                                                         \r',NULL,NULL,NULL),(8434,'93320','DOPPLER ECHOCARD PULSE WAVE W/SPECTRAL DISPLAY                                                      \r',NULL,NULL,NULL),(8435,'93321','DOP ECHOCARD PULSE WAVE W/SPECTRAL F-UP/LMTD STD                                                    \r',NULL,NULL,NULL),(8436,'93325','DOP ECHOCARD COLOR FLOW VELOCITY MAPPING                                                            \r',NULL,NULL,NULL),(8437,'93350','ECHO TTHRC R-T 2D W/WO M-MODE COMPLETE REST&ST                                                      \r',NULL,NULL,NULL),(8438,'93351','ECHO TTHRC R-T 2D W/WO M-MODE REST&STRS CONT ECG                                                    \r',NULL,NULL,NULL),(8439,'93352','USE OF ECHO CONTRAST AGENT DURING STRESS ECHO                                                       \r',NULL,NULL,NULL),(8440,'93451','RIGHT HEART CATH O2 SATURATION & CARDIAC OUTPU                                                      \r',NULL,NULL,NULL),(8441,'93452','L HRT CATH W/NJX L VENTRICULOGRAPHY IMG S&I                                                         \r',NULL,NULL,NULL),(8442,'93453','R & L HRT CATH W/NJX L VENTRICULOG IMG S&I                                                          \r',NULL,NULL,NULL),(8443,'93454','CATH PLMT & NJX CORONARY ART ANGIO IMG S&I                                                          \r',NULL,NULL,NULL),(8444,'93455','CATH PLMT & NJX CORONARY ART/GRFT ANGIO IMG S&I                                                     \r',NULL,NULL,NULL),(8445,'93456','CATH PLMT R HRT & ARTS W/NJX & ANGIO IMG S&I                                                        \r',NULL,NULL,NULL),(8446,'93457','CATH PLMT R HRT/ARTS/GRFTS W/NJX& ANGIO IMG S&I                                                     \r',NULL,NULL,NULL),(8447,'93458','CATH PLMT L HRT & ARTS W/NJX & ANGIO IMG S&I                                                        \r',NULL,NULL,NULL),(8448,'93459','CATH PLMT L HRT/ARTS/GRFTS WNJX & ANGIO IMG S&I                                                     \r',NULL,NULL,NULL),(8449,'93460','R & L HRT CATH WINJX HRT ART& L VENTR IMG                                                           \r',NULL,NULL,NULL),(8450,'93461','R& L HRT CATH W/INJEC HRT ART/GRFT& L VENT I                                                        \r',NULL,NULL,NULL),(8451,'93462','LEFT HEART CATH BY TRANSEPTAL PUNCTURE                                                              \r',NULL,NULL,NULL),(8452,'93463','MEDICATION ADMIN & HEMODYNAMIC MEASURMENT                                                           \r',NULL,NULL,NULL),(8453,'93464','PHYSIOLOGIC EXERCISE STUDY & HEMODYNAMIC MEASU                                                      \r',NULL,NULL,NULL),(8454,'93503','INSERTION FLOW DIRECTED CATHETER FOR MONITORING                                                     \r',NULL,NULL,NULL),(8455,'93505','ENDOMYOCARDIAL BIOPSY                                                                               \r',NULL,NULL,NULL),(8456,'93530','R HRT CATHETERIZATION CONGENITAL CARDIAC ANOMALY                                                    \r',NULL,NULL,NULL),(8457,'93531','CMBN R HRT & RETROGRADE L HRT CATHJ CGEN ANOMA                                                      \r',NULL,NULL,NULL),(8458,'93532','CMBN R HRT T-SEPTAL L HRT CATHJ NTC SEPTUM CGEN                                                     \r',NULL,NULL,NULL),(8459,'93533','CMBN R HRT T-SEPTAL L HRT CATHJ SEPTAL OPNG CGEN                                                    \r',NULL,NULL,NULL),(8460,'93561','INDIC DIL STD ARTL&/OR VEN CATHJ W/OUTP MEAS                                                        \r',NULL,NULL,NULL),(8461,'93562','INDIC DIL STD ARTL&/OR VEN CATHJ SBSQ OUTP MEA                                                      \r',NULL,NULL,NULL),(8462,'93563','NJX SEL HRT ART CONGENITAL HRT CATH W/S&I                                                           \r',NULL,NULL,NULL),(8463,'93564','NJX SEL HRT ART/GRFT CONGENITAL HRT CATH W/S&I                                                      \r',NULL,NULL,NULL),(8464,'93565','NJX SEL L VENT/ATRIAL ANGIO HRT CATH W/S&I                                                          \r',NULL,NULL,NULL),(8465,'93566','NJX SEL R VENT/ATRIAL ANGIO HRT CATH W/S&I                                                          \r',NULL,NULL,NULL),(8466,'93567','NJX SUPRAVALV AORTOG HRT CATH W/S&I                                                                 \r',NULL,NULL,NULL),(8467,'93568','NJX PULMONARY ANGIO HRT CATH W/S&I                                                                  \r',NULL,NULL,NULL),(8468,'93571','IV DOP VEL&/OR PRESS C/FLO RSRV MEAS 1ST VSL                                                        \r',NULL,NULL,NULL),(8469,'93572','IV DOP VEL&/OR PRESS C/FLO RSRV MEAS ADDL VSL                                                       \r',NULL,NULL,NULL),(8470,'93580','PRQ TCAT CLSR CGEN INTRATRL COMUNICAJ W/IMPLT                                                       \r',NULL,NULL,NULL),(8471,'93581','PRQ TCAT CLSR CGEN VENTR SEPTAL DFCT W/IMPLT                                                        \r',NULL,NULL,NULL),(8472,'93582','PERCUTAN TRANSCATH CLOSURE PAT DUCT ARTERIOSUS                                                      \r',NULL,NULL,NULL),(8473,'93583','PERCUTANEOUS TRANSCATHETER SEPTAL REDUCTION THER                                                    \r',NULL,NULL,NULL),(8474,'93600','BUNDLE OF HIS RECORDING                                                                             \r',NULL,NULL,NULL),(8475,'93602','INTRA-ATRIAL RECORDING                                                                              \r',NULL,NULL,NULL),(8476,'93603','RIGHT VENTRICULAR RECORDING                                                                         \r',NULL,NULL,NULL),(8477,'93609','INTRA-VENTRIC&/ATRIAL MAPG TACHYCARD W/CATH MA                                                      \r',NULL,NULL,NULL),(8478,'93610','INTRA-ATRIAL PACING                                                                                 \r',NULL,NULL,NULL),(8479,'93612','INTRAVENTRICULAR PACING                                                                             \r',NULL,NULL,NULL),(8480,'93613','INTRACARDIAC ELECTROPHYSIOLOGIC 3D MAPPING                                                          \r',NULL,NULL,NULL),(8481,'93615','ESOPHGL REC ATRIAL W/WO VENTRICULAR ELECTROGRAMS                                                    \r',NULL,NULL,NULL),(8482,'93616','ESOPHGL REC ATRIAL W/WO VENTR ELECTRGRAMS W/PACG                                                    \r',NULL,NULL,NULL),(8483,'93618','INDUCTION ARRHYTHMIA ELECTRICAL PACING                                                              \r',NULL,NULL,NULL),(8484,'93619','COMPRE ELECTROPHYSIOLOGIC W/O ARRHYT INDUCTION                                                      \r',NULL,NULL,NULL),(8485,'93620','COMPRE ELECTROPHYSIOLOGIC ARRHYTHMIA INDUCTION                                                      \r',NULL,NULL,NULL),(8486,'93621','COMPRE ELECTROPHYSIOL XM W/LEFT ATRIAL PACNG/REC                                                    \r',NULL,NULL,NULL),(8487,'93622','COMPRE ELECTROPHYSIOL XM W/LEFT VENTR PACNG/REC                                                     \r',NULL,NULL,NULL),(8488,'93623','PROGRAMMED STIMJ & PACG AFTER IV DRUG NFS                                                           \r',NULL,NULL,NULL),(8489,'93624','ELECTROPHYSIOLOGIC FOLLOW-UP W/PAC/REC W/ARRHYT                                                     \r',NULL,NULL,NULL),(8490,'93631','INTRAOP EPICAR& ENDOCAR PACG& MAPG                                                                  \r',NULL,NULL,NULL),(8491,'93640','EPHYS EVAL PACG CVDFB LDS INITIAL IMPLAN/REPLACE                                                    \r',NULL,NULL,NULL),(8492,'93641','EPHYS EVAL PACG CVDFB LDS W/TSTG OF PULSE GEN                                                       \r',NULL,NULL,NULL),(8493,'93642','EPHYS EVAL PACG CVDFB PRGRMG/REPRGRMG PARAMETERS                                                    \r',NULL,NULL,NULL),(8494,'93650','ICAR CATHETER ABLATION ATRIOVENTR NODE FUNCTION                                                     \r',NULL,NULL,NULL),(8495,'93653','EPHYS EVAL W/ABLATION SUPRAVENT ARRHYTHMIA                                                          \r',NULL,NULL,NULL),(8496,'93654','EPHYS EVAL W/ABLATION VENTRICULAR TACHYCARDIA                                                       \r',NULL,NULL,NULL),(8497,'93655','ICAR CATHETER ABLATION ARRHYTHMIA ADD ON                                                            \r',NULL,NULL,NULL),(8498,'93656','EPHYS EVL TRNSPTL TX ATRIAL FIB ISOLAT PULM VEIN                                                    \r',NULL,NULL,NULL),(8499,'93657','ABLATE L/R ATRIAL FIBRIL W/ISOLATED PULM VEIN                                                       \r',NULL,NULL,NULL),(8500,'93660','CARDIOVASCULAR FUNCTION EVAL W/TILT TABLE W/MNTR                                                    \r',NULL,NULL,NULL),(8501,'93662','INTRACARD ECHOCARD W/THER/DX IVNTJ INCL IMG S&I                                                     \r',NULL,NULL,NULL),(8502,'93668','PERIPHERAL ARTERIAL DISEASE REHAB PER SESSION                                                       \r',NULL,NULL,NULL),(8503,'93701','BIOMPEDANCE-DERIVED PHYSIOLOGIC CV ANALYSIS                                                         \r',NULL,NULL,NULL),(8504,'93724','ELECTRONIC ANALYSIS ANTITACHY PACEMAKER SYSTEM                                                      \r',NULL,NULL,NULL),(8505,'93740','TEMPRATURE GRADIENT STUDY                                                                           \r',NULL,NULL,NULL),(8506,'93745','1ST SET-UP & PRGRMG PHYS/QHP OF WEARABLE CVDFB                                                      \r',NULL,NULL,NULL),(8507,'93750','INTERROGATION VAD IN PRSON W/PHYS/QHP ANALYSIS                                                      \r',NULL,NULL,NULL),(8508,'93770','DERMINATION OF VENOUS PRESSUE                                                                       \r',NULL,NULL,NULL),(8509,'93784','AMBL BLD PRESS W/TAPE&/DISK 24/> HR ALYS I&R                                                        \r',NULL,NULL,NULL),(8510,'93786','BL BLD PRESS W/TAPE&/DISK 24/> HR REC ONL                                                           \r',NULL,NULL,NULL),(8511,'93788','AMBL BLD PRESS W/TAPE/DISK 24/>HR ALYS W/REPRT                                                      \r',NULL,NULL,NULL),(8512,'93790','AMBL BLD PRESS TAPE&/DISK 24/> HR REVIEW                                                            \r',NULL,NULL,NULL),(8513,'93797','OUTPATIENT CARDIAC REHAB W/O CONT ECG MONITOR                                                       \r',NULL,NULL,NULL),(8514,'93798','OUTPATIENT CARDIAC REHAB W/CONT ECG MONITORING                                                      \r',NULL,NULL,NULL),(8515,'93799','UNLISTED CARDIOVASCULAR SERVICE/PROCEDURE                                                           \r',NULL,NULL,NULL),(8516,'93880','DUPLEX SCAN EXTRACRANIAL ART COMPL BI STUDY                                                         \r',NULL,NULL,NULL),(8517,'93882','DUPLEX SCAN EXTRACRANIAL ART UNI/LMTD STUDY                                                         \r',NULL,NULL,NULL),(8518,'93886','TRANSCRANIAL DOPPLER STDY INTRACRANIAL ART COMPL                                                    \r',NULL,NULL,NULL),(8519,'93888','TRANSCRANIAL DOPPLER STDY INTRACRANIAL ART LMTD                                                     \r',NULL,NULL,NULL),(8520,'93890','TRANSCRANIAL DOPPLER INTRACRAN ART VASOREAC STDY                                                    \r',NULL,NULL,NULL),(8521,'93892','TRANSCRANIAL DOPPLER INTRACRAN ART EMBOLI DETECT                                                    \r',NULL,NULL,NULL),(8522,'93893','TRANSCRAN DOPPLER INTRACRAN ART MICROBUBBLE INJ                                                     \r',NULL,NULL,NULL),(8523,'93922','NON-INVAS PHYSIOLOGIC STD EXTREMITY ART 2 LEVEL                                                     \r',NULL,NULL,NULL),(8524,'93923','NON-INVASIVE PHYSIOLOGIC STUDY EXTREMITY 3 LEVLS                                                    \r',NULL,NULL,NULL),(8525,'93924','N-INVAS PHYSIOLOGIC STD LXTR ART COMPL BI                                                           \r',NULL,NULL,NULL),(8526,'93925','DUP-SCAN LXTR ART/ARTL BPGS COMPL BI STUDY                                                          \r',NULL,NULL,NULL),(8527,'93926','DUP-SCAN LXTR ART/ARTL BPGS UNI/LMTD STUDY                                                          \r',NULL,NULL,NULL),(8528,'93930','DUP-SCAN UXTR ART/ARTL BPGS COMPL BI STUDY                                                          \r',NULL,NULL,NULL),(8529,'93931','DUP-SCAN UXTR ART/ARTL BPGS UNI/LMTD STUDY                                                          \r',NULL,NULL,NULL),(8530,'93965','N-INVAS PHYSIOLOGIC STD XTR VEINS COMPL BI STD                                                      \r',NULL,NULL,NULL),(8531,'93970','DUP-SCAN XTR VEINS COMPLETE BILATERAL STUDY                                                         \r',NULL,NULL,NULL),(8532,'93971','DUP-SCAN XTR VEINS UNILATERAL/LIMITED STUDY                                                         \r',NULL,NULL,NULL),(8533,'93975','DUP-SCAN ARTL FLO ABDL/PEL/SCROT&/RPR ORGN COM                                                      \r',NULL,NULL,NULL),(8534,'93976','DUP-SCAN ARTL FLO ABDL/PEL/SCROT&/RPR ORGN LMT                                                      \r',NULL,NULL,NULL),(8535,'93978','DUP-SCAN AORTA IVC ILIAC VASCL/BPGS COMPLETE                                                        \r',NULL,NULL,NULL),(8536,'93979','DUP-SCAN AORTA IVC ILIAC VASCL/BPGS UNI/LMTD                                                        \r',NULL,NULL,NULL),(8537,'93980','DUP-SCAN ARTL INFL&VEN O/F PEN VSL COMPL                                                            \r',NULL,NULL,NULL),(8538,'93981','DUP-SCAN ARTL INFL&VEN O/F PEN VSL F-UP/LMTD S                                                      \r',NULL,NULL,NULL),(8539,'93982','IMPLANT WIRELESS PRESS SENSOR STUDY ANEURYSM SAC                                                    \r',NULL,NULL,NULL),(8540,'93990','DUPLEX SCAN HEMODIALYSIS ACCESS                                                                     \r',NULL,NULL,NULL),(8541,'93998','UNLISTED NONINVASIVE VASCULAR DIAGNOSTIC STUDY                                                      \r',NULL,NULL,NULL),(8542,'94002','VENTILATION ASSIST & MGMT INPATIENT 1ST DAY                                                         \r',NULL,NULL,NULL),(8543,'94003','VENTILATION ASSIST & MGMT INPATIENT EA SBSQ DA                                                      \r',NULL,NULL,NULL),(8544,'94004','VENTILATION ASSIST & MGMT NURSING FAC PR DAY                                                        \r',NULL,NULL,NULL),(8545,'94005','HOME VENTILATOR MGMT CARE OVERSIGHT 30 MIN/>                                                        \r',NULL,NULL,NULL),(8546,'94010','SPMTRY W/VC EXPIRATORY FLO W/WO MXML VOL VNTJ                                                       \r',NULL,NULL,NULL),(8547,'94011','MEAS SPIROMTRC FORCD EXPIRATORY FLO INFANT&/2 Y                                                     \r',NULL,NULL,NULL),(8548,'94012','MEAS SPIRO FRCD EXP FLO PRE&POST BRONCH INF/2YRS                                                    \r',NULL,NULL,NULL),(8549,'94013','MEASUREMENT LUNG VOLUMES INFANT/CHILD/2 YRS                                                         \r',NULL,NULL,NULL),(8550,'94014','PT-INITIATE SPIROMETRIC RECORDING PHYS/QHP R&I                                                      \r',NULL,NULL,NULL),(8551,'94015','PATIENT-INITIATED SPIROMETRIC RECORDING                                                             \r',NULL,NULL,NULL),(8552,'94016','PATIENT-INITIATED SPIROMETRIC PHYS/QHP R&I ONLY                                                     \r',NULL,NULL,NULL),(8553,'94060','BRNCDILAT RSPSE SPMTRY PRE&POST-BRNCDILAT ADMN                                                      \r',NULL,NULL,NULL),(8554,'94070','BRNCSPSM PROVOCATION EVAL MLT SPMTRY W/ADMN AGT                                                     \r',NULL,NULL,NULL),(8555,'94150','VITAL CAPACITY TOTAL SEPARATE PROCEDURE                                                             \r',NULL,NULL,NULL),(8556,'94200','MAX BREATHING CAPACITY MAXIMAL VOLUNTARY VENTJ                                                      \r',NULL,NULL,NULL),(8557,'94250','EXPIRED GAS COLLECTION QUANT 1 PROCEDURE SPX                                                        \r',NULL,NULL,NULL),(8558,'94375','RESPIRATORY FLOW VOLUME LOOP                                                                        \r',NULL,NULL,NULL),(8559,'94400','BREATHING RESPONSE TO CO2                                                                           \r',NULL,NULL,NULL),(8560,'94450','BREATHING RESPONSE TO HYPOXIA                                                                       \r',NULL,NULL,NULL),(8561,'94452','HIGH ALTITUDE SIMULATJ TEST W/PHYS INTERP&REPORT                                                    \r',NULL,NULL,NULL),(8562,'94453','HIGH ALTITUDE SIMULATJ W/PHYS I&R W/O2 TITRATION                                                    \r',NULL,NULL,NULL),(8563,'94610','INTRAPULMONARY SURFACTANT ADMINISTJ PHYS/QHP                                                        \r',NULL,NULL,NULL),(8564,'94620','PULMONARY STRESS TESTING SIMPLE                                                                     \r',NULL,NULL,NULL),(8565,'94621','PULMONARY STRESS TESTING COMPLEX                                                                    \r',NULL,NULL,NULL),(8566,'94640','PRESSURIZED/NONPRESSURIZED INHALATION TREATMENT                                                     \r',NULL,NULL,NULL),(8567,'94642','PENTAMIDINE AERSL INHALATION PNEUMOCYSTIS/PROPH                                                     \r',NULL,NULL,NULL),(8568,'94644','CONTINUOUS INHALATION TREATMENT 1ST HR                                                              \r',NULL,NULL,NULL),(8569,'94645','CONTINUOUS INHALATION TREATMENT EA ADDL HR                                                          \r',NULL,NULL,NULL),(8570,'94660','CPAP VENTILATION CPAP INITIATION&MGMT                                                               \r',NULL,NULL,NULL),(8571,'94662','CONTINUOUS NEGATIVE PRESSURE VENTJ INITIAT&MGM                                                      \r',NULL,NULL,NULL),(8572,'94664','DEMO&/EVAL OF PT UTILIZ AERSL GEN/NEB/INHLR/IP                                                      \r',NULL,NULL,NULL),(8573,'94667','MANJ CH WALL FACILITATE LNG FUNCJ 1 DEMO&/EVAL                                                      \r',NULL,NULL,NULL),(8574,'94668','MANJ CHEST WALL FACILITATE LUNG FUNCTION SUBSQ                                                      \r',NULL,NULL,NULL),(8575,'94669','MECHANICAL CHEST WALL OSCILLATION LUNG FUNCTION                                                     \r',NULL,NULL,NULL),(8576,'94680','O2 UPTK EXP GAS ANALYSIS REST&XERS DIRECT SIMP                                                      \r',NULL,NULL,NULL),(8577,'94681','O2 UPTK EXP GAS ALYS W/CO2 OUTPUT % O2 XTRC                                                         \r',NULL,NULL,NULL),(8578,'94690','O2 UPTAKE EXP GAS ANALYSIS REST INDIRECT SPX                                                        \r',NULL,NULL,NULL),(8579,'94726','PLETHYSMOGRAPHY LUNG VOLUMES W/WO AIRWAY RESIST                                                     \r',NULL,NULL,NULL),(8580,'94727','GAS DILUT/WASHOUT LUNG VOL W/WO DISTRIB VENT&V                                                      \r',NULL,NULL,NULL),(8581,'94728','AIRWAY RESISTANCE BY IMPULSE OSCILLOMETRY                                                           \r',NULL,NULL,NULL),(8582,'94729','CO DIFFUSING CAPACITY                                                                               \r',NULL,NULL,NULL),(8583,'94750','PULMONARY COMPLIANCE STUDY                                                                          \r',NULL,NULL,NULL),(8584,'94760','NONINVASIVE EAR/PULSE OXIMETRY SINGLE DETER                                                         \r',NULL,NULL,NULL),(8585,'94761','NONINVASIVE EAR/PULSE OXIMETRY MULTIPLE DETER                                                       \r',NULL,NULL,NULL),(8586,'94762','NONINVASIVE EAR/PULSE OXIMETRY OVERNIGHT MONITOR                                                    \r',NULL,NULL,NULL),(8587,'94770','CARBON DIOXIDE EXP GAS DETER INFRARED ANALYZER                                                      \r',NULL,NULL,NULL),(8588,'94772','CIRCADIAN RESPIRATRY PATTERN REC 12-24 HR INFANT                                                    \r',NULL,NULL,NULL),(8589,'94774','PEDIATRIC APNEA MONITOR ATTACHMENT PHYS I&R                                                         \r',NULL,NULL,NULL),(8590,'94775','PEDIATRIC APNEA MONITOR ATTACHMENT                                                                  \r',NULL,NULL,NULL),(8591,'94776','PEDIATRIC APNEA MONITOR ANALYSES COMPUTER                                                           \r',NULL,NULL,NULL),(8592,'94777','PEDIATRIC APNEA MONITOR PHYS/QHP REVIEW                                                             \r',NULL,NULL,NULL),(8593,'94780','CAR SEAT/BED TESTING W/INTERP & REPORT 60 MIN                                                       \r',NULL,NULL,NULL),(8594,'94781','CAR SEAT/BED TESTNG W/INTERP & REPORT ADDL 30MIN                                                    \r',NULL,NULL,NULL),(8595,'94799','UNLISTED PULMONARY SERVICE/PROCEDURE                                                                \r',NULL,NULL,NULL),(8596,'95004','PERCUTANEOUS TESTS W/ALLERGENIC EXTRACTS                                                            \r',NULL,NULL,NULL),(8597,'95012','NITRIC OXIDE EXPIRED GAS DETERMINATION                                                              \r',NULL,NULL,NULL),(8598,'95017','ALLG TSTG PERQ & IC VENOMS IMMED REACT W/I&R                                                        \r',NULL,NULL,NULL),(8599,'95018','ALLG TEST PERQ & IC DRUG/BIOL IMMED REACT W/I&R                                                     \r',NULL,NULL,NULL),(8600,'95024','INTRACUTANEOUS TESTS W/ALLERGENIC EXTRACTS                                                          \r',NULL,NULL,NULL),(8601,'95027','INTRACUTANEOUS TESTS W/ALLERGENIC XTRCS AIRBORNE                                                    \r',NULL,NULL,NULL),(8602,'95028','IC TSTS W/ALLGIC XTRCS DLYD TYP RXN W/READING                                                       \r',NULL,NULL,NULL),(8603,'95044','PATCH/APPLICATION TEST SPECIFY NUMBER TESTS                                                         \r',NULL,NULL,NULL),(8604,'95052','PHOTO PATCH TEST SPECIFY NUMBER TSTS                                                                \r',NULL,NULL,NULL),(8605,'95056','PHOTO TESTS                                                                                         \r',NULL,NULL,NULL),(8606,'95060','OPHTHALMIC MUCOUS MEMBRANE TESTS                                                                    \r',NULL,NULL,NULL),(8607,'95065','DIRECT NASAL MUCOUS MEMBRANE TEST                                                                   \r',NULL,NULL,NULL),(8608,'95070','INHLJ BRNCL CHALLENGE TSTG W/HISTAM/METHACHOL                                                       \r',NULL,NULL,NULL),(8609,'95071','INHLJ BRNCL CHALLENGE TSTG W/AGS/GASES                                                              \r',NULL,NULL,NULL),(8610,'95076','INGESTION CHALLENGE TEST INITIAL 120 MINUTES                                                        \r',NULL,NULL,NULL),(8611,'95079','INGESTION CHALLENGE TEST EACH ADDL 60 MINUTES                                                       \r',NULL,NULL,NULL),(8612,'95115','PROF SVCS ALLG IMMNTX X W/PRV ALLGIC XTRCS 1 NJX                                                    \r',NULL,NULL,NULL),(8613,'95117','PROF SVCS ALLG IMMNTX X W/PRV ALLGIC XTRCS NJXS                                                     \r',NULL,NULL,NULL),(8614,'95120','PROF SVCS ALLG IMMNTX W/PRV ALLGIC XTRC 1 NJX                                                       \r',NULL,NULL,NULL),(8615,'95125','PROF SVCS ALLG IMMNTX W/PRV ALLGIC XTRC 2/> NJX                                                     \r',NULL,NULL,NULL),(8616,'95130','PROF SVCS ALLG IMMNTX W/PRV XTRC 1 STING INSECT                                                     \r',NULL,NULL,NULL),(8617,'95131','PROF SVCS ALLG IMMNTX W/PRV XTRC 2 STING INSECT                                                     \r',NULL,NULL,NULL),(8618,'95132','PROF SVCS ALLG IMMNTX W/PRV XTRC 3 STING INSECT                                                     \r',NULL,NULL,NULL),(8619,'95133','PROF SVCS ALLG IMMNTX W/PRV XTRC 4 STING INSECT                                                     \r',NULL,NULL,NULL),(8620,'95134','PROF SVCS ALLG IMMNTX W/PRV XTRC 5 STING INSECT                                                     \r',NULL,NULL,NULL),(8621,'95144','PREPJ& ANTIGEN PRV ALLERGEN IMMUNOTHERAPY 1 DO                                                      \r',NULL,NULL,NULL),(8622,'95145','PREPJ& ANTIGEN ALLERGEN IMMUNOTHERAPY 1 INSECT                                                      \r',NULL,NULL,NULL),(8623,'95146','PREPJ& ANTIGEN ALLERGEN IMMUNOTHERAPY 2 INSECT                                                      \r',NULL,NULL,NULL),(8624,'95147','PREPJ& ANTIGEN ALLERGEN IMMUNOTHERAPY 3 INSECT                                                      \r',NULL,NULL,NULL),(8625,'95148','PREPJ& ANTIGEN ALLERGEN IMMUNOTHERAPY 4 INSECT                                                      \r',NULL,NULL,NULL),(8626,'95149','PREPJ& ANTIGEN ALLERGEN IMMUNOTHERAPY 5 INSECT                                                      \r',NULL,NULL,NULL),(8627,'95165','PREPJ& ALLERGEN IMMUNOTHERAPY 1/MLT ANTIGEN                                                         \r',NULL,NULL,NULL),(8628,'95170','PREPJ& ANTIGEN ALLERGEN IMMUNOTHERAPY WHL INSE                                                      \r',NULL,NULL,NULL),(8629,'95180','RAPID DESENSITIZATION PROCEDURE EACH HOUR                                                           \r',NULL,NULL,NULL),(8630,'95199','UNLISTED ALLERGY/CLINICAL IMMUNOLOGIC SRVC/PX                                                       \r',NULL,NULL,NULL),(8631,'95250','GLUC MNTR CONT REC FROM INTERSTITIAL TISS FLUID                                                     \r',NULL,NULL,NULL),(8632,'95251','GLUC MNTR CONT REC FROM NTRSTL TISS FLU I&R                                                         \r',NULL,NULL,NULL),(8633,'95782','POLYSOM <6 YRS SLEEP STAGE 4/> ADDL PARAM ATTND                                                     \r',NULL,NULL,NULL),(8634,'95783','POLYSOM <6 YRS SLEEP W/CPAP/BILVL VENT 4/> PARAM                                                    \r',NULL,NULL,NULL),(8635,'95800','SLP STDY UNATND W/HRT RATE/O2 SAT/RESP/SLP TIME                                                     \r',NULL,NULL,NULL),(8636,'95801','SLP STDY UNATND W/MIN HRT RATE/O2 SAT/RESP ANAL                                                     \r',NULL,NULL,NULL),(8637,'95803','ACTIGRAPHY TESTING RECORDING ANALYSIS I&R                                                           \r',NULL,NULL,NULL),(8638,'95805','MLT SLEEP LATENCY/MAINT OF WAKEFULNESS TSTG                                                         \r',NULL,NULL,NULL),(8639,'95806','SLEEP STD AIRFLOW HRT RATE&O2 SAT EFFORT UNATT                                                      \r',NULL,NULL,NULL),(8640,'95807','SLEEP STD REC VNTJ RESPIR ECG/HRT RATE&O2 ATTN                                                      \r',NULL,NULL,NULL),(8641,'95808','POLYSOM ANY AGE SLEEP STAGE 1-3 ADDL PARAM ATTND                                                    \r',NULL,NULL,NULL),(8642,'95810','POLYSOM 6/>YRS SLEEP 4/> ADDL PARAM ATTND                                                           \r',NULL,NULL,NULL),(8643,'95811','POLYSOM 6/>YRS SLEEP W/CPAP 4/> ADDL PARAM ATTND                                                    \r',NULL,NULL,NULL),(8644,'95812','ELECTROENCEPHALOGRAM EXTEND MONITORING 41-60 MIN                                                    \r',NULL,NULL,NULL),(8645,'95813','ELECTROENCEPHALOGRAM EXTND MNTR >1 HR                                                               \r',NULL,NULL,NULL),(8646,'95816','ELECTROENCEPHALOGRAM W/REC AWAKE&DROWSY                                                             \r',NULL,NULL,NULL),(8647,'95819','ELECTROENCEPHALOGRAM W/REC AWAKE&ASLEEP                                                             \r',NULL,NULL,NULL),(8648,'95822','ELECTROENCEPHALOGRAM REC COMA/SLEEP ONLY                                                            \r',NULL,NULL,NULL),(8649,'95824','ELECTROENCEPHALOGRAM CERE DEATH EVAL ONLY                                                           \r',NULL,NULL,NULL),(8650,'95827','ELECTROENCEPHALOGRAM ALL NIGHT RECORDING                                                            \r',NULL,NULL,NULL),(8651,'95829','ELECTROCORTICOGRAM SURGERY SPX                                                                      \r',NULL,NULL,NULL),(8652,'95830','INSERTION SPHENOIDAL ELECTRODES EEG PHYS/QHP                                                        \r',NULL,NULL,NULL),(8653,'95831','MUSC TSTG MNL W/REPRT XTR EX HAND/TRNK                                                              \r',NULL,NULL,NULL),(8654,'95832','MUSC TSTG MNL W/REPRT HAND W/WO CMPRSN NRML SIDE                                                    \r',NULL,NULL,NULL),(8655,'95833','MUSC TSTG MNL W/REPRT TOTAL EVAL BODY EX HANDS                                                      \r',NULL,NULL,NULL),(8656,'95834','MUSC TSTG MNL W/REPRT TOTAL EVAL BODY W/HANDS                                                       \r',NULL,NULL,NULL),(8657,'95851','ROM MEAS&REPRT EA XTR EX HAND/EA TRNK SCTJ SPI                                                      \r',NULL,NULL,NULL),(8658,'95852','ROM MEAS&REPRT HAND W/WO COMPARISON NORMAL SID                                                      \r',NULL,NULL,NULL),(8659,'95857','CHOLINESTERASE INHIBITOR CHALLENGE TEST                                                             \r',NULL,NULL,NULL),(8660,'95860','NDL EMG 1 XTR W/WO RELATED PARASPINAL AREAS                                                         \r',NULL,NULL,NULL),(8661,'95861','NDL EMG 2 XTR W/WO RELATED PARASPINAL AREAS                                                         \r',NULL,NULL,NULL),(8662,'95863','NDL EMG 3 XTR W/WO RELATED PARASPINAL AREAS                                                         \r',NULL,NULL,NULL),(8663,'95864','NDL EMG 4 XTR W/WO RELATED PARASPINAL AREAS                                                         \r',NULL,NULL,NULL),(8664,'95865','NEEDLE ELECTROMYOGRAPHY LARYNX                                                                      \r',NULL,NULL,NULL),(8665,'95866','NEEDLE ELECTROMYOGRAPHY HEMIDIAPHRAGM                                                               \r',NULL,NULL,NULL),(8666,'95867','NEEDLE ELECTROMYOGRAPHY CRANIAL NRV MUSCLE UNI                                                      \r',NULL,NULL,NULL),(8667,'95868','NEEDLE ELECTROMYOGRAPHY CRANIAL NRV MUSCLE BI                                                       \r',NULL,NULL,NULL),(8668,'95869','NEEDLE EMG THRC PARASPI MUSC EXCLUDING T1/T12                                                       \r',NULL,NULL,NULL),(8669,'95870','NEEDLE EMG LMTD STD MUSC 1 XTR/NON-LIMB UNI/BI                                                      \r',NULL,NULL,NULL),(8670,'95872','NEEDLE EMG W/1 FIBER ELECTRODE QUAN MEAS JITTER                                                     \r',NULL,NULL,NULL),(8671,'95873','ELECTRICAL STIMULATION GUID W/CHEMODENERVATION                                                      \r',NULL,NULL,NULL),(8672,'95874','NEEDLE EMG GUID W/CHEMODENERVATION                                                                  \r',NULL,NULL,NULL),(8673,'95875','ISCHEMIC LIMB XERS TST SPEC ACQUISJ METAB                                                           \r',NULL,NULL,NULL),(8674,'95885','NEEDLE EMG EA EXTREMITY W/PARASPINL AREA LIMITED                                                    \r',NULL,NULL,NULL),(8675,'95886','NEEDLE EMG EA EXTREMTY W/PARASPINL AREA COMPLETE                                                    \r',NULL,NULL,NULL),(8676,'95887','NEEDLE EMG NONEXTREMTY MSCLES W/NERVE CONDUCTION                                                    \r',NULL,NULL,NULL),(8677,'95905','MOTOR &/SENS NRV CNDJ PRECONF ELTRD ARRAY LIMB                                                      \r',NULL,NULL,NULL),(8678,'95907','NERVE CONDUCTION STUDIES 1-2 STUDIES                                                                \r',NULL,NULL,NULL),(8679,'95908','NERVE CONDUCTION STUDIES 3-4 STUDIES                                                                \r',NULL,NULL,NULL),(8680,'95909','NERVE CONDUCTION STUDIES 5-6 STUDIES                                                                \r',NULL,NULL,NULL),(8681,'95910','NERVE CONDUCTION STUDIES 7-8 STUDIES                                                                \r',NULL,NULL,NULL),(8682,'95911','NERVE CONDUCTION STUDIES 9-10 STUDIES                                                               \r',NULL,NULL,NULL),(8683,'95912','NERVE CONDUCTION STUDIES 11-12 STUDIES                                                              \r',NULL,NULL,NULL),(8684,'95913','NERVE CONDUCTION STUDIES 13/> STUDIES                                                               \r',NULL,NULL,NULL),(8685,'95921','TSTG ANS FUNCJ CARDIOVAGAL INNERVAJ PARASYMP                                                        \r',NULL,NULL,NULL),(8686,'95922','TSTG ANS FUNCJ VASOMOTOR ADRENERGIC INNERVAJ                                                        \r',NULL,NULL,NULL),(8687,'95923','TESTING AUTONOMIC NERVOUS SYSTEM FUNCTION                                                           \r',NULL,NULL,NULL),(8688,'95924','TSTG ANS FUNCJ PARASYMP&SYMP W/5 MIN PASIVE TILT                                                    \r',NULL,NULL,NULL),(8689,'95925','SHORT-LATENCY SOMATOSENS EP STD UPR LIMBS                                                           \r',NULL,NULL,NULL),(8690,'95926','SHORT-LATENCY SOMATOSENS EP STD LWR LIMBS                                                           \r',NULL,NULL,NULL),(8691,'95927','SHORT-LATENCY SOMATOSENS EP STD TRNK/HEAD                                                           \r',NULL,NULL,NULL),(8692,'95928','CTR MOTOR EP STD TRANSCRNL MOTOR STIMJ UPR LIMBS                                                    \r',NULL,NULL,NULL),(8693,'95929','CTR MOTOR EP STD TRANSCRNL MOTOR STIMJ LWR LIMBS                                                    \r',NULL,NULL,NULL),(8694,'95930','VISUAL EP TSTG CNS CHECKERBOARD/FLASH                                                               \r',NULL,NULL,NULL),(8695,'95933','ORBICULARIS OCULI REFLX ELECTRODIAGNOSTIC TEST                                                      \r',NULL,NULL,NULL),(8696,'95937','NEUROMUSCULAR JUNCT TSTG EA NRV ANY 1 METH                                                          \r',NULL,NULL,NULL),(8697,'95938','SHORT-LATENCY SOMATOSENS EP STD UPR & LOW LIMB                                                      \r',NULL,NULL,NULL),(8698,'95939','CTR MOTR EP STD TRANSCRNL MOTR STIM UPR&LOW LI                                                      \r',NULL,NULL,NULL),(8699,'95940','IONM 1 ON 1 IN OR W/ATTENDANCE EACH 15 MINUTES                                                      \r',NULL,NULL,NULL),(8700,'95941','IONM REMOTE/NEARBY/>1 PATIENT IN OR PER HOUR                                                        \r',NULL,NULL,NULL),(8701,'95943','PARASYMP & SYMP NRV FUNCJ HRT RATE VARIABILITY                                                      \r',NULL,NULL,NULL),(8702,'95950','MONITOR ID& LATERALIZATION SEIZURE FOCUS EEG                                                        \r',NULL,NULL,NULL),(8703,'95951','LOCALIZE CEREBRAL SEIZURE CABLE/RADIO EEG/VIDEO                                                     \r',NULL,NULL,NULL),(8704,'95953','LOCALIZE CEREBRAL SEIZURE CPTR PORTABLE EEG                                                         \r',NULL,NULL,NULL),(8705,'95954','RX/PHYSICAL EEG ACTIVAJ PHYS/QHP ATTENDANCE                                                         \r',NULL,NULL,NULL),(8706,'95955','EEG NONINTRACRANIAL SURGERY                                                                         \r',NULL,NULL,NULL),(8707,'95956','MNTR SEIZURE CMPTR 16CHAN EEG ATND EA 24 HR                                                         \r',NULL,NULL,NULL),(8708,'95957','DIGITAL ANALYSIS ELECTROENCEPHALOGRAM                                                               \r',NULL,NULL,NULL),(8709,'95958','WADA ACTIVATION TEST HEMISPHERIC FUNCTION W/EEG                                                     \r',NULL,NULL,NULL),(8710,'95961','FUNCJAL CORT&SUBCORT MAPG PHYS/QHP ATTND INIT HR                                                    \r',NULL,NULL,NULL),(8711,'95962','FUNCJAL CORT&SUBCORT MAPG PHYS/QHP ATTND ADDL HR                                                    \r',NULL,NULL,NULL),(8712,'95965','MAGNETOENCEPHALOGRAPHY SPON BRAIN ACTIVITY                                                          \r',NULL,NULL,NULL),(8713,'95966','MAGNETOENCEPHALOGRAPY EVOKED FIELDS 1 MODALITY                                                      \r',NULL,NULL,NULL),(8714,'95967','MAGNETOENCEPHALOGRAPY EVOKED FIELDS EACH ADDL                                                       \r',NULL,NULL,NULL),(8715,'95970','ELEC ALYS NSTIM PLS GEN BRN/SC/PERPH W/O REPRGRM                                                    \r',NULL,NULL,NULL),(8716,'95971','ELEC ALYS NSTIM PLS GEN SMPL SC/PERPH W/PRGRMG                                                      \r',NULL,NULL,NULL),(8717,'95972','ELEC ALYS NSTIM PLS GEN CPLX SC/PERPH 1ST HR                                                        \r',NULL,NULL,NULL),(8718,'95973','ELEC ALYS NSTIM PLS GEN CPLX SC/PERPH EA 30 MIN                                                     \r',NULL,NULL,NULL),(8719,'95974','ELEC ALYS NSTIM PLS GEN CPLX CRNL NRV 1ST HR                                                        \r',NULL,NULL,NULL),(8720,'95975','ELEC ALYS NSTIM PLS GEN CPLX CRNL NRV EA 30 MIN                                                     \r',NULL,NULL,NULL),(8721,'95978','ELEC ALYS NSTIM PLS GEN CPLX DP BRN 1ST HR                                                          \r',NULL,NULL,NULL),(8722,'95979','ELEC ALYS NSTIM PLS GEN CPLX DP BRN EA 30 MIN                                                       \r',NULL,NULL,NULL),(8723,'95980','ELEC ALYS NSTIM PLS GEN GASTRIC INTRAOP W/PRGRMG                                                    \r',NULL,NULL,NULL),(8724,'95981','ELEC ALYS NSTIM GEN GASTRIC SBSQ W/O REPRGRMG                                                       \r',NULL,NULL,NULL),(8725,'95982','ELEC ALYS NSTIM PLS GEN GASTRIC SBSQ W/REPRGRMG                                                     \r',NULL,NULL,NULL),(8726,'95990','REFILL&MAINTENANCE PUMP DRUG DLVR SPINAL/BRAIN                                                      \r',NULL,NULL,NULL),(8727,'95991','RFL&MAIN IMPLT PMP/RSVR DLVR SPI/BRN PHY/QHP                                                        \r',NULL,NULL,NULL),(8728,'95992','CANALITH REPOSITIONING PROCEDURE                                                                    \r',NULL,NULL,NULL),(8729,'95999','UNLIS NEUROLOGICAL/NEUROMUSCULAR DX PX                                                              \r',NULL,NULL,NULL),(8730,'96000','COMPRE CPTR MTN ALYS VIDEO TAPING 3D KINEMATICS                                                     \r',NULL,NULL,NULL),(8731,'96001','COMPRE CPTR MTN ALYS W/DYN PLNTR PRES MEAS WALKG                                                    \r',NULL,NULL,NULL),(8732,'96002','DYN SURF EMG WALKG/FUNCJAL ACTV 1-12 MUSC                                                           \r',NULL,NULL,NULL),(8733,'96003','DYN FINE WIRE EMG WALKG/FUNCJAL ACTV 1 MUSC                                                         \r',NULL,NULL,NULL),(8734,'96004','PHYS/QHP R&I CPTR MTN ALYS WALK/FUNCJL ACTV REPR                                                    \r',NULL,NULL,NULL),(8735,'96020','TEST SELECT & ADMN FUNCTL BRAIN MAP PHYS/QHP                                                        \r',NULL,NULL,NULL),(8736,'96040','MEDICAL GENETICS COUNSELING EACH 30 MINUTES                                                         \r',NULL,NULL,NULL),(8737,'96101','PSYCHOLOGICAL TESTING PR HR WITH PATIENT                                                            \r',NULL,NULL,NULL),(8738,'96102','PSYCHOLOGICAL TESTING ADMN BY TECH PR HR                                                            \r',NULL,NULL,NULL),(8739,'96103','PSYCHOLOGICAL TESTING COMPUTER W/PROF I&R                                                           \r',NULL,NULL,NULL),(8740,'96105','ASSESSMENT APHASIA W/INTERP & REPORT PER HOUR                                                       \r',NULL,NULL,NULL),(8741,'96110','DEVELOPMENTAL SCREENING W/INTERP&REPRT STD FORM                                                     \r',NULL,NULL,NULL),(8742,'96111','DEVELOPMENTAL TESTING W/INTERP & REPORT                                                             \r',NULL,NULL,NULL),(8743,'96116','NUBHVL STATUS XM PR HR W/PT INTERPJ&PREPJ                                                           \r',NULL,NULL,NULL),(8744,'96118','NUROPSYC TESTING PR HR W/PT & INTERPJ TIME                                                          \r',NULL,NULL,NULL),(8745,'96119','NUROPSYC TSTG W/PROF I&R ADMN BY TECH PR HR                                                         \r',NULL,NULL,NULL),(8746,'96120','NEUROPSYCHOLOG TESTING COMPUTER W/PROF I&R                                                          \r',NULL,NULL,NULL),(8747,'96125','STANDARDIZED COGNITIVE PERFORMANCE TESTING                                                          \r',NULL,NULL,NULL),(8748,'96150','HLTH&BEHAVIOR ASSMT EA 15 MIN W/PT 1ST ASSMT                                                        \r',NULL,NULL,NULL),(8749,'96151','HLTH&BEHAVIOR ASSMT EA 15 MIN W/PT RE-ASSMT                                                         \r',NULL,NULL,NULL),(8750,'96152','HLTH&BEHAVIOR IVNTJ EA 15 MIN INDIV                                                                 \r',NULL,NULL,NULL),(8751,'96153','HLTH&BEHAVIOR IVNTJ EA 15 MIN GRP 2/>PTS                                                            \r',NULL,NULL,NULL),(8752,'96154','HLTH&BEHAVIOR IVNTJ EA 15 MIN FAM W/PT                                                              \r',NULL,NULL,NULL),(8753,'96155','HLTH&BEHAVIOR IVNTJ EA 15 MIN FAM W/O PT                                                            \r',NULL,NULL,NULL),(8754,'96360','IV INFUSION HYDRATION INITIAL 31 MIN-1 HOUR                                                         \r',NULL,NULL,NULL),(8755,'96361','IV INFUSION HYDRATION EACH ADDITIONAL HOUR                                                          \r',NULL,NULL,NULL),(8756,'96365','IV INFUSION THERAPY/PROPHYLAXIS /DX 1ST TO 1 HR                                                     \r',NULL,NULL,NULL),(8757,'96366','IV INFUSION THERAPY PROPHYLAXIS/DX EA HOUR                                                          \r',NULL,NULL,NULL),(8758,'96367','IV INFUSION THER PROPH ADDL SEQUENTIAL TO 1 HR                                                      \r',NULL,NULL,NULL),(8759,'96368','IV NFS THERAPY PROPHYLAXIS/DX CONCURRENT NFS                                                        \r',NULL,NULL,NULL),(8760,'96369','SUBCUTANEOUS INFUSION INITIAL 1 HR W/PUMP SET-UP                                                    \r',NULL,NULL,NULL),(8761,'96370','SUBCUTANEOUS INFUSION EACH ADDITIONAL HOUR                                                          \r',NULL,NULL,NULL),(8762,'96371','SUBQ INFUSION ADDITIONAL PUMP INFUSION SITE                                                         \r',NULL,NULL,NULL),(8763,'96372','THERAPEUTIC PROPHYLACTIC/DX INJECTION SUBQ/IM                                                       \r',NULL,NULL,NULL),(8764,'96373','THERAPEUTIC PROPHYLACTIC/DX NJX INTRA-ARTERIAL                                                      \r',NULL,NULL,NULL),(8765,'96374','THER PROPH/DX NJX IV PUSH SINGLE/1ST SBST/DRUG                                                      \r',NULL,NULL,NULL),(8766,'96375','THERAPEUTIC INJECTION IV PUSH EACH NEW DRUG                                                         \r',NULL,NULL,NULL),(8767,'96376','THER PROPH/DX NJX EA SEQL IV PUSH SBST/DRUG FAC                                                     \r',NULL,NULL,NULL),(8768,'96379','UNLISTED THERAPEUTIC PROPH/DX IV/IA NJX/NFS                                                         \r',NULL,NULL,NULL),(8769,'96401','CHEMOTX ADMN SUBQ/IM NON-HORMONAL ANTI-NEO                                                          \r',NULL,NULL,NULL),(8770,'96402','CHEMOTX ADMN SUBQ/IM HORMONAL ANTI-NEO                                                              \r',NULL,NULL,NULL),(8771,'96405','CHEMOTHERAPY ADMINISTRATION INTRALESIONAL </7                                                       \r',NULL,NULL,NULL),(8772,'96406','CHEMOTHERAPY ADMINISTRATION INTRALESIONAL >7                                                        \r',NULL,NULL,NULL),(8773,'96409','CHEMOTX ADMN IV PUSH TQ 1/1ST SBST/DRUG                                                             \r',NULL,NULL,NULL),(8774,'96411','CHEMOTX ADMN IV PUSH TQ EA SBST/DRUG                                                                \r',NULL,NULL,NULL),(8775,'96413','CHEMOTX ADMN IV NFS TQ UP 1 HR 1/1ST SBST/DRUG                                                      \r',NULL,NULL,NULL),(8776,'96415','CHEMOTHERAPY ADMN IV INFUSION TQ EA HR                                                              \r',NULL,NULL,NULL),(8777,'96416','CHEMOTX ADMN TQ INIT PROLNG CHEMOTX NFUS PMP                                                        \r',NULL,NULL,NULL),(8778,'96417','CHEMOTX ADMN IV NFS TQ EA SEQL NFS TO 1 HR                                                          \r',NULL,NULL,NULL),(8779,'96420','CHEMOTHERAPY ADMIN INTRA-ARTERIAL PUSH TQ                                                           \r',NULL,NULL,NULL),(8780,'96422','CHEMOTHERAPY ADMIN INTRA-ARTERIAL INFUS </1 HR                                                      \r',NULL,NULL,NULL),(8781,'96423','CHEMOTHERAPY ADMN INTRAARTERIAL INFUSION EA HR                                                      \r',NULL,NULL,NULL),(8782,'96425','CHEMOTX ADMN IA NFS >8 HR PRTBLE IMPLTBL PMP                                                        \r',NULL,NULL,NULL),(8783,'96440','CHEMOTX ADMN PLEURAL CAVITY REQ&W/THORACNTS                                                         \r',NULL,NULL,NULL),(8784,'96446','CHEMOTX ADMN PRTL CAVITY PORT/CATH                                                                  \r',NULL,NULL,NULL),(8785,'96450','CHEMOTX ADMN CNS REQ SPINAL PUNCTURE                                                                \r',NULL,NULL,NULL),(8786,'96521','REFILLING & MAINTENANCE PORTABLE PUMP                                                               \r',NULL,NULL,NULL),(8787,'96522','REFILL&MAINTENANCE PUMP DRUG DLVR SYSTEMIC                                                          \r',NULL,NULL,NULL),(8788,'96523','IRRIGAJ IMPLNTD VENOUS ACCESS DRUG DELIVERY SYST                                                    \r',NULL,NULL,NULL),(8789,'96542','CHEMOTX NJX SUBARACHND/INTRAVENTR RSVR 1/MULT                                                       \r',NULL,NULL,NULL),(8790,'96549','UNLISTED CHEMOTHERAPY PROCEDURE                                                                     \r',NULL,NULL,NULL),(8791,'96567','PDT XTRNL APPL LIGHT DSTR LES SKN BY ACTIVJ RX                                                      \r',NULL,NULL,NULL),(8792,'96570','PDT NDSC ABL ABNOR TISS VIA ACTIVJ RX 30 MIN                                                        \r',NULL,NULL,NULL),(8793,'96571','PDT NDSC ABL ABNOR TISS VIA ACTIVJ RX A 15 MIN                                                      \r',NULL,NULL,NULL),(8794,'96900','ACTINOTHERAPY ULTRAVIOLET LIGHT                                                                     \r',NULL,NULL,NULL),(8795,'96902','MCRSCP XM HAIR PLUCK/CLIP FOR CNTS/STRUCT ABNORM                                                    \r',NULL,NULL,NULL),(8796,'96904','WHOLE BODY INTEGUMENTARY PHOTOGRAPHY                                                                \r',NULL,NULL,NULL),(8797,'96910','PHOTOCHEMOTX TAR&UVB/PETROLATUM/UVB                                                                 \r',NULL,NULL,NULL),(8798,'96912','PHOTOCHEMOTX PSORALENS&ULTRAVIOLET PUVA                                                             \r',NULL,NULL,NULL),(8799,'96913','PHOTOCHEMOTHERAPY DERMATOSES 4-8 HRS SUPERVISION                                                    \r',NULL,NULL,NULL),(8800,'96920','LASER SKIN DISEASE PSORIASIS TOT AREA <250 SQ C                                                     \r',NULL,NULL,NULL),(8801,'96921','LASER SKIN DISEASE PSORIASIS 250-500 SQ CM                                                          \r',NULL,NULL,NULL),(8802,'96922','LASER SKIN DISEASE PSORIASIS >500 SQ CM                                                             \r',NULL,NULL,NULL),(8803,'96999','UNLISTED SPECIAL DERMATOLOGICAL SERVICE/PROCED                                                      \r',NULL,NULL,NULL),(8804,'97001','PHYSICAL THERAPY EVALUATION                                                                         \r',NULL,NULL,NULL),(8805,'97002','PHYSICAL THERAPY RE-EVALUATION                                                                      \r',NULL,NULL,NULL),(8806,'97003','OCCUPATIONAL THERAPY EVALUATION                                                                     \r',NULL,NULL,NULL),(8807,'97004','OCCUPATIONAL THERAPY RE-EVALUATION                                                                  \r',NULL,NULL,NULL),(8808,'97005','ATHLETIC TRAINING EVALUATION                                                                        \r',NULL,NULL,NULL),(8809,'97006','ATHLETIC TRAINING RE-EVALUATION                                                                     \r',NULL,NULL,NULL),(8810,'97010','APPLICATION MODALITY 1/> AREAS HOT/COLD PACKS                                                       \r',NULL,NULL,NULL),(8811,'97012','APPL MODALITY 1/> AREAS TRACTION MECHANICAL                                                         \r',NULL,NULL,NULL),(8812,'97014','APPL MODALITY 1/> AREAS ELEC STIMJ UNATTENDED                                                       \r',NULL,NULL,NULL),(8813,'97016','APPL MODALITY 1/> AREAS VASOPNEUMATIC DEVICES                                                       \r',NULL,NULL,NULL),(8814,'97018','APPL MODALITY 1/> AREAS PARAFFIN BATH                                                               \r',NULL,NULL,NULL),(8815,'97022','APPLICATION MODALITY 1/> AREAS WHIRLPOOL                                                            \r',NULL,NULL,NULL),(8816,'97024','APPLICATION MODALITY 1/> AREAS DIATHERMY                                                            \r',NULL,NULL,NULL),(8817,'97026','APPLICATION MODALITY 1/> AREAS INFRARED                                                             \r',NULL,NULL,NULL),(8818,'97028','APPL MODALITY 1/> AREAS ULTRAVIOLET                                                                 \r',NULL,NULL,NULL),(8819,'97032','APPL MODALITY 1/> AREAS ELEC STIMJ EA 15 MIN                                                        \r',NULL,NULL,NULL),(8820,'97033','APPL MODALITY 1/> AREAS IONTOPHORESIS EA 15 MIN                                                     \r',NULL,NULL,NULL),(8821,'97034','APPL MODALITY 1/> AREAS CONTRAST BATHS EA 15 MIN                                                    \r',NULL,NULL,NULL),(8822,'97035','APPL MODALITY 1/> AREAS ULTRASOUND EA 15 MIN                                                        \r',NULL,NULL,NULL),(8823,'97036','APPL MODALITY 1/> AREAS HUBBARD TANK EA 15 MIN                                                      \r',NULL,NULL,NULL),(8824,'97039','UNLIST MODALITY SPEC TYPE&TIME CONSTANT ATTEND                                                      \r',NULL,NULL,NULL),(8825,'97110','THERAPEUTIC PX 1/> AREAS EACH 15 MIN EXERCISES                                                      \r',NULL,NULL,NULL),(8826,'97112','THER PX 1/> AREAS EACH 15 MIN NEUROMUSC REEDUCA                                                     \r',NULL,NULL,NULL),(8827,'97113','THER PX 1/> AREAS EACH 15 MIN AQUA THER W/XERSS                                                     \r',NULL,NULL,NULL),(8828,'97116','THER PX 1/> AREAS EA 15 MIN GAIT TRAINJ W/STAIR                                                     \r',NULL,NULL,NULL),(8829,'97124','THER PX 1/> AREAS EACH 15 MINUTES MASSAGE                                                           \r',NULL,NULL,NULL),(8830,'97139','UNLISTED THERAPEUTIC PROCEDURE SPECIFY                                                              \r',NULL,NULL,NULL),(8831,'97140','MANUAL THERAPY TQS 1/> REGIONS EACH 15 MINUTES                                                      \r',NULL,NULL,NULL),(8832,'97150','THERAPEUTIC PROCEDURES GROUP 2/> INDIVIDUALS                                                        \r',NULL,NULL,NULL),(8833,'97530','THERAPEUT ACTVITY DIRECT PT CONTACT EACH 15 MIN                                                     \r',NULL,NULL,NULL),(8834,'97532','DEVELOPMENT OF COGNITIVE SKILLS EACH 15 MINUTES                                                     \r',NULL,NULL,NULL),(8835,'97533','SENSORY INTEGRATIVE TECHNIQUES EACH 15 MINUTES                                                      \r',NULL,NULL,NULL),(8836,'97535','SELF-CARE/HOME MGMT TRAINING EACH 15 MINUTES                                                        \r',NULL,NULL,NULL),(8837,'97537','COMMUNITY/WORK REINTEGRATION TRAINJ EA 15 MIN                                                       \r',NULL,NULL,NULL),(8838,'97542','WHEELCHAIR MGMT EA 15 MIN                                                                           \r',NULL,NULL,NULL),(8839,'97545','WORK HARDENING/CONDITIONING 1ST 2 HR                                                                \r',NULL,NULL,NULL),(8840,'97546','WORK HARDENING/CONDITIONING EACH HOUR                                                               \r',NULL,NULL,NULL),(8841,'97597','DEBRIDEMENT OPEN WOUND 20 SQ CM/<                                                                   \r',NULL,NULL,NULL),(8842,'97598','DEBRIDEMENT OPEN WOUND EACH ADDITIONAL 20 SQ CM                                                     \r',NULL,NULL,NULL),(8843,'97602','RMVL DEVITAL TISS N-SLCTV DBRDMT W/O ANES 1 SESS                                                    \r',NULL,NULL,NULL),(8844,'97605','NEGATIVE PRESSURE WOUND THERAPY </= 50 SQ CM                                                        \r',NULL,NULL,NULL),(8845,'97606','NEGATIVE PRESSURE WOUND THERAPY >50 SQ CM                                                           \r',NULL,NULL,NULL),(8846,'97610','LOW FREQUENCY NON-THERMAL ULTRASOUND PER DAY                                                        \r',NULL,NULL,NULL),(8847,'97750','PHYSICAL PERFORMANCE TEST/MEAS W/REPRT EA 15 MIN                                                    \r',NULL,NULL,NULL),(8848,'97755','ASSTV TECHNOL ASSMT DIR CNTCT W/REPRT EA 15 MIN                                                     \r',NULL,NULL,NULL),(8849,'97760','ORTHOTIC MGMT&TRAINJ UXTR LXTR&/TRNK EA 15                                                          \r',NULL,NULL,NULL),(8850,'97761','PROSTHETIC TRAINING UPPR&/LOWER EXTREM EA 15 M                                                      \r',NULL,NULL,NULL),(8851,'97762','CHECKOUT ORTHOTIC/PROSTHETIC ESTAB PT EA 15 MIN                                                     \r',NULL,NULL,NULL),(8852,'97799','UNLISTED PHYSICAL MEDICINE/REHAB SERVICE/PROC                                                       \r',NULL,NULL,NULL),(8853,'97802','MEDICAL NUTRITION ASSMT&IVNTJ INDIV EACH 15 MI                                                      \r',NULL,NULL,NULL),(8854,'97803','MEDICAL NUTRITION RE-ASSMT&IVNTJ INDIV EA 15 M                                                      \r',NULL,NULL,NULL),(8855,'97804','MEDICAL NUTRITION THERAPY GRP2/ INDIV EA 30 MI                                                      \r',NULL,NULL,NULL),(8856,'97810','ACUPUNCTURE 1/> NDLES W/O ELEC STIMJ INIT 15 MIN                                                    \r',NULL,NULL,NULL),(8857,'97811','ACUPUNCTURE 1/> NDLS W/O ELEC STIMJ EA 15 MIN                                                       \r',NULL,NULL,NULL),(8858,'97813','ACUPUNCTURE 1/> NDLS W/ELEC STIMJ 1ST 15 MIN                                                        \r',NULL,NULL,NULL),(8859,'97814','ACUP 1/> NDLS W/ELEC STIMJ EA 15 MIN W/RE-INSJ                                                      \r',NULL,NULL,NULL),(8860,'98925','OSTEOPATHIC MANIPULATIVE TX 1-2 BODY REGIONS                                                        \r',NULL,NULL,NULL),(8861,'98926','OSTEOPATHIC MANIPULATIVE TX 3-4 BODY REGIONS                                                        \r',NULL,NULL,NULL),(8862,'98927','OSTEOPATHIC MANIPULATIVE TX 5-6 BODY REGIONS                                                        \r',NULL,NULL,NULL),(8863,'98928','OSTEOPATHIC MANIPULATIVE TX 7-8 BODY REGIONS                                                        \r',NULL,NULL,NULL),(8864,'98929','OSTEOPATHIC MANIPULATIVE TX 9-10 BODY REGIONS                                                       \r',NULL,NULL,NULL),(8865,'98940','CHIROPRACTIC MANIPULATIVE TX SPINAL 1-2 REGIONS                                                     \r',NULL,NULL,NULL),(8866,'98941','CHIROPRACTIC MANIPULATIVE TX SPINAL 3-4 REGIONS                                                     \r',NULL,NULL,NULL),(8867,'98942','CHIROPRACTIC MANIPULATIVE TX SPINAL 5 REGIONS                                                       \r',NULL,NULL,NULL),(8868,'98943','CHIROPRACTIC MANIPLTV TX EXTRASPINAL 1/> REGION                                                     \r',NULL,NULL,NULL),(8869,'98960','EDUCATION&TRAINING SELF-MGMT NONPHYS 1 PT                                                           \r',NULL,NULL,NULL),(8870,'98961','EDUCATION&TRAINING SELF-MGMT NONPHYS 2-4 PTS                                                        \r',NULL,NULL,NULL),(8871,'98962','EDUCATION&TRAINING SELF-MGMT NONPHYS 5-8 PTS                                                        \r',NULL,NULL,NULL),(8872,'98966','NONPHYSICIAN TELEPHONE ASSESSMENT 5-10 MIN                                                          \r',NULL,NULL,NULL),(8873,'98967','NONPHYSICIAN TELEPHONE ASSESSMENT 11-20 MIN                                                         \r',NULL,NULL,NULL),(8874,'98968','NONPHYSICIAN TELEPHONE ASSESSMENT 21-30 MIN                                                         \r',NULL,NULL,NULL),(8875,'98969','NONPHYSICIAN ONLINE ASSESSMENT AND MANAGEMENT                                                       \r',NULL,NULL,NULL),(8876,'99000','HANDLG&/OR CONVEY OF SPEC FOR TR OFFICE TO LAB                                                      \r',NULL,NULL,NULL),(8877,'99001','HANDLG&/OR CONVEY OF SPEC FOR TR FROM PT TO LAB                                                     \r',NULL,NULL,NULL),(8878,'99002','HANDLE/CONVEY/ANY OTH SVC DEVICE FIT PHYS/QHP                                                       \r',NULL,NULL,NULL),(8879,'99024','POSTOP FOLLOW UP VISIT RELATED TO ORIGINAL PX                                                       \r',NULL,NULL,NULL),(8880,'99026','HOSPITAL MANDATED CALL SERVICE IN-HOSPITAL EA HR                                                    \r',NULL,NULL,NULL),(8881,'99027','HOSPITAL MANDATED CALL SVC OUT-OF-HOSPITAL EA HR                                                    \r',NULL,NULL,NULL),(8882,'99050','SERVICES PROVIDED OFFICE OTH/THN REG SCHED HOURS                                                    \r',NULL,NULL,NULL),(8883,'99051','SVC PRV OFFICE REG SCHEDD EVN WKEND/HOLIDAY HRS                                                     \r',NULL,NULL,NULL),(8884,'99053','SERVICES PROVIDED BTW 10 PM&8 AM AT 24-HR FACI                                                      \r',NULL,NULL,NULL),(8885,'99056','SVC TYPICAL PRV OFFICE PRV OUT OFFICE REQUEST PT                                                    \r',NULL,NULL,NULL),(8886,'99058','SVC PRV EMER BASIS IN OFFICE DISRUPTING SVCS                                                        \r',NULL,NULL,NULL),(8887,'99060','SVC PRV EMER OUT OFFICE DISRUPTS OFFICE SVC                                                         \r',NULL,NULL,NULL),(8888,'99070','SUPPLIES&MATERIALS ABOVE/BEYOND PROV BY PHYS/QHP                                                    \r',NULL,NULL,NULL),(8889,'99071','EDUCATIONAL SUPPLIES PRV BY THE PHYS AT COST                                                        \r',NULL,NULL,NULL),(8890,'99075','MEDICAL TESTIMONY                                                                                   \r',NULL,NULL,NULL),(8891,'99078','PHYS/QHP EDUCATION SVCS RENDERED PTS GRP SETTING                                                    \r',NULL,NULL,NULL),(8892,'99080','SPEC REPORTS > USUAL MED COMUNICAJ/STAND RPRTG                                                      \r',NULL,NULL,NULL),(8893,'99082','UNUSUAL TRAVEL                                                                                      \r',NULL,NULL,NULL),(8894,'99090','ANALYSIS CLINICAL DATA STORED IN COMPUTERS                                                          \r',NULL,NULL,NULL),(8895,'99091','COLLJ&INTERPJ PHYS/QHP PHYSIO COMPUTR DATA 30 MI                                                    \r',NULL,NULL,NULL),(8896,'99100','ANESTHESIA EXTREME AGE PATIENT UNDER 1 YR/<                                                         \r',NULL,NULL,NULL),(8897,'99116','ANES COMPLICJ UTILIZATION TOTAL BODY HYPOTHERMIA                                                    \r',NULL,NULL,NULL),(8898,'99135','ANES COMPLICJ UTILIZATION CONTROLLED HYPOTENSION                                                    \r',NULL,NULL,NULL),(8899,'99140','ANES COMPLICJ EMERGENCY CONDITIONS SPECIFY                                                          \r',NULL,NULL,NULL),(8900,'99143','MODERATE SEDATJ SAME PHYS/QHP <5 YRS INIT 30 MIN                                                    \r',NULL,NULL,NULL),(8901,'99144','MODERATE SEDATJ SAME PHYS/QHP 5/>YRS INIT 30 MIN                                                    \r',NULL,NULL,NULL),(8902,'99145','MODERATE SEDATJ SAME PHYS/QHP EACH ADDL 15 MIN                                                      \r',NULL,NULL,NULL),(8903,'99148','MOD SEDATJ DIFF PHYS/QHP <5 YRS INIT 30 MIN                                                         \r',NULL,NULL,NULL),(8904,'99149','MODERATE SEDATJ DIFF PHYS/QHP 5/>YRS INIT 30 MIN                                                    \r',NULL,NULL,NULL),(8905,'99150','MODERATE SEDATJ DIFF PHYS/QHP EA ADDL 15 MIN                                                        \r',NULL,NULL,NULL),(8906,'99170','ANOGENITAL XM MAGNIFY CHILD/SUSPECT TRAUMA W IMG                                                    \r',NULL,NULL,NULL),(8907,'99172','VISUAL FUNCT SCRNG AUTO SEMI-AUTO BI QUAN DETERM                                                    \r',NULL,NULL,NULL),(8908,'99173','SCREENING TEST VISUAL ACUITY QUANTITATIVE BILAT                                                     \r',NULL,NULL,NULL),(8909,'99174','INSTRUMENT BASED OCULAR SCREENING BILATERAL                                                         \r',NULL,NULL,NULL),(8910,'99175','IPECAC/SIMILAR ADMN EMESIS&OBS STOMACH EMPTIED                                                      \r',NULL,NULL,NULL),(8911,'99183','PHYS/QHP ATTN&SUPVJ HYPRBARIC OXYGEN TX /SESSION                                                    \r',NULL,NULL,NULL),(8912,'99190','ASSEMBLY&OPERJ PUMP OXYGENATOR/HEAT EXCH EA HR                                                      \r',NULL,NULL,NULL),(8913,'99191','ASSEMBLY&OPERJ PUMP OXYGENATOR/HEAT EXCH 45 MI                                                      \r',NULL,NULL,NULL),(8914,'99192','ASSEMBLY&OPERJ PUMP OXYGENATOR/HEAT EXCH 30 MI                                                      \r',NULL,NULL,NULL),(8915,'99195','PHLEBOTOMY THERAPEUTIC SEPARATE PROCEDURE                                                           \r',NULL,NULL,NULL),(8916,'99199','UNLISTED SPECIAL SERVICE PROCEDURE/REPORT                                                           \r',NULL,NULL,NULL),(8917,'99201','OFFICE OUTPATIENT NEW 10 MINUTES                                                                    \r',NULL,NULL,NULL),(8918,'99202','OFFICE OUTPATIENT NEW 20 MINUTES                                                                    \r',NULL,NULL,NULL),(8919,'99203','OFFICE OUTPATIENT NEW 30 MINUTES                                                                    \r',NULL,NULL,NULL),(8920,'99204','OFFICE OUTPATIENT NEW 45 MINUTES                                                                    \r',NULL,NULL,NULL),(8921,'99205','OFFICE OUTPATIENT NEW 60 MINUTES                                                                    \r',NULL,NULL,NULL),(8922,'99211','OFFICE OUTPATIENT VISIT 5 MINUTES                                                                   \r',NULL,NULL,NULL),(8923,'99212','OFFICE OUTPATIENT VISIT 10 MINUTES                                                                  \r',NULL,NULL,NULL),(8924,'99213','OFFICE OUTPATIENT VISIT 15 MINUTES                                                                  \r',NULL,NULL,NULL),(8925,'99214','OFFICE OUTPATIENT VISIT 25 MINUTES                                                                  \r',NULL,NULL,NULL),(8926,'99215','OFFICE OUTPATIENT VISIT 40 MINUTES                                                                  \r',NULL,NULL,NULL),(8927,'99217','OBSERVATION CARE DISCHARGE MANAGEMENT                                                               \r',NULL,NULL,NULL),(8928,'99218','INITIAL OBSERVATION CARE/DAY 30 MINUTES                                                             \r',NULL,NULL,NULL),(8929,'99219','INITIAL OBSERVATION CARE/DAY 50 MINUTES                                                             \r',NULL,NULL,NULL),(8930,'99220','INITIAL OBSERVATION CARE/DAY 70 MINUTES                                                             \r',NULL,NULL,NULL),(8931,'99221','INITIAL HOSPITAL CARE/DAY 30 MINUTES                                                                \r',NULL,NULL,NULL),(8932,'99222','INITIAL HOSPITAL CARE/DAY 50 MINUTES                                                                \r',NULL,NULL,NULL),(8933,'99223','INITIAL HOSPITAL CARE/DAY 70 MINUTES                                                                \r',NULL,NULL,NULL),(8934,'99224','SBSQ OBSERVATION CARE/DAY 15 MINUTES                                                                \r',NULL,NULL,NULL),(8935,'99225','SBSQ OBSERVATION CARE/DAY 25 MINUTES                                                                \r',NULL,NULL,NULL),(8936,'99226','SBSQ OBSERVATION CARE/DAY 35 MINUTES                                                                \r',NULL,NULL,NULL),(8937,'99231','SBSQ HOSPITAL CARE/DAY 15 MINUTES                                                                   \r',NULL,NULL,NULL),(8938,'99232','SBSQ HOSPITAL CARE/DAY 25 MINUTES                                                                   \r',NULL,NULL,NULL),(8939,'99233','SBSQ HOSPITAL CARE/DAY 35 MINUTES                                                                   \r',NULL,NULL,NULL),(8940,'99234','OBSERVATION/INPATIENT HOSPITAL CARE 40 MINUTES                                                      \r',NULL,NULL,NULL),(8941,'99235','OBSERVATION/INPATIENT HOSPITAL CARE 50 MINUTES                                                      \r',NULL,NULL,NULL),(8942,'99236','OBSERVATION/INPATIENT HOSPITAL CARE 55 MINUTES                                                      \r',NULL,NULL,NULL),(8943,'99238','HOSPITAL DISCHARGE DAY MANAGEMENT 30 MIN/<                                                          \r',NULL,NULL,NULL),(8944,'99239','HOSPITAL DISCHARGE DAY MANAGEMENT > 30 MIN                                                          \r',NULL,NULL,NULL),(8945,'99241','OFFICE CONSULTATION NEW/ESTAB PATIENT 15 MIN                                                        \r',NULL,NULL,NULL),(8946,'99242','OFFICE CONSULTATION NEW/ESTAB PATIENT 30 MIN                                                        \r',NULL,NULL,NULL),(8947,'99243','OFFICE CONSULTATION NEW/ESTAB PATIENT 40 MIN                                                        \r',NULL,NULL,NULL),(8948,'99244','OFFICE CONSULTATION NEW/ESTAB PATIENT 60 MIN                                                        \r',NULL,NULL,NULL),(8949,'99245','OFFICE CONSULTATION NEW/ESTAB PATIENT 80 MIN                                                        \r',NULL,NULL,NULL),(8950,'99251','INITL INPATIENT CONSULT NEW/ESTAB PT 20 MIN                                                         \r',NULL,NULL,NULL),(8951,'99252','INITL INPATIENT CONSULT NEW/ESTAB PT 40 MIN                                                         \r',NULL,NULL,NULL),(8952,'99253','INITL INPATIENT CONSULT NEW/ESTAB PT 55 MIN                                                         \r',NULL,NULL,NULL),(8953,'99254','INITL INPATIENT CONSULT NEW/ESTAB PT 80 MIN                                                         \r',NULL,NULL,NULL),(8954,'99255','INITIAL INPATIENT CONSULT NEW/ESTAB PT 110 MIN                                                      \r',NULL,NULL,NULL),(8955,'99281','EMERGENCY DEPARTMENT VISIT LIMITED/MINOR PROB                                                       \r',NULL,NULL,NULL),(8956,'99282','EMERGENCY DEPARTMENT VISIT LOW/MODER SEVERITY                                                       \r',NULL,NULL,NULL),(8957,'99283','EMERGENCY DEPARTMENT VISIT MODERATE SEVERITY                                                        \r',NULL,NULL,NULL),(8958,'99284','EMERGENCY DEPARTMENT VISIT HIGH/URGENT SEVERITY                                                     \r',NULL,NULL,NULL),(8959,'99285','EMERGENCY DEPT VISIT HIGH SEVERITY&THREAT FUNCJ                                                     \r',NULL,NULL,NULL),(8960,'99288','PHYS/QHP DIRECTION EMERGENCY MEDICAL SYSTEMS                                                        \r',NULL,NULL,NULL),(8961,'99291','CRITICAL CARE ILL/INJURED PATIENT INIT 30-74 MIN                                                    \r',NULL,NULL,NULL),(8962,'99292','CRITICAL CARE ILL/INJURED PATIENT ADDL 30 MIN                                                       \r',NULL,NULL,NULL),(8963,'99304','INITIAL NURSING FACILITY CARE/DAY 25 MINUTES                                                        \r',NULL,NULL,NULL),(8964,'99305','INITIAL NURSING FACILITY CARE/DAY 35 MINUTES                                                        \r',NULL,NULL,NULL),(8965,'99306','INITIAL NURSING FACILITY CARE/DAY 45 MINUTES                                                        \r',NULL,NULL,NULL),(8966,'99307','SBSQ NURSING FACILITY CARE/DAY E/M STABLE 10 MIN                                                    \r',NULL,NULL,NULL),(8967,'99308','SBSQ NURSING FACIL CARE/DAY MINOR COMPLJ 15 MIN                                                     \r',NULL,NULL,NULL),(8968,'99309','SBSQ NURSING FACIL CARE/DAY NEW PROBLEM 25 MIN                                                      \r',NULL,NULL,NULL),(8969,'99310','SBSQ NURS FACIL CARE/DAY UNSTABL/NEW PROB 35 MIN                                                    \r',NULL,NULL,NULL),(8970,'99315','NURSING FACILITY DISCHARGE MANAGEMENT 30 MINUTES                                                    \r',NULL,NULL,NULL),(8971,'99316','NURSING FACILITY DISCHARGE MANAGEMENT 30 MINUTES                                                    \r',NULL,NULL,NULL),(8972,'99318','E/M ANNUAL NURSING FACILITY ASSESS STABLE 30 MIN                                                    \r',NULL,NULL,NULL),(8973,'99324','DOMICIL/REST HOME NEW PT VISIT LOW SEVER 20 MIN                                                     \r',NULL,NULL,NULL),(8974,'99325','DOMICIL/REST HOME NEW PT VISIT MOD SEVER 30 MIN                                                     \r',NULL,NULL,NULL),(8975,'99326','DOMICIL/REST HOME NEW PT HI-MOD SEVER 45 MINUTES                                                    \r',NULL,NULL,NULL),(8976,'99327','DOMICIL/REST HOME NEW PT VISIT HI SEVER 60 MIN                                                      \r',NULL,NULL,NULL),(8977,'99328','DOM/R-HOME E/M NEW PT SIGNIF NEW PROB 75 MINUTES                                                    \r',NULL,NULL,NULL),(8978,'99334','DOM/R-HOME E/M EST PT SELF-LMTD/MINOR 15 MINUTES                                                    \r',NULL,NULL,NULL),(8979,'99335','DOM/R-HOME E/M EST PT LW MOD SEVERITY 25 MINUTES                                                    \r',NULL,NULL,NULL),(8980,'99336','DOM/R-HOME E/M EST PT MOD HI SEVERITY 40 MINUTES                                                    \r',NULL,NULL,NULL),(8981,'99337','DOM/R-HOME E/M EST PT SIGNIF NEW PROB 60 MINUTES                                                    \r',NULL,NULL,NULL),(8982,'99339','INDIV PHYS SUPVJ HOME/DOM/R-HOME MO 15-29 MIN                                                       \r',NULL,NULL,NULL),(8983,'99340','INDIV PHYS SUPVJ HOME/DOM/R-HOME MO 30 MIN/>                                                        \r',NULL,NULL,NULL),(8984,'99341','HOME VISIT NEW PATIENT LOW SEVERITY 20 MINUTES                                                      \r',NULL,NULL,NULL),(8985,'99342','HOME VISIT NEW PATIENT MOD SEVERITY 30 MINUTES                                                      \r',NULL,NULL,NULL),(8986,'99343','HOME VST NEW PATIENT MOD-HI SEVERITY 45 MINUTES                                                     \r',NULL,NULL,NULL),(8987,'99344','HOME VISIT NEW PATIENT HI SEVERITY 60 MINUTES                                                       \r',NULL,NULL,NULL),(8988,'99345','HOME VISIT NEW PT UNSTABL/SIGNIF NEW PROB 75 MIN                                                    \r',NULL,NULL,NULL),(8989,'99347','HOME VISIT EST PT SELF LIMITED/MINOR 15 MINUTES                                                     \r',NULL,NULL,NULL),(8990,'99348','HOME VISIT EST PT LOW-MOD SEVERITY 25 MINUTES                                                       \r',NULL,NULL,NULL),(8991,'99349','HOME VISIT EST PT MOD-HI SEVERITY 40 MINUTES                                                        \r',NULL,NULL,NULL),(8992,'99350','HOME VST EST PT UNSTABLE/SIGNIF NEW PROB 60 MINS                                                    \r',NULL,NULL,NULL),(8993,'99354','PROLNG SVC OFFICE O/P DIR CONTACT 1ST HR                                                            \r',NULL,NULL,NULL),(8994,'99355','PROLNG SVC OFFICE O/P DIR CONTACT EA 30 MINUTES                                                     \r',NULL,NULL,NULL),(8995,'99356','PROLONGED SERVICE I/P REQ UNIT/FLOOR TIME 1ST HR                                                    \r',NULL,NULL,NULL),(8996,'99357','PROLONGED SVC I/P REQ UNIT/FLOOR TIME EA 30 MIN                                                     \r',NULL,NULL,NULL),(8997,'99358','PROLNG E/M SVC BEFORE&/AFTER DIR PT CARE 1ST HR                                                     \r',NULL,NULL,NULL),(8998,'99359','PROLNG E/M BEFORE&/AFTER DIR CARE EA 30 MINUTES                                                     \r',NULL,NULL,NULL),(8999,'99360','PHYS STANDBY SVC PROLNG PHYS ATTN EA 30 MINUTES                                                     \r',NULL,NULL,NULL),(9000,'99363','ANTICOAGULANT MGMT OUTPATIENT INIT 90 DAYS                                                          \r',NULL,NULL,NULL),(9001,'99364','ANTICOAGULANT MGMT OUTPATIENT EA SBSQ 90 DAYS                                                       \r',NULL,NULL,NULL),(9002,'99366','TEAM CONFERENCE FACE-TO-FACE NONPHYSICIAN                                                           \r',NULL,NULL,NULL),(9003,'99367','TEAM CONFERENCE NON-FACE-TO-FACE PHYSICIAN                                                          \r',NULL,NULL,NULL),(9004,'99368','TEAM CONFERENCE NON-FACE-TO-FACE NONPHYSICIAN                                                       \r',NULL,NULL,NULL),(9005,'99374','SUPVJ PT HOME HEALTH AGENCY MO 15-29 MINUTES                                                        \r',NULL,NULL,NULL),(9006,'99375','SUPERVISION PT HOME HEALTH AGENCY MONTH 30 MIN/>                                                    \r',NULL,NULL,NULL),(9007,'99377','SUPERVISION HOSPICE PATIENT/MONTH 15-29 MIN                                                         \r',NULL,NULL,NULL),(9008,'99378','SUPERVISION HOSPICE PATIENT/MONTH 30 MINUTES/>                                                      \r',NULL,NULL,NULL),(9009,'99379','SUPERVISION NURS FACILITY PATIENT MO 15-29 MIN                                                      \r',NULL,NULL,NULL),(9010,'99380','SUPERVISION NURS FACILITY PATIENT MONTH 30 MIN/>                                                    \r',NULL,NULL,NULL),(9011,'99381','INITIAL PREVENTIVE MEDICINE NEW PATIENT <1YEAR                                                      \r',NULL,NULL,NULL),(9012,'99382','INITIAL PREVENTIVE MEDICINE NEW PT AGE 1-4 YRS                                                      \r',NULL,NULL,NULL),(9013,'99383','INITIAL PREVENTIVE MEDICINE NEW PT AGE 5-11 YRS                                                     \r',NULL,NULL,NULL),(9014,'99384','INITIAL PREVENTIVE MEDICINE NEW PT AGE 12-17 YR                                                     \r',NULL,NULL,NULL),(9015,'99385','INITIAL PREVENTIVE MEDICINE NEW PT AGE 18-39YRS                                                     \r',NULL,NULL,NULL),(9016,'99386','INITIAL PREVENTIVE MEDICINE NEW PATIENT 40-64YRS                                                    \r',NULL,NULL,NULL),(9017,'99387','INITIAL PREVENTIVE MEDICINE NEW PATIENT 65YRS&>                                                     \r',NULL,NULL,NULL),(9018,'99391','PERIODIC PREVENTIVE MED ESTABLISHED PATIENT <1Y                                                     \r',NULL,NULL,NULL),(9019,'99392','PERIODIC PREVENTIVE MED EST PATIENT 1-4YRS                                                          \r',NULL,NULL,NULL),(9020,'99393','PERIODIC PREVENTIVE MED EST PATIENT 5-11YRS                                                         \r',NULL,NULL,NULL),(9021,'99394','PERIODIC PREVENTIVE MED EST PATIENT 12-17YRS                                                        \r',NULL,NULL,NULL),(9022,'99395','PERIODIC PREVENTIVE MED EST PATIENT 18-39 YRS                                                       \r',NULL,NULL,NULL),(9023,'99396','PERIODIC PREVENTIVE MED EST PATIENT 40-64YRS                                                        \r',NULL,NULL,NULL),(9024,'99397','PERIODIC PREVENTIVE MED EST PATIENT 65YRS& OLDER                                                    \r',NULL,NULL,NULL),(9025,'99401','PREVENT MED COUNSEL&/RISK FACTOR REDJ SPX 15 M                                                      \r',NULL,NULL,NULL),(9026,'99402','PREVENT MED COUNSEL&/RISK FACTOR REDJ SPX 30 M                                                      \r',NULL,NULL,NULL),(9027,'99403','PREVENT MED COUNSEL&/RISK FACTOR REDJ SPX 45 M                                                      \r',NULL,NULL,NULL),(9028,'99404','PREVENT MED COUNSEL&/RISK FACTOR REDJ SPX 60 M                                                      \r',NULL,NULL,NULL),(9029,'99406','TOBACCO USE CESSATION INTERMEDIATE 3-10 MINUTES                                                     \r',NULL,NULL,NULL),(9030,'99407','TOBACCO USE CESSATION INTENSIVE >10 MINUTES                                                         \r',NULL,NULL,NULL),(9031,'99408','ALCOHOL/SUBSTANCE SCREEN & INTERVEN 15-30 MIN                                                       \r',NULL,NULL,NULL),(9032,'99409','ALCOHOL/SUBSTANCE SCREEN & INTERVENTION >30 MIN                                                     \r',NULL,NULL,NULL),(9033,'99411','PREV MED COUNSEL & RISK FACTOR REDJ GRP SPX 30 M                                                    \r',NULL,NULL,NULL),(9034,'99412','PREV MED COUNSEL & RISK FACTOR REDJ GRP SPX 60 M                                                    \r',NULL,NULL,NULL),(9035,'99420','ADMN & INTERPJ HEALTH RISK ASSESSMENT INSTRUMENT                                                    \r',NULL,NULL,NULL),(9036,'99429','UNLISTED PREVENTIVE MEDICINE SERVICE                                                                \r',NULL,NULL,NULL),(9037,'99441','PHYS/QHP TELEPHONE EVALUATION 5-10 MIN                                                              \r',NULL,NULL,NULL),(9038,'99442','PHYS/QHP TELEPHONE EVALUATION 11-20 MIN                                                             \r',NULL,NULL,NULL),(9039,'99443','PHYS/QHP TELEPHONE EVALUATION 21-30 MIN                                                             \r',NULL,NULL,NULL),(9040,'99444','PHYS/QHP ONLINE EVALUATION & MANAGEMENT SERVICE                                                     \r',NULL,NULL,NULL),(9041,'99446','INTERPROF PHONE/INTERNET ASSESS/MANAGE 5-10                                                         \r',NULL,NULL,NULL),(9042,'99447','INTERPROF PHONE/INTERNET ASSESS/MANAGE 11-20                                                        \r',NULL,NULL,NULL),(9043,'99448','INTERPROF PHONE/INTERNET ASSESS/MANAGE 21-30                                                        \r',NULL,NULL,NULL),(9044,'99449','INTERPROF PHONE/INTERNET ASSESS/MANAGE31/>                                                          \r',NULL,NULL,NULL),(9045,'99450','BASIC LIFE AND/OR DISABILITY EXAMINATION                                                            \r',NULL,NULL,NULL),(9046,'99455','WORK RELATED/MED DBLT XM TREATING PHYS                                                              \r',NULL,NULL,NULL),(9047,'99456','WORK RELATED/MED DBLT XM OTH/THN TREATING PHYS                                                      \r',NULL,NULL,NULL),(9048,'99460','1ST HOSP/BIRTHING CENTER CARE PER DAY NML NB                                                        \r',NULL,NULL,NULL),(9049,'99461','1ST CARE PR DAY NML NB XCPT HOSP/BIRTHING CENTER                                                    \r',NULL,NULL,NULL),(9050,'99462','SUBQ HOSPITAL CARE PER DAY E/M NORMAL NEWBORN                                                       \r',NULL,NULL,NULL),(9051,'99463','1ST HOSP/BIRTHING CENTER NB ADMIT & DSCHG SM DAT                                                    \r',NULL,NULL,NULL),(9052,'99464','ATTN AT DELIVERY 1ST STABILIZATION OF NEWBORN                                                       \r',NULL,NULL,NULL),(9053,'99465','DELIVERY/BIRTHING ROOM RESUSCITATION                                                                \r',NULL,NULL,NULL),(9054,'99466','CRITICAL CARE INTERFACILITY TRANSPORT 30-74 MIN                                                     \r',NULL,NULL,NULL),(9055,'99467','CRITICAL CARE INTERFACILITY TRANSPORT EA 30 MIN                                                     \r',NULL,NULL,NULL),(9056,'99468','1ST INPATIENT CRITICAL CARE PR DAY AGE 28 DAYS/<                                                    \r',NULL,NULL,NULL),(9057,'99469','SUBQ I/P CRITICAL CARE PR DAY AGE 28 DAYS/<                                                         \r',NULL,NULL,NULL),(9058,'99471','INITIAL PED CRITICAL CARE 29 DAYS THRU 24 MONTHS                                                    \r',NULL,NULL,NULL),(9059,'99472','SUBSQ PED CRITICAL CARE 29 DAYS THRU 24 MO                                                          \r',NULL,NULL,NULL),(9060,'99475','INITIAL PED CRITICAL CARE 2 THRU 5 YEARS                                                            \r',NULL,NULL,NULL),(9061,'99476','SUBSEQUENT PED CRITICAL CARE 2 THRU 5 YEARS                                                         \r',NULL,NULL,NULL),(9062,'99477','INITIAL HOSP NEONATE 28 D/< NOT CRITICALLY ILL                                                      \r',NULL,NULL,NULL),(9063,'99478','SUBSEQUENT INTENSIVE CARE INFANT < 1500 GRAMS                                                       \r',NULL,NULL,NULL),(9064,'99479','SUBSEQUENT INTENSIVE CARE INFANT 1500-2500 GRAMS                                                    \r',NULL,NULL,NULL),(9065,'99480','SUBSEQUENT INTENSIVE CARE INFANT 2501-5000 GRAMS                                                    \r',NULL,NULL,NULL),(9066,'99481','TOT BODY SYSTEMIC HYPOTHERMIA CRITICAL NEONATE                                                      \r',NULL,NULL,NULL),(9067,'99482','SELECTIVE HEAD HYPOTHERMIA CRITICAL NEONATE                                                         \r',NULL,NULL,NULL),(9068,'99485','SUPERVISION INTERFACILITY TRANSPORT INIT 30 MIN                                                     \r',NULL,NULL,NULL),(9069,'99486','SUPERVISION INTERFACILITY TRANSPORT ADDL 30 MIN                                                     \r',NULL,NULL,NULL),(9070,'99487','COMPLX CHRON CARE COORD W/O PT VST 1ST HR PER MO                                                    \r',NULL,NULL,NULL),(9071,'99488','COMPLX CHRON CARE COORD W/ PT VST 1ST HR PER MO                                                     \r',NULL,NULL,NULL),(9072,'99489','COMPLX CHRON CARE COORD EA ADDL 30 MIN PER MONTH                                                    \r',NULL,NULL,NULL),(9073,'99495','TRANSITIONAL CARE MANAGE SRVC 14 DAY DISCHARGE                                                      \r',NULL,NULL,NULL),(9074,'99496','TRANSITIONAL CARE MANAGE SRVC 7 DAY DISCHARGE                                                       \r',NULL,NULL,NULL),(9075,'99499','UNLISTED EVALUATION AND MANAGEMENT SERVICE                                                          \r',NULL,NULL,NULL),(9076,'99500','HOME VISIT PRENATAL MONITORING & ASSESSMENT                                                         \r',NULL,NULL,NULL),(9077,'99501','HOME VISIT POSTNATAL ASSMT&F-UP CARE                                                                \r',NULL,NULL,NULL),(9078,'99502','HOME VISIT NEWBORN CARE & ASSESSMENT                                                                \r',NULL,NULL,NULL),(9079,'99503','HOME VISIT RESPIRATORY THERAPY CARE                                                                 \r',NULL,NULL,NULL),(9080,'99504','HOME VISIT MECHANICAL VENTILATION CARE                                                              \r',NULL,NULL,NULL),(9081,'99505','HOME VISIT STOMA CARE&MAINT CLST&CSTOST                                                             \r',NULL,NULL,NULL),(9082,'99506','HOME VISIT INTRAMUSCULAR INJECTIONS                                                                 \r',NULL,NULL,NULL),(9083,'99507','HOME VISIT CARE&MAINT CATH                                                                          \r',NULL,NULL,NULL),(9084,'99509','HOME VISIT ASSISTANCE DAILY LIV&PRSONAL CARE                                                        \r',NULL,NULL,NULL),(9085,'99510','HOME VISIT INDIV FAM/MARRIAGE COUNSELING                                                            \r',NULL,NULL,NULL),(9086,'99511','HOME VISIT FECAL IMPACTION MGMT&ENEMA ADMN                                                          \r',NULL,NULL,NULL),(9087,'99512','HOME VISIT HEMODIALYSIS                                                                             \r',NULL,NULL,NULL),(9088,'99600','UNLISTED HOME VISIT SERVICE/PROCEDURE                                                               \r',NULL,NULL,NULL),(9089,'99601','HOME NFS/SPECTY DRUG ADMN PR VST </2 HR                                                             \r',NULL,NULL,NULL),(9090,'99602','HOME NFS/SPECTY DRUG ADMN PR VST </2 HR EA HR                                                       \r',NULL,NULL,NULL),(9091,'99605','MEDICATION THERAPY INITIAL 15 MIN NEW PATIENT                                                       \r',NULL,NULL,NULL),(9092,'99606','MEDICATION THERAPY INITIAL 15 MIN ESTABLISHED PT                                                    \r',NULL,NULL,NULL),(9093,'99607','MEDICATION THERAPY EACH ADDITIONAL 15 MIN                                                           \r',NULL,NULL,NULL),(9094,'0001F','HRT FAILURE ASSESSED                                                                                \r',NULL,NULL,NULL),(9095,'0005F','OSTEOARTHRITIS COMPOSITE                                                                            \r',NULL,NULL,NULL),(9096,'0012F','COMMUNITY-ACQUIRED BACTERIAL PNEUMONIA ASSMT                                                        \r',NULL,NULL,NULL),(9097,'0014F','COMP PREOP ASSESS CATARACT SURG W/IOL PLACEMNT                                                      \r',NULL,NULL,NULL),(9098,'0015F','MELANOMA FOLLOW UP COMPLETED                                                                        \r',NULL,NULL,NULL),(9099,'0500F','INITIAL PRENATAL CARE VISIT                                                                         \r',NULL,NULL,NULL),(9100,'0501F','PRENATAL FLOW SHEET                                                                                 \r',NULL,NULL,NULL),(9101,'0502F','SUBSEQUENT PRENATAL CARE VISIT                                                                      \r',NULL,NULL,NULL),(9102,'0503F','POSTPARTUM CARE VISIT                                                                               \r',NULL,NULL,NULL),(9103,'0505F','HEMODIALYSIS PLAN OF CARE DOCUMENTED                                                                \r',NULL,NULL,NULL),(9104,'0507F','PERITONEAL DIALYSIS PLAN DOCUMENTED                                                                 \r',NULL,NULL,NULL),(9105,'0509F','URINARY INCONTINENCE PLAN OF CARE DOCUMENTED                                                        \r',NULL,NULL,NULL),(9106,'0513F','ELEVATED BLOOD PRESSURE PLAN OF CARE DOCUMENTED                                                     \r',NULL,NULL,NULL),(9107,'0514F','PLAN/CARE INCRSD HGB LVL DOCD PT ON ESA THXPY                                                       \r',NULL,NULL,NULL),(9108,'0516F','ANEMIA PLAN OF CARE DOCUMENTED                                                                      \r',NULL,NULL,NULL),(9109,'0517F','GLAUCOMA PLAN OF CARE DOCUMENTED                                                                    \r',NULL,NULL,NULL),(9110,'0518F','FALLS PLAN OF CARE DOCUMENTED                                                                       \r',NULL,NULL,NULL),(9111,'0519F','PLANNED CHEMO REGIMEN DOCD PRIOR START NEW TX                                                       \r',NULL,NULL,NULL),(9112,'0520F','RAD DOSE LIMTS EST PRIOR3D RAD FOR MIN 2 TIS/ORG                                                    \r',NULL,NULL,NULL),(9113,'0521F','PLAN OF CARE TO ADDRESS PAIN DOCUMENTED                                                             \r',NULL,NULL,NULL),(9114,'0525F','INITIAL VISIT FOR EPISODE                                                                           \r',NULL,NULL,NULL),(9115,'0526F','SUBSEQUENT VISIT FOR EPISODE                                                                        \r',NULL,NULL,NULL),(9116,'0528F','RCMND FLLW-UP 2ND CLNSCPY 10/> YRS DOCD RPRT                                                        \r',NULL,NULL,NULL),(9117,'0529F','INTRVL 3/> YRS PTS LAST COLONOSCOPY DOCD                                                            \r',NULL,NULL,NULL),(9118,'0535F','DYSPNEA MANAGEMENT PLAN DOCUMENTED                                                                  \r',NULL,NULL,NULL),(9119,'0540F','GLUCORTICOID MANAGEMENT PLAN DOCUMENTED                                                             \r',NULL,NULL,NULL),(9120,'0545F','PLAN FOR FOLLOW-UP CARE FOR MDD DOCD                                                                \r',NULL,NULL,NULL),(9121,'0550F','CYTOPATH REPORT ON NONGYN SPECIMEN 2 WKNG DAYS                                                      \r',NULL,NULL,NULL),(9122,'0551F','CYTOPATH REPORT NONGYN SPCMN DOCD NON-ROUTINE                                                       \r',NULL,NULL,NULL),(9123,'0555F','SYMPTOM MANAGEMENT PLAN OF CARE DOCUMENTED                                                          \r',NULL,NULL,NULL),(9124,'0556F','PLAN OF CARE TO ACHIEVE LIPID CONTROL DOCUMENTED                                                    \r',NULL,NULL,NULL),(9125,'0557F','PLAN OF CARE TO MANAGE ANGINAL SYMPTOMS DOCD                                                        \r',NULL,NULL,NULL),(9126,'0575F','HIV RNA CONTROL PLAN OF CARE DOCD                                                                   \r',NULL,NULL,NULL),(9127,'0580F','MULTIDISCIPLINARY CARE PLAN DEVELOPED/UPDATED                                                       \r',NULL,NULL,NULL),(9128,'0581F','PT TRANSFERRED FROM ANESTHETIZING TO CC UNIT                                                        \r',NULL,NULL,NULL),(9129,'0582F','PT NOT TRANSFERRED FROM ANESTHETIZING TO CC UNIT                                                    \r',NULL,NULL,NULL),(9130,'0583F','TRANSFER OF CARE CHECKLIST USED                                                                     \r',NULL,NULL,NULL),(9131,'0584F','TRANSFER OF CARE CHECKLIST NOT USED                                                                 \r',NULL,NULL,NULL),(9132,'1000F','TOBACCO USE ASSESSED                                                                                \r',NULL,NULL,NULL),(9133,'1002F','ANGINAL SYMPTOMS & LEVEL ACTIVITY ASSESSED                                                          \r',NULL,NULL,NULL),(9134,'1003F','LEVEL ACTIVITY ASSESSED                                                                             \r',NULL,NULL,NULL),(9135,'1004F','CLINICAL SYMPTOMS VOL OVERLOAD ASSESSED                                                             \r',NULL,NULL,NULL),(9136,'1005F','ASTHMA SYMPTOMS EVALUATED                                                                           \r',NULL,NULL,NULL),(9137,'1006F','OSTEOARTHRITIS SYMPTOMS&FUNCJAL STATUS ASSES                                                        \r',NULL,NULL,NULL),(9138,'1007F','ANTI-INFLAMMATORY/ANALGESIC SYMPTOM RELIEF ASSES                                                    \r',NULL,NULL,NULL),(9139,'1008F','GI&RENAL PRESCRIBED/OTC NSAID RISK FACTORS ASS                                                      \r',NULL,NULL,NULL),(9140,'1010F','SEVERITY OF ANGINA ASSESSED BY LEVEL OF ACTIVITY                                                    \r',NULL,NULL,NULL),(9141,'1011F','ANGINA PRESENT                                                                                      \r',NULL,NULL,NULL),(9142,'1012F','ANGINA ABSENT                                                                                       \r',NULL,NULL,NULL),(9143,'1015F','COPD SYMPTOMS ASSESSED/TOOL COMPLETED                                                               \r',NULL,NULL,NULL),(9144,'1018F','DYSPNEA ASSESSED NOT PRESENT                                                                        \r',NULL,NULL,NULL),(9145,'1019F','DYSPNEA ASSESSED PRESENT                                                                            \r',NULL,NULL,NULL),(9146,'1022F','PNEUMOCOCCUS IMMUNIZATION STATUS ASSESSED                                                           \r',NULL,NULL,NULL),(9147,'1026F','CO-MORBID CONDITIONS ASSESSED                                                                       \r',NULL,NULL,NULL),(9148,'1030F','INFLUENZA IMMUNIZATION STATUS ASSESSED                                                              \r',NULL,NULL,NULL),(9149,'1031F','SMOKING & 2ND HAND SMOKE IN THE HOME ASSESSED                                                       \r',NULL,NULL,NULL),(9150,'1032F','CURRENT SMOKER/EXPOSED TO SECONDHAND SMOKE                                                          \r',NULL,NULL,NULL),(9151,'1033F','TOBACCO NON-SMOKER & NO 2NDHAND SMOKE EXPOSURE                                                      \r',NULL,NULL,NULL),(9152,'1034F','CURRENT TOBACCO SMOKER                                                                              \r',NULL,NULL,NULL),(9153,'1035F','CURRENT SMOKELESS TOBACCO USER                                                                      \r',NULL,NULL,NULL),(9154,'1036F','CURRENT TOBACCO NON-USER CAD CAP COPD PV DM                                                         \r',NULL,NULL,NULL),(9155,'1038F','PERSISTENT ASTHMA MILD MODERATE OR SEVERE ASTHMA                                                    \r',NULL,NULL,NULL),(9156,'1039F','INTERMITTENT ASTHMA                                                                                 \r',NULL,NULL,NULL),(9157,'1040F','DSM-5 CRITERIA MDD DOCD AT THE INITIAL EVAL                                                         \r',NULL,NULL,NULL),(9158,'1050F','HISTORY NEW OR CHANGING MOLES                                                                       \r',NULL,NULL,NULL),(9159,'1052F','TYPE ANATOMIC LOCATION AND ACTIVITY ALL ASSESSED                                                    \r',NULL,NULL,NULL),(9160,'1055F','VISUAL FUNCTIONAL STATUS ASSESSED                                                                   \r',NULL,NULL,NULL),(9161,'1060F','DOC PERM/PERSISTENT/PAROXYSMAL ATRIAL FIB                                                           \r',NULL,NULL,NULL),(9162,'1061F','DOC ABSENCE PERM+PERSISTENT+PAROXYSM ATRIAL FIB                                                     \r',NULL,NULL,NULL),(9163,'1065F','ISCHEMIC STROKE SYMP ONSET <3 HRS PRIOR ARRIVAL                                                     \r',NULL,NULL,NULL),(9164,'1066F','ISCHEMIC STROKE SYMP ONSET >/=3 HRS PRIOR ARRIVA                                                    \r',NULL,NULL,NULL),(9165,'1070F','ALARM SYMPTOMS ASSESSED NONE PRESENT                                                                \r',NULL,NULL,NULL),(9166,'1071F','ALARM SYMPTOMS ASSESSED 1/> PRESENT                                                                 \r',NULL,NULL,NULL),(9167,'1090F','PRESENCE/ABSENCE URINARY INCONTINENCE ASSESSED                                                      \r',NULL,NULL,NULL),(9168,'1091F','URINE INCONTINENCE CHARACTERIZED                                                                    \r',NULL,NULL,NULL),(9169,'1100F','PT FALLS ASSESS DOCD 2/> FALLS/FALL W/INJURY/YR                                                     \r',NULL,NULL,NULL),(9170,'1101F','PT FALLS ASSESS DOCD W/O FALL/INJURY PAST YEAR                                                      \r',NULL,NULL,NULL),(9171,'1110F','PT DISCHARGE INPT FACILITY WITHIN LAST 60 DAYS                                                      \r',NULL,NULL,NULL),(9172,'1111F','DISCHRG MEDS RECONCILED W/CURRENT MED LIST                                                          \r',NULL,NULL,NULL),(9173,'1116F','AURICULAR/PERIAURICULAR PAIN ASSESSED                                                               \r',NULL,NULL,NULL),(9174,'1118F','GERD SYMPTOMS ASSESSED AFTER 12 MONTHS THERAPY                                                      \r',NULL,NULL,NULL),(9175,'1119F','INITIAL EVALUATION FOR CONDITION                                                                    \r',NULL,NULL,NULL),(9176,'1121F','SUBSEQUENT EVALUATION CONDITION                                                                     \r',NULL,NULL,NULL),(9177,'1123F','ADV CARE PLN TLKD & ALT DCSN MAKER DOCD                                                             \r',NULL,NULL,NULL),(9178,'1124F','ADV CARE PLN/ NO ALT DCSN MKR DOCD OR REFUSAL                                                       \r',NULL,NULL,NULL),(9179,'1125F','PAIN SEVERITY QUANTIFIED PAIN PRESENT                                                               \r',NULL,NULL,NULL),(9180,'1126F','PAIN SEVERITY QUANTIFIED NO PAIN PRESENT                                                            \r',NULL,NULL,NULL),(9181,'1127F','NEW EPISODE FOR CONDITION                                                                           \r',NULL,NULL,NULL),(9182,'1128F','SUBS EPISODE FOR CONDITION                                                                          \r',NULL,NULL,NULL),(9183,'1130F','BK PAIN & FXN ASSESSED CERTAIN ASPECTS OF CARE                                                      \r',NULL,NULL,NULL),(9184,'1134F','EPISODE BACK PAIN LASTING SIX WEEKS/<                                                               \r',NULL,NULL,NULL),(9185,'1135F','EPISODE BACK PAIN LASTING >SIX WEEKS                                                                \r',NULL,NULL,NULL),(9186,'1136F','EPISODE BACK PAIN LASTING 12 WEEKS/<                                                                \r',NULL,NULL,NULL),(9187,'1137F','EPISODE BACK PAIN LASTING >12 WKS                                                                   \r',NULL,NULL,NULL),(9188,'1150F','DOC PT W/SUBSTANTIAL RISK DEATH WITHIN 1 YEAR                                                       \r',NULL,NULL,NULL),(9189,'1151F','DOC PT W/O SUBSTANTIAL RISK DEATH WITHIN 1 YEAR                                                     \r',NULL,NULL,NULL),(9190,'1152F','DOC ADVANCED DISEASE DX CARE GOALS COMFORT                                                          \r',NULL,NULL,NULL),(9191,'1153F','DOC ADVANCED DISEASE DX CARE GOALS W/O COMFORT                                                      \r',NULL,NULL,NULL),(9192,'1157F','ADVNC CARE PLAN OR EQV LGL DOC IN MED RCRD                                                          \r',NULL,NULL,NULL),(9193,'1158F','ADVNC CARE PLANNING TLK DOCD IN MED RCRD                                                            \r',NULL,NULL,NULL),(9194,'1159F','MEDICATION LIST DOCUMENTED IN MEDICAL RECORD                                                        \r',NULL,NULL,NULL),(9195,'1160F','RVW ALL MEDS BY RXNG PRCTIONR OR CLIN RPH DOCD                                                      \r',NULL,NULL,NULL),(9196,'1170F','FUNCTIONAL STATUS ASSESSED                                                                          \r',NULL,NULL,NULL),(9197,'1175F','FUNCTIONAL STATUS DEMENTIA ASSESS RESULTS RVWD                                                      \r',NULL,NULL,NULL),(9198,'1180F','THROMBOEMBOLIC RISK ASSESSED                                                                        \r',NULL,NULL,NULL),(9199,'1181F','NEUROPSYCHIATRIC SYMPTS ASSESSED RESULTS REVIEWD                                                    \r',NULL,NULL,NULL),(9200,'1182F','NEUROPSYCHIATRIC SYMPTOMS ONE OR MORE PRESENT                                                       \r',NULL,NULL,NULL),(9201,'1183F','NEUROPSYCHIATRIC SYMPTOMS ABSENT                                                                    \r',NULL,NULL,NULL),(9202,'1200F','SEIZURE TYPE FREQUENCY DOCUMENTED                                                                   \r',NULL,NULL,NULL),(9203,'1205F','ETIOLOGY OF EPILEPSY SYNDROME RVWD & DOCD                                                           \r',NULL,NULL,NULL),(9204,'1220F','PATIENT SCREENED DEPRESSION                                                                         \r',NULL,NULL,NULL),(9205,'1400F','PARKINSON DISEASE DIAGNOSIS REVIEWED                                                                \r',NULL,NULL,NULL),(9206,'1450F','SYMPTOMS IMPROVED/CONSIST W/TXMNT GOAL ASSESSMNT                                                    \r',NULL,NULL,NULL),(9207,'1451F','SYMPTOMS SHOW CLIN IMPRTNT DROP SINCE ASSESSMENT                                                    \r',NULL,NULL,NULL),(9208,'1460F','QUALIFYING CARD EVENT/DIAGNOSIS PRIOR 12 MONTHS                                                     \r',NULL,NULL,NULL),(9209,'1461F','NO QUAL CARD EVENT/DIAG IN PREVIOUS 12 MONTHS                                                       \r',NULL,NULL,NULL),(9210,'1490F','DEMENTIA SEVERITY CLASSIFIED MILD                                                                   \r',NULL,NULL,NULL),(9211,'1491F','DEMENTIA SEVERITY CLASSIFIED MODERATE                                                               \r',NULL,NULL,NULL),(9212,'1493F','DEMENTIA SEVERITY CLASSIFIED SEVERE                                                                 \r',NULL,NULL,NULL),(9213,'1494F','COGNITION ASSESSED AND REVIEWED                                                                     \r',NULL,NULL,NULL),(9214,'1500F','SYMP+SIGN DISTAL SYMM POLYNEUROPATHY REVWD+DOCD                                                     \r',NULL,NULL,NULL),(9215,'1501F','NOT INITIAL EVALUATION FOR CONDITION                                                                \r',NULL,NULL,NULL),(9216,'1502F','PT QUERIED RE PAIN W/FUNC USING RELIABLE INSTRM                                                     \r',NULL,NULL,NULL),(9217,'1503F','PT QUERIED RE SYMP RESPIRATORY INSUFFICIENCY                                                        \r',NULL,NULL,NULL),(9218,'1504F','PATIENT HAS RESPIRATORY INSUFFICIENCY                                                               \r',NULL,NULL,NULL),(9219,'1505F','PATIENT DOES NOT HAVE RESPIRATORY INSUFFICIENCY                                                     \r',NULL,NULL,NULL),(9220,'2000F','BLOOD PRESSURE MEASURED                                                                             \r',NULL,NULL,NULL),(9221,'2001F','WEIGHT RECORDED                                                                                     \r',NULL,NULL,NULL),(9222,'2002F','CLINICAL SIGNS VOLUME OVERLOAD ASSESSED                                                             \r',NULL,NULL,NULL),(9223,'2004F','INITIAL EXAMINATION INVOLVED JOINTS                                                                 \r',NULL,NULL,NULL),(9224,'2010F','VITAL SIGNS RECORDED                                                                                \r',NULL,NULL,NULL),(9225,'2014F','MENTAL STATUS ASSESSED                                                                              \r',NULL,NULL,NULL),(9226,'2015F','ASTHMA IMPAIRMENT ASSESSED                                                                          \r',NULL,NULL,NULL),(9227,'2016F','ASTHMA RISK ASSESSED                                                                                \r',NULL,NULL,NULL),(9228,'2018F','HYDRATION STATUS ASSESSED                                                                           \r',NULL,NULL,NULL),(9229,'2019F','DILATED MACULAR EXAM PERFORMED                                                                      \r',NULL,NULL,NULL),(9230,'2020F','DILATED FUNDUS EVALUATION PERFORMED                                                                 \r',NULL,NULL,NULL),(9231,'2021F','DILATED MACULAR OR FUNDUS EXAM PERFORMED                                                            \r',NULL,NULL,NULL),(9232,'2022F','DILAT RETINAL EYE EXAM W/INTERP OPHTHAL/OPTOM                                                       \r',NULL,NULL,NULL),(9233,'2024F','7 STANDARD FIELD STEREOSCOPIC PHOTOS W/INTERPJ                                                      \r',NULL,NULL,NULL),(9234,'2026F','EYE IMAGING VALIDATED MATCH PHOTOS DIAGNOSIS                                                        \r',NULL,NULL,NULL),(9235,'2027F','OPTIC NERVE HEAD EVALUATION PERFORMED                                                               \r',NULL,NULL,NULL),(9236,'2028F','FOOT EXAMINATION PERFORMED                                                                          \r',NULL,NULL,NULL),(9237,'2029F','COMPLETE PHYSICAL SKIN EXAM PERFORMED                                                               \r',NULL,NULL,NULL),(9238,'2030F','HYDRATION STATUS DOCD NORMALLY HYDRATED                                                             \r',NULL,NULL,NULL),(9239,'2031F','HYDRATION STATUS DOCUMENTED DEHYDRATED                                                              \r',NULL,NULL,NULL),(9240,'2035F','TYMPANIC MEMBRANE MOBILITY ASSESS                                                                   \r',NULL,NULL,NULL),(9241,'2040F','PHYS EXAM ON DATE OF INIT VST FOR LBP DONE                                                          \r',NULL,NULL,NULL),(9242,'2044F','DOC MNTL HLTH ASSES PRIOR INTVN BACK PAIN 6WKS                                                      \r',NULL,NULL,NULL),(9243,'2050F','WOUND CHARACTERISTICS DOCD PRIOR DEBRIDEMENT                                                        \r',NULL,NULL,NULL),(9244,'2060F','PT INTRVWD BY EVAL CLINICIAN </DATE DIAG MDD                                                        \r',NULL,NULL,NULL),(9245,'3006F','CHEST X-RAY RESULTS DOCUMENTED & REVIEWED                                                           \r',NULL,NULL,NULL),(9246,'3008F','BODY MASS INDEX DOCUMENTED                                                                          \r',NULL,NULL,NULL),(9247,'3011F','LIPID PANEL RESULTS DOCUMENTED & REVIEWED                                                           \r',NULL,NULL,NULL),(9248,'3014F','SCREENING MAMMOGRAPHY RESULTS DOC&REV                                                               \r',NULL,NULL,NULL),(9249,'3015F','CERVICAL CANCER SCREENING RESULTS DOCD & RVWD                                                       \r',NULL,NULL,NULL),(9250,'3016F','PT SCRND UNHLTHY OH USE BY SYSTMTC SCRNG METHD                                                      \r',NULL,NULL,NULL),(9251,'3017F','COLORECTAL CANCER SCREENING RESULTS DOC&REV                                                         \r',NULL,NULL,NULL),(9252,'3018F','PRE-PRX RISK ASSESS DEPTH&QUAL BOWEL PREP&                                                          \r',NULL,NULL,NULL),(9253,'3019F','LVEF ASSESSMENT PLANNED POST DISCHARGE                                                              \r',NULL,NULL,NULL),(9254,'3020F','LEFT VENTRICULAR FUNCTION ASSESSMENT DOCUMENTED                                                     \r',NULL,NULL,NULL),(9255,'3021F','LEFT VENTRICULAR EJECTION FRACTION <40%                                                             \r',NULL,NULL,NULL),(9256,'3022F','LEFT VENTRICULAR EJECTION FRACTION >/EQUAL 40%                                                      \r',NULL,NULL,NULL),(9257,'3023F','SPIROMETRY RESULTS DOCUMENTED AND REVIEWED                                                          \r',NULL,NULL,NULL),(9258,'3025F','SPIROMETRY TEST RESULTS FEV/FVC <70% W/COPD                                                         \r',NULL,NULL,NULL),(9259,'3027F','SPIROMETRY TEST RESULTS FEV/FVC >/=70% W/O COPD                                                     \r',NULL,NULL,NULL),(9260,'3028F','OXYGEN SATURATION RESULTS DOCUMENTED & REVIEWE                                                      \r',NULL,NULL,NULL),(9261,'3035F','OXYGEN SATUR </EQUAL 88%/PAO2 </EQUAL 55 MM                                                         \r',NULL,NULL,NULL),(9262,'3037F','OXYGEN SATURATION >88%/PAO2 >55 MM HG                                                               \r',NULL,NULL,NULL),(9263,'3038F','PULMONARY FUNC TEST WITHIN 12 MON PRIOR SURG                                                        \r',NULL,NULL,NULL),(9264,'3040F','FUNCTIONAL EXPIRATORY VOLUME < 40%                                                                  \r',NULL,NULL,NULL),(9265,'3042F','FUNCTJL EXPIR VOLUME >/EQUAL 40% PREDICTED VALUE                                                    \r',NULL,NULL,NULL),(9266,'3044F','MOST RECENT HEMOGLOBIN A1C LEVEL < 7.0%                                                             \r',NULL,NULL,NULL),(9267,'3045F','MOST RECENT HEMOGLOBIN A1C LEVEL GT 7.0-9.0 %                                                       \r',NULL,NULL,NULL),(9268,'3046F','MOST RECENT HEMOGLOBIN A1C LEVEL >9.0%                                                              \r',NULL,NULL,NULL),(9269,'3048F','MOST RECENT LDL-C <100 MG/DL                                                                        \r',NULL,NULL,NULL),(9270,'3049F','MOST RECENT LDL-C 100-129 MG/DL                                                                     \r',NULL,NULL,NULL),(9271,'3050F','MOST RECENT LDL-C >/EQUAL 130 MG/DL                                                                 \r',NULL,NULL,NULL),(9272,'3055F','LVEF LESS THAN OR EQUAL TO 35%                                                                      \r',NULL,NULL,NULL),(9273,'3056F','LVEF GREATER THAN 35%                                                                               \r',NULL,NULL,NULL),(9274,'3060F','POSITIVE MICROALBUMINURIA TEST RESULT DOC&REV                                                       \r',NULL,NULL,NULL),(9275,'3061F','NEGATIVE MICROALBUMINURIA TEST RESULT DOC&REV                                                       \r',NULL,NULL,NULL),(9276,'3062F','POSITIVE MACROALBUMINURIA TEST RESULT DOC&REV                                                       \r',NULL,NULL,NULL),(9277,'3066F','DOCUMENTATION OF TREATMENT FOR NEPHROPATHY                                                          \r',NULL,NULL,NULL),(9278,'3072F','LOW RISK FOR RETINOPATHY                                                                            \r',NULL,NULL,NULL),(9279,'3073F','DOCUMENTED LENGTH CORNEAL POWER & LENS POWER                                                        \r',NULL,NULL,NULL),(9280,'3074F','MOST RECENT SYSTOLIC BLOOD PRESSURE <130 MM HG                                                      \r',NULL,NULL,NULL),(9281,'3075F','MOST RECENT SYSTOLIC BLOOD PRESS 130-139MM HG                                                       \r',NULL,NULL,NULL),(9282,'3077F','MOST RECENT SYSTOLIC BLOOD PRES>/EQUAL 140 MM HG                                                    \r',NULL,NULL,NULL),(9283,'3078F','MOST RECENT DIASTOLIC BLOOD PRESSURE < 80 MM HG                                                     \r',NULL,NULL,NULL),(9284,'3079F','MOST RECENT DIASTOLIC BLOOD PRESSURE 80-89 MM HG                                                    \r',NULL,NULL,NULL),(9285,'3080F','MOST RECENT DIASTOL BLOOD PRES >/EQUAL 90 MM HG                                                     \r',NULL,NULL,NULL),(9286,'3082F','KT/V <1.2 (CLEARANCE OF UREA (KT)/VOLUME (V))                                                       \r',NULL,NULL,NULL),(9287,'3083F','KT/V EQUAL/>1.2 & <1.7                                                                              \r',NULL,NULL,NULL),(9288,'3084F','KT/V /EQUAL 1.7                                                                                     \r',NULL,NULL,NULL),(9289,'3085F','SUICIDE RISK ASSESSED                                                                               \r',NULL,NULL,NULL),(9290,'3088F','MAJOR DEPRESSIVE DISORDER MILD                                                                      \r',NULL,NULL,NULL),(9291,'3089F','MAJOR DEPRESSIVE DISORDER MODERATE                                                                  \r',NULL,NULL,NULL),(9292,'3090F','MDD SEVERE WITHOUT PSYCHOTIC FEATURES                                                               \r',NULL,NULL,NULL),(9293,'3091F','MAJOR DESPRESV DISORDER SEVERE W/PSYCHOT FEATURE                                                    \r',NULL,NULL,NULL),(9294,'3092F','MAJOR DEPRESSIVE DISORDER REMISSION                                                                 \r',NULL,NULL,NULL),(9295,'3093F','DOC NEW DIAG DX INIT/RECURRENT EPISODE OF MDD                                                       \r',NULL,NULL,NULL),(9296,'3095F','CENTRAL DUAL ENERGY ABSORPTIOMETRY DOCD                                                             \r',NULL,NULL,NULL),(9297,'3096F','CENTRAL DUAL ENERGY ABSORPTIOMETRY ORDERED                                                          \r',NULL,NULL,NULL),(9298,'3100F','CAROTID IMAGNG REPORT DIR/INDIR MEAS VESSEL DIAM                                                    \r',NULL,NULL,NULL),(9299,'3110F','CT/MRI HMRHG/MASS LESION/ACUTE INFRC DOC                                                            \r',NULL,NULL,NULL),(9300,'3111F','CT OR MRI BRAIN DONE W/IN 24 HRS HOSP ARRIVAL                                                       \r',NULL,NULL,NULL),(9301,'3112F','CT/MRI BRAIN DONE 24 HRS AFTER HOSP ARRIVAL                                                         \r',NULL,NULL,NULL),(9302,'3115F','QUANT RESULTS EVAL CURR LEVEL ACTIVITY CLIN SYMP                                                    \r',NULL,NULL,NULL),(9303,'3117F','HF DISEASE SPECIFIC ASSESSMENT TOOL COMPLETED                                                       \r',NULL,NULL,NULL),(9304,'3118F','NEW YORK HEART ASSOCIATION (NYHA) CLASS DOCD                                                        \r',NULL,NULL,NULL),(9305,'3119F','NO EVAL LEVEL OF ACTIVITY OR CLINICAL SYMPTOMS                                                      \r',NULL,NULL,NULL),(9306,'3120F','12-LEAD ECG PERFORMED                                                                               \r',NULL,NULL,NULL),(9307,'3125F','ESOPH BX RPRT W/DYSPL INFO (PRSNT/ABSNT/UNKNWN)                                                     \r',NULL,NULL,NULL),(9308,'3130F','UPPER GI ENDOSCOPY PERFORMED                                                                        \r',NULL,NULL,NULL),(9309,'3132F','DOC REFERAL FOR UPPER GI ENDOSCOPY                                                                  \r',NULL,NULL,NULL),(9310,'3140F','UPPER GI ENDO REPORT SHOWS POSS BARRETT\'S ESOPH                                                     \r',NULL,NULL,NULL),(9311,'3141F','UPPER GI ENDO REPORT SHOW NO SUSPECT BARRETT\'S                                                      \r',NULL,NULL,NULL),(9312,'3142F','BARIUM SWALLOW TEST ORDERED                                                                         \r',NULL,NULL,NULL),(9313,'3150F','FORCEPS ESOPHAGEAL BIOPSY PERFORMED                                                                 \r',NULL,NULL,NULL),(9314,'3155F','CYTOGEN TEST DONE MARROW DIAG OR PRIOR TXMNT                                                        \r',NULL,NULL,NULL),(9315,'3160F','DOC IRON STORES PRIOR START EPO THERAPY                                                             \r',NULL,NULL,NULL),(9316,'3170F','FLOW CYTOMETRY W/DIAG/PRIOR INITIATING TREATMENT                                                    \r',NULL,NULL,NULL),(9317,'3200F','BARIUM SWALLOW TEST NOT ORDERED                                                                     \r',NULL,NULL,NULL),(9318,'3210F','GROUP A STREP TEST PERFORMED                                                                        \r',NULL,NULL,NULL),(9319,'3215F','DOCUMENTED IMMUNITY HEPATITIS A                                                                     \r',NULL,NULL,NULL),(9320,'3216F','DOCUMENTED IMMUNITY HEPATITIS B                                                                     \r',NULL,NULL,NULL),(9321,'3218F','HEP C RNA TEST 6 MOS BEFORE ANTIVIRAL TX                                                            \r',NULL,NULL,NULL),(9322,'3220F','HEP C QUANT RNA TEST 12 WKS AFTER ANTIVIRAL TX                                                      \r',NULL,NULL,NULL),(9323,'3230F','HEARING TEST 6 MOS PRIOR TO EAR TUBE INSERTION                                                      \r',NULL,NULL,NULL),(9324,'3250F','NONPRIM ANATOMIC LOCATION OF SPECIMEN SITE                                                          \r',NULL,NULL,NULL),(9325,'3260F','TUMOR/NODES/HISTO GRADE DOCUMENTED                                                                  \r',NULL,NULL,NULL),(9326,'3265F','RNA TESTING FOR HEP C VIREMIA ORDERED/DOCD                                                          \r',NULL,NULL,NULL),(9327,'3266F','HEPATITIS C GENOTYPE PRIOR ANTIVIRAL TREATMENT                                                      \r',NULL,NULL,NULL),(9328,'3267F','PATH RPRT INCLUDES PT & PN CAT GLEASON                                                              \r',NULL,NULL,NULL),(9329,'3268F','PSA & TUMOR STAGE&GLEASON SCORE PRIOR INIT                                                          \r',NULL,NULL,NULL),(9330,'3269F','BONE SCAN PRIOR INITIAT TX/DX PROSTATE CANCER                                                       \r',NULL,NULL,NULL),(9331,'3270F','BONE SCAN NOT PRIOR INITIAT TX/DX PROSTATE CA                                                       \r',NULL,NULL,NULL),(9332,'3271F','LOW RISK OF RECURRENCE PROSTATE CANCER                                                              \r',NULL,NULL,NULL),(9333,'3272F','INTERMED RISK OF RECURRENCE PROSTATE CANCER                                                         \r',NULL,NULL,NULL),(9334,'3273F','HIGH RISK OF RECURRENCE PROSTATE CANCER                                                             \r',NULL,NULL,NULL),(9335,'3274F','PROST CANCER RSK RECUR NOT DETER/LOW/INTERMED/HI                                                    \r',NULL,NULL,NULL),(9336,'3278F','SERUM LEVELS CALCUM PHOSPH PARATHYR & LIPID PR                                                      \r',NULL,NULL,NULL),(9337,'3279F','HEMOGLOBIN LEVEL>/EQUAL 13 G/DL                                                                     \r',NULL,NULL,NULL),(9338,'3280F','HEMOGLOBIN LEVEL 11 G/DL-12.9 G/DL                                                                  \r',NULL,NULL,NULL),(9339,'3281F','HEMOGLOBIN LEVEL <11 G/DL                                                                           \r',NULL,NULL,NULL),(9340,'3284F','INTRAOCULAR PRESS REDUCED >/EQUAL 15%                                                               \r',NULL,NULL,NULL),(9341,'3285F','IOP REDUCED <15% PRE-INTERVENTION LEVEL                                                             \r',NULL,NULL,NULL),(9342,'3288F','FALLS RISK ASSESSMENT DOCUMENTED                                                                    \r',NULL,NULL,NULL),(9343,'3290F','PATIENT IS D (RH) NEGATIVE AND UNSENSITIZED                                                         \r',NULL,NULL,NULL),(9344,'3291F','PATIENT IS D (RH) POSITIVE OR SENSITIZED                                                            \r',NULL,NULL,NULL),(9345,'3292F','HIV TSTNG ASK/DOCD/RVWD AT 1ST/2ND PRENATAL VST                                                     \r',NULL,NULL,NULL),(9346,'3293F','ABO AND RH BLOOD TYPING DOCUMENTED AS PERFORMED                                                     \r',NULL,NULL,NULL),(9347,'3294F','GBS SCRNING DOCD DONE DURING WK 35-37 GESTATION                                                     \r',NULL,NULL,NULL),(9348,'3300F','AJCC STAGE DOCUMENTED & REVIEWED                                                                    \r',NULL,NULL,NULL),(9349,'3301F','CANCER STAGE DOCD METASTATIC & REVIEWED                                                             \r',NULL,NULL,NULL),(9350,'3315F','ESTROGEN/PROGEST RECEPTOR POSITIVE BREAST CANCER                                                    \r',NULL,NULL,NULL),(9351,'3316F','ESTROGEN/PROGEST RECEPTOR NEGATIVE BREAST CANCER                                                    \r',NULL,NULL,NULL),(9352,'3317F','PATH REPRT MALIGNANCY DOCD & RVWD INITIATE CHE                                                      \r',NULL,NULL,NULL),(9353,'3318F','PATH REPRT MALIGNANCY DOCD & RVWD INITIA RAD                                                        \r',NULL,NULL,NULL),(9354,'3319F','1 DX IMG ORDER CHEST XRAY CT US MRI PET/NUC MED                                                     \r',NULL,NULL,NULL),(9355,'3320F','0 DX IMG ORDER CHEST XRAY CT US MRI PET/NUC MED                                                     \r',NULL,NULL,NULL),(9356,'3321F','AJCC CANCER STAGE 0 OR IA MELANOMA                                                                  \r',NULL,NULL,NULL),(9357,'3322F','MELANOMA THAN AJCC STAGE 0                                                                          \r',NULL,NULL,NULL),(9358,'3323F','CLIN TUMOR NODE METASTASES STAGE DOCD PRIOR SURG                                                    \r',NULL,NULL,NULL),(9359,'3324F','MRI CT SCAN ORDERED REVIEWED/REQUESTED                                                              \r',NULL,NULL,NULL),(9360,'3325F','PREOP ASSES 12 MOS PRIOR CATARACT SURG W/IO LENS                                                    \r',NULL,NULL,NULL),(9361,'3328F','PERFORMANCE STATUS DOCD RVWD 2 WKS PRIOR SURG                                                       \r',NULL,NULL,NULL),(9362,'3330F','IMAGING STUDY ORDERED                                                                               \r',NULL,NULL,NULL),(9363,'3331F','IMAGING STUDY NOT ORDERED                                                                           \r',NULL,NULL,NULL),(9364,'3340F','MAMMO ASSESSMENT CAT INCOMP ADDTNL IMAGE DOCD                                                       \r',NULL,NULL,NULL),(9365,'3341F','MAMMO ASSESSMENT CAT NEGATIVE DOCD                                                                  \r',NULL,NULL,NULL),(9366,'3342F','MAMMO ASSESSMENT CAT BENIGN DOCD                                                                    \r',NULL,NULL,NULL),(9367,'3343F','MAMMO ASSESSMENT CAT PROB BENIGN DOCD                                                               \r',NULL,NULL,NULL),(9368,'3344F','MAMMO ASSESSMENT CAT SUSPICIOUS DOCD                                                                \r',NULL,NULL,NULL),(9369,'3345F','MAMMO ASSESSMENT CAT HIGH CHANCE MALIG DOCD                                                         \r',NULL,NULL,NULL),(9370,'3350F','MAMMO ASSESSMENT CAT BIOPSY PROVEN MALIG DOCD                                                       \r',NULL,NULL,NULL),(9371,'3351F','NEG DEP SYMP CAT USING STAND DEP ASSESS TOOL                                                        \r',NULL,NULL,NULL),(9372,'3352F','NO SIGNIF DEP SYMP CAT BY STAND DEP ASSESS TOOL                                                     \r',NULL,NULL,NULL),(9373,'3353F','MILD TO MOD DEP SYMP BY STAND DEP ASSESS TOOL                                                       \r',NULL,NULL,NULL),(9374,'3354F','CLIN SIGN DEP SYMP BY STAND DEP ASSESS TOOL                                                         \r',NULL,NULL,NULL),(9375,'3370F','AJCC BREAST CANCER STAGE 0 DOCUMENTED                                                               \r',NULL,NULL,NULL),(9376,'3372F','AJCC BREAST CANCER STAGE I T1MIC T1A/T1B                                                            \r',NULL,NULL,NULL),(9377,'3374F','AJCC BREAST CANCER STAGE I T1C                                                                      \r',NULL,NULL,NULL),(9378,'3376F','AJCC BREAST CANCER STAGE II                                                                         \r',NULL,NULL,NULL),(9379,'3378F','AJCC BREAST CANCER STAGE III                                                                        \r',NULL,NULL,NULL),(9380,'3380F','AJCC BREAST CANCER STAGE IV                                                                         \r',NULL,NULL,NULL),(9381,'3382F','AJCC COLON CANCER STAGE 0                                                                           \r',NULL,NULL,NULL),(9382,'3384F','AJCC COLON CANCER STAGE I                                                                           \r',NULL,NULL,NULL),(9383,'3386F','AJCC COLON CANCER STAGE II                                                                          \r',NULL,NULL,NULL),(9384,'3388F','AJCC COLON CANCER STAGE III DOCD                                                                    \r',NULL,NULL,NULL),(9385,'3390F','AJCC COLON CANCER STAGE IV DOCD                                                                     \r',NULL,NULL,NULL),(9386,'3394F','QUANT HER2 IHC EVAL OF BRST CANCER ASCO/CAP                                                         \r',NULL,NULL,NULL),(9387,'3395F','QUANT NON-HER2 IHC EVAL OF BRST CANCER PERFORMED                                                    \r',NULL,NULL,NULL),(9388,'3450F','DYSPNEA SCRND NO-MILD DYSPNEA                                                                       \r',NULL,NULL,NULL),(9389,'3451F','DYSPNEA SCRND MOD-SEVERE DYSPNEA                                                                    \r',NULL,NULL,NULL),(9390,'3452F','DYSPNEA NOT SCREENED                                                                                \r',NULL,NULL,NULL),(9391,'3455F','TB SCRNG DONE INTRPD </6 MOS START RA THXPY                                                         \r',NULL,NULL,NULL),(9392,'3470F','RHEUMATOID ARTHRITIS (RA) DISEASE ACTIVITY LOW                                                      \r',NULL,NULL,NULL),(9393,'3471F','RHEUMATOID ARTHRITIS (RA) DISEASE ACTIVITY MOD                                                      \r',NULL,NULL,NULL),(9394,'3472F','RHEUMATOID ARTHRITIS (RA) DISEASE ACTIVITY HIGH                                                     \r',NULL,NULL,NULL),(9395,'3475F','DISEASE PROGNOSIS RA ASSESSED POOR PROG DOCD                                                        \r',NULL,NULL,NULL),(9396,'3476F','DISEASE PROGNOSIS RA ASSESSED GOOD PROG DOCD                                                        \r',NULL,NULL,NULL),(9397,'3490F','HISTORY OF AIDS-DEFINING CONDITION                                                                  \r',NULL,NULL,NULL),(9398,'3491F','HIV INDETERMINATE INFANTS BORN OF HIV MOTHERS                                                       \r',NULL,NULL,NULL),(9399,'3492F','HISTORY OF NADIR CD4+ CELL COUNT <350 CELLS/MM3                                                     \r',NULL,NULL,NULL),(9400,'3493F','NO HIST NADIR CD4+ CELL CNT <350 OR AIDS-INDICA                                                     \r',NULL,NULL,NULL),(9401,'3494F','CD4+ CELL COUNT <200 CELLS/MM                                                                       \r',NULL,NULL,NULL),(9402,'3495F','CD4+ CELL COUNT 200 - 499 CELLS/MM (HIV)                                                            \r',NULL,NULL,NULL),(9403,'3496F','CD4+ CELL COUNT /EQUAL 500 CELLS/MM                                                                 \r',NULL,NULL,NULL),(9404,'3497F','CD4+ CELL PERCENTAGE <15% HIV                                                                       \r',NULL,NULL,NULL),(9405,'3498F','CD4+ CELL PERCENTAGE /EQUAL 5% HIV                                                                  \r',NULL,NULL,NULL),(9406,'3500F','CD4+CELL CNT/CD4+CELL % DOCD AS DONE                                                                \r',NULL,NULL,NULL),(9407,'3502F','HIV RNA VIRAL LOAD <LIMITS OF QUANTIF                                                               \r',NULL,NULL,NULL),(9408,'3503F','HIV RNA VIRAL LOAD NOT <LIMITS OF QUANTIF                                                           \r',NULL,NULL,NULL),(9409,'3510F','DOCJ TB SCREEN PERFORMED & RESULTS INTERPRET                                                        \r',NULL,NULL,NULL),(9410,'3511F','CHLAMYDIA/GONORRHEA TSTS DOCD AS DONE                                                               \r',NULL,NULL,NULL),(9411,'3512F','SYPHILIS SCREENING DOCUMENTED AS DONE                                                               \r',NULL,NULL,NULL),(9412,'3513F','HEPATITIS B SCREENING DOCUMENTED AS PERFORMED                                                       \r',NULL,NULL,NULL),(9413,'3514F','HEPATITIS C SCREENING DOCUMENTED AS PERFORMED                                                       \r',NULL,NULL,NULL),(9414,'3515F','PATIENT HAS DOCUMENTED IMMUNITY TO HEPATITIS C                                                      \r',NULL,NULL,NULL),(9415,'3517F','HBV STATUS ASSESSED W/ RESULTS IN 1 YR                                                              \r',NULL,NULL,NULL),(9416,'3520F','CLOSTRIDIUM DIFFICILE TESTING PERFORMED                                                             \r',NULL,NULL,NULL),(9417,'3550F','LOW RISK FOR THROMBOEMBOLISM                                                                        \r',NULL,NULL,NULL),(9418,'3551F','INTERMEDIATE RISK FOR THROMBOEMBOLISM                                                               \r',NULL,NULL,NULL),(9419,'3552F','HIGH RISK FOR THROMBOEMBOLISM                                                                       \r',NULL,NULL,NULL),(9420,'3555F','PT HAD INR MEASUREMENT PERFORMED                                                                    \r',NULL,NULL,NULL),(9421,'3570F','REPORT BONE SCINTIGRAPHY W/X-RAY SAME REGION                                                        \r',NULL,NULL,NULL),(9422,'3572F','PT POTENTIAL RISK FRACTURE WEIGHT-BEARING SITE                                                      \r',NULL,NULL,NULL),(9423,'3573F','PT NOT POTENT RISK FRACTURE WEIGHT-BEARING SITE                                                     \r',NULL,NULL,NULL),(9424,'3650F','ELECTROENCEPHALOGRAM ORDERED RVWD OR REQ                                                            \r',NULL,NULL,NULL),(9425,'3700F','PSYCHIATRIC DISORDERS/DISTURBANCES ASSESSED                                                         \r',NULL,NULL,NULL),(9426,'3720F','COGNITIVE IMPAIRMENT/DYSFUNCTION ASSESSED                                                           \r',NULL,NULL,NULL),(9427,'3725F','SCREENING FOR DEPRESSION PERFORMED                                                                  \r',NULL,NULL,NULL),(9428,'3750F','PT NOT RCVNG CORTICOSTERIDS>/=10MG/DAY 60/> DAYS                                                    \r',NULL,NULL,NULL),(9429,'3751F','ELECTRODIAG STUDIES DSP DOCD RVWD W/IN 6 MONTHS                                                     \r',NULL,NULL,NULL),(9430,'3752F','ELECTRODIAG STUDIES DSP NOT DOCD RVWD W/IN 6 MON                                                    \r',NULL,NULL,NULL),(9431,'3753F','PT HAS CLINICAL SYMP+SIGNS NEUROPATHY W/CAUSE                                                       \r',NULL,NULL,NULL),(9432,'3754F','SCREENING TSTS DIABETES MELLITUS RVWD RQSTD ORD                                                     \r',NULL,NULL,NULL),(9433,'3755F','COGNITIVE+BEHAVIORAL IMPAIRMENT SCRNG PERFORMED                                                     \r',NULL,NULL,NULL),(9434,'3756F','PT HAS PSEUDOBULBAR AFFECT/SIALORRHEA/ALS SYMP                                                      \r',NULL,NULL,NULL),(9435,'3757F','NO PSEUDOBULBAR AFFECT/SIALORRHEA/ALS SYMP                                                          \r',NULL,NULL,NULL),(9436,'3758F','PULM FUNC TESTING/PEAK COUGH EXPIRATORY FLOW                                                        \r',NULL,NULL,NULL),(9437,'3759F','PT SCRND DYSPHAGIA+WT LOSS+IMPAIRED NUTRITION                                                       \r',NULL,NULL,NULL),(9438,'3760F','DYSPHAG+WT LOSS+IMPAIRED NUTRITION                                                                  \r',NULL,NULL,NULL),(9439,'3761F','NO DYSPHAG+WT LOSS+IMPAIRED NUTRITION                                                               \r',NULL,NULL,NULL),(9440,'3762F','PATIENT IS DYSARTHRIC                                                                               \r',NULL,NULL,NULL),(9441,'3763F','PATIENT IS NOT DYSARTHRIC                                                                           \r',NULL,NULL,NULL),(9442,'4000F','TOBACCO USE CESSATION IVNTJ COUNSELING                                                              \r',NULL,NULL,NULL),(9443,'4001F','TOBACCO USE CESSATION IVNTJ PHARMACOLOGIC THER                                                      \r',NULL,NULL,NULL),(9444,'4003F','PT EDUCATION WRTTN/ORAL HRT FAILURE PTS PFRMD                                                       \r',NULL,NULL,NULL),(9445,'4004F','PT SCRND TOBACCO USE RCVD TOBACCO CESSATION TALK                                                    \r',NULL,NULL,NULL),(9446,'4005F','PHARMACOLOGIC OSTEOPOROSIS THERAPY PRESCRIBED                                                       \r',NULL,NULL,NULL),(9447,'4008F','BETA BLOCKER THERAPY RXD/CURRENTLY BEING TAKEN                                                      \r',NULL,NULL,NULL),(9448,'4010F','ACE INHIBITOR/ARB THERAPY RXD/CURRENTLY TAKEN                                                       \r',NULL,NULL,NULL),(9449,'4011F','ORAL ANTIPLATELET THERAPY PRESCRIBED                                                                \r',NULL,NULL,NULL),(9450,'4012F','WARFARIN THERAPY PRESCRIBED                                                                         \r',NULL,NULL,NULL),(9451,'4013F','STATIN THERAPY RXD/CURRENTLY TAKEN                                                                  \r',NULL,NULL,NULL),(9452,'4014F','DSCHRG INSTRUCTIONS HRT FAILURE XCP PTS 18 YR                                                       \r',NULL,NULL,NULL),(9453,'4015F','PRSISTENT ASTHMA LONG TERM CTRL MED PRESCRIBED                                                      \r',NULL,NULL,NULL),(9454,'4016F','ANTI-INFLAMMATORY/ANALGESIC AGT PRESCRIBED                                                          \r',NULL,NULL,NULL),(9455,'4017F','GI PROPHYLAXIS NSAID USE PRESCRIBED                                                                 \r',NULL,NULL,NULL),(9456,'4018F','THERAPEUTIC EXERCISE INVOLVED JTS INST/PRESCRIBE                                                    \r',NULL,NULL,NULL),(9457,'4019F','DOCUMENT COUNSELING EXERCISE CALCIUM & VITAMIN                                                      \r',NULL,NULL,NULL),(9458,'4025F','INHALED BRONCHODILATOR PRESCRIBED                                                                   \r',NULL,NULL,NULL),(9459,'4030F','LONG-TERM OXYGEN THERAPY PRESCRIBED                                                                 \r',NULL,NULL,NULL),(9460,'4033F','PULMONARY REHABILITATION RECOMMENDED                                                                \r',NULL,NULL,NULL),(9461,'4035F','INFLUENZA IMMUNIZATION RECOMMENDED                                                                  \r',NULL,NULL,NULL),(9462,'4037F','INFLUENZA IMMUNIZATION ORDERED OR ADMINISTERED                                                      \r',NULL,NULL,NULL),(9463,'4040F','PNEUMOCOCCAL VACCINE ADMIN RCVD PRIOR                                                               \r',NULL,NULL,NULL),(9464,'4041F','DOC ORDER CEFAZOLIN/CEFUROXIME ANTIMICRB PROPHYL                                                    \r',NULL,NULL,NULL),(9465,'4042F','DOC PROPHY ANTIBIO NOT GIVEN W/IN 4 HR PRIOR SUR                                                    \r',NULL,NULL,NULL),(9466,'4043F','DOC ORDER DISCONT ANTIBIO W/IN 48 HOURS OF SURG                                                     \r',NULL,NULL,NULL),(9467,'4044F','DOC ORDER VTE PROPHYL W/IN 24 HRS PRIOR SURG                                                        \r',NULL,NULL,NULL),(9468,'4045F','APPROPRIATE EMPIRIC ANTIBIOTIC PRESCRIBED                                                           \r',NULL,NULL,NULL),(9469,'4046F','DOCD ANTIBIO W/IN 4 HRS PRIOR/INTRAOP SURG INCIS                                                    \r',NULL,NULL,NULL),(9470,'4047F','DOC ORDER ANTIBIO GIVEN W/IN 1 HR PRIOR SURG/INC                                                    \r',NULL,NULL,NULL),(9471,'4048F','DOC ANTIBIO GIVEN W/IN 1 HR PRIOR SURG/INCIS                                                        \r',NULL,NULL,NULL),(9472,'4049F','DOC ORDER GIVEN TO STOP ANTIBIO W/IN 24 HRS SURG                                                    \r',NULL,NULL,NULL),(9473,'4050F','HYPERTENSION PLAN OF CARE DOCUMENTED                                                                \r',NULL,NULL,NULL),(9474,'4051F','REFERRED FOR AN ARTERIO-VENOUS (AV) FISTULA                                                         \r',NULL,NULL,NULL),(9475,'4052F','HEMODIAL VIA FUNCTIONG AV FISTULA                                                                   \r',NULL,NULL,NULL),(9476,'4053F','HEMODIALYSIS VIA FUNCTIONING AVGRAFT                                                                \r',NULL,NULL,NULL),(9477,'4054F','HEMODIALYSIS VIA CATHETER                                                                           \r',NULL,NULL,NULL),(9478,'4055F','PATIENT RECEIVING PERITONEAL DIALYSIS                                                               \r',NULL,NULL,NULL),(9479,'4056F','APPROPRIATE ORAL REHYD SOLUTION RECOMMENDED                                                         \r',NULL,NULL,NULL),(9480,'4058F','PAG PROVIDED TO CAREGIVER                                                                           \r',NULL,NULL,NULL),(9481,'4060F','PSYCHOTHERAPY SERVICES PROVIDED                                                                     \r',NULL,NULL,NULL),(9482,'4062F','PATIENT REFERRAL FOR PSYCHOTHERAPY DOCUMENTED                                                       \r',NULL,NULL,NULL),(9483,'4063F','ANTIDEPRESSANT RXTHXY CONSIDER & NOT PRESCRIBE                                                      \r',NULL,NULL,NULL),(9484,'4064F','ANTIDEPRESSANT PHARMACOTHERAPY PRESCRIBED                                                           \r',NULL,NULL,NULL),(9485,'4065F','ANTIPSYCHOTIC PHARMACOTHERAPY PRESCRIBED                                                            \r',NULL,NULL,NULL),(9486,'4066F','ELECTROCONVULSIVE THERAPY (ECT) PROVIDED                                                            \r',NULL,NULL,NULL),(9487,'4067F','PT REFERRAL ELECTROCONVULSIVE THXPY (ECT) DOCD                                                      \r',NULL,NULL,NULL),(9488,'4069F','VENOUS THROMBOEMBOLISM (VTE) PROPHYLAXIS RCVD                                                       \r',NULL,NULL,NULL),(9489,'4070F','DEEP VEIN THROMB PROPHYL RECVD BY HOSP DAY 2                                                        \r',NULL,NULL,NULL),(9490,'4073F','ORAL ANTIPLATELET THERAPY PRESCRBED AT DISCHARGE                                                    \r',NULL,NULL,NULL),(9491,'4075F','ANTICOAGULANT THERAPY PRESCRIBED AT DISCHARGE                                                       \r',NULL,NULL,NULL),(9492,'4077F','DOC T-PA ADMINISTRATION WAS CONSIDERED                                                              \r',NULL,NULL,NULL),(9493,'4079F','DOC REHAB SERVICES WERE CONSIDERED                                                                  \r',NULL,NULL,NULL),(9494,'4084F','ASPIRIN RECVD W/IN 24 HRS PRIOR ED ARRIVAL/STAY                                                     \r',NULL,NULL,NULL),(9495,'4086F','ASPIRIN OR CLOPIDOGREL PRESCRIBED                                                                   \r',NULL,NULL,NULL),(9496,'4090F','PATIENT RECEIVING ERYTHROPOIETIN THERAPY                                                            \r',NULL,NULL,NULL),(9497,'4095F','PATIENT NOT RECEIVING ERYTHORPOIETIN THERAPY                                                        \r',NULL,NULL,NULL),(9498,'4100F','BISPHOS THXPY VENOUS ORDERED OR RECEIVED                                                            \r',NULL,NULL,NULL),(9499,'4110F','LIMA GRAFT USED IN 1ST ISOLATED CABG PXD                                                            \r',NULL,NULL,NULL),(9500,'4115F','BETA BLOCKER GIVEN W/IN 24 HRS PRIOR SURG INC                                                       \r',NULL,NULL,NULL),(9501,'4120F','ANTIBIOTIC PRESCRIBED OR DISPENSED                                                                  \r',NULL,NULL,NULL),(9502,'4124F','ANTIBIOTIC NEITHER PRESCRIBED NOR DISPENSED                                                         \r',NULL,NULL,NULL),(9503,'4130F','ACUTE OTITIS EXTERNA TOPICAL PREPS PRESCRIBED                                                       \r',NULL,NULL,NULL),(9504,'4131F','SYSTEMIC ANTIMICROBIAL TX PRESCRIBED                                                                \r',NULL,NULL,NULL),(9505,'4132F','SYSTEMIC ANTIMICROBIAL TX NOT PRESCRIBED                                                            \r',NULL,NULL,NULL),(9506,'4133F','ANTIHISTAMINE/DECONGESTANT PRESCRIBED                                                               \r',NULL,NULL,NULL),(9507,'4134F','ANTIHISTAMINE/DECONGESTANT NOT PRESCRIBED                                                           \r',NULL,NULL,NULL),(9508,'4135F','SYSTEMIC CORTICOSTEROIDS PRESCRIBED                                                                 \r',NULL,NULL,NULL),(9509,'4136F','SYSTEMIC CORTICOSTEROIDS NOT PRESCRIBED                                                             \r',NULL,NULL,NULL),(9510,'4140F','INHALED CORTICOSTEROIDS PRESCRIBED                                                                  \r',NULL,NULL,NULL),(9511,'4142F','CORTICOSTEROID SPARING THERAPY PRESCRIBED                                                           \r',NULL,NULL,NULL),(9512,'4144F','ALTERNATIVE LONG-TERM CONTROL MEDICATION RXD                                                        \r',NULL,NULL,NULL),(9513,'4145F','2+ ANTI-HYPERTENSIVE AGENTS RXD OR TAKEN                                                            \r',NULL,NULL,NULL),(9514,'4148F','HEPATITIS A VACCINE ADMIN OR PREVIOSLY RECVD                                                        \r',NULL,NULL,NULL),(9515,'4149F','HEPATITIS B VACCCINE ADMIN OR PREVIOSLY RECVD                                                       \r',NULL,NULL,NULL),(9516,'4150F','CURRENT HEPATITIS C ANTIVIRAL TREATMENT                                                             \r',NULL,NULL,NULL),(9517,'4151F','NO CURRENT HEPATITIS C ANTIVIRAL TREATMENT                                                          \r',NULL,NULL,NULL),(9518,'4153F','COMB PEGINTERF/RIBAVIRIN TX PRESCRIBED                                                              \r',NULL,NULL,NULL),(9519,'4155F','HEPATITIS A VACCINE SERIES PREVIOUSLY RECEIVED                                                      \r',NULL,NULL,NULL),(9520,'4157F','HEPATITIS B VACCINE SERIES PREVIOUSLY RECEIVED                                                      \r',NULL,NULL,NULL),(9521,'4158F','PATIENT COUNSELED ABOUT RISKS ALCOHOL USE                                                           \r',NULL,NULL,NULL),(9522,'4159F','CONTRACEPTION COUNSEL BEFORE ANTIVIRAL TX                                                           \r',NULL,NULL,NULL),(9523,'4163F','PT COUNSELING TREATMENT OPTIONS PROSTATE CANCER                                                     \r',NULL,NULL,NULL),(9524,'4164F','ADJUVANT HORMONAL THXPY RX/ADMIN                                                                    \r',NULL,NULL,NULL),(9525,'4165F','3D-CRT OR INTENSITY MODUL RAD THXPY RECVD                                                           \r',NULL,NULL,NULL),(9526,'4167F','HEAD-BED ELEV 30-45 DEG 1ST VENT DAY ORDERED                                                        \r',NULL,NULL,NULL),(9527,'4168F','PT RCVG CARE ICU & RCVNG MECH VENT 24 HRS/<                                                         \r',NULL,NULL,NULL),(9528,'4169F','PT NOT RCVG CARE IN ICU/NOT RCVG MECHL VENT                                                         \r',NULL,NULL,NULL),(9529,'4171F','PATIENT RECEIVING (ESA) THERAPY                                                                     \r',NULL,NULL,NULL),(9530,'4172F','PATIENT NOT RECEIVING (ESA) THERAPY                                                                 \r',NULL,NULL,NULL),(9531,'4174F','TLK VIS FXN & QUAL LIFE/TRXMNT FOR PT/CRGVR                                                         \r',NULL,NULL,NULL),(9532,'4175F','CORRECT VISUAL ACUIT 20/40/> W/IN 90 DAYS SURG                                                      \r',NULL,NULL,NULL),(9533,'4176F','COUNSEL UV LITE PROTEC PREV/PROG CATARACT DEVEL                                                     \r',NULL,NULL,NULL),(9534,'4177F','COUNSEL BENEF/RISK AREDS PREV AGE RELATED AMD                                                       \r',NULL,NULL,NULL),(9535,'4178F','ANTI-D IMMUNE GLOBULIN RCVD 26-30 WKS GESTATION                                                     \r',NULL,NULL,NULL),(9536,'4179F','TAMOXIFEN OR AROMATASE INHIBITOR (AI) RXD                                                           \r',NULL,NULL,NULL),(9537,'4180F','ADJVNT CHEMO RFRRD RXD/RCVD STAGE III COLON CA                                                      \r',NULL,NULL,NULL),(9538,'4181F','CONFORMAL RADIATION THERAPY RECEIVED                                                                \r',NULL,NULL,NULL),(9539,'4182F','CONFORMAL RADIATION THERAPY NOT RECEIVED                                                            \r',NULL,NULL,NULL),(9540,'4185F','NONSTOP 12MON THXPY W/PPI OR H2 H2RA RCVD                                                           \r',NULL,NULL,NULL),(9541,'4186F','NO CONTIN 12MON THXPY W/PPI OR H2 H2RA RCVD                                                         \r',NULL,NULL,NULL),(9542,'4187F','DIS MODFY ANTI-RHEU DRUG THXPY RX/GVN                                                               \r',NULL,NULL,NULL),(9543,'4188F','APPROP ACE/ARB THXP MONIT TEST ORDRD/DONE                                                           \r',NULL,NULL,NULL),(9544,'4189F','APPROP DIGOXIN THXP MONIT TST ORDRD/DONE                                                            \r',NULL,NULL,NULL),(9545,'4190F','APPROP DIURETIC THXP MONIT TST ORDRD/DONE                                                           \r',NULL,NULL,NULL),(9546,'4191F','APPROP ANTICONVUL THXP MONIT TST ORDRD/DONE                                                         \r',NULL,NULL,NULL),(9547,'4192F','PATIENT NOT RECEIVING GLUCOCORTICOID                                                                \r',NULL,NULL,NULL),(9548,'4193F','PATIENT RCVNG <10 MG DAILY PREDNISONE                                                               \r',NULL,NULL,NULL),(9549,'4194F','PATIENT RCVNG 10 MG DAILY PREDNISONE                                                                \r',NULL,NULL,NULL),(9550,'4195F','PT RCVNG 1ST BIOL ANTI-RHEUM DRUG THXRPY FOR RA                                                     \r',NULL,NULL,NULL),(9551,'4196F','PT NOT RCVNG 1ST BIOL ANTI-RHEUM DRUG THXPY RA                                                      \r',NULL,NULL,NULL),(9552,'4200F','EXTRNL BM RADIOTHXPY TO PROST W/WO NODAL IRRAD                                                      \r',NULL,NULL,NULL),(9553,'4201F','EXTRNL BM RADIOTHXPY W/WO NODAL IRRAD AS ADJV                                                       \r',NULL,NULL,NULL),(9554,'4210F','ACE/ARB MEDICATION THERAPY 6 MONTHS/>                                                               \r',NULL,NULL,NULL),(9555,'4220F','DIGOXIN MEDICATION THERAPY 6 MONTHS/>                                                               \r',NULL,NULL,NULL),(9556,'4221F','DIURETIC MEDICATION THERAPY 6 MOS/>                                                                 \r',NULL,NULL,NULL),(9557,'4230F','ANTICONVUL MED THERAPY 6 MOS/>                                                                      \r',NULL,NULL,NULL),(9558,'4240F','INSTR THER XRCS-DR FLLWUP PT EPSD BACK PN >12 WK                                                    \r',NULL,NULL,NULL),(9559,'4242F','TLK RE SPRVSD XRCS PROG TO PTS BACK PN >12WKS                                                       \r',NULL,NULL,NULL),(9560,'4245F','PT TLK 1ST VST TO KEEP/RESUME NORMAL ACTIVITIES                                                     \r',NULL,NULL,NULL),(9561,'4248F','COUNSEL INIT BACK PAIN AGNST BED REST 4 DAYS/>                                                      \r',NULL,NULL,NULL),(9562,'4250F','ACTV WRMNG INTRAOP FOR NORMOTHERMIA                                                                 \r',NULL,NULL,NULL),(9563,'4255F','DURATION GEN NEUR ANESTH 60 MINS/> DOC RECORD                                                       \r',NULL,NULL,NULL),(9564,'4256F','DURATION GEN NEUR ANESTH <60 MIN DOCD RECORD                                                        \r',NULL,NULL,NULL),(9565,'4260F','WOUND SURFACE CULTURE TECHNIQUE USED                                                                \r',NULL,NULL,NULL),(9566,'4261F','TECH OTHER THAN SURFACE CULTURE WOUND EXUD USED                                                     \r',NULL,NULL,NULL),(9567,'4265F','USE OF WET TO DRY DRESSINGS PRESCRIBED RECMD                                                        \r',NULL,NULL,NULL),(9568,'4266F','USE WET TO DRY DRESSINGS NEITHER RXD NOR RECMD                                                      \r',NULL,NULL,NULL),(9569,'4267F','COMPRESSION THERAPY PRESCRIBED                                                                      \r',NULL,NULL,NULL),(9570,'4268F','PT ED RE NEED LONG TERM COMPRESS THXPY RCVD                                                         \r',NULL,NULL,NULL),(9571,'4269F','APPROP METHOD OFFLOADING PRESCRIBED                                                                 \r',NULL,NULL,NULL),(9572,'4270F','PT RCVNG POTENT ANTI R-VIRAL THXPY 6 MON OR MORE                                                    \r',NULL,NULL,NULL),(9573,'4271F','PT RCVNG POT ANTI R-VIRAL THXPY <6 MON/NOT RCVN                                                     \r',NULL,NULL,NULL),(9574,'4274F','FLU IMMUNO ADMIND/PREVIOUSLY RCVD                                                                   \r',NULL,NULL,NULL),(9575,'4276F','POTENT ANTIRETROVIRAL THERAPY PRESCRIBED                                                            \r',NULL,NULL,NULL),(9576,'4279F','PNEUMOCYSTIS JIROVECI PNEUMONIA PROPHYLAXIS RXD                                                     \r',NULL,NULL,NULL),(9577,'4280F','PNEUMOCYS JIROVECI PNEUMO PRPHYLXS PRSCRBD 3 MON                                                    \r',NULL,NULL,NULL),(9578,'4290F','PATIENT SCREENED FOR INJECTION DRUG USE                                                             \r',NULL,NULL,NULL),(9579,'4293F','PT SCRND HGH-RSK SEXUAL BEHAVIOR                                                                    \r',NULL,NULL,NULL),(9580,'4300F','PT RCVNG WARFARIN THXPY NONVALV AFIB OR AFLUT                                                       \r',NULL,NULL,NULL),(9581,'4301F','PT NOT RCVNG WARFARIN THXPY NONVALV AFIB/AFLUT                                                      \r',NULL,NULL,NULL),(9582,'4305F','PT EDUC FOOT CARE & DAILY INSPCTN FEET RCVD                                                         \r',NULL,NULL,NULL),(9583,'4306F','PT COUNSEL PSYCHOSOC&PHARM TX OPIOID ADDICTION                                                      \r',NULL,NULL,NULL),(9584,'4320F','PT COUNSEL PSYCHSOC & PHARM TX ALCOHOL DEPEND                                                       \r',NULL,NULL,NULL),(9585,'4322F','CRGVR PROVIDED W/ED REFERRED ADDL RESOURCES                                                         \r',NULL,NULL,NULL),(9586,'4324F','PT QUERIED PARKINSONS MED-RELATED COMPLICATION                                                      \r',NULL,NULL,NULL),(9587,'4325F','MEDICAL & SURGICAL TREATMENT OPTION REVIEW W/P                                                      \r',NULL,NULL,NULL),(9588,'4326F','PT/CAREGIVER QUERIED AUTONOMIC DYSFUNCJ SYMPTOMS                                                    \r',NULL,NULL,NULL),(9589,'4328F','PT/CAREGIVER QUERIED SLEEP DISTURBANCES                                                             \r',NULL,NULL,NULL),(9590,'4330F','EPILEPSY SPECIFIC SAFETY COUNSELING TO PATIENT                                                      \r',NULL,NULL,NULL),(9591,'4340F','COUNSEL WOMEN CHILDBEARING POTENTIAL W/EPILEPSY                                                     \r',NULL,NULL,NULL),(9592,'4350F','COUNSELING PROVIDED SYMP MNGMNT PALLIATION                                                          \r',NULL,NULL,NULL),(9593,'4400F','REHAB THERAPY OPTIONS DISCUSSED W/PATIENT                                                           \r',NULL,NULL,NULL),(9594,'4450F','SELF-CARE EDUCATION PROVIDED TO PATIENT                                                             \r',NULL,NULL,NULL),(9595,'4470F','IMPLANT CARDIOVERT-DEFIB (ICD) COUNSELING PROV                                                      \r',NULL,NULL,NULL),(9596,'4480F','PT RCVNG ACE/ARB BETA BLOCKER TX 3 MONS/LONGER                                                      \r',NULL,NULL,NULL),(9597,'4481F','PT RCVNG ACE/ARB AND BETA BLOCKER > 3 MONTHS                                                        \r',NULL,NULL,NULL),(9598,'4500F','REFERRED TO OUTPT CARD REHABILITATION PROGRAM                                                       \r',NULL,NULL,NULL),(9599,'4510F','PREVIOUS CARDIAC REHAB FOR QUAL CARD EVENT DONE                                                     \r',NULL,NULL,NULL),(9600,'4525F','NEUROPSYCHIATRIC INTERVENTION ORDERED                                                               \r',NULL,NULL,NULL),(9601,'4526F','NEUROPSYCHIATRIC INTERVENTION RECEIVED                                                              \r',NULL,NULL,NULL),(9602,'4540F','DISEASE MODIFYING PHARMACOTHERAPY DISCUSSED                                                         \r',NULL,NULL,NULL),(9603,'4541F','TX PSEUDOBULBAR AFFECT SIALORRHEA/ALS SYMP                                                          \r',NULL,NULL,NULL),(9604,'4550F','OPTIONS NONINVASIVE RESP SUPPORT DISCUSSED W/PT                                                     \r',NULL,NULL,NULL),(9605,'4551F','NUTRITIONAL SUPPORT OFFERED                                                                         \r',NULL,NULL,NULL),(9606,'4552F','PT OFFERED REFERRAL SPEECH LANGUAGE PATHOLOGIST                                                     \r',NULL,NULL,NULL),(9607,'4553F','PT OFFERED ASSISTANCE PLANNING END LIFE ISSUES                                                      \r',NULL,NULL,NULL),(9608,'4554F','PT RECEIVED INHALATIONAL ANESTHETIC AGENT                                                           \r',NULL,NULL,NULL),(9609,'4555F','PT DID NOT RECEIVE INHALATIONAL ANESTHETIC AGENT                                                    \r',NULL,NULL,NULL),(9610,'4556F','PT SHOWS 3+RISK FACTORS POST-OP NAUSEA+VOMITING                                                     \r',NULL,NULL,NULL),(9611,'4557F','PT NO EXHIBIT 3+ RISK FACTORS POST-OP NAUSEA/VOM                                                    \r',NULL,NULL,NULL),(9612,'4558F','PT RCEVD 2 PROPHYLACTIC RX AGENTS PRE+INTRA-OP                                                      \r',NULL,NULL,NULL),(9613,'4559F','1BODY TEMP MEAS>=35.5C IN 30-15 MINS POST ANESTH                                                    \r',NULL,NULL,NULL),(9614,'4560F','ANESTH DID NOT INVOLVE GENERAL/NEURAXIAL ANESTH                                                     \r',NULL,NULL,NULL),(9615,'4561F','PATIENT HAS A CORONARY ARTERY STENT                                                                 \r',NULL,NULL,NULL),(9616,'4562F','PATIENT DOES NOT HAVE A CORONARY ARTERY STENT                                                       \r',NULL,NULL,NULL),(9617,'4563F','PT RECVD ASPIRIN W/IN 24 HRS PRIOR ANESTH START                                                     \r',NULL,NULL,NULL),(9618,'5005F','COUNSEL NEW/CHANGING MOLES SELF-EXAMINATION                                                         \r',NULL,NULL,NULL),(9619,'5010F','DILATED MACULAR/FUNDUS XM COMMUNJ TX PHYS/QHP                                                       \r',NULL,NULL,NULL),(9620,'5015F','DOCD CONTACT THAT FX EXISTED & PT TSTED/TXD OP                                                      \r',NULL,NULL,NULL),(9621,'5020F','TX SUMM RPRT COMMUN PHYS&PT 1 MO COMPLETE                                                           \r',NULL,NULL,NULL),(9622,'5050F','TX COMMUN PROVIDERS CONTINUING CARE 1 MO DX                                                         \r',NULL,NULL,NULL),(9623,'5060F','FINDNGS DIAG MAM TO MNGNG PRACT 3 DAYS INTERP                                                       \r',NULL,NULL,NULL),(9624,'5062F','DOC DIRECT COMM DIAG MAMMO FNDNGS-PHONE/PERSON                                                      \r',NULL,NULL,NULL),(9625,'5100F','FX RISK REF PHYS/QHP COMMJ 24 HRS IMAGING STUDY                                                     \r',NULL,NULL,NULL),(9626,'5200F','CONSID NEURO EVAL APPROP SURG THXPY EPIL 3YRS                                                       \r',NULL,NULL,NULL),(9627,'5250F','ASTHMA DISCHARGE PLAN PRESENT                                                                       \r',NULL,NULL,NULL),(9628,'6005F','RATIONALE FOR LEVEL OF CARE DOCUMENTED                                                              \r',NULL,NULL,NULL),(9629,'6010F','DYSPHAGIA SCREENING PRIOR ORAL INTAKE                                                               \r',NULL,NULL,NULL),(9630,'6015F','PATIENT OK FOR PER ORAL INTAKE (FOOD/MEDICATION)                                                    \r',NULL,NULL,NULL),(9631,'6020F','NOTHING BY MOUTH ORDERED                                                                            \r',NULL,NULL,NULL),(9632,'6030F','ALL ELEM OF MAX STERILE BARRIER TECHNQ FLLWD                                                        \r',NULL,NULL,NULL),(9633,'6040F','USE APPROP RAD DOSE RDXN DEV/MAN TECHS DOCD                                                         \r',NULL,NULL,NULL),(9634,'6045F','RAD EXPOS/TIME IN LAST RPRT FLUORO PRXD DOCD                                                        \r',NULL,NULL,NULL),(9635,'6070F','PATIENT QUERIED COUNSELED RE AED SIDE EFFECTS                                                       \r',NULL,NULL,NULL),(9636,'6080F','PATIENT QUERIED ABOUT FALLS                                                                         \r',NULL,NULL,NULL),(9637,'6090F','PATIENT SAFETY COUNSEL DISEASE STAGE APPROPRIATE                                                    \r',NULL,NULL,NULL),(9638,'6100F','VERIFY CORRECT PT SITE PXD DOCUMENTED                                                               \r',NULL,NULL,NULL),(9639,'6101F','SAFETY COUNSELING DEMENTIA PROVIDED                                                                 \r',NULL,NULL,NULL),(9640,'6102F','SAFETY COUNSELING DEMENTIA ORDERED                                                                  \r',NULL,NULL,NULL),(9641,'6110F','COUNSELING PROV RE RISKS DRIVING ALT TO DRIVING                                                     \r',NULL,NULL,NULL),(9642,'6150F','PT NOT RCVNG 1ST COURSE OF ANTI-TNF THERAPY                                                         \r',NULL,NULL,NULL),(9643,'7010F','PT INFORMATION ENTERED INTO RECALL SYSTEM                                                           \r',NULL,NULL,NULL),(9644,'7020F','MAMMO ASSESSMENT CAT IN DATABASE FOR RATE                                                           \r',NULL,NULL,NULL),(9645,'7025F','INFO SYSTEM ANALYSIS ABNORMAL INTERPRATE                                                            \r',NULL,NULL,NULL),(9646,'9001F','AORTIC ANEURYSM<5CM MAX DIAM CENTERLINE/AXIAL CT                                                    \r',NULL,NULL,NULL),(9647,'9002F','AORTIC ANEURYSM 5-5.4CM MAX DIAM CTRLN/AXIAL CT                                                     \r',NULL,NULL,NULL),(9648,'9003F','AORTIC ARYSM 5.5-5.9CM MAX DIAM CTRLN/AXIAL CT                                                      \r',NULL,NULL,NULL),(9649,'9004F','AORTIC ANEURYSM 6/> CM MAX DIAM CTRLN/AXIAL CT                                                      \r',NULL,NULL,NULL),(9650,'9005F','ASYMPT CAROT STEN NO ISCHEM/STRK CAROT/VRTBROBAS                                                    \r',NULL,NULL,NULL),(9651,'9006F','SYMPT CAROT STENOS IPSIL CAROT TIA/STRK<120DAYS                                                     \r',NULL,NULL,NULL),(9652,'9007F','OTHER CAROTID STENT IPSIL TIA/STRK 120 DAYS/>                                                       \r',NULL,NULL,NULL),(9653,'0019T','EXTRACORPOREAL SHOCK WAVE MUSCSKEL NOS LOW ENRGY                                                    \r',NULL,NULL,NULL),(9654,'0042T','CEREBRAL PERFUSION ANALYS CT W/BLOOD FLOW&VOLUME                                                    \r',NULL,NULL,NULL),(9655,'0051T','IMPLTJ TOT RPLCMT HRT SYS W/RCP CARDIECTOMY                                                         \r',NULL,NULL,NULL),(9656,'0052T','RPLCMT/RPR THRC UNIT TOT RPLCMT HRT SYS                                                             \r',NULL,NULL,NULL),(9657,'0053T','RPLCMT/RPR IMPLTBL COMPNT TOT RPLCMT HRT EX THRC                                                    \r',NULL,NULL,NULL),(9658,'0054T','CPTR-ASST MUSCSKEL NAVIGJ ORTHO FLUOR IMAGES                                                        \r',NULL,NULL,NULL),(9659,'0055T','CPTR-ASST MUSCSKEL NAVIGJ ORTHO CT/MRI                                                              \r',NULL,NULL,NULL),(9660,'0058T','CRYOPRESERVATION REPRODUCTIVE TISSUE OVARIAN                                                        \r',NULL,NULL,NULL),(9661,'0059T','CRYOPRESERVATION OOCYTES                                                                            \r',NULL,NULL,NULL),(9662,'0071T','US ABLATJ UTERINE LEIOMYOMATA < 200 CC TISSUE                                                       \r',NULL,NULL,NULL),(9663,'0072T','US ABLATJ UTERINE LEIOMYOMAT >/EQUAL 200 CC TISS                                                    \r',NULL,NULL,NULL),(9664,'0073T','COMPNSTR-BASED BEAM MODLJ TX DLVR INVERSE 3> FLD                                                    \r',NULL,NULL,NULL),(9665,'0075T','TCAT PLMT XTRC VRT CRTD STENT RS&I PRQ 1ST VSL                                                      \r',NULL,NULL,NULL),(9666,'0076T','TCAT PLMT XTRC VRT CRTD STENT RS&IPRQ EA VSL                                                        \r',NULL,NULL,NULL),(9667,'0085T','BREATH TEST HEART TRANSPLANT REJECTION                                                              \r',NULL,NULL,NULL),(9668,'0092T','TOT DISC ARTHRP ANT APPR DSKC PREP CRV EA NTRSPC                                                    \r',NULL,NULL,NULL),(9669,'0095T','RMVL TOT DISC ARTHRP ANT APPR CRV EA NTRSPC                                                         \r',NULL,NULL,NULL),(9670,'0098T','REVJ TOT DISC ARTHRP ANT APPR CRV EA NTRSPC                                                         \r',NULL,NULL,NULL),(9671,'0099T','IMPLTJ INTRASTROMAL CORNEAL RING SEGMENTS                                                           \r',NULL,NULL,NULL),(9672,'0100T','PLMT SCJNCL RTA PROSTH&PLS&IMPLTJ INTRA-OC RTA                                                      \r',NULL,NULL,NULL),(9673,'0101T','EXTRCORPL SHOCK WAVE MUSCSKELE NOS HIGH ENERGY                                                      \r',NULL,NULL,NULL),(9674,'0102T','EXTRCRPL SHOCK WAVE W/ANES LAT HUMERL EPICONDYLE                                                    \r',NULL,NULL,NULL),(9675,'0103T','HOLOTRANSCOBALAMIN QUANTITATIVE                                                                     \r',NULL,NULL,NULL),(9676,'0106T','QUANT SENSORY TEST&INTERPJ/XTR W/TOUCH STIMULI                                                      \r',NULL,NULL,NULL),(9677,'0107T','QUANT SENSORY TEST&INTERPJ/XTR W/VIBRJ STIMULI                                                      \r',NULL,NULL,NULL),(9678,'0108T','QUANT SENSORY TEST&INTERPJ/XTR W/COOL STIMULI                                                       \r',NULL,NULL,NULL),(9679,'0109T','QUANT SENAORY TEST&INTERPJ/XTR W/HT-PN STIMULI                                                      \r',NULL,NULL,NULL),(9680,'0110T','QUANT SENSORY TEST&INTERPJ/XTR OTHER STIMULI                                                        \r',NULL,NULL,NULL),(9681,'0111T','LONG-CHAIN OMEGA-3 FATTY ACIDS RBC MEMBS                                                            \r',NULL,NULL,NULL),(9682,'0123T','FISTULIZATION SCLERA GLAUCOMA CILIARY BODY                                                          \r',NULL,NULL,NULL),(9683,'0126T','COMMON CAROTID INTIMA MEDIA THICKNESS STUDY                                                         \r',NULL,NULL,NULL),(9684,'0159T','COMPUTER AIDED DETECTION BREAST MRI                                                                 \r',NULL,NULL,NULL),(9685,'0163T','TOT DISC ARTHRP ANT APPR DSKC PREP LMBR EA                                                          \r',NULL,NULL,NULL),(9686,'0164T','RMVL TOT DISC ARTHRP ANT APPR LMBR EA NTRSPC                                                        \r',NULL,NULL,NULL),(9687,'0165T','REVJ TOT DISC ARTHRP ANT APPR LMBR EA NTRSPC                                                        \r',NULL,NULL,NULL),(9688,'0169T','STEREOTACTIC PLACEMENT CATHETER BRAIN                                                               \r',NULL,NULL,NULL),(9689,'0171T','PST SPINOUS PROCESS DEVICE INSERT LMBR 1 LVL                                                        \r',NULL,NULL,NULL),(9690,'0172T','PST SPINOUS PROCESS DEVICE INSERT LMBR EA LVL                                                       \r',NULL,NULL,NULL),(9691,'0174T','CAD CHEST RADIOGRAPH CONCURRENT W/INTERPRETATION                                                    \r',NULL,NULL,NULL),(9692,'0175T','CAD CHEST RADIOGRAPH REMOTE FROM PRIMARY INTERPJ                                                    \r',NULL,NULL,NULL),(9693,'0178T','64 LEAD ECG W/INTERPRETATION & REPORT                                                               \r',NULL,NULL,NULL),(9694,'0179T','64 LEAD ECG W/TRACING & GRAPHICS                                                                    \r',NULL,NULL,NULL),(9695,'0180T','64 LEAD ECG W/INTERPRETATION & REPORT ONLY                                                          \r',NULL,NULL,NULL),(9696,'0181T','CORNEAL HYSTERESIS AIR IMPULSE STIMJ BI W/I&R                                                       \r',NULL,NULL,NULL),(9697,'0182T','HDR ELECTRONIC BRACHYTHERAPY PER FRACTION                                                           \r',NULL,NULL,NULL),(9698,'0184T','RECTAL TUMOR EXCISION TRANSANAL ENDOSCOPIC                                                          \r',NULL,NULL,NULL),(9699,'0188T','VIDEOCONFERENCED CRITICAL CARE FIRST 30- 74 MIN                                                     \r',NULL,NULL,NULL),(9700,'0189T','VIDEOCONFERENCED CRITICAL CARE EA ADDL 30 MIN                                                       \r',NULL,NULL,NULL),(9701,'0190T','INTRAOCULAR RADIATION SRC APPLICATOR PLACEMENT                                                      \r',NULL,NULL,NULL),(9702,'0191T','ANT SEGMENT INSERTION DRAINAGE W/O RESERVOIR INT                                                    \r',NULL,NULL,NULL),(9703,'0195T','ARTHRODESIS PRESACRAL INTRBDY W/O INSTRUM L5/S1                                                     \r',NULL,NULL,NULL),(9704,'0196T','ARTHRODESIS PRESACRAL INTRBDY W/O INSTRUM L4/L5                                                     \r',NULL,NULL,NULL),(9705,'0197T','IFXJ LOCLZ&TRAKG TRGT/PT MTN DUR RADTX EA FXJ                                                       \r',NULL,NULL,NULL),(9706,'0198T','MEAS OCULAR BLOOD FLOW REPEAT IO PRES SAMP W/I&R                                                    \r',NULL,NULL,NULL),(9707,'0199T','PHYSIOL REC TRMR W/ACCEL & GYRO FREQ&AMPL & I&O                                                     \r',NULL,NULL,NULL),(9708,'0200T','PERQ SAC AGMNTJ UNI W/WO BALO/MCHNL DEV 1/> NDL                                                     \r',NULL,NULL,NULL),(9709,'0201T','PERQ SAC AGMNTJ BI W/WO BALO/MCHNL DEV 2/> NDLS                                                     \r',NULL,NULL,NULL),(9710,'0202T','POST VERT ARTHRPLSTY W/WO BONE CEMENT 1 LUMB LVL                                                    \r',NULL,NULL,NULL),(9711,'0205T','IV CATH CORONARY VESSEL/GRAFT SPECTROSCPY EA VSL                                                    \r',NULL,NULL,NULL),(9712,'0206T','CPTR DBS ALYS MLT CYCLS CAR ELEC DTA 2/> ECG LDS                                                    \r',NULL,NULL,NULL),(9713,'0207T','EVAC MEIBOMIAN GLNDS AUTO HT& INTMT PRESS UNI                                                       \r',NULL,NULL,NULL),(9714,'0208T','PURE TONE AUDIOMETRY AUTOMATED AIR ONLY                                                             \r',NULL,NULL,NULL),(9715,'0209T','PURE TONE AUDIOMETRY AUTOMATED AIR & BONE                                                           \r',NULL,NULL,NULL),(9716,'0210T','SPEECH AUDIOMETRY THRESHOLD AUTOMATED                                                               \r',NULL,NULL,NULL),(9717,'0211T','SPEECH AUDIOM THRESHLD AUTO W/SPEECH RECOGNITION                                                    \r',NULL,NULL,NULL),(9718,'0212T','COMPRE AUDIOM THRESHOLD EVAL & SPEECH RECOG                                                         \r',NULL,NULL,NULL),(9719,'0213T','NJX DX/THER PARAVER FCT JT W/US CER/THOR 1 LVL                                                      \r',NULL,NULL,NULL),(9720,'0214T','NJX DX/THER PARAVER FCT JT W/US CER/THOR 2ND LVL                                                    \r',NULL,NULL,NULL),(9721,'0215T','NJX PARAVERTBRL FACET JT W/US CER/THOR 3RD&> LVL                                                    \r',NULL,NULL,NULL),(9722,'0216T','NJX DX/THER PARAVER FCT JT W/US LUMB/SAC 1 LVL                                                      \r',NULL,NULL,NULL),(9723,'0217T','NJX DX/THER PARAVER FCT JT W/US LUMB/SAC LVL 2                                                      \r',NULL,NULL,NULL),(9724,'0218T','NJX PARAVERTBRL FCT JT W/US LUMB/SAC 3RD&> LVL                                                      \r',NULL,NULL,NULL),(9725,'0219T','PLMT POST FACET IMPLANT UNI/BI W/IMG & GRFT CERV                                                    \r',NULL,NULL,NULL),(9726,'0220T','PLMT POST FACET IMPLT UNI/BI W/IMG & GRFT THOR                                                      \r',NULL,NULL,NULL),(9727,'0221T','PLMT POST FACET IMPLT UNI/BI W/IMG & GRFT LUMB                                                      \r',NULL,NULL,NULL),(9728,'0222T','PLACE POSTERIOR INTRAFACET IMPLANT ADDL SEGMENT                                                     \r',NULL,NULL,NULL),(9729,'0223T','ACOUSTIC CARDIOGRAPHY W/INTERPRETATION & REPORT                                                     \r',NULL,NULL,NULL),(9730,'0224T','ACOUSTIC CARDIOGRAPHY MULT ANALYSIS W/I&R                                                           \r',NULL,NULL,NULL),(9731,'0225T','ACOUSTIC CARDIOGRAPHY MULT ALYS W/I&R & REPROG                                                      \r',NULL,NULL,NULL),(9732,'0226T','ANOSCOPY HIGH RESOLUTION W/SPECIMEN COLLECTION                                                      \r',NULL,NULL,NULL),(9733,'0227T','ANOSCOPY HIGH RESOLUTION W/BIOPSY                                                                   \r',NULL,NULL,NULL),(9734,'0228T','NJX ANES/STEROID TFRML EDRL W/US CER/THOR 1 LVL                                                     \r',NULL,NULL,NULL),(9735,'0229T','NJX ANES/STERD TFRML EDRL W/US CER/THOR EA ADDL                                                     \r',NULL,NULL,NULL),(9736,'0230T','NJX ANES/STEROID TFRML EDRL W/US LUM/SAC 1 LVL                                                      \r',NULL,NULL,NULL),(9737,'0231T','NJX ANES/STEROID TFRML EDRL W/US LUM/SAC EA ADDL                                                    \r',NULL,NULL,NULL),(9738,'0232T','NJX PLTLT PLASMA W/IMG HARVEST/PREPARATION                                                          \r',NULL,NULL,NULL),(9739,'0233T','SKIN ADVANCED GLYCATION ENDPRODUCTS SPECTROSCOPY                                                    \r',NULL,NULL,NULL),(9740,'0234T','TRLUML PERIPHERAL ATHERECTOMY RENAL ARTERY EA                                                       \r',NULL,NULL,NULL),(9741,'0235T','TRLUML PERIPHERAL ATHERECTOMY VISCERAL ARTERY EA                                                    \r',NULL,NULL,NULL),(9742,'0236T','TRLUML PERIPH ATHRC W/RS&I ABDOM AORTA                                                              \r',NULL,NULL,NULL),(9743,'0237T','TRLUML PERIPH ATHRC W/RS&I BRCHIOCPHL EA VSL                                                        \r',NULL,NULL,NULL),(9744,'0238T','TRLUML PERIPHERAL ATHERECTOMY ILIAC ARTERY EA                                                       \r',NULL,NULL,NULL),(9745,'0239T','BIOIMPEDANCE SPECTROSCOPY LIMB DIFFERENCES                                                          \r',NULL,NULL,NULL),(9746,'0240T','ESOPH MOTILITY 3D PRESSURE TOPOGRAPHY W/I&R                                                         \r',NULL,NULL,NULL),(9747,'0241T','ESOPH/GASTROESOPH MOTILITY W/STIM/PERFU W/I&R                                                       \r',NULL,NULL,NULL),(9748,'0243T','INTERMIT MEAS WHEEZE RATE BRONCHODIL DX W/I&R                                                       \r',NULL,NULL,NULL),(9749,'0244T','CONT MEAS WHEEZE RATE BRONCHODIL SLEEP 3-24 HRS                                                     \r',NULL,NULL,NULL),(9750,'0245T','OPEN TX RIB FRACTURE W/INT FIX UNI 1-2 RIBS                                                         \r',NULL,NULL,NULL),(9751,'0246T','OPEN TX RIB FRACTURE W/INT FIX UNI 3-4 RIBS                                                         \r',NULL,NULL,NULL),(9752,'0247T','OPEN TX RIB FRACTURE W/INT FIX UNI 5-6 RIBS                                                         \r',NULL,NULL,NULL),(9753,'0248T','OPEN TX RIB FRACTURE W/INT FIXATION UNI 7/> RIBS                                                    \r',NULL,NULL,NULL),(9754,'0249T','LIGATION HEMORRHOID BUNDLE W/US                                                                     \r',NULL,NULL,NULL),(9755,'0253T','INSERT ANT SGM DRAINAGE DEV W/O RESERVR INT APPR                                                    \r',NULL,NULL,NULL),(9756,'0254T','EVASC ILIAC ART BIFURC W/ENDOPROSTH UNI                                                             \r',NULL,NULL,NULL),(9757,'0255T','EVASC ILIAC ART BIFURC W/ENDOPROSTH UNI RS&I                                                        \r',NULL,NULL,NULL),(9758,'0262T','IMPLANT CATH DELIVRD PROSTH PULM VALVE ENDOVASC                                                     \r',NULL,NULL,NULL),(9759,'0263T','AUTO BONE MARRW CELL RX COMPLT BONE MARRW HARVST                                                    \r',NULL,NULL,NULL),(9760,'0264T','AUTO BONE MARRW CELL RX COMP W/O BONE MAR HARVST                                                    \r',NULL,NULL,NULL),(9761,'0265T','BONE MAR HARVST ONLY FOR INTMUSC AUTOLO CELL RX                                                     \r',NULL,NULL,NULL),(9762,'0266T','IM/REPL CARTD SINUS BAROREFLX ACTIV DEV TOT SYST                                                    \r',NULL,NULL,NULL),(9763,'0267T','IM/REPL CARTD SINS BAROREFLX ACTIV DEV LEAD ONLY                                                    \r',NULL,NULL,NULL),(9764,'0268T','IM/REPL CARTD SINS BARREFLX ACT DEV PLS GEN ONLY                                                    \r',NULL,NULL,NULL),(9765,'0269T','REV/REMVL CARTD SINS BARREFLX ACT DEV TOT SYSTEM                                                    \r',NULL,NULL,NULL),(9766,'0270T','REV/REMVL CARTD SINS BARREFLX ACT DEV LEAD ONLY                                                     \r',NULL,NULL,NULL),(9767,'0271T','REV/REM CARTD SINS BARREFLX ACT DEV PLS GEN ONLY                                                    \r',NULL,NULL,NULL),(9768,'0272T','INTRGORTION DEV EVAL CARTD SINS BARREFLX W/I&R                                                      \r',NULL,NULL,NULL),(9769,'0273T','INTROGATION DEV EVAL CARTD SINS BARREFLX W/PRGRM                                                    \r',NULL,NULL,NULL),(9770,'0274T','PERC LAMINO-/LAMINECTOMY IMAGE GUIDE CERV/THORAC                                                    \r',NULL,NULL,NULL),(9771,'0275T','PERC LAMINO-/LAMINECTOMY INDIR IMAG GUIDE LUMBAR                                                    \r',NULL,NULL,NULL),(9772,'0278T','TRNSCUT ELECT MODLATION PAIN REPROCES EA TX SESS                                                    \r',NULL,NULL,NULL),(9773,'0281T','PERC TRANSCTH CLOSR LT ATRIAL APPNDGE IMPLNT S&I                                                    \r',NULL,NULL,NULL),(9774,'0282T','PERC/OPEN IMPLNT NEUROSTIM ELECTRODE SUBQ TRIAL                                                     \r',NULL,NULL,NULL),(9775,'0283T','PERC/OPEN IMPLNT NEUROSTIM ELECTRODE SUBQ PERM                                                      \r',NULL,NULL,NULL),(9776,'0284T','REV/REMVL PG/ELCTRODES IMAGNG ADDN NEW ELCTRODES                                                    \r',NULL,NULL,NULL),(9777,'0285T','ELEC ANLYS IMPLANT SUBQ FIELD STIM PG REPROGRAMM                                                    \r',NULL,NULL,NULL),(9778,'0286T','NEAR INFRARED SPECTROSCPY STUDIES LOW EXT WOUNDS                                                    \r',NULL,NULL,NULL),(9779,'0287T','NEARINFRED GUIDANCE VASC ACES RL TIME DIG VISU                                                      \r',NULL,NULL,NULL),(9780,'0288T','ANSCPY W/DELVRY THERMAL ENERGY MUSCLE ANAL CANAL                                                    \r',NULL,NULL,NULL),(9781,'0289T','CORNEA INCISNS DONOR CORNEA W/LASR KERTPLSTY                                                        \r',NULL,NULL,NULL),(9782,'0290T','CORNEA INCISNS RECIPIENT CORNEA W/LASR KERTPLSTY                                                    \r',NULL,NULL,NULL),(9783,'0291T','INTRAV OPTCL CHERNCE TMGRPHY W/S&I INTL VESL                                                        \r',NULL,NULL,NULL),(9784,'0292T','INTRAV OPTCAL COHRNCE TMGRPHY W/S&I ADL VESL                                                        \r',NULL,NULL,NULL),(9785,'0293T','INS LT ATRL HEMODYN MOTR CMPLETE SYST W/S&I                                                         \r',NULL,NULL,NULL),(9786,'0294T','INS LT ATRL HEMDYN MTR PRSR SENSR LEAD W/S&I                                                        \r',NULL,NULL,NULL),(9787,'0295T','EXT ECG > 48HR TO 21 DAY RCRD SCAN ANLYS REP R&I                                                    \r',NULL,NULL,NULL),(9788,'0296T','EXT ECG > 48HR TO 21 DAY RCRD W/CONECT INTL RCRD                                                    \r',NULL,NULL,NULL),(9789,'0297T','EXT ECG > 48HR TO 21 DAY SCAN ANALYSIS W/REPORT                                                     \r',NULL,NULL,NULL),(9790,'0298T','EXT ECG > 48HR TO 21 DAY REVIEW AND INTERPRETATN                                                    \r',NULL,NULL,NULL),(9791,'0299T','ESW HI ENERGY W/TOPCAL APP &DRESNG CARE 1ST WND                                                     \r',NULL,NULL,NULL),(9792,'0300T','ESW HI ENERGY W/TOPCAL APP &DRESNG CARE ADL WND                                                     \r',NULL,NULL,NULL),(9793,'0301T','DEST/REDUC MALIG BRST TUMR W/US THRMORX GUIDANCE                                                    \r',NULL,NULL,NULL),(9794,'0302T','INSJ/RMVL RPLCMT ICAR ISCHM MNTRNG SYS COMPL                                                        \r',NULL,NULL,NULL),(9795,'0303T','INSJ/RMVL RPLCMT ICAR ISCHM MNTRNG SYS ELTRD                                                        \r',NULL,NULL,NULL),(9796,'0304T','INSJ/RMVL RPLCMT ICAR ISCHM MNTRNG SYS DEVICE                                                       \r',NULL,NULL,NULL),(9797,'0305T','PROGRAM EVAL ICAR ISCHM MNTRNG SYS                                                                  \r',NULL,NULL,NULL),(9798,'0306T','INTERROGATION EVAL ICAR ISCHM MNTRNG SYS                                                            \r',NULL,NULL,NULL),(9799,'0307T','RMVL INTRACARDIAC ISCHEMIA MONITORING DEVICE                                                        \r',NULL,NULL,NULL),(9800,'0308T','INSJ OCULAR TELESCOPE PROSTH                                                                        \r',NULL,NULL,NULL),(9801,'0309T','ARTHRODESIS PRESACRAL INTRBDY W/INSTRUMENT L4/L5                                                    \r',NULL,NULL,NULL),(9802,'0310T','MOTOR FUNCTION MAPPING NAVIGATED TMS TX PLAN                                                        \r',NULL,NULL,NULL),(9803,'0311T','N-INVAS CAL & ALYS CNTRL ARTL PRESSURE WAVEFORM                                                     \r',NULL,NULL,NULL),(9804,'0312T','LAPS IMPLTJ NSTIM ELTRD ARRAY&PLS GEN VAGUS NRV                                                     \r',NULL,NULL,NULL),(9805,'0313T','LAPS REVJ/REPLCMT NSTIM ELTRD ARRAY VAGUS NRV                                                       \r',NULL,NULL,NULL),(9806,'0314T','LAPS RMVL NSTIM ELTRD ARRAY & PLS GEN VAGUS NRV                                                     \r',NULL,NULL,NULL),(9807,'0315T','REMOVAL PULSE GENERATOR VAGUS NERVE                                                                 \r',NULL,NULL,NULL),(9808,'0316T','REPLACEMENT PULSE GENERATOR VAGUS NERVE                                                             \r',NULL,NULL,NULL),(9809,'0317T','ELEC ALYS NSTIM PLS GEN VAGUS NRV W/REPRGRMG                                                        \r',NULL,NULL,NULL),(9810,'0319T','INS/REPLCMT SUBQ IMPLT DEFIB SYSTEM W/SUBQ ELTRD                                                    \r',NULL,NULL,NULL),(9811,'0320T','INSERTION SUBCUTANEOUS DEFIBRILLATOR ELECTRODE                                                      \r',NULL,NULL,NULL),(9812,'0321T','INSERTION SUBQ IMPLT DEFIB PLS GEN W/SUBQ ELTRD                                                     \r',NULL,NULL,NULL),(9813,'0322T','REMOVAL SUBQ IMPLT DEFIB PULSE GENERATOR                                                            \r',NULL,NULL,NULL),(9814,'0323T','RMVL W/REPLCMT SUBQ IMPLT DEFIB PULSE GENERATOR                                                     \r',NULL,NULL,NULL),(9815,'0324T','REMOVAL SUBCUTANEOUS DEFIBRILLATOR ELECTRODE                                                        \r',NULL,NULL,NULL),(9816,'0325T','REPOSITN SUBQ IMPLANT DEFIB ELECTRODE/PULSE GEN                                                     \r',NULL,NULL,NULL),(9817,'0326T','EPHYS EVAL SUBQ IMPLT DEFIB PRGRMG/REPRGRMG                                                         \r',NULL,NULL,NULL),(9818,'0327T','IMPLT SUBQ DEFIB SYS INTERROGATION DEVICE EVAL                                                      \r',NULL,NULL,NULL),(9819,'0328T','IMPLT SUBQ DEFIB SYS PROGRAMMING DEVICE EVAL                                                        \r',NULL,NULL,NULL),(9820,'0329T','MNTR INTRAOCULAR PRESS 24HRS/> UNI/BI W/INTERP                                                      \r',NULL,NULL,NULL),(9821,'0330T','TEAR FILM IMAGING UNILATERAL OR BILATERAL W/I&R                                                     \r',NULL,NULL,NULL),(9822,'0331T','MYOCRD SYMPATHETIC INNERVAJ IMG PLNR QUAL&QUANT                                                     \r',NULL,NULL,NULL),(9823,'0332T','MYOCRD SYMP INNERVAJ IMG PLNR QUAL&QUANT W/SPECT                                                    \r',NULL,NULL,NULL),(9824,'0333T','VISUAL EVOKED POTENTIAL ACUITY SCREENING AUTO                                                       \r',NULL,NULL,NULL),(9825,'0334T','STABLJ SI JOINT FOR ARTHRODESIS PERQ/MIN INVAS                                                      \r',NULL,NULL,NULL),(9826,'0335T','EXTRA-OSSEOUS JOINT IMPLANT TALOTARSAL STABILIZE                                                    \r',NULL,NULL,NULL),(9827,'0336T','LAPS ABLATJ UTERINE FIBROIDS W/INTRAOP US GDNC                                                      \r',NULL,NULL,NULL),(9828,'0337T','ENDOTHELIAL FUNCTION ASSESSMENT NON-INVASIVE                                                        \r',NULL,NULL,NULL),(9829,'0338T','TRANSCATHETER RENAL SYMPATH DENERVATION UNILAT                                                      \r',NULL,NULL,NULL),(9830,'0339T','TRANSCATHETER RENAL SYMPATH DENERVATION BILAT                                                       \r',NULL,NULL,NULL);
/*!40000 ALTER TABLE `cpt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cpt_modifier`
--

DROP TABLE IF EXISTS `cpt_modifier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cpt_modifier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `short_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cpt_modifier`
--

LOCK TABLES `cpt_modifier` WRITE;
/*!40000 ALTER TABLE `cpt_modifier` DISABLE KEYS */;
INSERT INTO `cpt_modifier` VALUES (1,NULL,NULL,NULL,'22','Increased Procedural Services: When the work required to provide a service is substantially greater than typically required, it may be identified by adding modifier 22 to the usual procedure code. Documentation must support the substantial additional work','Increased Procedural Services'),(2,NULL,NULL,NULL,'23','Unusual Anesthesia: Occasionally, a procedure, which usually requires either no anesthesia or local anesthesia, because of unusual circumstances must be done under general anesthesia. This circumstance may be reported by adding modifier 23 to the procedur','Unusual Anesthesia'),(3,NULL,NULL,NULL,'24','Unrelated Evaluation and Management Service by the Same Physician or Other Qualified Health Care Professional During a Postoperative Period: The physician or other qualified health care professional may need to indicate that an evaluation and management s','Evaluation and Management Service'),(4,NULL,NULL,NULL,'25','Significant, Separately Identifiable Evaluation and Management Service by the Same Physician or Other Qualified Health Care Professional on the Same Day of the Procedure or Other Service: It may be necessary to indicate that on the day a procedure or serv','Significant, Separately Identifiable Evaluation and Management'),(5,NULL,NULL,NULL,'26','Professional Component: Certain procedures are a combination of a physician or other qualified health care professional component and a technical component. When the physician or other qualified health care professional component is reported separately, t','Professional Component'),(6,NULL,NULL,NULL,'32','Mandated Services: Services related to mandated consultation and/or related services (eg, third party payer, governmental, legislative or regulatory requirement) may be identified by adding modifier 32 to the basic procedure.','Mandated Services'),(7,NULL,NULL,NULL,'33','Preventive Services: When the primary purpose of the service is the delivery of an evidence based service in accordance with a US Preventive Services Task Force A or B rating in effect and other preventive services identified in preventive services mandat','Preventive Services'),(8,NULL,NULL,NULL,'47','Anesthesia by Surgeon: Regional or general anesthesia provided by the surgeon may be reported by adding modifier 47 to the basic service. (This does not include local anesthesia.) Note: Modifier 47 would not be used as a modifier for the anesthesia proced','Anesthesia by Surgeon'),(9,NULL,NULL,NULL,'50','Bilateral Procedure: Unless otherwise identified in the listings, bilateral procedures that are performed at the same session, should be identified by adding modifier 50 to the appropriate 5 digit code.','Bilateral Procedure'),(10,NULL,NULL,NULL,'51','Multiple Procedures: When multiple procedures, other than E/M services, Physical Medicine and Rehabilitation services or provision of supplies (eg, vaccines), are performed at the same session by the same individual, the primary procedure or service may b','Multiple Procedures'),(11,NULL,NULL,NULL,'52','Reduced Services: Under certain circumstances a service or procedure is partially reduced or eliminated at the discretion of the physician or other qualified health care professional. Under these circumstances the service provided can be identified by its','Reduced Services'),(12,NULL,NULL,NULL,'53','Discontinued Procedure: Under certain circumstances, the physician or other qualified health care professional may elect to terminate a surgical or diagnostic procedure. Due to extenuating circumstances or those that threaten the well being of the patient','Discontinued Procedure'),(13,NULL,NULL,NULL,'54','Surgical Care Only: When 1 physician or other qualified health care professional performs a surgical procedure and another provides preoperative and/or postoperative management, surgical services may be identified by adding modifier 54 to the usual proced','Surgical Care Only'),(14,NULL,NULL,NULL,'55','Postoperative Management Only: When 1 physician or other qualified health care professional performed the postoperative management and another performed the surgical procedure, the postoperative component may be identified by adding modifier 55 to the usu','Postoperative Management Only'),(15,NULL,NULL,NULL,'56','Preoperative Management Only: When 1 physician or other qualified health care professional performed the preoperative care and evaluation and another performed the surgical procedure, the preoperative component may be identified by adding modifier 56 to t','Preoperative Management Only'),(16,NULL,NULL,NULL,'57','Decision for Surgery: An evaluation and management service that resulted in the initial decision to perform the surgery may be identified by adding modifier 57 to the appropriate level of E/M service.','Decision for Surgery'),(17,NULL,NULL,NULL,'58','Staged or Related Procedure or Service by the Same Physician or Other Qualified Health Care Professional During the Postoperative Period: It may be necessary to indicate that the performance of a procedure or service during the postoperative period was: (','Staged or Related Procedure or Service by the Same Physician'),(18,NULL,NULL,NULL,'59','Distinct Procedural Service: Under certain circumstances, it may be necessary to indicate that a procedure or service was distinct or independent from other non-E/M services performed on the same day. Modifier 59 is used to identify procedures/services, o','Distinct Procedural Service'),(19,NULL,NULL,NULL,'62','Two Surgeons: When 2 surgeons work together as primary surgeons performing distinct part(s) of a procedure, each surgeon should report his/her distinct operative work by adding modifier 62 to the procedure code and any associated add-on code(s) for that p','Two Surgeons'),(20,NULL,NULL,NULL,'63','Procedure Performed on Infants less than 4 kg: Procedures performed on neonates and infants up to a present body weight of 4 kg may involve significantly increased complexity and physician or other qualified health care professional work commonly associat','Procedure Performed on Infants less than 4 kg'),(21,NULL,NULL,NULL,'66','Surgical Team: Under some circumstances, highly complex procedures (requiring the concomitant services of several physicians or other qualified health care professionals, often of different specialties, plus other highly skilled, specially trained personn','Surgical Team'),(22,NULL,NULL,NULL,'76','Repeat Procedure or Service by Same Physician or Other Qualified Health Care Professional: It may be necessary to indicate that a procedure or service was repeated by the same physician or other qualified health care professional subsequent to the origina','Repeat Procedure or Service by Same Physician'),(23,NULL,NULL,NULL,'77','Repeat Procedure by Another Physician or Other Qualified Health Care Professional: It may be necessary to indicate that a basic procedure or service was repeated by another physician or other qualified health care professional subsequent to the original p','Repeat Procedure by Another Physician'),(24,NULL,NULL,NULL,'78','Unplanned Return to the Operating/Procedure Room by the Same Physician or Other Qualified Health Care Professional Following Initial Procedure for a Related Procedure During the Postoperative Period: It may be necessary to indicate that another procedure ','Unplanned Return to the Operating'),(25,NULL,NULL,NULL,'79','Unrelated Procedure or Service by the Same Physician or Other Qualified Health Care Professional During the Postoperative Period : The individual may need to indicate that the performance of a procedure or service during the postoperative period was unrel','Unrelated Procedure or Service by the Same Physician'),(26,NULL,NULL,NULL,'80','Assistant Surgeon: Surgical assistant services may be identified by adding modifier 80 to the usual procedure number(s).','Assistant Surgeon'),(27,NULL,NULL,NULL,'81','Minimum Assistant Surgeon: Minimum surgical assistant services are identified by adding modifier 81 to the usual procedure number.','Minimum Assistant Surgeon'),(28,NULL,NULL,NULL,'82','Assistant Surgeon (when qualified resident surgeon not available): The unavailability of a qualified resident surgeon is a prerequisite for use of modifier 82 appended to the usual procedure code number(s).','Assistant Surgeon'),(29,NULL,NULL,NULL,'90','Reference (Outside) Laboratory: When laboratory procedures are performed by a party other than the treating or reporting physician or other qualified health care professional, the procedure may be identified by adding modifier 90 to the usual procedure nu','Reference (Outside) Laboratory'),(30,NULL,NULL,NULL,'91','Repeat Clinical Diagnostic Laboratory Test: In the course of treatment of the patient, it may be necessary to repeat the same laboratory test on the same day to obtain subsequent (multiple) test results. Under these circumstances, the laboratory test perf','Repeat Clinical Diagnostic Laboratory Test'),(31,NULL,NULL,NULL,'92','Alternative Laboratory Platform Testing: When laboratory testing is being performed using a kit or transportable instrument that wholly or in part consists of a single use, disposable analytical chamber, the service may be identified by adding modifier 92','Alternative Laboratory Platform Testing'),(32,NULL,NULL,NULL,'99','Multiple Modifiers: Under certain circumstances 2 or more modifiers may be necessary to completely delineate a service. In such situations modifier 99 should be added to the basic procedure, and other applicable modifiers may be listed as part of the desc','Multiple Modifiers'),(33,NULL,NULL,NULL,'P1','Physical Status Modifier P1: A normal healthy patient ','Physical Status Modifier P1'),(34,NULL,NULL,NULL,'P2','Physical Status Modifier P2: A patient with mild systemic disease','Physical Status Modifier P2'),(35,NULL,NULL,NULL,'P3','Physical Status Modifier P3: A patient with severe systemic disease','Physical Status Modifier P3'),(36,NULL,NULL,NULL,'P4','Physical Status Modifier P4: A patient with severe systemic disease that is a constant threat to life','Physical Status Modifier P4'),(37,NULL,NULL,NULL,'P5','Physical Status Modifier P5: A moribund patient who is not expected to survive without the operation','Physical Status Modifier P5'),(38,NULL,NULL,NULL,'P6','Physical Status Modifier P6: A declared brain-dead patient whose organs are being removed for donor purposes','Physical Status Modifier P6'),(39,NULL,NULL,NULL,'27','Multiple Outpatient Hospital E/M Encounters on the Same Date: For hospital outpatient reporting purposes, utilization of hospital resources related to separate and distinct E/M encounters performed in multiple outpatient hospital settings on the same date','Multiple Outpatient Hospital E/M Encounters on the Same Date'),(40,NULL,NULL,NULL,'73','Discontinued Out-Patient Hospital/Ambulatory Surgery Center (ASC) Procedure Prior to the Administration of Anesthesia: Due to extenuating circumstances or those that threaten the well being of the patient, the physician may cancel a surgical or diagnostic','Discontinued Out-Patient Hospital/Ambulatory Surgery Center (ASC) Procedure Prior to the Administration of Anesthesia'),(41,NULL,NULL,NULL,'74','Discontinued Out-Patient Hospital/Ambulatory Surgery Center (ASC) Procedure After Administration of Anesthesia: Due to extenuating circumstances or those that threaten the well being of the patient, the physician may terminate a surgical or diagnostic pro','Discontinued Out-Patient Hospital/Ambulatory Surgery Center (ASC) Procedure After Administration of Anesthesia'),(42,NULL,NULL,NULL,'E1',NULL,'Upper left, eyelid'),(43,NULL,NULL,NULL,'E2',NULL,'Lower left, eyelid'),(44,NULL,NULL,NULL,'E3',NULL,'Upper right, eyelid'),(45,NULL,NULL,NULL,'E4',NULL,'Lower right, eyelid'),(46,NULL,NULL,NULL,'F1',NULL,'Left hand, second digit'),(47,NULL,NULL,NULL,'F2',NULL,'Left hand, third digit'),(48,NULL,NULL,NULL,'F3',NULL,'Left hand, fourth digit'),(49,NULL,NULL,NULL,'F4',NULL,'Left hand, fifth digit'),(50,NULL,NULL,NULL,'F5',NULL,'Right hand, thumb'),(51,NULL,NULL,NULL,'F6',NULL,'Right hand, second digit'),(52,NULL,NULL,NULL,'F7',NULL,'Right hand, third digit'),(53,NULL,NULL,NULL,'F8',NULL,'Right hand, fourth digit'),(54,NULL,NULL,NULL,'F9',NULL,'Right hand, fifth digit'),(55,NULL,NULL,NULL,'FA',NULL,'Left hand, thumb'),(56,NULL,NULL,NULL,'GG',NULL,'Performance and payment of a screening mammogram and diagnostic mammogram on the same patient, same day'),(57,NULL,NULL,NULL,'GH',NULL,'Diagnostic mammogram converted from screening mammogram on same day'),(58,NULL,NULL,NULL,'LC',NULL,'Left circumflex coronary artery'),(59,NULL,NULL,NULL,'LD',NULL,'Left anterior descending coronary artery'),(60,NULL,NULL,NULL,'LT',NULL,'Left side (used to identify procedures performed on the left side of the body)'),(61,NULL,NULL,NULL,'QM',NULL,'Ambulance service provided under arrangement by a provider of services'),(62,NULL,NULL,NULL,'QN',NULL,'Ambulance service furnished directly by a provider of services'),(63,NULL,NULL,NULL,'RC',NULL,'Right coronary artery'),(64,NULL,NULL,NULL,'RI',NULL,'Right side (used to identify procedures performed on the right side of the body)'),(65,NULL,NULL,NULL,'T1',NULL,'Left foot, second digit'),(66,NULL,NULL,NULL,'T2',NULL,'Left foot, fourth digit'),(67,NULL,NULL,NULL,'T3',NULL,'Left foot, fourth digit'),(68,NULL,NULL,NULL,'T4',NULL,'Left foot, fifth digit'),(69,NULL,NULL,NULL,'T5',NULL,'Right foot, great toe'),(70,NULL,NULL,NULL,'T6',NULL,'Right foot, second digit'),(71,NULL,NULL,NULL,'T7',NULL,'Right foot, third digit'),(72,NULL,NULL,NULL,'T8',NULL,'Right foot, fourth digit'),(73,NULL,NULL,NULL,'T9',NULL,'Right foot, fifth digit'),(74,NULL,NULL,NULL,'TA',NULL,'Left foot, great toe');
/*!40000 ALTER TABLE `cpt_modifier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credential`
--

DROP TABLE IF EXISTS `credential`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credential` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credential`
--

LOCK TABLES `credential` WRITE;
/*!40000 ALTER TABLE `credential` DISABLE KEYS */;
/*!40000 ALTER TABLE `credential` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crisis_plan`
--

DROP TABLE IF EXISTS `crisis_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crisis_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `call_order` text DEFAULT NULL,
  `cell_phone` varchar(255) DEFAULT NULL,
  `client_sig` varchar(255) DEFAULT NULL,
  `client_sig_date` datetime DEFAULT NULL,
  `deescalation` text DEFAULT NULL,
  `health_issues` text DEFAULT NULL,
  `helpful` text DEFAULT NULL,
  `home_phone` varchar(255) DEFAULT NULL,
  `not_mention` text DEFAULT NULL,
  `positive` text DEFAULT NULL,
  `provider_sig` varchar(255) DEFAULT NULL,
  `provider_sig_date` datetime DEFAULT NULL,
  `service_providers` text DEFAULT NULL,
  `signals` text DEFAULT NULL,
  `triggers` text DEFAULT NULL,
  `work_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK537911254661880D` (`clinician_id`),
  CONSTRAINT `FK537911254661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crisis_plan`
--

LOCK TABLES `crisis_plan` WRITE;
/*!40000 ALTER TABLE `crisis_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `crisis_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnosis`
--

DROP TABLE IF EXISTS `diagnosis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnosis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnosis`
--

LOCK TABLES `diagnosis` WRITE;
/*!40000 ALTER TABLE `diagnosis` DISABLE KEYS */;
/*!40000 ALTER TABLE `diagnosis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `division`
--

DROP TABLE IF EXISTS `division`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `division` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `division`
--

LOCK TABLES `division` WRITE;
/*!40000 ALTER TABLE `division` DISABLE KEYS */;
/*!40000 ALTER TABLE `division` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dx_code`
--

DROP TABLE IF EXISTS `dx_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dx_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `encounter_id` int(11) DEFAULT NULL,
  `icd_9` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7C1E16B8430D62E` (`icd_9`),
  CONSTRAINT `FK7C1E16B8430D62E` FOREIGN KEY (`icd_9`) REFERENCES `icd_9` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dx_code`
--

LOCK TABLES `dx_code` WRITE;
/*!40000 ALTER TABLE `dx_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `dx_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edt_registration_form`
--

DROP TABLE IF EXISTS `edt_registration_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edt_registration_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `allergy_1` varchar(255) DEFAULT NULL,
  `allergy_2` varchar(255) DEFAULT NULL,
  `allergy_3` varchar(255) DEFAULT NULL,
  `allergy_info` varchar(255) DEFAULT NULL,
  `allergy_level_1` varchar(255) DEFAULT NULL,
  `allergy_level_2` varchar(255) DEFAULT NULL,
  `allergy_level_3` varchar(255) DEFAULT NULL,
  `arriving_by` varchar(255) DEFAULT NULL,
  `attendence_sig` varchar(255) DEFAULT NULL,
  `attendence_sig_date` datetime DEFAULT NULL,
  `child_sig` varchar(255) DEFAULT NULL,
  `child_sig_date` datetime DEFAULT NULL,
  `days` varchar(255) DEFAULT NULL,
  `discipline_sig` varchar(255) DEFAULT NULL,
  `discipline_sig_date` datetime DEFAULT NULL,
  `dose_1` varchar(255) DEFAULT NULL,
  `dose_2` varchar(255) DEFAULT NULL,
  `dose_3` varchar(255) DEFAULT NULL,
  `emerg_name_1` varchar(255) DEFAULT NULL,
  `emerg_name_2` varchar(255) DEFAULT NULL,
  `emerg_phone_1` varchar(255) DEFAULT NULL,
  `emerg_phone_2` varchar(255) DEFAULT NULL,
  `emerg_rel_1` varchar(255) DEFAULT NULL,
  `emerg_rel_2` varchar(255) DEFAULT NULL,
  `food` varchar(255) DEFAULT NULL,
  `freq_1` varchar(255) DEFAULT NULL,
  `freq_2` varchar(255) DEFAULT NULL,
  `freq_3` varchar(255) DEFAULT NULL,
  `goals` varchar(255) DEFAULT NULL,
  `gps_transport` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `group_number` varchar(255) DEFAULT NULL,
  `guardian_address` varchar(255) DEFAULT NULL,
  `guardian_cell_phone` varchar(255) DEFAULT NULL,
  `guardian_city` varchar(255) DEFAULT NULL,
  `guardian_email` varchar(255) DEFAULT NULL,
  `guardian_gender` tinyblob DEFAULT NULL,
  `guardian_home_phone` varchar(255) DEFAULT NULL,
  `guardian_name` varchar(255) DEFAULT NULL,
  `guardian_postal_code` varchar(255) DEFAULT NULL,
  `guardian_sig` varchar(255) DEFAULT NULL,
  `guardian_sig_date` datetime DEFAULT NULL,
  `guardian_us_state` tinyblob DEFAULT NULL,
  `hospital` varchar(255) DEFAULT NULL,
  `iep_info` varchar(255) DEFAULT NULL,
  `insurance` varchar(255) DEFAULT NULL,
  `insured` varchar(255) DEFAULT NULL,
  `intake_coord_sig` varchar(255) DEFAULT NULL,
  `intake_coord_sig_date` datetime DEFAULT NULL,
  `interests` varchar(255) DEFAULT NULL,
  `lang` varchar(255) DEFAULT NULL,
  `lang_other` varchar(255) DEFAULT NULL,
  `leaving_by` varchar(255) DEFAULT NULL,
  `lives_with` varchar(255) DEFAULT NULL,
  `lives_with_other` varchar(255) DEFAULT NULL,
  `med_1` varchar(255) DEFAULT NULL,
  `med_2` varchar(255) DEFAULT NULL,
  `med_3` varchar(255) DEFAULT NULL,
  `med_admin_sig` varchar(255) DEFAULT NULL,
  `med_admin_sig_date` datetime DEFAULT NULL,
  `med_info` varchar(255) DEFAULT NULL,
  `med_rel_sig` varchar(255) DEFAULT NULL,
  `med_rel_sig_date` datetime DEFAULT NULL,
  `pcp_address` varchar(255) DEFAULT NULL,
  `pcp_name` varchar(255) DEFAULT NULL,
  `pcp_phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `photo_sig` varchar(255) DEFAULT NULL,
  `photo_sig_date` datetime DEFAULT NULL,
  `pick_up_name_1` varchar(255) DEFAULT NULL,
  `pick_up_name_2` varchar(255) DEFAULT NULL,
  `pick_up_phone_1` varchar(255) DEFAULT NULL,
  `pick_up_phone_2` varchar(255) DEFAULT NULL,
  `pick_up_rel_1` varchar(255) DEFAULT NULL,
  `pick_up_rel_2` varchar(255) DEFAULT NULL,
  `policy_number` varchar(255) DEFAULT NULL,
  `prescriber_1` varchar(255) DEFAULT NULL,
  `prescriber_2` varchar(255) DEFAULT NULL,
  `prescriber_3` varchar(255) DEFAULT NULL,
  `prev_program` varchar(255) DEFAULT NULL,
  `prev_program_assess` varchar(255) DEFAULT NULL,
  `private_ride` varchar(255) DEFAULT NULL,
  `rep_name` varchar(255) DEFAULT NULL,
  `rep_rel` varchar(255) DEFAULT NULL,
  `reporting_sig` varchar(255) DEFAULT NULL,
  `reporting_sig_date` datetime DEFAULT NULL,
  `sig` tinyint(1) DEFAULT NULL,
  `ssn` varchar(255) DEFAULT NULL,
  `violence` varchar(255) DEFAULT NULL,
  `violence_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK46B036804661880D` (`clinician_id`),
  CONSTRAINT `FK46B036804661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edt_registration_form`
--

LOCK TABLES `edt_registration_form` WRITE;
/*!40000 ALTER TABLE `edt_registration_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `edt_registration_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emergency_info_form`
--

DROP TABLE IF EXISTS `emergency_info_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emergency_info_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `allergy_1` varchar(20) DEFAULT NULL,
  `allergy_2` varchar(20) DEFAULT NULL,
  `allergy_3` varchar(20) DEFAULT NULL,
  `allergy_info` text DEFAULT NULL,
  `allergy_level_1` varchar(20) DEFAULT NULL,
  `allergy_level_2` varchar(20) DEFAULT NULL,
  `allergy_level_3` varchar(20) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `contact_name1` varchar(255) DEFAULT NULL,
  `contact_name2` varchar(255) DEFAULT NULL,
  `contact_phone1` varchar(255) DEFAULT NULL,
  `contact_phone2` varchar(255) DEFAULT NULL,
  `contact_rel1` varchar(255) DEFAULT NULL,
  `contact_rel2` varchar(255) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `dose_1` varchar(20) DEFAULT NULL,
  `dose_2` varchar(20) DEFAULT NULL,
  `dose_3` varchar(20) DEFAULT NULL,
  `drop_off_name` varchar(255) DEFAULT NULL,
  `drop_off_phone` varchar(255) DEFAULT NULL,
  `dx1` varchar(255) DEFAULT NULL,
  `dx2` varchar(255) DEFAULT NULL,
  `dx3` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `freq_1` varchar(20) DEFAULT NULL,
  `freq_2` varchar(20) DEFAULT NULL,
  `freq_3` varchar(20) DEFAULT NULL,
  `hospital` varchar(255) DEFAULT NULL,
  `insurance` varchar(255) DEFAULT NULL,
  `lang` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `med_1` varchar(20) DEFAULT NULL,
  `med_2` varchar(20) DEFAULT NULL,
  `med_3` varchar(20) DEFAULT NULL,
  `med_info` text DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `pcp_name` varchar(255) DEFAULT NULL,
  `pcp_phone` varchar(255) DEFAULT NULL,
  `pharmacy` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `pick_up_name` varchar(255) DEFAULT NULL,
  `pick_up_phone` varchar(255) DEFAULT NULL,
  `policy_number` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `prescriber_1` varchar(20) DEFAULT NULL,
  `prescriber_2` varchar(20) DEFAULT NULL,
  `prescriber_3` varchar(20) DEFAULT NULL,
  `prescriber_name` varchar(255) DEFAULT NULL,
  `prescriber_phone` varchar(255) DEFAULT NULL,
  `read_doc` tinyint(1) DEFAULT NULL,
  `sig_date` datetime DEFAULT NULL,
  `sig_patient` varchar(255) DEFAULT NULL,
  `sig_rel` varchar(255) DEFAULT NULL,
  `sig_rep` varchar(255) DEFAULT NULL,
  `sig_rep_date` datetime DEFAULT NULL,
  `spec_name` varchar(255) DEFAULT NULL,
  `spec_phone` varchar(255) DEFAULT NULL,
  `ssn` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `us_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK26D3DC874661880D` (`clinician_id`),
  KEY `FK26D3DC87B929E6ED` (`us_state`),
  CONSTRAINT `FK26D3DC874661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK26D3DC87B929E6ED` FOREIGN KEY (`us_state`) REFERENCES `us_state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emergency_info_form`
--

LOCK TABLES `emergency_info_form` WRITE;
/*!40000 ALTER TABLE `emergency_info_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `emergency_info_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `encounter`
--

DROP TABLE IF EXISTS `encounter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `encounter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `lock_status` int(11) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `soap_note` int(11) DEFAULT NULL,
  `chief_complaint` int(11) DEFAULT NULL,
  `encounter_type` int(11) NOT NULL,
  `exam` int(11) DEFAULT NULL,
  `family_history` int(11) DEFAULT NULL,
  `lab` int(11) DEFAULT NULL,
  `medical_history` int(11) DEFAULT NULL,
  `ob_gyn` int(11) DEFAULT NULL,
  `vital_signs` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5AD86933F98BD0CD` (`exam`),
  KEY `FK5AD86933275B9A` (`family_history`),
  KEY `FK5AD86933970EB11D` (`vital_signs`),
  KEY `FK5AD869333A678E6` (`chief_complaint`),
  KEY `FK5AD8693369815224` (`patient_id`),
  KEY `FK5AD86933F5A5ADAA` (`ob_gyn`),
  KEY `FK5AD869334661880D` (`clinician_id`),
  KEY `FK5AD86933738FF83F` (`lab`),
  KEY `FK5AD86933F3FE4B78` (`medical_history`),
  KEY `FK5AD869338ED5CF52` (`soap_note`),
  KEY `FK5AD86933DAE88CB8` (`encounter_type`),
  CONSTRAINT `FK5AD86933275B9A` FOREIGN KEY (`family_history`) REFERENCES `family_history` (`id`),
  CONSTRAINT `FK5AD869333A678E6` FOREIGN KEY (`chief_complaint`) REFERENCES `chief_complaint` (`id`),
  CONSTRAINT `FK5AD869334661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK5AD8693369815224` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `FK5AD86933738FF83F` FOREIGN KEY (`lab`) REFERENCES `lab` (`id`),
  CONSTRAINT `FK5AD869338ED5CF52` FOREIGN KEY (`soap_note`) REFERENCES `soap_note` (`id`),
  CONSTRAINT `FK5AD86933970EB11D` FOREIGN KEY (`vital_signs`) REFERENCES `vital_sign` (`id`),
  CONSTRAINT `FK5AD86933DAE88CB8` FOREIGN KEY (`encounter_type`) REFERENCES `encounter_type` (`id`),
  CONSTRAINT `FK5AD86933F3FE4B78` FOREIGN KEY (`medical_history`) REFERENCES `patient_medical_history` (`id`),
  CONSTRAINT `FK5AD86933F5A5ADAA` FOREIGN KEY (`ob_gyn`) REFERENCES `obgyn_encounter_data` (`id`),
  CONSTRAINT `FK5AD86933F98BD0CD` FOREIGN KEY (`exam`) REFERENCES `exam` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encounter`
--

LOCK TABLES `encounter` WRITE;
/*!40000 ALTER TABLE `encounter` DISABLE KEYS */;
/*!40000 ALTER TABLE `encounter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `encounter_type`
--

DROP TABLE IF EXISTS `encounter_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `encounter_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encounter_type`
--

LOCK TABLES `encounter_type` WRITE;
/*!40000 ALTER TABLE `encounter_type` DISABLE KEYS */;
INSERT INTO `encounter_type` VALUES (1,'2013-10-04 20:36:50','Check Up',NULL,'2018-07-28 17:02:25'),(2,'2013-10-04 20:37:45','Follow Up',NULL,NULL),(3,'2013-10-04 20:38:06','Urgent Care',NULL,NULL),(4,'2013-10-04 20:38:33','Immunization',NULL,NULL);
/*!40000 ALTER TABLE `encounter_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ethnicity`
--

DROP TABLE IF EXISTS `ethnicity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ethnicity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ethnicity`
--

LOCK TABLES `ethnicity` WRITE;
/*!40000 ALTER TABLE `ethnicity` DISABLE KEYS */;
INSERT INTO `ethnicity` VALUES (1,NULL,'2016-02-07 09:49:35',NULL,'Not Hispanic or Latino'),(2,NULL,'2016-02-07 19:17:24',NULL,'Hispanic or Latino'),(3,NULL,NULL,NULL,'Unknown or Not Reported');
/*!40000 ALTER TABLE `ethnicity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `diagnosis` varchar(255) DEFAULT NULL,
  `diagram_path` varchar(255) DEFAULT NULL,
  `heart_rhythm` varchar(255) DEFAULT NULL,
  `hs` varchar(255) DEFAULT NULL,
  `treatment_plan` varchar(255) DEFAULT NULL,
  `encounter` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2FB81F5D457337` (`encounter`),
  KEY `FK2FB81F4661880D` (`clinician_id`),
  CONSTRAINT `FK2FB81F4661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK2FB81F5D457337` FOREIGN KEY (`encounter`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam`
--

LOCK TABLES `exam` WRITE;
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facility`
--

DROP TABLE IF EXISTS `facility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facility` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `institution_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `primary_phone` varchar(255) DEFAULT NULL,
  `secondary_phone` varchar(255) DEFAULT NULL,
  `street_address1` varchar(255) DEFAULT NULL,
  `street_address2` varchar(255) DEFAULT NULL,
  `country` int(11) DEFAULT NULL,
  `us_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1DDE6EA385B829B6` (`country`),
  KEY `FK1DDE6EA3B929E6ED` (`us_state`),
  CONSTRAINT `FK1DDE6EA385B829B6` FOREIGN KEY (`country`) REFERENCES `country` (`id`),
  CONSTRAINT `FK1DDE6EA3B929E6ED` FOREIGN KEY (`us_state`) REFERENCES `us_state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility`
--

LOCK TABLES `facility` WRITE;
/*!40000 ALTER TABLE `facility` DISABLE KEYS */;
/*!40000 ALTER TABLE `facility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `family_history`
--

DROP TABLE IF EXISTS `family_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `family_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `caretaker_name` varchar(255) DEFAULT NULL,
  `caretaker_relationship` varchar(255) DEFAULT NULL,
  `father_alive` tinyint(1) DEFAULT NULL,
  `father_death_reason` varchar(255) DEFAULT NULL,
  `job_type` varchar(255) DEFAULT NULL,
  `mother_alive` tinyint(1) DEFAULT NULL,
  `mother_dob` datetime DEFAULT NULL,
  `mother_death_reason` varchar(255) DEFAULT NULL,
  `mother_name` varchar(255) DEFAULT NULL,
  `num_brothers` int(11) DEFAULT NULL,
  `num_children` int(11) DEFAULT NULL,
  `num_daughters` int(11) DEFAULT NULL,
  `num_residents` int(11) DEFAULT NULL,
  `num_siblings` int(11) DEFAULT NULL,
  `num_sisters` int(11) DEFAULT NULL,
  `num_sons` int(11) DEFAULT NULL,
  `partner_alive` tinyint(1) DEFAULT NULL,
  `partner_death_reason` varchar(255) DEFAULT NULL,
  `saved` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9D9842B94661880D` (`clinician_id`),
  CONSTRAINT `FK9D9842B94661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `family_history`
--

LOCK TABLES `family_history` WRITE;
/*!40000 ALTER TABLE `family_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `family_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fba_bip`
--

DROP TABLE IF EXISTS `fba_bip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fba_bip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `background` text DEFAULT NULL,
  `bcba_sig` varchar(255) DEFAULT NULL,
  `bcba_sig_date` datetime DEFAULT NULL,
  `behaviorist` varchar(255) DEFAULT NULL,
  `behaviorist_sig` varchar(255) DEFAULT NULL,
  `behaviorist_sig_date` datetime DEFAULT NULL,
  `clinician_name` varchar(255) DEFAULT NULL,
  `guardian_sig` varchar(255) DEFAULT NULL,
  `guardian_sig_date` datetime DEFAULT NULL,
  `impact` text DEFAULT NULL,
  `intervention_date` datetime DEFAULT NULL,
  `intervention_plan` text DEFAULT NULL,
  `methods` varchar(255) DEFAULT NULL,
  `rewards` text DEFAULT NULL,
  `sources` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC07A1CEF4661880D` (`clinician_id`),
  CONSTRAINT `FKC07A1CEF4661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fba_bip`
--

LOCK TABLES `fba_bip` WRITE;
/*!40000 ALTER TABLE `fba_bip` DISABLE KEYS */;
/*!40000 ALTER TABLE `fba_bip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gender` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES (1,'2018-11-26 14:07:19','2018-12-02 13:08:23',NULL,'M','Male'),(2,'2018-11-26 14:07:31',NULL,NULL,NULL,'Female');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guardian`
--

DROP TABLE IF EXISTS `guardian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guardian` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `client_type` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `pager` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `password_created` tinyint(4) DEFAULT 0,
  `primary_phone` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `secondary_phone` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `address_same_as_client` tinyint(1) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `customer_key` varchar(255) DEFAULT NULL,
  `goals` text DEFAULT NULL,
  `marital_other` varchar(255) DEFAULT NULL,
  `patient_form_id` int(11) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `profession` varchar(255) DEFAULT NULL,
  `street_address1` varchar(255) DEFAULT NULL,
  `recovery_code` int(11) DEFAULT NULL,
  `marital_status` int(11) DEFAULT NULL,
  `us_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA4CDB9915649B2AD` (`marital_status`),
  KEY `FKA4CDB991C1AEB72F` (`recovery_code`),
  KEY `FKA4CDB991B929E6ED` (`us_state`),
  CONSTRAINT `FKA4CDB9915649B2AD` FOREIGN KEY (`marital_status`) REFERENCES `marital_status` (`id`),
  CONSTRAINT `FKA4CDB991B929E6ED` FOREIGN KEY (`us_state`) REFERENCES `us_state` (`id`),
  CONSTRAINT `FKA4CDB991C1AEB72F` FOREIGN KEY (`recovery_code`) REFERENCES `recovery_code` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guardian`
--

LOCK TABLES `guardian` WRITE;
/*!40000 ALTER TABLE `guardian` DISABLE KEYS */;
/*!40000 ALTER TABLE `guardian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `health_issue`
--

DROP TABLE IF EXISTS `health_issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `health_issue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `dxtx` varchar(255) DEFAULT NULL,
  `onset` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `symptoms` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `health_issue`
--

LOCK TABLES `health_issue` WRITE;
/*!40000 ALTER TABLE `health_issue` DISABLE KEYS */;
/*!40000 ALTER TABLE `health_issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `icd_9`
--

DROP TABLE IF EXISTS `icd_9`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `icd_9` (
  `code_type` int(11) DEFAULT NULL,
  `code` varchar(16) DEFAULT NULL,
  `modifier` varchar(16) DEFAULT NULL,
  `code_text` text DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14316 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `icd_9`
--

LOCK TABLES `icd_9` WRITE;
/*!40000 ALTER TABLE `icd_9` DISABLE KEYS */;
INSERT INTO `icd_9` VALUES (2,'948.96','','Burn (any degree) involving 90 percent or more of body surface with third degree burn of 60-69%',11430,NULL,NULL,NULL),(2,'948.97','','Burn (any degree) involving 90 percent or more of body surface with third degree burn of 70-79%',11431,NULL,NULL,NULL),(2,'948.98','','Burn (any degree) involving 90 percent or more of body surface with third degree burn of 80-89%',11432,NULL,NULL,NULL),(2,'948.99','','Burn (any degree) involving 90 percent or more of body surface with third degree burn of 90% or more of body surface',11433,NULL,NULL,NULL),(2,'949.0','','Burn of unspecified site unspecified degree',11434,NULL,NULL,NULL),(2,'949.1','','Erythema due to burn (first degree) unspecified site',11435,NULL,NULL,NULL),(2,'949.2','','Blisters with epidermal loss due to burn (second degree) unspecified site',11436,NULL,NULL,NULL),(2,'949.3','','Full-thickness skin loss due to burn (third degree nos) unspecified site',11437,NULL,NULL,NULL),(2,'949.4','','Deep necrosis of underlying tissue due to burn (deep third degree) unspecified site without loss of a body part',11438,NULL,NULL,NULL),(2,'949.5','','Deep necrosis of underlying tissues due to burn (deep third degree unspecified site with loss of a body part',11439,NULL,NULL,NULL),(2,'950.0','','Optic nerve injury',11440,NULL,NULL,NULL),(2,'950.1','','Injury to optic chiasm',11441,NULL,NULL,NULL),(2,'950.2','','Injury to optic pathways',11442,NULL,NULL,NULL),(2,'950.3','','Injury to visual cortex',11443,NULL,NULL,NULL),(2,'950.9','','Injury to unspecified optic nerve and pathways',11444,NULL,NULL,NULL),(2,'951.0','','Injury to oculomotor nerve',11445,NULL,NULL,NULL),(2,'951.1','','Injury to trochlear nerve',11446,NULL,NULL,NULL),(2,'951.2','','Injury to trigeminal nerve',11447,NULL,NULL,NULL),(2,'951.3','','Injury to abducens nerve',11448,NULL,NULL,NULL),(2,'951.4','','Injury to facial nerve',11449,NULL,NULL,NULL),(2,'951.5','','Injury to acoustic nerve',11450,NULL,NULL,NULL),(2,'951.6','','Injury to accessory nerve',11451,NULL,NULL,NULL),(2,'951.7','','Injury to hypoglossal nerve',11452,NULL,NULL,NULL),(2,'951.8','','Injury to other specified cranial nerves',11453,NULL,NULL,NULL),(2,'951.9','','Injury to unspecified cranial nerve',11454,NULL,NULL,NULL),(2,'952.00','','C1-c4 level spinal cord injury unspecified',11455,NULL,NULL,NULL),(2,'952.01','','C1-c4 level with complete lesion of spinal cord',11456,NULL,NULL,NULL),(2,'952.02','','C1-c4 level with anterior cord syndrome',11457,NULL,NULL,NULL),(2,'952.03','','C1-c4 level with central cord syndrome',11458,NULL,NULL,NULL),(2,'952.04','','C1-c4 level with other specified spinal cord injury',11459,NULL,NULL,NULL),(2,'952.05','','C5-c7 level spinal cord injury unspecified',11460,NULL,NULL,NULL),(2,'952.06','','C5-c7 level with complete lesion of spinal cord',11461,NULL,NULL,NULL),(2,'952.07','','C5-c7 level with anterior cord syndrome',11462,NULL,NULL,NULL),(2,'952.08','','C5-c7 level with central cord syndrome',11463,NULL,NULL,NULL),(2,'952.09','','C5-c7 level with other specified spinal cord injury',11464,NULL,NULL,NULL),(2,'952.10','','T1-t6 level spinal cord injury unspecified',11465,NULL,NULL,NULL),(2,'952.11','','T1-t6 level with complete lesion of spinal cord',11466,NULL,NULL,NULL),(2,'952.12','','T1-t6 level with anterior cord syndrome',11467,NULL,NULL,NULL),(2,'952.13','','T1-t6 level with central cord syndrome',11468,NULL,NULL,NULL),(2,'952.14','','T1-t6 level with other specified spinal cord injury',11469,NULL,NULL,NULL),(2,'952.15','','T7-t12 level spinal cord injury unspecified',11470,NULL,NULL,NULL),(2,'952.16','','T7-t12 level with complete lesion of spinal cord',11471,NULL,NULL,NULL),(2,'952.17','','T7-t12 level with anterior cord syndrome',11472,NULL,NULL,NULL),(2,'952.18','','T7-t12 level with central cord syndrome',11473,NULL,NULL,NULL),(2,'952.19','','T7-t12 level with other specified spinal cord injury',11474,NULL,NULL,NULL),(2,'952.2','','Lumbar spinal cord injury without spinal bone injury',11475,NULL,NULL,NULL),(2,'952.3','','Sacral spinal cord injury without spinal bone injury',11476,NULL,NULL,NULL),(2,'952.4','','Cauda equina spinal cord injury without spinal bone injury',11477,NULL,NULL,NULL),(2,'952.8','','Multiple sites of spinal cord injury without spinal bone injury',11478,NULL,NULL,NULL),(2,'952.9','','Unspecified site of spinal cord injury without spinal bone injury',11479,NULL,NULL,NULL),(2,'953.0','','Injury to cervical nerve root',11480,NULL,NULL,NULL),(2,'953.1','','Injury to dorsal nerve root',11481,NULL,NULL,NULL),(2,'953.2','','Injury to lumbar nerve root',11482,NULL,NULL,NULL),(2,'953.3','','Injury to sacral nerve root',11483,NULL,NULL,NULL),(2,'953.4','','Injury to brachial plexus',11484,NULL,NULL,NULL),(2,'953.5','','Injury to lumbosacral plexus',11485,NULL,NULL,NULL),(2,'953.8','','Injury to multiple sites of nerve roots and spinal plexus',11486,NULL,NULL,NULL),(2,'953.9','','Injury to unspecified site of nerve roots and spinal plexus',11487,NULL,NULL,NULL),(2,'954.0','','Injury to cervical sympathetic nerve excluding shoulder and pelvic girdles',11488,NULL,NULL,NULL),(2,'954.1','','Injury to other sympathetic nerve excluding shoulder and pelvic girdles',11489,NULL,NULL,NULL),(2,'954.8','','Injury to other specified nerve(s) of trunk excluding shoulder and pelvic girdles',11490,NULL,NULL,NULL),(2,'954.9','','Injury to unspecified nerve of trunk excluding shoulder and pelvic girdles',11491,NULL,NULL,NULL),(2,'955.0','','Injury to axillary nerve',11492,NULL,NULL,NULL),(2,'955.1','','Injury to median nerve',11493,NULL,NULL,NULL),(2,'955.2','','Injury to ulnar nerve',11494,NULL,NULL,NULL),(2,'955.3','','Injury to radial nerve',11495,NULL,NULL,NULL),(2,'955.4','','Injury to musculocutaneous nerve',11496,NULL,NULL,NULL),(2,'955.5','','Injury to cutaneous sensory nerve upper limb',11497,NULL,NULL,NULL),(2,'955.6','','Injury to digital nerve upper limb',11498,NULL,NULL,NULL),(2,'955.7','','Injury to other specified nerve(s) of shoulder girdle and upper limb',11499,NULL,NULL,NULL),(2,'955.8','','Injury to multiple nerves of shoulder girdle and upper limb',11500,NULL,NULL,NULL),(2,'955.9','','Injury to unspecified nerve of shoulder girdle and upper limb',11501,NULL,NULL,NULL),(2,'956.0','','Injury to sciatic nerve',11502,NULL,NULL,NULL),(2,'956.1','','Injury to femoral nerve',11503,NULL,NULL,NULL),(2,'956.2','','Injury to posterior tibial nerve',11504,NULL,NULL,NULL),(2,'956.3','','Injury to peroneal nerve',11505,NULL,NULL,NULL),(2,'956.4','','Injury to cutaneous sensory nerve lower limb',11506,NULL,NULL,NULL),(2,'956.5','','Injury to other specified nerve(s) of pelvic girdle and lower limb',11507,NULL,NULL,NULL),(2,'956.8','','Injury to multiple nerves of pelvic girdle and lower limb',11508,NULL,NULL,NULL),(2,'956.9','','Injury to unspecified nerve of pelvic girdle and lower limb',11509,NULL,NULL,NULL),(2,'957.0','','Injury to superficial nerves of head and neck',11510,NULL,NULL,NULL),(2,'957.1','','Injury to other specified nerve(s)',11511,NULL,NULL,NULL),(2,'957.8','','Injury to multiple nerves in several parts',11512,NULL,NULL,NULL),(2,'957.9','','Injury to nerves unspecified site',11513,NULL,NULL,NULL),(2,'958.0','','Air embolism as an early complication of trauma',11514,NULL,NULL,NULL),(2,'958.1','','Fat embolism as an early complication of trauma',11515,NULL,NULL,NULL),(2,'958.2','','Secondary and recurrent hemorrhage as an early complication of trauma',11516,NULL,NULL,NULL),(2,'958.3','','Posttraumatic wound infection not elsewhere classified',11517,NULL,NULL,NULL),(2,'958.4','','Traumatic shock',11518,NULL,NULL,NULL),(2,'958.5','','Traumatic anuria',11519,NULL,NULL,NULL),(2,'958.6','','Volkmann\'s ischemic contracture',11520,NULL,NULL,NULL),(2,'958.7','','Traumatic subcutaneous emphysema',11521,NULL,NULL,NULL),(2,'958.8','','Other early complications of trauma',11522,NULL,NULL,NULL),(2,'958.90','','Compartment syndrome, unspecified',11523,NULL,NULL,NULL),(2,'958.91','','Traumatic compartment syndrome of upper extremity',11524,NULL,NULL,NULL),(2,'958.92','','Traumatic compartment syndrome of lower extremity',11525,NULL,NULL,NULL),(2,'958.93','','Traumatic compartment syndrome of abdomen',11526,NULL,NULL,NULL),(2,'958.99','','Traumatic compartment syndrome of other sites',11527,NULL,NULL,NULL),(2,'959.01','','Other and unspecified injury to head',11528,NULL,NULL,NULL),(2,'959.09','','Other and unspecified injury to face and neck',11529,NULL,NULL,NULL),(2,'959.11','','Other injury of chest wall',11530,NULL,NULL,NULL),(2,'959.12','','Other injury of abdomen',11531,NULL,NULL,NULL),(2,'959.13','','Fracture of corpus cavernosum penis',11532,NULL,NULL,NULL),(2,'959.14','','Other injury of external genitals',11533,NULL,NULL,NULL),(2,'959.19','','Other and unspecified injury of other sites of trunk',11534,NULL,NULL,NULL),(2,'959.2','','Other and unspecified injury to shoulder and upper arm',11535,NULL,NULL,NULL),(2,'959.3','','Other and unspecified injury to elbow forearm and wrist',11536,NULL,NULL,NULL),(2,'959.4','','Other and unspecified injury to hand except finger',11537,NULL,NULL,NULL),(2,'959.5','','Other and unspecified injury to finger',11538,NULL,NULL,NULL),(2,'959.6','','Other and unspecified injury to hip and thigh',11539,NULL,NULL,NULL),(2,'959.7','','Other and unspecified injury to knee leg ankle and foot',11540,NULL,NULL,NULL),(2,'959.8','','Other and unspecified injury to other specified sites including multiple',11541,NULL,NULL,NULL),(2,'959.9','','Other and unspecified injury to unspecified site',11542,NULL,NULL,NULL),(2,'960.0','','Poisoning by penicillins',11543,NULL,NULL,NULL),(2,'960.1','','Poisoning by antifungal antibiotics',11544,NULL,NULL,NULL),(2,'960.2','','Poisoning by chloramphenicol group',11545,NULL,NULL,NULL),(2,'960.3','','Poisoning by erythromycin and other macrolides',11546,NULL,NULL,NULL),(2,'960.4','','Poisoning by tetracycline group',11547,NULL,NULL,NULL),(2,'960.5','','Poisoning of cephalosporin group',11548,NULL,NULL,NULL),(2,'960.6','','Poisoning of antimycobacterial antibiotics',11549,NULL,NULL,NULL),(2,'960.7','','Poisoning by antineoplastic antibiotics',11550,NULL,NULL,NULL),(2,'960.8','','Poisoning by other specified antibiotics',11551,NULL,NULL,NULL),(2,'960.9','','Poisoning by unspecified antibiotic',11552,NULL,NULL,NULL),(2,'961.0','','Poisoning by sulfonamides',11553,NULL,NULL,NULL),(2,'961.1','','Poisoning by arsenical anti-infectives',11554,NULL,NULL,NULL),(2,'961.2','','Poisoning by heavy metal anti-infectives',11555,NULL,NULL,NULL),(2,'961.3','','Poisoning by quinoline and hydroxyquinoline derivatives',11556,NULL,NULL,NULL),(2,'961.4','','Poisoning by antimalarials and drugs acting on other blood protozoa',11557,NULL,NULL,NULL),(2,'961.5','','Poisoning by other antiprotozoal drugs',11558,NULL,NULL,NULL),(2,'961.6','','Poisoning by anthelmintics',11559,NULL,NULL,NULL),(2,'961.7','','Poisoning by antiviral drugs',11560,NULL,NULL,NULL),(2,'961.8','','Poisoning by other antimycobacterial drugs',11561,NULL,NULL,NULL),(2,'961.9','','Poisoning by other and unspecified anti-infectives',11562,NULL,NULL,NULL),(2,'962.0','','Poisoning by adrenal cortical steroids',11563,NULL,NULL,NULL),(2,'962.1','','Poisoning by androgens and anabolic congeners',11564,NULL,NULL,NULL),(2,'962.2','','Poisoning by ovarian hormones and synthetic substitutes',11565,NULL,NULL,NULL),(2,'962.3','','Poisoning by insulins and antidiabetic agents',11566,NULL,NULL,NULL),(2,'962.4','','Poisoning by anterior pituitary hormones',11567,NULL,NULL,NULL),(2,'962.5','','Poisoning by posterior pituitary hormones',11568,NULL,NULL,NULL),(2,'962.6','','Poisoning by parathyroid and parathyroid derivatives',11569,NULL,NULL,NULL),(2,'962.7','','Poisoning by thyroid and thyroid derivatives',11570,NULL,NULL,NULL),(2,'962.8','','Poisoning by antithyroid agents',11571,NULL,NULL,NULL),(2,'962.9','','Poisoning by other and unspecified hormones and synthetic substitutes',11572,NULL,NULL,NULL),(2,'963.0','','Poisoning by antiallergic and antiemetic drugs',11573,NULL,NULL,NULL),(2,'963.1','','Poisoning by antineoplastic and immunosuppressive drugs',11574,NULL,NULL,NULL),(2,'963.2','','Poisoning by acidifying agents',11575,NULL,NULL,NULL),(2,'963.3','','Poisoning by alkalizing agents',11576,NULL,NULL,NULL),(2,'963.4','','Poisoning by enzymes not elsewhere classified',11577,NULL,NULL,NULL),(2,'963.5','','Poisoning by vitamins not elsewhere classified',11578,NULL,NULL,NULL),(2,'963.8','','Poisoning by other specified systemic agents',11579,NULL,NULL,NULL),(2,'963.9','','Poisoning by unspecified systemic agent',11580,NULL,NULL,NULL),(2,'964.0','','Poisoning by iron and its compounds',11581,NULL,NULL,NULL),(2,'964.1','','Poisoning by liver preparations and other antianemic agents',11582,NULL,NULL,NULL),(2,'964.2','','Poisoning by anticoagulants',11583,NULL,NULL,NULL),(2,'964.3','','Poisoning by vitamin k (phytonadione)',11584,NULL,NULL,NULL),(2,'964.4','','Poisoning by fibrinolysis-affecting drugs',11585,NULL,NULL,NULL),(2,'964.5','','Poisoning by anticoagulant antagonists and other coagulants',11586,NULL,NULL,NULL),(2,'964.6','','Poisoning by gamma globulin',11587,NULL,NULL,NULL),(2,'964.7','','Poisoning by natural blood and blood products',11588,NULL,NULL,NULL),(2,'964.8','','Poisoning by other specified agents affecting blood constituents',11589,NULL,NULL,NULL),(2,'964.9','','Poisoning by unspecified agent affecting blood constituents',11590,NULL,NULL,NULL),(2,'965.00','','Poisoning by opium (alkaloids) unspecified',11591,NULL,NULL,NULL),(2,'965.01','','Poisoning by heroin',11592,NULL,NULL,NULL),(2,'965.02','','Poisoning by methadone',11593,NULL,NULL,NULL),(2,'965.09','','Poisoning by other opiates and related narcotics',11594,NULL,NULL,NULL),(2,'965.1','','Poisoning by salicylates',11595,NULL,NULL,NULL),(2,'965.4','','Poisoning by aromatic analgesics not elsewhere classified',11596,NULL,NULL,NULL),(2,'965.5','','Poisoning by pyrazole derivatives',11597,NULL,NULL,NULL),(2,'965.61','','Poisoning by propionic acid derivatives',11598,NULL,NULL,NULL),(2,'965.69','','Poisoning by other antirheumatics',11599,NULL,NULL,NULL),(2,'965.7','','Poisoning by other non-narcotic analgesics',11600,NULL,NULL,NULL),(2,'965.8','','Poisoning by other specified analgesics and antipyretics',11601,NULL,NULL,NULL),(2,'965.9','','Poisoning by unspecified analgesic and antipyretic',11602,NULL,NULL,NULL),(2,'966.0','','Poisoning by oxazolidine derivatives',11603,NULL,NULL,NULL),(2,'966.1','','Poisoning by hydantoin derivatives',11604,NULL,NULL,NULL),(2,'966.2','','Poisoning by succinimides',11605,NULL,NULL,NULL),(2,'966.3','','Poisoning by other and unspecified anticonvulsants',11606,NULL,NULL,NULL),(2,'966.4','','Poisoning by anti-parkinsonism drugs',11607,NULL,NULL,NULL),(2,'967.0','','Poisoning by barbiturates',11608,NULL,NULL,NULL),(2,'967.1','','Poisoning by chloral hydrate group',11609,NULL,NULL,NULL),(2,'967.2','','Poisoning by paraldehyde',11610,NULL,NULL,NULL),(2,'967.3','','Poisoning by bromine compounds',11611,NULL,NULL,NULL),(2,'967.4','','Poisoning by methaqualone compounds',11612,NULL,NULL,NULL),(2,'967.5','','Poisoning by glutethimide group',11613,NULL,NULL,NULL),(2,'967.6','','Poisoning by mixed sedatives not elsewhere classified',11614,NULL,NULL,NULL),(2,'967.8','','Poisoning by other sedatives and hypnotics',11615,NULL,NULL,NULL),(2,'967.9','','Poisoning by unspecified sedative or hypnotic',11616,NULL,NULL,NULL),(2,'968.0','','Poisoning by central nervous system muscle-tone depressants',11617,NULL,NULL,NULL),(2,'968.1','','Poisoning by halothane',11618,NULL,NULL,NULL),(2,'968.2','','Poisoning by other gaseous anesthetics',11619,NULL,NULL,NULL),(2,'968.3','','Poisoning by intravenous anesthetics',11620,NULL,NULL,NULL),(2,'968.4','','Poisoning by other and unspecified general anesthetics',11621,NULL,NULL,NULL),(2,'968.5','','Poisoning by surface (topical) and infiltration anesthetics',11622,NULL,NULL,NULL),(2,'968.6','','Poisoning by peripheral nerve- and plexus-blocking anesthetics',11623,NULL,NULL,NULL),(2,'968.7','','Poisoning by spinal anesthetics',11624,NULL,NULL,NULL),(2,'968.9','','Poisoning by other and unspecified local anesthetics',11625,NULL,NULL,NULL),(2,'969.00','','Poisoning by antidepressant, unspecified',11626,NULL,NULL,NULL),(2,'969.01','','Poisoning by monoamine oxidase inhibitors',11627,NULL,NULL,NULL),(2,'969.02','','Poisoning by selective serotonin and norepinephrine reuptake inhibitors',11628,NULL,NULL,NULL),(2,'969.03','','Poisoning by selective serotonin reuptake inhibitors',11629,NULL,NULL,NULL),(2,'969.04','','Poisoning by tetracyclic antidepressants',11630,NULL,NULL,NULL),(2,'969.05','','Poisoning by tricyclic antidepressants',11631,NULL,NULL,NULL),(2,'969.09','','Poisoning by other antidepressants',11632,NULL,NULL,NULL),(2,'969.1','','Poisoning by phenothiazine-based tranquilizers',11633,NULL,NULL,NULL),(2,'969.2','','Poisoning by butyrophenone-based tranquilizers',11634,NULL,NULL,NULL),(2,'969.3','','Poisoning by other antipsychotics neuroleptics and major tranquilizers',11635,NULL,NULL,NULL),(2,'969.4','','Poisoning by benzodiazepine-based tranquilizers',11636,NULL,NULL,NULL),(2,'969.5','','Poisoning by other tranquilizers',11637,NULL,NULL,NULL),(2,'969.6','','Poisoning by psychodysleptics (hallucinogens)',11638,NULL,NULL,NULL),(2,'969.70','','Poisoning by psychostimulant, unspecified',11639,NULL,NULL,NULL),(2,'969.71','','Poisoning by caffeine',11640,NULL,NULL,NULL),(2,'969.72','','Poisoning by amphetamines',11641,NULL,NULL,NULL),(2,'969.73','','Poisoning by methylphenidate',11642,NULL,NULL,NULL),(2,'969.79','','Poisoning by other psychostimulants',11643,NULL,NULL,NULL),(2,'969.8','','Poisoning by other specified psychotropic agents',11644,NULL,NULL,NULL),(2,'969.9','','Poisoning by unspecified psychotropic agent',11645,NULL,NULL,NULL),(2,'970.0','','Poisoning by analeptics',11646,NULL,NULL,NULL),(2,'970.1','','Poisoning by opiate antagonists',11647,NULL,NULL,NULL),(2,'970.8','','Poisoning by other specified central nervous system stimulants',11648,NULL,NULL,NULL),(2,'970.9','','Poisoning by unspecified central nervous system stimulant',11649,NULL,NULL,NULL),(2,'971.0','','Poisoning by parasympathomimetics (cholinergics)',11650,NULL,NULL,NULL),(2,'971.1','','Poisoning by parasympatholytics (anticholinergics and antimuscarinics) and spasmolytics',11651,NULL,NULL,NULL),(2,'971.2','','Poisoning by sympathomimetics (adrenergics)',11652,NULL,NULL,NULL),(2,'971.3','','Poisoning by sympatholytics (antiadrenergics)',11653,NULL,NULL,NULL),(2,'971.9','','Poisoning by unspecified drug primarily affecting autonomic nervous system',11654,NULL,NULL,NULL),(2,'972.0','','Poisoning by cardiac rhythm regulators',11655,NULL,NULL,NULL),(2,'972.1','','Poisoning by cardiotonic glycosides and drugs of similar action',11656,NULL,NULL,NULL),(2,'972.2','','Poisoning by antilipemic and antiarteriosclerotic drugs',11657,NULL,NULL,NULL),(2,'972.3','','Poisoning by ganglion-blocking agents',11658,NULL,NULL,NULL),(2,'972.4','','Poisoning by coronary vasodilators',11659,NULL,NULL,NULL),(2,'972.5','','Poisoning by other vasodilators',11660,NULL,NULL,NULL),(2,'972.6','','Poisoning by other antihypertensive agents',11661,NULL,NULL,NULL),(2,'972.7','','Poisoning by antivaricose drugs including sclerosing agents',11662,NULL,NULL,NULL),(2,'972.8','','Poisoning by capillary-active drugs',11663,NULL,NULL,NULL),(2,'972.9','','Poisoning by other and unspecified agents primarily affecting the cardiovascular system',11664,NULL,NULL,NULL),(2,'973.0','','Poisoning by antacids and antigastric secretion drugs',11665,NULL,NULL,NULL),(2,'973.1','','Poisoning by irritant cathartics',11666,NULL,NULL,NULL),(2,'973.2','','Poisoning by emollient cathartics',11667,NULL,NULL,NULL),(2,'973.3','','Poisoning by other cathartics including intestinal atonia',11668,NULL,NULL,NULL),(2,'973.4','','Poisoning by digestants',11669,NULL,NULL,NULL),(2,'973.5','','Poisoning by antidiarrheal drugs',11670,NULL,NULL,NULL),(2,'973.6','','Poisoning by emetics',11671,NULL,NULL,NULL),(2,'973.8','','Poisoning by other specified agents primarily affecting the gastrointestinal system',11672,NULL,NULL,NULL),(2,'973.9','','Poisoning by unspecified agent primarily affecting the gastrointestinal system',11673,NULL,NULL,NULL),(2,'974.0','','Poisoning by mercurial diuretics',11674,NULL,NULL,NULL),(2,'974.1','','Poisoning by purine derivative diuretics',11675,NULL,NULL,NULL),(2,'974.2','','Poisoning by carbonic acid anhydrase inhibitors',11676,NULL,NULL,NULL),(2,'974.3','','Poisoning by saluretics',11677,NULL,NULL,NULL),(2,'974.4','','Poisoning by other diuretics',11678,NULL,NULL,NULL),(2,'974.5','','Poisoning by electrolytic caloric and water-balance agents',11679,NULL,NULL,NULL),(2,'974.6','','Poisoning by other mineral salts not elsewhere classified',11680,NULL,NULL,NULL),(2,'974.7','','Poisoning by uric acid metabolism drugs',11681,NULL,NULL,NULL),(2,'975.0','','Poisoning by oxytocic agents',11682,NULL,NULL,NULL),(2,'975.1','','Poisoning by smooth muscle relaxants',11683,NULL,NULL,NULL),(2,'975.2','','Poisoning by skeletal muscle relaxants',11684,NULL,NULL,NULL),(2,'975.3','','Poisoning by other and unspecified drugs acting on muscles',11685,NULL,NULL,NULL),(2,'975.4','','Poisoning by antitussives',11686,NULL,NULL,NULL),(2,'975.5','','Poisoning by expectorants',11687,NULL,NULL,NULL),(2,'975.6','','Poisoning by anti-common cold drugs',11688,NULL,NULL,NULL),(2,'975.7','','Poisoning by antiasthmatics',11689,NULL,NULL,NULL),(2,'975.8','','Poisoning by other and unspecified respiratory drugs',11690,NULL,NULL,NULL),(2,'976.0','','Poisoning by local anti-infectives and anti-inflammatory drugs',11691,NULL,NULL,NULL),(2,'976.1','','Poisoning by antipruritics',11692,NULL,NULL,NULL),(2,'976.2','','Poisoning by local astringents and local detergents',11693,NULL,NULL,NULL),(2,'976.3','','Poisoning by emollients demulcents and protectants',11694,NULL,NULL,NULL),(2,'976.4','','Poisoning by keratolytics keratoplastics other hair treatment drugs and preparations',11695,NULL,NULL,NULL),(2,'976.5','','Poisoning by eye anti-infectives and other eye drugs',11696,NULL,NULL,NULL),(2,'976.6','','Poisoning by anti-infectives and other drugs and preparations for ear nose and throat',11697,NULL,NULL,NULL),(2,'976.7','','Poisoning by dental drugs topically applied',11698,NULL,NULL,NULL),(2,'976.8','','Poisoning by other agents primarily affecting skin and mucous membrane',11699,NULL,NULL,NULL),(2,'976.9','','Poisoning by unspecified agent primarily affecting skin and mucous membrane',11700,NULL,NULL,NULL),(2,'977.0','','Poisoning by dietetics',11701,NULL,NULL,NULL),(2,'977.1','','Poisoning by lipotropic drugs',11702,NULL,NULL,NULL),(2,'977.2','','Poisoning by antidotes and chelating agents not elsewhere classified',11703,NULL,NULL,NULL),(2,'977.3','','Poisoning by alcohol deterrents',11704,NULL,NULL,NULL),(2,'977.4','','Poisoning by pharmaceutical excipients',11705,NULL,NULL,NULL),(2,'977.8','','Poisoning by other specified drugs and medicinal substances',11706,NULL,NULL,NULL),(2,'977.9','','Poisoning by unspecified drug or medicinal substance',11707,NULL,NULL,NULL),(2,'978.0','','Poisoning by bcg vaccine',11708,NULL,NULL,NULL),(2,'978.1','','Poisoning by typhoid and paratyphoid vaccine',11709,NULL,NULL,NULL),(2,'978.2','','Poisoning by cholera vaccine',11710,NULL,NULL,NULL),(2,'978.3','','Poisoning by plague vaccine',11711,NULL,NULL,NULL),(2,'978.4','','Poisoning by tetanus vaccine',11712,NULL,NULL,NULL),(2,'978.5','','Poisoning by diphtheria vaccine',11713,NULL,NULL,NULL),(2,'978.6','','Poisoning by pertussis vaccine including combinations with a pertussis component',11714,NULL,NULL,NULL),(2,'978.8','','Poisoning by other and unspecified bacterial vaccines',11715,NULL,NULL,NULL),(2,'978.9','','Poisoning by mixed bacterial vaccines except combinations with a pertussis component',11716,NULL,NULL,NULL),(2,'979.0','','Poisoning by smallpox vaccine',11717,NULL,NULL,NULL),(2,'979.1','','Poisoning by rabies vaccine',11718,NULL,NULL,NULL),(2,'979.2','','Poisoning by typhus vaccine',11719,NULL,NULL,NULL),(2,'979.3','','Poisoning by yellow fever vaccine',11720,NULL,NULL,NULL),(2,'979.4','','Poisoning by measles vaccine',11721,NULL,NULL,NULL),(2,'979.5','','Poisoning by poliomyelitis vaccine',11722,NULL,NULL,NULL),(2,'979.6','','Poisoning by other and unspecified viral and rickettsial vaccines',11723,NULL,NULL,NULL),(2,'979.7','','Poisoning by mixed viral-rickettsial and bacterial vaccines except combinations with a pertussis component',11724,NULL,NULL,NULL),(2,'979.9','','Poisoning by other and unspecified vaccines and biological substances',11725,NULL,NULL,NULL),(2,'980.0','','Toxic effect of ethyl alcohol',11726,NULL,NULL,NULL),(2,'980.1','','Toxic effect of methyl alcohol',11727,NULL,NULL,NULL),(2,'980.2','','Toxic effect of isopropyl alcohol',11728,NULL,NULL,NULL),(2,'980.3','','Toxic effect of fusel oil',11729,NULL,NULL,NULL),(2,'980.8','','Toxic effect of other specified alcohols',11730,NULL,NULL,NULL),(2,'980.9','','Toxic effect of unspecified alcohol',11731,NULL,NULL,NULL),(2,'981','','Toxic effect of petroleum products',11732,NULL,NULL,NULL),(2,'982.0','','Toxic effect of benzene and homologues',11733,NULL,NULL,NULL),(2,'982.1','','Toxic effect of carbon tetrachloride',11734,NULL,NULL,NULL),(2,'982.2','','Toxic effect of carbon disulfide',11735,NULL,NULL,NULL),(2,'982.3','','Toxic effect of other chlorinated hydrocarbon solvents',11736,NULL,NULL,NULL),(2,'982.4','','Toxic effect of nitroglycol',11737,NULL,NULL,NULL),(2,'982.8','','Toxic effect of other nonpetroleum-based solvents',11738,NULL,NULL,NULL),(2,'983.0','','Toxic effect of corrosive aromatics',11739,NULL,NULL,NULL),(2,'983.1','','Toxic effect of acids',11740,NULL,NULL,NULL),(2,'983.2','','Toxic effect of caustic alkalis',11741,NULL,NULL,NULL),(2,'983.9','','Toxic effect of caustic unspecified',11742,NULL,NULL,NULL),(2,'984.0','','Toxic effect of inorganic lead compounds',11743,NULL,NULL,NULL),(2,'984.1','','Toxic effect of organic lead compounds',11744,NULL,NULL,NULL),(2,'984.8','','Toxic effect of other lead compounds',11745,NULL,NULL,NULL),(2,'984.9','','Toxic effect of unspecified lead compound',11746,NULL,NULL,NULL),(2,'985.0','','Toxic effect of mercury and its compounds',11747,NULL,NULL,NULL),(2,'985.1','','Toxic effect of arsenic and its compounds',11748,NULL,NULL,NULL),(2,'985.2','','Toxic effect of manganese and its compounds',11749,NULL,NULL,NULL),(2,'985.3','','Toxic effect of beryllium and its compounds',11750,NULL,NULL,NULL),(2,'985.4','','Toxic effect of antimony and its compounds',11751,NULL,NULL,NULL),(2,'985.5','','Toxic effect of cadmium and its compounds',11752,NULL,NULL,NULL),(2,'985.6','','Toxic effect of chromium',11753,NULL,NULL,NULL),(2,'985.8','','Toxic effect of other specified metals',11754,NULL,NULL,NULL),(2,'985.9','','Toxic effect of unspecified metal',11755,NULL,NULL,NULL),(2,'986','','Toxic effect of carbon monoxide',11756,NULL,NULL,NULL),(2,'987.0','','Toxic effect of liquefied petroleum gases',11757,NULL,NULL,NULL),(2,'987.1','','Toxic effect of other hydrocarbon gas',11758,NULL,NULL,NULL),(2,'987.2','','Toxic effect of nitrogen oxides',11759,NULL,NULL,NULL),(2,'987.3','','Toxic effect of sulfur dioxide',11760,NULL,NULL,NULL),(2,'987.4','','Toxic effect of freon',11761,NULL,NULL,NULL),(2,'987.5','','Toxic effect of lacrimogenic gas',11762,NULL,NULL,NULL),(2,'987.6','','Toxic effect of chlorine gas',11763,NULL,NULL,NULL),(2,'987.7','','Toxic effect of hydrocyanic acid gas',11764,NULL,NULL,NULL),(2,'987.8','','Toxic effect of other specified gases fumes or vapors',11765,NULL,NULL,NULL),(2,'987.9','','Toxic effect of unspecified gas fume or vapor',11766,NULL,NULL,NULL),(2,'988.0','','Toxic effect of fish and shellfish eaten as food',11767,NULL,NULL,NULL),(2,'988.1','','Toxic effect of mushrooms eaten as food',11768,NULL,NULL,NULL),(2,'988.2','','Toxic effect of berries and other plants eaten as food',11769,NULL,NULL,NULL),(2,'988.8','','Toxic effect of other specified noxious substances eaten as food',11770,NULL,NULL,NULL),(2,'988.9','','Toxic effect of unspecified noxious substance eaten as food',11771,NULL,NULL,NULL),(2,'989.0','','Toxic effect of hydrocyanic acid and cyanides',11772,NULL,NULL,NULL),(2,'989.1','','Toxic effect of strychnine and salts',11773,NULL,NULL,NULL),(2,'989.2','','Toxic effect of chlorinated hydrocarbons',11774,NULL,NULL,NULL),(2,'989.3','','Toxic effect of organophosphate and carbamate',11775,NULL,NULL,NULL),(2,'989.4','','Toxic effect of other pesticides not elsewhere classified',11776,NULL,NULL,NULL),(2,'989.5','','Toxic effect of venom',11777,NULL,NULL,NULL),(2,'989.6','','Toxic effect of soaps and detergents',11778,NULL,NULL,NULL),(2,'989.7','','Toxic effect of aflatoxin and other mycotoxin (food contaminants)',11779,NULL,NULL,NULL),(2,'989.81','','Toxic effect of asbestos',11780,NULL,NULL,NULL),(2,'989.82','','Toxic effect of latex',11781,NULL,NULL,NULL),(2,'989.83','','Toxic effect of silicone',11782,NULL,NULL,NULL),(2,'989.84','','Toxic effect of tobacco',11783,NULL,NULL,NULL),(2,'989.89','','Toxic effect of other substance chiefly nonmedicinal as to source not elsewhere classified',11784,NULL,NULL,NULL),(2,'989.9','','Toxic effect of unspecified substance chiefly nonmedicinal as to source',11785,NULL,NULL,NULL),(2,'990','','Effects of radiation unspecified',11786,NULL,NULL,NULL),(2,'991.0','','Frostbite of face',11787,NULL,NULL,NULL),(2,'991.1','','Frostbite of hand',11788,NULL,NULL,NULL),(2,'991.2','','Frostbite of foot',11789,NULL,NULL,NULL),(2,'991.3','','Frostbite of other and unspecified sites',11790,NULL,NULL,NULL),(2,'991.4','','Immersion foot',11791,NULL,NULL,NULL),(2,'991.5','','Chilblains',11792,NULL,NULL,NULL),(2,'991.6','','Hypothermia',11793,NULL,NULL,NULL),(2,'991.8','','Other specified effects of reduced temperature',11794,NULL,NULL,NULL),(2,'991.9','','Unspecified effect of reduced temperature',11795,NULL,NULL,NULL),(2,'992.0','','Heat stroke and sunstroke',11796,NULL,NULL,NULL),(2,'992.1','','Heat syncope',11797,NULL,NULL,NULL),(2,'992.2','','Heat cramps',11798,NULL,NULL,NULL),(2,'992.3','','Heat exhaustion anhydrotic',11799,NULL,NULL,NULL),(2,'992.4','','Heat exhaustion due to salt depletion',11800,NULL,NULL,NULL),(2,'992.5','','Heat exhaustion unspecified',11801,NULL,NULL,NULL),(2,'992.6','','Heat fatigue transient',11802,NULL,NULL,NULL),(2,'992.7','','Heat edema',11803,NULL,NULL,NULL),(2,'992.8','','Other specified heat effects',11804,NULL,NULL,NULL),(2,'992.9','','Unspecified effects of heat and light',11805,NULL,NULL,NULL),(2,'993.0','','Barotrauma otitic',11806,NULL,NULL,NULL),(2,'993.1','','Barotrauma sinus',11807,NULL,NULL,NULL),(2,'993.2','','Other and unspecified effects of high altitude',11808,NULL,NULL,NULL),(2,'993.3','','Caisson disease',11809,NULL,NULL,NULL),(2,'993.4','','Effects of air pressure caused by explosion',11810,NULL,NULL,NULL),(2,'993.8','','Other specified effects of air pressure',11811,NULL,NULL,NULL),(2,'993.9','','Unspecified effect of air pressure',11812,NULL,NULL,NULL),(2,'994.0','','Effects of lightning',11813,NULL,NULL,NULL),(2,'994.1','','Drowning and nonfatal submersion',11814,NULL,NULL,NULL),(2,'994.2','','Effects of neoplasms',11815,NULL,NULL,NULL),(2,'994.3','','Effects of thirst',11816,NULL,NULL,NULL),(2,'994.4','','Exhaustion due to exposure',11817,NULL,NULL,NULL),(2,'994.5','','Exhaustion due to excessive exertion',11818,NULL,NULL,NULL),(2,'994.6','','Motion sickness',11819,NULL,NULL,NULL),(2,'994.7','','Asphyxiation and strangulation',11820,NULL,NULL,NULL),(2,'994.8','','Electrocution and nonfatal effects of electric current',11821,NULL,NULL,NULL),(2,'994.9','','Other effects of external causes',11822,NULL,NULL,NULL),(2,'995.0','','Other anaphylactic shock not elsewhere classified',11823,NULL,NULL,NULL),(2,'995.1','','Angioneurotic edema not elsewhere classified',11824,NULL,NULL,NULL),(2,'995.20','','Unspecified adverse effect of unspecified drug, medicinal and biological substance',11825,NULL,NULL,NULL),(2,'995.21','','Arthus phenomenon',11826,NULL,NULL,NULL),(2,'995.22','','Unspecified adverse effect of anesthesia',11827,NULL,NULL,NULL),(2,'995.23','','Unspecified adverse effect of insulin',11828,NULL,NULL,NULL),(2,'995.24','','Failed moderate sedation during procedure',11829,NULL,NULL,NULL),(2,'995.27','','Other drug allergy',11830,NULL,NULL,NULL),(2,'995.29','','Unspecified adverse effect of other drug, medicinal and biological substance',11831,NULL,NULL,NULL),(2,'995.3','','Allergy unspecified not elsewhere classified',11832,NULL,NULL,NULL),(2,'995.4','','Shock due to anesthesia not elsewhere classified',11833,NULL,NULL,NULL),(2,'995.50','','Unspecified child abuse',11834,NULL,NULL,NULL),(2,'995.51','','Child emotional/psychological abuse',11835,NULL,NULL,NULL),(2,'995.52','','Child neglect (nutritional)',11836,NULL,NULL,NULL),(2,'995.53','','Child sexual abuse',11837,NULL,NULL,NULL),(2,'995.54','','Child physical abuse',11838,NULL,NULL,NULL),(2,'995.55','','Shaken baby syndrome',11839,NULL,NULL,NULL),(2,'995.59','','Other child abuse and neglect',11840,NULL,NULL,NULL),(2,'995.60','','Anaphylactic shock due to unspecified food',11841,NULL,NULL,NULL),(2,'995.61','','Anaphylactic shock due to peanuts',11842,NULL,NULL,NULL),(2,'995.62','','Anaphylactic shock due to crustaceans',11843,NULL,NULL,NULL),(2,'995.63','','Anaphylactic shock due to fruits and vegetables',11844,NULL,NULL,NULL),(2,'995.64','','Anaphylactic shock due to tree nuts and seeds',11845,NULL,NULL,NULL),(2,'995.65','','Anaphylactic shock due to fish',11846,NULL,NULL,NULL),(2,'995.66','','Anaphylactic shock due to food additives',11847,NULL,NULL,NULL),(2,'995.67','','Anaphylactic shock due to milk products',11848,NULL,NULL,NULL),(2,'995.68','','Anaphylactic shock due to eggs',11849,NULL,NULL,NULL),(2,'995.69','','Anaphylactic shock due to other specified food',11850,NULL,NULL,NULL),(2,'995.7','','Other adverse food reactions not elsewhere classified',11851,NULL,NULL,NULL),(2,'995.80','','Unspecified adult maltreatment',11852,NULL,NULL,NULL),(2,'995.81','','Adult physical abuse',11853,NULL,NULL,NULL),(2,'995.82','','Adult emotional/psychological abuse',11854,NULL,NULL,NULL),(2,'995.83','','Adult sexual abuse',11855,NULL,NULL,NULL),(2,'995.84','','Adult neglect (nutritional)',11856,NULL,NULL,NULL),(2,'995.85','','Other adult abuse and neglect',11857,NULL,NULL,NULL),(2,'995.86','','Malignant hyperthermia',11858,NULL,NULL,NULL),(2,'995.89','','Other specified adverse effects not elsewhere classified',11859,NULL,NULL,NULL),(2,'995.90','','Systemic inflammatory response syndrome unspecified',11860,NULL,NULL,NULL),(2,'995.91','','Systemic inflammatory response syndrome due to infectious process without organ dysfunction',11861,NULL,NULL,NULL),(2,'995.92','','Systemic inflammatory response syndrome due to infectious process with organ dysfunction',11862,NULL,NULL,NULL),(2,'995.93','','Systemic inflammatory response syndrome due to noninfectious process without organ dysfunction',11863,NULL,NULL,NULL),(2,'995.94','','Systemic inflammatory response syndrome due to noninfectious process with organ dysfunction',11864,NULL,NULL,NULL),(2,'996.00','','Mechanical complications of unspecified cardiac device implant and graft',11865,NULL,NULL,NULL),(2,'996.01','','Mechanical complication due to cardiac pacemaker (electrode)',11866,NULL,NULL,NULL),(2,'996.02','','Mechanical complication due to heart valve prosthesis',11867,NULL,NULL,NULL),(2,'996.03','','Mechanical complication due to coronary bypass graft',11868,NULL,NULL,NULL),(2,'996.04','','Mechanical complication of automatic implantable cardiac defibrillator',11869,NULL,NULL,NULL),(2,'996.09','','Other mechanical complication of cardiac device implant and graft',11870,NULL,NULL,NULL),(2,'996.1','','Mechanical complication of other vascular device implant and graft',11871,NULL,NULL,NULL),(2,'996.2','','Mechanical complication of nervous system device implant and graft',11872,NULL,NULL,NULL),(2,'996.30','','Mechanical complication of unspecified genitourinary device implant and graft',11873,NULL,NULL,NULL),(2,'996.31','','Mechanical complication due to urethral (indwelling) catheter',11874,NULL,NULL,NULL),(2,'996.32','','Mechanical complication due to intrauterine contraceptive device',11875,NULL,NULL,NULL),(2,'996.39','','Other mechanical complication of genitourinary device implant and graft',11876,NULL,NULL,NULL),(2,'996.40','','Unspecified mechanical complication of internal orthopedic device, implant and graft',11877,NULL,NULL,NULL),(2,'996.41','','Mechanical loosening of prosthetic joint',11878,NULL,NULL,NULL),(2,'996.42','','Dislocation of prosthetic joint',11879,NULL,NULL,NULL),(2,'996.43','','Broken prosthetic joint implant',11880,NULL,NULL,NULL),(2,'996.44','','Peri-prosthetic fracture around prosthetic joint',11881,NULL,NULL,NULL),(2,'996.45','','Peri-prosthetic osteolysis',11882,NULL,NULL,NULL),(2,'996.46','','Articular bearing surface wear of prosthetic joint',11883,NULL,NULL,NULL),(2,'996.47','','Other mechanical complication of prosthetic joint implant',11884,NULL,NULL,NULL),(2,'996.49','','Other mechanical complication of other internal orthopedic device, implant, and graft',11885,NULL,NULL,NULL),(2,'996.51','','Mechanical complication of prosthetic corneal graft',11886,NULL,NULL,NULL),(2,'996.52','','Mechanical complication of prosthetic graft of other tissue not elsewhere classified',11887,NULL,NULL,NULL),(2,'996.53','','Mechanical complication of prosthetic ocular lens prosthesis',11888,NULL,NULL,NULL),(2,'996.54','','Mechanical complication of breast prosthesis',11889,NULL,NULL,NULL),(2,'996.55','','Mechanical complication due to artificial skin graft and decellularized allodermis',11890,NULL,NULL,NULL),(2,'996.56','','Mechanical complication due to peritoneal dialysis catheter',11891,NULL,NULL,NULL),(2,'996.57','','Mechanical complication due to insulin pump',11892,NULL,NULL,NULL),(2,'996.59','','Mechanical complication of other implant and internal device not elsewhere classified',11893,NULL,NULL,NULL),(2,'996.60','','Infection and inflammatory reaction due to unspecified device implant and graft',11894,NULL,NULL,NULL),(2,'996.61','','Infection and inflammatory reaction due to cardiac device implant and graft',11895,NULL,NULL,NULL),(2,'996.62','','Infection and inflammatory reaction due vascular device, implant and graft',11896,NULL,NULL,NULL),(2,'996.63','','Infection and inflammatory reaction due to nervous system device implant and graft',11897,NULL,NULL,NULL),(2,'996.64','','Infection and inflammatory reaction due to indwelling urinary catheter',11898,NULL,NULL,NULL),(2,'996.65','','Infection and inflammatory reaction due to other genitourinary device implant and graft',11899,NULL,NULL,NULL),(2,'996.66','','Infection and inflammatory reaction due to internal joint prosthesis',11900,NULL,NULL,NULL),(2,'996.67','','Infection and inflammatory reaction due to other internal orthopedic device implant and graft',11901,NULL,NULL,NULL),(2,'996.68','','Infection and inflammatory reaction due to peritoneal dialysis catheter',11902,NULL,NULL,NULL),(2,'996.69','','Infection and inflammatory reaction due to other internal prosthetic device implant and graft',11903,NULL,NULL,NULL),(2,'996.70','','Other complications due to unspecified device implant and graft',11904,NULL,NULL,NULL),(2,'996.71','','Other complications due to heart valve prosthesis',11905,NULL,NULL,NULL),(2,'996.72','','Other complications due to other cardiac device implant and graft',11906,NULL,NULL,NULL),(2,'996.73','','Other complications due to renal dialysis device implant and graft',11907,NULL,NULL,NULL),(2,'996.74','','Other complications due to other vascular device implant and graft',11908,NULL,NULL,NULL),(2,'996.75','','Other complications due to nervous system device implant and graft',11909,NULL,NULL,NULL),(2,'996.76','','Other complications due to genitourinary device implant and graft',11910,NULL,NULL,NULL),(2,'996.77','','Other complications due to internal joint prosthesis',11911,NULL,NULL,NULL),(2,'996.78','','Other complications due to other internal orthopedic device implant and graft',11912,NULL,NULL,NULL),(2,'996.79','','Other complications due to other internal prosthetic device implant and graft',11913,NULL,NULL,NULL),(2,'996.80','','Complications of unspecified transplanted organ',11914,NULL,NULL,NULL),(2,'996.81','','Complications of transplanted kidney',11915,NULL,NULL,NULL),(2,'996.82','','Complications of transplanted liver',11916,NULL,NULL,NULL),(2,'996.83','','Complications of transplanted heart',11917,NULL,NULL,NULL),(2,'996.84','','Complications of transplanted lung',11918,NULL,NULL,NULL),(2,'996.85','','Complications of transplanted bone marrow',11919,NULL,NULL,NULL),(2,'996.86','','Complications of transplanted pancreas',11920,NULL,NULL,NULL),(2,'996.87','','Complications of transplanted organ intestine',11921,NULL,NULL,NULL),(2,'996.89','','Complications of other specified transplanted organ',11922,NULL,NULL,NULL),(2,'996.90','','Complications of unspecified reattached extremity',11923,NULL,NULL,NULL),(2,'996.91','','Complications of reattached forearm',11924,NULL,NULL,NULL),(2,'996.92','','Complications of reattached hand',11925,NULL,NULL,NULL),(2,'996.93','','Complications of reattached finger(s)',11926,NULL,NULL,NULL),(2,'996.94','','Complications of reattached upper extremity other and unspecified',11927,NULL,NULL,NULL),(2,'996.95','','Complication of reattached foot and toe(s)',11928,NULL,NULL,NULL),(2,'996.96','','Complication of reattached lower extremity other and unspecified',11929,NULL,NULL,NULL),(2,'996.99','','Complication of other specified reattached body part',11930,NULL,NULL,NULL),(2,'997.00','','Nervous system complication unspecified',11931,NULL,NULL,NULL),(2,'997.01','','Central nervous system complication',11932,NULL,NULL,NULL),(2,'997.02','','Iatrogenic cerebrovascular infarction or hemorrhage',11933,NULL,NULL,NULL),(2,'997.09','','Other nervous system complications',11934,NULL,NULL,NULL),(2,'997.1','','Cardiac complications not elsewhere classified',11935,NULL,NULL,NULL),(2,'997.2','','Peripheral vascular complications not elsewhere classified',11936,NULL,NULL,NULL),(2,'997.31','','Ventilator associated pneumonia',11937,NULL,NULL,NULL),(2,'997.39','','Other respiratory complications',11938,NULL,NULL,NULL),(2,'997.4','','Digestive system complications not elsewhere classified',11939,NULL,NULL,NULL),(2,'997.5','','Urinary complications not elsewhere classified',11940,NULL,NULL,NULL),(2,'997.60','','Unspecified late complication of amputation stump',11941,NULL,NULL,NULL),(2,'997.61','','Neuroma of amputation stump',11942,NULL,NULL,NULL),(2,'997.62','','Infection (chronic) of amputation stump',11943,NULL,NULL,NULL),(2,'997.69','','Other late amputation stump complication',11944,NULL,NULL,NULL),(2,'997.71','','Vascular complications of mesenteric artery',11945,NULL,NULL,NULL),(2,'997.72','','Vascular complications of renal artery',11946,NULL,NULL,NULL),(2,'997.79','','Vascular complications of other vessels',11947,NULL,NULL,NULL),(2,'997.91','','Complications affecting other specified body systems not elsewhere classified hypertension',11948,NULL,NULL,NULL),(2,'997.99','','Complications affecting other specified body systems not elsewhere classified',11949,NULL,NULL,NULL),(2,'998.0','','Postoperative shock not elsewhere classified',11950,NULL,NULL,NULL),(2,'998.11','','Hemorrhage complicating a procedure',11951,NULL,NULL,NULL),(2,'998.12','','Hematoma complicating a procedure',11952,NULL,NULL,NULL),(2,'998.13','','Seroma complicating a procedure',11953,NULL,NULL,NULL),(2,'998.2','','Accidental puncture or laceration during a procedure not elsewhere classified',11954,NULL,NULL,NULL),(2,'998.30','','Disruption of wound, unspecified',11955,NULL,NULL,NULL),(2,'998.31','','Disruption of internal operation (surgical) wound',11956,NULL,NULL,NULL),(2,'998.32','','Disruption of external operation (surgical) wound',11957,NULL,NULL,NULL),(2,'998.33','','Disruption of traumatic wound repair',11958,NULL,NULL,NULL),(2,'998.4','','Foreign body accidentally left during a procedure not elsewhere classified',11959,NULL,NULL,NULL),(2,'998.51','','Infected postoperative seroma',11960,NULL,NULL,NULL),(2,'998.59','','Other postoperative infection',11961,NULL,NULL,NULL),(2,'998.6','','Persistent postoperative fistula not elsewhere classified',11962,NULL,NULL,NULL),(2,'998.7','','Acute reaction to foreign substance accidentally left during a procedure not elsewhere classified',11963,NULL,NULL,NULL),(2,'998.81','','Emphysema (subcutaneous) (surgical) resulting from procedure',11964,NULL,NULL,NULL),(2,'998.82','','Cataract fragments in eye following cataract surgery',11965,NULL,NULL,NULL),(2,'998.83','','Non-healing surgical wound',11966,NULL,NULL,NULL),(2,'998.89','','Other specified complications of procedures not elsewhere classified',11967,NULL,NULL,NULL),(2,'998.9','','Unspecified complication of procedure not elsewhere classified',11968,NULL,NULL,NULL),(2,'999.0','','Generalized vaccinia as a complication of medical care not elsewhere classified',11969,NULL,NULL,NULL),(2,'999.1','','Air embolism as a complication of medical care not elsewhere classified',11970,NULL,NULL,NULL),(2,'999.2','','Other vascular complications of medical care not elsewhere classified',11971,NULL,NULL,NULL),(2,'999.31','','Infection due to central venous catheter',11972,NULL,NULL,NULL),(2,'999.39','','Infection following other infusion, injection, transfusion, or vaccination',11973,NULL,NULL,NULL),(2,'999.4','','Anaphylactic shock due to serum not elsewhere classified',11974,NULL,NULL,NULL),(2,'999.5','','Other serum reaction not elsewhere classified',11975,NULL,NULL,NULL),(2,'999.6','','Abo incompatibility reaction not elsewhere classified',11976,NULL,NULL,NULL),(2,'999.7','','Rh incompatibility reaction not elsewhere classified',11977,NULL,NULL,NULL),(2,'999.81','','Extravasation of vesicant chemotherapy',11978,NULL,NULL,NULL),(2,'999.82','','Extravasation of other vesicant agent',11979,NULL,NULL,NULL),(2,'999.88','','Other infusion reaction',11980,NULL,NULL,NULL),(2,'999.89','','Other transfusion reaction',11981,NULL,NULL,NULL),(2,'999.9','','Other and unspecified complications of medical care not elsewhere classified',11982,NULL,NULL,NULL),(2,'E000.0','','Civilian activity done for income or pay',11983,NULL,NULL,NULL),(2,'E000.1','','Military activity',11984,NULL,NULL,NULL),(2,'E000.8','','Other external cause status',11985,NULL,NULL,NULL),(2,'E000.9','','Unspecified external cause status',11986,NULL,NULL,NULL),(2,'E001.0','','Activities involving walking, marching and hiking',11987,NULL,NULL,NULL),(2,'E001.1','','Activities involving running',11988,NULL,NULL,NULL),(2,'E002.0','','Activities involving swimming',11989,NULL,NULL,NULL),(2,'E002.1','','Activities involving springboard and platform diving',11990,NULL,NULL,NULL),(2,'E002.2','','Activities involving water polo',11991,NULL,NULL,NULL),(2,'E002.3','','Activities involving water aerobics and water exercise',11992,NULL,NULL,NULL),(2,'E002.4','','Activities involving underwater diving and snorkeling',11993,NULL,NULL,NULL),(2,'E002.5','','Activities involving rowing, canoeing, kayaking, rafting and tubing',11994,NULL,NULL,NULL),(2,'E002.6','','Activities involving water skiing and wake boarding',11995,NULL,NULL,NULL),(2,'E002.7','','Activities involving surfing, windsurfing and boogie boarding',11996,NULL,NULL,NULL),(2,'E002.8','','Activities involving water sliding',11997,NULL,NULL,NULL),(2,'E002.9','','Other activity involving water and watercraft',11998,NULL,NULL,NULL),(2,'E003.0','','Activities involving ice skating',11999,NULL,NULL,NULL),(2,'E003.1','','Activities involving ice hockey',12000,NULL,NULL,NULL),(2,'E003.2','','Activities involving snow (alpine) (downhill) skiing, snow boarding, sledding, tobogganing and snow tubing',12001,NULL,NULL,NULL),(2,'E003.3','','Activities involving cross country skiing',12002,NULL,NULL,NULL),(2,'E003.9','','Other activity involving ice and snow',12003,NULL,NULL,NULL),(2,'E004.0','','Activities involving mountain climbing, rock climbing and wall climbing',12004,NULL,NULL,NULL),(2,'E004.1','','Activities involving rappelling',12005,NULL,NULL,NULL),(2,'E004.2','','Activities involving climbing base jumping',12006,NULL,NULL,NULL),(2,'E004.3','','Activities involving climbing bungee jumping',12007,NULL,NULL,NULL),(2,'E004.4','','Activities involving hang gliding',12008,NULL,NULL,NULL),(2,'E004.9','','Other activity involving climbing, rappelling and jumping off',12009,NULL,NULL,NULL),(2,'E005.0','','Activities involving dancing',12010,NULL,NULL,NULL),(2,'E005.1','','Activities involving yoga',12011,NULL,NULL,NULL),(2,'E005.2','','Activities involving gymnastics',12012,NULL,NULL,NULL),(2,'E005.3','','Activities involving trampoline',12013,NULL,NULL,NULL),(2,'E005.4','','Activities involving cheerleading',12014,NULL,NULL,NULL),(2,'E005.9','','Other activity involving dancing and other rhythmic movements',12015,NULL,NULL,NULL),(2,'E006.0','','Activities involving roller skating (inline) and skateboarding',12016,NULL,NULL,NULL),(2,'E006.1','','Activities involving horseback riding',12017,NULL,NULL,NULL),(2,'E006.2','','Activities involving golf',12018,NULL,NULL,NULL),(2,'E006.3','','Activities involving bowling',12019,NULL,NULL,NULL),(2,'E006.4','','Activities involving bike riding',12020,NULL,NULL,NULL),(2,'E006.5','','Activities involving jump rope',12021,NULL,NULL,NULL),(2,'E006.6','','Activities involving non-running track and field events',12022,NULL,NULL,NULL),(2,'E006.9','','Other activity involving other sports and athletics played individually',12023,NULL,NULL,NULL),(2,'E007.0','','Activities involving american tackle football',12024,NULL,NULL,NULL),(2,'E007.1','','Activities involving american flag or touch football',12025,NULL,NULL,NULL),(2,'E007.2','','Activities involving rugby',12026,NULL,NULL,NULL),(2,'E007.3','','Activities involving baseball',12027,NULL,NULL,NULL),(2,'E007.4','','Activities involving lacrosse and field hockey',12028,NULL,NULL,NULL),(2,'E007.5','','Activities involving soccer',12029,NULL,NULL,NULL),(2,'E007.6','','Activities involving basketball',12030,NULL,NULL,NULL),(2,'E007.7','','Activities involving volleyball (beach) (court)',12031,NULL,NULL,NULL),(2,'E007.8','','Activities involving physical games generally associated with school recess, summer camp and children',12032,NULL,NULL,NULL),(2,'E007.9','','Other activity involving other sports and athletes played as a team or group',12033,NULL,NULL,NULL),(2,'E008.0','','Activities involving boxing',12034,NULL,NULL,NULL),(2,'E008.1','','Activities involving wrestling',12035,NULL,NULL,NULL),(2,'E008.2','','Activities involving racquet and hand sports',12036,NULL,NULL,NULL),(2,'E008.3','','Activities involving frisbee',12037,NULL,NULL,NULL),(2,'E008.4','','Activities involving martial arts',12038,NULL,NULL,NULL),(2,'E008.9','','Other specified sports and athletics activities',12039,NULL,NULL,NULL),(2,'E009.0','','Activity involving exercise machines primarily for cardiorespiratory conditioning',12040,NULL,NULL,NULL),(2,'E009.1','','Activity involving calisthenics',12041,NULL,NULL,NULL),(2,'E009.2','','Activity involving aerobic and step exercise',12042,NULL,NULL,NULL),(2,'E009.3','','Activity involving circuit training',12043,NULL,NULL,NULL),(2,'E009.4','','Activity involving obstacle course',12044,NULL,NULL,NULL),(2,'E009.5','','Activity involving grass drills',12045,NULL,NULL,NULL),(2,'E009.9','','Other activity involving cardiorespiratory exercise',12046,NULL,NULL,NULL),(2,'E010.0','','Activity involving exercise machines primarily for muscle strengthening',12047,NULL,NULL,NULL),(2,'E010.1','','Activity involving push-ups, pull-ups, sit-ups',12048,NULL,NULL,NULL),(2,'E010.2','','Activity involving free weights',12049,NULL,NULL,NULL),(2,'E010.3','','Activity involving pilates',12050,NULL,NULL,NULL),(2,'E010.9','','Other activity involving other muscle strengthening exercises',12051,NULL,NULL,NULL),(2,'E011.0','','Activities involving computer keyboarding',12052,NULL,NULL,NULL),(2,'E011.1','','Activities involving hand held interactive electronic device',12053,NULL,NULL,NULL),(2,'E011.9','','Other activity involving computer technology and electronic devices',12054,NULL,NULL,NULL),(2,'E012.0','','Activities involving knitting and crocheting',12055,NULL,NULL,NULL),(2,'E012.1','','Activities involving sewing',12056,NULL,NULL,NULL),(2,'E012.2','','Activities involving furniture building and finishing',12057,NULL,NULL,NULL),(2,'E012.9','','Activity involving other arts and handcrafts',12058,NULL,NULL,NULL),(2,'E013.0','','Activities involving personal bathing and showering',12059,NULL,NULL,NULL),(2,'E013.1','','Activities involving laundry',12060,NULL,NULL,NULL),(2,'E013.2','','Activities involving vacuuming',12061,NULL,NULL,NULL),(2,'E013.3','','Activities involving ironing',12062,NULL,NULL,NULL),(2,'E013.4','','Activities involving floor mopping and cleaning',12063,NULL,NULL,NULL),(2,'E013.5','','Activities involving residential relocation',12064,NULL,NULL,NULL),(2,'E013.8','','Other personal hygiene activity',12065,NULL,NULL,NULL),(2,'E013.9','','Other household maintenance',12066,NULL,NULL,NULL),(2,'E014.0','','Caregiving involving bathing',12067,NULL,NULL,NULL),(2,'E014.1','','Caregiving involving lifting',12068,NULL,NULL,NULL),(2,'E014.9','','Other activity involving person providing caregiving',12069,NULL,NULL,NULL),(2,'E015.0','','Activities involving food preparation and clean up',12070,NULL,NULL,NULL),(2,'E015.1','','Activities involving grilling and smoking food',12071,NULL,NULL,NULL),(2,'E015.2','','Activities involving cooking and baking',12072,NULL,NULL,NULL),(2,'E015.9','','Other activity involving cooking and grilling',12073,NULL,NULL,NULL),(2,'E016.0','','Activities involving digging, shoveling and raking',12074,NULL,NULL,NULL),(2,'E016.1','','Activities involving gardening and landscaping',12075,NULL,NULL,NULL),(2,'E016.2','','Activities involving building and construction',12076,NULL,NULL,NULL),(2,'E016.9','','Other activity involving property and land maintenance, building and construction',12077,NULL,NULL,NULL),(2,'E017.0','','Activities involving roller coaster riding',12078,NULL,NULL,NULL),(2,'E017.9','','Other activity involving external motion',12079,NULL,NULL,NULL),(2,'E018.0','','Activities involving piano playing',12080,NULL,NULL,NULL),(2,'E018.1','','Activities involving drum and other percussion instrument playing',12081,NULL,NULL,NULL),(2,'E018.2','','Activities involving string instrument playing',12082,NULL,NULL,NULL),(2,'E018.3','','Activities involving winds and brass instrument playing',12083,NULL,NULL,NULL),(2,'E019.0','','Activities involving walking an animal',12084,NULL,NULL,NULL),(2,'E019.1','','Activities involving milking an animal',12085,NULL,NULL,NULL),(2,'E019.2','','Activities involving grooming and shearing an animal',12086,NULL,NULL,NULL),(2,'E019.9','','Other activity involving animal care',12087,NULL,NULL,NULL),(2,'E029.0','','Refereeing a sports activity',12088,NULL,NULL,NULL),(2,'E029.1','','Spectator at an event',12089,NULL,NULL,NULL),(2,'E029.2','','Rough housing and horseplay',12090,NULL,NULL,NULL),(2,'E029.9','','Other activity',12091,NULL,NULL,NULL),(2,'E030','','Unspecified activity',12092,NULL,NULL,NULL),(2,'E800.0','','Railway accident involving collision with rolling stock and injuring railway employee',12093,NULL,NULL,NULL),(2,'E800.1','','Railway accident involving collision with rolling stock and injuring passenger on railway',12094,NULL,NULL,NULL),(2,'E800.2','','Railway accident involving collision with rolling stock and injuring pedestrian',12095,NULL,NULL,NULL),(2,'E800.3','','Railway accident involving collision with rolling stock and injuring pedal cyclist',12096,NULL,NULL,NULL),(2,'E800.8','','Railway accident involving collision with rolling stock and injuring other specified person',12097,NULL,NULL,NULL),(2,'E800.9','','Railway accident involving collision with rolling stock and injuring unspecified person',12098,NULL,NULL,NULL),(2,'E801.0','','Railway accident involving collision with other object and injuring railway employee',12099,NULL,NULL,NULL),(2,'E801.1','','Railway accident involving collision with other object and injuring passenger on railway',12100,NULL,NULL,NULL),(2,'E801.2','','Railway accident involving collision with other object and injuring pedestrian',12101,NULL,NULL,NULL),(2,'E801.3','','Railway accident involving collision with other object and injuring pedal cyclist',12102,NULL,NULL,NULL),(2,'E801.8','','Railway accident involving collision with other object and injuring other specified person',12103,NULL,NULL,NULL),(2,'E801.9','','Railway accident involving collision with other object and injuring unspecified person',12104,NULL,NULL,NULL),(2,'E802.0','','Railway accident involving derailment without antecedent collision injuring railway employee',12105,NULL,NULL,NULL),(2,'E802.1','','Railway accident involving derailment without antecedent collision injuring passenger on railway',12106,NULL,NULL,NULL),(2,'E802.2','','Railway accident involving derailment without antecedent collision injuring pedestrian',12107,NULL,NULL,NULL),(2,'E802.3','','Railway accident involving derailment without antecedent collision injuring pedal cyclist',12108,NULL,NULL,NULL),(2,'E802.8','','Railway accident involving derailment without antecedent collision injuring other specified person',12109,NULL,NULL,NULL),(2,'E802.9','','Railway accident involving derailment without antecedent collision injuring unspecified person',12110,NULL,NULL,NULL),(2,'E803.0','','Railway accident involving explosion fire or burning injuring railway employee',12111,NULL,NULL,NULL),(2,'E803.1','','Railway accident involving explosion fire or burning injuring passenger on railway',12112,NULL,NULL,NULL),(2,'E803.2','','Railway accident involving explosion fire or burning injuring pedestrian',12113,NULL,NULL,NULL),(2,'E803.3','','Railway accident involving explosion fire or burning injuring pedal cyclist',12114,NULL,NULL,NULL),(2,'E803.8','','Railway accident involving explosion fire or burning injuring other specified person',12115,NULL,NULL,NULL),(2,'E803.9','','Railway accident involving explosion fire or burning injuring unspecified person',12116,NULL,NULL,NULL),(2,'E804.0','','Fall in on or from railway train injuring railway employee',12117,NULL,NULL,NULL),(2,'E804.1','','Fall in on or from railway train injuring passenger on railway',12118,NULL,NULL,NULL),(2,'E804.2','','Fall in on or from railway train injuring pedestrian',12119,NULL,NULL,NULL),(2,'E804.3','','Fall in on or from railway train injuring pedal cyclist',12120,NULL,NULL,NULL),(2,'E804.8','','Fall in on or from railway train injuring other specified person',12121,NULL,NULL,NULL),(2,'E804.9','','Fall in on or from railway train injuring unspecified person',12122,NULL,NULL,NULL),(2,'E805.0','','Railway employee hit by rolling stock',12123,NULL,NULL,NULL),(2,'E805.1','','Passenger on railway hit by rolling stock',12124,NULL,NULL,NULL),(2,'E805.2','','Pedestrian hit by rolling stock',12125,NULL,NULL,NULL),(2,'E805.3','','Pedal cyclist hit by rolling stock',12126,NULL,NULL,NULL),(2,'E805.8','','Other specified person hit by rolling stock',12127,NULL,NULL,NULL),(2,'E805.9','','Unspecified person hit by rolling stock',12128,NULL,NULL,NULL),(2,'E806.0','','Other specified railway accident injuring railway employee',12129,NULL,NULL,NULL),(2,'E806.1','','Other specified railway accident injuring passenger on railway',12130,NULL,NULL,NULL),(2,'E806.2','','Other specified railway accident injuring pedestrian',12131,NULL,NULL,NULL),(2,'E806.3','','Other specified railway accident injuring pedal cyclist',12132,NULL,NULL,NULL),(2,'E806.8','','Other specified railway accident injuring other specified person',12133,NULL,NULL,NULL),(2,'E806.9','','Other specified railway accident injuring unspecified person',12134,NULL,NULL,NULL),(2,'E807.0','','Railway accident of unspecified nature injuring railway employee',12135,NULL,NULL,NULL),(2,'E807.1','','Railway accident of unspecified nature injuring passenger on railway',12136,NULL,NULL,NULL),(2,'E807.2','','Railway accident of unspecified nature injuring pedestrian',12137,NULL,NULL,NULL),(2,'E807.3','','Railway accident of unspecified nature injuring pedal cyclist',12138,NULL,NULL,NULL),(2,'E807.8','','Railway accident of unspecified nature injuring other specified person',12139,NULL,NULL,NULL),(2,'E807.9','','Railway accident of unspecified nature injuring unspecified person',12140,NULL,NULL,NULL),(2,'E810.0','','Motor vehicle traffic accident involving collision with train injuring driver of motor vehicle other than motorcycle',12141,NULL,NULL,NULL),(2,'E810.1','','Motor vehicle traffic accident involving collision with train injuring passenger in motor vehicle other than motorcycle',12142,NULL,NULL,NULL),(2,'E810.2','','Motor vehicle traffic accident involving collision with train injuring motorcyclist',12143,NULL,NULL,NULL),(2,'E810.3','','Motor vehicle traffic accident involving collision with train injuring passenger on motorcycle',12144,NULL,NULL,NULL),(2,'E810.4','','Motor vehicle traffic accident involving collision with train injuring occupant of streetcar',12145,NULL,NULL,NULL),(2,'E810.5','','Motor vehicle traffic accident involving collision with train injuring rider of animal; occupant of animal-drawn vehicle',12146,NULL,NULL,NULL),(2,'E810.6','','Motor vehicle traffic accident involving collision with train injuring pedal cyclist',12147,NULL,NULL,NULL),(2,'E810.7','','Motor vehicle traffic accident involving collision with train injuring pedestrian',12148,NULL,NULL,NULL),(2,'E810.8','','Motor vehicle traffic accident involving collision with train injuring other specified person',12149,NULL,NULL,NULL),(2,'E810.9','','Motor vehicle traffic accident involving collision with train injuring unspecified person',12150,NULL,NULL,NULL),(2,'E811.0','','Motor vehicle traffic accident involving re-entrant collision with another motor vehicle injuring driver of motor vehicle other than motorcycle',12151,NULL,NULL,NULL),(2,'E811.1','','Motor vehicle traffic accident involving re-entrant collision with another motor vehicle injuring passenger in motor vehicle other than motorcycle',12152,NULL,NULL,NULL),(2,'E811.2','','Motor vehicle traffic accident involving re-entrant collision with another motor vehicle injuring motorcyclist',12153,NULL,NULL,NULL),(2,'E811.3','','Motor vehicle traffic accident involving re-entrant collision with another motor vehicle injuring passenger on motorcycle',12154,NULL,NULL,NULL),(2,'E811.4','','Motor vehicle traffic accident involving re-entrant collision with another motor vehicle injuring occupant of streetcar',12155,NULL,NULL,NULL),(2,'E811.5','','Motor vehicle traffic accident involving re-entrant collision with another motor vehicle injuring rider of animal; occupant of animal-drawn vehicle',12156,NULL,NULL,NULL),(2,'E811.6','','Motor vehicle traffic accident involving re-entrant collision with another motor vehicle injuring pedal cyclist',12157,NULL,NULL,NULL),(2,'E811.7','','Motor vehicle traffic accident involving re-entrant collision with another motor vehicle injuring pedestrian',12158,NULL,NULL,NULL),(2,'E811.8','','Motor vehicle traffic accident involving re-entrant collision with another motor vehicle injuring other specified person',12159,NULL,NULL,NULL),(2,'E811.9','','Motor vehicle traffic accident involving re-entrant collision with another motor vehicle injuring unspecified person',12160,NULL,NULL,NULL),(2,'E812.0','','Other motor vehicle traffic accident involving collision with motor vehicle injuring driver of motor vehicle other than motorcycle',12161,NULL,NULL,NULL),(2,'E812.1','','Other motor vehicle traffic accident involving collision with motor vehicle injuring passenger in motor vehicle other than motorcycle',12162,NULL,NULL,NULL),(2,'E812.2','','Other motor vehicle traffic accident involving collision with motor vehicle injuring motorcyclist',12163,NULL,NULL,NULL),(2,'E812.3','','Other motor vehicle traffic accident involving collision with motor vehicle injuring passenger on motorcycle',12164,NULL,NULL,NULL),(2,'E812.4','','Other motor vehicle traffic accident involving collision with motor vehicle injuring occupant of streetcar',12165,NULL,NULL,NULL),(2,'E812.5','','Other motor vehicle traffic accident involving collision with motor vehicle injuring rider of animal; occupant of animal-drawn vehicle',12166,NULL,NULL,NULL),(2,'E812.6','','Other motor vehicle traffic accident involving collision with motor vehicle injuring pedal cyclist',12167,NULL,NULL,NULL),(2,'E812.7','','Other motor vehicle traffic accident involving collision with motor vehicle injuring pedestrian',12168,NULL,NULL,NULL),(2,'E812.8','','Other motor vehicle traffic accident involving collision with motor vehicle injuring other specified person',12169,NULL,NULL,NULL),(2,'E812.9','','Other motor vehicle traffic accident involving collision with motor vehicle injuring unspecified person',12170,NULL,NULL,NULL),(2,'E813.0','','Motor vehicle traffic accident involving collision with other vehicle injuring driver of motor vehicle other than motorcycle',12171,NULL,NULL,NULL),(2,'E813.1','','Motor vehicle traffic accident involving collision with other vehicle injuring passenger in motor vehicle other than motorcycle',12172,NULL,NULL,NULL),(2,'E813.2','','Motor vehicle traffic accident involving collision with other vehicle injuring motorcyclist',12173,NULL,NULL,NULL),(2,'E813.3','','Motor vehicle traffic accident involving collision with other vehicle injuring passenger on motorcycle',12174,NULL,NULL,NULL),(2,'E813.4','','Motor vehicle traffic accident involving collision with other vehicle injuring occupant of streetcar',12175,NULL,NULL,NULL),(2,'E813.5','','Motor vehicle traffic accident involving collision with other vehicle injuring rider of animal; occupant of animal-drawn vehicle',12176,NULL,NULL,NULL),(2,'E813.6','','Motor vehicle traffic accident involving collision with other vehicle injuring pedal cyclist',12177,NULL,NULL,NULL),(2,'E813.7','','Motor vehicle traffic accident involving collision with other vehicle injuring pedestrian',12178,NULL,NULL,NULL),(2,'E813.8','','Motor vehicle traffic accident involving collision with other vehicle injuring other specified person',12179,NULL,NULL,NULL),(2,'E813.9','','Motor vehicle traffic accident involving collision with other vehicle injuring unspecified person',12180,NULL,NULL,NULL),(2,'E814.0','','Motor vehicle traffic accident involving collision with pedestrian injuring driver of motor vehicle other than motorcycle',12181,NULL,NULL,NULL),(2,'E814.1','','Motor vehicle traffic accident involving collision with pedestrian injuring passenger in motor vehicle other than motorcycle',12182,NULL,NULL,NULL),(2,'E814.2','','Motor vehicle traffic accident involving collision with pedestrian injuring motorcyclist',12183,NULL,NULL,NULL),(2,'E814.3','','Motor vehicle traffic accident involving collision with pedestrian injuring passenger on motorcycle',12184,NULL,NULL,NULL),(2,'E814.4','','Motor vehicle traffic accident involving collision with pedestrian injuring occupant of streetcar',12185,NULL,NULL,NULL),(2,'E814.5','','Motor vehicle traffic accident involving collision with pedestrian injuring rider of animal; occupant of animal drawn vehicle',12186,NULL,NULL,NULL),(2,'E814.6','','Motor vehicle traffic accident involving collision with pedestrian injuring pedal cyclist',12187,NULL,NULL,NULL),(2,'E814.7','','Motor vehicle traffic accident involving collision with pedestrian injuring pedestrian',12188,NULL,NULL,NULL),(2,'E814.8','','Motor vehicle traffic accident involving collision with pedestrian injuring other specified person',12189,NULL,NULL,NULL),(2,'E814.9','','Motor vehicle traffic accident involving collision with pedestrian injuring unspecified person',12190,NULL,NULL,NULL),(2,'E815.0','','Other motor vehicle traffic accident involving collision on the highway injuring driver of motor vehicle other than motorcycle',12191,NULL,NULL,NULL),(2,'E815.1','','Other motor vehicle traffic accident involving collision on the highway injuring passenger in motor vehicle other than motorcycle',12192,NULL,NULL,NULL),(2,'E815.2','','Other motor vehicle traffic accident involving collision on the highway injuring motorcyclist',12193,NULL,NULL,NULL),(2,'E815.3','','Other motor vehicle traffic accident involving collision on the highway injuring passenger on motorcycle',12194,NULL,NULL,NULL),(2,'E815.4','','Other motor vehicle traffic accident involving collision on the highway injuring occupant of streetcar',12195,NULL,NULL,NULL),(2,'E815.5','','Other motor vehicle traffic accident involving collision on the highway injuring rider of animal; occupant of animal-drawn vehicle',12196,NULL,NULL,NULL),(2,'E815.6','','Other motor vehicle traffic accident involving collision on the highway injuring pedal cyclist',12197,NULL,NULL,NULL),(2,'E815.7','','Other motor vehicle traffic accident involving collision on the highway injuring pedestrian',12198,NULL,NULL,NULL),(2,'E815.8','','Other motor vehicle traffic accident involving collision on the highway injuring other specified person',12199,NULL,NULL,NULL),(2,'E815.9','','Other motor vehicle traffic accident involving collision on the highway injuring unspecified person',12200,NULL,NULL,NULL),(2,'E816.0','','Motor vehicle traffic accident due to loss of control without collision on the highway injuring driver of motor vehicle other than motorcycle',12201,NULL,NULL,NULL),(2,'E816.1','','Motor vehicle traffic accident due to loss of control without collision on the highway injuring passenger in motor vehicle other than motorcycle',12202,NULL,NULL,NULL),(2,'E816.2','','Motor vehicle traffic accident due to loss of control without collision on the highway injuring motorcyclist',12203,NULL,NULL,NULL),(2,'E816.3','','Motor vehicle traffic accident due to loss of control without collision on the highway injuring passenger on motorcycle',12204,NULL,NULL,NULL),(2,'E816.4','','Motor vehicle traffic accident due to loss of control without collision on the highway injuring occupant of streetcar',12205,NULL,NULL,NULL),(2,'E816.5','','Motor vehicle traffic accident due to loss of control without collision on the highway injuring rider of animal; occupant of animal-drawn vehicle',12206,NULL,NULL,NULL),(2,'E816.6','','Motor vehicle traffic accident due to loss of control without collision on the highway injuring pedal cyclist',12207,NULL,NULL,NULL),(2,'E816.7','','Motor vehicle traffic accident due to loss of control without collision on the highway injuring pedestrian',12208,NULL,NULL,NULL),(2,'E816.8','','Motor vehicle traffic accident due to loss of control without collision on the highway injuring other specified person',12209,NULL,NULL,NULL),(2,'E816.9','','Motor vehicle traffic accident due to loss of control without collision on the highway injuring unspecified person',12210,NULL,NULL,NULL),(2,'E817.0','','Noncollision motor vehicle traffic accident while boarding or alighting injuring driver of motor vehicle other than motorcycle',12211,NULL,NULL,NULL),(2,'E817.1','','Noncollision motor vehicle traffic accident while boarding or alighting injuring passenger in motor vehicle other than motorcycle',12212,NULL,NULL,NULL),(2,'E817.2','','Noncollision motor vehicle traffic accident while boarding or alighting injuring motorcyclist',12213,NULL,NULL,NULL),(2,'E817.3','','Noncollision motor vehicle traffic accident while boarding or alighting injuring passenger on motorcycle',12214,NULL,NULL,NULL),(2,'E817.4','','Noncollision motor vehicle traffic accident while boarding or alighting injuring occupant of streetcar',12215,NULL,NULL,NULL),(2,'E817.5','','Noncollision motor vehicle traffic accident while boarding or alighting injuring rider of animal; occupant of animal-drawn vehicle',12216,NULL,NULL,NULL),(2,'E817.6','','Noncollision motor vehicle traffic accident while boarding or alighting injuring pedal cyclist',12217,NULL,NULL,NULL),(2,'E817.7','','Noncollision motor vehicle traffic accident while boarding or alighting injuring pedestrian',12218,NULL,NULL,NULL),(2,'E817.8','','Noncollision motor vehicle traffic accident while boarding or alighting injuring other specified person',12219,NULL,NULL,NULL),(2,'E817.9','','Noncollision motor vehicle traffic accident while boarding or alighting injuring unspecified person',12220,NULL,NULL,NULL),(2,'E818.0','','Other noncollision motor vehicle traffic accident injuring driver of motor vehicle other than motorcycle',12221,NULL,NULL,NULL),(2,'E818.1','','Other noncollision motor vehicle traffic accident injuring passenger in motor vehicle other than motorcycle',12222,NULL,NULL,NULL),(2,'E818.2','','Other noncollision motor vehicle traffic accident injuring motorcyclist',12223,NULL,NULL,NULL),(2,'E818.3','','Other noncollision motor vehicle traffic accident injuring passenger on motorcycle',12224,NULL,NULL,NULL),(2,'E818.4','','Other noncollision motor vehicle traffic accident injuring occupant of streetcar',12225,NULL,NULL,NULL),(2,'E818.5','','Other noncollision motor vehicle traffic accident injuring rider of animal; occupant of animal-drawn vehicle',12226,NULL,NULL,NULL),(2,'E818.6','','Other noncollision motor vehicle traffic accident injuring pedal cyclist',12227,NULL,NULL,NULL),(2,'E818.7','','Other noncollision motor vehicle traffic accident injuring pedestrian',12228,NULL,NULL,NULL),(2,'E818.8','','Other noncollision motor vehicle traffic accident injuring other specified person',12229,NULL,NULL,NULL),(2,'E818.9','','Other noncollision motor vehicle traffic accident injuring unspecified person',12230,NULL,NULL,NULL),(2,'E819.0','','Motor vehicle traffic accident of unspecified nature injuring driver of motor vehicle other than motorcycle',12231,NULL,NULL,NULL),(2,'E819.1','','Motor vehicle traffic accident of unspecified nature injuring passenger in motor vehicle other than motorcycle',12232,NULL,NULL,NULL),(2,'E819.2','','Motor vehicle traffic accident of unspecified nature injuring motorcyclist',12233,NULL,NULL,NULL),(2,'E819.3','','Motor vehicle traffic accident of unspecified nature injuring passenger on motorcycle',12234,NULL,NULL,NULL),(2,'E819.4','','Motor vehicle traffic accident of unspecified nature injuring occupant of streetcar',12235,NULL,NULL,NULL),(2,'E819.5','','Motor vehicle traffic accident of unspecified nature injuring rider of animal; occupant of animal-drawn vehicle',12236,NULL,NULL,NULL),(2,'E819.6','','Motor vehicle traffic accident of unspecified nature injuring pedal cyclist',12237,NULL,NULL,NULL),(2,'E819.7','','Motor vehicle traffic accident of unspecified nature injuring pedestrian',12238,NULL,NULL,NULL),(2,'E819.8','','Motor vehicle traffic accident of unspecified nature injuring other specified person',12239,NULL,NULL,NULL),(2,'E819.9','','Motor vehicle traffic accident of unspecified nature injuring unspecified person',12240,NULL,NULL,NULL),(2,'E820.0','','Nontraffic accident involving motor-driven snow vehicle injuring driver of motor vehicle other than motorcycle',12241,NULL,NULL,NULL),(2,'E820.1','','Nontraffic accident involving motor-driven snow vehicle injuring passenger in motor vehicle other than motorcycle',12242,NULL,NULL,NULL),(2,'E820.2','','Nontraffic accident involving motor-driven snow vehicle injuring motorcyclist',12243,NULL,NULL,NULL),(2,'E820.3','','Nontraffic accident involving motor-driven snow vehicle injuring passenger on motorcycle',12244,NULL,NULL,NULL),(2,'E820.4','','Nontraffic accident involving motor-driven snow vehicle injuring occupant of streetcar',12245,NULL,NULL,NULL),(2,'E820.5','','Nontraffic accident involving motor-driven snow vehicle injuring rider of animal; occupant of animal-drawn vehicle',12246,NULL,NULL,NULL),(2,'E820.6','','Nontraffic accident involving motor-driven snow vehicle injuring pedal cyclist',12247,NULL,NULL,NULL),(2,'E820.7','','Nontraffic accident involving motor-driven snow vehicle injuring pedestrian',12248,NULL,NULL,NULL),(2,'E820.8','','Nontraffic accident involving motor-driven snow vehicle injuring other specified person',12249,NULL,NULL,NULL),(2,'E820.9','','Nontraffic accident involving motor-driven snow vehicle injuring unspecified person',12250,NULL,NULL,NULL),(2,'E821.0','','Nontraffic accident involving other off-road motor vehicle injuring driver of motor vehicle other than motorcycle',12251,NULL,NULL,NULL),(2,'E821.1','','Nontraffic accident involving other off-road motor vehicle injuring passenger in motor vehicle other than motorcycle',12252,NULL,NULL,NULL),(2,'E821.2','','Nontraffic accident involving other off-road motor vehicle injuring motorcyclist',12253,NULL,NULL,NULL),(2,'E821.3','','Nontraffic accident involving other off-road motor vehicle injuring passenger on motorcycle',12254,NULL,NULL,NULL),(2,'E821.4','','Nontraffic accident involving other off-road motor vehicle injuring occupant of streetcar',12255,NULL,NULL,NULL),(2,'E821.5','','Nontraffic accident involving other off-road motor vehicle injuring rider of animal; occupant of animal-drawn vehicle',12256,NULL,NULL,NULL),(2,'E821.6','','Nontraffic accident involving other off-road motor vehicle injuring pedal cyclist',12257,NULL,NULL,NULL),(2,'E821.7','','Nontraffic accident involving other off-road motor vehicle injuring pedestrian',12258,NULL,NULL,NULL),(2,'E821.8','','Nontraffic accident involving other off-road motor vehicle injuring other specified person',12259,NULL,NULL,NULL),(2,'E821.9','','Nontraffic accident involving other off-road motor vehicle injuring unspecified person',12260,NULL,NULL,NULL),(2,'E822.0','','Other motor vehicle nontraffic accident involving collision with moving object injuring driver of motor vehicle other than motorcycle',12261,NULL,NULL,NULL),(2,'E822.1','','Other motor vehicle nontraffic accident involving collision with moving object injuring passenger in motor vehicle other than motorcycle',12262,NULL,NULL,NULL),(2,'E822.2','','Other motor vehicle nontraffic accident involving collision with moving object injuring motorcyclist',12263,NULL,NULL,NULL),(2,'E822.3','','Other motor vehicle nontraffic accident involving collision with moving object injuring passenger on motorcycle',12264,NULL,NULL,NULL),(2,'E822.4','','Other motor vehicle nontraffic accident involving collision with moving object injuring occupant of streetcar',12265,NULL,NULL,NULL),(2,'E822.5','','Other motor vehicle nontraffic accident involving collision with moving object injuring rider of animal; occupant of animal-drawn vehicle',12266,NULL,NULL,NULL),(2,'E822.6','','Other motor vehicle nontraffic accident involving collision with moving object injuring pedal cyclist',12267,NULL,NULL,NULL),(2,'E822.7','','Other motor vehicle nontraffic accident involving collision with moving object injuring pedestrian',12268,NULL,NULL,NULL),(2,'E822.8','','Other motor vehicle nontraffic accident involving collision with moving object injuring other specified person',12269,NULL,NULL,NULL),(2,'E822.9','','Other motor vehicle nontraffic accident involving collision with moving object injuring unspecified person',12270,NULL,NULL,NULL),(2,'E823.0','','Other motor vehicle nontraffic accident involving collision with stationary object injuring driver of motor vehicle other than motorcycle',12271,NULL,NULL,NULL),(2,'E823.1','','Other motor vehicle nontraffic accident involving collision with stationary object injuring passenger in motor vehicle other than motorcycle',12272,NULL,NULL,NULL),(2,'E823.2','','Other motor vehicle nontraffic accident involving collision with stationary object injuring motorcyclist',12273,NULL,NULL,NULL),(2,'E823.3','','Other motor vehicle nontraffic accident involving collision with stationary object injuring passenger on motorcycle',12274,NULL,NULL,NULL),(2,'E823.4','','Other motor vehicle nontraffic accident involving collision with stationary object injuring occupant of streetcar',12275,NULL,NULL,NULL),(2,'E823.5','','Other motor vehicle nontraffic accident involving collision with stationary object injuring rider of animal; occupant of animal-drawn vehicle',12276,NULL,NULL,NULL),(2,'E823.6','','Other motor vehicle nontraffic accident involving collision with stationary object injuring pedal cyclist',12277,NULL,NULL,NULL),(2,'E823.7','','Other motor vehicle nontraffic accident involving collision with stationary object injuring pedestrian',12278,NULL,NULL,NULL),(2,'E823.8','','Other motor vehicle nontraffic accident involving collision with stationary object injuring other specified person',12279,NULL,NULL,NULL),(2,'E823.9','','Other motor vehicle nontraffic accident involving collision with stationary object injuring unspecified person',12280,NULL,NULL,NULL),(2,'E824.0','','Other motor vehicle nontraffic accident while boarding and alighting injuring driver of motor vehicle other than motorcycle',12281,NULL,NULL,NULL),(2,'E824.1','','Other motor vehicle nontraffic accident while boarding and alighting injuring passenger in motor vehicle other than motorcycle',12282,NULL,NULL,NULL),(2,'E824.2','','Other motor vehicle nontraffic accident while boarding and alighting injuring motorcyclist',12283,NULL,NULL,NULL),(2,'E824.3','','Other motor vehicle nontraffic accident while boarding and alighting injuring passenger on motorcycle',12284,NULL,NULL,NULL),(2,'E824.4','','Other motor vehicle nontraffic accident while boarding and alighting injuring occupant of streetcar',12285,NULL,NULL,NULL),(2,'E824.5','','Other motor vehicle nontraffic accident while boarding and alighting injuring rider of animal; occupant of animal-drawn vehicle',12286,NULL,NULL,NULL),(2,'E824.6','','Other motor vehicle nontraffic accident while boarding and alighting injuring pedal cyclist',12287,NULL,NULL,NULL),(2,'E824.7','','Other motor vehicle nontraffic accident while boarding and alighting injuring pedestrian',12288,NULL,NULL,NULL),(2,'E824.8','','Other motor vehicle nontraffic accident while boarding and alighting injuring other specified person',12289,NULL,NULL,NULL),(2,'E824.9','','Other motor vehicle nontraffic accident while boarding and alighting injuring unspecified person',12290,NULL,NULL,NULL),(2,'E825.0','','Other motor vehicle nontraffic accident of other and unspecified nature injuring driver of motor vehicle other than motorcycle',12291,NULL,NULL,NULL),(2,'E825.1','','Other motor vehicle nontraffic accident of other and unspecified nature injuring passenger in motor vehicle other than motorcycle',12292,NULL,NULL,NULL),(2,'E825.2','','Other motor vehicle nontraffic accident of other and unspecified nature injuring motorcyclist',12293,NULL,NULL,NULL),(2,'E825.3','','Other motor vehicle nontraffic accident of other and unspecified nature injuring passenger on motorcycle',12294,NULL,NULL,NULL),(2,'E825.4','','Other motor vehicle nontraffic accident of other and unspecified nature injuring occupant of streetcar',12295,NULL,NULL,NULL),(2,'E825.5','','Other motor vehicle nontraffic accident of other and unspecified nature injuring rider of animal; occupant of animal-drawn vehicle',12296,NULL,NULL,NULL),(2,'E825.6','','Other motor vehicle nontraffic accident of other and unspecified nature injuring pedal cyclist',12297,NULL,NULL,NULL),(2,'E825.7','','Other motor vehicle nontraffic accident of other and unspecified nature injuring pedestrian',12298,NULL,NULL,NULL),(2,'E825.8','','Other motor vehicle nontraffic accident of other and unspecified nature injuring other specified person',12299,NULL,NULL,NULL),(2,'E825.9','','Other motor vehicle nontraffic accident of other and unspecified nature injuring unspecified person',12300,NULL,NULL,NULL),(2,'E826.0','','Pedal cycle accident injuring pedestrian',12301,NULL,NULL,NULL),(2,'E826.1','','Pedal cycle accident injuring pedal cyclist',12302,NULL,NULL,NULL),(2,'E826.2','','Pedal cycle accident injuring rider of animal',12303,NULL,NULL,NULL),(2,'E826.3','','Pedal cycle accident injuring occupant of animal-drawn vehicle',12304,NULL,NULL,NULL),(2,'E826.4','','Pedal cycle accident injuring occupant of streetcar',12305,NULL,NULL,NULL),(2,'E826.8','','Pedal cycle accident injuring other specified person',12306,NULL,NULL,NULL),(2,'E826.9','','Pedal cycle accident injuring unspecified person',12307,NULL,NULL,NULL),(2,'E827.0','','Animal-drawn vehicle accident injuring pedestrian',12308,NULL,NULL,NULL),(2,'E827.2','','Animal-drawn vehicle accident injuring rider of animal',12309,NULL,NULL,NULL),(2,'E827.3','','Animal-drawn vehicle accident injuring occupant of animal drawn vehicle',12310,NULL,NULL,NULL),(2,'E827.4','','Animal-drawn vehicle accident injuring occupant of streetcar',12311,NULL,NULL,NULL),(2,'E827.8','','Animal-drawn vehicle accident injuring other specified person',12312,NULL,NULL,NULL),(2,'E827.9','','Animal-drawn vehicle accident injuring unspecified person',12313,NULL,NULL,NULL),(2,'E828.0','','Accident involving animal being ridden injuring pedestrian',12314,NULL,NULL,NULL),(2,'E828.2','','Accident involving animal being ridden injuring rider of animal',12315,NULL,NULL,NULL),(2,'E828.4','','Accident involving animal being ridden injuring occupant of streetcar',12316,NULL,NULL,NULL),(2,'E828.8','','Accident involving animal being ridden injuring other specified person',12317,NULL,NULL,NULL),(2,'E828.9','','Accident involving animal being ridden injuring unspecified person',12318,NULL,NULL,NULL),(2,'E829.0','','Other road vehicle accidents injuring pedestrian',12319,NULL,NULL,NULL),(2,'E829.4','','Other road vehicle accidents injuring occupant of streetcar',12320,NULL,NULL,NULL),(2,'E829.8','','Other road vehicle accidents injuring other specified person',12321,NULL,NULL,NULL),(2,'E829.9','','Other road vehicle accidents injuring unspecified person',12322,NULL,NULL,NULL),(2,'E830.0','','Accident to watercraft causing submersion injuring occupant of small boat unpowered',12323,NULL,NULL,NULL),(2,'E830.1','','Accident to watercraft causing submersion injuring occupant of small boat powered',12324,NULL,NULL,NULL),(2,'E830.2','','Accident to watercraft causing submersion injuring occupant of other watercraft -- crew',12325,NULL,NULL,NULL),(2,'E830.3','','Accident to watercraft causing submersion injuring occupant of other watercraft -- other than crew',12326,NULL,NULL,NULL),(2,'E830.4','','Accident to watercraft causing submersion injuring water skier',12327,NULL,NULL,NULL),(2,'E830.5','','Accident to watercraft causing submersion injuring swimmer',12328,NULL,NULL,NULL),(2,'E830.6','','Accident to watercraft causing submersion injuring dockers stevedores',12329,NULL,NULL,NULL),(2,'E830.7','','Accident to watercraft causing submersion, occupant of military watercraft, any type',12330,NULL,NULL,NULL),(2,'E830.8','','Accident to watercraft causing submersion injuring other specified person',12331,NULL,NULL,NULL),(2,'E830.9','','Accident to watercraft causing submersion injuring unspecified person',12332,NULL,NULL,NULL),(2,'E831.0','','Accident to watercraft causing other injury to occupant of small boat unpowered',12333,NULL,NULL,NULL),(2,'E831.1','','Accident to watercraft causing other injury to occupant of small boat powered',12334,NULL,NULL,NULL),(2,'E831.2','','Accident to watercraft causing other injury to occupant of other watercraft -- crew',12335,NULL,NULL,NULL),(2,'E831.3','','Accident to watercraft causing other injury to occupant of other watercraft -- other than crew',12336,NULL,NULL,NULL),(2,'E831.4','','Accident to watercraft causing other injury to water skier',12337,NULL,NULL,NULL),(2,'E831.5','','Accident to watercraft causing other injury to swimmer',12338,NULL,NULL,NULL),(2,'E831.6','','Accident to watercraft causing other injury to dockers stevedores',12339,NULL,NULL,NULL),(2,'E831.7','','Accident to watercraft causing other injury, occupant of military watercraft, any type',12340,NULL,NULL,NULL),(2,'E831.8','','Accident to watercraft causing other injury to other specified person',12341,NULL,NULL,NULL),(2,'E831.9','','Accident to watercraft causing other injury to unspecified person',12342,NULL,NULL,NULL),(2,'E832.0','','Other accidental submersion or drowning in water transport accident injuring occupant of small boat unpowered',12343,NULL,NULL,NULL),(2,'E832.1','','Other accidental submersion or drowning in water transport accident injuring occupant of small boat powered',12344,NULL,NULL,NULL),(2,'E832.2','','Other accidental submersion or drowning in water transport accident injuring occupant of other watercraft -- crew',12345,NULL,NULL,NULL),(2,'E832.3','','Other accidental submersion or drowning in water transport accident injuring occupant of other watercraft -- other than crew',12346,NULL,NULL,NULL),(2,'E832.4','','Other accidental submersion or drowning in water transport accident injuring water skier',12347,NULL,NULL,NULL),(2,'E832.5','','Other accidental submersion or drowning in water transport accident injuring swimmer',12348,NULL,NULL,NULL),(2,'E832.6','','Other accidental submersion or drowning in water transport accident injuring dockers stevedores',12349,NULL,NULL,NULL),(2,'E832.7','','Other accidental submersion or drowning in water transport accident, occupant of military watercraft, any type',12350,NULL,NULL,NULL),(2,'E832.8','','Other accidental submersion or drowning in water transport accident injuring other specified person',12351,NULL,NULL,NULL),(2,'E832.9','','Other accidental submersion or drowning in water transport accident injuring unspecified person',12352,NULL,NULL,NULL),(2,'E833.0','','Fall on stairs or ladders in water transport injuring occupant of small boat unpowered',12353,NULL,NULL,NULL),(2,'E833.1','','Fall on stairs or ladders in water transport injuring occupant of small boat powered',12354,NULL,NULL,NULL),(2,'E833.2','','Fall on stairs or ladders in water transport injuring occupant of other watercraft -- crew',12355,NULL,NULL,NULL),(2,'E833.3','','Fall on stairs or ladders in water transport injuring occupant of other watercraft -- other than crew',12356,NULL,NULL,NULL),(2,'E833.4','','Fall on stairs or ladders in water transport injuring water skier',12357,NULL,NULL,NULL),(2,'E833.5','','Fall on stairs or ladders in water transport injuring swimmer',12358,NULL,NULL,NULL),(2,'E833.6','','Fall on stairs or ladders in water transport injuring dockers stevedores',12359,NULL,NULL,NULL),(2,'E833.7','','Fall on stairs or ladders in water transport, occupant of military watercraft, any type',12360,NULL,NULL,NULL),(2,'E833.8','','Fall on stairs or ladders in water transport injuring other specified person',12361,NULL,NULL,NULL),(2,'E833.9','','Fall on stairs or ladders in water transport injuring unspecified person',12362,NULL,NULL,NULL),(2,'E834.0','','Other fall from one level to another in water transport injuring occupant of small boat unpowered',12363,NULL,NULL,NULL),(2,'E834.1','','Other fall from one level to another in water transport injuring occupant of small boat powered',12364,NULL,NULL,NULL),(2,'E834.2','','Other fall from one level to another in water transport injuring occupant of other watercraft -- crew',12365,NULL,NULL,NULL),(2,'E834.3','','Other fall from one level to another in water transport injuring occupant of other watercraft -- other than crew',12366,NULL,NULL,NULL),(2,'E834.4','','Other fall from one level to another in water transport injuring water skier',12367,NULL,NULL,NULL),(2,'E834.5','','Other fall from one level to another in water transport injuring swimmer',12368,NULL,NULL,NULL),(2,'E834.6','','Other fall from one level to another in water transport injuring dockers stevedores',12369,NULL,NULL,NULL),(2,'E834.7','','Other fall from one level to another in water transport, occupant of military watercraft, any type',12370,NULL,NULL,NULL),(2,'E834.8','','Other fall from one level to another in water transport injuring other specified person',12371,NULL,NULL,NULL),(2,'E834.9','','Other fall from one level to another in water transport injuring unspecified person',12372,NULL,NULL,NULL),(2,'E835.0','','Other and unspecified fall in water transport injuring occupant of small boat unpowered',12373,NULL,NULL,NULL),(2,'E835.1','','Other and unspecified fall in water transport injuring occupant of small boat powered',12374,NULL,NULL,NULL),(2,'E835.2','','Other and unspecified fall in water transport injuring occupant of other watercraft -- crew',12375,NULL,NULL,NULL),(2,'E835.3','','Other and unspecified fall in water transport injuring occupant of other watercraft -- other than crew',12376,NULL,NULL,NULL),(2,'E835.4','','Other and unspecified fall in water transport injuring water skier',12377,NULL,NULL,NULL),(2,'E835.5','','Other and unspecified fall in water transport injuring swimmer',12378,NULL,NULL,NULL),(2,'E835.6','','Other and unspecified fall in water transport injuring dockers stevedores',12379,NULL,NULL,NULL),(2,'E835.7','','Other and unspecified fall in water transport, occupant of military watercraft, any type',12380,NULL,NULL,NULL),(2,'E835.8','','Other and unspecified fall in water transport injuring other specified person',12381,NULL,NULL,NULL),(2,'E835.9','','Other and unspecified fall in water transport injuring unspecified person',12382,NULL,NULL,NULL),(2,'E836.0','','Machinery accident in water transport injuring occupant of small boat unpowered',12383,NULL,NULL,NULL),(2,'E836.1','','Machinery accident in water transport injuring occupant of small boat powered',12384,NULL,NULL,NULL),(2,'E836.2','','Machinery accident in water transport injuring occupant of other watercraft -- crew',12385,NULL,NULL,NULL),(2,'E836.3','','Machinery accident in water transport injuring occupant of other watercraft -- other than crew',12386,NULL,NULL,NULL),(2,'E836.4','','Machinery accident in water transport injuring water skier',12387,NULL,NULL,NULL),(2,'E836.5','','Machinery accident in water transport injuring swimmer',12388,NULL,NULL,NULL),(2,'E836.6','','Machinery accident in water transport injuring dockers stevedores',12389,NULL,NULL,NULL),(2,'E836.7','','Machinery accident in water transport, occupant of military watercraft, any type',12390,NULL,NULL,NULL),(2,'E836.8','','Machinery accident in water transport injuring other specified person',12391,NULL,NULL,NULL),(2,'E836.9','','Machinery accident in water transport injuring unspecified person',12392,NULL,NULL,NULL),(2,'E837.0','','Explosion fire or burning in watercraft injuring occupant of small boat unpowered',12393,NULL,NULL,NULL),(2,'E837.1','','Explosion fire or burning in watercraft injuring occupant of small boat powered',12394,NULL,NULL,NULL),(2,'E837.2','','Explosion fire or burning in watercraft injuring occupant of other watercraft -- crew',12395,NULL,NULL,NULL),(2,'E837.3','','Explosion fire or burning in watercraft injuring occupant of other watercraft -- other than crew',12396,NULL,NULL,NULL),(2,'E837.4','','Explosion fire or burning in watercraft injuring water skier',12397,NULL,NULL,NULL),(2,'E837.5','','Explosion fire or burning in watercraft injuring swimmer',12398,NULL,NULL,NULL),(2,'E837.6','','Explosion fire or burning in watercraft injuring dockers stevedores',12399,NULL,NULL,NULL),(2,'E837.7','','Explosion, fire, or burning in watercraft, occupant of military watercraft, any type',12400,NULL,NULL,NULL),(2,'E837.8','','Explosion fire or burning in watercraft injuring other specified person',12401,NULL,NULL,NULL),(2,'E837.9','','Explosion fire or burning in watercraft injuring unspecified person',12402,NULL,NULL,NULL),(2,'E838.0','','Other and unspecified water transport accident injuring occupant of small boat unpowered',12403,NULL,NULL,NULL),(2,'E838.1','','Other and unspecified water transport accident injuring occupant of small boat powered',12404,NULL,NULL,NULL),(2,'E838.2','','Other and unspecified water transport accident injuring occupant of other watercraft -- crew',12405,NULL,NULL,NULL),(2,'E838.3','','Other and unspecified water transport accident injuring occupant of other watercraft -- other than crew',12406,NULL,NULL,NULL),(2,'E838.4','','Other and unspecified water transport accident injuring water skier',12407,NULL,NULL,NULL),(2,'E838.5','','Other and unspecified water transport accident injuring swimmer',12408,NULL,NULL,NULL),(2,'E838.6','','Other and unspecified water transport accident injuring dockers stevedores',12409,NULL,NULL,NULL),(2,'E838.7','','Other and unspecified water transport accident, occupant of military watercraft, any type',12410,NULL,NULL,NULL),(2,'E838.8','','Other and unspecified water transport accident injuring other specified person',12411,NULL,NULL,NULL),(2,'E838.9','','Other and unspecified water transport accident injuring unspecified person',12412,NULL,NULL,NULL),(2,'E840.0','','Accident to powered aircraft at takeoff or landing injuring occupant of spacecraft',12413,NULL,NULL,NULL),(2,'E840.1','','Accident to powered aircraft at takeoff or landing injuring occupant of military aircraft any',12414,NULL,NULL,NULL),(2,'E840.2','','Accident to powered aircraft at takeoff or landing injuring crew of commercial aircraft (powered) in surface to surface transport',12415,NULL,NULL,NULL),(2,'E840.3','','Accident to powered aircraft at takeoff or landing injuring other occupant of commercial aircraft (powered) in surface to surface transport',12416,NULL,NULL,NULL),(2,'E840.4','','Accident to powered aircraft at takeoff or landing injuring occupant of commercial aircraft (powered) in surface to air transport',12417,NULL,NULL,NULL),(2,'E840.5','','Accident to powered aircraft at takeoff or landing injuring occupant of other powered aircraft',12418,NULL,NULL,NULL),(2,'E840.6','','Accident to powered aircraft at takeoff or landing injuring occupant of unpowered aircraft except parachutist',12419,NULL,NULL,NULL),(2,'E840.7','','Accident to powered aircraft at takeoff or landing injuring parachutist (military) (other)',12420,NULL,NULL,NULL),(2,'E840.8','','Accident to powered aircraft at takeoff or landing injuring ground crew airline employee',12421,NULL,NULL,NULL),(2,'E840.9','','Accident to powered aircraft at takeoff or landing injuring other person',12422,NULL,NULL,NULL),(2,'E841.0','','Accident to powered aircraft other and unspecified injuring occupant of spacecraft',12423,NULL,NULL,NULL),(2,'E841.1','','Accident to powered aircraft other and unspecified injuring occupant of military aircraft any',12424,NULL,NULL,NULL),(2,'E841.2','','Accident to powered aircraft other and unspecified injuring crew of commercial aircraft (powered) in surface to surface transport',12425,NULL,NULL,NULL),(2,'E841.3','','Accident to powered aircraft other and unspecified injuring other occupant of commercial aircraft (powered) in surface to surface transport',12426,NULL,NULL,NULL),(2,'E841.4','','Accident to powered aircraft other and unspecified injuring occupant of commercial aircraft (powered) in surface to air transport',12427,NULL,NULL,NULL),(2,'E841.5','','Accident to powered aircraft other and unspecified injuring occupant of other powered aircraft',12428,NULL,NULL,NULL),(2,'E841.6','','Accident to powered aircraft other and unspecified injuring occupant of unpowered aircraft except parachutist',12429,NULL,NULL,NULL),(2,'E841.7','','Accident to powered aircraft other and unspecified injuring parachutist (military) (other)',12430,NULL,NULL,NULL),(2,'E841.8','','Accident to powered aircraft other and unspecified injuring ground crew airline employee',12431,NULL,NULL,NULL),(2,'E841.9','','Accident to powered aircraft other and unspecified injuring other person',12432,NULL,NULL,NULL),(2,'E842.6','','Accident to unpowered aircraft injuring occupant of unpowered aircraft except parachutist',12433,NULL,NULL,NULL),(2,'E842.7','','Accident to unpowered aircraft injuring parachutist (military) (other)',12434,NULL,NULL,NULL),(2,'E842.8','','Accident to unpowered aircraft injuring ground crew airline employee',12435,NULL,NULL,NULL),(2,'E842.9','','Accident to unpowered aircraft injuring other person',12436,NULL,NULL,NULL),(2,'E843.0','','Fall in on or from aircraft injuring occupant of spacecraft',12437,NULL,NULL,NULL),(2,'E843.1','','Fall in on or from aircraft injuring occupant of military aircraft any',12438,NULL,NULL,NULL),(2,'E843.2','','Fall in on or from aircraft injuring crew of commercial aircraft (powered) in surface to surface transport',12439,NULL,NULL,NULL),(2,'E843.3','','Fall in on or from aircraft injuring other occupant of commercial aircraft (powered) in surface to surface transport',12440,NULL,NULL,NULL),(2,'E843.4','','Fall in on or from aircraft injuring occupant of commercial aircraft (powered) in surface to air transport',12441,NULL,NULL,NULL),(2,'E843.5','','Fall in on or from aircraft injuring occupant of other powered aircraft',12442,NULL,NULL,NULL),(2,'E843.6','','Fall in on or from aircraft injuring occupant of unpowered aircraft except parachutist',12443,NULL,NULL,NULL),(2,'E843.7','','Fall in on or from aircraft injuring parachutist (military) (other)',12444,NULL,NULL,NULL),(2,'E843.8','','Fall in on or from aircraft injuring ground crew airline employee',12445,NULL,NULL,NULL),(2,'E843.9','','Fall in on or from aircraft injuring other person',12446,NULL,NULL,NULL),(2,'E844.0','','Other specified air transport accidents injuring occupant of spacecraft',12447,NULL,NULL,NULL),(2,'E844.1','','Other specified air transport accidents injuring occupant of military aircraft any',12448,NULL,NULL,NULL),(2,'E844.2','','Other specified air transport accidents injuring crew of commercial aircraft (powered) in surface to surface transport',12449,NULL,NULL,NULL),(2,'E844.3','','Other specified air transport accidents injuring other occupant of commercial aircraft (powered) in surface to surface transport',12450,NULL,NULL,NULL),(2,'E844.4','','Other specified air transport accidents injuring occupant of commercial aircraft (powered) in surface to air transport',12451,NULL,NULL,NULL),(2,'E844.5','','Other specified air transport accidents injuring occupant of other powered aircraft',12452,NULL,NULL,NULL),(2,'E844.6','','Other specified air transport accidents injuring occupant of unpowered aircraft except parachutist',12453,NULL,NULL,NULL),(2,'E844.7','','Other specified air transport accidents injuring parachutist (military) (other)',12454,NULL,NULL,NULL),(2,'E844.8','','Other specified air transport accidents injuring ground crew airline employee',12455,NULL,NULL,NULL),(2,'E844.9','','Other specified air transport accidents injuring other person',12456,NULL,NULL,NULL),(2,'E845.0','','Accident involving spacecraft injuring occupant of spacecraft',12457,NULL,NULL,NULL),(2,'E845.8','','Accident involving spacecraft injuring ground crew airline employee',12458,NULL,NULL,NULL),(2,'E845.9','','Accident involving spacecraft injuring other person',12459,NULL,NULL,NULL),(2,'E846','','Accidents involving powered vehicles used solely within the buildings and premises of industrial or commercial establishment',12460,NULL,NULL,NULL),(2,'E847','','Accidents involving cable cars not running on rails',12461,NULL,NULL,NULL),(2,'E848','','Accidents involving other vehicles not elsewhere classifiable',12462,NULL,NULL,NULL),(2,'E849.0','','Home accidents',12463,NULL,NULL,NULL),(2,'E849.1','','Farm accidents',12464,NULL,NULL,NULL),(2,'E849.2','','Mine and quarry accidents',12465,NULL,NULL,NULL),(2,'E849.3','','Accidents occurring in industrial places and premises',12466,NULL,NULL,NULL),(2,'E849.4','','Accidents occurring in place for recreation and sport',12467,NULL,NULL,NULL),(2,'E849.5','','Street and highway accidents',12468,NULL,NULL,NULL),(2,'E849.6','','Accidents occurring in public building',12469,NULL,NULL,NULL),(2,'E849.7','','Accidents occurring in residential institution',12470,NULL,NULL,NULL),(2,'E849.8','','Accidents occurring in other specified places',12471,NULL,NULL,NULL),(2,'E849.9','','Accidents occurring in unspecified place',12472,NULL,NULL,NULL),(2,'E850.0','','Accidental poisoning by heroin',12473,NULL,NULL,NULL),(2,'E850.1','','Accidental poisoning by methadone',12474,NULL,NULL,NULL),(2,'E850.2','','Accidental poisoning by other opiates and related narcotics',12475,NULL,NULL,NULL),(2,'E850.3','','Accidental poisoning by salicylates',12476,NULL,NULL,NULL),(2,'E850.4','','Accidental poisoning by aromatic analgesics not elsewhere classified',12477,NULL,NULL,NULL),(2,'E850.5','','Accidental poisoning by pyrazole derivatives',12478,NULL,NULL,NULL),(2,'E850.6','','Accidental poisoning by antirheumatics (antiphlogistics)',12479,NULL,NULL,NULL),(2,'E850.7','','Accidental poisoning by other non-narcotic analgesics',12480,NULL,NULL,NULL),(2,'E850.8','','Accidental poisoning by other specified analgesics and antipyretics',12481,NULL,NULL,NULL),(2,'E850.9','','Accidental poisoning by unspecified analgesic or antipyretic',12482,NULL,NULL,NULL),(2,'E851','','Accidental poisoning by barbiturates',12483,NULL,NULL,NULL),(2,'E852.0','','Accidental poisoning by chloral hydrate group',12484,NULL,NULL,NULL),(2,'E852.1','','Accidental poisoning by paraldehyde',12485,NULL,NULL,NULL),(2,'E852.2','','Accidental poisoning by bromine compounds',12486,NULL,NULL,NULL),(2,'E852.3','','Accidental poisoning by methaqualone compounds',12487,NULL,NULL,NULL),(2,'E852.4','','Accidental poisoning by glutethimide group',12488,NULL,NULL,NULL),(2,'E852.5','','Accidental poisoning by mixed sedatives not elsewhere classified',12489,NULL,NULL,NULL),(2,'E852.8','','Accidental poisoning by other specified sedatives and hypnotics',12490,NULL,NULL,NULL),(2,'E852.9','','Accidental poisoning by unspecified sedative or hypnotic',12491,NULL,NULL,NULL),(2,'E853.0','','Accidental poisoning by phenothiazine-based tranquilizers',12492,NULL,NULL,NULL),(2,'E853.1','','Accidental poisoning by butyrophenone-based tranquilizers',12493,NULL,NULL,NULL),(2,'E853.2','','Accidental poisoning by benzodiazepine-based tranquilizers',12494,NULL,NULL,NULL),(2,'E853.8','','Accidental poisoning by other specified tranquilizers',12495,NULL,NULL,NULL),(2,'E853.9','','Accidental poisoning by unspecified tranquilizer',12496,NULL,NULL,NULL),(2,'E854.0','','Accidental poisoning by antidepressants',12497,NULL,NULL,NULL),(2,'E854.1','','Accidental poisoning by psychodysleptics (hallucinogens)',12498,NULL,NULL,NULL),(2,'E854.2','','Accidental poisoning by psychostimulants',12499,NULL,NULL,NULL),(2,'E854.3','','Accidental poisoning by central nervous system stimulants',12500,NULL,NULL,NULL),(2,'E854.8','','Accidental poisoning by other psychotropic agents',12501,NULL,NULL,NULL),(2,'E855.0','','Accidental poisoning by anticonvulsant and anti-parkinsonism drugs',12502,NULL,NULL,NULL),(2,'E855.1','','Accidental poisoning by other central nervous system depressants',12503,NULL,NULL,NULL),(2,'E855.2','','Accidental poisoning by local anesthetics',12504,NULL,NULL,NULL),(2,'E855.3','','Accidental poisoning by parasympathomimetics (cholinergics)',12505,NULL,NULL,NULL),(2,'E855.4','','Accidental poisoning by parasympatholytics (anticholinergics and antimuscarinics) and spasmolytics',12506,NULL,NULL,NULL),(2,'E855.5','','Accidental poisoning by sympathomimetics (adrenergics)',12507,NULL,NULL,NULL),(2,'E855.6','','Accidental poisoning by sympatholytics (antiadrenergics)',12508,NULL,NULL,NULL),(2,'E855.8','','Accidental poisoning by other specified drugs acting on central and autonomic nervous systems',12509,NULL,NULL,NULL),(2,'E855.9','','Accidental poisoning by unspecified drug acting on central and autonomic nervous systems',12510,NULL,NULL,NULL),(2,'E856','','Accidental poisoning by antibiotics',12511,NULL,NULL,NULL),(2,'E857','','Accidental poisoning by other anti-infectives',12512,NULL,NULL,NULL),(2,'E858.0','','Accidental poisoning by hormones and synthetic substitutes',12513,NULL,NULL,NULL),(2,'E858.1','','Accidental poisoning by primarily systemic agents',12514,NULL,NULL,NULL),(2,'E858.2','','Accidental poisoning by agents primarily affecting blood constituents',12515,NULL,NULL,NULL),(2,'E858.3','','Accidental poisoning by agents primarily affecting cardiovascular system',12516,NULL,NULL,NULL),(2,'E858.4','','Accidental poisoning by agents primarily affecting gastrointestinal system',12517,NULL,NULL,NULL),(2,'E858.5','','Accidental poisoning by water mineral and uric acid metabolism drugs',12518,NULL,NULL,NULL),(2,'E858.6','','Accidental poisoning by agents primarily acting on the smooth and skeletal muscles and respiratory system',12519,NULL,NULL,NULL),(2,'E858.7','','Accidental poisoning by agents primarily affecting skin and mucous membrane ophthalmological otorhinolaryngological and dental drugs',12520,NULL,NULL,NULL),(2,'E858.8','','Accidental poisoning by other specified drugs',12521,NULL,NULL,NULL),(2,'E858.9','','Accidental poisoning by unspecified drug',12522,NULL,NULL,NULL),(2,'E860.0','','Accidental poisoning by alcoholic beverages',12523,NULL,NULL,NULL),(2,'E860.1','','Accidental poisoning by other and unspecified ethyl alcohol and its products',12524,NULL,NULL,NULL),(2,'E860.2','','Accidental poisoning by methyl alcohol',12525,NULL,NULL,NULL),(2,'E860.3','','Accidental poisoning by isopropyl alcohol',12526,NULL,NULL,NULL),(2,'E860.4','','Accidental poisoning by fusel oil',12527,NULL,NULL,NULL),(2,'E860.8','','Accidental poisoning by other specified alcohols',12528,NULL,NULL,NULL),(2,'E860.9','','Accidental poisoning by unspecified alcohol',12529,NULL,NULL,NULL),(2,'E861.0','','Accidental poisoning by synthetic detergents and shampoos',12530,NULL,NULL,NULL),(2,'E861.1','','Accidental poisoning by soap products',12531,NULL,NULL,NULL),(2,'E861.2','','Accidental poisoning by polishes',12532,NULL,NULL,NULL),(2,'E861.3','','Accidental poisoning by other cleansing and polishing agents',12533,NULL,NULL,NULL),(2,'E861.4','','Accidental poisoning by disinfectants',12534,NULL,NULL,NULL),(2,'E861.5','','Accidental poisoning by lead paints',12535,NULL,NULL,NULL),(2,'E861.6','','Accidental poisoning by other paints and varnishes',12536,NULL,NULL,NULL),(2,'E861.9','','Accidental poisonings by unspecified cleansing and polishing agents disinfectants paints and varnishes',12537,NULL,NULL,NULL),(2,'E862.0','','Accidental poisoning by petroleum solvents',12538,NULL,NULL,NULL),(2,'E862.1','','Accidental poisoning by petroleum fuels and cleaners',12539,NULL,NULL,NULL),(2,'E862.2','','Accidental poisoning by lubricating oils',12540,NULL,NULL,NULL),(2,'E862.3','','Accidental poisoning by petroleum solids',12541,NULL,NULL,NULL),(2,'E862.4','','Accidental poisoning by other specified solvents not elsewhere classified',12542,NULL,NULL,NULL),(2,'E862.9','','Accidental poisoning by unspecified solvent not elsewhere classified',12543,NULL,NULL,NULL),(2,'E863.0','','Accidental poisoning by insecticides of organochlorine compounds',12544,NULL,NULL,NULL),(2,'E863.1','','Accidental poisoning by insecticides of organophosphorus compounds',12545,NULL,NULL,NULL),(2,'E863.2','','Accidental poisoning by carbamates',12546,NULL,NULL,NULL),(2,'E863.3','','Accidental poisoning by mixtures of insecticides',12547,NULL,NULL,NULL),(2,'E863.4','','Accidental poisoning by other and unspecified insecticides',12548,NULL,NULL,NULL),(2,'E863.5','','Accidental poisoning by herbicides',12549,NULL,NULL,NULL),(2,'E863.6','','Accidental poisoning by fungicides',12550,NULL,NULL,NULL),(2,'E863.7','','Accidental poisoning by rodenticides',12551,NULL,NULL,NULL),(2,'E863.8','','Accidental poisoning by fumigants',12552,NULL,NULL,NULL),(2,'E863.9','','Accidental poisoning by other and unspecified agricultural and horticultural chemical and pharmaceutical preparations other than plant foods and fertilizers',12553,NULL,NULL,NULL),(2,'E864.0','','Accidental poisoning by corrosive aromatics not elsewhere classified',12554,NULL,NULL,NULL),(2,'E864.1','','Accidental poisoning by acids not elsewhere classified',12555,NULL,NULL,NULL),(2,'E864.2','','Accidental poisoning by caustic alkalis not elsewhere classified',12556,NULL,NULL,NULL),(2,'E864.3','','Accidental poisoning by other specified corrosives and caustics not elsewhere classified',12557,NULL,NULL,NULL),(2,'E864.4','','Accidental poisoning by unspecified corrosives and caustics not elsewhere classified',12558,NULL,NULL,NULL),(2,'E865.0','','Accidental poisoning by meat',12559,NULL,NULL,NULL),(2,'E865.1','','Accidental poisoning by shellfish',12560,NULL,NULL,NULL),(2,'E865.2','','Accidental poisoning from other fish',12561,NULL,NULL,NULL),(2,'E865.3','','Accidental poisoning from berries and seeds',12562,NULL,NULL,NULL),(2,'E865.4','','Accidental poisoning from other specified plants',12563,NULL,NULL,NULL),(2,'E865.5','','Accidental poisoning from mushrooms and other fungi',12564,NULL,NULL,NULL),(2,'E865.8','','Accidental poisoning from other specified foods',12565,NULL,NULL,NULL),(2,'E865.9','','Accidental poisoning from unspecified foodstuff or poisonous plant',12566,NULL,NULL,NULL),(2,'E866.0','','Accidental poisoning by lead and its compounds and fumes',12567,NULL,NULL,NULL),(2,'E866.1','','Accidental poisoning by mercury and its compounds and fumes',12568,NULL,NULL,NULL),(2,'E866.2','','Accidental poisoning by antimony and its compounds and fumes',12569,NULL,NULL,NULL),(2,'E866.3','','Accidental poisoning by arsenic and its compounds and fumes',12570,NULL,NULL,NULL),(2,'E866.4','','Accidental poisoning by other metals and their compounds and fumes',12571,NULL,NULL,NULL),(2,'E866.5','','Accidental poisoning by plant foods and fertilizers',12572,NULL,NULL,NULL),(2,'E866.6','','Accidental poisoning by glues and adhesives',12573,NULL,NULL,NULL),(2,'E866.7','','Accidental poisoning by cosmetics',12574,NULL,NULL,NULL),(2,'E866.8','','Accidental poisoning by other specified solid or liquid substances',12575,NULL,NULL,NULL),(2,'E866.9','','Accidental poisoning by unspecified solid or liquid substance',12576,NULL,NULL,NULL),(2,'E867','','Accidental poisoning by gas distributed by pipeline',12577,NULL,NULL,NULL),(2,'E868.0','','Accidental poisoning by liquefied petroleum gas distributed in mobile containers',12578,NULL,NULL,NULL),(2,'E868.1','','Accidental poisoning by other and unspecified utility gas',12579,NULL,NULL,NULL),(2,'E868.2','','Accidental poisoning by motor vehicle exhaust gas',12580,NULL,NULL,NULL),(2,'E868.3','','Accidental poisoning by carbon monoxide from incomplete combustion of other domestic fuels',12581,NULL,NULL,NULL),(2,'E868.8','','Accidental poisoning by carbon monoxide from other sources',12582,NULL,NULL,NULL),(2,'E868.9','','Accidental poisoning by unspecified carbon monoxide',12583,NULL,NULL,NULL),(2,'E869.0','','Accidental poisoning by nitrogen oxides',12584,NULL,NULL,NULL),(2,'E869.1','','Accidental poisoning by sulfur dioxide',12585,NULL,NULL,NULL),(2,'E869.2','','Accidental poisoning by freon',12586,NULL,NULL,NULL),(2,'E869.3','','Accidental poisoning by lacrimogenic gas (tear gas)',12587,NULL,NULL,NULL),(2,'E869.4','','Second hand tobacco smoke',12588,NULL,NULL,NULL),(2,'E869.8','','Accidental poisoning by other specified gases and vapors',12589,NULL,NULL,NULL),(2,'E869.9','','Accidental poisoning by unspecified gases and vapors',12590,NULL,NULL,NULL),(2,'E870.0','','Accidental cut puncture perforation or hemorrhage during surgical operation',12591,NULL,NULL,NULL),(2,'E870.1','','Accidental cut puncture perforation or hemorrhage during infusion or transfusion',12592,NULL,NULL,NULL),(2,'E870.2','','Accidental cut puncture perforation or hemorrhage during kidney dialysis or other perfusion',12593,NULL,NULL,NULL),(2,'E870.3','','Accidental cut puncture perforation or hemorrhage during injection or vaccination',12594,NULL,NULL,NULL),(2,'E870.4','','Accidental cut puncture perforation or hemorrhage during endoscopic examination',12595,NULL,NULL,NULL),(2,'E870.5','','Accidental cut puncture perforation or hemorrhage during aspiration of fluid or tissue puncture and catheterization',12596,NULL,NULL,NULL),(2,'E870.6','','Accidental cut puncture perforation or hemorrhage during heart catheterization',12597,NULL,NULL,NULL),(2,'E870.7','','Accidental cut puncture perforation or hemorrhage during administration of enema',12598,NULL,NULL,NULL),(2,'E870.8','','Accidental cut puncture perforation or hemorrhage during other specified medical care',12599,NULL,NULL,NULL),(2,'E870.9','','Accidental cut puncture perforation or hemorrhage during unspecified medical care',12600,NULL,NULL,NULL),(2,'E871.0','','Foreign object left in body during surgical operation',12601,NULL,NULL,NULL),(2,'E871.1','','Foreign object left in body during infusion or transfusion',12602,NULL,NULL,NULL),(2,'E871.2','','Foreign object left in body during kidney dialysis or other perfusion',12603,NULL,NULL,NULL),(2,'E871.3','','Foreign object left in body during injection or vaccination',12604,NULL,NULL,NULL),(2,'E871.4','','Foreign object left in body during endoscopic examination',12605,NULL,NULL,NULL),(2,'E871.5','','Foreign object left in body during aspiration of fluid or tissue puncture and catheterization',12606,NULL,NULL,NULL),(2,'E871.6','','Foreign object left in body during heart catheterization',12607,NULL,NULL,NULL),(2,'E871.7','','Foreign object left in body during removal of catheter or packing',12608,NULL,NULL,NULL),(2,'E871.8','','Foreign object left in body during other specified procedures',12609,NULL,NULL,NULL),(2,'E871.9','','Foreign object left in body during unspecified procedure',12610,NULL,NULL,NULL),(2,'E872.0','','Failure of sterile precautions during surgical operation',12611,NULL,NULL,NULL),(2,'E872.1','','Failure of sterile precautions during infusion or transfusion',12612,NULL,NULL,NULL),(2,'E872.2','','Failure of sterile precautions during kidney dialysis and other perfusion',12613,NULL,NULL,NULL),(2,'E872.3','','Failure of sterile precautions during injection or vaccination',12614,NULL,NULL,NULL),(2,'E872.4','','Failure of sterile precautions during endoscopic examination',12615,NULL,NULL,NULL),(2,'E872.5','','Failure of sterile precautions during aspiration of fluid or tissue puncture and catheterization',12616,NULL,NULL,NULL),(2,'E872.6','','Failure of sterile precautions during heart catheterization',12617,NULL,NULL,NULL),(2,'E872.8','','Failure of sterile precautions during other specified procedures',12618,NULL,NULL,NULL),(2,'E872.9','','Failure of sterile precautions during unspecified procedure',12619,NULL,NULL,NULL),(2,'E873.0','','Excessive amount of blood or other fluid during transfusion or infusion',12620,NULL,NULL,NULL),(2,'E873.1','','Incorrect dilution of fluid during infusion',12621,NULL,NULL,NULL),(2,'E873.2','','Overdose of radiation in therapy',12622,NULL,NULL,NULL),(2,'E873.3','','Inadvertent exposure of patient to radiation during medical care',12623,NULL,NULL,NULL),(2,'E873.4','','Failure in dosage in electroshock or insulin-shock therapy',12624,NULL,NULL,NULL),(2,'E873.5','','Inappropriate (too hot or too cold) temperature in local application and packing',12625,NULL,NULL,NULL),(2,'E873.6','','Nonadministration of necessary drug or medicinal substance',12626,NULL,NULL,NULL),(2,'E873.8','','Other specified failure in dosage',12627,NULL,NULL,NULL),(2,'E873.9','','Unspecified failure in dosage',12628,NULL,NULL,NULL),(2,'E874.0','','Mechanical failure of instrument or apparatus during surgical operation',12629,NULL,NULL,NULL),(2,'E874.1','','Mechanical failure of instrument or apparatus during infusion and transfusion',12630,NULL,NULL,NULL),(2,'E874.2','','Mechanical failure of instrument or apparatus during kidney dialysis and other perfusion',12631,NULL,NULL,NULL),(2,'E874.3','','Mechanical failure of instrument or apparatus during endoscopic examination',12632,NULL,NULL,NULL),(2,'E874.4','','Mechanical failure of instrument or apparatus during aspiration of fluid or tissue puncture and catheterization',12633,NULL,NULL,NULL),(2,'E874.5','','Mechanical failure of instrument or apparatus during heart catheterization',12634,NULL,NULL,NULL),(2,'E874.8','','Mechanical failure of instrument or apparatus during other specified procedures',12635,NULL,NULL,NULL),(2,'E874.9','','Mechanical failure of instrument or apparatus during unspecified procedure',12636,NULL,NULL,NULL),(2,'E875.0','','Contaminated substance transfused or infused',12637,NULL,NULL,NULL),(2,'E875.1','','Contaminated substance injected or used for vaccination',12638,NULL,NULL,NULL),(2,'E875.2','','Contaminated drug or biological substance administered by other means',12639,NULL,NULL,NULL),(2,'E875.8','','Misadventure to patient from other contamination',12640,NULL,NULL,NULL),(2,'E875.9','','Misadventure to patient from unspecified contamination',12641,NULL,NULL,NULL),(2,'E876.0','','Mismatched blood in transfusion',12642,NULL,NULL,NULL),(2,'E876.1','','Wrong fluid in infusion',12643,NULL,NULL,NULL),(2,'E876.2','','Failure in suture and ligature during surgical operation',12644,NULL,NULL,NULL),(2,'E876.3','','Endotracheal tube wrongly placed during anesthetic procedure',12645,NULL,NULL,NULL),(2,'E876.4','','Failure to introduce or to remove other tube or instrument',12646,NULL,NULL,NULL),(2,'E876.5','','Performance of wrong operation (procedure) on correct patient',12647,NULL,NULL,NULL),(2,'E876.6','','Performance of operation (procedure) on patient not scheduled for surgery',12648,NULL,NULL,NULL),(2,'E876.7','','Performance of correct operation (procedure) on wrong side/body part',12649,NULL,NULL,NULL),(2,'E876.8','','Other specified misadventures during medical care',12650,NULL,NULL,NULL),(2,'E876.9','','Unspecified misadventure during medical care',12651,NULL,NULL,NULL),(2,'E878.0','','Surgical operation with transplant of whole organ causing abnormal patient reaction or later complication without misadventure at time of operation',12652,NULL,NULL,NULL),(2,'E878.1','','Surgical operation with implant of artificial internal device causing abnormal patient reaction or later complication without misadventure at time of operation',12653,NULL,NULL,NULL),(2,'E878.2','','Surgical operation with anastomosis bypass or graft with natural or artificial tissues used as implant causing abnormal patient reaction or later complication without misadventure at time of operation',12654,NULL,NULL,NULL),(2,'E878.3','','Surgical operation with formation of external stoma causing abnormal patient reaction or later complication without misadventure at time of operation',12655,NULL,NULL,NULL),(2,'E878.4','','Other restorative surgery causing abnormal patient reaction or later complication without misadventure at time of operation',12656,NULL,NULL,NULL),(2,'E878.5','','Amputation of limb(s) causing abnormal patient reaction or later complication without misadventure at time of operation',12657,NULL,NULL,NULL),(2,'E878.6','','Removal of other organ (partial) (total) causing abnormal patient reaction or later complication without misadventure at time of operation',12658,NULL,NULL,NULL),(2,'E878.8','','Other specified surgical operations and procedures causing abnormal patient reaction or later complication without misadventure at time of operation',12659,NULL,NULL,NULL),(2,'E878.9','','Unspecified surgical operations and procedures causing abnormal patient reaction or later complication without misadventure at time of operation',12660,NULL,NULL,NULL),(2,'E879.0','','Cardiac catheterization as the cause of abnormal reaction of patient or of later complication without misadventure at time of procedure',12661,NULL,NULL,NULL),(2,'E879.1','','Kidney dialysis as the cause of abnormal reaction of patient or of later complication without misadventure at time of procedure',12662,NULL,NULL,NULL),(2,'E879.2','','Radiological procedure and radiotherapy as the cause of abnormal reaction of patient or of later complication without misadventure at time of procedure',12663,NULL,NULL,NULL),(2,'E879.3','','Shock therapy as the cause of abnormal reaction of patient or of later complication without misadventure at time of procedure',12664,NULL,NULL,NULL),(2,'E879.4','','Aspiration of fluid as the cause of abnormal reaction of patient or of later complication without misadventure at time of procedure',12665,NULL,NULL,NULL),(2,'E879.5','','Insertion of gastric or duodenal sound as the cause of abnormal reaction of patient or of later complication without misadventure of time of procedure',12666,NULL,NULL,NULL),(2,'E879.6','','Urinary catheterization as the cause of abnormal reaction of patient or of later complication without misadventure at time of procedure',12667,NULL,NULL,NULL),(2,'E879.7','','Blood sampling as the cause of abnormal reaction of patient or of later complication without misadventure at time of procedure',12668,NULL,NULL,NULL),(2,'E879.8','','Other specified procedures as the cause of abnormal reaction of patient or of later complication without misadventure at time of procedure',12669,NULL,NULL,NULL),(2,'E879.9','','Unspecified procedure as the cause of abnormal reaction of patient or of later complication without misadventure at time of procedure',12670,NULL,NULL,NULL),(2,'E880.0','','Accidental fall on or from escalator',12671,NULL,NULL,NULL),(2,'E880.1','','Accidental fall on or from sidewalk curb',12672,NULL,NULL,NULL),(2,'E880.9','','Accidental fall on or from other stairs or steps',12673,NULL,NULL,NULL),(2,'E881.0','','Accidental fall from ladder',12674,NULL,NULL,NULL),(2,'E881.1','','Accidental fall from scaffolding',12675,NULL,NULL,NULL),(2,'E882','','Accidental fall from or out of building or other structure',12676,NULL,NULL,NULL),(2,'E883.0','','Accident from diving or jumping into water (swimming pool)',12677,NULL,NULL,NULL),(2,'E883.1','','Accidental fall into well',12678,NULL,NULL,NULL),(2,'E883.2','','Accidental fall into storm drain or manhole',12679,NULL,NULL,NULL),(2,'E883.9','','Accidental fall into other hole or other opening in surface',12680,NULL,NULL,NULL),(2,'E884.0','','Accidental fall from playground equipment',12681,NULL,NULL,NULL),(2,'E884.1','','Accidental fall from cliff',12682,NULL,NULL,NULL),(2,'E884.2','','Accidental fall from chair',12683,NULL,NULL,NULL),(2,'E884.3','','Accidental fall from wheelchair',12684,NULL,NULL,NULL),(2,'E884.4','','Accidental fall from bed',12685,NULL,NULL,NULL),(2,'E884.5','','Accidental fall from other furniture',12686,NULL,NULL,NULL),(2,'E884.6','','Accidental fall from commode',12687,NULL,NULL,NULL),(2,'E884.9','','Other accidental fall from one level to another',12688,NULL,NULL,NULL),(2,'E885.0','','Accidental fall from (nonmotorized) scooter',12689,NULL,NULL,NULL),(2,'E885.1','','Accidental fall from roller skates',12690,NULL,NULL,NULL),(2,'E885.2','','Accidental fall from skateboard',12691,NULL,NULL,NULL),(2,'E885.3','','Accidental fall from skis',12692,NULL,NULL,NULL),(2,'E885.4','','Accidental fall from snowboard',12693,NULL,NULL,NULL),(2,'E885.9','','Accidental fall from other slipping tripping or stumbling',12694,NULL,NULL,NULL),(2,'E886.0','','Accidental fall on same level from collision pushing or shoving by or with other person in sports',12695,NULL,NULL,NULL),(2,'E886.9','','Other and unspecified accidental falls on same level from collision pushing or shoving by or with other person',12696,NULL,NULL,NULL),(2,'E887','','Fracture cause unspecified',12697,NULL,NULL,NULL),(2,'E888.0','','Accidental fall resulting in striking against sharp object',12698,NULL,NULL,NULL),(2,'E888.1','','Accidental fall resulting in striking against other object',12699,NULL,NULL,NULL),(2,'E888.8','','Other accidental fall',12700,NULL,NULL,NULL),(2,'E888.9','','Unspecified accidental fall',12701,NULL,NULL,NULL),(2,'E890.0','','Explosion caused by conflagration in private dwelling',12702,NULL,NULL,NULL),(2,'E890.1','','Fumes from combustion of polyvinylchloride (pvc) and similar material in conflagration in private dwelling',12703,NULL,NULL,NULL),(2,'E890.2','','Other smoke and fumes from conflagration in private dwelling',12704,NULL,NULL,NULL),(2,'E890.3','','Burning caused by conflagration in private dwelling',12705,NULL,NULL,NULL),(2,'E890.8','','Other accident resulting from conflagration in private dwelling',12706,NULL,NULL,NULL),(2,'E890.9','','Unspecified accident resulting from conflagration in private dwelling',12707,NULL,NULL,NULL),(2,'E891.0','','Explosion caused by conflagration in other and unspecified building or structure',12708,NULL,NULL,NULL),(2,'E891.1','','Fumes from combustion of polyvinylchloride (pvc) and similar material in conflagration in other and unspecified building or structure',12709,NULL,NULL,NULL),(2,'E891.2','','Other smoke and fumes from conflagration in other and unspecified building or structure',12710,NULL,NULL,NULL),(2,'E891.3','','Burning caused by conflagration in other and unspecified building or structure',12711,NULL,NULL,NULL),(2,'E891.8','','Other accident resulting from conflagration in other and unspecified building or structure',12712,NULL,NULL,NULL),(2,'E891.9','','Unspecified accident resulting from conflagration of other and unspecified building or structure',12713,NULL,NULL,NULL),(2,'E892','','Conflagration not in building or structure',12714,NULL,NULL,NULL),(2,'E893.0','','Accident caused by ignition of clothing from controlled fire in private dwelling',12715,NULL,NULL,NULL),(2,'E893.1','','Accident caused by ignition of clothing from controlled fire in other building or structure',12716,NULL,NULL,NULL),(2,'E893.2','','Accident caused by ignition of clothing from controlled fire not in building or structure',12717,NULL,NULL,NULL),(2,'E893.8','','Accident caused by ignition of clothing from other specified sources',12718,NULL,NULL,NULL),(2,'E893.9','','Accident caused by ignition of clothing by unspecified source',12719,NULL,NULL,NULL),(2,'E894','','Ignition of highly inflammable material',12720,NULL,NULL,NULL),(2,'E895','','Accident caused by controlled fire in private dwelling',12721,NULL,NULL,NULL),(2,'E896','','Accident caused by controlled fire in other and unspecified building or structure',12722,NULL,NULL,NULL),(2,'E897','','Accident caused by controlled fire not in building or structure',12723,NULL,NULL,NULL),(2,'E898.0','','Accident caused by burning bedclothes',12724,NULL,NULL,NULL),(2,'E898.1','','Accident caused by other burning materials',12725,NULL,NULL,NULL),(2,'E899','','Accident caused by unspecified fire',12726,NULL,NULL,NULL),(2,'E900.0','','Accident caused by excessive heat due to weather conditions',12727,NULL,NULL,NULL),(2,'E900.1','','Accidents due to excessive heat of man-made origin',12728,NULL,NULL,NULL),(2,'E900.9','','Accidents due to excessive heat of unspecified origin',12729,NULL,NULL,NULL),(2,'E901.0','','Accident due to excessive cold due to weather conditions',12730,NULL,NULL,NULL),(2,'E901.1','','Accident due to excessive cold of man-made origin',12731,NULL,NULL,NULL),(2,'E901.8','','Accident due to excessive cold of other specified origin',12732,NULL,NULL,NULL),(2,'E901.9','','Accident due to excessive cold of unspecified origin',12733,NULL,NULL,NULL),(2,'E902.0','','Accident due to residence or prolonged visit at high altitude',12734,NULL,NULL,NULL),(2,'E902.1','','Accident due to changes in air pressure in aircraft',12735,NULL,NULL,NULL),(2,'E902.2','','Accident due to changes in air pressure due to diving',12736,NULL,NULL,NULL),(2,'E902.8','','Accident due to changes in air pressure due to other specified causes',12737,NULL,NULL,NULL),(2,'E902.9','','Accident due to changes in air pressure from unspecified cause',12738,NULL,NULL,NULL),(2,'E903','','Accident caused by travel and motion',12739,NULL,NULL,NULL),(2,'E904.0','','Accident due to abandonment or neglect of infants and helpless persons',12740,NULL,NULL,NULL),(2,'E904.1','','Accident due to lack of food',12741,NULL,NULL,NULL),(2,'E904.2','','Accident due to lack of water',12742,NULL,NULL,NULL),(2,'E904.3','','Accident due to exposure (to weather conditions) not elsewhere classifiable',12743,NULL,NULL,NULL),(2,'E904.9','','Accident due to privation unqualified',12744,NULL,NULL,NULL),(2,'E905.0','','Venomous snakes and lizards causing poisoning and toxic reactions',12745,NULL,NULL,NULL),(2,'E905.1','','Venomous spiders causing poisoning and toxic reactions',12746,NULL,NULL,NULL),(2,'E905.2','','Scorpion sting causing poisoning and toxic reactions',12747,NULL,NULL,NULL),(2,'E905.3','','Sting of hornets wasps and bees causing poisoning and toxic reactions',12748,NULL,NULL,NULL),(2,'E905.4','','Centipede and venomous millipede (tropical) bite causing poisoning and toxic reactions',12749,NULL,NULL,NULL),(2,'E905.5','','Other venomous arthropods causing poisoning and toxic reactions',12750,NULL,NULL,NULL),(2,'E905.6','','Venomous marine animals and plants causing poisoning and toxic reactions',12751,NULL,NULL,NULL),(2,'E905.7','','Poisoning and toxic reactions caused by other plants',12752,NULL,NULL,NULL),(2,'E905.8','','Poisoning and toxic reactions caused by other specified animals and plants',12753,NULL,NULL,NULL),(2,'E905.9','','Poisoning and toxic reactions caused by unspecified animals and plants',12754,NULL,NULL,NULL),(2,'E906.0','','Dog bite',12755,NULL,NULL,NULL),(2,'E906.1','','Rat bite',12756,NULL,NULL,NULL),(2,'E906.2','','Bite of nonvenomous snakes and lizards',12757,NULL,NULL,NULL),(2,'E906.3','','Bite of other animal except arthropod',12758,NULL,NULL,NULL),(2,'E906.4','','Bite of nonvenomous arthropod',12759,NULL,NULL,NULL),(2,'E906.5','','Bite by unspecified animal',12760,NULL,NULL,NULL),(2,'E906.8','','Other specified injury caused by animal',12761,NULL,NULL,NULL),(2,'E906.9','','Unspecified injury caused by animal',12762,NULL,NULL,NULL),(2,'E907','','Accident due to lightning',12763,NULL,NULL,NULL),(2,'E908.0','','Hurricane',12764,NULL,NULL,NULL),(2,'E908.1','','Tornado',12765,NULL,NULL,NULL),(2,'E908.2','','Floods',12766,NULL,NULL,NULL),(2,'E908.3','','Blizzard (snow) (ice)',12767,NULL,NULL,NULL),(2,'E908.4','','Dust storm',12768,NULL,NULL,NULL),(2,'E908.8','','Other cataclysmic storms',12769,NULL,NULL,NULL),(2,'E908.9','','Unspecified cataclysmic storms and floods resulting from storms',12770,NULL,NULL,NULL),(2,'E909.0','','Earthquakes',12771,NULL,NULL,NULL),(2,'E909.1','','Volcanic eruptions',12772,NULL,NULL,NULL),(2,'E909.2','','Avalanche landslide or mudslide',12773,NULL,NULL,NULL),(2,'E909.3','','Collapse of dam or man-made structure',12774,NULL,NULL,NULL),(2,'E909.4','','Tidal wave caused by earthquake',12775,NULL,NULL,NULL),(2,'E909.8','','Other cataclysmic earth surface movements and eruptions',12776,NULL,NULL,NULL),(2,'E909.9','','Unspecified cataclysmic earth surface movements and eruptions',12777,NULL,NULL,NULL),(2,'E910.0','','Accidental drowning and submersion while water-skiing',12778,NULL,NULL,NULL),(2,'E910.1','','Accidental drowning and submersion while engaged in other sport or recreational activity with diving equipment',12779,NULL,NULL,NULL),(2,'E910.2','','Accidental drowning and submersion while engaged in other sport or recreational activity without diving equipment',12780,NULL,NULL,NULL),(2,'E910.3','','Accidental drowning and submersion while swimming or diving for purposes other than recreation or sport',12781,NULL,NULL,NULL),(2,'E910.4','','Accidental drowning and submersion in bathtub',12782,NULL,NULL,NULL),(2,'E910.8','','Other accidental drowning or submersion',12783,NULL,NULL,NULL),(2,'E910.9','','Unspecified accidental drowning or submersion',12784,NULL,NULL,NULL),(2,'E911','','Inhalation and ingestion of food causing obstruction of respiratory tract or suffocation',12785,NULL,NULL,NULL),(2,'E912','','Inhalation and ingestion of other object causing obstruction of respiratory tract or suffocation',12786,NULL,NULL,NULL),(2,'E913.0','','Accidental mechanical suffocation in bed or cradle',12787,NULL,NULL,NULL),(2,'E913.1','','Accidental mechanical suffocation by plastic bag',12788,NULL,NULL,NULL),(2,'E913.2','','Accidental mechanical suffocation due to lack of air (in closed place)',12789,NULL,NULL,NULL),(2,'E913.3','','Accidental mechanical suffocation by falling earth or other substance',12790,NULL,NULL,NULL),(2,'E913.8','','Accidental mechanical suffocation by other specified means',12791,NULL,NULL,NULL),(2,'E913.9','','Accidental mechanical suffocation by unspecified means',12792,NULL,NULL,NULL),(2,'E914','','Foreign body accidentally entering eye and adnexa',12793,NULL,NULL,NULL),(2,'E915','','Foreign body accidentally entering other orifice',12794,NULL,NULL,NULL),(2,'E916','','Struck accidentally by falling object',12795,NULL,NULL,NULL),(2,'E917.0','','Striking against or struck accidentally in sports without subsequent fall',12796,NULL,NULL,NULL),(2,'E917.1','','Striking against or struck accidentally by a crowd by collective fear or panic without subsequent fall',12797,NULL,NULL,NULL),(2,'E917.2','','Striking against or struck accidentally in running water without subsequent fall',12798,NULL,NULL,NULL),(2,'E917.3','','Striking against or struck accidentally by furniture without subsequent fall',12799,NULL,NULL,NULL),(2,'E917.4','','Striking against or struck accidentally by other stationary object without subsequent fall',12800,NULL,NULL,NULL),(2,'E917.5','','Striking against or struck accidentally in sports with subsequent fall',12801,NULL,NULL,NULL),(2,'E917.6','','Striking against or struck accidentally by a crowd by collective fear or panic with subsequent fall',12802,NULL,NULL,NULL),(2,'E917.7','','Striking against or struck accidentally by furniture with subsequent fall',12803,NULL,NULL,NULL),(2,'E917.8','','Striking against or struck accidentally by other stationary object with subsequent fall',12804,NULL,NULL,NULL),(2,'E917.9','','Other accident caused by striking against or being struck accidentally by objects or persons with/without subsequent fall',12805,NULL,NULL,NULL),(2,'E918','','Caught accidentally in or between objects',12806,NULL,NULL,NULL),(2,'E919.0','','Accidents caused by agricultural machines',12807,NULL,NULL,NULL),(2,'E919.1','','Accidents caused by mining and earth-drilling machinery',12808,NULL,NULL,NULL),(2,'E919.2','','Accidents caused by lifting machines and appliances',12809,NULL,NULL,NULL),(2,'E919.3','','Accidents caused by metalworking machines',12810,NULL,NULL,NULL),(2,'E919.4','','Accidents caused by woodworking and forming machines',12811,NULL,NULL,NULL),(2,'E919.5','','Accidents caused by prime movers except electrical motors',12812,NULL,NULL,NULL),(2,'E919.6','','Accidents caused by transmission machinery',12813,NULL,NULL,NULL),(2,'E919.7','','Accidents caused by earth moving scraping and other excavating machines',12814,NULL,NULL,NULL),(2,'E919.8','','Accidents caused by other specified machinery',12815,NULL,NULL,NULL),(2,'E919.9','','Accidents caused by unspecified machinery',12816,NULL,NULL,NULL),(2,'E920.0','','Accidents caused by powered lawn mower',12817,NULL,NULL,NULL),(2,'E920.1','','Accidents caused by other powered hand tools',12818,NULL,NULL,NULL),(2,'E920.2','','Accidents caused by powered household appliances and implements',12819,NULL,NULL,NULL),(2,'E920.3','','Accidents caused by knives swords and daggers',12820,NULL,NULL,NULL),(2,'E920.4','','Accidents caused by other hand tools and implements',12821,NULL,NULL,NULL),(2,'E920.5','','Accidents caused by hypodermic needle',12822,NULL,NULL,NULL),(2,'E920.8','','Accidents caused by other specified cutting and piercing instruments or objects',12823,NULL,NULL,NULL),(2,'E920.9','','Accidents caused by unspecified cutting and piercing instrument or object',12824,NULL,NULL,NULL),(2,'E921.0','','Accident caused by explosion of boilers',12825,NULL,NULL,NULL),(2,'E921.1','','Accident caused by explosion of gas cylinders',12826,NULL,NULL,NULL),(2,'E921.8','','Accident caused by explosion of other specified pressure vessels',12827,NULL,NULL,NULL),(2,'E921.9','','Accident caused by explosion of unspecified pressure vessel',12828,NULL,NULL,NULL),(2,'E922.0','','Accident caused by handgun',12829,NULL,NULL,NULL),(2,'E922.1','','Accident caused by shotgun (automatic)',12830,NULL,NULL,NULL),(2,'E922.2','','Accident caused by hunting rifle',12831,NULL,NULL,NULL),(2,'E922.3','','Accident caused by military firearms',12832,NULL,NULL,NULL),(2,'E922.4','','Accident caused by air gun',12833,NULL,NULL,NULL),(2,'E922.5','','Accident caused by paintball gun',12834,NULL,NULL,NULL),(2,'E922.8','','Accident caused by other specified firearm missile',12835,NULL,NULL,NULL),(2,'E922.9','','Accident caused by unspecified firearm missile',12836,NULL,NULL,NULL),(2,'E923.0','','Accident caused by fireworks',12837,NULL,NULL,NULL),(2,'E923.1','','Accident caused by blasting materials',12838,NULL,NULL,NULL),(2,'E923.2','','Accident caused by explosive gases',12839,NULL,NULL,NULL),(2,'E923.8','','Accident caused by other explosive materials',12840,NULL,NULL,NULL),(2,'E923.9','','Accident caused by unspecified explosive material',12841,NULL,NULL,NULL),(2,'E924.0','','Accident caused by hot liquids and vapors including steam',12842,NULL,NULL,NULL),(2,'E924.1','','Accident caused by caustic and corrosive substances',12843,NULL,NULL,NULL),(2,'E924.2','','Accident caused by hot (boiling) tap water',12844,NULL,NULL,NULL),(2,'E924.8','','Accident caused by other hot substance or object',12845,NULL,NULL,NULL),(2,'E924.9','','Accident caused by unspecified hot substance or object',12846,NULL,NULL,NULL),(2,'E925.0','','Accident caused by domestic wiring and appliances',12847,NULL,NULL,NULL),(2,'E925.1','','Accident caused by electric current in electric power generating plants distribution stations transmission lines',12848,NULL,NULL,NULL),(2,'E925.2','','Accident caused by industrial wiring appliances and electrical machinery',12849,NULL,NULL,NULL),(2,'E925.8','','Accident caused by other electric current',12850,NULL,NULL,NULL),(2,'E925.9','','Accident caused by unspecified electric current',12851,NULL,NULL,NULL),(2,'E926.0','','Exposure to radiofrequency radiation',12852,NULL,NULL,NULL),(2,'E926.1','','Exposure to infra-red radiation from heaters and lamps',12853,NULL,NULL,NULL),(2,'E926.2','','Exposure to visible and ultraviolet light sources',12854,NULL,NULL,NULL),(2,'E926.3','','Exposure to x-rays and other electromagnetic ionizing radiation',12855,NULL,NULL,NULL),(2,'E926.4','','Exposure to lasers',12856,NULL,NULL,NULL),(2,'E926.5','','Exposure to radioactive isotopes',12857,NULL,NULL,NULL),(2,'E926.8','','Exposure to other specified radiation',12858,NULL,NULL,NULL),(2,'E926.9','','Exposure to unspecified radiation',12859,NULL,NULL,NULL),(2,'E927.0','','Overexertion from sudden strenuous movement',12860,NULL,NULL,NULL),(2,'E927.1','','Overexertion from prolonged static position',12861,NULL,NULL,NULL),(2,'E927.2','','Excessive physical exertion from prolonged activity',12862,NULL,NULL,NULL),(2,'E927.3','','Cumulative trauma from repetitive motion',12863,NULL,NULL,NULL),(2,'E927.4','','Cumulative trauma from repetitive impact',12864,NULL,NULL,NULL),(2,'E927.8','','Other overexertion and strenuous and repetitive movements or loads',12865,NULL,NULL,NULL),(2,'E927.9','','Unspecified overexertion and strenuous and repetitive movements or loads',12866,NULL,NULL,NULL),(2,'E928.0','','Prolonged stay in weightless environment',12867,NULL,NULL,NULL),(2,'E928.1','','Exposure to noise',12868,NULL,NULL,NULL),(2,'E928.2','','Vibration',12869,NULL,NULL,NULL),(2,'E928.3','','Human bite',12870,NULL,NULL,NULL),(2,'E928.4','','External constriction caused by hair',12871,NULL,NULL,NULL),(2,'E928.5','','External constriction caused by other object',12872,NULL,NULL,NULL),(2,'E928.6','','External constriction caused by environmental exposure to harmful algae and toxins',12873,NULL,NULL,NULL),(2,'E928.7','','Environmental and accidental causes, mechanism or component of firearm and air gun',12874,NULL,NULL,NULL),(2,'E928.8','','Other accidents',12875,NULL,NULL,NULL),(2,'E928.9','','Unspecified accident',12876,NULL,NULL,NULL),(2,'E929.0','','Late effects of motor vehicle accident',12877,NULL,NULL,NULL),(2,'E929.1','','Late effects of other transport accident',12878,NULL,NULL,NULL),(2,'E929.2','','Late effects of accidental poisoning',12879,NULL,NULL,NULL),(2,'E929.3','','Late effects of accidental fall',12880,NULL,NULL,NULL),(2,'E929.4','','Late effects of accident caused by fire',12881,NULL,NULL,NULL),(2,'E929.5','','Late effects of accident due to natural and environmental factors',12882,NULL,NULL,NULL),(2,'E929.8','','Late effects of other accidents',12883,NULL,NULL,NULL),(2,'E929.9','','Late effects of unspecified accident',12884,NULL,NULL,NULL),(2,'E930.0','','Penicillins',12885,NULL,NULL,NULL),(2,'E930.1','','Antifungal antibiotics causing adverse effects in therapeutic use',12886,NULL,NULL,NULL),(2,'E930.2','','Chloramphenicol group causing adverse effects in therapeutic use',12887,NULL,NULL,NULL),(2,'E930.3','','Erythromycin and other macrolides causing adverse effects in therapeutic use',12888,NULL,NULL,NULL),(2,'E930.4','','Tetracycline group causing adverse effects in therapeutic use',12889,NULL,NULL,NULL),(2,'E930.5','','Cephalosporin group causing adverse effects in therapeutic use',12890,NULL,NULL,NULL),(2,'E930.6','','Antimycobacterial antibiotics causing adverse effects in therapeutic use',12891,NULL,NULL,NULL),(2,'E930.7','','Antineoplastic antibiotics causing adverse effects in therapeutic use',12892,NULL,NULL,NULL),(2,'E930.8','','Other specified antibiotics causing adverse effects in therapeutic use',12893,NULL,NULL,NULL),(2,'E930.9','','Unspecified antibiotic causing adverse effects in therapeutic use',12894,NULL,NULL,NULL),(2,'E931.0','','Sulfonamides causing adverse effects in therapeutic use',12895,NULL,NULL,NULL),(2,'E931.1','','Arsenical anti-infectives causing adverse effects in therapeutic use',12896,NULL,NULL,NULL),(2,'E931.2','','Heavy metal anti-infectives causing adverse effects in therapeutic use',12897,NULL,NULL,NULL),(2,'E931.3','','Quinoline and hydroxyquinoline derivatives causing adverse effects in therapeutic use',12898,NULL,NULL,NULL),(2,'E931.4','','Antimalarials and drugs acting on other blood protozoa causing adverse effects in therapeutic use',12899,NULL,NULL,NULL),(2,'E931.5','','Other antiprotozoal drugs causing adverse effects in therapeutic use',12900,NULL,NULL,NULL),(2,'E931.6','','Anthelmintics causing adverse effects in therapeutic use',12901,NULL,NULL,NULL),(2,'E931.7','','Antiviral drugs causing adverse effects in therapeutic use',12902,NULL,NULL,NULL),(2,'E931.8','','Other antimycobacterial drugs causing adverse effects in therapeutic use',12903,NULL,NULL,NULL),(2,'E931.9','','Other and unspecified anti-infectives causing adverse effects in therapeutic use',12904,NULL,NULL,NULL),(2,'E932.0','','Adrenal cortical steroids causing adverse effects in therapeutic use',12905,NULL,NULL,NULL),(2,'E932.1','','Androgens and anabolic congeners causing adverse effects in therapeutic use',12906,NULL,NULL,NULL),(2,'E932.2','','Ovarian hormones and synthetic substitutes causing adverse effects in therapeutic use',12907,NULL,NULL,NULL),(2,'E932.3','','Insulins and antidiabetic agents causing adverse effects in therapeutic use',12908,NULL,NULL,NULL),(2,'E932.4','','Anterior pituitary hormones causing adverse effects in therapeutic use',12909,NULL,NULL,NULL),(2,'E932.5','','Posterior pituitary hormones causing adverse effects in therapeutic use',12910,NULL,NULL,NULL),(2,'E932.6','','Parathyroid and parathyroid derivatives causing adverse effects in therapeutic use',12911,NULL,NULL,NULL),(2,'E932.7','','Thyroid and thyroid derivatives causing adverse effects in therapeutic use',12912,NULL,NULL,NULL),(2,'E932.8','','Antithyroid agents causing adverse effects in therapeutic use',12913,NULL,NULL,NULL),(2,'E932.9','','Other and unspecified hormones and synthetic substitutes causing adverse effects in therapeutic use',12914,NULL,NULL,NULL),(2,'E933.0','','Antiallergic and antiemetic drugs causing adverse effects in therapeutic use',12915,NULL,NULL,NULL),(2,'E933.1','','Antineoplastic and immunosuppressive drugs causing adverse effects in therapeutic use',12916,NULL,NULL,NULL),(2,'E933.2','','Acidifying agents causing adverse effects in therapeutic use',12917,NULL,NULL,NULL),(2,'E933.3','','Alkalizing agents causing adverse effects in therapeutic use',12918,NULL,NULL,NULL),(2,'E933.4','','Enzymes not elsewhere classified causing adverse effects in therapeutic use',12919,NULL,NULL,NULL),(2,'E933.5','','Vitamins not elsewhere classified causing adverse effects in therapeutic use',12920,NULL,NULL,NULL),(2,'E933.6','','Oral bisphosphomates',12921,NULL,NULL,NULL),(2,'E933.7','','Intravenous bisphosphonates',12922,NULL,NULL,NULL),(2,'E933.8','','Other systemic agents not elsewhere classified causing adverse effects in therapeutic use',12923,NULL,NULL,NULL),(2,'E933.9','','Unspecified systemic agent causing adverse effects in therapeutic use',12924,NULL,NULL,NULL),(2,'E934.0','','Iron and its compounds causing adverse effects in therapeutic use',12925,NULL,NULL,NULL),(2,'E934.1','','Liver preparations and other antianemic agents causing adverse effects in therapeutic use',12926,NULL,NULL,NULL),(2,'E934.2','','Anticoagulants causing adverse effects in therapeutic use',12927,NULL,NULL,NULL),(2,'E934.3','','Vitamin k (phytonadione) causing adverse effects in therapeutic use',12928,NULL,NULL,NULL),(2,'E934.4','','Fibrinolysis-affecting drugs causing adverse effects in therapeutic use',12929,NULL,NULL,NULL),(2,'E934.5','','Anticoagulant antagonists and other coagulants causing adverse effects in therapeutic use',12930,NULL,NULL,NULL),(2,'E934.6','','Gamma globulin causing adverse effects in therapeutic use',12931,NULL,NULL,NULL),(2,'E934.7','','Natural blood and blood products causing adverse effects in therapeutic use',12932,NULL,NULL,NULL),(2,'E934.8','','Other agents affecting blood constituents causing adverse effects in therapeutic use',12933,NULL,NULL,NULL),(2,'E934.9','','Unspecified agent affecting blood constituents causing adverse effects in therapeutic use',12934,NULL,NULL,NULL),(2,'E935.0','','Heroin causing adverse effects in therapeutic use',12935,NULL,NULL,NULL),(2,'E935.1','','Methadone causing averse effects in therapeutic use',12936,NULL,NULL,NULL),(2,'E935.2','','Other opiates and related narcotics causing adverse effects in therapeutic use',12937,NULL,NULL,NULL),(2,'E935.3','','Salicylates causing adverse effects in therapeutic use',12938,NULL,NULL,NULL),(2,'E935.4','','Aromatic analgesics not elsewhere classified causing adverse effects in therapeutic use',12939,NULL,NULL,NULL),(2,'E935.5','','Pyrazole derivatives causing adverse effects in therapeutic use',12940,NULL,NULL,NULL),(2,'E935.6','','Antirheumatics (antiphlogistics) causing adverse effects in therapeutic use',12941,NULL,NULL,NULL),(2,'E935.7','','Other non-narcotic analgesics causing adverse effects in therapeutic use',12942,NULL,NULL,NULL),(2,'E935.8','','Other specified analgesics and antipyretics causing adverse effects in therapeutic use',12943,NULL,NULL,NULL),(2,'E935.9','','Unspecified analgesic and antipyretic causing adverse effects in therapeutic use',12944,NULL,NULL,NULL),(2,'E936.0','','Oxazolidine derivatives causing adverse effects in therapeutic use',12945,NULL,NULL,NULL),(2,'E936.1','','Hydantoin derivatives causing adverse effects in therapeutic use',12946,NULL,NULL,NULL),(2,'E936.2','','Succinimides causing adverse effects in therapeutic use',12947,NULL,NULL,NULL),(2,'E936.3','','Other and unspecified anticonvulsants causing adverse effects in therapeutic use',12948,NULL,NULL,NULL),(2,'E936.4','','Anti-parkinsonism drugs causing adverse effects in therapeutic use',12949,NULL,NULL,NULL),(2,'E937.0','','Barbiturates causing adverse effects in therapeutic use',12950,NULL,NULL,NULL),(2,'E937.1','','Chloral hydrate group causing adverse effects in therapeutic use',12951,NULL,NULL,NULL),(2,'E937.2','','Paraldehyde causing adverse effects in therapeutic use',12952,NULL,NULL,NULL),(2,'E937.3','','Bromine compounds causing adverse effects in therapeutic use',12953,NULL,NULL,NULL),(2,'E937.4','','Methaqualone compounds causing adverse effects in therapeutic use',12954,NULL,NULL,NULL),(2,'E937.5','','Glutethimide group causing adverse effects in therapeutic use',12955,NULL,NULL,NULL),(2,'E937.6','','Mixed sedatives not elsewhere classified causing adverse effects in therapeutic use',12956,NULL,NULL,NULL),(2,'E937.8','','Other sedatives and hypnotics causing adverse effects in therapeutic use',12957,NULL,NULL,NULL),(2,'E937.9','','Unspecified sedatives and hypnotics causing adverse effects in therapeutic use',12958,NULL,NULL,NULL),(2,'E938.0','','Central nervous system muscle-tone depressants causing adverse effects in therapeutic use',12959,NULL,NULL,NULL),(2,'E938.1','','Halothane causing adverse effects in therapeutic use',12960,NULL,NULL,NULL),(2,'E938.2','','Other gaseous anesthetics causing adverse effects in therapeutic use',12961,NULL,NULL,NULL),(2,'E938.3','','Intravenous anesthetics causing adverse effects in therapeutic use',12962,NULL,NULL,NULL),(2,'E938.4','','Other and unspecified general anesthetics causing adverse effects in therapeutic use',12963,NULL,NULL,NULL),(2,'E938.5','','Surface and infiltration anesthetics causing adverse effects in therapeutic use',12964,NULL,NULL,NULL),(2,'E938.6','','Peripheral nerve- and plexus-blocking anesthetics causing adverse effects in therapeutic use',12965,NULL,NULL,NULL),(2,'E938.7','','Spinal anesthetics causing adverse effects in therapeutic use',12966,NULL,NULL,NULL),(2,'E938.9','','Other and unspecified local anesthetics causing adverse effects in therapeutic use',12967,NULL,NULL,NULL),(2,'E939.0','','Antidepressants causing adverse effects in therapeutic use',12968,NULL,NULL,NULL),(2,'E939.1','','Phenothiazine-based tranquilizers causing adverse effects in therapeutic use',12969,NULL,NULL,NULL),(2,'E939.2','','Butyrophenone-based tranquilizers causing adverse effects in therapeutic use',12970,NULL,NULL,NULL),(2,'E939.3','','Other antipsychotics neuroleptics and major tranquilizers causing adverse effects in therapeutic use',12971,NULL,NULL,NULL),(2,'E939.4','','Benzodiazepine-based tranquilizers causing adverse effects in therapeutic use',12972,NULL,NULL,NULL),(2,'E939.5','','Other tranquilizers causing adverse effects in therapeutic use',12973,NULL,NULL,NULL),(2,'E939.6','','Psychodysleptics (hallucinogens) causing adverse effects in therapeutic use',12974,NULL,NULL,NULL),(2,'E939.7','','Psychostimulants causing adverse effects in therapeutic use',12975,NULL,NULL,NULL),(2,'E939.8','','Other psychotropic agents causing adverse effects in therapeutic use',12976,NULL,NULL,NULL),(2,'E939.9','','Unspecified psychotropic agent causing adverse effects in therapeutic use',12977,NULL,NULL,NULL),(2,'E940.0','','Analeptics causing adverse effects in therapeutic use',12978,NULL,NULL,NULL),(2,'E940.1','','Opiate antagonists causing adverse effects in therapeutic use',12979,NULL,NULL,NULL),(2,'E940.8','','Other specified central nervous system stimulants causing adverse effects in therapeutic use',12980,NULL,NULL,NULL),(2,'E940.9','','Unspecified central nervous system stimulant causing adverse effects in therapeutic use',12981,NULL,NULL,NULL),(2,'E941.0','','Parasympathomimetics (cholinergics) causing adverse effects in therapeutic use',12982,NULL,NULL,NULL),(2,'E941.1','','Parasympatholytics (anticholinergics and antimuscarinics) and spasmolytics causing adverse effects in therapeutic use',12983,NULL,NULL,NULL),(2,'E941.2','','Sympathomimetics (adrenergics) causing adverse effects in therapeutic use',12984,NULL,NULL,NULL),(2,'E941.3','','Sympatholytics (antiadrenergics) causing adverse effects in therapeutic use',12985,NULL,NULL,NULL),(2,'E941.9','','Unspecified drug primarily affecting the autonomic nervous system causing adverse effects in therapeutic use',12986,NULL,NULL,NULL),(2,'E942.0','','Cardiac rhythm regulators causing adverse effects in therapeutic use',12987,NULL,NULL,NULL),(2,'E942.1','','Cardiotonic glycosides and drugs of similar action causing adverse effects in therapeutic use',12988,NULL,NULL,NULL),(2,'E942.2','','Antilipemic and antiarteriosclerotic drugs causing adverse effects in therapeutic use',12989,NULL,NULL,NULL),(2,'E942.3','','Ganglion-blocking agents causing adverse effects in therapeutic use',12990,NULL,NULL,NULL),(2,'E942.4','','Coronary vasodilators causing adverse effects in therapeutic use',12991,NULL,NULL,NULL),(2,'E942.5','','Other vasodilators causing adverse effects in therapeutic use',12992,NULL,NULL,NULL),(2,'E942.6','','Other antihypertensive agents causing adverse effects in therapeutic use',12993,NULL,NULL,NULL),(2,'E942.7','','Antivaricose drugs including sclerosing agents causing adverse effects in therapeutic use',12994,NULL,NULL,NULL),(2,'E942.8','','Capillary-active drugs causing adverse effects in therapeutic use',12995,NULL,NULL,NULL),(2,'E942.9','','Other and unspecified agents primarily affecting the cardiovascular system causing adverse effects in therapeutic use',12996,NULL,NULL,NULL),(2,'E943.0','','Antacids and antigastric secretion drugs causing adverse effects in therapeutic use',12997,NULL,NULL,NULL),(2,'E943.1','','Irritant cathartics causing adverse effects in therapeutic use',12998,NULL,NULL,NULL),(2,'E943.2','','Emollient cathartics causing adverse effects in therapeutic use',12999,NULL,NULL,NULL),(2,'E943.3','','Other cathartics including intestinal atonia drugs causing adverse effects in therapeutic use',13000,NULL,NULL,NULL),(2,'E943.4','','Digestants causing adverse effects in therapeutic use',13001,NULL,NULL,NULL),(2,'E943.5','','Antidiarrheal drugs causing adverse effects in therapeutic use',13002,NULL,NULL,NULL),(2,'E943.6','','Emetics causing adverse effects in therapeutic use',13003,NULL,NULL,NULL),(2,'E943.8','','Other specified agents primarily affecting the gastro-intestinal system causing adverse effects in therapeutic use',13004,NULL,NULL,NULL),(2,'E943.9','','Unspecified agent primarily affecting the gastrointestinal system causing adverse effects in therapeutic use',13005,NULL,NULL,NULL),(2,'E944.0','','Mercurial diuretics causing adverse effects in therapeutic use',13006,NULL,NULL,NULL),(2,'E944.1','','Purine derivative diuretics causing adverse effects in therapeutic use',13007,NULL,NULL,NULL),(2,'E944.2','','Carbonic acid anhydrase inhibitors causing adverse effects in therapeutic use',13008,NULL,NULL,NULL),(2,'E944.3','','Saluretics causing adverse effects in therapeutic use',13009,NULL,NULL,NULL),(2,'E944.4','','Other diuretics causing adverse effects in therapeutic use',13010,NULL,NULL,NULL),(2,'E944.5','','Electrolytic caloric and water-balance agents causing adverse effects in therapeutic use',13011,NULL,NULL,NULL),(2,'E944.6','','Other mineral salts not elsewhere classified causing adverse effects in therapeutic use',13012,NULL,NULL,NULL),(2,'E944.7','','Uric acid metabolism drugs causing adverse effects in therapeutic use',13013,NULL,NULL,NULL),(2,'E945.0','','Oxytocic agents causing adverse effects in therapeutic use',13014,NULL,NULL,NULL),(2,'E945.1','','Smooth muscle relaxants causing adverse effects in therapeutic use',13015,NULL,NULL,NULL),(2,'E945.2','','Skeletal muscle relaxants causing adverse effects in therapeutic use',13016,NULL,NULL,NULL),(2,'E945.3','','Other and unspecified drugs acting on muscles causing adverse effects in therapeutic use',13017,NULL,NULL,NULL),(2,'E945.4','','Antitussives causing adverse effects in therapeutic use',13018,NULL,NULL,NULL),(2,'E945.5','','Expectorants causing adverse effects in therapeutic use',13019,NULL,NULL,NULL),(2,'E945.6','','Anti-common cold drugs causing adverse effects in therapeutic use',13020,NULL,NULL,NULL),(2,'E945.7','','Antiasthmatics causing adverse effects in therapeutic use',13021,NULL,NULL,NULL),(2,'E945.8','','Other and unspecified respiratory drugs causing adverse effects in therapeutic use',13022,NULL,NULL,NULL),(2,'E946.0','','Local anti-infectives and anti-inflammatory drugs causing adverse effects in therapeutic use',13023,NULL,NULL,NULL),(2,'E946.1','','Antipruritics causing adverse effects in therapeutic use',13024,NULL,NULL,NULL),(2,'E946.2','','Local astringents and local detergents causing adverse effects in therapeutic use',13025,NULL,NULL,NULL),(2,'E946.3','','Emollients demulcents and protectants causing adverse effects in therapeutic use',13026,NULL,NULL,NULL),(2,'E946.4','','Keratolytics keratoplastics other hair treatment drugs and preparations causing adverse effects in therapeutic use',13027,NULL,NULL,NULL),(2,'E946.5','','Eye anti-infectives and other eye drugs causing adverse effects in therapeutic use',13028,NULL,NULL,NULL),(2,'E946.6','','Anti-infectives and other drugs and preparations for ear nose and throat causing adverse effects in therapeutic use',13029,NULL,NULL,NULL),(2,'E946.7','','Dental drugs topically applied causing adverse effects in therapeutic use',13030,NULL,NULL,NULL),(2,'E946.8','','Other agents primarily affecting skin and mucous membrane causing adverse effects in therapeutic use',13031,NULL,NULL,NULL),(2,'E946.9','','Unspecified agent primarily affecting skin and mucous membrane causing adverse effects in therapeutic use',13032,NULL,NULL,NULL),(2,'E947.0','','Dietetics causing adverse effects in therapeutic use',13033,NULL,NULL,NULL),(2,'E947.1','','Lipotropic drugs causing adverse effects in therapeutic use',13034,NULL,NULL,NULL),(2,'E947.2','','Antidotes and chelating agents not elsewhere classified causing adverse effects in therapeutic use',13035,NULL,NULL,NULL),(2,'E947.3','','Alcohol deterrents causing adverse effects in therapeutic use',13036,NULL,NULL,NULL),(2,'E947.4','','Pharmaceutical excipients causing adverse effects in therapeutic use',13037,NULL,NULL,NULL),(2,'E947.8','','Other drugs and medicinal substances causing adverse effects in therapeutic use',13038,NULL,NULL,NULL),(2,'E947.9','','Unspecified drug or medicinal substance causing adverse effects in therapeutic use',13039,NULL,NULL,NULL),(2,'E948.0','','Bcg vaccine causing adverse effects in therapeutic use',13040,NULL,NULL,NULL),(2,'E948.1','','Typhoid and paratyphoid vaccines causing adverse effects in therapeutic use',13041,NULL,NULL,NULL),(2,'E948.2','','Cholera vaccine causing adverse effects in therapeutic use',13042,NULL,NULL,NULL),(2,'E948.3','','Plague vaccine causing adverse effects in therapeutic use',13043,NULL,NULL,NULL),(2,'E948.4','','Tetanus vaccine causing adverse effects in therapeutic use',13044,NULL,NULL,NULL),(2,'E948.5','','Diphtheria vaccine causing adverse effects in therapeutic use',13045,NULL,NULL,NULL),(2,'E948.6','','Pertussis vaccine including combinations with a pertussis component causing adverse effects in therapeutic use',13046,NULL,NULL,NULL),(2,'E948.8','','Other and unspecified bacterial vaccines causing adverse effects in therapeutic use',13047,NULL,NULL,NULL),(2,'E948.9','','Mixed bacterial vaccines except combinations with a pertussis component causing adverse effects in therapeutic use',13048,NULL,NULL,NULL),(2,'E949.0','','Smallpox vaccine causing adverse effects in therapeutic use',13049,NULL,NULL,NULL),(2,'E949.1','','Rabies vaccine causing adverse effects in therapeutic use',13050,NULL,NULL,NULL),(2,'E949.2','','Typhus vaccine causing adverse effects in therapeutic use',13051,NULL,NULL,NULL),(2,'E949.3','','Yellow fever vaccine causing adverse effects in therapeutic use',13052,NULL,NULL,NULL),(2,'E949.4','','Measles vaccine causing adverse effects in therapeutic use',13053,NULL,NULL,NULL),(2,'E949.5','','Poliomyelitis vaccine causing adverse effects in therapeutic use',13054,NULL,NULL,NULL),(2,'E949.6','','Other and unspecified viral and rickettsial vaccines causing adverse effects in therapeutic use',13055,NULL,NULL,NULL),(2,'E949.7','','Mixed viral-rickettsial and bacterial vaccines except combinations with a pertussis component causing adverse effects in therapeutic use',13056,NULL,NULL,NULL),(2,'E949.9','','Other and unspecified vaccines and biological substances causing adverse effects in therapeutic use',13057,NULL,NULL,NULL),(2,'E950.0','','Suicide and self-inflicted poisoning by analgesics antipyretics and antirheumatics',13058,NULL,NULL,NULL),(2,'E950.1','','Suicide and self-inflicted poisoning by barbiturates',13059,NULL,NULL,NULL),(2,'E950.2','','Suicide and self-inflicted poisoning by other sedatives and hypnotics',13060,NULL,NULL,NULL),(2,'E950.3','','Suicide and self-inflicted poisoning by tranquilizers and other psychotropic agents',13061,NULL,NULL,NULL),(2,'E950.4','','Suicide and self-inflicted poisoning by other specified drugs and medicinal substances',13062,NULL,NULL,NULL),(2,'E950.5','','Suicide and self-inflicted poisoning by unspecified drug or medicinal substance',13063,NULL,NULL,NULL),(2,'E950.6','','Suicide and self-inflicted poisoning by agricultural and horticultural chemical and pharmaceutical preparations other than plant foods and fertilizers',13064,NULL,NULL,NULL),(2,'E950.7','','Suicide and self-inflicted poisoning by corrosive and caustic substances',13065,NULL,NULL,NULL),(2,'E950.8','','Suicide and self-inflicted poisoning by arsenic and its compounds',13066,NULL,NULL,NULL),(2,'E950.9','','Suicide and self-inflicted poisoning by other and unspecified solid and liquid substances',13067,NULL,NULL,NULL),(2,'E951.0','','Suicide and self-inflicted poisoning by gas distributed by pipeline',13068,NULL,NULL,NULL),(2,'E951.1','','Suicide and self-inflicted poisoning by liquefied petroleum gas distributed in mobile containers',13069,NULL,NULL,NULL),(2,'E951.8','','Suicide and self-inflicted poisoning by other utility gas',13070,NULL,NULL,NULL),(2,'E952.0','','Suicide and self-inflicted poisoning by motor vehicle exhaust gas',13071,NULL,NULL,NULL),(2,'E952.1','','Suicide and self-inflicted poisoning by other carbon monoxide',13072,NULL,NULL,NULL),(2,'E952.8','','Suicide and self-inflicted poisoning by other specified gases and vapors',13073,NULL,NULL,NULL),(2,'E952.9','','Suicide and self-inflicted poisoning by unspecified gases and vapors',13074,NULL,NULL,NULL),(2,'E953.0','','Suicide and self-inflicted injury by hanging',13075,NULL,NULL,NULL),(2,'E953.1','','Suicide and self-inflicted injury by suffocation by plastic bag',13076,NULL,NULL,NULL),(2,'E953.8','','Suicide and self-inflicted injury by other specified means',13077,NULL,NULL,NULL),(2,'E953.9','','Suicide and self-inflicted injury by unspecified means',13078,NULL,NULL,NULL),(2,'E954','','Suicide and self-inflicted injury by submersion (drowning)',13079,NULL,NULL,NULL),(2,'E955.0','','Suicide and self-inflicted injury by handgun',13080,NULL,NULL,NULL),(2,'E955.1','','Suicide and self-inflicted injury by shotgun',13081,NULL,NULL,NULL),(2,'E955.2','','Suicide and self-inflicted injury by hunting rifle',13082,NULL,NULL,NULL),(2,'E955.3','','Suicide and self-inflicted injury by military firearms',13083,NULL,NULL,NULL),(2,'E955.4','','Suicide and self-inflicted injury by other and unspecified firearm',13084,NULL,NULL,NULL),(2,'E955.5','','Suicide and self-inflicted injury by explosives',13085,NULL,NULL,NULL),(2,'E955.6','','Suicide and self-inflicted injury by air gun',13086,NULL,NULL,NULL),(2,'E955.7','','Suicide and self-inflicted injury by paintball gun',13087,NULL,NULL,NULL),(2,'E955.9','','Suicide and self-inflicted injury by firearms and explosives unspecified',13088,NULL,NULL,NULL),(2,'E956','','Suicide and self-inflicted injury by cutting and piercing instrument',13089,NULL,NULL,NULL),(2,'E957.0','','Suicide and self-inflicted injuries by jumping from residential premises',13090,NULL,NULL,NULL),(2,'E957.1','','Suicide and self-inflicted injuries by jumping from other man-made structures',13091,NULL,NULL,NULL),(2,'E957.2','','Suicide and self-inflicted injuries by jumping from natural sites',13092,NULL,NULL,NULL),(2,'E957.9','','Suicide and self-inflicted injuries by jumping from unspecified site',13093,NULL,NULL,NULL),(2,'E958.0','','Suicide and self-inflicted injury by jumping or lying before moving object',13094,NULL,NULL,NULL),(2,'E958.1','','Suicide and self-inflicted injury by burns fire',13095,NULL,NULL,NULL),(2,'E958.2','','Suicide and self-inflicted injury by scald',13096,NULL,NULL,NULL),(2,'E958.3','','Suicide and self-inflicted injury by extremes of cold',13097,NULL,NULL,NULL),(2,'E958.4','','Suicide and self-inflicted injury by electrocution',13098,NULL,NULL,NULL),(2,'E958.5','','Suicide and self-inflicted injury by crashing of motor vehicle',13099,NULL,NULL,NULL),(2,'E958.6','','Suicide and self-inflicted injury by crashing of aircraft',13100,NULL,NULL,NULL),(2,'E958.7','','Suicide and self-inflicted injury by caustic substances except poisoning',13101,NULL,NULL,NULL),(2,'E958.8','','Suicide and self-inflicted injury by other specified means',13102,NULL,NULL,NULL),(2,'E958.9','','Suicide and self-inflicted injury by unspecified means',13103,NULL,NULL,NULL),(2,'E959','','Late effects of self-inflicted injury',13104,NULL,NULL,NULL),(2,'E960.0','','Unarmed fight or brawl',13105,NULL,NULL,NULL),(2,'E960.1','','Rape',13106,NULL,NULL,NULL),(2,'E961','','Assault by corrosive or caustic substance except poisoning',13107,NULL,NULL,NULL),(2,'E962.0','','Assault by drugs and medicinal substances',13108,NULL,NULL,NULL),(2,'E962.1','','Assault by other solid and liquid substances',13109,NULL,NULL,NULL),(2,'E962.2','','Assault by other gases and vapors',13110,NULL,NULL,NULL),(2,'E962.9','','Assault by unspecified poisoning',13111,NULL,NULL,NULL),(2,'E963','','Assault by hanging and strangulation',13112,NULL,NULL,NULL),(2,'E964','','Assault by submersion (drowning)',13113,NULL,NULL,NULL),(2,'E965.0','','Assault by handgun',13114,NULL,NULL,NULL),(2,'E965.1','','Assault by shotgun',13115,NULL,NULL,NULL),(2,'E965.2','','Assault by hunting rifle',13116,NULL,NULL,NULL),(2,'E965.3','','Assault by military firearms',13117,NULL,NULL,NULL),(2,'E965.4','','Assault by other and unspecified firearm',13118,NULL,NULL,NULL),(2,'E965.5','','Assault by antipersonnel bomb',13119,NULL,NULL,NULL),(2,'E965.6','','Assault by gasoline bomb',13120,NULL,NULL,NULL),(2,'E965.7','','Assault by letter bomb',13121,NULL,NULL,NULL),(2,'E965.8','','Assault by other specified explosive',13122,NULL,NULL,NULL),(2,'E965.9','','Assault by unspecified explosive',13123,NULL,NULL,NULL),(2,'E966','','Assault by cutting and piercing instrument',13124,NULL,NULL,NULL),(2,'E967.0','','Perpetrator of child and adult abuse by father stepfather or boyfriend',13125,NULL,NULL,NULL),(2,'E967.1','','Perpetrator of child and adult abuse by other specified person',13126,NULL,NULL,NULL),(2,'E967.2','','Perpetrator of child and adult abuse by mother stepmother or girlfriend',13127,NULL,NULL,NULL),(2,'E967.3','','Perpetrator of child and adult abuse by spouse or partner',13128,NULL,NULL,NULL),(2,'E967.4','','Perpetrator of child and adult abuse by child',13129,NULL,NULL,NULL),(2,'E967.5','','Perpetrator of child and adult abuse by sibling',13130,NULL,NULL,NULL),(2,'E967.6','','Perpetrator of child and adult abuse by grandparent',13131,NULL,NULL,NULL),(2,'E967.7','','Perpetrator of child and adult abuse by other relative',13132,NULL,NULL,NULL),(2,'E967.8','','Perpetrator of child and adult abuse by non-related caregiver',13133,NULL,NULL,NULL),(2,'E967.9','','Perpetrator of child and adult abuse by unspecified person',13134,NULL,NULL,NULL),(2,'E968.0','','Assault by fire',13135,NULL,NULL,NULL),(2,'E968.1','','Assault by pushing from a high place',13136,NULL,NULL,NULL),(2,'E968.2','','Assault by striking by blunt or thrown object',13137,NULL,NULL,NULL),(2,'E968.3','','Assault by hot liquid',13138,NULL,NULL,NULL),(2,'E968.4','','Assault by criminal neglect',13139,NULL,NULL,NULL),(2,'E968.5','','Assault by transport vehicle',13140,NULL,NULL,NULL),(2,'E968.6','','Assault by air gun',13141,NULL,NULL,NULL),(2,'E968.7','','Assault by human bite',13142,NULL,NULL,NULL),(2,'E968.8','','Assault by other specified means',13143,NULL,NULL,NULL),(2,'E968.9','','Assault by unspecified means',13144,NULL,NULL,NULL),(2,'E969','','Late effects of injury purposely inflicted by other person',13145,NULL,NULL,NULL),(2,'E970','','Injury due to legal intervention by firearms',13146,NULL,NULL,NULL),(2,'E971','','Injury due to legal intervention by explosives',13147,NULL,NULL,NULL),(2,'E972','','Injury due to legal intervention by gas',13148,NULL,NULL,NULL),(2,'E973','','Injury due to legal intervention by blunt object',13149,NULL,NULL,NULL),(2,'E974','','Injury due to legal intervention by cutting and piercing instrument',13150,NULL,NULL,NULL),(2,'E975','','Injury due to legal intervention by other specified means',13151,NULL,NULL,NULL),(2,'E976','','Injury due to legal intervention by unspecified means',13152,NULL,NULL,NULL),(2,'E977','','Late effects of injuries due to legal intervention',13153,NULL,NULL,NULL),(2,'E978','','Legal execution',13154,NULL,NULL,NULL),(2,'E979.0','','Terrorism involving explosion of marine weapons',13155,NULL,NULL,NULL),(2,'E979.1','','Terrorism involving destruction of aircraft',13156,NULL,NULL,NULL),(2,'E979.2','','Terrorism involving other explosions and fragments',13157,NULL,NULL,NULL),(2,'E979.3','','Terrorism involving fires conflagration and hot substances',13158,NULL,NULL,NULL),(2,'E979.4','','Terrorism involving firearms',13159,NULL,NULL,NULL),(2,'E979.5','','Terrorism involving nuclear weapons',13160,NULL,NULL,NULL),(2,'E979.6','','Terrorism involving biological weapons',13161,NULL,NULL,NULL),(2,'E979.7','','Terrorism involving chemical weapons',13162,NULL,NULL,NULL),(2,'E979.8','','Terrorism involving other means',13163,NULL,NULL,NULL),(2,'E979.9','','Terrorism secondary effects',13164,NULL,NULL,NULL),(2,'E980.0','','Poisoning by analgesics antipyretics and antirheumatics undetermined whether accidentally or purposely inflicted',13165,NULL,NULL,NULL),(2,'E980.1','','Poisoning by barbiturates undetermined whether accidentally or purposely inflicted',13166,NULL,NULL,NULL),(2,'E980.2','','Poisoning by other sedatives and hypnotics undetermined whether accidentally or purposely inflicted',13167,NULL,NULL,NULL),(2,'E980.3','','Poisoning by tranquilizers and other psychotropic agents undetermined whether accidentally or purposely inflicted',13168,NULL,NULL,NULL),(2,'E980.4','','Poisoning by other specified drugs and medicinal substances undetermined whether accidentally or purposely inflicted',13169,NULL,NULL,NULL),(2,'E980.5','','Poisoning by unspecified drug or medicinal substance undetermined whether accidentally or purposely inflicted',13170,NULL,NULL,NULL),(2,'E980.6','','Poisoning by corrosive and caustic substances undetermined whether accidentally or purposely inflicted',13171,NULL,NULL,NULL),(2,'E980.7','','Poisoning by agricultural and horticultural chemical and pharmaceutical preparations other than plant foods and fertilizers undetermined whether accidentally or purposely inflicted',13172,NULL,NULL,NULL),(2,'E980.8','','Poisoning by arsenic and its compounds undetermined whether accidentally or purposely inflicted',13173,NULL,NULL,NULL),(2,'E980.9','','Poisoning by other and unspecified solid and liquid substances undetermined whether accidentally or purposely inflicted',13174,NULL,NULL,NULL),(2,'E981.0','','Poisoning by gas distributed by pipeline undetermined whether accidentally or purposely inflicted',13175,NULL,NULL,NULL),(2,'E981.1','','Poisoning by liquefied petroleum gas distributed in mobile containers undetermined whether accidentally or purposely inflicted',13176,NULL,NULL,NULL),(2,'E981.8','','Poisoning by other utility gas undetermined whether accidentally or purposely inflicted',13177,NULL,NULL,NULL),(2,'E982.0','','Poisoning by motor vehicle exhaust gas undetermined whether accidentally or purposely inflicted',13178,NULL,NULL,NULL),(2,'E982.1','','Poisoning by other carbon monoxide undetermined whether accidentally or purposely inflicted',13179,NULL,NULL,NULL),(2,'E982.8','','Poisoning by other specified gases and vapors undetermined whether accidentally or purposely inflicted',13180,NULL,NULL,NULL),(2,'E982.9','','Poisoning by unspecified gases and vapors undetermined whether accidentally or purposely inflicted',13181,NULL,NULL,NULL),(2,'E983.0','','Hanging undetermined whether accidentally or purposely inflicted',13182,NULL,NULL,NULL),(2,'E983.1','','Suffocation by plastic bag undetermined whether accidentally or purposely inflicted',13183,NULL,NULL,NULL),(2,'E983.8','','Strangulation or suffocation by other specified means undetermined whether accidentally or purposely inflicted',13184,NULL,NULL,NULL),(2,'E983.9','','Strangulation or suffocation by unspecified means undetermined whether accidentally or purposely inflicted',13185,NULL,NULL,NULL),(2,'E984','','Submersion (drowning) undetermined whether accidentally or purposely inflicted',13186,NULL,NULL,NULL),(2,'E985.0','','Injury by handgun undetermined whether accidentally or purposely inflicted',13187,NULL,NULL,NULL),(2,'E985.1','','Injury by shotgun undetermined whether accidentally or purposely inflicted',13188,NULL,NULL,NULL),(2,'E985.2','','Injury by hunting rifle undetermined whether accidentally or purposely inflicted',13189,NULL,NULL,NULL),(2,'E985.3','','Injury by military firearms undetermined whether accidentally or purposely inflicted',13190,NULL,NULL,NULL),(2,'E985.4','','Injury by other and unspecified firearm undetermined whether accidentally or purposely inflicted',13191,NULL,NULL,NULL),(2,'E985.5','','Injury by explosives undetermined whether accidentally or purposely inflicted',13192,NULL,NULL,NULL),(2,'E985.6','','Injury by air gun undetermined whether accidental or purposely inflicted',13193,NULL,NULL,NULL),(2,'E985.7','','Injury by paintball gun undetermined whether accidental or purposely inflicted',13194,NULL,NULL,NULL),(2,'E986','','Injury by cutting and piercing instruments undetermined whether accidentally or purposely inflicted',13195,NULL,NULL,NULL),(2,'E987.0','','Falling from residential premises undetermined whether accidentally or purposely inflicted',13196,NULL,NULL,NULL),(2,'E987.1','','Falling from other man-made structures undetermined whether accidentally or purposely inflicted',13197,NULL,NULL,NULL),(2,'E987.2','','Falling from natural sites undetermined whether accidentally or purposely inflicted',13198,NULL,NULL,NULL),(2,'E987.9','','Falling from unspecified site undetermined whether accidentally or purposely inflicted',13199,NULL,NULL,NULL),(2,'E988.0','','Injury by jumping or lying before moving object undetermined whether accidentally or purposely inflicted',13200,NULL,NULL,NULL),(2,'E988.1','','Injury by burns or fire undetermined whether accidentally or purposely inflicted',13201,NULL,NULL,NULL),(2,'E988.2','','Injury by scald undetermined whether accidentally or purposely inflicted',13202,NULL,NULL,NULL),(2,'E988.3','','Injury by extremes of cold undetermined whether accidentally or purposely inflicted',13203,NULL,NULL,NULL),(2,'E988.4','','Injury by electrocution undetermined whether accidentally or purposely inflicted',13204,NULL,NULL,NULL),(2,'E988.5','','Injury by crashing of motor vehicle undetermined whether accidentally or purposely inflicted',13205,NULL,NULL,NULL),(2,'E988.6','','Injury by crashing of aircraft undetermined whether accidentally or purposely inflicted',13206,NULL,NULL,NULL),(2,'E988.7','','Injury by caustic substances except poisoning undetermined whether accidentally or purposely inflicted',13207,NULL,NULL,NULL),(2,'E988.8','','Injury by other specified means undetermined whether accidentally or purposely inflicted',13208,NULL,NULL,NULL),(2,'E988.9','','Injury by unspecified means undetermined whether accidentally or purposely inflicted',13209,NULL,NULL,NULL),(2,'E989','','Late effects of injury undetermined whether accidentally or purposely inflicted',13210,NULL,NULL,NULL),(2,'E990.0','','Injury due to war operations from gasoline bomb',13211,NULL,NULL,NULL),(2,'E990.1','','Injury due to war operations from flamethrower',13212,NULL,NULL,NULL),(2,'E990.2','','Injury due to war operations from incendiary bullet',13213,NULL,NULL,NULL),(2,'E990.3','','Injury due to war operations from fire caused indirectly from conventional weapon',13214,NULL,NULL,NULL),(2,'E990.9','','Injury due to war operations from other and unspecified source',13215,NULL,NULL,NULL),(2,'E991.0','','Injury due to war operations from rubber bullets (rifle)',13216,NULL,NULL,NULL),(2,'E991.1','','Injury due to war operations from pellets (rifle)',13217,NULL,NULL,NULL),(2,'E991.2','','Injury due to war operations from other bullets',13218,NULL,NULL,NULL),(2,'E991.3','','Injury due to war operations from antipersonnel bomb (fragments)',13219,NULL,NULL,NULL),(2,'E991.4','','Injury due to war operations by fragments from munitions',13220,NULL,NULL,NULL),(2,'E991.5','','Injury due to war operations by fragments from person-borne improvised explosive device',13221,NULL,NULL,NULL),(2,'E991.6','','Injury due to war operations by fragments from vehicle-borne improvised explosive device (ied)',13222,NULL,NULL,NULL),(2,'E991.7','','Injury due to war operations by fragments from other improvised explosive device (ied)',13223,NULL,NULL,NULL),(2,'E991.8','','Injury due to war operations by fragments from weapons',13224,NULL,NULL,NULL),(2,'E991.9','','Injury due to war operations from other and unspecified fragments',13225,NULL,NULL,NULL),(2,'E992.0','','Injury due to torpedo',13226,NULL,NULL,NULL),(2,'E992.1','','Injury due to depth charge',13227,NULL,NULL,NULL),(2,'E992.2','','Injury due to marine mines',13228,NULL,NULL,NULL),(2,'E992.3','','Injury due to sea-based artillery shell',13229,NULL,NULL,NULL),(2,'E992.8','','Injury due to war operations by other marine weapons',13230,NULL,NULL,NULL),(2,'E992.9','','Injury due to war operations by unspecified marine weapon',13231,NULL,NULL,NULL),(2,'E993.0','','Injury due to war operations by aerial bomb',13232,NULL,NULL,NULL),(2,'E993.1','','Injury due to war operations by guided missile',13233,NULL,NULL,NULL),(2,'E993.2','','Injury due to war operations by mortar',13234,NULL,NULL,NULL),(2,'E993.3','','Injury due to war operations by person-borne improvised explosive device (ied)',13235,NULL,NULL,NULL),(2,'E993.4','','Injury due to war operations by vehicle-borne improvised explosive device (ied)',13236,NULL,NULL,NULL),(2,'E993.5','','Injury due to war operations by other improvised explosive device (ied)',13237,NULL,NULL,NULL),(2,'E993.6','','Injury due to war operations by unintentional detonation of own munitions',13238,NULL,NULL,NULL),(2,'E993.7','','Injury due to war operations by unintentional discharge of own munitions launch device',13239,NULL,NULL,NULL),(2,'E993.8','','Injury due to war operations by other specified explosion',13240,NULL,NULL,NULL),(2,'E993.9','','Injury due to war operations by unspecified explosion',13241,NULL,NULL,NULL),(2,'E994.0','','Injury due to war operations by destruction of aircraft due to enemy fire or explosives',13242,NULL,NULL,NULL),(2,'E994.1','','Injury due to war operations by unintentional destruction of aircraft due to own onboard explosives',13243,NULL,NULL,NULL),(2,'E994.2','','Injury due to war operations by destruction of aircraft due to collision with other aircraft',13244,NULL,NULL,NULL),(2,'E994.3','','Injury due to war operations by destruction of aircraft due to onboard fire',13245,NULL,NULL,NULL),(2,'E994.8','','Injury due to war operations by other destruction of aircraft',13246,NULL,NULL,NULL),(2,'E994.9','','Injury due to war operations by unspecified destruction of aircraft',13247,NULL,NULL,NULL),(2,'E995.0','','Injury due to war operations by unarmed hand-to-hand combat',13248,NULL,NULL,NULL),(2,'E995.1','','Injury due to war operations, struck by blunt object',13249,NULL,NULL,NULL),(2,'E995.2','','Injury due to war operations by piercing object',13250,NULL,NULL,NULL),(2,'E995.3','','Injury due to war operations by intentional restriction of air and airway',13251,NULL,NULL,NULL),(2,'E995.4','','Injury due to war operations by unintentional drowning due to inability to surface or obtain air',13252,NULL,NULL,NULL),(2,'E995.8','','Injury due to war operations by other forms of conventional warfare',13253,NULL,NULL,NULL),(2,'E995.9','','Injury due to war operations by unspecified form of conventional warfare',13254,NULL,NULL,NULL),(2,'E996.0','','Injury due to war operations by direct blast effect of nuclear weapon',13255,NULL,NULL,NULL),(2,'E996.1','','Injury due to war operations by indirect blast effect of nuclear weapon',13256,NULL,NULL,NULL),(2,'E996.2','','Injury due to war operations by thermal radiation effect of nuclear weapon',13257,NULL,NULL,NULL),(2,'E996.3','','Injury due to war operations by nuclear radiation effects',13258,NULL,NULL,NULL),(2,'E996.8','','Injury due to war operations by other effects of nuclear weapons',13259,NULL,NULL,NULL),(2,'E996.9','','Injury due to war operations by unspecified effect of nuclear weapon',13260,NULL,NULL,NULL),(2,'E997.0','','Injury due to war operations by lasers',13261,NULL,NULL,NULL),(2,'E997.1','','Injury due to war operations by biological warfare',13262,NULL,NULL,NULL),(2,'E997.2','','Injury due to war operations by gases fumes and chemicals',13263,NULL,NULL,NULL),(2,'E997.3','','Injury due to war operations by weapon of mass destruction (wmd), unspecified',13264,NULL,NULL,NULL),(2,'E997.8','','Injury due to other specified forms of unconventional warfare',13265,NULL,NULL,NULL),(2,'E997.9','','Injury due to unspecified form of unconventional warfare',13266,NULL,NULL,NULL),(2,'E998.0','','Injury due to war operations but occurring after cessation of hostilities by explosion of mines',13267,NULL,NULL,NULL),(2,'E998.1','','Injury due to war operations but occurring after cessation of hostilities by explosion of bombs',13268,NULL,NULL,NULL),(2,'E998.8','','Injury due to other war operations but occurring after cessation of hostilities',13269,NULL,NULL,NULL),(2,'E998.9','','Injury due to unspecified war operations but occurring after cessation of hostilities',13270,NULL,NULL,NULL),(2,'E999.0','','Late effect of injury due to war operations',13271,NULL,NULL,NULL),(2,'E999.1','','Late effect of injury due to terrorism',13272,NULL,NULL,NULL),(2,'V01.0','','Contact with or exposure to cholera',13273,NULL,NULL,NULL),(2,'V01.1','','Contact with or exposure to tuberculosis',13274,NULL,NULL,NULL),(2,'V01.2','','Contact with or exposure to poliomyelitis',13275,NULL,NULL,NULL),(2,'V01.3','','Contact with or exposure to smallpox',13276,NULL,NULL,NULL),(2,'V01.4','','Contact with or exposure to rubella',13277,NULL,NULL,NULL),(2,'V01.5','','Contact with or exposure to rabies',13278,NULL,NULL,NULL),(2,'V01.6','','Contact with or exposure to venereal diseases',13279,NULL,NULL,NULL),(2,'V01.71','','Contact/exposure to varicella',13280,NULL,NULL,NULL),(2,'V01.79','','Contact/exposure to other viral diseases',13281,NULL,NULL,NULL),(2,'V01.81','','Contact with or exposure to anthrax',13282,NULL,NULL,NULL),(2,'V01.82','','Exposure to sars-associated coronavirus',13283,NULL,NULL,NULL),(2,'V01.83','','Contact/exposure to escherichia coli [e.coli]',13284,NULL,NULL,NULL),(2,'V01.84','','Contact/exposure to meningococcus',13285,NULL,NULL,NULL),(2,'V01.89','','Contact with or exposure to other communicable diseases',13286,NULL,NULL,NULL),(2,'V01.9','','Contact with or exposure to unspecified communicable disease',13287,NULL,NULL,NULL),(2,'V02.0','','Carrier or suspected carrier of cholera',13288,NULL,NULL,NULL),(2,'V02.1','','Carrier or suspected carrier of typhoid',13289,NULL,NULL,NULL),(2,'V02.2','','Carrier or suspected carrier of amebiasis',13290,NULL,NULL,NULL),(2,'V02.3','','Carrier or suspected carrier of other gastrointestinal pathogens',13291,NULL,NULL,NULL),(2,'V02.4','','Carrier or suspected carrier of diphtheria',13292,NULL,NULL,NULL),(2,'V02.51','','Carrier or suspected carrier of group b streptococcus',13293,NULL,NULL,NULL),(2,'V02.52','','Carrier or suspected carrier of other streptococcus',13294,NULL,NULL,NULL),(2,'V02.53','','Methicillin susceptible staphylococcus aureus',13295,NULL,NULL,NULL),(2,'V02.54','','Methicillin resistant staphylococcus aureus',13296,NULL,NULL,NULL),(2,'V02.59','','Carrier or suspected carrier of other specified bacterial diseases',13297,NULL,NULL,NULL),(2,'V02.60','','Carrier or suspected carrier of viral hepatitis unspecified',13298,NULL,NULL,NULL),(2,'V02.61','','Carrier or suspected carrier of hepatitis b',13299,NULL,NULL,NULL),(2,'V02.62','','Carrier or suspected carrier of hepatitis c',13300,NULL,NULL,NULL),(2,'V02.69','','Carrier or suspected carrier of other viral hepatitis',13301,NULL,NULL,NULL),(2,'V02.7','','Carrier or suspected carrier of gonorrhea',13302,NULL,NULL,NULL),(2,'V02.8','','Carrier or suspected carrier of other venereal diseases',13303,NULL,NULL,NULL),(2,'V02.9','','Carrier or suspected carrier of other specified infectious organism',13304,NULL,NULL,NULL),(2,'V03.0','','Need for prophylactic vaccination and inoculation against cholera alone',13305,NULL,NULL,NULL),(2,'V03.1','','Need for prophylactic vaccination with typhoid-paratyphoid (tab) vaccine',13306,NULL,NULL,NULL),(2,'V03.2','','Need for prophylactic vaccination with tuberculosis (bcg) vaccine',13307,NULL,NULL,NULL),(2,'V03.3','','Need for prophylactic vaccination and inoculation against plague',13308,NULL,NULL,NULL),(2,'V03.4','','Need for prophylactic vaccination and inoculation against tularemia',13309,NULL,NULL,NULL),(2,'V03.5','','Need for prophylactic vaccination and inoculation against diphtheria alone',13310,NULL,NULL,NULL),(2,'V03.6','','Need for prophylactic vaccination and inoculation against pertussis alone',13311,NULL,NULL,NULL),(2,'V03.7','','Need for prophylactic vaccination with tetanus toxoid alone',13312,NULL,NULL,NULL),(2,'V03.81','','Need for prophylactic vaccination and inoculation against hemophilus influenza type b [hib]',13313,NULL,NULL,NULL),(2,'V03.82','','Need for prophylactic vaccination and inoculation against streptococcus pneumoniae [pneumococcus]',13314,NULL,NULL,NULL),(2,'V03.89','','Need for prophylactic vaccination and inoculation against other specified single bacterial disease',13315,NULL,NULL,NULL),(2,'V03.9','','Need for prophylactic vaccination and inoculation against unspecified single bacterial disease',13316,NULL,NULL,NULL),(2,'V04.0','','Need for prophylactic vaccination and inoculation against poliomyelitis',13317,NULL,NULL,NULL),(2,'V04.1','','Need for prophylactic vaccination and inoculation against smallpox',13318,NULL,NULL,NULL),(2,'V04.2','','Need for prophylactic vaccination and inoculation against measles alone',13319,NULL,NULL,NULL),(2,'V04.3','','Need for prophylactic vaccination and inoculation against rubella alone',13320,NULL,NULL,NULL),(2,'V04.4','','Need for prophylactic vaccination and inoculation against yellow fever',13321,NULL,NULL,NULL),(2,'V04.5','','Need for prophylactic vaccination and inoculation against rabies',13322,NULL,NULL,NULL),(2,'V04.6','','Need for prophylactic vaccination and inoculation against mumps alone',13323,NULL,NULL,NULL),(2,'V04.7','','Need for prophylactic vaccination and inoculation against common cold',13324,NULL,NULL,NULL),(2,'V04.81','','Need for prophylactic vaccination and inoculation against influenza',13325,NULL,NULL,NULL),(2,'V04.82','','Need for prophylactic vaccination and inoculation against respiratory syncytial virus (rsv)',13326,NULL,NULL,NULL),(2,'V04.89','','Need for prophylactic vaccination and inoculation against other viral diseases',13327,NULL,NULL,NULL),(2,'V05.0','','Need for prophylactic vaccination and inoculation against arthropod-borne viral encephalitis',13328,NULL,NULL,NULL),(2,'V05.1','','Need for prophylactic vaccination and inoculation against other arthropod-borne viral diseases',13329,NULL,NULL,NULL),(2,'V05.2','','Need for prophylactic vaccination and inoculation against leishmaniasis',13330,NULL,NULL,NULL),(2,'V05.3','','Need for prophylactic vaccination and inoculation against viral hepatitis',13331,NULL,NULL,NULL),(2,'V05.4','','Need for prophylactic vaccination and inoculation against varicella',13332,NULL,NULL,NULL),(2,'V05.8','','Need for prophylactic vaccination and inoculation against other specified disease',13333,NULL,NULL,NULL),(2,'V05.9','','Need for prophylactic vaccination and inoculation against unspecified single disease',13334,NULL,NULL,NULL),(2,'V06.0','','Need for prophylactic vaccination against cholera with typhoid-paratyphoid (cholera + tab) vaccine',13335,NULL,NULL,NULL),(2,'V06.1','','Need for prophylactic vaccination with diphtheria-tetanus-pertussis combined [dtp] [tdap] vaccine',13336,NULL,NULL,NULL),(2,'V06.2','','Need for prophylactic vaccination with diphtheria-tetanus-pertussis with typhoid-paratyphoid (dtp + tab) vaccine',13337,NULL,NULL,NULL),(2,'V06.3','','Need for prophylactic vaccination with diphtheria-tetanus-pertussis with poliomyelitis (dtp + polio) vaccine',13338,NULL,NULL,NULL),(2,'V06.4','','Need for prophylactic vaccination with measles-mumps-rubella (mmr) vaccine',13339,NULL,NULL,NULL),(2,'V06.5','','Need for prophylactic vaccination and inoculation against tetanus-diphtheria [td] [dt]',13340,NULL,NULL,NULL),(2,'V06.6','','Need for prophylactic vaccination and inoculation against streptococcus pneumoniae and influenza',13341,NULL,NULL,NULL),(2,'V06.8','','Need for prophylactic vaccination and inoculation against other combinations of diseases',13342,NULL,NULL,NULL),(2,'V06.9','','Need for prophylactic vaccination with unspecified combined vaccine',13343,NULL,NULL,NULL),(2,'V07.0','','Need for isolation',13344,NULL,NULL,NULL),(2,'V07.1','','Need for desensitization to allergens',13345,NULL,NULL,NULL),(2,'V07.2','','Need for prophylactic immunotherapy',13346,NULL,NULL,NULL),(2,'V07.31','','Need for prophylactic fluoride administration',13347,NULL,NULL,NULL),(2,'V07.39','','Need for other prophylactic chemotherapy',13348,NULL,NULL,NULL),(2,'V07.4','','Hormone replacement therapy (postmenopausal)',13349,NULL,NULL,NULL),(2,'V07.51','','Prophylactic use of selective estrogen receptor modulators (serms)',13350,NULL,NULL,NULL),(2,'V07.52','','Prophylactic use of aromatase inhibitors',13351,NULL,NULL,NULL),(2,'V07.59','','Prophylactic use of other agents affecting estrogen receptors and estrogen levels',13352,NULL,NULL,NULL),(2,'V07.8','','Need for other specified prophylactic measure',13353,NULL,NULL,NULL),(2,'V07.9','','Need for unspecified prophylactic measure',13354,NULL,NULL,NULL),(2,'V08','','Asymptomatic human immunodeficiency virus (hiv) infection status',13355,NULL,NULL,NULL),(2,'V09.0','','Infection with microorganisms resistant to penicillins',13356,NULL,NULL,NULL),(2,'V09.1','','Infection with microorganisms resistant to cephalosporins and other b-lactam antibiotics',13357,NULL,NULL,NULL),(2,'V09.2','','Infection with microorganisms resistant to macrolides',13358,NULL,NULL,NULL),(2,'V09.3','','Infection with microorganisms resistant to tetracyclines',13359,NULL,NULL,NULL),(2,'V09.4','','Infection with microorganisms resistant to aminoglycosides',13360,NULL,NULL,NULL),(2,'V09.50','','Without resistance to multiple quinolones and fluroquinolones',13361,NULL,NULL,NULL),(2,'V09.51','','With resistance to multiple quinolones and fluroquinolones',13362,NULL,NULL,NULL),(2,'V09.6','','Infection with microorganisms resistant to sulfonamides',13363,NULL,NULL,NULL),(2,'V09.70','','Infection with microorganisms resistant to other specified antimycobacterial agents without resistance to multiple antimycobacterial agents',13364,NULL,NULL,NULL),(2,'V09.71','','Infection with microorganisms resistant to other specified antimycobacterial agents with resistance to multiple antimycobacterial agents',13365,NULL,NULL,NULL),(2,'V09.80','','Infection with microorganisms resistant to other specified drugs without resistance to multiple drugs',13366,NULL,NULL,NULL),(2,'V09.81','','Infection with microorganisms resistant to other specified drugs with resistance to multiple drugs',13367,NULL,NULL,NULL),(2,'V09.90','','Infection with drug-resistant microorganisms unspecified without multiple drug resistance',13368,NULL,NULL,NULL),(2,'V09.91','','Infection with drug-resistant microorganisms unspecified with multiple drug resistance',13369,NULL,NULL,NULL),(2,'V10.00','','Personal history of malignant neoplasm of unspecified site in gastrointestinal tract',13370,NULL,NULL,NULL),(2,'V10.01','','Personal history of malignant neoplasm of tongue',13371,NULL,NULL,NULL),(2,'V10.02','','Personal history of malignant neoplasm of other and unspecified parts of oral cavity and pharynx',13372,NULL,NULL,NULL),(2,'V10.03','','Personal history of malignant neoplasm of esophagus',13373,NULL,NULL,NULL),(2,'V10.04','','Personal history of malignant neoplasm of stomach',13374,NULL,NULL,NULL),(2,'V10.05','','Personal history of malignant neoplasm of large intestine',13375,NULL,NULL,NULL),(2,'V10.06','','Personal history of malignant neoplasm of rectum rectosigmoid junction and anus',13376,NULL,NULL,NULL),(2,'V10.07','','Personal history of malignant neoplasm of liver',13377,NULL,NULL,NULL),(2,'V10.09','','Personal history of malignant neoplasm of other sites in gastrointestinal tract',13378,NULL,NULL,NULL),(2,'V10.11','','Personal history of malignant neoplasm of bronchus and lung',13379,NULL,NULL,NULL),(2,'V10.12','','Personal history of malignant neoplasm of trachea',13380,NULL,NULL,NULL),(2,'V10.20','','Personal history of malignant neoplasm of unspecified respiratory organ',13381,NULL,NULL,NULL),(2,'V10.21','','Personal history of malignant neoplasm of larynx',13382,NULL,NULL,NULL),(2,'V10.22','','Personal history of malignant neoplasm of nasal cavities middle ear and accessory sinuses',13383,NULL,NULL,NULL),(2,'V10.29','','Personal history of malignant neoplasm of other respiratory and intrathoracic organs',13384,NULL,NULL,NULL),(2,'V10.3','','Personal history of malignant neoplasm of breast',13385,NULL,NULL,NULL),(2,'V10.40','','Personal history of malignant neoplasm of unspecified female genital organ',13386,NULL,NULL,NULL),(2,'V10.41','','Personal history of malignant neoplasm of cervix uteri',13387,NULL,NULL,NULL),(2,'V10.42','','Personal history of malignant neoplasm of other parts of uterus',13388,NULL,NULL,NULL),(2,'V10.43','','Personal history of malignant neoplasm of ovary',13389,NULL,NULL,NULL),(2,'V10.44','','Personal history of malignant neoplasm of other female genital organs',13390,NULL,NULL,NULL),(2,'V10.45','','Personal history of malignant neoplasm of unspecified male genital organ',13391,NULL,NULL,NULL),(2,'V10.46','','Personal history of malignant neoplasm of prostate',13392,NULL,NULL,NULL),(2,'V10.47','','Personal history of malignant neoplasm of testis',13393,NULL,NULL,NULL),(2,'V10.48','','Personal history of malignant neoplasm of epididymis',13394,NULL,NULL,NULL),(2,'V10.49','','Personal history of malignant neoplasm of other male genital organs',13395,NULL,NULL,NULL),(2,'V10.50','','Personal history of malignant neoplasm of unspecified urinary organ',13396,NULL,NULL,NULL),(2,'V10.51','','Personal history of malignant neoplasm of bladder',13397,NULL,NULL,NULL),(2,'V10.52','','Personal history of malignant neoplasm of kidney',13398,NULL,NULL,NULL),(2,'V10.53','','Personal history of malignant neoplasm of renal pelvis',13399,NULL,NULL,NULL),(2,'V10.59','','Personal history of malignant neoplasm of other urinary organs',13400,NULL,NULL,NULL),(2,'V10.60','','Personal history of unspecified leukemia',13401,NULL,NULL,NULL),(2,'V10.61','','Personal history of lymphoid leukemia',13402,NULL,NULL,NULL),(2,'V10.62','','Personal history of myeloid leukemia',13403,NULL,NULL,NULL),(2,'V10.63','','Personal history of monocytic leukemia',13404,NULL,NULL,NULL),(2,'V10.69','','Personal history of other leukemia',13405,NULL,NULL,NULL),(2,'V10.71','','Personal history of lymphosarcoma and reticulosarcoma',13406,NULL,NULL,NULL),(2,'V10.72','','Personal history of hodgkin\'s disease',13407,NULL,NULL,NULL),(2,'V10.79','','Personal history of other lymphatic and hematopoietic neoplasms',13408,NULL,NULL,NULL),(2,'V10.81','','Personal history of malignant neoplasm of bone',13409,NULL,NULL,NULL),(2,'V10.82','','Personal history of malignant melanoma of skin',13410,NULL,NULL,NULL),(2,'V10.83','','Personal history of other malignant neoplasm of skin',13411,NULL,NULL,NULL),(2,'V10.84','','Personal history of malignant neoplasm of eye',13412,NULL,NULL,NULL),(2,'V10.85','','Personal history of malignant neoplasm of brain',13413,NULL,NULL,NULL),(2,'V10.86','','Personal history of malignant neoplasm of other parts of nervous system',13414,NULL,NULL,NULL),(2,'V10.87','','Personal history of malignant neoplasm of thyroid',13415,NULL,NULL,NULL),(2,'V10.88','','Personal history of malignant neoplasm of other endocrine glands and related structures',13416,NULL,NULL,NULL),(2,'V10.89','','Personal history of malignant neoplasm of other sites',13417,NULL,NULL,NULL),(2,'V10.90','','Personal history of unspecified type of malignant neoplasm',13418,NULL,NULL,NULL),(2,'V10.91','','Personal history of malignant neuroendocrine tumor',13419,NULL,NULL,NULL),(2,'V11.0','','Personal history of schizophrenia',13420,NULL,NULL,NULL),(2,'V11.1','','Personal history of affective disorders',13421,NULL,NULL,NULL),(2,'V11.2','','Personal history of neurosis',13422,NULL,NULL,NULL),(2,'V11.3','','Personal history of alcoholism',13423,NULL,NULL,NULL),(2,'V11.8','','Personal history of other mental disorders',13424,NULL,NULL,NULL),(2,'V11.9','','Personal history of unspecified mental disorder',13425,NULL,NULL,NULL),(2,'V12.00','','Personal history of unspecified infectious and parasitic disease',13426,NULL,NULL,NULL),(2,'V12.01','','Personal history of tuberculosis',13427,NULL,NULL,NULL),(2,'V12.02','','Personal history of poliomyelitis',13428,NULL,NULL,NULL),(2,'V12.03','','Personal history of malaria',13429,NULL,NULL,NULL),(2,'V12.04','','Personal history of methicillin resistant staphylococcus aureus',13430,NULL,NULL,NULL),(2,'V12.09','','Personal history of other specified infectious and parasitic disease',13431,NULL,NULL,NULL),(2,'V12.1','','Personal history of nutritional deficiency',13432,NULL,NULL,NULL),(2,'V12.2','','Personal history of endocrine metabolic and immunity disorders',13433,NULL,NULL,NULL),(2,'V12.3','','Personal history of diseases of blood and blood-forming organs',13434,NULL,NULL,NULL),(2,'V12.40','','Personal history of unspecified disorder of nervous system and sense organs',13435,NULL,NULL,NULL),(2,'V12.41','','Personal history of benign neoplasm of the brain',13436,NULL,NULL,NULL),(2,'V12.42','','Personal history of infections of the central nervous system',13437,NULL,NULL,NULL),(2,'V12.49','','Personal history of other disorders of nervous system and sense organs',13438,NULL,NULL,NULL),(2,'V12.50','','Personal history of unspecified circulatory disease',13439,NULL,NULL,NULL),(2,'V12.51','','Personal history of venous thrombosis and embolism',13440,NULL,NULL,NULL),(2,'V12.52','','Personal history of thrombophlebitis',13441,NULL,NULL,NULL),(2,'V12.53','','Personal history of sudden cardiac arrest',13442,NULL,NULL,NULL),(2,'V12.54','','Personal history of transient ischemic attack (tia), and cerebral infarction without residual deficits',13443,NULL,NULL,NULL),(2,'V12.59','','Personal history of other diseases of circulatory system not elsewhere classified',13444,NULL,NULL,NULL),(2,'V12.60','','Personal history of unspecified disease of respiratory system',13445,NULL,NULL,NULL),(2,'V12.61','','Personal history of pneumonia (recurrent)',13446,NULL,NULL,NULL),(2,'V12.69','','Personal history of other diseases of respiratory system',13447,NULL,NULL,NULL),(2,'V12.70','','Personal history of unspecified digestive disease',13448,NULL,NULL,NULL),(2,'V12.71','','Personal history of peptic ulcer disease',13449,NULL,NULL,NULL),(2,'V12.72','','Personal history of colonic polyps',13450,NULL,NULL,NULL),(2,'V12.79','','Personal history of other specified digestive system diseases',13451,NULL,NULL,NULL),(2,'V13.00','','Personal history of unspecified urinary disorder',13452,NULL,NULL,NULL),(2,'V13.01','','Personal history of urinary calculi',13453,NULL,NULL,NULL),(2,'V13.02','','Personal history of urinary (tract) infection',13454,NULL,NULL,NULL),(2,'V13.03','','Personal history of nephrotic syndrome',13455,NULL,NULL,NULL),(2,'V13.09','','Personal history of other specified urinary system disorders',13456,NULL,NULL,NULL),(2,'V13.1','','Personal history of trophoblastic disease',13457,NULL,NULL,NULL),(2,'V13.21','','Personal history of pre-term labor',13458,NULL,NULL,NULL),(2,'V13.22','','Personal history of cervical dysplasia',13459,NULL,NULL,NULL),(2,'V13.29','','Personal history of other genital system and obstetric disorders',13460,NULL,NULL,NULL),(2,'V13.3','','Personal history of diseases of skin and subcutaneous tissue',13461,NULL,NULL,NULL),(2,'V13.4','','Personal history of arthritis',13462,NULL,NULL,NULL),(2,'V13.51','','Personal history of pathologic fracture',13463,NULL,NULL,NULL),(2,'V13.52','','Personal history of stress fracture',13464,NULL,NULL,NULL),(2,'V13.59','','Personal history of other musculoskeletal disorders',13465,NULL,NULL,NULL),(2,'V13.61','','Personal history of hypospadias',13466,NULL,NULL,NULL),(2,'V13.69','','Personal history of other congenital malformations',13467,NULL,NULL,NULL),(2,'V13.7','','Personal history of perinatal problems',13468,NULL,NULL,NULL),(2,'V13.8','','Personal history of other specified diseases',13469,NULL,NULL,NULL),(2,'V13.9','','Personal history of unspecified disease',13470,NULL,NULL,NULL),(2,'V14.0','','Personal history of allergy to penicillin',13471,NULL,NULL,NULL),(2,'V14.1','','Personal history of allergy to other antibiotic agent',13472,NULL,NULL,NULL),(2,'V14.2','','Personal history of allergy to sulfonamides',13473,NULL,NULL,NULL),(2,'V14.3','','Personal history of allergy to other anti-infective agent',13474,NULL,NULL,NULL),(2,'V14.4','','Personal history of allergy to anesthetic agent',13475,NULL,NULL,NULL),(2,'V14.5','','Personal history of allergy to narcotic agent',13476,NULL,NULL,NULL),(2,'V14.6','','Personal history of allergy to analgesic agent',13477,NULL,NULL,NULL),(2,'V14.7','','Personal history of allergy to serum or vaccine',13478,NULL,NULL,NULL),(2,'V14.8','','Personal history of allergy to other specified medicinal agents',13479,NULL,NULL,NULL),(2,'V14.9','','Personal history of allergy to unspecified medicinal agent',13480,NULL,NULL,NULL),(2,'V15.01','','Personal history of allergy to peanuts',13481,NULL,NULL,NULL),(2,'V15.02','','Personal history of allergy to milk products',13482,NULL,NULL,NULL),(2,'V15.03','','Personal history of allergy to eggs',13483,NULL,NULL,NULL),(2,'V15.04','','Personal history of allergy to seafood',13484,NULL,NULL,NULL),(2,'V15.05','','Personal history of allergy to other foods',13485,NULL,NULL,NULL),(2,'V15.06','','Allergy to insects and arachnids',13486,NULL,NULL,NULL),(2,'V15.07','','Personal history of allergy to latex',13487,NULL,NULL,NULL),(2,'V15.08','','Personal history of allergy to radiographic dye',13488,NULL,NULL,NULL),(2,'V15.09','','Personal history of other allergy other than to medicinal agents',13489,NULL,NULL,NULL),(2,'V15.1','','Personal history of surgery to heart and great vessels presenting hazards to health',13490,NULL,NULL,NULL),(2,'V15.21','','Personal history of undergoing in utero procedure during pregnancy',13491,NULL,NULL,NULL),(2,'V15.22','','Personal history of undergoing in utero procedure while a fetus',13492,NULL,NULL,NULL),(2,'V15.29','','Personal history of surgery to other organs',13493,NULL,NULL,NULL),(2,'V15.3','','Personal history of irradiation presenting hazards to health',13494,NULL,NULL,NULL),(2,'V15.41','','Personal history of physical abuse',13495,NULL,NULL,NULL),(2,'V15.42','','Personal history of emotional abuse',13496,NULL,NULL,NULL),(2,'V15.49','','Personal history of d11506other psychological trauma',13497,NULL,NULL,NULL),(2,'V15.51','','Personal history of traumatic fracture',13498,NULL,NULL,NULL),(2,'V15.52','','Personal history of traumatic brain injury',13499,NULL,NULL,NULL),(2,'V15.59','','Personal history of other injury',13500,NULL,NULL,NULL),(2,'V15.6','','Personal history of poisoning presenting hazards to health',13501,NULL,NULL,NULL),(2,'V15.7','','Personal history of contraception presenting hazards to health',13502,NULL,NULL,NULL),(2,'V15.80','','Personal history of failed moderate sedation',13503,NULL,NULL,NULL),(2,'V15.81','','Personal history of noncompliance with medical treatment presenting hazards to health',13504,NULL,NULL,NULL),(2,'V15.82','','Personal history of tobacco use',13505,NULL,NULL,NULL),(2,'V15.83','','Personal history of underimmunization status',13506,NULL,NULL,NULL),(2,'V15.84','','Personal history of contact with and (suspected) exposure to asbestos',13507,NULL,NULL,NULL),(2,'V15.85','','Personal history of contact with and (suspected) exposure to potentially hazardous body fluids',13508,NULL,NULL,NULL),(2,'V15.86','','Personal history of contact with and (suspected) exposure to lead',13509,NULL,NULL,NULL),(2,'V15.87','','Personal history of extracorporeal membrane oxygenation (ecmo)',13510,NULL,NULL,NULL),(2,'V15.88','','Personal history of fall',13511,NULL,NULL,NULL),(2,'V15.89','','Other specified personal history presenting hazards to health',13512,NULL,NULL,NULL),(2,'V15.9','','Unspecified personal history presenting hazards to health',13513,NULL,NULL,NULL),(2,'V16.0','','Family history of malignant neoplasm of gastrointestinal tract',13514,NULL,NULL,NULL),(2,'V16.1','','Family history of malignant neoplasm of trachea bronchus and lung',13515,NULL,NULL,NULL),(2,'V16.2','','Family history of malignant neoplasm of other respiratory and intrathoracic organs',13516,NULL,NULL,NULL),(2,'V16.3','','Family history of malignant neoplasm of breast',13517,NULL,NULL,NULL),(2,'V16.40','','Family history of malignant neoplasm of genital organ unspecified',13518,NULL,NULL,NULL),(2,'V16.41','','Family history of malignant neoplasm of ovary',13519,NULL,NULL,NULL),(2,'V16.42','','Family history of malignant neoplasm of prostate',13520,NULL,NULL,NULL),(2,'V16.43','','Family history of malignant neoplasm of testis',13521,NULL,NULL,NULL),(2,'V16.49','','Family history of malignant neoplasm of other',13522,NULL,NULL,NULL),(2,'V16.51','','Family history of malignant neoplasm of kidney',13523,NULL,NULL,NULL),(2,'V16.52','','Family history of malignant neoplasm of bladder',13524,NULL,NULL,NULL),(2,'V16.59','','Family history of malignant neoplasm of other',13525,NULL,NULL,NULL),(2,'V16.6','','Family history of leukemia',13526,NULL,NULL,NULL),(2,'V16.7','','Family history of other lymphatic and hematopoietic neoplasms',13527,NULL,NULL,NULL),(2,'V16.8','','Family history of other specified malignant neoplasm',13528,NULL,NULL,NULL),(2,'V16.9','','Family history of unspecified malignant neoplasm',13529,NULL,NULL,NULL),(2,'V17.0','','Family history of psychiatric condition',13530,NULL,NULL,NULL),(2,'V17.1','','Family history of stroke (cerebrovascular)',13531,NULL,NULL,NULL),(2,'V17.2','','Family history of other neurological diseases',13532,NULL,NULL,NULL),(2,'V17.3','','Family history of ischemic heart disease',13533,NULL,NULL,NULL),(2,'V17.41','','Family history of sudden cardiac death (scd)',13534,NULL,NULL,NULL),(2,'V17.49','','Family history of other cardiovascular diseases',13535,NULL,NULL,NULL),(2,'V17.5','','Family history of asthma',13536,NULL,NULL,NULL),(2,'V17.6','','Family history of other chronic respiratory conditions',13537,NULL,NULL,NULL),(2,'V17.7','','Family history of arthritis',13538,NULL,NULL,NULL),(2,'V17.81','','Family history of osteoporosis',13539,NULL,NULL,NULL),(2,'V17.89','','Family history of other musculoskeletal diseases',13540,NULL,NULL,NULL),(2,'V18.0','','Family history of diabetes mellitus',13541,NULL,NULL,NULL),(2,'V18.11','','Family history of multiple endocrine neoplasia [men] syndrome',13542,NULL,NULL,NULL),(2,'V18.19','','Family history of other endocrine and metabolic diseases',13543,NULL,NULL,NULL),(2,'V18.2','','Family history of anemia',13544,NULL,NULL,NULL),(2,'V18.3','','Family history of other blood disorders',13545,NULL,NULL,NULL),(2,'V18.4','','Family history of mental retardation',13546,NULL,NULL,NULL),(2,'V18.51','','Family history, colonic polyps',13547,NULL,NULL,NULL),(2,'V18.59','','Family history, other digestive disorders',13548,NULL,NULL,NULL),(2,'V18.61','','Family history of polycystic kidney',13549,NULL,NULL,NULL),(2,'V18.69','','Family history of other kidney diseases',13550,NULL,NULL,NULL),(2,'V18.7','','Family history of other genitourinary diseases',13551,NULL,NULL,NULL),(2,'V18.8','','Family history of infectious and parasitic diseases',13552,NULL,NULL,NULL),(2,'V18.9','','Family history of genetic disease carrier',13553,NULL,NULL,NULL),(2,'V19.0','','Family history of blindness or visual loss',13554,NULL,NULL,NULL),(2,'V19.1','','Family history of other eye disorders',13555,NULL,NULL,NULL),(2,'V19.2','','Family history of deafness or hearing loss',13556,NULL,NULL,NULL),(2,'V19.3','','Family history of other ear disorders',13557,NULL,NULL,NULL),(2,'V19.4','','Family history of skin conditions',13558,NULL,NULL,NULL),(2,'V19.5','','Family history of congenital anomalies',13559,NULL,NULL,NULL),(2,'V19.6','','Family history of allergic disorders',13560,NULL,NULL,NULL),(2,'V19.7','','Family history of consanguinity',13561,NULL,NULL,NULL),(2,'V19.8','','Family history of other condition',13562,NULL,NULL,NULL),(2,'V20.0','','Health supervision of foundling',13563,NULL,NULL,NULL),(2,'V20.1','','Other healthy infant or child receiving care',13564,NULL,NULL,NULL),(2,'V20.2','','Routine infant or child health check',13565,NULL,NULL,NULL),(2,'V20.31','','Health supervision for newborn under 8 days',13566,NULL,NULL,NULL),(2,'V20.32','','Health supervision for newborn 8 to 28 days old',13567,NULL,NULL,NULL),(2,'V21.0','','Period of rapid growth in childhood',13568,NULL,NULL,NULL),(2,'V21.1','','Puberty',13569,NULL,NULL,NULL),(2,'V21.2','','Other development of adolescence',13570,NULL,NULL,NULL),(2,'V21.30','','Unspecified low birth weight status',13571,NULL,NULL,NULL),(2,'V21.31','','Low birth weight status less than 500 grams',13572,NULL,NULL,NULL),(2,'V21.32','','Low birth weight status 500-999 grams',13573,NULL,NULL,NULL),(2,'V21.33','','Low birth weight status 1000-1499 grams',13574,NULL,NULL,NULL),(2,'V21.34','','Low birth weight status 1500-1999 grams',13575,NULL,NULL,NULL),(2,'V21.35','','Low birth weight status 2000-2500 grams',13576,NULL,NULL,NULL),(2,'V21.8','','Other specified constitutional states in development',13577,NULL,NULL,NULL),(2,'V21.9','','Unspecified constitutional state in development',13578,NULL,NULL,NULL),(2,'V22.0','','Supervision of normal first pregnancy',13579,NULL,NULL,NULL),(2,'V22.1','','Supervision of other normal pregnancy',13580,NULL,NULL,NULL),(2,'V22.2','','Pregnant state incidental',13581,NULL,NULL,NULL),(2,'V23.0','','Supervision of high-risk pregnancy with history of infertility',13582,NULL,NULL,NULL),(2,'V23.1','','Supervision of high-risk pregnancy with history of trophoblastic disease',13583,NULL,NULL,NULL),(2,'V23.2','','Supervision of high-risk pregnancy with history of abortion',13584,NULL,NULL,NULL),(2,'V23.3','','Supervision of high-risk pregnancy with grand multiparity',13585,NULL,NULL,NULL),(2,'V23.41','','Supervision of high-risk pregnancy with history of pre-term labor',13586,NULL,NULL,NULL),(2,'V23.49','','Supervision of high-risk pregnancy with other poor obstetric history',13587,NULL,NULL,NULL),(2,'V23.5','','Supervision of high-risk pregnancy with other poor reproductive history',13588,NULL,NULL,NULL),(2,'V23.7','','Supervision of high-risk pregnancy with insufficient prenatal care',13589,NULL,NULL,NULL),(2,'V23.81','','Supervision of high-risk pregnancy with elderly primigravida',13590,NULL,NULL,NULL),(2,'V23.82','','Supervision of high-risk pregnancy with elderly multigravida',13591,NULL,NULL,NULL),(2,'V23.83','','Supervision of high-risk pregnancy with young primigravida',13592,NULL,NULL,NULL),(2,'V23.84','','Supervision of high-risk pregnancy with young multigravida',13593,NULL,NULL,NULL),(2,'V23.85','','Pregnancy resulting from assisted reproductive technology',13594,NULL,NULL,NULL),(2,'V23.86','','Pregnancy with history of in utero procedure during previous pregnancy',13595,NULL,NULL,NULL),(2,'V23.89','','Supervision of other high-risk pregnancy',13596,NULL,NULL,NULL),(2,'V23.9','','Supervision of unspecified high-risk pregnancy',13597,NULL,NULL,NULL),(2,'V24.0','','Postpartum care and examination immediately after delivery',13598,NULL,NULL,NULL),(2,'V24.1','','Postpartum care and examination of lactating mother',13599,NULL,NULL,NULL),(2,'V24.2','','Routine postpartum follow-up',13600,NULL,NULL,NULL),(2,'V25.01','','General counseling on prescription of oral contraceptives',13601,NULL,NULL,NULL),(2,'V25.02','','General counseling on initiation of other contraceptive measures',13602,NULL,NULL,NULL),(2,'V25.03','','Encounter for emergency contraceptive counseling and prescription',13603,NULL,NULL,NULL),(2,'V25.04','','Counseling and instruction in natural family planning to avoid pregnancy',13604,NULL,NULL,NULL),(2,'V25.09','','Other general counseling and advice on contraceptive management',13605,NULL,NULL,NULL),(2,'V25.1','','Insertion of intrauterine contraceptive device',13606,NULL,NULL,NULL),(2,'V25.2','','Sterilization',13607,NULL,NULL,NULL),(2,'V25.3','','Menstrual extraction',13608,NULL,NULL,NULL),(2,'V25.40','','Contraceptive surveillance unspecified',13609,NULL,NULL,NULL),(2,'V25.41','','Surveillance of contraceptive pill',13610,NULL,NULL,NULL),(2,'V25.42','','Surveillance of intrauterine contraceptive device',13611,NULL,NULL,NULL),(2,'V25.43','','Surveillance of implantable subdermal contraceptive',13612,NULL,NULL,NULL),(2,'V25.49','','Surveillance of other contraceptive method',13613,NULL,NULL,NULL),(2,'V25.5','','Insertion of implantable subdermal contraceptive',13614,NULL,NULL,NULL),(2,'V25.8','','Other specified contraceptive management',13615,NULL,NULL,NULL),(2,'V25.9','','Unspecified contraceptive management',13616,NULL,NULL,NULL),(2,'V26.0','','Tuboplasty or vasoplasty after previous sterilization',13617,NULL,NULL,NULL),(2,'V26.1','','Artificial insemination',13618,NULL,NULL,NULL),(2,'V26.21','','Fertility testing',13619,NULL,NULL,NULL),(2,'V26.22','','Aftercare following sterilization reversal',13620,NULL,NULL,NULL),(2,'V26.29','','Other investigation and testing',13621,NULL,NULL,NULL),(2,'V26.31','','Testing for genetic disease carrier status of female',13622,NULL,NULL,NULL),(2,'V26.32','','Other genetic testing of female',13623,NULL,NULL,NULL),(2,'V26.33','','Genetic counseling',13624,NULL,NULL,NULL),(2,'V26.34','','Testing of male for genetic disease carrier status',13625,NULL,NULL,NULL),(2,'V26.35','','Encounter for testing of male partner of habitual aborter',13626,NULL,NULL,NULL),(2,'V26.39','','Other genetic testing of male',13627,NULL,NULL,NULL),(2,'V26.41','','Procreative counseling and advice using natural family planning',13628,NULL,NULL,NULL),(2,'V26.42','','Encounter for fertility preservation counseling',13629,NULL,NULL,NULL),(2,'V26.49','','Other procreative management counseling and advice',13630,NULL,NULL,NULL),(2,'V26.51','','Tubal ligation status',13631,NULL,NULL,NULL),(2,'V26.52','','Vasectomy status',13632,NULL,NULL,NULL),(2,'V26.81','','Encounter for assisted reproductive fertility procedure cycle',13633,NULL,NULL,NULL),(2,'V26.82','','Encounter for fertility preservation procedure',13634,NULL,NULL,NULL),(2,'V26.89','','Other specified procreative management',13635,NULL,NULL,NULL),(2,'V26.9','','Unspecified procreative management',13636,NULL,NULL,NULL),(2,'V27.0','','Mother with single liveborn',13637,NULL,NULL,NULL),(2,'V27.1','','Mother with single stillborn',13638,NULL,NULL,NULL),(2,'V27.2','','Mother with twins both liveborn',13639,NULL,NULL,NULL),(2,'V27.3','','Mother with twins one liveborn and one stillborn',13640,NULL,NULL,NULL),(2,'V27.4','','Mother with twins both stillborn',13641,NULL,NULL,NULL),(2,'V27.5','','Mother with other multiple birth all liveborn',13642,NULL,NULL,NULL),(2,'V27.6','','Mother with other multiple birth some liveborn',13643,NULL,NULL,NULL),(2,'V27.7','','Mother with other multiple birth all stillborn',13644,NULL,NULL,NULL),(2,'V27.9','','Mother with unspecified outcome of delivery',13645,NULL,NULL,NULL),(2,'V28.0','','Screening for chromosomal anomalies by amniocentesis',13646,NULL,NULL,NULL),(2,'V28.1','','Screening for raised alpha-fetoprotein levels in amniotic fluid',13647,NULL,NULL,NULL),(2,'V28.2','','Other antenatal screening based on amniocentesis',13648,NULL,NULL,NULL),(2,'V28.3','','Encounter for routine screening for malformation using ultrasonics',13649,NULL,NULL,NULL),(2,'V28.4','','Antenatal screening for fetal growth retardation using ultrasonics',13650,NULL,NULL,NULL),(2,'V28.5','','Antenatal screening for isoimmunization',13651,NULL,NULL,NULL),(2,'V28.6','','Antenatal screening for streptococcus b',13652,NULL,NULL,NULL),(2,'V28.81','','Encounter for fetal anatomic survey',13653,NULL,NULL,NULL),(2,'V28.82','','Encounter for screening for risk of pre-term labor',13654,NULL,NULL,NULL),(2,'V28.89','','Other specified antenatal screening',13655,NULL,NULL,NULL),(2,'V28.9','','Unspecified antenatal screening',13656,NULL,NULL,NULL),(2,'V29.0','','Observation for suspected infectious condition',13657,NULL,NULL,NULL),(2,'V29.1','','Observation for suspected neurological conditions',13658,NULL,NULL,NULL),(2,'V29.2','','Observation and evaluation of newborn for suspected respiratory condition',13659,NULL,NULL,NULL),(2,'V29.3','','Observation for suspected genetic or metabolic condition',13660,NULL,NULL,NULL),(2,'V29.8','','Observation for other specified suspected conditions',13661,NULL,NULL,NULL),(2,'V29.9','','Observation for unspecified suspected conditions',13662,NULL,NULL,NULL),(2,'V30.00','','Single liveborn born in hospital delivered without cesarean section',13663,NULL,NULL,NULL),(2,'V30.01','','Single liveborn born in hospital delivered by cesarean section',13664,NULL,NULL,NULL),(2,'V30.1','','Single liveborn born before admission to hospital',13665,NULL,NULL,NULL),(2,'V30.2','','Single liveborn born outside hospital and not hospitalized',13666,NULL,NULL,NULL),(2,'V31.00','','Twin birth mate liveborn born in hospital delivered without cesarean section',13667,NULL,NULL,NULL),(2,'V31.01','','Twin birth mate liveborn born in hospital delivered by cesarean section',13668,NULL,NULL,NULL),(2,'V31.1','','Twin birth mate liveborn born before admission to hospital',13669,NULL,NULL,NULL),(2,'V31.2','','Twin birth mate liveborn born outside hospital and not hospitalized',13670,NULL,NULL,NULL),(2,'V32.00','','Twin birth mate stillborn born in hospital delivered without cesarean section',13671,NULL,NULL,NULL),(2,'V32.01','','Twin birth mate stillborn born in hospital delivered by cesarean section',13672,NULL,NULL,NULL),(2,'V32.1','','Twin birth mate stillborn born before admission to hospital',13673,NULL,NULL,NULL),(2,'V32.2','','Twin birth mate stillborn born outside hospital and not hospitalized',13674,NULL,NULL,NULL),(2,'V33.00','','Twin birth unspecified whether mate liveborn or stillborn born in hospital delivered without cesarean section',13675,NULL,NULL,NULL),(2,'V33.01','','Twin birth unspecified whether mate liveborn or stillborn born in hospital delivered by cesarean section',13676,NULL,NULL,NULL),(2,'V33.1','','Twin birth unspecified whether mate liveborn or stillborn born before admission to hospital',13677,NULL,NULL,NULL),(2,'V33.2','','Twin birth unspecified whether mate liveborn or stillborn born outside hospital and not hospitalized',13678,NULL,NULL,NULL),(2,'V34.00','','Other multiple birth (three or more) mates all liveborn born in hospital delivered without cesarean section',13679,NULL,NULL,NULL),(2,'V34.01','','Other multiple birth (three or more) mates all liveborn born in hospital delivered by cesarean section',13680,NULL,NULL,NULL),(2,'V34.1','','Other multiple birth (three or more) mates all liveborn born before admission to hospital',13681,NULL,NULL,NULL),(2,'V34.2','','Other multiple birth (three or more) mates all liveborn born outside hospital and not hospitalized',13682,NULL,NULL,NULL),(2,'V35.00','','Other multiple birth (three or more) mates all still born in hospital delivered without cesarean section',13683,NULL,NULL,NULL),(2,'V35.01','','Other multiple birth (three or more) mates all still born in hospital delivered by cesarean section',13684,NULL,NULL,NULL),(2,'V35.1','','Other multiple birth (three or more) mates all stillborn born before admission to hospital',13685,NULL,NULL,NULL),(2,'V35.2','','Other multiple birth (three or more) mates all stillborn born outside of hospital and not hospitalized',13686,NULL,NULL,NULL),(2,'V36.00','','Other multiple birth (three or more) mates liveborn and stillborn born in hospital delivered without cesarean section',13687,NULL,NULL,NULL),(2,'V36.01','','Other multiple birth (three or more) mates liveborn and stillborn born in hospital delivered without cesarean section',13688,NULL,NULL,NULL),(2,'V36.1','','Other multiple birth (three or more) mates liveborn and stillborn born before admission to hospital',13689,NULL,NULL,NULL),(2,'V36.2','','Other multiple birth (three or more) mates liveborn and stillborn born outside hospital and not hospitalized',13690,NULL,NULL,NULL),(2,'V37.00','','Other multiple birth (three or more) unspecified whether mates liveborn or stillborn born in hospital delivered without cesarean section',13691,NULL,NULL,NULL),(2,'V37.01','','Other multiple birth (three or more) unspecified whether mates liveborn or stillborn born in hospital delivered by cesarean section',13692,NULL,NULL,NULL),(2,'V37.1','','Other multiple birth (three or more) unspecified whether mates liveborn or stillborn born before admission to hospital',13693,NULL,NULL,NULL),(2,'V37.2','','Other multiple birth (three or more) unspecified whether mates liveborn or stillborn born outside of hospital',13694,NULL,NULL,NULL),(2,'V39.00','','Liveborn unspecified whether single twin or multiple born in hospital delivered without cesarean section',13695,NULL,NULL,NULL),(2,'V39.01','','Liveborn unspecified whether single twin or multiple born in hospital delivered by cesarean section',13696,NULL,NULL,NULL),(2,'V39.1','','Liveborn unspecified whether single twin or multiple born before admission to hospital',13697,NULL,NULL,NULL),(2,'V39.2','','Liveborn unspecified whether single twin or multiple born outside hospital and not hospitalized',13698,NULL,NULL,NULL),(2,'V40.0','','Mental and behavioral problems with learning',13699,NULL,NULL,NULL),(2,'V40.1','','Mental and behavioral problems with communication (including speech)',13700,NULL,NULL,NULL),(2,'V40.2','','Other mental problems',13701,NULL,NULL,NULL),(2,'V40.3','','Other behavioral problems',13702,NULL,NULL,NULL),(2,'V40.9','','Unspecified mental or behavioral problem',13703,NULL,NULL,NULL),(2,'V41.0','','Problems with sight',13704,NULL,NULL,NULL),(2,'V41.1','','Other eye problems',13705,NULL,NULL,NULL),(2,'V41.2','','Problems with hearing',13706,NULL,NULL,NULL),(2,'V41.3','','Other ear problems',13707,NULL,NULL,NULL),(2,'V41.4','','Problems with voice production',13708,NULL,NULL,NULL),(2,'V41.5','','Problems with smell and taste',13709,NULL,NULL,NULL),(2,'V41.6','','Problems with swallowing and mastication',13710,NULL,NULL,NULL),(2,'V41.7','','Problems with sexual function',13711,NULL,NULL,NULL),(2,'V41.8','','Other problems with special functions',13712,NULL,NULL,NULL),(2,'V41.9','','Unspecified problem with special functions',13713,NULL,NULL,NULL),(2,'V42.0','','Kidney replaced by transplant',13714,NULL,NULL,NULL),(2,'V42.1','','Heart replaced by transplant',13715,NULL,NULL,NULL),(2,'V42.2','','Heart valve replaced by transplant',13716,NULL,NULL,NULL),(2,'V42.3','','Skin replaced by transplant',13717,NULL,NULL,NULL),(2,'V42.4','','Bone replaced by transplant',13718,NULL,NULL,NULL),(2,'V42.5','','Cornea replaced by transplant',13719,NULL,NULL,NULL),(2,'V42.6','','Lung replaced by transplant',13720,NULL,NULL,NULL),(2,'V42.7','','Liver replaced by transplant',13721,NULL,NULL,NULL),(2,'V42.81','','Bone marrow replaced by transplant',13722,NULL,NULL,NULL),(2,'V42.82','','Peripheral stem cells replaced by transplant',13723,NULL,NULL,NULL),(2,'V42.83','','Pancreas replaced by transplant',13724,NULL,NULL,NULL),(2,'V42.84','','Organ or tissue replaced by transplant intestines',13725,NULL,NULL,NULL),(2,'V42.89','','Other specified organ or tissue replaced by transplant',13726,NULL,NULL,NULL),(2,'V42.9','','Unspecified organ or tissue replaced by transplant',13727,NULL,NULL,NULL),(2,'V43.0','','Eye globe replaced by other means',13728,NULL,NULL,NULL),(2,'V43.1','','Lens replaced by other means',13729,NULL,NULL,NULL),(2,'V43.21','','Heart replaced by heart assist device',13730,NULL,NULL,NULL),(2,'V43.22','','Heart replaced by fully implantable artificial heart',13731,NULL,NULL,NULL),(2,'V43.3','','Heart valve replaced by other means',13732,NULL,NULL,NULL),(2,'V43.4','','Blood vessel replaced by other means',13733,NULL,NULL,NULL),(2,'V43.5','','Bladder replaced by other means',13734,NULL,NULL,NULL),(2,'V43.60','','Unspecified joint replacement',13735,NULL,NULL,NULL),(2,'V43.61','','Shoulder joint replacement',13736,NULL,NULL,NULL),(2,'V43.62','','Elbow joint replacement',13737,NULL,NULL,NULL),(2,'V43.63','','Wrist joint replacement',13738,NULL,NULL,NULL),(2,'V43.64','','Hip joint replacement',13739,NULL,NULL,NULL),(2,'V43.65','','Knee joint replacement',13740,NULL,NULL,NULL),(2,'V43.66','','Ankle joint replacement',13741,NULL,NULL,NULL),(2,'V43.69','','Other joint replacement',13742,NULL,NULL,NULL),(2,'V43.7','','Limb replaced by other means',13743,NULL,NULL,NULL),(2,'V43.81','','Larynx replacement status',13744,NULL,NULL,NULL),(2,'V43.82','','Breast replacement status',13745,NULL,NULL,NULL),(2,'V43.83','','Artificial skin replacement status',13746,NULL,NULL,NULL),(2,'V43.89','','Other organ or tissue replacement status not elsewhere classified',13747,NULL,NULL,NULL),(2,'V44.0','','Tracheostomy status',13748,NULL,NULL,NULL),(2,'V44.1','','Gastrostomy status',13749,NULL,NULL,NULL),(2,'V44.2','','Ileostomy status',13750,NULL,NULL,NULL),(2,'V44.3','','Colostomy status',13751,NULL,NULL,NULL),(2,'V44.4','','Status of other artificial opening of gastrointestinal tract',13752,NULL,NULL,NULL),(2,'V44.50','','Cystostomy unspecified',13753,NULL,NULL,NULL),(2,'V44.51','','Cutaneous-vesicostomy',13754,NULL,NULL,NULL),(2,'V44.52','','Appendico-vesicostomy',13755,NULL,NULL,NULL),(2,'V44.59','','Other cystostomy',13756,NULL,NULL,NULL),(2,'V44.6','','Status of other artificial opening of urinary tract',13757,NULL,NULL,NULL),(2,'V44.7','','Artificial vagina status',13758,NULL,NULL,NULL),(2,'V44.8','','Other artificial opening status',13759,NULL,NULL,NULL),(2,'V44.9','','Unspecified artificial opening status',13760,NULL,NULL,NULL),(2,'V45.00','','Unspecified cardiac device in situ',13761,NULL,NULL,NULL),(2,'V45.01','','Cardiac pacemaker in situ',13762,NULL,NULL,NULL),(2,'V45.02','','Automatic implantable cardiac defibrillator in situ',13763,NULL,NULL,NULL),(2,'V45.09','','Other specified cardiac device in situ',13764,NULL,NULL,NULL),(2,'V45.11','','Renal dialysis status',13765,NULL,NULL,NULL),(2,'V45.12','','Noncompliance with renal dialysis',13766,NULL,NULL,NULL),(2,'V45.2','','Postsurgical presence of cerebrospinal fluid drainage device',13767,NULL,NULL,NULL),(2,'V45.3','','Postsurgical intestinal bypass or anastomosis status',13768,NULL,NULL,NULL),(2,'V45.4','','Postsurgical arthrodesis status',13769,NULL,NULL,NULL),(2,'V45.51','','Presence of intrauterine contraceptive device',13770,NULL,NULL,NULL),(2,'V45.52','','Presence of subdermal contraceptive implant',13771,NULL,NULL,NULL),(2,'V45.59','','Presence of other contraceptive device',13772,NULL,NULL,NULL),(2,'V45.61','','Cataract extraction status',13773,NULL,NULL,NULL),(2,'V45.69','','Other states following surgery of eye and adnexa',13774,NULL,NULL,NULL),(2,'V45.71','','Acquired absence of breast and nipple',13775,NULL,NULL,NULL),(2,'V45.72','','Acquired absence of intestine (large) (small)',13776,NULL,NULL,NULL),(2,'V45.73','','Acquired absence of kidney',13777,NULL,NULL,NULL),(2,'V45.74','','Acquired absence of organ other parts of urinary tract',13778,NULL,NULL,NULL),(2,'V45.75','','Acquired absence of organ stomach',13779,NULL,NULL,NULL),(2,'V45.76','','Acquired absence of organ lung',13780,NULL,NULL,NULL),(2,'V45.77','','Acquired absence of organ genital organs',13781,NULL,NULL,NULL),(2,'V45.78','','Acquired absence of organ eye',13782,NULL,NULL,NULL),(2,'V45.79','','Other acquired absence of organ',13783,NULL,NULL,NULL),(2,'V45.81','','Postsurgical aortocoronary bypass status',13784,NULL,NULL,NULL),(2,'V45.82','','Percutaneous transluminal coronary angioplasty status',13785,NULL,NULL,NULL),(2,'V45.83','','Breast implant removal status',13786,NULL,NULL,NULL),(2,'V45.84','','Dental restoration status',13787,NULL,NULL,NULL),(2,'V45.85','','Insulin pump status',13788,NULL,NULL,NULL),(2,'V45.86','','Bariatric surgery status',13789,NULL,NULL,NULL),(2,'V45.87','','Transplanted organ removal status',13790,NULL,NULL,NULL),(2,'V45.88','','Status post administration of tpa (rtpa) in a different facility within the last 24 hours prior to admission to current facility',13791,NULL,NULL,NULL),(2,'V45.89','','Other postsurgical status',13792,NULL,NULL,NULL),(2,'V46.0','','Dependence on aspirator',13793,NULL,NULL,NULL),(2,'V46.11','','Dependence on respirator, status',13794,NULL,NULL,NULL),(2,'V46.12','','Encounter for respirator dependence during power failure',13795,NULL,NULL,NULL),(2,'V46.13','','Encounter for weaning from respirator [weaning]',13796,NULL,NULL,NULL),(2,'V46.14','','Mechanical complication of respirator [ventilator]',13797,NULL,NULL,NULL),(2,'V46.2','','Dependence on supplemental oxygen',13798,NULL,NULL,NULL),(2,'V46.3','','Wheelchair dependence',13799,NULL,NULL,NULL),(2,'V46.8','','Dependence on other enabling machines',13800,NULL,NULL,NULL),(2,'V46.9','','Unspecified machine dependence',13801,NULL,NULL,NULL),(2,'V47.0','','Deficiencies of internal organs',13802,NULL,NULL,NULL),(2,'V47.1','','Mechanical and motor problems with internal organs',13803,NULL,NULL,NULL),(2,'V47.2','','Other cardiorespiratory problems',13804,NULL,NULL,NULL),(2,'V47.3','','Other digestive problems',13805,NULL,NULL,NULL),(2,'V47.4','','Other urinary problems',13806,NULL,NULL,NULL),(2,'V47.5','','Other genital problems',13807,NULL,NULL,NULL),(2,'V47.9','','Unspecified problems with internal organs',13808,NULL,NULL,NULL),(2,'V48.0','','Deficiencies of head',13809,NULL,NULL,NULL),(2,'V48.1','','Deficiencies of neck and trunk',13810,NULL,NULL,NULL),(2,'V48.2','','Mechanical and motor problems with head',13811,NULL,NULL,NULL),(2,'V48.3','','Mechanical and motor problems with neck and trunk',13812,NULL,NULL,NULL),(2,'V48.4','','Sensory problem with head',13813,NULL,NULL,NULL),(2,'V48.5','','Sensory problem with neck and trunk',13814,NULL,NULL,NULL),(2,'V48.6','','Disfigurements of head',13815,NULL,NULL,NULL),(2,'V48.7','','Disfigurements of neck and trunk',13816,NULL,NULL,NULL),(2,'V48.8','','Other problems with head neck and trunk',13817,NULL,NULL,NULL),(2,'V48.9','','Unspecified problem with head neck or trunk',13818,NULL,NULL,NULL),(2,'V49.0','','Deficiencies of limbs',13819,NULL,NULL,NULL),(2,'V49.1','','Mechanical problems with limbs',13820,NULL,NULL,NULL),(2,'V49.2','','Motor problems with limbs',13821,NULL,NULL,NULL),(2,'V49.3','','Sensory problems with limbs',13822,NULL,NULL,NULL),(2,'V49.4','','Disfigurements of limbs',13823,NULL,NULL,NULL),(2,'V49.5','','Other problems of limbs',13824,NULL,NULL,NULL),(2,'V49.60','','Unspecified level upper limb amputation status',13825,NULL,NULL,NULL),(2,'V49.61','','Thumb amputation status',13826,NULL,NULL,NULL),(2,'V49.62','','Other finger(s) amputation status',13827,NULL,NULL,NULL),(2,'V49.63','','Hand amputation status',13828,NULL,NULL,NULL),(2,'V49.64','','Wrist amputation status',13829,NULL,NULL,NULL),(2,'V49.65','','Below elbow amputation status',13830,NULL,NULL,NULL),(2,'V49.66','','Above elbow amputation status',13831,NULL,NULL,NULL),(2,'V49.67','','Shoulder amputation status',13832,NULL,NULL,NULL),(2,'V49.70','','Unspecified level lower limb amputation status',13833,NULL,NULL,NULL),(2,'V49.71','','Great toe amputation status',13834,NULL,NULL,NULL),(2,'V49.72','','Other toe(s) amputation status',13835,NULL,NULL,NULL),(2,'V49.73','','Foot amputation status',13836,NULL,NULL,NULL),(2,'V49.74','','Ankle amputation status',13837,NULL,NULL,NULL),(2,'V49.75','','Below knee amputation status',13838,NULL,NULL,NULL),(2,'V49.76','','Above knee amputation status',13839,NULL,NULL,NULL),(2,'V49.77','','Hip amputation status',13840,NULL,NULL,NULL),(2,'V49.81','','Asymptomatic postmenopausal status (age-related) (natural)',13841,NULL,NULL,NULL),(2,'V49.82','','Dental sealant status',13842,NULL,NULL,NULL),(2,'V49.83','','Awaiting organ transplant status',13843,NULL,NULL,NULL),(2,'V49.84','','Bed confinement status',13844,NULL,NULL,NULL),(2,'V49.85','','Dual sensory impairment',13845,NULL,NULL,NULL),(2,'V49.89','','Other specified conditions influencing health status',13846,NULL,NULL,NULL),(2,'V49.9','','Unspecified problems with limbs and other problems',13847,NULL,NULL,NULL),(2,'V50.0','','Elective hair transplant for purposes other than remedying health states',13848,NULL,NULL,NULL),(2,'V50.1','','Other plastic surgery for unacceptable cosmetic appearance',13849,NULL,NULL,NULL),(2,'V50.2','','Routine or ritual circumcision',13850,NULL,NULL,NULL),(2,'V50.3','','Ear piercing',13851,NULL,NULL,NULL),(2,'V50.41','','Prophylactic breast removal',13852,NULL,NULL,NULL),(2,'V50.42','','Prophylactic ovary removal',13853,NULL,NULL,NULL),(2,'V50.49','','Other prophylactic gland removal',13854,NULL,NULL,NULL),(2,'V50.8','','Other elective surgery for purposes other than remedying health states',13855,NULL,NULL,NULL),(2,'V50.9','','Unspecified elective surgery for purposes other than remedying health states',13856,NULL,NULL,NULL),(2,'V51.0','','Encounter for breast reconstruction following mastectomy',13857,NULL,NULL,NULL),(2,'V51.8','','Other aftercare involving the use of plastic surgery',13858,NULL,NULL,NULL),(2,'V52.0','','Fitting and adjustment of artificial arm (complete) (partial)',13859,NULL,NULL,NULL),(2,'V52.1','','Fitting and adjustment of artificial leg (complete) (partial)',13860,NULL,NULL,NULL),(2,'V52.2','','Fitting and adjustment of artificial eye',13861,NULL,NULL,NULL),(2,'V52.3','','Fitting and adjustment of dental prosthetic device',13862,NULL,NULL,NULL),(2,'V52.4','','Fitting and adjustment of breast prosthesis and implant',13863,NULL,NULL,NULL),(2,'V52.8','','Fitting and adjustment of other specified prosthetic device',13864,NULL,NULL,NULL),(2,'V52.9','','Fitting and adjustment of unspecified prosthetic device',13865,NULL,NULL,NULL),(2,'V53.01','','Fitting and adjustment of cerebral ventricular (communicating) shunt',13866,NULL,NULL,NULL),(2,'V53.02','','Fitting and adjustment of neuropacemaker (brain) (peripheral nerve) (spinal cord)',13867,NULL,NULL,NULL),(2,'V53.09','','Fitting and adjustment of other devices related to nervous system and special senses',13868,NULL,NULL,NULL),(2,'V53.1','','Fitting and adjustment of spectacles and contact lenses',13869,NULL,NULL,NULL),(2,'V53.2','','Fitting and adjustment of hearing aid',13870,NULL,NULL,NULL),(2,'V53.31','','Fitting and adjustment of cardiac pacemaker',13871,NULL,NULL,NULL),(2,'V53.32','','Fitting and adjustment of automatic implantable cardiac defibrillator',13872,NULL,NULL,NULL),(2,'V53.39','','Fitting and adjustment of other cardiac device',13873,NULL,NULL,NULL),(2,'V53.4','','Fitting and adjustment of orthodontic devices',13874,NULL,NULL,NULL),(2,'V53.50','','Fitting and adjustment of intestinal appliance and device',13875,NULL,NULL,NULL),(2,'V53.51','','Fitting and adjustment of gastric lap band',13876,NULL,NULL,NULL),(2,'V53.59','','Fitting and adjustment of other gastrointestinal appliance and device',13877,NULL,NULL,NULL),(2,'V53.6','','Fitting and adjustment of urinary devices',13878,NULL,NULL,NULL),(2,'V53.7','','Fitting and adjustment of orthopedic devices',13879,NULL,NULL,NULL),(2,'V53.8','','Fitting and adjustment of wheelchair',13880,NULL,NULL,NULL),(2,'V53.90','','Fitting and adjustment of unspecified device',13881,NULL,NULL,NULL),(2,'V53.91','','Fitting and adjustment of insulin pump',13882,NULL,NULL,NULL),(2,'V53.99','','Fitting and adjustment of other device',13883,NULL,NULL,NULL),(2,'V54.01','','Encounter for removal of internal fixation device',13884,NULL,NULL,NULL),(2,'V54.02','','Encounter for lengthening/adjustment of growth rod',13885,NULL,NULL,NULL),(2,'V54.09','','Other aftercare involving internal fixation device',13886,NULL,NULL,NULL),(2,'V54.10','','Aftercare for healing traumatic fracture of arm unspecified',13887,NULL,NULL,NULL),(2,'V54.11','','Aftercare for healing traumatic fracture of upper arm',13888,NULL,NULL,NULL),(2,'V54.12','','Aftercare for healing traumatic fracture of lower arm',13889,NULL,NULL,NULL),(2,'V54.13','','Aftercare for healing traumatic fracture of hip',13890,NULL,NULL,NULL),(2,'V54.14','','Aftercare for healing traumatic fracture of leg unspecified',13891,NULL,NULL,NULL),(2,'V54.15','','Aftercare for healing traumatic fracture of upper leg',13892,NULL,NULL,NULL),(2,'V54.16','','Aftercare for healing traumatic fracture of lower leg',13893,NULL,NULL,NULL),(2,'V54.17','','Aftercare for healing traumatic fracture of vertebrae',13894,NULL,NULL,NULL),(2,'V54.19','','Aftercare for healing traumatic fracture of other bone',13895,NULL,NULL,NULL),(2,'V54.20','','Aftercare for healing pathologic fracture of arm unspecified',13896,NULL,NULL,NULL),(2,'V54.21','','Aftercare for healing pathologic fracture of upper arm',13897,NULL,NULL,NULL),(2,'V54.22','','Aftercare for healing pathologic fracture of lower arm',13898,NULL,NULL,NULL),(2,'V54.23','','Aftercare for healing pathologic fracture of hip',13899,NULL,NULL,NULL),(2,'V54.24','','Aftercare for healing pathologic fracture of leg unspecified',13900,NULL,NULL,NULL),(2,'V54.25','','Aftercare for healing pathologic fracture of upper leg',13901,NULL,NULL,NULL),(2,'V54.26','','Aftercare for healing pathologic fracture of lower leg',13902,NULL,NULL,NULL),(2,'V54.27','','Aftercare for healing pathologic fracture of vertebrae',13903,NULL,NULL,NULL),(2,'V54.29','','Aftercare for healing pathologic fracture of other bone',13904,NULL,NULL,NULL),(2,'V54.81','','Aftercare following joint replacement',13905,NULL,NULL,NULL),(2,'V54.89','','Other orthopedic aftercare',13906,NULL,NULL,NULL),(2,'V54.9','','Unspecified orthopedic aftercare',13907,NULL,NULL,NULL),(2,'V55.0','','Attention to tracheostomy',13908,NULL,NULL,NULL),(2,'V55.1','','Attention to gastrostomy',13909,NULL,NULL,NULL),(2,'V55.2','','Attention to ileostomy',13910,NULL,NULL,NULL),(2,'V55.3','','Attention to colostomy',13911,NULL,NULL,NULL),(2,'V55.4','','Attention to other artificial opening of digestive tract',13912,NULL,NULL,NULL),(2,'V55.5','','Attention to cystostomy',13913,NULL,NULL,NULL),(2,'V55.6','','Attention to other artificial opening of urinary tract',13914,NULL,NULL,NULL),(2,'V55.7','','Attention to artificial vagina',13915,NULL,NULL,NULL),(2,'V55.8','','Attention to other specified artificial opening',13916,NULL,NULL,NULL),(2,'V55.9','','Attention to unspecified artificial opening',13917,NULL,NULL,NULL),(2,'V56.0','','Aftercare involving extracorporeal dialysis',13918,NULL,NULL,NULL),(2,'V56.1','','Fitting and adjustment of extracorporeal dialysis catheter',13919,NULL,NULL,NULL),(2,'V56.2','','Fitting and adjustment of peritoneal dialysis catheter',13920,NULL,NULL,NULL),(2,'V56.31','','Encounter for adequacy testing for hemodialysis',13921,NULL,NULL,NULL),(2,'V56.32','','Encounter for adequacy testing for peritoneal dialysis',13922,NULL,NULL,NULL),(2,'V56.8','','Aftercare involving other dialysis',13923,NULL,NULL,NULL),(2,'V57.0','','Care involving breathing exercises',13924,NULL,NULL,NULL),(2,'V57.1','','Care involving other physical therapy',13925,NULL,NULL,NULL),(2,'V57.21','','Care involving occupational therapy',13926,NULL,NULL,NULL),(2,'V57.22','','Care involving vocational therapy',13927,NULL,NULL,NULL),(2,'V57.3','','Care involving speech-language therapy',13928,NULL,NULL,NULL),(2,'V57.4','','Care involving orthoptic training',13929,NULL,NULL,NULL),(2,'V57.81','','Care involving orthotic training',13930,NULL,NULL,NULL),(2,'V57.89','','Care involving other specified rehabilitation procedure',13931,NULL,NULL,NULL),(2,'V57.9','','Care involving unspecified rehabilitation procedure',13932,NULL,NULL,NULL),(2,'V58.0','','Encounter for radiotherapy',13933,NULL,NULL,NULL),(2,'V58.11','','Encounter for antineoplastic chemotherapy',13934,NULL,NULL,NULL),(2,'V58.12','','Encounter for immunotherapy for neoplastic condition',13935,NULL,NULL,NULL),(2,'V58.2','','Blood transfusion without reported diagnosis',13936,NULL,NULL,NULL),(2,'V58.30','','Encounter for change or removal of nonsurgical wound dressing',13937,NULL,NULL,NULL),(2,'V58.31','','Encounter for change or removal of surgical wound dressing',13938,NULL,NULL,NULL),(2,'V58.32','','Encounter for removal of sutures',13939,NULL,NULL,NULL),(2,'V58.41','','Encounter for planned post-operative wound closure',13940,NULL,NULL,NULL),(2,'V58.42','','Aftercare following surgery for neoplasm',13941,NULL,NULL,NULL),(2,'V58.43','','Aftercare following surgery for injury and trauma',13942,NULL,NULL,NULL),(2,'V58.44','','Aftercare following organ transplant',13943,NULL,NULL,NULL),(2,'V58.49','','Other specified aftercare following surgery',13944,NULL,NULL,NULL),(2,'V58.5','','Orthodontics aftercare',13945,NULL,NULL,NULL),(2,'V58.61','','Long-term (current) use of anticoagulants',13946,NULL,NULL,NULL),(2,'V58.62','','Long-term (current) use of antibiotics',13947,NULL,NULL,NULL),(2,'V58.63','','Long-term (current) use of antiplatelets/antithrombotics',13948,NULL,NULL,NULL),(2,'V58.64','','Long-term (current) use of nonsteroidal anti-inflammatories (nsaid)',13949,NULL,NULL,NULL),(2,'V58.65','','Long-term (current) use of steroids',13950,NULL,NULL,NULL),(2,'V58.66','','Long-term (current) use of aspirin',13951,NULL,NULL,NULL),(2,'V58.67','','Long-term (current) use of insulin',13952,NULL,NULL,NULL),(2,'V58.69','','Long-term (current) use of other medications',13953,NULL,NULL,NULL),(2,'V58.71','','Aftercare following surgery of the sense organs not elsewhere classified',13954,NULL,NULL,NULL),(2,'V58.72','','Aftercare following surgery of the nervous system not elsewhere classified',13955,NULL,NULL,NULL),(2,'V58.73','','Aftercare following surgery of the circulatory system not elsewhere classified',13956,NULL,NULL,NULL),(2,'V58.74','','Aftercare following surgery of the respiratory system not elsewhere classified',13957,NULL,NULL,NULL),(2,'V58.75','','Aftercare following surgery of the teeth oral cavity and digestive system not elsewhere classified',13958,NULL,NULL,NULL),(2,'V58.76','','Aftercare following surgery on the genitourinary system not elsewhere classified',13959,NULL,NULL,NULL),(2,'V58.77','','Aftercare following surgery of the skin and subcutaneous tissue not elsewhere classified',13960,NULL,NULL,NULL),(2,'V58.78','','Aftercare following surgery on the musculoskeletal system not elsewhere classified',13961,NULL,NULL,NULL),(2,'V58.81','','Fitting and adjustment of vascular catheter',13962,NULL,NULL,NULL),(2,'V58.82','','Fitting and adjustment of nonvascular catheter not elsewhere classified',13963,NULL,NULL,NULL),(2,'V58.83','','Encounter for therapeutic drug monitoring',13964,NULL,NULL,NULL),(2,'V58.89','','Other specified aftercare',13965,NULL,NULL,NULL),(2,'V58.9','','Unspecified aftercare',13966,NULL,NULL,NULL),(2,'V59.01','','Blood donors whole blood',13967,NULL,NULL,NULL),(2,'V59.02','','Blood donors stem cells',13968,NULL,NULL,NULL),(2,'V59.09','','Other blood donors',13969,NULL,NULL,NULL),(2,'V59.1','','Skin donors',13970,NULL,NULL,NULL),(2,'V59.2','','Bone donors',13971,NULL,NULL,NULL),(2,'V59.3','','Bone marrow donors',13972,NULL,NULL,NULL),(2,'V59.4','','Kidney donors',13973,NULL,NULL,NULL),(2,'V59.5','','Cornea donors',13974,NULL,NULL,NULL),(2,'V59.6','','Liver donors',13975,NULL,NULL,NULL),(2,'V59.70','','Egg (ooctye)(ovum) donor, unspecified',13976,NULL,NULL,NULL),(2,'V59.71','','Egg (ooctye)(ovum) donor, under age 35, anonymous recipient',13977,NULL,NULL,NULL),(2,'V59.72','','Egg (ooctye)(ovum) donor, under age 35, designated recipient',13978,NULL,NULL,NULL),(2,'V59.73','','Egg (ooctye)(ovum) donor, age 35 and over, anonymous recipient',13979,NULL,NULL,NULL),(2,'V59.74','','Egg (ooctye)(ovum) donor, age 35 and over, designated recipient',13980,NULL,NULL,NULL),(2,'V59.8','','Donors of other specified organ or tissue',13981,NULL,NULL,NULL),(2,'V59.9','','Donors of unspecified organ or tissue',13982,NULL,NULL,NULL),(2,'V60.0','','Lack of housing',13983,NULL,NULL,NULL),(2,'V60.1','','Inadequate housing',13984,NULL,NULL,NULL),(2,'V60.2','','Inadequate material resources',13985,NULL,NULL,NULL),(2,'V60.3','','Person living alone',13986,NULL,NULL,NULL),(2,'V60.4','','No other household member able to render care',13987,NULL,NULL,NULL),(2,'V60.5','','Holiday relief care',13988,NULL,NULL,NULL),(2,'V60.6','','Person living in residential institution',13989,NULL,NULL,NULL),(2,'V60.81','','Foster care (status)',13990,NULL,NULL,NULL),(2,'V60.89','','Other specified housing or economic circumstances',13991,NULL,NULL,NULL),(2,'V60.9','','Unspecified housing or economic circumstance',13992,NULL,NULL,NULL),(2,'V61.01','','Family disruption due to family member on military deployment',13993,NULL,NULL,NULL),(2,'V61.02','','Family disruption due to return of family member from military deployment',13994,NULL,NULL,NULL),(2,'V61.03','','Family disruption due to divorce or legal separation',13995,NULL,NULL,NULL),(2,'V61.04','','Family disruption due to parent-child estrangement',13996,NULL,NULL,NULL),(2,'V61.05','','Family disruption due to child in welfare custody',13997,NULL,NULL,NULL),(2,'V61.06','','Family disruption due to child in foster care or in care of non-parental family member',13998,NULL,NULL,NULL),(2,'V61.07','','Family disruption due to death of family member',13999,NULL,NULL,NULL),(2,'V61.08','','Family disruption due to other extended absence of family member',14000,NULL,NULL,NULL),(2,'V61.09','','Other family disruption',14001,NULL,NULL,NULL),(2,'V61.10','','Unspecified counseling for marital and partner problems',14002,NULL,NULL,NULL),(2,'V61.11','','Counseling for victim of spousal and partner abuse',14003,NULL,NULL,NULL),(2,'V61.12','','Counseling for perpetrator of spousal and partner abuse',14004,NULL,NULL,NULL),(2,'V61.20','','Counseling for parent-child problem unspecified',14005,NULL,NULL,NULL),(2,'V61.21','','Counseling for victim of child abuse',14006,NULL,NULL,NULL),(2,'V61.22','','Counseling for perpetrator of parent child abuse',14007,NULL,NULL,NULL),(2,'V61.23','','Counseling for parent-biological child problem',14008,NULL,NULL,NULL),(2,'V61.24','','Counseling for parent-adopted child problem',14009,NULL,NULL,NULL),(2,'V61.25','','Counseling for parent (guardian)-foster child problem',14010,NULL,NULL,NULL),(2,'V61.29','','Other parent-child problems',14011,NULL,NULL,NULL),(2,'V61.3','','Problems with aged parents or in-laws',14012,NULL,NULL,NULL),(2,'V61.41','','Alcoholism in family',14013,NULL,NULL,NULL),(2,'V61.42','','Substance abuse in family',14014,NULL,NULL,NULL),(2,'V61.49','','Other health problems within the family',14015,NULL,NULL,NULL),(2,'V61.5','','Multiparity',14016,NULL,NULL,NULL),(2,'V61.6','','Illegitimacy or illegitimate pregnancy',14017,NULL,NULL,NULL),(2,'V61.7','','Other unwanted pregnancy',14018,NULL,NULL,NULL),(2,'V61.8','','Other specified family circumstances',14019,NULL,NULL,NULL),(2,'V61.9','','Unspecified family circumstance',14020,NULL,NULL,NULL),(2,'V62.0','','Unemployment',14021,NULL,NULL,NULL),(2,'V62.1','','Adverse effects of work environment',14022,NULL,NULL,NULL),(2,'V62.21','','Personal current military deployment status',14023,NULL,NULL,NULL),(2,'V62.22','','Personal history of return from military deployment',14024,NULL,NULL,NULL),(2,'V62.29','','Other occupational circumstances or maladjustment',14025,NULL,NULL,NULL),(2,'V62.3','','Educational circumstances',14026,NULL,NULL,NULL),(2,'V62.4','','Social maladjustment',14027,NULL,NULL,NULL),(2,'V62.5','','Legal circumstances',14028,NULL,NULL,NULL),(2,'V62.6','','Refusal of treatment for reasons of religion or conscience',14029,NULL,NULL,NULL),(2,'V62.81','','Interpersonal problems not elsewhere classified',14030,NULL,NULL,NULL),(2,'V62.82','','Bereavement uncomplicated',14031,NULL,NULL,NULL),(2,'V62.83','','Counseling for perpetrator of physical/sexual abuse',14032,NULL,NULL,NULL),(2,'V62.84','','Suicidal ideation',14033,NULL,NULL,NULL),(2,'V62.89','','Other psychological or physical stress not elsewhere classified',14034,NULL,NULL,NULL),(2,'V62.9','','Unspecified psychosocial circumstance',14035,NULL,NULL,NULL),(2,'V63.0','','Residence remote from hospital or other health care facility',14036,NULL,NULL,NULL),(2,'V63.1','','Medical services in home not available',14037,NULL,NULL,NULL),(2,'V63.2','','Person awaiting admission to adequate facility elsewhere',14038,NULL,NULL,NULL),(2,'V63.8','','Other specified reasons for unavailability of medical facilities',14039,NULL,NULL,NULL),(2,'V63.9','','Unspecified reason for unavailability of medical facilities',14040,NULL,NULL,NULL),(2,'V64.00','','Vaccination not carried out, unspecified reason',14041,NULL,NULL,NULL),(2,'V64.01','','Vaccination not carried out because of acute illness',14042,NULL,NULL,NULL),(2,'V64.02','','Vaccination not carried out because of chronic illness or condition',14043,NULL,NULL,NULL),(2,'V64.03','','Vaccination not carried out because of immune compromised state',14044,NULL,NULL,NULL),(2,'V64.04','','Vaccination not carried out because of allergy to vaccine or component',14045,NULL,NULL,NULL),(2,'V64.05','','Vaccination not carried out because of caregiver refusal',14046,NULL,NULL,NULL),(2,'V64.06','','Vaccination not carried out because of patient refusal',14047,NULL,NULL,NULL),(2,'V64.07','','Vaccination not carried out for religious reasons',14048,NULL,NULL,NULL),(2,'V64.08','','Vaccination not carried out because patient had disease being vaccinated against',14049,NULL,NULL,NULL),(2,'V64.09','','Vaccination not carried out for other reason',14050,NULL,NULL,NULL),(2,'V64.1','','Surgical or other procedure not carried out because of contraindication',14051,NULL,NULL,NULL),(2,'V64.2','','Surgical or other procedure not carried out because of patient\'s decision',14052,NULL,NULL,NULL),(2,'V64.3','','Procedure not carried out for other reasons',14053,NULL,NULL,NULL),(2,'V64.41','','Laparoscopic surgical procedure converted to open procedure',14054,NULL,NULL,NULL),(2,'V64.42','','Thoracoscopic surgical procedure converted to open procedure',14055,NULL,NULL,NULL),(2,'V64.43','','Arthroscopic surgical procedure converted to open procedure',14056,NULL,NULL,NULL),(2,'V65.0','','Healthy person accompanying sick person',14057,NULL,NULL,NULL),(2,'V65.11','','Pediatric pre-birth visit for expectant parent(s)',14058,NULL,NULL,NULL),(2,'V65.19','','Other person consulting on behalf of another person',14059,NULL,NULL,NULL),(2,'V65.2','','Person feigning illness',14060,NULL,NULL,NULL),(2,'V65.3','','Dietary surveillance and counseling',14061,NULL,NULL,NULL),(2,'V65.40','','Other specified counseling',14062,NULL,NULL,NULL),(2,'V65.41','','Exercise counseling',14063,NULL,NULL,NULL),(2,'V65.42','','Counseling on substance use and abuse',14064,NULL,NULL,NULL),(2,'V65.43','','Counseling on injury prevention',14065,NULL,NULL,NULL),(2,'V65.44','','Human immunodeficiency virus (hiv) counseling',14066,NULL,NULL,NULL),(2,'V65.45','','Counseling on other sexually transmitted diseases',14067,NULL,NULL,NULL),(2,'V65.46','','Encounter for insulin pump training',14068,NULL,NULL,NULL),(2,'V65.49','','Other specified counseling',14069,NULL,NULL,NULL),(2,'V65.5','','Person with feared complaint in whom no diagnosis was made',14070,NULL,NULL,NULL),(2,'V65.8','','Other reasons for seeking consultation',14071,NULL,NULL,NULL),(2,'V65.9','','Unspecified reason for consultation',14072,NULL,NULL,NULL),(2,'V66.0','','Convalescence following surgery',14073,NULL,NULL,NULL),(2,'V66.1','','Convalescence following radiotherapy',14074,NULL,NULL,NULL),(2,'V66.2','','Convalescence following chemotherapy',14075,NULL,NULL,NULL),(2,'V66.3','','Convalescence following psychotherapy and other treatment for mental disorder',14076,NULL,NULL,NULL),(2,'V66.4','','Convalescence following treatment of fracture',14077,NULL,NULL,NULL),(2,'V66.5','','Convalescence following other treatment',14078,NULL,NULL,NULL),(2,'V66.6','','Convalescence following combined treatment',14079,NULL,NULL,NULL),(2,'V66.7','','Encounter for palliative care',14080,NULL,NULL,NULL),(2,'V66.9','','Unspecified convalescence',14081,NULL,NULL,NULL),(2,'V67.00','','Follow-up examination following surgery, unspecified',14082,NULL,NULL,NULL),(2,'V67.01','','Following surgery follow-up vaginal pap smear',14083,NULL,NULL,NULL),(2,'V67.09','','Follow-up examination following other surgery',14084,NULL,NULL,NULL),(2,'V67.1','','Follow-up examination following radiotherapy',14085,NULL,NULL,NULL),(2,'V67.2','','Follow-up examination following chemotherapy',14086,NULL,NULL,NULL),(2,'V67.3','','Follow-up examination following psychotherapy and other treatment for mental disorder',14087,NULL,NULL,NULL),(2,'V67.4','','Following treatment of healed fracture',14088,NULL,NULL,NULL),(2,'V67.51','','Follow-up examination following completed treatment with high-risk medication not elsewhere classified',14089,NULL,NULL,NULL),(2,'V67.59','','Other follow-up examination',14090,NULL,NULL,NULL),(2,'V67.6','','Follow-up examination following combined treatment',14091,NULL,NULL,NULL),(2,'V67.9','','Unspecified follow-up examination',14092,NULL,NULL,NULL),(2,'V68.01','','Disability examination',14093,NULL,NULL,NULL),(2,'V68.09','','Other issue of medical certificates',14094,NULL,NULL,NULL),(2,'V68.1','','Issue of repeat prescriptions',14095,NULL,NULL,NULL),(2,'V68.2','','Request for expert evidence',14096,NULL,NULL,NULL),(2,'V68.81','','Referral of patient without examination or treatment',14097,NULL,NULL,NULL),(2,'V68.89','','Encounters for other specified administrative purpose',14098,NULL,NULL,NULL),(2,'V68.9','','Encounters for unspecified administrative purpose',14099,NULL,NULL,NULL),(2,'V69.0','','Lack of physical exercise',14100,NULL,NULL,NULL),(2,'V69.1','','Inappropriate diet and eating habits',14101,NULL,NULL,NULL),(2,'V69.2','','High-risk sexual behavior',14102,NULL,NULL,NULL),(2,'V69.3','','Gambling and betting',14103,NULL,NULL,NULL),(2,'V69.4','','Lack of adequate sleep',14104,NULL,NULL,NULL),(2,'V69.5','','Behavioral insomnia of childhood',14105,NULL,NULL,NULL),(2,'V69.8','','Other problems related to lifestyle',14106,NULL,NULL,NULL),(2,'V69.9','','Unspecified problem related to lifestyle',14107,NULL,NULL,NULL),(2,'V70.0','','Routine general medical examination at a health care facility',14108,NULL,NULL,NULL),(2,'V70.1','','General psychiatric examination requested by the authority',14109,NULL,NULL,NULL),(2,'V70.2','','General psychiatric examination other and unspecified',14110,NULL,NULL,NULL),(2,'V70.3','','Other general medical examination for administrative purposes',14111,NULL,NULL,NULL),(2,'V70.4','','Examination for medicolegal reasons',14112,NULL,NULL,NULL),(2,'V70.5','','Health examination of defined subpopulations',14113,NULL,NULL,NULL),(2,'V70.6','','Health examination in population surveys',14114,NULL,NULL,NULL),(2,'V70.7','','Examination of participant in clinical trial',14115,NULL,NULL,NULL),(2,'V70.8','','Other specified general medical examinations',14116,NULL,NULL,NULL),(2,'V70.9','','Unspecified general medical examination',14117,NULL,NULL,NULL),(2,'V71.01','','Observation of adult antisocial behavior',14118,NULL,NULL,NULL),(2,'V71.02','','Observation of childhood or adolescent antisocial behavior',14119,NULL,NULL,NULL),(2,'V71.09','','Observation of other suspected mental condition',14120,NULL,NULL,NULL),(2,'V71.1','','Observation for suspected malignant neoplasm',14121,NULL,NULL,NULL),(2,'V71.2','','Observation for suspected tuberculosis',14122,NULL,NULL,NULL),(2,'V71.3','','Observation following accident at work',14123,NULL,NULL,NULL),(2,'V71.4','','Observation following other accident',14124,NULL,NULL,NULL),(2,'V71.5','','Observation following alleged rape or seduction',14125,NULL,NULL,NULL),(2,'V71.6','','Observation following other inflicted injury',14126,NULL,NULL,NULL),(2,'V71.7','','Observation for suspected cardiovascular disease',14127,NULL,NULL,NULL),(2,'V71.81','','Observation for suspected abuse and neglect',14128,NULL,NULL,NULL),(2,'V71.82','','Observation and evaluation for suspected exposure to anthrax',14129,NULL,NULL,NULL),(2,'V71.83','','Observation and evaluation for suspected exposure to other biological agent',14130,NULL,NULL,NULL),(2,'V71.89','','Observation for other specified suspected conditions',14131,NULL,NULL,NULL),(2,'V71.9','','Observation for unspecified suspected condition',14132,NULL,NULL,NULL),(2,'V72.0','','Examination of eyes and vision',14133,NULL,NULL,NULL),(2,'V72.11','','Encounter for hearing examination following failed hearing screening',14134,NULL,NULL,NULL),(2,'V72.12','','Encounter for hearing conservation and treatment',14135,NULL,NULL,NULL),(2,'V72.19','','Other examination of ears and hearing',14136,NULL,NULL,NULL),(2,'V72.2','','Dental examination',14137,NULL,NULL,NULL),(2,'V72.31','','Routine gynecological examination',14138,NULL,NULL,NULL),(2,'V72.32','','Encounter for papanicolaou cervical smear to confirm findings of recent normal smear following initial abnormal smear',14139,NULL,NULL,NULL),(2,'V72.40','','Pregnancy examination or test, pregnancy unconfirmed',14140,NULL,NULL,NULL),(2,'V72.41','','Pregnancy examination or test, negative result',14141,NULL,NULL,NULL),(2,'V72.42','','Pregnancy examination or test, positive result',14142,NULL,NULL,NULL),(2,'V72.5','','Radiological examination not elsewhere classified',14143,NULL,NULL,NULL),(2,'V72.60','','Laboratory examination, unspecified',14144,NULL,NULL,NULL),(2,'V72.61','','Antibody response examination',14145,NULL,NULL,NULL),(2,'V72.62','','Laboratory examination ordered as part of a routine general medical examination',14146,NULL,NULL,NULL),(2,'V72.63','','Pre-procedural laboratory examination',14147,NULL,NULL,NULL),(2,'V72.69','','Other laboratory examination',14148,NULL,NULL,NULL),(2,'V72.7','','Diagnostic skin and sensitization tests',14149,NULL,NULL,NULL),(2,'V72.81','','Pre-operative cardiovascular examination',14150,NULL,NULL,NULL),(2,'V72.82','','Pre-operative respiratory examination',14151,NULL,NULL,NULL),(2,'V72.83','','Other specified pre-operative examination',14152,NULL,NULL,NULL),(2,'V72.84','','Pre-operative examination unspecified',14153,NULL,NULL,NULL),(2,'V72.85','','Other specified examination',14154,NULL,NULL,NULL),(2,'V72.86','','Encounter for blood typing',14155,NULL,NULL,NULL),(2,'V72.9','','Unspecified examination',14156,NULL,NULL,NULL),(2,'V73.0','','Screening examination for poliomyelitis',14157,NULL,NULL,NULL),(2,'V73.1','','Screening examination for smallpox',14158,NULL,NULL,NULL),(2,'V73.2','','Screening examination for measles',14159,NULL,NULL,NULL),(2,'V73.3','','Screening examination for rubella',14160,NULL,NULL,NULL),(2,'V73.4','','Screening examination for yellow fever',14161,NULL,NULL,NULL),(2,'V73.5','','Screening examination for other arthropod-borne viral diseases',14162,NULL,NULL,NULL),(2,'V73.6','','Screening examination for trachoma',14163,NULL,NULL,NULL),(2,'V73.81','','Screening examination for human papillomavirus (hpv)',14164,NULL,NULL,NULL),(2,'V73.88','','Other specified chlamydial diseases',14165,NULL,NULL,NULL),(2,'V73.89','','Other specified viral diseases',14166,NULL,NULL,NULL),(2,'V73.98','','Screening examination for unspecified chlamydial disease',14167,NULL,NULL,NULL),(2,'V73.99','','Screening examination for unspecified viral disease',14168,NULL,NULL,NULL),(2,'V74.0','','Screening examination for cholera',14169,NULL,NULL,NULL),(2,'V74.1','','Screening examination for pulmonary tuberculosis',14170,NULL,NULL,NULL),(2,'V74.2','','Screening examination for leprosy (hansen\'s disease)',14171,NULL,NULL,NULL),(2,'V74.3','','Screening examination for diphtheria',14172,NULL,NULL,NULL),(2,'V74.4','','Screening examination for bacterial conjunctivitis',14173,NULL,NULL,NULL),(2,'V74.5','','Screening examination for venereal disease',14174,NULL,NULL,NULL),(2,'V74.6','','Screening examination for yaws',14175,NULL,NULL,NULL),(2,'V74.8','','Screening examination for other specified bacterial and spirochetal diseases',14176,NULL,NULL,NULL),(2,'V74.9','','Screening examination for unspecified bacterial and spirochetal diseases',14177,NULL,NULL,NULL),(2,'V75.0','','Screening examination for rickettsial diseases',14178,NULL,NULL,NULL),(2,'V75.1','','Screening examination for malaria',14179,NULL,NULL,NULL),(2,'V75.2','','Screening examination for leishmaniasis',14180,NULL,NULL,NULL),(2,'V75.3','','Screening examination for trypanosomiasis',14181,NULL,NULL,NULL),(2,'V75.4','','Screening examination for mycotic infections',14182,NULL,NULL,NULL),(2,'V75.5','','Screening examination for schistosomiasis',14183,NULL,NULL,NULL),(2,'V75.6','','Screening examination for filariasis',14184,NULL,NULL,NULL),(2,'V75.7','','Screening examination for intestinal helminthiasis',14185,NULL,NULL,NULL),(2,'V75.8','','Screening examination for other specified parasitic infections',14186,NULL,NULL,NULL),(2,'V75.9','','Screening examination for unspecified infectious disease',14187,NULL,NULL,NULL),(2,'V76.0','','Special screening for malignant neoplasms of the respiratory organs',14188,NULL,NULL,NULL),(2,'V76.10','','Breast screening unspecified',14189,NULL,NULL,NULL),(2,'V76.11','','Screening mammogram for high-risk patient',14190,NULL,NULL,NULL),(2,'V76.12','','Other screening mammogram',14191,NULL,NULL,NULL),(2,'V76.19','','Other screening breast examination',14192,NULL,NULL,NULL),(2,'V76.2','','Screening for malignant neoplasms of the cervix',14193,NULL,NULL,NULL),(2,'V76.3','','Screening for malignant neoplasms of the bladder',14194,NULL,NULL,NULL),(2,'V76.41','','Screening for malignant neoplasms of the rectum',14195,NULL,NULL,NULL),(2,'V76.42','','Screening for malignant neoplasms of the oral cavity',14196,NULL,NULL,NULL),(2,'V76.43','','Screening for malignant neoplasms of the skin',14197,NULL,NULL,NULL),(2,'V76.44','','Screening for malignant neoplasms of the prostate',14198,NULL,NULL,NULL),(2,'V76.45','','Screening for malignant neoplasms of the testis',14199,NULL,NULL,NULL),(2,'V76.46','','Special screening for malignant neoplasms ovary',14200,NULL,NULL,NULL),(2,'V76.47','','Special screening for malignant neoplasms vagina',14201,NULL,NULL,NULL),(2,'V76.49','','Special screening for malignant neoplasms other sites',14202,NULL,NULL,NULL),(2,'V76.50','','Special screening for malignant neoplasms unspecified intestine',14203,NULL,NULL,NULL),(2,'V76.51','','Special screening for malignant neoplasms colon',14204,NULL,NULL,NULL),(2,'V76.52','','Special screening for malignant neoplasms small intestine',14205,NULL,NULL,NULL),(2,'V76.81','','Special screening for malignant neoplasms nervous system',14206,NULL,NULL,NULL),(2,'V76.89','','Special screening for other malignant neoplasms',14207,NULL,NULL,NULL),(2,'V76.9','','Screening for unspecified malignant neoplasms',14208,NULL,NULL,NULL),(2,'V77.0','','Screening for thyroid disorders',14209,NULL,NULL,NULL),(2,'V77.1','','Screening for diabetes mellitus',14210,NULL,NULL,NULL),(2,'V77.2','','Screening for malnutrition',14211,NULL,NULL,NULL),(2,'V77.3','','Screening for phenylketonuria (pku)',14212,NULL,NULL,NULL),(2,'V77.4','','Screening for galactosemia',14213,NULL,NULL,NULL),(2,'V77.5','','Screening for gout',14214,NULL,NULL,NULL),(2,'V77.6','','Screening for cystic fibrosis',14215,NULL,NULL,NULL),(2,'V77.7','','Screening for other inborn errors of metabolism',14216,NULL,NULL,NULL),(2,'V77.8','','Screening for obesity',14217,NULL,NULL,NULL),(2,'V77.91','','Screening for lipoid disorders',14218,NULL,NULL,NULL),(2,'V77.99','','Screening for other and unspecified endocrine nutritional metabolic and immunity disorders',14219,NULL,NULL,NULL),(2,'V78.0','','Screening for iron deficiency anemia',14220,NULL,NULL,NULL),(2,'V78.1','','Screening for other and unspecified deficiency anemia',14221,NULL,NULL,NULL),(2,'V78.2','','Screening for sickle-cell disease or trait',14222,NULL,NULL,NULL),(2,'V78.3','','Screening for other hemoglobinopathies',14223,NULL,NULL,NULL),(2,'V78.8','','Screening for other disorders of blood and blood-forming organs',14224,NULL,NULL,NULL),(2,'V78.9','','Screening for unspecified disorder of blood and blood-forming organs',14225,NULL,NULL,NULL),(2,'V79.0','','Screening for depression',14226,NULL,NULL,NULL),(2,'V79.1','','Screening for alcoholism',14227,NULL,NULL,NULL),(2,'V79.2','','Screening for mental retardation',14228,NULL,NULL,NULL),(2,'V79.3','','Screening for developmental handicaps in early childhood',14229,NULL,NULL,NULL),(2,'V79.8','','Screening for other specified mental disorders and developmental handicaps',14230,NULL,NULL,NULL),(2,'V79.9','','Screening for unspecified mental disorder and developmental handicap',14231,NULL,NULL,NULL),(2,'V80.01','','Special screening for traumatic brain injury',14232,NULL,NULL,NULL),(2,'V80.09','','Special screening for other neurological conditions',14233,NULL,NULL,NULL),(2,'V80.1','','Screening for glaucoma',14234,NULL,NULL,NULL),(2,'V80.2','','Screening for other eye conditions',14235,NULL,NULL,NULL),(2,'V80.3','','Screening for ear diseases',14236,NULL,NULL,NULL),(2,'V81.0','','Screening for ischemic heart disease',14237,NULL,NULL,NULL),(2,'V81.1','','Screening for hypertension',14238,NULL,NULL,NULL),(2,'V81.2','','Screening for other and unspecified cardiovascular conditions',14239,NULL,NULL,NULL),(2,'V81.3','','Screening for chronic bronchitis and emphysema',14240,NULL,NULL,NULL),(2,'V81.4','','Screening for other and unspecified respiratory conditions',14241,NULL,NULL,NULL),(2,'V81.5','','Screening for nephropathy',14242,NULL,NULL,NULL),(2,'V81.6','','Screening for other and unspecified genitourinary conditions',14243,NULL,NULL,NULL),(2,'V82.0','','Screening for skin conditions',14244,NULL,NULL,NULL),(2,'V82.1','','Screening for rheumatoid arthritis',14245,NULL,NULL,NULL),(2,'V82.2','','Screening for other rheumatic disorders',14246,NULL,NULL,NULL),(2,'V82.3','','Screening for congenital dislocation of hip',14247,NULL,NULL,NULL),(2,'V82.4','','Maternal postnatal screening for chromosomal anomalies',14248,NULL,NULL,NULL),(2,'V82.5','','Screening for chemical poisoning and other contamination',14249,NULL,NULL,NULL),(2,'V82.6','','Multiphasic screening',14250,NULL,NULL,NULL),(2,'V82.71','','Screening for genetic disease carrier status',14251,NULL,NULL,NULL),(2,'V82.79','','Other genetic screening',14252,NULL,NULL,NULL),(2,'V82.81','','Special screening for osteoporosis',14253,NULL,NULL,NULL),(2,'V82.89','','Special screening for other specified conditions',14254,NULL,NULL,NULL),(2,'V82.9','','Screening for unspecified condition',14255,NULL,NULL,NULL),(2,'V83.01','','Asymptomatic hemophilia a carrier',14256,NULL,NULL,NULL),(2,'V83.02','','Symptomatic hemophilia a carrier',14257,NULL,NULL,NULL),(2,'V83.81','','Cystic fibrosis gene carrier',14258,NULL,NULL,NULL),(2,'V83.89','','Other genetic carrier status',14259,NULL,NULL,NULL),(2,'V84.01','','Genetic susceptibility to malignant neoplasm of breast',14260,NULL,NULL,NULL),(2,'V84.02','','Genetic susceptibility to malignant neoplasm of ovary',14261,NULL,NULL,NULL),(2,'V84.03','','Genetic susceptibility to malignant neoplasm of prostate',14262,NULL,NULL,NULL),(2,'V84.04','','Genetic susceptibility to malignant neoplasm of endometrium',14263,NULL,NULL,NULL),(2,'V84.09','','Genetic susceptibility to other malignant neoplasm',14264,NULL,NULL,NULL),(2,'V84.81','','Genetic susceptibility to multiple endocrine neoplasia [men]',14265,NULL,NULL,NULL),(2,'V84.89','','Genetic susceptibility to other disease',14266,NULL,NULL,NULL),(2,'V85.0','','Body mass index less than 19, adult',14267,NULL,NULL,NULL),(2,'V85.1','','Body mass index between 19-24, adult',14268,NULL,NULL,NULL),(2,'V85.21','','Body mass index 25.0-25.9, adult',14269,NULL,NULL,NULL),(2,'V85.22','','Body mass index 26.0-26.9, adult',14270,NULL,NULL,NULL),(2,'V85.23','','Body mass index 27.0-27.9, adult',14271,NULL,NULL,NULL),(2,'V85.24','','Body mass index 28.0-28.9, adult',14272,NULL,NULL,NULL),(2,'V85.25','','Body mass index 29.0-29.9, adult',14273,NULL,NULL,NULL),(2,'V85.30','','Body mass index 30.0-30.9, adult',14274,NULL,NULL,NULL),(2,'V85.31','','Body mass index 31.0-31.9, adult',14275,NULL,NULL,NULL),(2,'V85.32','','Body mass index 32.0-32.9, adult',14276,NULL,NULL,NULL),(2,'V85.33','','Body mass index 33.0-33.9, adult',14277,NULL,NULL,NULL),(2,'V85.34','','Body mass index 34.0-34.9, adult',14278,NULL,NULL,NULL),(2,'V85.35','','Body mass index 35.0-35.9, adult',14279,NULL,NULL,NULL),(2,'V85.36','','Body mass index 36.0-36.9, adult',14280,NULL,NULL,NULL),(2,'V85.37','','Body mass index 37.0-37.9, adult',14281,NULL,NULL,NULL),(2,'V85.38','','Body mass index 38.0-38.9, adult',14282,NULL,NULL,NULL),(2,'V85.39','','Body mass index 39.0-39.9, adult',14283,NULL,NULL,NULL),(2,'V85.4','','Body mass index 40 and over, adult',14284,NULL,NULL,NULL),(2,'V85.51','','Body mass index, pediatric, less than5th percentile for age',14285,NULL,NULL,NULL),(2,'V85.52','','Body mass index, pediatric,5th percentile to less than 85th percentile for age',14286,NULL,NULL,NULL),(2,'V85.53','','Body mass index, pediatric, 85th percentile to less than 95th percentile for age',14287,NULL,NULL,NULL),(2,'V85.54','','Body mass index, pediatric, greater than or equal to 95th percentile for age',14288,NULL,NULL,NULL),(2,'V86.0','','Estrogen receptor positive status [er+]',14289,NULL,NULL,NULL),(2,'V86.1','','Estrogen receptor negative status [er-]',14290,NULL,NULL,NULL),(2,'V87.01','','Contact with and (suspected) exposure to arsenic',14291,NULL,NULL,NULL),(2,'V87.09','','Contact with and (suspected) exposure to other hazardous metals',14292,NULL,NULL,NULL),(2,'V87.11','','Contact with and (suspected) exposure to aromatic amines',14293,NULL,NULL,NULL),(2,'V87.12','','Contact with and (suspected) exposure to benzene',14294,NULL,NULL,NULL),(2,'V87.19','','Contact with and (suspected) exposure to other hazardous aromatic compounds',14295,NULL,NULL,NULL),(2,'V87.2','','Contact with and (suspected) exposure to other potentially hazardous chemicals',14296,NULL,NULL,NULL),(2,'V87.31','','Contact with and (suspected) exposure to mold',14297,NULL,NULL,NULL),(2,'V87.32','','Contact with and (suspected) exposure to algae bloom',14298,NULL,NULL,NULL),(2,'V87.39','','Contact with and (suspected) exposure to other potentially hazardous substances',14299,NULL,NULL,NULL),(2,'V87.41','','Personal history of antineoplastic chemotherapy',14300,NULL,NULL,NULL),(2,'V87.42','','Personal history of monoclonal drug therapy',14301,NULL,NULL,NULL),(2,'V87.43','','Personal history of estrogen therapy',14302,NULL,NULL,NULL),(2,'V87.44','','Personal history of inhaled steroid therapy',14303,NULL,NULL,NULL),(2,'V87.45','','Personal history of systemic steroid therapy',14304,NULL,NULL,NULL),(2,'V87.46','','Personal history of immunosuppressive therapy',14305,NULL,NULL,NULL),(2,'V87.49','','Personal history of other drug therapy',14306,NULL,NULL,NULL),(2,'V88.01','','Acquired absence of both cervix and uterus',14307,NULL,NULL,NULL),(2,'V88.02','','Acquired absence of uterus with remaining cervical stump',14308,NULL,NULL,NULL),(2,'V88.03','','Acquired absence of cervix with remaining uterus',14309,NULL,NULL,NULL),(2,'V89.01','','Suspected problem with amniotic cavity and membrane not found',14310,NULL,NULL,NULL),(2,'V89.02','','Suspected placental problem not found',14311,NULL,NULL,NULL),(2,'V89.03','','Suspected fetal anomaly not found',14312,NULL,NULL,NULL),(2,'V89.04','','Suspected problem with fetal growth not found',14313,NULL,NULL,NULL),(2,'V89.05','','Suspected cervical shortening not found',14314,NULL,NULL,NULL),(2,'V89.09','','Other suspected maternal and fetal condition not found',14315,NULL,NULL,NULL);
/*!40000 ALTER TABLE `icd_9` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ihb_progress`
--

DROP TABLE IF EXISTS `ihb_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ihb_progress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `client_sig` varchar(255) DEFAULT NULL,
  `contact_type` varchar(255) DEFAULT NULL,
  `functioning` text DEFAULT NULL,
  `goal` text DEFAULT NULL,
  `intervention_desc` text DEFAULT NULL,
  `interventions` varchar(255) DEFAULT NULL,
  `interventions_other` varchar(255) DEFAULT NULL,
  `issue` text DEFAULT NULL,
  `next_appt_date` datetime DEFAULT NULL,
  `persons_present` varchar(255) DEFAULT NULL,
  `persons_present_details` text DEFAULT NULL,
  `plan` text DEFAULT NULL,
  `progress` text DEFAULT NULL,
  `provider_sig` varchar(255) DEFAULT NULL,
  `response` text DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF29CB3C94661880D` (`clinician_id`),
  CONSTRAINT `FKF29CB3C94661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ihb_progress`
--

LOCK TABLES `ihb_progress` WRITE;
/*!40000 ALTER TABLE `ihb_progress` DISABLE KEYS */;
/*!40000 ALTER TABLE `ihb_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incident_report`
--

DROP TABLE IF EXISTS `incident_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `incident_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `authorities` text DEFAULT NULL,
  `clinician_sig` varchar(255) DEFAULT NULL,
  `clinician_sig_date` datetime DEFAULT NULL,
  `consequences` text DEFAULT NULL,
  `description` text DEFAULT NULL,
  `event_codes` varchar(255) DEFAULT NULL,
  `factors` text DEFAULT NULL,
  `family` text DEFAULT NULL,
  `incident_date` datetime DEFAULT NULL,
  `incident_time` varchar(255) DEFAULT NULL,
  `investigation` varchar(255) DEFAULT NULL,
  `investigation_status` varchar(255) DEFAULT NULL,
  `mgmt_sig` varchar(255) DEFAULT NULL,
  `mgmt_sig_date` datetime DEFAULT NULL,
  `persons` text DEFAULT NULL,
  `program_name` varchar(255) DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `report_author` varchar(255) DEFAULT NULL,
  `report_date` datetime DEFAULT NULL,
  `service` varchar(255) DEFAULT NULL,
  `witness_sig` varchar(255) DEFAULT NULL,
  `witness_sig_date` datetime DEFAULT NULL,
  `witnesses` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK84F5D414661880D` (`clinician_id`),
  CONSTRAINT `FK84F5D414661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incident_report`
--

LOCK TABLES `incident_report` WRITE;
/*!40000 ALTER TABLE `incident_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `incident_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institution`
--

DROP TABLE IF EXISTS `institution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institution` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `primary_phone` varchar(255) DEFAULT NULL,
  `secondary_phone` varchar(255) DEFAULT NULL,
  `street_address1` varchar(255) DEFAULT NULL,
  `street_address2` varchar(255) DEFAULT NULL,
  `us_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3529A5B8B929E6ED` (`us_state`),
  CONSTRAINT `FK3529A5B8B929E6ED` FOREIGN KEY (`us_state`) REFERENCES `us_state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institution`
--

LOCK TABLES `institution` WRITE;
/*!40000 ALTER TABLE `institution` DISABLE KEYS */;
/*!40000 ALTER TABLE `institution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intervention_plan`
--

DROP TABLE IF EXISTS `intervention_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intervention_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `antecedent` text DEFAULT NULL,
  `description` text DEFAULT NULL,
  `fbabip_id` int(11) DEFAULT NULL,
  `crisis` text DEFAULT NULL,
  `implementedWhere` text DEFAULT NULL,
  `goals` text DEFAULT NULL,
  `reinforcements` text DEFAULT NULL,
  `replacement` text DEFAULT NULL,
  `rewards` text DEFAULT NULL,
  `skillsDefecits` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intervention_plan`
--

LOCK TABLES `intervention_plan` WRITE;
/*!40000 ALTER TABLE `intervention_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `intervention_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `due_date` datetime DEFAULT NULL,
  `invoice_number` varchar(255) DEFAULT NULL,
  `invoice_status` varchar(255) DEFAULT NULL,
  `issue_date` datetime DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `sales_tax` float DEFAULT NULL,
  `subtotal` float DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `total` float DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_line_item`
--

DROP TABLE IF EXISTS `invoice_line_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice_line_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `invoice_id` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_line_item`
--

LOCK TABLES `invoice_line_item` WRITE;
/*!40000 ALTER TABLE `invoice_line_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice_line_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lab`
--

DROP TABLE IF EXISTS `lab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lab` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `encounter_id` int(11) DEFAULT NULL,
  `glucose` varchar(255) DEFAULT NULL,
  `hb` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `urine_dip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab`
--

LOCK TABLES `lab` WRITE;
/*!40000 ALTER TABLE `lab` DISABLE KEYS */;
/*!40000 ALTER TABLE `lab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `letter`
--

DROP TABLE IF EXISTS `letter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `letter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `content` text DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `letter_status` varchar(255) DEFAULT NULL,
  `letter_type` varchar(255) DEFAULT NULL,
  `letter_type_label` varchar(255) DEFAULT NULL,
  `recipient_client_type` varchar(255) NOT NULL,
  `recipient_id` int(11) DEFAULT NULL,
  `sender_client_type` varchar(255) DEFAULT NULL,
  `sender_full_name` varchar(255) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `letter`
--

LOCK TABLES `letter` WRITE;
/*!40000 ALTER TABLE `letter` DISABLE KEYS */;
INSERT INTO `letter` VALUES (1,'2018-11-29 10:33:15','2018-11-29 10:33:25','2018-11-29 10:33:25','%3Ctable%20class%3D%22MsoNormalTable%22%20style%3D%22width%3A%207.5in%3B%22%20border%3D%220%22%20cellpadding%3D%220%22%20cellspacing%3D%220%22%20width%3D%22540%22%3E%3Ctbody%3E%3Ctr%20style%3D%22mso-yfti-irow%3A0%3Bmso-yfti-firstrow%3Ayes%3Bmso-yfti-lastrow%3Ayes%3B%20%20%20height%3A60.0pt%22%3E%3Ctd%20style%3D%22padding%3A5.25pt%205.25pt%205.25pt%205.25pt%3Bheight%3A60.0pt%22%20valign%3D%22top%22%3E%3Cp%20class%3D%22MsoNormal%22%20style%3D%22margin-bottom%3A0in%3Bmargin-bottom%3A.0001pt%3B%20%20%20text-align%3Acenter%3Bline-height%3Anormal%22%20align%3D%22center%22%3E%3Cspan%20style%3D%22font-size%3A9.0pt%3B%20%20%20font-family%3AArial%3Bmso-fareast-font-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%22%3EGROWING%20%20%20POTENTIAL%20SERVICES%3A%3C/span%3E%3Cspan%20style%3D%22font-size%3A12.0pt%3Bfont-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%3B%20%20%20mso-fareast-font-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%3Bcolor%3Awindowtext%22%3E%3C/span%3E%3C/p%3E%20%20%20%3Cp%20class%3D%22MsoNormal%22%20style%3D%22margin-bottom%3A0in%3Bmargin-bottom%3A.0001pt%3B%20%20%20text-align%3Acenter%3Bline-height%3Anormal%22%20align%3D%22center%22%3E%3Cspan%20style%3D%22font-size%3A9.0pt%3B%20%20%20font-family%3AArial%3Bmso-fareast-font-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%22%3ETherapeutic%20and%20%20%20Behavioral%20Solutions%2C%20PC%3C/span%3E%3Cspan%20style%3D%22font-size%3A12.0pt%3Bfont-family%3A%20%20%20%26quot%3BTimes%20New%20Roman%26quot%3B%3Bmso-fareast-font-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%3Bcolor%3Awindowtext%22%3E%3C/span%3E%3C/p%3E%20%20%20%3Cp%20class%3D%22MsoNormal%22%20style%3D%22margin-bottom%3A0in%3Bmargin-bottom%3A.0001pt%3B%20%20%20text-align%3Acenter%3Bline-height%3Anormal%22%20align%3D%22center%22%3E%3Cspan%20style%3D%22font-size%3A9.0pt%3B%20%20%20font-family%3AArial%3Bmso-fareast-font-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%22%3E%28%u201CGPS%u201D%29%3C/span%3E%3Cspan%20style%3D%22font-size%3A12.0pt%3Bfont-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%3Bmso-fareast-font-family%3A%20%20%20%26quot%3BTimes%20New%20Roman%26quot%3B%3Bcolor%3Awindowtext%22%3E%3C/span%3E%3C/p%3E%20%20%20%3C/td%3E%20%20%20%3Ctd%20style%3D%22padding%3A5.25pt%205.25pt%205.25pt%205.25pt%3Bheight%3A60.0pt%22%20valign%3D%22top%22%3E%20%20%20%3Cp%20class%3D%22MsoNormal%22%20style%3D%22margin-top%3A0in%3Bmargin-right%3A-5.75pt%3B%20%20%20margin-bottom%3A0in%3Bmargin-left%3A0in%3Bmargin-bottom%3A.0001pt%3Btext-align%3Acenter%3B%20%20%20line-height%3Anormal%22%20align%3D%22center%22%3E%3Cspan%20style%3D%22font-size%3A9.0pt%3Bfont-family%3AArial%3B%20%20%20mso-fareast-font-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%22%3E139%20%26amp%3B%20141%20Hazard%20Ave%3C/span%3E%3Cspan%20style%3D%22font-size%3A12.0pt%3Bfont-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%3Bmso-fareast-font-family%3A%20%20%20%26quot%3BTimes%20New%20Roman%26quot%3B%3Bcolor%3Awindowtext%22%3E%3C/span%3E%3C/p%3E%20%20%20%3Cp%20class%3D%22MsoNormal%22%20style%3D%22margin-top%3A0in%3Bmargin-right%3A-5.75pt%3B%20%20%20margin-bottom%3A0in%3Bmargin-left%3A0in%3Bmargin-bottom%3A.0001pt%3Btext-align%3Acenter%3B%20%20%20line-height%3Anormal%22%20align%3D%22center%22%3E%3Cspan%20style%3D%22font-size%3A9.0pt%3Bfont-family%3AArial%3B%20%20%20mso-fareast-font-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%22%3EEnfield%2C%20CT%2006082%3C/span%3E%3Cspan%20style%3D%22font-size%3A12.0pt%3Bfont-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%3Bmso-fareast-font-family%3A%20%20%20%26quot%3BTimes%20New%20Roman%26quot%3B%3Bcolor%3Awindowtext%22%3E%3C/span%3E%3C/p%3E%20%20%20%3Cp%20class%3D%22MsoNormal%22%20style%3D%22margin-top%3A0in%3Bmargin-right%3A-5.75pt%3B%20%20%20margin-bottom%3A0in%3Bmargin-left%3A0in%3Bmargin-bottom%3A.0001pt%3Btext-align%3Acenter%3B%20%20%20line-height%3Anormal%22%20align%3D%22center%22%3E%3Cspan%20style%3D%22font-size%3A9.0pt%3Bfont-family%3AArial%3B%20%20%20mso-fareast-font-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%22%3ETel%3A%20860-698-6077%3C/span%3E%3Cspan%20style%3D%22font-size%3A12.0pt%3Bfont-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%3Bmso-fareast-font-family%3A%20%20%20%26quot%3BTimes%20New%20Roman%26quot%3B%3Bcolor%3Awindowtext%22%3E%3C/span%3E%3C/p%3E%20%20%20%3Cp%20class%3D%22MsoNormal%22%20style%3D%22margin-top%3A0in%3Bmargin-right%3A-5.75pt%3B%20%20%20margin-bottom%3A0in%3Bmargin-left%3A0in%3Bmargin-bottom%3A.0001pt%3Btext-align%3Acenter%3B%20%20%20line-height%3Anormal%22%20align%3D%22center%22%3E%3Cspan%20style%3D%22font-size%3A9.0pt%3Bfont-family%3AArial%3B%20%20%20mso-fareast-font-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%22%3EFax%3A860-698-6631%3C/span%3E%3Cspan%20style%3D%22font-size%3A12.0pt%3Bfont-family%3A%26quot%3BTimes%20New%20Roman%26quot%3B%3Bmso-fareast-font-family%3A%20%20%20%26quot%3BTimes%20New%20Roman%26quot%3B%3Bcolor%3Awindowtext%22%3E%3C/span%3E%3C/p%3E%20%20%20%3C/td%3E%20%20%3C/tr%3E%20%3C/tbody%3E%3C/table%3E%3Cp%20class%3D%22Normal1%22%20style%3D%22line-height%3Anormal%22%3E%20%26nbsp%3B%3C/p%3E%3Cp%3E%20%20%3Cbr%3E%3C/p%3E%3Cp%3E%20%20%3Cbr%3E%3C/p%3E%3Cp%20class%3D%22Normal1%22%20style%3D%22line-height%3Anormal%22%3E%3Cb%3E%3Cspan%20style%3D%22font-size%3A12.0pt%3Bfont-family%3ACambria%3Bmso-fareast-font-family%3A%20Cambria%3Bmso-bidi-font-family%3ACambria%3Bcolor%3A%23272727%22%3EDate%3A%2011/29/2018%3C/span%3E%3C/b%3E%3C/p%3E%3Cp%3E%20%20%3Cbr%3E%3C/p%3E%3Cp%20class%3D%22Normal1%22%20style%3D%22line-height%3Anormal%22%3E%3Cb%3E%3Cspan%20style%3D%22font-size%3A12.0pt%3Bfont-family%3ACambria%3Bmso-fareast-font-family%3A%20Cambria%3Bmso-bidi-font-family%3ACambria%3Bcolor%3A%23272727%22%3EDear%3A%20Jim%20James%3C/span%3E%3C/b%3E%3C/p%3E%3Cp%3E%20%20%3Cbr%3E%3C/p%3E%3Cp%20class%3D%22Normal1%22%20style%3D%22line-height%3Anormal%22%3E%3Cb%3E%3Cspan%20style%3D%22font-size%3A12.0pt%3Bfont-family%3ACambria%3Bmso-fareast-font-family%3A%20Cambria%3Bmso-bidi-font-family%3ACambria%3Bcolor%3A%23272727%22%3ERe%3A%20_Test%20test%3C/span%3E%3C/b%3E%3C/p%3E%3Cp%3E%20%20%3Cbr%3E%3C/p%3E%3Cp%20class%3D%22Normal1%22%20style%3D%22line-height%3A200%25%22%3E%3Cb%3E%3Cspan%20style%3D%22font-size%3A12.0pt%3Bline-height%3A200%25%3Bfont-family%3ACambria%3Bmso-fareast-font-family%3A%20Cambria%3Bmso-bidi-font-family%3ACambria%3Bcolor%3A%23272727%22%3EThis%20letter%20is%20being%20sent%20to%20you%20because%20I%20would%20like%20to%20schedule%20an%20appointment.%26nbsp%3B%26nbsp%3B%20To%20date%20you%20have%20missed%20_______%20appointments%20and%20I%20have%20had%20no%20contact%20with%20you.%26nbsp%3B%20Please%20remember%20that%20attendance%20is%20a%20requirement%20to%20keeping%20your%20case%20open%20and%20active.%26nbsp%3B%20I%20am%20scheduling%20you%20for%20______________@_________%20.%26nbsp%3B%20Please%20call%20me%20%28857%20239-9120%29%26nbsp%3B%20or%20email%20me%20%28admin@practice.com%29%20at%20your%20earliest%20convenience%20to%20confirm%20this%20appointment.%20%26nbsp%3B%26nbsp%3BIf%20I%20do%20not%20hear%20from%20you%2C%20unfortunately%20your%20case%20will%20be%20closed.%26nbsp%3B%20%3C/span%3E%3C/b%3E%3C/p%3E%3Cp%3E%20%20%3Cbr%3E%3C/p%3E%3Cp%20class%3D%22Normal1%22%20style%3D%22line-height%3A200%25%22%3E%3Cb%3E%3Cspan%20style%3D%22font-size%3A12.0pt%3Bline-height%3A200%25%3Bfont-family%3ACambria%3Bmso-fareast-font-family%3A%20Cambria%3Bmso-bidi-font-family%3ACambria%3Bcolor%3A%23272727%22%3EI%20hope%20to%20hear%20from%20you.%3C/span%3E%3C/b%3E%3C/p%3E%3Cp%20class%3D%22Normal1%22%20style%3D%22line-height%3Anormal%22%3E%3Cb%3E%3Cspan%20style%3D%22font-size%3A12.0pt%3Bfont-family%3ACambria%3Bmso-fareast-font-family%3A%20Cambria%3Bmso-bidi-font-family%3ACambria%3Bcolor%3A%23272727%22%3EThank%20You%3C/span%3E%3C/b%3E%3C/p%3E%3Cp%3E%20%20%3Cbr%3E%3C/p%3E%3Cp%20class%3D%22Normal1%22%20style%3D%22line-height%3Anormal%22%3E%3Cb%3E%3Cspan%20style%3D%22font-size%3A12.0pt%3Bfont-family%3ACambria%3Bmso-fareast-font-family%3A%20Cambria%3Bmso-bidi-font-family%3ACambria%3Bcolor%3A%23272727%22%3EAnnie%20Admin%3C/span%3E%3C/b%3E%3C/p%3E%3Cbr%3E%3Cp%20class%3D%22Normal1%22%20style%3D%22line-height%3Anormal%22%3E%26nbsp%3B%3C/p%3E%3Cp%3E%20%20%3Cbr%3E%3C/p%3E%3Cp%20class%3D%22Normal1%22%20style%3D%22line-height%3Anormal%22%3E%3Cb%3E%3Ci%3E%3Cu%3E%3C/u%3E%3C/i%3E%3C/b%3E%3C/p%3E','2018-11-29 10:33:15','read','missed_appt','Missed Appointment','patient',2,'user','Annie Admin',1);
/*!40000 ALTER TABLE `letter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marital_status`
--

DROP TABLE IF EXISTS `marital_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marital_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marital_status`
--

LOCK TABLES `marital_status` WRITE;
/*!40000 ALTER TABLE `marital_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `marital_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_condition`
--

DROP TABLE IF EXISTS `medical_condition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medical_condition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_condition`
--

LOCK TABLES `medical_condition` WRITE;
/*!40000 ALTER TABLE `medical_condition` DISABLE KEYS */;
/*!40000 ALTER TABLE `medical_condition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medication`
--

DROP TABLE IF EXISTS `medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medication`
--

LOCK TABLES `medication` WRITE;
/*!40000 ALTER TABLE `medication` DISABLE KEYS */;
/*!40000 ALTER TABLE `medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `content` text DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `draft` tinyint(1) DEFAULT NULL,
  `sent_from` varchar(255) DEFAULT NULL,
  `message_type` int(11) DEFAULT NULL,
  `sender_client_type` varchar(255) DEFAULT NULL,
  `sender_full_name` varchar(255) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_message_recipient`
--

DROP TABLE IF EXISTS `message_message_recipient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_message_recipient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `message_id` int(11) DEFAULT NULL,
  `notify` tinyint(1) DEFAULT NULL,
  `read_by_recipient` tinyint(1) DEFAULT NULL,
  `recipient_client_type` varchar(255) NOT NULL,
  `recipient_id` int(11) DEFAULT NULL,
  `recipient_mode` varchar(255) DEFAULT NULL,
  `saved` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_message_recipient`
--

LOCK TABLES `message_message_recipient` WRITE;
/*!40000 ALTER TABLE `message_message_recipient` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_message_recipient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `network_marketing_source`
--

DROP TABLE IF EXISTS `network_marketing_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `network_marketing_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `network_marketing_source`
--

LOCK TABLES `network_marketing_source` WRITE;
/*!40000 ALTER TABLE `network_marketing_source` DISABLE KEYS */;
/*!40000 ALTER TABLE `network_marketing_source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obgyn_encounter_data`
--

DROP TABLE IF EXISTS `obgyn_encounter_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `obgyn_encounter_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `a` varchar(255) DEFAULT NULL,
  `age_first_period` varchar(255) DEFAULT NULL,
  `birth_control_status` varchar(255) DEFAULT NULL,
  `birth_control_type` varchar(255) DEFAULT NULL,
  `breastfeeding` varchar(255) DEFAULT NULL,
  `breastfeeding_months` varchar(255) DEFAULT NULL,
  `g` varchar(255) DEFAULT NULL,
  `history` varchar(255) DEFAULT NULL,
  `history_other` varchar(255) DEFAULT NULL,
  `l` varchar(255) DEFAULT NULL,
  `last_period` varchar(255) DEFAULT NULL,
  `p` varchar(255) DEFAULT NULL,
  `pap_smear_status` varchar(255) DEFAULT NULL,
  `preg_status` varchar(255) DEFAULT NULL,
  `t` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDBED132C4661880D` (`clinician_id`),
  CONSTRAINT `FKDBED132C4661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obgyn_encounter_data`
--

LOCK TABLES `obgyn_encounter_data` WRITE;
/*!40000 ALTER TABLE `obgyn_encounter_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `obgyn_encounter_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parent_cbc`
--

DROP TABLE IF EXISTS `parent_cbc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parent_cbc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `best` text DEFAULT NULL,
  `chore_1` varchar(255) DEFAULT NULL,
  `chore_2` varchar(255) DEFAULT NULL,
  `chore_3` varchar(255) DEFAULT NULL,
  `chore_ability_1` varchar(255) DEFAULT NULL,
  `chore_ability_2` varchar(255) DEFAULT NULL,
  `chore_ability_3` varchar(255) DEFAULT NULL,
  `concerns` text DEFAULT NULL,
  `ethnicity` varchar(255) DEFAULT NULL,
  `friend_count` varchar(255) DEFAULT NULL,
  `friend_freq` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `guardian_name` varchar(255) DEFAULT NULL,
  `guardian_rel` varchar(255) DEFAULT NULL,
  `hobby_1` varchar(255) DEFAULT NULL,
  `hobby_2` varchar(255) DEFAULT NULL,
  `hobby_3` varchar(255) DEFAULT NULL,
  `hobby_ability_1` varchar(255) DEFAULT NULL,
  `hobby_ability_2` varchar(255) DEFAULT NULL,
  `hobby_ability_3` varchar(255) DEFAULT NULL,
  `hobby_time_1` varchar(255) DEFAULT NULL,
  `hobby_time_2` varchar(255) DEFAULT NULL,
  `hobby_time_3` varchar(255) DEFAULT NULL,
  `no_chores` tinyint(1) DEFAULT NULL,
  `no_hobbies` tinyint(1) DEFAULT NULL,
  `no_orgs` tinyint(1) DEFAULT NULL,
  `no_school` tinyint(1) DEFAULT NULL,
  `no_school_reason` varchar(255) DEFAULT NULL,
  `no_siblings` tinyint(1) DEFAULT NULL,
  `no_sports` tinyint(1) DEFAULT NULL,
  `not_attending` tinyint(1) DEFAULT NULL,
  `org_1` varchar(255) DEFAULT NULL,
  `org_2` varchar(255) DEFAULT NULL,
  `org_3` varchar(255) DEFAULT NULL,
  `org_time_1` varchar(255) DEFAULT NULL,
  `org_time_2` varchar(255) DEFAULT NULL,
  `org_time_3` varchar(255) DEFAULT NULL,
  `parent_work_1` varchar(255) DEFAULT NULL,
  `parent_work_2` varchar(255) DEFAULT NULL,
  `repeat_grade` varchar(255) DEFAULT NULL,
  `repeat_grade_desc` varchar(255) DEFAULT NULL,
  `report` text DEFAULT NULL,
  `school_a` varchar(255) DEFAULT NULL,
  `school_b` varchar(255) DEFAULT NULL,
  `school_c` varchar(255) DEFAULT NULL,
  `school_d` varchar(255) DEFAULT NULL,
  `school_e` varchar(255) DEFAULT NULL,
  `school_f` varchar(255) DEFAULT NULL,
  `school_g` varchar(255) DEFAULT NULL,
  `school_name_e` varchar(255) DEFAULT NULL,
  `school_name_f` varchar(255) DEFAULT NULL,
  `school_name_g` varchar(255) DEFAULT NULL,
  `sibling_a` varchar(255) DEFAULT NULL,
  `sibling_b` varchar(255) DEFAULT NULL,
  `sibling_c` varchar(255) DEFAULT NULL,
  `sibling_d` varchar(255) DEFAULT NULL,
  `sped` varchar(255) DEFAULT NULL,
  `sped_desc` varchar(255) DEFAULT NULL,
  `sport_1` varchar(255) DEFAULT NULL,
  `sport_2` varchar(255) DEFAULT NULL,
  `sport_3` varchar(255) DEFAULT NULL,
  `sport_ability_1` varchar(255) DEFAULT NULL,
  `sport_ability_2` varchar(255) DEFAULT NULL,
  `sport_ability_3` varchar(255) DEFAULT NULL,
  `sport_time_1` varchar(255) DEFAULT NULL,
  `sport_time_2` varchar(255) DEFAULT NULL,
  `sport_time_3` varchar(255) DEFAULT NULL,
  `guardian_gender` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKF16F52CF4661880D` (`clinician_id`),
  KEY `FKF16F52CFC27E8026` (`guardian_gender`),
  CONSTRAINT `FKF16F52CF4661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKF16F52CFC27E8026` FOREIGN KEY (`guardian_gender`) REFERENCES `gender` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parent_cbc`
--

LOCK TABLES `parent_cbc` WRITE;
/*!40000 ALTER TABLE `parent_cbc` DISABLE KEYS */;
/*!40000 ALTER TABLE `parent_cbc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parent_meeting`
--

DROP TABLE IF EXISTS `parent_meeting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parent_meeting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `behavior` text DEFAULT NULL,
  `comments` text DEFAULT NULL,
  `concerns` text DEFAULT NULL,
  `goals` text DEFAULT NULL,
  `progress` text DEFAULT NULL,
  `strengths` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK474AAFA64661880D` (`clinician_id`),
  CONSTRAINT `FK474AAFA64661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parent_meeting`
--

LOCK TABLES `parent_meeting` WRITE;
/*!40000 ALTER TABLE `parent_meeting` DISABLE KEYS */;
/*!40000 ALTER TABLE `parent_meeting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `client_type` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `pager` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `password_created` tinyint(4) DEFAULT 0,
  `primary_phone` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `secondary_phone` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `additional_name` varchar(255) DEFAULT NULL,
  `assigned_clinician_id` int(11) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `customer_key` varchar(255) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `drivers_license` varchar(255) DEFAULT NULL,
  `employer` varchar(255) DEFAULT NULL,
  `employment_status` varchar(255) DEFAULT NULL,
  `govt_id` varchar(255) DEFAULT NULL,
  `group_number` varchar(255) DEFAULT NULL,
  `insurance_carrier` varchar(255) DEFAULT NULL,
  `insured_name` varchar(255) DEFAULT NULL,
  `intake_closed` tinyint(1) DEFAULT NULL,
  `member_number` varchar(255) DEFAULT NULL,
  `mrn` varchar(255) DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `opening_date` datetime DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `preferred_name` varchar(255) DEFAULT NULL,
  `prepayment_amount` varchar(255) DEFAULT NULL,
  `profile_image_path` varchar(255) DEFAULT NULL,
  `programs` varchar(255) DEFAULT NULL,
  `sales_lead_source` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `school_status` varchar(255) DEFAULT NULL,
  `street_address1` varchar(255) DEFAULT NULL,
  `street_address2` varchar(255) DEFAULT NULL,
  `recovery_code` int(11) DEFAULT NULL,
  `activate_code` int(11) DEFAULT NULL,
  `country` int(11) DEFAULT NULL,
  `ethnicity` int(11) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `marital_status` int(11) DEFAULT NULL,
  `race` int(11) DEFAULT NULL,
  `us_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mrn` (`mrn`),
  KEY `FKD0D3EB0585B829B6` (`country`),
  KEY `FKD0D3EB05AD360C91` (`activate_code`),
  KEY `FKD0D3EB055649B2AD` (`marital_status`),
  KEY `FKD0D3EB05C1AEB72F` (`recovery_code`),
  KEY `FKD0D3EB05F78C0908` (`ethnicity`),
  KEY `FKD0D3EB05C683F1B8` (`gender`),
  KEY `FKD0D3EB05CBC43758` (`race`),
  KEY `FKD0D3EB05B929E6ED` (`us_state`),
  CONSTRAINT `FKD0D3EB055649B2AD` FOREIGN KEY (`marital_status`) REFERENCES `marital_status` (`id`),
  CONSTRAINT `FKD0D3EB0585B829B6` FOREIGN KEY (`country`) REFERENCES `country` (`id`),
  CONSTRAINT `FKD0D3EB05AD360C91` FOREIGN KEY (`activate_code`) REFERENCES `recovery_code` (`id`),
  CONSTRAINT `FKD0D3EB05B929E6ED` FOREIGN KEY (`us_state`) REFERENCES `us_state` (`id`),
  CONSTRAINT `FKD0D3EB05C1AEB72F` FOREIGN KEY (`recovery_code`) REFERENCES `recovery_code` (`id`),
  CONSTRAINT `FKD0D3EB05C683F1B8` FOREIGN KEY (`gender`) REFERENCES `gender` (`id`),
  CONSTRAINT `FKD0D3EB05CBC43758` FOREIGN KEY (`race`) REFERENCES `race` (`id`),
  CONSTRAINT `FKD0D3EB05F78C0908` FOREIGN KEY (`ethnicity`) REFERENCES `ethnicity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_clinician`
--

DROP TABLE IF EXISTS `patient_clinician`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_clinician` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician` int(11) DEFAULT NULL,
  `patient` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK42721BB4692005EF` (`clinician`),
  KEY `FK42721BB4B5315094` (`patient`),
  CONSTRAINT `FK42721BB4692005EF` FOREIGN KEY (`clinician`) REFERENCES `user` (`id`),
  CONSTRAINT `FK42721BB4B5315094` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_clinician`
--

LOCK TABLES `patient_clinician` WRITE;
/*!40000 ALTER TABLE `patient_clinician` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_clinician` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_complaint`
--

DROP TABLE IF EXISTS `patient_complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_complaint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `complaint_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `complaint_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_complaint`
--

LOCK TABLES `patient_complaint` WRITE;
/*!40000 ALTER TABLE `patient_complaint` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_file`
--

DROP TABLE IF EXISTS `patient_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `client_type` varchar(255) DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_file`
--

LOCK TABLES `patient_file` WRITE;
/*!40000 ALTER TABLE `patient_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_food_allergy`
--

DROP TABLE IF EXISTS `patient_food_allergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_food_allergy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `notes` text DEFAULT NULL,
  `patient_form_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_food_allergy`
--

LOCK TABLES `patient_food_allergy` WRITE;
/*!40000 ALTER TABLE `patient_food_allergy` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_food_allergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_form`
--

DROP TABLE IF EXISTS `patient_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `intake` tinyint(1) DEFAULT NULL,
  `parent_patient_form_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `practice_form_id` int(11) DEFAULT NULL,
  `practice_form_instance_id` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_form`
--

LOCK TABLES `patient_form` WRITE;
/*!40000 ALTER TABLE `patient_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_guardian`
--

DROP TABLE IF EXISTS `patient_guardian`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_guardian` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `is_primary` tinyint(1) DEFAULT 0,
  `patient` int(11) NOT NULL,
  `relationship` varchar(255) DEFAULT NULL,
  `guardian` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4706958BA74149D8` (`guardian`),
  CONSTRAINT `FK4706958BA74149D8` FOREIGN KEY (`guardian`) REFERENCES `guardian` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_guardian`
--

LOCK TABLES `patient_guardian` WRITE;
/*!40000 ALTER TABLE `patient_guardian` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_guardian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_history_medication`
--

DROP TABLE IF EXISTS `patient_history_medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_history_medication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `dose` varchar(255) DEFAULT NULL,
  `frequency` varchar(255) DEFAULT NULL,
  `patient_medical_history_id` int(11) DEFAULT NULL,
  `medication` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_history_medication`
--

LOCK TABLES `patient_history_medication` WRITE;
/*!40000 ALTER TABLE `patient_history_medication` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_history_medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_image`
--

DROP TABLE IF EXISTS `patient_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_image`
--

LOCK TABLES `patient_image` WRITE;
/*!40000 ALTER TABLE `patient_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_intake`
--

DROP TABLE IF EXISTS `patient_intake`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_intake` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `portal_invite_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_intake`
--

LOCK TABLES `patient_intake` WRITE;
/*!40000 ALTER TABLE `patient_intake` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_intake` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_lab`
--

DROP TABLE IF EXISTS `patient_lab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_lab` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `date_due` datetime DEFAULT NULL,
  `date_ordered` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_lab`
--

LOCK TABLES `patient_lab` WRITE;
/*!40000 ALTER TABLE `patient_lab` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_lab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_med_allergy`
--

DROP TABLE IF EXISTS `patient_med_allergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_med_allergy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `notes` text DEFAULT NULL,
  `patient_form_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_med_allergy`
--

LOCK TABLES `patient_med_allergy` WRITE;
/*!40000 ALTER TABLE `patient_med_allergy` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_med_allergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_medical_history`
--

DROP TABLE IF EXISTS `patient_medical_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_medical_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `allerg_drug` varchar(255) DEFAULT NULL,
  `allerg_evn` varchar(255) DEFAULT NULL,
  `allerg_food` varchar(255) DEFAULT NULL,
  `current_drugs` varchar(255) DEFAULT NULL,
  `etoh_units_week` float DEFAULT NULL,
  `fam_hist` varchar(255) DEFAULT NULL,
  `fam_hist_notes` varchar(255) DEFAULT NULL,
  `fam_hist_other` varchar(255) DEFAULT NULL,
  `past_sm` varchar(255) DEFAULT NULL,
  `smoke_pks_day` float DEFAULT NULL,
  `smoke_years_quit` float DEFAULT NULL,
  `subst` varchar(255) DEFAULT NULL,
  `vacc` varchar(255) DEFAULT NULL,
  `vacc_notes` varchar(255) DEFAULT NULL,
  `years_smoked` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK28FC1BAC4661880D` (`clinician_id`),
  CONSTRAINT `FK28FC1BAC4661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_medical_history`
--

LOCK TABLES `patient_medical_history` WRITE;
/*!40000 ALTER TABLE `patient_medical_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_medical_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_medication`
--

DROP TABLE IF EXISTS `patient_medication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_medication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `med_cond` varchar(255) DEFAULT NULL,
  `dose` varchar(255) DEFAULT NULL,
  `medication` varchar(255) DEFAULT NULL,
  `patient_form_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_medication`
--

LOCK TABLES `patient_medication` WRITE;
/*!40000 ALTER TABLE `patient_medication` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_medication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_program`
--

DROP TABLE IF EXISTS `patient_program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `program` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7F2DDB4AEEA7B792` (`program`),
  CONSTRAINT `FK7F2DDB4AEEA7B792` FOREIGN KEY (`program`) REFERENCES `program` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_program`
--

LOCK TABLES `patient_program` WRITE;
/*!40000 ALTER TABLE `patient_program` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_supplement`
--

DROP TABLE IF EXISTS `patient_supplement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_supplement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `dose` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `supp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_supplement`
--

LOCK TABLES `patient_supplement` WRITE;
/*!40000 ALTER TABLE `patient_supplement` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_supplement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `practice_form`
--

DROP TABLE IF EXISTS `practice_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `practice_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `form_type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  `loader` varchar(255) DEFAULT NULL,
  `template` varchar(255) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `listloader` varchar(255) DEFAULT NULL,
  `reopener` varchar(255) DEFAULT NULL,
  `is_single` tinyint(1) DEFAULT NULL,
  `print_template` varchar(255) DEFAULT NULL,
  `print_loader` varchar(255) DEFAULT NULL,
  `print_renderer` varchar(255) DEFAULT NULL,
  `closer` varchar(255) DEFAULT NULL,
  `validator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `practice_form`
--

LOCK TABLES `practice_form` WRITE;
/*!40000 ALTER TABLE `practice_form` DISABLE KEYS */;
INSERT INTO `practice_form` VALUES (1,'2015-10-18 00:47:44','2018-01-17 21:47:51',NULL,'patient_intake','client_contact',1,'app_loadClientContactForm','form/gp/client_contact_form','com.wdeanmedical.gp.entity.form.ClientContact','Client Contact',NULL,NULL,NULL,'form/gp/client_contact_form','app_loadClientContactForm',NULL,NULL,'app_pm_clientContactFormValidator'),(2,'2015-10-18 00:54:51','2018-01-17 21:47:51',NULL,'patient_intake','client_rights',2,'app_loadClientRightsForm','form/gp/client_rights_form','com.wdeanmedical.gp.entity.form.ClientRights','Client Rights',NULL,NULL,NULL,'print/gp/client_rights_print','app_loadPrintClientRightsForm','app_renderPrintClientRightsForm',NULL,'app_pm_clientRightsFormValidator'),(3,'2015-10-18 00:55:10','2018-01-17 21:47:51',NULL,'patient_intake','consent',3,'app_loadConsentForm','form/gp/consent_form','com.wdeanmedical.gp.entity.form.Consent','Consent',NULL,NULL,NULL,'print/gp/consent_print','app_loadPrintConsentForm','app_renderPrintConsentForm',NULL,'app_pm_consentFormValidator'),(4,'2015-10-18 00:55:33','2018-01-17 21:47:51',NULL,'patient_intake','texting_waiver',4,'app_loadTextingWaiverForm','form/gp/texting_waiver_form','com.wdeanmedical.gp.entity.form.TextingWaiver','Texting Waiver',NULL,NULL,NULL,'print/gp/texting_waiver_print','app_loadPrintTextingWaiverForm','app_renderPrintTextingWaiverForm',NULL,'app_pm_textingWaiverFormValidator'),(5,'2016-09-27 20:59:00','2018-01-17 21:47:51',NULL,'patient_intake','release',5,'app_loadReleaseForm','form/gp/release_form','com.wdeanmedical.gp.entity.form.Release','Release',NULL,NULL,NULL,'print/gp/release_print','app_loadPrintReleaseForm','app_renderPrintReleaseForm',NULL,'app_pm_releaseFormValidator'),(6,'2016-09-27 21:01:40','2018-01-17 21:47:51',NULL,'patient_intake','emergency_info',6,'app_loadEmergencyInfoForm','form/gp/emergency_info_form','com.wdeanmedical.gp.entity.form.EmergencyInfo','Emergency Info',NULL,NULL,NULL,'form/gp/emergency_info_form','app_loadEmergencyInfoForm',NULL,NULL,'app_pm_emergencyInfoFormValidator'),(7,'2016-09-27 21:02:54','2018-01-17 21:47:51',NULL,'patient_intake','self_rating',7,'app_loadSelfRatingForm','form/gp/self_rating_form','com.wdeanmedical.gp.entity.form.SelfRating','Self Rating',NULL,NULL,NULL,'form/gp/self_rating_form','app_loadSelfRatingForm',NULL,NULL,''),(25,'2017-03-06 23:27:22',NULL,NULL,'built_in','encounter',24,'app_loadEncounterForm','form/gp/encounter_form','com.wdeanmedical.gp.entity.form.Encounter','Patient Encounter',NULL,NULL,1,'print/gp/encounter/all_print','app_loadPrintEncounterForm','','app_closeEncounterForm',NULL),(26,'2017-03-06 23:27:22',NULL,NULL,'built_in','medical_history',24,'app_loadEncounterSubForm','form/gp/encounter/medical_history','com.wdeanmedical.gp.entity.form.MedicalHistory','Medical History',NULL,NULL,1,'print/gp/encounter/medical_hiistory','app_loadPrintEncounterSubForm','','',NULL),(27,'2017-03-06 23:27:22',NULL,NULL,'built_in','soap_note',24,'app_loadEncounterSubForm','form/gp/encounter/soap_note','com.wdeanmedical.gp.entity.form.SoapNote','Soap Note',NULL,NULL,1,'print/gp/encounter/soap_note','app_loadPrintEncounterSubForm','','',NULL),(28,'2017-03-06 23:27:22',NULL,NULL,'built_in','family_history',24,'app_loadEncounterSubForm','form/gp/encounter/family_history','com.wdeanmedical.gp.entity.form.FamilyHistory','Social & Family History',NULL,NULL,1,'print/gp/encounter/family_history','app_loadPrintEncounterSubForm','','',NULL),(29,'2017-03-06 23:27:22',NULL,NULL,'built_in','exam',24,'app_loadEncounterSubForm','form/gp/encounter/exam','com.wdeanmedical.gp.entity.form.Exam','Exam',NULL,NULL,1,'print/gp/encounter/exam','app_loadPrintEncounterSubForm','','',NULL),(30,'2017-03-06 23:27:22',NULL,NULL,'built_in','chief_complaint',24,'app_loadEncounterSubForm','form/gp/encounter/chief_complaint','com.wdeanmedical.gp.entity.form.ChiefComplaint','Chief Complaint',NULL,NULL,1,'print/gp/encounter/chief_complaint','app_loadPrintEncounterSubForm','','',NULL),(31,'2017-03-06 23:27:22',NULL,NULL,'built_in','obgyn',24,'app_loadEncounterSubForm','form/gp/encounter/obgyn','com.wdeanmedical.gp.entity.form.Obgyn','OB/GYN',NULL,NULL,1,'print/gp/encounter/obgyn','app_loadPrintEncounterSubForm','','',NULL),(32,'2017-03-06 23:27:22',NULL,NULL,'built_in','vital_sign',24,'app_loadEncounterSubForm','form/gp/encounter/vital_sign','com.wdeanmedical.gp.entity.form.VitalSign','Vital Sign',NULL,NULL,1,'print/gp/encounter/vital_sign','app_loadPrintEncounterSubForm','','',NULL);
/*!40000 ALTER TABLE `practice_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presenting_problem`
--

DROP TABLE IF EXISTS `presenting_problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `presenting_problem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `info` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `problem` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presenting_problem`
--

LOCK TABLES `presenting_problem` WRITE;
/*!40000 ALTER TABLE `presenting_problem` DISABLE KEYS */;
/*!40000 ALTER TABLE `presenting_problem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problem_analysis`
--

DROP TABLE IF EXISTS `problem_analysis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `problem_analysis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `constitutional` text DEFAULT NULL,
  `distortion` text DEFAULT NULL,
  `fbabip_id` int(11) DEFAULT NULL,
  `modeling` text DEFAULT NULL,
  `reactivity` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem_analysis`
--

LOCK TABLES `problem_analysis` WRITE;
/*!40000 ALTER TABLE `problem_analysis` DISABLE KEYS */;
/*!40000 ALTER TABLE `problem_analysis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program`
--

LOCK TABLES `program` WRITE;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
/*!40000 ALTER TABLE `program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `race`
--

DROP TABLE IF EXISTS `race`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `race` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `race`
--

LOCK TABLES `race` WRITE;
/*!40000 ALTER TABLE `race` DISABLE KEYS */;
INSERT INTO `race` VALUES (1,NULL,'2016-02-09 15:51:27',NULL,'White'),(2,NULL,'2016-02-04 02:11:08',NULL,'Black or African American'),(3,NULL,'2016-02-09 16:29:02',NULL,'Asian'),(4,NULL,NULL,NULL,'American Indian/ Alaskan Native'),(5,NULL,NULL,NULL,'Native Hawaiian or Other Pacific Islander'),(6,NULL,NULL,NULL,'More than one race'),(7,NULL,'2015-05-30 14:11:18',NULL,'Unknown or Not Reported');
/*!40000 ALTER TABLE `race` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recovery_code`
--

DROP TABLE IF EXISTS `recovery_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recovery_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `client_type` varchar(255) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `expires_at` datetime NOT NULL,
  `recovered` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recovery_code`
--

LOCK TABLES `recovery_code` WRITE;
/*!40000 ALTER TABLE `recovery_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `recovery_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `referral_source_type`
--

DROP TABLE IF EXISTS `referral_source_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `referral_source_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referral_source_type`
--

LOCK TABLES `referral_source_type` WRITE;
/*!40000 ALTER TABLE `referral_source_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `referral_source_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `release_form`
--

DROP TABLE IF EXISTS `release_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `release_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `agency_city` varchar(255) DEFAULT NULL,
  `agency_contact` varchar(255) DEFAULT NULL,
  `agency_fax` varchar(255) DEFAULT NULL,
  `agency_name` varchar(255) DEFAULT NULL,
  `agency_phone` varchar(255) DEFAULT NULL,
  `agency_postal_code` varchar(255) DEFAULT NULL,
  `agency_street_address` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `exp_date` datetime DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `info_type` varchar(255) DEFAULT NULL,
  `info_type_other` varchar(255) DEFAULT NULL,
  `initials1` varchar(255) DEFAULT NULL,
  `initials2` varchar(255) DEFAULT NULL,
  `initials3` varchar(255) DEFAULT NULL,
  `initials4` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `purpose_other` varchar(255) DEFAULT NULL,
  `readDoc` tinyint(1) DEFAULT NULL,
  `sig_date` datetime DEFAULT NULL,
  `sig_patient` varchar(255) DEFAULT NULL,
  `sig_rel` varchar(255) DEFAULT NULL,
  `sig_rep` varchar(255) DEFAULT NULL,
  `sig_rep_date` datetime DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `agency_us_state` int(11) DEFAULT NULL,
  `us_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8AD489FC1E1CB1E7` (`agency_us_state`),
  KEY `FK8AD489FC4661880D` (`clinician_id`),
  KEY `FK8AD489FCB929E6ED` (`us_state`),
  CONSTRAINT `FK8AD489FC1E1CB1E7` FOREIGN KEY (`agency_us_state`) REFERENCES `us_state` (`id`),
  CONSTRAINT `FK8AD489FC4661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK8AD489FCB929E6ED` FOREIGN KEY (`us_state`) REFERENCES `us_state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `release_form`
--

LOCK TABLES `release_form` WRITE;
/*!40000 ALTER TABLE `release_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `release_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `sort_order` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `risk_assessment_form`
--

DROP TABLE IF EXISTS `risk_assessment_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `risk_assessment_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `client_action` text DEFAULT NULL,
  `clinical_impression` varchar(255) DEFAULT NULL,
  `clinician_sig` varchar(255) DEFAULT NULL,
  `clinician_sig_date` datetime DEFAULT NULL,
  `date_assessed` datetime DEFAULT NULL,
  `further_action` varchar(255) DEFAULT NULL,
  `incident_date` datetime DEFAULT NULL,
  `sig` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2E52BAB14661880D` (`clinician_id`),
  CONSTRAINT `FK2E52BAB14661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `risk_assessment_form`
--

LOCK TABLES `risk_assessment_form` WRITE;
/*!40000 ALTER TABLE `risk_assessment_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `risk_assessment_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,'2018-11-26 18:38:37',NULL,'Admin'),(2,NULL,'2017-03-06 11:59:16',NULL,'User'),(3,NULL,'2017-03-06 11:59:16',NULL,'Front Office'),(4,NULL,'2017-02-15 08:29:05',NULL,'General'),(5,NULL,'2017-03-06 11:59:16',NULL,'Consultant');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_lead`
--

DROP TABLE IF EXISTS `sales_lead`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_lead` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `alt_city` varchar(255) DEFAULT NULL,
  `alt_postal_code` varchar(255) DEFAULT NULL,
  `alt_street_address1` varchar(255) DEFAULT NULL,
  `best_time_to_contact` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `facility_id` int(11) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `occupation` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `primary_phone` varchar(255) DEFAULT NULL,
  `secondary_phone` varchar(255) DEFAULT NULL,
  `street_address1` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `address_type` int(11) DEFAULT NULL,
  `age_range` int(11) DEFAULT NULL,
  `alt_address_type` int(11) DEFAULT NULL,
  `alt_us_state` int(11) DEFAULT NULL,
  `call_status` int(11) DEFAULT NULL,
  `email_status` int(11) DEFAULT NULL,
  `gender` int(11) NOT NULL,
  `sales_lead_goal` int(11) DEFAULT NULL,
  `mktg_source` int(11) DEFAULT NULL,
  `source` int(11) DEFAULT NULL,
  `source_type` int(11) DEFAULT NULL,
  `stage` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `us_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK666AD6AFA37EAF7D` (`address_type`),
  KEY `FK666AD6AFC683F1B8` (`gender`),
  KEY `FK666AD6AFD9256992` (`mktg_source`),
  KEY `FK666AD6AFC0834186` (`source_type`),
  KEY `FK666AD6AFEB9AFD68` (`source`),
  KEY `FK666AD6AF42E19EB1` (`email_status`),
  KEY `FK666AD6AFB929E6ED` (`us_state`),
  KEY `FK666AD6AFDF735A53` (`alt_address_type`),
  KEY `FK666AD6AF778F5A2D` (`age_range`),
  KEY `FK666AD6AF2AA16CC3` (`alt_us_state`),
  KEY `FK666AD6AF55644915` (`call_status`),
  KEY `FK666AD6AF30E7EA8A` (`stage`),
  KEY `FK666AD6AF6A4E8768` (`sales_lead_goal`),
  KEY `FK666AD6AFEC15CD16` (`status`),
  CONSTRAINT `FK666AD6AF2AA16CC3` FOREIGN KEY (`alt_us_state`) REFERENCES `us_state` (`id`),
  CONSTRAINT `FK666AD6AF30E7EA8A` FOREIGN KEY (`stage`) REFERENCES `sales_lead_stage` (`id`),
  CONSTRAINT `FK666AD6AF42E19EB1` FOREIGN KEY (`email_status`) REFERENCES `sales_lead_email_status` (`id`),
  CONSTRAINT `FK666AD6AF55644915` FOREIGN KEY (`call_status`) REFERENCES `sales_lead_call_status` (`id`),
  CONSTRAINT `FK666AD6AF6A4E8768` FOREIGN KEY (`sales_lead_goal`) REFERENCES `sales_lead_goal` (`id`),
  CONSTRAINT `FK666AD6AF778F5A2D` FOREIGN KEY (`age_range`) REFERENCES `sales_lead_age_range` (`id`),
  CONSTRAINT `FK666AD6AFA37EAF7D` FOREIGN KEY (`address_type`) REFERENCES `address_type` (`id`),
  CONSTRAINT `FK666AD6AFB929E6ED` FOREIGN KEY (`us_state`) REFERENCES `us_state` (`id`),
  CONSTRAINT `FK666AD6AFC0834186` FOREIGN KEY (`source_type`) REFERENCES `referral_source_type` (`id`),
  CONSTRAINT `FK666AD6AFC683F1B8` FOREIGN KEY (`gender`) REFERENCES `gender` (`id`),
  CONSTRAINT `FK666AD6AFD9256992` FOREIGN KEY (`mktg_source`) REFERENCES `network_marketing_source` (`id`),
  CONSTRAINT `FK666AD6AFDF735A53` FOREIGN KEY (`alt_address_type`) REFERENCES `address_type` (`id`),
  CONSTRAINT `FK666AD6AFEB9AFD68` FOREIGN KEY (`source`) REFERENCES `sales_lead_source` (`id`),
  CONSTRAINT `FK666AD6AFEC15CD16` FOREIGN KEY (`status`) REFERENCES `sales_lead_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_lead`
--

LOCK TABLES `sales_lead` WRITE;
/*!40000 ALTER TABLE `sales_lead` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_lead` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_lead_action`
--

DROP TABLE IF EXISTS `sales_lead_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_lead_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `notes` text DEFAULT NULL,
  `sales_lead_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_lead_action`
--

LOCK TABLES `sales_lead_action` WRITE;
/*!40000 ALTER TABLE `sales_lead_action` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_lead_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_lead_action_user`
--

DROP TABLE IF EXISTS `sales_lead_action_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_lead_action_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `sales_lead_action_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_lead_action_user`
--

LOCK TABLES `sales_lead_action_user` WRITE;
/*!40000 ALTER TABLE `sales_lead_action_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_lead_action_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_lead_age_range`
--

DROP TABLE IF EXISTS `sales_lead_age_range`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_lead_age_range` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_lead_age_range`
--

LOCK TABLES `sales_lead_age_range` WRITE;
/*!40000 ALTER TABLE `sales_lead_age_range` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_lead_age_range` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_lead_call_status`
--

DROP TABLE IF EXISTS `sales_lead_call_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_lead_call_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_lead_call_status`
--

LOCK TABLES `sales_lead_call_status` WRITE;
/*!40000 ALTER TABLE `sales_lead_call_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_lead_call_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_lead_email_status`
--

DROP TABLE IF EXISTS `sales_lead_email_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_lead_email_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_lead_email_status`
--

LOCK TABLES `sales_lead_email_status` WRITE;
/*!40000 ALTER TABLE `sales_lead_email_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_lead_email_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_lead_goal`
--

DROP TABLE IF EXISTS `sales_lead_goal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_lead_goal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_lead_goal`
--

LOCK TABLES `sales_lead_goal` WRITE;
/*!40000 ALTER TABLE `sales_lead_goal` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_lead_goal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_lead_source`
--

DROP TABLE IF EXISTS `sales_lead_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_lead_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_lead_source`
--

LOCK TABLES `sales_lead_source` WRITE;
/*!40000 ALTER TABLE `sales_lead_source` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_lead_source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_lead_stage`
--

DROP TABLE IF EXISTS `sales_lead_stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_lead_stage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_lead_stage`
--

LOCK TABLES `sales_lead_stage` WRITE;
/*!40000 ALTER TABLE `sales_lead_stage` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_lead_stage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_lead_status`
--

DROP TABLE IF EXISTS `sales_lead_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_lead_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_lead_status`
--

LOCK TABLES `sales_lead_status` WRITE;
/*!40000 ALTER TABLE `sales_lead_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_lead_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_lead_task`
--

DROP TABLE IF EXISTS `sales_lead_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_lead_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `due_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `notes` text DEFAULT NULL,
  `sales_lead_id` int(11) DEFAULT NULL,
  `time_specified` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_lead_task`
--

LOCK TABLES `sales_lead_task` WRITE;
/*!40000 ALTER TABLE `sales_lead_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_lead_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_lead_task_user`
--

DROP TABLE IF EXISTS `sales_lead_task_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_lead_task_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `sales_lead_task_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_lead_task_user`
--

LOCK TABLES `sales_lead_task_user` WRITE;
/*!40000 ALTER TABLE `sales_lead_task_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_lead_task_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_lead_user`
--

DROP TABLE IF EXISTS `sales_lead_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales_lead_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `sales_lead_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_lead_user`
--

LOCK TABLES `sales_lead_user` WRITE;
/*!40000 ALTER TABLE `sales_lead_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `sales_lead_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `self_rating_form`
--

DROP TABLE IF EXISTS `self_rating_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `self_rating_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `freq` varchar(255) DEFAULT NULL,
  `rel` varchar(255) DEFAULT NULL,
  `report` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCEE29B34661880D` (`clinician_id`),
  CONSTRAINT `FKCEE29B34661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `self_rating_form`
--

LOCK TABLES `self_rating_form` WRITE;
/*!40000 ALTER TABLE `self_rating_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `self_rating_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `soap_note`
--

DROP TABLE IF EXISTS `soap_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `soap_note` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `assessment` text DEFAULT NULL,
  `objective` text DEFAULT NULL,
  `plan` text DEFAULT NULL,
  `subjective` text DEFAULT NULL,
  `encounter` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7529F4265D457337` (`encounter`),
  KEY `FK7529F4264661880D` (`clinician_id`),
  CONSTRAINT `FK7529F4264661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK7529F4265D457337` FOREIGN KEY (`encounter`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `soap_note`
--

LOCK TABLES `soap_note` WRITE;
/*!40000 ALTER TABLE `soap_note` DISABLE KEYS */;
/*!40000 ALTER TABLE `soap_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stripe_pending_transaction`
--

DROP TABLE IF EXISTS `stripe_pending_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stripe_pending_transaction` (
  `type` varchar(31) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `errorMessage` varchar(255) DEFAULT NULL,
  `patient` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `patient` (`patient`),
  KEY `FKB116D544B5315094` (`patient`),
  CONSTRAINT `FKB116D544B5315094` FOREIGN KEY (`patient`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stripe_pending_transaction`
--

LOCK TABLES `stripe_pending_transaction` WRITE;
/*!40000 ALTER TABLE `stripe_pending_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `stripe_pending_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tap_registration_form`
--

DROP TABLE IF EXISTS `tap_registration_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tap_registration_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `allergy_1` varchar(255) DEFAULT NULL,
  `allergy_2` varchar(255) DEFAULT NULL,
  `allergy_3` varchar(255) DEFAULT NULL,
  `allergy_info` varchar(255) DEFAULT NULL,
  `allergy_level_1` varchar(255) DEFAULT NULL,
  `allergy_level_2` varchar(255) DEFAULT NULL,
  `allergy_level_3` varchar(255) DEFAULT NULL,
  `arriving_by` varchar(255) DEFAULT NULL,
  `attendence_sig` varchar(255) DEFAULT NULL,
  `attendence_sig_date` datetime DEFAULT NULL,
  `child_sig` varchar(255) DEFAULT NULL,
  `child_sig_date` datetime DEFAULT NULL,
  `days` varchar(255) DEFAULT NULL,
  `discipline_sig` varchar(255) DEFAULT NULL,
  `discipline_sig_date` datetime DEFAULT NULL,
  `dose_1` varchar(255) DEFAULT NULL,
  `dose_2` varchar(255) DEFAULT NULL,
  `dose_3` varchar(255) DEFAULT NULL,
  `emerg_name_1` varchar(255) DEFAULT NULL,
  `emerg_name_2` varchar(255) DEFAULT NULL,
  `emerg_phone_1` varchar(255) DEFAULT NULL,
  `emerg_phone_2` varchar(255) DEFAULT NULL,
  `emerg_rel_1` varchar(255) DEFAULT NULL,
  `emerg_rel_2` varchar(255) DEFAULT NULL,
  `food` varchar(255) DEFAULT NULL,
  `freq_1` varchar(255) DEFAULT NULL,
  `freq_2` varchar(255) DEFAULT NULL,
  `freq_3` varchar(255) DEFAULT NULL,
  `goals` varchar(255) DEFAULT NULL,
  `gps_transport` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `group_number` varchar(255) DEFAULT NULL,
  `guardian_address` varchar(255) DEFAULT NULL,
  `guardian_cell_phone` varchar(255) DEFAULT NULL,
  `guardian_city` varchar(255) DEFAULT NULL,
  `guardian_email` varchar(255) DEFAULT NULL,
  `guardian_gender` tinyblob DEFAULT NULL,
  `guardian_home_phone` varchar(255) DEFAULT NULL,
  `guardian_name` varchar(255) DEFAULT NULL,
  `guardian_postal_code` varchar(255) DEFAULT NULL,
  `guardian_sig` varchar(255) DEFAULT NULL,
  `guardian_sig_date` datetime DEFAULT NULL,
  `guardian_us_state` tinyblob DEFAULT NULL,
  `hospital` varchar(255) DEFAULT NULL,
  `iep_info` varchar(255) DEFAULT NULL,
  `insurance` varchar(255) DEFAULT NULL,
  `insured` varchar(255) DEFAULT NULL,
  `intake_coord_sig` varchar(255) DEFAULT NULL,
  `intake_coord_sig_date` datetime DEFAULT NULL,
  `interests` varchar(255) DEFAULT NULL,
  `lang` varchar(255) DEFAULT NULL,
  `lang_other` varchar(255) DEFAULT NULL,
  `leaving_by` varchar(255) DEFAULT NULL,
  `lives_with` varchar(255) DEFAULT NULL,
  `lives_with_other` varchar(255) DEFAULT NULL,
  `med_1` varchar(255) DEFAULT NULL,
  `med_2` varchar(255) DEFAULT NULL,
  `med_3` varchar(255) DEFAULT NULL,
  `med_admin_sig` varchar(255) DEFAULT NULL,
  `med_admin_sig_date` datetime DEFAULT NULL,
  `med_info` varchar(255) DEFAULT NULL,
  `med_rel_sig` varchar(255) DEFAULT NULL,
  `med_rel_sig_date` datetime DEFAULT NULL,
  `pcp_address` varchar(255) DEFAULT NULL,
  `pcp_name` varchar(255) DEFAULT NULL,
  `pcp_phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `photo_sig` varchar(255) DEFAULT NULL,
  `photo_sig_date` datetime DEFAULT NULL,
  `pick_up_name_1` varchar(255) DEFAULT NULL,
  `pick_up_name_2` varchar(255) DEFAULT NULL,
  `pick_up_phone_1` varchar(255) DEFAULT NULL,
  `pick_up_phone_2` varchar(255) DEFAULT NULL,
  `pick_up_rel_1` varchar(255) DEFAULT NULL,
  `pick_up_rel_2` varchar(255) DEFAULT NULL,
  `policy_number` varchar(255) DEFAULT NULL,
  `prescriber_1` varchar(255) DEFAULT NULL,
  `prescriber_2` varchar(255) DEFAULT NULL,
  `prescriber_3` varchar(255) DEFAULT NULL,
  `prev_program` varchar(255) DEFAULT NULL,
  `prev_program_assess` varchar(255) DEFAULT NULL,
  `private_ride` varchar(255) DEFAULT NULL,
  `rep_name` varchar(255) DEFAULT NULL,
  `rep_rel` varchar(255) DEFAULT NULL,
  `reporting_sig` varchar(255) DEFAULT NULL,
  `reporting_sig_date` datetime DEFAULT NULL,
  `sig` tinyint(1) DEFAULT NULL,
  `ssn` varchar(255) DEFAULT NULL,
  `violence` varchar(255) DEFAULT NULL,
  `violence_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK16178EEE4661880D` (`clinician_id`),
  CONSTRAINT `FK16178EEE4661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tap_registration_form`
--

LOCK TABLES `tap_registration_form` WRITE;
/*!40000 ALTER TABLE `tap_registration_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `tap_registration_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `target_behavior`
--

DROP TABLE IF EXISTS `target_behavior`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `target_behavior` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `behavior` text DEFAULT NULL,
  `description` text DEFAULT NULL,
  `duration` text DEFAULT NULL,
  `fbabip_id` int(11) DEFAULT NULL,
  `freq` text DEFAULT NULL,
  `intensity` text DEFAULT NULL,
  `previous_intervention` text DEFAULT NULL,
  `settings` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `target_behavior`
--

LOCK TABLES `target_behavior` WRITE;
/*!40000 ALTER TABLE `target_behavior` DISABLE KEYS */;
/*!40000 ALTER TABLE `target_behavior` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_cbc`
--

DROP TABLE IF EXISTS `teacher_cbc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher_cbc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `academic_1` varchar(255) DEFAULT NULL,
  `academic_2` varchar(255) DEFAULT NULL,
  `academic_3` varchar(255) DEFAULT NULL,
  `academic_4` varchar(255) DEFAULT NULL,
  `academin_5` varchar(255) DEFAULT NULL,
  `academic_6` varchar(255) DEFAULT NULL,
  `academic_subject_1` text DEFAULT NULL,
  `academic_subject_2` text DEFAULT NULL,
  `academic_subject_3` text DEFAULT NULL,
  `academic_subject_4` text DEFAULT NULL,
  `academic_subject_5` text DEFAULT NULL,
  `academic_subject_6` text DEFAULT NULL,
  `best` text DEFAULT NULL,
  `comments` text DEFAULT NULL,
  `compare_1` varchar(255) DEFAULT NULL,
  `compare_2` varchar(255) DEFAULT NULL,
  `compare_3` varchar(255) DEFAULT NULL,
  `compare_4` varchar(255) DEFAULT NULL,
  `concerns` text DEFAULT NULL,
  `ethnicity` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `illness` varchar(255) DEFAULT NULL,
  `illness_desc` text DEFAULT NULL,
  `iq_date_1` datetime DEFAULT NULL,
  `iq_date_2` datetime DEFAULT NULL,
  `iq_date_3` datetime DEFAULT NULL,
  `iq_date_4` datetime DEFAULT NULL,
  `iq_date_5` datetime DEFAULT NULL,
  `iq_name_1` varchar(255) DEFAULT NULL,
  `iq_name_2` varchar(255) DEFAULT NULL,
  `iq_name_3` varchar(255) DEFAULT NULL,
  `i1_name_4` varchar(255) DEFAULT NULL,
  `iq_name_5` varchar(255) DEFAULT NULL,
  `ig_score_1` varchar(255) DEFAULT NULL,
  `iq_score_2` varchar(255) DEFAULT NULL,
  `iq_score_3` varchar(255) DEFAULT NULL,
  `iq_score_4` varchar(255) DEFAULT NULL,
  `iq_score_5` varchar(255) DEFAULT NULL,
  `know_well` varchar(255) DEFAULT NULL,
  `months_known` varchar(255) DEFAULT NULL,
  `parent_work_1` varchar(255) DEFAULT NULL,
  `parent_work_2` varchar(255) DEFAULT NULL,
  `referred` varchar(255) DEFAULT NULL,
  `repeat_info` varchar(255) DEFAULT NULL,
  `repeated` varchar(255) DEFAULT NULL,
  `report` text DEFAULT NULL,
  `school_address` varchar(255) DEFAULT NULL,
  `school_name` varchar(255) DEFAULT NULL,
  `service_info` varchar(255) DEFAULT NULL,
  `service_type` varchar(255) DEFAULT NULL,
  `teacher_other_role` varchar(255) DEFAULT NULL,
  `teacher_role` varchar(255) DEFAULT NULL,
  `test_date_1` datetime DEFAULT NULL,
  `test_date_2` datetime DEFAULT NULL,
  `test_date_3` datetime DEFAULT NULL,
  `test_date_4` datetime DEFAULT NULL,
  `test_date_5` datetime DEFAULT NULL,
  `test_grade_1` varchar(255) DEFAULT NULL,
  `test_grade_2` varchar(255) DEFAULT NULL,
  `test_grade_3` varchar(255) DEFAULT NULL,
  `test_grade_4` varchar(255) DEFAULT NULL,
  `test_grade_5` varchar(255) DEFAULT NULL,
  `test_name_1` varchar(255) DEFAULT NULL,
  `test_name_2` varchar(255) DEFAULT NULL,
  `test_name_3` varchar(255) DEFAULT NULL,
  `test_name_4` varchar(255) DEFAULT NULL,
  `test_name_5` varchar(255) DEFAULT NULL,
  `test_subject_1` varchar(255) DEFAULT NULL,
  `test_subject_2` varchar(255) DEFAULT NULL,
  `test_subject_3` varchar(255) DEFAULT NULL,
  `test_subject_4` varchar(255) DEFAULT NULL,
  `test_subject_5` varchar(255) DEFAULT NULL,
  `time_in_class` varchar(255) DEFAULT NULL,
  `guardian_gender` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK62F1BA074661880D` (`clinician_id`),
  KEY `FK62F1BA07C27E8026` (`guardian_gender`),
  CONSTRAINT `FK62F1BA074661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK62F1BA07C27E8026` FOREIGN KEY (`guardian_gender`) REFERENCES `gender` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_cbc`
--

LOCK TABLES `teacher_cbc` WRITE;
/*!40000 ALTER TABLE `teacher_cbc` DISABLE KEYS */;
/*!40000 ALTER TABLE `teacher_cbc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `texting_waiver_form`
--

DROP TABLE IF EXISTS `texting_waiver_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `texting_waiver_form` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `client_rel` varchar(255) DEFAULT NULL,
  `dob` datetime DEFAULT NULL,
  `guardian` varchar(255) DEFAULT NULL,
  `guardian_date` datetime DEFAULT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `provider_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9D3216154661880D` (`clinician_id`),
  CONSTRAINT `FK9D3216154661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `texting_waiver_form`
--

LOCK TABLES `texting_waiver_form` WRITE;
/*!40000 ALTER TABLE `texting_waiver_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `texting_waiver_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment_plan`
--

DROP TABLE IF EXISTS `treatment_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `treatment_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `axis_1` varchar(255) DEFAULT NULL,
  `axis_2` varchar(255) DEFAULT NULL,
  `axis_3` varchar(255) DEFAULT NULL,
  `axis_4` varchar(255) DEFAULT NULL,
  `axis_5` varchar(255) DEFAULT NULL,
  `clinician_sig` varchar(255) DEFAULT NULL,
  `clinician_sig_date` datetime DEFAULT NULL,
  `discharge` text DEFAULT NULL,
  `dx` varchar(255) DEFAULT NULL,
  `goal_details` text DEFAULT NULL,
  `goals` text DEFAULT NULL,
  `medications` text DEFAULT NULL,
  `prescriber` varchar(255) DEFAULT NULL,
  `health_issues` text DEFAULT NULL,
  `release_on_file` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK572656904661880D` (`clinician_id`),
  CONSTRAINT `FK572656904661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment_plan`
--

LOCK TABLES `treatment_plan` WRITE;
/*!40000 ALTER TABLE `treatment_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `treatment_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tx_code`
--

DROP TABLE IF EXISTS `tx_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tx_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `encounter_id` int(11) DEFAULT NULL,
  `cpt` int(11) DEFAULT NULL,
  `cpt_modifier` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCA81CAC818ACAC32` (`cpt_modifier`),
  KEY `FKCA81CAC8738FB473` (`cpt`),
  CONSTRAINT `FKCA81CAC818ACAC32` FOREIGN KEY (`cpt_modifier`) REFERENCES `cpt_modifier` (`id`),
  CONSTRAINT `FKCA81CAC8738FB473` FOREIGN KEY (`cpt`) REFERENCES `cpt` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tx_code`
--

LOCK TABLES `tx_code` WRITE;
/*!40000 ALTER TABLE `tx_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `tx_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `us_state`
--

DROP TABLE IF EXISTS `us_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `us_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `us_state`
--

LOCK TABLES `us_state` WRITE;
/*!40000 ALTER TABLE `us_state` DISABLE KEYS */;
INSERT INTO `us_state` VALUES (1,NULL,'2016-03-24 15:53:48',NULL,'MA','Massachusetts'),(2,NULL,'2016-01-30 12:58:33',NULL,'AL','Alabama'),(3,NULL,'2018-12-02 13:06:12',NULL,'AK','Alaska'),(4,NULL,'2016-04-01 00:14:47',NULL,'AZ','Arizona'),(5,NULL,'2018-12-02 13:08:23',NULL,'AR','Arkansas'),(6,NULL,'2017-11-30 04:05:17',NULL,'CA','California'),(7,NULL,'2018-12-02 13:15:12',NULL,'CO','Colorado'),(8,NULL,'2016-10-21 00:37:41',NULL,'CT','Connecticut'),(9,NULL,'2018-11-29 09:09:38',NULL,'DE','Delaware'),(10,NULL,'2016-02-08 17:26:27',NULL,'FL','Florida'),(11,NULL,'2015-12-18 07:37:49',NULL,'GA','Georgia'),(12,NULL,'2015-11-04 06:35:39',NULL,'HI','Hawaii'),(13,NULL,'2016-02-22 21:54:42',NULL,'ID','Idaho'),(14,NULL,'2015-06-03 16:06:21',NULL,'IL','Illinois'),(15,NULL,'2015-11-06 08:52:35',NULL,'IN','Indiana'),(16,NULL,'2015-06-10 19:06:50',NULL,'IA','Iowa'),(17,NULL,'2015-12-06 14:02:01',NULL,'KS','Kansas'),(18,NULL,NULL,NULL,'KY','Kentucky'),(19,NULL,'2016-02-07 19:17:24',NULL,'LA','Louisiana'),(20,NULL,'2016-02-07 19:17:24',NULL,'ME','Maine'),(21,NULL,'2016-04-13 15:04:50',NULL,'MD','Maryland'),(22,NULL,NULL,NULL,'MN','Michigan'),(23,NULL,NULL,NULL,'MN','Minnesota'),(24,NULL,NULL,NULL,'MS','Mississippi'),(25,NULL,NULL,NULL,'MI','Missouri'),(26,NULL,NULL,NULL,'MT','Montana'),(27,NULL,NULL,NULL,'ME','Nebraska'),(28,NULL,NULL,NULL,'MV','Nevada'),(29,NULL,NULL,NULL,'NH','New Hampshire'),(30,NULL,'2015-06-01 21:08:24',NULL,'NJ','New Jersey'),(31,NULL,NULL,NULL,'NM','New Mexico'),(32,NULL,NULL,NULL,'NY','New York'),(33,NULL,NULL,NULL,'NC','North Carolina'),(34,NULL,NULL,NULL,'ND','North Dakota'),(35,NULL,'2015-10-13 23:33:04',NULL,'OH','Ohio'),(36,NULL,NULL,NULL,'OK','Oklahoma'),(37,NULL,NULL,NULL,'OR','Oregon'),(38,NULL,'2015-12-31 04:48:10',NULL,'PA','Pennsylvania'),(39,NULL,NULL,NULL,'RI','Rhode Island'),(40,NULL,'2015-10-14 16:47:43',NULL,'SC','South Carolina'),(41,NULL,NULL,NULL,'SD','South Dakota'),(42,NULL,'2015-11-15 23:49:10',NULL,'TN','Tennessee'),(43,NULL,NULL,NULL,'TX','Texas'),(44,NULL,'2015-12-12 11:03:04',NULL,'UT','Utah'),(45,NULL,NULL,NULL,'VT','Vermont'),(46,NULL,NULL,NULL,'VA','Virginia'),(47,NULL,'2015-10-13 14:10:01',NULL,'WA','Washington'),(48,NULL,NULL,NULL,'WV','West Virginia'),(49,NULL,NULL,NULL,'WI','Wisconsin'),(50,NULL,NULL,NULL,'WY','Wyoming'),(51,NULL,'2016-04-01 00:15:51',NULL,NULL,'American Samoa'),(52,NULL,NULL,NULL,'DC','District of Columbia'),(53,NULL,NULL,NULL,NULL,'Federated States of Micronesia'),(54,NULL,'2016-02-04 02:11:08',NULL,NULL,'Guam'),(55,NULL,NULL,NULL,NULL,'Marshall Islands'),(56,NULL,NULL,NULL,NULL,'Northern Mariana Islands'),(57,NULL,NULL,NULL,NULL,'Palau'),(58,NULL,NULL,NULL,'','Puerto Rico'),(59,NULL,NULL,NULL,NULL,'Virgin Islands');
/*!40000 ALTER TABLE `us_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `client_type` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `pager` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `password_created` tinyint(4) DEFAULT 0,
  `primary_phone` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `secondary_phone` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `credential_id` int(11) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `division_id` int(11) DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `practice_name` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL,
  `recovery_code` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK36EBCBC1AEB72F` (`recovery_code`),
  CONSTRAINT `FK36EBCBC1AEB72F` FOREIGN KEY (`recovery_code`) REFERENCES `recovery_code` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2018-11-26 18:16:43','2018-12-02 13:13:07','2018-12-02 13:13:07','user','admin@practice.com',NULL,'Annie','2018-12-02 13:13:07','Admin',NULL,NULL,'gcd1Oi5ohpMROajCUlg/NDAwJp2fZFPtCFBh0N16i5o=',1,'617 980-8833','$3@Nn3HMS2012','617 888-0099',1,'admin',13,3,31,NULL,NULL,1,'clinical',NULL),(2,'2018-11-26 18:21:33','2018-12-01 03:57:40','2018-12-01 03:57:40','user','pam@practice.com',NULL,'Pam','2018-12-01 03:57:40','Practitioner',NULL,NULL,'gcd1Oi5ohpMROajCUlg/NDAwJp2fZFPtCFBh0N16i5o=',0,'508 222-0192','$3@Nn3HMS2012','617 873-0292',1,'pam',13,3,31,NULL,NULL,1,'clinical',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_facility`
--

DROP TABLE IF EXISTS `user_facility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_facility` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `facility` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK20E1C8979962B3FC` (`facility`),
  CONSTRAINT `FK20E1C8979962B3FC` FOREIGN KEY (`facility`) REFERENCES `facility` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_facility`
--

LOCK TABLES `user_facility` WRITE;
/*!40000 ALTER TABLE `user_facility` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_facility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_network_marketing_source`
--

DROP TABLE IF EXISTS `user_network_marketing_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_network_marketing_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `source` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4B4E039BD07AE24` (`source`),
  CONSTRAINT `FK4B4E039BD07AE24` FOREIGN KEY (`source`) REFERENCES `network_marketing_source` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_network_marketing_source`
--

LOCK TABLES `user_network_marketing_source` WRITE;
/*!40000 ALTER TABLE `user_network_marketing_source` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_network_marketing_source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vital_sign`
--

DROP TABLE IF EXISTS `vital_sign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vital_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `bmi` float DEFAULT NULL,
  `diastolic` int(11) NOT NULL DEFAULT 0,
  `height` float NOT NULL DEFAULT 0,
  `ofc` float NOT NULL DEFAULT 0,
  `oximetry` float NOT NULL DEFAULT 0,
  `pulse` int(11) NOT NULL DEFAULT 0,
  `respiration` int(11) DEFAULT NULL,
  `systolic` int(11) NOT NULL DEFAULT 0,
  `temperature` float NOT NULL DEFAULT 0,
  `weight` float NOT NULL DEFAULT 0,
  `encounter` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK44EC6E105D457337` (`encounter`),
  KEY `FK44EC6E104661880D` (`clinician_id`),
  CONSTRAINT `FK44EC6E104661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK44EC6E105D457337` FOREIGN KEY (`encounter`) REFERENCES `encounter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vital_sign`
--

LOCK TABLES `vital_sign` WRITE;
/*!40000 ALTER TABLE `vital_sign` DISABLE KEYS */;
/*!40000 ALTER TABLE `vital_sign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `youth_self_report`
--

DROP TABLE IF EXISTS `youth_self_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `youth_self_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_accessed` datetime DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `clinician_id` int(11) DEFAULT NULL,
  `closed` tinyint(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `best` text DEFAULT NULL,
  `chore_1` varchar(255) DEFAULT NULL,
  `chore_2` varchar(255) DEFAULT NULL,
  `chore_3` varchar(255) DEFAULT NULL,
  `chore_ability_1` varchar(255) DEFAULT NULL,
  `chore_ability_2` varchar(255) DEFAULT NULL,
  `chore_ability_3` varchar(255) DEFAULT NULL,
  `concerns` text DEFAULT NULL,
  `concerns_school` text DEFAULT NULL,
  `ethnicity` varchar(255) DEFAULT NULL,
  `friend_count` varchar(255) DEFAULT NULL,
  `friend_freq` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `hobby_1` varchar(255) DEFAULT NULL,
  `hobby_2` varchar(255) DEFAULT NULL,
  `hobby_3` varchar(255) DEFAULT NULL,
  `hobby_ability_1` varchar(255) DEFAULT NULL,
  `hobby_ability_2` varchar(255) DEFAULT NULL,
  `hobby_ability_3` varchar(255) DEFAULT NULL,
  `hobby_time_1` varchar(255) DEFAULT NULL,
  `hobby_time_2` varchar(255) DEFAULT NULL,
  `hobby_time_3` varchar(255) DEFAULT NULL,
  `no_chores` tinyint(1) DEFAULT NULL,
  `no_hobbies` tinyint(1) DEFAULT NULL,
  `no_orgs` tinyint(1) DEFAULT NULL,
  `no_school` tinyint(1) DEFAULT NULL,
  `no_school_reason` varchar(255) DEFAULT NULL,
  `no_siblings` tinyint(1) DEFAULT NULL,
  `no_sports` tinyint(1) DEFAULT NULL,
  `not_attending` tinyint(1) DEFAULT NULL,
  `org_1` varchar(255) DEFAULT NULL,
  `org_2` varchar(255) DEFAULT NULL,
  `org_3` varchar(255) DEFAULT NULL,
  `org_time_1` varchar(255) DEFAULT NULL,
  `org_time_2` varchar(255) DEFAULT NULL,
  `org_time_3` varchar(255) DEFAULT NULL,
  `parent_work_1` varchar(255) DEFAULT NULL,
  `parent_work_2` varchar(255) DEFAULT NULL,
  `report` text DEFAULT NULL,
  `school_a` varchar(255) DEFAULT NULL,
  `school_b` varchar(255) DEFAULT NULL,
  `school_c` varchar(255) DEFAULT NULL,
  `school_d` varchar(255) DEFAULT NULL,
  `school_e` varchar(255) DEFAULT NULL,
  `school_f` varchar(255) DEFAULT NULL,
  `school_g` varchar(255) DEFAULT NULL,
  `school_name_e` varchar(255) DEFAULT NULL,
  `school_name_f` varchar(255) DEFAULT NULL,
  `school_name_g` varchar(255) DEFAULT NULL,
  `sibling_a` varchar(255) DEFAULT NULL,
  `sibling_b` varchar(255) DEFAULT NULL,
  `sibling_c` varchar(255) DEFAULT NULL,
  `sibling_d` varchar(255) DEFAULT NULL,
  `sped` varchar(255) DEFAULT NULL,
  `sped_desc` varchar(255) DEFAULT NULL,
  `sport_1` varchar(255) DEFAULT NULL,
  `sport_2` varchar(255) DEFAULT NULL,
  `sport_3` varchar(255) DEFAULT NULL,
  `sport_ability_1` varchar(255) DEFAULT NULL,
  `sport_ability_2` varchar(255) DEFAULT NULL,
  `sport_ability_3` varchar(255) DEFAULT NULL,
  `sport_time_1` varchar(255) DEFAULT NULL,
  `sport_time_2` varchar(255) DEFAULT NULL,
  `sport_time_3` varchar(255) DEFAULT NULL,
  `work` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB680061B4661880D` (`clinician_id`),
  CONSTRAINT `FKB680061B4661880D` FOREIGN KEY (`clinician_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `youth_self_report`
--

LOCK TABLES `youth_self_report` WRITE;
/*!40000 ALTER TABLE `youth_self_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `youth_self_report` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-02 20:02:22