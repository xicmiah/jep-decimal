package org.nfunk.jep;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.config.BigDecimalConfig;
import org.nfunk.jep.config.ConfigurationBuilder;
import org.nfunk.jep.config.JepConfiguration;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author DPavlov
 */
public class DecimalJepTest {

    private JEP j;
    private MathContext context;

    @Before
    public void setUp(){
        context = MathContext.DECIMAL64;
        JepConfiguration conf = new ConfigurationBuilder()
                .setVariableFactory(new VariableFactory())
                .initWith(new BigDecimalConfig(context))
                .createConfig();
        j = new JEP(conf);
    }

    @Test
    public void testAddition() throws ParseException {
        String s = "0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1+0.1";
        Node node = j.parse(s);
        Object obj = j.evaluate(node);

        Assert.assertEquals("wrong class", BigDecimal.class, obj.getClass());
        Assert.assertTrue("wrong value", BigDecimal.valueOf(1).compareTo((BigDecimal) obj) == 0);
    }
    
    @Test
    public void testSubtract() throws ParseException {
        String s = "1-0.1-0.1-0.1-0.1-0.1-0.1-0.1-0.1-0.1";
        Node node = j.parse(s);
        Object obj = j.evaluate(node);

        Assert.assertEquals("wrong class", BigDecimal.class, obj.getClass());
        Assert.assertTrue("wrong value", new BigDecimal("0.1").compareTo((BigDecimal) obj) == 0);
    }
    @Test(expected = ArithmeticException.class )
    public void testZeroDivision() throws ParseException {
        String s = "1-0.1/0.0-0.1-0.1-0.1-0.1-0.1-0.1-0.1-0.1";
        Node node = j.parse(s);
        Object obj = j.evaluate(node);
    }
    
    @Test(expected = NumberFormatException.class )
    public void testInfinityToDecimalCreation() throws ParseException {
        String s = "1+ln(0)";
        Node node = j.parse(s);
        Object obj = j.evaluate(node);
    }

}
