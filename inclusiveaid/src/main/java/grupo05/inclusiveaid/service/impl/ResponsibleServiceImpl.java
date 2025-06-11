package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.ResponsibleDTO;
import grupo05.inclusiveaid.entity.Responsible;
import grupo05.inclusiveaid.entity.User;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.ResponsibleMapper;
import grupo05.inclusiveaid.repository.ResponsibleRepository;
import grupo05.inclusiveaid.repository.UserRepository;
import grupo05.inclusiveaid.service.ResponsibleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResponsibleServiceImpl implements ResponsibleService {
    private final ResponsibleRepository responsibleRepository;
    private final UserRepository userRepository;
    private final ResponsibleMapper responsibleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<ResponsibleDTO> getAllResponsibles() {
        return responsibleRepository.findAll().stream()
            .map(responsibleMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public ResponsibleDTO getResponsibleById(Long id) {
        return responsibleRepository.findById(id)
            .map(responsibleMapper::toDTO)
            .orElseThrow(() -> new ResourceNotFoundException("Responsible not found"));
    }

    @Override
    @Transactional
    public ResponsibleDTO createResponsible(ResponsibleDTO responsibleDTO) {
        User user = userRepository.findById(responsibleDTO.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Responsible responsible = responsibleMapper.toEntity(responsibleDTO);
        responsible.setUser(user);
        responsible.setPassword(passwordEncoder.encode(responsibleDTO.getPassword()));

        return responsibleMapper.toDTO(responsibleRepository.save(responsible));
    }

    @Override
    @Transactional
    public ResponsibleDTO updateResponsible(Long id, ResponsibleDTO responsibleDTO) {
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
    public void deleteResponsible(Long id) {
        if (!responsibleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Responsible not found");
        }
        responsibleRepository.deleteById(id);
    }

    private boolean isPasswordUpdateRequired(String newPassword) {
        return newPassword != null && !newPassword.trim().isEmpty();
    }
}
