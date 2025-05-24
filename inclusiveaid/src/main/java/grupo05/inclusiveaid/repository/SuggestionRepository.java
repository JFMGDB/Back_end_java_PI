package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para Suggestion.
 */
public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
}