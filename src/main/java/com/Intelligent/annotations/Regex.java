package com.Intelligent.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Documented
public @interface Regex {
	
	public enum RegexType {
		REGEX_DATE("^[0-9]{8}$"),
		REGEX_IP("(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d|\\*)"), 
		REGEX_ENGLISH("^[a-zA-z]{1,}$"), 
		REGEX_PHONE("^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$"),
		REGEX_EMAIL("\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}");

		RegexType(String type) {
			this.regexType = type;
		}

		private String regexType;

		public String getRegexType() {
			return regexType;
		}
	}

	String value() default "";
	
	RegexType type() default RegexType.REGEX_EMAIL;

	String msg() default "";
}
