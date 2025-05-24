package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.DisabilityTypeDTO;
import org.springframework.data.domain.Page;

/**
 * CRUD para DisabilityType.
 */
public interface DisabilityTypeService {
  DisabilityTypeDTO create(DisabilityTypeDTO dto);
  DisabilityTypeDTO getById(Long id);
  Page<DisabilityTypeDTO> listAll(int page,int size);
  DisabilityTypeDTO update(Long id,DisabilityTypeDTO dto);
  void delete(Long id);
}