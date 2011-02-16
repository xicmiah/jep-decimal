package org.nfunk.jep.type;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * BigDecimal factory which supports locale-dependant decimal separators
 */
public class LocalizedBigDecimalNumberFactory extends BigDecimalNumberFactory {

	private DecimalFormat format;

	/**
	 * Creates a LocalizedBigDecimalNumberFactory from supplied MathContext and Locale
	 * @param mathContext math context
	 * @param locale locale
	 */
	public LocalizedBigDecimalNumberFactory(MathContext mathContext, Locale locale) {
		super(mathContext);
		format = (DecimalFormat) NumberFormat.getNumberInstance(locale);
		format.setParseBigDecimal(true);
	}

	/**
	 * Creates a LocalizedBigDecimalNumberFactory from supplied MathContext and default locale
	 * @param mathContext math context
	 */
	public LocalizedBigDecimalNumberFactory(MathContext mathContext) {
		this(mathContext, Locale.getDefault());
	}

	/**
	 * Create BigDecimal instance from supplied string. Uses decimal separators from locale
	 *
	 * @param value string to parse
	 * @return BigDecimal instance from parsed string
	 */
	@Override
	public BigDecimal createNumber(String value) throws NumberFormatException {
		try {
			return (BigDecimal) format.parse(value);
		} catch (ParseException e) {
			// Mimic BigDecimal(String) constructor
			throw new NumberFormatException("Could not parse string " + value);
		}
	}
}
