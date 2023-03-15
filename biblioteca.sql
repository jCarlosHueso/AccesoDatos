drop database if exists biblioteca;
create database biblioteca;
use biblioteca;
CREATE TABLE `libro` (
  `isbn` varchar(255) NOT NULL,
  `numEjemplares` int NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `autor` varchar(255) NOT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `libro` VALUES ('l2321-es',2,'Patria','Fernando Aramburu'),('l2332-es',3,'El Perfume','Patrick Süskind'),
('65254-es',5,'Terra Alta','Javier Cercas'),('65321-es',4,'Independenica','Javier Cercas'),('65344-es',6,'El castillo de Barbazul','Javier Cercas'),
('657521-es',3,'El monje que vendio su ferrari','JRobin S. Sharma');

CREATE TABLE `prestamo` (
  `fechaP` date NOT NULL,
  `fechaDevolPrevista` date NOT NULL,
  `fechaDevolReal` date DEFAULT NULL,
  `socio` int NOT NULL,
  `libro` varchar(255) NOT NULL,
  PRIMARY KEY (`fechaP`,`libro`,`socio`),
  KEY `FK4jen70v03qdrly7s1ukubodcd` (`socio`),
  KEY `FKotmvvyu4uw5slg19dbtwaeart` (`libro`),
  CONSTRAINT `FK4jen70v03qdrly7s1ukubodcd` FOREIGN KEY (`socio`) REFERENCES `socio` (`id`),
  CONSTRAINT `FKotmvvyu4uw5slg19dbtwaeart` FOREIGN KEY (`libro`) REFERENCES `libro` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
insert into prestamo values('19960421','19960521','19960517',2,'l2321-es');
insert into prestamo values('19960521','19960621','19960617',2,'l2332-es');
insert into prestamo values('19960521','19960621',null,2,'65321-es');
CREATE TABLE `socio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fechaSancion` date DEFAULT NULL,
  `nif` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_36eesmhc5lhps0xaaw8s1h6oy` (`nif`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `socio` VALUES (null,NULL,'11111111A','Lourdes Díazz'),
(null,NULL,'22222222A','Paco León'),
(null,NULL,'33333333A','Fito Páez'),
(null,NULL,'44444444A','Mónica Naranjo');


