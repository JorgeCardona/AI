package jorge.cardona.concepts.jpa.anotations.methods;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = ValidateContentType.class)
@Target({ METHOD, FIELD, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AnotationValidateContentType {

    String message() default "{property.invalid.other}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
