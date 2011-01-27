package org.nfunk.jep.function.operator.bigdecimal;

import org.nfunk.jep.function.operator.AbstractModulus;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author DPavlov
 */
public class BigDecimalModulus extends AbstractModulus implements MathContextAware {

    private MathContext mathContext;

    @Override
    protected Number modulus(Number d1, Number d2) {
        BigDecimal bd1;
        BigDecimal bd2;
        if (d1 instanceof BigDecimal) {
            bd1 = (BigDecimal) d1;
        } else {
            bd1 = new BigDecimal(d1.doubleValue(), mathContext);
        }

        if (d2 instanceof BigDecimal) {
            bd2 = (BigDecimal) d2;
        } else {
            bd2 = new BigDecimal(d2.doubleValue(), mathContext);
        }

        return bd1.remainder(bd2, mathContext);
    }

    public MathContext getMathContext() {
        return mathContext;
    }

    public void setMathContext(MathContext mathContext) {
        this.mathContext = mathContext;
    }
}
