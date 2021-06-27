package jorge.cardona.concepts.util;

import jorge.cardona.concepts.errors.ErrorList;
import jorge.cardona.concepts.errors.ValidationResponse;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Component
public class ValidateErrors {

    public ValidationResponse getErrorList (Errors errors){

        // build validation Object with the information
        ValidationResponse validationResponse = ValidationResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();

        // create a errors list
        List<ErrorList> errorRequestList = new ArrayList<>();

        // extracts the errors and save in a list
        errors.getFieldErrors().stream().forEach(x ->
                errorRequestList.add(
                        ErrorList.builder()
                                .title("VALIDATION ERROR")
                                .atribute(x.getField())
                                .detail(x.getDefaultMessage().toUpperCase())
                                .validatorName(x.getCode())
                                .build())
        );

        // fill the error list to show to the user
        validationResponse.setErrors(errorRequestList);

        return validationResponse;

    }
}
