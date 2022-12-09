package LoginModule;

public interface ILoginValidator {
	
	/**
	 * <p> Checks if a user with the given username exists in the database/JSON
	 * </p>
	 * @param username
	 * @return boolean indicating if username exists
	 */
	public boolean checkExistingUser(String username);
	
	/**
	 * <p>A method to validate user credentials
	 * </p>
	 * @param username to match
	 * @param password to match
	 * @return boolean indicating if username and password match has been found
	 */
	public boolean authenticateUser(String username, String password);
	

}
