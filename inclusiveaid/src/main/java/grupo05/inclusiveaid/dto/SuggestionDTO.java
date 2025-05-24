package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;

/**
 * DTO para Suggestion.
 */
@Data @Builder
public class SuggestionDTO {
  public interface Create {}
  private Long id;

  @NotNull(groups = Create.class)
  private Long layoutAnalysisId;

  @NotBlank(groups = Create.class)
  private String message;
}