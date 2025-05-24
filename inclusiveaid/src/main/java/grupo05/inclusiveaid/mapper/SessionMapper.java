package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.SessionDTO;
import grupo05.inclusiveaid.entity.Session;
import org.mapstruct.*;

/**
 * Mapeia Session ↔ DTO.
 */
@Mapper(componentModel="spring")
public interface SessionMapper {
  @Mapping(source="user.id",target="userId")
  SessionDTO toDto(Session e);

  @Mapping(target="id",ignore=true)
  @Mapping(source="userId",target="user.id")
  Session toEntity(SessionDTO dto);
}