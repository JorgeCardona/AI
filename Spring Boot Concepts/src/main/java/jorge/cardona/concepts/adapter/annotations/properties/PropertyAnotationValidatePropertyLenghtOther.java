package jorge.cardona.concepts.adapter.annotations.properties;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidatePropertyLenghtOther.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyAnotationValidatePropertyLenghtOther {

    String message() default "{property.invalid.other}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
