package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.PriorityDTO;
import grupo05.inclusiveaid.entity.Priority;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PriorityMapper {
    Priority toEntity(PriorityDTO dto);

    PriorityDTO toDto(Priority entity);

    void updateEntity(PriorityDTO dto, @MappingTarget Priority entity);
}