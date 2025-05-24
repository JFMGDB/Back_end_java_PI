package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para Role.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}