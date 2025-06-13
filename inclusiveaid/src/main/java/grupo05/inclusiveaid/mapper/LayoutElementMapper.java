package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.LayoutElementDTO;
import grupo05.inclusiveaid.entity.LayoutElement;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LayoutElementMapper extends EntityMapper<LayoutElement, LayoutElementDTO> {
    @Override
    LayoutElementDTO toDto(LayoutElement entity);

    @Override
    @Mapping(target = "id", ignore = true)
    LayoutElement toEntity(LayoutElementDTO dto);

    @Override
    @Mapping(target = "id", ignore = true)
    void updateEntity(LayoutElementDTO dto, @MappingTarget LayoutElement entity);
} 