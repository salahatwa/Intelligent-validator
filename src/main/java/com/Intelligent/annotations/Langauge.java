package com.Intelligent.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Langauge {
	
	public enum LanguageType{EN,CH,AR}
	
    String msg() default "";
    
    LanguageType type();
}
