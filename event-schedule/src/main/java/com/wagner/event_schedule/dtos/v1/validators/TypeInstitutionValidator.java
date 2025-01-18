package com.wagner.event_schedule.dtos.v1.validators;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class TypeInstitutionValidator implements ConstraintValidator<ValueOfTypeInstitutionEnum, String> {

    private Enum<?>[] enumValues;

    @Override
    public void initialize(ValueOfTypeInstitutionEnum annotation) {
        enumValues = annotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        return Arrays.stream(enumValues)
                .anyMatch(e -> e.name().equalsIgnoreCase(value));
    }
}
