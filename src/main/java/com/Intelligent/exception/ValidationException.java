package com.Intelligent.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author salah atwa
 *
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 276486514583932180L;

	private static Logger Log = LoggerFactory.getLogger(ValidationException.class);

	public ValidationException(String msg) {
		super(msg);
		Log.error(msg);
	}

}
