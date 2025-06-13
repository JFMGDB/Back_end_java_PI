package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.UserDTO;
import grupo05.inclusiveaid.entity.User;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.UserMapper;
import grupo05.inclusiveaid.repository.UserRepository;
import grupo05.inclusiveaid.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviço responsável pelo gerenciamento de usuários.
 * <p>
 * Provê operações CRUD, controle de senha (com hashing) e listagem paginada,
 * utilizando {@link UserMapper} para conversão entre entidade e DTO.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  /**
   * Cria um novo usuário no sistema.
   *
   * @param dto dados do usuário a ser criado
   * @return usuário criado convertido em DTO
   */
  @Override
  @Transactional
  public UserDTO create(UserDTO dto) {
    User user = userMapper.toEntity(dto);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userMapper.toDto(userRepository.save(user));
  }

  /**
   * Recupera um usuário pelo seu ID.
   *
   * @param id identificador do usuário
   * @return DTO do usuário encontrado
   * @throws ResourceNotFoundException caso o usuário não exista
   */
  @Override
  public UserDTO getById(Long id) {
    return userMapper.toDto(userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found")));
  }

  /**
   * Lista usuários de forma paginada.
   *
   * @param page número da página
   * @param size tamanho da página
   * @return página contendo usuários convertidos em DTO
   */
  @Override
  public Page<UserDTO> listAll(int page, int size) {
    return userRepository.findAll(PageRequest.of(page, size))
        .map(userMapper::toDto);
  }

  /**
   * Atualiza um usuário existente.
   *
   * @param id  identificador do usuário
   * @param dto novos dados do usuário
   * @return usuário atualizado em DTO
   * @throws ResourceNotFoundException caso o usuário não exista
   */
  @Override
  @Transactional
  public UserDTO update(Long id, UserDTO dto) {
    User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    
    User user = userMapper.toEntity(dto);
    user.setId(id);
    
    if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
    } else {
        user.setPassword(existingUser.getPassword());
    }
    
    return userMapper.toDto(userRepository.save(user));
  }

  /**
   * Remove um usuário pelo ID.
   *
   * @param id identificador do usuário
   * @throws ResourceNotFoundException caso o usuário não exista
   */
  @Override
  @Transactional
  public void delete(Long id) {
    if (!userRepository.existsById(id)) {
        throw new ResourceNotFoundException("User not found");
    }
    userRepository.deleteById(id);
  }
}