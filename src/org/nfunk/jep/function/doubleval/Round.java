/*****************************************************************************

 JEP 2.4.1, Extensions 1.1.1
 April 30 2007
 (c) Copyright 2007, Nathan Funk and Richard Morris
 See LICENSE-*.txt for license information.

 *****************************************************************************/
package org.nfunk.jep.function.doubleval;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.AbstractRound;

import java.util.Stack;

/**
 * A PostfixMathCommandI which rounds a number
 * round(pi) finds the closest integer to the argument
 * round(pi,3) rounds the argument to 3 decimal places
 *
 * @author Richard Morris
 */
public class Round extends AbstractRound {

    @Override
    protected Object roundNumber(Number l, Number r)  {
        int dp = r.intValue();
        double val = l.doubleValue();
        double mul = Math.pow(10, dp);
        return new Double(Math.rint(val * mul) / mul);
    }

    @Override
    protected Object roundNumber(Number param) {
        return new Double(Math.rint(param.doubleValue()));
    }
}
