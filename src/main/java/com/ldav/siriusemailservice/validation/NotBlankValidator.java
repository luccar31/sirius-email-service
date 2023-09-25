package com.ldav.siriusemailservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotBlankValidator implements ConstraintValidator<NotBlank, String> {

    @Override
    public boolean isValid(String str, ConstraintValidatorContext context) {
        return str == null || !str.isBlank();
    }
}
