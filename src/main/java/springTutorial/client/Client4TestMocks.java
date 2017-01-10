package springTutorial.client;

import org.junit.Test;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

import springTutorial.services.exceptions.AccountNameInUseException;
import springTutorial.services.exceptions.BalanceTooLowException;
import springTutorial.services.exceptions.TooManyMoneyTransactionsException;
import springTutorial.services.interfaces.AccountManagement;
import springTutorial.services.interfaces.BalanceManagement;
import springTutorial.services.interfaces.MoneyManagement;

public class Client4TestMocks extends
		AbstractDependencyInjectionSpringContextTests {

	private AccountManagement accountManagement;
	private BalanceManagement balanceManagement;
	private MoneyManagement moneyManagement;

	@Test
	public void testSomething() {
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

		assertEquals(-750F, accountManagement.getAccountBalance("Jan"));
		assertEquals(-250F, accountManagement.getAccountBalance("Piet"));
		assertEquals(1000F, accountManagement.getAccountBalance("Henk"));
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

	@Override
	protected String[] getConfigLocations() {
		return new String[] {
				"classpath:springTutorial/client/client4.spring.xml",
				"classpath:springTutorial/client/mocks4.spring.xml" };
	}

}
