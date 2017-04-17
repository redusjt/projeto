package model;

import java.util.ResourceBundle;
import java.util.Scanner;

import dao.ArCondicionadoDAO;

import java.io.File;


public class ArCondicionadoM
{
   private ResourceBundle bundle;
   private ArCondicionadoDAO adao;
   
   public ArCondicionadoM( ResourceBundle bn )
   {
      adao   = new ArCondicionadoDAO();
      bundle = bn;
   }
   
   public String[][] getAllAC()
   {  
      
      String[][]        listaEmpresas = adao.getAllRegisters();
      String[][]        listaAC       = new String[ listaEmpresas.length ][5];
      
      for(int i = 0; i < listaAC.length; i++)
      {
         listaAC[i][0] = listaEmpresas[i][0]; // Coluna Razão Social
         listaAC[i][1] = listaEmpresas[i][1]; // Coluna Conjunto
         listaAC[i][2] = listaEmpresas[i][2]; // Coluna Temperatura Máxima
         listaAC[i][3] = String.valueOf( tempAtualNoConjunto(listaEmpresas[i][1]) );
         listaAC[i][4] = getStatus( Double.parseDouble(listaAC[i][3]), Double.parseDouble(listaAC[i][2]) );  
      }
      
      return listaAC;
   }
   
   public boolean permissaoAlterarAC( long cpf )
   {
      if( adao.getPermissaoAC( cpf ) == 0 )
         return false;
      else
         return true;
   }
   
   public void alterarTemperatura( double t, long cpf )
   {
      adao.alterarTemperatura(t, cpf);
   }
   
   /*public void alterarTemperatura( long cnpj, double t )
   {
      adao.alterarTemperatura(cnpj, t);
   }*/
   
   private double tempAtualNoConjunto( String conjunto )
   {
      double temp = 0.00;
      
      try
      {
         Scanner  temperaturasPorConjunto = new Scanner( new File("temperaturasAtuais.txt") );
      
         while( temperaturasPorConjunto.hasNext() )
         {
            temperaturasPorConjunto.useDelimiter("/");
            String conj = temperaturasPorConjunto.next();
         
            if( conj.equals(conjunto) )
            {
               temperaturasPorConjunto.skip("/");
               temperaturasPorConjunto.reset();
            
               temp = temperaturasPorConjunto.nextDouble();
            }
         
            temperaturasPorConjunto.nextLine(); 
         }
      }
      catch(Exception e)
      {
      }
      
      return temp;
   }
   
   private String getStatus( double tAtual, double tMax)
   {
      if( tAtual > tMax)
      {
         return bundle.getString("Ligado");
      }
      else
      {
         return bundle.getString("Desligado");
      }
   }
   
   
}