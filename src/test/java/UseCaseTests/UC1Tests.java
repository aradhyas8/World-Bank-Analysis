package UseCaseTests;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;

import LoginModule.ExistingUser;
import LoginModule.Handler;
import LoginModule.ILoginValidator;
import LoginModule.LoginUI;
import LoginModule.LoginValidator;
import LoginModule.ValidatePassword;

/**
 * Tests for UC1: The user logs into the system
 */
public class UC1Tests {
	
	private FrameFixture window;
	
	@BeforeClass
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}
	
	@Before
	public void setUp() {
		
		ILoginValidator validator = new LoginValidator();
		Handler handler = new ExistingUser(validator);
		handler.setNext(new ValidatePassword(validator));
		
		LoginUI loginUI = GuiActionRunner.execute(() -> new LoginUI(handler));
		window = new FrameFixture(loginUI);
	    window.show(); 
	    window.maximize();
	}
	
	@Test (timeout = 50000)
	public void loginFailedNonExistantUser() {
	
		window.panel("loginPanel").textBox("usernameInput").enterText("software design");
		window.panel("loginPanel").textBox("passwordInput").enterText("1234");
		window.panel("loginPanel").button("loginButton").click();
		
		// Expect error message expected since user does not exist
		window.panel("loginPanel").label("messageLabel").requireText("Username or password does not match.");
	}
	
	@Test (timeout = 50000)
	public void loginFailedWrongPassword() {
	
		window.panel("loginPanel").textBox("usernameInput").enterText("axita");
		window.panel("loginPanel").textBox("passwordInput").enterText("abcd");
		window.panel("loginPanel").button("loginButton").click();
		
		// Expect error message is expected since password is wrong
		window.panel("loginPanel").label("messageLabel").requireText("Username or password does not match.");
	}
	
	@Test (timeout = 50000)
	public void loginFailedEmptyPassword() {
	
		window.panel("loginPanel").textBox("usernameInput").enterText("axita");
		window.panel("loginPanel").textBox("passwordInput").enterText("");
		window.panel("loginPanel").button("loginButton").click();
		window.panel("loginPanel").label("messageLabel").requireText("Both username and password required.");
	}
	
	@Test (timeout = 50000)
	public void loginFailedEmptyUsername() {
	
		window.panel("loginPanel").textBox("usernameInput").enterText("");
		window.panel("loginPanel").textBox("passwordInput").enterText("12345");
		window.panel("loginPanel").button("loginButton").click();
		window.panel("loginPanel").label("messageLabel").requireText("Both username and password required.");
	}
	
	@Test (timeout = 50000)
	public void loginSuccess() {
	
		window.panel("loginPanel").textBox("usernameInput").enterText("axita");
		window.panel("loginPanel").textBox("passwordInput").enterText("1234");
		window.panel("loginPanel").button("loginButton").click();
		window.panel("loginPanel").label("messageLabel").requireText("Success");

	}
	
	 @After
	 public void tearDown() {
		 window.cleanUp();
	 }

}
