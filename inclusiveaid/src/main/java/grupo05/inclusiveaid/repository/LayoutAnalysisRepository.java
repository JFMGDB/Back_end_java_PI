package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.LayoutAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para LayoutAnalysis.
 */
public interface LayoutAnalysisRepository extends JpaRepository<LayoutAnalysis, Long> {
}