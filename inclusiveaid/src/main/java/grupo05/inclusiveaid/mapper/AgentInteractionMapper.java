package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.AgentInteractionDTO;
import grupo05.inclusiveaid.entity.AgentInteraction;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AgentInteractionMapper extends EntityMapper<AgentInteraction, AgentInteractionDTO> {
    @Mapping(source = "agent.id", target = "agentId")
    @Mapping(source = "user.id", target = "userId")
    @Override
    AgentInteractionDTO toDto(AgentInteraction entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "context", ignore = true)
    @Mapping(source = "agentId", target = "agent.id")
    @Mapping(source = "userId", target = "user.id")
    @Override
    AgentInteraction toEntity(AgentInteractionDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "context", ignore = true)
    @Mapping(source = "agentId", target = "agent.id")
    @Mapping(source = "userId", target = "user.id")
    @Override
    void updateEntity(AgentInteractionDTO dto, @MappingTarget AgentInteraction entity);
} 