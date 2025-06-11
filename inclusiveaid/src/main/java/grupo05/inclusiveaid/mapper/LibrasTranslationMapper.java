package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.LibrasTranslationDTO;
import grupo05.inclusiveaid.entity.LibrasTranslation;
import org.mapstruct.*;

/**
 * Mapeia LibrasTranslation ↔ DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LibrasTranslationMapper {
    @Mapping(target = "isActive", source = "active")
    LibrasTranslationDTO toDto(LibrasTranslation entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", source = "isActive", defaultValue = "true")
    @Mapping(target = "originalText", source = "originalText")
    @Mapping(target = "avatarAnimationUrl", source = "avatarAnimationUrl")
    LibrasTranslation toEntity(LibrasTranslationDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", source = "isActive", defaultValue = "true")
    @Mapping(target = "originalText", source = "originalText")
    @Mapping(target = "avatarAnimationUrl", source = "avatarAnimationUrl")
    void updateEntity(LibrasTranslationDTO dto, @MappingTarget LibrasTranslation entity);
} 