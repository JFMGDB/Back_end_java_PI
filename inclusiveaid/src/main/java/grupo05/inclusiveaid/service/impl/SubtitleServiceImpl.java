package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.SubtitleDTO;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.SubtitleMapper;
import grupo05.inclusiveaid.repository.SessionRepository;
import grupo05.inclusiveaid.repository.SubtitleRepository;
import grupo05.inclusiveaid.service.SubtitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.Instant;
/**
 * Implementação de SubtitleService.
 */
@Service
@RequiredArgsConstructor
public class SubtitleServiceImpl implements SubtitleService {
  private final SubtitleRepository repo;
  private final SubtitleMapper mapper;
  private final SessionRepository sessionRepo;

  @Override
  public SubtitleDTO create(SubtitleDTO dto) {
    sessionRepo.findById(dto.getSessionId())
      .orElseThrow(() -> new ResourceNotFoundException("Session não encontrada"));
    dto.setTimestamp(Instant.now());
    return mapper.toDto(repo.save(mapper.toEntity(dto)));
  }

  @Override
  public SubtitleDTO getById(Long id) {
    return mapper.toDto(repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Subtitle não encontrada")));
  }

  @Override
  public Page<SubtitleDTO> listAll(int page,int size) {
    return repo.findAll(PageRequest.of(page,size)).map(mapper::toDto);
  }

  @Override
  public SubtitleDTO update(Long id, SubtitleDTO dto) {
    var subtitle = repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Subtitle não encontrada"));
    
    subtitle.setText(dto.getText());
    // Não atualizamos o timestamp pois é um campo que deve manter o valor original
    
    return mapper.toDto(repo.save(subtitle));
  }

  @Override
  public void delete(Long id) {
    if (!repo.existsById(id))
      throw new ResourceNotFoundException("Subtitle não encontrada");
    repo.deleteById(id);
  }
}