package Controller;

import java.io.File;
import java.util.ArrayList;

import Controller.AcessoArquivoTexto;
import Controller.CryptoRSA;
import DAO.CipherDAO;

public class Cypher {

	public static void main(String[] args) throws Exception {
		AcessoArquivoTexto aat = new AcessoArquivoTexto();
		CipherDAO cdao = new CipherDAO();
		String[] vLinhasTextoClaro = cdao.consultarTodosLogins();
		byte[] bLinhaTextoClaro = null;
		ArrayList<byte[]> linhasEmBytes = new ArrayList(vLinhasTextoClaro.length);

		/*
		 * Montando um ArrayList que contém os vetores de bytes obtidos de cada
		 * linha lida no arquivo texto com os logins. Isto evita que um vetor de
		 * bytes não consiga armazenar todo o texto de um arquivo.
		 */
		for (int i = 0; i < vLinhasTextoClaro.length; i++) {
			bLinhaTextoClaro = vLinhasTextoClaro[i].getBytes("ISO-8859-1");
			linhasEmBytes.add(bLinhaTextoClaro);
		}

		CryptoRSA crsa = new CryptoRSA();
		crsa.geraParDeChaves(new File("publicK.pubK"), new File("privateK.priK"));

		/*
		 * cifra linha a linha, ou seja, uma posição do ArrayList linhasEmBytes,
		 * com a chave publica criada com o metodo geraParDeChaves e monta uma
		 * String para ser gravada no arquivo acesso.txt
		 */
		String sTextoCifrado = "";
		byte[] bLinhaCifrada = null;

		for (int i = 0; i < linhasEmBytes.size(); i++) {
			crsa.geraCifra(linhasEmBytes.get(i), new File("publicK.pubK"));
			bLinhaCifrada = crsa.getTextoCifrado();

			// adiciona a linha que foi cifrada na String que será gravada no
			// arquivo texto
			for (int j = 0; j < bLinhaCifrada.length; j++) {
				sTextoCifrado += bLinhaCifrada[j] + ";"; // cada byte está sendo
															// separado com um
															// ';' para ser mais
															// fácil a leitura
															// do mesmo
															// posteriormente
			}
			sTextoCifrado += "\n";
		}

		aat.gravarDados(sTextoCifrado, "acesso.txt");
	}
}