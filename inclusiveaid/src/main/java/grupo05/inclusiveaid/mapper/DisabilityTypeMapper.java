package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.DisabilityTypeDTO;
import grupo05.inclusiveaid.entity.DisabilityType;
import org.mapstruct.*;

/**
 * Mapeia DisabilityType ↔ DTO.
 */
@Mapper(componentModel="spring")
public interface DisabilityTypeMapper {
  DisabilityTypeDTO toDto(DisabilityType e);

  @Mapping(target="id", ignore=true)
  DisabilityType toEntity(DisabilityTypeDTO dto);
}