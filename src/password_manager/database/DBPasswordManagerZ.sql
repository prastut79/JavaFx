/*
DATABASE DBPasswordManagerZ
*/

DROP DATABASE IF EXISTS dbpasswordmanagerz;
CREATE DATABASE dbpasswordmanagerz;


CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fname` varchar(15) NOT NULL,
  `mname` varchar(15) DEFAULT NULL,
  `lname` varchar(15) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(30) NOT NULL,
  `profileImage` varchar(1024) NOT NULL,
  `joinedDate` datetime NOT NULL,
  `lastLogin` datetime NOT NULL,
  `additionalInfo` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
);



CREATE TABLE `passwords` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `website` varchar(255) NOT NULL,
  `info` varchar(500) NOT NULL,
  `addedDate` datetime NOT NULL,
  `modifiedDate` datetime NOT NULL,
  `addedBy` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
);



    insert into user (fname, mname, lname, username, password, joinedDate, lastLogin, additionalInfo, profileImage)
    VALUES ("Prastut", null, "Paudel", "netsos", "netsos", "2021-05-19 17:22:20", "2021-05-19 17:22:20", "Zer0", "D:\\AWp\\ProfileImage.jpg");
