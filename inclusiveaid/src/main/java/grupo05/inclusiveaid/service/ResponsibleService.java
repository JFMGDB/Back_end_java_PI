package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.ResponsibleDTO;
import java.util.List;

public interface ResponsibleService {
    List<ResponsibleDTO> getAllResponsibles();
    ResponsibleDTO getResponsibleById(Long id);
    ResponsibleDTO createResponsible(ResponsibleDTO responsibleDTO);
    ResponsibleDTO updateResponsible(Long id, ResponsibleDTO responsibleDTO);
    void deleteResponsible(Long id);
}