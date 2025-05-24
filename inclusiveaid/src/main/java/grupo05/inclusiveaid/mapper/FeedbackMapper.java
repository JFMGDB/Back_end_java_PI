package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.FeedbackDTO;
import grupo05.inclusiveaid.entity.Feedback;
import org.mapstruct.*;

/**
 * Mapeia Feedback ↔ DTO.
 */
@Mapper(componentModel="spring")
public interface FeedbackMapper {
  @Mapping(source="user.id",target="userId")
  FeedbackDTO toDto(Feedback e);

  @Mapping(target="id",ignore=true)
  @Mapping(source="userId",target="user.id")
  Feedback toEntity(FeedbackDTO dto);
}