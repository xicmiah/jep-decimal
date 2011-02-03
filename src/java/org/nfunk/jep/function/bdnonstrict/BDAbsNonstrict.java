package org.nfunk.jep.function.bdnonstrict;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.bigdecimal.BigDecimalAbs;

/**
 * @author DPavlov
 */
public class BDAbsNonstrict extends BigDecimalAbs{

    @Override
    protected Number getNumber(double doubleVal) throws ParseException {
        if (Double.isNaN(doubleVal) || Double.isInfinite(doubleVal)) return doubleVal;
        return super.getNumber(doubleVal);
    }

    @Override
    protected Number absNumber(Number number) throws ParseException {
        double d = number.doubleValue();
        if (Double.isNaN(d)) return d;
        if (Double.isInfinite(d)) return Math.abs(d);
        
        return super.absNumber(number);
    }
}
