-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 01-10-2014 a las 21:25:37
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `sigmo_desarrollo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(30) DEFAULT NULL,
  `APELLIDOS` varchar(30) DEFAULT NULL,
  `DIRECCION` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`ID`, `NOMBRE`, `APELLIDOS`, `DIRECCION`, `EMAIL`) VALUES
(1, 'Maycol', 'Espinoza', 'Rimac', 'mher_91@hotmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cotizacion`
--

CREATE TABLE IF NOT EXISTS `cotizacion` (
  `CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `FECHAREGISTRO` date DEFAULT NULL,
  `IMPORTETOTAL` int(11) DEFAULT NULL,
  `IDCLIENTE` int(11) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`),
  KEY `FK_CLIENTE` (`IDCLIENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cotizacion`
--

INSERT INTO `cotizacion` ( `FECHAREGISTRO`, `IMPORTETOTAL`, `IDCLIENTE`) VALUES
('1991-12-14', 450, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallecotizacion`
--

CREATE TABLE IF NOT EXISTS `detallecotizacion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODIGO` varchar(20) DEFAULT NULL,
  `DESCRIPCION` varchar(60) DEFAULT NULL,
  `NRODEPIEZAS` int(11) DEFAULT NULL,
  `PRECIO` decimal(10,0) DEFAULT NULL,
  `IMPORTE` decimal(10,0) DEFAULT NULL,
  `CODCOTIZACION` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CODCOTIZACION` (`CODCOTIZACION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------
INSERT INTO DETALLECOTIZACION(CODIGO,DESCRIPCION,NRODEPIEZAS,PRECIO,IMPORTE,CODCOTIZACION) 
VALUES('000024','MADERA CAOBA',10,2.5,25,1);
--
-- Estructura de tabla para la tabla `documento`
--

CREATE TABLE IF NOT EXISTS `documento` (
  `ID` int(11) NOT NULL,
  `TIPO` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `documento`
--

INSERT INTO `documento` (`ID`, `TIPO`) VALUES
(1, 'DNI'),
(2, 'PASAPORTE'),
(3, 'RUC'),
(4, 'OTRO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documentoscliente`
--

CREATE TABLE IF NOT EXISTS `documentoscliente` (
  `IDCLIENTE` int(11) NOT NULL,
  `IDDOCUMENTO` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IDCLIENTE`,`IDDOCUMENTO`),
  KEY `FK_IDCLIENTE` (`IDCLIENTE`),
  KEY `FK_IDDOCUMENTO` (`IDDOCUMENTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `documentoscliente`
--

INSERT INTO `documentoscliente` (`IDCLIENTE`, `IDDOCUMENTO`, `IDENTIFICADOR`) VALUES
(1, 1, '47113327'),
(1, 3, '10471133278');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE IF NOT EXISTS `producto` (
  `CODIGO` int(11) NOT NULL,
  `ESPESOR` int(11) DEFAULT NULL,
  `ANCHO` int(11) DEFAULT NULL,
  `LARGO` int(11) DEFAULT NULL,
  `UNIDAD` int(11) DEFAULT NULL,
  `descripcion` varchar(60) NOT NULL,
  PRIMARY KEY (`CODIGO`),
  KEY `FK_UNIDAD` (`UNIDAD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO PRODUCTO (CODIGO,DESCRIPCION,ESPESOR,ANCHO,LARGO,UNIDAD) 
VALUES(2225,'MADERA CAOBA',20,5,10,2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE IF NOT EXISTS `rol` (
  `idRol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idRol`, `nombre`) VALUES
(1, 'encargadoVentas'),
(2, 'encargadoAlmacen'),
(3, 'gerente'),
(4, 'cajera'),
(5, 'secretaria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `unidad`
--

CREATE TABLE IF NOT EXISTS `unidad` (
  `ID` int(11) NOT NULL,
  `UMEDIDA` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `unidad`
--

INSERT INTO `unidad` (`ID`, `UMEDIDA`) VALUES
(1, 'CM'),
(2, 'MTS'),
(3, 'PIES');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) DEFAULT NULL,
  `clave` varchar(45) DEFAULT NULL,
  `estado` int(2) DEFAULT NULL,
  `fechaCreacion` date DEFAULT NULL,
  `idRol` int(11) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `idRol` (`idRol`),
  KEY `idRol_2` (`idRol`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `usuario`, `clave`, `estado`, `fechaCreacion`, `idRol`) VALUES
(4, 'maycol', 'espinoza', 1, '2014-09-16', 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cotizacion`
--
ALTER TABLE `cotizacion`
  ADD CONSTRAINT `FK_CLIENTE` FOREIGN KEY (`IDCLIENTE`) REFERENCES `cliente` (`ID`);

--
-- Filtros para la tabla `detallecotizacion`
--
ALTER TABLE `detallecotizacion`
  ADD CONSTRAINT `FK_CODCOTIZACION` FOREIGN KEY (`CODCOTIZACION`) REFERENCES `cotizacion` (`CODIGO`);

--
-- Filtros para la tabla `documentoscliente`
--
ALTER TABLE `documentoscliente`
  ADD CONSTRAINT `FK_IDCLIENTE` FOREIGN KEY (`IDCLIENTE`) REFERENCES `cliente` (`ID`),
  ADD CONSTRAINT `FK_IDDOCUMENTO` FOREIGN KEY (`IDDOCUMENTO`) REFERENCES `documento` (`ID`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `FK_UNIDAD` FOREIGN KEY (`UNIDAD`) REFERENCES `unidad` (`ID`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
