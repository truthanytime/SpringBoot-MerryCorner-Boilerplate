/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 10.4.6-MariaDB : Database - develop_merry_corner_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`develop_merry_corner_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `develop_merry_corner_db`;

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `street_1` varchar(45) DEFAULT NULL,
  `street_2` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zipCode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `booking_details` */

DROP TABLE IF EXISTS `booking_details`;

CREATE TABLE `booking_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `spring_security_user_id` bigint(20) NOT NULL,
  `decoration_flag` tinyint(4) DEFAULT NULL,
  `food_services_flag` tinyint(4) DEFAULT NULL,
  `tables_and_chairs_flag` tinyint(4) DEFAULT NULL,
  `drinks_flag` tinyint(4) DEFAULT NULL,
  `music_and_dj_flag` tinyint(4) DEFAULT NULL,
  `photo_video_flag` tinyint(4) DEFAULT NULL,
  `special_transportation_flag` tinyint(4) DEFAULT NULL,
  `deposit_confirmed_flag` tinyint(4) DEFAULT 0,
  `selected_date` date DEFAULT NULL,
  `selected_start_time` time DEFAULT NULL,
  `selected_end_time` time DEFAULT NULL,
  `poc_full_name` varchar(45) DEFAULT NULL,
  `poc_mobile_number` char(12) DEFAULT NULL,
  `poc_relation` varchar(45) DEFAULT NULL,
  `theme_of_event` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_spring_security_user_selected_date` (`spring_security_user_id`,`selected_date`),
  CONSTRAINT `fk_booking_details_spring_security_user` FOREIGN KEY (`spring_security_user_id`) REFERENCES `spring_security_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `event_details` */

DROP TABLE IF EXISTS `event_details`;

CREATE TABLE `event_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `booking_details_id` bigint(20) NOT NULL,
  `guests_count` bigint(20) DEFAULT NULL,
  `first_arrival` time DEFAULT NULL,
  `chairs_count` bigint(20) DEFAULT NULL,
  `party_tables_count` bigint(20) DEFAULT NULL,
  `food_preferences` varchar(2000) DEFAULT NULL,
  `decoration_preferences` varchar(2000) DEFAULT NULL,
  `music_preferences` varchar(2000) DEFAULT NULL,
  `drink_preferences` varchar(2000) DEFAULT NULL,
  `photo_video_preferences` varchar(2000) DEFAULT NULL,
  `special_transportaion_preferences` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_event_booking_details` (`booking_details_id`),
  CONSTRAINT `fk_event_booking_details` FOREIGN KEY (`booking_details_id`) REFERENCES `booking_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `spring_security_authorities` */

DROP TABLE IF EXISTS `spring_security_authorities`;

CREATE TABLE `spring_security_authorities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(50) NOT NULL,
  `spring_security_user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_auth_username` (`spring_security_user_id`,`authority`),
  CONSTRAINT `fk_authorities_spring_security_user` FOREIGN KEY (`spring_security_user_id`) REFERENCES `spring_security_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `spring_security_social_user` */

DROP TABLE IF EXISTS `spring_security_social_user`;

CREATE TABLE `spring_security_social_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `provider` varchar(50) NOT NULL,
  `provider_user_id` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL,
  `spring_security_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `spring_security_user_id` (`spring_security_user_id`),
  CONSTRAINT `spring_security_social_user_ibfk_1` FOREIGN KEY (`spring_security_user_id`) REFERENCES `spring_security_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `spring_security_user` */

DROP TABLE IF EXISTS `spring_security_user`;

CREATE TABLE `spring_security_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(70) DEFAULT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `user_profile` */

DROP TABLE IF EXISTS `user_profile`;

CREATE TABLE `user_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email_address` varchar(100) DEFAULT NULL,
  `spring_security_user_id` bigint(20) NOT NULL,
  `address_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_profile_spring_security_user` (`spring_security_user_id`),
  KEY `fk_user_profile_address` (`address_id`),
  CONSTRAINT `fk_user_profile_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `fk_user_profile_spring_security_user` FOREIGN KEY (`spring_security_user_id`) REFERENCES `spring_security_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `vendor` */

DROP TABLE IF EXISTS `vendor`;

CREATE TABLE `vendor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `business_email` varchar(45) DEFAULT NULL,
  `vendor_category_id` bigint(20) NOT NULL,
  `legal_first_name` varchar(45) DEFAULT NULL,
  `legal_middle_name` varchar(45) DEFAULT NULL,
  `legal_last_name` varchar(45) DEFAULT NULL,
  `mobile_number` char(12) DEFAULT NULL,
  `company_name` varchar(45) DEFAULT NULL,
  `address_id` bigint(20) NOT NULL,
  `accepted_agreement_flag` tinyint(4) DEFAULT NULL,
  `application_approved_flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `business_email_UNIQUE` (`business_email`),
  KEY `fk_vendor_address` (`address_id`),
  KEY `fk_vendor_category` (`vendor_category_id`),
  CONSTRAINT `fk_vendor_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `fk_vendor_category` FOREIGN KEY (`vendor_category_id`) REFERENCES `vendor_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `vendor_category` */

DROP TABLE IF EXISTS `vendor_category`;

CREATE TABLE `vendor_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) DEFAULT NULL,
  `category_description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_name_UNIQUE` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `venue_availability` */

DROP TABLE IF EXISTS `venue_availability`;

CREATE TABLE `venue_availability` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `venue_id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `booking_flag` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uc_venue_availability` (`venue_id`,`date`),
  CONSTRAINT `fk_venue_availability_details` FOREIGN KEY (`venue_id`) REFERENCES `venue_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `venue_details` */

DROP TABLE IF EXISTS `venue_details`;

CREATE TABLE `venue_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `venue_name` varchar(45) DEFAULT NULL,
  `address_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_venue_details_address` (`address_id`),
  CONSTRAINT `fk_venue_details_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
