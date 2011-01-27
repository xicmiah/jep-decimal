package org.nfunk.jeptesting.operators.bigdecimal;

import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.operator.AbstractAdd;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalAdd;
import org.nfunk.jep.function.operator.doubleval.Add;

import java.math.BigDecimal;
import java.util.Stack;

import static org.junit.Assert.*;

/**
 * @author DPavlov
 */
public class AdditionTest extends BigDecimalTestSupport{

    private BigDecimalAdd add;
    private Stack stack;

    @Before
    public void setUp(){
        add = new BigDecimalAdd();
        add.setMathContext(mc);
        add.setCurNumberOfParameters(2);
        stack = new Stack();
    }

    @Test
    public void testAdditionForDoubleVars() throws ParseException {

        stack.push(1.0);
        stack.push(3.2);
        add.run(stack);
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("4.2",mc).compareTo((BigDecimal) stack.pop()));
    }

    @Test
    public void testAdditionForBigDecimalVars() throws ParseException{
        stack.push(new BigDecimal(100500,mc));
        stack.push(new BigDecimal("100141.3241235", mc));
        add.run(stack);

        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal("200641.3241235", mc).compareTo((BigDecimal) stack.pop()));
    }

    @Test
    public void testCompareBigDecimalAndDouble() throws ParseException {
        fillDoubleStack();

        for (int i=0; i< 9; i++){
            add.run(stack);
        }

        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal(1, mc).compareTo((BigDecimal) stack.pop()));

        double val = 0.0;
        for (int i = 0; i < 10; i++){
            val += 0.1;
        }

        assertFalse("addition without precision loss", 1.0 == val);

        fillDecimalStack();

        for (int i=0; i< 9; i++){
            add.run(stack);
        }

        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", BigDecimal.class, stack.peek().getClass());
        assertEquals("wrong calculation", 0, new BigDecimal(1, mc).compareTo((BigDecimal) stack.pop()));

        fillDoubleStack();

        AbstractAdd doubleAdd = new Add();
        doubleAdd.setCurNumberOfParameters(2);
        for(int i = 0; i< 9; i++){
            doubleAdd.run(stack);
        }
        assertEquals("value was not calculated", 1, stack.size());
        assertNotNull("value is null", stack.peek());
        assertEquals("wrong result class", Double.class, stack.peek().getClass());
        assertFalse("addition without precision loss", 1 == (Double) stack.pop());
    }

    private void fillDoubleStack() {
        stack.clear();
        for (int i = 0; i<10; i++){
            stack.push(0.1);
        }
    }

    private void fillDecimalStack() {
        stack.clear();
        for (int i = 0; i<10; i++){
            stack.push(new BigDecimal(0.1));
        }
    }

}
