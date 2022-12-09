package LoginModule;

public class Main {
	
	public static void main(String[] args) {
		
		ILoginValidator validator = new LoginValidator();
		Handler handler = new ExistingUser(validator);
		handler.setNext(new ValidatePassword(validator));
		
		LoginUI loginUI = new LoginUI(handler);

	}

}
