DROP DATABASE IF EXISTS `medexpert`;

CREATE DATABASE IF NOT EXISTS `medexpert`;
USE `medexpert`;

--
-- Tabele structure for `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
	`username` varchar(50) NOT NULL,
    `password` char(68) NOT NULL,
    `enabled` tinyint(1) NOT NULL,
    PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for  table `users`
--
INSERT INTO `users` VALUES 
	('john', '{bcrypt}$2a$10$CJwkHmTIeqxNO1QP2FauM.DQoef3uM2b18wWWcS4EiK85ftLLDLLO', 1),
    ('mary', '{bcrypt}$2a$10$cSjmTri9.TYqXwR0tWq8x.s41LdfrLVl8FrDNp.R0GZ0pa5Xthrxq', 1),
    ('susan', '{bcrypt}$2a$10$jrAMQCrGMgMfDIZKlFOX1O6mld0PWAUaLiAnu7ZhyCrPosK0mb43O', 1);
    
-- 
-- Table structure for `authorities`
-- 
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
	`username` varchar(50) NOT NULL,
    `authority` varchar(50) NOT NULL,
    UNIQUE KEY `authorities_idx_1` (`username`, `authority`),
    CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for  table `authorities`
--
INSERT INTO `authorities` VALUES 
	('john', 'ROLE_EMPLYEE'),
    ('mary', 'ROLE_EMPLYEE'),
    ('mary', 'ROLE_MANAGER'),
    ('susan', 'ROLE_EMPLYEE'),
    ('susan', 'ROLE_ADMIN');
