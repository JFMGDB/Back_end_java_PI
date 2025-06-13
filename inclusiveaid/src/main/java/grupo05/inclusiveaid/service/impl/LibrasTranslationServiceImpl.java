package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.LibrasTranslationDTO;
import grupo05.inclusiveaid.entity.LibrasTranslation;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.LibrasTranslationMapper;
import grupo05.inclusiveaid.repository.LibrasTranslationRepository;
import grupo05.inclusiveaid.service.LibrasTranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviço responsável por gerenciar traduções em Libras.
 * <p>
 * Esta implementação encapsula a lógica de negócios relacionada às traduções de texto
 * para Libras (Língua Brasileira de Sinais), oferecendo métodos para criar,
 * atualizar, excluir, recuperar e listar traduções de forma paginada.
 * Todas as operações de escrita são executadas dentro de transações para garantir a
 * consistência dos dados.
 */
@Service
@RequiredArgsConstructor
public class LibrasTranslationServiceImpl implements LibrasTranslationService {

    private final LibrasTranslationRepository repository;
    private final LibrasTranslationMapper mapper;

    /**
     * Cria uma nova tradução em Libras.
     *
     * @param dto objeto contendo os dados da tradução a ser persistida
     * @return a tradução persistida convertida em DTO
     */
    @Override
    @Transactional
    public LibrasTranslationDTO create(LibrasTranslationDTO dto) {
        LibrasTranslation entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    /**
     * Atualiza uma tradução existente identificada pelo seu ID.
     *
     * @param id  identificador da tradução a ser atualizada
     * @param dto dados que devem sobrescrever os valores atuais
     * @return a tradução atualizada convertida em DTO
     * @throws ResourceNotFoundException caso nenhuma tradução seja encontrada com o ID fornecido
     */
    @Override
    @Transactional
    public LibrasTranslationDTO update(Long id, LibrasTranslationDTO dto) {
        LibrasTranslation entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tradução não encontrada com id: " + id));
        
        mapper.updateEntity(dto, entity);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    /**
     * Exclui uma tradução em Libras pelo seu ID.
     *
     * @param id identificador da tradução a ser removida
     * @throws ResourceNotFoundException caso o ID não exista no banco de dados
     */
    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tradução não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }

    /**
     * Recupera uma tradução em Libras pelo seu ID.
     *
     * @param id identificador da tradução
     * @return DTO correspondente à tradução encontrada
     * @throws ResourceNotFoundException caso nenhuma tradução seja encontrada
     */
    @Override
    public LibrasTranslationDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new ResourceNotFoundException("Tradução não encontrada com id: " + id));
    }

    /**
     * Lista todas as traduções em Libras de forma paginada.
     *
     * @param page número da página (0-based)
     * @param size quantidade de elementos por página
     * @return página contendo DTOs das traduções encontradas
     */
    @Override
    public Page<LibrasTranslationDTO> listAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
            .map(mapper::toDto);
    }
} 