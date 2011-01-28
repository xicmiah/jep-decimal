package org.nfunk.jeptesting.operators.bigdecimal;

import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalSubtract;
import org.nfunk.jep.function.operator.doubleval.Subtract;
import org.nfunk.jep.type.BigDecimalNumberFactory;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.*;

/**
 * @author DPavlov
 */

public class SubtractionTest extends BigDecimalTestSupport{
    private BigDecimalSubtract subtract;
    private Stack stack;

    @Before
    public void setUp(){
        subtract = new BigDecimalSubtract();
        subtract.setMathContext(mc);
        subtract.setNumberFactory(new BigDecimalNumberFactory(mc));
        stack = new Stack();
    }

    @Test
    public void testSubtractionForDoubleVars() throws ParseException {

        stack.push(1.0);
        stack.push(3.2);
        subtract.run(stack);
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("-2.2",mc).compareTo((BigDecimal) stack.pop()));
    }

    @Test
    public void testSubtractionForBigDecimalVars() throws ParseException{
        stack.push(new BigDecimal(100500,mc));
        stack.push(new BigDecimal("100141.3241235", mc));
        subtract.run(stack);

        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("358.6758765", mc).compareTo((BigDecimal) stack.pop()));
    }

    @Test
    public void testCompareBigDecimalAndDouble() throws ParseException {
        stack.push(new BigDecimal(1,mc));
        stack.push(new BigDecimal(0.9, mc));
        subtract.run(stack);

        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal(0.1, mc).compareTo((BigDecimal) stack.pop()));

        assertFalse("subtraction without precision loss", 0.1 == (1-0.9));

        stack.push(1);
        stack.push(0.9);
        subtract.run(stack);
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal(0.1, mc).compareTo((BigDecimal) stack.pop()));

        stack.push(1);
        stack.push(0.9);
        new Subtract().run(stack);
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", Double.class, stack.peek().getClass());
        assertFalse("wrong calculation", 0.1 == (Double) stack.pop());
    }

}
