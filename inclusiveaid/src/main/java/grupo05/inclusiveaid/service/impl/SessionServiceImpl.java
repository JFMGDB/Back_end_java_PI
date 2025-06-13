package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.SessionDTO;
import grupo05.inclusiveaid.entity.Session;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.SessionMapper;
import grupo05.inclusiveaid.repository.SessionRepository;
import grupo05.inclusiveaid.repository.UserRepository;
import grupo05.inclusiveaid.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.Instant;

/**
 * Serviço que gerencia sessões de uso do sistema, incluindo seu ciclo de vida (início e término).
 */
@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {
  private final SessionRepository repo;
  private final SessionMapper mapper;
  private final UserRepository userRepo;

  @Override
  public SessionDTO create(SessionDTO dto) {
    userRepo.findById(dto.getUserId())
      .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    dto.setStartTime(Instant.now().toString());
    return mapper.toDto(repo.save(mapper.toEntity(dto)));
  }

  @Override
  public SessionDTO getById(Long id) {
    return mapper.toDto(repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Session não encontrada")));
  }

  @Override
  public Page<SessionDTO> listAll(int page,int size) {
    return repo.findAll(PageRequest.of(page,size)).map(mapper::toDto);
  }

  @Override
  public SessionDTO update(Long id,SessionDTO dto) {
    if (!repo.existsById(id))
      throw new ResourceNotFoundException("Session não encontrada");
    dto.setEndTime(Instant.now().toString());
    Session updated = mapper.toEntity(dto);
    updated.setId(id);
    return mapper.toDto(repo.save(updated));
  }

  @Override
  public void delete(Long id) {
    if (!repo.existsById(id))
      throw new ResourceNotFoundException("Session não encontrada");
    repo.deleteById(id);
  }
}