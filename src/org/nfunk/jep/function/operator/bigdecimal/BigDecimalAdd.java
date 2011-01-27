package org.nfunk.jep.function.operator.bigdecimal;

import org.nfunk.jep.function.operator.AbstractAdd;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author DPavlov
 */
public class BigDecimalAdd extends AbstractAdd implements MathContextAware{

    private MathContext mathContext;

    @Override
    protected Number addNumber(Number v1, Number v2) {
        BigDecimal bd1;
        BigDecimal bd2;
        if (v1 instanceof BigDecimal){
            bd1 = (BigDecimal) v1;
        }else{
            bd1 = new BigDecimal(v1.doubleValue(), mathContext);
        }

        if (v2 instanceof BigDecimal){
            bd2 = (BigDecimal) v2;
        }else{
            bd2 = new BigDecimal(v2.doubleValue(), mathContext);
        }

        return bd1.add(bd2, mathContext);
    }

    public MathContext getMathContext() {
        return mathContext;
    }

    public void setMathContext(MathContext mathContext) {
        this.mathContext = mathContext;
    }
}
