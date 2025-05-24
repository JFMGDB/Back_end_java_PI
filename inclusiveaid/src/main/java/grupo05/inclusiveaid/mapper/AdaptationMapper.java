package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.AdaptationDTO;
import grupo05.inclusiveaid.entity.Adaptation;
import org.mapstruct.*;

/**
 * Mapeia Adaptation ↔ DTO.
 */
@Mapper(componentModel="spring")
public interface AdaptationMapper {
  @Mapping(source="disabilityType.id",target="disabilityTypeId")
  AdaptationDTO toDto(Adaptation e);

  @Mapping(target="id",ignore=true)
  @Mapping(source="disabilityTypeId",target="disabilityType.id")
  Adaptation toEntity(AdaptationDTO dto);
}