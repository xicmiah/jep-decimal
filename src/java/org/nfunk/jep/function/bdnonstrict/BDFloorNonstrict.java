package org.nfunk.jep.function.bdnonstrict;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.bigdecimal.BigDecimalFloor;

/**
 * @author DPavlov
 */
public class BDFloorNonstrict extends BigDecimalFloor {

    @Override
    protected Number floorNumber(Number param) throws ParseException {
        double d = param.doubleValue();
        if (Double.isNaN(d) || Double.isInfinite(d)) return d;
        return super.floorNumber(param);
    }
}
