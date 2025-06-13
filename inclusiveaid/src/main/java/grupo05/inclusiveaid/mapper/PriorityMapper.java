package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.PriorityDTO;
import grupo05.inclusiveaid.entity.Priority;
import grupo05.inclusiveaid.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@SuppressWarnings("unused")  
@Mapper(componentModel = "spring")
public interface PriorityMapper extends EntityMapper<Priority, PriorityDTO> {
    @Override
    Priority toEntity(PriorityDTO dto);

    @Override
    PriorityDTO toDto(Priority entity);

    @Override
    void updateEntity(PriorityDTO dto, @MappingTarget Priority entity);
}