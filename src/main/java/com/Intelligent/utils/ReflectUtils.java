package com.Intelligent.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.Intelligent.core.Validator;
import com.Intelligent.finder.JavaClassFinder;

/**
 * 
 * @author salah atwa
 *
 */
public class ReflectUtils {

	private static Logger LOGGER = LoggerFactory.getLogger(ReflectUtils.class);

	public static <T> Set<Field> getFieldsByClass(Class<T> cls) {
		Set<Field> fieldSet = new HashSet<>();
		for (Class<?> clazz = cls; clazz != Object.class; clazz = clazz.getSuperclass()) {
			Field[] fields = clazz.getDeclaredFields();
			if (CommonUtil.isNull(fields)) {
				continue;
			}
			for (Field field : fields) {
				if (!field.getName().equals("class") && !field.getName().equals("serialVersionUID")) {
					fieldSet.add(field);
				}
			}
		}
		return fieldSet;
	}

	public static Map<Class, Parameter> search() {
		Map<Class, Parameter> map = new HashMap<>();

		JavaClassFinder classFinder = new JavaClassFinder();
		String originalClassPath = System.getProperty("java.class.path");
		String originalCustomClassPath = System.getProperty(JavaClassFinder.CUSTOM_CLASS_PATH_PROPERTY);
		if (originalCustomClassPath != null) {
			LOGGER.warn("custom classpath was already set to={}", originalCustomClassPath);
		}

		System.setProperty("java.class.path", originalClassPath);
		if (originalCustomClassPath != null) {
			System.setProperty(JavaClassFinder.CUSTOM_CLASS_PATH_PROPERTY, originalCustomClassPath);
			LOGGER.warn("custom classpath sysproperty reset to={}", originalCustomClassPath);
		} else {
			System.clearProperty(JavaClassFinder.CUSTOM_CLASS_PATH_PROPERTY);
		}

		List<Class<? extends Validator>> classes = classFinder.findAllMatchingTypes(Validator.class);

		for (Class<? extends Validator> class1 : classes) {

			Type[] genericInterfaces = class1.getGenericInterfaces();

			for (Type type : genericInterfaces) {
				if (type instanceof ParameterizedType) {
					Type[] genericTypes = ((ParameterizedType) type).getActualTypeArguments();

					Class type1 = (Class) genericTypes[0];
					Class type2 = (Class) genericTypes[1];
					Parameter parameter = new Parameter();
					parameter.setAnnotation(type1);
					parameter.setObjectType(type2);

					map.put(class1, parameter);
				}
			}
		}
		return map;
	}

	public static void main(String[] args) {

		Map<Class, Parameter> x = search();

		for (Map.Entry<Class, Parameter> ss : x.entrySet()) {
			System.err.println(ss.getKey() + ":" + ss.getValue());
		}
	}
}
