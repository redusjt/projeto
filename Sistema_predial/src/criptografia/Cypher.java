package criptografia;

import java.io.File;

import java.util.ArrayList;

import connection.AcessoArquivoTexto;
import dao.CipherDAO;

/*
  Classe com a fun��o de cifrar as informa��es do arquivo de texto contendo os logins, e assim
  salv�-los em um novo arquivo cifrado conforme os m�todos da classe CryptoRSA.
*/

public class Cypher
{
   
   public static void main(String[] args) throws Exception
   {
      AcessoArquivoTexto aat                    = new AcessoArquivoTexto();
      CipherDAO          cdao                   = new CipherDAO();
      String[]           vLinhasTextoClaro      = cdao.consultarTodosLogins();
      byte[]             bLinhaTextoClaro       = null;      
      ArrayList<byte[]>  linhasEmBytes          = new ArrayList(vLinhasTextoClaro.length);
      
      /*Montando um ArrayList que cont�m os vetores de bytes obtidos de cada linha lida no arquivo texto com os logins.
       *Isto evita que um vetor de bytes n�o consiga armazenar todo o texto de um arquivo. 
      */
      for(int i = 0; i < vLinhasTextoClaro.length; i++)
      {
         bLinhaTextoClaro = vLinhasTextoClaro[i].getBytes("ISO-8859-1");
         linhasEmBytes.add(bLinhaTextoClaro);     
      }
      
      CryptoRSA crsa = new CryptoRSA();
      crsa.geraParDeChaves( new File("publicK.pubK"), new File("privateK.priK") );
      
      /*
       *cifra linha a linha, ou seja, uma posi��o do ArrayList linhasEmBytes, com a chave publica criada com o metodo geraParDeChaves e monta
       *uma String para ser gravada no arquivo acesso.txt 
       */
      String sTextoCifrado   = "";
      byte[] bLinhaCifrada   = null;
      
      for(int i = 0; i < linhasEmBytes.size(); i++)
      {      
         crsa.geraCifra( linhasEmBytes.get(i), new File("publicK.pubK") );
         bLinhaCifrada = crsa.getTextoCifrado();
         
         //adiciona a linha que foi cifrada na String que ser� gravada no arquivo texto
         for(int j = 0; j < bLinhaCifrada.length; j++)
         {
            sTextoCifrado += bLinhaCifrada[j] + ";"; // cada byte est� sendo separado com um ';' para ser mais f�cil a leitura do mesmo posteriormente
         }
         sTextoCifrado += "\n";   
      }
      
      aat.gravarDados( sTextoCifrado, "acesso.txt" );   
   }
}