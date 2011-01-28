package org.nfunk.jep.function.operators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.operator.Logical;
import org.nfunk.jep.function.operators.bigdecimal.BigDecimalTestSupport;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * @author DPavlov
 */
@SuppressWarnings({"unchecked", "UnnecessaryUnboxing"})
public class LogicalTest extends BigDecimalTestSupport{

    private Logical logical;
    private Stack stack;

    @Before
    public void setUp() {
        stack = new Stack();
    }

    @Test
    public void testBigDecimalToBoolAnd() throws ParseException {
        logical = new Logical(Logical.AND);
        stack.push(new BigDecimal("4.3", mc));
        stack.push(new BigDecimal("-1", mc));
        logical.run(stack);

        Assert.assertEquals(1.0, ((Double)stack.pop()).doubleValue(), 1e-5);

        stack.push(new BigDecimal(".000000001", mc));
        stack.push(new BigDecimal("100500", mc));
        logical.run(stack);

        Assert.assertEquals(1.0, ((Double)stack.pop()).doubleValue(), 1e-5);

        stack.push(new BigDecimal("0.000", mc));
        stack.push(new BigDecimal("100500", mc));
        logical.run(stack);

        Assert.assertEquals(0.0, ((Double)stack.pop()).doubleValue(), 1e-5);

        stack.push(new BigDecimal("10.000", mc));
        stack.push(new BigDecimal(0, mc));
        logical.run(stack);

        Assert.assertEquals(0.0, ((Double)stack.pop()).doubleValue(), 1e-5);
        
        stack.push(new BigDecimal("0.000", mc));
        stack.push(new BigDecimal(0, mc));
        logical.run(stack);

        Assert.assertEquals(0.0, ((Double)stack.pop()).doubleValue(), 1e-5);


        stack.push(new BigDecimal("-0.000", mc));
        stack.push(new BigDecimal("+0.00", mc));
        logical.run(stack);

        Assert.assertEquals(0.0, ((Double)stack.pop()).doubleValue(), 1e-5);
    }

    @Test
    public void testBigDecimalToBoolOr() throws ParseException {
        logical = new Logical(Logical.OR);

        stack.push(new BigDecimal("4.3", mc));
        stack.push(new BigDecimal("-1", mc));
        logical.run(stack);

        Assert.assertEquals(1.0, ((Double)stack.pop()).doubleValue(), 1e-5);

        stack.push(new BigDecimal(".000000001", mc));
        stack.push(new BigDecimal("100500", mc));
        logical.run(stack);

        Assert.assertEquals(1.0, ((Double)stack.pop()).doubleValue(), 1e-5);

        stack.push(new BigDecimal("0.000", mc));
        stack.push(new BigDecimal("100500", mc));
        logical.run(stack);

        Assert.assertEquals(1.0, ((Double)stack.pop()).doubleValue(), 1e-5);

        stack.push(new BigDecimal("10.000", mc));
        stack.push(new BigDecimal(0, mc));
        logical.run(stack);

        Assert.assertEquals(1.0, ((Double)stack.pop()).doubleValue(), 1e-5);

        stack.push(new BigDecimal("0.000", mc));
        stack.push(new BigDecimal(0, mc));
        logical.run(stack);

        Assert.assertEquals(0.0, ((Double)stack.pop()).doubleValue(), 1e-5);


        stack.push(new BigDecimal("-0.000", mc));
        stack.push(new BigDecimal("+0.00", mc));
        logical.run(stack);

        Assert.assertEquals(0.0, ((Double)stack.pop()).doubleValue(), 1e-5);
    }

}
