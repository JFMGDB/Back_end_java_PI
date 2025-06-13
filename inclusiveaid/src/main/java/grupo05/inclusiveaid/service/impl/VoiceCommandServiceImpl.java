package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.VoiceCommandDTO;
import grupo05.inclusiveaid.entity.VoiceCommand;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.VoiceCommandMapper;
import grupo05.inclusiveaid.repository.VoiceCommandRepository;
import grupo05.inclusiveaid.service.VoiceCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.Instant;

/**
 * Serviço responsável por gerenciar comandos de voz capturados durante as sessões.
 * <p>
 * Esta implementação oferece operações CRUD para {@link VoiceCommand}, garantindo
 * validação da existência de sessões, tratamento de exceções e conversão entre
 * entidade e DTO por meio do {@link VoiceCommandMapper}.
 */
@Service
@RequiredArgsConstructor
public class VoiceCommandServiceImpl implements VoiceCommandService {
  private final VoiceCommandRepository repo;
  private final VoiceCommandMapper mapper;

  /**
   * Cria um novo comando de voz associado a uma sessão existente.
   *
   * @param dto dados do comando de voz a serem persistidos
   * @return comando de voz criado em formato DTO
   * @throws ResourceNotFoundException caso a sessão indicada pelo ID não exista
   */
  @Override
  public VoiceCommandDTO create(VoiceCommandDTO dto) {
    dto.setTimestamp(Instant.now().toString());
    return mapper.toDto(repo.save(mapper.toEntity(dto)));
  }

  /**
   * Recupera um comando de voz pelo seu identificador.
   *
   * @param id identificador do comando de voz
   * @return DTO correspondente ao comando de voz encontrado
   * @throws ResourceNotFoundException caso nenhum comando seja encontrado
   */
  @Override
  public VoiceCommandDTO getById(Long id) {
    return mapper.toDto(repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("VoiceCommand não encontrado")));
  }

  /**
   * Lista comandos de voz de forma paginada.
   *
   * @param page número da página (0-based)
   * @param size quantidade de elementos por página
   * @return página contendo os comandos de voz convertidos em DTO
   */
  @Override
  public Page<VoiceCommandDTO> listAll(int page,int size) {
    return repo.findAll(PageRequest.of(page,size)).map(mapper::toDto);
  }

  /**
   * Remove um comando de voz pelo seu ID.
   *
   * @param id identificador do comando de voz a ser removido
   * @throws ResourceNotFoundException caso o ID não exista
   */
  @Override
  public void delete(Long id) {
    if (!repo.existsById(id))
      throw new ResourceNotFoundException("VoiceCommand não encontrado");
    repo.deleteById(id);
  }

  /**
   * Atualiza um comando de voz existente.
   *
   * @param id  identificador do comando a ser atualizado
   * @param dto dados que devem atualizar o comando existente
   * @return DTO do comando de voz já atualizado
   * @throws ResourceNotFoundException caso o comando não seja encontrado
   */
  @Override
  public VoiceCommandDTO update(Long id, VoiceCommandDTO dto) {
    VoiceCommand existingCommand = repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("VoiceCommand não encontrado"));
    
    mapper.updateEntity(dto, existingCommand);
    return mapper.toDto(repo.save(existingCommand));
  }
}