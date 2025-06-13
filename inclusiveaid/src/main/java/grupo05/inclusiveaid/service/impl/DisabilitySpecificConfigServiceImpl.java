package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.DisabilitySpecificConfigDTO;
import grupo05.inclusiveaid.entity.DisabilitySpecificConfig;
import grupo05.inclusiveaid.mapper.DisabilitySpecificConfigMapper;
import grupo05.inclusiveaid.repository.DisabilitySpecificConfigRepository;
import grupo05.inclusiveaid.service.DisabilitySpecificConfigService;
import org.springframework.stereotype.Service;

@Service
public class DisabilitySpecificConfigServiceImpl
        extends AbstractCrudServiceImpl<DisabilitySpecificConfig, DisabilitySpecificConfigDTO>
        implements DisabilitySpecificConfigService {

    public DisabilitySpecificConfigServiceImpl(DisabilitySpecificConfigRepository repo,
                                               DisabilitySpecificConfigMapper mapper) {
        super(repo, mapper);
    }
} 