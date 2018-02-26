package com.Intelligent.validate;

import com.Intelligent.annotations.*;
import com.Intelligent.core.ValidateCache;
import com.Intelligent.core.ValidateHandler;
import com.Intelligent.core.Validator;
import com.Intelligent.utils.CommonUtil;
import com.Intelligent.utils.Parameter;
import com.Intelligent.utils.ReflectUtils;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author salah atwa
 *
 */
public class ValidateUtils {

	private Object value;

	private ValidateUtils(Object value) {
		this.value = value;
	}

	public static ValidateUtils is(Object value) {
		return new ValidateUtils(value);
	}

	public ValidateUtils and(Object value) {
		this.value = value;
		return this;
	}

	public ValidateUtils notNull() {
		return notNull(null);
	}

	public ValidateUtils notNull(String msg) {
		ValidateHandler.notNull(value, msg);
		return this;
	}

	public ValidateUtils regex(String regex) {
		return regex(regex, null);
	}

	public ValidateUtils regex(String regex, String msg) {
		ValidateHandler.regex(regex, value, msg);
		return this;
	}

	public ValidateUtils max(Number max) {
		return max(max, null);
	}

	public ValidateUtils max(Number max, String msg) {
		ValidateHandler.max(max, value, msg);
		return this;
	}

	public ValidateUtils min(Number min) {
		return min(min, null);
	}

	public ValidateUtils min(Number min, String msg) {
		ValidateHandler.min(min, value, msg);
		return this;
	}

	public ValidateUtils maxLength(int max) {
		return maxLength(max, null);
	}

	public ValidateUtils maxLength(int max, String msg) {
		ValidateHandler.maxLength(max, value, msg);
		return this;
	}

	public ValidateUtils minLength(int min) {
		return minLength(min, null);
	}

	public ValidateUtils minLength(int min, String msg) {
		ValidateHandler.minLength(min, value, msg);
		return this;
	}

	public ValidateUtils chinese() {
		return chinese(null);
	}

	public ValidateUtils chinese(String msg) {
		ValidateHandler.chinese(value, msg);
		return this;
	}

	public ValidateUtils english() {
		return english(null);
	}

	public ValidateUtils english(String msg) {
		ValidateHandler.english(value, msg);
		return this;
	}

	public ValidateUtils phone() {
		return phone(null);
	}

	public ValidateUtils phone(String msg) {
		ValidateHandler.phone(value, msg);
		return this;
	}

	public ValidateUtils email() {
		return email(null);
	}

	public ValidateUtils email(String msg) {
		ValidateHandler.email(value, msg);
		return this;
	}

	public ValidateUtils date(String format) {
		return date(format, null);
	}

	public ValidateUtils date(String format, String msg) {
		ValidateHandler.date(format, value, msg);
		return this;
	}

	public ValidateUtils idCard() {
		return idCard(null);
	}

	public ValidateUtils idCard(String msg) {
		ValidateHandler.idCard(value, msg);
		return this;
	}

	public ValidateUtils ip() {
		return ip(null);
	}

	public ValidateUtils ip(String msg) {
		ValidateHandler.ip(value, msg);
		return this;
	}

	public ValidateUtils acceptedValues(String msg, List<String> values) {
		ValidateHandler.acceptedValues(value, values, msg);
		return this;
	}

	public static ValidateUtils check(Object value) {

		ValidateUtils validateUtils = new ValidateUtils(value);
		validateUtils.notNull();
		Class classType = value.getClass();

		Set<Field> fieldSet = ValidateCache.getInstance().getFieldsByClass(classType);
		if (null == fieldSet) {
			fieldSet = ReflectUtils.getFieldsByClass(value.getClass());
			ValidateCache.getInstance().setClassFields(classType, fieldSet);
		}
		if (CommonUtil.isNull(fieldSet)) {
			return validateUtils;
		}
		for (Field field : fieldSet) {
			Annotation[] annotations = field.getAnnotations();

			for (Annotation annotation : annotations) {
				System.err.println(annotation + "::");
			}

			annotations = field.getAnnotations();

			if (CommonUtil.isNull(annotations)) {
				continue;
			}
			Object fieldValue;
			try {
				fieldValue = PropertyUtils.getProperty(value, field.getName());

			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			for (Annotation annotation : annotations) {
				Map<Class, Parameter> x = ReflectUtils.search();

				for (Map.Entry<Class, Parameter> ss : x.entrySet()) {
					Class key = ss.getKey();
					Parameter anno = ss.getValue();

					if (annotation.annotationType().equals(anno.getAnnotation())) {
						try {
							Method initialize = key.getMethod("initialize", anno.getAnnotation());

							Object initializeArglist[] = new Object[1];
							initializeArglist[0] = annotation;
							Object o = key.newInstance();

							initialize.invoke(o, initializeArglist);
							
							Method isValid = key.getMethod("isValid", anno.getObjectType());
							
							Object isValidArglist[] = new Object[1];
							isValidArglist[0] = fieldValue;
							
							System.out.println(fieldValue);

							isValid.invoke(o, isValidArglist);
							
							
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						}
						catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (InstantiationException e) {
							e.printStackTrace();
						}
					}

				}

			}
			// if (annotation instanceof NotNull) {
			// validateUtils.and(fieldValue).notNull(((NotNull) annotation).msg());
			// } else if (annotation instanceof Max) {
			// Max max = (Max) annotation;
//			 validateUtils.and(fieldValue).max(max.value(), max.msg());
			// } else if (annotation instanceof Min) {
			// Min min = (Min) annotation;
			// validateUtils.and(fieldValue).min(min.value(), min.msg());
			// } else if (annotation instanceof MaxLength) {
			// MaxLength maxLength = (MaxLength) annotation;
			// validateUtils.and(fieldValue).maxLength(maxLength.value(), maxLength.msg());
			// } else if (annotation instanceof MinLength) {
			// MinLength minLength = (MinLength) annotation;
			// validateUtils.and(fieldValue).minLength(minLength.value(), minLength.msg());
			// } else if (annotation instanceof Email) {
			// validateUtils.and(fieldValue).email(((Email) annotation).msg());
			// } else if (annotation instanceof Phone) {
			// validateUtils.and(fieldValue).phone(((Phone) annotation).msg());
			// } else if (annotation instanceof IdCard) {
			// validateUtils.and(fieldValue).idCard(((IdCard) annotation).msg());
			// } else if (annotation instanceof Regex) {
			// Regex regex = (Regex) annotation;
			// validateUtils.and(fieldValue).regex(regex.value(), regex.msg());
			// } else if (annotation instanceof Date) {
			// Date date = (Date) annotation;
			// String format = date.format();
			// validateUtils.and(fieldValue).date(format, date.msg());
			// } else if (annotation instanceof Langauge) {
			// validateUtils.and(fieldValue).chinese(((Langauge) annotation).msg());
			// } else if (annotation instanceof IP) {
			// validateUtils.and(fieldValue).ip(((IP) annotation).msg());
			// } else if (annotation instanceof AcceptedValues) {
			// List<String> values = new ArrayList<>();
			// AcceptedValues cc = (AcceptedValues) annotation;
			// for (String val : cc.acceptValues()) {
			// values.add(val.toLowerCase());
			// }
			// validateUtils.and(fieldValue).acceptedValues(((AcceptedValues)
			// annotation).msg(),values);
			// }
			// }
		}
		return validateUtils;
	}

}
