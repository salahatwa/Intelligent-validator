package com.Intelligent.annotations.impl;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.ValidationException;

import com.Intelligent.annotations.AcceptedValues;
import com.Intelligent.core.Validator;

public class AcceptedValuesImpl implements Validator<AcceptedValues, String> {
	List<String> values = null;
	AcceptedValues cc;

	@Override
	public void initialize(AcceptedValues annotation) {
		cc = (AcceptedValues) annotation;
		values = Arrays.asList(cc.acceptValues());
	}

	@Override
	public void isValid(String annotationValue) throws ValidationException {
		
		if (!values.contains(annotationValue.toString()))
			throw new ValidationException(cc.msg());
	}

	// @Override
	// public <T, R> void isValid(T annotation, R objectValue) {
	// AcceptedValues cc = (AcceptedValues) annotation;
	// List<String> values = Arrays.asList(cc.acceptValues());
	//
	// if (!values.contains(objectValue.toString()))
	// throw new ValidationException(cc.msg());
	// }

}
