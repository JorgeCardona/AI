package jorge.cardona.concepts.repository;

import jorge.cardona.concepts.entity.Nature;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NatureInterfaceRepository {


    List<Nature> getNatureList();

    Optional<Nature> getNatureById(UUID id);

    Nature saveNature(Nature nature);

    List<Nature> saveListNature(List<Nature> nature);
}
