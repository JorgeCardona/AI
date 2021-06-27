package jorge.cardona.concepts.annotations.methods;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateContentType implements ConstraintValidator<AnotationValidateContentType, String> {

    private Pattern validation =  Pattern.compile("^[^0-9]*$");


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        final Matcher matcher = validation.matcher(value);

        return matcher.matches() ? true: false;

        }
}