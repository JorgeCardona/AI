package jorge.cardona.concepts.annotations.properties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatePropertyLengthDescription implements ConstraintValidator<PropertyAnnotationValidationLengthDescription, String> {

    private static final int MAX = 1000;
    private static final int MIN = 100;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // validate that object parameter is present into request object
        if(value != null){

            // is the sumary of values of every character
            int total = value.chars().sum();

            // validate the lenght of the text value
            return value.length() > MIN && value.length() < MAX;
        }

        return false;
    }
}