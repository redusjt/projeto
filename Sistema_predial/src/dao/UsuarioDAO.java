package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.AcessoBD;
import transferObject.UsuarioTO;

import java.sql.ResultSet;

public class UsuarioDAO
{
   /*private String nome, dataNasc, endereco, bairro, senha, empresa, horEntrada, horSaida;
   private int tipo;
   private long cpf, telefone;
   private boolean permissaoAC;*/
   
   AcessoBD bd = new AcessoBD(); 
   Connection conn = null;
   
   //Construtor Padrão
   public UsuarioDAO()
   {
      //this(-1,"","","","",0000,"", "","00:00","00:00",0000,true);
   }
   
   
   /*Construto Parametrizado
   public UsuarioDAO(int tipo, String nome, String dataNasc, String endereco, String bairro, long telefone, String senha, String empresa, String horEntrada, String horSaida, long cpf, boolean permissaoAC)
   {
      setTipo(tipo);
      setNome(nome);
      setDataNasc(dataNasc);
      setEndereco(endereco);
      setBairro(bairro);
      setTelefone(telefone);
      setSenha(senha);
      setEmpresa(empresa);
      setHorEntrada(horEntrada);
      setHorSaida(horSaida);
      setCpf(cpf);
      setPermissaoAC(permissaoAC);   
   }
   
   //Get's e Set's
   public void setTipo(int tipo)
   {
      this.tipo = tipo;
   }
   
   public int getTipo()
   {
      return tipo;
   }
   
   public void setNome(String nome)
   {
      this.nome = nome;
   }
   
   public String getNome()
   {
      return nome;
   }
   
   public void setDataNasc(String dataNasc)
   {
      this.dataNasc = dataNasc;
   }
   
   public String getDataNasc()
   {
      return dataNasc;
   }
   
   public void setEndereco(String endereco)
   {
      this.endereco = endereco;
   }
   
   public String getEndereco()
   {
      return endereco;
   }
   
   public void setBairro(String bairro)
   {
      this.bairro = bairro;
   }
   
   public String getBairro()
   {
      return bairro;
   }
   
   public void setTelefone(long telefone)
   {
      this.telefone = telefone;   
   }
   
   public long getTelefone()
   {
      return telefone;
   }
   
   public void setSenha(String senha)
   {
      this.senha = senha;   
   }
   
   public String getSenha()
   {
      return senha;
   }
   
   public void setEmpresa(String empresa)
   {
      this.empresa = empresa;
   }
   
   public String getEmpresa()
   {
      return empresa;
   }
   
   public void setHorEntrada(String horEntrada)
   {
      this.horEntrada = horEntrada;
   }
   
   public String getHorEntrada()
   {
      return horEntrada;
   }
   
   public void setHorSaida(String horSaida)
   {
      this.horSaida = horSaida;
   }
   
   public String getHorSaida()
   {
      return horSaida;
   }
   
   public void setCpf(long cpf)
   {
      this.cpf = cpf;
   }
   
   public long getCpf()
   {
      return cpf;
   }
   
   public void setPermissaoAC(boolean permissaoAC)
   {
      this.permissaoAC = permissaoAC;
   }
   
   public boolean getPermissaoAC()
   {
      return permissaoAC;
   }*/
   
   //Comandos SQL
   public boolean cadastrarUsuario( UsuarioTO usuarioTO )
   {
      String sqlInsert = "INSERT INTO usuario ( usuarioCpf,"
                                               + "usuarioTipo,"
                                               + "usuarioNome,"
                                               + "usuarioDataNasc,"
                                               + "usuarioEndereco,"
                                               + "usuarioBairro,"
                                               + "usuarioTelefone,"
                                               + "usuarioSenha,"
                                               + "usuarioEmpresa,"
                                               + "usuarioHorarioAcessoInicial,"
                                               + "usuarioHorarioAcessoFinal,"
                                               + "usuarioPermissaoArCond) "
                                               + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement stm = null;
      try
      {
         int permissaoAC;
         if(usuarioTO.getPermissaoAC() == true) { permissaoAC = 1; }
         else { permissaoAC = 0; }
         
         conn = bd.obtemConexao();
         //conn.setAutoCommit(false);
         stm = conn.prepareStatement(sqlInsert);         
      		
         stm.setLong( 1, usuarioTO.getCpf() );
         stm.setInt(2, usuarioTO.getTipo() );
         stm.setString(3, usuarioTO.getNome() );
         stm.setString(4, usuarioTO.getDataNasc() );
         stm.setString(5, usuarioTO.getEndereco() );
         stm.setString(6, usuarioTO.getBairro() );
         stm.setLong(7, usuarioTO.getTelefone() );
         stm.setString(8, usuarioTO.getSenha() );
         stm.setString(9, usuarioTO.getEmpresa() );
         stm.setString(10, usuarioTO.getHorEntrada() );
         stm.setString(11, usuarioTO.getHorSaida() );
         stm.setInt(12, permissaoAC );
         stm.executeUpdate();
         stm.close();
         
         return true;			
      }
      catch (Exception e)
      {                           
         return false;
      }   
   }
   
   public UsuarioTO consultarUsuario( long cpf )
   {
      String sqlSelect = "SELECT * FROM usuario WHERE usuarioCpf = ?";
      PreparedStatement stm = null;
      ResultSet rs = null;
      UsuarioTO usuarioTO = new UsuarioTO();
      
      try
      {
         conn = bd.obtemConexao();
         //conn.setAutoCommit(false);
         stm = conn.prepareStatement(sqlSelect);         
      		
         stm.setLong( 1, cpf );
         rs = stm.executeQuery();
         
         if( rs.next() )
         {
        	 boolean permissaoAC = false;
        	 if(rs.getInt(12) == 1) 
                 permissaoAC = true;
             
        	 usuarioTO.setTipo(rs.getInt(2));
        	 usuarioTO.setNome(rs.getString(3));
        	 usuarioTO.setDataNasc(rs.getString(4));
        	 usuarioTO.setEndereco(rs.getString(5));
        	 usuarioTO.setBairro(rs.getString(6));
        	 usuarioTO.setTelefone(rs.getLong(7));
        	 usuarioTO.setSenha(rs.getString(8));
        	 usuarioTO.setEmpresa(rs.getString(9));
        	 usuarioTO.setHorEntrada(rs.getString(10));
        	 usuarioTO.setHorSaida(rs.getString(11));
        	 usuarioTO.setCpf( cpf );
        	 usuarioTO.setPermissaoAC( permissaoAC );
         }
         else
         {
        	 usuarioTO.setTipo(-1);
         }			
      }
      catch (Exception e)
      {                           
         System.out.println("Erro na consulta");
         
      }
      return usuarioTO;
      
   }
   
   public boolean alterarUsuario( long cpf, UsuarioTO usuarioTO )
   {
      String sqlUpdate = "UPDATE usuario SET usuarioCpf = ?, "
      									  + "usuarioNome = ?, "
                                          + "usuarioDataNasc = ?, "
                                          + "usuarioEndereco = ?, "
                                          + "usuarioBairro = ?, "
                                          + "usuarioTelefone = ?, "
                                          + "usuarioSenha = ?, "
                                          + "usuarioEmpresa = ?, "
                                          + "usuarioHorarioAcessoInicial = ?, "
                                          + "usuarioHorarioAcessoFinal = ?,"
                                          + "usuarioPermissaoArCond = ? "
                                       + "WHERE usuarioCpf = ?";
      PreparedStatement stm = null;
      try
      {
    	  int permissaoAC = 0;
     	 if(usuarioTO.getPermissaoAC() == true) 
              permissaoAC = 1;
    	  
    	  conn = bd.obtemConexao();
         //conn.setAutoCommit(false);
         stm = conn.prepareStatement(sqlUpdate);         
      		
         stm.setLong(1, usuarioTO.getCpf());
         stm.setString(2, usuarioTO.getNome() );
         stm.setString(3, usuarioTO.getDataNasc() );
         stm.setString(4, usuarioTO.getEndereco() );
         stm.setString(5, usuarioTO.getBairro() );
         stm.setLong(6, usuarioTO.getTelefone() );
         stm.setString(7, usuarioTO.getSenha() );
         stm.setString(8, usuarioTO.getEmpresa() );
         stm.setString(9, usuarioTO.getHorEntrada() );
         stm.setString(10, usuarioTO.getHorSaida() );
         stm.setInt(11, permissaoAC );
         stm.setLong(12, cpf );
         
         stm.executeUpdate();
         stm.close();
         
         return true;
      }   
      catch (Exception e)
      {                           
         System.out.println("Erro na Alteração");
         return false;
      }
   }
   
   public boolean excluirUsuario( long cpf )
   {
      String sqlSelect = "DELETE FROM usuario WHERE usuarioCpf = ?";
      PreparedStatement stm = null;
      try
      {
         conn = bd.obtemConexao();
         //conn.setAutoCommit(false);
         stm = conn.prepareStatement(sqlSelect);         
      		
         stm.setLong( 1, cpf );
         stm.executeUpdate();
         stm.close();
         
         return true;	
      }   
      catch (Exception e)
      {                           
         System.out.println("Erro na Deleção");
         return false;
      }
   }
   
}
