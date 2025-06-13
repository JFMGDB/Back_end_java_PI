package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.VoiceCommandDTO;
import grupo05.inclusiveaid.entity.VoiceCommand;
import org.mapstruct.*;

/**
 * Mapeia VoiceCommand ↔ DTO.
 */
@Mapper(componentModel="spring")
public interface VoiceCommandMapper {
  @Mapping(target = "userId", ignore = true)
  @Mapping(target = "confidence", ignore = true)
  @Mapping(target = "status", ignore = true)
  VoiceCommandDTO toDto(VoiceCommand e);

  @Mapping(target="id",ignore=true)
  @Mapping(target="session",ignore=true)
  @Mapping(target="result",ignore=true)
  VoiceCommand toEntity(VoiceCommandDTO dto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "timestamp", ignore = true)
  @Mapping(target = "session", ignore = true)
  @Mapping(target = "result", ignore = true)
  void updateEntity(VoiceCommandDTO dto, @MappingTarget VoiceCommand entity);
}