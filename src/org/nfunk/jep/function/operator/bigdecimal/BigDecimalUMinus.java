package org.nfunk.jep.function.operator.bigdecimal;

import org.nfunk.jep.function.operator.AbstractUMinus;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author DPavlov
 */
public class BigDecimalUMinus extends AbstractUMinus implements MathContextAware{

    private MathContext mathContext;

    @Override
    protected Number negateNumber(Number param) {
        BigDecimal val;
        if (param instanceof BigDecimal){
            val = (BigDecimal) param;
        }else{
            val = new BigDecimal(param.doubleValue(), mathContext);
        }
        return val.negate();
    }

    public MathContext getMathContext() {
        return mathContext;
    }

    public void setMathContext(MathContext mathContext) {
        this.mathContext = mathContext;
    }
}
