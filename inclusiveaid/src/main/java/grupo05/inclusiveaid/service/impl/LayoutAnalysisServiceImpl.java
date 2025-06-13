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
 * Serviço que processa análises de layout para avaliar acessibilidade de interfaces.
 * <p>
 * Oferece operações de criação, leitura, atualização, listagem e remoção das análises,
 * garantindo vínculo com o usuário que solicitou a avaliação.
 */
@Service
@RequiredArgsConstructor
public class LayoutAnalysisServiceImpl implements LayoutAnalysisService {
  private final LayoutAnalysisRepository repo;
  private final LayoutAnalysisMapper mapper;
  private final UserRepository userRepo;

  @Override
  /**
   * Cria uma nova análise de layout.
   *
   * @param dto dados da análise
   * @return análise criada em DTO
   * @throws ResourceNotFoundException caso o usuário associado não exista
   */
  public LayoutAnalysisDTO create(LayoutAnalysisDTO dto) {
    userRepo.findById(dto.getUserId())
      .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    return mapper.toDto(repo.save(mapper.toEntity(dto)));
  }

  @Override
  /**
   * Recupera análise pelo ID.
   *
   * @param id identificador da análise
   * @return DTO da análise encontrada
   * @throws ResourceNotFoundException caso a análise não exista
   */
  public LayoutAnalysisDTO getById(Long id) {
    return mapper.toDto(repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Layout Analysis not found")));
  }

  @Override
  /**
   * Lista análises de layout paginadas.
   *
   * @param page número da página
   * @param size tamanho da página
   * @return página contendo análises convertidas em DTO
   */
  public Page<LayoutAnalysisDTO> listAll(int page,int size) {
    return repo.findAll(PageRequest.of(page,size)).map(mapper::toDto);
  }

  @Override
  /**
   * Atualiza uma análise de layout existente.
   *
   * @param id  identificador da análise
   * @param dto novos dados
   * @return análise atualizada em DTO
   * @throws ResourceNotFoundException caso a análise ou usuário não exista
   */
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
  /**
   * Remove uma análise de layout pelo ID.
   *
   * @param id identificador da análise
   * @throws ResourceNotFoundException caso a análise não exista
   */
  public void delete(Long id) {
    if (!repo.existsById(id)) {
      throw new ResourceNotFoundException("Layout Analysis not found");
    }
    repo.deleteById(id);
  }
}