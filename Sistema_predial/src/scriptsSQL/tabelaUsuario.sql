CREATE TABLE `usuario` (
  `usuarioCpf` BIGINT(11) NOT NULL ,
  `usuarioTipo` INT NULL, 
  `usuarioNome` VARCHAR(30) NULL ,
  `usuarioDataNasc` DATE NULL,
  `usuarioEndereco` VARCHAR(30) NULL,
  `usuarioBairro` VARCHAR(30) NULL,
  `usuarioTelefone` BIGINT(10) NULL,
  `usuarioSenha` VARCHAR(7) NOT NULL,
  `usuarioEmpresa` VARCHAR(30) NULL,
  `usuarioHorarioAcessoInicial` VARCHAR(5) NULL,
  `usuarioHorarioAcessoFinal` VARCHAR(5) NULL,
  `usuarioPermissaoArCond` INT NULL,
  PRIMARY KEY (`usuarioCpf`));