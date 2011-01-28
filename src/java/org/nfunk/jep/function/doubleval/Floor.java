/*****************************************************************************

 JEP 2.4.1, Extensions 1.1.1
 April 30 2007
 (c) Copyright 2007, Nathan Funk and Richard Morris
 See LICENSE-*.txt for license information.

 *****************************************************************************/
package org.nfunk.jep.function.doubleval;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.AbstractFloor;

import java.util.Stack;

/**
 * A PostfixMathCommandI which find the smallest integer below the number
 * ceil(pi) give 3
 * ceil(-i) give -4
 *
 * @author Richard Morris
 * @see Math#floor(double)
 */

public class Floor extends AbstractFloor {

    @Override
    protected Number floorNumber(Number param)  {
        return new Double(Math.floor((param).doubleValue()));
    }
}
