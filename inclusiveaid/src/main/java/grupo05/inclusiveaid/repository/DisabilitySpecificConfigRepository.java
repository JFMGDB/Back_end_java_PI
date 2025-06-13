package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.DisabilitySpecificConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório JPA para {@link DisabilitySpecificConfig}.
 */
@Repository
public interface DisabilitySpecificConfigRepository extends JpaRepository<DisabilitySpecificConfig, Long> {
} 