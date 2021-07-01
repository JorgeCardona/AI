package jorge.cardona.concepts.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class NatureDTO {

    private UUID id;

    private String kingdom;

    private String description;

}