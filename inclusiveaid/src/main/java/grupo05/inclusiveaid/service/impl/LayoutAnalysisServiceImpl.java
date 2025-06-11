package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.LayoutAnalysisDTO;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.LayoutAnalysisMapper;
import grupo05.inclusiveaid.repository.LayoutAnalysisRepository;
import grupo05.inclusiveaid.repository.UserRepository;
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
  private final UserRepository userRepo;

  @Override
  public LayoutAnalysisDTO create(LayoutAnalysisDTO dto) {
    userRepo.findById(dto.getUserId())
      .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return mapper.toDto(repo.save(mapper.toEntity(dto)));
  }

  @Override
  public LayoutAnalysisDTO getById(Long id) {
    return mapper.toDto(repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Layout Analysis not found")));
  }

  @Override
  public Page<LayoutAnalysisDTO> listAll(int page,int size) {
    return repo.findAll(PageRequest.of(page,size)).map(mapper::toDto);
  }

  @Override
  public LayoutAnalysisDTO update(Long id, LayoutAnalysisDTO dto) {
    if (!repo.existsById(id)) {
      throw new ResourceNotFoundException("Layout Analysis not found");
    }
    
    userRepo.findById(dto.getUserId())
      .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            
    var layoutAnalysis = mapper.toEntity(dto);
    layoutAnalysis.setId(id);
    return mapper.toDto(repo.save(layoutAnalysis));
  }

  @Override
  public void delete(Long id) {
    if (!repo.existsById(id)) {
      throw new ResourceNotFoundException("Layout Analysis not found");
    }
    repo.deleteById(id);
  }
}