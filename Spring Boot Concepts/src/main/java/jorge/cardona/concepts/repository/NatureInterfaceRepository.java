package jorge.cardona.concepts.repository;

import jorge.cardona.concepts.entity.NatureEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NatureInterfaceRepository {

    List<NatureEntity> getNatureList();

    Optional<NatureEntity> getNatureById(UUID id);

    NatureEntity saveNature(NatureEntity natureEntity);

    List<NatureEntity> saveListNature(List<NatureEntity> natureEntity);
}
