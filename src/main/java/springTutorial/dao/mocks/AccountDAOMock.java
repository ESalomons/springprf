package springTutorial.dao.mocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import springTutorial.dao.interfaces.AccountDAO;
import springTutorial.model.Account;

public class AccountDAOMock implements AccountDAO {
	private Integer nextAccountNr = 42; 
	private Map<String, Account> accounts = new HashMap<String,Account>();
	
	@Override
	public Account createAccount(String name) {
		Account acc = new Account();
		acc.setName(name);
		acc.setId(nextAccountNr);
		nextAccountNr++;
		accounts.put(name, acc);
		return acc;
	}

	@Override
	public Account findAccount(String name) {
		return accounts.get(name);
	}

	@Override
	public List<Account> findAllAccounts() {
		return new ArrayList<Account>(accounts.values());
	}

}
