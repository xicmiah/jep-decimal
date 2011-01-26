package org.nfunk.jep.type;

import org.nfunk.jep.ParseException;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * BigDecimal number factory;
 *
 * @author Zelgadis <pavlov.dmitry.n@gmail.com>
 */
public class BigDecimalNumberFactory implements NumberFactory{

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

    public Object createNumber(String value) {	return new BigDecimal(value);	}
	public Object createNumber(double value) {	return new BigDecimal(value);	}
	public Object createNumber(Number value) {	return value;	}
	public Object createNumber(boolean value) { return (value?BigDecimal.ONE:BigDecimal.ZERO); }
	public Object createNumber(float value) {return new BigDecimal(value);	}
	public Object createNumber(int value) {return new BigDecimal(value);	}
	public Object createNumber(short value) {return new BigDecimal(value);	}
	public Object createNumber(Complex value)  throws ParseException {
		throw new ParseException("Cannot create a number from a Complex value");
	}
	public Object getMinusOne() {return MINUS_ONE;}
	public Object getOne() {return BigDecimal.ONE;}
	public Object getTwo() {return TWO;}
	public Object getZero() {return BigDecimal.ZERO;}
}
