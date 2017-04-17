CREATE TABLE `sistema_predial`.`registroacesso` (
  `idregistroAcesso` INT NOT NULL AUTO_INCREMENT,
  `regAceData` DATE NULL,
  `regAceCPFUsuario` BIGINT(11) NULL,
  `regAceNomeUsuario` VARCHAR(45) NULL,
  `regAceNomeEmpresa` VARCHAR(45) NULL,
  `regAceHorEntr` VARCHAR(5) NULL,
  `regAceHorSaida` VARCHAR(5) NULL,
  PRIMARY KEY (`idregistroAcesso`) );