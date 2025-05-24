package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para Feedback.
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}