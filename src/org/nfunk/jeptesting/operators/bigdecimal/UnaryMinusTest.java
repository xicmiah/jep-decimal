package org.nfunk.jeptesting.operators.bigdecimal;

import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalUMinus;
import org.nfunk.jep.function.operator.doubleval.Subtract;
import org.nfunk.jep.type.BigDecimalNumberFactory;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author DPavlov
 */
public class UnaryMinusTest extends BigDecimalTestSupport{

    private BigDecimalUMinus minus;
    private Stack stack;

    @Before
    public void setUp(){
        minus = new BigDecimalUMinus();
        minus.setMathContext(mc);
        minus.setNumberFactory(new BigDecimalNumberFactory(mc));
        stack = new Stack();
    }

    @Test
    public void testMinusForDoubleVars() throws ParseException {

        stack.push(1.0);
        minus.run(stack);
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("-1",mc).compareTo((BigDecimal) stack.pop()));
    }

    @Test
    public void testMinusForBigDecimalVars() throws ParseException{
        stack.push(new BigDecimal(2.55,mc));
        minus.run(stack);

        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("-2.55", mc).compareTo((BigDecimal) stack.pop()));
    }

}
