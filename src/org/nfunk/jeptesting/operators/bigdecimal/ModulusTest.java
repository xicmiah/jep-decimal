package org.nfunk.jeptesting.operators.bigdecimal;

import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalModulus;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalMultiply;
import org.nfunk.jep.function.operator.doubleval.Modulus;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.*;

/**
 * @author DPavlov
 */
public class ModulusTest extends BigDecimalTestSupport{

    private BigDecimalModulus mod;
    private Stack stack;

    @Before
    public void setUp(){
        mod = new BigDecimalModulus();
        mod.setMathContext(mc);
        stack = new Stack();
    }

    @Test
    public void testModulusForDoubleVars() throws ParseException {
        stack.push(6.5);
        stack.push(3.2);
        mod.run(stack);
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("0.1",mc).compareTo((BigDecimal) stack.pop()));
    }

    @Test
    public void testModulusForBigDecimalVars() throws ParseException{
        stack.push(new BigDecimal("10",mc));
        stack.push(new BigDecimal("3.1", mc));
        mod.run(stack);

        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("0.7", mc).compareTo((BigDecimal) stack.pop()));
    }

    @Test
    public void testModulusPrecision() throws ParseException {
        stack.push(6.5);
        stack.push(3.2);
        mod.run(stack);
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("0.1",mc).compareTo((BigDecimal) stack.pop()));

        assertFalse("double precision", (6.5 % 3.2) == 0.1);

        stack.push(6.5);
        stack.push(3.2);
        new Modulus().run(stack);
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", Double.class, stack.peek().getClass());
        assertFalse("wrong calculation", 0.1 == (Double) stack.pop());

    }

}
