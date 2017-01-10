package springTutorial.dao.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import springTutorial.dao.interfaces.MoneyTransactionDAO;
import springTutorial.model.Account;
import springTutorial.model.MoneyTransaction;

public class MoneyTransactionDAOMock implements MoneyTransactionDAO {

	private List<MoneyTransaction> transactions = new ArrayList<MoneyTransaction>();
	private int nextTransactionNr = 876;

	@Override
	public Integer countRecentMoneyTransactions(Account account, Date startDate) {
		int count = 0;
		for (MoneyTransaction mt : transactions) {
			if (mt.getCreationTime().after(startDate)) {
				Account from = mt.getSourceAccount();
				if (from.equals(account)) {
					count++;
				}
			}

		}
		return count;
	}

	@Override
	public MoneyTransaction createMoneyTransaction(Account sourceAccount,
			Account targetAccount, Float amount, String description) {
		MoneyTransaction transaction = new MoneyTransaction();
		transaction.setId(nextTransactionNr);
		transaction.setSourceAccount(sourceAccount);
		transaction.setTargetAccount(targetAccount);
		transaction.setAmount(amount);
		transaction.setDescription(description);
		transactions.add(transaction);
		nextTransactionNr++;
		return transaction;
	}

	@Override
	public List<MoneyTransaction> findMoneyTransactionsByAccount(Account account) {
		ArrayList<MoneyTransaction> found = new ArrayList<MoneyTransaction>();
		for (MoneyTransaction mt : transactions) {
			Account from = mt.getSourceAccount();
			Account to = mt.getTargetAccount();
			if (from.equals(account) || to.equals(account)) {
				found.add(mt);
			}

		}
		return found;
	}

}
