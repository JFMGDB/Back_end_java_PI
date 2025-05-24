package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.VoiceCommand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para VoiceCommand.
 */
public interface VoiceCommandRepository extends JpaRepository<VoiceCommand, Long> {
}