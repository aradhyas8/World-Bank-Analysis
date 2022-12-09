package LoginModule;

public abstract class Handler{
	
	protected Handler nextHandler;
	
	/**
	 * <p>Sets the next handler in the chain of responsibility
	 * </p>
	 * @param handler
	 */
	public void setNext(Handler h) {
		this.nextHandler = h;
	}
	
	/**
	 * <p>Handles the login process of a user
	 * </p>
	 * @param username entered by user
	 * @param password entered by user
	 */
	public abstract boolean handle(String username, String password);

}
