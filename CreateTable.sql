
create user 'visiter'@'localhost' identified by 'BXnmDUrvctrb3b6f';
create database if not exists `visiter`;
grant all on visiter.* to 'visiter'@'localhost';


CREATE TABLE IF NOT EXISTS `visiter`.`ipInfo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hostName` VARCHAR(45) NULL,
  `ip` VARCHAR(45) NULL,
  `timeStamp` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `region` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  `postal` INT NULL,
  `org` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))