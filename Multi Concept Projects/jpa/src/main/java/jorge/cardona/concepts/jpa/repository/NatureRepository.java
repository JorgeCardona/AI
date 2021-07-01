package jorge.cardona.concepts.jpa.repository;

import jorge.cardona.concepts.jpa.entity.NatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NatureRepository extends JpaRepository<NatureEntity, UUID> {}