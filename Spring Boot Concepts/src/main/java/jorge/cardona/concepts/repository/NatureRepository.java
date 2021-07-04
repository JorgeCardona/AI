package jorge.cardona.concepts.repository;

import jorge.cardona.concepts.entity.NatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NatureRepository extends JpaRepository<NatureEntity, UUID> {}
