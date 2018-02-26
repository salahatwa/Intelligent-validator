package com.Intelligent.annotations.impl;

import javax.xml.bind.ValidationException;

import com.Intelligent.annotations.Max;
import com.Intelligent.core.Validator;

public class MaxImpl implements Validator<Max, Object> {

	private Max max;
	private Number maxValue;

	@Override
	public void initialize(Max annotation) {
		max = annotation;
		maxValue = max.value();

	}

	@Override
	public void isValid(Object annotationValue) throws ValidationException {

		if (null == annotationValue || !(annotationValue instanceof Number)) {
			throw new ValidationException(max.msg() + ", max:" + maxValue + ", value:" + annotationValue);
		}
		if (maxValue instanceof Integer) {
			if ((int) annotationValue > maxValue.intValue())
				throw new ValidationException(max.msg() + ", max:" + maxValue + ", value:" + annotationValue);
		} else if (maxValue instanceof Long) {
			if ((Long) annotationValue > maxValue.longValue())
				throw new ValidationException(max.msg() + ", max:" + maxValue + ", value:" + annotationValue);
		} else if (maxValue instanceof Double) {
			if ((Double) annotationValue > maxValue.doubleValue())
				throw new ValidationException(max.msg() + ", max:" + maxValue + ", value:" + annotationValue);
		} else if (maxValue instanceof Float) {
			if ((Float) annotationValue > maxValue.floatValue())
				throw new ValidationException(max.msg() + ", max:" + maxValue + ", value:" + annotationValue);
		} else if (maxValue instanceof Short) {
			if ((Short) annotationValue > maxValue.shortValue())
				throw new ValidationException(max.msg() + ", max:" + maxValue + ", value:" + annotationValue);
		} else if (maxValue instanceof Byte) {
			if ((Byte) annotationValue > maxValue.byteValue())
				throw new ValidationException(max.msg() + ", max:" + maxValue + ", value:" + annotationValue);
		}

	}

}
