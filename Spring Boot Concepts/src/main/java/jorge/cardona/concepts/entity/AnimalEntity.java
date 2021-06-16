package jorge.cardona.concepts.entity;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AnimalEntity {

    @Id
    @GeneratedValue
    @GraphQLQuery(name = "id", description = "An Animal's id")
    private Long id;

    @GraphQLQuery(name = "name", description = "An Animal's name")
    private String name;

    @GraphQLQuery(name = "type", description = "An Animal's type")
    private String type;
}