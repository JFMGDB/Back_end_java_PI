package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.SuggestionDTO;
import org.springframework.data.domain.Page;

/**
 * CRUD para Suggestion.
 */
public interface SuggestionService {
  SuggestionDTO create(SuggestionDTO dto);
  SuggestionDTO getById(Long id);
  Page<SuggestionDTO> listAll(int page,int size);
  void delete(Long id);
}