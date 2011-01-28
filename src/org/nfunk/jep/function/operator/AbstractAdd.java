package org.nfunk.jep.function.operator;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;
import org.nfunk.jep.type.Complex;

import java.util.Stack;

/**
 * Addition function. Supports any number of parameters although typically
 * only 2 parameters are used.
 *
 * @author DPavlov
 * @author nathan
 */
public abstract class AbstractAdd extends PostfixMathCommand {

    protected AbstractAdd() {
        numberOfParameters = -1;
    }

    /**
     * Calculates the result of applying the "+" operator to the arguments from
     * the stack and pushes it back on the stack.
     */
    public void run(Stack stack) throws ParseException {
        checkStack(stack);// check the stack

        Object sum = stack.pop();
        Object param;
        int i = 1;

        // repeat summation for each one of the current parameters
        while (i < curNumberOfParameters) {
            // get the parameter from the stack
            param = stack.pop();

            // add it to the sum (order is important for String arguments)
            sum = add(param, sum);

            i++;
        }

        stack.push(sum);
    }

    /**
     * Adds two numbers together. The parameters can be of type Number,
     * Complex, or String. If a certain combination of types is not supported,
     * a ParseException is thrown.
     *
     * @param param1 The first parameter to be added.
     * @param param2 The second parameter to be added.
     * @return The sum of param1 and param2, or concatenation of the two if
     *         they are Strings.
     * @throws ParseException
     */
    public Object add(Object param1, Object param2) throws ParseException {
        if (param1 instanceof Complex) {
            if (param2 instanceof Complex)
                return addComplex((Complex) param1, (Complex) param2);
            else if (param2 instanceof Number)
                return addComplex((Complex) param1, (Number) param2);
        } else if (param1 instanceof Number) {
            if (param2 instanceof Complex)
                return addComplex((Complex) param2, (Number) param1);
            else if (param2 instanceof Number)
                return addNumber((Number) param1, (Number) param2);
        } else if ((param1 instanceof String) && (param2 instanceof String)) {
            return (String) param1 + (String) param2;
        }

        throw new ParseException("Invalid parameter type");
    }

    protected Complex addComplex(Complex c1, Complex c2) {
        return new Complex(c1.re() + c2.re(), c1.im() + c2.im());
    }

    protected Complex addComplex(Complex c, Number d) {
        return new Complex(c.re() + d.doubleValue(), c.im());
    }

    protected abstract Number addNumber(Number v1, Number v2) throws ParseException;

}
