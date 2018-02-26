package com.Intelligent.core;

import com.Intelligent.exception.ValidationException;
import com.Intelligent.utils.CommonUtil;
import com.Intelligent.utils.RegexUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

/**
 * 
 * @author salah atwa
 *
 */
class ValidateProcess {

	private final static int[] factorArr = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
	private final static char[] parityBit = { '1', '0', 'x', '9', '8', '7', '6', '5', '4', '3', '2' };

	private final static String REGEX_DATE8 = "^[0-9]{8}$";
	private final static String REGEX_IP = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)";
	private final static String REGEX_ENGLISH = "^[a-zA-z]{1,}$";
	private final static String REGEX_PHONE = "^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$";
	private final static String REGEX_EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

	private final static int MIN_YEAR = 1700;
	private final static int MAX_YEAR = 2500;

	private final static String NotNullErrorMsg = "Non-empty verification failed";
	private final static String RegexErrorMsg = "Regular verification failed";
	private final static String MaxErrorMsg = "Maximum verification failed";
	private final static String MinErrorMsg = "Minimum verification failed";
	private final static String MaxLengthErrorMsg = "Maximum length verification failed";
	private final static String MinLengthErrorMsg = "Minimum length verification failed";
	private final static String DateFormatErrorMsg = "Date format verification failed";
	private final static String IdCardErrorMsg = "ID card verification failed";
	private final static String IpErrorMsg = "IP verification failed";
	private final static String ChineseErrorMsg = "Chinese calibration failed";
	private final static String EnglishErrorMsg = "English verification failed";
	private final static String EmailErrorMsg = "E-mail verification failed";
	private final static String PhoneNumErrorMsg = "Phone number verification failed";

	static void notNull(Object value) {
		if (null == value)
			throw new ValidationException(NotNullErrorMsg);
	}

	static void notNull(String value) {
		if (CommonUtil.isNull(value))
			throw new ValidationException(NotNullErrorMsg);
	}

	static void notNull(Number number) {
		if (null == number)
			throw new ValidationException(NotNullErrorMsg);
	}

	static void notNull(Collection value) {
		if (CommonUtil.isNull(value))
			throw new ValidationException(NotNullErrorMsg);
	}

	static void notNull(Map value) {
		if (CommonUtil.isNull(value))
			throw new ValidationException(NotNullErrorMsg);
	}

	static void notNull(Object[] value) {
		if (CommonUtil.isNull(value))
			throw new ValidationException(NotNullErrorMsg);
	}

	static void regex(String regex, String value) {
		regex(regex, value, RegexErrorMsg + ", regex:" + regex + ", value:" + value);
	}

	private static void regex(String regex, String value, String msg) {
		if (!CommonUtil.isNull(value)) {
			if (!RegexUtil.isRegex(regex, value)) {
				throw new ValidationException(msg);
			}
		}
	}

	static void max(int max, int value) {
		if (value > max)
			throw new ValidationException(MaxErrorMsg + ", max:" + max + ", value:" + value);
	}

	static void max(long max, long value) {
		if (value > max)
			throw new ValidationException(MaxErrorMsg + ", max:" + max + ", value:" + value);
	}

	static void max(float max, float value) {
		if (value > max)
			throw new ValidationException(MaxErrorMsg + ", max:" + max + ", value:" + value);
	}

	static void max(double max, double value) {
		if (value > max)
			throw new ValidationException(MaxErrorMsg + ", max:" + max + ", value:" + value);
	}

	static void max(byte max, byte value) {
		if (value > max)
			throw new ValidationException(MaxErrorMsg + ", max:" + max + ", value:" + value);
	}

	static void max(short max, short value) {
		if (value > max)
			throw new ValidationException(MaxErrorMsg + ", max:" + max + ", value:" + value);
	}

	static void maxLength(int max, String value) {
		if (!CommonUtil.isNull(value)) {
			if (value.length() > max)
				throw new ValidationException(MaxLengthErrorMsg + ", max:" + max + ", value:" + value);
		}
	}

	static void maxLength(int max, Collection value) {
		if (null != value) {
			if (value.size() > max)
				throw new ValidationException(MaxLengthErrorMsg + ", max:" + max + ", value:" + value.size());
		}
	}

	static void maxLength(int max, Map value) {
		if (null != value) {
			if (value.size() > max)
				throw new ValidationException(MaxLengthErrorMsg + ", max:" + max + ", value:" + value.size());
		}
	}

	static void maxLength(int max, Object[] value) {
		if (null != value) {
			if (value.length > max)
				throw new ValidationException(MaxLengthErrorMsg + ", max:" + max + ", value:" + value.length);
		}
	}

	static void min(int min, int value) {
		if (value < min)
			throw new ValidationException(MinErrorMsg + ", min:" + min + ", value:" + value);
	}

	static void min(long min, long value) {
		if (value < min)
			throw new ValidationException(MinErrorMsg + ", min:" + min + ", value:" + value);
	}

	static void min(float min, float value) {
		if (value < min)
			throw new ValidationException(MinErrorMsg + ", min:" + min + ", value:" + value);
	}

	static void min(double min, double value) {
		if (value < min)
			throw new ValidationException(MinErrorMsg + ", min:" + min + ", value:" + value);
	}

	static void min(byte min, byte value) {
		if (value < min)
			throw new ValidationException(MinErrorMsg + ", min:" + min + ", value:" + value);
	}

	static void min(short min, short value) {
		if (value < min)
			throw new ValidationException(MinErrorMsg + ", min:" + min + ", value:" + value);
	}

	static void minLength(int min, String value) {
		if (!CommonUtil.isNull(value)) {
			if (value.length() < min)
				throw new ValidationException(MinErrorMsg + ", min:" + min + ", value:" + value);
		}
	}

	static void minLength(int min, Collection value) {
		if (null != value) {
			if (value.size() < min)
				throw new ValidationException(MinLengthErrorMsg + ", min:" + min + ", value:" + value.size());
		}
	}

	static void minLength(int min, Map value) {
		if (null != value) {
			if (value.size() < min)
				throw new ValidationException(MinLengthErrorMsg + ", min:" + min + ", value:" + value.size());
		}
	}

	static void minLength(int min, Object[] value) {
		if (null != value) {
			if (value.length < min)
				throw new ValidationException(MinLengthErrorMsg + ", min:" + min + ", value:" + value.length);
		}
	}

	static void date(String format, String value) {
		if (!CommonUtil.isNull(value)) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			try {
				simpleDateFormat.parse(value);
			} catch (ParseException e) {
				throw new ValidationException(DateFormatErrorMsg + ", format:" + format + ", value:" + value);
			}
		}
	}

	/**
	 * ID 15-bit encoding rules: dddddd yymmdd xx p dddddd: area code yymmdd: date
	 * of birth xx: sequence encoding p: Sex, odd number is male, even number is
	 * female ID 18-bit encoding rules: dddddd yyyymmdd xxx y dddddd: area code
	 * yyyymmdd: date of birth xxx: Sequential encoding, odd number is male, even
	 * number is female y: check code, the value of this bit can be calculated by
	 * the first 17 digits The 18 digit number weighting factor is (right to left)
	 * wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 1 ] Validation bit Y
	 * = [1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2] Check digit calculation formula: Y_P =
	 * mod (Σ (Ai × wi), 11 ) i is the ID number from right to left 2 ... 18 bits;
	 * Y_P is the check code where the array of check code
	 */
	static void idCard(String value) {
		if (!CommonUtil.isNull(value)) {
			String idCard = value.toLowerCase();
			int length = idCard.length();
			if (length != 15 && length != 18) {
				throw new ValidationException(IdCardErrorMsg + ", value:" + value);
			}
			if (15 == length && !isDate6(idCard.substring(6, 12))) {
				throw new ValidationException(IdCardErrorMsg + ", value:" + value);
			}
			if (18 == length && !isDate8(idCard.substring(6, 14))) {
				throw new ValidationException(IdCardErrorMsg + ", value:" + value);
			}
			if (18 == length) {
				char[] idCardArray = idCard.toCharArray();
				int sum = 0;
				for (int i = 0; i < idCardArray.length - 1; i++) {
					if (idCardArray[i] < '0' || idCardArray[i] > '9') {
						throw new ValidationException(IdCardErrorMsg + ", value:" + value);
					}
					sum += (idCardArray[i] - '0') * factorArr[i];
				}
				if (idCardArray[idCardArray.length - 1] != parityBit[sum % 11]) {
					throw new ValidationException(IdCardErrorMsg + ", value:" + value);
				}
			}
		}
	}

	private static boolean isDate6(String date) {
		return isDate8("19" + date);
	}

	private static boolean isDate8(String date) {
		if (!RegexUtil.isRegex(REGEX_DATE8, date)) {
			return false;
		}
		int[] iaMonthDays = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6));
		int day = Integer.parseInt(date.substring(6, 8));
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
			iaMonthDays[1] = 29;
		return !(year < MIN_YEAR || year > MAX_YEAR) && !(month < 1 || month > 12)
				&& !(day < 1 || day > iaMonthDays[month - 1]);
	}

	static void isIp(String value) {
		regex(REGEX_IP, value, IpErrorMsg + ", value:" + value);
	}

	static void chinese(String value) {
		boolean ret = true;
		if (!ret) {
			throw new ValidationException(ChineseErrorMsg + ", value:" + value);
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
