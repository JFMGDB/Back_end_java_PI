package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.AdaptationDTO;
import grupo05.inclusiveaid.entity.Adaptation;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.AdaptationMapper;
import grupo05.inclusiveaid.repository.AdaptationRepository;
import grupo05.inclusiveaid.service.AdaptationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementação de AdaptationService.
 */
@Service
@RequiredArgsConstructor
public class AdaptationServiceImpl implements AdaptationService {
    private final AdaptationRepository adaptationRepository;
    private final AdaptationMapper adaptationMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<AdaptationDTO> listAll(int page, int size) {
        return adaptationRepository.findAll(PageRequest.of(page, size))
                .map(adaptationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public AdaptationDTO getById(Long id) {
        return adaptationRepository.findById(id)
                .map(adaptationMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Adaptation not found with id: " + id));
    }

    @Override
    @Transactional
    public AdaptationDTO create(AdaptationDTO adaptationDTO) {
        Adaptation adaptation = adaptationMapper.toEntity(adaptationDTO);
        Adaptation savedAdaptation = adaptationRepository.save(adaptation);
        return adaptationMapper.toDto(savedAdaptation);
    }

    @Override
    @Transactional
    public AdaptationDTO update(Long id, AdaptationDTO adaptationDTO) {
        Adaptation adaptation = adaptationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adaptation not found with id: " + id));
        adaptationMapper.updateEntity(adaptationDTO, adaptation);
        return adaptationMapper.toDto(adaptationRepository.save(adaptation));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!adaptationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Adaptation not found with id: " + id);
        }
        adaptationRepository.deleteById(id);
    }
}