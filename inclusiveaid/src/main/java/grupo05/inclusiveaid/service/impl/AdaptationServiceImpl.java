package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.AdaptationDTO;
import grupo05.inclusiveaid.entity.Adaptation;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.AdaptationMapper;
import grupo05.inclusiveaid.repository.AdaptationRepository;
import grupo05.inclusiveaid.repository.DisabilityTypeRepository;
import grupo05.inclusiveaid.service.AdaptationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * Implementação de AdaptationService.
 */
@Service
@RequiredArgsConstructor
public class AdaptationServiceImpl implements AdaptationService {
  private final AdaptationRepository repo;
  private final AdaptationMapper mapper;
  private final DisabilityTypeRepository dtRepo;

  @Override
  public AdaptationDTO create(AdaptationDTO dto) {
    // garante que disabilityType existe
    dtRepo.findById(dto.getDisabilityTypeId())
      .orElseThrow(() -> new ResourceNotFoundException("DisabilityType não encontrado"));
    return mapper.toDto(repo.save(mapper.toEntity(dto)));
  }

  @Override
  public AdaptationDTO getById(Long id) {
    return mapper.toDto(repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Adaptation não encontrado")));
  }

  @Override
  public Page<AdaptationDTO> listAll(int page,int size) {
    return repo.findAll(PageRequest.of(page,size)).map(mapper::toDto);
  }

  @Override
  public AdaptationDTO update(Long id,AdaptationDTO dto) {
    Adaptation e = repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Adaptation não encontrado"));
    e.setName(dto.getName());
    e.setDescription(dto.getDescription());
    return mapper.toDto(repo.save(e));
  }

  @Override
  public void delete(Long id) {
    if (!repo.existsById(id))
      throw new ResourceNotFoundException("Adaptation não encontrado");
    repo.deleteById(id);
  }
}