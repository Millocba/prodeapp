-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: prodedb
-- ------------------------------------------------------
-- Server version	8.0.32
--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;

CREATE TABLE `equipo` (
  `idequipo` int NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idequipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;

INSERT INTO `equipo` VALUES (1,'Argentina','Selección'),(2,'Arabia Saudita','Selección'),(3,'Polonia','Selección'),(4,'México','Seleccion');

UNLOCK TABLES;

--
-- Table structure for table `participante`
--

DROP TABLE IF EXISTS `participante`;

CREATE TABLE `participante` (
  `idparticipante` int NOT NULL,
  `nombre` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `puntajeTotal` int DEFAULT NULL,
  `bonus` int DEFAULT NULL,
  PRIMARY KEY (`idparticipante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `participante`
--

LOCK TABLES `participante` WRITE;
INSERT INTO `participante` VALUES (1,'Mariana',10,6),(2,'Pedro',2,0),(3,'Juan',8,3);
UNLOCK TABLES;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;

CREATE TABLE `partido` (
  `idpartido` int NOT NULL,
  `idequipo1` int NOT NULL,
  `idequipo2` int NOT NULL,
  `golesEquipo1` int NOT NULL,
  `golesEquipo2` int NOT NULL,
  `resultado` int DEFAULT NULL,
  `ronda` int DEFAULT NULL,
  PRIMARY KEY (`idpartido`),
  KEY `idequipo1_idx` (`idequipo1`,`idequipo2`),
  KEY `idequipo2_idx` (`idequipo2`),
  KEY `idresultado_idx` (`resultado`),
  KEY `idronda_idx` (`ronda`),
  CONSTRAINT `partido_chk_1` CHECK (((`ronda` <= 3) or (`resultado` <> 2)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;

INSERT INTO `partido` VALUES (1,1,2,1,2,3,1),(2,3,4,0,0,2,1),(3,1,4,2,0,1,2),(4,2,3,0,2,3,2),(5,3,1,0,2,3,3),(6,2,4,1,2,3,3);

UNLOCK TABLES;

--
-- Table structure for table `pronostico`
--

DROP TABLE IF EXISTS `pronostico`;

CREATE TABLE `pronostico` (
  `idpronostico` int NOT NULL,
  `partido` int NOT NULL,
  `equipo` int NOT NULL,
  `resultado` int DEFAULT NULL,
  `participante` int NOT NULL,
  PRIMARY KEY (`idpronostico`),
  KEY `idpartido_idx` (`partido`),
  KEY `idresultado_idx` (`resultado`),
  KEY `idequipo_idx` (`equipo`),
  KEY `idparticipante_idx` (`participante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `pronostico`
--

LOCK TABLES `pronostico` WRITE;

INSERT INTO `pronostico` VALUES (1,1,1,1,1),(2,2,3,2,1),(3,3,1,1,1),(4,4,2,3,1),(5,5,3,3,1),(6,6,2,3,1),(7,1,1,1,2),(8,2,3,3,2),(9,3,1,1,2),(10,4,2,2,2),(11,5,3,2,2),(12,6,2,2,2),(13,1,1,3,3),(14,2,3,2,3),(15,3,1,3,3),(16,4,2,3,3),(17,5,3,1,3),(18,6,2,3,3);

UNLOCK TABLES;

--
-- Table structure for table `resultado`
--

DROP TABLE IF EXISTS `resultado`;

CREATE TABLE `resultado` (
  `idresultado` int NOT NULL,
  `resultado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idresultado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
--
-- Dumping data for table `resultado`
--

LOCK TABLES `resultado` WRITE;

INSERT INTO `resultado` VALUES (1,'GANADOR'),(2,'EMPATE'),(3,'PERDEDOR');

UNLOCK TABLES;

--
-- Table structure for table `ronda`
--

DROP TABLE IF EXISTS `ronda`;
CREATE TABLE `ronda` (
  `idronda` int NOT NULL,
  `numero` varchar(45) NOT NULL,
  PRIMARY KEY (`idronda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `ronda`
--

LOCK TABLES `ronda` WRITE;

INSERT INTO `ronda` VALUES (1,'1'),(2,'2'),(3,'3');
UNLOCK TABLES;

--
-- Temporary view structure for view `view_pronostico`
--

DROP TABLE IF EXISTS `view_pronostico`;
SET @saved_cs_client     = @@character_set_client;

SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_resultado`
--

DROP TABLE IF EXISTS `view_resultado`;
SET @saved_cs_client     = @@character_set_client;

SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `view_pronostico`
--

DROP VIEW IF EXISTS `view_pronostico`*/;

CREATE VIEW `view_pronostico` AS select `participante`.`nombre` AS `Participante`,`pr1`.`equipo` AS `idequipo1`,`r`.`resultado` AS `resultado`,`p`.`idequipo2` AS `idequipo2`,`pr1`.`partido` AS `partido` from (((`pronostico` `pr1` join `partido` `p` on((`p`.`idpartido` = `pr1`.`partido`))) join `resultado` `r` on((`r`.`idresultado` = `pr1`.`resultado`))) join `participante` on((`participante`.`idparticipante` = `pr1`.`participante`))) order by `pr1`.`idpronostico` */;

--
-- Final view structure for view `view_resultado`
--

DROP VIEW IF EXISTS `view_resultado`*/;

CREATE  VIEW `view_resultado` AS select `p`.`ronda` AS `ronda`,`p`.`idequipo1` AS `idequipo1`,`e`.`nombre` AS `nombre1`,`e`.`descripcion` AS `descripcion1`,`p`.`golesEquipo1` AS `golesEquipo1`,`p`.`golesEquipo2` AS `golesEquipo2`,`p`.`idequipo2` AS `idequipo2`,`e2`.`nombre` AS `nombre2`,`e2`.`descripcion` AS `descripcion2`,`p`.`idpartido` AS `idpartido` from ((`partido` `p` join `equipo` `e` on((`p`.`idequipo1` = `e`.`idequipo`))) join `equipo` `e2` on((`p`.`idequipo2` = `e2`.`idequipo`))) order by `p`.`idpartido` */;


-- Dump completed on 2023-04-23  1:33:39
