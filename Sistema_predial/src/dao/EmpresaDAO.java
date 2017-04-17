package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import connection.AcessoBD;
import transferObject.EmpresaTO;

public class EmpresaDAO {

	AcessoBD bd = new AcessoBD();
	Connection conn = null;

	// Construtor Padrão
	public EmpresaDAO() {
		/* this(0,"","","","",-1); */

	}

	// Cadastrar no banco
	public boolean cadastrar( EmpresaTO empresaTO ) {
		
	      String sqlSelect = "INSERT INTO empresa( empresaCNPJ,"
                  									+ "empresaRazaoSocial,"
                  									+ "empresaConjunto,"
                  									+ "empresaHorarioFuncionamento,"
                  									+ "empresaHorarioFuncionamentoAC,"
                  									+ "empresaTempMaxAC)"
                  									+ "VALUES (?, ?, ?, ?, ?, ?)";
		

		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = bd.obtemConexao();
			// conn.setAutoCommit(false);
			stm = conn.prepareStatement(sqlSelect);

			stm.setLong(1, empresaTO.getCnpj());
			stm.setString(2, empresaTO.getRazaoSocial());
			stm.setString(3, empresaTO.getConjunto());
			stm.setString(4, empresaTO.getHorFunc());
			stm.setString(5, empresaTO.getHorFuncAC());
			stm.setInt(6, empresaTO.getValorMaxAC());

			stm.executeUpdate();
			stm.close();

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Carregar do banco
	public EmpresaTO carregar(long cnpj) {
		String sqlSelect = "SELECT * FROM empresa WHERE empresaCNPJ = ?";
		PreparedStatement stm = null;
		ResultSet rs = null;
		EmpresaTO empresaTO = new EmpresaTO();
		
		try {
			conn = bd.obtemConexao();
			stm = conn.prepareStatement(sqlSelect);
			stm.setLong(1, cnpj);
			rs = stm.executeQuery();
			if (rs.next()) {
				empresaTO.setCnpj(rs.getLong(1));
				empresaTO.setRazaoSocial(rs.getString(2));
				empresaTO.setConjunto(rs.getString(3));
				empresaTO.setHorFunc(rs.getString(4));
				empresaTO.setHorFuncAC(rs.getString(5));
				empresaTO.setValorMaxAC(rs.getInt(6));
			}
			

		} catch (Exception e) {
			System.out.println("Erro na consulta");
		}
		return empresaTO;
	}
	
	public ArrayList<EmpresaTO> consultarTodasEmpresas(){
		
		String sqlSelect = "SELECT empresaRazaoSocial, empresaConjunto, empresaTempMaxAC FROM empresa";
		PreparedStatement stm = null;
		ResultSet rs = null;
		ArrayList<EmpresaTO> listaEmpresas = new ArrayList<EmpresaTO>();
		
		try {
			conn = bd.obtemConexao();
			stm = conn.prepareStatement(sqlSelect);
			rs = stm.executeQuery();
			while (rs.next()) {
				EmpresaTO empTO = new EmpresaTO();
				empTO.setRazaoSocial(rs.getString(1));
				empTO.setConjunto(rs.getString(2));
				empTO.setValorMaxAC(rs.getInt(3));
				listaEmpresas.add(empTO);
			}
			

		} catch (Exception e) {
			System.out.println("Erro na consulta de todas as empresas");
		}
		return listaEmpresas;
	}

	// Carregar todos CNPJs do banco
	public String[] selectAllCNPJ() {
		ArrayList listaEmpresas = new ArrayList(1);
		String sqlSelect = "SELECT empresaCNPJ FROM empresa";
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

		String[] cnpjs = new String[listaEmpresas.size()];
		for (int i = 0; i < listaEmpresas.size(); i++) {
			cnpjs[i] = listaEmpresas.get(i).toString();
		}

		return cnpjs;
	}

	// Excluir do banco
	public boolean excluir(long cnpj) {
		String sqlSelect = "DELETE FROM empresa WHERE empresaCNPJ = ?";
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
	public boolean alterar(EmpresaTO empresaTO) {
		
		String sqlSelect = "UPDATE empresa SET empresaCNPJ = ?, "
							+ "empresaRazaoSocial = ?, "
							+ "empresaConjunto = ?, "
							+ "empresaHorarioFuncionamento = ?, "
							+ "empresaHorarioFuncionamentoAC = ?, "
							+ "empresaTempMaxAC = ? "
							+ "WHERE empresaCNPJ = ?";
		
		PreparedStatement stm = null;
		System.out.println("Classe DAO");      			
      	System.out.println(empresaTO.getCnpj());
      					System.out.println(empresaTO.getRazaoSocial());
      					System.out.println(empresaTO.getConjunto());
      					System.out.println(empresaTO.getHorFunc());
      					System.out.println(empresaTO.getHorFuncAC());
      					System.out.println(empresaTO.getValorMaxAC());
		try {
			conn = bd.obtemConexao();
			stm = conn.prepareStatement(sqlSelect);
			stm.setLong(1, empresaTO.getCnpj());
			stm.setString(2, empresaTO.getRazaoSocial());
			stm.setString(3, empresaTO.getConjunto());
			stm.setString(4, empresaTO.getHorFunc());
			stm.setString(5, empresaTO.getHorFuncAC());
			stm.setInt(6, empresaTO.getValorMaxAC());
			stm.setLong(7, empresaTO.getCnpj());
			System.out.println(stm.toString());
			stm.executeUpdate();
			stm.close();

			return true;
		} catch (Exception j) {

			System.out.println("Erro alterar");
			return false;
		}
	}

}