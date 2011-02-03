package org.nfunk.jep.function.operator.bdnonstrict;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalUMinus;

import java.math.BigDecimal;

/**
 * @author DPavlov
 */
public class BDUMinusNonstrict extends BigDecimalUMinus{

    @Override
    protected Number negateNumber(Number param) throws ParseException {
        double d1 = param.doubleValue();
        if (Double.isNaN(d1)
                || Double.isInfinite(d1)) {
            return -d1;
        }
        return super.negateNumber(param);
    }
}
