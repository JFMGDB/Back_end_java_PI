package grupo05.inclusiveaid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import grupo05.inclusiveaid.entity.Permissions;


public interface PermissionsRepository extends JpaRepository<Permissions, Long>{
    
}
