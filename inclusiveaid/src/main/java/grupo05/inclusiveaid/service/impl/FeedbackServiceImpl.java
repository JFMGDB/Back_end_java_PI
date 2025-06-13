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
 * Serviço responsável pelo gerenciamento de feedbacks submetidos pelos usuários.
 */
@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
  private final FeedbackRepository repo;
  private final FeedbackMapper mapper;
  private final UserRepository userRepo;

  /**
   * Cria um novo feedback para um usuário existente.
   *
   * @param dto dados do feedback
   * @return feedback criado em DTO
   * @throws ResourceNotFoundException caso o usuário não exista
   */
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
  public FeedbackDTO update(Long id, FeedbackDTO dto) {
    var existingFeedback = repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Feedback não encontrado"));
    
    // garante que usuário existe
    userRepo.findById(dto.getUserId())
      .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    
    var feedback = mapper.toEntity(dto);
    feedback.setId(id);
    feedback.setTimestamp(existingFeedback.getTimestamp()); // mantém timestamp original
    
    return mapper.toDto(repo.save(feedback));
  }

  @Override
  public void delete(Long id) {
    if (!repo.existsById(id))
      throw new ResourceNotFoundException("Feedback não encontrado");
    repo.deleteById(id);
  }
}