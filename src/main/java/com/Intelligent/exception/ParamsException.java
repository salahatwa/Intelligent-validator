package com.Intelligent.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Salah atwa
 */
public class ParamsException extends RuntimeException {

	private static final long serialVersionUID = 276486514583932180L;

	private static Logger Log = LoggerFactory.getLogger(ParamsException.class);

	public ParamsException(String msg) {
		super(msg);
		Log.error(msg);
	}

}
