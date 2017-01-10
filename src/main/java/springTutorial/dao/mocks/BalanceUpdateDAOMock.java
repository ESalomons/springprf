package springTutorial.dao.mocks;

import java.util.List;

import springTutorial.dao.interfaces.BalanceUpdateDAO;
import springTutorial.dao.interfaces.MoneyTransactionDAO;
import springTutorial.model.Account;
import springTutorial.model.MoneyTransaction;

public class BalanceUpdateDAOMock implements BalanceUpdateDAO{
	
	private MoneyTransactionDAO transactionDAO;
	@Override
	public void updateAccountBalance(Account account) {
		List<MoneyTransaction> transactions = transactionDAO.findMoneyTransactionsByAccount(account);
		for(MoneyTransaction mt: transactions){
			float amount = mt.getAmount();
			if(mt.getSourceAccount().equals(account)){
				account.mutateBalance(-amount);
			} else if(mt.getTargetAccount().equals(account)) { // eigenlijk niet nodig te testen
				account.mutateBalance(amount);
			}
			
		}
	}
	
	public void setTransactionDAO(MoneyTransactionDAO transactionDAO){
		this.transactionDAO = transactionDAO;
	}

}
