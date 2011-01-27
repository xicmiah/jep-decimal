/*****************************************************************************

 JEP 2.4.1, Extensions 1.1.1
      April 30 2007
      (c) Copyright 2007, Nathan Funk and Richard Morris
      See LICENSE-*.txt for license information.

*****************************************************************************/
package org.nfunk.jep.function.operator.doubleval;

import java.lang.Math;

import org.nfunk.jep.function.operator.AbstractPower;
import org.nfunk.jep.type.*;

public class Power extends AbstractPower {

    @Override
    public Object powerNumber(Number d1, Number d2) {
		if (d1.doubleValue()<0 && d2.doubleValue() != d2.intValue())
		{
			Complex c = new Complex(d1.doubleValue(), 0.0);
			return c.power(d2.doubleValue());
		}
		else
			return new Double(Math.pow(d1.doubleValue(),d2.doubleValue()));
	}
}
