package com.Intelligent.annotations.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.xml.bind.ValidationException;

import com.Intelligent.annotations.Date;
import com.Intelligent.core.Validator;
import com.Intelligent.utils.CommonUtil;

public class DateImpl implements Validator<Date, Object> {

	private Date date;

	private String format;

	@Override
	public void initialize(Date annotation) {
		this.date = annotation;

		format = date.format();

	}

	@Override
	public void isValid(Object annotationValue) throws ValidationException {

		if (!CommonUtil.isNull(annotationValue.toString())) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			try {
				simpleDateFormat.parse(annotationValue.toString());
			} catch (ParseException e) {
				throw new ValidationException(
						date.msg() + ", format:" + format + ", value:" + annotationValue.toString());
			}
		}

	}

}
