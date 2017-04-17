package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.ArrayList;

import connection.AcessoBD;

public class CipherDAO
{ 
   AcessoBD bd = new AcessoBD(); 
   Connection conn = null;
   
@SuppressWarnings("rawtypes")
public String[] consultarTodosLogins()
   {
      String sqlSelect = "SELECT usuarioCpf, usuarioSenha, usuarioTipo FROM usuario ORDER BY usuarioCpf ASC";
      PreparedStatement stm = null;
      ResultSet rs = null;
      
      ArrayList listaUsuarios = new ArrayList(1);
      
      try
      {
         conn = bd.obtemConexao();
         //conn.setAutoCommit(false);
         stm = conn.prepareStatement(sqlSelect);         
         rs = stm.executeQuery();
      
         while( rs.next() )
         {
            String usuario = String.valueOf ( rs.getLong(1) ) + "/"
                           + ( rs.getString(2) ) + "/"
                           + String.valueOf ( rs.getInt(3) );
            
            listaUsuarios.add(usuario);
         }
         			
      }
      catch (Exception e)
      {                           
         listaUsuarios.add("Error");
         System.out.println("Puta que paril, que erro é esse?");
      }
      
      String[] l = new String[ listaUsuarios.size() ];
      for(int i = 0; i < listaUsuarios.size(); i++)
      {
         l[i] = listaUsuarios.get(i).toString();
      }
      
      return l;
   }
   
   public static void main(String args[])
   {
      CipherDAO c = new CipherDAO();
      
      String[] l = c.consultarTodosLogins();
      
      for(int i = 0; i < l.length; i++)
      {
         System.out.println( l[i] );
      } 
   }
}
