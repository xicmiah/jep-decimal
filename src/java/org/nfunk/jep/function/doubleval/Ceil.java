/*****************************************************************************

 JEP 2.4.1, Extensions 1.1.1
 April 30 2007
 (c) Copyright 2007, Nathan Funk and Richard Morris
 See LICENSE-*.txt for license information.

 *****************************************************************************/
package org.nfunk.jep.function.doubleval;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.AbstractCeil;

import java.util.Stack;

/**
 * A PostfixMathCommandI which find the smallest integer above the number
 * ceil(pi) give 4
 * ceil(-i) give -3
 *
 * @author Richard Morris
 * @see Math#ceil(double)
 */

public class Ceil extends AbstractCeil {

    @Override
    protected Number ceilNumber(Number param) {
        return new Double(Math.ceil(((Number) param).doubleValue()));
    }
}
