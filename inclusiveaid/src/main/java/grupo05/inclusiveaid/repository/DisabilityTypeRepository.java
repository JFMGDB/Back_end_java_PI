package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.DisabilityType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para DisabilityType.
 */
public interface DisabilityTypeRepository extends JpaRepository<DisabilityType, Long> {
}