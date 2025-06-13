package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.LanguageDTO;
import grupo05.inclusiveaid.entity.Language;
import grupo05.inclusiveaid.mapper.LanguageMapper;
import grupo05.inclusiveaid.repository.LanguageRepository;
import grupo05.inclusiveaid.service.LanguageService;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl extends AbstractCrudServiceImpl<Language, LanguageDTO> implements LanguageService {
    public LanguageServiceImpl(LanguageRepository repo, LanguageMapper mapper) {
        super(repo, mapper);
    }
} 