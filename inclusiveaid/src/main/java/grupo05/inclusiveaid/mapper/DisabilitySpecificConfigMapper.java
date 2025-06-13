package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.DisabilitySpecificConfigDTO;
import grupo05.inclusiveaid.entity.DisabilitySpecificConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapper para DisabilitySpecificConfig ↔ DTO.
 */
@Mapper(componentModel = "spring")
public interface DisabilitySpecificConfigMapper extends EntityMapper<DisabilitySpecificConfig, DisabilitySpecificConfigDTO> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "agent.id", source = "agentId")
    @Mapping(target = "disabilityType.id", source = "disabilityTypeId")
    DisabilitySpecificConfig toEntity(DisabilitySpecificConfigDTO dto);

    @Mapping(target = "agentId", source = "agent.id")
    @Mapping(target = "disabilityTypeId", source = "disabilityType.id")
    DisabilitySpecificConfigDTO toDto(DisabilitySpecificConfig entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "agent", ignore = true)
    @Mapping(target = "disabilityType", ignore = true)
    void updateEntity(DisabilitySpecificConfigDTO dto, @MappingTarget DisabilitySpecificConfig entity);
} 