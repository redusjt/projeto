package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.AcessoBD;

import java.sql.ResultSet;

public class ArCondicionadoDAO
{  
   AcessoBD bd = new AcessoBD(); 
   Connection conn = null;
   
   public int contarEmpresas()
   {
      String sqlSelect = "SELECT COUNT(vc_CNPJ) FROM projeto.empresa";
      PreparedStatement stm = null;
      ResultSet rs = null;
      
      try
      {
         conn = bd.obtemConexao();
         //conn.setAutoCommit(false);
         stm = conn.prepareStatement(sqlSelect);         
         rs = stm.executeQuery();
         
         if( rs.next() )
            return rs.getInt(1);			
      }
      catch (Exception e)
      {                           
         System.out.println("Erro na contagem");
      }
      
      return -1;
   }
   
   public String[][] getAllRegisters()
   {
      String[][] listaEmpresas = new String[ contarEmpresas() ][ 3 ];
      
      String sqlSelect = "SELECT vc_RazaoSoc,  vc_Conjunto, int_TempMax FROM projeto.empresa";
      PreparedStatement stm = null;
      ResultSet rs = null;
      
      try
      {
         conn = bd.obtemConexao();
         //conn.setAutoCommit(false);
         stm = conn.prepareStatement(sqlSelect);         
         rs = stm.executeQuery();
         
         int i = 0; //Controla o número de registros do ResultSet
         while( rs.next() )
         {
            listaEmpresas[i][0] = rs.getString(1);
            listaEmpresas[i][1] = rs.getString(2);
            listaEmpresas[i][2] = String.valueOf( rs.getDouble(3) );
            
            i++;
         }
         
      }
      catch (Exception e)
      {                           
         System.out.println("Erro na contagem");
      }
      return listaEmpresas;
   }
   
   public int getPermissaoAC( long cpf )
   {
      String sqlSelect = "SELECT usuarioPermissaoArCond FROM projeto.usuario WHERE usuarioCpf = ?";
      PreparedStatement stm = null;
      ResultSet rs = null;
      
      try
      {
         conn = bd.obtemConexao();
         //conn.setAutoCommit(false);
         stm = conn.prepareStatement(sqlSelect);
         stm.setLong(1,cpf);         
         rs = stm.executeQuery();
         
         if( rs.next() )
            return rs.getInt(1);			
      }
      catch (Exception e)
      {                           
         System.out.println("Erro na busca");
      }
      
      return -1;
   }
   
   public void alterarTemperatura( double t, long cpf )
   {
      long cnpj = pesquisarEmpresa(cpf);
      
      String sqlUpdate = "UPDATE empresa SET empresaTempMaxAC = ? WHERE empresaCNPJ = ?";
      PreparedStatement stm = null;
   
      try
      {
         conn = bd.obtemConexao();
         //conn.setAutoCommit(false);
         stm = conn.prepareStatement(sqlUpdate);         
         
         stm.setDouble(1, t);
         stm.setLong(2, cnpj);
         
         stm.executeUpdate();
      }
      catch (Exception e)
      {                           
         System.out.println("Erro na alteração de temperatura");
      }
   }
   /*public void alterarTemperatura( long cnpj, double t )
   {
      String sqlUpdate = "UPDATE empresa SET int_TempMax = ? WHERE vc_CNPJ = ?";
      PreparedStatement stm = null;
   
      try
      {
         conn = bd.obtemConexao();
         //conn.setAutoCommit(false);
         stm = conn.prepareStatement(sqlUpdate);         
         
         stm.setDouble(1, t);
         stm.setLong(2, cnpj);
         
         stm.executeUpdate();
      }
      catch (Exception e)
      {                           
         System.out.println("Erro na alteração de temperatura");
      }
   }*/
   
   private long pesquisarEmpresa( long cpf )//pesquisa o cnpj da empresa do funcionario com o cpf passado
   {
      String sqlSelect = "SELECT usuarioEmpresa FROM usuario WHERE usuarioCpf = ?";
      PreparedStatement stm = null;
      ResultSet rs = null;
      
      try
      {
         conn = bd.obtemConexao();
         //conn.setAutoCommit(false);
         stm = conn.prepareStatement(sqlSelect);         
         stm.setLong(1, cpf);
         rs = stm.executeQuery();
         
         if( rs.next() )
            return rs.getLong(1);			
      }
      catch (Exception e)
      {                           
         System.out.println("Erro na busca");
      }
      
      return -1;  
   }
}