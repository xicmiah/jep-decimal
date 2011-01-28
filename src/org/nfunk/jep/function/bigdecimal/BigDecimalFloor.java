package org.nfunk.jep.function.bigdecimal;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.AbstractFloor;
import org.nfunk.jep.function.MathContextAware;
import org.nfunk.jep.function.NumberFactoryAware;
import org.nfunk.jep.type.NumberFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author DPavlov
 */
public class BigDecimalFloor extends AbstractFloor implements MathContextAware, NumberFactoryAware<BigDecimal> {

    private MathContext mathContext;
    private NumberFactory<BigDecimal> numberFactory;

    @Override
    protected Number floorNumber(Number param) throws ParseException {
        return numberFactory.createNumber(param).setScale(0, RoundingMode.FLOOR);
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
