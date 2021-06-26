package jorge.cardona.concepts.adapter.annotations.properties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatePropertyLenghtOther implements ConstraintValidator<PropertyAnotationValidatePropertyLenghtOther, String> {

    private static final int MAX = 3;
    private static final int MIN = 10;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        int suma = value.chars().sum();

        return suma > MIN && suma < MAX;
    }
}