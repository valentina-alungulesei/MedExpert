DROP DATABASE IF EXISTS `medexpert`;
CREATE DATABASE IF NOT EXISTS `medexpert`;
USE `medexpert`;

-- 
-- Tabele structure for `users`
--
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(68) NOT NULL,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
-- NOTE: The passwords are encrypted using BCrypt
--       The generation tool is available at: https://www.bcryptcalculator.com/
--       Default passwords here are: test123
--
INSERT INTO `users` (username, password, first_name, last_name, email) VALUES 
	('john', '$2a$10$DYIgFSTlcaWBr7okSn0dkeFZB6il9ewVXhmjkKdeaA73i0x9uqOGS', 'John', 'Doe', 'john@gmail.com'),
    ('mary', '$2a$10$cSjmTri9.TYqXwR0tWq8x.s41LdfrLVl8FrDNp.R0GZ0pa5Xthrxq', 'Mary', 'Smith', 'mary@gmail.com'),
    ('susan', '$2a$10$jrAMQCrGMgMfDIZKlFOX1O6mld0PWAUaLiAnu7ZhyCrPosK0mb43O', 'Susan', 'Adamas', 'susan@gmail.com');
    
-- 
-- Table structure for `roles`
-- 
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--
INSERT INTO `roles` (name) VALUES 
('ROLE_CLIENT'),('ROLE_EMPLOYEE'),('ROLE_MANAGER'),('ROLE_ADMIN');

--
-- Table structure for table `users_roles`
--
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
	`user_id` INT(11) NOT NULL,
    `role_id` INT(11) NOT NULL,
    
    PRIMARY KEY (`user_id`, `role_id`),
    
    KEY `FK_ROLE_idx` (`role_id`),
    
    CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION,
    
    CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
    REFERENCES `roles` (`id`)
    ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS=1;

--
-- Ddmping data for table `users_roles`
--
INSERT INTO `users_roles` VALUES 
	(1, 1),
	(2, 2),
	(2, 3),
	(3, 2),
	(3, 4);
