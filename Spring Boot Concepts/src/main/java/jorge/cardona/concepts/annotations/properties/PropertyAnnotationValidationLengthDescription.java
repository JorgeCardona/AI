package jorge.cardona.concepts.annotations.properties;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = ValidatePropertyLengthDescription.class)
@Target(value={ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyAnnotationValidationLengthDescription {

    String message() default "{property.invalid.length}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}