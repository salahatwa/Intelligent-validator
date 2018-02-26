package com.Intelligent.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author salah atwa
 *
 */
public class RegexUtil {

	public static boolean isRegex(String regex, String value) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.matches();
	}
}
