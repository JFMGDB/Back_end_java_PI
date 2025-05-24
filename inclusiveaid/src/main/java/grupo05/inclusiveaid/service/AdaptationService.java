package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.AdaptationDTO;
import org.springframework.data.domain.Page;

/**
 * CRUD para Adaptation.
 */
public interface AdaptationService {
  AdaptationDTO create(AdaptationDTO dto);
  AdaptationDTO getById(Long id);
  Page<AdaptationDTO> listAll(int page,int size);
  AdaptationDTO update(Long id,AdaptationDTO dto);
  void delete(Long id);
}