package jorge.cardona.concepts.repository;

import jorge.cardona.concepts.entity.Nature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NatureRepository extends JpaRepository<Nature, UUID> {
}
