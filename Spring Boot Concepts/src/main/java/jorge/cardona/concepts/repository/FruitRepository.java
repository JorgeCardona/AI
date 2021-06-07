package jorge.cardona.concepts.repository;

import jorge.cardona.concepts.entity.Fruit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FruitRepository extends MongoRepository<Fruit, String> {

}
