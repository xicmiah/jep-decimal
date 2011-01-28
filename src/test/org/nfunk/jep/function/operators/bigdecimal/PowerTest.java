package org.nfunk.jep.function.operators.bigdecimal;

import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalPower;
import org.nfunk.jep.function.operator.doubleval.Modulus;
import org.nfunk.jep.type.BigDecimalNumberFactory;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * @author DPavlov
 */
public class PowerTest extends BigDecimalTestSupport{

     private BigDecimalPower pow;
    private Stack stack;

    @Before
    public void setUp(){
        pow = new BigDecimalPower();
        pow.setMathContext(mc);
        pow.setNumberFactory(new BigDecimalNumberFactory(mc));
        stack = new Stack();
    }

    @Test
    public void testPowerForDoubleVars() throws ParseException {
        stack.push(4);
        stack.push(0.5);
        pow.run(stack);
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("2",mc).compareTo((BigDecimal) stack.pop()));

        stack.push(2.5);
        stack.push(2);
        pow.run(stack);
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("6.25",mc).compareTo((BigDecimal) stack.pop()));

        stack.push(4);
        stack.push(0.2);
        pow.run(stack);
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("1.3195079107728942593740019712296",mc).compareTo((BigDecimal) stack.pop()));
    }

    @Test
    public void testPowerForBigDecimalVars() throws ParseException{
        stack.push(new BigDecimal(4,mc));
        stack.push(new BigDecimal("0.5", mc));
        pow.run(stack);

        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("2", mc).compareTo((BigDecimal) stack.pop()));

        stack.push(new BigDecimal(4,mc));
        stack.push(new BigDecimal(2, mc));
        pow.run(stack);

        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("16", mc).compareTo((BigDecimal) stack.pop()));
    }

    @Test
    /**
     * Test that shows precision problems when initialization big decimal from double values
     */
    public void testMultiplyPrecision() throws ParseException {
        stack.push(0.1);
        stack.push(3);
        pow.run(stack);

        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        //for decimal we can use native pow when the exponent is integer
        assertEquals("wrong calculation", 0, new BigDecimal("0.001", mc).compareTo((BigDecimal) stack.pop()));

// will be 0.0010000000000000002
        assertFalse("double precision", (Math.pow(0.1,3.0)) == 0.001);

        stack.push(0.1);
        stack.push(3);
        new Modulus().run(stack);
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", Double.class, stack.peek().getClass());
        assertFalse("wrong calculation", 0.001 == (Double) stack.pop());
    }

}
