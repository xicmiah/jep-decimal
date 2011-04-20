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

public class ConditionalsTest {
	JEP jep;

	@Before
	public void setUp() throws Exception {
		NumberFactory<BigDecimal> numberFactory = new LocalizedBigDecimalNumberFactory(MathContext.DECIMAL64, new Locale("RU", "ru"));
		JepConfiguration configuration = new ConfigurationBuilder()
				.initWith(new BigDecimalConfig(MathContext.DECIMAL64))
				.setVariableFactory(new VariableFactory())
				.setUseImplicitMultiplication(true)
				.setNumberFactory(numberFactory)
				.createConfig();
		jep = new JEP(configuration);
		jep.setTraverse(true);
	}

	private static Object eval(JEP jep, final String expression) throws ParseException {
		Node node = jep.parse(expression);
		return jep.evaluate(node);
	}

	@Test
	public void testIfThenElse() throws Exception {
		Object result = eval(jep, "if (2*2 == 4) then { 1 } else { 2 }");

		assertEquals(BigDecimal.valueOf(1), result);
	}

	@Test
	public void testLongBodies() throws Exception {
		Object result = eval(jep, "if (1) then { 2; 3; } else { 4; 5 }");

		assertEquals(BigDecimal.valueOf(3), result);
	}
}
