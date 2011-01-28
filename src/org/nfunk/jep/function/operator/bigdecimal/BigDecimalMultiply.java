package org.nfunk.jep.function.operator.bigdecimal;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.MathContextAware;
import org.nfunk.jep.function.NumberFactoryAware;
import org.nfunk.jep.function.operator.AbstractMultiply;
import org.nfunk.jep.type.NumberFactory;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author DPavlov
 */
public class BigDecimalMultiply extends AbstractMultiply implements MathContextAware, NumberFactoryAware<BigDecimal> {

    private MathContext mathContext;
    private NumberFactory<BigDecimal> numberFactory;

    @Override
    protected Number mulNumber(Number d1, Number d2) throws ParseException {
        BigDecimal bd1 = numberFactory.createNumber(d1);
        BigDecimal bd2 = numberFactory.createNumber(d2);        

        return bd1.multiply(bd2, mathContext);
    }

    public MathContext getMathContext() {
        return mathContext;
    }

    public void setMathContext(MathContext mathContext) {
        this.mathContext = mathContext;
    }

    public void setNumberFactory(NumberFactory<BigDecimal> numberFactory) {
        this.numberFactory = numberFactory;
    }
}
