package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.LanguageDTO;
import grupo05.inclusiveaid.entity.Language;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper para Language.
 */
@Mapper(componentModel = "spring")
public interface LanguageMapper extends EntityMapper<Language, LanguageDTO> {
    @Override
    @org.mapstruct.Mapping(target = "id", ignore = true)
    Language toEntity(LanguageDTO dto);

    @Override
    LanguageDTO toDto(Language entity);

    @Override
    @org.mapstruct.Mapping(target = "id", ignore = true)
    void updateEntity(LanguageDTO dto, @MappingTarget Language entity);
}