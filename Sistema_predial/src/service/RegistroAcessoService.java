package service;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import criptografia.Decypher;
import dao.RegistroAcessoDAO;
import model.LoginModel;
import transferObject.RegistroAcessoTO;

public class RegistroAcessoService {
	RegistroAcessoDAO regAceService;

	public boolean entrar(long login, String senha) throws Exception {
		Decypher decifrarCadastros = new Decypher();
		LoginModel loginM = new LoginModel();

		if (loginM.binaria(decifrarCadastros.getUsuario(), login)) {
			if ( decifrarCadastros.getSenhaAt(loginM.getPos()).equals( senha ) ) {
				
				RegistroAcessoDAO regAceDAO = new RegistroAcessoDAO();
				if (regAceDAO.registrarEntrada(login)) {
						return true;
				}
			
			}
		}
		return false;
	}
	public boolean sair(long login, String senha) throws Exception {
		Decypher decifrarCadastros = new Decypher();
		LoginModel loginM = new LoginModel();

		if (loginM.binaria(decifrarCadastros.getUsuario(), login)) {
			if ( decifrarCadastros.getSenhaAt(loginM.getPos()).equals( senha ) ) {
				
				RegistroAcessoDAO regAceDAO = new RegistroAcessoDAO();
				if (regAceDAO.registrarSaida(login)) {
						return true;
				}
			
			}
		}
		return false;
	}

	public JTable consultarPorEmpresa(String empresa) {
		RegistroAcessoDAO regAceDAO = new RegistroAcessoDAO();
		ArrayList<RegistroAcessoTO> listaRegistroAcessos = regAceDAO.consultarPorEmpresa(empresa);

		// Convertendo a lista de registros em JTable
		String[] nomeTabelaRegistros = { "Data", "CPF", "Nome", "Empresa", "Horário Entrada", "Horário Saída" };
		String[][] conteudoTabelaRegistros = new String[listaRegistroAcessos.size()][6];
		int linha = 0;
		for (RegistroAcessoTO raTO : listaRegistroAcessos) {
			conteudoTabelaRegistros[linha][0] = raTO.getData();
			conteudoTabelaRegistros[linha][1] = raTO.getCpfUsuario();
			conteudoTabelaRegistros[linha][2] = raTO.getNomeUsuario();
			conteudoTabelaRegistros[linha][3] = raTO.getNomeEmpresa();
			conteudoTabelaRegistros[linha][4] = raTO.getHorarioEntrada();
			conteudoTabelaRegistros[linha][5] = raTO.getHorarioSaida();
			linha++;
		}

		JTable registros = new JTable(conteudoTabelaRegistros, nomeTabelaRegistros);
		return registros;
	}

	public JTable consultarPorData(String dataInicial, String dataFinal) {
		RegistroAcessoDAO regAceDAO = new RegistroAcessoDAO();
		ArrayList<RegistroAcessoTO> listaRegistroAcessos = regAceDAO.consultarPorData(dataInicial, dataFinal);

		// Convertendo a lista de registros em JTable
		String[] nomeTabelaRegistros = { "Data", "CPF", "Nome", "Empresa", "Horário Entrada", "Horário Saída" };
		String[][] conteudoTabelaRegistros = new String[listaRegistroAcessos.size()][6];
		int linha = 0;
		for (RegistroAcessoTO raTO : listaRegistroAcessos) {
			conteudoTabelaRegistros[linha][0] = raTO.getData();
			conteudoTabelaRegistros[linha][1] = raTO.getCpfUsuario();
			conteudoTabelaRegistros[linha][2] = raTO.getNomeUsuario();
			conteudoTabelaRegistros[linha][3] = raTO.getNomeEmpresa();
			conteudoTabelaRegistros[linha][4] = raTO.getHorarioEntrada();
			conteudoTabelaRegistros[linha][5] = raTO.getHorarioSaida();
			linha++;
		}

		JTable registros = new JTable(conteudoTabelaRegistros, nomeTabelaRegistros);
		return registros;
	}

	public static void main(String[] args) throws Exception, Exception {
		RegistroAcessoService ras = new RegistroAcessoService();
		/*
		 * JTable resultado = ras.consultarPorEmpresa("23997723700");
		 * JOptionPane.showMessageDialog(null, resultado); JTable resultado2 =
		 * ras.consultarPorData("1970/01/01", "1999/01/01");
		 * JOptionPane.showMessageDialog(null, resultado2);
		 */
		System.out.println( ras.sair(Long.parseLong("22222222222"), "Senha2") );
	}
}
