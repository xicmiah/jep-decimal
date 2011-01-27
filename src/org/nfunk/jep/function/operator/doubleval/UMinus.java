/*****************************************************************************

 JEP 2.4.1, Extensions 1.1.1
      April 30 2007
      (c) Copyright 2007, Nathan Funk and Richard Morris
      See LICENSE-*.txt for license information.

*****************************************************************************/
package org.nfunk.jep.function.operator.doubleval;

import org.nfunk.jep.function.operator.AbstractUMinus;

public class UMinus extends AbstractUMinus {

    @Override
    protected Double negateNumber(Number param) {
        return new Double(-param.doubleValue());
    }
    
}
