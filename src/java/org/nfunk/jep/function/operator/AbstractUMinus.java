package org.nfunk.jep.function.operator;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;
import org.nfunk.jep.type.Complex;

import java.util.Stack;

/**
 * @author DPavlov
 */
public abstract class AbstractUMinus extends PostfixMathCommand {

    protected AbstractUMinus() {
        numberOfParameters = 1;
    }

    public void run(Stack inStack) throws ParseException {
        checkStack(inStack);// check the stack

        Object param = inStack.pop();

        inStack.push(umin(param));
    }

    public Object umin(Object param) throws ParseException {
        if (param instanceof Complex)
            return ((Complex) param).neg();
        if (param instanceof Number)
            return negateNumber((Number) param);

        throw new ParseException("Invalid parameter type");
    }

    protected abstract Number negateNumber(Number param) throws ParseException;

}
