package LoginModule;

public class ValidatePassword extends Handler {
	
	private ILoginValidator loginValidator;
	
	public ValidatePassword(ILoginValidator validator) {
		this.loginValidator = validator;
	}

	/**
	 * <p> Checks if password matches username and forwards request to next handler 
	 * </p>
	 * @param username entered by user
	 * @param password entered by user
	 */
	@Override
	public boolean handle(String username, String password) {
		if(loginValidator.authenticateUser(username, password) == true) {
			if(nextHandler != null) {
				return nextHandler.handle(username, password);
			} else {
				return true;
			}	

		}
		return false;
	}

}