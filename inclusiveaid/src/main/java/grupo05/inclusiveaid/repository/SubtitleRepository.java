package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.Subtitle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para Subtitle.
 */
public interface SubtitleRepository extends JpaRepository<Subtitle, Long> {
}