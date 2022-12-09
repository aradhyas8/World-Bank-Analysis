package JUnitTests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import LoginModule.ILoginValidator;
import LoginModule.LoginValidator;

public class LoginValidatorTest {
	
	ILoginValidator testee = new LoginValidator();
	
	@Test
	public void testExistingUser() {
		
		// Check if user exists 
		boolean result = testee.checkExistingUser("axita");
		assertTrue(result);	
	}
	
	@Test
	public void testExistingUserNullUsername() {
		
		boolean result = testee.checkExistingUser(null);
		assertFalse(result);	
	}
	
	@Test
	public void testExistingUserEmptyUsername() {
		
		boolean result = testee.checkExistingUser("");
		assertFalse(result);	
	}
	
	@Test
	public void testNonExistingUser() {
		
		// Non-existing username
		boolean result = testee.checkExistingUser("axitaa73");
		assertFalse(result);	
	}
	
	@Test
	public void testauthenticateUserValid() {
		
		// Valid username and password 
		String username = "axita";
		String password = "1234";
		boolean result = testee.authenticateUser(username, password);
		assertTrue(result);	
	}
	
	@Test
	public void testauthenticateUserInvalidPass() {
		
		// Valid username but invalid password
		String username = "axita";
		String password = "abcd";
		boolean result = testee.authenticateUser(username, password);
		assertFalse(result);	
	}
	
	@Test
	public void testauthenticateUserNullUsername() {
		
		String password = "1234";
		boolean result = testee.authenticateUser(null, password);
		assertFalse(result);	
	}
	
	@Test
	public void testauthenticateUserEmptyUsername() {
		
		String password = "1234";
		boolean result = testee.authenticateUser("", password);
		assertFalse(result);	
	}
	
	@Test
	public void testauthenticateUserNullPassword() {
		
		String username = "axita";
		boolean result = testee.authenticateUser(username, null);
		assertFalse(result);	
	}
	
	@Test
	public void testauthenticateUserEmptyPassword() {
		
		String username = "axita";
		boolean result = testee.authenticateUser(username, "");
		assertFalse(result);	
	}
	

}
