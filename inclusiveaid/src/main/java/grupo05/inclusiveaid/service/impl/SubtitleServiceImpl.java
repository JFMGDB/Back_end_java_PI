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
 * Serviço responsável pelo gerenciamento de legendas geradas para sessões de usuário.
 * <p>
 * Permite criar, atualizar, excluir e consultar legendas, além de listá-las de forma paginada.
 */
@Service
@RequiredArgsConstructor
public class SubtitleServiceImpl implements SubtitleService {
  private final SubtitleRepository repo;
  private final SubtitleMapper mapper;
  private final SessionRepository sessionRepo;

  /**
   * Cria uma nova legenda vinculada a uma sessão existente.
   *
   * @param dto dados da legenda a ser criada
   * @return legenda criada em formato DTO
   * @throws ResourceNotFoundException caso a sessão não exista
   */
  @Override
  public SubtitleDTO create(SubtitleDTO dto) {
    sessionRepo.findById(dto.getSessionId())
      .orElseThrow(() -> new ResourceNotFoundException("Session não encontrada"));
    dto.setTimestamp(Instant.now());
    return mapper.toDto(repo.save(mapper.toEntity(dto)));
  }

  /**
   * Recupera uma legenda pelo seu identificador.
   *
   * @param id identificador da legenda
   * @return DTO da legenda encontrada
   * @throws ResourceNotFoundException caso a legenda não exista
   */
  @Override
  public SubtitleDTO getById(Long id) {
    return mapper.toDto(repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Subtitle não encontrada")));
  }

  /**
   * Lista legendas de forma paginada.
   *
   * @param page número da página
   * @param size tamanho da página
   * @return página contendo legendas convertidas em DTO
   */
  @Override
  public Page<SubtitleDTO> listAll(int page,int size) {
    return repo.findAll(PageRequest.of(page,size)).map(mapper::toDto);
  }

  /**
   * Atualiza o texto de uma legenda existente.
   *
   * @param id  identificador da legenda a ser atualizada
   * @param dto dados contendo o novo texto da legenda
   * @return legenda atualizada convertida em DTO
   * @throws ResourceNotFoundException caso a legenda não exista
   */
  @Override
  public SubtitleDTO update(Long id, SubtitleDTO dto) {
    var subtitle = repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Subtitle não encontrada"));
    
    subtitle.setText(dto.getText());
    // Não atualizamos o timestamp pois é um campo que deve manter o valor original
    
    return mapper.toDto(repo.save(subtitle));
  }

  /**
   * Remove uma legenda pelo seu ID.
   *
   * @param id identificador da legenda
   * @throws ResourceNotFoundException caso a legenda não exista
   */
  @Override
  public void delete(Long id) {
    if (!repo.existsById(id))
      throw new ResourceNotFoundException("Subtitle não encontrada");
    repo.deleteById(id);
  }
}