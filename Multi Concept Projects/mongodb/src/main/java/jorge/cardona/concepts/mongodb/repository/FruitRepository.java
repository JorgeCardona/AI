package jorge.cardona.concepts.mongodb.repository;

import jorge.cardona.concepts.mongodb.entity.FruitEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FruitRepository extends MongoRepository<FruitEntity, String> {

}
