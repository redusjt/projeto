package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.UsuarioM;

public class UsuarioTest {
		UsuarioM usuario;
		
	@Before
	public void setUp() throws Exception {
		usuario = new UsuarioM("Esther", "12321");
	}

	@Test
	public void test() {
		assertEquals("Usuario 12321, Esther", usuario, new UsuarioM("12325", "Esther"));
	}

}
