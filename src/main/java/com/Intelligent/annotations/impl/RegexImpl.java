package com.Intelligent.annotations.impl;

import javax.xml.bind.ValidationException;

import com.Intelligent.annotations.Regex;
import com.Intelligent.annotations.Regex.RegexType;
import com.Intelligent.core.Validator;
import com.Intelligent.utils.CommonUtil;
import com.Intelligent.utils.RegexUtil;

public class RegexImpl implements Validator<Regex, Object> {

	private Regex regex;

	@Override
	public void initialize(Regex annotation) {
		this.regex = annotation;

	}

	@Override
	public void isValid(Object annotationValue) throws ValidationException {
		if (null == annotationValue) {
			return;
		}

		if (!CommonUtil.isNull(annotationValue.toString())) {

			if (regex.type().equals(RegexType.REGEX_EMAIL)) {
				if (!RegexUtil.isRegex(RegexType.REGEX_EMAIL.getRegexType(), annotationValue.toString()))
					throw new ValidationException("Inavlid Regex : " + regex.msg());
			} else if (regex.type().equals(RegexType.REGEX_DATE)) {
				if (!RegexUtil.isRegex(RegexType.REGEX_DATE.getRegexType(), annotationValue.toString()))
					throw new ValidationException("Inavlid Regex : " + regex.msg());
			} else if (regex.type().equals(RegexType.REGEX_IP)) {
				if (!RegexUtil.isRegex(RegexType.REGEX_IP.getRegexType(), annotationValue.toString()))
					throw new ValidationException("Inavlid Regex : " + regex.msg());
			} else if (regex.type().equals(RegexType.REGEX_PHONE)) {
				if (!RegexUtil.isRegex(RegexType.REGEX_PHONE.getRegexType(), annotationValue.toString()))
					throw new ValidationException("Inavlid Regex : " + regex.msg());
			} else if (!RegexUtil.isRegex(regex.value(), annotationValue.toString())) {
				throw new ValidationException("Inavlid Regex : " + regex.msg());
			}

		}

	}

}
