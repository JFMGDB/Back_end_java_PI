package grupo05.inclusiveaid.repository;

import grupo05.inclusiveaid.entity.LibrasTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para operações de persistência de traduções em Libras.
 */
@Repository
public interface LibrasTranslationRepository extends JpaRepository<LibrasTranslation, Long> {
    // Métodos personalizados podem ser adicionados aqui se necessário
} 