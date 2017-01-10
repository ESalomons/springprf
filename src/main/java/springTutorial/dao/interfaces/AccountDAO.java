package springTutorial.dao.interfaces;

import java.util.List;

import springTutorial.model.Account;

public interface AccountDAO {
	public Account createAccount(String name);
	public Account findAccount(String name);
	public List<Account> findAllAccounts();
}
