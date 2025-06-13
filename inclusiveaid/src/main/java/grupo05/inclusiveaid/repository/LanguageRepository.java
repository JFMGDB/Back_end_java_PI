package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para Language.
 */
@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}