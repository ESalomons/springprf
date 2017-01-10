package springTutorial.client;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springTutorial.services.exceptions.AccountNameInUseException;
import springTutorial.services.exceptions.BalanceTooLowException;
import springTutorial.services.exceptions.TooManyMoneyTransactionsException;
import springTutorial.services.interfaces.AccountManagement;
import springTutorial.services.interfaces.BalanceManagement;
import springTutorial.services.interfaces.MoneyManagement;

public class Client5 {

	private AccountManagement accountService;
	private BalanceManagement balanceService;
	private MoneyManagement moneyService;

	public static void main(String[] args) {
		BeanFactory factory = new ClassPathXmlApplicationContext(new String[] {
				"client5.spring.xml", "daos.xml", "hibernate.xml",
				"services.xml" });
		Client5 client = (Client5) factory.getBean("client");
		client.runClient();
	}

	public void runClient() {
		try {
			accountService.createAccount("Jan");
			accountService.createAccount("Piet");
			accountService.createAccount("Henk");
		} catch (AccountNameInUseException e) {
			System.err.println("account in use");
		}

		try {
			accountService.createAccount("Piet");
		} catch (AccountNameInUseException e1) {
			System.err.println("account Piet in use");
		} // gaat fout!

		try {
			moneyService.transferMoney("Jan", "Piet", 150f, "eerste");
			moneyService.transferMoney("Jan", "Piet", 150f, "tweede");
			moneyService.transferMoney("Jan", "Piet", 150f, "derde");
			moneyService.transferMoney("Jan", "Piet", 150f, "vierde");
			moneyService.transferMoney("Jan", "Piet", 150f, "vijfde");
		} catch (TooManyMoneyTransactionsException e) {
			System.err.println("te veel transacties");
		} catch (BalanceTooLowException e) {
			System.err.println("te weinig geld");
		}

		try {
			moneyService.transferMoney("Jan", "Piet", 150f, "zesde"); // te veel
																		// transacties
		} catch (TooManyMoneyTransactionsException e) {
			System.err.println("te veel transacties van Jan");
		} catch (BalanceTooLowException e) {
			System.err.println("te weinig geld");
		}

		try {
			moneyService.transferMoney("Piet", "Henk", 1500f, "boel geld");
		} catch (TooManyMoneyTransactionsException e) {
			System.out.println("te veel transacties");
		} catch (BalanceTooLowException e) {
			System.err.println("te weinig geld bij Piet");
		} // mag niet!

		try {
			moneyService.transferMoney("Piet", "Henk", 1000f, "boel geld");
		} catch (TooManyMoneyTransactionsException e) {
			System.err.println("te veel transacties");
		} catch (BalanceTooLowException e) {
			System.err.println("te weinig geld");
		}

		balanceService.updateBalances();

		System.out.printf("%s: EUR %.2f\n", "Jan",
				accountService.getAccountBalance("Jan"));
		System.out.printf("%s: EUR %.2f\n", "Piet",
				accountService.getAccountBalance("Piet"));
		System.out.printf("%s: EUR %.2f\n", "Henk",
				accountService.getAccountBalance("Henk"));

		System.out.println("ready");
	}

	public void setAccountService(AccountManagement accountService) {
		this.accountService = accountService;
	}

	public AccountManagement getAccountService() {
		return accountService;
	}

	public void setBalanceService(BalanceManagement balanceService) {
		this.balanceService = balanceService;
	}

	public BalanceManagement getBalanceService() {
		return balanceService;
	}

	public void setMoneyService(MoneyManagement managementService) {
		this.moneyService = managementService;
	}

	public MoneyManagement getMoneyService() {
		return moneyService;
	}

}
