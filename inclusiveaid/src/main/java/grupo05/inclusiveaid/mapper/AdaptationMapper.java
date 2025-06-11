package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.AdaptationDTO;
import grupo05.inclusiveaid.entity.Adaptation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Mapeia Adaptation ↔ DTO.
 */
@Mapper(componentModel = "spring")
public interface AdaptationMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "disabilityType.id", source = "disabilityTypeId")
    Adaptation toEntity(AdaptationDTO dto);

    @Mapping(target = "disabilityTypeId", source = "disabilityType.id")
    AdaptationDTO toDto(Adaptation entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "disabilityType", ignore = true)
    void updateEntity(AdaptationDTO dto, @MappingTarget Adaptation entity);
}