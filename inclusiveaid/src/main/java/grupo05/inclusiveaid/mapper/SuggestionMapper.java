package grupo05.inclusiveaid.mapper;

import grupo05.inclusiveaid.dto.SuggestionDTO;
import grupo05.inclusiveaid.entity.Suggestion;
import org.mapstruct.*;

/**
 * Mapeia Suggestion ↔ DTO.
 */
@Mapper(componentModel="spring")
public interface SuggestionMapper {
  @Mapping(source="analysis.id",target="layoutAnalysisId")
  SuggestionDTO toDto(Suggestion e);

  @Mapping(target="id",ignore=true)
  @Mapping(source="layoutAnalysisId",target="analysis.id")
  Suggestion toEntity(SuggestionDTO dto);
}