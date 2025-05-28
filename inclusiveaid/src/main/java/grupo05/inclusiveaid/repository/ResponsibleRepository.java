package grupo05.inclusiveaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import grupo05.inclusiveaid.entity.Responsible;

public interface ResponsibleRepository extends JpaRepository<Responsible, Long>{
    
}
