package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CatracaDAO {
	private String nome, empresa;
	private long cpf;
	private boolean status;
	AcessoBD bd = new AcessoBD();
	Connection conn = null;

	// Construtor Padrão
	public CatracaDAO() {
		nome = "";
		empresa = "";
		cpf = -1;
		status = false;

	}

	// Cadastrar Entrada no banco
	public boolean cadastrarEntrada(long cpf) {
		String sqlSelect = "INSERT INTO acessos (vc_CPF, vc_Nome, vc_Empresa, dt_data, tm_HorarioEnt, bl_Status) values (?,?,?,CURDATE(),CURTIME(),1)";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = bd.obtemConexao();
			// conn.setAutoCommit(false);
			stm = conn.prepareStatement(sqlSelect);

			stm.setLong(1, this.getcpf());
			stm.setString(2, this.getNome());
			stm.setString(3, this.getEmpresa());
			stm.executeUpdate();
			stm.close();

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Carregar do banco
	public boolean carregarDadosUsuario(long cpf) {
		String sqlSelect = "SELECT usuarioCpf,usuarioNome,usuarioEmpresa FROM usuario WHERE usuarioCpf = ?";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = bd.obtemConexao();
			stm = conn.prepareStatement(sqlSelect);
			stm.setLong(1, cpf);
			rs = stm.executeQuery();
			if (rs.next()) {
				this.setcpf(rs.getLong(1));
				this.setNome(rs.getString(2));
				this.setEmpresa(rs.getString(3));
			}
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	// cadatrar Saida
	public boolean cadastrarSaida(long cpf) {
		String sqlSelect = "update acessos set tm_HorarioSaida = CURTIME(), bl_Status= 0 where vc_CPF = ? and bl_status = 1;";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = bd.obtemConexao();
			stm = conn.prepareStatement(sqlSelect);
			stm.setLong(1, cpf);
			stm.executeUpdate();
			stm.close();

			return true;
		} catch (Exception j) {

			return false;
		}
	}

	// status do usuario se 0 ele esta fora se 1 ele esta dentro
	public boolean carregarStatus(long cpf) {
		String sqlSelect = "SELECT bl_Status FROM acessos WHERE vc_CPF = ?";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = bd.obtemConexao();
			stm = conn.prepareStatement(sqlSelect);
			stm.setLong(1, cpf);
			rs = stm.executeQuery();
			if (rs.next()) {
				this.setStatus(rs.getBoolean(1));
			}
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	// Get's e Set's
	public void setcpf(long cpf) {
		this.cpf = cpf;
	}

	public long getcpf() {
		return cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getStatus() {
		return status;
	}

	public void Entrou() {
		status = true;
	}

	public void Saiu() {
		status = false;
	}

}
