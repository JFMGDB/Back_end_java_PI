package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.Adaptation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para Adaptation.
 */
public interface AdaptationRepository extends JpaRepository<Adaptation, Long> {
}