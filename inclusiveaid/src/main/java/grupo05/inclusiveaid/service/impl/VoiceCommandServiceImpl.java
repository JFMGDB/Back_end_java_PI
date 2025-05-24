package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.VoiceCommandDTO;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.VoiceCommandMapper;
import grupo05.inclusiveaid.repository.SessionRepository;
import grupo05.inclusiveaid.repository.VoiceCommandRepository;
import grupo05.inclusiveaid.service.VoiceCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.Instant;

/**
 * Implementação de VoiceCommandService.
 */
@Service
@RequiredArgsConstructor
public class VoiceCommandServiceImpl implements VoiceCommandService {
  private final VoiceCommandRepository repo;
  private final VoiceCommandMapper mapper;
  private final SessionRepository sessionRepo;

  @Override
  public VoiceCommandDTO create(VoiceCommandDTO dto) {
    sessionRepo.findById(dto.getSessionId())
      .orElseThrow(() -> new ResourceNotFoundException("Session não encontrada"));
    dto.setTimestamp(Instant.now());
    return mapper.toDto(repo.save(mapper.toEntity(dto)));
  }

  @Override
  public VoiceCommandDTO getById(Long id) {
    return mapper.toDto(repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("VoiceCommand não encontrado")));
  }

  @Override
  public Page<VoiceCommandDTO> listAll(int page,int size) {
    return repo.findAll(PageRequest.of(page,size)).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    if (!repo.existsById(id))
      throw new ResourceNotFoundException("VoiceCommand não encontrado");
    repo.deleteById(id);
  }
}