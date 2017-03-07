package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmpresaDAO {
	private String razaoSocial, conjunto, horFunc, horFuncAC;
	private long cnpj;
	private int valorMaxAC;
	AcessoBD bd = new AcessoBD();
	Connection conn = null;

	// Construtor Padrão
	public EmpresaDAO() {
		this(0, "", "", "", "", -1);

	}

	// Construtor Parametrizado
	public EmpresaDAO(long cnpj, String razaoSocial, String conjunto, String horFunc, String horFuncAC,
			int valorMaxAC) {
		setCnpj(cnpj);
		setRazaoSocial(razaoSocial);
		setConjunto(conjunto);
		setHorFunc(horFunc);
		setHorFuncAC(horFuncAC);
		setValorMaxAC(valorMaxAC);
	}

	// Cadastrar no banco
	public boolean cadastrar(long cnpj, String rz, String cjt, String hFE, int tempM, String hFA) {
		String sqlSelect = "INSERT INTO empresa (vc_CNPJ, vc_RazaoSoc, vc_Conjunto, vc_horFuncEmp, int_TempMax, vc_horfuncAC, tm_HorarioEnt, tm_HorarioSair) values (?,?,?,?,?,?,?,?)";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = bd.obtemConexao();
			// conn.setAutoCommit(false);
			stm = conn.prepareStatement(sqlSelect);

			stm.setLong(1, cnpj);
			stm.setString(2, rz);
			stm.setString(3, cjt);
			stm.setString(4, hFE);
			stm.setInt(5, tempM);
			stm.setString(6, hFA);
			stm.setString(7, null);
			stm.setString(8, null);
			stm.executeUpdate();
			stm.close();

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Carregar do banco
	public boolean carregar(long cnpj) {
		String sqlSelect = "SELECT * FROM empresa WHERE vc_CNPJ = ?";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = bd.obtemConexao();
			stm = conn.prepareStatement(sqlSelect);
			stm.setLong(1, cnpj);
			rs = stm.executeQuery();
			if (rs.next()) {
				this.setCnpj(rs.getLong(1));
				this.setRazaoSocial(rs.getString(2));
				this.setConjunto(rs.getString(3));
				this.setHorFunc(rs.getString(4));
				this.setValorMaxAC(rs.getInt(5));
				this.setHorFuncAC(rs.getString(6));
			}
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	// Carregar todos CNPJs do banco
	public String[] selectAllCNPJ() {
		ArrayList listaEmpresas = new ArrayList(1);
		String sqlSelect = "SELECT vc_CNPJ FROM empresa";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = bd.obtemConexao();
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();
			while (rs.next()) {
				listaEmpresas.add(rs.getLong(1));
			}
		} catch (Exception e) {
			listaEmpresas.add(0);
		}

		String[] l = new String[listaEmpresas.size()];
		for (int i = 0; i < listaEmpresas.size(); i++) {
			l[i] = listaEmpresas.get(i).toString();
		}

		return l;
	}

	// Excluir do banco
	public boolean excluir(long cnpj) {
		String sqlSelect = "DELETE FROM empresa WHERE vc_CNPJ = ?";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = bd.obtemConexao();
			stm = conn.prepareStatement(sqlSelect);
			stm.setLong(1, cnpj);
			stm.executeUpdate();
			stm.close();
			return true;

		} catch (Exception j) {
			return false;
		}
	}

	// Alterar do banco
	public boolean alterar() {
		String sqlSelect = "UPDATE empresa SET vc_CNPJ=?, vc_RazaoSoc=?, vc_Conjunto=?, vc_horFuncEmp=?, int_TempMax=?, vc_horfuncAC=?, tm_HorarioEnt=?, tm_HorarioSair=? WHERE vc_CNPJ=?";
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = bd.obtemConexao();
			stm = conn.prepareStatement(sqlSelect);
			stm.setLong(1, getCnpj());
			stm.setString(2, getRazaoSocial());
			stm.setString(3, getConjunto());
			stm.setString(4, getHorFunc());
			stm.setInt(5, getValorMaxAC());
			stm.setString(6, getHorFuncAC());
			stm.setString(7, null);
			stm.setString(8, null);
			stm.setLong(9, getCnpj());
			stm.executeUpdate();
			stm.close();

			return true;
		} catch (Exception j) {

			return false;
		}
	}

	// Get's e Set's
	public void setCnpj(long cnpj) {
		this.cnpj = cnpj;
	}

	public long getCnpj() {
		return cnpj;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setConjunto(String conjunto) {
		this.conjunto = conjunto;
	}

	public String getConjunto() {
		return conjunto;
	}

	public void setHorFunc(String horFunc) {
		this.horFunc = horFunc;
	}

	public String getHorFunc() {
		return horFunc;
	}

	public void setHorFuncAC(String horFuncAC) {
		this.horFuncAC = horFuncAC;
	}

	public String getHorFuncAC() {
		return horFuncAC;
	}

	public void setValorMaxAC(int valorMaxAC) {
		this.valorMaxAC = valorMaxAC;
	}

	public int getValorMaxAC() {
		return valorMaxAC;
	}

}