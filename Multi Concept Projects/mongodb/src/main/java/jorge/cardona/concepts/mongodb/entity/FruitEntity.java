package jorge.cardona.concepts.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "fruit-test")
public class FruitEntity {

    @Id
    private String fruitId;

    private String fruitName;
}