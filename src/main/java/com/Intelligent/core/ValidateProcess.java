package com.Intelligent.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

import com.Intelligent.exception.ParamsException;
import com.Intelligent.utils.CommonUtil;
import com.Intelligent.utils.RegexUtil;

/**
 * Salah atwa
 */
class ValidateProcess {

	private final static String REGEX_DATE8 = "^[0-9]{8}$";
	private final static String REGEX_ENGLISH = "^[a-zA-z]{1,}$";
	private final static String REGEX_PHONE = "^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$";
	private final static String REGEX_EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

	private final static String NotNullErrorMsg = "Non-empty verification failed";
	private final static String RegexErrorMsg = "Regular verification failed";
	private final static String MaxErrorMsg = "Maximum verification failed";
	private final static String MinErrorMsg = "Minimum verification failed";
	private final static String MaxLengthErrorMsg = "Maximum length verification failed";
	private final static String MinLengthErrorMsg = "Minimum length verification failed";
	private final static String DateFormatErrorMsg = "Date format verification failed";
	private final static String EnglishErrorMsg = "English verification failed";
	private final static String PhoneNumErrorMsg = "Phone number verification failed";
	private final static String EmailErrorMsg = "E-mail verification failed";

	static void notNull(Object value) {
		if (null == value)
			throw new ParamsException(NotNullErrorMsg);
	}

	static void notNull(String value) {
		if (CommonUtil.isNull(value))
			throw new ParamsException(NotNullErrorMsg);
	}

	static void notNull(Number number) {
		if (null == number)
			throw new ParamsException(NotNullErrorMsg);
	}

	static void notNull(Collection value) {
		if (CommonUtil.isNull(value))
			throw new ParamsException(NotNullErrorMsg);
	}

	static void notNull(Map value) {
		if (CommonUtil.isNull(value))
			throw new ParamsException(NotNullErrorMsg);
	}

	static void notNull(Object[] value) {
		if (CommonUtil.isNull(value))
			throw new ParamsException(NotNullErrorMsg);
	}

	static void regex(String regex, String value) {
		regex(regex, value, RegexErrorMsg + ", regex:" + regex + ", value:" + value);
	}

	private static void regex(String regex, String value, String msg) {
		if (!CommonUtil.isNull(value)) {
			if (!RegexUtil.isRegex(regex, value)) {
				throw new ParamsException(msg);
			}
		}
	}

	static void max(int max, int value) {
		if (value > max)
			throw new ParamsException(MaxErrorMsg + ", max:" + max + ", value:" + value);
	}

	static void max(long max, long value) {
		if (value > max)
			throw new ParamsException(MaxErrorMsg + ", max:" + max + ", value:" + value);
	}

	static void max(float max, float value) {
		if (value > max)
			throw new ParamsException(MaxErrorMsg + ", max:" + max + ", value:" + value);
	}

	static void max(double max, double value) {
		if (value > max)
			throw new ParamsException(MaxErrorMsg + ", max:" + max + ", value:" + value);
	}

	static void max(byte max, byte value) {
		if (value > max)
			throw new ParamsException(MaxErrorMsg + ", max:" + max + ", value:" + value);
	}

	static void max(short max, short value) {
		if (value > max)
			throw new ParamsException(MaxErrorMsg + ", max:" + max + ", value:" + value);
	}

	static void maxLength(int max, String value) {
		if (!CommonUtil.isNull(value)) {
			if (value.length() > max)
				throw new ParamsException(MaxLengthErrorMsg + ", max:" + max + ", value:" + value);
		}
	}

	static void maxLength(int max, Collection value) {
		if (null != value) {
			if (value.size() > max)
				throw new ParamsException(MaxLengthErrorMsg + ", max:" + max + ", value:" + value.size());
		}
	}

	static void maxLength(int max, Map value) {
		if (null != value) {
			if (value.size() > max)
				throw new ParamsException(MaxLengthErrorMsg + ", max:" + max + ", value:" + value.size());
		}
	}

	static void maxLength(int max, Object[] value) {
		if (null != value) {
			if (value.length > max)
				throw new ParamsException(MaxLengthErrorMsg + ", max:" + max + ", value:" + value.length);
		}
	}

	static void min(int min, int value) {
		if (value < min)
			throw new ParamsException(MinErrorMsg + ", min:" + min + ", value:" + value);
	}

	static void min(long min, long value) {
		if (value < min)
			throw new ParamsException(MinErrorMsg + ", min:" + min + ", value:" + value);
	}

	static void min(float min, float value) {
		if (value < min)
			throw new ParamsException(MinErrorMsg + ", min:" + min + ", value:" + value);
	}

	static void min(double min, double value) {
		if (value < min)
			throw new ParamsException(MinErrorMsg + ", min:" + min + ", value:" + value);
	}

	static void min(byte min, byte value) {
		if (value < min)
			throw new ParamsException(MinErrorMsg + ", min:" + min + ", value:" + value);
	}

	static void min(short min, short value) {
		if (value < min)
			throw new ParamsException(MinErrorMsg + ", min:" + min + ", value:" + value);
	}

	static void minLength(int min, String value) {
		if (!CommonUtil.isNull(value)) {
			if (value.length() < min)
				throw new ParamsException(MinErrorMsg + ", min:" + min + ", value:" + value);
		}
	}

	static void minLength(int min, Collection value) {
		if (null != value) {
			if (value.size() < min)
				throw new ParamsException(MinLengthErrorMsg + ", min:" + min + ", value:" + value.size());
		}
	}

	static void minLength(int min, Map value) {
		if (null != value) {
			if (value.size() < min)
				throw new ParamsException(MinLengthErrorMsg + ", min:" + min + ", value:" + value.size());
		}
	}

	static void minLength(int min, Object[] value) {
		if (null != value) {
			if (value.length < min)
				throw new ParamsException(MinLengthErrorMsg + ", min:" + min + ", value:" + value.length);
		}
	}

	static void date(String format, String value) {
		if (!CommonUtil.isNull(value)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			try {
				simpleDateFormat.parse(value);
			} catch (ParseException e) {
				throw new ParamsException(DateFormatErrorMsg + ", format:" + format + ", value:" + value);
			}
		}
	}

	static void english(String value) {
		regex(REGEX_ENGLISH, value, EnglishErrorMsg + ", value:" + value);
	}

	static void phone(String value) {
		regex(REGEX_PHONE, value, PhoneNumErrorMsg + ", value:" + value);
	}

	static void email(String value) {
		regex(REGEX_EMAIL, value, EmailErrorMsg + ", value:" + value);
	}

}
