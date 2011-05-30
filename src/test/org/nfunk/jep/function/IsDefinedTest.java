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
				.createConfig();
		jep = new JEP(configuration);
	}

	private static Object eval(JEP jep, final String expression) throws ParseException {
		Node node = jep.parse(expression);
		return jep.evaluate(node);
	}

	@Test
	public void testEvaluate() throws Exception {
		assertEquals(Boolean.FALSE, eval(jep, "isDefined(a)"));
		assertEquals(Boolean.TRUE, eval(jep, "a = 42; isDefined(a)"));

//		assertEquals(Boolean.FALSE, eval(jep, "a = null; isDefined(a)")); // TODO: define null constant
	}
}
