package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.LayoutAnalysisDTO;
import grupo05.inclusiveaid.entity.LayoutAnalysis;
import org.mapstruct.*;

/**
 * Mapeia LayoutAnalysis ↔ DTO.
 */
@Mapper(componentModel="spring")
public interface LayoutAnalysisMapper {
  @Mapping(source="session.id",target="sessionId")
  LayoutAnalysisDTO toDto(LayoutAnalysis e);

  @Mapping(target="id",ignore=true)
  @Mapping(source="sessionId",target="session.id")
  LayoutAnalysis toEntity(LayoutAnalysisDTO dto);
}