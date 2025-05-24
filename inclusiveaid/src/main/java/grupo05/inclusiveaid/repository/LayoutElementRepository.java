package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.LayoutElement;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para LayoutElement.
 */
public interface LayoutElementRepository extends JpaRepository<LayoutElement, Long> {
}