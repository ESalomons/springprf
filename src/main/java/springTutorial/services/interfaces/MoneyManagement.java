package springTutorial.services.interfaces;

import springTutorial.services.exceptions.BalanceTooLowException;
import springTutorial.services.exceptions.TooManyMoneyTransactionsException;

public interface MoneyManagement {

	/**
	 * Create a money transaction for the accounts of the given names. Make sure
	 * that the balance of the user is not too low (e.g., -1000$) Make sure that
	 * the number of transactions in the last N days (e.g., 1 day) for each
	 * account does not the allowed limit (e.g., 5)
	 */
	public void transferMoney(String sourceAccountName,
			String targetAccountName, Float amount, String description)
			throws TooManyMoneyTransactionsException, BalanceTooLowException;

}
