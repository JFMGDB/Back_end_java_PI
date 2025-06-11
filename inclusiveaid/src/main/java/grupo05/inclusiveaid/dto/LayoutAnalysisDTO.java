package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

/**
 * DTO para LayoutAnalysis.
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class LayoutAnalysisDTO {
  public interface Create {}
  public interface Update {}
  
  private Long id;

  @NotNull(groups = {Create.class, Update.class})
  private Long userId;

  @NotBlank(groups = {Create.class, Update.class})
  private String analysis;

  private Instant timestamp;
}