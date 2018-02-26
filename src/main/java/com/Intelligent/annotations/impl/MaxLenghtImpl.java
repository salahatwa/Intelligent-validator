package com.Intelligent.annotations.impl;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.ValidationException;

import com.Intelligent.annotations.MaxLength;
import com.Intelligent.core.Validator;
import com.Intelligent.utils.CommonUtil;

public class MaxLenghtImpl implements Validator<MaxLength, Object> {

	private MaxLength maxLength;
	private int maxValue;

	@Override
	public void initialize(MaxLength annotation) {
		this.maxLength = annotation;
		maxValue = maxLength.value();
	}

	@Override
	public void isValid(Object annotationValue) throws ValidationException {
		if (null == annotationValue) {
			return;
		}
		if (annotationValue instanceof String) {

			if (!CommonUtil.isNull((String) annotationValue)) {
				if (((String) annotationValue).length() > maxValue)
					throw new ValidationException(
							maxLength.msg() + ", max length:" + maxValue + ", value:" + annotationValue);
			}

		} else if (annotationValue instanceof Collection) {

			if (null != annotationValue) {
				if (((Collection) annotationValue).size() > maxValue)
					throw new ValidationException(
							maxLength.msg() + ", max length:" + maxValue + ", value:" + annotationValue);
			}

		} else if (annotationValue instanceof Map) {
			if (null != annotationValue) {
				if (((Map) annotationValue).size() > maxValue)
					throw new ValidationException(
							maxLength.msg() + ", max length:" + maxValue + ", value:" + annotationValue);
			}
		} else if (annotationValue instanceof Object[]) {
			if (null != annotationValue) {
				if (((Object[]) annotationValue).length > maxValue)
					throw new ValidationException(
							maxLength.msg() + ", max length:" + maxValue + ", value:" + annotationValue);
			}
		}

	}

}
