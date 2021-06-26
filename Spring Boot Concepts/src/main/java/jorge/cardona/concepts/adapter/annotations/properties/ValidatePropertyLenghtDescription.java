package jorge.cardona.concepts.adapter.annotations.properties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatePropertyLenghtDescription implements ConstraintValidator<PropertyAnnotationValidationLenghtDescription, String> {

    private static final int MAX = 1000;
    private static final int MIN = 200;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        int suma = value.chars().sum();

        return suma > MIN && suma < MAX;
    }
}