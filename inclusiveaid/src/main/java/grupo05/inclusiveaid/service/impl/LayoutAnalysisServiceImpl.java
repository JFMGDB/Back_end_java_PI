package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.LayoutAnalysisDTO;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.LayoutAnalysisMapper;
import grupo05.inclusiveaid.repository.LayoutAnalysisRepository;
import grupo05.inclusiveaid.repository.SessionRepository;
import grupo05.inclusiveaid.service.LayoutAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * Implementação de LayoutAnalysisService.
 */
@Service
@RequiredArgsConstructor
public class LayoutAnalysisServiceImpl implements LayoutAnalysisService {
  private final LayoutAnalysisRepository repo;
  private final LayoutAnalysisMapper mapper;
  private final SessionRepository sessionRepo;

  @Override
  public LayoutAnalysisDTO create(LayoutAnalysisDTO dto) {
    sessionRepo.findById(dto.getSessionId())
      .orElseThrow(() -> new ResourceNotFoundException("Session não encontrada"));
    return mapper.toDto(repo.save(mapper.toEntity(dto)));
  }

  @Override
  public LayoutAnalysisDTO getById(Long id) {
    return mapper.toDto(repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("LayoutAnalysis não encontrada")));
  }

  @Override
  public Page<LayoutAnalysisDTO> listAll(int page,int size) {
    return repo.findAll(PageRequest.of(page,size)).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    if (!repo.existsById(id))
      throw new ResourceNotFoundException("LayoutAnalysis não encontrada");
    repo.deleteById(id);
  }
}