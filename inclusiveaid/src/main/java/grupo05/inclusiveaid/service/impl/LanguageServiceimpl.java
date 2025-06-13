package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.LanguageDTO;
import grupo05.inclusiveaid.entity.Language;
import grupo05.inclusiveaid.exception.ResourceNotFoundException;
import grupo05.inclusiveaid.mapper.LanguageMapper;
import grupo05.inclusiveaid.repository.LanguageRepository;
import grupo05.inclusiveaid.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementação de {@link LanguageService}.
 */
@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    @Override
    @Transactional
    public LanguageDTO create(LanguageDTO dto) {
        Language language = languageMapper.toEntity(dto);
        return languageMapper.toDto(languageRepository.save(language));
    }

@Override 
public LanguageDTO getById(Long id) { 
return languageMapper.toDto(languageRepository.findById(id) 
.orElseThrow(() -> new ResourceNotFoundException("Language 
not found"))); 
}

    @Override
    public Page<LanguageDTO> listAll(int page, int size) {
        return languageRepository.findAll(PageRequest.of(page, size))
                .map(languageMapper::toDto);
    }

    @Override
    @Transactional
    public LanguageDTO update(Long id, LanguageDTO dto) {
        if (!languageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Language not found");
        }
        Language language = languageMapper.toEntity(dto);
        language.setId(id);
        return languageMapper.toDto(languageRepository.save(language));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!languageRepository.existsById(id)) {
            throw new ResourceNotFoundException("Language not found");
        }
        languageRepository.deleteById(id);
    }
}