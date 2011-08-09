package org.nfunk.jep;

import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.config.BigDecimalConfig;
import org.nfunk.jep.config.ConfigurationBuilder;
import org.nfunk.jep.config.JepConfiguration;
import org.nfunk.jep.type.LocalizedBigDecimalNumberFactory;
import org.nfunk.jep.type.NumberFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class MultiExpressionTest {
	private JEP j;

	@Before
	public void setUp() throws Exception {
		NumberFactory<BigDecimal> numberFactory = new LocalizedBigDecimalNumberFactory(MathContext.DECIMAL64, new Locale("RU", "ru"));
		JepConfiguration configuration = new ConfigurationBuilder()
				.initWith(new BigDecimalConfig(MathContext.DECIMAL64))
				.setVariableFactory(new VariableFactory())
				.setUseImplicitMultiplication(true)
				.setAllowAssignment(true)
				.setAllowUndeclaredVariables(true)
				.setNumberFactory(numberFactory)
				.setUseAlternativeSeparators(true)
				.createConfig();
		j = new JEP(configuration);
	}

	/**
	 * Test that last evaluated expression is returned
	 * @throws Exception
	 */
	@Test
	public void testRubyStyleReturnValues() throws Exception {
		Node node = j.parse("5;4");
		Object result = j.evaluate(node);

		assertEquals(BigDecimal.valueOf(4), result);
	}

	/**
	 * Test that expression separator isn't confused with argument list separator
	 * @throws Exception
	 */
	@Test
	public void testFunctionParameters() throws Exception {
		Node node = j.parse("sum(1;2);sum(3;4)");
		Object result = j.evaluate(node);

		assertEquals(BigDecimal.valueOf(7), result);
	}

	/**
	 * Test that assignment returns last evaluated expression
	 * @throws Exception
	 */
	@Test
	public void testAssignment() throws Exception {
		Node node = j.parse("a = 3; b = a + 4");
		Object result = j.evaluate(node);

		assertEquals(BigDecimal.valueOf(7), result);
	}

	/**
	 * Test that mixed separators are parsed correctly
	 * @throws Exception
	 */
	@Test
	public void testConfusion() throws Exception {
		Node node = j.parse("a = 3,5; b = 2a; sum(a;31,5;b)");
		Object result = j.evaluate(node);

		assertEquals(BigDecimal.valueOf(42.0), result);
	}
}
