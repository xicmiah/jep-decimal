/*****************************************************************************

 JEP 2.4.1, Extensions 1.1.1
 April 30 2007
 (c) Copyright 2007, Nathan Funk and Richard Morris
 See LICENSE-*.txt for license information.

 *****************************************************************************/
package org.nfunk.jep.function.doubleval;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.AbstractAbs;
import org.nfunk.jep.type.Complex;

import java.util.Stack;

public class Abs extends AbstractAbs {

    @Override
    protected Number getNumber(double doubleVal) throws ParseException {
        return new Double(doubleVal);
    }

    @Override
    protected Number absNumber(Number number) throws ParseException {
        return new Double(Math.abs(number.doubleValue()));
    }
}
