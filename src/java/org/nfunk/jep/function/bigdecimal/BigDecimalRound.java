package org.nfunk.jep.function.bigdecimal;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.AbstractRound;
import org.nfunk.jep.function.MathContextAware;
import org.nfunk.jep.function.NumberFactoryAware;
import org.nfunk.jep.type.NumberFactory;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Round operation impl for big decimal numbers.
 *
 * @author DPavlov
 */
public class BigDecimalRound extends AbstractRound implements MathContextAware, NumberFactoryAware<BigDecimal> {

    private MathContext mathContext;
    private NumberFactory<BigDecimal> numberFactory;

    @Override
    protected Object roundNumber(Number l, Number r) throws ParseException {
        int scale = r.intValue();

        return numberFactory.createNumber(l).setScale(scale, mathContext.getRoundingMode());
    }

    @Override
    protected Object roundNumber(Number param) throws ParseException {
        BigDecimal bd = numberFactory.createNumber(param);

        return bd.setScale(0, mathContext.getRoundingMode());
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
