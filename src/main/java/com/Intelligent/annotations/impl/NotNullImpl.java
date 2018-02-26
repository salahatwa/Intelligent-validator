package com.Intelligent.annotations.impl;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.ValidationException;

import com.Intelligent.annotations.NotNull;
import com.Intelligent.core.Validator;
import com.Intelligent.utils.CommonUtil;

public class NotNullImpl implements Validator<NotNull, Object> {

	private NotNull notNull;

	@Override
	public void initialize(NotNull annotation) {
		this.notNull = annotation;

	}

	@Override
	public void isValid(Object annotationValue) throws ValidationException {

		if (null == annotationValue)
			throw new ValidationException(notNull.msg());
		else if (annotationValue instanceof String) {
			if (CommonUtil.isNull((String) annotationValue))
				throw new ValidationException(notNull.msg());
		} else if (annotationValue instanceof Number) {
			if (null == (Number) annotationValue)
				throw new ValidationException(notNull.msg());
		} else if (annotationValue instanceof Collection) {
			if (CommonUtil.isNull((Collection) annotationValue))
				throw new ValidationException(notNull.msg());
		} else if (annotationValue instanceof Map) {
			if (CommonUtil.isNull((Map) annotationValue))
				throw new ValidationException(notNull.msg());
		} else if (annotationValue instanceof Object[]) {
			if (CommonUtil.isNull((Object[]) annotationValue))
				throw new ValidationException(notNull.msg());
		}

	}

}
