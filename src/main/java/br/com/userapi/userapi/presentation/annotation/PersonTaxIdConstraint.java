package br.com.userapi.userapi.presentation.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.userapi.userapi.presentation.annotation.impl.PersonTaxIdValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PersonTaxIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonTaxIdConstraint {
    String message() default "personTaxId invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
