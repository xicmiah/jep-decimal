package org.nfunk.jep.function.operator;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;
import org.nfunk.jep.type.Complex;

import java.util.Stack;
import java.util.Vector;

/**
 * @author DPavlov
 */
public abstract class AbstractDivide extends PostfixMathCommand {

    public AbstractDivide() {
        numberOfParameters = 2;
    }

    public void run(Stack inStack)
            throws ParseException {
        checkStack(inStack); // check the stack
        Object param2 = inStack.pop();
        Object param1 = inStack.pop();
        inStack.push(div(param1, param2)); //push the result on the inStack
        return;
    }

    public Object div(Object param1, Object param2)
            throws ParseException {
        if (param1 instanceof Complex) {
            if (param2 instanceof Complex)
                return div((Complex) param1, (Complex) param2);
            else if (param2 instanceof Number)
                return div((Complex) param1, (Number) param2);
            else if (param2 instanceof Vector)
                return div((Complex) param1, (Vector) param2);
        } else if (param1 instanceof Number) {
            if (param2 instanceof Complex)
                return div((Number) param1, (Complex) param2);
            else if (param2 instanceof Number)
                return divNumber((Number) param1, (Number) param2);
            else if (param2 instanceof Vector)
                return div((Number) param1, (Vector) param2);
        } else if (param1 instanceof Vector) {
            if (param2 instanceof Complex)
                return div((Vector) param1, (Complex) param2);
            else if (param2 instanceof Number)
                return div((Vector) param1, (Number) param2);
        }

        throw new ParseException("Invalid parameter type");
    }


    protected abstract Number divNumber(Number d1, Number d2);

    protected Complex div(Complex c1, Complex c2) {
        return c1.div(c2);
    }

    protected Complex div(Number d, Complex c) {
        Complex c1 = new Complex(d.doubleValue(), 0);

        return c1.div(c);
    }

    protected Complex div(Complex c, Number d) {
        return new Complex(c.re() / d.doubleValue(), c.im() / d.doubleValue());
    }

    protected Vector div(Vector v, Number d) {
        Vector result = new Vector();

        for (int i = 0; i < v.size(); i++)
            result.addElement(divNumber((Number) v.elementAt(i), d));

        return result;
    }

    protected Vector div(Number d, Vector v) {
        Vector result = new Vector();

        for (int i = 0; i < v.size(); i++)
            result.addElement(divNumber(d, (Number) v.elementAt(i)));

        return result;
    }

    protected Vector div(Vector v, Complex c) {
        Vector result = new Vector();

        for (int i = 0; i < v.size(); i++)
            result.addElement(div((Number) v.elementAt(i), c));

        return result;
    }

    protected Vector div(Complex c, Vector v) {
        Vector result = new Vector();

        for (int i = 0; i < v.size(); i++)
            result.addElement(div(c, (Number) v.elementAt(i)));

        return result;
    }

}
