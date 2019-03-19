CREATE SCHEMA IF NOT EXISTS innocv

SET search_path TO innocv;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  longname VARCHAR(100),
  age INTEGER,
  PRIMARY KEY (id));
  
  
INSERT INTO user(id, name, longname, age)
VALUES(1,"Sergio","Liébanas Rodríguez", 36);

INSERT INTO user(id, name, longname, age)
VALUES(1,"Eva","Caballero Soto", 30);