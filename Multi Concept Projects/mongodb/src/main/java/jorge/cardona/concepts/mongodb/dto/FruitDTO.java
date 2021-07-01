package jorge.cardona.concepts.mongodb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FruitDTO {

    private String fruitId;

    private String fruitName;
}