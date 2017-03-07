package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class AcessoArquivoTexto {
	private Scanner lerArquivo;
	private Formatter gravarArquivo;

	public AcessoArquivoTexto() {
		lerArquivo = null;
		gravarArquivo = null;
	}

	public void abrirArquivoParaLeitura(String nomeArquivo) {
		try {
			lerArquivo = new Scanner(new File(nomeArquivo));
		} catch (FileNotFoundException fnfException) {
			System.err.println("Error opening file. File not found!");
		}

	}

	public void abrirArquivoParaEscrita(String nomeArquivo) {
		try {
			gravarArquivo = new Formatter(nomeArquivo);
		} catch (FileNotFoundException fnfException) {
			System.err.println("Error creating file. File is not as expected!");
		} catch (SecurityException seException) {
			System.err.println("Error creating file. You don't have write permission!");
		}

	}

	public void fecharArquivoDeLeitura(Scanner scanner) {
		if (scanner != null) {
			scanner.close();
		}
	}

	public void fecharArquivoDeEscrita(Formatter formatter) {
		if (formatter != null) {
			formatter.close();
		}
	}

	public void gravarDados(String dados, String nomeArquivo) {
		abrirArquivoParaEscrita(nomeArquivo);
		gravarArquivo.format(dados);
		fecharArquivoDeEscrita(gravarArquivo);
	}

	public String lerDados(String nomeArquivo) {
		abrirArquivoParaLeitura(nomeArquivo);
		String dados = "";

		while (lerArquivo.hasNext()) {
			dados += lerArquivo.next() + "\n"; // monta a String exatamente como
												// foi encontrado os dados no
												// arquivo de logins
		}

		fecharArquivoDeLeitura(lerArquivo);
		return dados;
	}

	public byte[] lerBytes(String nomeArquivo) // modo leitura para a classe
												// Decypher. Lê byte por byte
												// conforme indicado na gravação
												// de dados da classe cypher
	{
		abrirArquivoParaLeitura(nomeArquivo);

		ArrayList<String> bytesLidos = new ArrayList(1);

		// monta o ArrayList sendo cada posição deste equivalente a um byte lido
		// do arquivo
		while (lerArquivo.hasNext()) {
			lerArquivo.useDelimiter(";");
			bytesLidos.add(lerArquivo.next());
			lerArquivo.skip(";");
		}

		fecharArquivoDeLeitura(lerArquivo);

		// Transforma o ArrayList em um vetor de byte
		byte[] bTextoCifrado = new byte[bytesLidos.size()];
		for (int i = 0; i < bytesLidos.size(); i++) {
			bTextoCifrado[i] = Byte.valueOf(bytesLidos.get(i));
		}

		return bTextoCifrado;
	}

	public ArrayList<byte[]> lerLinhasDeBytes(String nomeArquivo) // modo
																	// leitura
																	// para a
																	// classe
																	// Decypher.
																	// Lê byte
																	// por byte
																	// conforme
																	// indicado
																	// na
																	// gravação
																	// de dados
																	// da classe
																	// cypher
	{
		abrirArquivoParaLeitura(nomeArquivo);

		// ArrayList<Byte> bytesLidos = new ArrayList(1);
		ArrayList<String> linhasDeBytesLidos = new ArrayList(1);
		ArrayList<byte[]> textoCifrado = new ArrayList(1);

		// monta o ArrayList sendo cada posição deste equivalente a uma linha
		// lida do arquivo
		while (lerArquivo.hasNext()) {
			try {

				linhasDeBytesLidos.add(lerArquivo.next());
				System.out.print(linhasDeBytesLidos.get(0));

			} catch (Exception e) {
				System.out.println("Vai para a puta que o paril");
			}
		}

		fecharArquivoDeLeitura(lerArquivo);

		// monta o ArrayList textoCifrado sendo cada posição deste equivalente a
		// um vetor de bytes contido no ArrayList linhasDeBytesLidos
		for (int i = 0; i < linhasDeBytesLidos.size(); i++) {
			textoCifrado.add(lerBytesDeUmaLinha(linhasDeBytesLidos.get(i)));
		}

		return textoCifrado;
	}

	// -----------------------------------------------
	private byte[] lerBytesDeUmaLinha(String linha) {
		Scanner l = new Scanner(linha);

		ArrayList<String> bytesLidos = new ArrayList(1);

		// monta o ArrayList sendo cada posição deste equivalente a um byte lido
		// da linha

		while (l.hasNext()) {
			try {
				l.useDelimiter(";");
				bytesLidos.add(l.next());
				l.reset();
				l.skip(";");
			} catch (Exception e) {
				System.out.println("FUUUUUUUUCCCK");
			}

		}

		// Transforma o ArrayList em um vetor de byte
		byte[] bBytesDaLinha = new byte[bytesLidos.size()];
		for (int i = 0; i < bytesLidos.size(); i++) {
			bBytesDaLinha[i] = Byte.valueOf(bytesLidos.get(i));
			System.out.print(bBytesDaLinha[i] + "     ");
		}

		return bBytesDaLinha;
	}
	// -----------------------------------------------------------

}
