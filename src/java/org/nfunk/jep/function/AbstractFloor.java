package org.nfunk.jep.function;

import org.nfunk.jep.ParseException;

import java.util.Stack;

/**
 * @author DPavlov
 */
public abstract class AbstractFloor extends PostfixMathCommand{

    public AbstractFloor() {
        numberOfParameters = 1;
    }

    public void run(Stack inStack)
            throws ParseException {
        checkStack(inStack);// check the stack
        Object param = inStack.pop();
        inStack.push(floor(param));//push the result on the inStack
        return;
    }


    public Object floor(Object param)
            throws ParseException {
        if (param instanceof Number) {
            return floorNumber((Number) param);
        }

        throw new ParseException("Invalid parameter type");
    }

    protected abstract Number floorNumber(Number param) throws ParseException;

}
