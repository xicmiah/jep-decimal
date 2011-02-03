package org.nfunk.jep.function.operator.bdnonstrict;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalPower;

import java.math.BigDecimal;

/**
 * @author DPavlov
 */
public class BDPowerNonstrict extends BigDecimalPower {

    @Override
    protected Object powerNumber(Number v1, Number v2) throws ParseException {
        double d1 = v1.doubleValue();
        double d2 = v2.doubleValue();
        if (Double.isNaN(d1)
                || Double.isNaN(d2)
                || Double.isInfinite(d1)
                || Double.isInfinite(d2)) {
            return Math.pow(d1, d2);
        }

        return super.powerNumber(v1, v2);
    }
}
