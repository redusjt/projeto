INSERT INTO sistema_predial.empresa VALUES
	(23997723700, 'Ricardo Ltda',	'A', '08:00 - 17:30', '08:00 - 17:30', 20),
    (23997723750, 'Richard Ltda',	'B', '09:00 - 17:30', '10:00 - 17:30', 21),
    (23997723770, 'Ricciardo Ltda', 'C', '10:00 - 17:30', '11:00 - 17:30', 22),
    (23997723780, 'Daniel Ltda',	'D', '08:00 - 17:30', '08:00 - 17:30', 23),
    (23997723790, 'Renato Ltda',	'E', '09:00 - 17:30', '09:00 - 17:30', 25);
    
INSERT INTO sistema_predial.usuario VALUES
	(11111111111, 0, 'Ricardo', 	'1990/03/25', 'Endereço1', 'Bairro1', 1111112222, 'Senha1', 23997723700, '08:00', '17:30', 0),
    (22222222222, 1, 'Richard', 	'1980/03/26', 'Endereço2', 'Bairro2', 1211113333, 'Senha2', null,		 null,	  null,	   1),
    (33333333333, 2, 'Ricciardo', 	'1970/04/27', 'Endereço3', 'Bairro3', 1311114444, 'Senha3', null,		 null,	  null,	   1),
    (44444444444, 2, 'Daniel', 		'1960/12/05', 'Endereço4', 'Bairro4', 1411112555, 'Senha4', null,		 null,	  null,	   1),
    (55555555555, 2, 'Renato', 		'1950/5/01',  'Endereço5', 'Bairro5', 1511112667, 'Senha5', null,		 null,	  null,	   1);
    
INSERT INTO sistema_predial.registroacesso (regAceCPFUsuario, regAceNomeUsuario, regAceData, regAceNomeEmpresa, regAceHorEntr, regAceHorSaida) VALUES
	(11111111111, 'Ricardo', '	1990/03/25',  23997723700, '08:00', '17:30'),
    (11111111111, 'Ricardo', '	1990/03/26',  23997723700, '08:00', '11:30'),
    (11111111111, 'Ricardo', '	1990/03/27',  23997723700, '08:00', '14:30'),
    (22222222222, 'Richard', 	'1980/03/26', 23997723700,	'08:00', '17:30'),
    (33333333333, 'Ricciardo', 	'1970/04/27', 23997723700, '08:00', '17:30'),
    (44444444444, 'Daniel', 	'1960/12/05', 23997723700, '08:00', '17:30'),
    (55555555555, 'Renato', 	'1950/5/01',  23997723700, '08:00', '17:30');