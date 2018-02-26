package com.Intelligent.validate;

import com.Intelligent.core.ValidateCache;
import com.Intelligent.utils.CommonUtil;
import com.Intelligent.utils.Parameter;
import com.Intelligent.utils.ReflectUtils;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
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
		CommonUtil.notNull(value);
		if (value instanceof String) {
			CommonUtil.notNull((String) value);
		} else if (value instanceof Number) {
			CommonUtil.notNull((Number) value);
		} else if (value instanceof Collection) {
			CommonUtil.notNull((Collection) value);
		} else if (value instanceof Map) {
			CommonUtil.notNull((Map) value);
		} else if (value instanceof Object[]) {
			CommonUtil.notNull((Object[]) value);
		}
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
						} catch (IllegalAccessException e) {
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
		}
		return validateUtils;
	}

}
