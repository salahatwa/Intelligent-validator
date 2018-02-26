package com.Intelligent.annotations.impl;

import javax.xml.bind.ValidationException;

import com.Intelligent.annotations.Min;
import com.Intelligent.core.Validator;

public class MinImpl implements Validator<Min, Object> {

	private Min min;
	private Number minValue;

	@Override
	public void initialize(Min annotation) {
		min = annotation;
		minValue = min.value();

	}

	@Override
	public void isValid(Object annotationValue) throws ValidationException {

		if (null == annotationValue || !(annotationValue instanceof Number)) {
			throw new ValidationException(min.msg() + ", min:" + minValue + ", value:" + annotationValue);
		}
		if (minValue instanceof Integer) {
			if ((int) annotationValue < minValue.intValue())
				throw new ValidationException(min.msg() + ", min:" + minValue + ", value:" + annotationValue);
		} else if (minValue instanceof Long) {
			if ((Long) annotationValue < minValue.longValue())
				throw new ValidationException(min.msg() + ", min:" + minValue + ", value:" + annotationValue);
		} else if (minValue instanceof Double) {
			if ((Double) annotationValue < minValue.doubleValue())
				throw new ValidationException(min.msg() + ", min:" + minValue + ", value:" + annotationValue);
		} else if (minValue instanceof Float) {
			if ((Float) annotationValue < minValue.floatValue())
				throw new ValidationException(min.msg() + ", min:" + minValue + ", value:" + annotationValue);
		} else if (minValue instanceof Short) {
			if ((Short) annotationValue < minValue.shortValue())
				throw new ValidationException(min.msg() + ", min:" + minValue + ", value:" + annotationValue);
		} else if (minValue instanceof Byte) {
			if ((Byte) annotationValue < minValue.byteValue())
				throw new ValidationException(min.msg() + ", min:" + minValue + ", value:" + annotationValue);
		}

	}

}
