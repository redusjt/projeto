package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO
{
   private String nome, dataNasc, endereco, bairro, senha, empresa, horEntrada, horSaida;
   private int tipo;
   private long cpf, telefone;
   private boolean permissaoAC;
   
   AcessoBD bd = new AcessoBD(); 
   Connection conn = null;
   
   //Construtor Padrão
   public UsuarioDAO()
   {
      this(-1,"","","","",0000,"", "","00:00","00:00",0000,true);
   }
   
   
   //Construto Parametrizado
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
   }
   
   //Comandos SQL
   public boolean cadastrarUsuario()
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
         int booleanValue;
         if(getPermissaoAC() == true) { booleanValue = 1; }
         else { booleanValue = 0; }
         
         conn = bd.obtemConexao();
         //conn.setAutoCommit(false);
         stm = conn.prepareStatement(sqlInsert);         
      		
         stm.setLong( 1, getCpf() );
         stm.setInt(2, getTipo() );
         stm.setString(3, getNome() );
         stm.setString(4, getDataNasc() );
         stm.setString(5, getEndereco() );
         stm.setString(6, getBairro() );
         stm.setLong(7, getTelefone() );
         stm.setString(8, getSenha() );
         stm.setString(9, getEmpresa() );
         stm.setString(10, getHorEntrada() );
         stm.setString(11, getHorSaida() );
         stm.setInt(12, booleanValue );
         stm.executeUpdate();
         stm.close();
         
         return true;			
      }
      catch (Exception e)
      {                           
         return false;
      }   
   }
   
   public void consultarUsuario( long cpf )
   {
      String sqlSelect = "SELECT * FROM usuario WHERE usuarioCpf = ?";
      PreparedStatement stm = null;
      ResultSet rs = null;
      
      try
      {
         conn = bd.obtemConexao();
         //conn.setAutoCommit(false);
         stm = conn.prepareStatement(sqlSelect);         
      		
         stm.setLong( 1, cpf );
         rs = stm.executeQuery();
         
         if( rs.next() )
         {
            setTipo(rs.getInt(2));
            setNome(rs.getString(3));
            setDataNasc(rs.getString(4));
            setEndereco(rs.getString(5));
            setBairro(rs.getString(6));
            setTelefone(rs.getLong(7));
            setSenha(rs.getString(8));
            setEmpresa(rs.getString(9));
            setHorEntrada(rs.getString(10));
            setHorSaida(rs.getString(11));
            if(rs.getInt(12) == 1) 
               setPermissaoAC(true);
            else 
               setPermissaoAC(false);
         }
         else
         {
            setTipo(-1);
         }			
      }
      catch (Exception e)
      {                           
         System.out.println("Erro na consulta");
      }
   }
   
   public boolean alterarUsuario( long cpf )
   {
      String sqlUpdate = "UPDATE usuario SET usuarioNome = ?, "
                                          + "usuarioDataNasc = ?, "
                                          + "usuarioEndereco = ?, "
                                          + "usuarioBairro = ?, "
                                          + "usuarioTelefone = ?, "
                                          + "usuarioSenha = ?, "
                                          + "usuarioEmpresa = ?, "
                                          + "usuarioHorarioAcessoInicial = ?, "
                                          + "usuarioHorarioAcessoFinal = ?, "
                                          + "usuarioPermissaoArCond = ? "
                                       + "WHERE usuarioCpf = ?";
      PreparedStatement stm = null;
      try
      {
         int booleanValue;
         if(getPermissaoAC() == true) { booleanValue = 1; }
         else { booleanValue = 0; }
         
         conn = bd.obtemConexao();
         //conn.setAutoCommit(false);
         stm = conn.prepareStatement(sqlUpdate);         
      		
         stm.setString(1, getNome() );
         stm.setString(2, getDataNasc() );
         stm.setString(3, getEndereco() );
         stm.setString(4, getBairro() );
         stm.setLong(5, getTelefone() );
         stm.setString(6, getSenha() );
         stm.setString(7, getEmpresa() );
         stm.setString(8, getHorEntrada() );
         stm.setString(9, getHorSaida() );
         stm.setInt(10, booleanValue );
         stm.setLong(11, cpf );
         
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


