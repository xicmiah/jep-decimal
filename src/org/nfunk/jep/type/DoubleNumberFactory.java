/*****************************************************************************

 JEP 2.4.1, Extensions 1.1.1
 April 30 2007
 (c) Copyright 2007, Nathan Funk and Richard Morris
 See LICENSE-*.txt for license information.

 *****************************************************************************/

package org.nfunk.jep.type;

import org.nfunk.jep.ParseException;

/**
 * Default class for creating number objects. This class can be replaced by
 * other NumberFactory implementations if other number types are required. This
 * can be done using the
 */
public class DoubleNumberFactory implements NumberFactory<Double> {
    public static Double ZERO = new Double(0.0);
    public static Double ONE = new Double(1.0);
    public static Double TWO = new Double(2.0);
    public static Double MINUSONE = new Double(-1.0);

    /**
     * Creates a Double object initialized to the value of the parameter.
     *
     * @param value The initialization value for the returned object.
     */
    public Double createNumber(String value) {
        return new Double(value);
    }

    public Double createNumber(double value) {
        return new Double(value);
    }

    public Double createNumber(Number value) {
        if (value instanceof Double) return (Double) value;
        return value.doubleValue();
    }

    public Double createNumber(boolean value) {
        return (value ? ONE : ZERO);
    }

    public Double createNumber(float value) {
        return new Double(value);
    }

    public Double createNumber(int value) {
        return new Double(value);
    }

    public Double createNumber(short value) {
        return new Double(value);
    }

    public Double createNumber(Complex value) throws ParseException {
        throw new ParseException("Cannot create a number from a Complex value");
    }

    public Double getMinusOne() {
        return MINUSONE;
    }

    public Double getOne() {
        return ONE;
    }

    public Double getTwo() {
        return TWO;
    }

    public Double getZero() {
        return ZERO;
    }
}
