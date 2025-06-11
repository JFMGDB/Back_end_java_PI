package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.AgentInteraction;
import grupo05.inclusiveaid.enums.InteractionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgentInteractionRepository extends JpaRepository<AgentInteraction, Long> {
    List<AgentInteraction> findByAgentIdAndUserId(Long agentId, Long userId);
    List<AgentInteraction> findByTypeAndSuccessful(InteractionType type, boolean successful);
    List<AgentInteraction> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
} 