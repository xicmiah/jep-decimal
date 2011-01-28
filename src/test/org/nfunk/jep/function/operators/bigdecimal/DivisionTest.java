package org.nfunk.jep.function.operators.bigdecimal;

import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalDivide;
import org.nfunk.jep.type.BigDecimalNumberFactory;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author DPavlov
 */
public class DivisionTest extends BigDecimalTestSupport{

    private BigDecimalDivide div;
    private Stack stack;

    @Before
    public void setUp(){
        div = new BigDecimalDivide();
        div.setMathContext(mc);
        div.setNumberFactory(new BigDecimalNumberFactory(mc));
        stack = new Stack();
    }

    @Test
    public void testDivideForDoubleVars() throws ParseException {
        stack.push(1.5);
        stack.push(3.2);
        div.run(stack);
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("0.46875",mc).compareTo((BigDecimal) stack.pop()));
    }

    @Test
    public void testDivideForBigDecimalVars() throws ParseException{
        stack.push(new BigDecimal(100500,mc));
        stack.push(new BigDecimal("0.100500", mc));
        div.run(stack);

        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("1000000", mc).compareTo((BigDecimal) stack.pop()));
    }    

}
