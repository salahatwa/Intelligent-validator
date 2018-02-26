package com.Intelligent.finder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;



/**
 * Some basic String utilities
 * 
 * @author Sam
 * 
 */
public class StringUtil {

	public static final String EMPTY_STRING = "";

	public static final String ELLIPSIS_STRING = "...";

	public static boolean isNullOrEmpty(String string) {
		return string == null || EMPTY_STRING.equals(string);
	}

	public static String removeTagFromTagValueBlock(String tagValueBlock, int tag) {
		String newTagValueBlock = "";
		if (tagValueBlock == null)
			return null;

		StringTokenizer blockTok = new StringTokenizer(tagValueBlock, "|");
		while (blockTok.hasMoreTokens()) {
			String tagValueField = blockTok.nextToken();
			StringTokenizer nvpTok = new StringTokenizer(tagValueField, "=");
			// Get the fix tag name.
			if (!nvpTok.hasMoreTokens()) {
				continue;
			}
			String fixTagName = nvpTok.nextToken();

			// Get the fix tag value.
			if (!nvpTok.hasMoreTokens()) {
				continue;
			}
			try {
				if (Integer.parseInt(fixTagName) == tag) {
					continue;
				}
				newTagValueBlock += tagValueField + "|";
			} catch (Exception ex) {
				continue;
			}

		}
		return newTagValueBlock;
	}


	/**
	 * List the contents of an array. Each entry is separated by a newline
	 * character
	 * 
	 * @param array
	 * @return
	 */
	public static String dumpArray(String[] array) {
		String delimiter = System.getProperty("line.separator");
		return dumpArray(array, delimiter);
	}

	/**
	 * List the contents of an array. Each entry is separated by specified
	 * delimiter
	 * 
	 * @param array
	 * @param delimiter
	 *            delimiter to use to separate each array element
	 * @return
	 */
	public static String dumpArray(String[] array, String delimiter) {
		String dump = "";
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (dump.length() > 0) {
					dump += delimiter;
				}
				dump += array[i];
			}
		}
		return dump;
	}

	/**
	 * List the contents of an array. Each entry is separated by a newline
	 * character. Objects are converted to their toString methods
	 * 
	 * @param array
	 * @return
	 */
	public static String dumpArray(Object[] array) {
		String[] newArray = convertObjectArrayToStringArray(array);
		return dumpArray(newArray);
	}

	private static String[] convertObjectArrayToStringArray(Object[] array) {
		String[] newArray = new String[array.length];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = String.valueOf(array[i]);
		}
		return newArray;
	}

	/**
	 * Check to see if source string contains the target string within it
	 * 
	 * @param source
	 *            string to search
	 * @param target
	 *            value to search for
	 * @return true if found, false if not
	 */
	public static boolean stringContains(String source, String target) {
		if (source == null || target == null) {
			return false;
		}
		return source.indexOf(target) > -1;
	}

	/**
	 * Check to see if source string contains the target string within it
	 * 
	 * @param source
	 *            string to search
	 * @param target
	 *            value to search for
	 * @return true if found, false if not
	 */
	public static boolean stringContains(String source, int target) {
		return stringContains(source, String.valueOf(target));
	}

	/**
	 * Check to see if source string contains the target string within it
	 * 
	 * @param source
	 *            string to search
	 * @param target
	 *            value to search for
	 * @return true if found, false if not
	 */
	public static boolean stringContains(String source, double target) {
		return stringContains(source, String.valueOf(target));
	}

	/**
	 * Check to see if source string contains the target string within it
	 * 
	 * @param source
	 *            string to search
	 * @param target
	 *            value to search for
	 * @return true if found, false if not
	 */
	public static boolean stringContains(String source, Object target) {
		return stringContains(source, String.valueOf(target));
	}

	public static String dumpArray(Collection<?> list) {
		if (list == null) {
			return null;
		}
		return dumpArray(list.toArray());
	}

	public static String dumpArray(Collection<?> list, String delimiter) {
		if (list == null) {
			return null;
		}
		return dumpArray(list.toArray(), delimiter);
	}

	public static String dumpArray(Object[] array, String delimiter) {
		return dumpArray(convertObjectArrayToStringArray(array), delimiter);
	}

	/**
	 * Concatenate all String elements using a "," delimiter
	 * 
	 * @param strings
	 * @return single string, with comma separated elements
	 */
	public static String concatStrings(String[] strings) {
		StringBuffer buffer = new StringBuffer();
		for (String string : strings) {
			if (buffer.length() > 0) {
				buffer.append(",");
			}
			buffer.append(string);
		}
		return buffer.toString();
	}

	/**
	 * Takes Collection of Strings and returns them as a single line of comma separated strings.
	 * See {@link #concatStrings(String[])}
	 * @param strings
	 * @return
	 */
	public static String concatStrings(Collection<String> strings) {
		return concatStrings(strings.toArray(new String[strings.size()]));
	}	

	public static String wrapStringForSQL(String value) {
		return "'" + value + "'";
	}

	public static String concatForSQLInClause(String[] stringList) {
		if (stringList == null)
			return null;
		StringBuffer buffer = new StringBuffer();
		for (String string : stringList) {
			if (buffer.length() > 0) {
				buffer.append(",");
			}
			buffer.append(wrapStringForSQL(string));
		}
		return buffer.toString();
	}

	/**
	 * If String exceeds maxLength, truncate it and add {@link #ELLIPSIS_STRING} 
	 * to end.
	 * @param msg
	 * @param maxLength
	 * @return
	 */
	public static String abbreviate(String msg, int maxLength) {
		if (msg == null) return null;
		if (msg.length() > maxLength) {
			return msg.substring(0, maxLength) + ELLIPSIS_STRING;
		}
		return msg;		
	}

	/**
	 * Abbreviates to a string to max length
	 * @param msgBuf
	 * @param offset
	 * @param maxLength
	 * @return
	 */
	public static String abbreviate(byte[] bytes, int offset, int maxLength) {
		if (bytes == null || offset < 0 || maxLength < 1) return EMPTY_STRING;
		int msgLength = bytes.length - offset;
		int length = msgLength > maxLength ? maxLength : msgLength;
		String message = new String(bytes, offset, length);
		if (msgLength > maxLength) message += ELLIPSIS_STRING;
		return message;
	}

	public static String join(String joiner, Collection<String> strings) {
		if (strings == null) return null;
		StringBuffer buffer = new StringBuffer();
		for (String string : strings) {
			if (buffer.length() > 0) {
				buffer.append(joiner);
			}
			buffer.append(string);
		}
		return buffer.toString();
	}

	public static Collection<String> wrapAll(String delimiter, Collection<String> strings) {

		Collection<String> results = new ArrayList<String>();
		for (String string : strings ) {
			StringBuffer buffer = new StringBuffer();
			buffer.append(delimiter);
			buffer.append(string);
			buffer.append(delimiter);
			results.add(buffer.toString());
		}
		return results;
	}



}
