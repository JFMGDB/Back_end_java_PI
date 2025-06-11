package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.PermissionsDTO;
import org.springframework.data.domain.Page;

/**
 * Interface que define os serviços relacionados às permissões de usuários.
 */
public interface PermissionsService {
    /**
     * Cria uma nova permissão.
     * @param dto DTO contendo os dados da permissão
     * @return DTO da permissão criada
     */
    PermissionsDTO create(PermissionsDTO dto);

    /**
     * Recupera uma permissão pelo seu ID.
     * @param id ID da permissão
     * @return DTO da permissão encontrada
     */
    PermissionsDTO getById(Long id);

    /**
     * Lista todas as permissões de forma paginada.
     * @param page Número da página (começa em 0)
     * @param size Tamanho da página
     * @return Página de DTOs de permissões
     */
    Page<PermissionsDTO> listAll(int page, int size);

    /**
     * Atualiza uma permissão existente.
     * @param id ID da permissão a ser atualizada
     * @param dto DTO contendo os novos dados da permissão
     * @return DTO da permissão atualizada
     */
    PermissionsDTO update(Long id, PermissionsDTO dto);

    /**
     * Remove uma permissão do sistema.
     * @param id ID da permissão a ser removida
     */
    void delete(Long id);
}
