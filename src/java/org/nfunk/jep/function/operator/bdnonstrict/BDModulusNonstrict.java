package org.nfunk.jep.function.operator.bdnonstrict;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalModulus;

import java.math.BigDecimal;

/**
 * @author DPavlov
 */
public class BDModulusNonstrict extends BigDecimalModulus{

    @Override
    protected Number modulus(Number v1, Number v2) throws ParseException {
        double d1 = v1.doubleValue();
        double d2 = v2.doubleValue();
        if (Double.isNaN(d1)
                || Double.isNaN(d2)
                || Double.isInfinite(d1)
                || Double.isInfinite(d2)) {
            return d1 % d2;
        }

        return super.modulus(v1, v2);
    }
}
