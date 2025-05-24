package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.VoiceCommandDTO;
import org.springframework.data.domain.Page;

/**
 * CRUD para VoiceCommand.
 */
public interface VoiceCommandService {
  VoiceCommandDTO create(VoiceCommandDTO dto);
  VoiceCommandDTO getById(Long id);
  Page<VoiceCommandDTO> listAll(int page,int size);
  void delete(Long id);
}