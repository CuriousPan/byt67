package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals(SweBank.getName(), "SweBank");
		assertEquals(Nordea.getName(), "Nordea");
		assertEquals(DanskeBank.getName(), "DanskeBank");
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SweBank.getCurrency(), SEK);
		assertEquals(Nordea.getCurrency(), SEK);
		assertEquals(DanskeBank.getCurrency(), DKK);
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("A");
//		SweBank.openAccount("A"); opening already existing account
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(1000, SEK));
//		SweBank.deposit("A", new Money(111, DKK)); attempt to deposit to non-existing account
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.withdraw("Bob", new Money(1000, SEK));
//		SweBank.withdraw("A", new Money(1000, SEK)); attempt to withdraw from non-existing account		
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		SweBank.getBalance("Bob");
//		SweBank.getBalance("A"); attempt to get balance of non-existing bank-account
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.transfer("Bob", "Ulrika", new Money(4550, SEK));
		SweBank.transfer("Bob", Nordea, "Bob", new Money(3300, SEK));
//		SweBank.transfer("A", "Bob", new Money(333, SEK)); attempt to transfer form non-existing account
//		SweBank.transfer("Bob", "A", new Money(333, SEK));// attempt to transfer to non-existing account

	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Bob", "Internet", 5, 30, new Money(2300, SEK), Nordea, "Ulrika");
//		SweBank.removeTimedPayment("A", "Internet"); attempt to remove time payment of non-existing account
		SweBank.removeTimedPayment("Bob", "Internet");
		SweBank.tick();
	}
}
