package service;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import dao.ArCondicionadoDAO;
import dao.EmpresaDAO;
import dao.UsuarioDAO;
import transferObject.EmpresaTO;
import transferObject.UsuarioTO;

import java.io.File;

public class ArCondicionadoService {
	private ResourceBundle bundle;
	private EmpresaDAO edao;
	private ArCondicionadoDAO adao;

	public ArCondicionadoService( ResourceBundle bn )
	   {
	      edao   = new EmpresaDAO();
	      adao = new ArCondicionadoDAO();
	      bundle = bn;
	   }

	public String[][] consultarTodosAC() {

		ArrayList<EmpresaTO> listaEmpresas = edao.consultarTodasEmpresas();
		String[][] listaAC = new String[listaEmpresas.size()][5];
		
		int linha = 0;
		for(EmpresaTO emp : listaEmpresas){
			listaAC[linha][0] = emp.getRazaoSocial(); // Coluna Razão Social
			listaAC[linha][1] = emp.getConjunto(); // Coluna Conjunto
			listaAC[linha][2] = String.valueOf( emp.getValorMaxAC() ); // Coluna Temperatura Máxima
			listaAC[linha][3] = String.valueOf( tempAtualNoConjunto( emp.getConjunto() ) );
			listaAC[linha][4] = getStatus(Double.parseDouble( listaAC[linha][3] ), Double.parseDouble( listaAC[linha][2]) );
			linha++;
		}
		return listaAC;
	}

	public boolean permissaoAlterarAC(long cpf) {
		UsuarioDAO udao = new UsuarioDAO();
		UsuarioTO uTO = udao.consultarUsuario(cpf);
				
		if ( uTO.getPermissaoAC())
			return true;
		else
			return false;
	}

	public void alterarTemperatura(double t, long cpf) {
		
		adao.alterarTemperatura(t, cpf);
	}

	public void alterarTemperatura(long cnpj, double t) {
		adao.alterarTemperatura(t, cnpj);
	}

	private double tempAtualNoConjunto(String conjunto) {
		double temp = 0.00;

		try {
			Scanner temperaturasPorConjunto = new Scanner(new File("temperaturasAtuais.txt"));

			while (temperaturasPorConjunto.hasNext()) {
				temperaturasPorConjunto.useDelimiter("/");
				String conj = temperaturasPorConjunto.next();

				if (conj.equals(conjunto)) {
					temperaturasPorConjunto.skip("/");
					temperaturasPorConjunto.reset();

					temp = temperaturasPorConjunto.nextDouble();
				}

				temperaturasPorConjunto.nextLine();
			}
		} catch (Exception e) {
		}

		return temp;
	}

	private String getStatus(double tAtual, double tMax) {
		if (tAtual > tMax) {
			return bundle.getString("Ligado");
		} else {
			return bundle.getString("Desligado");
		}
	}

}
