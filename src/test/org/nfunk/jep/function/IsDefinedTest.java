package org.nfunk.jep.function;

import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.VariableFactory;
import org.nfunk.jep.config.BigDecimalConfig;
import org.nfunk.jep.config.ConfigurationBuilder;
import org.nfunk.jep.config.JepConfiguration;

import java.math.MathContext;

import static org.junit.Assert.assertEquals;

/**
 * Test isDefined(var) function
 */
public class IsDefinedTest {
	JEP jep;
	
	@Before
	public void setUp() throws Exception {
		JepConfiguration configuration = new ConfigurationBuilder()
				.initWith(new BigDecimalConfig(MathContext.DECIMAL64))
				.setVariableFactory(new VariableFactory())
				.setUseImplicitMultiplication(true)
				.setAllowAssignment(true)
				.setAllowUndeclaredVariables(true)
				.setAllowNullValues(true)
				.createConfig();
		jep = new JEP(configuration);
	}

	private static Object eval(JEP jep, final String expression) throws ParseException {
		Node node = jep.parse(expression);
		return jep.evaluate(node);
	}

	/**
	 * Test that isDefined is a normal function - undefined variables are rejected
	 * @throws Exception
	 */
	@Test
	public void testUndefinedVariables() throws Exception {
		assertEquals(0d, eval(jep, "isDefined(a)"));
	}

	/**
	 * Test that evaluation of initialized variable causes ParseException, even with TrapNullValues disabled
	 * @throws Exception
	 */
	@Test(expected = ParseException.class)
	public void testAnonymousVariables() throws Exception {
		eval(jep, "a");
	}

	/**
	 * Test that isDefined returns false if argument is NULL
	 * @throws Exception
	 */
	@Test
	public void testEvaluation() throws Exception {
		assertEquals(1d, eval(jep, "a = 42; isDefined(a)"));
		assertEquals(0d, eval(jep, "a = NULL; isDefined(a)"));
	}

	/**
	 * Test that isDefined accepts known constants
	 * @throws Exception
	 */
	@Test
	public void testConstants() throws Exception {
		assertEquals(1d, eval(jep, "isDefined(pi)"));
		assertEquals(0d, eval(jep, "isDefined(NULL)"));
	}

	/**
	 * Test that isDefined falls back to true if argument is not a variable
	 * @throws Exception
	 */
	@Test
	public void testFallback() throws Exception {
		assertEquals(1d, eval(jep, "isDefined(42)"));
	}
}
