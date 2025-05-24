package grupo05.inclusiveaid.service;


import grupo05.inclusiveaid.dto.SessionDTO;
import org.springframework.data.domain.Page;

/**
 * CRUD para Session.
 */
public interface SessionService {
  SessionDTO create(SessionDTO dto);
  SessionDTO getById(Long id);
  Page<SessionDTO> listAll(int page,int size);
  SessionDTO update(Long id,SessionDTO dto);
  void delete(Long id);
}