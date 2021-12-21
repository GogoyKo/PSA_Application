-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 29, 2019 at 09:16 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `psadb`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(200) NOT NULL,
  `lname` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `fname`, `lname`, `email`, `password`) VALUES
(1, 'admin', 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3');

-- --------------------------------------------------------

--
-- Table structure for table `certification`
--

CREATE TABLE IF NOT EXISTS `certification` (
  `certification_id` int(11) NOT NULL AUTO_INCREMENT,
  `enumerator_id` int(150) NOT NULL,
  `geo_iden_id` int(150) NOT NULL,
  `enumerator` varchar(150) NOT NULL,
  `signature1` varchar(150) NOT NULL,
  `date_1` varchar(150) NOT NULL,
  `team_supervisor` varchar(150) NOT NULL,
  `signature2` varchar(150) NOT NULL,
  `date_2` varchar(150) NOT NULL,
  `census_area_supervisor` varchar(150) NOT NULL,
  `signature3` varchar(150) NOT NULL,
  `date_3` varchar(150) NOT NULL,
  `co_rsso_po` varchar(150) NOT NULL,
  `signature4` varchar(150) NOT NULL,
  `date_4` varchar(150) NOT NULL,
  `qr_number` int(150) NOT NULL,
  `year` varchar(150) NOT NULL,
  PRIMARY KEY (`certification_id`),
  KEY `geo_iden_id` (`geo_iden_id`),
  KEY `enumerator_id` (`enumerator_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `certification`
--


-- --------------------------------------------------------

--
-- Table structure for table `certification_records`
--

CREATE TABLE IF NOT EXISTS `certification_records` (
  `certification_id` int(11) NOT NULL AUTO_INCREMENT,
  `enumerator_id` int(150) NOT NULL,
  `geo_iden_id` int(150) NOT NULL,
  `enumerator` varchar(150) NOT NULL,
  `signature1` varchar(150) NOT NULL,
  `date_1` varchar(150) NOT NULL,
  `team_supervisor` varchar(150) NOT NULL,
  `signature2` varchar(150) NOT NULL,
  `date_2` varchar(150) NOT NULL,
  `census_area_supervisor` varchar(150) NOT NULL,
  `signature3` varchar(150) NOT NULL,
  `date_3` varchar(150) NOT NULL,
  `co_rsso_po` varchar(150) NOT NULL,
  `signature4` varchar(150) NOT NULL,
  `date_4` varchar(150) NOT NULL,
  `year` varchar(150) NOT NULL,
  `qr_number` int(150) NOT NULL,
  PRIMARY KEY (`certification_id`),
  KEY `geo_iden_id` (`geo_iden_id`),
  KEY `enumerator_id` (`enumerator_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `certification_records`
--


-- --------------------------------------------------------

--
-- Table structure for table `enum_user`
--

CREATE TABLE IF NOT EXISTS `enum_user` (
  `enumerator_id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(150) NOT NULL,
  `lastname` varchar(150) NOT NULL,
  `username` varchar(150) NOT NULL,
  `password` text NOT NULL,
  `email` varchar(150) NOT NULL,
  `age` int(150) NOT NULL,
  `mobile` varchar(150) NOT NULL,
  `gender` varchar(200) NOT NULL,
  `image` varchar(200) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`enumerator_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=88 ;

--
-- Dumping data for table `enum_user`
--

INSERT INTO `enum_user` (`enumerator_id`, `firstname`, `lastname`, `username`, `password`, `email`, `age`, `mobile`, `gender`, `image`, `status`) VALUES
(87, 'arthur', 'ladroma', 'user1', '827ccb0eea8a706c4c34a16891f84e7b', 'arthur.ladroma@gmail.com', 20, '0955555555', 'Male', 'http://10.0.2.2:8089/PSA/php/images/adad.png', 1);

-- --------------------------------------------------------

--
-- Table structure for table `geo_identification`
--

CREATE TABLE IF NOT EXISTS `geo_identification` (
  `geo_iden_id` int(11) NOT NULL AUTO_INCREMENT,
  `enumerator_id` int(150) NOT NULL,
  `province` varchar(150) NOT NULL,
  `province_num` text NOT NULL,
  `city` varchar(150) NOT NULL,
  `city_num` text NOT NULL,
  `brgy` varchar(150) NOT NULL,
  `brgy_num` text NOT NULL,
  `EAN` text NOT NULL,
  `BSN` text NOT NULL,
  `HUSN` text NOT NULL,
  `HSN` text NOT NULL,
  `LNTR` text NOT NULL,
  `Lname` varchar(150) NOT NULL,
  `Fname` text NOT NULL,
  `Address` varchar(150) NOT NULL,
  `qr_number` int(200) NOT NULL,
  `year` varchar(150) NOT NULL,
  PRIMARY KEY (`geo_iden_id`),
  KEY `enumerator_id` (`enumerator_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=121 ;

--
-- Dumping data for table `geo_identification`
--


-- --------------------------------------------------------

--
-- Table structure for table `geo_identification_records`
--

CREATE TABLE IF NOT EXISTS `geo_identification_records` (
  `geo_iden_id` int(11) NOT NULL AUTO_INCREMENT,
  `enumerator_id` int(150) NOT NULL,
  `province` varchar(150) NOT NULL,
  `province_num` varchar(150) NOT NULL,
  `city` varchar(150) NOT NULL,
  `city_num` varchar(150) NOT NULL,
  `brgy` varchar(150) NOT NULL,
  `brgy_num` varchar(150) NOT NULL,
  `EAN` varchar(150) NOT NULL,
  `BSN` varchar(150) NOT NULL,
  `HUSN` varchar(150) NOT NULL,
  `HSN` varchar(150) NOT NULL,
  `LNTR` varchar(150) NOT NULL,
  `Lname` varchar(150) NOT NULL,
  `Fname` varchar(150) NOT NULL,
  `Address` varchar(150) NOT NULL,
  `year` varchar(150) NOT NULL,
  `qr_number` int(150) NOT NULL,
  PRIMARY KEY (`geo_iden_id`),
  KEY `enumerator_id` (`enumerator_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=127 ;

--
-- Dumping data for table `geo_identification_records`
--


-- --------------------------------------------------------

--
-- Table structure for table `house_cen_ques`
--

CREATE TABLE IF NOT EXISTS `house_cen_ques` (
  `house_cen_ques_id` int(11) NOT NULL AUTO_INCREMENT,
  `enumerator_id` int(150) NOT NULL,
  `geo_iden_id` int(150) NOT NULL,
  `b1` varchar(150) NOT NULL,
  `b2` varchar(150) NOT NULL,
  `b3` varchar(150) NOT NULL,
  `h1` varchar(150) NOT NULL,
  `h2` varchar(150) NOT NULL,
  `h3` varchar(150) NOT NULL,
  `h4` varchar(150) NOT NULL,
  `qr_number` int(200) NOT NULL,
  `year` varchar(150) NOT NULL,
  PRIMARY KEY (`house_cen_ques_id`),
  KEY `geo_iden_id` (`geo_iden_id`),
  KEY `enumerator_id` (`enumerator_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `house_cen_ques`
--


-- --------------------------------------------------------

--
-- Table structure for table `house_cen_ques_records`
--

CREATE TABLE IF NOT EXISTS `house_cen_ques_records` (
  `house_cen_ques_id` int(11) NOT NULL AUTO_INCREMENT,
  `enumerator_id` int(150) NOT NULL,
  `geo_iden_id` int(150) NOT NULL,
  `b1` varchar(150) NOT NULL,
  `b2` varchar(150) NOT NULL,
  `b3` varchar(150) NOT NULL,
  `h1` varchar(150) NOT NULL,
  `h2` varchar(150) NOT NULL,
  `h3` varchar(150) NOT NULL,
  `h4` varchar(150) NOT NULL,
  `year` varchar(150) NOT NULL,
  `qr_number` int(150) NOT NULL,
  PRIMARY KEY (`house_cen_ques_id`),
  KEY `geo_iden_id` (`geo_iden_id`),
  KEY `enumerator_id` (`enumerator_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `house_cen_ques_records`
--


-- --------------------------------------------------------

--
-- Table structure for table `interview_rec`
--

CREATE TABLE IF NOT EXISTS `interview_rec` (
  `interview_rec_id` int(11) NOT NULL AUTO_INCREMENT,
  `enumerator_id` int(150) NOT NULL,
  `geo_iden_id` int(150) NOT NULL,
  `date_visit` varchar(150) NOT NULL,
  `time_began` varchar(150) NOT NULL,
  `time_end` varchar(150) NOT NULL,
  `result_visit` varchar(150) NOT NULL,
  `date_visit2` varchar(150) NOT NULL,
  `time_visit` varchar(150) NOT NULL,
  `num_of_visit` varchar(150) NOT NULL,
  `result_of_visit` varchar(150) NOT NULL,
  `num_of_household_mem` varchar(150) NOT NULL,
  `num_of_males` varchar(150) NOT NULL,
  `num_of_females` varchar(150) NOT NULL,
  `mode_of_date_collection` varchar(150) NOT NULL,
  `qr_number` int(200) NOT NULL,
  `year` varchar(150) NOT NULL,
  PRIMARY KEY (`interview_rec_id`),
  KEY `enumerator_id` (`enumerator_id`),
  KEY `geo_iden_id` (`geo_iden_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `interview_rec`
--


-- --------------------------------------------------------

--
-- Table structure for table `interview_rec_records`
--

CREATE TABLE IF NOT EXISTS `interview_rec_records` (
  `interview_rec_id` int(11) NOT NULL AUTO_INCREMENT,
  `enumerator_id` int(150) NOT NULL,
  `geo_iden_id` int(150) NOT NULL,
  `date_visit` varchar(150) NOT NULL,
  `time_began` varchar(150) NOT NULL,
  `time_end` varchar(150) NOT NULL,
  `result_visit` varchar(150) NOT NULL,
  `date_visit2` varchar(150) NOT NULL,
  `time_visit` varchar(150) NOT NULL,
  `num_of_visit` varchar(150) NOT NULL,
  `result_of_visit` varchar(150) NOT NULL,
  `num_of_household_mem` varchar(150) NOT NULL,
  `num_of_males` varchar(150) NOT NULL,
  `num_of_females` varchar(150) NOT NULL,
  `mode_of_date_collection` varchar(150) NOT NULL,
  `year` varchar(150) NOT NULL,
  `qr_number` int(150) NOT NULL,
  PRIMARY KEY (`interview_rec_id`),
  KEY `geo_iden_id` (`geo_iden_id`),
  KEY `enumerator_id` (`enumerator_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `interview_rec_records`
--


-- --------------------------------------------------------

--
-- Table structure for table `popu_cen_ques`
--

CREATE TABLE IF NOT EXISTS `popu_cen_ques` (
  `popu_cen_ques_id` int(11) NOT NULL AUTO_INCREMENT,
  `enumerator_id` int(150) NOT NULL,
  `geo_iden_id` int(150) NOT NULL,
  `person_num` int(150) NOT NULL,
  `lastname_p1` varchar(150) NOT NULL,
  `firstname_p1` varchar(150) NOT NULL,
  `p2` varchar(150) NOT NULL,
  `p3` varchar(150) NOT NULL,
  `month_p4` varchar(150) NOT NULL,
  `p5` varchar(150) NOT NULL,
  `p6` varchar(150) NOT NULL,
  `p7` varchar(150) NOT NULL,
  `p8` varchar(150) NOT NULL,
  `p9` varchar(150) NOT NULL,
  `p10` varchar(150) NOT NULL,
  `p11` varchar(150) NOT NULL,
  `p12` varchar(150) NOT NULL,
  `p13` varchar(150) NOT NULL,
  `p14` varchar(150) NOT NULL,
  `p15` varchar(150) NOT NULL,
  `p16` varchar(150) NOT NULL,
  `qr_number` varchar(200) NOT NULL,
  `year` varchar(150) NOT NULL,
  PRIMARY KEY (`popu_cen_ques_id`),
  KEY `geo_iden_id` (`geo_iden_id`),
  KEY `enumerator_id` (`enumerator_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `popu_cen_ques`
--


-- --------------------------------------------------------

--
-- Table structure for table `popu_cen_ques_records`
--

CREATE TABLE IF NOT EXISTS `popu_cen_ques_records` (
  `popu_cen_ques_id` int(11) NOT NULL AUTO_INCREMENT,
  `enumerator_id` int(150) NOT NULL,
  `geo_iden_id` int(150) NOT NULL,
  `person_num` int(150) NOT NULL,
  `lastname_p1` varchar(150) NOT NULL,
  `firstname_p1` varchar(150) NOT NULL,
  `p2` varchar(150) NOT NULL,
  `p3` varchar(150) NOT NULL,
  `month_p4` varchar(150) NOT NULL,
  `p5` varchar(150) NOT NULL,
  `p6` varchar(150) NOT NULL,
  `p7` varchar(150) NOT NULL,
  `p8` varchar(150) NOT NULL,
  `p9` varchar(150) NOT NULL,
  `p10` varchar(150) NOT NULL,
  `p11` varchar(150) NOT NULL,
  `p12` varchar(150) NOT NULL,
  `p13` varchar(150) NOT NULL,
  `p14` varchar(150) NOT NULL,
  `p15` varchar(150) NOT NULL,
  `p16` varchar(150) NOT NULL,
  `year` varchar(150) NOT NULL,
  `qr_number` int(150) NOT NULL,
  PRIMARY KEY (`popu_cen_ques_id`),
  KEY `geo_iden_id` (`geo_iden_id`),
  KEY `enumerator_id` (`enumerator_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `popu_cen_ques_records`
--


-- --------------------------------------------------------

--
-- Table structure for table `qr_codes`
--

CREATE TABLE IF NOT EXISTS `qr_codes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qr_code_number` varchar(150) NOT NULL,
  `status` varchar(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `qr_codes`
--

INSERT INTO `qr_codes` (`id`, `qr_code_number`, `status`) VALUES
(1, '123', '0');

-- --------------------------------------------------------

--
-- Table structure for table `rdlyhm`
--

CREATE TABLE IF NOT EXISTS `rdlyhm` (
  `rdlyhm_id` int(11) NOT NULL AUTO_INCREMENT,
  `enumerator_id` int(150) NOT NULL,
  `geo_iden_id` int(150) NOT NULL,
  `death_num` int(150) NOT NULL,
  `d1` varchar(150) NOT NULL,
  `d2` varchar(150) NOT NULL,
  `lastname` varchar(150) NOT NULL,
  `firstname` varchar(150) NOT NULL,
  `gender` varchar(150) NOT NULL,
  `age_at_death` varchar(150) NOT NULL,
  `death_reg_d6` varchar(150) NOT NULL,
  `death_reg_d7` varchar(150) NOT NULL,
  `qr_number` int(200) NOT NULL,
  `year` varchar(150) NOT NULL,
  PRIMARY KEY (`rdlyhm_id`),
  KEY `enumerator_id` (`enumerator_id`),
  KEY `geo_iden_id` (`geo_iden_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `rdlyhm`
--


-- --------------------------------------------------------

--
-- Table structure for table `rdlyhm_records`
--

CREATE TABLE IF NOT EXISTS `rdlyhm_records` (
  `rdlyhm_id` int(11) NOT NULL AUTO_INCREMENT,
  `enumerator_id` int(150) NOT NULL,
  `geo_iden_id` int(150) NOT NULL,
  `death_num` int(150) NOT NULL,
  `d1` varchar(150) NOT NULL,
  `d2` varchar(150) NOT NULL,
  `lastname` varchar(150) NOT NULL,
  `firstname` varchar(150) NOT NULL,
  `gender` varchar(150) NOT NULL,
  `age_at_death` varchar(150) NOT NULL,
  `death_reg_d6` varchar(150) NOT NULL,
  `death_reg_d7` varchar(150) NOT NULL,
  `year` varchar(150) NOT NULL,
  `qr_number` int(150) NOT NULL,
  PRIMARY KEY (`rdlyhm_id`),
  KEY `geo_iden_id` (`geo_iden_id`),
  KEY `enumerator_id` (`enumerator_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=106 ;

--
-- Dumping data for table `rdlyhm_records`
--


--
-- Constraints for dumped tables
--

--
-- Constraints for table `certification`
--
ALTER TABLE `certification`
  ADD CONSTRAINT `certification_ibfk_1` FOREIGN KEY (`geo_iden_id`) REFERENCES `geo_identification` (`geo_iden_id`),
  ADD CONSTRAINT `certification_ibfk_2` FOREIGN KEY (`enumerator_id`) REFERENCES `enum_user` (`enumerator_id`);

--
-- Constraints for table `certification_records`
--
ALTER TABLE `certification_records`
  ADD CONSTRAINT `certification_records_ibfk_1` FOREIGN KEY (`geo_iden_id`) REFERENCES `geo_identification` (`geo_iden_id`),
  ADD CONSTRAINT `certification_records_ibfk_2` FOREIGN KEY (`enumerator_id`) REFERENCES `enum_user` (`enumerator_id`);

--
-- Constraints for table `geo_identification`
--
ALTER TABLE `geo_identification`
  ADD CONSTRAINT `geo_identification_ibfk_1` FOREIGN KEY (`enumerator_id`) REFERENCES `enum_user` (`enumerator_id`);

--
-- Constraints for table `geo_identification_records`
--
ALTER TABLE `geo_identification_records`
  ADD CONSTRAINT `geo_identification_records_ibfk_1` FOREIGN KEY (`enumerator_id`) REFERENCES `enum_user` (`enumerator_id`);

--
-- Constraints for table `house_cen_ques`
--
ALTER TABLE `house_cen_ques`
  ADD CONSTRAINT `house_cen_ques_ibfk_2` FOREIGN KEY (`geo_iden_id`) REFERENCES `geo_identification` (`geo_iden_id`),
  ADD CONSTRAINT `house_cen_ques_ibfk_3` FOREIGN KEY (`enumerator_id`) REFERENCES `enum_user` (`enumerator_id`);

--
-- Constraints for table `house_cen_ques_records`
--
ALTER TABLE `house_cen_ques_records`
  ADD CONSTRAINT `house_cen_ques_records_ibfk_1` FOREIGN KEY (`geo_iden_id`) REFERENCES `geo_identification` (`geo_iden_id`),
  ADD CONSTRAINT `house_cen_ques_records_ibfk_2` FOREIGN KEY (`enumerator_id`) REFERENCES `enum_user` (`enumerator_id`);

--
-- Constraints for table `interview_rec`
--
ALTER TABLE `interview_rec`
  ADD CONSTRAINT `interview_rec_ibfk_2` FOREIGN KEY (`enumerator_id`) REFERENCES `enum_user` (`enumerator_id`),
  ADD CONSTRAINT `interview_rec_ibfk_3` FOREIGN KEY (`geo_iden_id`) REFERENCES `geo_identification` (`geo_iden_id`);

--
-- Constraints for table `interview_rec_records`
--
ALTER TABLE `interview_rec_records`
  ADD CONSTRAINT `interview_rec_records_ibfk_1` FOREIGN KEY (`geo_iden_id`) REFERENCES `geo_identification` (`geo_iden_id`),
  ADD CONSTRAINT `interview_rec_records_ibfk_2` FOREIGN KEY (`enumerator_id`) REFERENCES `enum_user` (`enumerator_id`);

--
-- Constraints for table `popu_cen_ques`
--
ALTER TABLE `popu_cen_ques`
  ADD CONSTRAINT `popu_cen_ques_ibfk_1` FOREIGN KEY (`geo_iden_id`) REFERENCES `geo_identification` (`geo_iden_id`),
  ADD CONSTRAINT `popu_cen_ques_ibfk_2` FOREIGN KEY (`enumerator_id`) REFERENCES `enum_user` (`enumerator_id`);

--
-- Constraints for table `popu_cen_ques_records`
--
ALTER TABLE `popu_cen_ques_records`
  ADD CONSTRAINT `popu_cen_ques_records_ibfk_1` FOREIGN KEY (`geo_iden_id`) REFERENCES `geo_identification` (`geo_iden_id`),
  ADD CONSTRAINT `popu_cen_ques_records_ibfk_2` FOREIGN KEY (`enumerator_id`) REFERENCES `enum_user` (`enumerator_id`);

--
-- Constraints for table `rdlyhm`
--
ALTER TABLE `rdlyhm`
  ADD CONSTRAINT `rdlyhm_ibfk_2` FOREIGN KEY (`enumerator_id`) REFERENCES `enum_user` (`enumerator_id`),
  ADD CONSTRAINT `rdlyhm_ibfk_3` FOREIGN KEY (`geo_iden_id`) REFERENCES `geo_identification` (`geo_iden_id`);

--
-- Constraints for table `rdlyhm_records`
--
ALTER TABLE `rdlyhm_records`
  ADD CONSTRAINT `rdlyhm_records_ibfk_1` FOREIGN KEY (`geo_iden_id`) REFERENCES `geo_identification` (`geo_iden_id`),
  ADD CONSTRAINT `rdlyhm_records_ibfk_2` FOREIGN KEY (`enumerator_id`) REFERENCES `enum_user` (`enumerator_id`);
