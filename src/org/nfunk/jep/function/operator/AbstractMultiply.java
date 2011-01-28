package org.nfunk.jep.function.operator;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;
import org.nfunk.jep.type.Complex;

import java.util.Stack;
import java.util.Vector;

/**
 * @author DPavlov
 */
public abstract class AbstractMultiply extends PostfixMathCommand {

    protected AbstractMultiply() {
        numberOfParameters = -1;
    }

    public void run(Stack stack) throws ParseException {
        checkStack(stack); // check the stack

        Object product = stack.pop();
        Object param;
        int i = 1;

        // repeat summation for each one of the current parameters
        while (i < curNumberOfParameters) {
            // get the parameter from the stack
            param = stack.pop();

            // multiply it with the product, order is important
            // if matricies are used
            product = mul(param, product);

            i++;
        }

        stack.push(product);
    }

    public Object mul(Object param1, Object param2)
            throws ParseException {
        if (param1 instanceof Complex) {
            if (param2 instanceof Complex)
                return mul((Complex) param1, (Complex) param2);
            else if (param2 instanceof Number)
                return mul((Complex) param1, (Number) param2);
            else if (param2 instanceof Vector)
                return mul((Vector) param2, (Complex) param1);
        } else if (param1 instanceof Number) {
            if (param2 instanceof Complex)
                return mul((Complex) param2, (Number) param1);
            else if (param2 instanceof Number)
                return mulNumber((Number) param1, (Number) param2);
            else if (param2 instanceof Vector)
                return mul((Vector) param2, (Number) param1);
        } else if (param1 instanceof Vector) {
            if (param2 instanceof Complex)
                return mul((Vector) param1, (Complex) param2);
            else if (param2 instanceof Number)
                return mul((Vector) param1, (Number) param2);
        }

        throw new ParseException("Invalid parameter type");
    }

    protected abstract Number mulNumber(Number d1, Number d2) throws ParseException;


    protected Complex mul(Complex c1, Complex c2) {
        return c1.mul(c2);
    }

    protected Complex mul(Complex c, Number d) {
        return c.mul(d.doubleValue());
    }

    protected Vector mul(Vector v, Number d) throws ParseException {
        Vector result = new Vector();

        for (int i = 0; i < v.size(); i++)
            result.addElement(mulNumber((Number) v.elementAt(i), d));

        return result;
    }

    protected Vector mul(Vector v, Complex c) {
        Vector result = new Vector();

        for (int i = 0; i < v.size(); i++)
            result.addElement(mul(c, (Number) v.elementAt(i)));

        return result;
    }

}
