package Controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginTest {

	@Test
	
	public void test() {
		Login l = new Login();
		long vetor[] = {1,2,3,4,5,6,7,8,9,10};
		
		assertEquals(true, l.binaria(vetor, 33));
	}

}

