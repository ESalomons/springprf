package springTutorial.client;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springTutorial.services.exceptions.AccountNameInUseException;
import springTutorial.services.exceptions.BalanceTooLowException;
import springTutorial.services.exceptions.TooManyMoneyTransactionsException;
import springTutorial.services.interfaces.AccountManagement;
import springTutorial.services.interfaces.BalanceManagement;
import springTutorial.services.interfaces.MoneyManagement;

public class Client7 {

	private AccountManagement accountManagement;
	private BalanceManagement balanceManagement;
	private MoneyManagement moneyManagement;

	public static void main (String[] args) {
		BeanFactory factory = new ClassPathXmlApplicationContext(new String[] {
				"client7.spring.xml"});
		Client7 client = (Client7) factory.getBean("client7");
		client.performTest();
	}	
	
	public void performTest(){
		String[] names = new String[] { "Jan", "Piet", "Henk" };
		try {
			for (String name : names) {
				accountManagement.createAccount(name);
			}
		} catch (AccountNameInUseException e) {
			System.err.println("account in use");
		}

		try {
			accountManagement.createAccount("Piet");
		} catch (AccountNameInUseException e1) {
			System.err.println("account Piet in use");
		} // gaat fout!

		try {
			moneyManagement.transferMoney("Jan", "Piet", 150f, "eerste");
			moneyManagement.transferMoney("Jan", "Piet", 150f, "tweede");
			moneyManagement.transferMoney("Jan", "Piet", 150f, "derde");
			moneyManagement.transferMoney("Jan", "Piet", 150f, "vierde");
			moneyManagement.transferMoney("Jan", "Piet", 150f, "vijfde");
		} catch (TooManyMoneyTransactionsException e) {
			System.err.println("te veel transacties");
		} catch (BalanceTooLowException e) {
			System.err.println("te weinig geld");
		}

		try {
			moneyManagement.transferMoney("Jan", "Piet", 150f, "zesde"); // te
			// veel
			// transacties
		} catch (TooManyMoneyTransactionsException e) {
			System.err.println("te veel transacties van Jan");
		} catch (BalanceTooLowException e) {
			System.err.println("te weinig geld");
		}

		try {
			moneyManagement.transferMoney("Piet", "Henk", 1500f, "boel geld");
		} catch (TooManyMoneyTransactionsException e) {
			System.out.println("te veel transacties");
		} catch (BalanceTooLowException e) {
			System.err.println("te weinig geld bij Piet");
		} // mag niet!

		try {
			moneyManagement.transferMoney("Piet", "Henk", 1000f, "boel geld");
		} catch (TooManyMoneyTransactionsException e) {
			System.out.println("te veel transacties");
		} catch (BalanceTooLowException e) {
			System.err.println("te weinig geld");
		} // mag niet!

		balanceManagement.updateBalances();
		
		System.out.printf("%s: EUR %.2f\n", "Jan", accountManagement.getAccountBalance("Jan"));
		System.out.printf("%s: EUR %.2f\n", "Piet", accountManagement
				.getAccountBalance("Piet"));
		System.out.printf("%s: EUR %.2f\n", "Henk", accountManagement
				.getAccountBalance("Henk"));
		
	}

	public AccountManagement getAccountManagement() {
		return accountManagement;
	}

	public void setAccountManagement(AccountManagement accountManagement) {
		this.accountManagement = accountManagement;
	}

	public BalanceManagement getBalanceManagement() {
		return balanceManagement;
	}

	public void setBalanceManagement(BalanceManagement balanceManagement) {
		this.balanceManagement = balanceManagement;
	}

	public MoneyManagement getMoneyManagement() {
		return moneyManagement;
	}

	public void setMoneyManagement(MoneyManagement moneyManagement) {
		this.moneyManagement = moneyManagement;
	}
}
