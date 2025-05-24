package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.UserDTO;
import grupo05.inclusiveaid.entity.Role;
import grupo05.inclusiveaid.entity.User;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.UserMapper;
import grupo05.inclusiveaid.repository.RoleRepository;
import grupo05.inclusiveaid.repository.UserRepository;
import grupo05.inclusiveaid.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementação de UserService: CRUD de usuários.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository repo;
  private final RoleRepository roleRepo;
  private final UserMapper mapper;
  private final PasswordEncoder encoder;

  @Override @Transactional
  public UserDTO create(UserDTO dto) {
    // Converte DTO em entidade
    User user = mapper.toEntity(dto);
    // Criptografa senha antes de salvar
    user.setPassword(encoder.encode(dto.getPassword()));
    // Associa Role existente
    Role role = roleRepo.findById(dto.getRoleId())
      .orElseThrow(() -> new ResourceNotFoundException("Role não encontrada"));
    user.setRole(role);
    // Persiste e retorna DTO
    return mapper.toDto(repo.save(user));
  }

  @Override
  public UserDTO getById(Long id) {
    User u = repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    return mapper.toDto(u);
  }

  @Override
  public Page<UserDTO> listAll(int page, int size) {
    return repo.findAll(PageRequest.of(page, size))
               .map(mapper::toDto);
  }

  @Override @Transactional
  public UserDTO update(Long id, UserDTO dto) {
    User u = repo.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    u.setName(dto.getName());
    u.setEmail(dto.getEmail());
    if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
      u.setPassword(encoder.encode(dto.getPassword()));
    }
    return mapper.toDto(repo.save(u));
  }

  @Override
  public void delete(Long id) {
    if (!repo.existsById(id)) {
      throw new ResourceNotFoundException("Usuário não encontrado");
    }
    repo.deleteById(id);
  }
}