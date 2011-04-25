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

/**
 * Test parsing of expressions with mandatory return
 */
public class CalcExpressionTest {
	JEP jep;

	@Before
	public void setUp() throws Exception {
		NumberFactory<BigDecimal> numberFactory = new LocalizedBigDecimalNumberFactory(MathContext.DECIMAL64, new Locale("RU", "ru"));
		JepConfiguration configuration = new ConfigurationBuilder()
				.initWith(new BigDecimalConfig(MathContext.DECIMAL64))
				.setVariableFactory(new VariableFactory())
				.setUseImplicitMultiplication(true)
				.setNumberFactory(numberFactory)
				.setAllowAssignment(true)
				.setAllowUndeclaredVariables(true)
				.createConfig();
		jep = new JEP(configuration);
	}

	private Object evalReturn(String expression) throws ParseException {
		Node node = jep.parseReturnExpression(expression);
		return jep.evaluate(node);
	}

	/**
	 * Test that parseReturnExpression works
	 * @throws Exception
	 */
	@Test
	public void testNormalExpression() throws Exception {
		String expression = "a = 3*7; return a*2";
		Object result = evalReturn(expression);

		assertEquals(BigDecimal.valueOf(42), result);
	}

	/**
	 * Test that parseReturnExpression accepts single "return" statement
	 * @throws Exception
	 */
	@Test
	public void testSingleReturn() throws Exception {
		String expression = "return 43 + 16";
		Object result = evalReturn(expression);

		assertEquals(BigDecimal.valueOf(59), result);
	}

	/**
	 * Test that parseReturnExpression is mandatory
	 * @throws Exception
	 */
	@Test(expected = ParseException.class)
	public void testMandatoryReturn() throws Exception {
		jep.parseReturnExpression("84/2;");
	}

	/**
	 * Test that parseReturnExpression rejects return in middle of expression
	 * @throws Exception
	 */
	@Test(expected = ParseException.class)
	public void testMiddleReturn() throws Exception {
		jep.parseReturnExpression("a = 3*7; return a*2; a*3");
	}

	/**
	 * Test that parseReturnExpression rejects multiple returns
	 * @throws Exception
	 */
	@Test(expected = ParseException.class)
	public void testMultipleReturns() throws Exception {
		jep.parseReturnExpression("a = 3*7; return a*2; return a*3;");
	}

	/**
	 * Test that parseReturnExpression rejects assignment inside return
	 * @throws Exception
	 */
	@Test(expected = ParseException.class)
	public void testAssignInReturn() throws Exception {
		jep.parseReturnExpression("a = 3*7; return a = 15");
	}

	/**
	 * Test that parseReturnExpression rejects "if" inside return
	 * @throws Exception
	 */
	@Test(expected = ParseException.class)
	public void testConditionalInReturn() throws Exception {
		jep.parseReturnExpression("return if (1) then {2} else {3}");
	}
}
