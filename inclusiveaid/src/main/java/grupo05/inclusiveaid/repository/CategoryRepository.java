package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para Categoria.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}