package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.LanguageDTO;
import grupo05.inclusiveaid.entity.Language;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Mapper para Language.
 */
@Mapper(componentModel = "spring")
public interface LanguageMapper {
    Language toEntity(LanguageDTO dto);

    LanguageDTO toDto(Language entity);

    void updateEntity(LanguageDTO dto, @MappingTarget Language entity);
}