
drop schema sigmo_desarrollo;
create schema sigmo_desarrollo;
use sigmo_desarrollo;


CREATE TABLE IF NOT EXISTS `cliente` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(30) DEFAULT NULL,
  `APELLIDOS` varchar(30) DEFAULT NULL,
  `DIRECCION` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;




CREATE TABLE IF NOT EXISTS `cotizacion` (
  `CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `FECHAREGISTRO` date DEFAULT NULL,
  `IMPORTETOTAL` int(11) DEFAULT NULL,
  `IDCLIENTE` int(11) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`),
  KEY `FK_CLIENTE` (`IDCLIENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






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




CREATE TABLE IF NOT EXISTS `documento` (
  `ID` int(11) NOT NULL,
  `TIPO` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE IF NOT EXISTS `documentoscliente` (
  `IDCLIENTE` int(11) NOT NULL,
  `IDDOCUMENTO` int(11) NOT NULL,
  `IDENTIFICADOR` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IDCLIENTE`,`IDDOCUMENTO`),
  KEY `FK_IDCLIENTE` (`IDCLIENTE`),
  KEY `FK_IDDOCUMENTO` (`IDDOCUMENTO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





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





CREATE TABLE IF NOT EXISTS `rol` (
  `idRol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idRol`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;





CREATE TABLE IF NOT EXISTS `unidad` (
  `ID` int(11) NOT NULL,
  `UMEDIDA` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





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






ALTER TABLE `cotizacion`
  ADD CONSTRAINT `FK_CLIENTE` FOREIGN KEY (`IDCLIENTE`) REFERENCES `cliente` (`ID`);


ALTER TABLE `detallecotizacion`
  ADD CONSTRAINT `FK_CODCOTIZACION` FOREIGN KEY (`CODCOTIZACION`) REFERENCES `cotizacion` (`CODIGO`);


ALTER TABLE `documentoscliente`
  ADD CONSTRAINT `FK_IDCLIENTE` FOREIGN KEY (`IDCLIENTE`) REFERENCES `cliente` (`ID`),
  ADD CONSTRAINT `FK_IDDOCUMENTO` FOREIGN KEY (`IDDOCUMENTO`) REFERENCES `documento` (`ID`);


ALTER TABLE `producto`
  ADD CONSTRAINT `FK_UNIDAD` FOREIGN KEY (`UNIDAD`) REFERENCES `unidad` (`ID`);


ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`);

select * from cliente;

INSERT INTO `cliente` (`NOMBRE`, `APELLIDOS`, `DIRECCION`, `EMAIL`) VALUES
( 'Maycol', 'Espinoza', 'Rimac', 'mher_91@hotmail.com'),
( 'Melissa', 'Castro', 'Rimac', 'melissa@hotmail.com'),
( 'Steve', 'Jobs', 'Luriwashigton', 'stevejobs@apple.com'),
( 'Anonymouse', 'secreteo', 'Your home', 'anonymouse@hotmail.com'),
( 'Anonymouse', 'secret', 'Your home', 'anonymouse@hotmail.com'),
( 'Omar', 'Cuba', 'Your home', 'omar@hotmail.com'),
( 'Antonio', 'Carrillo', 'Your home', 'antonio@hotmail.com');
INSERT INTO `cotizacion` ( `FECHAREGISTRO`, `IMPORTETOTAL`, `IDCLIENTE`) VALUES
('1991-12-14', 450, 1);
select * from detallecotizacion;
INSERT INTO DETALLECOTIZACION(CODIGO,DESCRIPCION,NRODEPIEZAS,PRECIO,IMPORTE,CODCOTIZACION) 
VALUES('000024','MADERA CAOBA',10,2.5,25,1);
INSERT INTO `documento` (`ID`, `TIPO`) VALUES
(1, 'DNI'),
(2, 'PASAPORTE'),
(3, 'RUC'),
(4, 'OTRO');

INSERT INTO `documentoscliente` (`IDCLIENTE`, `IDDOCUMENTO`, `IDENTIFICADOR`) VALUES
(1, 1, '47113327'),
(1, 3, '10471133278'),
(2, 1, '47113337'),
(2, 3, '10471134278'),
(3, 1, '47115527'),
(3, 3, '10471166278'),
(4, 1, '47113437'),
(4, 3, '104711313278'),
(5, 1, '47113312'),
(5, 3, '10471133321')
;
INSERT INTO `unidad` (`ID`, `UMEDIDA`) VALUES
(1, 'CM'),
(2, 'MTS'),
(3, 'PIES');

INSERT INTO PRODUCTO (CODIGO,DESCRIPCION,ESPESOR,ANCHO,LARGO,UNIDAD) VALUES
(2225,'MADERA CAOBA',20,5,10,2),
(2226,'MADERA EBANO',12,5,10,2),
(2227,'MADERA ALAMOS',11,5,10,2),
(2228,'MADARA UCHIHA',10,5,10,2),
(2229,'MADERA TEMPLE',29,5,10,2),
(2230,'MADERA TORNILLO',10,5,10,2),
(2231,'MADERA CLASE 1',10,5,10,2),
(2232,'MADERA CLASE 2',10,5,10,2),
(2233,'MADERA CLASE 3',10,5,10,2),
(2234,'MADERA CLASE 4',10,5,10,2),
(2235,'MADERA CLASE 5',10,5,10,2),
(2236,'MADERA CLASE 6',10,5,10,2),
(2237,'MADERA CLASE 7',10,5,10,2),
(2238,'MADERA CLASE 8',10,5,10,2),
(2239,'MADERA CLASE 9',10,5,10,2),
(2240,'MADERA CLASE 10',10,5,10,2);

INSERT INTO `rol` (`idRol`, `nombre`) VALUES
(1, 'encargadoVentas'),
(2, 'encargadoAlmacen'),
(3, 'gerente'),
(4, 'cajera'),
(5, 'secretaria');



INSERT INTO `usuario` (`idUsuario`, `usuario`, `clave`, `estado`, `fechaCreacion`, `idRol`) VALUES
(4, 'maycol', 'espinoza', 1, '2014-09-16', 1);