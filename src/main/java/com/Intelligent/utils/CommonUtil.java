package com.Intelligent.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.Intelligent.exception.ValidationException;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * @author salah atwa
 *
 */
public class CommonUtil {

	private final static String NotNullErrorMsg = "Non-empty verification failed";

	public static boolean isNull(String str) {
		return StringUtils.isBlank(str);
	}

	public static boolean isNull(Collection<?> collection) {
		return CollectionUtils.isEmpty(collection);
	}

	public static boolean isNull(Map<?, ?> paramMap) {
		return null == paramMap || paramMap.isEmpty();
	}

	public static boolean isNull(Object[] array) {
		return null == array || array.length == 0;
	}

	public static void notNull(Object value) {
		if (value == null)
			throw new ValidationException(NotNullErrorMsg);
	}

	public static void notNull(String value) {
		if (CommonUtil.isNull(value))
			throw new ValidationException(NotNullErrorMsg);
	}

	public static void notNull(Number number) {
		if (null == number)
			throw new ValidationException(NotNullErrorMsg);
	}

	public static void notNull(Collection value) {
		if (CommonUtil.isNull(value))
			throw new ValidationException(NotNullErrorMsg);
	}

	public static void notNull(Map value) {
		if (CommonUtil.isNull(value))
			throw new ValidationException(NotNullErrorMsg);
	}

	public static void notNull(Object[] value) {
		if (CommonUtil.isNull(value))
			throw new ValidationException(NotNullErrorMsg);
	}
}
