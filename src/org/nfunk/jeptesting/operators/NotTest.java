package org.nfunk.jeptesting.operators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.operator.Not;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * @author DPavlov
 */
public class NotTest {
    private Not not;
    private Stack stack;

    @Before
    public void setUp(){
        not = new Not();
        stack = new Stack();
    }

    @Test
    public void testBigDecimalNot() throws ParseException {
        stack.push(new BigDecimal("-1.3"));
        not.run(stack);

        Assert.assertEquals(0.0, ((Double)stack.pop()).doubleValue(), 1e-5);

        stack.push(new BigDecimal("1"));
        not.run(stack);

        Assert.assertEquals(0.0, ((Double)stack.pop()).doubleValue(), 1e-5);

        stack.push(new BigDecimal("0"));
        not.run(stack);

        Assert.assertEquals(1.0, ((Double)stack.pop()).doubleValue(), 1e-5);

        stack.push(new BigDecimal("-0.00"));
        not.run(stack);

        Assert.assertEquals(1.0, ((Double)stack.pop()).doubleValue(), 1e-5);

        stack.push(new BigDecimal(-0.00));
        not.run(stack);

        Assert.assertEquals(1.0, ((Double)stack.pop()).doubleValue(), 1e-5);
    }

}
