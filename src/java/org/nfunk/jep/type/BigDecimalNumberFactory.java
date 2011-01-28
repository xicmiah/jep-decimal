package org.nfunk.jep.type;

import org.nfunk.jep.ParseException;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * BigDecimal number factory;
 *
 * @author Zelgadis <pavlov.dmitry.n@gmail.com>
 */
public class BigDecimalNumberFactory implements NumberFactory<BigDecimal> {

    private static final BigDecimal MINUS_ONE = BigDecimal.ONE.negate();
    private static final BigDecimal TWO = BigDecimal.valueOf(2L);

    private MathContext mathContext = MathContext.DECIMAL128;

    public BigDecimalNumberFactory(MathContext mathContext) {
        this.mathContext = mathContext;
    }

    public MathContext getMathContext() {
        return mathContext;
    }

    public void setMathContext(MathContext mathContext) {
        this.mathContext = mathContext;
    }

    public BigDecimal createNumber(String value) {
        return new BigDecimal(value);
    }

    public BigDecimal createNumber(double value) {
        return new BigDecimal(value);
    }

    public BigDecimal createNumber(Number value) {
        if (value instanceof BigDecimal) return (BigDecimal) value;

        if (value instanceof Integer || value instanceof Long) {
            return new BigDecimal(value.longValue(), mathContext);
        }

        //todo think about NaN and infinity values
        return new BigDecimal(value.doubleValue(), mathContext);
    }

    public BigDecimal createNumber(boolean value) {
        return (value ? BigDecimal.ONE : BigDecimal.ZERO);
    }

    public BigDecimal createNumber(float value) {
        return new BigDecimal(value);
    }

    public BigDecimal createNumber(int value) {
        return new BigDecimal(value);
    }

    public BigDecimal createNumber(short value) {
        return new BigDecimal(value);
    }

    public BigDecimal createNumber(Complex value) throws ParseException {
        throw new ParseException("Cannot create a number from a Complex value");
    }

    public BigDecimal getMinusOne() {
        return MINUS_ONE;
    }

    public BigDecimal getOne() {
        return BigDecimal.ONE;
    }

    public BigDecimal getTwo() {
        return TWO;
    }

    public BigDecimal getZero() {
        return BigDecimal.ZERO;
    }
}
