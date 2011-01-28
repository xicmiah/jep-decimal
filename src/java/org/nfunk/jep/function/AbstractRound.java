package org.nfunk.jep.function;

import org.nfunk.jep.ParseException;

import java.util.Stack;

/**
 * @author DPavlov
 */
public abstract class AbstractRound extends PostfixMathCommand{

    public AbstractRound() {
        numberOfParameters = -1;
    }

    public void run(Stack inStack)
            throws ParseException {
        checkStack(inStack);// check the stack
        if (this.curNumberOfParameters == 1) {
            Object param = inStack.pop();
            inStack.push(round(param));//push the result on the inStack
        } else {
            Object r = inStack.pop();
            Object l = inStack.pop();
            inStack.push(round(l, r));//push the result on the inStack

        }
        return;
    }


    private Object round(Object l, Object r) throws ParseException {
        if (l instanceof Number && r instanceof Number) {
            return roundNumber((Number) l, (Number) r);
        }
        throw new ParseException("Invalid parameter type");
    }

    protected abstract Object roundNumber(Number l, Number r) throws ParseException;

    protected abstract Object roundNumber(Number param) throws ParseException;

    public Object round(Object param)
            throws ParseException {
        if (param instanceof Number) {
            return roundNumber((Number) param);
        }

        throw new ParseException("Invalid parameter type");
    }

}
