package grupo05.inclusiveaid.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import grupo05.inclusiveaid.dto.PermissionsDTO;
import grupo05.inclusiveaid.entity.Permissions;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.PermissionsMapper;
import grupo05.inclusiveaid.repository.PermissionsRepository;
import grupo05.inclusiveaid.service.PermissionsService;
import lombok.RequiredArgsConstructor;

/**
 * Serviço responsável pela gestão de permissões de acesso do sistema.
 * <p>
 * Possibilita criar, consultar, atualizar, remover e listar permissões garantindo
 * integridade dos dados por meio de validações e tratamento de exceções.
 */
@Service
@RequiredArgsConstructor
public class PermissionsServiceImpl implements PermissionsService {
    private final PermissionsRepository repository;
    private final PermissionsMapper mapper;
    
    /**
     * Cria uma nova permissão.
     *
     * @param dto dados da permissão
     * @return permissão criada em DTO
     */
    @Override
    @Transactional
    public PermissionsDTO create(PermissionsDTO dto) {
        // Validação básica
        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da permissão é obrigatório");
        }
        if (dto.getDescription() == null || dto.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição da permissão é obrigatória");
        }
        if (dto.getActive() == null) {
            dto.setActive(true); // Define como ativo por padrão
        }

        // Converte DTO para entidade e salva
        Permissions entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDTO(entity);
    }
    
    /**
     * Obtém detalhes de uma permissão pelo ID.
     *
     * @param id identificador da permissão
     * @return DTO da permissão
     * @throws ResourceNotFoundException caso a permissão não exista
     */
    @Override
    @Transactional(readOnly = true)
    public PermissionsDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDTO)
            .orElseThrow(() -> new ResourceNotFoundException("Permissão não encontrada com ID: " + id));
    }
    
    /**
     * Lista permissões de forma paginada.
     *
     * @param page número da página
     * @param size tamanho da página
     * @return página contendo permissões
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PermissionsDTO> listAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
            .map(mapper::toDTO);
    }
    
    /**
     * Atualiza uma permissão existente.
     *
     * @param id  identificador da permissão
     * @param dto novos dados
     * @return permissão atualizada em DTO
     */
    @Override
    @Transactional
    public PermissionsDTO update(Long id, PermissionsDTO dto) {
        // Verifica se a permissão existe
        Permissions existingPermission = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Permissão não encontrada com ID: " + id));

        // Validação básica
        if (dto.getName() != null && dto.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da permissão não pode ser vazio");
        }
        if (dto.getDescription() != null && dto.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição da permissão não pode ser vazia");
        }

        // Atualiza a entidade existente com os novos dados
        mapper.updateEntityFromDTO(dto, existingPermission);
        existingPermission = repository.save(existingPermission);
        return mapper.toDTO(existingPermission);
    }
    
    /**
     * Remove uma permissão pelo ID.
     *
     * @param id identificador da permissão
     * @throws ResourceNotFoundException caso a permissão não exista
     */
    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Permissão não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }
}
