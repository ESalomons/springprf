package springTutorial.services.implementations;

import java.util.logging.Logger;

import springTutorial.dao.interfaces.AccountDAO;
import springTutorial.dao.interfaces.BalanceUpdateDAO;
import springTutorial.model.Account;
import springTutorial.services.interfaces.BalanceManagement;

public class BalanceManagementImpl implements BalanceManagement {
	private BalanceUpdateDAO balanceUpdateDAO;
	private AccountDAO accountDAO;
	private Logger logger = Logger.getLogger("BalanceManagement");

	@Override
	public void updateBalances() {
		for (Account account : accountDAO.findAllAccounts()) {
			balanceUpdateDAO.updateAccountBalance(account);
			logger.info(String.format("Balance update account %s",
					account.getName()));
		}
	}

	public void setBalanceUpdateDAO(BalanceUpdateDAO balanceUpdateDAO) {
		this.balanceUpdateDAO = balanceUpdateDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

}
