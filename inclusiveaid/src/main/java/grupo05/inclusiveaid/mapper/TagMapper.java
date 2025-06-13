package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.TagDTO;
import grupo05.inclusiveaid.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper para entidade Tag.
 */
@Mapper(componentModel = "spring")
public interface TagMapper extends EntityMapper<Tag, TagDTO> {
    @Override
    Tag toEntity(TagDTO dto);

    @Override
    TagDTO toDto(Tag entity);

    @Override
    void updateEntity(TagDTO dto, @MappingTarget Tag entity);
}
