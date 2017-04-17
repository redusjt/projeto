package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dao.UsuarioDAO;
import transferObject.UsuarioTO;

public class UsuarioServiceTest {
	UsuarioTO user, copia;
	UsuarioDAO usuario;
	static int id = 42134011;
	static int id2 = 17005051;

	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		user = new UsuarioTO(id, "Joao", "12321");
		copia = new UsuarioTO(id2, "Mary", "12325");
		System.out.println(user);
		System.out.println(copia);
		System.out.println(id);
		System.out.println(id2);
	}

	@Test
	public void test01Criar() {
		System.out.println("criar");
		usuario.cadastrarUsuario(user);
		id = (int) user.getCpf();
		System.out.println(id);
		copia.setCpf(id);
		assertEquals("testa criacao", user, copia);
	}

	@Test
	public void test02Atualizar() {
		System.out.println("atualizar");
		user.setSenha("999999");
		copia.setSenha("999999");
		usuario.alterarUsuario(id, user);
		usuario.consultarUsuario(id);
		assertEquals("testa atualizacao", usuario, copia);
	}

	@Test
	public void test03Excluir() {
		System.out.println("excluir");
		copia.setCpf(-1);
		copia.setNome(null);
		copia.setSenha(null);
		usuario.excluirUsuario(id);
		usuario.consultarUsuario(id);
		assertEquals("testa exclusao", user, copia);
	}

}
