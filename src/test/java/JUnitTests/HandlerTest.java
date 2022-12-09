package JUnitTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import LoginModule.ExistingUser;
import LoginModule.Handler;
import LoginModule.ILoginValidator;
import LoginModule.LoginValidator;
import LoginModule.ValidatePassword;

public class HandlerTest {
	
	private Handler handler;
	private ILoginValidator validator;
	
	@Before
	public void setUp() {
		validator = new LoginValidator();
		handler = new ExistingUser(validator);
		handler.setNext(new ValidatePassword(validator));
	}
	
	@Test
	public void testNonExistingUser() {
		
		boolean result = handler.handle("compSci", "abcd");
		assertFalse(result);
	}
	
	@Test
	public void testWrongPassword() {
		
		boolean result = handler.handle("axita", "12abc");
		assertFalse(result);
	}
	
	@Test
	public void testEmptyPassword() {
		
		boolean result = handler.handle("axita", "");
		assertFalse(result);
	}
	
	@Test
	public void testEmptyUsername() {
		
		boolean result = handler.handle("", "1234");
		assertFalse(result);
	}
	
	@Test
	public void testEmptyUsernameAndPassword() {
		
		boolean result = handler.handle("", "");
		assertFalse(result);
	}
	
	@Test
	public void testValidUserAndPassword() {
		
		boolean result = handler.handle("axita", "1234");
		assertTrue(result);
	}

}
