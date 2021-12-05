package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		//Test of adding and removing timed payments.
		testAccount.addTimedPayment("Internet", 5, 30, new Money(4400, new Currency("SEK", 0.21)), SweBank, "Alice");
		testAccount.removeTimedPayment("Internet");
		assertFalse(testAccount.timedPaymentExists("Internet"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		//Test of adding timed payment.
		testAccount.addTimedPayment("Internet", 5, 30, new Money(4400, new Currency("SEK", 0.21)), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("Internet"));
	}

	@Test
	public void testAddWithdraw() {
		//Test of withdraw function.
		testAccount.withdraw(new Money(5200, new Currency("SEK", 0.21)));
	}
	
	@Test
	public void testGetBalance() {
		//Test of getBalance() function.
		assertEquals(testAccount.getBalance(), (Integer)10000000);
	}
}
