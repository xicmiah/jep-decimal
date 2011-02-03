package org.nfunk.jep.function.operator.bdnonstrict;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalMultiply;

import java.math.BigDecimal;

/**
 * @author DPavlov
 */
public class BDMultiplyNonstrict extends BigDecimalMultiply {

    @Override
    protected Number mulNumber(Number v1, Number v2) throws ParseException {
        double d1 = v1.doubleValue();
        double d2 = v2.doubleValue();
        if (Double.isNaN(d1)
                || Double.isNaN(d2)
                || Double.isInfinite(d1)
                || Double.isInfinite(d2)) {
            double result = d1 * d2;

            if (Double.isNaN(result) || Double.isInfinite(result)) return result;

            return BigDecimal.ZERO;
        }

        return super.mulNumber(v1, v2);
    }
}
