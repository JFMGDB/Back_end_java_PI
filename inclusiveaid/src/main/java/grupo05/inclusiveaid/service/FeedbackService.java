package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.FeedbackDTO;
import org.springframework.data.domain.Page;

/**
 * CRUD para Feedback.
 */
public interface FeedbackService {
  FeedbackDTO create(FeedbackDTO dto);
  FeedbackDTO getById(Long id);
  Page<FeedbackDTO> listAll(int page,int size);
  void delete(Long id);
}