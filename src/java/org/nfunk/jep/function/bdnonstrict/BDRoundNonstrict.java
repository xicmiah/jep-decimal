package org.nfunk.jep.function.bdnonstrict;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.bigdecimal.BigDecimalRound;

/**
 * @author DPavlov
 */
public class BDRoundNonstrict extends BigDecimalRound {

    @Override
    protected Object roundNumber(Number l, Number r) throws ParseException {
        double d = l.doubleValue();
        if (Double.isNaN(d) || Double.isInfinite(d)) return d;

        return super.roundNumber(l, r);
    }

    @Override
    protected Object roundNumber(Number param) throws ParseException {
        double d = param.doubleValue();
        if (Double.isNaN(d) || Double.isInfinite(d)) return d;

        return super.roundNumber(param);
    }
}
