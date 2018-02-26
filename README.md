# Intelligent-validator
<p> Java Annotation Validation API framework </p>
<p> Support comments, full-featured, easy to use </p>

## First, the function profile
Mainly to provide convenient background data validation, support for a single field or parameter validation, but also support the verification of objects through annotation, usage is simple. <br>
Provide basic non-empty, length, size and other verification methods, but also provide some special regular verification, ID card, telephone, email, IP and other verification methods.

## Second, usage introduction
The following verification methods are currently provided to support continued expansion

  | annotation       | Description    |
  | :---------: | :------ |
  | NotNull | Non-empty check |
  | Max | Maximum verification |
  | Min | Minimum verification |
  | MaxLength | Maximum length check, support set, array length check |
  | MinLength | Minimum length check, support set, array length check ||
  | AcceptedValues | Validate if field value in your values list |
  | Regex | Custom regular verification or choose from Predefind regular Email-Phone-IP-English|
  | Date | Date format verification |
  
## Third, usage introduction
You can custom annotation and custom your own  implementation .

### 1. Single parameter verification
```
ValidateUtils.is("a").notNull();
 
ValidateUtils.is("test").maxLength(20).minLength(4);
 
ValidateUtils.is(50).min(20).max(60);
```

Through and () support for continuous write (even write directly switch the calibration object)

```
ValidateUtils.is("a").notNull().and("test").maxLength(20).minLength(4).and(50).min(20).max(60);
```
Support for custom error messages

```
ValidateUtils.is("test").maxLength(20,"The maximum length can not exceed 20 words").minLength(4,"The minimum length can not be less than 4 words");
```
### 2. Verify the entire object (by annotation)
Define annotations on the properties of the class, and support for custom error messages
```
public class Model {

    @NotNull(msg = "Model Name can not be empty")
    @MaxLength(value = 50,msg = "Name can not exceed 50 characters")
    private String modelName;

    @Date
    private Date birthOfDate;

    @Max(30)
    @Min(12)
    private int age;

    @Regex("[1-9]([0-9]{5,11})")
    private String customRegex;
    
    @Regex(type = RegexType.REGEX_EMAIL)
    @MaxLength(50)
	  private String email; 
    
    //getter... setter..
}
```

Then call ValidateUtils.check () method can be

```
try {
    //....
    ValidateUtils.check(user);
    //.....
}catch (ParamsException e){
    throw e;
}catch (Exception e){
    //...
}
```

The same support for continuous writing

```
ValidateUtils.check(user).and("2017-06-05").date("yyyy-MM-dd");
```

### 3. Processing when verification fails
ParamsException (runtime exception) is thrown if the validation fails

Generally do not need special treatment, because the background check is a safety check, generally used to intercept illegal operations, so no friendly tips, it is recommended not to do any capture or special treatment, such as the outer catch, it is recommended that after a separate capture Threw up. <br>
If you want to catch exceptions, you can also add try / catch (not recommended) to your code, or add global interceptors to catch such exceptions. <br>


## Download the code and build project 
```mvn clean install```

```
<dependencies>
   <dependency>
	<groupId>com.intelligent.validator</groupId>
	<artifactId>Intelligent-validator</artifactId>
	<version>1.1.0</version>
   </dependency>
</dependencies>
```


## Write Your Custom Annotation 

```
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotation {
	
	public String print() default "";

}
```

## Write your Custom Implementation 

```
import com.Intelligent.core.Validator;

public class CustomAnnotationImpl implements Validator<CustomAnnotation, String> {

	private CustomAnnotation customAnnotation;

	@Override
	public void initialize(CustomAnnotation annotation) {
		this.customAnnotation = annotation;
		System.err.println("Intialized Here.....");

	}

	@Override
	public void isValid(String annotationValue) throws ValidationException {
		System.out.println("Custom Message Value :" + customAnnotation.print());

	}

}
```

## Use Your annotation in your model
```
public class Test {

	@CustomAnnotation(print = "Please Stare our repo ^_^ ....")
	private String data;
  
  //setter -- getter

```

## Output 

```
Intialized Here.....
Custom Message Value :Please Stare our repo ^_^ ....

```





