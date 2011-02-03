package org.nfunk.jep.function.operator.bdnonstrict;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalAdd;

/**
 * @author DPavlov
 */
public class BDAddNonstrict extends BigDecimalAdd {

    @Override
    protected Number addNumber(Number v1, Number v2) throws ParseException {
        double d1 = v1.doubleValue();
        double d2 = v2.doubleValue();

        if (Double.isNaN(d1)
                || Double.isNaN(d2)
                || Double.isInfinite(d1)
                || Double.isInfinite(d2)){
            return d1 + d2;
        }
        return super.addNumber(v1, v2);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
