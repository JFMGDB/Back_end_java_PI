package grupo05.inclusiveaid.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.Instant;

/**
 * DTO para Feedback.
 */
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class FeedbackDTO {
  public interface Create {}
  public interface Update {}
  private Long id;

  @NotNull(groups = {Create.class, Update.class})
  private Long userId;

  @NotBlank(groups = {Create.class, Update.class})
  private String message;

  private Instant timestamp;
}