package springTutorial.dao.hibernateDaos;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import springTutorial.dao.interfaces.AccountDAO;
import springTutorial.model.Account;

public class AccountDAOImpl3a extends GenericHibernateDAO<Account, Integer>
		implements AccountDAO {

	@Override
	public Account createAccount(String name) {
		Account acc = new Account();
		acc.setName(name);
		add(acc);
		throw new RuntimeException();
		// return acc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Account findAccount(String name) {
		List<Account> accounts = currentSession().createCriteria(Account.class)
				.add(Restrictions.eq("name", name)).list();
		if (accounts.size() > 0) {
			return accounts.get(0);
		}
		return null;

	}

	@Override
	public List<Account> findAllAccounts() {
		return currentSession().createCriteria(Account.class).list();
	}


}
