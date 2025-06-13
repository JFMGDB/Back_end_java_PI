package grupo05.inclusiveaid.service.impl;

import grupo05.inclusiveaid.dto.AgentInteractionDTO;
import grupo05.inclusiveaid.entity.AgentInteraction;
import grupo05.inclusiveaid.mapper.AgentInteractionMapper;
import grupo05.inclusiveaid.repository.AgentInteractionRepository;
import grupo05.inclusiveaid.service.AgentInteractionService;
import org.springframework.stereotype.Service;

@Service
public class AgentInteractionServiceImpl extends AbstractCrudServiceImpl<AgentInteraction, AgentInteractionDTO>
        implements AgentInteractionService {

    public AgentInteractionServiceImpl(AgentInteractionRepository repo, AgentInteractionMapper mapper) {
        super(repo, mapper);
    }
} 