package com.Intelligent.core;

import java.lang.annotation.Annotation;

import javax.xml.bind.ValidationException;

public interface Validator<A extends Annotation, T> {
	
	void initialize(A annotation);

	public  void isValid(T annotationValue)  throws ValidationException ;
}
