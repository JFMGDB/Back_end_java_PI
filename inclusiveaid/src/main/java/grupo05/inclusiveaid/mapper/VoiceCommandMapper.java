package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.VoiceCommandDTO;
import grupo05.inclusiveaid.entity.VoiceCommand;
import org.mapstruct.*;

/**
 * Mapeia VoiceCommand ↔ DTO.
 */
@Mapper(componentModel="spring")
public interface VoiceCommandMapper {
  @Mapping(source="session.id",target="sessionId")
  VoiceCommandDTO toDto(VoiceCommand e);

  @Mapping(target="id",ignore=true)
  @Mapping(source="sessionId",target="session.id")
  VoiceCommand toEntity(VoiceCommandDTO dto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "timestamp", ignore = true)
  @Mapping(target = "session", ignore = true)
  void updateEntity(VoiceCommandDTO dto, @MappingTarget VoiceCommand entity);
}