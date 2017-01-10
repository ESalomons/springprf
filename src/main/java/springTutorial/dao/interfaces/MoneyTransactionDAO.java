package springTutorial.dao.interfaces;

import java.util.Date;
import java.util.List;

import springTutorial.model.Account;
import springTutorial.model.MoneyTransaction;

public interface MoneyTransactionDAO {
	public MoneyTransaction createMoneyTransaction(Account sourceAccount,
			Account targetAccount, Float amount, String description);
	public Integer countRecentMoneyTransactions(Account account, Date startDate);
	public List<MoneyTransaction> findMoneyTransactionsByAccount(Account account);
}
