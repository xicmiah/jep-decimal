package org.nfunk.jep.function.operator.bigdecimal;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.MathContextAware;
import org.nfunk.jep.function.NumberFactoryAware;
import org.nfunk.jep.function.operator.AbstractUMinus;
import org.nfunk.jep.type.NumberFactory;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author DPavlov
 */
public class BigDecimalUMinus extends AbstractUMinus implements MathContextAware, NumberFactoryAware<BigDecimal> {

    private MathContext mathContext;
    private NumberFactory<BigDecimal> numberFactory;

    @Override
    protected Number negateNumber(Number param) throws ParseException {
        BigDecimal val = numberFactory.createNumber(param);
        return val.negate();
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
