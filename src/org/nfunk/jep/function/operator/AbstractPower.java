package org.nfunk.jep.function.operator;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;
import org.nfunk.jep.type.Complex;

import java.util.Stack;

/**
 * @author DPavlov
 */
public abstract class AbstractPower extends PostfixMathCommand {

    public AbstractPower() {
        numberOfParameters = 2;
    }

    public void run(Stack inStack)
            throws ParseException {
        checkStack(inStack); // check the stack

        Object param2 = inStack.pop();
        Object param1 = inStack.pop();

        inStack.push(power(param1, param2));
    }

    public Object power(Object param1, Object param2)
            throws ParseException {
        if (param1 instanceof Complex) {
            if (param2 instanceof Complex)
                return power((Complex) param1, (Complex) param2);
            else if (param2 instanceof Number)
                return power((Complex) param1, (Number) param2);
        } else if (param1 instanceof Number) {
            if (param2 instanceof Complex)
                return power((Number) param1, (Complex) param2);
            else if (param2 instanceof Number)
                return powerNumber((Number) param1, (Number) param2);
        }

        throw new ParseException("Invalid parameter type");
    }


    protected abstract Object powerNumber(Number d1, Number d2) throws ParseException;

    protected Object power(Complex c1, Complex c2) {
        Complex temp = c1.power(c2);

        if (temp.im() == 0)
            return new Double(temp.re());
        else
            return temp;
    }

    protected Object power(Complex c, Number d) {
        Complex temp = c.power(d.doubleValue());

        if (temp.im() == 0)
            return new Double(temp.re());
        else
            return temp;
    }

    protected Object power(Number d, Complex c) {
        Complex base = new Complex(d.doubleValue(), 0.0);
        Complex temp = base.power(c);

        if (temp.im() == 0)
            return new Double(temp.re());
        else
            return temp;
    }

}
