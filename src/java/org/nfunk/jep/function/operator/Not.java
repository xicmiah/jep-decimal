/*****************************************************************************

 JEP 2.4.1, Extensions 1.1.1
 April 30 2007
 (c) Copyright 2007, Nathan Funk and Richard Morris
 See LICENSE-*.txt for license information.

 *****************************************************************************/
package org.nfunk.jep.function.operator;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.math.BigDecimal;
import java.util.Stack;

public class Not extends PostfixMathCommand {
    public Not() {
        numberOfParameters = 1;

    }

    public void run(Stack inStack)
            throws ParseException {
        checkStack(inStack);// check the stack
        Object param = inStack.pop();
        int r;
        if (param instanceof BigDecimal) {
            r = ((BigDecimal) param).signum() == 0 ? 1 : 0;
        } else if (param instanceof Number) {
            r = ((Number) param).doubleValue() == 0 ? 1 : 0;
        } else if (param instanceof Boolean) {
            r = ((Boolean) param).booleanValue() ? 0 : 1;
        } else {
            throw new ParseException("Invalid parameter type");
        }

        inStack.push(new Double(r));//push the result on the inStack
    }

}
