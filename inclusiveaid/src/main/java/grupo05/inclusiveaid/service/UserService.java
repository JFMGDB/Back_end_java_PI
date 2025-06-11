package grupo05.inclusiveaid.service;

import grupo05.inclusiveaid.dto.UserDTO;
import org.springframework.data.domain.Page;

public interface UserService {
    UserDTO create(UserDTO dto);
    UserDTO getById(Long id);
    Page<UserDTO> listAll(int page, int size);
    UserDTO update(Long id, UserDTO dto);
    void delete(Long id);
}
