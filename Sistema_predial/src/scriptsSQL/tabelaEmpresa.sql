CREATE TABLE `empresa` (
  `empresaCNPJ` BIGINT(11) NOT NULL ,
  `empresaRazaoSocial` VARCHAR(30) NULL ,
  `empresaConjunto` VARCHAR(30) NULL,
  `empresaHorarioFuncionamento` VARCHAR(13) NULL,
  `empresaHorarioFuncionamentoAC` VARCHAR(13) NULL,
  `empresaTempMaxAC` int NULL,
  PRIMARY KEY (`empresaCNPJ`));

