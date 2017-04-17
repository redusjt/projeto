package model;

import dao.UsuarioDAO;
import transferObject.UsuarioTO;

public class UsuarioM {
	private String nome, dataNasc, endereco, bairro, senha, empresa, horEntrada, horSaida;
	private int tipo;
	private long cpf, telefone;
	private boolean permissaoAC;

	// Construtor Padrão
	public UsuarioM() {
		this(-1, "", "", "", "", 0000, "", "", "00:00", "00:00", 0000, true);
	}
	
	public UsuarioM(String nome, String senha){
		setNome(nome);
		setSenha(senha);
	}

	// Construto Parametrizado
	public UsuarioM(int tipo, String nome, String dataNasc, String endereco, String bairro, long telefone, String senha,
			String empresa, String horEntrada, String horSaida, long cpf, boolean permissaoAC) {
		setTipo(tipo);
		setNome(nome);
		setDataNasc(dataNasc);
		setEndereco(endereco);
		setBairro(bairro);
		setTelefone(telefone);
		setSenha(senha);
		setEmpresa(empresa);
		setHorEntrada(horEntrada);
		setHorSaida(horSaida);
		setCpf(cpf);
		setPermissaoAC(permissaoAC);
	}

	// Get's e Set's
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setHorEntrada(String horEntrada) {
		this.horEntrada = horEntrada;
	}

	public String getHorEntrada() {
		return horEntrada;
	}

	public void setHorSaida(String horSaida) {
		this.horSaida = horSaida;
	}

	public String getHorSaida() {
		return horSaida;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public long getCpf() {
		return cpf;
	}

	public void setPermissaoAC(boolean permissaoAC) {
		this.permissaoAC = permissaoAC;
	}

	public boolean getPermissaoAC() {
		return permissaoAC;
	}

	public boolean cadastrarUsuario() {
		UsuarioTO usuarioTO = new UsuarioTO(getTipo(), getNome(), getDataNasc(), getEndereco(), getBairro(),
				getTelefone(), getSenha(), getEmpresa(), getHorEntrada(), getHorSaida(), getCpf(), getPermissaoAC());

		UsuarioDAO usuarioDAO = new UsuarioDAO();

		if (usuarioDAO.cadastrarUsuario(usuarioTO))
			return true;
		else
			return false;

	}

	public void consultarUsuario(long cpf) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		UsuarioTO usuarioTO = usuarioDAO.consultarUsuario(cpf);

		if (usuarioTO.getTipo() != -1) {
			setTipo(usuarioTO.getTipo());
			setNome(usuarioTO.getNome());
			setDataNasc(usuarioTO.getDataNasc());
			setEndereco(usuarioTO.getEndereco());
			setBairro(usuarioTO.getBairro());
			setTelefone(usuarioTO.getTelefone());
			setSenha(usuarioTO.getSenha());
			setEmpresa(usuarioTO.getEmpresa());
			setHorEntrada(usuarioTO.getHorEntrada());
			setHorSaida(usuarioTO.getHorSaida());
			setPermissaoAC(usuarioTO.getPermissaoAC());
		}
	}

	public boolean alterarUsuario(long cpf) {
		UsuarioTO usuarioTO = new UsuarioTO(getTipo(), getNome(), getDataNasc(), getEndereco(), getBairro(),
				getTelefone(), getSenha(), getEmpresa(), getHorEntrada(), getHorSaida(), cpf, getPermissaoAC());
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.alterarUsuario(cpf, usuarioTO))
			return true;
		else
			return false;
	}

	public boolean excluirUsuario(long cpf) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.excluirUsuario(cpf))
			return true;
		else
			return false;
	}
}
