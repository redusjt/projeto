package transferObject;


public class EmpresaTO
{
   private String  razaoSocial, conjunto, horFunc, horFuncAC;
   private long cnpj;
   private int valorMaxAC;
   
   //Construtor Padrão
   public EmpresaTO()
   {
      this(0,"","","","",-1);
   }
   
   
   //Construtor Parametrizado
   public EmpresaTO(long cnpj, String razaoSocial, String conjunto, String horFunc, String horFuncAC, int valorMaxAC)
   {
      setCnpj(cnpj);
      setRazaoSocial(razaoSocial);
      setConjunto(conjunto);
      setHorFunc(horFunc);
      setHorFuncAC(horFuncAC);
      setValorMaxAC(valorMaxAC);
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
   
   public int getValorMaxAC()
   {
      return valorMaxAC;
   }
   
}