package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.FeedbackDTO;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.FeedbackMapper;
import grupo05.inclusiveaid.repository.FeedbackRepository;
import grupo05.inclusiveaid.repository.UserRepository;
import grupo05.inclusiveaid.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.Instant;

/**
 * Implementação de FeedbackService.
 */
@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
  private final FeedbackRepository repo;
  private final FeedbackMapper mapper;
  private final UserRepository userRepo;

  @Override
  public FeedbackDTO create(FeedbackDTO dto) {
    // garante que usuário existe
    userRepo.findById(dto.getUserId())
      .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    dto.setTimestamp(Instant.now());
    return mapper.toDto(repo.save(mapper.toEntity(dto)));
  }

  @Override
  public FeedbackDTO getById(Long id) {
    return mapper.toDto(repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Feedback não encontrado")));
  }

  @Override
  public Page<FeedbackDTO> listAll(int page,int size) {
    return repo.findAll(PageRequest.of(page,size)).map(mapper::toDto);
  }

  @Override
  public void delete(Long id) {
    if (!repo.existsById(id))
      throw new ResourceNotFoundException("Feedback não encontrado");
    repo.deleteById(id);
  }
}