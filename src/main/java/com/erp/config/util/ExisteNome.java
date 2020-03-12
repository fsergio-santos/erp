package com.erp.config.util;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.erp.config.util.validator.EmailValidator;
import com.erp.config.util.validator.NomeValidator;

@Target({ TYPE, FIELD, ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RUNTIME)
@Constraint(validatedBy = NomeValidator.class)
@Documented
public @interface ExisteNome {
	
	 String message() default "Nome já está cadastrado.";
	 Class<?>[] groups() default {};
	 Class<? extends Payload>[] payload() default {};
	 Class<? extends FieldValueExists> service();
	 String serviceQualifier() default "";
	 String fieldName();

}
