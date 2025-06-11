package grupo05.inclusiveaid.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import grupo05.inclusiveaid.entity.Permissions;

/**
 * Repositório para operações de persistência relacionadas às permissões.
 */
public interface PermissionsRepository extends JpaRepository<Permissions, Long> {
    /**
     * Verifica se existe uma permissão com o nome especificado (ignorando maiúsculas/minúsculas).
     * @param name Nome da permissão
     * @return true se existir, false caso contrário
     */
    boolean existsByNameIgnoreCase(String name);

    /**
     * Busca permissões que contenham o nome especificado (ignorando maiúsculas/minúsculas).
     * @param name Nome da permissão (parcial)
     * @param pageable Configuração de paginação
     * @return Página de permissões encontradas
     */
    Page<Permissions> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
