package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.PriorityDTO;
import grupo05.inclusiveaid.entity.Priority;
import grupo05.inclusiveaid.mapper.PriorityMapper;
import grupo05.inclusiveaid.repository.PriorityRepository;
import grupo05.inclusiveaid.service.PriorityService;
import org.springframework.stereotype.Service;

@Service
public class PriorityServiceImpl extends AbstractCrudServiceImpl<Priority, PriorityDTO> implements PriorityService {
    public PriorityServiceImpl(PriorityRepository repo, PriorityMapper mapper) {
        super(repo, mapper);
    }
} 