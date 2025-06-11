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
 * Implementação de SuggestionService.
 */
@Service
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService {
  private final SuggestionRepository repo;
  private final SuggestionMapper mapper;
  private final LayoutAnalysisRepository laRepo;

  @Override
  public SuggestionDTO create(SuggestionDTO dto) {
    laRepo.findById(dto.getLayoutAnalysisId())
      .orElseThrow(() -> new ResourceNotFoundException("LayoutAnalysis não encontrada"));
    return mapper.toDto(repo.save(mapper.toEntity(dto)));
  }

  @Override
  public SuggestionDTO getById(Long id) {
    return mapper.toDto(repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Suggestion não encontrada")));
  }

  @Override
  public Page<SuggestionDTO> listAll(int page,int size) {
    return repo.findAll(PageRequest.of(page,size)).map(mapper::toDto);
  }

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

  @Override
  public void delete(Long id) {
    if (!repo.existsById(id))
      throw new ResourceNotFoundException("Suggestion não encontrada");
    repo.deleteById(id);
  }
}