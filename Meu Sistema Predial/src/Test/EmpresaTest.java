package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmpresaTest {
	Empresa emp = new Empresa();
	@Test
	public void testCadastrar() {
		
		assertEquals(false, emp.cadastrar());
	}
	
	@Test
	public void testConsultar() {
		
		assertEquals(false, emp.consultar(45454545));
	}
	
	@Test
	public void testExcluir() {
		
		assertEquals(false, emp.excluir(45454545));
	}
	
	@Test
	public void testAlterar() {
		
		assertEquals(false, emp.alterar());
	}
	

}
