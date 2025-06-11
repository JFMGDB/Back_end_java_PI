package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.LayoutAnalysisDTO;
import org.springframework.data.domain.Page;

/**
 * CRUD para LayoutAnalysis.
 */
public interface LayoutAnalysisService {
  LayoutAnalysisDTO create(LayoutAnalysisDTO dto);
  LayoutAnalysisDTO getById(Long id);
  Page<LayoutAnalysisDTO> listAll(int page,int size);
  LayoutAnalysisDTO update(Long id, LayoutAnalysisDTO dto);
  void delete(Long id);
}