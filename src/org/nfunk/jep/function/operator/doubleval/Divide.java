/*****************************************************************************

 JEP 2.4.1, Extensions 1.1.1
      April 30 2007
      (c) Copyright 2007, Nathan Funk and Richard Morris
      See LICENSE-*.txt for license information.

*****************************************************************************/
package org.nfunk.jep.function.operator.doubleval;

import org.nfunk.jep.function.operator.AbstractDivide;

public class Divide extends AbstractDivide {

    @Override
    public Number divNumber(Number d1, Number d2) {
        return new Double(d1.doubleValue() / d2.doubleValue());
    }
}
