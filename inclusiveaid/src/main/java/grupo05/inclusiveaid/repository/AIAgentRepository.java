package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.AIAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AIAgentRepository extends JpaRepository<AIAgent, Long> {
    List<AIAgent> findByIsActive(boolean isActive);
    List<AIAgent> findByActiveUsersId(Long userId);
} 