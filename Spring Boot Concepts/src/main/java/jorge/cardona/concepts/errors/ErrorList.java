package jorge.cardona.concepts.errors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
public class ErrorList {

    @JsonProperty("title")
    private String title;

    @JsonProperty("atribute")
    private String atribute;

    @JsonProperty("detail")
    private String detail;

    @JsonProperty("validatorName")
    private String validatorName;
}
