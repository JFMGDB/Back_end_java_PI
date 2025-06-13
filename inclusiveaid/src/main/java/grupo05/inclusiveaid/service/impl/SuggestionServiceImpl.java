package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.SuggestionDTO;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.SuggestionMapper;
import grupo05.inclusiveaid.repository.LayoutAnalysisRepository;
import grupo05.inclusiveaid.repository.SuggestionRepository;
import grupo05.inclusiveaid.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por gerenciar sugestões derivadas de análises de layout.
 * <p>
 * Realiza operações de criação, leitura, atualização, exclusão e listagem paginada
 * das entidades {@code Suggestion}, garantindo a consistência das referências
 * com {@code LayoutAnalysis}.
 */
@Service
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService {
  private final SuggestionRepository repo;
  private final SuggestionMapper mapper;
  private final LayoutAnalysisRepository laRepo;

  /**
   * Cria uma nova sugestão vinculada a uma análise de layout existente.
   *
   * @param dto dados da sugestão
   * @return sugestão criada convertida em DTO
   * @throws ResourceNotFoundException caso a análise de layout não exista
   */
  @Override
  public SuggestionDTO create(SuggestionDTO dto) {
    laRepo.findById(dto.getLayoutAnalysisId())
      .orElseThrow(() -> new ResourceNotFoundException("LayoutAnalysis não encontrada"));
    return mapper.toDto(repo.save(mapper.toEntity(dto)));
  }

  /**
   * Recupera uma sugestão pelo ID.
   *
   * @param id identificador da sugestão
   * @return DTO da sugestão encontrada
   * @throws ResourceNotFoundException caso a sugestão não exista
   */
  @Override
  public SuggestionDTO getById(Long id) {
    return mapper.toDto(repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Suggestion não encontrada")));
  }

  /**
   * Lista todas as sugestões de forma paginada.
   *
   * @param page número da página
   * @param size tamanho da página
   * @return página com sugestões convertidas em DTO
   */
  @Override
  public Page<SuggestionDTO> listAll(int page,int size) {
    return repo.findAll(PageRequest.of(page,size)).map(mapper::toDto);
  }

  /**
   * Atualiza uma sugestão existente.
   *
   * @param id  identificador da sugestão
   * @param dto novos dados da sugestão
   * @return sugestão atualizada em DTO
   * @throws ResourceNotFoundException caso a sugestão ou análise de layout não exista
   */
  @Override
  public SuggestionDTO update(Long id, SuggestionDTO dto) {
    var suggestion = repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Suggestion não encontrada"));
    
    if (dto.getLayoutAnalysisId() != null) {
      laRepo.findById(dto.getLayoutAnalysisId())
        .orElseThrow(() -> new ResourceNotFoundException("LayoutAnalysis não encontrada"));
    }
    
    suggestion.setMessage(dto.getMessage());
    if (dto.getLayoutAnalysisId() != null) {
      suggestion.getAnalysis().setId(dto.getLayoutAnalysisId());
    }
    
    return mapper.toDto(repo.save(suggestion));
  }

  /**
   * Remove uma sugestão pelo seu ID.
   *
   * @param id identificador da sugestão
   * @throws ResourceNotFoundException caso a sugestão não exista
   */
  @Override
  public void delete(Long id) {
    if (!repo.existsById(id))
      throw new ResourceNotFoundException("Suggestion não encontrada");
    repo.deleteById(id);
  }
}