package grupo05.inclusiveaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import grupo05.inclusiveaid.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
