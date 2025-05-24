package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para Session.
 */
public interface SessionRepository extends JpaRepository<Session, Long> {
}