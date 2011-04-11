package org.nfunk.jep;

import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.config.BigDecimalConfig;
import org.nfunk.jep.config.ConfigurationBuilder;
import org.nfunk.jep.config.JepConfiguration;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalAdd;
import org.nfunk.jep.type.LocalizedBigDecimalNumberFactory;
import org.nfunk.jep.type.NumberFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AlternateSeparatorsTest {
	JEP j;

	@Before
	public void setUp() throws Exception {

		NumberFactory<BigDecimal> numberFactory = new LocalizedBigDecimalNumberFactory(MathContext.DECIMAL64, new Locale("RU", "ru"));
		JepConfiguration configuration = new ConfigurationBuilder()
				.initWith(new BigDecimalConfig(MathContext.DECIMAL64))
				.setVariableFactory(new VariableFactory())
				.setUseImplicitMultiplication(true)
				.setNumberFactory(numberFactory)
				.createConfig();
		j = new JEP(configuration);

		// Switch to alternative separators
		j.getParser().setInitialTokenManagerState(ParserConstants.ALT_SEPARATORS);
	}


	@Test
	public void testBigDecimalUsage() throws Exception {
		Node node = j.parse("1,5");
		Object result = j.evaluate(node);

		assertThat(result, instanceOf(BigDecimal.class));
		assertThat((BigDecimal) result, equalTo(BigDecimal.valueOf(1.5)));
	}

	@Test
	public void testExpressionParsing() throws Exception {
		Node node = j.parse("31,7 + 10,3");
		BigDecimal result = (BigDecimal) j.evaluate(node);
		assertEquals(BigDecimal.valueOf(42.0), result);
	}

	@Test
	public void testFunctionParameters() throws Exception {
		Node node = j.parse("sum(31,7;10,3)");
		BigDecimal result = (BigDecimal) j.evaluate(node);
		assertEquals(BigDecimal.valueOf(42.0), result);
	}
}
