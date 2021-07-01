package jorge.cardona.concepts.graphql.repository;

import jorge.cardona.concepts.graphql.entity.AnimalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends CrudRepository<AnimalEntity, Long> {}