package com.Intelligent.annotations.impl;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.ValidationException;

import com.Intelligent.annotations.MinLength;
import com.Intelligent.core.Validator;
import com.Intelligent.utils.CommonUtil;

public class MinLenghtImpl implements Validator<MinLength, Object> {

	private MinLength minLength;
	private int minValue;

	@Override
	public void initialize(MinLength annotation) {
		this.minLength = annotation;
		minValue = minLength.value();
	}

	@Override
	public void isValid(Object annotationValue) throws ValidationException {
		if (null == annotationValue) {
			return;
		}
		if (annotationValue instanceof String) {

			if (!CommonUtil.isNull((String) annotationValue)) {
				if (((String) annotationValue).length() < minValue)
					throw new ValidationException(minLength.msg() + ", min:" + minValue + ", value:" + annotationValue);
			}

		} else if (annotationValue instanceof Collection) {

			if (null != annotationValue) {
				if (((Collection) annotationValue).size() < minValue)
					throw new ValidationException(minLength.msg() + ", min:" + minValue + ", value:" + annotationValue);
			}

		} else if (annotationValue instanceof Map) {
			if (null != annotationValue) {
				if (((Map) annotationValue).size() < minValue)
					throw new ValidationException(minLength.msg() + ", min:" + minValue + ", value:" + annotationValue);
			}
		} else if (annotationValue instanceof Object[]) {
			if (null != annotationValue) {
				if (((Object[]) annotationValue).length < minValue)
					throw new ValidationException(minLength.msg() + ", min:" + minValue + ", value:" + annotationValue);
			}
		}

	}

}
