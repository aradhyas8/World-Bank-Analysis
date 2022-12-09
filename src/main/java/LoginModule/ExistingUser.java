package LoginModule;

public class ExistingUser extends Handler {
	
	private ILoginValidator loginValidator;
	
	public ExistingUser(ILoginValidator validator) {
		this.loginValidator = validator;
	}
	
	/**
	 * <p> Checks if the username already exists and forwards request to next handler 
	 * </p>
	 * @param username entered by user
	 * @param password entered by user
	 */
	@Override
	public boolean handle(String username, String password) {
		
		if(loginValidator.checkExistingUser(username) == true) {
			return nextHandler.handle(username, password);
		}
		else {
			return false;
		}
		
	}
}
