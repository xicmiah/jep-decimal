package org.nfunk.jep.function.bigdecimal;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.AbstractAbs;
import org.nfunk.jep.function.MathContextAware;
import org.nfunk.jep.function.NumberFactoryAware;
import org.nfunk.jep.type.NumberFactory;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author DPavlov
 */
public class BigDecimalAbs extends AbstractAbs implements MathContextAware, NumberFactoryAware<BigDecimal>{

    private MathContext mathContext;
    private NumberFactory<BigDecimal> factory;

    @Override
    protected Number getNumber(double doubleVal) throws ParseException {
        return factory.createNumber(doubleVal);
    }

    @Override
    protected Number absNumber(Number number) throws ParseException {
        return factory.createNumber(number).abs(mathContext);
    }

    public MathContext getMathContext() {
        return mathContext;
    }

    public void setMathContext(MathContext mathContext) {
        this.mathContext = mathContext;
    }

    public void setNumberFactory(NumberFactory<BigDecimal> factory) {
        this.factory = factory;
    }
}
