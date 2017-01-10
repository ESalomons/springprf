package springTutorial.dao.hibernateDaos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;

import springTutorial.dao.interfaces.MoneyTransactionDAO;
import springTutorial.model.Account;
import springTutorial.model.MoneyTransaction;

public class MoneyTransactionDAOImpl extends
		GenericHibernateDAO<MoneyTransaction, Integer> implements
		MoneyTransactionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Integer countRecentMoneyTransactions(Account account, Date startDate) {
		List<Object> objects = currentSession()
				.createCriteria(MoneyTransaction.class)
				.add(Restrictions.gt("creationTime", startDate))
				.add(Restrictions.eq("sourceAccount", account)).list();
		return objects.size();
	}

	@Override
	public MoneyTransaction createMoneyTransaction(Account sourceAccount,
			Account targetAccount, Float amount, String description) {
		MoneyTransaction transaction = new MoneyTransaction();
		transaction.setSourceAccount(sourceAccount);
		transaction.setTargetAccount(targetAccount);
		transaction.setAmount(amount);
		transaction.setDescription(description);
		update(transaction);
		return transaction;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MoneyTransaction> findMoneyTransactionsByAccount(Account account) {
		List<MoneyTransaction> results = currentSession()
				.createCriteria(MoneyTransaction.class)
				.add(Restrictions.eq("sourceAccount", account)).list();
		List<MoneyTransaction> transactions = new ArrayList<MoneyTransaction>();
		transactions.addAll(results);

		results = currentSession().createCriteria(MoneyTransaction.class)
				.add(Restrictions.eq("targetAccount", account)).list();
		transactions.addAll(results);
		return transactions;

	}

}
