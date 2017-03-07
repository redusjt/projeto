package Model;

import DAO.EmpresaDAO;

public class Empresa
{
   private String  razaoSocial, conjunto, horFunc, horFuncAC;
   private long cnpj;
   private int valorMaxAC;
   
   //Construtor Padrão
   public Empresa()
   {
      this(0,"","","","",-1);
   }
   
   
   //Construtor Parametrizado
   public Empresa(long cnpj, String razaoSocial, String conjunto, String horFunc, String horFuncAC, int valorMaxAC)
   {
      setCnpj(cnpj);
      setRazaoSocial(razaoSocial);
      setConjunto(conjunto);
      setHorFunc(horFunc);
      setHorFuncAC(horFuncAC);
      setValorMaxAC(valorMaxAC);
   }
   
   //Cadastrar
   public boolean cadastrar()
   {
      EmpresaDAO empD = new EmpresaDAO();
      if(empD.cadastrar(getCnpj(), getRazaoSocial(), getConjunto(), getHorFunc(), getvalorMaxAC(), getHorFuncAC())) 
         return true;
      else 
         return false;
      
   }
   
   //Consultar
   public boolean consultar(long cnpj)
   {
      EmpresaDAO empD2 = new EmpresaDAO();
      if(empD2.carregar(cnpj)) 
      {
         this.setCnpj(empD2.getCnpj());
         this.setRazaoSocial(empD2.getRazaoSocial());
         this.setConjunto(empD2.getConjunto());
         this.setHorFunc(empD2.getHorFunc());
         this.setValorMaxAC(empD2.getValorMaxAC());
         this.setHorFuncAC(empD2.getHorFuncAC());         
         return true;      
      }
      else 
         return false;
   }
   
   //Excluir
   public boolean excluir(long cnpj)
   {
      EmpresaDAO empD3 = new EmpresaDAO();
      if(empD3.excluir(cnpj))
      {
         
         return true;
      }
      else 
         return false;
   }
   
   //Alterar
   public boolean alterar()
   {
      EmpresaDAO empD4 = new EmpresaDAO(getCnpj(), getRazaoSocial(), getConjunto(), getHorFunc(), getHorFuncAC(), getvalorMaxAC()
      );
      
      if(empD4.alterar()) return true;
      else return false;
   }
   
   
   //Get's e Set's
   public void setCnpj(Long cnpj)
   {
      this.cnpj = cnpj;
   } 
   
   public long getCnpj()
   {
      return cnpj;
   }
   
   public void setRazaoSocial(String razaoSocial)
   {
      this.razaoSocial = razaoSocial;
   }
   
   public String getRazaoSocial()
   {
      return razaoSocial;
   }
   
   public void setConjunto(String conjunto)
   {
      this.conjunto = conjunto;
   }
   
   public String getConjunto()
   {
      return conjunto;
   }
   
   public void setHorFunc(String horFunc)
   {
      this.horFunc = horFunc;
   }
   
   public String getHorFunc()
   {
      return horFunc;
   }
   
   public void setHorFuncAC(String horFuncAC)
   {
      this.horFuncAC = horFuncAC;
   }
   
   public String getHorFuncAC()
   {
      return horFuncAC;
   }
   
   public void setValorMaxAC(int valorMaxAC)
   {
      this.valorMaxAC = valorMaxAC;
   }
   
   public int getvalorMaxAC()
   {
      return valorMaxAC;
   }
   
}