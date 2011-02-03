package org.nfunk.jep.function.bdnonstrict;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.bigdecimal.BigDecimalCeil;

/**
 * @author DPavlov
 */
public class BDCeilNonstrict extends BigDecimalCeil {

    @Override
    protected Number ceilNumber(Number param) throws ParseException {
        double d = param.doubleValue();
        if (Double.isNaN(d) || Double.isInfinite(d)) return d;
        return super.ceilNumber(param);
    }
}
