package jorge.cardona.concepts.repository;

import jorge.cardona.concepts.entity.FruitEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FruitRepository extends MongoRepository<FruitEntity, String> {

}
