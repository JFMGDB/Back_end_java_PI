package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.AdaptationDTO;
import grupo05.inclusiveaid.entity.Adaptation;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.AdaptationMapper;
import grupo05.inclusiveaid.repository.AdaptationRepository;
import grupo05.inclusiveaid.service.AdaptationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviço responsável por gerenciar as adaptações de acessibilidade disponíveis no sistema.
 * <p>
 * Esta implementação provê operações CRUD sobre a entidade {@link Adaptation},
 * incluindo listagem paginada, criação, atualização, recuperação por ID e remoção.
 */
@Service
@RequiredArgsConstructor
public class AdaptationServiceImpl implements AdaptationService {
    private final AdaptationRepository adaptationRepository;
    private final AdaptationMapper adaptationMapper;

    /**
     * Lista adaptações de forma paginada.
     *
     * @param page número da página (0-based)
     * @param size tamanho da página
     * @return página contendo as adaptações convertidas em DTO
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AdaptationDTO> listAll(int page, int size) {
        return adaptationRepository.findAll(PageRequest.of(page, size))
                .map(adaptationMapper::toDto);
    }

    /**
     * Busca uma adaptação pelo seu identificador.
     *
     * @param id identificador da adaptação
     * @return DTO correspondente à adaptação encontrada
     * @throws ResourceNotFoundException caso nenhuma adaptação seja encontrada
     */
    @Override
    @Transactional(readOnly = true)
    public AdaptationDTO getById(Long id) {
        return adaptationRepository.findById(id)
                .map(adaptationMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Adaptação não encontrada com ID: " + id));
    }

    /**
     * Cria uma nova adaptação de acessibilidade.
     *
     * @param adaptationDTO dados da adaptação a ser persistida
     * @return adaptação criada em formato DTO
     */
    @Override
    @Transactional
    public AdaptationDTO create(AdaptationDTO adaptationDTO) {
        Adaptation adaptation = adaptationMapper.toEntity(adaptationDTO);
        Adaptation savedAdaptation = adaptationRepository.save(adaptation);
        return adaptationMapper.toDto(savedAdaptation);
    }

    /**
     * Atualiza uma adaptação existente.
     *
     * @param id identificador da adaptação a ser atualizada
     * @param adaptationDTO novos dados da adaptação
     * @return DTO da adaptação atualizada
     * @throws ResourceNotFoundException caso a adaptação não exista
     */
    @Override
    @Transactional
    public AdaptationDTO update(Long id, AdaptationDTO adaptationDTO) {
        Adaptation adaptation = adaptationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adaptação não encontrada com ID: " + id));
        adaptationMapper.updateEntity(adaptationDTO, adaptation);
        return adaptationMapper.toDto(adaptationRepository.save(adaptation));
    }

    /**
     * Remove uma adaptação do banco de dados.
     *
     * @param id identificador da adaptação a ser removida
     * @throws ResourceNotFoundException caso o ID não exista
     */
    @Override
    @Transactional
    public void delete(Long id) {
        if (!adaptationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Adaptação não encontrada com ID: " + id);
        }
        adaptationRepository.deleteById(id);
    }
}