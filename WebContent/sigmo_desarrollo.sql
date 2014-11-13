

drop schema sigmo_desarrollo;
create schema sigmo_desarrollo;
use sigmo_desarrollo;



CREATE TABLE IF NOT EXISTS `sigmo_desarrollo`.`cliente` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(30) NULL DEFAULT NULL,
  `APELLIDOS` VARCHAR(30) NULL DEFAULT NULL,
  `DIRECCION` VARCHAR(100) NULL DEFAULT NULL,
  `EMAIL` VARCHAR(30) NULL DEFAULT NULL,
  `razonSocial` VARCHAR(100) NULL DEFAULT NULL,
  `telefono1` VARCHAR(13) NULL DEFAULT NULL,
  `telefono2` VARCHAR(13) NULL DEFAULT NULL,
  `NRODOCUMENTO` VARCHAR(20) NULL DEFAULT NULL,
  `TIPODOCUMENTO` INT(1) NULL DEFAULT NULL,
  `TIPOPERSONA` INT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS `cotizacion` (
  `CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `FECHAREGISTRO` date DEFAULT NULL,
  `IMPORTETOTAL` decimal(15,2) DEFAULT NULL,
  `IDCLIENTE` int(11) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`),
  KEY `FK_CLIENTE` (`IDCLIENTE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `pedido` (
  `CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `FECHAREGISTRO` date DEFAULT NULL,
  `IDCOTIZACION` int(11) not null,  
  PRIMARY KEY (`CODIGO`),
  KEY `FK_COTIZACION` (`IDCOTIZACION`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE IF NOT EXISTS `detallecotizacion` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODIGO` varchar(20) DEFAULT NULL,
  `DESCRIPCION` varchar(60) DEFAULT NULL,
  `NRODEPIEZAS` int(11) DEFAULT NULL,
  `PRECIO` decimal(10,2) DEFAULT NULL,
  `IMPORTE` decimal(15,2) DEFAULT NULL,
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
  `UNIDAD` int(11) DEFAULT NULL,
  `DESCRIPCION` varchar(60) NOT NULL,
  `PRECIOUNIT` decimal(10,2) DEFAULT NULL,
  `MONEDA` int(11) DEFAULT 1,
  PRIMARY KEY (`CODIGO`),
  KEY `FK_UNIDAD` (`UNIDAD`),
  KEY `FK_MONEDA` (`MONEDA`)
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


CREATE TABLE IF NOT EXISTS `moneda` (
  `ID` int(11) NOT NULL,
  `UMON` varchar(10) NOT NULL,
  `UMONEDA` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




ALTER TABLE `cotizacion`
  ADD CONSTRAINT `FK_CLIENTE` FOREIGN KEY (`IDCLIENTE`) REFERENCES `cliente` (`ID`);

ALTER TABLE `pedido`
  ADD CONSTRAINT `FK_COTIZACION` FOREIGN KEY (`IDCOTIZACION`) REFERENCES `cotizacion` (`CODIGO`);

ALTER TABLE `detallecotizacion`
  ADD CONSTRAINT `FK_CODCOTIZACION` FOREIGN KEY (`CODCOTIZACION`) REFERENCES `cotizacion` (`CODIGO`);


ALTER TABLE `documentoscliente`
  ADD CONSTRAINT `FK_IDCLIENTE` FOREIGN KEY (`IDCLIENTE`) REFERENCES `cliente` (`ID`),
  ADD CONSTRAINT `FK_IDDOCUMENTO` FOREIGN KEY (`IDDOCUMENTO`) REFERENCES `documento` (`ID`);


ALTER TABLE `producto`
  ADD CONSTRAINT `FK_UNIDAD` FOREIGN KEY (`UNIDAD`) REFERENCES `unidad` (`ID`),
  ADD CONSTRAINT `FK_MONEDA` FOREIGN KEY (`MONEDA`) REFERENCES `moneda` (`ID`);


ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idRol`) REFERENCES `rol` (`idRol`);

CREATE TABLE IF NOT EXISTS `sigmo_desarrollo`.`pago` (
  `codPago` INT(11) NOT NULL,
  `fecha` DATE NULL DEFAULT NULL,
  `estado` INT(11) NULL DEFAULT NULL,
  `codCotizacion` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`codPago`),
  INDEX `fk_codigo_pago_idx` (`codCotizacion` ASC),
  CONSTRAINT `fk_codigo_pago`
    FOREIGN KEY (`codCotizacion`)
    REFERENCES `sigmo_desarrollo`.`cotizacion` (`CODIGO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;  
  
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
INSERT INTO detallecotizacion(CODIGO,DESCRIPCION,NRODEPIEZAS,PRECIO,IMPORTE,CODCOTIZACION) 
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

INSERT INTO `moneda` (`ID`, `UMON`,`UMONEDA`) VALUES
(1, 'S/.','NUEVOS SOLES'),
(2, '$/.','DOLARES AMERICANOS'),
(3, '€/.','EUROS AMERINDIOS');

INSERT INTO producto (CODIGO,DESCRIPCION,PRECIOUNIT,UNIDAD) VALUES
(2225,'MADERA CAOBA  3 X 9 X 5 MTS',20.5,2),
(2226,'MADERA EBANO  3 X 8 X 5 MTS',20.5,2),
(2227,'MADERA ALAMOS 3 X 4 X 5 MTS',20.5,2),
(2228,'MADERA UCHIHA 3 X 2 X 5 MTS',20.5,2),
(2229,'MADERA TEMPLE 1 X 1 X 1 MTS',20.5,2),
(2230,'MADERA TORNILLO 3 X 3 X 3 MTS',30.5,2),
(2231,'MADERA CLASE 1 4.5 X 3.5 X 2.5 PIES',30.5,2),
(2232,'MADERA CLASE 2 3 X 4 X 5 PIES',120.5,2),
(2233,'MADERA CLASE 3 4 X 5 X 9 PIES',120.5,2),
(2234,'MADERA CLASE 4 30 X 12 X 14 CMS',20.8,2),
(2235,'MADERA CLASE 5 20 X 10 X 12 CMS',589.08,2),
(2236,'MADERA CLASE 6 300 X 10 X 100 CMS',120,2),
(2237,'MADERA CLASE 7 200 X 100 X 200 CMS',1200,2),
(2238,'MADERA CLASE 8',1400,2),
(2239,'MADERA CLASE 9',1500,2),
(2240,'MADERA CLASE 10',1800,2);

INSERT INTO `rol` (`idRol`, `nombre`) VALUES
(1, 'encargadoVentas'),
(2, 'encargadoAlmacen'),
(3, 'gerente'),
(4, 'cajera'),
(5, 'secretaria');



INSERT INTO `usuario` (`idUsuario`, `usuario`, `clave`, `estado`, `fechaCreacion`, `idRol`) VALUES
(4, 'maycol', 'espinoza', 1, '2014-09-16', 1),
(5, 'gabriela', 'argume', 1, '2014-09-16', 4),
(6, 'antonio', 'carrillo', 1, '2014-09-16', 2),
(7, 'omar', 'cuba', 1, '2014-09-16', 3),
(8, 'freddy', 'pineda', 1, '2014-09-16', 5);