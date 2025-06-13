package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.ResponsibleDTO;
import grupo05.inclusiveaid.entity.Responsible;
import grupo05.inclusiveaid.entity.User;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.ResponsibleMapper;
import grupo05.inclusiveaid.repository.ResponsibleRepository;
import grupo05.inclusiveaid.repository.UserRepository;
import grupo05.inclusiveaid.service.ResponsibleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviço responsável pela gestão de responsáveis (pessoas designadas a executar tarefas e acompanhar usuários).
 */
@Service
public class ResponsibleServiceImpl implements ResponsibleService {

    private final ResponsibleRepository responsibleRepository;
    private final UserRepository userRepository;
    private final ResponsibleMapper responsibleMapper;
    private final PasswordEncoder passwordEncoder;

    public ResponsibleServiceImpl(ResponsibleRepository responsibleRepository,
                                  UserRepository userRepository,
                                  ResponsibleMapper responsibleMapper,
                                  PasswordEncoder passwordEncoder) {
        this.responsibleRepository = responsibleRepository;
        this.userRepository = userRepository;
        this.responsibleMapper = responsibleMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public ResponsibleDTO create(ResponsibleDTO responsibleDTO) {
        User user = userRepository.findById(responsibleDTO.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Responsible responsible = responsibleMapper.toEntity(responsibleDTO);
        responsible.setUser(user);
        responsible.setPassword(passwordEncoder.encode(responsibleDTO.getPassword()));

        return responsibleMapper.toDTO(responsibleRepository.save(responsible));
    }

    @Override
    @Transactional
    public ResponsibleDTO update(Long id, ResponsibleDTO responsibleDTO) {
        Responsible existingResponsible = responsibleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Responsible not found"));

        User user = userRepository.findById(responsibleDTO.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        responsibleMapper.updateEntityFromDTO(responsibleDTO, existingResponsible);
        existingResponsible.setUser(user);

        if (isPasswordUpdateRequired(responsibleDTO.getPassword())) {
            existingResponsible.setPassword(passwordEncoder.encode(responsibleDTO.getPassword()));
        }

        return responsibleMapper.toDTO(responsibleRepository.save(existingResponsible));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!responsibleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Responsible not found");
        }
        responsibleRepository.deleteById(id);
    }

    private boolean isPasswordUpdateRequired(String newPassword) {
        return newPassword != null && !newPassword.trim().isEmpty();
    }

    @Override
    public ResponsibleDTO getById(Long id) {
        Responsible responsible = responsibleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Responsible not found"));
        return responsibleMapper.toDTO(responsible);
    }

    @Override
    public Page<ResponsibleDTO> listAll(int page, int size) {
        return responsibleRepository.findAll(PageRequest.of(page, size))
            .map(responsibleMapper::toDTO);
    }
}
