package jorge.cardona.concepts.adapter.annotations.properties;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = ValidatePropertyLenghtDescription.class)
@Target(value={ElementType.FIELD} )
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyAnnotationValidationLenghtDescription {

    String message() default "{property.invalid.lenght}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}