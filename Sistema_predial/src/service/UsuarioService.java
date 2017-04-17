package service;

import dao.UsuarioDAO;
import transferObject.UsuarioTO;

public class UsuarioService {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	UsuarioTO usuarioTO;
	
	public boolean cadastrarUsuario(int tipo, String nome, String dataNasc, String endereco, String bairro, long telefone, String senha,
		String empresa, String horEntrada, String horSaida, long cpf, boolean permissaoAC) {
		
		UsuarioTO usuarioTO = new UsuarioTO(tipo, nome, dataNasc, endereco, bairro, telefone, senha, empresa, horEntrada, horSaida, cpf, permissaoAC);
		if (usuarioDAO.cadastrarUsuario(usuarioTO))
			return true;
		else
			return false;
	}

	public UsuarioTO consultarUsuario(long cpf) {
		usuarioTO = usuarioDAO.consultarUsuario(cpf);
		return usuarioTO;
	}

	public boolean alterarUsuario(int tipo, String nome, String dataNasc, String endereco, String bairro, long telefone, String senha,
			String empresa, String horEntrada, String horSaida, long cpf, boolean permissaoAC){
	
		UsuarioTO usuarioTO = new UsuarioTO(tipo, nome, dataNasc, endereco, bairro, telefone, senha, empresa, horEntrada, horSaida, cpf, permissaoAC);
		
		if (usuarioDAO.alterarUsuario(cpf, usuarioTO))
			return true;
		else
			return false;
	}

	public boolean excluirUsuario(long cpf) {
		if (usuarioDAO.excluirUsuario(cpf))
			return true;
		else
			return false;
	}
}
