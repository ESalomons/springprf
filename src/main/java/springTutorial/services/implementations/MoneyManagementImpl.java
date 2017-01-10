package springTutorial.services.implementations;

import java.util.Date;

import springTutorial.dao.interfaces.AccountDAO;
import springTutorial.dao.interfaces.MoneyTransactionDAO;
import springTutorial.model.Account;
import springTutorial.services.exceptions.BalanceTooLowException;
import springTutorial.services.exceptions.TooManyMoneyTransactionsException;
import springTutorial.services.interfaces.MoneyManagement;

public class MoneyManagementImpl implements MoneyManagement {

	private AccountDAO accountDAO;
	private MoneyTransactionDAO moneyTransactionDAO;
	private float minBalance = -1000f;
	private int maxTransactions = 5;
	
	@Override
	public void transferMoney(String sourceAccountName,
			String targetAccountName, Float amount, String description)
			throws TooManyMoneyTransactionsException, BalanceTooLowException {
		Account sourceAccount = accountDAO.findAccount(sourceAccountName);
		Account targetAccount = accountDAO.findAccount(targetAccountName);
		if(sourceAccount.getBalance() - amount < minBalance){
			throw new BalanceTooLowException();
		}
		Date yesterday = new Date(new Date().getTime() - (1000 * 60 * 60 * 24));
		int nrTransactionsSource = moneyTransactionDAO.countRecentMoneyTransactions(sourceAccount, yesterday);
		if(nrTransactionsSource >=maxTransactions){
			throw new TooManyMoneyTransactionsException();
		}
		moneyTransactionDAO.createMoneyTransaction(sourceAccount, targetAccount, amount, description);
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}


	public void setMoneyTransactionDAO(MoneyTransactionDAO moneyTransactionDAO) {
		this.moneyTransactionDAO = moneyTransactionDAO;
	}



}
