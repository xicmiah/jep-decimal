package org.nfunk.jep.function.operators.bigdecimal;

import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalMultiply;
import org.nfunk.jep.type.BigDecimalNumberFactory;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author DPavlov
 */
public class MultiplyTest extends BigDecimalTestSupport{

    private BigDecimalMultiply mult;
    private Stack stack;

    @Before
    public void setUp(){
        mult = new BigDecimalMultiply();
        mult.setMathContext(mc);
        mult.setCurNumberOfParameters(2);
        mult.setNumberFactory(new BigDecimalNumberFactory(mc));
        stack = new Stack();
    }

    @Test
    public void testMultiplyForDoubleVars() throws ParseException {
        stack.push(1.5);
        stack.push(3.2);
        mult.run(stack);
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("4.8",mc).compareTo((BigDecimal) stack.pop()));
    }

    @Test
    public void testMultiplyForBigDecimalVars() throws ParseException{
        stack.push(new BigDecimal(100500,mc));
        stack.push(new BigDecimal("0.100500", mc));
        mult.run(stack);

        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("10100.25", mc).compareTo((BigDecimal) stack.pop()));
    }

    @Test
    /**
     * Test that shows precision problems when initialization big decimal from double values
     */
    public void testMultiplyPrecision() throws ParseException {
        //will be converted to 33.333333333333336
        stack.push(33.3333333333333333333333333);
        stack.push(100);
        mult.run(stack);

        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        //here we have bugs in conversion from double to decimal
        assertEquals("wrong calculation", 0, new BigDecimal("3333.333333333334", mc).compareTo((BigDecimal) stack.pop()));

        //and here is all fine

        stack.push(new BigDecimal("33.3333333333333333333333333"));
        stack.push(new BigDecimal(100));
        mult.run(stack);

        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("3333.33333333333333", mc).compareTo((BigDecimal) stack.pop()));
    }

}
