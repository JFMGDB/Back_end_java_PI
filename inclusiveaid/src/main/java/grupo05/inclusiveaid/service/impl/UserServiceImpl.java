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
 * Implementação do serviço de usuários.
 * Contém a lógica de negócio para operações relacionadas aos usuários.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public UserDTO create(UserDTO dto) {
    User user = userMapper.toEntity(dto);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userMapper.toDto(userRepository.save(user));
  }

  @Override
  public UserDTO getById(Long id) {
    return userMapper.toDto(userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found")));
  }

  @Override
  public Page<UserDTO> listAll(int page, int size) {
    return userRepository.findAll(PageRequest.of(page, size))
        .map(userMapper::toDto);
  }

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

  @Override
  @Transactional
  public void delete(Long id) {
    if (!userRepository.existsById(id)) {
        throw new ResourceNotFoundException("User not found");
    }
    userRepository.deleteById(id);
  }
}