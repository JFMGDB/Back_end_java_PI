package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.LibrasTranslationDTO;
import org.springframework.data.domain.Page;

/**
 * Interface para operações de serviço relacionadas a traduções em Libras.
 */
public interface LibrasTranslationService {
    /**
     * Cria uma nova tradução em Libras.
     * @param dto DTO com os dados da tradução
     * @return DTO da tradução criada
     */
    LibrasTranslationDTO create(LibrasTranslationDTO dto);

    /**
     * Atualiza uma tradução em Libras existente.
     * @param id ID da tradução
     * @param dto DTO com os novos dados
     * @return DTO da tradução atualizada
     */
    LibrasTranslationDTO update(Long id, LibrasTranslationDTO dto);

    /**
     * Deleta uma tradução em Libras.
     * @param id ID da tradução
     */
    void delete(Long id);

    /**
     * Busca uma tradução em Libras pelo ID.
     * @param id ID da tradução
     * @return DTO da tradução encontrada
     */
    LibrasTranslationDTO getById(Long id);

    /**
     * Lista todas as traduções em Libras com paginação.
     * @param page Número da página (0-based)
     * @param size Tamanho da página
     * @return Página de DTOs de traduções
     */
    Page<LibrasTranslationDTO> listAll(int page, int size);
} 