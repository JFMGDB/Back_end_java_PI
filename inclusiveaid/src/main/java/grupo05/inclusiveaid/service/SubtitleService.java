package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.SubtitleDTO;
import org.springframework.data.domain.Page;

/**
 * CRUD para Subtitle.
 */
public interface SubtitleService {
  SubtitleDTO create(SubtitleDTO dto);
  SubtitleDTO getById(Long id);
  Page<SubtitleDTO> listAll(int page,int size);
  void delete(Long id);
}