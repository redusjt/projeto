package criptografia;

import java.io.File;

import java.util.Scanner;

import connection.AcessoArquivoTexto;

import java.util.ArrayList;

/*
  Classe com a fun��o de decifrar as informa��es do arquivo contendo os logins cifrados, conforme os m�todos da classe CryptoRSA
  e assim retornar dois vetores: um contendo os usu�rios e outro contendo as senhas.
*/
public class Decypher
{
   private long[]    usuario;
   private String[]  senha;  
   private int[]     tipo;  
   
   public Decypher() throws Exception
   {
      AcessoArquivoTexto aat              = new AcessoArquivoTexto();
      ArrayList<byte[]>  textoCifrado     = aat.lerLinhasDeBytes("acesso.txt");  
      byte[]             bTextoDecifrado  = null;
      String             sLinhaDecifrada;
      String             sTextoDecifrado  = "";    
      
      CryptoRSA crsa = new CryptoRSA();
      
      for(int i = 0; i < textoCifrado.size(); i++)
      {
         crsa.geraDecifra( textoCifrado.get(i), new File("privateK.priK") );
         bTextoDecifrado = crsa.getTextoDecifrado();
         sLinhaDecifrada = new String( bTextoDecifrado, "ISO-8859-1" );
         
         sTextoDecifrado += sLinhaDecifrada + "\n";
         
      }
      System.out.println(sTextoDecifrado);
      
      //Transformando o texto decifrado em tr�s vetores: um contendo somente os usu�rios, um somente as senhas e outro com apenas os tipos de usu�rios
      Scanner            lerString = new Scanner( sTextoDecifrado );
      ArrayList<Long>    u         = new ArrayList(1);
      ArrayList<String>  s         = new ArrayList(1);
      ArrayList<Integer> t         = new ArrayList(1);
      
      while( lerString.hasNext() )
      {
         lerString.useDelimiter("/");
         u.add( Long.parseLong( lerString.next() ) );
        
         lerString.skip("/"); //pula o antigo delimitador para este n�o aparecer na pr�xima leitura de String
         
         s.add( lerString.next() );
         
         lerString.reset(); //volta para o delimitador padr�o
         lerString.skip("/");
         
         t.add( Integer.parseInt( lerString.next() ) );
         
         lerString.reset();
         lerString.nextLine();
      }
      
      //Atribuindo cada valor dos ArrayList u e s em seus respectivos vetores atributos desta classe
      usuario = new long[ u.size() ];
      senha   = new String[ s.size() ];
      tipo    = new int[ t.size() ];
      
      for(int i = 0; i < u.size(); i++ )
      {
         usuario[i] = u.get(i);
         senha[i]   = s.get(i);
         tipo[i]   = t.get(i);
      }
      
   } 
   
   public long[] getUsuario()
   {
      return usuario;
   }
   public String getUsuarioAt(int pos)
   {
      return String.valueOf(usuario[pos]);
   }
   public String[] getSenha()
   {
      return senha;
   }
   public String getSenhaAt(int pos)
   {
      return senha[pos];
   }
   public int[] getTipo()
   {
      return tipo;
   }
   public int getTipoAt(int pos)
   {
      return tipo[pos];
   }
   
   //apenas para teste da classe Decypher
   public static void main(String[] args)
   {
      try
      {
         Decypher d = new Decypher();
         long[] u = d.getUsuario();
         String[] s = d.getSenha();
         int[] t = d.getTipo();
      
         for(int i = 0; i < u.length; i++)
         {
            System.out.println("CPF(Usu�rio): " + u[i] + " Senha: " + s[i] + " Tipo: " + t[i]);
         }
      }
      catch(Exception e)
      {
         System.out.println("What the #@$%@$% is going on here???");
      }
   }
}