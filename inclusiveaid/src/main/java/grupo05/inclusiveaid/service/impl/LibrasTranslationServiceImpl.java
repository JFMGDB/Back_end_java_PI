package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.LibrasTranslationDTO;
import grupo05.inclusiveaid.entity.LibrasTranslation;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.LibrasTranslationMapper;
import grupo05.inclusiveaid.repository.LibrasTranslationRepository;
import grupo05.inclusiveaid.service.LibrasTranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementação do serviço de traduções em Libras.
 */
@Service
@RequiredArgsConstructor
public class LibrasTranslationServiceImpl implements LibrasTranslationService {

    private final LibrasTranslationRepository repository;
    private final LibrasTranslationMapper mapper;

    @Override
    @Transactional
    public LibrasTranslationDTO create(LibrasTranslationDTO dto) {
        LibrasTranslation entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public LibrasTranslationDTO update(Long id, LibrasTranslationDTO dto) {
        LibrasTranslation entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Tradução não encontrada com id: " + id));
        
        mapper.updateEntity(dto, entity);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Tradução não encontrada com id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public LibrasTranslationDTO getById(Long id) {
        return repository.findById(id)
            .map(mapper::toDto)
            .orElseThrow(() -> new ResourceNotFoundException("Tradução não encontrada com id: " + id));
    }

    @Override
    public Page<LibrasTranslationDTO> listAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
            .map(mapper::toDto);
    }
} 