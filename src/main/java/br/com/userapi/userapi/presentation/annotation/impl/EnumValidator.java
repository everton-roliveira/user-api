package br.com.userapi.userapi.presentation.annotation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.userapi.userapi.presentation.annotation.EnumConstraint;

public class EnumValidator implements ConstraintValidator<EnumConstraint, CharSequence> {
    private EnumConstraint annotation;

    @Override
    public void initialize(EnumConstraint constraintAnnotation) {
        annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Null values are considered valid
        }

        boolean isValid = false;
        Class<? extends Enum<?>> enumClass = annotation.enumClass();
        boolean ignoreCase = annotation.ignoreCase();

        StringBuilder supportedValues = new StringBuilder();
        for (Enum<?> enumValue : enumClass.getEnumConstants()) {
            String enumName = enumValue.name();
            supportedValues.append(enumName).append(", ");
            if (ignoreCase) {
                if (enumName.equalsIgnoreCase(value.toString())) {
                    isValid = true;
                    break;
                }
            } else {
                if (enumName.equals(value.toString())) {
                    isValid = true;
                    break;
                }
            }
        }

        if (!isValid) {
            supportedValues.setLength(supportedValues.length() - 2); // Remove the trailing comma and space
            String message = annotation.message().replace("{supportedValues}", supportedValues.toString());

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation();
        }

        return isValid;
    }
}
