package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.AcessoBD;

public class RegistroDeAcessoDAO
{

   Connection conn = null;
   AcessoBD bd = new AcessoBD(); 

//construtor

   public RegistroDeAcessoDAO()
   {

   }
 
 
   public String getAllAcessos(){
      
      String sql = "SELECT * FROM acessos order by dt_data";
      PreparedStatement stm = null;
      ResultSet rs;
			String resultado = "";
   	
      try{
      conn = bd.obtemConexao();
      stm = conn.prepareStatement(sql);
      rs = stm.executeQuery();
				
         while (rs.next()){
           
  				resultado += "\n Nome:"+rs.getString("vc_nome")+"\n  CPF:"+rs.getString("vc_CPF")+"\n  Empresa:"+rs.getString("vc_Empresa")+"\n  Data:"+rs.getString("dt_data")+" \n  Horario:"+rs.getString("tm_HorarioEnt")+" Até "+rs.getString("tm_HorarioSaida")+"\n";          
	           
         }
				
         stm.close();
      
      }
			
      catch(SQLException e){
         e.printStackTrace();         
      
      }

			
			return resultado;
   
   
   }

   public String getAcessosEmp(String emp){
      
      String sql = "SELECT * FROM acessos  where vc_Empresa = ? order by dt_data";
      PreparedStatement stm = null;
      ResultSet rs;
      String resultado = "";
      
      try{
      conn = bd.obtemConexao();
      stm = conn.prepareStatement(sql);
      stm.setString(1,emp);
      rs = stm.executeQuery();
         
         while (rs.next()){
           
            resultado += "\n Nome:"+rs.getString("vc_nome")+"\n  CPF:"+rs.getString("vc_CPF")+"\n  Empresa:"+rs.getString("vc_Empresa")+"\n  Data:"+rs.getString("dt_data")+" \n  Horario:"+rs.getString("tm_HorarioEnt")+" Até "+rs.getString("tm_HorarioSaida")+"\n";          
           
         }
         
         stm.close();
      
      }
      
      catch(SQLException e){
         e.printStackTrace();         
      
      }

      
      return resultado;
   
   
   }
   public String getAcessosData(String data1,String data2){
      
      String sql = "SELECT * FROM acessos  where between dt_data = ? and ? order by dt_data";
      PreparedStatement stm = null;
      ResultSet rs;
      String resultado = "";
      
      try{
      conn = bd.obtemConexao();
      stm = conn.prepareStatement(sql);
      stm.setString(1,data1);
      stm.setString(2,data2);
      rs = stm.executeQuery();
         
         while (rs.next()){
           
            resultado += "\n Nome:"+rs.getString("vc_nome")+"\n  CPF:"+rs.getString("vc_CPF")+"\n  Empresa:"+rs.getString("vc_Empresa")+"\n  Data:"+rs.getString("dt_data")+" \n  Horario:"+rs.getString("tm_HorarioEnt")+" Até "+rs.getString("tm_HorarioSaida")+"\n";          
           
         }
         
         stm.close();
      
      }
      
      catch(SQLException e){
         e.printStackTrace();         
      
      }

      
      return resultado;
   
   
   }
 
 
 
}