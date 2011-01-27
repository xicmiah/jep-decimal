package org.nfunk.jep.function.operator;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.util.Stack;

/**
 * @author DPavlov
 */
public abstract class AbstractModulus extends PostfixMathCommand {

    public AbstractModulus() {
        numberOfParameters = 2;
    }

    public void run(Stack inStack)
            throws ParseException {
        checkStack(inStack);// check the stack
        Object param2 = inStack.pop();
        Object param1 = inStack.pop();

        if ((param1 instanceof Number) && (param2 instanceof Number)) {
            inStack.push(modulus((Number) param1, (Number) param2));
        } else {
            throw new ParseException("Invalid parameter type");
        }
        return;
    }

    protected abstract Number modulus(Number d1, Number d2);

}
