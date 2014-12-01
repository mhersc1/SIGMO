CREATE DATABASE  IF NOT EXISTS `sigmo_desarrollo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sigmo_desarrollo`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: sigmo_desarrollo
-- ------------------------------------------------------
-- Server version	5.6.16

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(30) DEFAULT NULL,
  `APELLIDOS` varchar(30) DEFAULT NULL,
  `DIRECCION` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `razonSocial` varchar(100) DEFAULT NULL,
  `telefono1` varchar(13) DEFAULT NULL,
  `telefono2` varchar(13) DEFAULT NULL,
  `NRODOCUMENTO` varchar(20) DEFAULT NULL,
  `TIPODOCUMENTO` int(1) DEFAULT NULL,
  `TIPOPERSONA` int(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Maycol','Espinoza','Rimac','mher_91@hotmail.com',NULL,NULL,NULL,NULL,NULL,NULL),(2,'Melissa','Castro','Rimac','melissa@hotmail.com',NULL,NULL,NULL,NULL,NULL,NULL),(3,'Steve','Jobs','Luriwashigton','stevejobs@apple.com',NULL,NULL,NULL,NULL,NULL,NULL),(4,'Anonymouse','secreteo','Your home','anonymouse@hotmail.com',NULL,NULL,NULL,NULL,NULL,NULL),(5,'Anonymouse','secret','Your home','anonymouse@hotmail.com',NULL,NULL,NULL,NULL,NULL,NULL),(6,'Omar','Cuba','Your home','omar@hotmail.com',NULL,NULL,NULL,NULL,NULL,NULL),(7,'Antonio','Carrillo','Your home','antonio@hotmail.com',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cotizacion`
--

DROP TABLE IF EXISTS `cotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cotizacion` (
  `CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `FECHAREGISTRO` date DEFAULT NULL,
  `IMPORTETOTAL` int(11) DEFAULT NULL,
  `IDCLIENTE` int(11) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`),
  KEY `FK_CLIENTE` (`IDCLIENTE`),
  CONSTRAINT `FK_CLIENTE` FOREIGN KEY (`IDCLIENTE`) REFERENCES `cliente` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotizacion`
--

LOCK TABLES `cotizacion` WRITE;
/*!40000 ALTER TABLE `cotizacion` DISABLE KEYS */;
INSERT INTO `cotizacion` VALUES (1,'1991-12-14',450,1),(2,'2014-10-14',42,3),(3,'2014-11-30',12,2);
/*!40000 ALTER TABLE `cotizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallecotizacion`
--

DROP TABLE IF EXISTS `detallecotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallecotizacion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODIGO` varchar(20) DEFAULT NULL,
  `DESCRIPCION` varchar(60) DEFAULT NULL,
  `NRODEPIEZAS` int(11) DEFAULT NULL,
  `PRECIO` decimal(10,0) DEFAULT NULL,
  `IMPORTE` decimal(10,0) DEFAULT NULL,
  `CODCOTIZACION` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CODCOTIZACION` (`CODCOTIZACION`),
  CONSTRAINT `FK_CODCOTIZACION` FOREIGN KEY (`CODCOTIZACION`) REFERENCES `cotizacion` (`CODIGO`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallecotizacion`
--

LOCK TABLES `detallecotizacion` WRITE;
/*!40000 ALTER TABLE `detallecotizacion` DISABLE KEYS */;
INSERT INTO `detallecotizacion` VALUES (1,'000024','MADERA CAOBA',10,3,25,1),(2,'2226','MADERA EBANO',4,6,24,2),(3,'2227','MADERA ALAMOS',3,6,18,2),(4,'2225','MADERA CAOBA',1,12,12,3);
/*!40000 ALTER TABLE `detallecotizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documento`
--

DROP TABLE IF EXISTS `documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documento` (
  `ID` int(11) NOT NULL,
  `TIPO` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documento`
--

LOCK TABLES `documento` WRITE;
/*!40000 ALTER TABLE `documento` DISABLE KEYS */;
INSERT INTO `documento` VALUES (1,'DNI'),(2,'PASAPORTE'),(3,'RUC'),(4,'OTRO');
/*!40000 ALTER TABLE `documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documentoscliente`
--

DROP TABLE IF EXISTS `documentoscliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documentoscliente` (
  `IDCLIENTE` int(11) NOT NULL,
  `IDDOCUMENTO` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IDCLIENTE`,`IDDOCUMENTO`),
  KEY `FK_IDCLIENTE` (`IDCLIENTE`),
  KEY `FK_IDDOCUMENTO` (`IDDOCUMENTO`),
  CONSTRAINT `FK_IDCLIENTE` FOREIGN KEY (`IDCLIENTE`) REFERENCES `cliente` (`ID`),
  CONSTRAINT `FK_IDDOCUMENTO` FOREIGN KEY (`IDDOCUMENTO`) REFERENCES `documento` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentoscliente`
--

LOCK TABLES `documentoscliente` WRITE;
/*!40000 ALTER TABLE `documentoscliente` DISABLE KEYS */;
INSERT INTO `documentoscliente` VALUES (1,1,'47113327'),(1,3,'10471133278'),(2,1,'47113337'),(2,3,'10471134278'),(3,1,'47115527'),(3,3,'10471166278'),(4,1,'47113437'),(4,3,'104711313278'),(5,1,'47113312'),(5,3,'10471133321');
/*!40000 ALTER TABLE `documentoscliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moneda`
--

DROP TABLE IF EXISTS `moneda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `moneda` (
  `id` int(11) NOT NULL,
  `umon` varchar(10) NOT NULL,
  `umoneda` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moneda`
--

LOCK TABLES `moneda` WRITE;
/*!40000 ALTER TABLE `moneda` DISABLE KEYS */;
INSERT INTO `moneda` VALUES (1,'sol','sol');
/*!40000 ALTER TABLE `moneda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pago` (
  `codPago` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `codCotizacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`codPago`),
  KEY `fk_codigo_pago_idx` (`codCotizacion`),
  CONSTRAINT `fk_codigo_pago` FOREIGN KEY (`codCotizacion`) REFERENCES `cotizacion` (`CODIGO`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
INSERT INTO `pago` VALUES (1,'2014-11-08',1,1),(2,'2014-11-08',1,2),(3,'2014-11-09',1,1);
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `CODIGO` int(11) NOT NULL,
  `ESPESOR` int(11) DEFAULT NULL,
  `ANCHO` int(11) DEFAULT NULL,
  `LARGO` int(11) DEFAULT NULL,
  `UNIDAD` int(11) DEFAULT NULL,
  `DESCRIPCION` varchar(60) NOT NULL,
  `PRECIOUNIT` decimal(10,2) DEFAULT NULL,
  `MONEDA` int(11) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`),
  KEY `FK_UNIDAD` (`UNIDAD`),
  KEY `fk_moneda_idx` (`MONEDA`),
  CONSTRAINT `fk_moneda` FOREIGN KEY (`MONEDA`) REFERENCES `moneda` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_unidad` FOREIGN KEY (`UNIDAD`) REFERENCES `unidad` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (2225,20,5,10,2,'MADERA CAOBA',12.00,1),(2226,12,5,10,2,'MADERA EBANO',NULL,NULL),(2227,11,5,10,2,'MADERA ALAMOS',40.00,NULL),(2228,10,5,10,2,'MADARA UCHIHA',NULL,NULL),(2229,29,5,10,2,'MADERA TEMPLE',NULL,NULL),(2230,10,5,10,2,'MADERA TORNILLO',NULL,NULL),(2231,10,5,10,2,'MADERA CLASE 1',NULL,NULL),(2232,10,5,10,2,'MADERA CLASE 2',NULL,NULL),(2233,10,5,10,2,'MADERA CLASE 3',NULL,NULL),(2234,10,5,10,2,'MADERA CLASE 4',NULL,NULL),(2235,10,5,10,2,'MADERA CLASE 5',NULL,NULL),(2236,10,5,10,2,'MADERA CLASE 6',NULL,NULL),(2237,10,5,10,2,'MADERA CLASE 7',NULL,NULL),(2238,10,5,10,2,'MADERA CLASE 8',NULL,NULL),(2239,10,5,10,2,'MADERA CLASE 9',NULL,NULL),(2240,10,5,10,2,'MADERA CLASE 10',NULL,NULL);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `idRol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'encargadoVentas'),(2,'encargadoAlmacen'),(3,'gerente'),(4,'cajera'),(5,'secretaria');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidad`
--

DROP TABLE IF EXISTS `unidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unidad` (
  `ID` int(11) NOT NULL,
  `UMEDIDA` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidad`
--

LOCK TABLES `unidad` WRITE;
/*!40000 ALTER TABLE `unidad` DISABLE KEYS */;
INSERT INTO `unidad` VALUES (1,'CM'),(2,'MTS'),(3,'PIES');
/*!40000 ALTER TABLE `unidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) DEFAULT NULL,
  `clave` varchar(45) DEFAULT NULL,
  `estado` int(2) DEFAULT NULL,
  `fechaCreacion` date DEFAULT NULL,
  `idRol` int(11) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `idRol` (`idRol`),
  KEY `idRol_2` (`idRol`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'gabriela','argume',1,'2014-09-16',4),(2,'antonio','carrillo',1,'2014-09-16',2),(3,'omar','cuba',1,'2014-09-16',3),(4,'maycol','espinoza',1,'2014-09-16',1),(5,'freddy','pineda',1,'2014-09-16',5);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-30 23:45:46
