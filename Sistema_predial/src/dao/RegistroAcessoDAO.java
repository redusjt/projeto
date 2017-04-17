package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.AcessoBD;
import transferObject.RegistroAcessoTO;

public class RegistroAcessoDAO {
	Connection conn = null;
	AcessoBD bd = new AcessoBD();

	public ArrayList<RegistroAcessoTO> consultarPorEmpresa(String empresa) {

		String sql = "SELECT * FROM registroAcesso WHERE regAceNomeEmpresa = ? ORDER BY regAceData";
		PreparedStatement stm = null;
		ResultSet rs;
		ArrayList<RegistroAcessoTO> listaRegistroAcessos = new ArrayList<RegistroAcessoTO>();

		try {
			conn = bd.obtemConexao();
			stm = conn.prepareStatement(sql);
			stm.setString(1, empresa);
			rs = stm.executeQuery();

			while (rs.next()) {
				RegistroAcessoTO registroEncontrado = new RegistroAcessoTO(rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				listaRegistroAcessos.add(registroEncontrado);
			}

			stm.close();

		}

		catch (SQLException e) {
			e.printStackTrace();

		}
		return listaRegistroAcessos;
	}

	public ArrayList<RegistroAcessoTO> consultarPorData(String dataInicial, String dataFinal) {

		String sql = "SELECT * FROM registroacesso WHERE regAceData  BETWEEN ? AND ? ORDER BY regAceData";
		PreparedStatement stm = null;
		ResultSet rs;
		ArrayList<RegistroAcessoTO> listaRegistroAcessos = new ArrayList<RegistroAcessoTO>();

		try {
			conn = bd.obtemConexao();
			stm = conn.prepareStatement(sql);
			stm.setString(1, dataInicial);
			stm.setString(2, dataFinal);
			rs = stm.executeQuery();

			while (rs.next()) {
				RegistroAcessoTO registroEncontrado = new RegistroAcessoTO(rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				listaRegistroAcessos.add(registroEncontrado);
			}

			stm.close();

		}

		catch (SQLException e) {
			e.printStackTrace();

		}
		return listaRegistroAcessos;
	}

	private String consultarStatus(long login) {
		String status = "";
		String sqlConsultar = "SELECT regAceHorEntr, regAceHorSaida FROM registroacesso "
				+ "WHERE regAceCPFUsuario = ? ORDER BY regAceData DESC LIMIT 1"; // Verifica o último
														// registro encontrado
														// com o login(CPF)
														// informado
		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			conn = bd.obtemConexao();
			stm = conn.prepareStatement(sqlConsultar);
			stm.setLong(1, login);
			rs = stm.executeQuery();
			
			if (rs.next()) {
				/*
				 * Verifica se os campos 'regAceHorEntr' 'regAceHorSaida'
				 * possuem algum valor neles: --> Se os dois campos estiverem
				 * vazios ou os dois estiverem preenchidos, significa que o
				 * usuario nunca passou pela catraca ou já entrou e saiu do
				 * prédio --> Se o campo 'regAceHorEntr' estiver preenchida mas
				 * 'regAceHorSaida' não estiver significa que o usuario passou
				 * pela catraca mas ainda não saiu do edifício
				 */
				if ((rs.getString(1).equals("") && rs.getString(2).equals(""))
						|| (!rs.getString(1).equals("") && !rs.getString(2).equals(""))) {

					status = "nuncaEntrou"; // status: usuario nunca entrou, ou
											// seu ultimo registro indica que
											// ele entrou e já saiu;
					// Isto significa que ele pode entrar(passar pela catraca);
				}
				if( !rs.getString(1).equals("") && rs.getString(2).equals("") ){
					status = "entrou";
				}
				
				stm.close();

			}
		} catch (Exception e) {
			System.out.println("Erro consulta status");
		}
		return status;
	}

	public boolean registrarEntrada(long login) {

		String status = this.consultarStatus(login);

		if (status == "nuncaEntrou") {

			String sqlCadastrar = "INSERT INTO registroacesso( regAceData, regAceCPFUsuario, regAceNomeUsuario, regAceNomeEmpresa, regAceHorEntr, regAceHorSaida) VALUES"
					+ "(	curdate()," + "	?,"
					+ "	(SELECT usuario.usuarioNome FROM usuario WHERE usuario.usuarioCpf = ?),"
					+ "	(SELECT usuario.usuarioEmpresa FROM usuario WHERE usuario.usuarioCpf = ?),"
					+ "	date_format(current_time(), '%H:%i'),"
					+ " '' )";
			PreparedStatement stm = null;

			try {
				conn = bd.obtemConexao();
				stm = conn.prepareStatement(sqlCadastrar);
				stm.setLong(1, login);
				stm.setLong(2, login);
				stm.setLong(3, login);
				stm.execute();
				stm.close();
				return true;
			} catch (Exception e) {
				System.out.println("Erro registrar acesso");

			}
		}
		return false;

	}
	
	public boolean registrarSaida(long login) {

		String status = this.consultarStatus(login);

		if ( status.equals("entrou") ) {

			/*String sqlUpdate = "CREATE TABLE IF NOT EXISTS tmp(id INT); " //cria uma tabela temporária para armazenar o ultimo id encontrado do cpf informado
					+ "INSERT INTO tmp (SELECT idregistroAcesso FROM registroacesso WHERE regAceCPFUsuario = ? ORDER BY regAceData DESC LIMIT 1); " //armazena o id
					+ "UPDATE registroacesso SET regAceHorSaida = date_format(current_time(), '%H:%i') WHERE idregistroAcesso = (SELECT id FROM tmp); "
					+ "DROP TABLE tmp";*/
			PreparedStatement stm = null;
			//String sqlCreate = "CREATE TABLE IF NOT EXISTS tmp(id INT)";
			try {
				conn = bd.obtemConexao();

				//Obtém o id do último registro encontrado com o login(cpf) informado
				String sqlSelect = "SELECT idregistroAcesso FROM registroacesso WHERE regAceCPFUsuario = ? ORDER BY regAceData DESC LIMIT 1";
				stm = conn.prepareStatement(sqlSelect);
				stm.setLong(1, login);
				ResultSet rs = stm.executeQuery();
				int idUltimoRegistro = 0;
				if(rs.next())
					idUltimoRegistro = rs.getInt(1);
				stm.close();
				System.out.println(idUltimoRegistro);
				//
				
				//grava o horário de saída no último registro, este encontrado pelo id, do usuario
				String sqlUpdate = "UPDATE registroacesso SET regAceHorSaida = date_format(current_time(), '%H:%i') WHERE idregistroAcesso = ?"; 
				stm = conn.prepareStatement(sqlUpdate);
				stm.setInt(1, idUltimoRegistro);
				stm.executeUpdate();
				stm.close();
				
				return true;
			} catch (Exception e) {
				System.out.println("Erro registrar saida\n" + stm.toString());

			}
		}
		return false;

	}

	public static void main(String[] args) {
		RegistroAcessoDAO test = new RegistroAcessoDAO();
		/*
		 * ArrayList<RegistroAcessoTO> lista =
		 * test.consultarPorEmpresa("23997723700");
		 * 
		 * for (RegistroAcessoTO r : lista) { System.out.println(r.toString());
		 * System.out.println("Registro1\n"); }
		 */

		/*
		 * ArrayList<RegistroAcessoTO> lista2 =
		 * test.consultarPorData("1970/01/01", "2000/01/01");
		 * System.out.println("Consulta DATA"); 
		 * for (RegistroAcessoTO r : lista2){
		 *  System.out.println(r.toString());
		 * System.out.println("Registro1\n"); }
		 * 
		 */
		//System.out.println(test.registrarEntrada(Long.parseLong("33333333333")));
		System.out.println(test.registrarSaida(Long.parseLong("44444444444")));
	}
}
