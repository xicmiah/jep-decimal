package org.nfunk.jep.function.operator.bigdecimal;

import org.nfunk.jep.function.operator.AbstractPower;
import org.nfunk.jep.type.Complex;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author DPavlov
 */
public class BigDecimalPower extends AbstractPower implements MathContextAware {

    private MathContext mathContext;

    @Override
    protected Object powerNumber(Number d1, Number d2) {
        if (d1.doubleValue() < 0 && d2.doubleValue() != d2.intValue()) {
            Complex c = new Complex(d1.doubleValue(), 0.0);
            return c.power(d2.doubleValue());
        } else {

            if (powerIsInteger(d2)) {
                //power value is integer, so we can use std big decimal power operation
                BigDecimal bd1;

                if (d1 instanceof BigDecimal) {
                    bd1 = (BigDecimal) d1;
                } else {
                    bd1 = new BigDecimal(d1.doubleValue(), mathContext);
                }

                return bd1.pow(d2.intValue(), mathContext);
            } else {
                return new BigDecimal(Math.pow(d1.doubleValue(), d2.doubleValue()), mathContext);
            }
        }
    }

    private boolean powerIsInteger(Number d2) {
        if (d2 instanceof BigDecimal) {
            BigDecimal bd = (BigDecimal) d2;
            return bd.compareTo(bd.setScale(0, mathContext.getRoundingMode())) == 0;
        } else {
            return d2.doubleValue() == d2.intValue();
        }
    }

    public void setMathContext(MathContext context) {
        this.mathContext = context;
    }

    public MathContext getMathContext() {
        return mathContext;
    }
}
