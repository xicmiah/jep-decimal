package org.nfunk.jep;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.config.BigDecimalConfig;
import org.nfunk.jep.config.ComplexConfig;
import org.nfunk.jep.config.ConfigurationBuilder;
import org.nfunk.jep.config.JepConfiguration;
import org.nfunk.jep.type.LocalizedBigDecimalNumberFactory;
import org.nfunk.jep.type.NumberFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * @author dpavlov
 */
public class NullComparementsJepTest {

    private JEP jep;

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
                .setAllowNullValues(true)
                .add(new ComplexConfig())
				.createConfig();
		jep = new JEP(configuration);
		jep.setTraverse(true);
    }

    private static <T> T eval(String expression, JEP jep) throws ParseException {
        Node node = jep.parseExpression(expression);
        return (T) jep.evaluate(node);
    }

    @Test
    public void testEqualityWithNull() throws Exception {
        assertEquals(1.0d, eval("NULL == NULL", jep));
        assertEquals(0d, eval("1 == NULL", jep));
        assertEquals(0d, eval("NULL == 1", jep));
        assertEquals(0d, eval("NULL == 0", jep));
        assertEquals(0d, eval("0 == NULL", jep));
        assertEquals(0d, eval("i == NULL", jep));
        assertEquals(0d, eval("\"hello\" == NULL", jep));
    }

    @Test
    public void testNonEqualityWithNull() throws Exception {
        assertEquals(0d, eval("NULL != NULL", jep));
        assertEquals(1d, eval("1 != NULL", jep));
        assertEquals(1d, eval("NULL != 1", jep));
        assertEquals(1d, eval("NULL != 0", jep));
        assertEquals(1d, eval("0 != NULL", jep));
        assertEquals(1d, eval("i != NULL", jep));
        assertEquals(1d, eval("\"hello\" != NULL", jep));
    }

    @Test(expected = ParseException.class)
    public void testGreaterOperationWithNull() throws Exception {
        eval("NULL > 1", jep);
    }

    @Test(expected = ParseException.class)
    public void testGreaterOperationBothNull() throws Exception {
        eval("NULL > NULL", jep);
    }

    @Test(expected = ParseException.class)
    public void testLessOperationWithNull() throws Exception {
        eval("NULL < 1", jep);
    }

    @Test(expected = ParseException.class)
    public void testLessOperationBothNull() throws Exception {
        eval("NULL < NULL", jep);
    }

    @Test(expected = ParseException.class)
    public void testGreaterEqualsOperationWithNull() throws Exception {
        eval("NULL >= 1", jep);
    }

    @Test(expected = ParseException.class)
    public void testGreaterEqualsOperationBothNull() throws Exception {
        eval("NULL >= NULL", jep);
    }

    @Test(expected = ParseException.class)
    public void testLessEqualsOperationWithNull() throws Exception {
        eval("NULL <= 1", jep);
    }

    @Test(expected = ParseException.class)
    public void testLessEqualsOperationBothNull() throws Exception {
        eval("NULL <= NULL", jep);
    }
}
