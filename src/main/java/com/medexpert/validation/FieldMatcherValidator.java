package com.medexpert.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class FieldMatcherValidator implements ConstraintValidator<FieldMatch, Object> {
	
	private String firstFieldName;
	private String secondFieldName;
	private String message;
	
	@Override
	public void initialize(FieldMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		message = constraintAnnotation.message();
		
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean valid = true;
		
		try {
			final Object firstObject = new BeanWrapperImpl().getPropertyValue(firstFieldName);
			final Object secondObject = new BeanWrapperImpl().getPropertyValue(secondFieldName);
			
			valid = firstObject == null && secondObject == null 
					|| firstObject != null && firstObject.equals(secondObject);
		} catch (final Exception e) {
			// can ignore
		}
		
		if (!valid) {
			context.buildConstraintViolationWithTemplate(message)
					.addPropertyNode(firstFieldName)
					.addConstraintViolation()
					.disableDefaultConstraintViolation();
		}
		
		return true;
	}
}
