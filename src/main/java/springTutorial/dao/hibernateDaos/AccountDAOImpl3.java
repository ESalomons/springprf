package springTutorial.dao.hibernateDaos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import springTutorial.dao.interfaces.AccountDAO;
import springTutorial.model.Account;

public class AccountDAOImpl3 implements AccountDAO {

	private SessionFactory sessionFactory;

	@Override
	public Account createAccount(String name) {
		Account acc = new Account();
		acc.setName(name);
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(acc);
		session.close();
		throw new RuntimeException();
		// return acc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Account findAccount(String name) {
		Session session = sessionFactory.openSession();
		List<Account> accounts = session.createCriteria(Account.class)
				.add(Restrictions.eq("name", name)).list();
		if (accounts.size() > 0) {
			return accounts.get(0);
		}
		session.close();
		return null;

	}

	@Override
	public List<Account> findAllAccounts() {
		Session session = sessionFactory.openSession();
		List<Account> accounts = session.createCriteria(Account.class).list();
		session.close();
		return accounts;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
