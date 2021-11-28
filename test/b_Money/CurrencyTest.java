package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR, USD;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.15687);
		USD = new Currency("USD", 2.0);
	}

	@Test
	public void testGetName() {
		assertEquals(SEK.getName(), "SEK");
		assertEquals(DKK.getName(), "DKK");
		assertEquals(EUR.getName(), "EUR");
	}
	
	@Test
	public void testGetRate() {
		assertEquals(SEK.getRate(), (Double)0.15);
		assertEquals(DKK.getRate(), (Double)0.200);
		assertEquals(EUR.getRate(), (Double)1.15687);
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.2);
		DKK.setRate(0.25);
		EUR.setRate(2.4);
		assertEquals(SEK.getRate(), (Double)0.2);
		assertEquals(DKK.getRate(), (Double)0.25);
		assertEquals(EUR.getRate(), (Double)2.4);
	}
	
	@Test
	public void testGlobalValue() {
		assertEquals(SEK.universalValue(1000), (Integer)150);
		assertEquals(DKK.universalValue(100), (Integer)20);
		assertEquals(DKK.universalValue(470), (Integer)94);
		assertEquals(EUR.universalValue(45), (Integer)52);
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals(SEK.valueInThisCurrency(10000, DKK), (Integer)13333);
		assertEquals(EUR.valueInThisCurrency(75, USD), (Integer)129);
	}

}
