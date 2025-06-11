package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.LayoutAnalysisDTO;
import grupo05.inclusiveaid.entity.LayoutAnalysis;
import org.mapstruct.*;

/**
 * Mapeia LayoutAnalysis ↔ DTO.
 */
@Mapper(componentModel="spring")
public interface LayoutAnalysisMapper {
  @Mapping(source = "session.user.id", target = "userId")
  @Mapping(source = "details", target = "analysis")
  @Mapping(source = "timestamp", target = "timestamp")
  LayoutAnalysisDTO toDto(LayoutAnalysis entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "session", ignore = true)
  @Mapping(target = "details", expression = "java(dto.getAnalysis())")
  @Mapping(target = "timestamp", expression = "java(java.time.Instant.now())")
  LayoutAnalysis toEntity(LayoutAnalysisDTO dto);
}