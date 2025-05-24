package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;

/**
 * DTO para LayoutAnalysis.
 */
@Data @Builder
public class LayoutAnalysisDTO {
  public interface Create {}
  private Long id;

  @NotNull(groups = Create.class)
  private Long sessionId;

  @NotBlank(groups = Create.class)
  private String details;
}