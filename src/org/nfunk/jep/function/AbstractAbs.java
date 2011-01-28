package org.nfunk.jep.function;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.type.Complex;

import java.util.Stack;

/**
 * @author DPavlov
 */
public abstract class AbstractAbs extends PostfixMathCommand{

    public AbstractAbs() {
        numberOfParameters = 1;
    }

    public void run(Stack inStack)
            throws ParseException {
        checkStack(inStack);// check the stack
        Object param = inStack.pop();
        inStack.push(abs(param));//push the result on the inStack
        return;
    }


    public Object abs(Object param)
            throws ParseException {
        if (param instanceof Complex) {
            return new Double(((Complex) param).abs());
        } else if (param instanceof Number) {
            return new Double(Math.abs(((Number) param).doubleValue()));
        }

        throw new ParseException("Invalid parameter type");
    }

    protected abstract Number getNumber(double doubleVal);

    protected abstract Number absNumber(Number number);

}
