/*****************************************************************************

 JEP 2.4.1, Extensions 1.1.1
 April 30 2007
 (c) Copyright 2007, Nathan Funk and Richard Morris
 See LICENSE-*.txt for license information.

 *****************************************************************************/

package org.nfunk.jep.function.operator.doubleval;

import org.nfunk.jep.function.operator.AbstractModulus;

public class Modulus extends AbstractModulus {
    @Override
    protected Number modulus(Number param1, Number param2) {
        double divisor = param2.doubleValue();
        double dividend = param1.doubleValue();

        return new Double(dividend % divisor);
    }
}
