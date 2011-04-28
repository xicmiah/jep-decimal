package org.nfunk.jep.validation;

import org.nfunk.jep.ParseException;


public class ValidationException extends ParseException {
	public ValidationException(String message) {
		super(message);
	}
}
