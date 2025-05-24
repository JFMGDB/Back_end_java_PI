package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.DisabilityTypeDTO;
import grupo05.inclusiveaid.entity.DisabilityType;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.DisabilityTypeMapper;
import grupo05.inclusiveaid.repository.DisabilityTypeRepository;
import grupo05.inclusiveaid.service.DisabilityTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * Implementação de DisabilityTypeService.
 */
@Service
@RequiredArgsConstructor
public class DisabilityTypeServiceImpl implements DisabilityTypeService {
  private final DisabilityTypeRepository repo;
  private final DisabilityTypeMapper mapper;

  @Override
  public DisabilityTypeDTO create(DisabilityTypeDTO dto) {
    return mapper.toDto(repo.save(mapper.toEntity(dto)));
  }

  @Override
  public DisabilityTypeDTO getById(Long id) {
    DisabilityType e = repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("DisabilityType não encontrado"));
    return mapper.toDto(e);
  }

  @Override
  public Page<DisabilityTypeDTO> listAll(int page,int size) {
    return repo.findAll(PageRequest.of(page,size)).map(mapper::toDto);
  }

  @Override
  public DisabilityTypeDTO update(Long id,DisabilityTypeDTO dto) {
    DisabilityType e = repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("DisabilityType não encontrado"));
    e.setName(dto.getName());
    e.setDescription(dto.getDescription());
    return mapper.toDto(repo.save(e));
  }

  @Override
  public void delete(Long id) {
    if (!repo.existsById(id))
      throw new ResourceNotFoundException("DisabilityType não encontrado");
    repo.deleteById(id);
  }
}