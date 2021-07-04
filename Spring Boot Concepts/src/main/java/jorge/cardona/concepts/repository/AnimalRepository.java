package jorge.cardona.concepts.repository;

import jorge.cardona.concepts.entity.AnimalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends CrudRepository<AnimalEntity, Long> {}