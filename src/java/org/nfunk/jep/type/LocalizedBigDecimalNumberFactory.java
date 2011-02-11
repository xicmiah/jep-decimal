package org.nfunk.jep.type;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class LocalizedBigDecimalNumberFactory extends BigDecimalNumberFactory {

	NumberFormat format;

	public LocalizedBigDecimalNumberFactory(MathContext mathContext, Locale locale) {
		super(mathContext);
		format = NumberFormat.getNumberInstance(locale);
		if (format instanceof DecimalFormat) {
			((DecimalFormat) format).setParseBigDecimal(true);
		}
	}

	public LocalizedBigDecimalNumberFactory(MathContext mathContext, NumberFormat format) {
		super(mathContext);
		this.format = format;
	}

	@Override
	public BigDecimal createNumber(String value) {
		try {
			return (BigDecimal) format.parse(value);
		} catch (ParseException e) {
			throw new NumberFormatException("Could not parse string " + value);
		}
	}
}
