package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		//Simple test if getAmount() returns the correct amount.
		assertEquals(EUR20.getAmount(), (Integer)2000);
		assertEquals(SEK0.getAmount(), (Integer)0);
	}

	@Test
	public void testGetCurrency() {
		//Simple test if getCurrency() returns the correct currency.
		assertEquals(SEK100.getCurrency(), SEK);
	    assertEquals(SEK200.getCurrency(), SEK);
	}

	@Test
	public void testToString() {
		//Test of string representation
		System.out.println(EUR10.toString());
		System.out.println(SEK100.toString());
	}

	@Test
	public void testGlobalValue() {
		//Testing of converting amount of the currency to universal amount.
		assertEquals(EUR10.universalValue(), (Integer)1500);
	}

	@Test
	public void testEqualsMoney() {
		//Testing if some amount of money in a given currency is equal to another one.
		assertTrue(SEK100.equals(SEK100));
		assertFalse(SEK100.equals(EUR0));
	}

	@Test
	public void testAdd() {
		//Testing if addition function function works in the proper way.
		Money SEKnew = SEK200.add(SEK100);
		assertEquals(SEKnew.getAmount(), (Integer)30000);
	}

	@Test
	public void testSub() {
		//Testing if subtraction function function works in the proper way.
		Money SEKnew = SEK200.sub(SEK100);
		assertEquals(SEKnew.getAmount(), (Integer)10000);
	}

	@Test
	public void testIsZero() {
		//Testing is some anount of money is qual to 0.
		assertTrue(SEK0.isZero());
		assertFalse(SEK100.isZero());
	}

	@Test
	public void testNegate() {
		//Testing of negating the amount function.
		assertEquals(SEK100.negate().getAmount(), SEKn100.getAmount());
	}

	@Test
	public void testCompareTo() {
		//Test of compareTo method
		assertEquals(SEK100.compareTo(SEK100), 0);
		assertEquals(SEK0.compareTo(SEK100), -1);
		assertEquals(EUR20.compareTo(EUR10), 1);
	}
}
