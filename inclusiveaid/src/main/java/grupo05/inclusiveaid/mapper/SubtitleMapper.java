package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.SubtitleDTO;
import grupo05.inclusiveaid.entity.Subtitle;
import org.mapstruct.*;

/**
 * Mapeia Subtitle ↔ DTO.
 */
@Mapper(componentModel="spring")
public interface SubtitleMapper {
  @Mapping(source="session.id",target="sessionId")
  SubtitleDTO toDto(Subtitle e);

  @Mapping(target="id",ignore=true)
  @Mapping(source="sessionId",target="session.id")
  Subtitle toEntity(SubtitleDTO dto);
}