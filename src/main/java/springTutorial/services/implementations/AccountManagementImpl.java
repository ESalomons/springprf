package springTutorial.services.implementations;

import springTutorial.dao.interfaces.AccountDAO;
import springTutorial.model.Account;
import springTutorial.services.exceptions.AccountNameInUseException;
import springTutorial.services.interfaces.AccountManagement;

public class AccountManagementImpl implements AccountManagement {

	private AccountDAO accountDAO;
	
	@Override
	public Integer createAccount(String name) throws AccountNameInUseException {
		if(accountDAO.findAccount(name) != null){
			throw new AccountNameInUseException("Account " + name + " already exists");
		}
		Account acc = accountDAO.createAccount(name);
		return acc.getId();
	}

	@Override
	public Float getAccountBalance(String name) {
		Account acc = accountDAO.findAccount(name);
		return acc.getBalance();
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
}
