package service;

import dao.EmpresaDAO;
import transferObject.EmpresaTO;

public class EmpresaService {
	EmpresaDAO empDAO = new EmpresaDAO();
	//Cadastrar
	   public boolean cadastrar(long cnpj, String razaoSocial, String conjunto, String horFunc, String horFuncAC, int valorMaxAC)
	   {
	      EmpresaTO empTO = new EmpresaTO( cnpj, razaoSocial, conjunto, horFunc, horFuncAC, valorMaxAC );
	      if(empDAO.cadastrar(empTO)) 
	         return true;
	      else 
	         return false;
	      
	   }
	   
	   //Consultar
	   public EmpresaTO consultar(long cnpj)
	   {
	      EmpresaTO empTO = empDAO.carregar(cnpj); 
	      return empTO;
	   }
	   
	   //Excluir
	   public boolean excluir(long cnpj)
	   {
	      if(empDAO.excluir(cnpj))
	      {
	         
	         return true;
	      }
	      else 
	         return false;
	   }
	   
	   //Alterar
	   public boolean alterar(long cnpj, String razaoSocial, String conjunto, String horFunc, String horFuncAC, int valorMaxAC)
	   {
		   EmpresaDAO empDAO3 = new EmpresaDAO();
		   EmpresaTO empTO = new EmpresaTO( cnpj, razaoSocial, conjunto, horFunc, horFuncAC, valorMaxAC );
		   System.out.println("Classe Service");      			
	      	System.out.println(empTO.getCnpj());
	      					System.out.println(empTO.getRazaoSocial());
	      					System.out.println(empTO.getConjunto());
	      					System.out.println(empTO.getHorFunc());
	      					System.out.println(empTO.getHorFuncAC());
	      					System.out.println(empTO.getValorMaxAC());
	        
		   if(empDAO3.alterar(empTO)) 
	         return true;
	      else 
	         return false;
	      
	   }

}
