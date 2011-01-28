package org.nfunk.jep.function;

import org.nfunk.jep.ParseException;

import java.util.Stack;

/**
 * @author DPavlov
 */
public abstract class AbstractCeil extends PostfixMathCommand {

    public AbstractCeil() {
        numberOfParameters = 1;
    }

    public void run(Stack inStack)
            throws ParseException {
        checkStack(inStack);// check the stack
        Object param = inStack.pop();
        inStack.push(ceil(param));//push the result on the inStack
        return;
    }


    public Object ceil(Object param)
            throws ParseException {
        if (param instanceof Number) {
            return ceilNumber((Number) param);
        }

        throw new ParseException("Invalid parameter type");
    }

    protected abstract Number ceilNumber(Number param) throws ParseException;

}
