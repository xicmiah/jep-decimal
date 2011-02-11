package org.nfunk.jep.type;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Locale;

public class LocalizedBigDecimalNumberFactoryTest {

	NumberFactory<BigDecimal> factory;

	@Before
	public void setUp() throws Exception {
		// Russian locale, decimal separator is comma
		factory = new LocalizedBigDecimalNumberFactory(MathContext.DECIMAL64, new Locale("RU", "ru"));
	}

	@Test
	public void testCreateNumber() throws Exception {
		String number = "1,3";
		BigDecimal decimal = factory.createNumber(number);
		Assert.assertEquals(BigDecimal.valueOf(1.3), decimal);
	}

	@Test(expected = NumberFormatException.class)
	public void testCreateNumberThrowsExceptionOnInvalidNumber() throws Exception {
		factory.createNumber("a1.3");
	}
}
