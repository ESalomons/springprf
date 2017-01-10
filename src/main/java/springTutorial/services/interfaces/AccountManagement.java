package springTutorial.services.interfaces;

import springTutorial.services.exceptions.AccountNameInUseException;

public interface AccountManagement {

	/**
	 * Create a new account with the given name while making sure that there's
	 * no account with this name. Return the ID of the created account.
	 * @throws AccountNameInUseException 
	 */
	public Integer createAccount(String name) throws AccountNameInUseException;

	/**
	 * Return the pre-calculate balance of the account with the given name.
	 */
	public Float getAccountBalance(String name);
}
